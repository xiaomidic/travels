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

public class CommentModel extends BaseModel{
    private ResultCallBack<CommentBean> data;

    public void setData(int page,int num, final ResultCallBack<CommentBean> data) {
        ApiService apiserver = HttpUtils.getInstance().getApiserver(ApiService.url, ApiService.class);
        apiserver.getComment(page,num).compose(RxUtils.<CommentBean>rxObserableSchedulerHelper()).subscribe(
                new BaseObserver<CommentBean>() {
                    @Override
                    public void onNext(CommentBean commentBean) {
                        if (commentBean!=null){

                            data.onSuccess(commentBean);
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
