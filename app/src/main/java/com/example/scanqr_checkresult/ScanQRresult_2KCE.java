package com.example.scanqr_checkresult;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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
import com.google.zxing.Result;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class ScanQRresult_2KCE extends AppCompatActivity implements ZXingScannerView.ResultHandler{

    ZXingScannerView scannerView2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        scannerView2 = new ZXingScannerView(this);
        setContentView(scannerView2);


    }

    @Override
    public void handleResult(Result result) {
        MainActivity.resultKCE.setBackgroundResource(R.drawable.frame_btn_disible);
        MainActivity.tv_ReadResult2KCE.setText("");
        MainActivity.resultKCE.setText("");

        String ReadResult2kce= result.getText();
        int countqrcode = ReadResult2kce.length();

        String ReadResult1Kce = MainActivity.tv_ReadResult1KCE.getText().toString();
        String cutqrcode1 = ReadResult1Kce.substring(countqrcode-2);
        String TypeQRCode1 = "";

        if(cutqrcode1.equals("1|")|| cutqrcode1.equals("2|") || cutqrcode1.equals("3|")) {
            TypeQRCode1 = "New";
        }
        else
        {
            TypeQRCode1 = "Old";
        }
        String cutqrcode2 = ReadResult2kce.substring(countqrcode-2);
        String TypeQRCode2 = "";
        if(cutqrcode2.equals("1|")|| cutqrcode2.equals("2|") || cutqrcode2.equals("3|")) {
            TypeQRCode2 = "New";
        }
        else
        {
            TypeQRCode2 = "Old";
        }

        if (TypeQRCode1.compareTo(TypeQRCode2) == 0){
            //------------------NEW------------------------------------------------------------
            if (TypeQRCode1.compareTo("New") == 0)
            {
                if(cutqrcode2.equals("2|")){
                    QRCode_Format_New2(ReadResult2kce);
                    MainActivity.btn_save_activity_main.performClick();

                }
                else {
                    MainActivity.tv_ReadResult2KCE.setText("คุณยิงสติกเกอร์ไม่ถูกต้อง");
                    MainActivity.rt_QRScanner1KCE.setEnabled(false);
                    MainActivity.rt_QRScanner2KCE.setEnabled(true);
                }
            }
           else
           {
               if(!cutqrcode2.equals("2|")){
                   QRCode_Format_Old2(ReadResult2kce);
               }
               else {
                   MainActivity.tv_ReadResult2KCE.setText("คุณยิงสติกเกอร์ไม่ถูกต้อง");
                   MainActivity.rt_QRScanner1KCE.setEnabled(false);
                   MainActivity.rt_QRScanner2KCE.setEnabled(true);
               }
           }
        }

        else{
                MainActivity.tv_ReadResult2KCE.setText("คุณยิงสติกเกอร์ไม่ถูกต้อง");
                MainActivity.rt_QRScanner1KCE.setEnabled(false);
                MainActivity.rt_QRScanner2KCE.setEnabled(true);
        }
        onBackPressed();
    }

    @Override
    protected void onResume() {
        super.onResume();
        scannerView2.setResultHandler(this);
        scannerView2.startCamera();
    }

    @Override
    protected void onPause() {
        super.onPause();
        scannerView2.stopCamera();
    }

public void QRCode_Format_New2(String pResult2kce){
    if (pResult2kce != ""){
            CheckQRCode2_New(pResult2kce);

    }
    else
    {
        MainActivity.tv_ReadResult2KCE.setText("ไม่พบข้อมูล");
        MainActivity.rt_QRScanner1KCE.setEnabled(false);
        MainActivity.rt_QRScanner2KCE.setEnabled(true);
    }
}

    public void QRCode_Format_Old2(String pResult2kce){
        if (pResult2kce != "") {
            int countqrcode = pResult2kce.length();
            String cutqrcode = pResult2kce.substring(countqrcode-2);
            MainActivity.resultKCE.setBackgroundResource(R.drawable.frame_btn_disible);

            CheckQRCode2_Old(pResult2kce);
        }
        else
        {
            MainActivity.tv_ReadResult2KCE.setText("ไม่พบข้อมูล");
            MainActivity.rt_QRScanner1KCE.setEnabled(false);
            MainActivity.rt_QRScanner2KCE.setEnabled(true);
        }
    }
    public void CheckQRCode2_New(String pResult2kce) {
        String ReadResult1kce = MainActivity.tv_ReadResult1KCE.getText().toString();
        String ReadResult2kce = pResult2kce;
        MainActivity.tv_ReadResult2KCE.setText(pResult2kce);

        String subResult1 = ReadResult1kce.substring(0 ,ReadResult1kce.length()-2);
        String subResult2 = ReadResult2kce.substring(0 ,ReadResult2kce.length()-2);

        if (Objects.equals(subResult1, subResult2)) {
            MainActivity.resultKCE.setText("ถูกต้อง");
            MainActivity.resultKCE.setBackgroundResource(R.drawable.fram_btn_yes);
            MainActivity.rt_QRScanner1KCE.setEnabled(true);
            MainActivity.rt_QRScanner2KCE.setEnabled(false);
        }
        else {
            MainActivity.resultKCE.setText("ไม่ถูกต้อง");
            MainActivity.resultKCE.setBackgroundResource(R.drawable.frame_btn_no);
            MainActivity.rt_QRScanner1KCE.setEnabled(false);
            MainActivity.rt_QRScanner2KCE.setEnabled(true);
        }
    }
    public void CheckQRCode2_Old(String pResult2kce) {

        String ReadResult1kce = MainActivity.tv_ReadResult1KCE.getText().toString();
        String ReadResult2kce = pResult2kce;
        MainActivity.tv_ReadResult2KCE.setText(pResult2kce);
        if (Objects.equals(ReadResult1kce, ReadResult2kce)) {
            MainActivity.resultKCE.setText("ถูกต้อง");
            MainActivity.resultKCE.setBackgroundResource(R.drawable.fram_btn_yes);
            MainActivity.rt_QRScanner1KCE.setEnabled(true);
            MainActivity.rt_QRScanner2KCE.setEnabled(false);
        }
        else {
            MainActivity.resultKCE.setText("ไม่ถูกต้อง");
            MainActivity.resultKCE.setBackgroundResource(R.drawable.frame_btn_no);
            MainActivity.rt_QRScanner1KCE.setEnabled(false);
            MainActivity.rt_QRScanner2KCE.setEnabled(true);
        }

    }

}
