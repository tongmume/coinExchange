package com.bizzan.utils.okhttp;


import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Administrator on 2017/9/29.
 */

public abstract class Callback<T> {
    protected Gson gson = new Gson();

    public abstract T parseNetworkResponse(Response response) throws IOException;

    public void onBefore(Request request) {
    }


    public void onAfter() {
    }

    public void inProgress(float progress) {

    }

    public abstract void onError(Request request, Exception e);

    public abstract void onResponse(T response);

    public static Callback CALLBACK_DEFAULT = new Callback() {

        @Override
        public Object parseNetworkResponse(Response response) throws IOException {
            return null;
        }

        @Override
        public void onError(Request request, Exception e) {

        }

        @Override
        public void onResponse(Object response) {

        }
    };
}
