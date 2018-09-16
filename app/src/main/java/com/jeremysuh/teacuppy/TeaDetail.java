package com.jeremysuh.teacuppy;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

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

                setResult(GO_TO_TIMER, backIntent);

                finish();
            }
        });



    }


}
