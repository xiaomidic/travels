package day02.l.example.com.everywheretrip.trip.presenter;


import day02.l.example.com.everywheretrip.R;
import day02.l.example.com.everywheretrip.trip.base.BaseApp;
import day02.l.example.com.everywheretrip.trip.base.BasePresenter;
import day02.l.example.com.everywheretrip.trip.bean.VerifyCodeBean;
import day02.l.example.com.everywheretrip.trip.model.LoginModel;
import day02.l.example.com.everywheretrip.trip.net.ApiService;
import day02.l.example.com.everywheretrip.trip.net.ResultCallBack;
import day02.l.example.com.everywheretrip.trip.view.main.VerifyView;

/**
 * @author xts
 *         Created by asus on 2019/5/4.
 */

public class VerifyPresenter extends BasePresenter<VerifyView> {

    private LoginModel mLoginModel;

    @Override
    protected void initModel() {
        mLoginModel = new LoginModel();
        mModels.add(mLoginModel);
    }

    public void getVerifyCode() {
        mLoginModel.getVerifyCode(new ResultCallBack<VerifyCodeBean>() {
            @Override
            public void onSuccess(VerifyCodeBean bean) {
                if (bean != null && bean.getCode() == ApiService.SUCCESS_CODE){
                    if (mMvpView != null){
                        mMvpView.setData(bean.getData());
                    }
                }else {
                    if (mMvpView != null){
                        mMvpView.toastShort(BaseApp.getRes().getString(R.string.get_verify_fail));
                    }
                }
            }

            @Override
            public void onFail(String msg) {

            }
        });
    }
}
