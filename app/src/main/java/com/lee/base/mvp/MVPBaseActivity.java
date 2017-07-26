package com.lee.base.mvp;

import android.os.Bundle;

/******************************************
 * 类描述： MVP Activity基类 类名称：MVPBaseActivity
 *
 * @version: 1.0
 * @author: shaoningYang
 * @time: 2017-2-23 10:07
 ******************************************/
public abstract class MVPBaseActivity<V extends MVPBaseInter, T extends MVPBasePresenter<V>> extends BaseActivity {
    protected T mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = createPresenter();
        if (mPresenter != null) {
            mPresenter.attachView((V) this);
        }
    }

    @Override
    protected void onDestroy() {
        if (mPresenter != null) {
            mPresenter.detachView();
        }
        super.onDestroy();
    }

    protected abstract T createPresenter();
}
