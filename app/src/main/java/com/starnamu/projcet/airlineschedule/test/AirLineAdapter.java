package com.starnamu.projcet.airlineschedule.test;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

/**
 * Created by starnamu on 2015-05-08.
 */
public class AirLineAdapter extends BaseAdapter {

    ArrayList<AirlineItem> ItemList = new ArrayList<AirlineItem>();
    Context mContext;

    public AirLineAdapter(Context context) {
        this.mContext = context;
    }

    public void setItemList(ArrayList<AirlineItem> itemList) {

        this.ItemList = itemList;
    }

    @Override
    public int getCount() {
        return ItemList.size();
    }

    @Override
    public Object getItem(int position) {
        return ItemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        AirlineItemView view = null;
        if (convertView == null) {
            view = new AirlineItemView(mContext);
        } else {
            view = (AirlineItemView) convertView;
        }
        view.setAirlineItem(ItemList.get(position));
        return view;
    }
}
