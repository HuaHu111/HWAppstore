package com.example.acer.myapplication.api;

import com.example.acer.myapplication.bean.AppDetailBean;
import com.example.acer.myapplication.utils.JsonParseUtils;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;
import com.zhxu.library.api.BaseApi;
import com.zhxu.library.listener.HttpOnNextListener;

import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import rx.Observable;

/**
 * Created by acer on 2018/7/1.
 */

public class AppDetailApi extends BaseApi<AppDetailBean> {

    private String packageName;

    public AppDetailApi(HttpOnNextListener listener, RxAppCompatActivity rxAppCompatActivity,String packageName) {
        super(listener, rxAppCompatActivity);
        this.packageName=packageName;
    }

    @Override
    public Observable getObservable(Retrofit retrofit) {
        HttpGetService httpGetService=retrofit.create(HttpGetService.class);
        return httpGetService.getAppDetailData(packageName);
    }

    @Override
    public AppDetailBean call(ResponseBody responseBody) {
        String result="";
        try {
           result= responseBody.string();
        }catch (Exception e){
            e.printStackTrace();
        }
        return JsonParseUtils.parseAppDetailBean(result);
    }
}
