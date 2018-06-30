package com.example.acer.myapplication.mvp.view.Fragment;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.acer.myapplication.R;
import com.example.acer.myapplication.View.LoadingPager;
import com.example.acer.myapplication.adapter.section.CategorySection;
import com.example.acer.myapplication.adapter.top.CategoryTopWrapper;
import com.example.acer.myapplication.base.BaseFragment;
import com.example.acer.myapplication.base.mvpBase.BaseMvpFragment;
import com.example.acer.myapplication.bean.CategoryBean;
import com.example.acer.myapplication.mvp.presenter.impl.CategoryPresenterImpl;
import com.example.acer.myapplication.mvp.presenter.impl.RecommendPresenterImpl;
import com.example.acer.myapplication.mvp.view.View.CategoryFramentView;
import com.example.acer.myapplication.mvp.view.View.ViewUpSearch;
import com.example.acer.myapplication.utils.UIUtils;
import com.zhxu.recyclerview.section.SectionRVAdapter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by acer on 2018/6/9.
 */

public class CategoryFragment extends BaseMvpFragment<CategoryPresenterImpl> implements CategoryFramentView{

    private final static String TAG="CategoryFragment";

    @BindView(R.id.rv)
    RecyclerView rv;
    @BindView(R.id.search)
    ViewUpSearch search;

    @Inject
    public CategoryPresenterImpl categoryPresenter;

    private CategoryBean categoryBean;

    private boolean isExpand;

    @Override
    protected View creatSuccessView() {
       View view = UIUtils.inflate(R.layout.fragment_category);
        ButterKnife.bind(this,view);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));


        SectionRVAdapter adapter =new SectionRVAdapter(getContext());
        adapter.addSection(new CategorySection(getContext(),categoryBean.getTitle(),categoryBean.getCategoryDataBeanList()));


        CategoryTopWrapper topWrapper =new CategoryTopWrapper(getContext(),adapter);
        topWrapper.addData(categoryBean.getCategoryTopBeanList());
        rv.setAdapter(topWrapper);

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

        return view ;
    }

    @Override
    protected void load() {
        //网络请求操作
       categoryPresenter.getCateGoryData(mActivity);
    }

    @Override
    public void onCategoryDataSuccesss(CategoryBean categoryBean) {
        setState(LoadingPager.LoadResult.success);
        this.categoryBean=categoryBean;
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
