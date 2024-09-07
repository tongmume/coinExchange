package com.bizzan.ui.wallet;


import com.bizzan.socket.ISocket;


public class WalletMessage {
    public WalletMessage(String coin) {
        this.coin = coin;
    }

    private String coin;

    public String getCoin() {
        return coin;
    }

    public void setCoin(String coin) {
        this.coin = coin;
    }
}
