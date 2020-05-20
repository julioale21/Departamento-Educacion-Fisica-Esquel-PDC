package com.example.departamentoeducacionfisicaesquel.Login;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.departamentoeducacionfisicaesquel.Model.User;
import com.example.departamentoeducacionfisicaesquel.ui.NavigationActivity;
import com.example.departamentoeducacionfisicaesquel.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.email)
    EditText inputEmail;

    @BindView(R.id.password)
    EditText inputPassword;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    @BindView(R.id.btn_signup)
    Button btnSignup;

    @BindView(R.id.btn_login)
    Button btnLogin;

    @BindView(R.id.btn_reset_password)
    Button btnReset;

    private FirebaseAuth auth;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        auth = FirebaseAuth.getInstance();

        if (auth.getCurrentUser() != null) {
            //assignRole();
            startActivity(new Intent(LoginActivity.this, NavigationActivity.class));
            finish();
        } else{
            setContentView(R.layout.activity_login);
            ButterKnife.bind(this);

            //auth = FirebaseAuth.getInstance();
        }
    }

    @OnClick({R.id.btn_login, R.id.btn_reset_password, R.id.btn_signup})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_login:

                // Obtener valores de los editText
                String email = inputEmail.getText().toString();
                final String password = inputPassword.getText().toString();

                // Validar si el logín a sido ingresado
                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "¡Introducir la dirección de correo electrónico!", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Validar si se ingreso la constraseña
                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "¡Introducir la contraseña!", Toast.LENGTH_SHORT).show();
                    return;
                }
                // ProgressBar visible
                progressBar.setVisibility(View.VISIBLE);

                // Autenticar usuario existe
                auth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(LoginActivity.this, new OnCompleteListener() {
                            @Override
                            public void onComplete(@NonNull Task task) {
                                progressBar.setVisibility(View.GONE);
                                if (!task.isSuccessful()) {
                                    // Ocurrio un problema
                                    if (password.length() < 6) {
                                        inputPassword.setError(getString(R.string.minimum_password));
                                    } else {
                                        Toast.makeText(LoginActivity.this, getString(R.string.auth_failed), Toast.LENGTH_LONG).show();
                                    }
                                } else {
                                    // Verifica si el usuario es administrador
                                    assignRole();
                                }
                            }
                        });
                break;

            case R.id.btn_reset_password:
                startActivity(new Intent(LoginActivity.this, RestartPasswordActivity.class));
                break;
            case R.id.btn_signup:
                startActivity(new Intent(LoginActivity.this, SignUpActivity.class));
                break;

        }
    }

    private void assignRole() {
        FirebaseDatabase miBD = FirebaseDatabase.getInstance();
        DatabaseReference adminReference = miBD.getReference().child("admin");

        Query adminByEmailQuery = adminReference.orderByChild("email").equalTo(auth.getCurrentUser().getEmail()).limitToFirst(1);

        adminByEmailQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                user = User.getInstance();
                user.setName(auth.getCurrentUser().getDisplayName());
                user.setEmail(auth.getCurrentUser().getEmail().toString());

                if(dataSnapshot.exists()){
                    user.setAdmin(true);
                }
                startActivity(new Intent(LoginActivity.this, NavigationActivity.class));
                finish();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    };
}


