package com.example.acer.myapplication.mvp.interactor;

import com.example.acer.myapplication.api.AppDetailApi;
import com.example.acer.myapplication.api.IGetDataDelegate;
import com.example.acer.myapplication.base.BaseActivity;
import com.example.acer.myapplication.bean.AppDetailBean;
import com.example.acer.myapplication.utils.JsonParseUtils;
import com.zhxu.library.http.HttpManager;
import com.zhxu.library.listener.HttpOnNextListener;

import javax.inject.Inject;

/**
 * Created by acer on 2018/7/1.
 */

public class AppDetailInteractor {

    private IGetDataDelegate<AppDetailBean> mDelegate;

    @Inject
    public AppDetailInteractor(){

    }

    public void loadAppDetailData(BaseActivity activity,String packagename,IGetDataDelegate<AppDetailBean>delegate){
        mDelegate=delegate;
        AppDetailApi api=new AppDetailApi(listener,activity,packagename);
        HttpManager manager=HttpManager.getInstance();
        manager.doHttpDeal(api);

    }


    private HttpOnNextListener<AppDetailBean> listener=new HttpOnNextListener<AppDetailBean>() {
        @Override
        public void onNext(AppDetailBean appDetailBean) {
            mDelegate.getDataSuccess(appDetailBean);
        }

        @Override
        public void onCacheNext(String string) {
            super.onCacheNext(string);
            AppDetailBean appDetailBean= JsonParseUtils.parseAppDetailBean(string);
            mDelegate.getDataSuccess(appDetailBean);
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
            mDelegate.getDataError(e.getMessage());
        }
    };
}
