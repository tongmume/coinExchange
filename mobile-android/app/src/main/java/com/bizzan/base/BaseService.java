package com.bizzan.base;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.bizzan.socket.ISocket;
import com.bizzan.socket.SocketFactory;
import com.bizzan.utils.WonderfulLogUtils;

/**
 * Created by Administrator on 2018/4/19 0019.
 */

public class BaseService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        startTCP();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    private void startTCP() {
        SocketFactory.produceSocket(ISocket.MARKET).sendRequest(
                ISocket.CMD.SUBSCRIBE_SYMBOL_THUMB, null, new ISocket.TCPCallback() {
                    @Override
                    public void dataSuccess(ISocket.CMD cmd, String response) {
                        WonderfulLogUtils.logi(cmd + "  订阅成功回执", response);
                        //thumbSuccess(cmd, response);
                    }

                    @Override
                    public void dataFail(int code, ISocket.CMD cmd, String errorInfo) {
                        WonderfulLogUtils.logi(cmd + "  订阅失败回执", errorInfo);
                    }
                });
    }

}
