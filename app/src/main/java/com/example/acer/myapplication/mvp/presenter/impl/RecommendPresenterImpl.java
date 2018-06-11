package com.example.acer.myapplication.mvp.presenter.impl;

import android.os.SystemClock;

import com.example.acer.myapplication.base.mvpBase.BasePresenterImpl;
import com.example.acer.myapplication.mvp.presenter.RecommendFragmentPresenter;
import com.example.acer.myapplication.mvp.view.View.RecommendFragmentView;

import javax.inject.Inject;

/**
 * Created by acer on 2018/6/11.
 */

public class RecommendPresenterImpl extends BasePresenterImpl<RecommendFragmentView> implements RecommendFragmentPresenter{


    @Inject
    public RecommendPresenterImpl(){

    }

    @Override
    public void getRecommendData() {
//        网络请求操作
        new Thread(new Runnable() {
            @Override
            public void run() {
                SystemClock.sleep(2000);
               mPresenterView.onRecommendDataSuccesss();
            }
        }).start();
    }
}
