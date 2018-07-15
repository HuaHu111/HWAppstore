package com.example.acer.myapplication.base;

import android.os.Handler;

import com.example.acer.myapplication.BuildConfig;
import com.example.acer.myapplication.di.compoment.AppCompoment;
import com.example.acer.myapplication.di.compoment.DaggerAppCompoment;
import com.example.acer.myapplication.di.module.AppModel;
import com.example.recyclelib.App;
import com.zhxu.library.RxRetrofitApp;


/**
 * Created by acer on 2018/6/9.
 */

public class StoreApplication extends App {

    private static int mMainThreadId;
    private static Handler mhandler;
    private AppCompoment appCompoment;


    @Override
    public void onCreate() {
        super.onCreate();

        mhandler = new Handler();
        iniApplicationComponent();
        RxRetrofitApp.init(this, BuildConfig.DEBUG);
    }

    
    private void iniApplicationComponent(){
        appCompoment= DaggerAppCompoment.builder().appModel(new AppModel(this)).build();
    }


    public AppCompoment getAppCompoment(){
        return appCompoment;
    }

    public static int getMainThreadId() {
        return mMainThreadId;
    }

    public static Handler getHandler() {
        return mhandler;
    }
}
