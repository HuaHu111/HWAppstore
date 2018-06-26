package com.example.acer.myapplication.mvp.view.Fragment;

import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.acer.myapplication.View.LoadingPager;
import com.example.acer.myapplication.base.BaseFragment;
import com.example.acer.myapplication.base.mvpBase.BaseMvpFragment;
import com.example.acer.myapplication.bean.TopBean;
import com.example.acer.myapplication.mvp.presenter.impl.TopPresenterImpl;
import com.example.acer.myapplication.mvp.view.View.TopFragmentView;

import javax.inject.Inject;

/**
 * Created by acer on 2018/6/9.
 */

public class TopFragment extends BaseMvpFragment<TopPresenterImpl> implements TopFragmentView {

    @Inject
    public TopPresenterImpl topPresenter;
    private final String TAG="TopFragment";

    @Override
    protected View creatSuccessView() {
        TextView textView = new TextView(getContext());
        textView.setText(getClass().getSimpleName());
        return textView;
    }

    @Override
    protected void load() {
        //网络请求操作
       topPresenter.getTopData(mActivity);
    }

    @Override
    public void onTopDataSuccess(TopBean topBean) {
        Log.i(TAG,""+topBean.getTopTopBeanList().size());
        setState(LoadingPager.LoadResult.success);
    }

    @Override
    public void onTopDataError(String msg) {
        setState(LoadingPager.LoadResult.error);
    }

    @Override
    protected TopPresenterImpl initInjector() {
        mFragmentComponent.inject(this);
        return topPresenter;
    }
}
