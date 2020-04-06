package com.example.scanqr_checkresult;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class Main_MenuKCE extends AppCompatActivity {
    ImageView im_back_arrow_mainmenu;
    RelativeLayout Check_QrCode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menukce);

        Check_QrCode  = (RelativeLayout) findViewById(R.id.CheckQrCode_kce);
        im_back_arrow_mainmenu = (ImageView) findViewById(R.id.im_back_arrow_mainmenu_kce);

        Check_QrCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Main_MenuKCE.this, MainActivity.class));
            }
        });

        im_back_arrow_mainmenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main_MenuKCE.this,Select_Department.class);
                startActivity(intent);
                finish();
            }
        });

    }
}
