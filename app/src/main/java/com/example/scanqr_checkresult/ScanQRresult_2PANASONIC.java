package com.example.scanqr_checkresult;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.google.zxing.Result;

import java.util.Objects;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class ScanQRresult_2PANASONIC extends AppCompatActivity implements ZXingScannerView.ResultHandler {

    ZXingScannerView scannerView2pana;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        scannerView2pana = new ZXingScannerView(this);
        setContentView(scannerView2pana);
    }

    @Override
    public void handleResult(Result result) {

//        String ReadResult2pana= result.getText();
//        String Value = ReadResult2pana.substring(0,64);
//        Main2Activity_PANASONIC.tv_ReadResult2PANA.setText(Value);
//
//        // MainActivity.tv_ReadResult2.setText(result.getText());
//        CheckQRCode2();
//        onBackPressed();
//        CheckScan2();


        String ReadResult2pana= result.getText();
        if (ReadResult2pana != ""){
            String Type = ReadResult2pana.substring(60,61);//*
            String Value = ReadResult2pana.substring(0,60);//*
            Main2Activity_PANASONIC.tv_ReadResult2PANA.setText(Value);
            if (Type.equals("3"))  {
                Main2Activity_PANASONIC.tv_ReadResult2PANA.setText(Value);
                CheckQRCode2();
                Main2Activity_PANASONIC.btn_save_activity_main2.performClick();


            }
            else
            {
                Main2Activity_PANASONIC.tv_ReadResult2PANA.setText("คุณยิงสติกเกอร์ไม่ถูกต้อง");
                Main2Activity_PANASONIC.rt_QRScanner1PANA.setEnabled(false);
                Main2Activity_PANASONIC.rt_QRScanner2PANA.setEnabled(true);
              //  Main2Activity_PANASONIC.resultPANA.setBackgroundResource(R.drawable.frame_btn_disible);
            }
        }
        else
        {
            Main2Activity_PANASONIC.tv_ReadResult2PANA.setText("ไม่พบข้อมูล");
            Main2Activity_PANASONIC.rt_QRScanner1PANA.setEnabled(false);
            Main2Activity_PANASONIC.rt_QRScanner2PANA.setEnabled(true);

        }


        onBackPressed();

    }


    @Override
    protected void onResume() {
        super.onResume();
        scannerView2pana.setResultHandler(this);
        scannerView2pana.startCamera();
    }

    @Override
    protected void onPause() {
        super.onPause();
        scannerView2pana.stopCamera();
    }


    public void CheckQRCode2() {


        String ReadResult1pana = Main2Activity_PANASONIC.tv_ReadResult1PANA.getText().toString();
        String ReadResult2pana = Main2Activity_PANASONIC.tv_ReadResult2PANA.getText().toString();
        Main2Activity_PANASONIC.resultPANA.setBackgroundResource(R.drawable.frame_btn_disible);

        if (Objects.equals(ReadResult1pana, ReadResult2pana)) {
            Main2Activity_PANASONIC.resultPANA.setText("ถูกต้อง");
            Main2Activity_PANASONIC.resultPANA.setBackgroundResource(R.drawable.fram_btn_yes);
        }

        else {
            Main2Activity_PANASONIC.resultPANA.setText("ไม่ถูกต้อง");
            Main2Activity_PANASONIC.resultPANA.setBackgroundResource(R.drawable.frame_btn_no);
        }


        if (Main2Activity_PANASONIC.tv_ReadResult2PANA.getText().length() > 0 ) {
            Main2Activity_PANASONIC.rt_QRScanner1PANA.setEnabled(true);
            Main2Activity_PANASONIC.rt_QRScanner2PANA.setEnabled(false);
        }else {
            Main2Activity_PANASONIC.rt_QRScanner1PANA.setEnabled(false);
            Main2Activity_PANASONIC.rt_QRScanner2PANA.setEnabled(true);
        }

    }

}
