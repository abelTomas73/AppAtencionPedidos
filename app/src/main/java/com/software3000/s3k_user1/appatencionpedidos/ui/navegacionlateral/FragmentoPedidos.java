package com.software3000.s3k_user1.appatencionpedidos.ui.navegacionlateral;

import android.content.Context;
import android.content.SharedPreferences;
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
import com.software3000.s3k_user1.appatencionpedidos.R;
import com.software3000.s3k_user1.appatencionpedidos.helpers.SessionManager;
import com.software3000.s3k_user1.appatencionpedidos.loginSistema.LoginActivity;
import com.software3000.s3k_user1.appatencionpedidos.model.CortesiaPedido;
import com.software3000.s3k_user1.appatencionpedidos.services.VolleySingleton;
import com.software3000.s3k_user1.appatencionpedidos.ui.Pedidos.AdaptadorPedidos;
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
    private SessionManager session;
    private String sesion_usuario;
    private String sesion_usuario_id;
    private View view;

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
        view = inflater.inflate(R.layout.fragmento_pedidos, container, false);
        //poblarPedidosPorTraer();
        reciclador =  view.findViewById(R.id.reciclador_pedidos);

        session = new SessionManager(getActivity().getApplicationContext());
        session.checkLogin();

        HashMap<String, String> user = session.getUserDetails();
        sesion_usuario = user.get(SessionManager.KEY_USUARIO_NOMBRE);
        sesion_usuario_id = user.get(SessionManager.KEY_USUARIO_ID);

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



    public String ObtenerIp(){

        SharedPreferences sharedPreferences =this.getActivity().getSharedPreferences("Protocol", Context.MODE_PRIVATE);
        String ip =sharedPreferences.getString("ip","");
        return ip ;
    }
    public void servicioPoblarListarCortesiaPedidoPendientes() {
        cortesiaPedidosPendientesList.clear();

//        String URls = "http://192.168.1.58/online/Cortesias/ListarCortesiaPedidosxZona?fZona=7";
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
                params.put("usuarioRegistroId", sesion_usuario_id);
                return params;
            }
        };

        //VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);

        //AppSin
        VolleySingleton.getInstance(getContext()).addToRequestQueue(stringRequest);

    }
}
