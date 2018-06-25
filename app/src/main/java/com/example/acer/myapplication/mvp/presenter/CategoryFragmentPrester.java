package com.example.acer.myapplication.mvp.presenter;

import com.example.acer.myapplication.base.BaseActivity;
import com.example.acer.myapplication.base.mvpBase.BasePresenter;
import com.example.acer.myapplication.mvp.view.View.CategoryFramentView;

/**
 * Created by acer on 2018/6/25.
 */

public interface CategoryFragmentPrester extends BasePresenter<CategoryFramentView> {

    void getCateGoryData(BaseActivity activity);
}
