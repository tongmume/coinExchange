package com.bizzan.socket;

import android.os.Handler;
import android.os.Looper;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import com.bizzan.config.UrlConfig;

public class MarketSocket implements ISocket {

    private static MarketSocket marketSocket = null;
    private static final String lock = "lock";
    private static Socket socket = null;
    private static DataInputStream dis = null;
    private static DataOutputStream dos = null;
    private static SocketThread socketThread = null;
    public static String ip = UrlConfig.MARKET_URL;
    public static int port = 28901;

    private Handler handler;

    public static final long sequenceId = 0;//以后用于token

    public static final int requestid = 0;//请求ID

    public static final int version = 1;

    public static final String terminal = "1001";  //安卓:1001,苹果:1002,WEB:1003,PC:1004

    public MarketSocket() {
        handler = new Handler(Looper.getMainLooper());
        startListenerThread();
    }

    public static synchronized MarketSocket getInstance() {
        return marketSocket == null ? marketSocket = new MarketSocket() : marketSocket;
    }

    public synchronized void startListenerThread() {
        if (socketThread == null || !socketThread.isAlive()) {
            socketThread = new SocketThread();
            socketThread.start();
        }
    }

    public static void releaseSocket() {
        if (socket != null) {
            try {
                dis.close();
                dos.close();
                socket.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void run() {

    }

    class SocketThread extends Thread {

        private TCPCallback tcpCallback;

        @Override
        public void run() {
            synchronized (lock) {
                if (socket == null) try {
                    socket = new Socket(ip, port);
                    dis = new DataInputStream(socket.getInputStream());
                    dos = new DataOutputStream(socket.getOutputStream());
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
            }
            while (true) {
                try {
                    dealResponse(tcpCallback);
                } catch (IOException e) {
                    socketThread = null;
                    break;
                }
            }
        }

        public void setTcpCallback(TCPCallback tcpCallback) {
            this.tcpCallback = tcpCallback;
        }
    }

    @Override
    public void sendRequest(final CMD cmd, final byte[] body, TCPCallback callback) {
        socketThread.setTcpCallback(callback);
        new Thread() {
            @Override
            public void run() {
                synchronized (lock) {
                    if (socket == null) try {
                        socket = new Socket(ip, port);
                        dis = new DataInputStream(socket.getInputStream());
                        dos = new DataOutputStream(socket.getOutputStream());
                    } catch (IOException e) {
                        e.printStackTrace();
                        return;
                    }
                }
                toRequest(cmd, body);
            }
        }.start();
    }

    private static void toRequest(CMD cmd, byte[] body) {
        try {
            byte[] requestBytes = buildRequest(cmd, body);
            dos.write(requestBytes);
            dos.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static byte[] buildRequest(CMD cmd, byte[] body) {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(bos);
        try {
            int length = body == null ? 26 : (26 + body.length);
            dos.writeInt(length);
            dos.writeLong(sequenceId);
            dos.writeShort(cmd.getCode());
            dos.writeInt(version);
            byte[] terminalBytes = terminal.getBytes();
            dos.write(terminalBytes);
            dos.writeInt(requestid);
            if (body != null) dos.write(body);
            return bos.toByteArray();
        } catch (IOException ex) {
            try {
                dos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public void dealResponse(final TCPCallback tcpCallback) throws IOException {
        int length = dis.readInt();
        long sequenceId = dis.readLong();
        short code = dis.readShort();
        final int responseCode = dis.readInt();
        int requestId = dis.readInt();
        byte[] buffer = new byte[length - 22];
        final CMD cmd = CMD.findObjByCode(code);
        int nIdx = 0;
        int nReadLen = 0;
        while (nIdx < buffer.length) {
            nReadLen = dis.read(buffer, nIdx, buffer.length - nIdx);
            if (nReadLen > 0) {
                nIdx += nReadLen;
            } else {
                break;
            }
        }
        final String str = new String(buffer);
        /*Log.i("返回的固定信息", "cmd:" + code + ",length:" + length + ",sequenceId:" +
                sequenceId + ",responseCode:" + responseCode + ",sequenceId:" + requestId + ",str:" + str);*/
//
        handler.post(new Runnable() {
            @Override
            public void run() {
                if (tcpCallback == null) return;
                if (responseCode == 200) {
                    tcpCallback.dataSuccess(cmd, str);
                } else {
                    tcpCallback.dataFail(responseCode, cmd, str);
                }
            }
        });
//        if (cmd.isUnSubscrible()) {
//            synchronized (this) {
//                try {
//                    wait();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
    }

}
