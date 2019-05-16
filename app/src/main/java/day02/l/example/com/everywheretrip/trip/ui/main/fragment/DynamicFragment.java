package day02.l.example.com.everywheretrip.trip.ui.main.fragment;


import android.annotation.SuppressLint;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.Unbinder;
import day02.l.example.com.everywheretrip.R;
import day02.l.example.com.everywheretrip.trip.base.BaseFragment;
import day02.l.example.com.everywheretrip.trip.bean.DynamicBean;
import day02.l.example.com.everywheretrip.trip.presenter.DynamicPresenter;
import day02.l.example.com.everywheretrip.trip.ui.main.adapter.MyDyanmic;
import day02.l.example.com.everywheretrip.trip.view.main.DynamicView;

/**
 * A simple {@link Fragment} subclass.
 */
public class DynamicFragment extends BaseFragment<DynamicView,DynamicPresenter> implements DynamicView {


    @BindView(R.id.lv)
    RecyclerView mLv;
    private View view;
    private Unbinder unbinder;
    private ArrayList<DynamicBean.ResultBean.ActivitiesBean> list;
    private MyDyanmic adapter;

    public DynamicFragment() {
        // Required empty public constructor
    }

    private int id;

    @SuppressLint("ValidFragment")
    public DynamicFragment(int id) {
        this.id = id;
    }

   /* @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_dynamic, container, false);
        unbinder = ButterKnife.bind(this, inflate);

        return inflate;
    }*/

    @Override
    protected DynamicPresenter initPresenter() {
        return new DynamicPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_dynamic;
    }

    @Override
    protected void initView() {
        list = new ArrayList<>();
        adapter = new MyDyanmic(getActivity(), list);
        mLv.setLayoutManager(new LinearLayoutManager(getActivity()));
        mLv.setAdapter(adapter);

    }

    @Override
    protected void initData() {
        mPresenter.setDynamic(id);
    }

    @Override
    public void setData(DynamicBean bean) {
        list.addAll(bean.getResult().getActivities());
        adapter.notifyDataSetChanged();
    }
}
