package com.jeremysuh.teacuppy;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class Tea_List extends Fragment {

    private List<Tea> teaList = new ArrayList<>();
    private RecyclerView recyclerView;
    private TeaAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_tea, container, false);
       // TextView textview = (TextView) rootView.findViewById(R.id.text_test);
        //textview.setText("wee woo");

        //avoid duplicate? idk yet..

            recyclerView = (RecyclerView) rootView.findViewById(R.id.tea_list);


            mAdapter = new TeaAdapter(teaList);
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
            recyclerView.setLayoutManager(mLayoutManager);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setAdapter(mAdapter);

//        Log.d("yee", "this exists....llolololo");

            initTeaData();



        // recycleview.bac
        return rootView;
    }


    public void initTeaData(){
        Tea tea = new Tea("Black Tea", "20");
        teaList.add(tea);

        tea = new Tea("Green Tea", "40");
        teaList.add(tea);

        tea = new Tea("Oolong Tea", "30");
        teaList.add(tea);

        tea = new Tea("Yellow Tea", "20");
        teaList.add(tea);

        tea = new Tea("Fire Tea", "20");
        teaList.add(tea);

        Log.d("yee", "this exists....wooo");

        mAdapter.notifyDataSetChanged();

    }
}