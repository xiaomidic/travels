package day02.l.example.com.everywheretrip.trip.view.main;

import day02.l.example.com.everywheretrip.trip.base.BaseMvpView;
import day02.l.example.com.everywheretrip.trip.bean.DynamicBean;

public interface DynamicView extends BaseMvpView{
    void setData(DynamicBean bean);
}
