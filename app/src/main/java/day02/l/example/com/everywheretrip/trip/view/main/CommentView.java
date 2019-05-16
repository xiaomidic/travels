package day02.l.example.com.everywheretrip.trip.view.main;

import day02.l.example.com.everywheretrip.trip.base.BaseMvpView;
import day02.l.example.com.everywheretrip.trip.bean.CommentBean;

public interface CommentView extends BaseMvpView{
    void setData(CommentBean bean);
}
