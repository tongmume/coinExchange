package com.bizzan.utils.okhttp.get;


import com.bizzan.utils.okhttp.OkHttpRequest;

import java.util.Map;

import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by Administrator on 2017/11/13.
 * 公司一直用的post 暂时没有封装 如果需要 请参考 洪洋的开源项目 做一些修改即可
 */

public class GetRequest extends OkHttpRequest {

    public GetRequest(String url, Map<String, String> params, Map<String, String> headers) {
        super(url, params, headers);
    }

    @Override
    protected RequestBody buildRequestBody() {
        return null;
    }

    @Override
    protected Request buildRequest(Request.Builder builder, RequestBody requestBody) {
        return builder.get().build();
    }
}
