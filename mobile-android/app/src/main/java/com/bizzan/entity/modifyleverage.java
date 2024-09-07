package com.bizzan.entity;

/**
 * data: 2020/9/1.
 */
public class modifyleverage {

    /**
     * data : null
     * code : 500
     * message : 您有正在委托的单，请先撤销
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
