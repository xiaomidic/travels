package day02.l.example.com.everywheretrip.trip.ui.main.activity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;

import java.util.List;

import day02.l.example.com.everywheretrip.R;
import day02.l.example.com.everywheretrip.trip.bean.CancelCollectionBean;
import day02.l.example.com.everywheretrip.trip.bean.ColltionBean;
import day02.l.example.com.everywheretrip.trip.bean.ParticularsBean;
import day02.l.example.com.everywheretrip.trip.net.ApiService;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class WebActivity extends AppCompatActivity implements View.OnClickListener {
    //5cd8180a3fc1952208001059

    /**
     * 东京
     */
    private TextView mTvTitle;
    /**
     * 东京
     */
    private TextView mTvGinza;
    /**
     * 东京
     */
    private TextView mTvShop;
    private ImageView mImg;
    private ImageView mImgTitle;
    /**
     * 我的关注
     */
    private TextView mTvName;
    /**
     * 我的关注
     */
    private TextView mTvCity;
    /**
     * 我的关注
     */
    private TextView mTvOccupation;
    /**
     * 我的关注
     */
    private TextView mTvMessage;
    private ImageView mImgUser1;
    /**
     * 我的关注
     */
    private TextView mTvUser1;
    /**
     * 我的关注
     */
    private TextView mTvDate1;
    /**
     * 我的关注
     */
    private TextView mTvMessage1;
    private View mView1;
    private ImageView mImgTitle2;
    /**
     * 我的关注
     */
    private TextView mTvUser2;
    /**
     * 我的关注
     */
    private TextView mTvDate2;
    private int page;
    private ImageView mImg1;
    private ImageView mImg2;
    private ImageView mImg3;
    private ImageView mImg4;
    private LinearLayout mLl;
    private View mView2;
    private ImageView mImgTitle3;
    /**
     * 我的关注
     */
    private TextView mTvUser3;
    /**
     * 我的关注
     */
    private TextView mTvDate3;
    private TextView mTvMessage2;
    private View mView3;
    /**
     * 查看全部评论
     */
    private ImageView mIv;
    private LinearLayout mAa;
    /**
     * 开启线程
     */
    private Button mBtnStart;
    private ImageView mX;
    private ImageView mImgColltion;
    private int id;
    /**
     * 未收藏
     */
    private TextView mColltion;
    private boolean collected;
    private LinearLayout mShare;
    private String cardURL;
    private String introduction;
    private ParticularsBean.ResultBean.RouteBean route;
    /**
     * 查看全部评论
     */
    private TextView mTvComment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        initView();
        initPer();
        initData();
    }

    private void initPer() {
        String[] per = {Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE};
        ActivityCompat.requestPermissions(this, per, 100);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }

    private void initData() {
        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiService.url)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiService apiService = retrofit.create(ApiService.class);
        Observable<ParticularsBean> observable = apiService.getData(page);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ParticularsBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ParticularsBean particularsBean) {
                        route = particularsBean.getResult().getRoute();
                        id = route.getId();
                        ParticularsBean.ResultBean.BanmiBean banmi = particularsBean.getResult().getBanmi();
                        ParticularsBean.ResultBean result = particularsBean.getResult();
                        List<ParticularsBean.ResultBean.ReviewsBean> reviews = particularsBean.getResult().getReviews();
                        if (route != null) {

                            String city = route.getCity();
                            mTvTitle.setText(city);

                            String title = route.getTitle();
                            mTvGinza.setText(title);


                            String shareTitle = route.getShareContent();
                            mTvShop.setText(shareTitle);

                            cardURL = route.getCardURL();
                            Glide.with(WebActivity.this).load(cardURL).into(mImg);

                        }


                        collected = route.getisCollected();
                        if (collected == true) {
                            mImgColltion.setImageResource(R.drawable.collect_highlight);
                            mColltion.setText("已收藏");

                        } else {
                            mImgColltion.setImageResource(R.drawable.collect_default);
                            mColltion.setText("未收藏");
                        }


                        if (banmi != null) {
                            String photo = banmi.getPhoto();
                            RequestOptions options = new RequestOptions().circleCrop();
                            Glide.with(WebActivity.this).load(photo).apply(options).into(mImgTitle);
                            String name = banmi.getName();
                            mTvUser1.setText(name);

                            String occupation = banmi.getOccupation();
                            mTvOccupation.setText(occupation);

                            String location = banmi.getLocation();
                            mTvCity.setText(location);

                            introduction = banmi.getIntroduction();
                            mTvMessage.setText(introduction);

                        }
                        if (reviews != null) {
                            String name2 = reviews.get(0).getUserName();
                            mTvName.setText(name2);
                            String name = reviews.get(0).getUserName();
                            mTvUser1.setText(name);
                            String createdAt = reviews.get(0).getCreatedAt();
                            mTvDate1.setText(createdAt);
                            String content = reviews.get(0).getContent();
                            mTvMessage1.setText(content);
                            String userPhoto = reviews.get(0).getUserPhoto();
                            RequestOptions options = new RequestOptions().circleCrop();
                            Glide.with(WebActivity.this).load(userPhoto).apply(options).into(mImgUser1);

                            String userPhoto1 = reviews.get(1).getUserPhoto();
                            RequestOptions options1 = new RequestOptions().circleCrop();
                            Glide.with(WebActivity.this).load(userPhoto1).apply(options1).into(mImgTitle);
                            String name1 = reviews.get(1).getUserName();
                            mTvUser2.setText(name1);
                            String createdAt1 = reviews.get(1).getCreatedAt();
                            mTvDate2.setText(createdAt1);
                            String userPhoto2 = reviews.get(2).getUserPhoto();
                            RequestOptions options2 = new RequestOptions().circleCrop();
                            Glide.with(WebActivity.this).load(userPhoto2).apply(options2).into(mImgTitle2);
                            Glide.with(WebActivity.this).load(userPhoto1).into(mImg1);
                            Glide.with(WebActivity.this).load(userPhoto2).into(mImg2);
                            Glide.with(WebActivity.this).load(userPhoto).into(mImg3);
                            Glide.with(WebActivity.this).load(userPhoto1).into(mImg4);


                            String userPhoto7 = reviews.get(1).getUserPhoto();
                            RequestOptions options7 = new RequestOptions().circleCrop();
                            Glide.with(WebActivity.this).load(userPhoto7).apply(options7).into(mImgTitle3);
                            String name3 = reviews.get(1).getUserName();
                            mTvUser3.setText(name3);
                            String createdAt2 = reviews.get(2).getCreatedAt();
                            mTvDate3.setText(createdAt2);
                            String content1 = reviews.get(2).getContent();
                            mTvMessage2.setText(content1);

                        }


                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void initView() {

        mImg1 = (ImageView) findViewById(R.id.img1);
        mImg2 = (ImageView) findViewById(R.id.img2);
        mImg3 = (ImageView) findViewById(R.id.img3);
        mImg4 = (ImageView) findViewById(R.id.img4);
        mLl = (LinearLayout) findViewById(R.id.ll);
        mView2 = (View) findViewById(R.id.view2);
        mImgTitle3 = (ImageView) findViewById(R.id.img_title3);
        mTvUser3 = (TextView) findViewById(R.id.tv_user3);
        mTvDate3 = (TextView) findViewById(R.id.tv_date3);
        mTvMessage2 = (TextView) findViewById(R.id.tv_message2);
        mView3 = (View) findViewById(R.id.view3);
        mIv = (ImageView) findViewById(R.id.iv);
        mAa = (LinearLayout) findViewById(R.id.aa);
        mBtnStart = (Button) findViewById(R.id.btn_start);
        mBtnStart.setOnClickListener(this);
        mX = (ImageView) findViewById(R.id.x);
        mImgTitle3.setOnClickListener(this);
        mTvUser3.setOnClickListener(this);
        mTvDate3.setOnClickListener(this);
        mTvMessage2.setOnClickListener(this);
        mView3.setOnClickListener(this);
        mIv.setOnClickListener(this);
        mAa.setOnClickListener(this);
        mTvTitle = (TextView) findViewById(R.id.tv_title);
        mTvGinza = (TextView) findViewById(R.id.tv_ginza);
        mTvShop = (TextView) findViewById(R.id.tv_shop);
        mImg = (ImageView) findViewById(R.id.img);
        mImgTitle = (ImageView) findViewById(R.id.img_title);
        mTvName = (TextView) findViewById(R.id.tv_name);
        mTvCity = (TextView) findViewById(R.id.tv_city);
        mTvOccupation = (TextView) findViewById(R.id.tv_occupation);
        mTvMessage = (TextView) findViewById(R.id.tv_message);
        mImgUser1 = (ImageView) findViewById(R.id.img_user1);
        mTvUser1 = (TextView) findViewById(R.id.tv_user1);
        mTvDate1 = (TextView) findViewById(R.id.tv_date1);
        mTvMessage1 = (TextView) findViewById(R.id.tv_message1);
        mView1 = (View) findViewById(R.id.view1);
        mImgTitle2 = (ImageView) findViewById(R.id.img_title2);
        mTvUser2 = (TextView) findViewById(R.id.tv_user2);
        mTvDate2 = (TextView) findViewById(R.id.tv_date2);
        page = getIntent().getIntExtra("page", 0);
        mX.setOnClickListener(this);
        mImgColltion = (ImageView) findViewById(R.id.img_colltion);
        mImgColltion.setOnClickListener(this);
        mImg4.setOnClickListener(this);
        mColltion = (TextView) findViewById(R.id.colltion);

        mShare = (LinearLayout) findViewById(R.id.share);
        mShare.setOnClickListener(this);
        mTvComment = (TextView) findViewById(R.id.tv_comment);
        mTvComment.setOnClickListener(this);
    }

    boolean colltion = false;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.x:
                finish();
                break;
            case R.id.img_colltion:
               /* Intent intent = new Intent();
                intent.putExtra("id",id);
                startActivity(intent);*/


                break;
            case R.id.img4:
                break;
            case R.id.aa:
                initColltion();
                break;
            case R.id.share:
                shareBorad();
                break;
            case R.id.tv_comment:
                Intent intent = new Intent(this, CommentActivity.class);
                Intent intent1 = intent.putExtra("id", id);
                startActivity(intent1);
                break;
        }
    }

    /**
     * 带面板分享
     */
    private void shareBorad() {
        UMImage thumb = new UMImage(this, route.getShareImageWechat());
        thumb.compressStyle = UMImage.CompressStyle.SCALE;//大小压缩，默认为大小压缩，
        new ShareAction(WebActivity.this).withText(route.getShareTitle())
                .withMedia(thumb)
                .setDisplayList(SHARE_MEDIA.SINA, SHARE_MEDIA.QQ, SHARE_MEDIA.WEIXIN)
                .setCallback(umShareListener).open();
    }

    private UMShareListener umShareListener = new UMShareListener() {
        /**
         * @descrption 分享开始的回调
         * @param platform 平台类型
         */
        @Override
        public void onStart(SHARE_MEDIA platform) {

        }

        /**
         * @descrption 分享成功的回调
         * @param platform 平台类型
         */
        @Override
        public void onResult(SHARE_MEDIA platform) {
            Toast.makeText(WebActivity.this, "成功了", Toast.LENGTH_LONG).show();
        }

        /**
         * @descrption 分享失败的回调
         * @param platform 平台类型
         * @param t 错误原因
         */
        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            Toast.makeText(WebActivity.this, "失败" + t.getMessage(), Toast.LENGTH_LONG).show();
        }

        /**
         * @descrption 分享取消的回调
         * @param platform 平台类型
         */
        @Override
        public void onCancel(SHARE_MEDIA platform) {
            Toast.makeText(WebActivity.this, "取消了", Toast.LENGTH_LONG).show();

        }
    };

    private void initColltion() {

        if (collected) {
            mImgColltion.setImageResource(R.drawable.collect_default);
            mColltion.setText("未收藏");
            collected = false;
            cancelcolltion();
        } else {
            mImgColltion.setImageResource(R.drawable.collect_highlight);
            mColltion.setText("已收藏");
            collected = true;
            initcolltion();
        }
    }

    private void cancelcolltion() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiService.url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        ApiService apiService = retrofit.create(ApiService.class);
        Observable<CancelCollectionBean> data = apiService.getCancelColltion(page);
        data.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CancelCollectionBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(CancelCollectionBean cancelCollectionBean) {
                        String desc = cancelCollectionBean.getDesc();
                        Log.d("tag", "onNext: " + desc.toString());
                        Toast.makeText(WebActivity.this, desc, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void initcolltion() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiService.url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        ApiService apiService = retrofit.create(ApiService.class);
        Observable<ColltionBean> data = apiService.getColltion(page);
        data.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ColltionBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ColltionBean colltionBean) {
                        String desc = colltionBean.getDesc();
                        Log.d("tag", "onNext: " + desc.toString());
                        Toast.makeText(WebActivity.this, desc, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
