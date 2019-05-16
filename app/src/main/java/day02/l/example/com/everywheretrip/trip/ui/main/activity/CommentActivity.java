package day02.l.example.com.everywheretrip.trip.ui.main.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import day02.l.example.com.everywheretrip.R;
import day02.l.example.com.everywheretrip.trip.base.BaseActivity;
import day02.l.example.com.everywheretrip.trip.bean.CommentBean;
import day02.l.example.com.everywheretrip.trip.presenter.CommentPresenter;
import day02.l.example.com.everywheretrip.trip.view.main.CommentView;

public class CommentActivity extends BaseActivity<CommentView, CommentPresenter> implements CommentView {
    @BindView(R.id.img)
    ImageView mImg;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.lv)
    RecyclerView mLv;
    @BindView(R.id.smart)
    SmartRefreshLayout mSmart;
    private int page;
    private int num;
    private ArrayList<CommentBean.ResultBean.ReviewsBean> list;
    private MyCommentAdapter adapter;


    @Override
    protected CommentPresenter initPresenter() {
        return new CommentPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_comment;
    }

    @Override
    protected void initView() {
        page = getIntent().getIntExtra("id", 0);
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
        list = new ArrayList<>();
        adapter = new MyCommentAdapter(this,list);
        mLv.setAdapter(adapter);
        mLv.setLayoutManager(new LinearLayoutManager(this));
        mSmart.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                num=1;
                list.clear();
                initData();
                mSmart.finishLoadMore();

            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                num++;
                initData();
                mSmart.finishRefresh();

            }
        });
    }

    @Override
    protected void initData() {
        mPresenter.getComment(page,num);
    }

    @Override
    public void setData(CommentBean bean) {
      list.addAll(bean.getResult().getReviews());
      adapter.notifyDataSetChanged();
    }

    @OnClick({R.id.img, R.id.toolbar, R.id.lv})
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.img:
                finish();
                break;

        }
    }
}
