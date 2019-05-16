package day02.l.example.com.everywheretrip.trip.ui.main.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import day02.l.example.com.everywheretrip.R;
import day02.l.example.com.everywheretrip.trip.bean.CollectionBean;

public class MyCollectionAdapter extends RecyclerView.Adapter<MyCollectionAdapter.ViewHolder> {
    private Context context;
    private ArrayList<CollectionBean.ResultBean.CollectedRoutesBean> list;

    public MyCollectionAdapter(Context context, ArrayList<CollectionBean.ResultBean.CollectedRoutesBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.collection_one, null);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.mTv1.setText(list.get(position).getTitle());
        holder.mTv2.setText(list.get(position).getIntro());
        Glide.with(context).load(list.get(position).getCardURL()).into(holder.mImg);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView mImg;
        TextView mTv1;
        TextView mTv2;
        public ViewHolder(View itemView) {
            super(itemView);
            this.mImg = (ImageView) itemView.findViewById(R.id.img);
            this.mTv1 = (TextView) itemView.findViewById(R.id.tv1);
            this.mTv2 = (TextView) itemView.findViewById(R.id.tv2);
        }
    }


}
