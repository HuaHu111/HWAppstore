package com.example.acer.myapplication.mvp.view.Fragment;

import android.os.SystemClock;
import android.view.View;
import android.widget.TextView;

import com.example.acer.myapplication.R;
import com.example.acer.myapplication.View.LoadingPager;
import com.example.acer.myapplication.View.widge.EnterLayout;
import com.example.acer.myapplication.base.BaseFragment;
import com.example.acer.myapplication.utils.UIUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by acer on 2018/6/9.
 */

public class AppManagerFragment extends BaseFragment {

    @BindView(R.id.update_label_subtitle)
    TextView tvUpdateLabelSubtitle;
    @BindView(R.id.update_label_textview)
    TextView tvUpdateLabel;
    @BindView(R.id.install_manager_layout)
    EnterLayout installManager;
    @BindView(R.id.apk_manager_layout)
    EnterLayout apkManager;
    @BindView(R.id.system_manager_layout)
    EnterLayout systemManager;
    @BindView(R.id.connect_computer)
    EnterLayout connect;


    @Override
    protected View creatSuccessView() {
        View view = UIUtils.inflate(R.layout.fragment_manager);
        ButterKnife.bind(this, view);
        tvUpdateLabel.setText(UIUtils.getString(R.string.update_manager_title));
        tvUpdateLabelSubtitle.setText(UIUtils.getString(R.string.update_manager_subtitle_version_new));
        installManager.setTitle(UIUtils.getString(R.string.install_manager_title_ex));
        installManager.setMemo(UIUtils.getString(R.string.install_manager_subtitle));
        apkManager.setTitle(UIUtils.getString(R.string.apk_management));
        apkManager.setMemo(UIUtils.getString(R.string.apkmanage_tips_modify));
        systemManager.setTitle(UIUtils.getString(R.string.system_manager_title));
        systemManager.setMemo(UIUtils.getString(R.string.system_manager_memo));
        connect.setTitle(UIUtils.getString(R.string.connect_pc));
        connect.setMemo(UIUtils.getString(R.string.manager_phone_by_pc));

        return view;
    }

    @Override
    protected void load() {
        setState(LoadingPager.LoadResult.success);
    }
}
