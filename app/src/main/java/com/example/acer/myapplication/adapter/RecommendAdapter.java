package com.example.acer.myapplication.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.acer.myapplication.R;
import com.example.acer.myapplication.bean.AppBean;
import com.example.acer.myapplication.bean.RecommendBean;
import com.zhxu.recyclerview.adapter.CommonAdapter;
import com.zhxu.recyclerview.adapter.MultiItemTypeAdapter;
import com.zhxu.recyclerview.base.ItemViewDelegate;
import com.zhxu.recyclerview.base.ViewHolder;

import java.util.List;

/**
 * Created by acer on 2018/6/28.
 */

public class RecommendAdapter extends MultiItemTypeAdapter<RecommendBean.RecommendAppBean>{

    private Context mContext;

    public RecommendAdapter(Context context, List<RecommendBean.RecommendAppBean> datas) {
        super(context, datas);

        this.mContext=context;
        //添加应用条目
        addItemViewDelegate(new AppDelegate());
        //添加广告条目
        addItemViewDelegate(new AdDelegate());
    }


    /**
     * App列表的条目
     */
    public class AppDelegate implements ItemViewDelegate<RecommendBean.RecommendAppBean>{



        @Override
        public int getItemViewLayoutId() {
            return R.layout.item_applist_horizontal;
        }

        @Override
        public boolean isForViewType(RecommendBean.RecommendAppBean item, int position) {
            return item.getType()==0;
        }

        @Override
        public void convert(ViewHolder holder, RecommendBean.RecommendAppBean recommendAppBean, int position) {
            holder.setText(R.id.tv_item_title,recommendAppBean.getTitle());
            RecyclerView rv=holder.getView(R.id.rv_applist_item);
            LinearLayoutManager linearLayoutManager=new LinearLayoutManager(rv.getContext());
            linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            rv.setLayoutManager(linearLayoutManager);
            AppItemAdapter adapter = new AppItemAdapter(mContext);
            adapter.addDataAll(recommendAppBean.getAppList());
            rv.setAdapter(adapter);

        }
    }


    /**
     * 两张图片的广告条目
     */
    public class AdDelegate implements ItemViewDelegate<RecommendBean.RecommendAppBean>{

        @Override
        public int getItemViewLayoutId() {
            return R.layout.item_ad;
        }

        @Override
        public boolean isForViewType(RecommendBean.RecommendAppBean item, int position) {
            return item.getType()==1;
        }

        @Override
        public void convert(ViewHolder holder, RecommendBean.RecommendAppBean recommendAppBean, int position) {
            holder.setImageUrl(R.id.iv_ad1,recommendAppBean.getIconList().get(0));
            holder.setImageUrl(R.id.iv_ad2,recommendAppBean.getIconList().get(1));
        }
    }



    public class AppItemAdapter extends CommonAdapter<AppBean>{

        public AppItemAdapter(Context context) {
            super(context, R.layout.item_app);
        }

        @Override
        protected void convert(ViewHolder holder, AppBean appBean, int position) {
            holder.setImageUrl(R.id.iv_app_icon,appBean.getIcon());
            holder.setText(R.id.tv_app_name,appBean.getName());
            holder.setText(R.id.tv_app_size,appBean.getSizeDesc());
        }
    }
}

