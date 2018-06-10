package com.example.acer.myapplication.base;

import android.os.Handler;

import com.example.recyclelib.App;


/**
 * Created by acer on 2018/6/9.
 */

public class StoreApplication extends App {

    private static int mMainThreadId;
    private static Handler mhandler;

    @Override
    public void onCreate() {
        super.onCreate();

        mhandler = new Handler();
    }


    public static int getMainThreadId() {
        return mMainThreadId;
    }

    public static Handler getHandler() {
        return mhandler;
    }
}
