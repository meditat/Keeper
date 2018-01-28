package com.example.shiva.firstappactuallynot;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class Adapter extends BaseAdapter {
    Context context;
    ArrayList<AccountModel> accounts;
    @Override
    public int getCount() {
        return accounts.size() ;
    }

    public Adapter(Context context, ArrayList<AccountModel> accounts) {
        this.context = context;
        this.accounts = accounts;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        view = layoutInflater.inflate(R.layout.custom_listview ,viewGroup , false);
        TextView type = view.findViewById(R.id.custom_listview_type);
        TextView user = view.findViewById(R.id.custom_listview_user);
        TextView pass = view.findViewById(R.id.custom_listview_password);

        type.setText(accounts.get(i).getType());
        user.setText(accounts.get(i).getEmail());
        pass.setText(accounts.get(i).getPassword());
        return view;
    }
}
