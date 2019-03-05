package com.jeremysuh.teacuppy;


import android.media.RingtoneManager;
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
import android.widget.ImageView;
import android.widget.TextView;

public class Timer extends Fragment{

    public static View rootView;
    public static TextView tea_name;
    public static TextView tea_time;
    public static  Button start_button;

    static boolean timer_on = false;
    static boolean can_update = true;
    static int clock_time = 60;

    static boolean time_ended = false;

    private static ImageView img;

    static Handler timerHandler = new Handler();
    static Runnable timerRunnable = new Runnable(){

        @Override
        public void run() {

            if (clock_time <= 0 ) {
                tea_time.setText("Tea is ready!");

                start_button.setText("Start");
                timer_on = false;
                start_button.setEnabled(false);
                img.setImageResource(R.drawable.teapot_1);

                //update stats
                if (time_ended){
                    Log.d("water", "User Data Updated");

                    if (can_update){
                        Personal.update_user_data();
                        can_update = false;
                    }
                }
                time_ended = true;

            }else{
                time_ended = false;

                if (clock_time % 2 == 0){
                    img.setImageResource(R.drawable.teapot_2);

                }else{
                    img.setImageResource(R.drawable.teapot_3);

                }


                tea_time.setText(Integer.toString(clock_time--) + " seconds");
            }
            timerHandler.postDelayed(this, 1000);

        }
    };


    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {




        rootView = inflater.inflate(R.layout.fragment_timer, container, false);
        tea_name = (TextView) rootView.findViewById(R.id.ff);
        tea_time = (TextView) rootView.findViewById(R.id.tea_time);

        start_button = (Button) rootView.findViewById(R.id.start_button);
        start_button.setEnabled(false);


        img = (ImageView) rootView.findViewById(R.id.imageView3);
        img.setImageResource(R.drawable.teapot_1);

        start_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (timer_on){
                    timer_on = false;
                    start_button.setText("Start");
                    timerHandler.removeCallbacks(timerRunnable);


                }else{
                    timer_on = true;
                    start_button.setText("Pause");
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

        can_update = true;
        timerHandler.removeCallbacks(timerRunnable);

    }

    public static void update(String name, int time){

        reset_clock();

        clock_time = time;
        tea_name.setText(name);
        tea_time.setText(Integer.toString(time) + " seconds");

    }

}