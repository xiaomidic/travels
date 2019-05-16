package day02.l.example.com.everywheretrip.trip.ui.main.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import day02.l.example.com.everywheretrip.R;
import day02.l.example.com.everywheretrip.trip.bean.UpdateBean;

public class MyUpdateAdapter extends RecyclerView.Adapter<MyUpdateAdapter.ViewHolder> {
    private Context context;
    private ArrayList<UpdateBean.ResultBean> list;

    public MyUpdateAdapter(Context context, ArrayList<UpdateBean.ResultBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.update_item, null);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.mTv1.setText(list.get(position).getUserName());
        holder.mTv2.setText(list.get(position).getGender());
    }

    @Override
    public int getItemCount() {
        return list.size() > 0 ? list.size() : 0;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView mTv1;
        TextView mTv2;
        TextView mTv3;
        public ViewHolder(View itemView) {
            super(itemView);
            this.mTv1 = (TextView) itemView.findViewById(R.id.tv1);
            this.mTv2 = (TextView) itemView.findViewById(R.id.tv2);
            this.mTv3 = (TextView) itemView.findViewById(R.id.tv3);
        }
    }


}
