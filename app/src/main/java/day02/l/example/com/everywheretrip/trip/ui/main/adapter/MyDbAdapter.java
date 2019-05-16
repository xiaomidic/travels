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
import java.util.List;

import day02.l.example.com.everywheretrip.R;
import day02.l.example.com.everywheretrip.trip.bean.BanMiBean;
import day02.l.example.com.everywheretrip.trip.bean.DbBean;

public class MyDbAdapter extends RecyclerView.Adapter<MyDbAdapter.ViewHolder> {
    private Context context;
    private List<DbBean> list;

    public MyDbAdapter(Context context, List<DbBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.layout_banmi, null);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        final DbBean dbBean = list.get(position);
        holder.mTvName.setText(dbBean.getName());
        //holder.mTvGuanzhu.setText(banmiBean.getFollowing()+"人关注");
        holder.mTvWeizhi.setText(dbBean.getLocation());
        holder.mTvDiqu.setText(dbBean.getOccupation());
        Glide.with(context).load(dbBean.getPhoto()).into(holder.mImg);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onClicklistener!=null){
                    onClicklistener.OnClick(position,dbBean);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView mImg;
        TextView mTvName;
        ImageView mImgAixin;
        TextView mTvGuanzhu;
        ImageView mImgWeizhi;
        TextView mTvWeizhi;
        TextView mTvDiqu;
        public ViewHolder(View itemView) {
            super(itemView);
            this.mImg = (ImageView) itemView.findViewById(R.id.img);
            this.mTvName = (TextView) itemView.findViewById(R.id.tv_name);
            this.mImgAixin = (ImageView) itemView.findViewById(R.id.img_aixin);
            this.mTvGuanzhu = (TextView) itemView.findViewById(R.id.tv_guanzhu);
            this.mImgWeizhi = (ImageView) itemView.findViewById(R.id.img_weizhi);
            this.mTvWeizhi = (TextView) itemView.findViewById(R.id.tv_weizhi);
            this.mTvDiqu = (TextView) itemView.findViewById(R.id.tv_diqu);
        }
    }
    private OnClicklistener onClicklistener;

    public void setOnClicklistener(OnClicklistener onClicklistener) {
        this.onClicklistener = onClicklistener;
    }

    public interface  OnClicklistener{
        void OnClick(int position, DbBean dbBean);
    }


}
