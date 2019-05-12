package com.d.lib.aster.integration.volley.observer;

import android.support.annotation.NonNull;

import com.d.lib.aster.callback.SimpleCallback;
import com.d.lib.aster.integration.volley.RequestManagerImpl;

/**
 * Observer with Sync Callback
 */
public class ApiObserver<R> extends AbsObserver<R> {
    private R mData;
    private final SimpleCallback<R> mCallback;
    private final Object mTag; // Request tag

    public ApiObserver(@NonNull SimpleCallback<R> callback, Object tag) {
        if (callback == null) {
            throw new NullPointerException("This callback must not be null!");
        }
        this.mCallback = callback;
        this.mTag = tag;
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
