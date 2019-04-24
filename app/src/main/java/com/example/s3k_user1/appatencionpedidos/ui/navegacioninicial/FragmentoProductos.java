package com.example.s3k_user1.appatencionpedidos.ui.navegacioninicial;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.badoualy.stepperindicator.StepperIndicator;
import com.example.s3k_user1.appatencionpedidos.R;
import com.example.s3k_user1.appatencionpedidos.ui.AdaptadorInicio;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentoProductos extends Fragment  {

    StepperIndicator indicator;
    public FragmentoProductos() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

//        indicator.setViewPager(pager);
//// or keep last page as "end page"
//        indicator.setViewPager(pager, pager.getAdapter().getCount() - 1); //
//// or manual change
//        indicator.setStepCount(3);
//        indicator.setCurrentStep(2);


        View view = inflater.inflate(R.layout.fragmento_productos, container, false);
        RecyclerView reciclador = (RecyclerView) view.findViewById(R.id.reciclador_productos);

        final ViewPager pager = view.findViewById(R.id.pager);
        assert pager != null;
        pager.setAdapter(new com.example.s3k_user1.appatencionpedidos.ui.navegacioninicial.PagerAdapter(getFragmentManager()));

        final StepperIndicator indicator = view.findViewById(R.id.stepper_indicator);
        // We keep last page for a "finishing" page
        indicator.setViewPager(pager, true);

        indicator.addOnStepClickListener(new StepperIndicator.OnStepClickListener() {
            @Override
            public void onStepClicked(int step) {
                //if (step ==1){}
                pager.setCurrentItem(step, true);
            }
        });
        return view;
    }

}
