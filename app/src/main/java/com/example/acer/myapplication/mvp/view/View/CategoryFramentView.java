package com.example.acer.myapplication.mvp.view.View;

import com.example.acer.myapplication.base.mvpBase.BaseView;
import com.example.acer.myapplication.bean.CategoryBean;

/**
 * Created by acer on 2018/6/25.
 */

public interface CategoryFramentView extends BaseView{

    void onCategoryDataSuccesss(CategoryBean categoryBean);

    void onCategoryAtaError(String msg);
}
