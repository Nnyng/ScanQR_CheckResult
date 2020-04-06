package com.example.scanqr_checkresult;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class Select_Department extends AppCompatActivity {
    ImageView main_kce,main_panasonic;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_department);

        main_kce = (ImageView) findViewById(R.id.main_kce);
        main_panasonic = (ImageView) findViewById(R.id.main_panasonic);

        main_kce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Select_Department.this, Main_MenuKCE.class);
                startActivity(intent);
                finish();
            }
        });

        main_panasonic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Select_Department.this, Main_MenuPANASONIC.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
