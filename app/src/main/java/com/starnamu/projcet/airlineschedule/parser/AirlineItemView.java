package com.starnamu.projcet.airlineschedule.parser;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.starnamu.projcet.airlineschedule.R;

/**
 * Created by starnamu on 2015-05-08.
 */
public class AirlineItemView extends LinearLayout {

    TextView textView02, textView03, textView04, textView05, textView06, textView07;

    public AirlineItemView(Context context) {
        super(context);
        init(context);
    }

    public AirlineItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public void init(Context context) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.listviewitem, this, true);

        textView02 = (TextView) findViewById(R.id.textView02);
        textView03 = (TextView) findViewById(R.id.textView03);
        textView04 = (TextView) findViewById(R.id.textView04);
        textView05 = (TextView) findViewById(R.id.textView05);
        textView06 = (TextView) findViewById(R.id.textView06);
        textView07 = (TextView) findViewById(R.id.textView07);
    }

    public void setAirlineItem(AirlineItem items) {


        AirlineItem DAitem = items;

        String flightId = DAitem.getStriItem(3);
        textView02.setText(flightId);


        String scheduleDateTime = DAitem.getStriItem(4);
        textView03.setText(scheduleDateTime);

        String estimatedDateTime = DAitem.getStriItem(5);
        textView04.setText(estimatedDateTime);

        String gatenumber = DAitem.getStriItem(7);
        textView05.setText(gatenumber);

        String remark = DAitem.getStriItem(9);
        textView06.setText(remark);


        String carousel = DAitem.getStriItem(8);
        textView07.setText(carousel);


    }
}

