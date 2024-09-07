package com.bizzan.utils.okhttp;


import java.util.Map;

import okhttp3.Headers;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by Administrator on 2017/9/29.
 */

public abstract class OkHttpRequest {
    protected String url;
    protected Map<String, String> params;
    protected Map<String, String> headers;
    protected Request.Builder builder = new Request.Builder();

    protected OkHttpRequest(String url, Map<String, String> params, Map<String, String> headers) {
        this.url = url;
        this.params = params;
        this.headers = headers;
        if (url == null) {
            try {
                throw new Exception("url can not be null!");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public RequestCall build() {
        return new RequestCall(this);
    }

    public Request generateRequest(Callback callback) {
        RequestBody requestBody = wrapRequestBody(buildRequestBody(), callback);
        prepareBuilder();
        return buildRequest(builder, requestBody);
    }

    protected RequestBody wrapRequestBody(RequestBody requestBody, final Callback callback) {
        return requestBody;
    }

    protected abstract RequestBody buildRequestBody();

    protected abstract Request buildRequest(Request.Builder builder, RequestBody requestBody);

    private void prepareBuilder() {
        builder.url(url);
//                .tag(tag);
        appendHeaders();
    }

    protected void appendHeaders() {
        Headers.Builder headerBuilder = new Headers.Builder();
        if (headers == null || headers.isEmpty()) return;

        for (String key : headers.keySet()) {
            if (headers.get(key) != null)
                headerBuilder.add(key, headers.get(key));
        }
        builder.headers(headerBuilder.build());
    }
}
