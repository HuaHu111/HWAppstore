package com.example.acer.myapplication.di.scope;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;

/**
 * 限定符
 * 限定Context生命周期
 * Created by acer on 2018/6/10.
 */

@Qualifier
@Retention(RetentionPolicy.RUNTIME)
public @interface ContextLife {
    String value()default "";
}
