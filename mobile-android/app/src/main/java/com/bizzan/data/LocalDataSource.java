package com.bizzan.data;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;

import java.math.BigDecimal;


/**
 * 当需要使用缓存等时 需要使用Local该类加载数据
 */

public class LocalDataSource implements DataSource {
    private static LocalDataSource INSTANCE;
    private Handler handler = new Handler(Looper.getMainLooper());

    public LocalDataSource(Context context) {
    }

    public static LocalDataSource getInstance(@NonNull Context context) {
        if (INSTANCE == null) {
            INSTANCE = new LocalDataSource(context);
        }
        return INSTANCE;
    }


    @Override
    public void phoneCode(String phone, String country, DataCallback dataCallback) {

    }

    @Override
    public void emailCode(String email,  DataCallback dataCallback) {

    }

    @Override
    public void signUpByPhone(String phone, String username, String password, String country, String code,String tuijianma,String challenge, String validate, String seccode, DataCallback dataCallback) {

    }

    @Override
    public void signUpByEmail(String email, String password, String tuijian2,String country, String challenge, String validate, String seccode, DataCallback dataCallback) {

    }

    @Override
    public void login(String username, String password, String challenge, String validate, String seccode, DataCallback dataCallback) {

    }

    @Override
    public void KData(String symbol, Long from, Long to, String resolution, DataCallback dataCallback) {

    }

    @Override
    public void KData_Constract(String symbol, Long from, Long to, String resolution, DataCallback dataCallback) {

    }

    @Override
    public void allCurrency(DataCallback dataCallback) {

    }

    @Override
    public void allHomeCurrency(DataCallback dataCallback) {

    }

    @Override
    public void find(String token, DataCallback dataCallback) {

    }

    @Override
    public void delete(String token, String symbol, DataCallback dataCallback) {

    }

    @Override
    public void add(String token, String symbol, DataCallback dataCallback) {

    }

    @Override
    public void exChange(String token, String symbol, String price, String amount, String direction, String type, DataCallback dataCallback) {

    }

    @Override
    public void wallet(String token, String coinName, DataCallback dataCallback) {

    }

    @Override
    public void all(DataCallback dataCallback) {

    }

    @Override
    public void advertise(int pageNo, int pageSize, String advertiseType, String id, DataCallback dataCallback) {

    }

    @Override
    public void country(DataCallback dataCallback) {

    }

    @Override
    public void create(String token, String price, String advertiseType, String coinId, String minLimit, String maxLimit, int timeLimit, String countryZhName, String priceType, String premiseRate, String remark, String number, String pay, String jyPassword, String auto, String autoword, DataCallback dataCallback) {

    }

    @Override
    public void uploadBase64Pic(String token, String base64Data, DataCallback dataCallback) {

    }

    @Override
    public void name(String token, String realName, String idCard, String idCardFront, String idCardBack, String handHeldIdCard, DataCallback dataCallback) {

    }

    @Override
    public void accountPwd(String token, String jyPassword, DataCallback dataCallback) {

    }

    @Override
    public void allAds(String token, DataCallback dataCallback) {

    }

    @Override
    public void release(String token, int id, DataCallback dataCallback) {

    }

    @Override
    public void deleteAds(String token, int id, DataCallback dataCallback) {

    }

    @Override
    public void offShelf(String token, int id, DataCallback dataCallback) {

    }

    @Override
    public void adDetail(String token, int id, DataCallback dataCallback) {

    }

    @Override
    public void updateAd(String token, int id, String price, String advertiseType, String coinId, String minLimit, String maxLimit, int timeLimit, String countryZhName, String priceType, String premiseRate, String remark, String number, String pay, String jyPassword, String auto, String autoword, DataCallback dataCallback) {

    }

    @Override
    public void c2cInfo(int id, DataCallback dataCallback) {

    }

    @Override
    public void c2cBuy(String token, String id, String coinId, String price, String money, String amount, String remark,String mode, DataCallback dataCallback) {

    }

    @Override
    public void c2cSell(String token, String id, String coinId, String price, String money, String amount, String remark,String mode, DataCallback dataCallback) {

    }

    @Override
    public void myOrder(String token, int status, int pageNo, int pageSize, DataCallback dataCallback) {

    }

    @Override
    public void myWallet(String token, DataCallback dataCallback) {

    }

    @Override
    public void myWallet_Constract(String token, DataCallback dataCallback) {

    }

    @Override
    public void RequesTrans(String token, String unit, String from, String to, String fromWalletId, String toWalletId, String amount, DataCallback dataCallback) {

    }

    @Override
    public void extractinfo(String token, DataCallback dataCallback) {

    }

    @Override
    public void extract(String token, String unit, String amount, String fee, String remark, String jyPassword, String address,String code, DataCallback dataCallback) {

    }

    @Override
    public void allTransaction(String token, int pageNo, int pageSize,int memberId,String startTime,String endTime,String symbol,String type, DataCallback dataCallback) {

    }

    @Override
    public void safeSetting(String token, DataCallback dataCallback) {

    }

    @Override
    public void avatar(String token, String url, DataCallback dataCallback) {

    }

    @Override
    public void bindPhone(String token, String phone, String code, String passwrd, DataCallback dataCallback) {

    }

    @Override
    public void sendCode(String token, String phone, DataCallback dataCallback) {

    }

    @Override
    public void bindEmail(String token, String email, String code, String passwrd, DataCallback dataCallback) {

    }

    @Override
    public void sendEmailCode(String token, String email, DataCallback dataCallback) {

    }

    @Override
    public void sendEditLoginPwdCode(String token, DataCallback dataCallback) {

    }

    @Override
    public void editPwd(String token, String oldPassword, String newPassword, String code, DataCallback dataCallback) {

    }

    @Override
    public void plate(String symbol, DataCallback dataCallback) {

    }

    @Override
    public void entrust(String token, int pageSize, int pageNo, String symbol, DataCallback dataCallback) {


    }

    @Override
    public void cancleEntrust(String token, String orderId, DataCallback dataCallback) {

    }

    @Override
    public void phoneForgotCode(String phone, String challenge, String validate, String seccode, DataCallback dataCallback) {

    }

    @Override
    public void forgotPwd(String account, String code, String mode, String password, DataCallback dataCallback) {

    }

    @Override
    public void emailForgotCode(String phone, String challenge, String validate, String seccode, DataCallback dataCallback) {

    }

    @Override
    public void captch(DataCallback dataCallback) {

    }

    @Override
    public void sendChangePhoneCode(String token, DataCallback dataCallback) {

    }

    @Override
    public void changePhone(String token, String password, String phone, String code, DataCallback dataCallback) {

    }

    @Override
    public void message(int pageNo, int pageSize, DataCallback dataCallback) {

    }

    @Override
    public void messageDetail(String id, DataCallback dataCallback) {

    }

    @Override
    public void remark(String token, String remark, DataCallback dataCallback) {

    }

    @Override
    public void appInfo(DataCallback dataCallback) {

    }

    @Override
    public void banners(String sysAdvertiseLocation, DataCallback dataCallback) {

    }

    @Override
    public void orderDetail(String token, String orderSn, DataCallback dataCallback) {

    }

    @Override
    public void cancle(String token, String orderSn, DataCallback dataCallback) {

    }

    @Override
    public void payDone(String token, String orderSn, DataCallback dataCallback) {

    }

    @Override
    public void releaseOrder(String token, String orderSn, String jyPassword, DataCallback dataCallback) {

    }

    @Override
    public void appeal(String token, String remark, String orderSn, DataCallback dataCallback) {

    }

    @Override
    public void editAccountPed(String token, String newPassword, String oldPassword, DataCallback dataCallback) {

    }

    @Override
    public void resetAccountPwd(String token, String newPassword, String code, DataCallback dataCallback) {

    }

    @Override
    public void resetAccountPwdCode(String token, DataCallback dataCallback) {

    }

    @Override
    public void getHistoryMessage(String token, String orderId, int pageNo, int pageSize,DataCallback dataCallback) {

    }

    @Override
    public void getEntrustHistory(String token, String symbol,int pageNo, int pageSize, DataCallback dataCallback) {

    }

    @Override
    public void getCreditInfo(String token, DataCallback dataCallback) {

    }

    @Override
    public void getNewVision(String token, DataCallback dataCallback) {

    }

    @Override
    public void getSymbol(DataCallback dataCallback) {

    }

    @Override
    public void getAccountSetting(String token, DataCallback dataCallback) {

    }

    @Override
    public void getBindBank(String token, String bank, String branch, String jyPassword, String realName, String cardNo, DataCallback dataCallback) {

    }

    @Override
    public void getUpdateBank(String token, String bank, String branch, String jyPassword, String realName, String cardNo, DataCallback dataCallback) {

    }

    @Override
    public void getBindWeiChat(String token, String wechat, String jyPassword, String realName, String qrCodeUrl, DataCallback dataCallback) {

    }

    @Override
    public void getUpdateWeiChat(String token, String wechat, String jyPassword, String realName, String qrCodeUrl, DataCallback dataCallback) {

    }

    @Override
    public void getBindAli(String token, String ali, String jyPassword, String realName, String qrCodeUrl, DataCallback dataCallback) {

    }

    @Override
    public void getUpdateAli(String token, String ali, String jyPassword, String realName, String qrCodeUrl, DataCallback dataCallback) {

    }

    @Override
    public void getCheckMatch(String token, DataCallback dataCallback) {

    }

    @Override
    public void getStartMatch(String token, String amount, DataCallback dataCallback) {

    }

    @Override
    public void getPromotion(String token, String page, String number, DataCallback dataCallback) {

    }

    @Override
    public void getPromotionReward(String token, String page, String number, DataCallback dataCallback) {

    }

    @Override
    public void ctcOrderDetail(String token, Long oid, DataCallback dataCallback) {

    }

    @Override
    public void ctcOrderList(String token, int pageNo, int pageSize, DataCallback dataCallback) {

    }

    @Override
    public void ctcOrderCancel(String token, Long oid, DataCallback dataCallback) {

    }

    @Override
    public void ctcOrderPay(String token, Long oid, DataCallback dataCallback) {

    }

    @Override
    public void ctcPrice(DataCallback dataCallback) {

    }

    @Override
    public void ctcNewOrder(String token, BigDecimal price, BigDecimal amount, String payType, int direction, String unit, String fundpwd, String code, DataCallback dataCallback) {

    }

    @Override
    public void ctcSendNewOrderPhoneCode(String token, DataCallback dataCallback) {

    }

    @Override
    public void getDepositList(String token, DataCallback dataCallback) {

    }

    @Override
    public void commitSellerApply(String token, String coinId, String json, DataCallback dataCallback) {

    }

    @Override
    public void myWalletUsdt(String token, DataCallback dataCallback) {

    }



    @Override
    public void myPromotion(String token, DataCallback dataCallback) {

    }
}
