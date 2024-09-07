package com.bizzan.utils.okhttp.post;

import java.util.HashMap;
import com.bizzan.utils.okhttp.RequestBuilder;
import com.bizzan.utils.okhttp.RequestCall;
import okhttp3.MediaType;

/**
 * Created by Administrator on 2017/10/27.
 */

public class PostJsonBuilder extends RequestBuilder {

    private String body;
    private MediaType mime;

    @Override
    public PostJsonBuilder url(String url) {
        this.url = url;
        return this;
    }

    public PostJsonBuilder body(String body) {
        this.body = body;
        return this;
    }

    public PostJsonBuilder mime(MediaType mime) {
        this.mime = mime;
        return this;
    }
    @Override
    public RequestCall build() {
        return new PostJsonRequest(url, params, headers, body, mime).build();
    }

    @Override
    public PostJsonBuilder addParams(String key, String val) {
        if (this.params == null) params = new HashMap<>();
        params.put(key, val);
        return this;
    }

    @Override
    public PostJsonBuilder addHeader(String key, String val) {
        if (this.headers == null) headers = new HashMap<>();
        headers.put(key, val);
        return this;
    }
}
