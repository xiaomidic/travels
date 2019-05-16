package day02.l.example.com.everywheretrip.trip.ui.main.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import day02.l.example.com.everywheretrip.R;
import day02.l.example.com.everywheretrip.trip.base.BaseActivity;
import day02.l.example.com.everywheretrip.trip.bean.BanMiDetailsBean;
import day02.l.example.com.everywheretrip.trip.presenter.BanMiPresenter;
import day02.l.example.com.everywheretrip.trip.ui.main.adapter.MyViewPageAdapter;
import day02.l.example.com.everywheretrip.trip.ui.main.fragment.CircuitFragment;
import day02.l.example.com.everywheretrip.trip.ui.main.fragment.DynamicFragment;
import day02.l.example.com.everywheretrip.trip.view.main.BanMiView;

public class BanMiActivity extends BaseActivity<BanMiView, BanMiPresenter> implements BanMiView {

    @BindView(R.id.img_fanhui)
    ImageView mImgFanhui;
    @BindView(R.id.img_fenxiang)
    ImageView mImgFenxiang;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.img)
    ImageView mImg;
    @BindView(R.id.tv_name)
    TextView mTvName;
    @BindView(R.id.img_love)
    ImageView mImgLove;
    @BindView(R.id.tv_gz_num)
    TextView mTvGzNum;
    @BindView(R.id.tv_concern)
    TextView mTvConcern;
    @BindView(R.id.img_city)
    ImageView mImgCity;
    @BindView(R.id.tv_city)
    TextView mTvCity;
    @BindView(R.id.img_toponymy)
    ImageView mImgToponymy;
    @BindView(R.id.tv_toponymy)
    TextView mTvToponymy;
    @BindView(R.id.tv_message)
    TextView mTvMessage;
    @BindView(R.id.card)
    CardView mCard;
    @BindView(R.id.tab)
    TabLayout mTab;
    @BindView(R.id.vp)
    FrameLayout mVp;
    private int id;
    private ArrayList<Fragment> fragments;

   /* @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ban_mi);
        ButterKnife.bind(this);
    }*/

    @Override
    protected BanMiPresenter initPresenter() {
        return new BanMiPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_ban_mi;
    }

    @Override
    protected void initView() {
        id = getIntent().getIntExtra("id", 0);
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);

        fragments = new ArrayList<>();
        fragments.add(new DynamicFragment(id));
        fragments.add(new CircuitFragment(id));
        ArrayList<String> title = new ArrayList<>();
        mTab.addTab(mTab.newTab().setText("动态"));
        mTab.addTab(mTab.newTab().setText("线路"));

      mTab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
          @Override
          public void onTabSelected(TabLayout.Tab tab) {
        switch (tab.getPosition()){
            case 0:
                switchFragment(0);
                break;
                case 1:
                    switchFragment(1);
                break;
        }
          }

          @Override
          public void onTabUnselected(TabLayout.Tab tab) {

          }

          @Override
          public void onTabReselected(TabLayout.Tab tab) {

          }
      });

    }
    private int lastPosition = 0;

    private void switchFragment(int type) {
        FragmentManager manager = getSupportFragmentManager();

        Fragment fragment = fragments.get(type);
        FragmentTransaction tran = manager.beginTransaction();
        if (!fragment.isAdded()) {
            tran.add(R.id.vp, fragment);
        }
        tran.hide(fragments.get(lastPosition));
        tran.show(fragment);
        tran.commit();
        lastPosition = type;
    }
    @Override
    protected void initData() {
        mPresenter.getBanMi(id);
    }

    @Override
    public void setData(BanMiDetailsBean bean) {
        BanMiDetailsBean.ResultBean.ShareBean share = bean.getResult().getShare();
        BanMiDetailsBean.ResultBean.BanmiBean banmi = bean.getResult().getBanmi();

        String shareImage = share.getShareImage();

        RoundedCorners roundedCorners = new RoundedCorners(6);
        RequestOptions options1 = RequestOptions.bitmapTransform(roundedCorners).override(120,160);
        Glide.with(this).load(shareImage).apply(options1).into(mImg);
        String name = banmi.getName();
        mTvName.setText(name);

        int following = banmi.getFollowing();
        mTvGzNum.setText(following+"人关注");

        String location = banmi.getLocation();
        mTvCity.setText(location);

        String occupation = banmi.getOccupation();
        mTvToponymy.setText(occupation);

        String introduction = banmi.getIntroduction();
        mTvMessage.setText(introduction);
    }

    @OnClick({R.id.img_fanhui, R.id.img_fenxiang, R.id.toolbar, R.id.img, R.id.tv_name, R.id.img_love, R.id.tv_gz_num, R.id.tv_concern, R.id.img_city, R.id.tv_city, R.id.img_toponymy, R.id.tv_toponymy, R.id.tv_message, R.id.card, R.id.tab, R.id.vp})
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.img_fanhui:
                finish();
                break;
            case R.id.img_fenxiang:
                break;
            case R.id.toolbar:
                break;
            case R.id.img:
                break;
            case R.id.tv_name:
                break;
            case R.id.img_love:
                break;
            case R.id.tv_gz_num:
                break;
            case R.id.tv_concern:
                break;
            case R.id.img_city:
                break;
            case R.id.tv_city:
                break;
            case R.id.img_toponymy:
                break;
            case R.id.tv_toponymy:
                break;
            case R.id.tv_message:
                break;
            case R.id.card:
                break;
            case R.id.tab:
                break;
            case R.id.vp:
                break;
        }
    }
}
