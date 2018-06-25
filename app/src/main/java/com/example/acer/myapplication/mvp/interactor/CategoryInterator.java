package com.example.acer.myapplication.mvp.interactor;

import com.example.acer.myapplication.api.CategoryAppi;
import com.example.acer.myapplication.api.IGetDataDelegate;
import com.example.acer.myapplication.base.BaseActivity;
import com.example.acer.myapplication.bean.CategoryBean;
import com.example.acer.myapplication.utils.JsonParseUtils;
import com.zhxu.library.http.HttpManager;
import com.zhxu.library.listener.HttpOnNextListener;

import javax.inject.Inject;

/**
 * Created by acer on 2018/6/26.
 */

public class CategoryInterator {

    private IGetDataDelegate mdelegate;

    @Inject
    public CategoryInterator(){

    }

    public void loadCategoryData(BaseActivity activity, IGetDataDelegate<CategoryBean>delegate){
        this.mdelegate=delegate;
        CategoryAppi api=new CategoryAppi(listener,activity);
        HttpManager manager=HttpManager.getInstance();
        manager.doHttpDeal(api);
    }

    private HttpOnNextListener<CategoryBean> listener=new HttpOnNextListener<CategoryBean>() {
        @Override
        public void onNext(CategoryBean categoryBean) {
            mdelegate.getDataSuccess(categoryBean);
        }

        @Override
        public void onCacheNext(String string) {
            super.onCacheNext(string);
            CategoryBean categoryBean = JsonParseUtils.parseCategoryBean(string);
            mdelegate.getDataSuccess(categoryBean);
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
            mdelegate.getDataError(e.getMessage());
        }
    };

}
