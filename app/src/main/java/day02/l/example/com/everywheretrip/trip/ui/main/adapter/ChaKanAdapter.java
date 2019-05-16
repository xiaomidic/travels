package day02.l.example.com.everywheretrip.trip.ui.main.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

import day02.l.example.com.everywheretrip.R;
import day02.l.example.com.everywheretrip.trip.bean.WebBean;

public class ChaKanAdapter extends RecyclerView.Adapter<ChaKanAdapter.ViewHolder> {
    private Context context;
    private ArrayList<WebBean.ResultBean.BundlesBean> list;

    public ChaKanAdapter(Context context, ArrayList<WebBean.ResultBean.BundlesBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.layout_web, null);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

                 //加载一张圆角图片
                 RoundedCorners roundedCorners = new RoundedCorners(20);
                 RequestOptions options1 = RequestOptions.bitmapTransform(roundedCorners).override(140,100);
                 Glide.with(context).load(list.get(position).getCardURL()).apply(options1).into(holder.mImg);
                 holder.itemView.setOnClickListener(new View.OnClickListener() {
                     @Override
                     public void onClick(View v) {
                         if (onClicklistener!=null){
                             onClicklistener.OnClick(position);
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
        public ViewHolder(View itemView) {
            super(itemView);
            this.mImg = (ImageView) itemView.findViewById(R.id.img);
        }
    }
    private OnClicklistener onClicklistener;

    public void setOnClicklistener(OnClicklistener onClicklistener) {
        this.onClicklistener = onClicklistener;
    }

    public interface OnClicklistener{
        void OnClick(int position);
    }
}
