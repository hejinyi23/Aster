package com.d.lib.rxnet.request;

import com.d.lib.rxnet.api.RetrofitAPI;

import java.util.Map;

import javax.net.ssl.SSLSocketFactory;

import okhttp3.Interceptor;

/**
 * Created by D on 2017/10/24.
 */
public class HeadRequest extends HttpRequest<HeadRequest> {

    public HeadRequest(String url) {
        super(url);
    }

    public HeadRequest(String url, Map<String, String> params) {
        super(url, params);
    }

    @Override
    protected void prepare() {
        observable = getClient().create(RetrofitAPI.class).head(url, params);
    }

    @Override
    public HeadRequest baseUrl(String baseUrl) {
        return super.baseUrl(baseUrl);
    }

    @Override
    public HeadRequest headers(Map<String, String> headers) {
        return super.headers(headers);
    }

    @Override
    public HeadRequest connectTimeout(long timeout) {
        return super.connectTimeout(timeout);
    }

    @Override
    public HeadRequest readTimeout(long timeout) {
        return super.readTimeout(timeout);
    }

    @Override
    public HeadRequest writeTimeout(long timeout) {
        return super.writeTimeout(timeout);
    }

    @Override
    public HeadRequest sslSocketFactory(SSLSocketFactory sslSocketFactory) {
        return super.sslSocketFactory(sslSocketFactory);
    }

    @Override
    public HeadRequest addInterceptor(Interceptor interceptor) {
        return super.addInterceptor(interceptor);
    }

    @Override
    public HeadRequest addNetworkInterceptors(Interceptor interceptor) {
        return super.addNetworkInterceptors(interceptor);
    }

    @Override
    public HeadRequest retryCount(int retryCount) {
        return super.retryCount(retryCount);
    }

    @Override
    public HeadRequest retryDelayMillis(long retryDelayMillis) {
        return super.retryDelayMillis(retryDelayMillis);
    }

    /**
     * Singleton
     */
    public static class Singleton extends HttpRequest.Singleton<Singleton> {

        public Singleton(String url) {
            super(url);
        }

        public Singleton(String url, Map<String, String> params) {
            super(url, params);
        }

        @Override
        protected void prepare() {
            observable = getClient().create(RetrofitAPI.class).head(url, params);
        }
    }
}
