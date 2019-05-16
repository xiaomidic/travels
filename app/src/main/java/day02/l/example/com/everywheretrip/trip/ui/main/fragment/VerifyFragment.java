package day02.l.example.com.everywheretrip.trip.ui.main.fragment;


import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentManager;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.Timer;

import butterknife.BindView;
import butterknife.OnClick;
import day02.l.example.com.everywheretrip.R;
import day02.l.example.com.everywheretrip.trip.base.BaseApp;
import day02.l.example.com.everywheretrip.trip.base.BaseFragment;
import day02.l.example.com.everywheretrip.trip.base.Constants;
import day02.l.example.com.everywheretrip.trip.presenter.VerifyPresenter;
import day02.l.example.com.everywheretrip.trip.ui.main.activity.LoginActivity;
import day02.l.example.com.everywheretrip.trip.util.Logger;
import day02.l.example.com.everywheretrip.trip.view.main.VerifyView;
import day02.l.example.com.everywheretrip.trip.widget.IdentifyingCodeView;
import io.reactivex.annotations.Nullable;

/**
 * @author xts
 *         Created by asus on 2019/5/4.
 */

public class VerifyFragment extends BaseFragment<VerifyView, VerifyPresenter> implements VerifyView {
    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.tv_send_again)
    TextView mTvSendAgain;
    @BindView(R.id.icv)
    IdentifyingCodeView mIcv;
    @BindView(R.id.tv_wait)
    TextView mTvWait;
    private int mTime;

    /**
     *
     * @param code 验证码,没有传递""
     * @return
     */
    public static VerifyFragment newIntance(String code){
        VerifyFragment verifyFragment = new VerifyFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Constants.VERIFY_CODE,code);
        verifyFragment.setArguments(bundle);
        return verifyFragment;
    }

    @Override
    protected VerifyPresenter initPresenter() {
        return new VerifyPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_verify;
    }

    @OnClick({R.id.iv_back, R.id.tv_send_again})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                pop();
                break;
            case R.id.tv_send_again:
                //调用它是有条件的
                if (mTime == 0){
                    mPresenter.getVerifyCode();
                    //重新发起倒计时
                    LoginOrBindFragment fragment = (LoginOrBindFragment) getActivity().getSupportFragmentManager().findFragmentByTag(LoginActivity.TAG);
                    fragment.countDown();
                }

                break;
        }
    }

    /**
     * 碎片手动弹栈
     */
    private void pop() {
        FragmentManager manager = getActivity(). getSupportFragmentManager();
        //获取回退栈中碎片的数量
        /*int backStackEntryCount = manager.getBackStackEntryCount();
        Logger.println("回退栈Fragmnet数量:"+backStackEntryCount);*/
        //弹栈
        manager.popBackStack();
    }

    @Override
    protected void initData() {

    }

    @Override
    public void setData(String data) {
        if (!TextUtils.isEmpty(data) && mTvWait != null) {
            mTvWait.setText(BaseApp.getRes().getString(R.string.verify_code)+data);
        }
    }

    @Override
    protected void initListener() {
        mIcv.setOnEditorActionListener(new IdentifyingCodeView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                return false;
            }

            @Override
            public void onTextChanged(String s) {
                autoLogin();
            }
        });
    }

    private void autoLogin() {
        Logger.println(mIcv.getTextContent());
        if (mIcv.getTextContent().length()>=4){
            //自动登录
            toastShort("自动登录");
            mIcv.setBackgroundEnter(false);
            mTvWait.setText(BaseApp.getRes().getString(R.string.wait_please));
            showLoading();
        }
    }

    public void setCountDownTime(int time) {
        mTime = time;
        if (mTvSendAgain != null){
            if (time != 0){
                String format = String.format(getResources().getString(R.string.send_again) + "(%ss)", time);
                mTvSendAgain.setText(format);
                mTvSendAgain.setTextColor(getResources().getColor(R.color.c_999));
            }else {
                mTvSendAgain.setText(getResources().getString(R.string.send_again));
                mTvSendAgain.setTextColor(getResources().getColor(R.color.c_fa6a13));
            }
        }
    }

    @Override
    protected void initView() {
        String code = getArguments().getString(Constants.VERIFY_CODE);
        setData(code);
    }
}
