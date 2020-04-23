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

import com.example.departamentoeducacionfisicaesquel.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Obtener instancia de autenticación de Firebase
        auth = FirebaseAuth.getInstance();
        // Si la instancia es distinta de null
        if (auth.getCurrentUser() != null) {
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            finish();
        }
        // Establecer la vista ahora
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        // Obtener instancia de autenticación de Firebase
        auth = FirebaseAuth.getInstance();

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
                                // Si el inicio de sesión falla, muestre un mensaje al usuario. Si el inicio de sesión tiene éxito
                                // el Auth de estado de autenticación será notificado y la lógica para manejar el
                                // usuario registrado puede ser manejado en el Auth.
                                progressBar.setVisibility(View.GONE);
                                if (!task.isSuccessful()) {
                                    // Ocurrio un problema
                                    if (password.length() < 6) {
                                        inputPassword.setError(getString(R.string.minimum_password));
                                    } else {
                                        Toast.makeText(LoginActivity.this, getString(R.string.auth_failed), Toast.LENGTH_LONG).show();
                                    }
                                } else {

                                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                    startActivity(intent);
                                    finish();

                                  /*  FirebaseUser user = auth.getCurrentUser();
                                    if (!user.isEmailVerified()){
                                        Toast.makeText(LoginActivity.this, "Correo electronico no verificado", Toast.LENGTH_LONG).show();
                                    }else{
                                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                        startActivity(intent);
                                        finish();
                                    }*/
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
}


