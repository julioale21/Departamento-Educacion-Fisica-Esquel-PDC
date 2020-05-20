package com.example.departamentoeducacionfisicaesquel.Utils;

import android.app.Application;
import android.content.Intent;

import com.example.departamentoeducacionfisicaesquel.Login.LoginActivity;
import com.example.departamentoeducacionfisicaesquel.Model.User;
import com.google.firebase.auth.FirebaseAuth;

public class MyApplication extends Application {

    FirebaseAuth auth;

    @Override
    public void onCreate() {
        super.onCreate();
        User user = User.getInstance();

        if (auth.getInstance().getCurrentUser() != null){
            if (auth.getInstance().getCurrentUser().reload()==null) {
                user = null;
                startActivity(new Intent(this, LoginActivity.class));
            }
        }
    }
}
