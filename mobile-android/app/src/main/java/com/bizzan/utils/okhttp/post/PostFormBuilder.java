package com.bizzan.utils.okhttp.post;


import android.util.Log;

import com.bizzan.app.MyApplication;
import com.bizzan.utils.EncryUtils;
import com.bizzan.utils.SharedPreferenceInstance;
import com.bizzan.utils.okhttp.RequestBuilder;
import com.bizzan.utils.okhttp.RequestCall;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Administrator on 2017/9/29.
 */

public class PostFormBuilder extends RequestBuilder {
    private List<FileInput> files = new ArrayList<>();

    @Override
    public PostFormBuilder url(String url) {
        this.url = url;
        return this;
    }

    @Override
    public RequestCall build() {
        String lang = SharedPreferenceInstance.getInstance().getLanguageRegion();
        addHeader("lang", lang);

        String token;
        if ("".equals(SharedPreferenceInstance.getInstance().getaToken()) || !MyApplication.getApp().isLogin()) {
            token = EncryUtils.getInstance().decryptString(SharedPreferenceInstance.getInstance().getToken(), MyApplication.getApp().getPackageName());
            SharedPreferenceInstance.getInstance().saveaToken(token);
        } else {
            token = SharedPreferenceInstance.getInstance().getaToken();
        }
        addHeader("access-auth-token", token);
        Set<Map.Entry<String, String>> ms = headers.entrySet();
        for (Map.Entry entry : ms) {
            Log.d("httppost", "请求头:" + entry.getKey() + "---->" + entry.getValue());
        }
        if (params != null) {
            for (Map.Entry entry : params.entrySet()) {
                Log.d("httppost", "请求参数:" + entry.getKey() + "---->" + entry.getValue());
            }
        }
        return new PostFormRequest(url, params, headers, files).build();
    }

    ///IdentityHashMap  与 hashMap
    @Override
    public PostFormBuilder addParams(String key, String value) {
        if (this.params == null) params = new HashMap<>();
        if (value != null)
            params.put(key, value);
        return this;
    }

    public PostFormBuilder addFile(String name, String filename, File file) {
        files.add(new FileInput(name, filename, file));
        return this;
    }

    @Override
    public PostFormBuilder addHeader(String key, String value) {
        if (this.headers == null) headers = new HashMap<>();
        if (value != null)
            headers.put(key, value);
        return this;
    }

    public static class FileInput {
        public String key;
        public String filename;
        public File file;

        public FileInput(String name, String filename, File file) {
            this.key = name;
            this.filename = filename;
            this.file = file;
        }
    }
}
