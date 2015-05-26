package com.starnamu.projcet.airlineschedule;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;

import com.starnamu.projcet.airlineschedule.fragment.ArrivalAirlineFragment;
import com.starnamu.projcet.airlineschedule.fragment.DepartureAirLineFragment;
import com.starnamu.projcet.airlineschedule.parser.AirlineItem;

import java.util.ArrayList;


/**
 * Created by Edwin on 15/02/2015.
 */
public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    CharSequence Titles[]; // This will Store the Titles of the Tabs which are Going to be passed when ViewPagerAdapter is created
    int NumbOfTabs; // Store the number of tabs, this will also be passed when the ViewPagerAdapter is created
    ArrayList<AirlineItem> items;


    // Build a Constructor and assign the passed Values to appropriate values in the class
    public ViewPagerAdapter(FragmentManager fm, CharSequence mTitles[], int mNumbOfTabsumb, ArrayList<AirlineItem> items) {
        super(fm);

        this.Titles = mTitles;
        this.NumbOfTabs = mNumbOfTabsumb;
        this.items = items;

    }

    //This method return the fragment for the every position in the View Pager
    @Override
    public Fragment getItem(int position) {

        Bundle bundle = new Bundle();
        bundle.putSerializable("items", items);

        if (position == 0) // if the position is 0 we are returning the First tab
        {
            ArrivalAirlineFragment arrivalAirlineFragment = new ArrivalAirlineFragment();
            arrivalAirlineFragment.setArguments(bundle);
            return arrivalAirlineFragment;
        } else             // As we are having 2 tabs if the position is now 0 it must be 1 so we are returning second tab
        {
            DepartureAirLineFragment departureAirLineFragment = new DepartureAirLineFragment();
            departureAirLineFragment.setArguments(bundle);
            return departureAirLineFragment;
        }

    }

    // This method return the titles for the Tabs in the Tab Strip

    @Override
    public CharSequence getPageTitle(int position) {
        return Titles[position];
    }

    @Override
    public int getCount() {
        return NumbOfTabs;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        return super.instantiateItem(container, position);
    }

    @Override
    public int getItemPosition(Object item) {
        return POSITION_NONE;
    }
}