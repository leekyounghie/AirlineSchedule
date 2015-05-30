package com.starnamu.projcet.airlineschedule.choiceoption;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.starnamu.projcet.airlineschedule.R;

/**
 * Created by starnamu on 2015-05-26.
 */
public class ChoiceOptionFragment extends Fragment {

    ListView choiceTime;
    ImageButton reStart;
    ImageButton OptionMenuOpen;
    int StartCurentTime;
    int NowCurentTime;
    OptionAdapter optionAdapter;
    boolean isMenuOpen = true;
    Animation translateLeftAnim;
    Animation translateRightAnim;
    LinearLayout OptionMenuOpenLayout;

    public static int TimeDifference;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        optionAdapter = new OptionAdapter(getActivity());
        StartCurentTime = (int) System.currentTimeMillis();

        translateLeftAnim = AnimationUtils.loadAnimation(getActivity(), R.anim.translate_left);
        translateRightAnim = AnimationUtils.loadAnimation(getActivity(), R.anim.translate_right);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.choiceoption, container, true);
        OptionMenuOpenLayout = (LinearLayout) view.findViewById(R.id.OptionMenuOpenLayout);

        choiceTime = (ListView) view.findViewById(R.id.choiceTime);
        choiceTime.setAdapter(optionAdapter);
        choiceTime.setOnItemClickListener(onItemClickListener);

        reStart = (ImageButton) view.findViewById(R.id.reExecution);
        reStart.setOnClickListener(resercheOnClickListener);

        OptionMenuOpen = (ImageButton) view.findViewById(R.id.OptionMenuOpen);
        OptionMenuOpenLayout.setVisibility(View.INVISIBLE);
        OptionMenuOpen.setOnClickListener(openMenuOnClickListener);
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

    OnItemClickListener onItemClickListener = new OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            TextView tv = (TextView) view.findViewById(R.id.textView);
            String str = tv.getText().toString();
            String string = str.replace(" : ", "");
            int number = Integer.parseInt(string);
            custonListOnClickListener.onListClicked(number);
        }
    };

    OnClickListener openMenuOnClickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            if (isMenuOpen) {
                OptionMenuOpenLayout.setVisibility(View.VISIBLE);
                OptionMenuOpenLayout.startAnimation(translateLeftAnim);
                isMenuOpen = false;
            } else {
                OptionMenuOpenLayout.startAnimation(translateRightAnim);
                OptionMenuOpenLayout.setVisibility(View.INVISIBLE);
                isMenuOpen = true;
            }
        }
    };

    // 버튼에 설정한 OnClickListener의 구현, 버튼이 클릭 될 때마다 Activity의 커스텀 리스너를 호출함
    OnClickListener resercheOnClickListener = new OnClickListener() {
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