package com.lee.base.mvp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;


/******************************************
 * 类描述： Activity基类 类名称：BaseActivity
 *
 * @version: 1.0
 * @author: shaoningYang
 * @time: 2017-3-10 09:43
 ******************************************/

public class BaseActivity extends AppCompatActivity {

//    private ImageView mLoadingImageView;
//    private Animation mLoadingAnimation;

//    public static final int DEFAULT_JUMP_TYPE = 0x1;
//    public static final int BOTTOM_JUMP_TYPE = 0x2;

//    private int mPageJumpType = DEFAULT_JUMP_TYPE;
    /**
     * 是否可以使用沉浸模式true为是，false为否
     */
//    private boolean canUseStatusBarCompat = true;
    /**
     * 是否可以使用butterKnife true为是，false为否
     */
    private boolean canUseButterKnife = true;

    /**
     * 是否可以使用Android5.0提供的Activity转场动画 true为是，false为否
     */
//    protected static boolean canUseAndroidLOLLIPOPAnim = SDKUtils.hasLOLLIPOP();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setBackgroundDrawable(null);

        setContentView(getLayoutContentId());

        initOtherData();

        initView();
        initData();
    }

    /**
     * 初始化其他辅助业务逻辑
     */
    private void initOtherData() {
        if (canUseButterKnife) {
            ButterKnife.bind(this);
        }
    }


    /**
     * 禁用butterKnife功能
     */
    protected void disableButterKnife() {
        canUseButterKnife = false;
    }

    protected int getLayoutContentId() {
        return 0;
    }

    protected void initView() {
    }

    protected void initData() {
    }

    protected void showLoadingView() {
    }

    protected void hiddenLoading() {
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        super.startActivityForResult(intent, requestCode);
    }
}
