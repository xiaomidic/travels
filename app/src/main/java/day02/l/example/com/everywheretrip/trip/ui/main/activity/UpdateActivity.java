package day02.l.example.com.everywheretrip.trip.ui.main.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import day02.l.example.com.everywheretrip.R;
import day02.l.example.com.everywheretrip.trip.bean.UsenBean;
import day02.l.example.com.everywheretrip.trip.bean.XiuGaiBean;
import day02.l.example.com.everywheretrip.trip.net.ApiService;
import day02.l.example.com.everywheretrip.trip.net.HttpUtils;
import day02.l.example.com.everywheretrip.trip.net.RxUtils;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


public class UpdateActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView mImg;
    /**
     * 完成
     */
    private TextView mOk;
    private Toolbar mToolbar;
    /**
     * 我的关注
     */
    private EditText mEt;
    /**
     * 27/27
     */
    String userName ="userName";
    String description ="description";
    String gender ="gender";
    String photo ="photo";
    private TextView mTv;
    private int num = 0;
    private Intent upIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        initView();

    }


    private void initView() {
        mImg = (ImageView) findViewById(R.id.img);
        mOk = (TextView) findViewById(R.id.ok);
        mEt = (EditText) findViewById(R.id.et);
        mOk.setOnClickListener(this);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mImg.setOnClickListener(this);
        mToolbar.setOnClickListener(this);
        mEt.setOnClickListener(this);
        mTv = (TextView) findViewById(R.id.tv);
        upIntent = getIntent();
        String name = upIntent.getStringExtra("name");
        mEt.setText(name);

    }

    @Override
    public void onClick(View v) {
        String s = mEt.getText().toString();

        switch (v.getId()) {
            default:
                break;
            case R.id.ok:
                //Intent intent = new Intent();
                upIntent.putExtra("names", s);
                setResult(0x0002, upIntent);
                finish();
                break;
            case R.id.img:
                break;
            case R.id.toolbar:
                break;
            case R.id.et:
                break;
        }
    }
}
