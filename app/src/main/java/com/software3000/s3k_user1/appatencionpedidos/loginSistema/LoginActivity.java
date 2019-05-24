package com.software3000.s3k_user1.appatencionpedidos.loginSistema;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.android.volley.AuthFailureError;
import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.software3000.s3k_user1.appatencionpedidos.R;
import com.software3000.s3k_user1.appatencionpedidos.helpers.MySharedPreference;
import com.software3000.s3k_user1.appatencionpedidos.helpers.SessionManager;
import com.software3000.s3k_user1.appatencionpedidos.model.Empresa;
import com.software3000.s3k_user1.appatencionpedidos.model.Login;
import com.software3000.s3k_user1.appatencionpedidos.model.Sala;
import com.software3000.s3k_user1.appatencionpedidos.navigation.ActividadPrincipal;
import com.software3000.s3k_user1.appatencionpedidos.services.VolleySingleton;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


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
    public static String RAZONSOCIAL="";
    public static String NOMBRESALA="";

    public static Empresa LOGIN_EMPRESA;
    public static Sala LOGIN_SALA;
    boolean respuestaLogin = false;

    Button botonAprobarIP;

    Toolbar toolbar;

    ProgressBar progressBar;
    ProgressDialog progressDialog;
        //configurar ip
    Dialog myDialogIP;
    public static String IP_APK =  "http://192.168.1.58/online";
    private MySharedPreference sharedPreference;
    private Button btnAprobarIP;

    public static Context contextoLogin;

    private ImageView btnConfigurarIP = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_splash);



        contextoLogin = this.getApplicationContext();
        session = new SessionManager(getApplicationContext());
        if(session.isLoggedIn()){
            Intent intentPantalla = new Intent(LoginActivity.this,ActividadPrincipal.class);
            startActivity(intentPantalla);
        }
        vista = findViewById(R.id.vistaLogin);
        edtusuario = findViewById(R.id.edtusuario);
        edtcontrasena = findViewById(R.id.edtcontrasena);

        sharedPreference = new MySharedPreference(getApplicationContext());


        myDialogIP = new Dialog(LoginActivity.this);

        btnConfigurarIP = (ImageView) findViewById(R.id.btnConfigurar);

        btnConfigurarIP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openDialog();
            }
        });

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

                progressDialog = new ProgressDialog(LoginActivity.this);
                progressDialog.setMessage("Espere...");
                progressDialog.setIndeterminate(false);
                progressDialog.setCancelable(false);
                progressDialog.show();

                ValidacionLogin();

            }
        });

    }

    public String ObtenerIp(){

        SharedPreferences sharedPreferences =getSharedPreferences("Protocol", Context.MODE_PRIVATE);
        String ip =sharedPreferences.getString("ip","");
        return ip ;
    }

    public void CargarReferenciaIp() {
        //myDialogIP = new Dialog(LoginActivity.this);
        myDialogIP.setContentView(R.layout.dialog_cambiar);
        //SharedPreferences sharedPreferences = getSharedPreferences("Protocol", MODE_PRIVATE);
        //String ip = sharedPreferences.getString("ip", "");
//        String ip = sharedPreference.ObtenerIp();
        String ip = ObtenerIp();

        EditText dialog_edt_ip = myDialogIP.findViewById(R.id.dialog_edt_ip);
        if (ip.equals("")){
            dialog_edt_ip.setText(LoginActivity.IP_APK);
        }else{
            dialog_edt_ip.setText(ip);
        }
        //sharedPreference.GuardarReferenciaIp(ip);

    }

    public void openDialog() {

        //myDialogIP = new Dialog(LoginActivity.this);
        myDialogIP.setContentView(R.layout.dialog_cambiar);
        CargarReferenciaIp();
        final EditText dialog_edt_ip = myDialogIP.findViewById(R.id.dialog_edt_ip);

        btnAprobarIP = myDialogIP.findViewById(R.id.btnAprobarIP);
        myDialogIP.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialogIP.show();
        btnAprobarIP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String ip = dialog_edt_ip.getText().toString();

                GuardarReferenciaIp(dialog_edt_ip.getText().toString());

                LoginActivity.IP_APK = dialog_edt_ip.getText().toString();
                dialog_edt_ip.setText(ObtenerIp());

                //sharedPreference.GuardarReferenciaIp(LoginActivity.IP_APK);
                myDialogIP.hide();

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

        String URls = ObtenerIp()+"/Usuario/ValidacionLoginAndroid";
        //http://192.168.1.38/SysLudopatas/Login/ValidacionLoginExternoJson?usuLogin=admin&usuPassword=102030
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URls,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressBar.setVisibility(View.GONE);
                        progressDialog.dismiss();
                        try {
                            //converting response to json object
                            JSONObject jsonObject = new JSONObject(response);
                            String errormensaje =jsonObject.getString("mensaje");
                            //if no error in response
                            if (jsonObject.getBoolean("respuesta") ) {
                                errormensaje =jsonObject.getString("mensaje");
                                if (jsonObject.getBoolean("esAnfitriona")){


                                    //Toast.makeText(getApplicationContext(), jsonObject.getString("mensaje"), Toast.LENGTH_SHORT).show();
                                    Snackbar.make(vista, errormensaje, Snackbar.LENGTH_LONG)
                                            .setAction("Action", null).show();


                                    Gson gson = new Gson();
                                    Login login = new Login();

                                    login= gson.fromJson(jsonObject.toString(), Login.class);


                                    USUARIOID =login.getUsuario().getUsuarioID()+"";
                                    USUARIONOMBRE =login.getUsuario().getUsuarioNombre();
                                    EMPLEADOID=login.getEmpleado().getEmpleadoID()+"";
                                    USUARIOEMPLEADO=login.getEmpleado().getNombres();
                                    USUARIOROL=login.getRolUsuario().getWEBRolID()+"";
                                    respuestaLogin = jsonObject.getBoolean("respuesta");
                                    Log.e("Respuesta Login", String.valueOf(respuestaLogin));


                                    LOGIN_EMPRESA = login.getEmpresa();
                                    RAZONSOCIAL = login.getEmpresa().getRazonSocial();
                                    NOMBRESALA = login.getSala().getNomSala();


                                    LOGIN_SALA= login.getSala();
                                    session.createLoginSession(RAZONSOCIAL,NOMBRESALA,USUARIONOMBRE,EMPLEADOID, USUARIOID,USUARIOEMPLEADO,"",USUARIOROL,login.getSala().getCodSala(),login.getEmpresa().getCodEmpresa());

                                    //starting the profile activity
                                    //finish();
                                    //startActivity(new Intent(getApplicationContext(), BusquerdaDniActivity.class));
                                    Intent intentPantalla = new Intent(LoginActivity.this,ActividadPrincipal.class);
                                    startActivity(intentPantalla);
                                }else{
                                    errormensaje="Tipo de usuario no es anfitriona";
                                }
                            }

                                //Toast.makeText(getApplicationContext(), jsonObject.getString("mensaje"), Toast.LENGTH_SHORT).show();
                                Snackbar.make(vista, errormensaje, Snackbar.LENGTH_LONG)
                                        .setAction("Action", null).show();



                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        if (error instanceof TimeoutError || error instanceof NoConnectionError) {

                            //DynamicToast.makeWarning(getBaseContext(), "Error Tiempo de Respuesta Inicio de Sesión", Toast.LENGTH_LONG).show();

                        }
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("usuLogin", username);
                params.put("usuPassword", password);
                return params;
            }
        };

        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);


    }
}
