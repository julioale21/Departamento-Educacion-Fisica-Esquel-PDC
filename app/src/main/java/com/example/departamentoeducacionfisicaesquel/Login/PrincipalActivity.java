package com.example.departamentoeducacionfisicaesquel.Login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.departamentoeducacionfisicaesquel.R;
import com.google.firebase.auth.FirebaseAuth;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PrincipalActivity extends AppCompatActivity {

    User user;

    @BindView(R.id.textView)
    TextView textView;
    @BindView(R.id.btnAdmin)
    Button btnAdmin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        ButterKnife.bind(this);

       user = User.getInstance();

       if (user==null){

       }else{
           if (user.isAdmin()){
               btnAdmin.setVisibility(View.VISIBLE);
           }else{
               btnAdmin.setVisibility(View.GONE);
           }
       }
    }
}
