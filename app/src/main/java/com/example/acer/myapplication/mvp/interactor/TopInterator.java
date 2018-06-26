package com.example.acer.myapplication.mvp.interactor;

import com.example.acer.myapplication.api.IGetDataDelegate;
import com.example.acer.myapplication.api.TopApi;
import com.example.acer.myapplication.base.BaseActivity;
import com.example.acer.myapplication.bean.TopBean;
import com.example.acer.myapplication.utils.JsonParseUtils;
import com.zhxu.library.http.HttpManager;
import com.zhxu.library.listener.HttpOnNextListener;

import javax.inject.Inject;

/**
 * Created by acer on 2018/6/26.
 */

public class TopInterator {

    private IGetDataDelegate<TopBean> mDelegate;

    @Inject
    public TopInterator (){

    }

    public void loadTopData(BaseActivity activity, IGetDataDelegate<TopBean> delegate){
        this.mDelegate=delegate;
        TopApi topApi = new TopApi(listener, activity);
        HttpManager manager=HttpManager.getInstance();
        manager.doHttpDeal(topApi);
    }


    private HttpOnNextListener<TopBean> listener=new HttpOnNextListener<TopBean>() {
        @Override
        public void onNext(TopBean topBean) {
            mDelegate.getDataSuccess(topBean);
        }

        @Override
        public void onCacheNext(String string) {
            super.onCacheNext(string);
            TopBean topBean= JsonParseUtils.parseTopBean(string);
            mDelegate.getDataSuccess(topBean);
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
            mDelegate.getDataError(e.getMessage());
        }
    };


}
