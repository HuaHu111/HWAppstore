package com.example.acer.myapplication.mvp.view.View;

import com.example.acer.myapplication.base.mvpBase.BaseView;
import com.example.acer.myapplication.bean.TopBean;

/**
 * Created by acer on 2018/6/26.
 */

public interface TopFragmentView extends BaseView {

    void onTopDataSuccess(TopBean topBean);
    void onTopDataError(String msg);

}
