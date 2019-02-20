package com.jeremysuh.teacuppy;


import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class Timer extends Fragment{

    public static View rootView;
    public static TextView tea_name;
    public static TextView tea_time;

    static boolean timer_on = false;
    static int clock_time = 60;

    static Handler timerHandler = new Handler();
    static Runnable timerRunnable = new Runnable(){

        @Override
        public void run() {

            if (clock_time <= -1 ) {
                tea_time.setText("Tea is ready!");
                //update stats
                Personal.update_user_data();

            }else{
                tea_time.setText(Integer.toString(clock_time--) + " seconds");
            }
            Log.d("water", "timer works");
            timerHandler.postDelayed(this, 1000);

        }
    };


    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        rootView = inflater.inflate(R.layout.fragment_timer, container, false);
        tea_name = (TextView) rootView.findViewById(R.id.ff);
        tea_time = (TextView) rootView.findViewById(R.id.tea_time);

        final Button start_button = (Button) rootView.findViewById(R.id.start_button);
        start_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (timer_on){
                    timer_on = false;
                    start_button.setText("Start");
                    timerHandler.removeCallbacks(timerRunnable);

                }else{
                    timer_on = true;
                    start_button.setText("Stop");
                    timerHandler.postDelayed(timerRunnable, 0);
                }
            }
        });

        final Button choose_tea_button = (Button) rootView.findViewById(R.id.choose_tea_button);
        choose_tea_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TabLayout tabLayout = (TabLayout) getActivity().findViewById(R.id.tabs);
                TabLayout.Tab tab = tabLayout.getTabAt(0);
                tab.select();
            }
        });



        Log.d("fire", "timer works");


        // recycleview.bac
        return rootView;
    }


    public static void reset_clock(){
        timerHandler.removeCallbacks(timerRunnable);

    }

    public static void update(String name, int time){

        reset_clock();

        clock_time = time;
        tea_name.setText(name);
        tea_time.setText(Integer.toString(time) + " seconds");

    }

}