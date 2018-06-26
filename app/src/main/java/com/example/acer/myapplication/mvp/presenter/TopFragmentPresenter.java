package com.example.acer.myapplication.mvp.presenter;

import com.example.acer.myapplication.base.BaseActivity;
import com.example.acer.myapplication.base.mvpBase.BasePresenter;
import com.example.acer.myapplication.mvp.view.View.TopFragmentView;

/**
 * Created by acer on 2018/6/26.
 */

public interface TopFragmentPresenter extends BasePresenter<TopFragmentView>{


    /**
     * 获取排行数据
     * @param activity
     */
    void getTopData(BaseActivity activity);
}
