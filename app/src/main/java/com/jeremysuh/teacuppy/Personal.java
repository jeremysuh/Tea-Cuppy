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
import java.util.Calendar;


import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TimeZone;

public class Personal extends Fragment{

    public static View rootView;
    //
    public static GraphView graph;


    //data

    public static int total_cups_drank = 0;
    public static int total_caffeine_consumed = 0;
    public static String favorite_tea = "-";

    public static class UserData {
        int cups_drank = 0;
        int caffeine_consumed = 0;
        HashMap<String, Integer> tea_drank = new HashMap<String, Integer>();
    }
    static HashMap<String, UserData> user = new HashMap<String, UserData>();


    //key = data, value = day stats class?


    static String[] tea_favorite;



    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        rootView = inflater.inflate(R.layout.fragment_personal, container, false);
        // TextView textview = (TextView) rootView.findViewById(R.id.text_test);
        //textview.setText("wee woo")


        graph = (GraphView) rootView.findViewById(R.id.graph_one);
        LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>(new DataPoint[] {
                new DataPoint(0, 0),
                new DataPoint(1, 5),
                new DataPoint(2, 3),
                new DataPoint(3, 2),
                new DataPoint(4, 6)
        });
        graph.addSeries(series);

        load_user_data();

        // recycleview.bac
        return rootView;
    }

    public static void load_user_data(){


        //fill with empty user data day struct/classes


    }

    public static void update_user_data(){

        //get calendar instance
        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());

        //get key
        String key = "";
        key = key + "year" + calendar.get(Calendar.YEAR);
        key = key + "month" + (calendar.get(Calendar.MONTH)+1);
        key = key + "day" + calendar.get(Calendar.DAY_OF_MONTH);

         Log.d("fire", key);

         if (user.containsKey(key)){

             UserData userData_new = user.get(key);
             userData_new.cups_drank = user.get(key).cups_drank + 1;

             if (user.get(key).tea_drank.containsKey(Timer.tea_name.toString())){
                 //dont think this part will call
                 Integer i = user.get(key).tea_drank.get(Timer.tea_name.toString()) + 1;
                 Log.d("fire", i.toString());

                 userData_new.tea_drank.put(Timer.tea_name.toString(), i);

             }else{

                 Log.d("fire", "1");
                 userData_new.tea_drank.put(Timer.tea_name.toString(), 1);

             }

             user.remove(key);
             user.put(key, userData_new);


         }else{ //store

             UserData userData = new UserData();
             userData.cups_drank = userData.cups_drank + 1;

             if (userData.tea_drank.containsKey(Timer.tea_name.toString())){
                //dont think this part will call
                 Integer i = userData.tea_drank.get(Timer.tea_name.toString()) + 1;
                 Log.d("fire", i.toString());

                 userData.tea_drank.put(Timer.tea_name.toString(), i);

             }else{

                 Log.d("fire", "1");
                 userData.tea_drank.put(Timer.tea_name.toString(), 1);

             }

             user.put(key, userData);

         }


        update_graphs();

    }

    public static void update_graphs(){

        graph.removeAllSeries();
        LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>(new DataPoint[] {
                new DataPoint(0, 0),
                new DataPoint(1, 5),
                new DataPoint(2, 3),
                new DataPoint(3, 2),
                new DataPoint(4, 6)
        });
        graph.addSeries(series);


    }

}