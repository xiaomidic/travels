package day02.l.example.com.everywheretrip.trip.model;

import day02.l.example.com.everywheretrip.trip.base.BaseModel;
import day02.l.example.com.everywheretrip.trip.bean.BanMiDetailsBean;
import day02.l.example.com.everywheretrip.trip.bean.CollectionBean;
import day02.l.example.com.everywheretrip.trip.net.ApiService;
import day02.l.example.com.everywheretrip.trip.net.BaseObserver;
import day02.l.example.com.everywheretrip.trip.net.HttpUtils;
import day02.l.example.com.everywheretrip.trip.net.ResultCallBack;
import day02.l.example.com.everywheretrip.trip.net.RxUtils;
import io.reactivex.disposables.Disposable;

public class BanMiModel extends BaseModel{
    private ResultCallBack<BanMiDetailsBean> data;

    public void setData(int id, final ResultCallBack<BanMiDetailsBean> data) {
        ApiService apiserver = HttpUtils.getInstance().getApiserver(ApiService.url, ApiService.class);
        apiserver.getBanmi(id).compose(RxUtils.<BanMiDetailsBean>rxObserableSchedulerHelper()).subscribe(
                new BaseObserver<BanMiDetailsBean>() {
                    @Override
                    public void onNext(BanMiDetailsBean banMiDetailsBean) {
                        if (banMiDetailsBean!=null){

                            data.onSuccess(banMiDetailsBean);
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
