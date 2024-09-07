package com.bizzan.entity;

/**
 * Created by Administrator on 2018/4/24 0024.
 */

public class Vision {


    /**
     * data : {"id":1,"publishTime":"2018-04-24 17:20:33","remark":"beta","version":"1.0.0","downloadUrl":"http://admin.bitrade.wxmarket.cn/app-release.apk","platform":0}
     * code : 0
     * message : SUCCESS
     */

    private DataBean data;
    private int code;
    private String message;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
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

    public static class DataBean {
        /**
         * id : 1
         * publishTime : 2018-04-24 17:20:33
         * remark : beta
         * version : 1.0.0
         * downloadUrl : http://admin.bitrade.wxmarket.cn/app-release.apk
         * platform : 0
         */

        private int id;
        private String publishTime;
        private String remark;
        private String version;
        private String downloadUrl;
        private int platform;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getPublishTime() {
            return publishTime;
        }

        public void setPublishTime(String publishTime) {
            this.publishTime = publishTime;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }

        public String getDownloadUrl() {
            return downloadUrl;
        }

        public void setDownloadUrl(String downloadUrl) {
            this.downloadUrl = downloadUrl;
        }

        public int getPlatform() {
            return platform;
        }

        public void setPlatform(int platform) {
            this.platform = platform;
        }
    }
}
