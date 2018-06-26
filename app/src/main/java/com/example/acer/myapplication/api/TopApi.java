package com.example.acer.myapplication.api;

import com.example.acer.myapplication.bean.TopBean;
import com.example.acer.myapplication.utils.JsonParseUtils;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;
import com.zhxu.library.api.BaseApi;
import com.zhxu.library.listener.HttpOnNextListener;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import rx.Observable;

/**
 * Created by acer on 2018/6/26.
 */

public class TopApi extends BaseApi<TopBean> {

    public TopApi(HttpOnNextListener listener, RxAppCompatActivity rxAppCompatActivity) {
        super(listener, rxAppCompatActivity);
    }

    @Override
    public Observable getObservable(Retrofit retrofit) {
        HttpGetService httpGetService = retrofit.create(HttpGetService.class);
        return httpGetService.getTopData();
    }

    @Override
    public TopBean call(ResponseBody responseBody) {
        String result="";
        try {
            result=responseBody.string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return JsonParseUtils.parseTopBean(result);
    }
}
