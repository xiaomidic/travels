package day02.l.example.com.everywheretrip.trip.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import butterknife.ButterKnife;
import butterknife.Unbinder;
import day02.l.example.com.everywheretrip.trip.util.ToastUtil;
import day02.l.example.com.everywheretrip.trip.widget.LoadingDialog;

/**
 * @author xts
 *         Created by asus on 2019/4/3.
 */

public abstract class BaseFragment<V extends BaseMvpView,P extends BasePresenter>
        extends Fragment implements BaseMvpView{

    private Unbinder mUnbinder;
    protected P mPresenter;
    private LoadingDialog mLoadingDialog;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(getLayoutId(), null);
        mUnbinder = ButterKnife.bind(this, inflate);
        mPresenter = initPresenter();
        if (mPresenter != null){
            mPresenter.bind((V)this);
        }
        initView();
        initListener();
        initData();
        return inflate;
    }

    protected void initData() {

    }

    protected void initListener() {

    }

    protected void initView() {

    }

    protected abstract P initPresenter();

    protected abstract int getLayoutId();

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
        mPresenter.onDestory();
        mPresenter = null;
    }

    @Override
    public void showLoading() {
        if (mLoadingDialog == null){
            mLoadingDialog = new LoadingDialog(getContext());
        }
        mLoadingDialog.show();
    }

    @Override
    public void hideLoading() {
        if (mLoadingDialog != null && mLoadingDialog.isShowing()){
            mLoadingDialog.dismiss();
        }
    }

    @Override
    public void toastShort(String msg) {
        ToastUtil.showShort(msg);
    }
}