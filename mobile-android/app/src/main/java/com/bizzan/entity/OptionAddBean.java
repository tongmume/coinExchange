package com.bizzan.entity;

/**
 * data: 2020/10/20.
 */
public class OptionAddBean {

    /**
     * data : null
     * code : 0
     * message : 参与成功
     * totalPage : null
     * totalElement : null
     */

    private Object data;
    private int code;
    private String message;
    private Object totalPage;
    private Object totalElement;

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Object totalPage) {
        this.totalPage = totalPage;
    }

    public Object getTotalElement() {
        return totalElement;
    }

    public void setTotalElement(Object totalElement) {
        this.totalElement = totalElement;
    }
}
