package com.d.lib.aster.integration.http.request;

import com.d.lib.aster.base.Config;
import com.d.lib.aster.base.Params;
import com.d.lib.aster.integration.http.body.RequestBody;
import com.d.lib.aster.integration.http.client.HttpURLApi;

/**
 * Singleton
 * Created by D on 2017/10/24.
 */
public class PutRequest extends BodyRequest<PutRequest> {

    public PutRequest(String url) {
        super(url);
    }

    public PutRequest(String url, Params params) {
        super(url, params);
    }

    public PutRequest(String url, Params params, Config config) {
        super(url, params, config);
    }

    @Override
    protected void prepare() {
        final HttpURLApi.Callable callable;
        if (mRequestBody != null) {
            callable = getClient().create().putBody(mUrl, mRequestBody);
        } else if (mMediaType != null && mContent != null) {
            mRequestBody = RequestBody.create(mMediaType, mContent);
            callable = getClient().create().putBody(mUrl, mRequestBody);
        } else if (mParams != null && mParams.size() > 0) {
            callable = getClient().create().put(mUrl, mParams);
        } else {
            callable = getClient().create().put(mUrl);
        }
        mConn = callable.conn;
        mObservable = callable.observable;
    }


    /**
     * Singleton
     */
    public static class Singleton extends BodyRequest.Singleton<Singleton> {

        public Singleton(String url) {
            super(url);
        }

        public Singleton(String url, Params params) {
            super(url, params);
        }

        @Override
        protected void prepare() {
            final HttpURLApi.Callable callable;
            if (mRequestBody != null) {
                callable = getClient().create().putBody(mUrl, mRequestBody);
            } else if (mMediaType != null && mContent != null) {
                mRequestBody = RequestBody.create(mMediaType, mContent);
                callable = getClient().create().putBody(mUrl, mRequestBody);
            } else if (mParams != null && mParams.size() > 0) {
                callable = getClient().create().put(mUrl, mParams);
            } else {
                callable = getClient().create().put(mUrl);
            }
            mConn = callable.conn;
            mObservable = callable.observable;
        }
    }
}
