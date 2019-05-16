package day02.l.example.com.everywheretrip.trip.ui.main.activity;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

import day02.l.example.com.everywheretrip.R;
import day02.l.example.com.everywheretrip.trip.bean.CommentBean;

public class MyCommentAdapter extends RecyclerView.Adapter<MyCommentAdapter.ViewHolder> {
    private Context context;
    private ArrayList<CommentBean.ResultBean.ReviewsBean> list;

    public MyCommentAdapter(Context context, ArrayList<CommentBean.ResultBean.ReviewsBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.comment_item, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.mTvName.setText(list.get(position).getUserName());
        holder.mTvDate.setText(list.get(position).getCreatedAt());
        holder.mTvMessage.setText(list.get(position).getContent());
        RequestOptions options = new RequestOptions().circleCrop();
        Glide.with(context).load(list.get(position).getUserPhoto()).apply(options).into(holder.mImg);
    }

    @Override
    public int getItemCount() {
        return list.size() > 0 ? list.size():0;
    }



    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView mImg;
        TextView mTvName;
        TextView mTvDate;
        ImageView mImgComment;
        ImageView mImgPraise;
        TextView mTvMessage;
        public ViewHolder(View itemView) {
            super(itemView);
            this.mImg = (ImageView) itemView.findViewById(R.id.img);
            this.mTvName = (TextView) itemView.findViewById(R.id.tv_name);
            this.mTvDate = (TextView) itemView.findViewById(R.id.tv_date);
            this.mImgComment = (ImageView) itemView.findViewById(R.id.img_comment);
            this.mImgPraise = (ImageView) itemView.findViewById(R.id.img_praise);
            this.mTvMessage = (TextView) itemView.findViewById(R.id.tv_message);
        }
    }



}
