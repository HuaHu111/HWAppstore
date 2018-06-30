package com.example.acer.myapplication.adapter.top;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.acer.myapplication.R;
import com.example.acer.myapplication.base.StoreApplication;
import com.example.acer.myapplication.bean.CategoryBean;
import com.example.acer.myapplication.bean.TopBean;
import com.example.acer.myapplication.utils.UIUtils;
import com.zhxu.recyclerview.wrapper.HeaderAndFooterWrapper;

import java.util.List;

/**
 * Created by acer on 2018/6/30.
 */

public class TopTopWrapper extends HeaderAndFooterWrapper {


    private final GridView gridView;
    private Context context;

    public TopTopWrapper(Context context, RecyclerView.Adapter adapter) {
        super(adapter);
        this.context=context;
        View view = UIUtils.inflate(R.layout.header_top);
        gridView = view.findViewById(R.id.gv_title_grid);
        addHeaderView(view);

    }

    public void addData(List<TopBean.TopTopBean> topBeanList){
        gridView.setNumColumns(topBeanList.size());
        GridApter adapter=new GridApter(context,topBeanList);
        gridView.setAdapter(adapter);
    }

    public class GridApter extends BaseAdapter{

        private Context mContext;
        private List<TopBean.TopTopBean> topBeanList;
        public GridApter (Context context, List<TopBean.TopTopBean> topBeanList){
            this.mContext=context;
            this.topBeanList=topBeanList;
        }

        @Override
        public int getCount() {
            return topBeanList.size();
        }

        @Override
        public Object getItem(int i) {
            return topBeanList.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int position, View contentview, ViewGroup viewGroup) {
            ViewHolder holder;
            TopBean.TopTopBean topTopBean = topBeanList.get(position);
            if (contentview==null){
                contentview = UIUtils.inflate(R.layout.appdetail_subcat_title);
                holder=new ViewHolder();
                contentview.setTag(holder);
            }else {
                holder= (ViewHolder) contentview.getTag();
            }
            holder.appicon=contentview.findViewById(R.id.appicon);
            holder.ItemTItle=contentview.findViewById(R.id.ItemTitle);

            holder.ItemTItle.setText(topTopBean.getName());
            Glide.with(StoreApplication.getContext()).load(topTopBean.getIconUrl()).into(holder.appicon);

            return contentview;
        }



        public class ViewHolder{
            private ImageView appicon;
            private TextView ItemTItle;
        }
    }
}
