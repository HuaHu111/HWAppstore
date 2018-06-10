package com.example.acer.myapplication.di.compoment;

import android.content.Context;

import com.example.acer.myapplication.di.module.AppModel;
import com.example.acer.myapplication.di.scope.ContextLife;
import com.example.acer.myapplication.di.scope.PerApp;

import dagger.Component;

/**
 * Created by acer on 2018/6/10.
 */
@PerApp
@Component(modules = AppModel.class)
public interface AppCompoment {

    @ContextLife("Application")
    Context getApplicationContext();
}
