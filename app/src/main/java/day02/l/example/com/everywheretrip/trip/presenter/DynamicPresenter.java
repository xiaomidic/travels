package day02.l.example.com.everywheretrip.trip.presenter;

import day02.l.example.com.everywheretrip.trip.base.BasePresenter;
import day02.l.example.com.everywheretrip.trip.bean.DynamicBean;
import day02.l.example.com.everywheretrip.trip.model.DynamicModel;
import day02.l.example.com.everywheretrip.trip.net.ResultCallBack;
import day02.l.example.com.everywheretrip.trip.view.main.DynamicView;

public class DynamicPresenter extends BasePresenter<DynamicView>{

    private DynamicModel dynamicModel;

    @Override
    protected void initModel() {
        dynamicModel = new DynamicModel();
        mModels.add(dynamicModel);

    }
    public void setDynamic(int id){
        dynamicModel.setData(id,new ResultCallBack<DynamicBean>() {
            @Override
            public void onSuccess(DynamicBean bean) {
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
