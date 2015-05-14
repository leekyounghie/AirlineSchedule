package com.starnamu.projcet.airlineschedule.test;


import com.starnamu.projcet.airlineschedule.comm.CommonConventions;

/**
 * Created by starnamu on 2015-05-08.
 */
public class AirlineItem implements CommonConventions {


    String[] DepartureStrItem = new String[PARSERITEMGROUP.length];

    public AirlineItem(String[] strings) {
        for (int i = 0; i < strings.length; i++) {
            this.DepartureStrItem[i] = strings[i];
        }
    }


    public String getStriItem(int index) {
        return DepartureStrItem[index];
    }
}
