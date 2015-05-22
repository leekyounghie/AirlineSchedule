package com.starnamu.projcet.airlineschedule.test;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.starnamu.projcet.airlineschedule.R;

/**
 * Created by starnamu on 2015-05-23.
 */
public class CoustomToast extends LinearLayout {

    Context mContext;

    public CoustomToast(Context context) {
        super(context);
        this.mContext = context;
        init(context);
    }

    public CoustomToast(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        init(context);
    }

    public void init(Context mContext) {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.toastborder, (ViewGroup) findViewById(R.id.toast_layout_root));

        TextView text = (TextView) findViewById(R.id.text);
        Toast toast = new Toast(mContext);
        text.setText("Costom Toast");
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(view);
        toast.show();
    }
}
