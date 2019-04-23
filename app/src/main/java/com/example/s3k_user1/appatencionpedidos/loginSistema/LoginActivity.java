package com.example.s3k_user1.appatencionpedidos.loginSistema;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.example.s3k_user1.appatencionpedidos.R;
import com.example.s3k_user1.appatencionpedidos.helpers.SessionManager;
import com.example.s3k_user1.appatencionpedidos.navigation.ActividadPrincipal;


public class LoginActivity extends AppCompatActivity {
    Button btnIngresarLogin;
    TextInputEditText edtusuario;
    AppCompatEditText edtcontrasena;
    View vista;

    //sesion
    SessionManager session;
    public static String USUARIOID="";
    public static String USUARIONOMBRE="";
    public static String USUARIOEMPLEADO="";
    public static String USUARIOCORREO="";
    public static String USUARIOROL="";
    public static String EMPLEADOID="";
    boolean respuestaLogin = false;
    Dialog myDialogIP;
    Button botonAprobarIP;

    Toolbar toolbar;

    ProgressBar progressBar;
    ProgressDialog progressDialog;
    public static String IP_APK =  "http://181.65.204.99:2222/SysLudopatasPJ";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_splash);



        session = new SessionManager(getApplicationContext());

        edtusuario = findViewById(R.id.edtusuario);
        edtcontrasena = findViewById(R.id.edtcontrasena);

        btnIngresarLogin = findViewById(R.id.btnIngresarLogin);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        btnIngresarLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String username = edtusuario.getText().toString();
                final String password = edtcontrasena.getText().toString();

                if (TextUtils.isEmpty(username)) {
                    edtusuario.setError("Ingrese su Usuario");
                    edtusuario.requestFocus();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    edtcontrasena.setError("Ingrese su Contraseña");
                    edtcontrasena.requestFocus();
                    return;
                }

//                progressDialog = new ProgressDialog(LoginActivity.this);
//                progressDialog.setMessage("Espere...");
//                progressDialog.setIndeterminate(false);
//                progressDialog.setCancelable(false);
//                progressDialog.show();

                ValidacionLogin();

            }
        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.sesion_usuario, menu);
        return true;
    }

    public void GuardarReferenciaIp(String ip) {
        SharedPreferences sharedPreferences = getSharedPreferences("Protocol", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("ip", ip);
        editor.commit();
    }


    public void ValidacionLogin() {
        final String username = edtusuario.getText().toString();
        final String password = edtcontrasena.getText().toString();

        if (TextUtils.isEmpty(username)) {
            edtusuario.setError("Ingrese su Usuario");
            edtusuario.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            edtcontrasena.setError("Ingrese su Contraseña");
            edtcontrasena.requestFocus();
            return;
        }

        Intent intentPantalla = new Intent(LoginActivity.this,ActividadPrincipal.class);
        startActivity(intentPantalla);


    }
}
