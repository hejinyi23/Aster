package com.d.lib.rxnet.request;

import com.d.lib.rxnet.api.RetrofitAPI;

import java.util.Map;

import javax.net.ssl.SSLSocketFactory;

import okhttp3.Interceptor;

/**
 * Created by D on 2017/10/24.
 */
public class GetRequest extends HttpRequest<GetRequest> {

    public GetRequest(String url) {
        super(url);
    }

    public GetRequest(String url, Map<String, String> params) {
        super(url, params);
    }

    @Override
    protected void prepare() {
        if (params == null) {
            observable = getClient().create(RetrofitAPI.class).get(url);
        } else {
            observable = getClient().create(RetrofitAPI.class).get(url, params);
        }
    }

    @Override
    public GetRequest baseUrl(String baseUrl) {
        return super.baseUrl(baseUrl);
    }

    @Override
    public GetRequest headers(Map<String, String> headers) {
        return super.headers(headers);
    }

    @Override
    public GetRequest connectTimeout(long timeout) {
        return super.connectTimeout(timeout);
    }

    @Override
    public GetRequest readTimeout(long timeout) {
        return super.readTimeout(timeout);
    }

    @Override
    public GetRequest writeTimeout(long timeout) {
        return super.writeTimeout(timeout);
    }

    @Override
    public GetRequest sslSocketFactory(SSLSocketFactory sslSocketFactory) {
        return super.sslSocketFactory(sslSocketFactory);
    }

    @Override
    public GetRequest addInterceptor(Interceptor interceptor) {
        return super.addInterceptor(interceptor);
    }

    @Override
    public GetRequest addNetworkInterceptors(Interceptor interceptor) {
        return super.addNetworkInterceptors(interceptor);
    }

    @Override
    public GetRequest retryCount(int retryCount) {
        return super.retryCount(retryCount);
    }

    @Override
    public GetRequest retryDelayMillis(long retryDelayMillis) {
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
            if (params == null) {
                observable = getClient().create(RetrofitAPI.class).get(url);
            } else {
                observable = getClient().create(RetrofitAPI.class).get(url, params);
            }
        }
    }
}
