package com.example.scanqr_checkresult;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class Main_MenuPANASONIC extends AppCompatActivity {

    ImageView im_back_arrow_mainmenupanas;
    RelativeLayout Check_QrCode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menupanasonic);

        Check_QrCode  = (RelativeLayout) findViewById(R.id.CheckQrCode_pana);
        im_back_arrow_mainmenupanas = (ImageView) findViewById(R.id.im_back_arrow_mainmenu_pana);

        Check_QrCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Main_MenuPANASONIC.this, Main2Activity_PANASONIC.class));
            }
        });

        im_back_arrow_mainmenupanas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main_MenuPANASONIC.this,Select_Department.class);
                startActivity(intent);
                finish();
            }
        });

    }
}
