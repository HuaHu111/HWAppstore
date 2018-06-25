package com.example.acer.myapplication.api;

import com.example.acer.myapplication.bean.CategoryBean;
import com.example.acer.myapplication.utils.JsonParseUtils;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;
import com.zhxu.library.api.BaseApi;
import com.zhxu.library.listener.HttpOnNextListener;

import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import rx.Observable;

/**
 * Created by acer on 2018/6/26.
 */

public class CategoryAppi extends BaseApi<CategoryBean> {
    public CategoryAppi(HttpOnNextListener listener, RxAppCompatActivity rxAppCompatActivity) {
        super(listener, rxAppCompatActivity);
    }

    @Override
    public Observable getObservable(Retrofit retrofit) {
        HttpGetService httpGetService = retrofit.create(HttpGetService.class);
        return httpGetService.getCategoryData();
    }

    @Override
    public CategoryBean call(ResponseBody responseBody) {
        //转换规则
        String result="";
        try {
            result=responseBody.string();

        }catch (Exception e){
            e.printStackTrace();
        }
        return JsonParseUtils.parseCategoryBean(result);
    }
}
