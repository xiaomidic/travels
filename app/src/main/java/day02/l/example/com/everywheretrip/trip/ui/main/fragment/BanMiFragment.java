package day02.l.example.com.everywheretrip.trip.ui.main.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;

import day02.l.example.com.everywheretrip.R;
import day02.l.example.com.everywheretrip.trip.bean.BanMiBean;
import day02.l.example.com.everywheretrip.trip.bean.DbBean;
import day02.l.example.com.everywheretrip.trip.ui.main.activity.BanMiActivity;
import day02.l.example.com.everywheretrip.trip.ui.main.adapter.MyBanMiAdapter;
import day02.l.example.com.everywheretrip.trip.util.DbUtils;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class BanMiFragment extends Fragment {


    private View view;
    private RecyclerView mLv;
    private ArrayList<BanMiBean.ResultBean.BanmiBean> list;
    private MyBanMiAdapter adapter;


    public BanMiFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_ban_mi, container, false);
        initView(inflate);
        initData();
        return inflate;
    }

    private void initData() {
        OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder()
                        .get()
                        .url("http://api.banmi.com/api/3.0/banmi")
                        .addHeader("banmi-app-token","o033z8g7ALbaQW0tbYexFFzENXhajFeLGqhONbqG3kfXzjiHaNWjjAdHg2PI4EUvMgwz33DGdaNilZOj4wj6WOt4mMaf01U2bSHu92GJpMXeusRgYCIzGFPfdwd83scBA")
                        .build();
                Call call = client.newCall(request);
                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String string = response.body().string();
                        Gson gson = new Gson();
                        final BanMiBean banMiBean = gson.fromJson(string, BanMiBean.class);
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                list.addAll(banMiBean.getResult().getBanmi());
                                adapter.notifyDataSetChanged();
                            }
                        });
                    }
                });
    }

    private void initView(View inflate) {
        mLv = (RecyclerView) inflate.findViewById(R.id.lv);
        list = new ArrayList<>();
        adapter = new MyBanMiAdapter(getActivity(), list);
        mLv.setLayoutManager(new LinearLayoutManager(getActivity()));
        mLv.setAdapter(adapter);
        adapter.setOnClicklistener(new MyBanMiAdapter.OnClicklistener() {
            @Override
            public void OnClick(int position, BanMiBean.ResultBean.BanmiBean banmiBean) {
                Intent intent = new Intent(getActivity(), BanMiActivity.class);
                intent.putExtra("id",list.get(position).getId());
                getActivity().startActivity(intent);
            }
        });
    }
}
