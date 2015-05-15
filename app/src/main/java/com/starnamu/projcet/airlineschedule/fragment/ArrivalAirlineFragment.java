package com.starnamu.projcet.airlineschedule.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.starnamu.projcet.airlineschedule.R;
import com.starnamu.projcet.airlineschedule.comm.CommonConventions;
import com.starnamu.projcet.airlineschedule.test.AirLineAdapter;
import com.starnamu.projcet.airlineschedule.test.AirlineItem;
import com.starnamu.projcet.airlineschedule.test.AirlineParser;

import java.util.ArrayList;


/**
 * Created by Edwin on 15/02/2015.
 */
public class ArrivalAirlineFragment extends Fragment implements CommonConventions {

    ListView ArrivalAirlineListView;
    AirLineAdapter airLineAdapter;
    AirlineParser airlineParser;
    ArrayList<AirlineItem> airlineItems;
    ArrayList<AirlineItem> Items = new ArrayList<>();
    Context mContext = getActivity();

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.arrivalairlinefragment, container, false);

        Context context = getActivity();
        airLineAdapter = new AirLineAdapter(context);
        ArrivalAirlineListView = (ListView) v.findViewById(R.id.ArrivalAirlineListView);
        ArrivalAirlineListView.setAdapter(airLineAdapter);
        airlineParser = new AirlineParser(PARRIVALS);

        return v;
    }

    @Override
    public void onStart() {
        super.onStart();

        airlineItems = airlineParser.getArrayList();
        for (int i = 0; i < airlineItems.size(); i++) {
            AirlineItem item = airlineItems.get(i);
            if (airlineCheck(item.getStriItem(0))) {
                if (flightCheck(item.getStriItem(3))) {
                    Items.add(item);
                }
            }
            airLineAdapter.setItemList(Items);
            airLineAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    private boolean airlineCheck(String airline) {
        if (airline.equals("아시아나항공")) {
            return true;
        }
        return false;
    }

    private boolean flightCheck(String flight) {

        if (flight.length() <= 5) {
            return true;
        }
        return false;
    }
}
