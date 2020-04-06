package com.example.scanqr_checkresult;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

public class User_Login extends AppCompatActivity {

    private  EditText input_User;
    private  EditText input_Password;
    private ProgressBar progressBar;
    private Button Sigup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_login);

        input_User = (EditText) findViewById(R.id.User);
        input_Password = (EditText) findViewById(R.id.password);
        Sigup = (Button) findViewById(R.id.btn_login);

        Sigup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // if (TypeQRCode1.compareTo(TypeQRCode2) == 0){

              Boolean Check_Login =   checkLoginValidate(input_User.toString(),input_Password.toString());
              if (Check_Login = true){
                  Intent Test1 = new Intent(User_Login.this,Select_Department.class);
               startActivity(Test1);
              }else {
                    Toast.makeText(getApplication(),"รหัสผ่านไม่ถูกต้อง",Toast.LENGTH_LONG).show();
                }
//                if (input_User.equals("2869")&&input_Password.equals("2869")){
//                    Intent Test1 = new Intent(User_Login.this,MainActivity.class);
//                    startActivity(Test1);
//                }
//                else {
//                    Toast.makeText(getApplication(),"รหัสผ่านไม่ถูกต้อง",Toast.LENGTH_LONG).show();
//                }
            }

            public boolean checkLoginValidate(String username, String password) {
                String realUsername = "MIS";
                String realPassword = "1234";

                if ( (!TextUtils.isEmpty(username) && !TextUtils.isEmpty(password)) &&
                        username.equals(realUsername) &&
                        password.equals(realPassword)) {
                    return true;
                }
                return false;
            }
        });
    }
}
