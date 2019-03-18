package com.jeremysuh.teacuppy;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import java.lang.Math;

public class TeaDetail extends AppCompatActivity {

    public final static int GO_TO_TIMER = 101;

    Intent myIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tea_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        myIntent = getIntent();

        TextView tea_name = (TextView) findViewById(R.id.ff);
        tea_name.setText(myIntent.getStringExtra("tea_name"));

        TextView tea_description = (TextView) findViewById(R.id.tea_description);
        tea_description.setText(myIntent.getStringExtra("tea_description"));

        ImageView tea_detail_image = (ImageView) findViewById(R.id.tea_detail_image);

        String added_tea_name = myIntent.getStringExtra("tea_name");
        Log.d("name", added_tea_name);

        int tea_img_id = set_tea_image(added_tea_name, tea_detail_image);

        tea_img_id = (int)Math.round(Math.random()*5)+1;

        switch (tea_img_id){

            case 1: tea_detail_image.setImageResource(R.drawable.tea_1);
                break;
            case 2: tea_detail_image.setImageResource(R.drawable.tea_2);
                break;
            case 3: tea_detail_image.setImageResource(R.drawable.tea_3);
                break;
            case 4: tea_detail_image.setImageResource(R.drawable.tea_4);
                break;
            case 5: tea_detail_image.setImageResource(R.drawable.tea_5);
                break;
            case 6: tea_detail_image.setImageResource(R.drawable.tea_6);
                break;
            case 7: tea_detail_image.setImageResource(R.drawable.tea_7);
                break;
            default: tea_detail_image.setImageResource(R.drawable.tea_1);
                break;

        }


        TextView tea_caffeine = (TextView) findViewById(R.id.tea_caffeine);
        tea_caffeine.setText("Caffine: " + myIntent.getStringExtra("tea_caffeine") + " mg");

        TextView tea_temperature = (TextView) findViewById(R.id.tea_temperature);
        tea_temperature.setText("Temp: " + myIntent.getStringExtra("tea_temperature") + " F");

        TextView tea_time = (TextView) findViewById(R.id.tea_time);
        tea_time.setText(String.valueOf("Time: " + myIntent.getStringExtra("tea_time") + " s"));



        Button addButton = (Button) findViewById(R.id.brew_button);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent backIntent = new Intent();

                backIntent.putExtra("testy", "five");

                backIntent.putExtra("tea_name", TeaDetail.this.myIntent.getStringExtra("tea_name"));
                backIntent.putExtra("tea_time", TeaDetail.this.myIntent.getStringExtra("tea_time"));
                backIntent.putExtra("tea_calories", TeaDetail.this.myIntent.getStringExtra("tea_calories"));
                backIntent.putExtra("tea_caffeine", TeaDetail.this.myIntent.getStringExtra("tea_caffeine"));

                setResult(GO_TO_TIMER, backIntent);

                finish();
            }
        });



    }

    private int set_tea_image(String name, ImageView tea_detail_image){

        if (name.equals("Black Tea")) return 1;
        if (name.equals("Oolong Tea")) return 2;
        if (name.equals("Green Tea")) return 3;;
        if (name.equals("White Tea")) return 4;
        if (name.equals("Pu-erh Tea")) return 5;
        if (name.equals("Herbal Tea")) return 6;
        if (name.equals("Matcha")) return 7;
        return 0;
    }


}
