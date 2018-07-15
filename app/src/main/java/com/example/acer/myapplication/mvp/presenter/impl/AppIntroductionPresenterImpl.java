package com.example.acer.myapplication.mvp.presenter.impl;

import com.example.acer.myapplication.api.IGetDataDelegate;
import com.example.acer.myapplication.base.BaseActivity;
import com.example.acer.myapplication.base.mvpBase.BasePresenterImpl;
import com.example.acer.myapplication.bean.AppIntroductionBean;
import com.example.acer.myapplication.mvp.interactor.AppIntroductionInterator;
import com.example.acer.myapplication.mvp.presenter.AppIntroductionFragmentPresenter;
import com.example.acer.myapplication.mvp.view.View.AppIntroductionFragmentView;

import javax.inject.Inject;

/**
 * Created by acer on 2018/7/15.
 */

public class AppIntroductionPresenterImpl extends BasePresenterImpl<AppIntroductionFragmentView>implements AppIntroductionFragmentPresenter {

    @Inject
    public AppIntroductionInterator appIntroductionInterator;




   @Inject
   public AppIntroductionPresenterImpl(){

   }


    @Override
    public void getAppIntroductionData(BaseActivity activity, String packageName) {
        appIntroductionInterator.loadAppIntroductionData(activity,packageName, new IGetDataDelegate<AppIntroductionBean>() {
            @Override
            public void getDataSuccess(AppIntroductionBean appIntroductionBean) {
                mPresenterView.onAppIntroductionDataSuccess(appIntroductionBean);
            }

            @Override
            public void getDataError(String errmsg) {
                mPresenterView.onAppIntroductionDataFail(errmsg);
            }
        });
    }
}
