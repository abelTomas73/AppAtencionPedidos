package com.example.s3k_user1.appatencionpedidos.ui;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;

import com.example.s3k_user1.appatencionpedidos.R;
import com.example.s3k_user1.appatencionpedidos.modelo.Comida;

import java.util.ArrayList;
import java.util.List;

/**
 * Fragmento para la sección de "Inicio"
 */
public class FragmentoInicio extends Fragment {
    private RecyclerView reciclador;
    private LinearLayoutManager layoutManager;
    private AdaptadorInicio adaptador;
    SearchView mSearchView;

    List<Comida> COMIDAS_POPULARES;
    List<Comida> COMIDAS_POPULARES_COPIA;
    public FragmentoInicio() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmento_inicio, container, false);
        poblarMaquinas();
        reciclador = (RecyclerView) view.findViewById(R.id.reciclador);
        layoutManager = new LinearLayoutManager(getActivity());
        reciclador.setLayoutManager(layoutManager);
        COMIDAS_POPULARES_COPIA = new ArrayList<>();
        COMIDAS_POPULARES_COPIA.addAll(COMIDAS_POPULARES);
        adaptador = new AdaptadorInicio();
        adaptador = new AdaptadorInicio(COMIDAS_POPULARES);
        reciclador.setAdapter(adaptador);
        return view;
    }
    private void poblarMaquinas() {
        COMIDAS_POPULARES = new ArrayList<Comida>();
        COMIDAS_POPULARES.add(new Comida(1, "Maquina 2k", R.drawable.camarones));
        COMIDAS_POPULARES.add(new Comida(2, "Maquina 22", R.drawable.rosca));
        COMIDAS_POPULARES.add(new Comida(3, "Maquina 44", R.drawable.sushi));
        COMIDAS_POPULARES.add(new Comida(5, "Maquina 5K", R.drawable.sandwich));
        COMIDAS_POPULARES.add(new Comida(3f, "Maquina S3K", R.drawable.lomo_cerdo));

        //COMIDAS_POPULARES_COPIA.addAll(COMIDAS_POPULARES);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_categorias, menu);
//        MenuItem itemSearch = menu.findItem(R.id.action_search);
//        SearchManager searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);
//
//        if (itemSearch != null) {
//            mSearchView = (SearchView) MenuItemCompat.getActionView(itemSearch);
//            MenuItemCompat.collapseActionView(itemSearch);
//        }

        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();

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

    }
}
