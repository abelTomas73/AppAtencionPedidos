package com.software3000.s3k_user1.appatencionpedidos.ui.navfragmentocuenta;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.GsonBuilder;
import com.software3000.s3k_user1.appatencionpedidos.R;
import com.software3000.s3k_user1.appatencionpedidos.helpers.MySharedPreference;
import com.software3000.s3k_user1.appatencionpedidos.helpers.SessionManager;
import com.software3000.s3k_user1.appatencionpedidos.loginSistema.LoginActivity;
import com.software3000.s3k_user1.appatencionpedidos.model.CortesiaPedido;
import com.software3000.s3k_user1.appatencionpedidos.model.GetConfiguracionCortesia;
import com.software3000.s3k_user1.appatencionpedidos.model.MaquinaZona;
import com.software3000.s3k_user1.appatencionpedidos.navigation.ActividadPrincipal;
import com.software3000.s3k_user1.appatencionpedidos.services.VolleySingleton;
import com.software3000.s3k_user1.appatencionpedidos.ui.navegacionlateral.FragmentoInicio;
import com.software3000.s3k_user1.appatencionpedidos.utils.Utils;
import com.google.gson.Gson;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import es.dmoral.toasty.Toasty;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentoMaquinas extends Fragment {

    private RecyclerView reciclador;

    SearchView mSearchView;


    private AdaptadorMaquina adaptador;
    private List<MaquinaZona> maquinaList;
    private List<MaquinaZona> maquinaListFull;
    private RecyclerView recyclerview_id;
    private Button btnSeleccionarMaquinaZona, btnAtrasIslas;

    public static MaquinaZona MAQUINAELEGIDA;

    private TextView textZonasIslasMaquinas;
    private SessionManager session;
    private String sesion_usuario_id;

    private List<CortesiaPedido>cortesiaPedidosPendientesList;

    boolean invalidarPedidoDeMaquinaElegida=false;
    GetConfiguracionCortesia getConfiguracionCortesia= new GetConfiguracionCortesia();

    SwipeRefreshLayout swipeRefreshLayout;
    ProgressBar progressBar;
    ProgressDialog progressDialog;

    public FragmentoMaquinas() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragmento_maquinas, container, false);
        getActivity().setTitle("Maquinas");


        session = new SessionManager(getActivity().getApplicationContext());
        session.checkLogin();

        HashMap<String, String> user = session.getUserDetails();

        sesion_usuario_id = user.get(SessionManager.KEY_USUARIO_ID);


        recyclerview_id = view.findViewById(R.id.recyclerview_id);
        btnSeleccionarMaquinaZona = view.findViewById(R.id.btnSeleccionarMaquina);
        btnAtrasIslas = view.findViewById(R.id.btnAtrasIslas);

        textZonasIslasMaquinas= view.findViewById(R.id.textZonasIslasMaquinas);

        String zonaeIslaElegida = FragmentoInicio.ZONAELEGIDA.getNombre()+ " > "+FragmentoIslas.ISLAELEGIDA.getNombre();
        textZonasIslasMaquinas.setText(zonaeIslaElegida);

        btnSeleccionarMaquinaZona.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                servicioPoblarListarCortesiaPedidoPendientes();
                if (FragmentoMaquinas.MAQUINAELEGIDA==null){
//                    Toast.makeText(getActivity(), "Eliga una Maquina", Toast.LENGTH_SHORT).show();
//                    DynamicToast.makeError(getContext(), "Eliga una Maquina", Toast.LENGTH_LONG).show();
                    Toasty.error(getContext(), "Eliga una Maquina.", Toast.LENGTH_SHORT, true).show();
                }else {
                    GetConfiguracionCortesiaPorMaquinaElegida(FragmentoMaquinas.MAQUINAELEGIDA);

                }
//                if (FragmentoMaquinas.MAQUINAELEGIDA==null){
////                    Toast.makeText(getActivity(), "Eliga una Maquina", Toast.LENGTH_SHORT).show();
////                    DynamicToast.makeError(getContext(), "Eliga una Maquina", Toast.LENGTH_LONG).show();
//                    Toasty.error(getContext(), "Eliga una Maquina.", Toast.LENGTH_SHORT, true).show();
//                }else{
//                    Fragment fragment = new FragmentoCategoriasProductos();
//                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
//                    transaction.replace(R.id.contenedor_principal, fragment);
//                    transaction.addToBackStack(null);
//                    transaction.commit();
//
//                }

            }
        });
        btnAtrasIslas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentoIslas.ISLAELEGIDA=null;
                Fragment fragment = new FragmentoIslas();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.contenedor_principal, fragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        maquinaList = new ArrayList<>();
        maquinaListFull = new ArrayList<>();
//        poblarMaquinaZonas();
        servicioPoblarMaquinas();
        maquinaListFull.addAll(maquinaList);

        adaptador = new AdaptadorMaquina(getContext(),maquinaList);

        SearchView searchView =  view.findViewById(R.id.search_maquina);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                adaptador.getFilter().filter(newText);

                return true;
            }
        });
        //
        progressBar = view.findViewById(R.id.progressBar);
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Espere...");
        progressDialog.setIndeterminate(false);
        progressDialog.setCancelable(false);
        progressDialog.show();

        swipeRefreshLayout = view.findViewById(R.id.swipeRefresh);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                adaptador.notifyDataSetChanged();
                maquinaList.clear();
                servicioPoblarMaquinas();
            }
        });
        //
        int mNoOfColumns = Utils.calculateNoOfColumns(getContext(),245);

        GridLayoutManager manager = new GridLayoutManager(getContext(), mNoOfColumns);

        int spanCount = 3; // 3 columns
        int spacing = 50; // 50px
        boolean includeEdge = false;

        recyclerview_id.setHasFixedSize(true);
        recyclerview_id.setLayoutManager(manager);
        recyclerview_id.setAdapter(adaptador);
        return  view;
    }
    public void servicioPoblarListarCortesiaPedidoPendientes() {
        cortesiaPedidosPendientesList = new ArrayList<>();

        cortesiaPedidosPendientesList.clear();
        String URls =  ObtenerIp()+"/Cortesias/ListarCortesiaPedidoPendientes";

        StringRequest stringRequest = new StringRequest  (Request.Method.POST, URls,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
//                        progressBar.setVisibility(View.GONE);
//                        progressDialog.dismiss();

                        JSONArray jsondata=null;
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            jsondata = jsonObject.getJSONArray("data");
                            Gson gson = new Gson();


                            for (int i = 0; i < jsondata.length(); i++) {
                                JSONObject jsonObject2 = jsondata.getJSONObject(i);
                                CortesiaPedido cortesiapedido = new CortesiaPedido();
                                cortesiapedido= gson.fromJson(jsonObject2.toString(), CortesiaPedido.class);
                                cortesiaPedidosPendientesList.add(cortesiapedido);
                            }
                            //callbackvalidarMaquinaElegidayOcupada(cortesiaPedidosPendientesList);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

//                        if (error instanceof TimeoutError || error instanceof NoConnectionError) {
//
//                            DynamicToast.makeWarning(getBaseContext(), "Error: Tiempo de Respuesta en búsqueda DNI ", Toast.LENGTH_LONG).show();
//                        }
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                //params.put("fIsla", String.valueOf(FragmentoIslas.ISLAELEGIDA.getCodIsla()));
                return params;
            }
        };

        //VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);

        //AppSin
        VolleySingleton.getInstance(getContext()).addToRequestQueue(stringRequest);

    }
    public void enviarRespuestaMaquinaOcupada(boolean respuesta){
        if (respuesta){

            Fragment fragment = new FragmentoCategoriasProductos();
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.contenedor_principal, fragment);
            transaction.addToBackStack(null);
            transaction.commit();
        }else {
            Toast.makeText(getContext(), "Maquina "+ FragmentoMaquinas.MAQUINAELEGIDA.getCodMaq() + " ocupada", Toast.LENGTH_SHORT).show();
        }
    }
    public void GetConfiguracionCortesiaPorMaquinaElegida(final MaquinaZona maquinaElegida) {

        //cortesiaPedidosPendientesList.clear();
        String URls =  ObtenerIp()+"/Cortesias/GetConfiguracionCortesiaPorMaquinaElegida";
        Log.e("Dato Maquina: ", MAQUINAELEGIDA.getCodMaq());
        Log.e("codigo Usuario : ", sesion_usuario_id);
        StringRequest stringRequest = new StringRequest  (Request.Method.POST, URls,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
//                        progressBar.setVisibility(View.GONE);
//                        progressDialog.dismiss();

                        JSONArray jsondata=null;
                        try {

                            JSONObject jsonObject = new JSONObject(response);
                            String errormensaje =jsonObject.getString("mensaje");
                            boolean respuesta =jsonObject.getBoolean("respuesta");
                            Gson gson = new Gson();

                            enviarRespuestaMaquinaOcupada(respuesta);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

//                        if (error instanceof TimeoutError || error instanceof NoConnectionError) {
//
//                            DynamicToast.makeWarning(getBaseContext(), "Error: Tiempo de Respuesta en búsqueda DNI ", Toast.LENGTH_LONG).show();
//                        }
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("maquinaElegida", maquinaElegida.getCodMaq());

                params.put("usuarioRegistroId", sesion_usuario_id);
                return params;
            }
        };

        //VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);

        //AppSin
        VolleySingleton.getInstance(getContext()).addToRequestQueue(stringRequest);

    }
    public void callbackfuncionConfiguracionTiempoCortesiaActivadoEnviarDatos(boolean tiempoConfiguracionEstaActivo){
        if (tiempoConfiguracionEstaActivo){
            servicioPoblarListarCortesiaPedidoPendientes();
        }else {
                Fragment fragment = new FragmentoCategoriasProductos();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.contenedor_principal, fragment);
                transaction.addToBackStack(null);
                transaction.commit();
        }
    }
    public void callbackvalidarMaquinaElegidayOcupada(List<CortesiaPedido> pedidoList){
        CortesiaPedido cortesiaPedidoSeleccionado = new CortesiaPedido();
        //servicioPoblarListarCortesiaPedidoPendientes();
        boolean maquinaOcupada=false;
        for (int i = 0; i < pedidoList.size(); i++) {
            if(pedidoList.get(i).getCodMaq().equals( FragmentoMaquinas.MAQUINAELEGIDA.getCodMaq()) ){
                cortesiaPedidoSeleccionado.setFechaRegistro(pedidoList.get(i).getFechaRegistro());
                cortesiaPedidoSeleccionado.setCodMaq(pedidoList.get(i).getCodMaq());
                cortesiaPedidoSeleccionado.setFechaRegistroDetalle(pedidoList.get(i).getFechaRegistroDetalle());
                maquinaOcupada=true;
            }

        }
        Date dtIn = null;
        if(maquinaOcupada){
            //2019-05-14 13:50:33
//            SimpleDateFormat inFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            SimpleDateFormat inFormat;
            inFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            try {
                dtIn = inFormat.parse(cortesiaPedidoSeleccionado.getFechaRegistroDetalle());

                String asf="";
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Calendar now = Calendar.getInstance();
            Calendar fechaPedido = Calendar.getInstance();

            fechaPedido.setTime(dtIn);

            int tiemppoRestaAntes= fechaPedido.get(Calendar.MINUTE);
            fechaPedido.add(Calendar.MINUTE, getConfiguracionCortesia.getTiempoAtencion().getTipo());

            int tiemppoRestaDespues= fechaPedido.get(Calendar.MINUTE);


            if ( now.after(fechaPedido) ){
                Fragment fragment = new FragmentoCategoriasProductos();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.contenedor_principal, fragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
            else {
                Toast.makeText(getContext(), "Maquina Ocupada", Toast.LENGTH_SHORT).show();

            }

        }else{
            Fragment fragment = new FragmentoCategoriasProductos();
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.contenedor_principal, fragment);
            transaction.addToBackStack(null);
            transaction.commit();
        }
    }
    public String ObtenerIp(){

        SharedPreferences sharedPreferences =this.getActivity().getSharedPreferences("Protocol", Context.MODE_PRIVATE);
        String ip =sharedPreferences.getString("ip","");
        return ip ;
    }

    public void servicioPoblarMaquinas() {
        maquinaList.clear();

        String URls = ObtenerIp()+"/Cortesias/ListarMaquinasxIsla";

        StringRequest stringRequest = new StringRequest  (Request.Method.POST, URls,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressBar.setVisibility(View.GONE);
                        progressDialog.dismiss();

                        JSONArray jsondata=null;
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            jsondata = jsonObject.getJSONArray("data");
                            Gson gson = new Gson();


                            for (int i = 0; i < jsondata.length(); i++) {
                                JSONObject jsonObject2 = jsondata.getJSONObject(i);
                                MaquinaZona maquina = new MaquinaZona();

                                maquina= gson.fromJson(jsonObject2.toString(), MaquinaZona.class);

                                maquinaList.add(maquina);
                            }


                            adaptador.updateSearchedList();
                            swipeRefreshLayout.setRefreshing(false);
                            // refreshing recycler view
                            adaptador.notifyDataSetChanged();


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        swipeRefreshLayout.setRefreshing(false);
//                        if (error instanceof TimeoutError || error instanceof NoConnectionError) {
//
//                            DynamicToast.makeWarning(getBaseContext(), "Error: Tiempo de Respuesta en búsqueda DNI ", Toast.LENGTH_LONG).show();
//                        }
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();

                params.put("StrUsuario", sesion_usuario_id);
                params.put("fIsla", String.valueOf(FragmentoIslas.ISLAELEGIDA.getCodIsla()));
                return params;
            }
        };

        //VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);

        //AppSin
        VolleySingleton.getInstance(getContext()).addToRequestQueue(stringRequest);

    }
}
