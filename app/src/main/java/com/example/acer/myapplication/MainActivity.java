package com.example.acer.myapplication;

import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import android.view.WindowManager;

import java.lang.reflect.Field;

/**
 * Created by acer on 2018/6/3.
 */

public class MainActivity extends AppCompatActivity {
    public ViewGroup bar_layout;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setStatus();
    }

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
