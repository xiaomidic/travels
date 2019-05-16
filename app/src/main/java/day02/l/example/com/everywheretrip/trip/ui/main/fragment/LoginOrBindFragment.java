package day02.l.example.com.everywheretrip.trip.ui.main.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.umeng.socialize.bean.SHARE_MEDIA;

import butterknife.BindView;
import butterknife.OnClick;
import day02.l.example.com.everywheretrip.R;
import day02.l.example.com.everywheretrip.trip.base.BaseFragment;
import day02.l.example.com.everywheretrip.trip.base.Constants;
import day02.l.example.com.everywheretrip.trip.presenter.LoginOrBindPresenter;
import day02.l.example.com.everywheretrip.trip.ui.main.activity.LoginActivity;
import day02.l.example.com.everywheretrip.trip.ui.main.activity.MainActivity;
import day02.l.example.com.everywheretrip.trip.ui.main.activity.WebViewActivity;
import day02.l.example.com.everywheretrip.trip.util.Tools;
import day02.l.example.com.everywheretrip.trip.view.main.LoginOrBindView;

/**
 * @author xts
 *         Created by asus on 2019/5/4.
 */

public class LoginOrBindFragment extends BaseFragment<LoginOrBindView, LoginOrBindPresenter> implements LoginOrBindView{

    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.tv_hello)
    TextView mTvHello;
    @BindView(R.id.tv_login)
    TextView mTvLogin;
    @BindView(R.id.tv_coutry_code)
    TextView mTvCoutryCode;
    @BindView(R.id.et_phone)
    EditText mEtPhone;
    @BindView(R.id.btn_send_verify)
    Button mBtnSendVerify;
    @BindView(R.id.ll_container)
    LinearLayout mLlContainer;
    @BindView(R.id.view)
    View mView;
    @BindView(R.id.ll_or)
    LinearLayout mLlOr;
    @BindView(R.id.ll_oauth_login)
    LinearLayout mLlOauthLogin;
    @BindView(R.id.iv_wechat)
    ImageView mIvWechat;
    @BindView(R.id.iv_qq)
    ImageView mIvQq;
    @BindView(R.id.iv_sina)
    ImageView mIvSina;
    @BindView(R.id.tv_protocol)
    TextView mTvProtocol;
    //因为登录和绑定手机号码是用的一个碎片,所以需要使用type隐藏和显示某一些view
    //如果是0:代表登录界面;1:代表要绑定手机
    private int mType;
    private VerifyFragment mVerifyFragment;
    private static int COUNT_DOWN_TIME = 10;
    private int mTime = COUNT_DOWN_TIME;
    private Handler mHandler;
    //验证码
    private String mVerifyCode = "";

    public static LoginOrBindFragment newIntance(int type){
        LoginOrBindFragment fragment = new LoginOrBindFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(Constants.TYPE,type);
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    protected LoginOrBindPresenter initPresenter() {
        return new LoginOrBindPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_login_bind;
    }

    @OnClick({R.id.iv_back, R.id.btn_send_verify, R.id.iv_wechat, R.id.iv_qq, R.id.iv_sina})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                getActivity().finish();
                break;
            case R.id.btn_send_verify:
                getVerifyCode();
                addVerifyFragment();
                time();
                break;
            case R.id.iv_wechat:
                mPresenter.oauthLogin(SHARE_MEDIA.WEIXIN);
                break;
            case R.id.iv_qq:
                mPresenter.oauthLogin(SHARE_MEDIA.QQ);
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
                break;
            case R.id.iv_sina:
                mPresenter.oauthLogin(SHARE_MEDIA.SINA);
                /*Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);*/
                break;
        }
    }

    private void time() {
        //避免多次执行倒计时
        if (mTime>0 && mTime<COUNT_DOWN_TIME){
            return;
        }
        countDown();
    }

    /**
     * 倒计时,如果执行中,不要再调用
     */
    public void countDown() {
        if (mHandler  == null){
            mHandler = new Handler();
        }
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //避免倒计时变成负的
                if (mTime <= 0){
                    mTime = COUNT_DOWN_TIME;
                    return;
                }
                mTime--;
                if (mVerifyFragment != null){
                    mVerifyFragment.setCountDownTime(mTime);
                }
                countDown();
            }
        },1000);
    }

    private void getVerifyCode() {
        //if (60s之内如果发送过,就不用发送)
        /*if (mTime>0 && mTime != COUNT_DOWN_TIME){
            //倒计时中
            return;
        }
        mPresenter.getVerifyCode();*/

        if (mTime>0 && mTime<COUNT_DOWN_TIME-1){
            //倒计时中
            return;
        }
        mVerifyCode = "";
        mPresenter.getVerifyCode();
    }

    private void addVerifyFragment() {
        if (TextUtils.isEmpty(getPhone())){
            return;
        }
        FragmentManager manager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = manager.beginTransaction();
        //添加到回退栈
        fragmentTransaction.addToBackStack(null);
        mVerifyFragment = VerifyFragment.newIntance(mVerifyCode);
        fragmentTransaction.add(R.id.fl_container, mVerifyFragment).commit();
        //关闭软键盘
        Tools.closeKeyBoard(getActivity());
    }

    @Override
    protected void initListener() {
        //文本发生改变监听
        mEtPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                switchBtnState(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    /**
     * 根据输入框中是否有内容,切换发送验证码的背景
     * @param s
     */
    private void switchBtnState(CharSequence s) {
        if (TextUtils.isEmpty(s)){
            mBtnSendVerify.setBackgroundResource(R.drawable.bg_btn_ea_r15);
        }else {
            mBtnSendVerify.setBackgroundResource(R.drawable.bg_btn_fa6a13_r15);
        }
    }

    @Override
    public String getPhone() {
        return mEtPhone.getText().toString().trim();
    }

    @Override
    public Activity getAct() {
        return getActivity();
    }

    @Override
    public void go2MainActivity() {
        MainActivity.startAct(getContext());
    }

    @Override
    public void setData(String code) {
        this.mVerifyCode = code;
        if (mVerifyFragment != null){
            mVerifyFragment.setData(code);
        }
    }

    @Override
    protected void initView() {
        getArgumentsData();
        setProtocol();
        showOrHideView();

    }

    /**
     * 因为登录和绑定手机号码是用的一个碎片,所以需要使用type隐藏和显示某一些view
     */
    private void showOrHideView() {
        if (mType == LoginActivity.TYPE_LOGIN){
            //登录
            //View.VISIBLE 显示
            //View.INVISIBLE 隐藏,占位置
            //View.GONE 隐藏 不占位置
            mIvBack.setVisibility(View.INVISIBLE);
            mLlOauthLogin.setVisibility(View.VISIBLE);
            mLlOr.setVisibility(View.VISIBLE);
        }else {
            //绑定
            mIvBack.setVisibility(View.VISIBLE);
            mLlOauthLogin.setVisibility(View.GONE);
            mLlOr.setVisibility(View.GONE);
        }
    }

    private void getArgumentsData() {
        Bundle arguments = getArguments();
        mType = arguments.getInt(Constants.TYPE);
    }

    private void setProtocol() {
        //SpannableString ss = new SpannableString(getResources().getString(R.string.agree_protocol));
        //通过设置不同的span达到不同的显示效果
      /*  what：对SpannableString进行润色的各种Span；
        start：需要润色文字段开始的下标,包含；
        end：需要润色文字段结束的下标,结束应用指定Span的位置，不包含；
        flags：Spannable.SPAN_EXCLUSIVE_EXCLUSIVE：前后都不包括（
        在标志位【start，end）前后添加文字，新添加的文字不会有任何设置的属性）
Spannable.SPAN_EXCLUSIVE_INCLUSIVE ：前面不包括，后面包括。
（在标志位【start，end）前添加文字，新添加的文字不会有任何设置的属性，后边的添加的文字会带有设置的what属性）
Spannable.SPAN_INCLUSIVE_EXCLUSIVE ：前面包括，后面不包括。
（在标志位【start，end）后添加文字，新添加的文字不会有任何设置的属性，前边边的添加的文字会带有设置的what属性）
Spannable.SPAN_INCLUSIVE_INCLUSIVE ：前后都包括。前后都不包括
（在标志位【start，end）前后添加文字，新添加的文字会有设置的属性）
*/

       /* ss.setSpan(what,0,16, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);


        mTvProtocol.setText(ss);*/

        //17个字,0-16
     /*   SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(getResources().getString(R.string.agree_protocol));
        //SPAN_INCLUSIVE_EXCLUSIVE 1-16,插入的位置1-15 可以渲染,16就不会被渲染
        //SPAN_INCLUSIVE_INCLUSIVE 1-16,插入的位置1-16 都可以渲染
        //SPAN_EXCLUSIVE_INCLUSIVE 1-16,插入的位置2-16 都可以渲染
        spannableStringBuilder.setSpan(what,1,16, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        spannableStringBuilder.insert(2,"12");
        mTvProtocol.setText(spannableStringBuilder);*/

        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(getResources().getString(R.string.agree_protocol));
        //点击事件
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                //跳转页面,webview展示协议
                //webView有很多坑,所以我们不直接用webView
                WebViewActivity.startAct(getActivity());
            }
        };
        spannableStringBuilder.setSpan(clickableSpan,13,17, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        //下划线
        UnderlineSpan underlineSpan = new UnderlineSpan();
        spannableStringBuilder.setSpan(underlineSpan,13,17, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        //前景色
        ForegroundColorSpan what = new ForegroundColorSpan(
                getResources().getColor(R.color.c_fa6a13));
        spannableStringBuilder.setSpan(what,13,17, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        //需要设置这个ClickableSpan才会有效果
        mTvProtocol.setMovementMethod(LinkMovementMethod.getInstance());
        mTvProtocol.setText(spannableStringBuilder);
    }

}
