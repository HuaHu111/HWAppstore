package com.example.acer.myapplication.api;

import com.example.acer.myapplication.bean.RecommendBean;
import com.example.acer.myapplication.utils.JsonParseUtils;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;
import com.zhxu.library.api.BaseApi;
import com.zhxu.library.api.BaseResultEntity;
import com.zhxu.library.listener.HttpOnNextListener;

import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import rx.Observable;

/**
 * Created by acer on 2018/6/14.
 */

public class RecommendApi  extends BaseApi<RecommendBean>{

    public RecommendApi(HttpOnNextListener listener, RxAppCompatActivity rxAppCompatActivity) {
        super(listener, rxAppCompatActivity);
    }

    @Override
    public Observable getObservable(Retrofit retrofit) {
        HttpGetService httpGetService = retrofit.create(HttpGetService.class);
        return httpGetService.getRecommendData();
    }


    @Override
    public RecommendBean call(ResponseBody responseBody) {
        //转换规则
        String result="";
        try {
            result=responseBody.string();

        }catch (Exception e){
            e.printStackTrace();
        }
        return JsonParseUtils.parseRecommendBean(result);
    }
}
