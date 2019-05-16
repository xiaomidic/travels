package day02.l.example.com.everywheretrip.trip.ui.main.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.animation.AlphaAnimation;
import android.view.animation.AnimationSet;
import android.widget.RelativeLayout;

import java.util.Timer;
import java.util.TimerTask;

import day02.l.example.com.everywheretrip.R;
import day02.l.example.com.everywheretrip.trip.base.Constants;
import day02.l.example.com.everywheretrip.trip.util.SpUtil;

public class guidanceActivity extends AppCompatActivity {


    private RelativeLayout mLy;

    private void guide() {

        //是否是第一次登陆
        SharedPreferences sharedPreferences = this.getSharedPreferences("share", MODE_PRIVATE);
        boolean isFirstRun = sharedPreferences.getBoolean("isFirstRun", true);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        if (isFirstRun) {
            Log.e("debug", "第一次运行");
            editor.putBoolean("isFirstRun", false);
            editor.commit();
            Intent intent = new Intent();
            intent.setClass(guidanceActivity.this, GuidePageActivity.class);
            startActivity(intent);
        } else {
            Log.e("debug", "不是第一次运行");

            String token = (String) SpUtil.getParam(Constants.TOKEN, "");
            if (TextUtils.isEmpty(token)) {
                Intent intent = new Intent();
                intent.setClass(guidanceActivity.this, LoginActivity.class);
                startActivity(intent);
            } else {
                Intent intent = new Intent();
                intent.setClass(guidanceActivity.this, MainActivity.class);
                startActivity(intent);
            }
        }

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.AppTheme);

        SystemClock.sleep(2000);
        setContentView(R.layout.activity_guidance);
        initView();
        guide();
        finish();
    }

    private void startAnimation() {
       /* AnimationSet animationSet=new AnimationSet(false);

        AlphaAnimation alpha=new AlphaAnimation(0,1);
        alpha.setDuration(3000);
        alpha.setFillAfter(true);

//        animationSet.addAnimation(rotate);
        //  animationSet.addAnimation(scale);
        animationSet.addAnimation(alpha);

        mLy.startAnimation(animationSet);*/
    }
    private void initView() {
      /*  mLy = (RelativeLayout) findViewById(R.id.ly);
        startAnimation();
        Timer timer=new Timer();
        TimerTask task =new TimerTask() {
            @Override
            public void run() {

            }
        };
        timer.schedule(task,3000);*/
    }
}
