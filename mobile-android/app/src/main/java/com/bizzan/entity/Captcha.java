package com.bizzan.entity;

/**
 * Created by Administrator on 2018/3/20.
 */

public class Captcha {

    /**
     * geetest_challenge : 3abc805694694245af02535adbc311aeic
     * geetest_validate : 5dd24ed2a07a520072a533591daffd56
     * geetest_seccode : 5dd24ed2a07a520072a533591daffd56|jordan
     */

    private String geetest_challenge;
    private String geetest_validate;
    private String geetest_seccode;

    public String getGeetest_challenge() {
        return geetest_challenge;
    }

    public void setGeetest_challenge(String geetest_challenge) {
        this.geetest_challenge = geetest_challenge;
    }

    public String getGeetest_validate() {
        return geetest_validate;
    }

    public void setGeetest_validate(String geetest_validate) {
        this.geetest_validate = geetest_validate;
    }

    public String getGeetest_seccode() {
        return geetest_seccode;
    }

    public void setGeetest_seccode(String geetest_seccode) {
        this.geetest_seccode = geetest_seccode;
    }
}
