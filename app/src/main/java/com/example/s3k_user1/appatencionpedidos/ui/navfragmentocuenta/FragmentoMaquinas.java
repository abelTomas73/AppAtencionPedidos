package com.example.s3k_user1.appatencionpedidos.ui.navfragmentocuenta;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.s3k_user1.appatencionpedidos.R;
import com.example.s3k_user1.appatencionpedidos.model.MaquinaZona;
import com.example.s3k_user1.appatencionpedidos.model.MaquinaZona;
import com.example.s3k_user1.appatencionpedidos.model.MaquinaZona;
import com.example.s3k_user1.appatencionpedidos.services.VolleySingleton;
import com.example.s3k_user1.appatencionpedidos.ui.FragmentoCategoria;
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
    public FragmentoMaquinas() {
        // Required empty public constructor
    }

    private void poblarMaquinaZonas() {
        maquinaList = new ArrayList<>();
        maquinaList.add(new MaquinaZona("1","MAQ-1","2","1","3"));
        maquinaList.add(new MaquinaZona("2","MAQ-2","2","1","2"));
        maquinaList.add(new MaquinaZona("3","MAQ-4","1","3","3"));
        maquinaList.add(new MaquinaZona("4","MAQ-3","3","3","3"));
        maquinaList.add(new MaquinaZona("5","MAQ-6","2","2","1"));
        maquinaList.add(new MaquinaZona("6","MAQ-7","3","1","2"));
        maquinaList.add(new MaquinaZona("7","MAQ-8","1","1","2"));
        maquinaList.add(new MaquinaZona("8","MAQ-9","1","3","3"));

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

        recyclerview_id = view.findViewById(R.id.recyclerview_id);
        btnSeleccionarMaquinaZona = view.findViewById(R.id.btnSeleccionarMaquina);
        btnAtrasIslas = view.findViewById(R.id.btnAtrasIslas);

        textZonasIslasMaquinas= view.findViewById(R.id.textZonasIslasMaquinas);

        String zonaeIslaElegida = FragmentoInicio.ZONAELEGIDA.getNombre()+ " - "+FragmentoIslas.ISLAELEGIDA.getNombre();
        textZonasIslasMaquinas.setText(zonaeIslaElegida);

        btnSeleccionarMaquinaZona.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (FragmentoMaquinas.MAQUINAELEGIDA==null){
//                    Toast.makeText(getActivity(), "Eliga una Maquina", Toast.LENGTH_SHORT).show();
//                    DynamicToast.makeError(getContext(), "Eliga una Maquina", Toast.LENGTH_LONG).show();
                    Toasty.error(getContext(), "Eliga una Maquina.", Toast.LENGTH_SHORT, true).show();
                }else{
                    Fragment fragment = new FragmentoCategoriasProductos();
                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
                    transaction.replace(R.id.contenedor_principal, fragment);
                    transaction.addToBackStack(null);
                    transaction.commit();

                }

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
        int mNoOfColumns = Utils.calculateNoOfColumns(getContext(),200);

        GridLayoutManager manager = new GridLayoutManager(getContext(), mNoOfColumns);

        int spanCount = 3; // 3 columns
        int spacing = 50; // 50px
        boolean includeEdge = false;

        recyclerview_id.setHasFixedSize(true);
        recyclerview_id.setLayoutManager(manager);
        recyclerview_id.setAdapter(adaptador);
        return  view;
    }
    public void servicioPoblarMaquinas() {
        maquinaList.clear();
        //https://api.myjson.com/bins/wicz0


        String IP_LUDOPATA = "http://localhost:55406/Cortesias/ListarMaquinaZonas";

//        String URls = "http://192.168.1.58/online/Cortesias/ListarMaquinaZonasxZona?fZona=7";
        String URls = "http://192.168.1.58/online/Cortesias/ListarMaquinasxIsla";

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
                                MaquinaZona maquina = new MaquinaZona();

                                maquina= gson.fromJson(jsonObject2.toString(), MaquinaZona.class);

                                maquinaList.add(maquina);
                            }


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
//                        if (error instanceof TimeoutError || error instanceof NoConnectionError) {
//
//                            DynamicToast.makeWarning(getBaseContext(), "Error: Tiempo de Respuesta en búsqueda DNI ", Toast.LENGTH_LONG).show();
//                        }
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("fIsla", String.valueOf(FragmentoIslas.ISLAELEGIDA.getCodIsla()));
                return params;
            }
        };

        //VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);

        //AppSin
        VolleySingleton.getInstance(getContext()).addToRequestQueue(stringRequest);

    }
}
