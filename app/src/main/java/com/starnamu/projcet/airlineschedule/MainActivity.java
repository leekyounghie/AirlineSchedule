package com.starnamu.projcet.airlineschedule;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.starnamu.projcet.airlineschedule.choiceoption.ChoiceOptionFragment;
import com.starnamu.projcet.airlineschedule.comm.CommonConventions;
import com.starnamu.projcet.airlineschedule.parser.AirlineItem;
import com.starnamu.projcet.airlineschedule.parser.AirlineParser;

import java.util.ArrayList;


/**
 * Created by Edwin on 15/02/2015.
 */
public class MainActivity extends ActionBarActivity implements CommonConventions,
        ChoiceOptionFragment.CustomOnClickListener, ChoiceOptionFragment.CustonListOnClickListener {

    // Declaring Your View and Variables

    Toolbar toolbar;
    ViewPager pager;
    ViewPagerAdapter adapter;
    SlidingTabLayout tabs;
    CharSequence Titles[] = {"도착편", "출발편"};
    int Numboftabs = 2;
    DrawerLayout dlDrawer;
    ActionBarDrawerToggle dtToggle;

    ArrayList<AirlineItem> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        items = Intro_Activity.items;
        try {
            stateUrlConnation();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void stateUrlConnation() throws InterruptedException {
        boolean state = true;

        while (state) {
            if (items == null) {
                Log.i("from Intro_Activity", "Null");
                state = true;
            }
            if (items != null) {
                startMetrialView();
                state = false;
                Thread.sleep(1000);
                Log.i("from Intro_Activity", "Not Null");
            }
        }
    }

    private void startMetrialView() {
        // Creating The Toolbar and setting it as the Toolbar for the activity


        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);

        dlDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        dtToggle = new ActionBarDrawerToggle(this, dlDrawer, R.string.app_name, R.string.app_name);
        dlDrawer.setDrawerListener(dtToggle);

        // Creating The ViewPagerAdapter and Passing Fragment Manager, Titles fot the Tabs and Number Of Tabs.
        adapter = new ViewPagerAdapter(getSupportFragmentManager(), Titles, Numboftabs, items);

        // Assigning ViewPager View and setting the adapter
        pager = (ViewPager) findViewById(R.id.pager);
        pager.setAdapter(adapter);

        // Assiging the Sliding Tab Layout View
        tabs = (SlidingTabLayout) findViewById(R.id.tabs);
        tabs.setDistributeEvenly(true); // To make the Tabs Fixed set this true, This makes the tabs Space Evenly in Available width

        // Setting Custom Color for the Scroll bar indicator of the Tab View
        tabs.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
            @Override
            public int getIndicatorColor(int position) {
                return getResources().getColor(R.color.tabsScrollColor);
            }
        });

        // Setting the ViewPager For the SlidingTabsLayout
        tabs.setViewPager(pager);
    }

    @Override
    public void onStop() {
        super.onStop();
        /**프로세스 완전 종료 방법*/
        finish();
        android.os.Process.killProcess(android.os.Process.myPid());
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        dtToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        dtToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (dtToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onClicked() {
        AirlineParser parser = new AirlineParser(PDEPARTURES, PARRIVALS);
        items = parser.getArrayList();
    }

    @Override
    public void onListClicked(int choicetime) {
//        AirlineParser parser = new AirlineParser(PDEPARTURES, PARRIVALS);
//        items = parser.getArrayList();
        adapter.notifyDataSetChanged();
    }
}