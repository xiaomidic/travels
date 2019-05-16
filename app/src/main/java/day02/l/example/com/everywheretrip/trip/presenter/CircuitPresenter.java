package day02.l.example.com.everywheretrip.trip.presenter;

import day02.l.example.com.everywheretrip.trip.base.BasePresenter;
import day02.l.example.com.everywheretrip.trip.bean.CircuitBean;
import day02.l.example.com.everywheretrip.trip.model.CircuitModel;
import day02.l.example.com.everywheretrip.trip.net.ResultCallBack;
import day02.l.example.com.everywheretrip.trip.view.main.CircuitView;

public class CircuitPresenter extends BasePresenter<CircuitView> {

    private CircuitModel circuitModel;

    @Override
    protected void initModel() {
        circuitModel = new CircuitModel();
        mModels.add(circuitModel);
    }
    public void getCircuit(int id){
        circuitModel.setData(id,new ResultCallBack<CircuitBean>() {
            @Override
            public void onSuccess(CircuitBean bean) {
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
