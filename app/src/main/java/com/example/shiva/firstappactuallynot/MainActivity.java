package com.example.shiva.firstappactuallynot;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    SQLiteDatabase db;
    SQLiteDatabase db2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db=openOrCreateDatabase("my_db",MODE_PRIVATE,null);
        db.execSQL("create table if not exists my_table (id integer primary key autoincrement,username TEXT,password TEXT,phone TEXT);");

    }

    public void login(View view) {
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);
    }

    public void register(View view) {
        Intent intent = new Intent(MainActivity.this , RegisterActivity.class);
        startActivity(intent);
    }


}
