package com.bizzan.serivce;


import com.bizzan.socket.ISocket;

/**
 * author: wuzongjie
 * time  : 2018/5/4 0004 09:35
 * desc  :
 */

public class SocketResponse {

    private ISocket.CMD cmd; // 传的指令
    private String response; // 返回的参数

    public SocketResponse(ISocket.CMD cmd, String response) {
        this.cmd = cmd;
        this.response = response;
    }

    public ISocket.CMD getCmd() {
        return cmd;
    }

    public void setCmd(ISocket.CMD cmd) {
        this.cmd = cmd;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}
