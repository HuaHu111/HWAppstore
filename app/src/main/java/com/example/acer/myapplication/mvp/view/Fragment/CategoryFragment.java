package com.example.acer.myapplication.mvp.view.Fragment;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.acer.myapplication.View.LoadingPager;
import com.example.acer.myapplication.base.BaseFragment;
import com.example.acer.myapplication.base.mvpBase.BaseMvpFragment;
import com.example.acer.myapplication.bean.CategoryBean;
import com.example.acer.myapplication.mvp.presenter.impl.CategoryPresenterImpl;
import com.example.acer.myapplication.mvp.presenter.impl.RecommendPresenterImpl;
import com.example.acer.myapplication.mvp.view.View.CategoryFramentView;

import javax.inject.Inject;

/**
 * Created by acer on 2018/6/9.
 */

public class CategoryFragment extends BaseMvpFragment<CategoryPresenterImpl> implements CategoryFramentView{

    private final static String TAG="CategoryFragment";

    @Inject
    public CategoryPresenterImpl categoryPresenter;

    @Override
    protected View creatSuccessView() {
        TextView textView = new TextView(getContext());
        textView.setText(getClass().getSimpleName());
        return textView;
    }

    @Override
    protected void load() {
        //网络请求操作
       categoryPresenter.getCateGoryData(mActivity);
    }

    @Override
    public void onCategoryDataSuccesss(CategoryBean categoryBean) {
        setState(LoadingPager.LoadResult.success);
        Log.i(TAG,categoryBean.getCategoryTopBeanList().size()+"");
    }

    @Override
    public void onCategoryAtaError(String msg) {
        setState(LoadingPager.LoadResult.error);
        Log.i(TAG,msg);
    }

    @Override
    protected CategoryPresenterImpl initInjector() {
        mFragmentComponent.inject(this);
        return categoryPresenter;
    }


}
