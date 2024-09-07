package com.bizzan.socket;

/**
 * Created by Administrator on 2018/4/12.
 */

public class SocketFactory {
    /**
     * 1 行情   2 C2C   3 组聊天 4 心跳包
     */
    public static ISocket produceSocket(int type) {
        switch (type) {
            case ISocket.MARKET:
                return MarketSocket.getInstance();
            case ISocket.C2C:
                return C2CSocket.getInstance();
            case ISocket.GROUP:
                return GroupSocket.getInstance();
            case ISocket.HEART:
                return HeartSocket.getInstance();
            default:
        }
        return null;
    }

}
