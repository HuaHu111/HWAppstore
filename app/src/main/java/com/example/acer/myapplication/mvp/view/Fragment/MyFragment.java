package com.example.acer.myapplication.mvp.view.Fragment;

import android.content.Context;
import android.os.SystemClock;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.acer.myapplication.R;
import com.example.acer.myapplication.View.LoadingPager;
import com.example.acer.myapplication.View.widge.MyEnterLayout;
import com.example.acer.myapplication.base.BaseFragment;
import com.example.acer.myapplication.base.StoreApplication;
import com.example.acer.myapplication.bean.MyGvBean;
import com.example.acer.myapplication.utils.UIUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by acer on 2018/6/9.
 */

public class MyFragment extends BaseFragment {

    @BindView(R.id.gv_title_grid)
    GridView gv_title_grid ;
    @BindView(R.id.book_game_layout)
    MyEnterLayout book_game_layout ;
    @BindView(R.id.buy_layout)
    MyEnterLayout buy_layout ;
    @BindView(R.id.huaban_layout)
    MyEnterLayout huaban_layout ;
    @BindView(R.id.setting_computer)
    MyEnterLayout setting_computer ;
    @BindView(R.id.faq_layout)
    MyEnterLayout faq_layout ;
    @BindView(R.id.check_update_layout)
    MyEnterLayout check_update_layout ;
    @BindView(R.id.about_layout)
    MyEnterLayout about_layout ;
    private List<MyGvBean> gvBeanList =new ArrayList<>();


    @Override
    protected View creatSuccessView() {
        View view = UIUtils.inflate(R.layout.fragment_my);
        ButterKnife.bind(this, view);

        gvBeanList.add(new MyGvBean(UIUtils.getString(R.string.market_prize),R.drawable.icon_market_lucky_draw));
        gvBeanList.add(new MyGvBean(UIUtils.getString(R.string.market_gift),R.drawable.ic_mine_package_normal));
        gvBeanList.add(new MyGvBean(UIUtils.getString(R.string.appzone_comments),R.drawable.icon_market_comment));
        gvBeanList.add(new MyGvBean(UIUtils.getString(R.string.market_mine_message),R.drawable.icon_market_message));
        gv_title_grid.setNumColumns(gvBeanList.size());
        GvAdapter adapter =new GvAdapter(getContext(),gvBeanList);
        gv_title_grid.setAdapter(adapter);

        book_game_layout.setTitle(UIUtils.getString(R.string.reserve_warpup_game_str));
        buy_layout.setTitle(UIUtils.getString(R.string.purchase_title));
        huaban_layout.setTitle(UIUtils.getString(R.string.mine_point_area));
        setting_computer.setTitle(UIUtils.getString(R.string.action_settings));
        faq_layout.setTitle(UIUtils.getString(R.string.menu_feedback));
        check_update_layout.setTitle(UIUtils.getString(R.string.settings_check_version_update));
        about_layout.setTitle(UIUtils.getString(R.string.about));

        return view;
    }

    public class GvAdapter extends BaseAdapter{

        private Context context;
        private List<MyGvBean>gvBeanlist;

        public GvAdapter(Context context,List<MyGvBean>gvBeanlist){
            this.context=context;
            this.gvBeanlist=gvBeanlist;
        }

        @Override
        public int getCount() {
            return gvBeanlist.size();
        }

        @Override
        public Object getItem(int i) {
            return gvBeanlist.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int position, View contentview, ViewGroup viewGroup) {
            MyGvBean myGvBean = gvBeanlist.get(position);
            ViewHoder hoder;
            if (contentview==null){
                contentview=UIUtils.inflate(R.layout.appdetail_subcat_title);
                hoder=new ViewHoder();
                contentview.setTag(hoder);
            }else {
                hoder= (ViewHoder) contentview.getTag();
            }
            hoder.ItemTitle=contentview.findViewById(R.id.ItemTitle);
            hoder.appicon=contentview.findViewById(R.id.appicon);

            hoder.ItemTitle.setText(myGvBean.getName());
            Glide.with(StoreApplication.getContext()).load(myGvBean.getIconId()).into(hoder.appicon);
            return contentview;
        }
    }

    class ViewHoder{
        public ImageView appicon;
        public TextView ItemTitle;
    }

    @Override
    protected void load() {
        setState(LoadingPager.LoadResult.success);
    }
}
