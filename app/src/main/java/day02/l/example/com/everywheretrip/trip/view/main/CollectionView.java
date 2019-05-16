package day02.l.example.com.everywheretrip.trip.view.main;

import day02.l.example.com.everywheretrip.trip.base.BaseMvpView;
import day02.l.example.com.everywheretrip.trip.bean.CollectionBean;

public interface CollectionView extends BaseMvpView{
    void setData(CollectionBean collectionBean);


}
