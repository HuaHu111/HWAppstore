package com.example.acer.myapplication.fragment;

import android.os.SystemClock;
import android.view.View;
import android.widget.TextView;

import com.example.acer.myapplication.base.BaseFragment;

/**
 * Created by acer on 2018/6/9.
 */

public class AppManagerFragment extends BaseFragment {


    @Override
    protected View creatSuccessView() {
        TextView textView = new TextView(getContext());
        textView.setText(getClass().getSimpleName());
        return textView;
    }

    @Override
    protected void load() {
        //网络请求操作
        new Thread(new Runnable() {
            @Override
            public void run() {
                SystemClock.sleep(2000);
                setState(LoadResult.empty);
            }
        }).start();
    }
}
