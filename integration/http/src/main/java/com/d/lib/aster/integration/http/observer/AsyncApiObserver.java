package com.d.lib.aster.integration.http.observer;

import com.d.lib.aster.callback.AsyncCallback;
import com.d.lib.aster.integration.http.RequestManagerImpl;

/**
 * Observer with Async Callback
 */
public class AsyncApiObserver<T, R> extends AbsObserver<R> {
    private R mData;
    private Object mTag; // Request tag
    private AsyncCallback<T, R> mCallback;

    public AsyncApiObserver(Object tag, AsyncCallback<T, R> callback) {
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

    public R getData() {
        return mData;
    }
}
