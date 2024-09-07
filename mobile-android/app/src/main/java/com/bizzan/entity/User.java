package com.bizzan.entity;


import java.io.Serializable;

/**
 * Created by Administrator on 2018/1/26.
 */

public class User   implements Serializable {
    private String token;
    private String username;
    private Location location;
    private int memberLevel;
    private String realName;
    private boolean isSelect;
    private Country country;
    private String avatar;
    private int id;
    private String promotionPrefix;
    private String promotionCode;
    private int firstLevel;
    private int secondLevel;
    private int thirdLevel;

    public String getPromotionPrefix() {
        return promotionPrefix;
    }

    public void setPromotionPrefix(String promotionPrefix) {
        this.promotionPrefix = promotionPrefix;
    }

    public String getPromotionCode() {
        return promotionCode;
    }

    public void setPromotionCode(String promotionCode) {
        this.promotionCode = promotionCode;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setFirstLevel(int firstLevel) {this.firstLevel = firstLevel;}
    public void setSecondLevel(int secondLevel) {this.secondLevel = secondLevel;}
    public void setThirdLevel(int thirdLevel) {this.thirdLevel = thirdLevel;}

    public int getFirstLevel(){return this.firstLevel;}
    public int getSecondLevel(){return this.secondLevel;}
    public int getThirdLevel(){return this.thirdLevel;}

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public int getMemberLevel() {
        return memberLevel;
    }

    public void setMemberLevel(int memberLevel) {
        this.memberLevel = memberLevel;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    @Override
    public String toString() {
        return "User{" +
                "token='" + token + '\'' +
                ", username='" + username + '\'' +
                ", location=" + location +
                ", memberLevel=" + memberLevel +
                ", realName='" + realName + '\'' +
                ", isSelect=" + isSelect +
                '}';
    }

    public static class Location implements Serializable {

        private String country;
        private String province;
        private String city;
        private String district;


        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getDistrict() {
            return district;
        }

        public void setDistrict(String district) {
            this.district = district;
        }
    }

}
