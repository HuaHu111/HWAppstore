package com.example.acer.myapplication.mvp.interactor;

import com.example.acer.myapplication.api.AppIntroductionApi;
import com.example.acer.myapplication.api.IGetDataDelegate;
import com.example.acer.myapplication.base.BaseActivity;
import com.example.acer.myapplication.bean.AppIntroductionBean;
import com.example.acer.myapplication.utils.JsonParseUtils;
import com.zhxu.library.http.HttpManager;
import com.zhxu.library.listener.HttpOnNextListener;

import javax.inject.Inject;

import retrofit2.http.HTTP;

/**
 * Created by acer on 2018/7/15.
 */

public class AppIntroductionInterator {


    private IGetDataDelegate<AppIntroductionBean> mDelegate;

    @Inject
    public AppIntroductionInterator(){

    }


    public void loadAppIntroductionData(BaseActivity activity,String packageName,IGetDataDelegate<AppIntroductionBean>delegate){
        this.mDelegate=delegate;
        AppIntroductionApi api=new AppIntroductionApi(listener,activity,packageName);
        HttpManager manager= HttpManager.getInstance();
        manager.doHttpDeal(api);
    }

    private HttpOnNextListener<AppIntroductionBean> listener=new HttpOnNextListener<AppIntroductionBean>() {
        @Override
        public void onNext(AppIntroductionBean appIntroductionBean) {
            mDelegate.getDataSuccess(appIntroductionBean);
        }

        @Override
        public void onCacheNext(String string) {
            super.onCacheNext(string);
            AppIntroductionBean appIntroductionBean= JsonParseUtils.parseAppIntroductionBean(string);
            mDelegate.getDataSuccess(appIntroductionBean);
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
            mDelegate.getDataError(e.getMessage());
        }
    };
}
