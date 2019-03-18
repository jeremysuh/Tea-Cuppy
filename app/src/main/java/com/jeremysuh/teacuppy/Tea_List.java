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

        recyclerView = (RecyclerView) rootView.findViewById(R.id.tea_list);

        mAdapter = new TeaAdapter(teaList, getActivity());

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);

        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(),
                    DividerItemDecoration.VERTICAL));

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

            return rootView;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == TeaAdd.ADD_TEA){

            add_tea(

                    data.getStringExtra("tea_name"),
                    data.getStringExtra("tea_description"),
                    Integer.parseInt(data.getStringExtra("tea_temperature")),
                    Integer.parseInt(data.getStringExtra("tea_time")),
                    Integer.parseInt(data.getStringExtra("tea_caffeine"))
            );
        }

    }


    public void updateAdapter(){
        mAdapter.notifyDataSetChanged();

    }

    public void add_tea(String name, String description, int temperature, int time, int caffeine){
        teaList.add(new Tea(name, temperature, time, description, caffeine, 1, 0));
        updateAdapter();
    }


    public void initTeaData(){

        Tea tea = new Tea("Black Tea", 205,
                60*4, getString(R.string.black_tea_description),
                47, 1, 0);
        teaList.add(tea);

        tea = new Tea("Oolong Tea", 192, 60*4, getString(R.string.oolong_tea_description), 37, 1,0);
        teaList.add(tea);

        tea = new Tea("Green Tea", 182, 60*3, getString(R.string.green_tea_description), 35, 1,0);
        teaList.add(tea);

        tea = new Tea("White Tea", 180, 60*5, getString(R.string.white_tea_description), 31, 1,0);
        teaList.add(tea);

        tea = new Tea("Pu-erh Tea", 195, 60*4, getString(R.string.puerh_tea_description), 35, 1,0);
        teaList.add(tea);

        tea = new Tea("Herbal Tea", 177, 60*4, getString(R.string.herbal_tea_description), 0, 1,0);
        teaList.add(tea);

        tea = new Tea("Matcha", 175, 60*2, getString(R.string.matcha_tea_description), 72, 1,0);
        teaList.add(tea);


        mAdapter.notifyDataSetChanged();

    }
}