package com.example.acer.myapplication.mvp.view.Fragment;

import android.os.SystemClock;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.acer.myapplication.R;
import com.example.acer.myapplication.View.LoadingPager;
import com.example.acer.myapplication.adapter.top.TopSection;
import com.example.acer.myapplication.adapter.top.TopTopWrapper;
import com.example.acer.myapplication.base.BaseFragment;
import com.example.acer.myapplication.base.mvpBase.BaseMvpFragment;
import com.example.acer.myapplication.bean.AppBean;
import com.example.acer.myapplication.bean.TopBean;
import com.example.acer.myapplication.mvp.presenter.impl.TopPresenterImpl;
import com.example.acer.myapplication.mvp.view.View.TopFragmentView;
import com.example.acer.myapplication.mvp.view.View.ViewUpSearch;
import com.example.acer.myapplication.utils.UIUtils;
import com.zhxu.recyclerview.section.Section;
import com.zhxu.recyclerview.section.SectionRVAdapter;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by acer on 2018/6/9.
 */

public class TopFragment extends BaseMvpFragment<TopPresenterImpl> implements TopFragmentView {

    @Inject
    public TopPresenterImpl topPresenter;
    private final String TAG="TopFragment";

    private TopBean topBean;

    @BindView(R.id.rv)
    RecyclerView rv;
    @BindView(R.id.search)
    ViewUpSearch search;
    private boolean isExpand;


    @Override
    protected View creatSuccessView() {
       View view= UIUtils.inflate(R.layout.fragment_top);
        ButterKnife.bind(this,view);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));

        SectionRVAdapter adapter =new SectionRVAdapter(getContext());
        Map<String, List<AppBean>> appBeanMap = topBean.getAppBeanMap();
        Set<String> strings = appBeanMap.keySet();
        for (String key:strings){
            List<AppBean> appBeanlist = appBeanMap.get(key);
            adapter.addSection(new TopSection(getContext(),key,appBeanlist));
        }

        TopTopWrapper topTopWrapper =new TopTopWrapper(getContext(),adapter);
        topTopWrapper.addData(topBean.getTopTopBeanList());
        rv.setAdapter(topTopWrapper);


        rv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int firstVisibleItemPosition = ((LinearLayoutManager) rv.getLayoutManager()).findFirstVisibleItemPosition();
                //dy>0上啦
                if (firstVisibleItemPosition==0&&dy>0&&isExpand){
                    search.updateShow(!isExpand);
                    isExpand=false;
                }else if (firstVisibleItemPosition==0&&!isExpand){
                    search.updateShow(!isExpand);
                    isExpand=true;
                }
            }
        });
        return view;
    }

    @Override
    protected void load() {
        //网络请求操作
       topPresenter.getTopData(mActivity);
    }

    @Override
    public void onTopDataSuccess(TopBean topBean) {
        this.topBean=topBean;
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
