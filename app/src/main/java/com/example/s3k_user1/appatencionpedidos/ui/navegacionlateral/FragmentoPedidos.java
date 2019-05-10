package com.example.s3k_user1.appatencionpedidos.ui.navegacionlateral;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.s3k_user1.appatencionpedidos.R;
import com.example.s3k_user1.appatencionpedidos.loginSistema.LoginActivity;
import com.example.s3k_user1.appatencionpedidos.model.CortesiaPedido;
import com.example.s3k_user1.appatencionpedidos.model.CortesiaPedido;
import com.example.s3k_user1.appatencionpedidos.modelo.Comida;
import com.example.s3k_user1.appatencionpedidos.services.VolleySingleton;
import com.example.s3k_user1.appatencionpedidos.ui.AdaptadorInicio;
import com.example.s3k_user1.appatencionpedidos.ui.Pedidos.AdaptadorPedidos;
import com.example.s3k_user1.appatencionpedidos.ui.navfragmentocuenta.FragmentoIslas;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import static android.content.Context.VIBRATOR_SERVICE;


public class FragmentoPedidos extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private RecyclerView reciclador;
    private LinearLayoutManager layoutManager;
    private AdaptadorPedidos adaptador;
    SearchView mSearchView;
    List<CortesiaPedido> cortesiaPedidosPendientesList;
    private SwipeRefreshLayout swipeRefreshLayout;

    public FragmentoPedidos() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragmento_pedidos, container, false);
        //poblarPedidosPorTraer();
        reciclador =  view.findViewById(R.id.reciclador_pedidos);

        swipeRefreshLayout = view.findViewById(R.id.swipeRefresh);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                adaptador.notifyDataSetChanged();
                cortesiaPedidosPendientesList.clear();
                servicioPoblarListarCortesiaPedidoPendientes();
            }});

        layoutManager = new LinearLayoutManager(getActivity());
        reciclador.setLayoutManager(layoutManager);

        
        cortesiaPedidosPendientesList = new ArrayList<>();
        servicioPoblarListarCortesiaPedidoPendientes();
        adaptador = new AdaptadorPedidos(getContext(),cortesiaPedidosPendientesList);
        reciclador.setAdapter(adaptador);
        //seguirPeticiciones();
        return view;
    }

    public void seguirPeticiciones() {

        final Handler handler = new Handler();
        Timer timer = new Timer(false);
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        //estado 2 por recoger
                        peticiconesServicioPedidosPorRecoger("2");

                    }
                });
            }
        };

        timer.scheduleAtFixedRate(timerTask, 5000, 5000); // every 5 seconds.
        //timer.cancel();

    }
    public void peticiconesServicioPedidosPorRecoger(final String estado){

        //String URls = "http://192.168.1.37:1234/api/ApiAutorizacion/MaquinaSinClienteList";

        String URls = LoginActivity.IP_APK+"/Cortesias/ListarCortesiaPedidoPendientesPorEstado";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URls, new com.android.volley.Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                JSONArray jsondata=null;
                try {
                    //converting response to json object
                    JSONObject jsonObject = new JSONObject(response);


//                    JSONArray datosArray = new JSONArray(response);
                    jsondata = jsonObject.getJSONArray("data");

                    if (jsondata.length()!=0){
                        Toast.makeText(getContext(), "Tiene Pedidos por Recoger", Toast.LENGTH_SHORT).show();
                        Vibrator vibrator = (Vibrator) getActivity().getSystemService(VIBRATOR_SERVICE);
                        if (Build.VERSION.SDK_INT >= 26) {
                            vibrator.vibrate(VibrationEffect.createOneShot(200, VibrationEffect.DEFAULT_AMPLITUDE));
                        } else {
                            vibrator.vibrate(200);
                        }
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("estado", estado);
                return params;
            }
        };

        //VolleySingleton.getInstance(this).addToRequestQueue(stringRequest); //en un activity

        //AppSin
        VolleySingleton.getInstance(getContext()).addToRequestQueue(stringRequest);
    }
    public void servicioPoblarListarCortesiaPedidoPendientes() {
        cortesiaPedidosPendientesList.clear();

//        String URls = "http://192.168.1.58/online/Cortesias/ListarCortesiaPedidosxZona?fZona=7";
        String URls = LoginActivity.IP_APK+"/Cortesias/ListarCortesiaPedidoPendientes";

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

                            swipeRefreshLayout.setRefreshing(false);
                            adaptador.updateSearchedList();
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
                        //progressDialog.dismiss();
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
                //params.put("fIsla", String.valueOf(FragmentoIslas.ISLAELEGIDA.getCodIsla()));
                return params;
            }
        };

        //VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);

        //AppSin
        VolleySingleton.getInstance(getContext()).addToRequestQueue(stringRequest);

    }
}
