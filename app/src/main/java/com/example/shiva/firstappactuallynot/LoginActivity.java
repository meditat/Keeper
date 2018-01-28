package com.example.shiva.firstappactuallynot;


import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
SQLiteDatabase db;
    Button loginBtn;
    EditText username;
    EditText password;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginBtn = findViewById(R.id.login_btn2);
        db=openOrCreateDatabase("my_db",MODE_PRIVATE,null);
        username = findViewById(R.id.login_username_edt);
        password = findViewById(R.id.login_password_edt);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString();
                String pass = password.getText().toString();
                Cursor c =db.rawQuery("select * from my_table where username = '"+user +"' and password = '"+pass +"';",null);
               int count = c.getCount();
                if(count>0){
                    Intent intent =  new Intent(LoginActivity.this , AddAccounts.class);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(LoginActivity.this, "Invalid username or password", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    public void unAccounted(View view) {
        Intent intent = new Intent(LoginActivity.this , RegisterActivity.class);
        startActivity(intent);
    }
}
