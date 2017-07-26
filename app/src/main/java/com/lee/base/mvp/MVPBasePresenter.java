package com.lee.base.mvp;

import java.lang.ref.WeakReference;

/******************************************
 * 类描述： Presenter基类 类名称：MVPBasePresenter
 *
 * @version: 1.0
 * @author: shaoningYang
 * @time: 2017-2-23 10:12
 ******************************************/

public class MVPBasePresenter<T> {
    protected WeakReference<T> mViewRef;

    public void attachView(T view) {
        mViewRef = new WeakReference<>(view);
    }

    public void detachView() {
        if (mViewRef != null) {
            mViewRef.enqueue();
            mViewRef.clear();
            mViewRef = null;
        }
    }

    public boolean isViewAttached() {
        return mViewRef != null && mViewRef.get() != null;
    }

    protected T getViewInterface() {
        if (isViewAttached()) {
            return mViewRef.get();
        } else {
            return null;
        }
    }

    protected void cancelRequest() {
    }
}
