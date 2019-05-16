package day02.l.example.com.everywheretrip.trip.ui.main.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;

import day02.l.example.com.everywheretrip.R;
import day02.l.example.com.everywheretrip.trip.bean.MianBean;
import day02.l.example.com.everywheretrip.trip.ui.main.activity.SpecialActivity;

public class MyAdapter extends RecyclerView.Adapter {
    Context context;
    private final ArrayList<MianBean.ResultBean.BannersBean> bannersBeans;
    private final ArrayList<MianBean.ResultBean.RoutesBean> routesBeans;
    private int mposition;

    public MyAdapter(Context context, ArrayList<MianBean.ResultBean.BannersBean> bannersBeans, ArrayList<MianBean.ResultBean.RoutesBean> routesBeans) {
        this.context = context;

        this.bannersBeans = bannersBeans;
        this.routesBeans = routesBeans;
    }



    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        if (viewType == 0) {
            View inflate = LayoutInflater.from(context).inflate(R.layout.layout_banner, null);
            viewHolder = new BannerViewHolder(inflate);

        } else if (viewType == 1) {
            View inflate = LayoutInflater.from(context).inflate(R.layout.layout_img, null);
            viewHolder = new ImageViewHolder(inflate);
        } else /*if (viewType==2)*/{
            View view = LayoutInflater.from(context).inflate(R.layout.layout_item, null);
            viewHolder = new ItemViewHolder(view);
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof BannerViewHolder) {
            BannerViewHolder bannerViewHolder = (BannerViewHolder) holder;
            bannerViewHolder.mBanner.setImages(bannersBeans);
            bannerViewHolder.mBanner.setImageLoader(new ImageLoader() {
                @Override
                public void displayImage(Context context, Object path, ImageView imageView) {
                    MianBean.ResultBean.BannersBean bannersBean = (MianBean.ResultBean.BannersBean) path;
                    Glide.with(context).load(bannersBean.getImageURL()).into(imageView);
                }
            });
            bannerViewHolder.mBanner.start();
        } else if (holder instanceof ImageViewHolder) {
            ImageViewHolder imageViewHolder = (ImageViewHolder) holder;
            mposition = position;
            if (routesBeans.size()>0){
                mposition =position-1;
            }

            //加载一张圆角图片
            RoundedCorners roundedCorners = new RoundedCorners(25);
            final RequestOptions options = RequestOptions.bitmapTransform(roundedCorners).override(355,120);
            Glide.with(context).load(routesBeans.get(mposition).getCardURL()).apply(options).into(imageViewHolder.mImg);

            imageViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, SpecialActivity.class);
                    intent.putExtra("url",routesBeans.get(mposition).getContentURL());
                    intent.putExtra("title",routesBeans.get(mposition).getTitle());
                    context.startActivity(intent);
                }
            });

        } else if (holder instanceof ItemViewHolder) {
            ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
            int mposition = position;
            if (routesBeans.size()>0){
                mposition=position-1;
            }
            final MianBean.ResultBean.RoutesBean routesBean = routesBeans.get(mposition);

            itemViewHolder.mGinza.setText(routesBean.getTitle());
            itemViewHolder.mJd.setText(routesBean.getCity());
            itemViewHolder.mShop.setText(routesBean.getIntro());
            itemViewHolder.mBtnPrice.setText(routesBean.getPriceInCents() + "");
            itemViewHolder.mTvPrice.setText(routesBean.getPurchasedTimes() + "");

            //加载一张圆角图片
            RoundedCorners roundedCorners = new RoundedCorners(30);
            RequestOptions options1 = RequestOptions.bitmapTransform(roundedCorners).override(375, 200);
            Glide.with(context).load(routesBeans.get(mposition).getCardURL()).apply(options1).into(itemViewHolder.mImg);

            itemViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onClicklistener != null) {
                        onClicklistener.OnClick(position-1 , routesBean);
                    }
                }
            });

        }


    }

    @Override
    public int getItemCount() {

        return routesBeans.size();

    }
    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return 0;
        } else if (routesBeans.get(position-1).getType().equals("bundle")) {
            return 1;
        } else {
            return 2;
        }

    }

    class BannerViewHolder extends RecyclerView.ViewHolder {
        Banner mBanner;

        public BannerViewHolder(View itemView) {
            super(itemView);
            this.mBanner = (Banner) itemView.findViewById(R.id.banner);
        }
    }

    class ImageViewHolder extends RecyclerView.ViewHolder {

        ImageView mImg;
        public ImageViewHolder(View itemView) {
            super(itemView);
            this.mImg = (ImageView) itemView.findViewById(R.id.img);
        }
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {
        ImageView mImg;
        TextView mGinza;
        TextView mShop;
        ImageView mImgLocation;
        TextView mJd;
        Button mBtnPrice;
        TextView mTvPrice;

        public ItemViewHolder(View itemView) {
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

    private OnClicklistener onClicklistener;

    public void setOnClicklistener(OnClicklistener onClicklistener) {
        this.onClicklistener = onClicklistener;
    }

    public interface OnClicklistener {
        void OnClick(int position, MianBean.ResultBean.RoutesBean routesBean);
    }


}