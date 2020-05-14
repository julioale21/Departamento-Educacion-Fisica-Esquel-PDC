package com.example.departamentoeducacionfisicaesquel.Utils;

import android.app.Application;
import android.content.Intent;
import android.widget.Toast;

import com.example.departamentoeducacionfisicaesquel.Login.LoginActivity;
import com.example.departamentoeducacionfisicaesquel.Login.PrincipalActivity;
import com.example.departamentoeducacionfisicaesquel.Login.User;
import com.google.firebase.auth.FirebaseAuth;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        User user = User.getInstance();

        if (FirebaseAuth.getInstance().getCurrentUser().reload()==null){
            user = null;
            startActivity(new Intent(this, LoginActivity.class));
        }
    }
}
