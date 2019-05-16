package day02.l.example.com.everywheretrip.trip.ui.main.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import day02.l.example.com.everywheretrip.R;

public class SpecialActivity extends AppCompatActivity implements View.OnClickListener {

    private WebView mWeb;
    private String url;
    private TextView mTitle;
    private Toolbar mToolbar;
    private ImageView mImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_special);
        initView();

    }


    private void initView() {
        mWeb = (WebView) findViewById(R.id.web);
        String title = getIntent().getStringExtra("title");
        mTitle = (TextView) findViewById(R.id.title);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mTitle.setText(title);
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
        mWeb.getSettings().setJavaScriptEnabled(true);

        mWeb.setWebViewClient(new WebViewClient());

        url = getIntent().getStringExtra("url") + "?os=android";


        mWeb.loadUrl(url);
        AndroidJs androidJs = new AndroidJs(this);
        mWeb.addJavascriptInterface(androidJs, "android");
        mImg = (ImageView) findViewById(R.id.img);
        mImg.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.img:
                finish();
                break;
        }
    }
}
