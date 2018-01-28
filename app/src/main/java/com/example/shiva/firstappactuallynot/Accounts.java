package com.example.shiva.firstappactuallynot;

import android.accounts.Account;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Objects;

public class Accounts extends AppCompatActivity {
    SQLiteDatabase db;
    ListView listView;
    ArrayList<AccountModel> accounts;
    EditText updateType, updatePassword, updateUsername;
    Button updateBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accounts);
        db = openOrCreateDatabase("accounts_added", MODE_PRIVATE, null);
        listView = findViewById(R.id.select_dialog_listview);
        accounts = new ArrayList<>();
        try {
            Cursor c = db.rawQuery("select * from user_added_accounts;", null);
            if (c != null) {
                c.moveToFirst();

                do {
                    String type = c.getString(c.getColumnIndex("accountType"));
                    String email = c.getString(c.getColumnIndex("username"));
                    String password = c.getString(c.getColumnIndex("password"));
                    AccountModel model = new AccountModel(type, email, password);
                    accounts.add(model);
                } while (c.moveToNext());

            }

            Adapter adapter = new Adapter(this, accounts);
            listView.setAdapter(adapter);
        } catch (Exception e) {
            setContentView(R.layout.empty_list);
            Toast.makeText(this, "Please add some accounts first", Toast.LENGTH_SHORT).show();
        }

        registerForContextMenu(listView);
    }

    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        LayoutInflater inflater = getLayoutInflater().from(this);
        View view = inflater.inflate(R.layout.custom_listview, null, false);
        menu.setHeaderView(view);
        menu.add("Delete");
        menu.add("Edit");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int position = info.position;
        View v = listView.getChildAt(position);
        TextView typeTv = v.findViewById(R.id.custom_listview_type);
        TextView userTv = v.findViewById(R.id.custom_listview_user);
        TextView passTv = v.findViewById(R.id.custom_listview_password);

        if (item.getTitle().equals("Delete")) {
            db.execSQL("delete from user_added_accounts where accountType = '" + typeTv.getText().toString() + "' and username = '" + userTv.getText().toString() + "';");
//            Toast.makeText(this, "text " + typeTv.getText().toString() + " " + userTv.getText().toString(), Toast.LENGTH_SHORT).show();
            recreate();
        }
        if (item.getTitle().equals("Edit")) {
            update(typeTv.getText().toString(), userTv.getText().toString(), passTv.getText().toString());
        }
        return true;
    }

    public void update(final String type, final String user, String password) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = LayoutInflater.from(this);
        View v = inflater.inflate(R.layout.alert_view, null, false);
        updatePassword = v.findViewById(R.id.alert_password);
        updateUsername = v.findViewById(R.id.alert_account_mail);
        updateType = v.findViewById(R.id.alert_account_type);
        updatePassword.setText(password);
        updateUsername.setText(user);
        updateType.setText(type);
        updateBtn = v.findViewById(R.id.alert_update_btn);
        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String updatedAccountTyped = updateType.getText().toString();
                String updatedUsername = updateUsername.getText().toString();
                String updatedPassword = updatePassword.getText().toString();
                db.execSQL("update user_added_accounts set accountType ='" + updatedAccountTyped + "', username = '" + updatedUsername + "', password ='" + updatedPassword + "' where accountType = '" + type + "' and username = '" + user + "';");
                recreate();

            }
        });

        builder.setView(v);
        builder.create();
        builder.show();
    }


}
