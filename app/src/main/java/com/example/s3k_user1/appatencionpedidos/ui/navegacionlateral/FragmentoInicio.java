package com.example.s3k_user1.appatencionpedidos.ui.navegacionlateral;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.example.s3k_user1.appatencionpedidos.R;
import com.example.s3k_user1.appatencionpedidos.helpers.MySharedPreference;
import com.example.s3k_user1.appatencionpedidos.helpers.SessionManager;
import com.example.s3k_user1.appatencionpedidos.loginSistema.LoginActivity;
import com.example.s3k_user1.appatencionpedidos.model.Zona;
import com.example.s3k_user1.appatencionpedidos.modelo.Comida;
import com.example.s3k_user1.appatencionpedidos.navigation.ActividadPrincipal;
import com.example.s3k_user1.appatencionpedidos.services.VolleySingleton;
import com.example.s3k_user1.appatencionpedidos.ui.navfragmentocuenta.AdaptadorZonas;
import com.example.s3k_user1.appatencionpedidos.ui.navfragmentocuenta.FragmentoIslas;
import com.example.s3k_user1.appatencionpedidos.utils.Utils;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import es.dmoral.toasty.Toasty;

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


    ProgressBar progressBar;
    ProgressDialog progressDialog;

    //
    private LinearLayout checkout_list_layout;
    private ImageView imagen_carrito_vacio;
    private TextView texto_carrito_vacio;
    private SessionManager session;

    public FragmentoInicio() {
    }
    SwipeRefreshLayout swipeRefreshLayout;
    private List<Zona> zonaListEstatica;

    private MySharedPreference sharedPreference;
    private Context viewfragmentcontext;
    public static Activity activitydelFragmento;

    String sesion_usuario = "";
    String sesion_usuario_id = "";

    // id
    String sesion_id = "";

    String sesion_empleado = "";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    View view;


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_categorias, menu);

        MenuItem menuItem = menu.findItem(R.id.action_carrito);
        int mCount = sharedPreference.retrieveProductCount();
        LayerDrawable icon = (LayerDrawable) menuItem.getIcon();
        //menuItem.setIcon(buildCounterDrawable(mCount, R.drawable.cart));
        Utils.setBadgeCount(viewfragmentcontext, icon, mCount);
    }

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

        view = inflater.inflate(R.layout.fragmento_zonas,container,false);
        getActivity().setTitle("Zonas");

        session = new SessionManager(getActivity().getApplicationContext());
        session.checkLogin();

        HashMap<String, String> user = session.getUserDetails();
        sesion_usuario = user.get(SessionManager.KEY_USUARIO_NOMBRE);
        sesion_usuario_id = user.get(SessionManager.KEY_USUARIO_ID);

        recyclerview_id = view.findViewById(R.id.recyclerview_id);
        btnSeleccionarZona = view.findViewById(R.id.btnSeleccionarZona);

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
                zonaList.clear();
                servicioPoblarZonas();
            }});

        btnSeleccionarZona.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (FragmentoInicio.ZONAELEGIDA==null){
//                    Toast.makeText(getActivity(), "Eliga una Zona", Toast.LENGTH_SHORT).show();
//                    DynamicToast.makeError(getContext(), "Eliga una Zona", Toast.LENGTH_LONG).show();
                    Toasty.error(getContext(), "Eliga una Zona.", Toast.LENGTH_SHORT, true).show();
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
        zonaList = new ArrayList<>();
        zonaListFull = new ArrayList<>();
        servicioPoblarZonas();
//        poblarZonas();
        zonaListFull.addAll(zonaList);


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

        sharedPreference = new MySharedPreference(ActividadPrincipal.contextoAcPrincipal);
        viewfragmentcontext = ActividadPrincipal.contextoAcPrincipal;

        activitydelFragmento= getActivity();

        return view;
    }

    private void poblarZonas() {
        zonaList = new ArrayList<>();
        zonaList.add(new Zona("Zona 1"));
        zonaList.add(new Zona("Zona 2"));
        zonaList.add(new Zona("Zona 3"));
        zonaList.add(new Zona("Zona 4"));
        zonaList.add(new Zona("Zona 6"));
        zonaList.add(new Zona("Zona 7"));
        zonaList.add(new Zona("Zona 8"));
        zonaList.add(new Zona("Zona 77"));
    }
    public void servicioPoblarZonas() {
        zonaList.clear();

//        String URls = "http://localhost:55406/Cortesias/ListarZonas";
        String URls = LoginActivity.IP_APK+"/online/Cortesias/ListarZonas";

        JsonObjectRequest  stringRequest = new JsonObjectRequest (Request.Method.POST, URls,null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        progressBar.setVisibility(View.GONE);
                        progressDialog.dismiss();
                        JSONArray jsondata=null;
                        try {
                            jsondata = response.getJSONArray("data");
                            Gson gson = new Gson();


                            for (int i = 0; i < jsondata.length(); i++) {
                                JSONObject jsonObject = jsondata.getJSONObject(i);
                                Zona zona = new Zona();

                                zona= gson.fromJson(jsonObject.toString(), Zona.class);
                                if(zona.getEstado()==1)
                                    zonaList.add(zona);
                            }
                            //Zona zona= gson.fromJson(response.toString(), Zona.class);

                            Zona[] addCartProducts = gson.fromJson(jsondata.toString(), Zona[].class);


                           //zonaList.addAll(Arrays.asList(addCartProducts));

                            //ArrayList<Zona> aList = new ArrayList<Zona>(Arrays.asList(addCartProducts));
                            //aList.addAll(Arrays.asList(addCartProducts));
//                                zonaList.addAll(Arrays.asList(gson.fromJson(jsondata.toString(), Zona[].class)));



                            adaptador.updateSearchedList();

                            if (zonaList==null || zonaList.size()==0){
                                checkout_list_layout.setGravity(Gravity.CENTER);

                                imagen_carrito_vacio.setVisibility(View.VISIBLE);
                                texto_carrito_vacio.setVisibility(View.VISIBLE);
                                recyclerview_id.setVisibility(View.GONE);


                            }else{
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
