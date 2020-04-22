package com.example.departamentoeducacionfisicaesquel.Login;

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

public class RestartPasswordActivity extends AppCompatActivity {
    @BindView(R.id.email)
    EditText inputEmail;
    @BindView(R.id.btn_reset_password)
    Button btnReset;
    @BindView(R.id.btn_back)
    Button btnBack;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    // Declara variables

    private FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restart_password);
        ButterKnife.bind(this);

        // Obtener instancias de Firebase
        auth = FirebaseAuth.getInstance();
        // Clic para regresar el Login
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        // Clic para resetear la contraseña
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obtener valor del editText
                String email = inputEmail.getText().toString().trim();
                // Validar si se ingreso el correo electronico
                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplication(), "Ingrese su ID de correo electrónico registrado", Toast.LENGTH_SHORT).show();
                    return;
                }
                // Mostrar progressbar
                progressBar.setVisibility(View.VISIBLE);
                // Resetear contraseña
                auth.sendPasswordResetEmail(email)
                        .addOnCompleteListener(new OnCompleteListener() {
                            @Override
                            public void onComplete(@NonNull Task task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(RestartPasswordActivity.this, "¡Le hemos enviado instrucciones para restablecer su contraseña!", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(RestartPasswordActivity.this, "Error al enviar el correo electrónico de reinicio", Toast.LENGTH_SHORT).show();
                                }

                                progressBar.setVisibility(View.GONE);
                            }
                        });
            }
        });
    }

}
