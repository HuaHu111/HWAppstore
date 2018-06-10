package com.example.acer.myapplication.di.module;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;

import com.example.acer.myapplication.di.scope.ContextLife;
import com.example.acer.myapplication.di.scope.PerActivity;
import com.example.acer.myapplication.di.scope.PerFragment;

import dagger.Module;
import dagger.Provides;

/**
 * Created by acer on 2018/6/10.
 */
@Module
public class FragmentModule {

    private Fragment mFragment;

    public FragmentModule(Fragment fragment){
        this.mFragment=fragment;
    }

    @Provides
    @PerFragment
    public Fragment provideFragment(){
        return mFragment;
    }


    @Provides
    @PerFragment
    public Activity provideActivity(){
        return mFragment.getActivity();
    }

    @Provides
    @PerFragment
    @ContextLife("Activity")
    public Context provideContext(){
        return mFragment.getContext();
    }
}
