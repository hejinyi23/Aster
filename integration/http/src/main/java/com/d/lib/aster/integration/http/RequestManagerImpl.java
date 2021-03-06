package com.d.lib.aster.integration.http;

import android.support.annotation.Nullable;

import com.d.lib.aster.base.IRequestManager;
import com.d.lib.aster.integration.http.observer.DownloadObserver;
import com.d.lib.aster.integration.http.observer.UploadObserver;
import com.d.lib.aster.scheduler.callback.DisposableObserver;
import com.d.lib.aster.scheduler.callback.Observer;

import java.util.HashMap;
import java.util.Set;

/**
 * Request management to facilitate mid-way cancellation of requests
 */
public class RequestManagerImpl implements IRequestManager<Observer> {
    private HashMap<Object, Observer> mHashMap;

    private static class Singleton {
        private final static RequestManagerImpl INSTANCE = new RequestManagerImpl();
    }

    public static RequestManagerImpl getIns() {
        return Singleton.INSTANCE;
    }

    private RequestManagerImpl() {
        mHashMap = new HashMap<>();
    }

    @Override
    public synchronized void add(Object tag, Observer disposable) {
        if (tag == null || disposable == null) {
            return;
        }
        mHashMap.put(tag, disposable);
    }

    @Override
    public synchronized void remove(Object tag) {
        if (mHashMap.isEmpty() || tag == null) {
            return;
        }
        mHashMap.remove(tag);
    }

    @Override
    public synchronized void removeAll() {
        if (mHashMap.isEmpty()) {
            return;
        }
        mHashMap.clear();
    }

    @Override
    public synchronized boolean canceled(Object tag) {
        if (tag == null) {
            return false;
        }
        boolean canceled = mHashMap.containsKey(tag);
        cancel(tag);
        return canceled;
    }

    @Override
    public synchronized void cancel(Object tag) {
        if (tag == null) {
            return;
        }
        Observer value = mHashMap.remove(tag);
        cancelImpl(value);
    }

    private void cancelImpl(@Nullable Observer value) {
        if (value == null || !(value instanceof DisposableObserver)) {
            return;
        }
        DisposableObserver disposable = (DisposableObserver) value;
        if (!disposable.isDisposed()) {
            if (disposable instanceof DownloadObserver) {
                ((DownloadObserver) disposable).cancel();
            } else if (disposable instanceof UploadObserver) {
                ((UploadObserver) disposable).cancel();
            } else {
                disposable.dispose();
            }
        }
    }

    @Override
    public synchronized void cancelAll() {
        if (mHashMap.isEmpty()) {
            return;
        }
        HashMap<Object, Observer> temp = new HashMap<>(mHashMap);
        mHashMap.clear();
        Set<Object> keys = temp.keySet();
        for (Object k : keys) {
            cancelImpl(temp.get(k));
        }
    }
}
