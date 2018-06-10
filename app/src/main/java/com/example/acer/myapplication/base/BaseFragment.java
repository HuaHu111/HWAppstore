package com.example.acer.myapplication.base;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.acer.myapplication.R;
import com.example.acer.myapplication.View.LoadingPager;
import com.example.acer.myapplication.utils.UIUtils;

/**
 * Created by acer on 2018/6/9.
 */

public abstract class BaseFragment extends Fragment {


    private LoadingPager loadingPager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       if (loadingPager==null){
           loadingPager=new LoadingPager(getContext()) {
               @Override
               protected View creatSuccessView() {
                   return BaseFragment.this.creatSuccessView();
               }

               @Override
               protected void load() {
                    BaseFragment.this.load();
               }
           };
       }
       return loadingPager;
    }

    public void show(){
        if (loadingPager!=null){
            loadingPager.show();
        }
    }

    public void setState(LoadingPager.LoadResult result){
        if (loadingPager!=null){
            loadingPager.setState(result);
        }
    }

    /**
     * 创建成功布局
     * @return
     */
    protected abstract View creatSuccessView();

    /**
     * 网络请求
     */
    protected abstract void load() ;
}
