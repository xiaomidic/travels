package day02.l.example.com.everywheretrip.trip.ui.main.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

import day02.l.example.com.everywheretrip.R;
import day02.l.example.com.everywheretrip.trip.bean.CircuitBean;

public class MyCircuitAdapter extends RecyclerView.Adapter<MyCircuitAdapter.ViewHoder> {
    private Context context;
    private ArrayList<CircuitBean.ResultBean.RoutesBean> list;

    public MyCircuitAdapter(Context context, ArrayList<CircuitBean.ResultBean.RoutesBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.layout_item, null);
        return new ViewHoder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHoder holder, int position) {
        holder.mGinza.setText(list.get(position).getTitle());
        holder.mShop.setText(list.get(position).getIntro());
        holder.mJd.setText(list.get(position).getCity());
        holder.mTvPrice.setText(list.get(position).getPriceInCents()+"人购买");

        //加载一张圆角图片
        RoundedCorners roundedCorners = new RoundedCorners(10);
        RequestOptions options1 = RequestOptions.bitmapTransform(roundedCorners).override(140,100);
        Glide.with(context).load(list.get(position).getCardURL()).apply(options1).into(holder.mImg);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHoder extends RecyclerView.ViewHolder {
        ImageView mImg;
        TextView mGinza;
        TextView mShop;
        ImageView mImgLocation;
        TextView mJd;
        Button mBtnPrice;
        TextView mTvPrice;

        public ViewHoder(View itemView) {
            super(itemView);
            this.mImg = (ImageView) itemView.findViewById(R.id.img);
            this.mGinza = (TextView) itemView.findViewById(R.id.ginza);
            this.mShop = (TextView) itemView.findViewById(R.id.shop);
            this.mImgLocation = (ImageView) itemView.findViewById(R.id.img_location);
            this.mJd = (TextView) itemView.findViewById(R.id.jd);
            this.mBtnPrice = (Button) itemView.findViewById(R.id.btn_price);
            this.mTvPrice = (TextView) itemView.findViewById(R.id.tv_price);
        }
    }


}
