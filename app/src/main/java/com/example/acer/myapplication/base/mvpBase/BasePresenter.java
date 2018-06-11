package com.example.acer.myapplication.base.mvpBase;

/**
 * 该类是所有Presenter接口的基类
 * Created by acer on 2018/6/10.
 */

public interface BasePresenter<T extends BaseView> {
    void attachView(T view);
    void detachView();
}
