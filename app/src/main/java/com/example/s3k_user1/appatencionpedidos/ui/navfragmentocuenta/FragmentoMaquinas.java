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
import com.example.s3k_user1.appatencionpedidos.model.MaquinaZona;
import com.example.s3k_user1.appatencionpedidos.model.MaquinaZona;
import com.example.s3k_user1.appatencionpedidos.ui.FragmentoCategoria;
import com.example.s3k_user1.appatencionpedidos.ui.navegacionlateral.FragmentoInicio;
import com.example.s3k_user1.appatencionpedidos.utils.Utils;
import com.pranavpandey.android.dynamic.toasts.DynamicToast;

import java.util.ArrayList;
import java.util.List;

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragmento_maquinas, container, false);
        getActivity().setTitle("Maqu");
        poblarMaquinaZonas();
        recyclerview_id = view.findViewById(R.id.recyclerview_id);
        btnSeleccionarMaquinaZona = view.findViewById(R.id.btnSeleccionarMaquina);
        btnAtrasIslas = view.findViewById(R.id.btnAtrasIslas);

        btnSeleccionarMaquinaZona.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (FragmentoMaquinas.MAQUINAELEGIDA==null){
//                    Toast.makeText(getActivity(), "Eliga una Maquina", Toast.LENGTH_SHORT).show();
                    DynamicToast.makeError(getContext(), "Eliga una Maquina", Toast.LENGTH_LONG).show();
                }else{
                    Fragment fragment = new FragmentoArticulos();
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

        maquinaListFull = new ArrayList<>();
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
