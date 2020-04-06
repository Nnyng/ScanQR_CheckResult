package com.example.scanqr_checkresult;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class ScanQRresult_1KCE extends AppCompatActivity implements ZXingScannerView.ResultHandler {

    ZXingScannerView scannerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        scannerView = new ZXingScannerView(this);
        setContentView(scannerView);
    }

    @Override
    public void handleResult(Result result) {

        MainActivity.tv_ReadResult1KCE.setText("");
        MainActivity.resultKCE.setText("");
        MainActivity.tv_ReadResult2KCE.setText("");
        String ReadResult1kce = result.getText();
        int countqrcode = ReadResult1kce.length();
        String cutqrcode = ReadResult1kce.substring(countqrcode-2);
        MainActivity.resultKCE.setBackgroundResource(R.drawable.frame_btn_disible);
        if(cutqrcode.equals("1|")|| cutqrcode.equals("2|") || cutqrcode.equals("3|")) {
            QRCode_Format_New(ReadResult1kce);


        }else {
            QRCode_Format_Old(ReadResult1kce);
        }

       onBackPressed();

    }

    @Override
    protected void onResume() {
        super.onResume();
        scannerView.setResultHandler(this);
        scannerView.startCamera();
    }

    @Override
    protected void onPause() {
        super.onPause();
        scannerView.stopCamera();
    }



    public void QRCode_Format_New(String pResult1kce){
        if (pResult1kce != "") {
            String Type = pResult1kce.substring(64,65);
            String Value = pResult1kce.substring(0,64);
            if (Type.equals("1")) {
                MainActivity.tv_ReadResult1KCE.setText(Value);
                CheckScan_Enable(pResult1kce);
            }
            else
            {
                MainActivity.tv_ReadResult1KCE.setText("คุณยิงสติกเกอร์ไม่ถูกต้อง");
                MainActivity.rt_QRScanner1KCE.setEnabled(true);
                MainActivity.rt_QRScanner2KCE.setEnabled(false);
            }
        }
        else
        {
            MainActivity.tv_ReadResult1KCE.setText("ไม่พบข้อมูล");
            MainActivity.rt_QRScanner1KCE.setEnabled(true);
            MainActivity.rt_QRScanner2KCE.setEnabled(false);
        }
    }
    public void QRCode_Format_Old(String pResult1kce){
        if (pResult1kce != "") {
                CheckScan_Enable(pResult1kce);

        }
        else
        {
            MainActivity.tv_ReadResult1KCE.setText("ไม่พบข้อมูล");
            MainActivity.rt_QRScanner1KCE.setEnabled(true);
            MainActivity.rt_QRScanner2KCE.setEnabled(false);
        }
    }

    public void CheckScan_Enable(String pResult1kce) {



        if (pResult1kce.length() > 0 ) {
            MainActivity.tv_ReadResult1KCE.setText(pResult1kce);
            MainActivity.rt_QRScanner1KCE.setEnabled(false);
            MainActivity.rt_QRScanner2KCE.setEnabled(true);
        }else {
            MainActivity.rt_QRScanner1KCE.setEnabled(true);
            MainActivity.rt_QRScanner2KCE.setEnabled(false);
        }

    }
}