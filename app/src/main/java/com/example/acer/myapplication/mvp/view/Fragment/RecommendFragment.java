package com.example.acer.myapplication.mvp.view.Fragment;

import android.content.Intent;
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
import com.example.acer.myapplication.adapter.top.RecommendTopWrapper;
import com.example.acer.myapplication.base.mvpBase.BaseMvpFragment;
import com.example.acer.myapplication.bean.RecommendBean;
import com.example.acer.myapplication.mvp.presenter.impl.RecommendPresenterImpl;
import com.example.acer.myapplication.mvp.view.Activity.AppDetailActivity;
import com.example.acer.myapplication.mvp.view.View.RecommendFragmentView;
import com.example.acer.myapplication.utils.UIUtils;
import com.zhxu.recyclerview.pullrefresh.PullToRefreshView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by acer on 2018/6/9.
 */

public class RecommendFragment extends BaseMvpFragment<RecommendPresenterImpl> implements RecommendFragmentView {


    private static final String TAG = "RMB";

    @BindView(R.id.rv_recommend)
    RecyclerView rv;
    @BindView(R.id.ptr)
    PullToRefreshView ptr;

    private RecommendBean recommendBean;

    @Inject
    public RecommendPresenterImpl recommendPresenter;

    private List<RecommendBean.RecommendAppBean> appBeanList=new ArrayList<>();
    private RecommendAdapter adapter;
    private RecommendTopWrapper topWrapper;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        show();
    }

    @Override
    protected View creatSuccessView() {
        View view = UIUtils.inflate(R.layout.fragment_recommend);
        ButterKnife.bind(this,view);

        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new RecommendAdapter(getContext(), recommendBean.getRecommendAppBeanList());

        topWrapper = new RecommendTopWrapper(getContext(), adapter);
        topWrapper.addData(recommendBean.getBannerList());
        rv.setAdapter(topWrapper);


        ptr.setPullDownEnable(false);
        ptr.setListener(new PullToRefreshView.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //下啦刷新
            }

            @Override
            public void onLoadMore() {
                //上啦加载更多
                recommendPresenter.getMoreRecommendData(mActivity);
            }
        });

        adapter.setAppItemClickListener(new RecommendAdapter.AppItemClickListener() {
            @Override
            public void goAppDetail(String packageName) {
               Intent intent=new Intent(mActivity,AppDetailActivity.class);
                intent.putExtra("packageName",packageName);
                mActivity.startActivity(intent);
            }
        });

        return view;
    }

    @Override
    protected void load() {
        recommendPresenter.getRecommendData(mActivity);
    }

    @Override
    public void onRecommendDataSuccesss(RecommendBean recommendBean) {
        setState(LoadingPager.LoadResult.success);
        this.recommendBean = recommendBean;
    }

    @Override
    public void onMoreRecommendData(RecommendBean recommendBean) {
        appBeanList.addAll(recommendBean.getRecommendAppBeanList());
        //将原先的数据情况
        adapter.clearData();
        //添加新的数据
        adapter.addDataAll(appBeanList);
        //刷新数据
        topWrapper.notifyDataSetChanged();
        ptr.onFinishLoading();
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
