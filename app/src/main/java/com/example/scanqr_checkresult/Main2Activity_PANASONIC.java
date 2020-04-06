package com.example.scanqr_checkresult;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

import static com.example.scanqr_checkresult.MainActivity.PERMISSION_READ_STATE;

public class Main2Activity_PANASONIC extends AppCompatActivity {

    public static TextView date2,tv_ReadResult1PANA,tv_ReadResult2PANA,tv_ReadResult3PANA,resultPANA,nameDevice_main_pana,IMEINumber_main_pana;
    public static Button QRScanner1,QRScanner2,btn_save_activity_main2;
    private ImageView im_back_arrowpana;
    public static RelativeLayout rt_QRScanner1PANA,rt_QRScanner2PANA,rt_QRScanner3PANA;
    private DatabaseReference firebaseReference;
    private List<Main2Activity_PANASONIC_GetSetDB> main2Activity_panasonic_getSetDBS;
    String DeviceName;
    String info;
    String strPhoneType = "";
    static  final  int  PERMISSION_READ_STATE =123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2_panasonic);


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
                                TextView tdate = (TextView) findViewById(R.id.date_activity_main2);
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
        IMEINumber_main_pana = (TextView) findViewById(R.id.IMEINumber_main2_activity);
        IMEINumber_main_pana.setText(info);

    }

        private void initFirebase(){

        firebaseReference = FirebaseDatabase.getInstance().getReference();
    }


    private void initInstances(){

        // CheckQRCode2();
        im_back_arrowpana = (ImageView) findViewById(R.id.im_back_arrow_pana);
        rt_QRScanner1PANA = (RelativeLayout) findViewById(R.id.rt_QRScanner1_Panasonic);
        rt_QRScanner2PANA= (RelativeLayout) findViewById(R.id.rt_QRScanner2_Panasonic);
//        rt_QRScanner3PANA= (RelativeLayout) findViewById(R.id.rt_QRScanner3_Panasonic);
        resultPANA = (TextView) findViewById(R.id.result_Panasonic);
        tv_ReadResult1PANA = (TextView) findViewById(R.id.tv_ReadResult1_Panasonic);
        tv_ReadResult2PANA = (TextView) findViewById(R.id.tv_ReadResult2_Panasonic);
        nameDevice_main_pana = (TextView) findViewById(R.id.nameDevice_main2_activity);
        date2 = (TextView) findViewById(R.id.date_activity_main2);
        btn_save_activity_main2 = (Button) findViewById(R.id.btn_save_activity_main2);
        IMEINumber_main_pana = (TextView) findViewById(R.id.IMEINumber_main2_activity);
 //       tv_ReadResult3PANA = (TextView) findViewById(R.id.tv_ReadResult3_Panasonic);
        //QRScanner1 = (Button) findViewById(R.id.btnQRScanner1);
        //     QRScanner2 = (Button) findViewById(R.id.btnQRScanner2);
//        checkResult  = (Button)  findViewById(R.id.checkResult);

        im_back_arrowpana.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main2Activity_PANASONIC.this, Main_MenuPANASONIC.class);
                startActivity(intent);
                finish();
            }
        });

        rt_QRScanner1PANA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Main2Activity_PANASONIC.this, ScanQRresult_1PANASONIC.class));
                //rt_QRScanner1.setEnabled(false);
                //          checkResult.setEnabled(false);
                // rt_QRScanner2.setEnabled(true);




            }
        });

        rt_QRScanner2PANA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Main2Activity_PANASONIC.this, ScanQRresult_2PANASONIC.class));
//                rt_QRScanner1.setEnabled(true);
//                rt_QRScanner2.setEnabled(false);

            }

        });

//        rt_QRScanner3PANA.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(Main2Activity_PANASONIC.this, ScanQRresult_2KCE.class));
////               rt_QRScanner1.setEnabled(true);
// //               rt_QRScanner2.setEnabled(false);
//
//            }
//         });

        btn_save_activity_main2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (v == btn_save_activity_main2){
                    addData_Activity_Main2();
//                    Intent intent = new Intent(Main2Activity_PANASONIC.this,Main_MenuPANASONIC.class);
//                    startActivity(intent);
//                    finish();
                }
            }
        });

        main2Activity_panasonic_getSetDBS = new ArrayList<>();
    }

    private void addData_Activity_Main2(){

        DeviceName= android.os.Build.MANUFACTURER;
        nameDevice_main_pana.setText(DeviceName);

        String datetime = date2.getText().toString();
        String nDevice = nameDevice_main_pana.getText().toString();
        String Readresult1 = tv_ReadResult1PANA.getText().toString();
        String Readresult2 = tv_ReadResult2PANA.getText().toString();
        String result = resultPANA.getText().toString();
        String IMEINumber = IMEINumber_main_pana.getText().toString();

        if (!TextUtils.isEmpty(datetime)){
            String id = firebaseReference.child("TLM_PP_CheckLabel").push().getKey();
            Main2Activity_PANASONIC_GetSetDB  main2ActivityPanasonicGetSetDB = new Main2Activity_PANASONIC_GetSetDB();

            //main2ActivityPanasonicGetSetDB.setId(id);
            main2ActivityPanasonicGetSetDB.setCreatedDate(datetime);
            main2ActivityPanasonicGetSetDB.setMachine(nDevice);
            main2ActivityPanasonicGetSetDB.setScan1(Readresult1);
            main2ActivityPanasonicGetSetDB.setScan2(Readresult2);
            main2ActivityPanasonicGetSetDB.setStatus(result);
            main2ActivityPanasonicGetSetDB.setIMEI(IMEINumber);

            firebaseReference.child("TLM_PP_CheckLabel").child(id).setValue(main2ActivityPanasonicGetSetDB);
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
                main2Activity_panasonic_getSetDBS.clear();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()){
                    Main2Activity_PANASONIC_GetSetDB main2ActivityPanasonicGetSetDB = postSnapshot.getValue(Main2Activity_PANASONIC_GetSetDB.class);
                    main2Activity_panasonic_getSetDBS.add(main2ActivityPanasonicGetSetDB);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
