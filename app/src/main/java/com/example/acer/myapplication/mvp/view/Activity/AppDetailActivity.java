package com.example.acer.myapplication.mvp.view.Activity;

import android.os.Environment;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.format.Formatter;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.acer.myapplication.R;
import com.example.acer.myapplication.View.widge.DetailShareButton;
import com.example.acer.myapplication.View.widge.DownloadProgressButton;
import com.example.acer.myapplication.View.widge.SubTabNavigator;
import com.example.acer.myapplication.adapter.AppDetailPagerAdapter;
import com.example.acer.myapplication.base.BaseActivity;
import com.example.acer.myapplication.base.mvpBase.BaseMvpActivity;
import com.example.acer.myapplication.bean.AppDetailBean;
import com.example.acer.myapplication.mvp.presenter.impl.AppDetailPresenterImpl;
import com.example.acer.myapplication.mvp.view.Fragment.AppCommentFragment;
import com.example.acer.myapplication.mvp.view.Fragment.AppIntroductionFragment;
import com.example.acer.myapplication.mvp.view.Fragment.AppRecommendFragment;
import com.example.acer.myapplication.mvp.view.View.AppDetialActivityView;
import com.example.acer.myapplication.utils.UIUtils;
import com.zhxu.library.download.DownInfo;
import com.zhxu.library.download.DownState;
import com.zhxu.library.download.HttpDownManager;
import com.zhxu.library.listener.HttpDownOnNextListener;
import com.zhxu.library.utils.DbDownUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by acer on 2018/7/1.
 */

public class AppDetailActivity extends BaseMvpActivity<AppDetailPresenterImpl> implements AppDetialActivityView {


    @BindView(R.id.detail_head_app_icon_imageview)
    ImageView detail_app_icon;
    @BindView(R.id.detail_head_app_name_textview)
    TextView detail_app_name;
    @BindView(R.id.detail_head_download_count_textview)
    TextView detail_app_download_count;
    @BindView(R.id.detail_head_app_stars_ratingbar)
    RatingBar detail_app_stars;
    @BindView(R.id.detail_head_label_layout_linearlayout)
    RelativeLayout detail_head_label_layout;
    @BindView(R.id.detail_head_label_icon_layout_linearlayout)
    LinearLayout detail_head_label_icon_layout;
    @BindView(R.id.detail_head_lable_folding_textview)
    TextView detail_head_lable_folding;
    @BindView(R.id.detail_head_safe_icon_container_linearlayout)
    LinearLayout detail_head_safe_icon_container;
    @BindView(R.id.detail_head_safe_icon_layout_linearlayout)
    LinearLayout detail_head_safe_icon_layout;
    @BindView(R.id.subTab)
    SubTabNavigator subTabNavigator;
    @BindView(R.id.vp)
    ViewPager mViewPager;
    @BindView(R.id.appdetail_head)
    LinearLayout appdetail_head;
    @BindView(R.id.detail_download_button)
    DownloadProgressButton detail_download_button;
    @BindView(R.id.detail_download_share_button)
    DetailShareButton detail_download_share_button;
    @BindView(R.id.detail_download_comment_button_linearlayout)
    LinearLayout detail_download_comment_button_linearlayout;
    private String packageName;
    private final static String TAG = "AppDetailActivity";
    private boolean expand = false;
    private HttpDownManager manager;
    private DbDownUtil dbDownUtil;
    private File outputFile;
    private DownInfo downInfo;



    @Inject
    public AppDetailPresenterImpl appDetailPresenter;


    @Override
    public void initlayout() {
        setContentView(R.layout.activity_app_detail2);
    }

    @Override
    public void initview() {
        setStatus();
    }

    @Override
    public void onAppDetailDataSuccess(AppDetailBean appDetailBean) {
        Log.i(TAG, appDetailBean.getName());
        setDetailHead(appDetailBean);
    }

    private void setDetailHead(AppDetailBean appDetailBean) {
        Glide.with(UIUtils.getContext()).load(appDetailBean.getIcoUrl()).into(detail_app_icon);
        detail_app_name.setText(appDetailBean.getName());
        detail_app_download_count.setText(appDetailBean.getIntro());
        detail_app_stars.setRating(Float.parseFloat(appDetailBean.getStars()));
        setLabel(appDetailBean);
        setSafeLabel(appDetailBean);
        setSubTab(appDetailBean);
        setDownLoad(appDetailBean);
    }

    private void setDownLoad(final AppDetailBean appDetailBean){

        if (downInfo==null){
            detail_download_button.setStartText("安装"+ Formatter.formatFileSize(this,Long.parseLong(appDetailBean.getSize())));
        }else {
            if (downInfo.getState()==DownState.DOWN){
                detail_download_button.setState(DownloadProgressButton.STATUS_PROGRESS_BAR_DOWNLOADING);
            }else if (downInfo.getState()== DownState.PAUSE){
                detail_download_button.setState(DownloadProgressButton.STATUS_PROGRESS_BAR_PAUSE);
            }else if (downInfo.getState()==DownState.FINISH){
                detail_download_button.setState(DownloadProgressButton.STATUS_PROGRESS_BAR_BEGIN);
            }
            detail_download_button.setProgress((int) ((100*downInfo.getReadLength())/downInfo.getCountLength()));
        }

             detail_download_button.setStateChangeListener(new DownloadProgressButton.StateChangeListener() {
            @Override
            public void onPauseTask() {
                manager.pause(downInfo);
            }

            @Override
            public void onFinishTask() {

            }

            @Override
            public void onLoadingTask() {
                detail_download_button.setMax(100);
                if (downInfo==null){
                    downInfo=new DownInfo(appDetailBean.getDownloadUrl());
                    downInfo.setListener(downListener);
                    downInfo.setId((long) packageName.hashCode());
                    downInfo.setSavePath(outputFile.getAbsolutePath());
                    downInfo.setState(DownState.START);
                    dbDownUtil.save(downInfo);
                }
                if (downInfo.getState()!=DownState.FINISH){
                    manager.startDown(downInfo);
                }

            }
        });
    }

    private HttpDownOnNextListener downListener =new HttpDownOnNextListener() {
        @Override
        public void onNext(Object o) {

        }

        @Override
        public void onStart() {

        }

        @Override
        public void onComplete() {

        }

        @Override
        public void updateProgress(long readLength, long countLength) {
            int progress=(int)((100*readLength)/countLength);
            detail_download_button.setProgress(progress);
        }
    };

    private void setLabel(AppDetailBean appDetailBean) {
        for (AppDetailBean.LabelName labelNamesBean : appDetailBean.getLabelNameList()) {
            View labelView = UIUtils.inflate(R.layout.appdetail_item_head_label_item);
            TextView label = (TextView) labelView.findViewById(R.id.appdetail_head_label_textview);
            label.setText(labelNamesBean.getName());
            if (labelNamesBean.getType() == 1) {
                label.setTextColor(getResources().getColor(R.color.app_not_safe_textcolor));
            }
            detail_head_label_icon_layout.addView(labelView);
        }
    }

    private void setSafeLabel(AppDetailBean appDetailBean) {
        for (AppDetailBean.SafeLabel safeLabelsBean : appDetailBean.getSafeLabelList()) {
            View safeLabelView = UIUtils.inflate(R.layout.appdetail_item_head_safe_item);
            TextView safe_checker = (TextView) safeLabelView.findViewById(R.id.safe_checker_textview);
            TextView safe_checker_label = (TextView) safeLabelView.findViewById(R.id.safe_checker_label);
            ImageView detail_head_app_icon = (ImageView) safeLabelView.findViewById(R.id.detail_head_app_icon_imageview);
            TextView detail_safe_desc_textview = (TextView) safeLabelView.findViewById(R.id.detail_safe_desc_textview);

            safe_checker.setText(safeLabelsBean.getDetectorName());
            safe_checker_label.setText(safeLabelsBean.getDetectorDesc());
            Glide.with(UIUtils.getContext()).load(safeLabelsBean.getUrl()).into(detail_head_app_icon);
            detail_safe_desc_textview.setText(safeLabelsBean.getName());

            detail_head_safe_icon_container.addView(safeLabelView);
        }
        detail_head_label_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (expand) {
                    expand = false;
                    detail_head_safe_icon_layout.setVisibility(View.GONE);
                    detail_head_lable_folding.setBackgroundResource(R.drawable.ic_public_arrow_down);
                } else {
                    expand = true;
                    detail_head_safe_icon_layout.setVisibility(View.VISIBLE);
                    detail_head_lable_folding.setBackgroundResource(R.drawable.ic_public_arrow_up);
                }
            }
        });
    }

    private void setSubTab(AppDetailBean appDetailBean) {
        subTabNavigator.setLeftText(appDetailBean.getTabInfoList().get(0));
        subTabNavigator.setNoneText(appDetailBean.getTabInfoList().get(1));
        subTabNavigator.setRightText(appDetailBean.getTabInfoList().get(2));
        List<Fragment>fragments=new ArrayList<>();
        fragments.add(new AppIntroductionFragment());
        fragments.add(new AppCommentFragment());
        fragments.add(new AppRecommendFragment());
        AppDetailPagerAdapter adapter=new AppDetailPagerAdapter(getSupportFragmentManager());
       adapter.setFragments(fragments);
        mViewPager.setAdapter(adapter);
        mViewPager.setOnPageChangeListener(subTabNavigator);
        subTabNavigator.setViewPager(mViewPager);
    }

    @Override
    public void onAppDetailDataFail(String msg) {
        Log.i(TAG, msg);
    }

    @Override
    protected void initData() {
        packageName = getIntent().getStringExtra("packageName");
        appDetailPresenter.getAppDetail(this, packageName);

        manager=HttpDownManager.getInstance();
        dbDownUtil=DbDownUtil.getInstance();
        downInfo = dbDownUtil.queryDownBy((long) packageName.hashCode());
        if (downInfo==null){
            outputFile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), packageName + ".apk");
        }



    }

    @Override
    protected AppDetailPresenterImpl initInjector() {
        mActivityComponent.inject(this);
        return appDetailPresenter;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (dbDownUtil!=null&&downInfo!=null){
            dbDownUtil.update(downInfo);
        }
    }
}
