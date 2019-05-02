package com.example.s3k_user1.appatencionpedidos.ui.navegacionlateral;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.example.s3k_user1.appatencionpedidos.R;
import com.example.s3k_user1.appatencionpedidos.model.Zona;
import com.example.s3k_user1.appatencionpedidos.modelo.Comida;
import com.example.s3k_user1.appatencionpedidos.services.VolleySingleton;
import com.example.s3k_user1.appatencionpedidos.ui.navfragmentocuenta.AdaptadorZonas;
import com.example.s3k_user1.appatencionpedidos.ui.navfragmentocuenta.FragmentoIslas;
import com.example.s3k_user1.appatencionpedidos.utils.Utils;
import com.google.gson.Gson;
import com.pranavpandey.android.dynamic.toasts.DynamicToast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Fragmento para la sección de "Inicio"
 */
public class FragmentoInicio extends Fragment {
    private RecyclerView reciclador;
    private LinearLayoutManager layoutManager;
    //private AdaptadorInicio adaptador;
    SearchView mSearchView;

    List<Comida> COMIDAS_POPULARES;
    List<Comida> COMIDAS_POPULARES_COPIA;

    //nuevos
    private AdaptadorZonas adaptador;
    private List<Zona> zonaList;
    private List<Zona> zonaListFull;
    private RecyclerView recyclerview_id;
    private Button btnSeleccionarZona;

    public static Zona ZONAELEGIDA;
    public FragmentoInicio() {
    }

    private List<Zona> zonaListEstatica;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //TODO datos anteteriros maquinas
//        View view = inflater.inflate(R.layout.fragmento_inicio, container, false);
//        poblarMaquinas();
//        reciclador = (RecyclerView) view.findViewById(R.id.reciclador);
//
//        layoutManager = new LinearLayoutManager(getActivity());
//        reciclador.setLayoutManager(layoutManager);
//        COMIDAS_POPULARES_COPIA = new ArrayList<>();
//        COMIDAS_POPULARES_COPIA.addAll(COMIDAS_POPULARES);
//        adaptador = new AdaptadorInicio();
//        adaptador = new AdaptadorInicio(COMIDAS_POPULARES);
//        reciclador.setAdapter(adaptador);
//        return view;

        View view = inflater.inflate(R.layout.fragmento_zonas,container,false);
        getActivity().setTitle("Zonas");
        //poblarZonas();
        servicioPoblarZonas();
        recyclerview_id = view.findViewById(R.id.recyclerview_id);
        btnSeleccionarZona = view.findViewById(R.id.btnSeleccionarZona);
        btnSeleccionarZona.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (FragmentoInicio.ZONAELEGIDA==null){
//                    Toast.makeText(getActivity(), "Eliga una Zona", Toast.LENGTH_SHORT).show();
                    DynamicToast.makeError(getContext(), "Eliga una Zona", Toast.LENGTH_LONG).show();
                }else{
//                    Toast.makeText(getActivity(), "Correcto", Toast.LENGTH_SHORT).show();
                    Fragment fragment = new FragmentoIslas();
                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
                    transaction.replace(R.id.contenedor_principal, fragment);
                    transaction.addToBackStack(null);
                    transaction.commit();

                }

            }
        });

        adaptador = new AdaptadorZonas(getContext(),zonaList);

        SearchView searchView =  view.findViewById(R.id.search_zona);
        searchView.setQueryHint("Buscar Zonas ..");
        searchView.setMaxWidth( Integer.MAX_VALUE );
        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);
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
        int mNoOfColumns = Utils.calculateNoOfColumns(getContext(),180);

        GridLayoutManager manager = new GridLayoutManager(getContext(), mNoOfColumns);

        int spanCount = 3; // 3 columns
        int spacing = 50; // 50px
        boolean includeEdge = false;
        //recyclerview_id.addItemDecoration(new GridSpacingItemDecoration(spanCount, spacing, includeEdge));

        recyclerview_id.setHasFixedSize(true);
//        recyclerview_id.setLayoutManager(new GridLayoutManager(getContext(),mNoOfColumns));
        recyclerview_id.setLayoutManager(manager);
        recyclerview_id.setAdapter(adaptador);
        return view;
    }

    private void poblarZonas() {
        zonaList = new ArrayList<>();
        zonaList.add(new Zona("Zona 1"));
        zonaList.add(new Zona("Zona 2"));
        zonaList.add(new Zona("Zona 3"));
        zonaList.add(new Zona("Zona 4"));
        zonaList.add(new Zona("Zona 5"));
        zonaList.add(new Zona("Zona 5"));
        zonaList.add(new Zona("Zona 5"));zonaList.add(new Zona("Zona 5"));zonaList.add(new Zona("Zona 5"));
    }
    public void servicioPoblarZonas() {

        //https://api.myjson.com/bins/wicz0

        zonaList = new ArrayList<>();
        String IP_LUDOPATA = "http://localhost:55406/Cortesias/ListarZonas";

//        String URls = "http://localhost:55406/Cortesias/ListarZonas";
        String URls = "http://192.168.1.58/online/Cortesias/ListarZonas";

        JsonObjectRequest  stringRequest = new JsonObjectRequest (Request.Method.POST, URls,null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
//                        progressBar.setVisibility(View.GONE);
//                        progressDialog.dismiss();
                        JSONArray jsondata=null;
                        try {
                            jsondata = response.getJSONArray("data");
                            Gson gson = new Gson();

                            //Zona zona= gson.fromJson(response.toString(), Zona.class);

                            Zona[] addCartProducts = gson.fromJson(jsondata.toString(), Zona[].class);


                           zonaList.addAll(Arrays.asList(addCartProducts));

                            //ArrayList<Zona> aList = new ArrayList<Zona>(Arrays.asList(addCartProducts));
                            //aList.addAll(Arrays.asList(addCartProducts));
//                                zonaList.addAll(Arrays.asList(gson.fromJson(jsondata.toString(), Zona[].class)));


                            String abc="fs";
                            zonaListFull = new ArrayList<>();
                            zonaListFull.addAll(zonaList);

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

        };

        //VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);

        //AppSin
        VolleySingleton.getInstance(getContext()).addToRequestQueue(stringRequest);



    }

    private void poblarMaquinas() {
        COMIDAS_POPULARES = new ArrayList<Comida>();

        COMIDAS_POPULARES.add(new Comida(1,5, "Maquina", R.drawable.camarones));
        COMIDAS_POPULARES.add(new Comida(2,3.2f, "Maquina 2", R.drawable.rosca));
        COMIDAS_POPULARES.add(new Comida(3,12f, "Maquina 3", R.drawable.sushi));
        COMIDAS_POPULARES.add(new Comida(4,9, "Maquina Sl", R.drawable.sandwich));
        COMIDAS_POPULARES.add(new Comida(5,34f, "Maquina S3K", R.drawable.lomo_cerdo));

        //COMIDAS_POPULARES_COPIA.addAll(COMIDAS_POPULARES);
    }

//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setHasOptionsMenu(true);
//    }

//    @Override
//    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
//        inflater.inflate(R.menu.menu_categorias, menu);
////        MenuItem itemSearch = menu.findItem(R.id.action_search);
////        SearchManager searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);
////
////        if (itemSearch != null) {
////            mSearchView = (SearchView) MenuItemCompat.getActionView(itemSearch);
////            MenuItemCompat.collapseActionView(itemSearch);
////        }
//
//        MenuItem searchItem = menu.findItem(R.id.action_search);
//        searchItem.setTitle("Buscar Maquina");
//
//        SearchView searchView = (SearchView) searchItem.getActionView();
//
//        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);
//
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//
//                adaptador.getFilter().filter(newText);
//
//                return true;
//            }
//        });
//
//    }
}
