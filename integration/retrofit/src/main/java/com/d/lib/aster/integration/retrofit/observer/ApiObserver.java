package com.d.lib.aster.integration.retrofit.observer;

import com.d.lib.aster.callback.SimpleCallback;
import com.d.lib.aster.integration.retrofit.RequestManagerImpl;

/**
 * Observer with Sync Callback
 */
public class ApiObserver<R> extends AbsObserver<R> {
    private R mData;
    private Object mTag; // Request tag
    private SimpleCallback<R> mCallback;

    public ApiObserver(Object tag, SimpleCallback<R> callback) {
        if (callback == null) {
            throw new NullPointerException("This callback must not be null!");
        }
        this.mTag = tag;
        this.mCallback = callback;
    }

    @Override
    public void onNext(R r) {
        RequestManagerImpl.getIns().remove(mTag);
        this.mData = r;
        mCallback.onSuccess(r);
    }

    @Override
    public void onError(Throwable e) {
        RequestManagerImpl.getIns().remove(mTag);
        super.onError(e);
        mCallback.onError(e);
    }

    @Override
    public void onComplete() {
    }

    public R getData() {
        return mData;
    }
}
