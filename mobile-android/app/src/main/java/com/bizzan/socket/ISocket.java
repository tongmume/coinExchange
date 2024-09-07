package com.bizzan.socket;

/**
 * Created by Administrator on 2018/4/12.
 */

public interface ISocket extends Runnable {
    int MARKET = 1;
    int C2C = 2;
    int GETCHAT = 3;
    int GROUP = 3;
    int HEART = 4;

    void sendRequest(CMD cmd, byte[] body, TCPCallback callback);

    interface TCPCallback {
        void dataSuccess(CMD cmd, String response);

        void dataFail(int code, CMD cmd, String errorInfo);

    }


    enum CMD {
        COMMANDS_VERSION((short) 1),
        HEART_BEAT((short) 11004),
        SUBSCRIBE_SYMBOL_THUMB((short) 20001), UNSUBSCRIBE_SYMBOL_THUMB((short) 20002),
        PUSH_SYMBOL_THUMB((short) 20003),
        SUBSCRIBE_SYMBOL_KLINE((short) 20011), UNSUBSCRIBE_SYMBOL_KLINE((short) 20012),
        PUSH_SYMBOL_KLINE((short) 20013),
        SUBSCRIBE_EXCHANGE_TRADE((short) 20021), // 盘口信息
        UNSUBSCRIBE_EXCHANGE_TRADE((short) 20022), // 取消盘口信息
        PUSH_EXCHANGE_TRADE((short) 20024), // 盘口返回    ---
        PUSH_EXCHANGE_TRADE2((short) 20023), //成交
        SUBSCRIBE_CHAT((short) 20031), UNSUBSCRIBE_CHAT((short) 20032),
        PUSH_CHAT((short) 20033),
        SEND_CHAT((short) 20034),
        PUSH_EXCHANGE_ORDER_COMPLETED((short) 20026),
        PUSH_EXCHANGE_ORDER_CANCELED((short) 20027),
        PUSH_EXCHANGE_ORDER_TRADE((short) 20028),
        SUBSCRIBE_GROUP_CHAT((short) 20035), UNSUBSCRIBE_GROUP_CHAT((short) 20036),
        PUSH_GROUP_CHAT((short) 20039), PUSH_EXCHANGE_DEPTH((short) 20029),
        //---------------合约-----------------
        CONTRACT_SUBSCRIBE_SYMBOL_THUMB((short) 30001),// 指令：订阅行情
        CONTRACT_PUSH_SYMBOL_THUMB((short) 30003), // 指令：推送币种行情
        SUBSCRIBE_EXCHANGE_TRADE_CONTRACT((short) 30021), // 指令：订阅交易信息（盘口，K线、成交明细）   盘口信息
        UNSUBSCRIBE_EXCHANGE_TRADE_CONTRACT((short) 30022), // 指令：取消订阅交易信息      取消盘口信息
        CONTRACT_PUSH_EXCHANGE_PLATE((short) 30024),  // 指令：推送盘口数据

        CONTRACT_UNSUBSCRIBE_SYMBOL_THUMB((short) 30002), // 指令：取消订阅行情
        CONTRACT_PUSH_EXCHANGE_TRADE((short) 30023),  // 指令：推送交易明细
        CONTRACT_PUSH_EXCHANGE_ORDER_TRADE((short) 30028), // 指令：推送订单成交信息（指定用户）

        CONTRACT_PUSH_EXCHANGE_KLINE((short) 30025), // 指令：推送K线
        CONTRACT_PUSH_EXCHANGE_ORDER_COMPLETED((short) 30026),  // 指令：推送订单完成信息（指定用户）
        CONTRACT_PUSH_EXCHANGE_ORDER_CANCELED((short) 30027), // 指令：推送订单取消信息（指定用户）

        CONTRACT_PUSH_EXCHANGE_DEPTH((short) 30029); // 指令：推送盘口深度



        private short code;

        CMD(short code) {
            this.code = code;
        }

        public short getCode() {
            return code;
        }

        public static CMD findObjByCode(short code) {
            for (CMD cmd : CMD.values()) {
                if (cmd.getCode() == code) return cmd;
            }
            return null;
        }

    }

}
