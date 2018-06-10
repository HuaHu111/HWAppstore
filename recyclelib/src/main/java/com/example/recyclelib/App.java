package com.example.recyclelib;

import android.app.Application;

/**
 * Created by acer on 2018/6/9.
 */

public class App extends Application {

    private static App context;

    @Override
    public void onCreate() {
        super.onCreate();
        context=this;
    }

    public static App getContext(){
        return context;
    }
}
