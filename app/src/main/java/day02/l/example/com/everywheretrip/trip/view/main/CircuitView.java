package day02.l.example.com.everywheretrip.trip.view.main;

import day02.l.example.com.everywheretrip.trip.base.BaseMvpView;
import day02.l.example.com.everywheretrip.trip.bean.CircuitBean;

public interface CircuitView extends BaseMvpView{
    void setData(CircuitBean bean);
}
