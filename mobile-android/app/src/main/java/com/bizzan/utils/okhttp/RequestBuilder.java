package com.bizzan.utils.okhttp;


import java.util.Map;

/**
 * Created by Administrator on 2017/9/29.
 */

public abstract class RequestBuilder {
    protected String url;
    protected Map<String, String> params;
    protected Map<String, String> headers;

    public abstract RequestBuilder url(String url);

    public abstract RequestCall build();

    public abstract RequestBuilder addParams(String key, String val);

    public abstract RequestBuilder addHeader(String key, String val);

}
