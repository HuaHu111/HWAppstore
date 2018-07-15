package com.example.acer.myapplication.di.compoment;

import android.app.Activity;
import android.content.Context;

import com.example.acer.myapplication.mvp.view.Activity.AppDetailActivity;
import com.example.acer.myapplication.mvp.view.Activity.HomeActivity;
import com.example.acer.myapplication.di.module.ActivityModule;
import com.example.acer.myapplication.di.scope.ContextLife;
import com.example.acer.myapplication.di.scope.PerActivity;
import com.example.acer.myapplication.mvp.view.View.AppDetialActivityView;

import dagger.Component;

/**
 * Created by acer on 2018/6/10.
 */
@PerActivity
@Component(modules = ActivityModule.class,dependencies = AppCompoment.class)
public interface ActivityComponment {

    @ContextLife("Activity")
    Context getActivityContext();

    @ContextLife("Application")
    Context getApplicationContext();

    Activity getActivity();

    void inject(HomeActivity activity);
    void inject(AppDetailActivity appDetailActivity);

}
