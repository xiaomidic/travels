package day02.l.example.com.everywheretrip.trip.ui.main.fragment;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import day02.l.example.com.everywheretrip.R;
import day02.l.example.com.everywheretrip.trip.base.BaseFragment;
import day02.l.example.com.everywheretrip.trip.bean.CircuitBean;
import day02.l.example.com.everywheretrip.trip.presenter.CircuitPresenter;
import day02.l.example.com.everywheretrip.trip.ui.main.adapter.MyCircuitAdapter;
import day02.l.example.com.everywheretrip.trip.view.main.CircuitView;

/**
 * A simple {@link Fragment} subclass.
 */
public class CircuitFragment extends BaseFragment<CircuitView, CircuitPresenter> implements CircuitView {


    @BindView(R.id.lv)
    RecyclerView mLv;
     View view;
    private ArrayList<CircuitBean.ResultBean.RoutesBean> routesBeans;
    private MyCircuitAdapter adapter;

    public CircuitFragment() {
        // Required empty public constructor
    }

    private int id;

    @SuppressLint("ValidFragment")
    public CircuitFragment(int id) {
        this.id = id;
    }



    @Override
    protected CircuitPresenter initPresenter() {
        return new CircuitPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_circuit;
    }

    @Override
    protected void initView() {
        routesBeans = new ArrayList<>();
        mLv.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new MyCircuitAdapter(getActivity(),routesBeans);
        mLv.setAdapter(adapter);

    }

    @Override
    protected void initData() {
        mPresenter.getCircuit(id);
    }

    @Override
    public void setData(CircuitBean bean) {
        routesBeans.addAll(bean.getResult().getRoutes());
        adapter.notifyDataSetChanged();

    }

    @OnClick(R.id.lv)
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.lv:
                break;
        }
    }


}
