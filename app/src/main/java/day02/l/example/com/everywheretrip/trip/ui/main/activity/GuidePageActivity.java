package day02.l.example.com.everywheretrip.trip.ui.main.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;

import day02.l.example.com.everywheretrip.R;
import day02.l.example.com.everywheretrip.trip.ui.main.adapter.MyViewPage;
import day02.l.example.com.everywheretrip.trip.widget.PreviewIndicator;

public class GuidePageActivity extends AppCompatActivity implements View.OnClickListener {

    private ViewPager mVp;
    private ArrayList<View> list;
    private PreviewIndicator mIndicator;
    /**
     * 立即体验
     */
    private Button mBtnTiao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide_page);
        initView();
    }

    private void initView() {
        mIndicator = (PreviewIndicator) findViewById(R.id.indicator);
        mBtnTiao = (Button) findViewById(R.id.btn_tiao);
        mVp = (ViewPager) findViewById(R.id.vp);

     /*   mIndicator.setNumbers(3);
        mIndicator.initSize(80,32,6);*/
        list = new ArrayList<>();
        View view1 = LayoutInflater.from(this).inflate(R.layout.layout_view1, null);
        View view2 = LayoutInflater.from(this).inflate(R.layout.layout_view2, null);
        View view3 = LayoutInflater.from(this).inflate(R.layout.layout_view3, null);
        list.add(view1);
        list.add(view2);
        list.add(view3);
        MyViewPage page = new MyViewPage(this, list);
        mVp.setAdapter(page);



        mVp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mIndicator.setSelected(position);
                if (position == 2) {
                    mIndicator.setVisibility(View.GONE);
                    mBtnTiao.setVisibility(View.VISIBLE);
                } else {
                    mIndicator.setVisibility(View.VISIBLE);
                    mBtnTiao.setVisibility(View.GONE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


        mBtnTiao.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_tiao:
                startActivity(new Intent(this,LoginActivity.class));
                break;
        }
    }
}
