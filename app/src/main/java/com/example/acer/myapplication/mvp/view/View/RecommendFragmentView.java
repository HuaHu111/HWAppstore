package com.example.acer.myapplication.mvp.view.View;

import com.example.acer.myapplication.base.mvpBase.BaseView;
import com.example.acer.myapplication.bean.RecommendBean;

/**
 * Created by acer on 2018/6/11.
 */

public interface RecommendFragmentView extends BaseView
{


    void onRecommendDataSuccesss(RecommendBean recommendBean);

    void onRecommendAtaError(String msg);
}
