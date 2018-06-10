package com.example.acer.myapplication.di.scope;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by acer on 2018/6/10.
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface PerApp {

}
