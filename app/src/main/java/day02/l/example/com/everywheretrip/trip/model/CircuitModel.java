package day02.l.example.com.everywheretrip.trip.model;

import day02.l.example.com.everywheretrip.trip.base.BaseModel;
import day02.l.example.com.everywheretrip.trip.bean.CircuitBean;
import day02.l.example.com.everywheretrip.trip.bean.CommentBean;
import day02.l.example.com.everywheretrip.trip.net.ApiService;
import day02.l.example.com.everywheretrip.trip.net.BaseObserver;
import day02.l.example.com.everywheretrip.trip.net.HttpUtils;
import day02.l.example.com.everywheretrip.trip.net.ResultCallBack;
import day02.l.example.com.everywheretrip.trip.net.RxUtils;
import io.reactivex.disposables.Disposable;

public class CircuitModel extends BaseModel {

    private ResultCallBack<CircuitBean> data;

    public void setData(int id, final ResultCallBack<CircuitBean> data) {
        ApiService apiserver = HttpUtils.getInstance().getApiserver(ApiService.url, ApiService.class);
        apiserver.getCircuit(id).compose(RxUtils.<CircuitBean>rxObserableSchedulerHelper()).subscribe(
                new BaseObserver<CircuitBean>() {
            @Override
            public void onNext(CircuitBean circuitBean) {
                if (circuitBean!=null){
                    data.onSuccess(circuitBean);
                }
            }

            @Override
            public void error(String msg) {

            }

            @Override
            protected void subscribe(Disposable d) {

            }
        });

    }
}
