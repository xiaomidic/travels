package day02.l.example.com.everywheretrip.trip.net;


import day02.l.example.com.everywheretrip.trip.bean.BanMiDetailsBean;
import day02.l.example.com.everywheretrip.trip.bean.CancelCollectionBean;
import day02.l.example.com.everywheretrip.trip.bean.CircuitBean;
import day02.l.example.com.everywheretrip.trip.bean.CollectionBean;
import day02.l.example.com.everywheretrip.trip.bean.ColltionBean;
import day02.l.example.com.everywheretrip.trip.bean.CommentBean;
import day02.l.example.com.everywheretrip.trip.bean.DynamicBean;
import day02.l.example.com.everywheretrip.trip.bean.ParticularsBean;
import day02.l.example.com.everywheretrip.trip.bean.UsenBean;
import day02.l.example.com.everywheretrip.trip.bean.VerifyCodeBean;
import day02.l.example.com.everywheretrip.trip.bean.VersionBean;
import day02.l.example.com.everywheretrip.trip.bean.WebBean;
import day02.l.example.com.everywheretrip.trip.bean.XiuGaiBean;
import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * @author xts
 *         Created by asus on 2019/4/2.
 */

public interface ApiService {
    int SUCCESS_CODE =200;
    String sBaseUrl = "http://yun918.cn/study/public/index.php/";

    /**
     * 获取验证码
     * @return
     */
    @GET("verify")
    Observable<VerifyCodeBean> getVerifyCode();

    String url = "https://api.banmi.com/api/3.0/";
    @GET("content/routes/{page}")
    @Headers("banmi-app-token:o033z8g7ALbaQW0tbYexFFzENXhajFeLGqhONbqG3kfXzjiHaNWjjAdHg2PI4EUvMgwz33DGdaNilZOj4wj6WOt4mMaf01U2bSHu92GJpMXeusRgYCIzGFPfdwd83scBA")
    Observable<ParticularsBean> getData(@Path("page") int page);

    @POST("content/routes/{page}/like")
    @Headers("banmi-app-token:o033z8g7ALbaQW0tbYexFFzENXhajFeLGqhONbqG3kfXzjiHaNWjjAdHg2PI4EUvMgwz33DGdaNilZOj4wj6WOt4mMaf01U2bSHu92GJpMXeusRgYCIzGFPfdwd83scBA")
    Observable<ColltionBean> getColltion(@Path("page") int page);

    @POST("content/routes/{page}/dislike")
    @Headers("banmi-app-token:o033z8g7ALbaQW0tbYexFFzENXhajFeLGqhONbqG3kfXzjiHaNWjjAdHg2PI4EUvMgwz33DGdaNilZOj4wj6WOt4mMaf01U2bSHu92GJpMXeusRgYCIzGFPfdwd83scBA")
    Observable<CancelCollectionBean> getCancelColltion(@Path("page") int page);

    @GET("banmi/{page}")
    @Headers("banmi-app-token:o033z8g7ALbaQW0tbYexFFzENXhajFeLGqhONbqG3kfXzjiHaNWjjAdHg2PI4EUvMgwz33DGdaNilZOj4wj6WOt4mMaf01U2bSHu92GJpMXeusRgYCIzGFPfdwd83scBA")
    Observable<BanMiDetailsBean> getBanmi(@Path("page") int page);

    @GET("account/collectedRoutes")
    @Headers("banmi-app-token:o033z8g7ALbaQW0tbYexFFzENXhajFeLGqhONbqG3kfXzjiHaNWjjAdHg2PI4EUvMgwz33DGdaNilZOj4wj6WOt4mMaf01U2bSHu92GJpMXeusRgYCIzGFPfdwd83scBA")
    Observable<CollectionBean> getCollection();

    @GET("banmi/{page}/routes")
    @Headers("banmi-app-token:o033z8g7ALbaQW0tbYexFFzENXhajFeLGqhONbqG3kfXzjiHaNWjjAdHg2PI4EUvMgwz33DGdaNilZOj4wj6WOt4mMaf01U2bSHu92GJpMXeusRgYCIzGFPfdwd83scBA")
    Observable<CircuitBean> getCircuit(@Path("page") int page);

    @GET("content/routes/{page}/reviews?page=")
    @Headers("banmi-app-token:o033z8g7ALbaQW0tbYexFFzENXhajFeLGqhONbqG3kfXzjiHaNWjjAdHg2PI4EUvMgwz33DGdaNilZOj4wj6WOt4mMaf01U2bSHu92GJpMXeusRgYCIzGFPfdwd83scBA")
    Observable<CommentBean> getComment(@Path("page") int page, @Query("num")int num);

    @POST("account/updateInfo")
    @FormUrlEncoded
    @Headers("banmi-app-token:o033z8g7ALbaQW0tbYexFFzENXhajFeLGqhONbqG3kfXzjiHaNWjjAdHg2PI4EUvMgwz33DGdaNilZOj4wj6WOt4mMaf01U2bSHu92GJpMXeusRgYCIzGFPfdwd83scBA")
    Observable<XiuGaiBean> getupdtate(@Field("userName") String userName,
                                      @Field("description") String description,
                                      @Field("gender") String gender,
                                      @Field("photo") String photo
                                      );

    @GET("account/info")
    @Headers("banmi-app-token:o033z8g7ALbaQW0tbYexFFzENXhajFeLGqhONbqG3kfXzjiHaNWjjAdHg2PI4EUvMgwz33DGdaNilZOj4wj6WOt4mMaf01U2bSHu92GJpMXeusRgYCIzGFPfdwd83scBA")
    Observable<UsenBean> getUser();

    @GET("content/bundles")
    @Headers("banmi-app-token:o033z8g7ALbaQW0tbYexFFzENXhajFeLGqhONbqG3kfXzjiHaNWjjAdHg2PI4EUvMgwz33DGdaNilZOj4wj6WOt4mMaf01U2bSHu92GJpMXeusRgYCIzGFPfdwd83scBA")
    Observable<WebBean> getWeb();

    @GET("banmi/{page}")
    @Headers("banmi-app-token:o033z8g7ALbaQW0tbYexFFzENXhajFeLGqhONbqG3kfXzjiHaNWjjAdHg2PI4EUvMgwz33DGdaNilZOj4wj6WOt4mMaf01U2bSHu92GJpMXeusRgYCIzGFPfdwd83scBA")
    Observable<DynamicBean> getDynamic(@Path("page") int page);

    String VersionUrl="https://api.banmi.com/api/";
    @GET("app/common/getVersionInfo?operating_system=android")
    Observable<VersionBean> getVersion();

    public String Url = "http://cdn.banmi.com/banmiapp/";

    @GET("apk/banmi_330.apk")
    Observable<ResponseBody> download();
}
