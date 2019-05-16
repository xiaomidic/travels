package day02.l.example.com.everywheretrip.trip.presenter;

import day02.l.example.com.everywheretrip.trip.base.BasePresenter;
import day02.l.example.com.everywheretrip.trip.bean.CommentBean;
import day02.l.example.com.everywheretrip.trip.model.CommentModel;
import day02.l.example.com.everywheretrip.trip.net.ResultCallBack;
import day02.l.example.com.everywheretrip.trip.view.main.CommentView;

public class CommentPresenter extends BasePresenter<CommentView>{
    private CommentModel commentModel;
    @Override
    protected void initModel() {
        commentModel = new CommentModel();
        mModels.add(commentModel);
    }
    public void getComment( int page, int num){
        commentModel.setData(page,num,new ResultCallBack<CommentBean>() {
            @Override
            public void onSuccess(CommentBean bean) {
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
