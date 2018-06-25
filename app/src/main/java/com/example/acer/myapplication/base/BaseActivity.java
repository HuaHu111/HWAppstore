package com.example.acer.myapplication.base;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.example.acer.myapplication.AppActivityManager;
import com.example.acer.myapplication.R;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import java.lang.reflect.Field;

import butterknife.ButterKnife;

/**
 * Created by acer on 2018/6/9.
 */

public class BaseActivity extends RxAppCompatActivity {

    private ViewGroup bar_layout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);  //保持竖屏

        AppActivityManager.getInstance().addActivity(this);


        initlayout();
        ButterKnife.bind(this);
        setStatus();
        initview();

    }

    //初始化view
    public void initview() {

    }


    //设置沉浸式状态栏
    private void setStatus(){
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.KITKAT){
            //设置状态栏透明
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);

            bar_layout= (ViewGroup) findViewById(R.id.bar_layout);
            if (bar_layout!=null){
                final int statusBarHeight = getStatusBarHeight();
                bar_layout.post(new Runnable() {
                    @Override
                    public void run() {
                        int height = bar_layout.getHeight();
                        ViewGroup.LayoutParams layoutParams = bar_layout.getLayoutParams();
                        layoutParams.height= statusBarHeight+height;
                        bar_layout.setLayoutParams(layoutParams);
                    }
                });
            }

        }
    }

    //初始化布局
    public void initlayout() {

    }


    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
        overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_left);
    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        super.startActivityForResult(intent, requestCode);
        overridePendingTransition(R.anim.anim_slide_in_right,R.anim.anim_slide_out_left);
    }

    protected void openActivity(Class clzaa){
        Intent intent = new Intent(this, clzaa);
        startActivity(intent);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.anim_slide_in_left,R.anim.anim_slide_out_right);
    }

    protected int getStatusBarHeight(){
        try
        {
            Class<?> c = Class.forName("com.android.internal.R$dimen");
            Object obj = c.newInstance();
            Field field = c.getField("status_bar_height");
            int x = Integer.parseInt(field.get(obj).toString());
            return  getResources().getDimensionPixelSize(x);
        }catch(Exception e){
            e.printStackTrace();
        }
        return 0;
    }
}
