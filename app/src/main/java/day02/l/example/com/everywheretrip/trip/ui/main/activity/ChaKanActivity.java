package day02.l.example.com.everywheretrip.trip.ui.main.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import day02.l.example.com.everywheretrip.R;
import day02.l.example.com.everywheretrip.trip.bean.WebBean;
import day02.l.example.com.everywheretrip.trip.net.ApiService;
import day02.l.example.com.everywheretrip.trip.net.BaseObserver;
import day02.l.example.com.everywheretrip.trip.net.HttpUtils;
import day02.l.example.com.everywheretrip.trip.net.RxUtils;
import day02.l.example.com.everywheretrip.trip.ui.main.adapter.ChaKanAdapter;
import io.reactivex.disposables.Disposable;

public class ChaKanActivity extends AppCompatActivity {

    private RecyclerView mLv;
    private ArrayList<WebBean.ResultBean.BundlesBean> list;
    private ChaKanAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cha_kan);
        initView();
        initData();
    }

    private void initData() {
        ApiService apiserver = HttpUtils.getInstance().getApiserver(ApiService.url, ApiService.class);
        apiserver.getWeb().compose(RxUtils.<WebBean>rxObserableSchedulerHelper()).subscribe(
                new BaseObserver<WebBean>() {
                    @Override
                    public void onNext(WebBean webBean) {
                        list.addAll(webBean.getResult().getBundles());
                        adapter.notifyDataSetChanged();


                    }

                    @Override
                    public void error(String msg) {

                    }

                    @Override
                    protected void subscribe(Disposable d) {

                    }
                }
        );
    }

    private void initView() {
        mLv = (RecyclerView) findViewById(R.id.lv);
        list = new ArrayList<>();
        adapter = new ChaKanAdapter(this,list);
        mLv.setLayoutManager(new LinearLayoutManager(this));
        mLv.setAdapter(adapter);

        adapter.setOnClicklistener(new ChaKanAdapter.OnClicklistener() {
            @Override
            public void OnClick(int position) {
                finish();
            }
        });
    }
}
