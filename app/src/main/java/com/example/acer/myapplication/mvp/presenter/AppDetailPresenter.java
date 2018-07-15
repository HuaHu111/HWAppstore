package com.example.acer.myapplication.mvp.presenter;

import com.example.acer.myapplication.base.BaseActivity;
import com.example.acer.myapplication.base.mvpBase.BasePresenter;
import com.example.acer.myapplication.mvp.view.View.AppDetialActivityView;

/**
 * Created by acer on 2018/7/1.
 */

public interface AppDetailPresenter extends BasePresenter<AppDetialActivityView> {

    void getAppDetail(BaseActivity activity,String packagename);

}
