package com.bizzan.data;

import java.math.BigDecimal;

/**
 * Created by Administrator on 2017/9/25.
 */
public class DataRepository implements DataSource {
    private static DataRepository INSTANCE = null;
    private final DataSource mRemoteDataSource;
    private final DataSource mLocalDataSource;
    private boolean isLocal = false;

    private DataRepository(DataSource mRemoteDataSource, DataSource mLocalDataSource) {
        this.mRemoteDataSource = mRemoteDataSource;
        this.mLocalDataSource = mLocalDataSource;
    }

    public static DataRepository getInstance(DataSource mRemoteDataSource, DataSource mLocalDataSource) {
        if (INSTANCE == null) INSTANCE = new DataRepository(mRemoteDataSource, mLocalDataSource);
        return INSTANCE;
    }

    @Override
    public void phoneCode(String phone, String country, DataCallback dataCallback) {
        if (isLocal) mLocalDataSource.phoneCode(phone, country, dataCallback);
        else mRemoteDataSource.phoneCode(phone, country, dataCallback);
    }

    @Override
    public void emailCode(String email,  DataCallback dataCallback) {
        if (isLocal) mLocalDataSource.emailCode(email, dataCallback);
        else mRemoteDataSource.emailCode(email, dataCallback);
    }

    @Override
    public void signUpByPhone(String phone, String username, String password, String country, String code,String tuijianma,String challenge, String validate, String seccode, DataCallback dataCallback) {
        if (isLocal) mLocalDataSource.signUpByPhone(phone, username, password, country, code,tuijianma,challenge,validate,seccode, dataCallback);
        else mRemoteDataSource.signUpByPhone(phone, username, password, country, code,tuijianma, challenge,validate,seccode,dataCallback);
    }

    @Override
    public void signUpByEmail(String email,String password,String tuijian2, String country, String challenge, String validate, String seccode, DataCallback dataCallback) {
        if (isLocal)
            mLocalDataSource.signUpByEmail(email, password, tuijian2,country, challenge, validate, seccode, dataCallback);
        else mRemoteDataSource.signUpByEmail(email, password, tuijian2,country, challenge, validate, seccode, dataCallback);
    }

    @Override
    public void login(String username, String password, String challenge, String validate, String seccode, DataCallback dataCallback) {
        if (isLocal) mLocalDataSource.login(username, password, challenge, validate, seccode, dataCallback);
        else mRemoteDataSource.login(username, password, challenge, validate, seccode, dataCallback);
    }



    @Override
    public void KData(String symbol, Long from, Long to, String resolution, DataCallback dataCallback) {
        if (isLocal) mLocalDataSource.KData(symbol, from, to, resolution, dataCallback);
        else mRemoteDataSource.KData(symbol, from, to, resolution, dataCallback);
    }

    @Override
    public void KData_Constract(String symbol, Long from, Long to, String resolution, DataCallback dataCallback) {
        if (isLocal) mLocalDataSource.KData_Constract(symbol, from, to, resolution, dataCallback);
        else mRemoteDataSource.KData_Constract(symbol, from, to, resolution, dataCallback);
    }

    @Override
    public void allCurrency(DataCallback dataCallback) {
        if (isLocal) mLocalDataSource.allCurrency(dataCallback);
        else mRemoteDataSource.allCurrency(dataCallback);
    }

    @Override
    public void allHomeCurrency(DataCallback dataCallback) {
        if (isLocal) mLocalDataSource.allHomeCurrency(dataCallback);
        else mRemoteDataSource.allHomeCurrency(dataCallback);
    }

    @Override
    public void find(String token, DataCallback dataCallback) {
        if (isLocal) mLocalDataSource.find(token, dataCallback);
        else mRemoteDataSource.find(token, dataCallback);
    }

    @Override
    public void delete(String token, String symbol, DataCallback dataCallback) {
        if (isLocal) mLocalDataSource.delete(token, symbol, dataCallback);
        else mRemoteDataSource.delete(token, symbol, dataCallback);
    }

    @Override
    public void add(String token, String symbol, DataCallback dataCallback) {
        if (isLocal) mLocalDataSource.add(token, symbol, dataCallback);
        else mRemoteDataSource.add(token, symbol, dataCallback);
    }

    @Override
    public void exChange(String token, String symbol, String price, String amount, String direction, String type, DataCallback dataCallback) {
        if (isLocal)
            mLocalDataSource.exChange(token, symbol, price, amount, direction, type, dataCallback);
        else
            mRemoteDataSource.exChange(token, symbol, price, amount, direction, type, dataCallback);
    }

    @Override
    public void wallet(String token, String coinName, DataCallback dataCallback) {
        if (isLocal) mLocalDataSource.wallet(token, coinName, dataCallback);
        else mRemoteDataSource.wallet(token, coinName, dataCallback);
    }

    @Override
    public void all(DataCallback dataCallback) {
        if (isLocal) mLocalDataSource.all(dataCallback);
        else mRemoteDataSource.all(dataCallback);
    }

    @Override
    public void advertise(int pageNo, int pageSize, String advertiseType, String id, DataCallback dataCallback) {
        if (isLocal) mLocalDataSource.advertise(pageNo, pageSize, advertiseType, id, dataCallback);
        else mRemoteDataSource.advertise(pageNo, pageSize, advertiseType, id, dataCallback);
    }

    @Override
    public void country(DataCallback dataCallback) {
        if (isLocal) mLocalDataSource.country(dataCallback);
        else mRemoteDataSource.country(dataCallback);
    }

    @Override
    public void create(String token, String price, String advertiseType, String coinId, String minLimit, String maxLimit, int timeLimit, String countryZhName, String priceType, String premiseRate, String remark, String number, String pay, String jyPassword, String auto, String autoword, DataCallback dataCallback) {
        if (isLocal)
            mLocalDataSource.create(token, price, advertiseType, coinId, minLimit, maxLimit, timeLimit, countryZhName
                    , priceType, premiseRate, remark, number, pay, jyPassword, auto, autoword, dataCallback);
        else
            mRemoteDataSource.create(token, price, advertiseType, coinId, minLimit, maxLimit, timeLimit, countryZhName
                    , priceType, premiseRate, remark, number, pay, jyPassword, auto, autoword, dataCallback);
    }

    @Override
    public void uploadBase64Pic(String token, String base64Data, DataCallback dataCallback) {
        if (isLocal) mLocalDataSource.uploadBase64Pic(token, base64Data, dataCallback);
        else mRemoteDataSource.uploadBase64Pic(token, base64Data, dataCallback);
    }

    @Override
    public void name(String token, String realName, String idCard, String idCardFront, String idCardBack, String handHeldIdCard, DataCallback dataCallback) {
        if (isLocal)
            mLocalDataSource.name(token, realName, idCard, idCardFront, idCardBack, handHeldIdCard, dataCallback);
        else
            mRemoteDataSource.name(token, realName, idCard, idCardFront, idCardBack, handHeldIdCard, dataCallback);
    }

    @Override
    public void accountPwd(String token, String jyPassword, DataCallback dataCallback) {
        if (isLocal) mLocalDataSource.accountPwd(token, jyPassword, dataCallback);
        else mRemoteDataSource.accountPwd(token, jyPassword, dataCallback);
    }

    @Override
    public void allAds(String token, DataCallback dataCallback) {
        if (isLocal) mLocalDataSource.allAds(token, dataCallback);
        else mRemoteDataSource.allAds(token, dataCallback);
    }

    @Override
    public void release(String token, int id, DataCallback dataCallback) {
        if (isLocal) mLocalDataSource.release(token, id, dataCallback);
        else mRemoteDataSource.release(token, id, dataCallback);
    }

    @Override
    public void deleteAds(String token, int id, DataCallback dataCallback) {
        if (isLocal) mLocalDataSource.deleteAds(token, id, dataCallback);
        else mRemoteDataSource.deleteAds(token, id, dataCallback);
    }

    @Override
    public void offShelf(String token, int id, DataCallback dataCallback) {
        if (isLocal) mLocalDataSource.offShelf(token, id, dataCallback);
        else mRemoteDataSource.offShelf(token, id, dataCallback);
    }

    @Override
    public void adDetail(String token, int id, DataCallback dataCallback) {
        if (isLocal) mLocalDataSource.adDetail(token, id, dataCallback);
        else mRemoteDataSource.adDetail(token, id, dataCallback);
    }

    @Override
    public void updateAd(String token, int id, String price, String advertiseType, String coinId, String minLimit, String maxLimit, int timeLimit, String countryZhName, String priceType, String premiseRate, String remark, String number, String pay, String jyPassword, String auto, String autoword, DataCallback dataCallback) {
        if (isLocal)
            mLocalDataSource.updateAd(token, id, price, advertiseType, coinId, minLimit, maxLimit, timeLimit, countryZhName, priceType, premiseRate, remark, number, pay, jyPassword, auto, autoword, dataCallback);
        else
            mRemoteDataSource.updateAd(token, id, price, advertiseType, coinId, minLimit, maxLimit, timeLimit, countryZhName, priceType, premiseRate, remark, number, pay, jyPassword, auto, autoword, dataCallback);
    }

    @Override
    public void c2cInfo(int id, DataCallback dataCallback) {
        if (isLocal) mLocalDataSource.c2cInfo(id, dataCallback);
        else mRemoteDataSource.c2cInfo(id, dataCallback);
    }

    @Override
    public void c2cBuy(String token, String id, String coinId, String price, String money, String amount, String remark, String mode, DataCallback dataCallback) {
        if (isLocal)
            mLocalDataSource.c2cBuy(token, id, coinId, price, money, amount, remark, mode, dataCallback);
        else
            mRemoteDataSource.c2cBuy(token, id, coinId, price, money, amount, remark, mode, dataCallback);
    }

    @Override
    public void c2cSell(String token, String id, String coinId, String price, String money, String amount, String remark, String mode, DataCallback dataCallback) {
        if (isLocal)
            mLocalDataSource.c2cSell(token, id, coinId, price, money, amount, remark, mode, dataCallback);
        else
            mRemoteDataSource.c2cSell(token, id, coinId, price, money, amount, remark, mode, dataCallback);

    }

    @Override
    public void myOrder(String token, int status, int pageNo, int pageSize, DataCallback dataCallback) {
        if (isLocal) mLocalDataSource.myOrder(token, status, pageNo, pageSize, dataCallback);
        else mRemoteDataSource.myOrder(token, status, pageNo, pageSize, dataCallback);
    }

    @Override
    public void myWallet(String token, DataCallback dataCallback) {
        if (isLocal) mLocalDataSource.myWallet(token, dataCallback);
        else mRemoteDataSource.myWallet(token, dataCallback);
    }

    @Override
    public void myWallet_Constract(String token, DataCallback dataCallback) {
        if (isLocal) mLocalDataSource.myWallet_Constract(token, dataCallback);
        else mRemoteDataSource.myWallet_Constract(token, dataCallback);
    }

    @Override
    public void RequesTrans(String token, String unit, String from, String to, String fromWalletId, String toWalletId, String amount, DataCallback dataCallback) {
        if (isLocal) mLocalDataSource.RequesTrans(token, unit,from,to,fromWalletId,toWalletId,amount,dataCallback);
        else mRemoteDataSource.RequesTrans(token, unit,from,to,fromWalletId,toWalletId,amount, dataCallback);
    }

    @Override
    public void extractinfo(String token, DataCallback dataCallback) {
        if (isLocal) mLocalDataSource.extractinfo(token, dataCallback);
        else mRemoteDataSource.extractinfo(token, dataCallback);
    }

    @Override
    public void extract(String token, String unit, String amount, String fee, String remark, String jyPassword, String address, String code,DataCallback dataCallback) {
        if (isLocal)
            mLocalDataSource.extract(token, unit, amount, fee, remark, jyPassword, address,code, dataCallback);
        else
            mRemoteDataSource.extract(token, unit, amount, fee, remark, jyPassword, address,code, dataCallback);
    }

    @Override
    public void allTransaction(String token, int pageNo, int limit,int memberId,String startTime,String endTime,String symbol,String type, DataCallback dataCallback) {
        if (isLocal) mLocalDataSource.allTransaction(token, pageNo, limit, memberId,startTime,endTime,symbol,type,dataCallback);
        else mRemoteDataSource.allTransaction(token, pageNo, limit,memberId,startTime,endTime,symbol,type, dataCallback);
    }

    @Override
    public void safeSetting(String token, DataCallback dataCallback) {
        if (isLocal) mLocalDataSource.safeSetting(token, dataCallback);
        else mRemoteDataSource.safeSetting(token, dataCallback);
    }

    @Override
    public void avatar(String token, String url, DataCallback dataCallback) {
        if (isLocal) mLocalDataSource.avatar(token, url, dataCallback);
        else mRemoteDataSource.avatar(token, url, dataCallback);
    }

    @Override
    public void bindPhone(String token, String phone, String code, String password, DataCallback dataCallback) {
        if (isLocal) mLocalDataSource.bindPhone(token, phone, code, password, dataCallback);
        else mRemoteDataSource.bindPhone(token, phone, code, password, dataCallback);
    }

    @Override
    public void sendCode(String token, String phone, DataCallback dataCallback) {
        if (isLocal) mLocalDataSource.sendCode(token, phone, dataCallback);
        else mRemoteDataSource.sendCode(token, phone, dataCallback);
    }

    @Override
    public void bindEmail(String token, String email, String code, String password, DataCallback dataCallback) {
        if (isLocal) mLocalDataSource.bindEmail(token, email, code, password, dataCallback);
        else mRemoteDataSource.bindEmail(token, email, code, password, dataCallback);
    }

    @Override
    public void sendEmailCode(String token, String email, DataCallback dataCallback) {
        if (isLocal) mLocalDataSource.sendEmailCode(token, email, dataCallback);
        else mRemoteDataSource.sendEmailCode(token, email, dataCallback);
    }

    @Override
    public void sendEditLoginPwdCode(String token, DataCallback dataCallback) {
        if (isLocal) mLocalDataSource.sendEditLoginPwdCode(token, dataCallback);
        else mRemoteDataSource.sendEditLoginPwdCode(token, dataCallback);
    }

    @Override
    public void editPwd(String token, String oldPassword, String newPassword, String code, DataCallback dataCallback) {
        if (isLocal) mLocalDataSource.editPwd(token, oldPassword, newPassword, code, dataCallback);
        else mRemoteDataSource.editPwd(token, oldPassword, newPassword, code, dataCallback);
    }

    @Override
    public void plate(String symbol, DataCallback dataCallback) {
        if (isLocal) mLocalDataSource.plate(symbol, dataCallback);
        else mRemoteDataSource.plate(symbol, dataCallback);
    }

    @Override
    public void entrust(String token, int pageSize, int pageNo, String symbol, DataCallback dataCallback) {
        if (isLocal) mLocalDataSource.entrust(token, pageSize, pageNo, symbol, dataCallback);
        else mRemoteDataSource.entrust(token, pageSize, pageNo, symbol, dataCallback);
    }

    @Override
    public void cancleEntrust(String token, String orderId, DataCallback dataCallback) {
        if (isLocal) mLocalDataSource.cancleEntrust(token, orderId, dataCallback);
        else mRemoteDataSource.cancleEntrust(token, orderId, dataCallback);
    }

    @Override
    public void phoneForgotCode(String phone, String challenge, String validate, String seccode, DataCallback dataCallback) {
        if (isLocal)
            mLocalDataSource.phoneForgotCode(phone, challenge, validate, seccode, dataCallback);
        else
            mRemoteDataSource.phoneForgotCode(phone, challenge, validate, seccode, dataCallback);
    }

    @Override
    public void forgotPwd(String account, String code, String mode, String password, DataCallback dataCallback) {
        if (isLocal) mLocalDataSource.forgotPwd(account, code, mode, password, dataCallback);
        else mRemoteDataSource.forgotPwd(account, code, mode, password, dataCallback);
    }

    @Override
    public void emailForgotCode(String email, String challenge, String validate, String seccode, DataCallback dataCallback) {
        if (isLocal)
            mLocalDataSource.emailForgotCode(email, challenge, validate, seccode, dataCallback);
        else
            mRemoteDataSource.emailForgotCode(email, challenge, validate, seccode, dataCallback);
    }

    @Override
    public void captch(DataCallback dataCallback) {
        if (isLocal) mLocalDataSource.captch(dataCallback);
        else mRemoteDataSource.captch(dataCallback);
    }

    @Override
    public void sendChangePhoneCode(String token, DataCallback dataCallback) {
        if (isLocal) mLocalDataSource.sendChangePhoneCode(token, dataCallback);
        else mRemoteDataSource.sendChangePhoneCode(token, dataCallback);
    }

    @Override
    public void changePhone(String token, String password, String phone, String code, DataCallback dataCallback) {
        if (isLocal) mLocalDataSource.changePhone(token, password, phone, code, dataCallback);
        else mRemoteDataSource.changePhone(token, password, phone, code, dataCallback);
    }

    @Override
    public void message(int pageNo, int pageSize, DataCallback dataCallback) {
        if (isLocal) mLocalDataSource.message(pageNo, pageSize, dataCallback);
        else mRemoteDataSource.message(pageNo, pageSize, dataCallback);
    }

    @Override
    public void messageDetail(String id, DataCallback dataCallback) {
        if (isLocal) mLocalDataSource.messageDetail(id, dataCallback);
        else mRemoteDataSource.messageDetail(id, dataCallback);
    }

    @Override
    public void remark(String token, String remark, DataCallback dataCallback) {
        if (isLocal) mLocalDataSource.remark(token, remark, dataCallback);
        else mRemoteDataSource.remark(token, remark, dataCallback);
    }

    @Override
    public void appInfo(DataCallback dataCallback) {
        if (isLocal) mLocalDataSource.appInfo(dataCallback);
        else mRemoteDataSource.appInfo(dataCallback);
    }

    @Override
    public void banners(String sysAdvertiseLocation, DataCallback dataCallback) {
        if (isLocal) mLocalDataSource.banners(sysAdvertiseLocation, dataCallback);
        else mRemoteDataSource.banners(sysAdvertiseLocation, dataCallback);
    }

    @Override
    public void orderDetail(String token, String orderSn, DataCallback dataCallback) {
        if (isLocal) mLocalDataSource.orderDetail(token, orderSn, dataCallback);
        else mRemoteDataSource.orderDetail(token, orderSn, dataCallback);
    }

    @Override
    public void cancle(String token, String orderSn, DataCallback dataCallback) {
        if (isLocal) mLocalDataSource.cancle(token, orderSn, dataCallback);
        else mRemoteDataSource.cancle(token, orderSn, dataCallback);
    }

    @Override
    public void payDone(String token, String orderSn, DataCallback dataCallback) {
        if (isLocal) mLocalDataSource.payDone(token, orderSn, dataCallback);
        else mRemoteDataSource.payDone(token, orderSn, dataCallback);
    }

    @Override
    public void releaseOrder(String token, String orderSn, String jyPassword, DataCallback dataCallback) {
        if (isLocal) mLocalDataSource.releaseOrder(token, orderSn, jyPassword, dataCallback);
        else mRemoteDataSource.releaseOrder(token, orderSn, jyPassword, dataCallback);
    }

    @Override
    public void appeal(String token, String remark, String orderSn, DataCallback dataCallback) {
        if (isLocal) mLocalDataSource.appeal(token, remark, orderSn, dataCallback);
        else mRemoteDataSource.appeal(token, remark, orderSn, dataCallback);
    }

    @Override
    public void editAccountPed(String token, String newPassword, String oldPassword, DataCallback dataCallback) {
        if (isLocal) mLocalDataSource.editAccountPed(token, newPassword, oldPassword, dataCallback);
        else mRemoteDataSource.editAccountPed(token, newPassword, oldPassword, dataCallback);
    }

    @Override
    public void resetAccountPwd(String token, String newPassword, String code, DataCallback dataCallback) {
        if (isLocal) mLocalDataSource.resetAccountPwd(token, newPassword, code, dataCallback);
        else mRemoteDataSource.resetAccountPwd(token, newPassword, code, dataCallback);
    }

    @Override
    public void resetAccountPwdCode(String token, DataCallback dataCallback) {
        if (isLocal) mLocalDataSource.resetAccountPwdCode(token, dataCallback);
        else mRemoteDataSource.resetAccountPwdCode(token, dataCallback);
    }

    @Override
    public void getHistoryMessage(String token, String orderId, int pageNo, int pageSize,DataCallback dataCallback) {
        if (isLocal) mLocalDataSource.getHistoryMessage(token,orderId, pageNo,pageSize,dataCallback);
        else mRemoteDataSource.getHistoryMessage(token,orderId,pageNo,pageSize,dataCallback);
    }

    @Override
    public void getEntrustHistory(String token, String symbol, int pageNo, int pageSize, DataCallback dataCallback) {
        if (isLocal)
            mLocalDataSource.getEntrustHistory(token, symbol, pageNo, pageSize, dataCallback);
        else mRemoteDataSource.getEntrustHistory(token, symbol, pageNo, pageSize, dataCallback);
    }

    @Override
    public void getCreditInfo(String token, DataCallback dataCallback) {
        if (isLocal) mLocalDataSource.getCreditInfo(token, dataCallback);
        else mRemoteDataSource.getCreditInfo(token, dataCallback);
    }

    @Override
    public void getNewVision(String token, DataCallback dataCallback) {
        if (isLocal) mLocalDataSource.getNewVision(token, dataCallback);
        else mRemoteDataSource.getNewVision(token, dataCallback);
    }

    @Override
    public void getSymbol(DataCallback dataCallback) {
        if (isLocal) mLocalDataSource.getSymbol(dataCallback);
        else mRemoteDataSource.getSymbol(dataCallback);
    }

    @Override
    public void getAccountSetting(String token, DataCallback dataCallback) {
        if (isLocal) mLocalDataSource.getAccountSetting(token,dataCallback);
        else mRemoteDataSource.getAccountSetting(token,dataCallback);
    }

    @Override
    public void getBindBank(String token, String bank, String branch, String jyPassword, String realName, String cardNo, DataCallback dataCallback) {
        if (isLocal) mLocalDataSource.getBindBank(token,bank,branch,jyPassword,realName,cardNo,dataCallback);
        else mRemoteDataSource.getBindBank(token,bank,branch,jyPassword,realName,cardNo,dataCallback);
    }

    @Override
    public void getUpdateBank(String token, String bank, String branch, String jyPassword, String realName, String cardNo, DataCallback dataCallback) {
        if (isLocal) mLocalDataSource.getUpdateBank(token,bank,branch,jyPassword,realName,cardNo,dataCallback);
        else mRemoteDataSource.getUpdateBank(token,bank,branch,jyPassword,realName,cardNo,dataCallback);
    }

    @Override
    public void getBindWeiChat(String token, String wechat, String jyPassword, String realName, String qrCodeUrl, DataCallback dataCallback) {
        if (isLocal) mLocalDataSource.getBindWeiChat(token,wechat,jyPassword,realName,qrCodeUrl,dataCallback);
        else mRemoteDataSource.getBindWeiChat(token,wechat,jyPassword,realName,qrCodeUrl,dataCallback);
    }

    @Override
    public void getUpdateWeiChat(String token, String wechat, String jyPassword, String realName, String qrCodeUrl, DataCallback dataCallback) {
        if (isLocal) mLocalDataSource.getUpdateWeiChat(token,wechat,jyPassword,realName,qrCodeUrl,dataCallback);
        else mRemoteDataSource.getUpdateWeiChat(token,wechat,jyPassword,realName,qrCodeUrl,dataCallback);
    }

    @Override
    public void getBindAli(String token, String ali, String jyPassword, String realName, String qrCodeUrl, DataCallback dataCallback) {
        if (isLocal) mLocalDataSource.getBindAli(token,ali,jyPassword,realName,qrCodeUrl,dataCallback);
        else mRemoteDataSource.getBindAli(token,ali,jyPassword,realName,qrCodeUrl,dataCallback);
    }

    @Override
    public void getUpdateAli(String token, String ali, String jyPassword, String realName, String qrCodeUrl, DataCallback dataCallback) {
        if (isLocal) mLocalDataSource.getUpdateAli(token,ali,jyPassword,realName,qrCodeUrl,dataCallback);
        else mRemoteDataSource.getUpdateAli(token,ali,jyPassword,realName,qrCodeUrl,dataCallback);
    }

    @Override
    public void getCheckMatch(String token, DataCallback dataCallback) {
        if (isLocal) mLocalDataSource.getCheckMatch(token,dataCallback);
        else mRemoteDataSource.getCheckMatch(token,dataCallback);
    }

    @Override
    public void getStartMatch(String token, String amount, DataCallback dataCallback) {
        if (isLocal) mLocalDataSource.getStartMatch(token,amount,dataCallback);
        else mRemoteDataSource.getStartMatch(token,amount,dataCallback);
    }

    @Override
    public void getPromotion(String token,String page,String number, DataCallback dataCallback) {
        if (isLocal) mLocalDataSource.getPromotion(token,page,number,dataCallback);
        else mRemoteDataSource.getPromotion(token,page,number,dataCallback);
    }

    @Override
    public void getPromotionReward(String token,String page,String number, DataCallback dataCallback) {
        if (isLocal) mLocalDataSource.getPromotionReward(token,page,number,dataCallback);
        else mRemoteDataSource.getPromotionReward(token,page,number,dataCallback);
    }

    // C2C交易处理接口（承兑商版）
    @Override
    public void ctcOrderDetail(String token, Long oid, DataCallback dataCallback) {
        if (isLocal) mLocalDataSource.ctcOrderDetail(token, oid, dataCallback);
        else mRemoteDataSource.ctcOrderDetail(token, oid, dataCallback);
    }

    @Override
    public void ctcOrderList(String token, int pageNo, int pageSize, DataCallback dataCallback) {
        if (isLocal) mLocalDataSource.ctcOrderList(token, pageNo, pageSize, dataCallback);
        else mRemoteDataSource.ctcOrderList(token, pageNo, pageSize, dataCallback);
    }

    @Override
    public void ctcOrderCancel(String token, Long oid, DataCallback dataCallback) {
        if (isLocal) mLocalDataSource.ctcOrderCancel(token, oid, dataCallback);
        else mRemoteDataSource.ctcOrderCancel(token, oid, dataCallback);
    }

    @Override
    public void ctcOrderPay(String token, Long oid, DataCallback dataCallback) {
        if (isLocal) mLocalDataSource.ctcOrderPay(token, oid, dataCallback);
        else mRemoteDataSource.ctcOrderPay(token, oid, dataCallback);
    }

    @Override
    public void ctcPrice(DataCallback dataCallback) {
        if (isLocal) mLocalDataSource.ctcPrice(dataCallback);
        else mRemoteDataSource.ctcPrice(dataCallback);
    }

    @Override
    public void ctcNewOrder(String token, BigDecimal price, BigDecimal amount, String payType, int direction, String unit, String fundpwd, String code, DataCallback dataCallback) {
        if (isLocal) mLocalDataSource.ctcNewOrder(token, price, amount, payType, direction, unit, fundpwd, code, dataCallback);
        else mRemoteDataSource.ctcNewOrder(token, price, amount, payType, direction, unit, fundpwd, code, dataCallback);
    }

    @Override
    public void ctcSendNewOrderPhoneCode(String token, DataCallback dataCallback) {
        if (isLocal) mLocalDataSource.ctcSendNewOrderPhoneCode(token, dataCallback);
        else mRemoteDataSource.ctcSendNewOrderPhoneCode(token, dataCallback);
    }

    @Override
    public void getDepositList(String token, DataCallback dataCallback) {
        if (isLocal) mLocalDataSource.getDepositList(token,  dataCallback);
        else mRemoteDataSource.getDepositList(token,  dataCallback);
    }

    @Override
    public void commitSellerApply(String token, String coinId, String json, DataCallback dataCallback) {
        if (isLocal) mLocalDataSource.commitSellerApply(token, coinId, json, dataCallback);
        else mRemoteDataSource.commitSellerApply(token, coinId, json, dataCallback);
    }

    @Override
    public void myWalletUsdt(String token, DataCallback dataCallback) {
        if (isLocal) mLocalDataSource.myWalletUsdt(token, dataCallback);
        else mRemoteDataSource.myWalletUsdt(token, dataCallback);
    }

    @Override
    public void myPromotion(String token, DataCallback dataCallback){
        if (isLocal) mLocalDataSource.myPromotion(token, dataCallback);
        else mRemoteDataSource.myPromotion(token, dataCallback);
    }
}

