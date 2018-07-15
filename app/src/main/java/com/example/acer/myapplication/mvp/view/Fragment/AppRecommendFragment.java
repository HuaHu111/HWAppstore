package com.example.acer.myapplication.mvp.view.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;



import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * <p>Description:
 *
 * @author xzhang
 */

public class AppRecommendFragment extends Fragment {
//
//    @BindView(R.id.rv)
//    RecyclerView rv ;
//
//    @Inject
//    AppRecommendFragmentPresenterImpl appRecommendFragmentPresenter ;
//
//    private String packageName ;
//
//    private AppRecommendBean appRecommendBean ;
//
//    @Override
//    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//        packageName = ((AppDetailActivity)getActivity()).getAppPackageName();
//        show();
//    }
//
//    @Override
//    public void onAppRecommendDataSuccess(AppRecommendBean appRecommendBean) {
//        this.appRecommendBean = appRecommendBean ;
//        setState(MultipleStatusView.LoadResult.success);
//    }
//
//    @Override
//    public void onAppRecommendDataError(String msg) {
//        setState(MultipleStatusView.LoadResult.error);
//    }
//
//    @Override
//    protected AppRecommendFragmentPresenterImpl initInjector() {
//        mFragmentComponent.inject(this);
//        return appRecommendFragmentPresenter;
//    }
//
//    @Override
//    public void initView() {
//        SectionRVAdapter adapter = new SectionRVAdapter(getContext());
//        adapter.addSection(new AppRecommendPopularSection(getContext(),"流行应用",appRecommendBean.getPopularAppBeanList(),packageName));
//        adapter.addSection(new AppRecommendTasteSection(getContext(),"兴趣相近的用户也安装了",appRecommendBean.getTasteAppBeanList(),packageName));
//        adapter.addSection(new AppRecommendHotSection(getContext(),"本周热议的社区应用",appRecommendBean.getHotAppBeanList(),packageName));
//        rv.setAdapter(adapter);
//        rv.setLayoutManager(new LinearLayoutManager(getContext()));
//    }
//
//    @Override
//    public View createSuccessView() {
//        View view = UIUtils.inflate(R.layout.fragment_app_recommend);
//        ButterKnife.bind(this,view);
//
//
//
//
//        return view;
//    }
//
//    @Override
//    public void load() {
//        appRecommendFragmentPresenter.getAppRecommendData(mActivity,packageName);
//    }
}
