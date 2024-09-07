package com.bizzan.utils.okhttp.post;



import java.util.Map;

import com.bizzan.utils.okhttp.OkHttpRequest;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by Administrator on 2017/10/27.
 */

public class PostJsonRequest extends OkHttpRequest {

    private String body;
    private MediaType mime;

    protected PostJsonRequest(String url, Map<String, String> params, Map<String, String> headers, String body, MediaType mime) {
        super(url, params, headers);
        this.body = body;
        this.mime = mime;
    }

    @Override
    protected RequestBody buildRequestBody() {
        return RequestBody.create(mime, body);
    }

    @Override
    protected Request buildRequest(Request.Builder builder, RequestBody requestBody) {
        return builder.post(requestBody).build();
    }
}
