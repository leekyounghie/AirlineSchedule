package com.starnamu.projcet.airlineschedule.choiceoption;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.starnamu.projcet.airlineschedule.R;

import java.util.ArrayList;

/**
 * Created by starnamu on 2015-05-26.
 */
public class OptionAdapter extends BaseAdapter /*implements View.OnClickListener */ {
    Context mContext;
    TextView textView;
    int number;


    ArrayList<OptionItemClass> items;

    public OptionAdapter(Context mContext) {
        super();
        this.mContext = mContext;
        items = new ArrayList<OptionItemClass>();
        addItem();
    }

    public void addItem() {
        for (int i = 0; i < 25; i++) {
            for (int j = 0; j < 6; j++) {
                String str = String.format("%04d", i * 100 + j * 10);
                items.add(new OptionItemClass(str));

//                items.add(new OptionItemClass(stringFormConversion(str)));
            }
        }
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.timeoptionstringtextview, parent, false);

        textView = (TextView) view.findViewById(R.id.textView);
        textView.setText(stringFormConversion(items.get(position).getOptionItem()));

/*
        textView.setOnClickListener(this);
*/

        return view;
    }

  /*  @Override
    public void onClick(View v) {
        TextView textVView = (TextView) v;
        String str = (String) textVView.getText();

        String string = str.replace(" : ", "");
        number = Integer.parseInt(string);

        Log.i("item Click", Integer.toString(number));

    }*/

    private String stringFormConversion(String str) {
        String Hour = str.substring(0, 2);
        String Minute = str.substring(2, 4);
        String timeChange = Hour + " : " + Minute;
        return timeChange;
    }
}
