package com.example.acer.myapplication.mvp.presenter;

import com.example.acer.myapplication.base.BaseActivity;
import com.example.acer.myapplication.base.mvpBase.BasePresenter;
import com.example.acer.myapplication.mvp.view.View.AppIntroductionFragmentView;

/**
 * Created by acer on 2018/7/15.
 */

public interface AppIntroductionFragmentPresenter extends BasePresenter<AppIntroductionFragmentView>{

    void getAppIntroductionData(BaseActivity activity,String packageName);

}
