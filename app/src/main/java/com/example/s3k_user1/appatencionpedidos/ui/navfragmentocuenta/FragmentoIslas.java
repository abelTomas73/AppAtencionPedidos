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
import android.widget.Toast;

import com.example.s3k_user1.appatencionpedidos.R;
import com.example.s3k_user1.appatencionpedidos.model.Isla;
import com.example.s3k_user1.appatencionpedidos.ui.navegacionlateral.FragmentoInicio;
import com.example.s3k_user1.appatencionpedidos.utils.Utils;
import com.pranavpandey.android.dynamic.toasts.DynamicToast;

import java.util.ArrayList;
import java.util.List;

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragmento_islas, container, false);
        getActivity().setTitle("Islas");
        poblarIslas();
        recyclerview_id = view.findViewById(R.id.recyclerview_id);
        btnSeleccionarIsla = view.findViewById(R.id.btnSeleccionarIsla);
        btnAtrasZonas = view.findViewById(R.id.btnAtrasZonas);

        btnSeleccionarIsla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (FragmentoIslas.ISLAELEGIDA==null){
//                    Toast.makeText(getActivity(), "Eliga una Isla", Toast.LENGTH_SHORT).show();
                    DynamicToast.makeError(getContext(), "Eliga una Isla", Toast.LENGTH_LONG).show();
                }else{
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
                FragmentoInicio.ZONAELEGIDA=null;
                Fragment fragment = new FragmentoInicio();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.contenedor_principal, fragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        islaListFull = new ArrayList<>();
        islaListFull.addAll(islaList);

        adaptador = new AdaptadorIslas(getContext(),islaList);

        SearchView searchView =  view.findViewById(R.id.search_isla);

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

        recyclerview_id.setHasFixedSize(true);
        recyclerview_id.setLayoutManager(manager);
        recyclerview_id.setAdapter(adaptador);
        return  view;
    }

}
