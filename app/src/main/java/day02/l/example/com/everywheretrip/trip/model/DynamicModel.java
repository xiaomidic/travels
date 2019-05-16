package day02.l.example.com.everywheretrip.trip.model;

import day02.l.example.com.everywheretrip.trip.base.BaseModel;
import day02.l.example.com.everywheretrip.trip.bean.DynamicBean;
import day02.l.example.com.everywheretrip.trip.net.ApiService;
import day02.l.example.com.everywheretrip.trip.net.BaseObserver;
import day02.l.example.com.everywheretrip.trip.net.HttpUtils;
import day02.l.example.com.everywheretrip.trip.net.ResultCallBack;
import day02.l.example.com.everywheretrip.trip.net.RxUtils;
import io.reactivex.disposables.Disposable;

public class DynamicModel extends BaseModel{
    public void setData(int id, final ResultCallBack<DynamicBean> data) {
        ApiService apiserver = HttpUtils.getInstance().getApiserver(ApiService.url, ApiService.class);
        apiserver.getDynamic(id).compose(RxUtils.<DynamicBean>rxObserableSchedulerHelper()).subscribe(
                new BaseObserver<DynamicBean>() {
                    @Override
                    public void onNext(DynamicBean dynamicBean) {
                        if (dynamicBean!=null){
                            data.onSuccess(dynamicBean);
                        }
                    }

                    @Override
                    public void error(String msg) {

                    }

                    @Override
                    protected void subscribe(Disposable d) {

                    }
                }
        );
    }
}
