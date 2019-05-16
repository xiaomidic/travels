package day02.l.example.com.everywheretrip.trip.ui.main.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;

import day02.l.example.com.everywheretrip.R;
import day02.l.example.com.everywheretrip.trip.bean.MianBean;
import day02.l.example.com.everywheretrip.trip.ui.main.activity.WebActivity;
import day02.l.example.com.everywheretrip.trip.ui.main.adapter.MyAdapter;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {


    private View view;
    private RecyclerView mLv;
    private ArrayList<MianBean.ResultBean.BannersBean> resultBeans;
    private ArrayList<MianBean.ResultBean.RoutesBean> routesBeans = new ArrayList<>();
    private MyAdapter adapter;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_home, container, false);
        initView(inflate);
        initData();
        return inflate;
    }

    private void initData() {
        OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder()
                        .get()
                        .url("http://api.banmi.com/api/3.0/content/routesbundles?page=1")
                        .addHeader("banmi-app-token","o033z8g7ALbaQW0tbYexFFzENXhajFeLGqhONbqG3kfXzjiHaNWjjAdHg2PI4EUvMgwz33DGdaNilZOj4wj6WOt4mMaf01U2bSHu92GJpMXeusRgYCIzGFPfdwd83scBA")
                        .build();
                Call call = client.newCall(request);
                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        final Gson gson = new Gson();
                        final MianBean mianBean = gson.fromJson(response.body().string(), MianBean.class);
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                MianBean.ResultBean result = mianBean.getResult();
                                Log.d("tag", "run: "+result.toString());
                                if (result.getBanners() != null) {

                                    resultBeans.addAll(result.getBanners());
                                }
                                if (result.getRoutes() != null) {

                                    routesBeans.addAll(result.getRoutes());
                                }
                                adapter.notifyDataSetChanged();
                            }
                        });
                    }
                });
    }

    private void initView(View inflate) {
        mLv = (RecyclerView) inflate.findViewById(R.id.lv);
        resultBeans = new ArrayList<>();
        adapter = new MyAdapter(getActivity(), resultBeans,routesBeans);
        mLv.setAdapter(adapter);
        mLv.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter.setOnClicklistener(new MyAdapter.OnClicklistener() {
            @Override
            public void OnClick(int position, MianBean.ResultBean.RoutesBean routesBean) {
                Intent intent = new Intent(getActivity(), WebActivity.class);
                intent.putExtra("page",routesBeans.get(position).getId());
                getActivity().startActivity(intent);

            }
        });
    }
}
