package com.jeremysuh.teacuppy;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class Tea_List extends Fragment {

    private List<Tea> teaList = new ArrayList<>();
    private RecyclerView recyclerView;
    private TeaAdapter mAdapter;

    private static boolean listInitialized = false;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_tea, container, false);
       // TextView textview = (TextView) rootView.findViewById(R.id.text_test);
        //textview.setText("wee woo");

        //avoid duplicate? idk yet..

            recyclerView = (RecyclerView) rootView.findViewById(R.id.tea_list);


            mAdapter = new TeaAdapter(teaList, getActivity());
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
            recyclerView.setLayoutManager(mLayoutManager);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setAdapter(mAdapter);
            recyclerView.addItemDecoration(new DividerItemDecoration(getContext(),
                    DividerItemDecoration.VERTICAL));

//        Log.d("yee", "this exists....llolololo");

        FloatingActionButton add_tea_button = rootView.findViewById(R.id.add_button);
        add_tea_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent = new Intent(getActivity(), TeaAdd.class);
                startActivityForResult(intent, 1);


            }
        });



        if (!listInitialized) initTeaData();
            listInitialized = true;



        // recycleview.bac
        return rootView;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d("fire", "just success testing");

        super.onActivityResult(requestCode, resultCode, data);
        add_tea();



    }


    public void updateAdapter(){
        mAdapter.notifyDataSetChanged();

    }

    public void add_tea(){
        teaList.add(new Tea("test tea", "50", 2, "desc", 5));
        updateAdapter();
    }


    public void initTeaData(){


        Tea tea = new Tea("Black Tea", "20", 5, "1", 1);
        teaList.add(tea);

        tea = new Tea("Green Tea", "40", 5, "1", 1);
        teaList.add(tea);

        tea = new Tea("Oolong Tea", "30", 5, "1", 1);
        teaList.add(tea);

        tea = new Tea("Yellow Tea", "20", 5, "1", 1);
        teaList.add(tea);

        tea = new Tea("Jasmine Tea", "20", 5, "1", 1);
        teaList.add(tea);

        tea = new Tea("Whey Tea", "30", 5, "1", 1);
        teaList.add(tea);

        tea = new Tea(" Milk Tea", "20", 5, "1", 1);
        teaList.add(tea);

        tea = new Tea("Flower Bad Tea", "20", 5, "1", 1);
        teaList.add(tea);


        mAdapter.notifyDataSetChanged();

    }
}