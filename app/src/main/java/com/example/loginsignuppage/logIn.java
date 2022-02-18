package com.example.loginsignuppage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class logIn extends AppCompatActivity {

    EditText username, password;
    TextView forgot;
    Button logIn;
    DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        DB = new DBHelper(this);
        username = findViewById(R.id.userName);
        password = findViewById(R.id.password);
        logIn = findViewById(R.id.signInBtn);
        forgot = findViewById(R.id.forgotPass);

    }
    public void forgot(View view){
        Toast.makeText(com.example.loginsignuppage.logIn.this, "forgot", Toast.LENGTH_SHORT).show();
    }
    public void logIn(View view){
        String user = username.getText().toString();
        String pass = password.getText().toString();
        if(TextUtils.isEmpty(user) || TextUtils.isEmpty(pass))
            Toast.makeText(com.example.loginsignuppage.logIn.this, "FILL ALL FIELDS!!!", Toast.LENGTH_SHORT).show();
        else{
            boolean userCheck = DB.checkUsername(user);
            if(userCheck == true){
                boolean logIn = DB.checkUsernamePassword(user, pass);
                if(logIn == true){
                    Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(com.example.loginsignuppage.logIn.this, "FILL ALL FIELDS!!!", Toast.LENGTH_SHORT).show();
                }
            }
            else{
                Toast.makeText(com.example.loginsignuppage.logIn.this, "User does not exists.!!", Toast.LENGTH_SHORT).show();
            }
        }
    }
}