package com.bizzan.entity;


import com.bizzan.socket.ISocket;

/**
 * Created by Administrator on 2018/4/20 0020.
 */

public class ChatEvent {
    private String resonpce;
    private String type;
    private ISocket.CMD cmd;

    public ISocket.CMD getCmd() {
        return cmd;
    }

    public void setCmd(ISocket.CMD cmd) {
        this.cmd = cmd;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getResonpce() {
        return resonpce;
    }

    public void setResonpce(String resonpce) {
        this.resonpce = resonpce;
    }
}
