package com.starnamu.projcet.airlineschedule.choiceoption;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.starnamu.projcet.airlineschedule.R;

/**
 * Created by starnamu on 2015-05-26.
 */
public class ChoiceOptionFragment extends Fragment {

    ListView choiceTime;
    Button reExecution;
    int StartCurentTime;
    int NowCurentTime;
    OptionAdapter optionAdapter;

    public static int TimeDifference;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        optionAdapter = new OptionAdapter(getActivity());
        StartCurentTime = (int) System.currentTimeMillis();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.choiceoption, container, true);
        choiceTime = (ListView) view.findViewById(R.id.choiceTime);
        choiceTime.setAdapter(optionAdapter);
        choiceTime.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                TextView tv = (TextView) view.findViewById(R.id.textView);
                String str = tv.getText().toString();
                Log.i("크릭한 view는", str);

                String string = str.replace(" : ", "");
                int number = Integer.parseInt(string);
                custonListOnClickListener.onListClicked(number);
            }
        });
        reExecution = (Button) view.findViewById(R.id.reExecution);

        reExecution.setOnClickListener(onClickListener);
        return view;
    }

    // Activity 로 데이터를 전달할 커스텀 리스너
    private CustomOnClickListener customListener;
    private CustonListOnClickListener custonListOnClickListener;

    // Activity 로 데이터를 전달할 커스텀 리스너의 인터페이스
    public interface CustomOnClickListener {
        public void onClicked();
    }

    public interface CustonListOnClickListener {
        public void onListClicked(int number);
    }

    // 버튼에 설정한 OnClickListener의 구현, 버튼이 클릭 될 때마다 Activity의 커스텀 리스너를 호출함
    OnClickListener onClickListener = new OnClickListener() {

        @Override
        public void onClick(View v) {

            NowCurentTime = (int) System.currentTimeMillis();
            TimeDifference = NowCurentTime - StartCurentTime;
            String TimeDifferenceStr = Integer.toString(100 - (TimeDifference / 1000));
            if (TimeDifference >= 100000) {
                StartCurentTime = NowCurentTime;
                customListener.onClicked();
            } else {
                Toast.makeText(getActivity(), (TimeDifferenceStr) + "초후 다시 누르세요", Toast.LENGTH_SHORT).show();
            }
        }
    };

    // Activity 로 데이터를 전달할 커스텀 리스너를 연결
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        customListener = (CustomOnClickListener) activity;
        custonListOnClickListener = (CustonListOnClickListener) activity;
    }
}
