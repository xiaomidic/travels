package day02.l.example.com.everywheretrip.trip.ui.main.activity;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.webkit.JavascriptInterface;

public class AndroidJs extends Object{
    private static final String TAG = "AndroidJs";
    private Context context;

    public AndroidJs(Context context) {
        this.context = context;
    }

    //定义JS需要调用的方法
    //被JS调用的方法必须加入@JavasrciptInterface注解
    @JavascriptInterface
    public void callAndroid(String type,int id){
        if (type.equals("route_details")){
            Log.d(TAG, "callAndroid: "+id);
            Intent intent = new Intent(context, WebActivity.class);
                intent.putExtra("page",id);
            context.startActivity(intent);
        }
    }

    @JavascriptInterface
    public void callAndroid(String type){
        if (type.equals("subject_list")){
            Log.d(TAG, "callAndroid: "+12345678);
            Intent intent = new Intent(context, ChaKanActivity.class);
            context.startActivity(intent);
        }
    }
}
