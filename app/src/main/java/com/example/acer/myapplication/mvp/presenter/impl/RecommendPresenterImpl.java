package com.example.acer.myapplication.mvp.presenter.impl;

import android.os.SystemClock;

import com.example.acer.myapplication.api.IGetDataDelegate;
import com.example.acer.myapplication.base.BaseActivity;
import com.example.acer.myapplication.base.mvpBase.BasePresenterImpl;
import com.example.acer.myapplication.bean.RecommendBean;
import com.example.acer.myapplication.mvp.interactor.RecommendInterator;
import com.example.acer.myapplication.mvp.presenter.RecommendFragmentPresenter;
import com.example.acer.myapplication.mvp.view.View.RecommendFragmentView;

import javax.inject.Inject;

/**
 * Created by acer on 2018/6/11.
 */

public class RecommendPresenterImpl extends BasePresenterImpl<RecommendFragmentView> implements RecommendFragmentPresenter{

    @Inject
    RecommendInterator recommendInterator;


    @Inject
    public RecommendPresenterImpl(){

    }

    @Override
    public void getRecommendData(BaseActivity activity) {
//        网络请求操作
        recommendInterator.loadRecommendData(activity, new IGetDataDelegate<RecommendBean>() {
            @Override
            public void getDataSuccess(RecommendBean recommendBean) {
                mPresenterView.onRecommendDataSuccesss(recommendBean);
            }

            @Override
            public void getDataError(String errmsg) {
                mPresenterView.onRecommendAtaError(errmsg);
            }
        });

    }
}
