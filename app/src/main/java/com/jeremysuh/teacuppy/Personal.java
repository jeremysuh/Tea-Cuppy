package com.jeremysuh.teacuppy;


import android.graphics.Color;
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
import android.widget.Button;
import android.widget.TextView;
import java.util.Calendar;



import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GridLabelRenderer;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TimeZone;

public class Personal extends Fragment{

    public static View rootView;

    //graphs
    public static GraphView graph;
    public static GridLabelRenderer gridLabel;

    //database
    private static UserDatabaseHelper db;

    //values for each stats tab
    private  static int cup_values[] = {40, 50, 43, 2, 20, 41, 39};
    private  static int caffeine_values[] = {8, 19, 11, 39, 32, 5, 22};
    private  static int calorie_values[] = {29, 14, 15, 45, 27, 30, 44};


    //to add to stats
    public static int to_add_calories = 0;
    public static int to_add_caffeine = 0;

    //screen state
    private static int current_screen = 1;

    //data
    public static int graph_num = 1; // 1 = cup, 2 = caffeine, 3 = calories


    public static int temp = 0;

    public static int total_cups_drank = 0;
    public static int total_caffeine_consumed = 0;
    public static String favorite_tea = "-";

    public static class UserData {
        int cups_drank = 0;
        int caffeine_consumed = 0;
        HashMap<String, Integer> tea_drank = new HashMap<String, Integer>();
    }
    static HashMap<String, UserData> user = new HashMap<String, UserData>();

    static String[] tea_favorite; //store favorite tea


    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        rootView = inflater.inflate(R.layout.fragment_personal, container, false);

        //instantiate database handler
        db=new UserDatabaseHelper(getContext());

        Button calories_button;
        Button caffeine_button;
        Button cup_button;

        graph = (GraphView) rootView.findViewById(R.id.graph_one);
        gridLabel = graph.getGridLabelRenderer();
        gridLabel.setHorizontalAxisTitle("Days");
        gridLabel.setVerticalAxisTitle("Cups");
        gridLabel.setPadding(32);

        graph.setTitle("Cups (Past 7 days)");
        graph.setBackgroundColor(Color.argb(0,0,0,0));

        graph.getViewport().setMinX(1);
        graph.getViewport().setMaxX(7);
        graph.getGridLabelRenderer().setNumHorizontalLabels(7);
        graph.getViewport().setXAxisBoundsManual(true);

        //initialize graph
        LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>(new DataPoint[] {
                new DataPoint(1, cup_values[0]),
                new DataPoint(2, cup_values[1]),
                new DataPoint(3, cup_values[2]),
                new DataPoint(4, cup_values[3]),
                new DataPoint(5, cup_values[4]),
                new DataPoint(6, cup_values[5]),
                new DataPoint(7, cup_values[6]),
        });

        graph.addSeries(series);
        load_user_data();


        cup_button = (Button) rootView.findViewById(R.id.cup_button);
        cup_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                if (graph_num != 1) show_cups();
                graph_num = 1;

                Log.d("fire", "cup!");

            }
        });


        caffeine_button = (Button) rootView.findViewById(R.id.caffeine_button);
        caffeine_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                if (graph_num != 2) show_caffeine();
                graph_num = 2;

            }
        });


        calories_button = (Button) rootView.findViewById(R.id.calories_button);
        calories_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                if (graph_num != 3) show_calories();
                graph_num = 3;

            }
        });


        load_user_data();

        // recycleview.bac
        return rootView;
    }

    public static void load_user_data(){


        //fill with empty user data day struct/classes
        String last_seven_days[] = {"","","","","","","",};

        //calendar instance and fill List with all; databse entries
        Calendar calendar2 = Calendar.getInstance(TimeZone.getDefault());
        ArrayList<Date> contacts2 = new ArrayList<>(db.get_allDates());

        //add total

        int total_cups = 0;
        int total_calories = 0;
        int total_caffeine = 0;

        //scan through all entries and load total stats
        for (int c = 0; c < contacts2.size(); ++c){

            total_cups += contacts2.get(c).get_cups();
            total_caffeine += contacts2.get(c).get_caffeine();
            total_calories += contacts2.get(c).get_calories();

        }

        //edit text
        TextView cup_text = (TextView) rootView.findViewById(R.id.cup_text);
        cup_text.setText(""+total_cups);

        TextView caffeine_text = (TextView) rootView.findViewById(R.id.caffeine_text);
        caffeine_text.setText(""+total_caffeine+" mg");

        TextView calorie_text = (TextView) rootView.findViewById(R.id.calorie_text);
        calorie_text.setText(""+total_calories+ " kcal");

        //get last 7 day key values
        for (int i = 0; i < 7; ++i) {

            String key2 = "";
            key2 = key2 + "year" + calendar2.get(Calendar.YEAR);
            key2 = key2 + "month" + (calendar2.get(Calendar.MONTH) + 1);
            key2 = key2 + "day" + calendar2.get(Calendar.DAY_OF_MONTH);

            last_seven_days[7-i-1] = key2;
            calendar2.add(Calendar.DAY_OF_YEAR, -1);

        }

        // load info for the past 7 days
        for (int i = 0; i < 7; ++i){

            String key_2 = last_seven_days[7-i-1];
            boolean found_2 = false;

            for(int j = 0; j < contacts2.size(); ++j){

                if (key_2.equals(contacts2.get(j).get_date())){ //found

                    found_2 = true;
                    cup_values[7-i-1]  = contacts2.get(j).get_cups();
                    caffeine_values[7-i-1]= contacts2.get(j).get_caffeine();
                    calorie_values[7-i-1] = contacts2.get(j).get_calories();
                    break;

                }

            }

            if (!found_2){
                cup_values[7-i-1]  = 0;
                caffeine_values[7-i-1] = 0;
                calorie_values[7-i-1] = 0;
            }

        }

        //change graph depending on which screen state
        if (current_screen == 1){
            show_cups();
        }else if (current_screen == 2){
            show_caffeine();
        }else{
            show_calories();
        }

    }

    public static void update_user_data(){

        //get calendar instance
        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());

        //get key
        String key = "";
        key = key + "year" + calendar.get(Calendar.YEAR);
        key = key + "month" + (calendar.get(Calendar.MONTH)+1);
        key = key + "day" + calendar.get(Calendar.DAY_OF_MONTH);

        ArrayList<Date> contacts = new ArrayList<>(db.get_allDates());

        int old_cups = 0;
        int old_caffeine = 0;
        int old_calories = 0;

        //look through data
        boolean found = false;
        for(int i = 0; i < contacts.size(); ++i){

            if (key.equals(contacts.get(i).get_date())){
                found = true;
                old_cups = contacts.get(i).get_cups();
                old_caffeine = contacts.get(i).get_caffeine();
                old_calories = contacts.get(i).get_calories();

            }

        }

        //if key exists, just update
        if (found){

            Date d = new Date(key, 1+old_cups, to_add_calories+old_calories, to_add_caffeine+old_caffeine);
            db.updateDate(d);

        }else{ //otherwise, add new date entry

            Date d = new Date(key, 0, 0, 0);
            db.addDayStats(d);
        }

        load_user_data();
    }

    public static void show_cups() {

        current_screen = 1;
        graph.removeAllSeries();

        //update to cups info
        LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>(new DataPoint[] {
                new DataPoint(1, cup_values[0]),
                new DataPoint(2, cup_values[1]),
                new DataPoint(3, cup_values[2]),
                new DataPoint(4, cup_values[3]),
                new DataPoint(5, cup_values[4]),
                new DataPoint(6, cup_values[5]),
                new DataPoint(7, cup_values[6]),
        });


        gridLabel.setHorizontalAxisTitle("Days");
        gridLabel.setVerticalAxisTitle("Cups");
        graph.setTitle("Cups (Past 7 days)");

        graph.addSeries(series);


    }


    public static void show_calories() {

        graph.removeAllSeries();
        current_screen = 3;

        //update to calorie info
        LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>(new DataPoint[] {
                new DataPoint(1, calorie_values[0]),
                new DataPoint(2, calorie_values[1]),
                new DataPoint(3, calorie_values[2]),
                new DataPoint(4, calorie_values[3]),
                new DataPoint(5, calorie_values[4]),
                new DataPoint(6, calorie_values[5]),
                new DataPoint(7, calorie_values[6]),
        });

        //update labels
        gridLabel.setHorizontalAxisTitle("Days");
        gridLabel.setVerticalAxisTitle("Calories (kcal)");
        graph.setTitle("Calories (Past 7 days)");

        graph.addSeries(series);

    }

    public static void show_caffeine() {

        //change screen state
        current_screen = 1;

        //clear graph
        graph.removeAllSeries();

        //update graph to caffeine values
        LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>(new DataPoint[] {
                new DataPoint(1, caffeine_values[0]),
                new DataPoint(2, caffeine_values[1]),
                new DataPoint(3, caffeine_values[2]),
                new DataPoint(4, caffeine_values[3]),
                new DataPoint(5, caffeine_values[4]),
                new DataPoint(6, caffeine_values[5]),
                new DataPoint(7, caffeine_values[6]),
        });

        //update labels
        gridLabel.setHorizontalAxisTitle("Days");
        gridLabel.setVerticalAxisTitle("Caffeine (mg)");
        graph.setTitle("Caffeine (Past 7 days)");
        graph.addSeries(series);
    }

        //clear graphs
        public static void update_graphs(){
        graph.removeAllSeries();
        }

}