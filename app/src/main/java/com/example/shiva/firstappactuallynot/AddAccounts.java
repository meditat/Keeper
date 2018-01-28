package com.example.shiva.firstappactuallynot;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddAccounts extends AppCompatActivity {

    SQLiteDatabase accountsDB;
    AutoCompleteTextView accountType;
    EditText username;
    EditText password;
    Button save;
    String[] accountTypes = {"Facebook", "Google", "Reddit", "Github", "LinkedIn", "StackOverflow", "Instagram", "Tumblr", "Pinerest"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_accounts);
        accountsDB = openOrCreateDatabase("accounts_added", MODE_PRIVATE, null);
        accountsDB.execSQL("create table if not exists user_added_accounts (id integer primary key autoincrement , accountType TEXT,username TEXR, password TEXT);");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                R.layout.dropdown, accountTypes);
        accountType = findViewById(R.id.account_type);
        accountType.setAdapter(adapter);
        username = findViewById(R.id.accounts_mail);
        password = findViewById(R.id.register_pass_edt);
        save = findViewById(R.id.save_btn);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String type = accountType.getText().toString();
                String mail = username.getText().toString();
                String pass = password.getText().toString();
                accountsDB.execSQL("insert into user_added_accounts(accountType, username, password)values ('" + type + "','" + mail + "','" + pass + "');");
                Toast.makeText(AddAccounts.this, "Saved to the database", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void showAccounts(View view) {
        Intent intent = new Intent(AddAccounts.this, Accounts.class);
        startActivity(intent);
    }
}
