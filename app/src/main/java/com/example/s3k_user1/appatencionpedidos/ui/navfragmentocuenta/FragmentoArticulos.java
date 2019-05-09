package com.example.s3k_user1.appatencionpedidos.ui.navfragmentocuenta;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.s3k_user1.appatencionpedidos.R;
import com.example.s3k_user1.appatencionpedidos.loginSistema.LoginActivity;
import com.example.s3k_user1.appatencionpedidos.model.CortesiaProductos;
import com.example.s3k_user1.appatencionpedidos.model.CortesiaProductos;
import com.example.s3k_user1.appatencionpedidos.modelo.Comida;
import com.example.s3k_user1.appatencionpedidos.services.VolleySingleton;
import com.example.s3k_user1.appatencionpedidos.ui.AdaptadorCategorias;
import com.example.s3k_user1.appatencionpedidos.ui.navegacionlateral.FragmentoInicio;
import com.example.s3k_user1.appatencionpedidos.utils.Utils;
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

    List<Comida> COMIDAS_POPULARES;
    List<Comida> COMIDAS_POPULARES_COPIA;

    private GridLayoutManager layoutManager;
    private AdaptadorArticulos adaptador;



    private List<CortesiaProductos> cortesiaProductosList;

    private RecyclerView recyclerview_id;

    //parametrso
    String categoria_idtipo="";
    public FragmentoArticulos() {
        // Required empty public constructor
    }

    private void poblarArticulos() {
        COMIDAS_POPULARES = new ArrayList<Comida>();

        COMIDAS_POPULARES.add(new Comida(1,5, "Maquina", R.drawable.camarones));
        COMIDAS_POPULARES.add(new Comida(2,3.2f, "Maquina 2", R.drawable.rosca));
        COMIDAS_POPULARES.add(new Comida(3,12f, "Maquina 3", R.drawable.sushi));
        COMIDAS_POPULARES.add(new Comida(4,9, "Maquina Sl", R.drawable.sandwich));
        COMIDAS_POPULARES.add(new Comida(5,34f, "Maquina S3K", R.drawable.lomo_cerdo));

        //COMIDAS_POPULARES_COPIA.addAll(COMIDAS_POPULARES);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragmento_articulos, container, false);
        View view = inflater.inflate(R.layout.fragmento_articulos, container, false);
        //poblarArticulos();

        categoria_idtipo = getArguments().getString("categoria_idtipo");
        String categoria_nombre = getArguments().getString("categoria_nombre");

        reciclador = view.findViewById(R.id.reciclador);

        textZonasIslasMaquinasCategorias = view.findViewById(R.id.textZonasIslasMaquinasCategorias);
        tituloDetalle= FragmentoInicio.ZONAELEGIDA.getNombre()+" - "
                +FragmentoIslas.ISLAELEGIDA.getNombre()+" - "
                +FragmentoMaquinas.MAQUINAELEGIDA.getCodMaq()+" - "
                +categoria_nombre;
        textZonasIslasMaquinasCategorias.setText(tituloDetalle);
//        layoutManager = new LinearLayoutManager(getActivity());

        int mNoOfColumns = Utils.calculateNoOfColumns(getContext(),180);


        cortesiaProductosList = new ArrayList<>();
        servicioPoblarCortesiaProductos();
        adaptador = new AdaptadorArticulos(cortesiaProductosList);

        layoutManager = new GridLayoutManager(getActivity(), mNoOfColumns);
        reciclador.setLayoutManager(layoutManager);
        reciclador.setAdapter(adaptador);
        return view;
    }

    public void servicioPoblarCortesiaProductos() {
        cortesiaProductosList.clear();
        String URls = LoginActivity.IP_APK+"/online/Cortesias/ListarCortesiaProductosImagenesxTipo";

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
                                CortesiaProductos productos = new CortesiaProductos();

                                productos= gson.fromJson(jsonObject2.toString(), CortesiaProductos.class);
                                cortesiaProductosList.add(productos);
                            }

                            //adaptador.updateSearchedList();
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
}
