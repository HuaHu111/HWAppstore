package com.example.acer.myapplication.mvp.presenter.impl;

import com.example.acer.myapplication.api.IGetDataDelegate;
import com.example.acer.myapplication.base.BaseActivity;
import com.example.acer.myapplication.base.mvpBase.BasePresenterImpl;
import com.example.acer.myapplication.bean.CategoryBean;
import com.example.acer.myapplication.mvp.interactor.CategoryInterator;
import com.example.acer.myapplication.mvp.presenter.CategoryFragmentPrester;
import com.example.acer.myapplication.mvp.view.View.CategoryFramentView;

import javax.inject.Inject;

/**
 * Created by acer on 2018/6/25.
 */

public class CategoryPresenterImpl extends BasePresenterImpl<CategoryFramentView>  implements CategoryFragmentPrester{

    @Inject
    public CategoryInterator categoryInterator;


    @Inject
    public CategoryPresenterImpl(){

    }


    @Override
    public void getCateGoryData(BaseActivity activity) {
        categoryInterator.loadCategoryData(activity, new IGetDataDelegate<CategoryBean>() {
            @Override
            public void getDataSuccess(CategoryBean categoryBean) {
                mPresenterView.onCategoryDataSuccesss(categoryBean);
            }

            @Override
            public void getDataError(String errmsg) {
                mPresenterView.onCategoryAtaError(errmsg);
            }
        });
    }
}
