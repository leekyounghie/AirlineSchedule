package com.example.starnamu.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;

/*
 * File name : IntroActivity.java
 * Written by Jin with bucketplace.
 *  - Application intro page
 *  - This application is firstly started in this page.)
 */
public class MainActivity extends ActionBarActivity {

    // intro 화면에서 사용할 핸들러를 인스턴트를 생성한다.
    Handler handler_intro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 핸들러를 실질적으로 정의하고, 핸들러를 시작하는 지연시간을 설정한다.
        handler_intro = new Handler();
        handler_intro.postDelayed(run_intro, 2000);
    }

    /*
     *  run_intro : intro 핸들러에서 하는 일을 정의한다.
     *  현재 페이지(IntroActivity)를 종료하면서 다음 페이지(MainActivity)를 실행한다.
     *  복잡하지 않으므로, 내부 선언으로 만족시켰다.
     */
    Runnable run_intro = new Runnable() {
        public void run() {
            Intent i = new Intent(MainActivity.this, MainActivity.class);
            startActivity(i);
            finish();
            // 화면이 Fade in-out효과와 함께 작동하도로 등록해준다.
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        }
    };

    /*
     *  onBackPressed function override
     *   - intro 화면이 나오는 중에는 back 버튼을 사용하지 못하게 한다.
     *   - 핸들러에 의해서 잘못된 결과를 나타낼 수 있다.
     */
    @Override
    public void onBackPressed() {

    }
}