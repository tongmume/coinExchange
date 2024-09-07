//package com.bizzan.serivce;
//
//import android.app.Service;
//import android.content.Intent;
//import android.os.IBinder;
//import android.support.annotation.Nullable;
//import android.util.Log;
//
//import org.greenrobot.eventbus.EventBus;
//import org.json.JSONObject;
//
//import com.bizzan.entity.ChatEvent;
//import com.bizzan.entity.MarketEvent;
//import com.bizzan.socket.C2CSocket;
//import com.bizzan.socket.ISocket;
//import com.bizzan.socket.MarketSocket;
//import com.bizzan.utils.WonderfulLogUtils;
//
///**
// * Created by Administrator on 2018/4/19 0019.
// */
//
//public class MyBindService extends Service {
//
//    private int socket;
//    private ISocket.CMD cmd;
//    public static final int MARKET = 1;
//    public static final int CHAT = 2;
//    private int type;
//    private String orderId;
//    private String uid;
//    private static C2CSocket c2CSocket = new C2CSocket();
//    private static MarketSocket martketSocket = new MarketSocket();
//
//    @Override
//    public void onCreate() {
//        super.onCreate();
//       // c2CSocket = new C2CSocket();
//    }
//
//    /*public static C2CSocket getInstance(){
//        if (c2CSocket == null) {
//            c2CSocket = new C2CSocket();
//        }
//        //WonderfulLogUtils.logi("MyBindService",String.valueOf(c2CSocket.hashCode()));
//        return c2CSocket == null ? new C2CSocket() : c2CSocket;
//    }*/
//
//    @Override
//    public int onStartCommand(Intent intent, int flags, int startId) {
//        try{
//            type = intent.getIntExtra("type",0);
//            switch (type) {
//                case MyBindService.MARKET:
//                    startTCP();
//                    break;
//                case MyBindService.CHAT:
//                    orderId = intent.getStringExtra("orderId");
//                    uid = intent.getStringExtra("uid");
//                    startChat();
//                    break;
//            }
//        }catch (Exception e){}
//
//        return super.onStartCommand(intent, flags, startId);
//    }
//
//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//        switch (type) {
//            case MyBindService.MARKET:
//                stopTCP();
//                break;
//            case MyBindService.CHAT:
//                stopChat();
//                break;
//        }
//    }
//
//    @Nullable
//    @Override
//    public IBinder onBind(Intent intent) {
//        return null;
//    }
//
//    private void startTCP() {
//        Log.i("MyBindService startTCP", "send request(SUBSCRIBE_SYMBOL_THUMB)");
//        EventBus.getDefault().post(new SocketMessage(0, ISocket.CMD.SUBSCRIBE_SYMBOL_THUMB, null)); // 开始订阅
//        /*
//        martketSocket.sendRequest(
//                ISocket.CMD.SUBSCRIBE_SYMBOL_THUMB, null, new ISocket.TCPCallback() {
//                    @Override
//                    public void dataSuccess(ISocket.CMD cmd, String response) {
//                        //WonderfulLogUtils.logi(cmd + "  MyBindSrevice订阅成功回执", response);
//                        //thumbSuccess(cmd, response);
//                        if (cmd == ISocket.CMD.PUSH_SYMBOL_THUMB) {
//                            MarketEvent marketEvent = new MarketEvent();
//                            marketEvent.setResonpce(response);
//                            EventBus.getDefault().post(marketEvent);
//                        }
//                    }
//
//                    @Override
//                    public void dataFail(int code, ISocket.CMD cmd, String errorInfo) {
//                        WonderfulLogUtils.logi(cmd + "  MyBindSrevice订阅失败回执", errorInfo);
//                    }
//                });
//         */
//    }
//
//    private void startChat() {
//        c2CSocket.sendRequest(
//                ISocket.CMD.SUBSCRIBE_CHAT, buildGetBodyJson().toString().getBytes(), new ISocket.TCPCallback() {
//                    @Override
//                    public void dataSuccess(ISocket.CMD cmd, String response) {
//                        WonderfulLogUtils.logi(cmd + "  MyBindSrevice订阅成功回执", response);
//                        if (cmd == ISocket.CMD.PUSH_CHAT) {
//                            ChatEvent chatEvent = new ChatEvent();
//                            chatEvent.setResonpce(response);
//                            chatEvent.setType("left");
//                            chatEvent.setCmd(cmd);
//                            EventBus.getDefault().post(chatEvent);
//                        }else if(cmd == ISocket.CMD.SEND_CHAT){
//                            ChatEvent chatEvent = new ChatEvent();
//                            chatEvent.setResonpce(response);
//                            chatEvent.setType("right");
//                            chatEvent.setCmd(ISocket.CMD.SEND_CHAT);
//                            EventBus.getDefault().post(chatEvent);
//                        }
//                    }
//
//                    @Override
//                    public void dataFail(int code, ISocket.CMD cmd, String errorInfo) {
//                        WonderfulLogUtils.logi(cmd + "  MyBindSrevice订阅失败回执", errorInfo);
//                    }
//                });
//    }
//
//    private void stopTCP(){
//        martketSocket.sendRequest(ISocket.CMD.UNSUBSCRIBE_SYMBOL_THUMB, null, new ISocket.TCPCallback() {
//            @Override
//            public void dataSuccess(ISocket.CMD cmd, String response) {
//                //WonderfulLogUtils.logi(cmd + "  取消订阅成功回执", response);
//            }
//
//            @Override
//            public void dataFail(int code, ISocket.CMD cmd, String errorInfo) {
//                WonderfulLogUtils.logi(cmd + "  取消订阅失败回执", errorInfo);
//            }
//        });
//    }
//
//    private void stopChat(){
//        c2CSocket.sendRequest(ISocket.CMD.UNSUBSCRIBE_CHAT, buildGetBodyJson().toString().getBytes(), new ISocket.TCPCallback() {
//            @Override
//            public void dataSuccess(ISocket.CMD cmd, String response) {
//                WonderfulLogUtils.logi(cmd + "  取消订阅成功回执", response);
//            }
//
//            @Override
//            public void dataFail(int code, ISocket.CMD cmd, String errorInfo) {
//                WonderfulLogUtils.logi(cmd + "  取消订阅失败回执", errorInfo);
//            }
//        });
//    }
//
//    private JSONObject buildGetBodyJson() {
//        JSONObject obj = new JSONObject();
//        try {
//            obj.put("orderId", orderId);
//            //obj.put("uid", orderDetial.getMyId());
//            obj.put("uid", uid);
//            return obj;
//        } catch (Exception ex) {
//            return null;
//        }
//    }
//
//}
