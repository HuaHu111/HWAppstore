package com.example.acer.myapplication.adapter.section;

import android.content.Context;
import android.view.View;

import com.example.acer.myapplication.R;
import com.example.acer.myapplication.bean.CategoryBean;
import com.zhxu.recyclerview.base.ViewHolder;
import com.zhxu.recyclerview.section.StatelessSection;

import java.util.List;

/**
 * Created by acer on 2018/6/30.
 */

public class CategorySection extends StatelessSection{

    private List<CategoryBean.CategoryDataBean> dataBeanList;
    private Context context;
    private String title;
    public CategorySection(Context context,String title,List<CategoryBean.CategoryDataBean> dataBeanList) {
        super(R.layout.applistitem_titlecard,R.layout.applistitem_subcat);
        this.dataBeanList=dataBeanList;
        this.title=title;
        this.context=context;
    }

    @Override
    public int getContentItemsTotal() {
        return dataBeanList.size();
    }

    @Override
    public ViewHolder getItemViewHolder(View view, int viewType) {
        return new ViewHolder(context,view);
    }

    @Override
    public void onBindItemViewHolder(ViewHolder holder, int position) {
        CategoryBean.CategoryDataBean categoryDataBean = dataBeanList.get(position);
        holder.setImageUrl(R.id.appicon,categoryDataBean.getIconUrl());
        holder.setText(R.id.ItemTitle,categoryDataBean.getName());
    }

    @Override
    public ViewHolder getHeaderViewHolder(Context context, View view) {
        return new ViewHolder(context,view);
    }

    @Override
    public void onBindHeaderViewHolder(ViewHolder holder) {
        super.onBindHeaderViewHolder(holder);
        holder.setText(R.id.ItemTitle,title);
    }
}
