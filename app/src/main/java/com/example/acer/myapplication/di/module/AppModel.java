package com.example.acer.myapplication.di.module;

import android.content.Context;

import com.example.acer.myapplication.base.StoreApplication;
import com.example.acer.myapplication.di.scope.ContextLife;
import com.example.acer.myapplication.di.scope.PerApp;

import dagger.Module;
import dagger.Provides;

/**
 * Created by acer on 2018/6/10.
 */
@Module
public class AppModel {
    private StoreApplication storeApplication;

    public AppModel(StoreApplication application){
        this.storeApplication=application;
    }

    @Provides
    @PerApp
    @ContextLife("Application")
    public Context provideAppContext(){
        return storeApplication.getApplicationContext();
    }
}
