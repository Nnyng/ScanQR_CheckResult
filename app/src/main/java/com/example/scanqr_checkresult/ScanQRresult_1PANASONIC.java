package com.example.scanqr_checkresult;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class ScanQRresult_1PANASONIC extends AppCompatActivity implements ZXingScannerView.ResultHandler{

    ZXingScannerView scannerView1pana;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        scannerView1pana = new ZXingScannerView(this);
        setContentView(scannerView1pana);

    }

    @Override
    public void handleResult(Result result) {

        Main2Activity_PANASONIC.tv_ReadResult1PANA.setText("");
        Main2Activity_PANASONIC.resultPANA.setText("");
        Main2Activity_PANASONIC.tv_ReadResult2PANA.setText("");
        Main2Activity_PANASONIC.resultPANA.setBackgroundResource(R.drawable.frame_btn_disible);



        String Result1_Panasonic = result.getText();
        if (Result1_Panasonic != "") {
            String Type = Result1_Panasonic.substring(60,61); //*
            String Value = Result1_Panasonic.substring(0,60); //*
            Main2Activity_PANASONIC.tv_ReadResult1PANA.setText(Value);
            if ((Type.equals("1") || Type.equals("2")))  {
                Main2Activity_PANASONIC.tv_ReadResult1PANA.setText(Value);
                CheckScan();
            }
            else
            {
                Main2Activity_PANASONIC.tv_ReadResult1PANA.setText("คุณยิงสติกเกอร์ไม่ถูกต้อง");
                Main2Activity_PANASONIC.rt_QRScanner1PANA.setEnabled(true);
                Main2Activity_PANASONIC.rt_QRScanner2PANA.setEnabled(false);

            }
        }
        else
        {
            Main2Activity_PANASONIC.tv_ReadResult1PANA.setText("ไม่พบข้อมูล");
            Main2Activity_PANASONIC.rt_QRScanner1PANA.setEnabled(true);
            Main2Activity_PANASONIC.rt_QRScanner2PANA.setEnabled(false);
        }

        onBackPressed();

    }
    @Override
    protected void onResume() {
        super.onResume();
        scannerView1pana.setResultHandler(this);
        scannerView1pana.startCamera();
    }

    @Override
    protected void onPause() {
        super.onPause();
        scannerView1pana.stopCamera();
    }

    public void CheckScan() {

        Main2Activity_PANASONIC.resultPANA.setBackgroundResource(R.drawable.frame_btn_disible);

        if (Main2Activity_PANASONIC.tv_ReadResult1PANA.getText().length() > 0 ) {
            Main2Activity_PANASONIC.rt_QRScanner1PANA.setEnabled(false);
            Main2Activity_PANASONIC.rt_QRScanner2PANA.setEnabled(true);
        }else {
            Main2Activity_PANASONIC.rt_QRScanner1PANA.setEnabled(true);
            Main2Activity_PANASONIC.rt_QRScanner2PANA.setEnabled(false);
        }

    }
}
