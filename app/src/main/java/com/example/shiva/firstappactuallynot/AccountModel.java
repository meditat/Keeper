package com.example.shiva.firstappactuallynot;

/**
 * Created by shiva on 27-01-2018.
 */

public class AccountModel {
    String type,email,password;

    public AccountModel(String type, String email, String password) {
        this.type = type;
        this.email = email;
        this.password = password;
    }

    public String getType() {
        return type;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
