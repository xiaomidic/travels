package day02.l.example.com.everywheretrip.trip.ui.main.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import day02.l.example.com.everywheretrip.R;
import day02.l.example.com.everywheretrip.trip.bean.DbBean;
import day02.l.example.com.everywheretrip.trip.ui.main.adapter.MyDbAdapter;
import day02.l.example.com.everywheretrip.trip.util.DbUtils;

public class ColltionActivity extends AppCompatActivity {

    private RecyclerView mLv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colltion);
        initView();
    }

    private void initView() {
        mLv = (RecyclerView) findViewById(R.id.lv);
        final List<DbBean> list = DbUtils.getDbUtils().query();
        final MyDbAdapter adapter = new MyDbAdapter(this, list);
        mLv.setAdapter(adapter);
        mLv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        adapter.setOnClicklistener(new MyDbAdapter.OnClicklistener() {
            @Override
            public void OnClick(int position, DbBean dbBean) {

                DbUtils.getDbUtils().delete(dbBean);
                list.remove(position);
                adapter.notifyDataSetChanged();

            }
        });
    }
}
