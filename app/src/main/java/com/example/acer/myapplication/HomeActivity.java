package com.example.acer.myapplication;

import android.os.Build;
import android.os.Bundle;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.acer.myapplication.adapter.FixPagerAdapter;
import com.example.acer.myapplication.base.BaseActivity;
import com.example.acer.myapplication.base.BaseFragment;
import com.example.acer.myapplication.factory.FragmentFactory;
import com.example.acer.myapplication.fragment.RecommendFragment;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by acer on 2018/6/9.
 */

public class HomeActivity extends BaseActivity {

    @BindView(R.id.status_bar)
    LinearLayout statusBar;
    @BindView(R.id.tab_layout)
    android.support.design.widget.TabLayout tabLayout;
    @BindView(R.id.tab)
    LinearLayout tab;
    @BindView(R.id.main_viewpager)
    ViewPager mainViewpager;

    private FixPagerAdapter fixPagerAdapter;
    private String[] title={"推荐","分类","排行","管理","我的"};
    private ArrayList<Fragment> fragments;

    @Override
    public void initlayout() {
        setContentView(R.layout.activity_home);
    }

    @Override
    public void initview() {
        super.initview();
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.KITKAT){
            final int statusBarHeight = getStatusBarHeight();
            statusBar.post(new Runnable() {
                @Override
                public void run() {
                    int height = statusBar.getHeight();
                    ViewGroup.LayoutParams layoutParams = statusBar.getLayoutParams();
                    layoutParams.height= statusBarHeight+height;
                    statusBar.setLayoutParams(layoutParams);
                }
            });
        }
        initviewAPgerFragment();
    }

    private void initviewAPgerFragment() {
        fixPagerAdapter = new FixPagerAdapter(getSupportFragmentManager());
        fragments = new ArrayList<>();
        for (int i = 0; i <title.length; i++) {
            fragments.add(FragmentFactory.createFragment(i));
        }
        fixPagerAdapter.setTitles(title);
        fixPagerAdapter.setFragments(fragments);

        mainViewpager.setAdapter(fixPagerAdapter);
        tabLayout.setupWithViewPager(mainViewpager);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);

        mainViewpager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener(){
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                if (position==0){
                    RecommendFragment fragment = (RecommendFragment) FragmentFactory.createFragment(0);
                    fragment.show();
                }
            }
        });


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
