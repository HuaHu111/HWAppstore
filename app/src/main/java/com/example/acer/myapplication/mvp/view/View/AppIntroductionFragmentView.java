package com.example.acer.myapplication.mvp.view.View;

import com.example.acer.myapplication.base.mvpBase.BaseView;
import com.example.acer.myapplication.bean.AppIntroductionBean;

/**
 * Created by acer on 2018/7/15.
 */

public interface AppIntroductionFragmentView extends BaseView{

    void onAppIntroductionDataSuccess(AppIntroductionBean appIntroductionBean);

    void onAppIntroductionDataFail(String msg);

}
