package day02.l.example.com.everywheretrip.trip.presenter;

import day02.l.example.com.everywheretrip.trip.base.BasePresenter;
import day02.l.example.com.everywheretrip.trip.bean.BanMiDetailsBean;
import day02.l.example.com.everywheretrip.trip.model.BanMiModel;
import day02.l.example.com.everywheretrip.trip.net.ResultCallBack;
import day02.l.example.com.everywheretrip.trip.view.main.BanMiView;

public class BanMiPresenter extends BasePresenter<BanMiView>{

    private BanMiModel banMiModel;

    @Override
    protected void initModel() {
        banMiModel = new BanMiModel();
        mModels.add(banMiModel);
    }
    public void getBanMi(int id){
        banMiModel.setData(id,new ResultCallBack<BanMiDetailsBean>() {
            @Override
            public void onSuccess(BanMiDetailsBean bean) {
                if (bean!=null){
                    if (mMvpView!=null){
                        mMvpView.setData(bean);
                    }
                }
            }

            @Override
            public void onFail(String msg) {

            }
        });

    }
}
