package day02.l.example.com.everywheretrip.trip.presenter;

import day02.l.example.com.everywheretrip.trip.base.BasePresenter;
import day02.l.example.com.everywheretrip.trip.bean.CollectionBean;
import day02.l.example.com.everywheretrip.trip.model.CollectionModel;
import day02.l.example.com.everywheretrip.trip.net.ResultCallBack;
import day02.l.example.com.everywheretrip.trip.view.main.CollectionView;

public class CollectionPresenter extends BasePresenter<CollectionView>{
    private CollectionModel collectionModel;
    @Override
    protected void initModel() {
        collectionModel = new CollectionModel();
        mModels.add(collectionModel);
    }
    public void getCollection(){
        collectionModel.getData(new ResultCallBack<CollectionBean>() {
            @Override
            public void onSuccess(CollectionBean bean) {
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
