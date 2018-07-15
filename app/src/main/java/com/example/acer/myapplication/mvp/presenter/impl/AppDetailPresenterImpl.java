package com.example.acer.myapplication.mvp.presenter.impl;

import com.example.acer.myapplication.api.IGetDataDelegate;
import com.example.acer.myapplication.base.BaseActivity;
import com.example.acer.myapplication.base.mvpBase.BasePresenter;
import com.example.acer.myapplication.base.mvpBase.BasePresenterImpl;
import com.example.acer.myapplication.bean.AppDetailBean;
import com.example.acer.myapplication.mvp.interactor.AppDetailInteractor;
import com.example.acer.myapplication.mvp.presenter.AppDetailPresenter;
import com.example.acer.myapplication.mvp.view.View.AppDetialActivityView;

import javax.inject.Inject;

/**
 * Created by acer on 2018/7/1.
 */

public class AppDetailPresenterImpl extends BasePresenterImpl<AppDetialActivityView> implements AppDetailPresenter{

    @Inject
    public AppDetailInteractor appDetailInteractor;

    @Inject
    public AppDetailPresenterImpl(){

    }

    @Override
    public void getAppDetail(BaseActivity activity,String packageName) {
        appDetailInteractor.loadAppDetailData(activity, packageName, new IGetDataDelegate<AppDetailBean>() {
            @Override
            public void getDataSuccess(AppDetailBean appDetailBean) {
                mPresenterView.onAppDetailDataSuccess(appDetailBean);
            }

            @Override
            public void getDataError(String errmsg) {
                mPresenterView.onAppDetailDataFail(errmsg);
            }
        });
    }
}
