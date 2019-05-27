package com.software3000.s3k_user1.appatencionpedidos;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.software3000.s3k_user1.appatencionpedidos.adapter.CheckRecyclerViewAdapter;
import com.software3000.s3k_user1.appatencionpedidos.helpers.MySharedPreference;
import com.software3000.s3k_user1.appatencionpedidos.helpers.SessionManager;
import com.software3000.s3k_user1.appatencionpedidos.helpers.SimpleDividerItemDecoration;
import com.software3000.s3k_user1.appatencionpedidos.loginSistema.LoginActivity;
import com.software3000.s3k_user1.appatencionpedidos.model.CortesiaAtencion;
import com.software3000.s3k_user1.appatencionpedidos.model.CortesiaCombo;
import com.software3000.s3k_user1.appatencionpedidos.model.CortesiaProductos;

import com.software3000.s3k_user1.appatencionpedidos.model.CortesiasProductosAtencion;
import com.software3000.s3k_user1.appatencionpedidos.model.MaquinaZona;
import com.software3000.s3k_user1.appatencionpedidos.navigation.ActividadPrincipal;
import com.software3000.s3k_user1.appatencionpedidos.services.VolleySingleton;
import com.software3000.s3k_user1.appatencionpedidos.ui.navegacionlateral.FragmentoInicio;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.software3000.s3k_user1.appatencionpedidos.ui.navfragmentocuenta.FragmentoArticulos;
import com.software3000.s3k_user1.appatencionpedidos.ui.navfragmentocuenta.FragmentoIslas;
import com.software3000.s3k_user1.appatencionpedidos.utils.Utils;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CheckoutActivity extends AppCompatActivity {

    private static final String TAG = CheckoutActivity.class.getSimpleName();

    private RecyclerView checkRecyclerView;

    private static TextView subTotal;

    private double mSubTotal = 0;
    private static String mSubTotalStatico = "";
    private static DecimalFormat df2 = new DecimalFormat("#.##");
    private LinearLayout checkout_list_layout;
    private ImageView imagen_carrito_vacio;
    private TextView texto_carrito_vacio;

    CortesiaProductos[] addCartProducts;
    CortesiaCombo[] addCartCombos;

    CortesiaProductos[] addCartProductsActualizadosGuardar;
    List<CortesiaProductos> productList;

    List<CortesiaProductos> productListParaEnviar;
    private SessionManager session;
    private String sesion_usuario;
    private String sesion_usuario_id;
    private String sesion_sala_id;
    private String sesion_empresa_id;
    MySharedPreference mShared;
    View vista;
    private CheckRecyclerViewAdapter mAdapter;
    private Gson gson;
    private GsonBuilder builder;
    private CortesiaAtencion atencion;

    private TextInputEditText checkout_comentarios;
    private void agregarToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setDisplayHomeAsUpEnabled(true);
        }
    }
    public static void  actualizarSubtotal(List<CortesiaProductos> productList){
        double mSubTotal = (double) getTotalPrice(productList);
        subTotal.setText("Subtotal : " +  "$ " +df2.format(mSubTotal ));
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);
        agregarToolbar();
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setTitle("Pedidos");



        subTotal = findViewById(R.id.sub_total);

        checkout_comentarios = findViewById(R.id.checkout_comentarios);

        subTotal.setText("Sin Registros");
        vista= findViewById(R.id.vistaCheckOut);
        checkRecyclerView = (RecyclerView)findViewById(R.id.checkout_list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(CheckoutActivity.this);
        checkRecyclerView.setLayoutManager(linearLayoutManager);
        checkRecyclerView.setHasFixedSize(true);
        checkRecyclerView.addItemDecoration(new SimpleDividerItemDecoration(CheckoutActivity.this));


        checkout_list_layout = findViewById(R.id.checkout_list_layout);
        imagen_carrito_vacio = findViewById(R.id.imagen_carrito_vacio);
        texto_carrito_vacio = findViewById(R.id.texto_carrito_vacio);
        // get content of cart
       mShared = new MySharedPreference(CheckoutActivity.this);

        builder = new GsonBuilder();
        gson = builder.create();

        addCartProducts = gson.fromJson(mShared.retrieveProductFromCart(), CortesiaProductos[].class);

//        addCartCombos = gson.fromJson(mShared.retrieveComboFromCart(), CortesiaCombo[].class);


        if (addCartProducts==null){
            mostrarImagenCarritoVacion();

        }
        else {


        //if (addCartProducts==null) return;

        session = new SessionManager(getApplicationContext());
        session.checkLogin();

        HashMap<String, String> user = session.getUserDetails();
        sesion_usuario = user.get(SessionManager.KEY_USUARIO_NOMBRE);
        sesion_usuario_id = user.get(SessionManager.KEY_USUARIO_ID);
        sesion_sala_id=user.get(SessionManager.KEY_SALA);
        sesion_empresa_id=user.get(SessionManager.KEY_EMPRESA);

        productList = convertObjectArrayToListObject(addCartProducts);
//        if (addCartCombos!=null){
//            for (int i = 0; i < addCartCombos.length; i++) {
//                CortesiaProductos cortPro = new CortesiaProductos();
//                cortPro.setArchivo64String("");
//                cortPro.setNombre(addCartCombos[i].getNombre());
//                productList.add(cortPro);
//            }
//        }


        //TODO DATOS ATENCION

        MaquinaZona maquinaZona= gson.fromJson(mShared.recibirPreferenciaMaquinaGuardada(), MaquinaZona.class);
        atencion= new CortesiaAtencion();
//        atencion.setCodMaq(FragmentoMaquinas.MAQUINAELEGIDA.getCodMaq());
        atencion.setCodMaq(maquinaZona.getCodMaq());

        atencion.setUsuarioRegistroID(sesion_usuario_id);
        atencion.setCodTurno("1");
        atencion.setCodSala(sesion_sala_id);
        atencion.setCodEmpresa(sesion_empresa_id);
        atencion.setCodzona(maquinaZona.getCodZona());
        atencion.setCodisla( String.valueOf(FragmentoIslas.ISLAELEGIDA.getCodIsla() )) ;
//Codzona y codisla
        atencion.setEstado("0");
        String PreferenciaEmpresaSalaMaquina ="Sala: "+atencion.getCodSala() +
                "  Empresa: "+atencion.getCodEmpresa()+
                "  Maquina: "+atencion.getCodMaq();

        subTotal.setText(PreferenciaEmpresaSalaMaquina);
        //
        mAdapter = new CheckRecyclerViewAdapter(CheckoutActivity.this, productList);
        checkRecyclerView.setAdapter(mAdapter);

        //mAdapter.notifyDataSetChanged();
        //mAdapter.notifyItemRemoved(3);
        mSubTotal = getTotalPrice(productList);


        //subTotal.setText("Subtotal : " +  "$ " + df2.format(mSubTotal ));
        }

        Button checkButton = (Button)findViewById(R.id.checkout);
        //assert checkButton != null;
        checkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //MySharedPreference sharedPreference = new MySharedPreference(getBaseContext());

                GuardarCortesiaAtencion();
            }
        });
    }
    private void mostrarImagenCarritoVacion(){
        checkout_list_layout.setGravity(Gravity.CENTER);

        imagen_carrito_vacio.setVisibility(View.VISIBLE);
        texto_carrito_vacio.setVisibility(View.VISIBLE);
        checkRecyclerView.setVisibility(View.GONE);
    }
    private List<CortesiaProductos> convertObjectArrayToListObject(CortesiaProductos[] allProducts){
        List<CortesiaProductos> mProduct = new ArrayList<CortesiaProductos>();
        Collections.addAll(mProduct, allProducts);
        return mProduct;
    }

    private List<CortesiaCombo> convertObjectArrayToListObject(CortesiaCombo[] allCombos){
        List<CortesiaCombo> mCombo = new ArrayList<>();
        Collections.addAll(mCombo, allCombos);
        return mCombo;
    }

    private int returnQuantityByProductName(String productName, List<CortesiaProductos> mProducts){
        int quantityCount = 0;
        for(int i = 0; i < mProducts.size(); i++){
            CortesiaProductos pObject = mProducts.get(i);
            if(pObject.getNombre().trim().equals(productName.trim())){
                quantityCount++;
            }
        }
        return quantityCount;
    }

    private static double getTotalPrice(List<CortesiaProductos> mProducts){
        double totalCost = 0;
        for(int i = 0; i < mProducts.size(); i++){
            CortesiaProductos pObject = mProducts.get(i);
            totalCost = totalCost;
        }
        return totalCost;
    }
    public String ObtenerIp(){

        SharedPreferences sharedPreferences =getSharedPreferences("Protocol", Context.MODE_PRIVATE);
        String ip =sharedPreferences.getString("ip","");
        return ip ;
    }
    public void GuardarCortesiaAtencion() {
        addCartProductsActualizadosGuardar = gson.fromJson(mShared.retrieveProductFromCart(), CortesiaProductos[].class);
        if (addCartProductsActualizadosGuardar==null) {
            Snackbar.make(vista, "No hay Productos", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
            return;
        }
        atencion.setComentario(checkout_comentarios.getText().toString());

        productListParaEnviar = new ArrayList<>();
        productListParaEnviar = convertObjectArrayToListObject(addCartProductsActualizadosGuardar);
        //productListParaEnviar.addAll(productList);

        for (int i = 0; i < productListParaEnviar.size(); i++) {

            productListParaEnviar.get(i).setArchivo64String("");
        }


        CortesiasProductosAtencion cortesiasProductosAtencion =new CortesiasProductosAtencion();




        List<CortesiaProductos> productListParaEnviarAgrupados;
        productListParaEnviarAgrupados = new ArrayList<>();


        for (int i = 0; i < productListParaEnviar.size(); i++) {
            if (productListParaEnviar.get(i).getCantidadPro()>1){
                for (int j = 0; j < productListParaEnviar.get(i).getCantidadPro(); j++) {
                    productListParaEnviarAgrupados.add(productListParaEnviar.get(i));
                }
            }else{
                productListParaEnviarAgrupados.add(productListParaEnviar.get(i));
            }
        }
        cortesiasProductosAtencion.setCortesiaAtencion(atencion);
        cortesiasProductosAtencion.setCortesiaProductosList(productListParaEnviarAgrupados);

        JSONObject js = new JSONObject();

        Gson gson = new GsonBuilder().create();
        String json = gson.toJson(cortesiasProductosAtencion);// obj is your object
        JSONObject jsonObj = null;;
        try {

            jsonObj = new JSONObject(json);
            //js.put("documentoId","A");

        }catch (JSONException e) {
            e.printStackTrace();
        }
        String URls =  ObtenerIp()+"/Cortesias/GuardarCortesiaAtencionProductos";
        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.POST,
                URls,
                jsonObj,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
//                        progressBar.setVisibility(View.GONE);
//                        progressDialog.dismiss();
                        try {

                            //if no error in response
                            if (response.getBoolean("respuesta")) {

                                //Toast.makeText(getApplicationContext(), jsonObject.getString("mensaje"), Toast.LENGTH_SHORT).show();
                                Snackbar.make(vista, response.getString("mensaje"), Snackbar.LENGTH_LONG).show();


                                //Intent intentPantalla = new Intent(LoginActivity.this,ActividadPrincipal.class);
                                //startActivity(intentPantalla);
                                //mAdapter.notify();
//                                mAdapter.notifyItemRangeRemoved(0, productList.size());

                                checkout_comentarios.setText("");
                                productList.clear();
                                mAdapter.notifyDataSetChanged();

                                mShared.deleteAllProductsFromTheCart();
                                mShared.addProductCount(0);
                                mShared.deleteAllProductCount();
                                mostrarImagenCarritoVacion();
//                                invalidateOptionsMenu();


                                Intent checkoutIntent = new Intent(CheckoutActivity.this, ActividadPrincipal.class);
                                startActivity(checkoutIntent);

//                                ActivityCompat.invalidateOptionsMenu(FragmentoInicio.activitydelFragmento);

                            } else {
                                //Toast.makeText(getApplicationContext(), jsonObject.getString("mensaje"), Toast.LENGTH_SHORT).show();
                                Snackbar.make(vista, response.getString("mensaje"), Snackbar.LENGTH_LONG).show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //progressDialog.dismiss();
                        if (error instanceof TimeoutError || error instanceof NoConnectionError) {

                            //DynamicToast.makeWarning(getBaseContext(), "Error Tiempo de Respuesta Inicio de Sesión", Toast.LENGTH_LONG).show();

                        }
                    }
                }
                ) {
                    @Override
                    public Map<String, String> getHeaders() throws AuthFailureError {
                        Map<String,String> params = new HashMap<String, String>();
                        //params.put("Content-Type","application/x-www-form-urlencoded");
                        //params.put("nombre",edtNombreImagen);
                        return params;
                    }
        };

        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);


    }
}
