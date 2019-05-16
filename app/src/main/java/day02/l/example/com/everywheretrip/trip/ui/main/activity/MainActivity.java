package day02.l.example.com.everywheretrip.trip.ui.main.activity;

import android.Manifest;
import android.app.NotificationManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import day02.l.example.com.everywheretrip.R;
import day02.l.example.com.everywheretrip.trip.base.Constants;
import day02.l.example.com.everywheretrip.trip.bean.UsenBean;
import day02.l.example.com.everywheretrip.trip.bean.VersionBean;
import day02.l.example.com.everywheretrip.trip.net.ApiService;
import day02.l.example.com.everywheretrip.trip.net.BaseObserver;
import day02.l.example.com.everywheretrip.trip.net.HttpUtils;
import day02.l.example.com.everywheretrip.trip.net.RxUtils;
import day02.l.example.com.everywheretrip.trip.ui.main.adapter.MyPageAdapter;
import day02.l.example.com.everywheretrip.trip.ui.main.fragment.BanMiFragment;
import day02.l.example.com.everywheretrip.trip.ui.main.fragment.HomeFragment;
import day02.l.example.com.everywheretrip.trip.util.InstallUtil;
import day02.l.example.com.everywheretrip.trip.util.SpUtil;
import day02.l.example.com.everywheretrip.trip.util.ToastUtil;
import day02.l.example.com.everywheretrip.trip.util.Tools;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private TabLayout mTab;
    private ViewPager mVp;
    private ArrayList<Fragment> list;
    private Toolbar mToolBar;
    private NavigationView mNa;
    private DrawerLayout mDl;
    private ImageView mImg;
    private ImageView mImg2;
    private RelativeLayout mLayu;
    /**
     * 林小姐
     */
    private TextView mMiss;
    /**
     * 四体不勤五谷不分人懒嘴花痴健忘
     */
    private TextView mGexing;
    /**
     * 编辑
     */
    private TextView mTvBianji;
    private ImageView mIv;
    /**
     * 我的钱包
     */
    private TextView mTvWallet;
    /**
     * 99
     */
    private TextView mTv99;
    /**
     * 》
     */
    private TextView mTvText;
    private RelativeLayout mRel;
    private ImageView mImgKajuan;
    private View mView;
    private ImageView mImgYigou;
    private View mView2;
    private ImageView mImgShoucang;
    private View mView3;
    private ImageView mImgGuanzhu;
    private CardView mCard;
    private ImageView mImgKefu;
    /**
     * 联系客服
     */
    private TextView mTvKefu;
    private ImageView mImgFankui;
    /**
     * 意见反馈
     */
    private TextView mTvFankui;
    private ImageView mImgBanben;
    /**
     * 我的收藏
     */
    private TextView mTvColltion;
    /**
     * 我的收藏
     */
    private TextView mTvShou;
    private RequestOptions options;
    private LinearLayout mBanben;
    private String version;
    private VersionBean.ResultBean.InfoBean info;
    private File sd;
    //定义notification实用的ID
    private static final int NO_3 =0x3;
    private NotificationCompat.Builder builder;
    private long count;
    public static void startAct(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        options = new RequestOptions().circleCrop();
        Glide.with(this).load(SpUtil.getParam(Constants.HEADIMG, "")).apply(options).into(mImg);
        mMiss.setText((String) SpUtil.getParam(Constants.SPNAME, ""));
        mGexing.setText((String) SpUtil.getParam(Constants.SPPARAM, ""));
        initData();


    }


    private void initData() {
        ApiService apiserver = HttpUtils.getInstance().getApiserver(ApiService.VersionUrl, ApiService.class);
        apiserver.getVersion().compose(RxUtils.<VersionBean>rxObserableSchedulerHelper()).subscribe(
                new BaseObserver<VersionBean>() {



                    @Override
                    public void onNext(VersionBean versionBean) {
                        info = versionBean.getResult().getInfo();
                        version = info.getVersion();
                        String download_url = info.getDownload_url();
                    }

                    @Override
                    public void error(String msg) {

                    }

                    @Override
                    protected void subscribe(Disposable d) {

                    }
                }
        );


    }


    private void initView() {
        initSD();
        TangKuang();
        mTab = (TabLayout) findViewById(R.id.tab);
        mVp = (ViewPager) findViewById(R.id.vp);
        mNa = (NavigationView) findViewById(R.id.na);
        mDl = (DrawerLayout) findViewById(R.id.dl);
        mImg = (ImageView) findViewById(R.id.img);
        mToolBar = (Toolbar) findViewById(R.id.toolBar);
        mToolBar.setTitle("");
        setSupportActionBar(mToolBar);

        //1.设置图标不显示处理
        mNa.setItemIconTintList(null);

        //2.设置菜单点击处理


        list = new ArrayList<>();
        list.add(new HomeFragment());
        list.add(new BanMiFragment());

        mTab.addTab(mTab.newTab().setText("首页").setIcon(R.drawable.home_highlight));
        mTab.addTab(mTab.newTab().setText("伴米").setIcon(R.drawable.banmi_highlight));
        MyPageAdapter adapter = new MyPageAdapter(getSupportFragmentManager(), list);
        mVp.setAdapter(adapter);
        mTab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mVp.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        mVp.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTab));


        mImg2 = (ImageView) findViewById(R.id.img2);
        mLayu = (RelativeLayout) findViewById(R.id.layu);
        mMiss = (TextView) findViewById(R.id.miss);
        mGexing = (TextView) findViewById(R.id.gexing);
        mTvBianji = (TextView) findViewById(R.id.tv_bianji);
        mIv = (ImageView) findViewById(R.id.iv);
        mTvWallet = (TextView) findViewById(R.id.tv_wallet);
        mTv99 = (TextView) findViewById(R.id.tv_99);
        mTvText = (TextView) findViewById(R.id.tv_text);
        mRel = (RelativeLayout) findViewById(R.id.rel);
        mImgKajuan = (ImageView) findViewById(R.id.img_kajuan);
        mView = (View) findViewById(R.id.view);
        mImgYigou = (ImageView) findViewById(R.id.img_yigou);
        mView2 = (View) findViewById(R.id.view2);
        mImgShoucang = (ImageView) findViewById(R.id.img_shoucang);
        mView3 = (View) findViewById(R.id.view3);
        mImgGuanzhu = (ImageView) findViewById(R.id.img_guanzhu);
        mCard = (CardView) findViewById(R.id.card);
        mImgKefu = (ImageView) findViewById(R.id.img_kefu);
        mTvKefu = (TextView) findViewById(R.id.tv_kefu);
        mImgFankui = (ImageView) findViewById(R.id.img_fankui);
        mTvFankui = (TextView) findViewById(R.id.tv_fankui);
        mImgBanben = (ImageView) findViewById(R.id.img_banben);
        mTvColltion = (TextView) findViewById(R.id.tv_colltion);
        mTvColltion.setOnClickListener(this);
        mImg2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDl.openDrawer(Gravity.LEFT);
            }
        });
        mImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, MessageActivity.class));
            }
        });

        mTvShou = (TextView) findViewById(R.id.tv_shou);
        mTvShou.setOnClickListener(this);
        mMiss.setOnClickListener(this);
        mGexing.setOnClickListener(this);
        mBanben = (LinearLayout) findViewById(R.id.banben);
        mBanben.setOnClickListener(this);
    }

    //选项菜单
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.layout_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.tv_colltion:
                Intent intent = new Intent(this, ColltionActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_shou:
                Intent intent1 = new Intent(this, CollectionActivity.class);
                startActivity(intent1);

                break;
            case R.id.miss:
                break;
            case R.id.gexing:
                break;
            case R.id.banben:
                TangKuang();
                break;
        }
    }

    private void TangKuang() {
        String versionName = Tools.getVersionName();

        if (!versionName.equals(version)){
            new AlertDialog.Builder(MainActivity.this)
                    .setTitle("温馨提示")
                    .setMessage("是否下载最新版本"+version)
                    .setNegativeButton("下载", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // initNotifition(v1);
                            ToastUtil.showShort("正在下载，请稍后···");
                            ok(sd + "/" + "abc.apk");
                        }
                    }).setPositiveButton("取消",null).show();

        }else {
            ToastUtil.showShort("已经是最新版本！");
        }

    }
    private void initSD() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) ==
                PackageManager.PERMISSION_GRANTED) {
            readSD();
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission
                    .WRITE_EXTERNAL_STORAGE}, 100);
        }
    }

    private void readSD() {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            sd = Environment.getExternalStorageDirectory();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 100:
                if (grantResults.length > 0 && grantResults[0] == PackageManager
                        .PERMISSION_GRANTED) {
                    readSD();
                }
                break;
        }
    }

    private static final String TAG = "MainActivity";
    private void ok(final String path) {
        String url="http://cdn.banmi.com/banmiapp/apk/banmi_330.apk";
        //  String url="http://ww1.sinaimg.cn/large/0065oQSqly1g2pquqlp0nj30n00yiq8u.jpg";

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .build();

        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();

        Call call = okHttpClient.newCall(request);

        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, "onFailure: " + e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                ResponseBody body = response.body();
                InputStream inputStream = body.byteStream();
                long max = body.contentLength();

                //文件下载保存
                saveFile(inputStream, path, max);
            }
        });
    }

    private void saveFile(InputStream inputStream, final String path, long max) {

        try {
            FileOutputStream fos = new FileOutputStream(new File(path));

            count = 0;

            int length = -1;
            byte[] bys = new byte[1024 * 10];

            while ((length = inputStream.read(bys)) != -1) {
                fos.write(bys, 0, length);

                count += length;

                Log.e(TAG, "count: " + count + ", max:" + max);
                double v = (double) count / (double) max;
                final int v1 = (int) (v * 100);


                //进度条和视频播放SurfaceView可以直接在子线程中刷新
               /* pb.setMax((int) max);
                pb.setProgress((int) count);*/
                //下载进度提示
             /*   builder.setContentText("下载" + count + "%");
                builder.setProgress(((int) max), ((int) count), true);*/
                initNotifition(v1,max);
            }


            inputStream.close();
            fos.close();

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(MainActivity.this, "下载完毕", Toast.LENGTH_SHORT).show();

                    //apk安装处理
                    mPath = path;
                    InstallUtil.installApk(MainActivity.this, path);
                }
            });
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 8.0安装处理
     */
    private String mPath = null;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == InstallUtil.UNKNOWN_CODE) {
            InstallUtil.installApk(MainActivity.this, mPath);//再次执行安装流程，包含权限判等
        }
    }

    private void initNotifition(int v1, long max) {
        builder = new NotificationCompat.Builder(this);
        builder.setSmallIcon(R.mipmap.ic_launcher_round);
        builder.setContentTitle("下载");
        builder.setContentText("正在下载");

        final NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(NO_3, builder.build());

        builder.setProgress(((int)max), v1, false);
        builder.setContentText("下载" + v1 + "%");
        if (count ==max){
            builder.setProgress(((int)max), ((int) this.count), true);
            //下载完成后更改标题以及提示信息
            builder.setContentTitle("开始安装");
            builder.setContentText("安装中...");

//            manager.cancel(NO_3);
        }
        //下载完成后更改标题以及提示信息
        //builder.setContentTitle("开始安装");
        // builder.setContentText("安装中...");
        //设置进度为不确定，用于模拟安装
        //   builder.setProgress(0, ((int)count), true);
        manager.notify(NO_3, builder.build());

//        manager.cancel(NO_3);


        //下载以及安装线程模拟
       /* new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    builder.setProgress(100, i, false);
                    manager.notify(NO_3, builder.build());
                    //下载进度提示
                    builder.setContentText("下载" + count + "%");

                    try {
                        Thread.sleep(50);//演示休眠50毫秒

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                builder.setContentText("下载" + count + "%");
                //下载完成后更改标题以及提示信息
                builder.setContentTitle("开始安装");
                builder.setContentText("安装中...");
                //设置进度为不确定，用于模拟安装
                builder.setProgress(0, ((int)count), true);
                manager.notify(NO_3, builder.build());
                // manager.cancel(NO_3);//设置关闭通知栏
            }
        }).start();*/
    }


    @Override
    protected void onRestart() {
        super.onRestart();
        Glide.with(this).load(SpUtil.getParam(Constants.HEADIMG, "")).apply(options).into(mImg);
        mMiss.setText((String) SpUtil.getParam(Constants.SPNAME, ""));
        mGexing.setText((String) SpUtil.getParam(Constants.SPPARAM, ""));
    }
}

