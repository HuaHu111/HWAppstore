package com.example.acer.myapplication.mvp.view.Fragment;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.acer.myapplication.R;
import com.example.acer.myapplication.View.LoadingPager;
import com.example.acer.myapplication.base.BaseFragment;
import com.example.acer.myapplication.base.mvpBase.BaseMvpFragment;
import com.example.acer.myapplication.mvp.presenter.impl.RecommendPresenterImpl;
import com.example.acer.myapplication.mvp.view.View.RecommendFragmentView;
import com.example.acer.myapplication.utils.UIUtils;

import javax.inject.Inject;

/**
 * Created by acer on 2018/6/9.
 */

public class RecommendFragment extends BaseMvpFragment<RecommendPresenterImpl> implements RecommendFragmentView {


    @Inject
    public RecommendPresenterImpl recommendPresenter;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        show();
    }

    @Override
    protected View creatSuccessView() {
        TextView textView = new TextView(getContext());
        textView.setText(getClass().getSimpleName());
        return textView;
    }

    @Override
    protected void load() {

        recommendPresenter.getRecommendData();
    }

    @Override
    public void onRecommendDataSuccesss() {
        setState(LoadingPager.LoadResult.success);
    }

    @Override
    public void onRecommendAtaError() {

    }

    @Override
    protected RecommendPresenterImpl initInjector() {
        //完成依赖注入
        mFragmentComponent.inject(this);
        //返回注入的inject
        return recommendPresenter;
    }
}
