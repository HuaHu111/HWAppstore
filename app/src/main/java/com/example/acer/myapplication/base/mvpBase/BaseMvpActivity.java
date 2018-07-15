package com.example.acer.myapplication.base.mvpBase;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.example.acer.myapplication.base.BaseActivity;
import com.example.acer.myapplication.base.StoreApplication;
import com.example.acer.myapplication.di.compoment.ActivityComponment;
import com.example.acer.myapplication.di.compoment.DaggerActivityComponment;
import com.example.acer.myapplication.di.module.ActivityModule;

/**
 * Created by acer on 2018/6/10.
 */

public abstract class  BaseMvpActivity<T extends BasePresenter> extends BaseActivity implements BaseView {


    protected ActivityComponment mActivityComponent;
    private T mPresenter;

    //通过dagger2注入的是Presenter

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initActivityComponent();
        mPresenter = initInjector();
        mPresenter.attachView(this);
        initData();
    }

    protected abstract void initData();


    public void initActivityComponent(){
        mActivityComponent = DaggerActivityComponment.builder()
                .activityModule(new ActivityModule(this))
                .appCompoment(((StoreApplication) getApplication()).getAppCompoment())
                .build();

    }


    /**
     * 完成注入并返回注入的Presenter对象
     * @return
     */
    protected abstract T initInjector();

    @Override
    public void showToast(String msg) {
        Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_SHORT).show();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter!=null){
            mPresenter.detachView();
        }
    }
}
