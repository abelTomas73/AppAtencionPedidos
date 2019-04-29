package com.example.s3k_user1.appatencionpedidos.ui.navfragmentocuenta;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.s3k_user1.appatencionpedidos.R;
import com.example.s3k_user1.appatencionpedidos.model.Isla;
import com.example.s3k_user1.appatencionpedidos.model.Zona;
import com.example.s3k_user1.appatencionpedidos.modelo.Comida;
import com.example.s3k_user1.appatencionpedidos.utils.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentoIslas extends Fragment {

    private RecyclerView reciclador;

    SearchView mSearchView;

    List<Isla> islaList;
    List<Comida> COMIDAS_POPULARES_COPIA;

    private GridLayoutManager layoutManager;
    private AdaptadorIslas adaptador;

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

        //COMIDAS_POPULARES_COPIA.addAll(COMIDAS_POPULARES);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragmento_islas, container, false);
        if ( FragmentoZonas.ZONAELEGIDA==null){
            Fragment fragment = new FragmentoZonas();
            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.linear_islas, fragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }
        poblarIslas();
        //zonaList.add(new Zona("The Vegitarian"));

        RecyclerView myrv = view.findViewById(R.id.recyclerview_id);
        AdaptadorIslas myAdapter;

        int mNoOfColumns = Utils.calculateNoOfColumns(getContext(),110);

        myAdapter = new AdaptadorIslas(getContext(),islaList);
        myrv.setLayoutManager(new GridLayoutManager(getContext(),mNoOfColumns));
        myrv.setAdapter(myAdapter);

        return  view;
    }

}
