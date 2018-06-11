package com.example.acer.myapplication.mvp.presenter;

import com.example.acer.myapplication.base.mvpBase.BasePresenter;
import com.example.acer.myapplication.mvp.view.View.RecommendFragmentView;

/**
 * Created by acer on 2018/6/11.
 */

public interface RecommendFragmentPresenter extends BasePresenter<RecommendFragmentView> {

    /**
     * 获取推荐数据
     */
    void getRecommendData();
}
