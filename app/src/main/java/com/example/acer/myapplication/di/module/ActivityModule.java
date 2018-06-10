package com.example.acer.myapplication.di.module;

import android.app.Activity;
import android.content.Context;

import com.example.acer.myapplication.di.scope.ContextLife;
import com.example.acer.myapplication.di.scope.PerActivity;
import com.example.acer.myapplication.di.scope.PerFragment;

import dagger.Module;
import dagger.Provides;

/**
 * Created by acer on 2018/6/10.
 */
@Module
public class ActivityModule {
    private Activity mActivity;

    public ActivityModule (Activity activity){
        this.mActivity=activity;
    }

    @Provides
    @PerActivity
    public Activity provideActivity(){
        return mActivity;
    }

    @Provides
    @PerActivity
    @ContextLife("Activity")
    public Context provideActivityContext(){
        return mActivity;
    }
}
