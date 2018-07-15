package com.example.acer.myapplication.mvp.view.View;

import com.example.acer.myapplication.base.mvpBase.BaseView;
import com.example.acer.myapplication.bean.AppDetailBean;

/**
 * Created by acer on 2018/7/1.
 */

public interface AppDetialActivityView extends BaseView {

    void onAppDetailDataSuccess(AppDetailBean appDetailBean);
    void onAppDetailDataFail(String msg);
}
