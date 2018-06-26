package com.example.acer.myapplication.mvp.presenter.impl;

import com.example.acer.myapplication.api.IGetDataDelegate;
import com.example.acer.myapplication.base.BaseActivity;
import com.example.acer.myapplication.base.mvpBase.BasePresenterImpl;
import com.example.acer.myapplication.bean.TopBean;
import com.example.acer.myapplication.mvp.interactor.TopInterator;
import com.example.acer.myapplication.mvp.presenter.TopFragmentPresenter;
import com.example.acer.myapplication.mvp.view.View.TopFragmentView;

import javax.inject.Inject;

/**
 * Created by acer on 2018/6/26.
 */

public class TopPresenterImpl extends BasePresenterImpl<TopFragmentView>implements TopFragmentPresenter{

    @Inject
    public TopInterator topInterator;


    @Inject
    public TopPresenterImpl(){

    }

    @Override
    public void getTopData(BaseActivity activity) {
            topInterator.loadTopData(activity, new IGetDataDelegate<TopBean>() {
                @Override
                public void getDataSuccess(TopBean topBean) {
                    mPresenterView.onTopDataSuccess(topBean);
                }

                @Override
                public void getDataError(String errmsg) {
                    mPresenterView.onTopDataError(errmsg);
                }
            });
    }

    public TopInterator getTopInterator() {
        return topInterator;
    }
}
