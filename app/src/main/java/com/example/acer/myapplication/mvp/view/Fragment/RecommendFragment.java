package com.example.acer.myapplication.mvp.view.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.acer.myapplication.R;
import com.example.acer.myapplication.View.LoadingPager;
import com.example.acer.myapplication.adapter.RecommendAdapter;
import com.example.acer.myapplication.base.mvpBase.BaseMvpFragment;
import com.example.acer.myapplication.bean.RecommendBean;
import com.example.acer.myapplication.mvp.presenter.impl.RecommendPresenterImpl;
import com.example.acer.myapplication.mvp.view.View.RecommendFragmentView;
import com.example.acer.myapplication.utils.UIUtils;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by acer on 2018/6/9.
 */

public class RecommendFragment extends BaseMvpFragment<RecommendPresenterImpl> implements RecommendFragmentView {


    private static final String TAG = "RMB";

    RecyclerView rv;

    private RecommendBean recommendBean;

    @Inject
    public RecommendPresenterImpl recommendPresenter;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        show();
    }

    @Override
    protected View creatSuccessView() {
        View view = UIUtils.inflate(R.layout.fragment_app_recommend);
        rv=view.findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        RecommendAdapter adapter = new RecommendAdapter(getContext(), recommendBean.getRecommendAppBeanList());
        rv.setAdapter(adapter);
        return view;
    }

    @Override
    protected void load() {
        recommendPresenter.getRecommendData(mActivity);
    }

    @Override
    public void onRecommendDataSuccesss(RecommendBean recommendBean) {
        Log.i(TAG, recommendBean.getBannerList().size() + "个");
        setState(LoadingPager.LoadResult.success);
        this.recommendBean = recommendBean;
    }

    @Override
    public void onRecommendAtaError(String msg) {
        Log.i(TAG, msg);
    }

    @Override
    protected RecommendPresenterImpl initInjector() {
        //完成依赖注入
        mFragmentComponent.inject(this);
        //返回注入的inject
        return recommendPresenter;
    }


}
