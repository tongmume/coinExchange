package com.bizzan.app;

import com.bizzan.config.UrlConfig;

/**
 * Created by Administrator on 2018/1/29.
 */

public class UrlFactory {

    public static final String host = UrlConfig.BASE_URL;

    //获取充币地址
    public static String getChongbi() {
        return host + "/uc/asset/wallet/reset-address";
    }

    //申请成为商家
    public static String getSellerApply() {
        return host + "/uc/approve/certified/business/apply";
    }

    //    提币验证码
    public static String getCode() {
        return host + "/uc/mobile/withdraw/code";
    }

    //    提币接口
    public static String getTIBi() {
        return host + "/uc/withdraw/apply/code";
    }

    //获取保证金币种列表
    public static String getDepositList() {
        return host + "/uc/approve/business-auth-deposit/list";
    }

    //帮助中心
    public static String getHelp() {
        return host + "/uc/ancillary/more/help";
    }

    public static String getHelpXinShou() {
        return host + "/uc/ancillary/more/help/page";
    }


    //交易明细接口
    public static String getCha() {
        return host + "/uc/asset/transaction/all";
    }

    //提币明细
    public static String getChaTiBi() {
        return host + "/uc/withdraw/record";
    }

    //
    public static String getShangjia() {
        return host + "/uc/approve/certified/business/status";
    }

    // 获取汇率
    public static String getRateUrl() {
        return host + "/market/exchange-rate/usd-cny";
    }

    //注册手机号code
    public static String getPhoneCodeUrl() {
        return host + "/uc/mobile/code";
    }

    //注册邮箱code
    public static String getEmailCodeUrl() {
        return host + "/uc/reg/email/code";
    }

    //注册
    public static String getSignUpByPhone() {
        return host + "/uc/register/for_phone";
    }

    public static String getSignUpByEmail() {
        return host + "/uc/register/email";
    }

    public static String getLoginUrl() {
        return host + "/uc/login";
    }

    public static String getKDataUrl() {
        return host + "/market/history";
    }

    public static String getAllCurrency() {
        return host + "/market/symbol-thumb";
    }


    /**
     * 首页获取所有的币种
     */
    public static String getAllCurrencys() {
        return host + "/market/overview";
    }

    /**
     * 得到信息，来设置输入小数点位数的限制
     */
    public static String getSymbolInfo() {
        return host + "/market/symbol-info";
    }


    public static String getFindUrl() {
        return host + "/exchange/favor/find";
    }

    public static String getDeleteUrl() {
        return host + "/exchange/favor/delete";
    }

    public static String getAddUrl() {
        return host + "/exchange/favor/add";
    }

    public static String getExChangeUrl() {
        return host + "/exchange/order/add";
    }

    public static String getWalletUrl() {
        return host + "/uc/asset/wallet/";
    }


    public static String getAllUrl() {
        return host + "/otc/coin/all";
    }

    public static String getAdvertiseUrl() {
        return host + "/otc/advertise/page";
    }

    public static String getCountryUrl() {
        return host + "/uc/support/country";
    }

    public static String getReleaseAdUrl() {
        return host + "/otc/advertise/create";
    }

    public static String getUploadPicUrl() {
        return host + "/uc/upload/oss/base64";
    }

    public static String getNameUrl() {
        return host + "/uc/approve/real/name";
    }

    public static String getAccountPwdUrl() {
        return host + "/uc/approve/transaction/password";
    }

    public static String getAllAdsUrl() {
        return host + "/otc/advertise/all";
    }

    public static String getReleaseUrl() {
        return host + "/otc/advertise/on/shelves";
    }

    public static String getDeleteAdsUrl() {
        return host + "/otc/advertise/delete";
    }

    public static String getOffShelfUrl() {
        return host + "/otc/advertise/off/shelves";
    }

    public static String getAdDetailUrl() {
        return host + "/otc/advertise/detail";
    }

    public static String getUpdateAdUrl() {
        return host + "/otc/advertise/update";
    }

    public static String getC2CInfoUrl() {
        return host + "/otc/order/pre";
    }

    public static String getC2CBuyUrl() {
        return host + "/otc/order/buy";
    }

    public static String getC2CSellUrl() {
        return host + "/otc/order/sell";
    }

    public static String getMyOrderUrl() {
        return host + "/otc/order/self";
    }

    public static String getExtractinfoUrl() {
        return host + "/uc/withdraw/support/coin/info";
    }

    public static String getExtractUrl() {
        return host + "/uc/withdraw/apply";
    }

    public static String getAllTransactionUrl2() {
        return host + "/uc/asset/transaction/all";
    }

    public static String getSafeSettingUrl() {
        return host + "/uc/approve/security/setting";
    }

    public static String getAvatarUrl() {
        return host + "/uc/approve/change/avatar";
    }

    public static String getBindPhoneUrl() {
        return host + "/uc/approve/bind/phone";
    }

    public static String getSendCodeUrl() {
        return host + "/uc/mobile/bind/code";
    }

    public static String getBindEmailUrl() {
        return host + "/uc/approve/bind/email";
    }

    public static String getSendEmailCodeUrl() {
        return host + "/uc/bind/email/code";
    }

    public static String getEditLoginPwdUrl() {
        return host + "/uc/mobile/update/password/code";
    }

    public static String getEditPwdUrl() {
        return host + "/uc/approve/update/password";
    }

    public static String getPlateUrl() {
        return host + "/market/exchange-plate";
    }


    /**
     * 查询当前委托
     */
    public static String getEntrustUrl() {
        return host + "/exchange/order/personal/current";
    }

    /**
     * 获取历史委托记录
     */
    public static String getHistoryEntrus() {
        return host + "/exchange/order/personal/history";
    }

    public static String getCancleEntrustUrl() {
        return host + "/exchange/order/cancel/";
    }

    public static String getPhoneForgotPwdCodeUrl() {
        return host + "/uc/mobile/reset/code";
    }

    public static String getEmailForgotPwdCodeUrl() {
        return host + "/uc/reset/email/code";
    }

    public static String getForgotPwdUrl() {
        return host + "/uc/reset/login/password";
    }

    public static String getCaptchaUrl() {
        return host + "/uc/start/captcha";
    }

    public static String getSendChangePhoneCodeUrl() {
        return host + "/uc/mobile/change/code";
    }

    public static String getChangePhoneUrl() {
        return host + "/uc/approve/change/phone";
    }

    public static String getMessageUrl() {
        return host + "/uc/announcement/page";
    }

    public static String getMessageDetailUrl() {
        return host + "/uc/announcement/";
    }

    public static String getMessageHelpUrl() {
        return host + "/uc/ancillary/more/help/detail";
    }

    public static String getRemarkUrl() {
        return host + "/uc/feedback";
    }

    public static String getAppInfoUrl() {
        return host + "/uc/ancillary/website/info";
    }

    public static String getBannersUrl() {
        return host + "/uc/ancillary/system/advertise";
    }

    public static String getOrderDetailUrl() {
        return host + "/otc/order/detail";
    }

    public static String getCancleUrl() {
        return host + "/otc/order/cancel";
    }

    public static String getpayDoneUrl() {
        return host + "/otc/order/pay";
    }

    public static String getReleaseOrderUrl() {
        return host + "/otc/order/release";
    }

    public static String getAppealUrl() {
        return host + "/otc/order/appeal";
    }

    public static String getEditAccountPwdUrl() {
        return host + "/uc/approve/update/transaction/password";
    }

    public static String getResetAccountPwdUrl() {
        return host + "/uc/approve/reset/transaction/password";
    }

    public static String getResetAccountPwdCodeUrl() {
        return host + "/uc/mobile/transaction/code";
    }

    public static String getHistoryMessageUrl() {
        return host + "/chat/getHistoryMessage";
    }

    public static String getEntrustHistory() {
        return host + "/exchange/order/history";
    }

    public static String getCreditInfo() {
        return host + "/uc/approve/real/detail";
    }

    public static String getNewVision() {
        return host + "/uc/ancillary/system/app/version/0";
    }

    public static String getSymbolUrl() {
        return host + "/market/symbol";
    }

    public static String getAccountSettingUrl() {
        return host + "/uc/approve/account/setting";
    }

    public static String getBindBankUrl() {
        return host + "/uc/approve/bind/bank";
    }

    public static String getUpdateBankUrl() {
        return host + "/uc/approve/update/bank";
    }

    public static String getBindAliUrl() {
        return host + "/uc/approve/bind/ali";
    }

    public static String getUpdateAliUrl() {
        return host + "/uc/approve/update/ali";
    }

    public static String getBindWechatUrl() {
        return host + "/uc/approve/bind/wechat";
    }

    public static String getUpdateWechatUrl() {
        return host + "/uc/approve/update/wechat";
    }

    public static String getCheckMatchUrl() {
        return host + "/uc/asset/wallet/match-check";
    }

    public static String getStartMatchUrl() {
        return host + "/uc/asset/wallet/match";
    }

    public static String getPromotionUrl() {
        return host + "/uc/promotion/record";
    }

    public static String getPromotionRewardUrl() {
        return host + "/uc/promotion/reward/record";
    }

    public static String getDepth() {
        return host + "/market/exchange-plate-full";
    } // 获取深度图数据


    public static String getVolume() {
        return host + "/market/latest-trade";
    } // 获取成交数据


    // C2C交易接口（承兑商版）
    public static String getCtcOrderList() {
        return host + "/uc/ctc/page-query";
    }

    public static String getCtcOrderDetail() {
        return host + "/uc/ctc/detail";
    }

    public static String getCtcOrderCancel() {
        return host + "/uc/ctc/cancel-ctc-order";
    }

    public static String getCtcOrderPay() {
        return host + "/uc/ctc/pay-ctc-order";
    }

    public static String getCtcPrice() {
        return host + "/market/ctc-usdt";
    }

    public static String getCtcNewOrder() {
        return host + "/uc/ctc/new-ctc-order";
    }

    public static String getCtcSendNewOrderPhoneCode() {
        return host + "/uc/mobile/ctc/code";
    }

    public static String getMyPromotion() {
        return host + "/uc/promotion/mypromotion";
    }


    //--------------合约---------------
    public static String getAllCurrency_Constract() {
        return host + "/swap/symbol-thumb";
    }

    //切换仓位
    public static String getSwitchPattern() {
        return host + "/swap/order/switch-pattern";
    }

    public static String getDetail() {
        return host + "/swap/wallet/detail";
    }

    //修改倍数
    public static String getModifyLeverage() {
        return host + "/swap/order/modify-leverage";
    }

    //买卖  开仓
    public static String getOpen() {
        return host + "/swap/order/open";
    }

    //买卖  平仓
    public static String getClone() {
        return host + "/swap/order/close";
    }

    //当前委托
    public static String getEntrust() {
        return host + "/swap/order/current";
    }

    //获取历史委托记录
    public static String getHistoryEntrus_Constract() {
        return host + "/swap/order/history";
    }

    //撤销委托
    public static String getCancleEntrusConstracttUrl() {
        return host + "/swap/order/cancel";
    }

    // 获取合约成交数据
    public static String getVolume_Constract() {
        return host + "/swap/latest-trade";
    }

    //合约账户
    public static String getWalletUrl_Constract() {
        return host + "/swap/wallet/list";
    }

    //查询盘口
    public static String getPlate_sixUrl() {
        return host + "/swap/exchange-plate-mini";
    }

    /**
     * 得到信息，来设置输入小数点位数的限制  合约
     */
    public static String getSymbolInfo_Constract() {
        return host + "/swap/symbol-info";
    }

    // 获取合约深度图数据
    public static String getDepth_Constract() {
        return host + "/swap/exchange-plate-full";
    }

    //k线
    public static String getKDataUrl_Constract() {
        return host + "/swap/history";
    }

    //----------资产划转-----------
    //币币账户
    public static String getWalleUsdttUrl() {
        return host + "/uc/asset/wallet/usdt";
    }

    //划转
    public static String getTransUrl() {
        return host + "/swap/wallet/trans";
    }

    //----------期权--------------
    //期权币种
    public static String getAllCurrency_Option() {
        return host + "/option/coin/coin-list";
    }

    //往期结果数据
    public static String getOption_History() {
        return host + "/option/option/history";
    }

    //获取历史交割数据
    public static String getOrder_History() {
        return host + "/option/order/history";
    }

    //要开始的预测
    public static String getOption_Starting() {
        return host + "/option/option/starting";
    }

    //正在进行中的预测
    public static String getOption_Opening() {
        return host + "/option/option/opening";
    }

    //获取我的开仓
    public static String getOption_Current() {
        return host + "/option/order/current";
    }

    //看涨或者看跌
    public static String getAdd() {
        return host + "/option/order/add";
    }

    //----------创新实验室--------------
    public static String getALab() {
        return host + "/uc/activity/page-query";
    }

    //活动详情
    public static String getALabDetail() {
        return host + "/uc/activity/detail";
    }

    //用户参与活动列表
    public static String getMemberRecords() {
        return host + "/uc/activity/getmemberrecords";
    }

    //活动邮箱验证码
    public static String getALbEmailCode() {
        return host + "/uc/activity/code";
    }

    //活动手机验证码
    public static String getALbMobileCode() {
        return host + "/uc/mobile/activity/code";
    }
    //参加活动
    public static String getALbAttend() {
        return host + "/uc/activity/attend";
    }
    //我参与的活动
    public static String getMyAttendRecords()
    {
        return host +"/uc/activity/getmyorders";
    }
    //我的矿机
    public static String getMyMinings()
    {
        return host +"/uc/miningorder/my-minings";
    }
    //我的锁仓
    public static String getMylocked()
    {
        return host +"/uc/lockedorder/my-locked";
    }
}
