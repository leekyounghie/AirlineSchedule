package com.starnamu.projcet.airlineschedule.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.starnamu.projcet.airlineschedule.R;
import com.starnamu.projcet.airlineschedule.comm.CommonConventions;
import com.starnamu.projcet.airlineschedule.parser.AirLineAdapter;
import com.starnamu.projcet.airlineschedule.parser.AirlineItem;

import java.util.ArrayList;


/**
 * Created by Edwin on 15/02/2015.
 */
public class ArrivalAirlineFragment extends Fragment implements CommonConventions {

    ListView ArrivalAirlineListView;
    AirLineAdapter airLineAdapter;
    ArrayList<AirlineItem> Temitems;
    ArrayList<AirlineItem> items;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.arrivalairlinefragment, container, false);

        Temitems = new ArrayList<>();
        airLineAdapter = new AirLineAdapter(getActivity());
        ArrivalAirlineListView = (ListView) v.findViewById(R.id.ArrivalAirlineListView);
        ArrivalAirlineListView.setAdapter(airLineAdapter);

        return v;
    }

    @Override
    public void onStart() {
        super.onStart();

        Bundle bundle = getArguments();
        items = (ArrayList<AirlineItem>) bundle.getSerializable("items");

        for (int i = 0; i < items.size(); i++) {
            AirlineItem item = items.get(i);
            if (adCheck(item.getStriItem(10))) {
                if (airlineCheck(item.getStriItem(0))) {
                    if (flightCheck(item.getStriItem(3))) {
                        Temitems.add(item);
                    }
                }
            }
            airLineAdapter.setItemList(Temitems);
            airLineAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
    }


    /*Arraylist의 지료를 원하는 형태로 걸러낸다.*/
    private boolean adCheck(String airline) {
        if (airline.equals("A")) {
            return true;
        }
        return false;
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
