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
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.software3000.s3k_user1.appatencionpedidos.R;
import com.software3000.s3k_user1.appatencionpedidos.loginSistema.LoginActivity;
import com.software3000.s3k_user1.appatencionpedidos.model.Isla;
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

import es.dmoral.toasty.Toasty;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentoIslas extends Fragment {

    private RecyclerView reciclador;

    SearchView mSearchView;


    private AdaptadorIslas adaptador;
    private List<Isla> islaList;
    private List<Isla> islaListFull;
    private RecyclerView recyclerview_id;
    private Button btnSeleccionarIsla, btnAtrasZonas;

    public static Isla ISLAELEGIDA;

    //
    ProgressBar progressBar;
    ProgressDialog progressDialog;

    //
    private LinearLayout checkout_list_layout;
    private ImageView imagen_carrito_vacio;
    private TextView texto_carrito_vacio;
    SwipeRefreshLayout swipeRefreshLayout;

    TextView textZonasIslas;
    public FragmentoIslas() {
        // Required empty public constructor
    }

    private void poblarIslas() {
        islaList = new ArrayList<>();
        islaList.add(new Isla("Isla 1"));
        islaList.add(new Isla("Isla 2x"));
        islaList.add(new Isla("Isla ff"));
        islaList.add(new Isla("Isla 4"));
        islaList.add(new Isla("Isla 545"));
        islaList.add(new Isla("Isla 2x"));

        //COMIDAS_POPULARES_COPIA.addAll(COMIDAS_POPULARES);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragmento_islas, container, false);
        getActivity().setTitle("Islas");

        recyclerview_id = view.findViewById(R.id.recyclerview_id);
        btnSeleccionarIsla = view.findViewById(R.id.btnSeleccionarIsla);
        btnAtrasZonas = view.findViewById(R.id.btnAtrasZonas);

        textZonasIslas= view.findViewById(R.id.textZonasIslas);
        textZonasIslas.setText(FragmentoInicio.ZONAELEGIDA.getNombre());
        //
        progressBar = view.findViewById(R.id.progressBar);
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Espere...");
        progressDialog.setIndeterminate(false);
        progressDialog.setCancelable(false);
        progressDialog.show();

        //vacio
        checkout_list_layout = view.findViewById(R.id.registro_list_layout);
        imagen_carrito_vacio = view.findViewById(R.id.imagen_sin_registro);
        texto_carrito_vacio = view.findViewById(R.id.texto_sin_registro);

        swipeRefreshLayout = view.findViewById(R.id.swipeRefresh);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                adaptador.notifyDataSetChanged();
                islaList.clear();
                servicioPoblarIslas();
            }
        });
        //
        btnSeleccionarIsla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (FragmentoIslas.ISLAELEGIDA == null) {
//                    Toast.makeText(getActivity(), "Eliga una Isla", Toast.LENGTH_SHORT).show();
//                    DynamicToast.makeError(getContext(), "Eliga una Isla", Toast.LENGTH_LONG).show();
                    Toasty.error(getContext(), "Eliga una Isla.", Toast.LENGTH_SHORT, true).show();
                } else {
                    Fragment fragment = new FragmentoMaquinas();
                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
                    transaction.replace(R.id.contenedor_principal, fragment);
                    transaction.addToBackStack(null);
                    transaction.commit();

                }

            }
        });
        btnAtrasZonas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentoInicio.ZONAELEGIDA = null;
                Fragment fragment = new FragmentoInicio();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.contenedor_principal, fragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });


        islaList = new ArrayList<>();

        islaListFull = new ArrayList<>();

        servicioPoblarIslas();
        islaListFull.addAll(islaList);

        adaptador = new AdaptadorIslas(getContext(), islaList);

        SearchView searchView = view.findViewById(R.id.search_isla);

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
        int mNoOfColumns = Utils.calculateNoOfColumns(getContext(), 180);

        GridLayoutManager manager = new GridLayoutManager(getContext(), mNoOfColumns);

        int spanCount = 3; // 3 columns
        int spacing = 50; // 50px
        boolean includeEdge = false;

        recyclerview_id.setHasFixedSize(true);
        recyclerview_id.setLayoutManager(manager);
        recyclerview_id.setAdapter(adaptador);
        return view;
    }
    public String ObtenerIp(){

        SharedPreferences sharedPreferences =this.getActivity().getSharedPreferences("Protocol", Context.MODE_PRIVATE);
        String ip =sharedPreferences.getString("ip","");
        return ip ;
    }
    public void servicioPoblarIslas() {
        islaList.clear();
        //https://api.myjson.com/bins/wicz0


        String IP_LUDOPATA = "http://localhost:55406/Cortesias/ListarIslas";

//        String URls = online/Cortesias/ListarIslasxZona?fZona=7";
        String URls =  ObtenerIp()+"/Cortesias/ListarIslasxZona";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URls,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressBar.setVisibility(View.GONE);
                        progressDialog.dismiss();

                        JSONArray jsondata = null;
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            jsondata = jsonObject.getJSONArray("data");
                            Gson gson = new Gson();


                            for (int i = 0; i < jsondata.length(); i++) {
                                JSONObject jsonObject2 = jsondata.getJSONObject(i);
                                Isla isla = new Isla();

                                isla = gson.fromJson(jsonObject2.toString(), Isla.class);
                                if (isla.getEstado() == 1)
                                    islaList.add(isla);

                            }


                            adaptador.updateSearchedList();
                            if (islaList == null || islaList.size() == 0) {
                                checkout_list_layout.setGravity(Gravity.CENTER);

                                imagen_carrito_vacio.setVisibility(View.VISIBLE);
                                texto_carrito_vacio.setVisibility(View.VISIBLE);
                                recyclerview_id.setVisibility(View.GONE);

                            } else {
                                checkout_list_layout.setGravity(Gravity.CENTER);

                                imagen_carrito_vacio.setVisibility(View.GONE);
                                texto_carrito_vacio.setVisibility(View.GONE);
                                recyclerview_id.setVisibility(View.VISIBLE);
                            }

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
                params.put("fZona", String.valueOf(FragmentoInicio.ZONAELEGIDA.getCodZona()));
                return params;
            }
        };

        //VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);

        //AppSin
        VolleySingleton.getInstance(getContext()).addToRequestQueue(stringRequest);


    }


}
