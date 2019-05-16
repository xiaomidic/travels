package day02.l.example.com.everywheretrip.trip.ui.main.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bm.library.PhotoView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;

import day02.l.example.com.everywheretrip.R;
import day02.l.example.com.everywheretrip.trip.bean.DynamicBean;
import day02.l.example.com.everywheretrip.trip.bean.MianBean;
import day02.l.example.com.everywheretrip.trip.ui.main.activity.SpecialActivity;

public class MyDyanmic extends RecyclerView.Adapter {
    Context context;
    private ArrayList<DynamicBean.ResultBean.ActivitiesBean> list;

    public MyDyanmic(Context context, ArrayList<DynamicBean.ResultBean.ActivitiesBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        if (viewType == 0) {
            View inflate = LayoutInflater.from(context).inflate(R.layout.layout_voice, null);
            viewHolder = new MyVH(inflate);
        } else {
            View view = LayoutInflater.from(context).inflate(R.layout.layout_issue, null);
            viewHolder = new MusicVH(view);
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        int itemViewType = holder.getItemViewType();
        if (itemViewType==0){
            MyVH myVH = (MyVH) holder;
            myVH.mQq.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MediaPlayer mediaPlayer = MediaPlayer.create(context, Uri.parse(list.get(position).getAudioURL()));
                    mediaPlayer.start();
                }
            });

        }else {
            MusicVH musicVH = (MusicVH) holder;
            musicVH.mAaa2.setText(list.get(position).getContent());
            if (list.get(position).getImages().size()>0){
                Glide.with(context).load(list.get(position).getImages().get(0)).into(musicVH.mBigImg2);
            }
            musicVH.mBigImg2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    pop(position);
                }
            });

        }


    }

    private void pop(int position) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.layout_pop, null);
        PhotoView photoView = inflate.findViewById(R.id.pv);
        photoView.enable();
        Glide.with(context).load(list.get(position).getImages().get(0)).into(photoView);
        final PopupWindow pop = new PopupWindow(inflate, GridView.LayoutParams.MATCH_PARENT, GridView.LayoutParams.MATCH_PARENT);

        pop.setOutsideTouchable(true);
        pop.setBackgroundDrawable(new ColorDrawable(0x70000000));
        pop.showAtLocation(inflate, Gravity.CENTER,0,0);
        photoView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pop.dismiss();
            }
        });
    }

    @Override
    public int getItemCount() {

        return list.size();

    }

    @Override
    public int getItemViewType(int position) {
        if (list.get(position).getAudioURL().length() > 0) {
            return 0;
        } else {
            return 1;
        }

    }

    class MyVH extends RecyclerView.ViewHolder {
        View mDian2;
        View mXian2;
        TextView mNeww;
        ImageView mQq;
        TextView mSix3;
        TextView mSeven;
        public MyVH(View itemView) {
            super(itemView);
            this.mDian2 = (View) itemView.findViewById(R.id.dian2);
            this.mXian2 = (View) itemView.findViewById(R.id.xian2);
            this.mNeww = (TextView) itemView.findViewById(R.id.neww);
            this.mQq = (ImageView) itemView.findViewById(R.id.qq);
            this.mSix3 = (TextView) itemView.findViewById(R.id.six3);
            this.mSeven = (TextView) itemView.findViewById(R.id.seven);
        }
    }

    class MusicVH extends RecyclerView.ViewHolder {
        View mDian2;
        TextView mFive;
        View mXian2;
        TextView mDynamic;
        TextView mTitlt2;
        TextView mAaa2;
        ImageView mBigImg2;
        TextView mSix2;
        TextView mTwo2;
        CardView mCv2;
        RelativeLayout mRl2;
        RelativeLayout mRl3;

        public MusicVH(View itemView) {
            super(itemView);
            this.mDian2 = (View) itemView.findViewById(R.id.dian2);
            this.mFive = (TextView) itemView.findViewById(R.id.five);
            this.mXian2 = (View) itemView.findViewById(R.id.xian2);
            this.mDynamic = (TextView) itemView.findViewById(R.id.dynamic);
            this.mTitlt2 = (TextView) itemView.findViewById(R.id.titlt2);
            this.mAaa2 = (TextView) itemView.findViewById(R.id.aaa2);
            this.mBigImg2 = (ImageView) itemView.findViewById(R.id.big_img2);
            this.mSix2 = (TextView) itemView.findViewById(R.id.six2);
            this.mTwo2 = (TextView) itemView.findViewById(R.id.two2);
            this.mCv2 = (CardView) itemView.findViewById(R.id.cv2);
            this.mRl2 = (RelativeLayout) itemView.findViewById(R.id.rl2);
            this.mRl3 = (RelativeLayout) itemView.findViewById(R.id.rl3);
        }
    }


    private OnClicklistener onClicklistener;

    public void setOnClicklistener(OnClicklistener onClicklistener) {
        this.onClicklistener = onClicklistener;
    }

    public interface OnClicklistener {
        void OnClick(int position, MianBean.ResultBean.RoutesBean routesBean);
    }



}
