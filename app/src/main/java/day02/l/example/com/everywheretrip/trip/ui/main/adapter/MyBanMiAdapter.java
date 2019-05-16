package day02.l.example.com.everywheretrip.trip.ui.main.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import day02.l.example.com.everywheretrip.R;
import day02.l.example.com.everywheretrip.trip.bean.BanMiBean;
import day02.l.example.com.everywheretrip.trip.bean.DbBean;
import day02.l.example.com.everywheretrip.trip.util.DbUtils;

public class MyBanMiAdapter extends RecyclerView.Adapter<MyBanMiAdapter.ViewHolder> {
    private Context context;
    private ArrayList<BanMiBean.ResultBean.BanmiBean> list;
    private boolean isShowOrNot = false;
    public MyBanMiAdapter(Context context, ArrayList<BanMiBean.ResultBean.BanmiBean> list) {
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
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        final BanMiBean.ResultBean.BanmiBean banmiBean = list.get(position);
        holder.mTvName.setText(banmiBean.getName());
        //holder.mTvGuanzhu.setText(banmiBean.getFollowing()+"人关注");
        holder.mTvWeizhi.setText(banmiBean.getLocation());
        holder.mTvDiqu.setText(banmiBean.getOccupation());
        Glide.with(context).load(banmiBean.getPhoto()).into(holder.mImg);


        holder.mImgAixin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isShowOrNot==false){
                    holder.mImgAixin.setImageResource(R.mipmap.follow);
                    isShowOrNot = true;
                    BanMiBean.ResultBean.BanmiBean bean = list.get(position);
                    DbBean dbBean = new DbBean();
                    dbBean.setId(null);
                    dbBean.setLocation(bean.getLocation());
                    dbBean.setName(bean.getName());
                    dbBean.setOccupation(bean.getOccupation());
                    dbBean.setPhoto(bean.getPhoto());
                    DbUtils.getDbUtils().insert(dbBean);
                    Toast.makeText(context, "收藏成功", Toast.LENGTH_SHORT).show();
                }else {
                    holder.mImgAixin.setImageResource(R.mipmap.follow_unselected);
                    isShowOrNot = false;
                    Toast.makeText(context, "取消收藏", Toast.LENGTH_SHORT).show();

                }

            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onClicklistener!=null){
                    onClicklistener.OnClick(position,banmiBean);
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
        void OnClick(int position, BanMiBean.ResultBean.BanmiBean banmiBean);
    }


}
