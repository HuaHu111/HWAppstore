package com.example.acer.myapplication.di.compoment;

import android.app.Activity;
import android.content.Context;

import com.example.acer.myapplication.di.module.FragmentModule;
import com.example.acer.myapplication.di.scope.ContextLife;
import com.example.acer.myapplication.di.scope.PerFragment;
import com.example.acer.myapplication.mvp.view.Fragment.CategoryFragment;
import com.example.acer.myapplication.mvp.view.Fragment.RecommendFragment;
import com.example.acer.myapplication.mvp.view.Fragment.TopFragment;

import dagger.Component;

/**
 * Created by acer on 2018/6/10.
 */
@PerFragment
@Component(modules = FragmentModule.class,dependencies = AppCompoment.class)
public interface FragmentCompoment {

    @ContextLife("Activity")
    Context getActivityContext();

    @ContextLife("Application")
    Context getApplicationLife();

    Activity getActivity();

    void inject(RecommendFragment fragment);
    void inject(CategoryFragment fragment);
    void inject(TopFragment fragment);

}
