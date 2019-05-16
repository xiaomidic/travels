package day02.l.example.com.everywheretrip.trip.ui.main.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;
import day02.l.example.com.everywheretrip.R;
import day02.l.example.com.everywheretrip.trip.base.BaseActivity;
import day02.l.example.com.everywheretrip.trip.bean.CollectionBean;
import day02.l.example.com.everywheretrip.trip.presenter.CollectionPresenter;
import day02.l.example.com.everywheretrip.trip.ui.main.adapter.MyCollectionAdapter;
import day02.l.example.com.everywheretrip.trip.view.main.CollectionView;

public class CollectionActivity extends BaseActivity<CollectionView, CollectionPresenter> implements CollectionView {

    @BindView(R.id.tv)
    TextView mTv;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.lv)
    RecyclerView mLv;
    @BindView(R.id.img)
    ImageView mImg;
    private ArrayList<CollectionBean.ResultBean.CollectedRoutesBean> list;
    private MyCollectionAdapter adapter;

    /**
     * 我的收藏
     */


  /*  @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collection);
        ButterKnife.bind(this);
        initView();
    }*/
    @Override
    protected CollectionPresenter initPresenter() {
        return new CollectionPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_collection;
    }

    @Override
    protected void initView() {
        setSupportActionBar(mToolbar);
        list = new ArrayList<>();
        adapter = new MyCollectionAdapter(this, list);
        mLv.setLayoutManager(new LinearLayoutManager(this));
        mLv.setAdapter(adapter);
    }
    @Override
    protected void initData() {
        mPresenter.getCollection();
    }

    private static final String TAG = "CollectionActivity";
    @Override
    public void setData(CollectionBean collectionBean) {
        Log.d(TAG, "setData: "+collectionBean.toString());
        if (collectionBean!=null){

            list.addAll(collectionBean.getResult().getCollectedRoutes());
            adapter.notifyDataSetChanged();
        }
    }



    @OnClick({R.id.tv, R.id.toolbar, R.id.lv})
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.tv:
                break;
            case R.id.toolbar:
                break;
            case R.id.lv:

                break;
            case R.id.img:
                finish();
                break;
        }
    }


}
