package com.bizzan.serivce;


import com.bizzan.socket.ISocket;

/**
 * author: wuzongjie
 * time  : 2018/4/27 0027 11:13
 * desc  :
 */

public class SocketMessage {

    private int code; // 0 为行情的socket
    private ISocket.CMD cmd; // 传的指令
    private byte[] body; // 参数


    public SocketMessage(ISocket.CMD cmd, String response) {
        this.cmd = cmd;
    }

    public SocketMessage(int code, ISocket.CMD cmd, byte[] body) {
        this.code = code;
        this.cmd = cmd;
        this.body = body;
    }



    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public ISocket.CMD getCmd() {
        return cmd;
    }

    public void setCmd(ISocket.CMD cmd) {
        this.cmd = cmd;
    }

    public byte[] getBody() {
        return body;
    }

    public void setBody(byte[] body) {
        this.body = body;
    }
}
