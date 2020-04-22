package com.example.departamentoeducacionfisicaesquel.Login;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.departamentoeducacionfisicaesquel.R;
import com.example.departamentoeducacionfisicaesquel.Utils.MailJob;
import com.example.departamentoeducacionfisicaesquel.Utils.Utils;
import com.google.firebase.auth.FirebaseAuth;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SignUpActivity extends AppCompatActivity {

    @BindView(R.id.nombre)
    EditText etNombre;
    @BindView(R.id.apellido)
    EditText etApellido;
    @BindView(R.id.jerarquia)
    EditText etJerarquia;
    @BindView(R.id.dependencia)
    EditText etDependencia;
    @BindView(R.id.email)
    EditText etEmail;
    @BindView(R.id.btn_enviar)
    Button btnEnviar;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.lblNombre)
    TextView lblNombre;
    @BindView(R.id.lblApellido)
    TextView lblApellido;
    @BindView(R.id.lblJerarquia)
    TextView lblJerarquia;
    @BindView(R.id.lblDependencia)
    TextView lblDependencia;
    @BindView(R.id.lblEmail)
    TextView lblEmail;

    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ButterKnife.bind(this);

        auth = FirebaseAuth.getInstance();

        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (validarFormulario()) enviarMail();
            }
        });
    }

    private void enviarMail() {

        String contenido = "Nombre : " + etApellido.getText().toString() + " " + etNombre.getText().toString() + "\n" +
                "Jerarquia: " + etJerarquia.getText().toString() + "\n" +
                "Dependencia: " + etDependencia.getText().toString() + "\n" +
                "Email: " + etEmail.getText().toString() + "\n" +
                "Solicito se me envie un usuario y contraseña para acceder a la aplicación de Educación Física.";

        try {
            new MailJob(Utils.EMAIL, Utils.PASSWORD).execute(
                    new MailJob.Mail(etEmail.getText().toString(), "julio_ale21@hotmail.com", "Solicitud de usuario y contraseña", contenido)
            );

            Toast.makeText(SignUpActivity.this, "Te enviaremos los datos de tu cuenta al mail que ingresaste", Toast.LENGTH_LONG).show();

            finish();

        } catch (Exception e) {
            Toast.makeText(SignUpActivity.this, "Algo ocurrio y no se pudo realizar la solicitud. Intenta nuevamente", Toast.LENGTH_LONG).show();
        }
    }

    private boolean validarFormulario() {

        Boolean valido = true;

        if (TextUtils.isEmpty(etNombre.getText().toString())) {
            lblNombre.setText("Debe ingresar el nombre");
            lblNombre.setVisibility(View.VISIBLE);
            valido = false;
        } else {
            lblNombre.setVisibility(View.GONE);
        }

        if (TextUtils.isEmpty(etApellido.getText().toString())) {
            lblApellido.setText("Debe ingresar el Apellido");
            lblApellido.setVisibility(View.VISIBLE);
            valido = false;
        } else {
            lblApellido.setVisibility(View.GONE);
        }

        if (TextUtils.isEmpty(etJerarquia.getText().toString())) {
            lblJerarquia.setText("Debe ingresar su jerarquia");
            lblJerarquia.setVisibility(View.VISIBLE);
            valido = false;
        } else {
            lblJerarquia.setVisibility(View.GONE);
        }

        if (TextUtils.isEmpty(etDependencia.getText().toString())) {
            lblDependencia.setText("Debe ingresar su dependencia");
            lblDependencia.setVisibility(View.VISIBLE);
            valido = false;
        } else {
            lblDependencia.setVisibility(View.GONE);
        }

        if (TextUtils.isEmpty(etEmail.getText().toString())) {
            lblEmail.setText("Debe ingresar su email");
            lblEmail.setVisibility(View.VISIBLE);
            valido = false;
        } else {
            lblEmail.setVisibility(View.GONE);
        }

        return valido;
    }
}

