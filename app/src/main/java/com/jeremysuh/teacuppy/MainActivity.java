package com.jeremysuh.teacuppy;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TabHost;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.GooglePlayServicesUtil;


import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //random comment
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Log.d("fire", "fire");


        //Create the adapter that will return a fragment for each of the four
       //  primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        Log.d("fire", "grass");


        //  Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setOffscreenPageLimit(3);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));

        Log.d("fire", "purple");

        //FloatingActionButton bu = (FloatingActionButton) findViewById(R.id.test_button);



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        return true;
    }


    @Override
    public void onResume(){
        super.onResume();

        Log.d("fire", "water");
    }

        @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
      //  if (id == R.id.action_settings) {
        //    return true;
      //  }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d("fire", ""+requestCode);


        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 2 && resultCode == TeaDetail.GO_TO_TIMER) {

            Log.d("fire", data.getStringExtra("testy"));


            //stats to add after brew complete
            Personal.to_add_caffeine = Integer.parseInt(data.getStringExtra("tea_caffeine"));
            Personal.to_add_calories = Integer.parseInt(data.getStringExtra("tea_calories"));


            Timer.update(data.getStringExtra("tea_name"), Integer.parseInt(data.getStringExtra("tea_time")));
            Timer.start_button.setEnabled(true);
            //here


            TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
            TabLayout.Tab tab = tabLayout.getTabAt(1);
            tab.select();
        }

    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).

            switch (position) {

                case 0:
                    Log.d("fire", "one tab");
                    return new Tea_List();
                case 1:
                    Log.d("fire", "two tab");
                    return new Timer();
                case 2:
                    Log.d("fire", "three tab");
                    return new Personal();
                default: return null;

            }
         //   return PlaceholderFragment.newInstance(position + 1);
        }



        @Override
        public int getCount() {
            // Show 4 total pages.
            return 3;
        }
    }
}
