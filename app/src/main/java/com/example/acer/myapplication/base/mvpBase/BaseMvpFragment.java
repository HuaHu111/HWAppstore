package com.example.acer.myapplication.base.mvpBase;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.example.acer.myapplication.base.BaseFragment;
import com.example.acer.myapplication.base.StoreApplication;
import com.example.acer.myapplication.di.compoment.DaggerFragmentCompoment;
import com.example.acer.myapplication.di.compoment.FragmentCompoment;
import com.example.acer.myapplication.di.module.FragmentModule;

/**
 * Created by acer on 2018/6/10.
 */

public abstract class BaseMvpFragment<T extends BasePresenter> extends BaseFragment implements BaseView {


    protected FragmentCompoment mFragmentComponent;
    protected T mPresenter;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initFragmentComponent();
        mPresenter=initInjector();
        mPresenter.attachView(this);
    }

    private void initFragmentComponent() {
        mFragmentComponent = DaggerFragmentCompoment.builder()
                .fragmentModule(new FragmentModule(this))
                .appCompoment(((StoreApplication) getActivity().getApplication()).getAppCompoment())
                .build();
    }


    /**
     * 完成注入的presenter
     * @return
     */
    protected abstract T initInjector();

    @Override
    public void showToast(String msg) {
        Toast.makeText(getContext(),msg,Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter!=null){
            mPresenter=null;
        }
    }
}
