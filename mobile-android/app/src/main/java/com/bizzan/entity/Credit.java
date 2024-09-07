package com.bizzan.entity;

/**
 * Created by Administrator on 2018/4/24 0024.
 */

public class Credit {

    /**
     * data : {"id":30,"realName":"陈琪","idCard":"342524199207256222","identityCardImgFront":"http://xinhuo-xindai.oss-cn-hangzhou.aliyuncs.com/2018/03/21/556dc806-edae-4494-83d0-2cae6a69dda8.jpg","identityCardImgReverse":"http://xinhuo-xindai.oss-cn-hangzhou.aliyuncs.com/2018/03/21/4eebd775-b8bf-458b-8271-75759958e547.jpg","identityCardImgInHand":"http://xinhuo-xindai.oss-cn-hangzhou.aliyuncs.com/2018/03/21/2a08c78d-39de-4d5b-8641-c6417c0c15fa.jpg","auditStatus":2,"member":{"id":51,"username":"陈七七","realName":"陈琪","idNumber":"342524199207256222","email":"1030726326@qq.com","mobilePhone":"18255176187","location":{"country":"中国","province":null,"city":null,"district":null},"memberLevel":2,"status":0,"registrationTime":"2018-03-21 13:34:34","lastLoginTime":null,"token":"ada1db4e213f9945f6ea3eccbcdccd2a","transactions":29,"bankInfo":null,"alipay":{"aliNo":"18255478978","qrCodeUrl":null},"wechatPay":null,"appealTimes":0,"appealSuccessTimes":0,"inviterId":null,"promotionCode":"U0000517T","firstLevel":1,"secondLevel":0,"thirdLevel":0,"country":{"zhName":"中国","enName":"China","areaCode":"86","language":"zh_CN","localCurrency":"CNY","sort":0},"realNameStatus":2,"loginCount":0,"certifiedBusinessStatus":2,"certifiedBusinessApplyTime":"2018-03-28 17:58:41","avatar":"http://caymanex.oss-cn-hangzhou.aliyuncs.com/2018/03/27/8b76070d-4908-4f0e-b7f9-e67dc1d56c45.jpg","tokenExpireTime":"2018-05-01 09:38:53"},"rejectReason":null,"createTime":"2018-03-21 14:08:14","updateTime":"2018-03-21 14:08:40"}
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
         * id : 30
         * realName : 陈琪
         * idCard : 342524199207256222
         * identityCardImgFront : http://xinhuo-xindai.oss-cn-hangzhou.aliyuncs.com/2018/03/21/556dc806-edae-4494-83d0-2cae6a69dda8.jpg
         * identityCardImgReverse : http://xinhuo-xindai.oss-cn-hangzhou.aliyuncs.com/2018/03/21/4eebd775-b8bf-458b-8271-75759958e547.jpg
         * identityCardImgInHand : http://xinhuo-xindai.oss-cn-hangzhou.aliyuncs.com/2018/03/21/2a08c78d-39de-4d5b-8641-c6417c0c15fa.jpg
         * auditStatus : 2
         * member : {"id":51,"username":"陈七七","realName":"陈琪","idNumber":"342524199207256222","email":"1030726326@qq.com","mobilePhone":"18255176187","location":{"country":"中国","province":null,"city":null,"district":null},"memberLevel":2,"status":0,"registrationTime":"2018-03-21 13:34:34","lastLoginTime":null,"token":"ada1db4e213f9945f6ea3eccbcdccd2a","transactions":29,"bankInfo":null,"alipay":{"aliNo":"18255478978","qrCodeUrl":null},"wechatPay":null,"appealTimes":0,"appealSuccessTimes":0,"inviterId":null,"promotionCode":"U0000517T","firstLevel":1,"secondLevel":0,"thirdLevel":0,"country":{"zhName":"中国","enName":"China","areaCode":"86","language":"zh_CN","localCurrency":"CNY","sort":0},"realNameStatus":2,"loginCount":0,"certifiedBusinessStatus":2,"certifiedBusinessApplyTime":"2018-03-28 17:58:41","avatar":"http://caymanex.oss-cn-hangzhou.aliyuncs.com/2018/03/27/8b76070d-4908-4f0e-b7f9-e67dc1d56c45.jpg","tokenExpireTime":"2018-05-01 09:38:53"}
         * rejectReason : null
         * createTime : 2018-03-21 14:08:14
         * updateTime : 2018-03-21 14:08:40
         */

        private int id;
        private String realName;
        private String idCard;
        private String identityCardImgFront;
        private String identityCardImgReverse;
        private String identityCardImgInHand;
        private int auditStatus;
        private MemberBean member;
        private Object rejectReason;
        private String createTime;
        private String updateTime;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getRealName() {
            return realName;
        }

        public void setRealName(String realName) {
            this.realName = realName;
        }

        public String getIdCard() {
            return idCard;
        }

        public void setIdCard(String idCard) {
            this.idCard = idCard;
        }

        public String getIdentityCardImgFront() {
            return identityCardImgFront;
        }

        public void setIdentityCardImgFront(String identityCardImgFront) {
            this.identityCardImgFront = identityCardImgFront;
        }

        public String getIdentityCardImgReverse() {
            return identityCardImgReverse;
        }

        public void setIdentityCardImgReverse(String identityCardImgReverse) {
            this.identityCardImgReverse = identityCardImgReverse;
        }

        public String getIdentityCardImgInHand() {
            return identityCardImgInHand;
        }

        public void setIdentityCardImgInHand(String identityCardImgInHand) {
            this.identityCardImgInHand = identityCardImgInHand;
        }

        public int getAuditStatus() {
            return auditStatus;
        }

        public void setAuditStatus(int auditStatus) {
            this.auditStatus = auditStatus;
        }

        public MemberBean getMember() {
            return member;
        }

        public void setMember(MemberBean member) {
            this.member = member;
        }

        public Object getRejectReason() {
            return rejectReason;
        }

        public void setRejectReason(Object rejectReason) {
            this.rejectReason = rejectReason;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }

        public static class MemberBean {
            /**
             * id : 51
             * username : 陈七七
             * realName : 陈琪
             * idNumber : 342524199207256222
             * email : 1030726326@qq.com
             * mobilePhone : 18255176187
             * location : {"country":"中国","province":null,"city":null,"district":null}
             * memberLevel : 2
             * status : 0
             * registrationTime : 2018-03-21 13:34:34
             * lastLoginTime : null
             * token : ada1db4e213f9945f6ea3eccbcdccd2a
             * transactions : 29
             * bankInfo : null
             * alipay : {"aliNo":"18255478978","qrCodeUrl":null}
             * wechatPay : null
             * appealTimes : 0
             * appealSuccessTimes : 0
             * inviterId : null
             * promotionCode : U0000517T
             * firstLevel : 1
             * secondLevel : 0
             * thirdLevel : 0
             * country : {"zhName":"中国","enName":"China","areaCode":"86","language":"zh_CN","localCurrency":"CNY","sort":0}
             * realNameStatus : 2
             * loginCount : 0
             * certifiedBusinessStatus : 2
             * certifiedBusinessApplyTime : 2018-03-28 17:58:41
             * avatar : http://caymanex.oss-cn-hangzhou.aliyuncs.com/2018/03/27/8b76070d-4908-4f0e-b7f9-e67dc1d56c45.jpg
             * tokenExpireTime : 2018-05-01 09:38:53
             */

            private int id;
            private String username;
            private String realName;
            private String idNumber;
            private String email;
            private String mobilePhone;
            private LocationBean location;
            private int memberLevel;
            private int status;
            private String registrationTime;
            private Object lastLoginTime;
            private String token;
            private int transactions;
            private Object bankInfo;
            private AlipayBean alipay;
            private Object wechatPay;
            private int appealTimes;
            private int appealSuccessTimes;
            private Object inviterId;
            private String promotionCode;
            private int firstLevel;
            private int secondLevel;
            private int thirdLevel;
            private CountryBean country;
            private int realNameStatus;
            private int loginCount;
            private int certifiedBusinessStatus;
            private String certifiedBusinessApplyTime;
            private String avatar;
            private String tokenExpireTime;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getUsername() {
                return username;
            }

            public void setUsername(String username) {
                this.username = username;
            }

            public String getRealName() {
                return realName;
            }

            public void setRealName(String realName) {
                this.realName = realName;
            }

            public String getIdNumber() {
                return idNumber;
            }

            public void setIdNumber(String idNumber) {
                this.idNumber = idNumber;
            }

            public String getEmail() {
                return email;
            }

            public void setEmail(String email) {
                this.email = email;
            }

            public String getMobilePhone() {
                return mobilePhone;
            }

            public void setMobilePhone(String mobilePhone) {
                this.mobilePhone = mobilePhone;
            }

            public LocationBean getLocation() {
                return location;
            }

            public void setLocation(LocationBean location) {
                this.location = location;
            }

            public int getMemberLevel() {
                return memberLevel;
            }

            public void setMemberLevel(int memberLevel) {
                this.memberLevel = memberLevel;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public String getRegistrationTime() {
                return registrationTime;
            }

            public void setRegistrationTime(String registrationTime) {
                this.registrationTime = registrationTime;
            }

            public Object getLastLoginTime() {
                return lastLoginTime;
            }

            public void setLastLoginTime(Object lastLoginTime) {
                this.lastLoginTime = lastLoginTime;
            }

            public String getToken() {
                return token;
            }

            public void setToken(String token) {
                this.token = token;
            }

            public int getTransactions() {
                return transactions;
            }

            public void setTransactions(int transactions) {
                this.transactions = transactions;
            }

            public Object getBankInfo() {
                return bankInfo;
            }

            public void setBankInfo(Object bankInfo) {
                this.bankInfo = bankInfo;
            }

            public AlipayBean getAlipay() {
                return alipay;
            }

            public void setAlipay(AlipayBean alipay) {
                this.alipay = alipay;
            }

            public Object getWechatPay() {
                return wechatPay;
            }

            public void setWechatPay(Object wechatPay) {
                this.wechatPay = wechatPay;
            }

            public int getAppealTimes() {
                return appealTimes;
            }

            public void setAppealTimes(int appealTimes) {
                this.appealTimes = appealTimes;
            }

            public int getAppealSuccessTimes() {
                return appealSuccessTimes;
            }

            public void setAppealSuccessTimes(int appealSuccessTimes) {
                this.appealSuccessTimes = appealSuccessTimes;
            }

            public Object getInviterId() {
                return inviterId;
            }

            public void setInviterId(Object inviterId) {
                this.inviterId = inviterId;
            }

            public String getPromotionCode() {
                return promotionCode;
            }

            public void setPromotionCode(String promotionCode) {
                this.promotionCode = promotionCode;
            }

            public int getFirstLevel() {
                return firstLevel;
            }

            public void setFirstLevel(int firstLevel) {
                this.firstLevel = firstLevel;
            }

            public int getSecondLevel() {
                return secondLevel;
            }

            public void setSecondLevel(int secondLevel) {
                this.secondLevel = secondLevel;
            }

            public int getThirdLevel() {
                return thirdLevel;
            }

            public void setThirdLevel(int thirdLevel) {
                this.thirdLevel = thirdLevel;
            }

            public CountryBean getCountry() {
                return country;
            }

            public void setCountry(CountryBean country) {
                this.country = country;
            }

            public int getRealNameStatus() {
                return realNameStatus;
            }

            public void setRealNameStatus(int realNameStatus) {
                this.realNameStatus = realNameStatus;
            }

            public int getLoginCount() {
                return loginCount;
            }

            public void setLoginCount(int loginCount) {
                this.loginCount = loginCount;
            }

            public int getCertifiedBusinessStatus() {
                return certifiedBusinessStatus;
            }

            public void setCertifiedBusinessStatus(int certifiedBusinessStatus) {
                this.certifiedBusinessStatus = certifiedBusinessStatus;
            }

            public String getCertifiedBusinessApplyTime() {
                return certifiedBusinessApplyTime;
            }

            public void setCertifiedBusinessApplyTime(String certifiedBusinessApplyTime) {
                this.certifiedBusinessApplyTime = certifiedBusinessApplyTime;
            }

            public String getAvatar() {
                return avatar;
            }

            public void setAvatar(String avatar) {
                this.avatar = avatar;
            }

            public String getTokenExpireTime() {
                return tokenExpireTime;
            }

            public void setTokenExpireTime(String tokenExpireTime) {
                this.tokenExpireTime = tokenExpireTime;
            }

            public static class LocationBean {
                /**
                 * country : 中国
                 * province : null
                 * city : null
                 * district : null
                 */

                private String country;
                private Object province;
                private Object city;
                private Object district;

                public String getCountry() {
                    return country;
                }

                public void setCountry(String country) {
                    this.country = country;
                }

                public Object getProvince() {
                    return province;
                }

                public void setProvince(Object province) {
                    this.province = province;
                }

                public Object getCity() {
                    return city;
                }

                public void setCity(Object city) {
                    this.city = city;
                }

                public Object getDistrict() {
                    return district;
                }

                public void setDistrict(Object district) {
                    this.district = district;
                }
            }

            public static class AlipayBean {
                /**
                 * aliNo : 18255478978
                 * qrCodeUrl : null
                 */

                private String aliNo;
                private Object qrCodeUrl;

                public String getAliNo() {
                    return aliNo;
                }

                public void setAliNo(String aliNo) {
                    this.aliNo = aliNo;
                }

                public Object getQrCodeUrl() {
                    return qrCodeUrl;
                }

                public void setQrCodeUrl(Object qrCodeUrl) {
                    this.qrCodeUrl = qrCodeUrl;
                }
            }

            public static class CountryBean {
                /**
                 * zhName : 中国
                 * enName : China
                 * areaCode : 86
                 * language : zh_CN
                 * localCurrency : CNY
                 * sort : 0
                 */

                private String zhName;
                private String enName;
                private String areaCode;
                private String language;
                private String localCurrency;
                private int sort;

                public String getZhName() {
                    return zhName;
                }

                public void setZhName(String zhName) {
                    this.zhName = zhName;
                }

                public String getEnName() {
                    return enName;
                }

                public void setEnName(String enName) {
                    this.enName = enName;
                }

                public String getAreaCode() {
                    return areaCode;
                }

                public void setAreaCode(String areaCode) {
                    this.areaCode = areaCode;
                }

                public String getLanguage() {
                    return language;
                }

                public void setLanguage(String language) {
                    this.language = language;
                }

                public String getLocalCurrency() {
                    return localCurrency;
                }

                public void setLocalCurrency(String localCurrency) {
                    this.localCurrency = localCurrency;
                }

                public int getSort() {
                    return sort;
                }

                public void setSort(int sort) {
                    this.sort = sort;
                }
            }
        }
    }
}
