package com.d.lib.aster.scheduler.callback;

import android.support.annotation.NonNull;

/**
 * Observer
 * Created by D on 2018/1/23.
 */
public interface Observer<T> {
    void onNext(@NonNull T result);

    void onError(@NonNull Throwable e);
}
