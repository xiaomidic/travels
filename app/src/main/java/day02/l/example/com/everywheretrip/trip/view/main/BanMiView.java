package day02.l.example.com.everywheretrip.trip.view.main;

import day02.l.example.com.everywheretrip.trip.base.BaseMvpView;
import day02.l.example.com.everywheretrip.trip.bean.BanMiDetailsBean;

public interface BanMiView extends BaseMvpView{
    void setData(BanMiDetailsBean bean);
}
