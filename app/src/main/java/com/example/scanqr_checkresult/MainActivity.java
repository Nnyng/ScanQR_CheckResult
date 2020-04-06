package com.example.scanqr_checkresult;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static TextView date,tv_ReadResult1KCE,tv_ReadResult2KCE,resultKCE,nameDevice_main_kce,IMEINumber_main_kce;
    public static Button QRScanner1,QRScanner2,btn_save_activity_main;
    private ImageView im_back_arrow;
    private DatabaseReference firebaseReference;
    private List<MainActivity_GetSetDB> mainActivity_getSetDBS;
    public static RelativeLayout  rt_QRScanner1KCE,rt_QRScanner2KCE;

    String DeviceName;
    String info;
    String strPhoneType = "";
    static  final  int  PERMISSION_READ_STATE =123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initInstances();
        initFirebase();
        showData();

        Thread t = new Thread(){
            @Override
            public void run() {
                try {
                    while (!isInterrupted()){
                        Thread.sleep(1000);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                TextView tdate = (TextView) findViewById(R.id.date_activity_main);
                                long date = System.currentTimeMillis();
                                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                String dateString = sdf.format(date);
                                tdate.setText(dateString);
                            }
                        });
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        t.start();


        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE);

        if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
            MyTelephonyManager();
        } else {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_PHONE_STATE},
                    PERMISSION_READ_STATE);
        }

//        String imei = device.getDeviceId();
//        String SimSerialNo = device.getSimSerialNumber();
//        String simOperator = device.getSimOperator();
//        String NetworkCountryISO=device.getNetworkCountryIso();
//        String SIMCountryISO=device.getSimCountryIso();
//        String SoftwareVersion=device.getDeviceSoftwareVersion();
//        String voiceMailNumber=device.getVoiceMailNumber();
//
//        String text = "Imei : "+ imei + "\n SimSerialNo : " + SimSerialNo + "\n Sim Operator : "
//                + simOperator + "\n NetworkCountryISO : " + NetworkCountryISO +
//                "\n SIMCountryISO : " + SIMCountryISO + "\n SoftwareVersion : " + SoftwareVersion +
//                "\n voiceMailNumber : " + voiceMailNumber;
//
//        IMEINumber_main_kce.setText(text);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case PERMISSION_READ_STATE: {
                if (grantResults.length >= 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    MyTelephonyManager();
                } else {
                    Toast.makeText(this, "You don't have required permission to make the Action", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }


    private void MyTelephonyManager() {
        TelephonyManager manager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        int phoneType = manager.getPhoneType();
        switch (phoneType) {
            case (TelephonyManager.PHONE_TYPE_CDMA):
                strPhoneType = "CDMA";
                break;

            case (TelephonyManager.PHONE_TYPE_GSM):
                strPhoneType = "GSM";
                break;

            case (TelephonyManager.PHONE_TYPE_NONE):
                strPhoneType = "NONE";
                break;
        }

        boolean isRoaming = manager.isNetworkRoaming();

        String PhoneType = strPhoneType;
        String IMEINumber = manager.getDeviceId();
      //  String voiceMailNumber = manager.getVoiceMailNumber();

        info = IMEINumber;
       // info += "\n Voice Mail Number:" + voiceMailNumber;
        IMEINumber_main_kce = (TextView) findViewById(R.id.IMEINumber_main_activity);
        IMEINumber_main_kce.setText(info);

    }


    private void initFirebase(){

        firebaseReference = FirebaseDatabase.getInstance().getReference();
    }

    private void initInstances(){

        date = (TextView) findViewById(R.id.date_activity_main);
        im_back_arrow = (ImageView) findViewById(R.id.im_back_arrow_kce);
        rt_QRScanner1KCE = (RelativeLayout) findViewById(R.id.rt_QRScanner1_kce);
        rt_QRScanner2KCE= (RelativeLayout) findViewById(R.id.rt_QRScanner2_kce);
        resultKCE = (TextView) findViewById(R.id.result_kce);
        nameDevice_main_kce = (TextView) findViewById(R.id.nameDevice_main_activity);
        IMEINumber_main_kce = (TextView) findViewById(R.id.IMEINumber_main_activity);
        tv_ReadResult1KCE = (TextView) findViewById(R.id.tv_ReadResult1_kce);
        tv_ReadResult2KCE = (TextView) findViewById(R.id.tv_ReadResult2_kce);
        btn_save_activity_main = (Button) findViewById(R.id.btn_save_activity_main);


        im_back_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Main_MenuKCE.class);
                startActivity(intent);
                finish();
            }
        });

        rt_QRScanner1KCE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ScanQRresult_1KCE.class));


            }
        });

        rt_QRScanner2KCE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ScanQRresult_2KCE.class));

            }

        });

        btn_save_activity_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (v == btn_save_activity_main){
                    addData_Activity_Main();

                }
            }
        });

        mainActivity_getSetDBS = new ArrayList<>();
    }

    private void addData_Activity_Main(){

//        TelephonyManager tMgr = (TelephonyManager)mAppContext.getSystemService(Context.TELEPHONY_SERVICE);
////        String mPhoneNumber = tMgr.getLine1Number();


        DeviceName= android.os.Build.MANUFACTURER;
        nameDevice_main_kce.setText(DeviceName);

        String datetime = date.getText().toString();
        String nDevice = nameDevice_main_kce.getText().toString();
        String Readresult1 = tv_ReadResult1KCE.getText().toString();
        String Readresult2 = tv_ReadResult2KCE.getText().toString();
        String result = resultKCE.getText().toString();
        String IMEINumber = IMEINumber_main_kce.getText().toString();


        if (!TextUtils.isEmpty(datetime)){
            String id = firebaseReference.child("TLM_PP_CheckLabel").push().getKey();
            MainActivity_GetSetDB mainActivity_getSetDB = new MainActivity_GetSetDB();

           // mainActivity_getSetDB.setId_main_activity(id);
            mainActivity_getSetDB.setCreatedDate(datetime);
            mainActivity_getSetDB.setMachine(nDevice);
            mainActivity_getSetDB.setScan1(Readresult1);
            mainActivity_getSetDB.setScan2(Readresult2);
            mainActivity_getSetDB.setStatus(result);
            mainActivity_getSetDB.setIMEI(IMEINumber);

            firebaseReference.child("TLM_PP_CheckLabel").child(id).setValue(mainActivity_getSetDB);
            Toast.makeText(this,"Checking Successful",Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this,"Please fill your information completely",Toast.LENGTH_LONG).show();
        }
    }

    private void showData(){
        Query query = firebaseReference.child("TLM_PP_CheckLabel");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mainActivity_getSetDBS.clear();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()){
                    MainActivity_GetSetDB mainActivityGetSetDB = postSnapshot.getValue(MainActivity_GetSetDB.class);
                    mainActivity_getSetDBS.add(mainActivityGetSetDB);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}