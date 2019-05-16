package day02.l.example.com.everywheretrip.trip.ui.main.activity;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.Gson;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import day02.l.example.com.everywheretrip.R;
import day02.l.example.com.everywheretrip.trip.base.Constants;
import day02.l.example.com.everywheretrip.trip.bean.Bean;
import day02.l.example.com.everywheretrip.trip.bean.UpdateBean;
import day02.l.example.com.everywheretrip.trip.bean.UsenBean;
import day02.l.example.com.everywheretrip.trip.bean.XiuGaiBean;
import day02.l.example.com.everywheretrip.trip.net.ApiService;
import day02.l.example.com.everywheretrip.trip.net.HttpUtils;
import day02.l.example.com.everywheretrip.trip.net.RxUtils;
import day02.l.example.com.everywheretrip.trip.ui.main.adapter.MyUpdateAdapter;
import day02.l.example.com.everywheretrip.trip.util.SpUtil;
import day02.l.example.com.everywheretrip.trip.util.ToastUtil;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MessageActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView mToolbarImg;
/**
     * 个人信息
     */

    private TextView mToolbarText;
    private Toolbar mPersonalToolbar;
    private View mViewV;

/**
     * 头像
     */

    private TextView mTvHead;
    private ImageView mHeadPortrait;

/**
     * 用户昵称
     */

    private TextView mTvUserName;

/**
     * 伴小米
     */

    private TextView mTvName;

/**
     * 性别
     */

    private TextView mTvSex;

/**
     * 保密
     */

    private TextView mTvGender;

/**
     * 个性签名
     */

    private TextView mTvSignature;

/**
     * 四肢不全五体不勤
     */

    private TextView mTvPersonalizedSignature;

/**
     * 修改密码
     */

    private TextView mChangePassword;

/**
     * 绑定手机
     */

    private TextView mBindPhone;

/**
     * 退出登录
     */

    private Button mLogOut;
    private File mFile;
    private Uri mImageUri;
    private ArrayList<UpdateBean.ResultBean> list;
    private MyUpdateAdapter adapter;
    private RelativeLayout mRl1;
    private RelativeLayout mRl2;
    private RelativeLayout mRl3;
    private RelativeLayout mRl4;

   /* userName,用户名
    description,个人简介
    gender,性别(F:女,M:男,U:保密)
    photo:图片(用不了,需要先传到阿里云服务器上,然后将阿里云返回的链接给到这里)
*/
    String userName ="userName";
    String description ="description";
    String gender ="gender";
    String photo ="photo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        initView();
    }



    private void initView() {
        mToolbarImg = (ImageView) findViewById(R.id.toolbar_img);
        mToolbarText = (TextView) findViewById(R.id.toolbar_text);
        mPersonalToolbar = (Toolbar) findViewById(R.id.personal_toolbar);
        mViewV = (View) findViewById(R.id.view_v);
        mTvHead = (TextView) findViewById(R.id.tv_head);
        mHeadPortrait = (ImageView) findViewById(R.id.head_portrait);
        mTvUserName = (TextView) findViewById(R.id.tv_user_name);
        mTvName = (TextView) findViewById(R.id.tv_name);
        mTvSex = (TextView) findViewById(R.id.tv_sex);
        mTvGender = (TextView) findViewById(R.id.tv_gender);
        mTvSignature = (TextView) findViewById(R.id.tv_signature);
        mTvPersonalizedSignature = (TextView) findViewById(R.id.tv_personalized_signature);
        mChangePassword = (TextView) findViewById(R.id.change_password);
        mBindPhone = (TextView) findViewById(R.id.bind_phone);
        mLogOut = (Button) findViewById(R.id.log_out);
        mLogOut.setOnClickListener(this);
        mToolbarImg.setOnClickListener(this);
        mToolbarText.setOnClickListener(this);
        mPersonalToolbar.setOnClickListener(this);
        mViewV.setOnClickListener(this);
        mTvHead.setOnClickListener(this);
        mHeadPortrait.setOnClickListener(this);
        mTvUserName.setOnClickListener(this);
        mTvName.setOnClickListener(this);
        mTvSex.setOnClickListener(this);
        mTvGender.setOnClickListener(this);
        mTvSignature.setOnClickListener(this);
        mTvPersonalizedSignature.setOnClickListener(this);

        registerForContextMenu(mTvSex);
        mRl1 = (RelativeLayout) findViewById(R.id.rl1);
        mRl1.setOnClickListener(this);
        mRl2 = (RelativeLayout) findViewById(R.id.rl2);
        mRl2.setOnClickListener(this);
        mRl3 = (RelativeLayout) findViewById(R.id.rl3);
        mRl3.setOnClickListener(this);
        mRl4 = (RelativeLayout) findViewById(R.id.rl4);
        mRl4.setOnClickListener(this);
        mChangePassword.setOnClickListener(this);
        mBindPhone.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String name = mTvName.getText().toString();
        String qianming = mTvPersonalizedSignature.getText().toString();

        switch (v.getId()) {
            default:
                break;
            case R.id.log_out:
                startActivity(new Intent(this, LoginActivity.class));
                break;
            case R.id.toolbar_img:
                finish();
                break;
            case R.id.toolbar_text:
                break;
            case R.id.personal_toolbar:
                break;
            case R.id.view_v:
                break;
            case R.id.tv_head:

                break;
            case R.id.head_portrait:
                break;
            case R.id.tv_user_name:

                break;
            case R.id.tv_name:
                break;
            case R.id.tv_sex:

                break;
            case R.id.tv_gender:
                break;
            case R.id.tv_signature:

                break;

            case R.id.rl1:
                initpop();
                break;
            case R.id.rl2:
                Intent intent = new Intent(this, UpdateActivity.class);
                intent.putExtra("name", name);
                startActivityForResult(intent, 0x0001);
                break;
            case R.id.rl3:
                break;
            case R.id.tv_personalized_signature:
                break;
            case R.id.rl4:
                Intent intent1 = new Intent(this, QianMingActivity.class);
                intent1.putExtra("gexing", qianming);
                startActivityForResult(intent1, 0x001);
                break;
            case R.id.change_password:
                break;
            case R.id.bind_phone:
                break;
        }
    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        menu.add(1, 1, 1, "男");
        menu.add(1, 2, 1, "女");
        menu.add(1, 3, 1, "取消");
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 1:
                mTvGender.setText("男");
                break;
            case 2:
                mTvGender.setText("女");
                break;
            case 3:
                break;
        }
        return super.onContextItemSelected(item);
    }




    private static final String TAG = "MainActivity";
    private static final int CAMERA_CODE = 100;
    private static final int ALBUM_CODE = 200;

    private void takePick() {//相册Sd卡权限
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            openAlbum();
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 200);
        }
    }

    private void takePhoto() {//相机权限

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
            openCamera();
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 100);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            if (requestCode == 100) {
                openCamera();
            } else if (requestCode == 200) {
                openAlbum();
            }
        }
    }

    //开启相册
    private void openAlbum() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
        startActivityForResult(intent, ALBUM_CODE);
    }

    //开启相机
    private void openCamera() {
        //1.创建空白文件
        mFile = new File(getExternalCacheDir(), System.currentTimeMillis() + ".jpg");
        if (!mFile.exists()) {
            try {
                mFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //2.将File文件转换为Uri路径
        //适配7.0
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
            mImageUri = Uri.fromFile(mFile);
        } else {
            //第二个参数要和清单文件中的配置保持一致
            mImageUri = FileProvider.getUriForFile(this, "com.baidu.upload.provider", mFile);
        }

        //3.启动相机
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, mImageUri);//将拍照图片存入mImageUri
        startActivityForResult(intent, CAMERA_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0x0001 && resultCode == 0x0002) {
            String name = data.getStringExtra("names");
            mTvName.setText(name);
            if (name != null){
                SpUtil.setParam(Constants.SPNAME,name);
            }

            initUpdate();
        }
         if (requestCode == 0x001 && resultCode == 0x002) {
             String datas = data.getStringExtra("datas");
             mTvPersonalizedSignature.setText(datas);
             if (datas!= null){
                SpUtil.setParam(Constants.SPPARAM,datas);
            }

            initUpdate();

        }
        if (resultCode == RESULT_OK) {

            if (requestCode == CAMERA_CODE) {//拍照

                //显示拍照后的图片
                try {
                    Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(mImageUri));
                    mHeadPortrait.setImageBitmap(bitmap);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

                //文件上传
                uploadFile(mFile);
            } else if (requestCode == ALBUM_CODE) {//相册

                //1.获取相册中选中的图片的URi路径
                Uri imageUri = data.getData();

                //显示相册中选中的图片
                try {
                    Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(imageUri));
                    mHeadPortrait.setImageBitmap(bitmap);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

                //2.将Uri路径转换为File文件
                File file = getFileFromUri(imageUri, this);

                //3.文件上传
                if (file.exists()) {
                    uploadFile(file);
                }
            }
        }
    }

    private void initUpdate() {
        String tvname = mTvName.getText().toString();
        String geren = mTvPersonalizedSignature.getText().toString();
        String tvgender = mTvGender.getText().toString();

        ApiService apiserver = HttpUtils.getInstance().getApiserver(ApiService.url, ApiService.class);
            apiserver.getupdtate(tvname,geren,tvgender,"").compose(RxUtils.<XiuGaiBean>rxObserableSchedulerHelper()).subscribe(
                    new Observer<XiuGaiBean>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(XiuGaiBean xiuGaiBean) {

                            Toast.makeText(MessageActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onError(Throwable e) {
                            Toast.makeText(MessageActivity.this, "修改失败", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onComplete() {

                        }
                    }
            );


    }

    public File getFileFromUri(Uri uri, Context context) {
        if (uri == null) {
            return null;
        }
        switch (uri.getScheme()) {
            case "content":
                return getFileFromContentUri(uri, context);
            case "file":
                return new File(uri.getPath());
            default:
                return null;
        }
    }

    /**
     * 通过内容解析中查询uri中的文件路径
     */
    private File getFileFromContentUri(Uri contentUri, Context context) {
        if (contentUri == null) {
            return null;
        }
        File file = null;
        String filePath;
        String[] filePathColumn = {MediaStore.MediaColumns.DATA};
        ContentResolver contentResolver = context.getContentResolver();
        Cursor cursor = contentResolver.query(contentUri, filePathColumn, null,
                null, null);
        if (cursor != null) {
            cursor.moveToFirst();
            filePath = cursor.getString(cursor.getColumnIndex(filePathColumn[0]));
            cursor.close();

            if (!TextUtils.isEmpty(filePath)) {
                file = new File(filePath);
            }
        }
        return file;
    }

    private void uploadFile(File mFile) {

        String url = "http://yun918.cn/study/public/file_upload.php";

        OkHttpClient client = new OkHttpClient.Builder()
                .build();

        RequestBody requestBody = RequestBody.create(MediaType.parse("image/jpg"), mFile);

        MultipartBody body = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("key", "H1808B")
                .addFormDataPart("file", mFile.getName(), requestBody)
                .build();

        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();

        Call call = client.newCall(request);

        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, "onFailure: " + e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                String string = response.body().string();
                Gson gson = new Gson();
                final Bean upLoadBean = gson.fromJson(string, Bean.class);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (upLoadBean != null) {
                            if (upLoadBean.getCode() == 200) {
                                Toast.makeText(MessageActivity.this, upLoadBean.getRes(), Toast.LENGTH_SHORT).show();
                                RequestOptions options = new RequestOptions().circleCrop();
                                Glide.with(MessageActivity.this).load(upLoadBean.getData().getUrl()).apply(options).into(mHeadPortrait);
                                if (upLoadBean.getData().getUrl()!=null){
                                    SpUtil.setParam(Constants.HEADIMG,upLoadBean.getData().getUrl());

                                }
                                Log.e(TAG, "run: " + upLoadBean.getData().getUrl());
                            } else {
                                Toast.makeText(MessageActivity.this, upLoadBean.getRes(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
            }
        });

    }

    private void initpop() {
        final PopupWindow popupWindow = new PopupWindow(MessageActivity.this);
        View inflate = LayoutInflater.from(MessageActivity.this).inflate(R.layout.layout_popu, null);
        popupWindow.setContentView(inflate);
        popupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        popupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
//                popupWindow.setFocusable(true);
//                popupWindow.setOutsideTouchable(true);
        popupWindow.showAtLocation(mToolbarText, Gravity.BOTTOM, 0, 0);
        final Button pick = inflate.findViewById(R.id.pick);
        Button photo = inflate.findViewById(R.id.photo);
        Button quxiao = inflate.findViewById(R.id.quxiao);

        pick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takePick();
            }
        });

        photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takePhoto();
            }
        });
        quxiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
    }
}
