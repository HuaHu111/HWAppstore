package com.example.acer.myapplication.factory;



import com.example.acer.myapplication.base.BaseFragment;
import com.example.acer.myapplication.mvp.view.Fragment.AppManagerFragment;
import com.example.acer.myapplication.mvp.view.Fragment.CategoryFragment;
import com.example.acer.myapplication.mvp.view.Fragment.MyFragment;
import com.example.acer.myapplication.mvp.view.Fragment.RecommendFragment;
import com.example.acer.myapplication.mvp.view.Fragment.TopFragment;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by xzhang on 2017/5/15.
 */

public class FragmentFactory {

    /**
     * 推荐
     */
    public static final int TAB_RECOMMEND = 0;
    /**
     * 分类
     */
    public static final int TAB_CATEGORY = 1;
    /**
     * 排行
     */
    public static final int TAB_TOP = 2;
    /**
     * 管理
     */
    public static final int TAB_APPMANAGER = 3;
    /**
     * 我的
     */
    public static final int TAB_MY = 4;
    private static Map<Integer, BaseFragment> mFragments = new HashMap<Integer, BaseFragment>();

    public static BaseFragment createFragment(int index) {
        BaseFragment fragment = mFragments.get(index);
        // 如果之前没有创建, 创建新的Fragment
        if (fragment == null) {
            switch (index) {
                case TAB_RECOMMEND:
                    fragment = new RecommendFragment();
                    break;
                case TAB_CATEGORY:
                    fragment = new CategoryFragment();
                    break;
                case TAB_TOP:
                    fragment = new TopFragment();
                    break;
                case TAB_APPMANAGER:
                    fragment = new AppManagerFragment();
                    break;
                case TAB_MY:
                    fragment = new MyFragment();
                    break;
            }
            // 把创建的Fragment 存起来
            mFragments.put(index, fragment);
        }
        return fragment;

    }
}
