package com.example.departamentoeducacionfisicaesquel.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.departamentoeducacionfisicaesquel.Utils.MailJob;
import com.example.departamentoeducacionfisicaesquel.R;
import com.example.departamentoeducacionfisicaesquel.Utils.Utils;
import com.google.firebase.auth.FirebaseAuth;

public class SignUpActivity extends AppCompatActivity {

    private EditText etNombre, etApellido, etJerarquia, etDependencia, etEmail;
    private Button btnEnviar;
    private TextView lblNombre, lblApellido, lblJerarquia, lblDependencia, lblEmail;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        auth = FirebaseAuth.getInstance();

        etNombre = (EditText)findViewById(R.id.nombre);
        etApellido = (EditText)findViewById(R.id.apellido);
        etJerarquia = (EditText)findViewById(R.id.jerarquia);
        etDependencia = (EditText)findViewById(R.id.dependencia);
        etEmail = (EditText)findViewById(R.id.email);

        lblNombre = (TextView)findViewById(R.id.lblNombre);
        lblApellido = (TextView)findViewById(R.id.lblApellido);
        lblJerarquia = (TextView)findViewById(R.id.lblJerarquia);
        lblDependencia = (TextView)findViewById(R.id.lblDependencia);
        lblEmail = (TextView)findViewById(R.id.lblEmail);

        btnEnviar = (Button)findViewById(R.id.btn_enviar);

        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (validarFormulario()) enviarMail();
            }
        });
    }

    private void enviarMail() {

        String contenido =  Utils.EMAIL.CONTENTMESSAGE.NAME + ": " + etApellido.getText().toString() + " " + etNombre.getText().toString() + "\n" +
                            Utils.EMAIL.CONTENTMESSAGE.HIERARCHY + ": " + etJerarquia.getText().toString() + "\n" +
                            Utils.EMAIL.CONTENTMESSAGE.DEPENDENCY + ": " + etDependencia.getText().toString() + "\n" +
                            Utils.EMAIL.CONTENTMESSAGE.EMAILADDRESS + ": " + etEmail.getText().toString() + "\n" +
                            Utils.EMAIL.REQUESTMESSAGE;

        try {
            new MailJob(Utils.EMAIL.ADDRESS, Utils.EMAIL.PASSWORD).execute(
                    new MailJob.Mail(etEmail.getText().toString(), Utils.EMAIL.ADDRESS2, Utils.EMAIL.SUBJECT, contenido)
            );

            Toast.makeText(SignUpActivity.this, Utils.EMAIL.RESPONSEMESSAGE, Toast.LENGTH_LONG).show();

            finish();

        }catch (Exception e){
            Toast.makeText(SignUpActivity.this, Utils.ERRORS.REQUESTMAILERRORMESSAGE, Toast.LENGTH_LONG).show();
        }
    }

    private boolean validarFormulario() {

        Boolean valido = true;

        if (TextUtils.isEmpty(etNombre.getText().toString())){
            lblNombre.setText(Utils.ERRORS.FORMS.EMPTYFIRSTNAME);
            lblNombre.setVisibility(View.VISIBLE);
            valido = false;
        }else {
            lblNombre.setVisibility(View.GONE);
        }

        if (TextUtils.isEmpty(etApellido.getText().toString())){
            lblApellido.setText(Utils.ERRORS.FORMS.EMPTYLASTNAME);
            lblApellido.setVisibility(View.VISIBLE);
            valido = false;
        }else {
            lblApellido.setVisibility(View.GONE);
        }

        if (TextUtils.isEmpty(etJerarquia.getText().toString())){
            lblJerarquia.setText(Utils.ERRORS.FORMS.EMPTYHIERARCHY);
            lblJerarquia.setVisibility(View.VISIBLE);
            valido = false;
        } else{
            lblJerarquia.setVisibility(View.GONE);
        }

        if (TextUtils.isEmpty(etDependencia.getText().toString())){
            lblDependencia.setText(Utils.ERRORS.FORMS.EMPTYDEPENDENCY);
            lblDependencia.setVisibility(View.VISIBLE);
            valido = false;
        } else {
            lblDependencia.setVisibility(View.GONE);
        }

        if (TextUtils.isEmpty(etEmail.getText().toString())){
            lblEmail.setText(Utils.ERRORS.FORMS.EMPTYEMAILADDRESS);
            lblEmail.setVisibility(View.VISIBLE);
            valido = false;
        } else {
            lblEmail.setVisibility(View.GONE);
        }

        return valido;
    }

}

