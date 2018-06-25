package com.example.acer.myapplication.mvp.interactor;

import com.example.acer.myapplication.api.IGetDataDelegate;
import com.example.acer.myapplication.api.RecommendApi;
import com.example.acer.myapplication.base.BaseActivity;
import com.example.acer.myapplication.bean.RecommendBean;
import com.example.acer.myapplication.utils.JsonParseUtils;
import com.zhxu.library.http.HttpManager;
import com.zhxu.library.listener.HttpOnNextListener;

import javax.inject.Inject;

/**
 * Created by acer on 2018/6/25.
 */

public class RecommendInterator  {

    private IGetDataDelegate<RecommendBean> recommendBeanIGetDataDelegate;

    @Inject
    public RecommendInterator(){

    }


    /**
     * 执行网络请求
     */

    public void loadRecommendData(BaseActivity activity, IGetDataDelegate<RecommendBean> iGetDataDelegate){
    this.recommendBeanIGetDataDelegate=iGetDataDelegate;
      RecommendApi api=  new RecommendApi(listener,activity);
        HttpManager manager=HttpManager.getInstance();
        manager.doHttpDeal(api);
    }

    private HttpOnNextListener<RecommendBean> listener=new HttpOnNextListener<RecommendBean>() {
        @Override
        public void onNext(RecommendBean recommendBean) {
                recommendBeanIGetDataDelegate.getDataSuccess(recommendBean);
        }

        @Override
        public void onCacheNext(String string) {
            super.onCacheNext(string);
            RecommendBean recommendBean = JsonParseUtils.parseRecommendBean(string);
            recommendBeanIGetDataDelegate.getDataSuccess(recommendBean);
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
            recommendBeanIGetDataDelegate.getDataError(e.getMessage());
        }
    };
}
