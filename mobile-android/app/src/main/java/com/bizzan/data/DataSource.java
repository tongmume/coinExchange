package com.bizzan.data;

import java.math.BigDecimal;

/**
 * Created by Administrator on 2017/9/25.
 */

public interface DataSource {

    void phoneCode(String phone, String country, DataCallback dataCallback);

    void emailCode(String email, DataCallback dataCallback);

    void signUpByPhone(String phone, String username, String password, String country, String code, String tuijianma, String challenge, String validate, String seccode, DataCallback dataCallback);

    void signUpByEmail(String email, String password, String tuijian2, String country, String challenge, String validate, String seccode, DataCallback dataCallback);

    void login(String username, String password, String challenge, String validate, String seccode, DataCallback dataCallback);

    void KData(String symbol, Long from, Long to, String resolution, DataCallback dataCallback);

    void KData_Constract(String symbol, Long from, Long to, String resolution, DataCallback dataCallback);

    void allCurrency(DataCallback dataCallback);

    void allHomeCurrency(DataCallback dataCallback);

    void find(String token, DataCallback dataCallback);

    void delete(String token, String symbol, DataCallback dataCallback);

    void add(String token, String symbol, DataCallback dataCallback);

    void exChange(String token, String symbol, String price, String amount, String direction, String type, DataCallback dataCallback);

    void wallet(String token, String coinName, DataCallback dataCallback);

    void all(DataCallback dataCallback);

    void advertise(int pageNo, int pageSize, String advertiseType, String id, DataCallback dataCallback);

    void country(DataCallback dataCallback);

    void create(String token, String price, String advertiseType, String coinId, String minLimit, String maxLimit, int timeLimit, String countryZhName, String priceType, String premiseRate, String remark, String number, String pay, String jyPassword, String auto, String autoword, DataCallback dataCallback);

    void uploadBase64Pic(String token, String base64Data, DataCallback dataCallback);

    void name(String token, String realName, String idCard, String idCardFront, String idCardBack, String handHeldIdCard, DataCallback dataCallback);

    void accountPwd(String token, String jyPassword, DataCallback dataCallback);

    void allAds(String token, DataCallback dataCallback);

    void release(String token, int id, DataCallback dataCallback);

    void deleteAds(String token, int id, DataCallback dataCallback);

    void offShelf(String token, int id, DataCallback dataCallback);

    void adDetail(String token, int id, DataCallback dataCallback);

    void updateAd(String token, int id, String price, String advertiseType, String coinId, String minLimit, String maxLimit, int timeLimit, String countryZhName, String priceType, String premiseRate, String remark, String number, String pay, String jyPassword, String auto, String autoword, DataCallback dataCallback);

    void c2cInfo(int id, DataCallback dataCallback);

    void c2cBuy(String token, String id, String coinId, String price, String money, String amount, String remark, String mode, DataCallback dataCallback);

    void c2cSell(String token, String id, String coinId, String price, String money, String amount, String remark, String mode, DataCallback dataCallback);

    void myOrder(String token, int status, int pageNo, int pageSize, DataCallback dataCallback);

    void myWallet(String token, DataCallback dataCallback);

    void myWallet_Constract(String token, DataCallback dataCallback);

    void RequesTrans(String token, String unit, String from, String to, String fromWalletId, String toWalletId, String amount, DataCallback dataCallback);

    void extractinfo(String token, DataCallback dataCallback);

    void extract(String token, String unit, String amount, String fee, String remark, String jyPassword, String address, String code, DataCallback dataCallback);

    void allTransaction(String token, int pageNo, int limit, int memberId, String startTime, String endTime, String symbol, String type, DataCallback dataCallback);

    void safeSetting(String token, DataCallback dataCallback);

    void avatar(String token, String url, DataCallback dataCallback);

    void bindPhone(String token, String phone, String code, String passwrd, DataCallback dataCallback);

    void sendCode(String token, String phone, DataCallback dataCallback);

    void bindEmail(String token, String email, String code, String passwrd, DataCallback dataCallback);

    void sendEmailCode(String token, String email, DataCallback dataCallback);

    void sendEditLoginPwdCode(String token, DataCallback dataCallback);

    void editPwd(String token, String oldPassword, String newPassword, String code, DataCallback dataCallback);

    void plate(String symbol, DataCallback dataCallback);

    void entrust(String token, int pageSize, int pageNo, String symbol, DataCallback dataCallback);

    void cancleEntrust(String token, String orderId, DataCallback dataCallback);

    void phoneForgotCode(String phone, String challenge, String validate, String seccode, DataCallback dataCallback);

    void forgotPwd(String account, String code, String mode, String password, DataCallback dataCallback);

    void emailForgotCode(String phone, String challenge, String validate, String seccode, DataCallback dataCallback);

    void captch(DataCallback dataCallback);

    void sendChangePhoneCode(String token, DataCallback dataCallback);

    void changePhone(String token, String password, String phone, String code, DataCallback dataCallback);

    void message(int pageNo, int pageSize, DataCallback dataCallback);

    void messageDetail(String id, DataCallback dataCallback);

    void remark(String token, String remark, DataCallback dataCallback);

    void appInfo(DataCallback dataCallback);

    void banners(String sysAdvertiseLocation, DataCallback dataCallback);

    void orderDetail(String token, String orderSn, DataCallback dataCallback);

    void cancle(String token, String orderSn, DataCallback dataCallback);

    void payDone(String token, String orderSn, DataCallback dataCallback);

    void releaseOrder(String token, String orderSn, String jyPassword, DataCallback dataCallback);

    void appeal(String token, String remark, String orderSn, DataCallback dataCallback);

    void editAccountPed(String token, String newPassword, String oldPassword, DataCallback dataCallback);

    void resetAccountPwd(String token, String newPassword, String code, DataCallback dataCallback);

    void resetAccountPwdCode(String token, DataCallback dataCallback);

    void getHistoryMessage(String token, String orderId, int pageNo, int pageSize, DataCallback dataCallback);

    void getEntrustHistory(String token, String symbol, int pageNo, int pageSize, DataCallback dataCallback);

    void getCreditInfo(String token, DataCallback dataCallback);

    void getNewVision(String token, DataCallback dataCallback);

    void getSymbol(DataCallback dataCallback);

    void getAccountSetting(String token, DataCallback dataCallback);

    void getBindBank(String token, String bank, String branch, String jyPassword, String realName, String cardNo, DataCallback dataCallback);

    void getUpdateBank(String token, String bank, String branch, String jyPassword, String realName, String cardNo, DataCallback dataCallback);

    void getBindWeiChat(String token, String wechat, String jyPassword, String realName, String qrCodeUrl, DataCallback dataCallback);

    void getUpdateWeiChat(String token, String wechat, String jyPassword, String realName, String qrCodeUrl, DataCallback dataCallback);

    void getBindAli(String token, String ali, String jyPassword, String realName, String qrCodeUrl, DataCallback dataCallback);

    void getUpdateAli(String token, String ali, String jyPassword, String realName, String qrCodeUrl, DataCallback dataCallback);

    void getCheckMatch(String token, DataCallback dataCallback);

    void getStartMatch(String token, String amount, DataCallback dataCallback);

    void getPromotion(String token, String page, String number, DataCallback dataCallback);

    void getPromotionReward(String token, String page, String number, DataCallback dataCallback);

    void ctcOrderList(String token, int pageNo, int pageSize, DataCallback dataCallback);

    void ctcOrderDetail(String token, Long oid, DataCallback dataCallback);

    void ctcOrderCancel(String token, Long oid, DataCallback dataCallback);

    void ctcOrderPay(String token, Long oid, DataCallback dataCallback);

    void ctcPrice(DataCallback dataCallback);

    void ctcNewOrder(String token, BigDecimal price, BigDecimal amount, String payType, int direction, String unit, String fundpwd, String code, DataCallback dataCallback);

    void ctcSendNewOrderPhoneCode(String token, DataCallback dataCallback);

    void myPromotion(String token, DataCallback dataCallback);


    void getDepositList(String token, DataCallback dataCallback);

    void commitSellerApply(String token, String coinId, String json, DataCallback dataCallback);

    void myWalletUsdt(String token, DataCallback dataCallback);


    interface DataCallback {

        void onDataLoaded(Object obj);

        void onDataNotAvailable(Integer code, String toastMessage);
    }
}
