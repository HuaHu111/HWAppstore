package com.example.acer.myapplication.base.mvpBase;

/**
 * Created by acer on 2018/6/10.
 */

public class BasePresenterImpl<T extends BaseView> implements BasePresenter<T> {

   protected T mPresenterView;

    @Override
    public void attachView(T view) {
        this.mPresenterView=view;

    }

    @Override
    public void detachView() {
        this.mPresenterView=null;
    }
}
