package com.example.loginsignuppage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText username, password, confirmPass;
    private Button logIn, signUp;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        username = findViewById(R.id.userName);
        password = findViewById(R.id.password);
        confirmPass = findViewById(R.id.passwordConfirm);
        logIn = findViewById(R.id.accountBtn);
        signUp = findViewById(R.id.signUpBtn);
        DB = new DBHelper(this);

    }

    public void run(View view) {
        Intent intent = new Intent(this, com.example.loginsignuppage.logIn.class);
        startActivity(intent);
    }
    public void message(View view) {
        String user = username.getText().toString();
        String pass = password.getText().toString();
        String repass = confirmPass.getText().toString();
        if(TextUtils.isEmpty(user) || TextUtils.isEmpty(pass) || TextUtils.isEmpty(repass)) {
            Toast.makeText(this, "Please fill are the fields!", Toast.LENGTH_SHORT).show();
        }
        else{
            if(pass.equals(repass)){
                boolean userCheck = DB.checkUsername(user);
                if(userCheck == false){
                    boolean insert = DB.insertData(user, pass);
                    if(insert== true){
                        Toast.makeText(this, "Registration Successfull!!!!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(this, HomeActivity.class);
                        startActivity(intent);
                    }
                    else{
                        Toast.makeText(this, "Something went wrong!!", Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(this, "Username already taken!!", Toast.LENGTH_SHORT).show(); }
            }
            else{
                Toast.makeText(this, "Passwords do not match!!!", Toast.LENGTH_SHORT).show(); }
        }
    }
}