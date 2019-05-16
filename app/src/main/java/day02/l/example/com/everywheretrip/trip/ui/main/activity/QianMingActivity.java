package day02.l.example.com.everywheretrip.trip.ui.main.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import day02.l.example.com.everywheretrip.R;

public class QianMingActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView mImg;
    /**
     * 完成
     */
    private TextView mOk;
    private Toolbar mToolbar;
    private EditText mEt;
    private Intent upIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qian_ming);
        initView();

        upIntent = getIntent();
        String gexing = upIntent.getStringExtra("gexing");
        mEt.setText(gexing);
    }

    private void initView() {
        mImg = (ImageView) findViewById(R.id.img);
        mOk = (TextView) findViewById(R.id.ok);
        mOk.setOnClickListener(this);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mEt = (EditText) findViewById(R.id.et);
    }

    @Override
    public void onClick(View v) {
        String s = mEt.getText().toString();
        switch (v.getId()) {
            default:
                break;
            case R.id.ok:
                upIntent.putExtra("datas",s);
                setResult(0x002,upIntent);
                finish();
                break;
        }
    }
}
