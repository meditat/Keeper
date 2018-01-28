package com.example.shiva.firstappactuallynot;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    EditText registerEmailEdt;
    EditText registerPassEdt;
    EditText registerPhoneEdt;
    EditText registerConfiEdt;
    Button registerBtn;
    TextView already;
    SQLiteDatabase  db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        db=openOrCreateDatabase("my_db",MODE_PRIVATE,null);

        db.execSQL("create table if not exists my_table (id integer primary key autoincrement,username TEXT,password TEXT,phone TEXT);");

        registerBtn = findViewById(R.id.register_btn);
        registerConfiEdt = findViewById(R.id.confirm_password_edt);
        registerPhoneEdt  = findViewById(R.id.phone_no_edt);
        registerPassEdt = findViewById(R.id.register_pass_edt);
        registerEmailEdt = findViewById(R.id.register_username_edt);
        already = findViewById(R.id.already_account);

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name=registerEmailEdt.getText().toString();
                String password=registerPassEdt.getText().toString();
                String confirm=registerConfiEdt.getText().toString();
                String phone=registerPhoneEdt.getText().toString();

                    db.execSQL("Insert into my_table(username,password,phone)values('"+name+"','"+password+"','"+phone+"');");
                    Toast.makeText(RegisterActivity.this, "success", Toast.LENGTH_SHORT).show();
            }
        });
    }


    public void alreadyAccounted(View view) {
        Intent intent = new Intent(RegisterActivity.this , LoginActivity.class);
        startActivity(intent);
    }
}
