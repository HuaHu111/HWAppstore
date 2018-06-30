package com.example.acer.myapplication.adapter.top;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import com.example.acer.myapplication.banner.RecommendController;
import com.zhxu.recyclerview.wrapper.HeaderAndFooterWrapper;

import java.util.List;

/**
 * Created by acer on 2018/6/30.
 */

public class RecommendTopWrapper extends HeaderAndFooterWrapper {

    private RecommendController controller;

    public RecommendTopWrapper(Context context,RecyclerView.Adapter adapter) {
        super(adapter);
        controller=new RecommendController(context);
        addHeaderView(controller.getContentView());
    }


    public void addData(List<String> urls){
        if (controller!=null){
            controller.setData(urls);
        }
    }

}
