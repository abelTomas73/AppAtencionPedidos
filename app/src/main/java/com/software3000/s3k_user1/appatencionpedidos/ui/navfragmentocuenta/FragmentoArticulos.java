package com.software3000.s3k_user1.appatencionpedidos.ui.navfragmentocuenta;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.software3000.s3k_user1.appatencionpedidos.R;
import com.software3000.s3k_user1.appatencionpedidos.loginSistema.LoginActivity;
import com.software3000.s3k_user1.appatencionpedidos.model.CortesiaCombo;
import com.software3000.s3k_user1.appatencionpedidos.model.CortesiaProductos;
import com.software3000.s3k_user1.appatencionpedidos.modelo.Comida;
import com.software3000.s3k_user1.appatencionpedidos.services.VolleySingleton;
import com.software3000.s3k_user1.appatencionpedidos.ui.navegacionlateral.FragmentoInicio;
import com.software3000.s3k_user1.appatencionpedidos.utils.Utils;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentoArticulos extends Fragment {

    private RecyclerView reciclador;
    private TextView textZonasIslasMaquinasCategorias;
    private String tituloDetalle;
    //private LinearLayoutManager layoutManager;
    //private AdaptadorInicio adaptador;
    SearchView mSearchView;


    private GridLayoutManager layoutManager;
    private AdaptadorArticulos adaptador;
    private AdaptadorCortesiasCombo adaptadorCortesiasCombo;



    private List<CortesiaProductos> cortesiaProductosList;
    private List<CortesiaCombo> cortesiaComboList;

    private RecyclerView recyclerview_id;
    public static String CATEGORIA_IDTIPO;
    public static String CATEGORIA_NOMBRE;

    SwipeRefreshLayout swipeRefreshLayout;
    ProgressBar progressBar;
    ProgressDialog progressDialog;

    //parametrso
    String categoria_idtipo="";
    public FragmentoArticulos() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragmento_articulos, container, false);
        View view = inflater.inflate(R.layout.fragmento_articulos, container, false);
        //poblarArticulos();

        categoria_idtipo = getArguments().getString("categoria_idtipo");
        CATEGORIA_IDTIPO=categoria_idtipo;

        String categoria_nombre = getArguments().getString("categoria_nombre");
        CATEGORIA_NOMBRE=categoria_nombre;
        reciclador = view.findViewById(R.id.reciclador);

        textZonasIslasMaquinasCategorias = view.findViewById(R.id.textZonasIslasMaquinasCategorias);
        tituloDetalle= FragmentoInicio.ZONAELEGIDA.getNombre()+" > "
                +FragmentoIslas.ISLAELEGIDA.getNombre()+" > "
                +FragmentoMaquinas.MAQUINAELEGIDA.getCodMaq()+" > "
                +categoria_nombre;
        textZonasIslasMaquinasCategorias.setText(tituloDetalle);
//        layoutManager = new LinearLayoutManager(getActivity());

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

                if(categoria_idtipo.equals("9")) {//codigo combo 8
                    //adaptador Combos
                    adaptadorCortesiasCombo.notifyDataSetChanged();
                    cortesiaComboList.clear();
                    servicioPoblarListarCombos();
                }else{
                    adaptador.notifyDataSetChanged();
                    cortesiaProductosList.clear();
                    servicioPoblarCortesiaProductos();

                }
            }
        });

        int mNoOfColumns = Utils.calculateNoOfColumns(getContext(),180);
        cortesiaProductosList = new ArrayList<>();
        cortesiaComboList = new ArrayList<>();

        layoutManager = new GridLayoutManager(getActivity(), mNoOfColumns);
        reciclador.setLayoutManager(layoutManager);

        if (categoria_idtipo.equals("9")){//codigo combo 8
            servicioPoblarListarCombos();
            adaptadorCortesiasCombo= new AdaptadorCortesiasCombo(cortesiaComboList);
            reciclador.setAdapter(adaptadorCortesiasCombo);
        }else{
            servicioPoblarCortesiaProductos();
            adaptador = new AdaptadorArticulos(cortesiaProductosList);
            reciclador.setAdapter(adaptador);
        }





        return view;
    }
    public String ObtenerIp(){

        SharedPreferences sharedPreferences =this.getActivity().getSharedPreferences("Protocol", Context.MODE_PRIVATE);
        String ip =sharedPreferences.getString("ip","");
        return ip ;
    }
    public void servicioPoblarCortesiaProductos() {
        cortesiaProductosList.clear();
        String URls =  ObtenerIp()+"/Cortesias/ListarCortesiaProductosImagenesxTipo";

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
                                CortesiaProductos productos = new CortesiaProductos();

                                productos= gson.fromJson(jsonObject2.toString(), CortesiaProductos.class);
                                cortesiaProductosList.add(productos);
                            }

                            //adaptador.updateSearchedList();
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
                params.put("cortesiaTipoid", categoria_idtipo);
                return params;
            }
        };

        //VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);

        //AppSin
        VolleySingleton.getInstance(getContext()).addToRequestQueue(stringRequest);



    }
    public void servicioPoblarListarCombos() {
        cortesiaComboList.clear();
        String URls =  ObtenerIp()+"/Cortesias/ListarCombos";

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
                                CortesiaCombo cortesiaCombo = new CortesiaCombo();

                                cortesiaCombo= gson.fromJson(jsonObject2.toString(), CortesiaCombo.class);
                                cortesiaComboList.add(cortesiaCombo);
                            }
                            swipeRefreshLayout.setRefreshing(false);
                            //adaptador.updateSearchedList();
                            // refreshing recycler view
                            adaptadorCortesiasCombo.notifyDataSetChanged();


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        swipeRefreshLayout.setRefreshing(false);
                        progressDialog.dismiss();
//                        if (error instanceof TimeoutError || error instanceof NoConnectionError) {
//
//                            DynamicToast.makeWarning(getBaseContext(), "Error: Tiempo de Respuesta en búsqueda DNI ", Toast.LENGTH_LONG).show();
//                        }
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                //params.put("cortesiaTipoid", categoria_idtipo);
                return params;
            }
        };

        //VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);

        //AppSin
        VolleySingleton.getInstance(getContext()).addToRequestQueue(stringRequest);



    }
}
