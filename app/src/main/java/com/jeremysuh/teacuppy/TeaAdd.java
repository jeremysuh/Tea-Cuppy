package com.jeremysuh.teacuppy;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class TeaAdd extends AppCompatActivity {

    public final static int ADD_TEA = 101;

    EditText edit_tea_name, edit_tea_time, edit_tea_description,
            edit_tea_caffeine, edit_tea_temperature;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tea_add);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        Button addButton = (Button) findViewById(R.id.add_button);
        edit_tea_name = (EditText) findViewById(R.id.edit_tea_name);
        edit_tea_time = (EditText) findViewById(R.id.edit_tea_time);
        edit_tea_caffeine = (EditText) findViewById(R.id.edit_tea_caffeine);
        edit_tea_temperature = (EditText) findViewById(R.id.edit_tea_temperature);
        edit_tea_description = (EditText) findViewById(R.id.edit_tea_description);


        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra("tea_name", TeaAdd.this.edit_tea_name.getText().toString());
                intent.putExtra("tea_description", TeaAdd.this.edit_tea_description.getText().toString());
                intent.putExtra("tea_caffeine", TeaAdd.this.edit_tea_caffeine.getText().toString());
                intent.putExtra("tea_time", TeaAdd.this.edit_tea_time.getText().toString());
                intent.putExtra("tea_temperature", TeaAdd.this.edit_tea_temperature.getText().toString());

                setResult(ADD_TEA, intent);
                finish();
            }
        });


    }

}
