//
//  MineNetManager.m
//  digitalCurrency
//
//  Created by sunliang on 2019/1/26.
//  Copyright © 2019年 BIZZAN. All rights reserved.
//

#import "MineNetManager.h"

@implementation MineNetManager

//保存用户信息
+(void)saveInfosVideoUrl:(NSString *)videourl random:(NSString *)random CompleteHandle:(void(^)(id resPonseObj,int code))completeHandle{
    NSString *path = @"uc/approve/real/name";
    NSMutableDictionary *dic = [NSMutableDictionary new];
    dic[@"video"] = videourl;
    dic[@"random"] = random;
    [self ylNonTokenRequestWithGET:path parameters:dic successBlock:^(id resultObject, int isSuccessed) {
        completeHandle(resultObject,isSuccessed);
    }];
}

//身份认证上传视频获取随机码
+(void)identityAuthenticationapprovevideorandomCompleteHandle:(void(^)(id resPonseObj,int code))completeHandle{
     NSString *path = @"uc/approve/video/random";
     NSMutableDictionary *dic = [NSMutableDictionary new];

    [self ylNonTokenRequestWithGET:path parameters:dic successBlock:^(id resultObject, int isSuccessed) {
        completeHandle(resultObject,isSuccessed);
    }];
}
//身份认证
+(void)identityAuthenticationRealName:(NSString *)realName andIdCard:(NSString *)idCard andVideoUrl:(NSString *)videoUrl andRandom:(NSString *)random andCardDic:(NSMutableDictionary *)cardDic CompleteHandle:(void(^)(id resPonseObj,int code))completeHandle{
    NSString *path = @"uc/approve/real/name";
    NSMutableDictionary *dic = [NSMutableDictionary new];
    dic[@"realName"] = realName;
    dic[@"idCard"] = idCard;
    dic[@"video"] = videoUrl;
    dic[@"random"] = random;
    dic[@"idCardFront"] = cardDic[@"idCardFront"];
    dic[@"idCardBack"] = cardDic[@"idCardBack"];
    dic[@"handHeldIdCard"] = cardDic[@"handHeldIdCard"];
    [self ylNonTokenRequestWithGET:path parameters:dic successBlock:^(id resultObject, int isSuccessed) {
        completeHandle(resultObject,isSuccessed);
    }];
}

//设置交易密码
+(void)moneyPasswordForJyPassword:(NSString *)jyPassword CompleteHandle:(void(^)(id resPonseObj,int code))completeHandle{
    NSString *path = @"uc/approve/transaction/password";
    NSMutableDictionary *dic = [NSMutableDictionary new];
    dic[@"jyPassword"] = jyPassword;
    [self ylNonTokenRequestWithGET:path parameters:dic successBlock:^(id resultObject, int isSuccessed) {
        completeHandle(resultObject,isSuccessed);
    }];
}
//获取重置交易密码的验证码
+(void)resetMoneyPasswordCodeForCompleteHandle:(void(^)(id resPonseObj,int code))completeHandle{
    NSString *path = @"uc/mobile/transaction/code";
    NSMutableDictionary *dic = [NSMutableDictionary new];
    [self ylNonTokenRequestWithGET:path parameters:dic successBlock:^(id resultObject, int isSuccessed) {
        completeHandle(resultObject,isSuccessed);
    }];
}

//重置交易密码
+(void)resetMoneyPasswordForCode:(NSString *)code withNewPassword:(NSString *)newPassword CompleteHandle:(void(^)(id resPonseObj,int code))completeHandle{
    NSString *path = @"uc/approve/reset/transaction/password";
    NSMutableDictionary *dic = [NSMutableDictionary new];
    dic[@"code"] = code;
    dic[@"newPassword"] = newPassword;
    [self ylNonTokenRequestWithGET:path parameters:dic successBlock:^(id resultObject, int isSuccessed) {
        completeHandle(resultObject,isSuccessed);
    }];
}
//修改交易密码
+(void)resetMoneyPasswordForOldPassword:(NSString *)oldPassword withLatestPassword:(NSString *)latestPassword CompleteHandle:(void(^)(id resPonseObj,int code))completeHandle{
    NSString *path = @"uc/approve/update/transaction/password";
    NSMutableDictionary *dic = [NSMutableDictionary new];
    dic[@"oldPassword"] = oldPassword;
    dic[@"newPassword"] = latestPassword;
    [self ylNonTokenRequestWithGET:path parameters:dic successBlock:^(id resultObject, int isSuccessed) {
        completeHandle(resultObject,isSuccessed);
    }];
}
//获取绑定邮箱的验证码
+(void)bindingEmailCodeForEmail:(NSString *)email CompleteHandle:(void(^)(id resPonseObj,int code))completeHandle{
    NSString *path = @"uc/bind/email/code";
    NSMutableDictionary *dic = [NSMutableDictionary new];
    dic[@"email"] = email;
    [self ylNonTokenRequestWithGET:path parameters:dic successBlock:^(id resultObject, int isSuccessed) {
        completeHandle(resultObject,isSuccessed);
    }];
}

//绑定邮箱
+(void)bindingEmailForCode:(NSString *)code withPassword:(NSString *)password withEmail:(NSString *)email CompleteHandle:(void(^)(id resPonseObj,int code))completeHandle{
    NSString *path = @"uc/approve/bind/email";
    NSMutableDictionary *dic = [NSMutableDictionary new];
    dic[@"code"] = code;
    dic[@"password"] = password;
    dic[@"email"] = email;
    [self ylNonTokenRequestWithGET:path parameters:dic successBlock:^(id resultObject, int isSuccessed) {
        completeHandle(resultObject,isSuccessed);
    }];
}
//获取绑定手机的验证码
+(void)bindingPhoneCodeForPhone:(NSString *)phone CompleteHandle:(void(^)(id resPonseObj,int code))completeHandle{
    NSString *path = @"uc/mobile/bind/code";
    NSMutableDictionary *dic = [NSMutableDictionary new];
    dic[@"phone"] = phone;
    [self ylNonTokenRequestWithGET:path parameters:dic successBlock:^(id resultObject, int isSuccessed) {
        completeHandle(resultObject,isSuccessed);
    }];
}

//绑定手机
+(void)bindingPhoneForCode:(NSString *)code withPassword:(NSString *)password withPhone:(NSString *)phone CompleteHandle:(void(^)(id resPonseObj,int code))completeHandle{
    NSString *path = @"uc/approve/bind/phone";
    NSMutableDictionary *dic = [NSMutableDictionary new];
    dic[@"code"] = code;
    dic[@"password"] = password;
    dic[@"phone"] = phone;
    [self ylNonTokenRequestWithGET:path parameters:dic successBlock:^(id resultObject, int isSuccessed) {
        completeHandle(resultObject,isSuccessed);
    }];
}
//获取重置登录密码的验证码
+(void)resetLoginPasswordCodeForCompleteHandle:(void(^)(id resPonseObj,int code))completeHandle{
    NSString *path = @"uc/mobile/update/password/code";
    NSMutableDictionary *dic = [NSMutableDictionary new];
    [self ylNonTokenRequestWithGET:path parameters:dic successBlock:^(id resultObject, int isSuccessed) {
        completeHandle(resultObject,isSuccessed);
    }];
}

//重置登录密码
+(void)resetLoginPasswordForCode:(NSString *)code withOldPassword:(NSString *)oldPassword withLatestPassword:(NSString *)latestPassword CompleteHandle:(void(^)(id resPonseObj,int code))completeHandle{
    NSString *path = @"uc/approve/update/password";
    NSMutableDictionary *dic = [NSMutableDictionary new];
    dic[@"code"] = code;
    dic[@"oldPassword"] = oldPassword;
    dic[@"newPassword"] = latestPassword;
    [self ylNonTokenRequestWithGET:path parameters:dic successBlock:^(id resultObject, int isSuccessed) {
        completeHandle(resultObject,isSuccessed);
    }];
}

//我的订单
+(void)myBillInfoForStatus:(NSString *)status withPageNo:(NSString*)pageNo withPageSize:(NSString *)pageSize CompleteHandle:(void(^)(id resPonseObj,int code))completeHandle{
    NSString *path = @"otc/order/self";
    NSMutableDictionary *dic = [NSMutableDictionary new];
    dic[@"status"] = status;
    dic[@"pageNo"] = pageNo;
    dic[@"pageSize"] = pageSize;
    [self ylNonTokenRequestWithGET:path parameters:dic successBlock:^(id resultObject, int isSuccessed) {
        completeHandle(resultObject,isSuccessed);
    }];
}
//账号设置的状态信息获取
+(void)accountSettingInfoForCompleteHandle:(void(^)(id resPonseObj,int code))completeHandle{
    NSString *path = @"uc/approve/security/setting";
    NSMutableDictionary *dic = [NSMutableDictionary new];
    [self ylNonTokenRequestWithGET:path parameters:dic successBlock:^(id resultObject, int isSuccessed) {
        completeHandle(resultObject,isSuccessed);
    }];
}
//获取我的钱包所有信息
+(void)getMyWalletInfoForCompleteHandle:(void(^)(id resPonseObj,int code))completeHandle{
    NSString *path = @"uc/asset/wallet";
    NSMutableDictionary *dic = [NSMutableDictionary new];
    [self ylNonTokenRequestWithGET:path parameters:dic successBlock:^(id resultObject, int isSuccessed) {
        completeHandle(resultObject,isSuccessed);
    }];
}
//获取我的广告
+(void)getMyAdvertisingForCompleteHandle:(void(^)(id resPonseObj,int code))completeHandle{
    NSString *path = @"otc/advertise/all";
    NSMutableDictionary *dic = [NSMutableDictionary new];
    [self ylNonTokenRequestWithGET:path parameters:dic successBlock:^(id resultObject, int isSuccessed) {
        completeHandle(resultObject,isSuccessed);
    }];
}
//上架我的广告
+(void)upMyAdvertisingForAdvertisingId:(NSString *)advertisingId CompleteHandle:(void(^)(id resPonseObj,int code))completeHandle{
    NSString *path = @"otc/advertise/on/shelves";
    NSMutableDictionary *dic = [NSMutableDictionary new];
    dic[@"id"] = advertisingId;
    [self ylNonTokenRequestWithGET:path parameters:dic successBlock:^(id resultObject, int isSuccessed) {
        completeHandle(resultObject,isSuccessed);
    }];
}

//下架我的广告
+(void)downMyAdvertisingForAdvertisingId:(NSString *)advertisingId CompleteHandle:(void(^)(id resPonseObj,int code))completeHandle{
    NSString *path = @"otc/advertise/off/shelves";
    NSMutableDictionary *dic = [NSMutableDictionary new];
    dic[@"id"] = advertisingId;
    [self ylNonTokenRequestWithGET:path parameters:dic successBlock:^(id resultObject, int isSuccessed) {
        completeHandle(resultObject,isSuccessed);
    }];
}
//获取广告的详细信息
+(void)getMyAdvertisingDetailInfoForAdvertisingId:(NSString *)advertisingId CompleteHandle:(void(^)(id resPonseObj,int code))completeHandle{
    NSString *path = @"otc/advertise/detail";
    NSMutableDictionary *dic = [NSMutableDictionary new];
    dic[@"id"] = advertisingId;
    [self ylNonTokenRequestWithGET:path parameters:dic successBlock:^(id resultObject, int isSuccessed) {
        completeHandle(resultObject,isSuccessed);
    }];
}
//明细 获取交易历史
+(void)getTradeHistoryForCompleteHandleWithPageNo:(NSString*)pageNo withPageSize:(NSString *)pageSize CompleteHandle:(void(^)(id resPonseObj,int code))completeHandle{
    NSString *path = @"uc/asset/transaction/all";
    NSMutableDictionary *dic = [NSMutableDictionary new];
    dic[@"pageNo"] = pageNo;
    dic[@"pageSize"] = pageSize;
    [self ylNonTokenRequestWithGET:path parameters:dic successBlock:^(id resultObject, int isSuccessed) {
        completeHandle(resultObject,isSuccessed);
    }];
}
//上传图片
+(void)uploadImageForFile:(NSData *)file CompleteHandle:(void(^)(id resPonseObj,int code))completeHandle{
    NSString *path = @"uc/upload/oss/image";
    NSMutableDictionary *dic = [NSMutableDictionary new];
    dic[@"file"] = file;
    [self ylNonTokenRequestWithGET:path parameters:dic successBlock:^(id resultObject, int isSuccessed) {
        completeHandle(resultObject,isSuccessed);
    }];
}
//设置头像
+(void)setHeadImageForUrl:(NSString *)urlString CompleteHandle:(void(^)(id resPonseObj,int code))completeHandle{
    NSString *path = @"uc/approve/change/avatar";
    NSMutableDictionary *dic = [NSMutableDictionary new];
    dic[@"url"] = urlString;
    [self ylNonTokenRequestWithGET:path parameters:dic successBlock:^(id resultObject, int isSuccessed) {
        completeHandle(resultObject,isSuccessed);
    }];
}

//提币获取验证码
+(void)resetwithdrawCodeForCompleteHandle:(void(^)(id resPonseObj,int code))completeHandle{
    NSString *path = @"uc/mobile/withdraw/code";
    NSMutableDictionary *dic = [NSMutableDictionary new];
    [self ylNonTokenRequestWithGET:path parameters:dic successBlock:^(id resultObject, int isSuccessed) {
        completeHandle(resultObject,isSuccessed);
    }];
}
//提币申请
+(void)mentionCoinApplyForUnit:(NSString *)unit withAddress:(NSString*)address withAmount:(NSString *)amount withFee:(NSString *)fee withRemark:(NSString *)remark withJyPassword:(NSString *)jyPassword mobilecode:(NSString *)mobilecode CompleteHandle:(void(^)(id resPonseObj,int code))completeHandle{
    NSString *path = @"uc/withdraw/apply/code";
    NSMutableDictionary *dic = [NSMutableDictionary new];
    dic[@"unit"] = unit;
    dic[@"remark"] = remark;
    dic[@"address"] = address;
    dic[@"amount"] = amount;
    dic[@"fee"] = fee;
    dic[@"jyPassword"] = jyPassword;
    dic[@"code"] = mobilecode;
    [self ylNonTokenRequestWithGET:path parameters:dic successBlock:^(id resultObject, int isSuccessed) {
        completeHandle(resultObject,isSuccessed);
    }];
}
//提币选择信息
+(void)mentionCoinInfoForCompleteHandle:(void(^)(id resPonseObj,int code))completeHandle{
    NSString *path = @"uc/withdraw/support/coin/info";
    NSMutableDictionary *dic = [NSMutableDictionary new];
    [self ylNonTokenRequestWithGET:path parameters:dic successBlock:^(id resultObject, int isSuccessed) {
        completeHandle(resultObject,isSuccessed);
    }];
}
//1付款 2取消
+(void)myBillDetailTipForUrlString:(NSString *)urlString withOrderSn:(NSString *)orderSn CompleteHandle:(void(^)(id resPonseObj,int code))completeHandle{
    NSString *path = urlString;
    NSMutableDictionary *dic = [NSMutableDictionary new];
    dic[@"orderSn"] = orderSn;
    [self ylNonTokenRequestWithGET:path parameters:dic successBlock:^(id resultObject, int isSuccessed) {
        completeHandle(resultObject,isSuccessed);
    }];
}
// 4放款
+(void)myBillDetailTipForOrderSn:(NSString *)orderSn withJyPassword:(NSString*)jyPassword CompleteHandle:(void(^)(id resPonseObj,int code))completeHandle{
    NSString *path = @"otc/order/release";
    NSMutableDictionary *dic = [NSMutableDictionary new];
    dic[@"orderSn"] = orderSn;
    dic[@"jyPassword"] = jyPassword;
    [self ylNonTokenRequestWithGET:path parameters:dic successBlock:^(id resultObject, int isSuccessed) {
        completeHandle(resultObject,isSuccessed);
    }];
}
//订单投诉
+(void)myBillDetailComplaintsForRemark:(NSString *)remark withOrderSn:(NSString *)orderSn CompleteHandle:(void(^)(id resPonseObj,int code))completeHandle;
{
    NSString *path = @"otc/order/appeal";
    NSMutableDictionary *dic = [NSMutableDictionary new];
    dic[@"orderSn"] = orderSn;
    dic[@"remark"] = remark;
    [self ylNonTokenRequestWithGET:path parameters:dic successBlock:^(id resultObject, int isSuccessed) {
        completeHandle(resultObject,isSuccessed);
    }];
}
//平台消息
+(void)getPlatformMessageForCompleteHandleWithPageNo:(NSString*)pageNo withPageSize:(NSString *)pageSize CompleteHandle:(void(^)(id resPonseObj,int code))completeHandle{
    NSString *path = @"uc/announcement/page";
    NSMutableDictionary *dic = [NSMutableDictionary new];
    dic[@"pageNo"] = pageNo;
    dic[@"pageSize"] = pageSize;
    [self ylNonTokenRequestWithGET:path parameters:dic successBlock:^(id resultObject, int isSuccessed) {
        completeHandle(resultObject,isSuccessed);
    }];
}
//获取更改绑定手机的验证码
+(void)changePhoneNumCodeForCompleteHandle:(void(^)(id resPonseObj,int code))completeHandle{
    NSString *path = @"uc/mobile/change/code";
    NSMutableDictionary *dic = [NSMutableDictionary new];
    [self ylNonTokenRequestWithGET:path parameters:dic successBlock:^(id resultObject, int isSuccessed) {
        completeHandle(resultObject,isSuccessed);
    }];
}
//更换手机号
+(void)changePhoneNumForPassword:(NSString *)password withPhone:(NSString*)phone withCode:(NSString *)code CompleteHandle:(void(^)(id resPonseObj,int code))completeHandle{
    NSString *path = @"uc/approve/change/phone";
    NSMutableDictionary *dic = [NSMutableDictionary new];
    dic[@"password"] = password;
    dic[@"phone"] = phone;
    dic[@"code"] = code;
    [self ylNonTokenRequestWithGET:path parameters:dic successBlock:^(id resultObject, int isSuccessed) {
        completeHandle(resultObject,isSuccessed);
    }];
}

//上传反馈意见
+(void)takeUpFeedBackForRemark:(NSString *)remark CompleteHandle:(void(^)(id resPonseObj,int code))completeHandle{
    NSString *path = @"uc/feedback";
    NSMutableDictionary *dic = [NSMutableDictionary new];
    dic[@"remark"] = remark;
    [self ylNonTokenRequestWithGET:path parameters:dic successBlock:^(id resultObject, int isSuccessed) {
        completeHandle(resultObject,isSuccessed);
    }];
}
//关于我们
+(void)aboutUSInfo:(void(^)(id resPonseObj,int code))completeHandle{
    NSString *path = @"uc/ancillary/website/info";
    NSMutableDictionary *dic = [NSMutableDictionary new];
    [self ylNonTokenRequestWithGET:path parameters:dic successBlock:^(id resultObject, int isSuccessed) {
        completeHandle(resultObject,isSuccessed);
    }];
}
//订单详情页
+(void)myBillDetailForId:(NSString *)orderSn CompleteHandle:(void(^)(id resPonseObj,int code))completeHandle{
    NSString *path = @"otc/order/detail";
    NSMutableDictionary *dic = [NSMutableDictionary new];
    dic[@"orderSn"] = orderSn;
    [self ylNonTokenRequestWithGET:path parameters:dic successBlock:^(id resultObject, int isSuccessed) {
        completeHandle(resultObject,isSuccessed);
    }];
}

//获取历史聊天记录
+(void)chatRecordDetailForId:(NSString *)orderSn uidTo:(NSString *)uidTo uidFrom:(NSString *)uidFrom limit:(NSString *)limit page:(NSString *)page sortFiled:(NSString *)sortFiled CompleteHandle:(void(^)(id resPonseObj,int code))completeHandle{
    NSString *path = @"chat/getHistoryMessage";
    NSMutableDictionary *dic = [NSMutableDictionary new];
    dic[@"orderId"] = orderSn;
//    dic[@"uidTo"] = uidTo;
//    dic[@"uidFrom"] = uidFrom;
    dic[@"limit"] = limit;
    dic[@"page"] = page;
    dic[@"sortFiled"] = sortFiled;
    [self ylNonTokenRequestWithGET:path parameters:dic successBlock:^(id resultObject, int isSuccessed) {
        completeHandle(resultObject,isSuccessed);
    }];
}

//查看历史委托
+(void)historyEntrustForPageNo:(NSString *)pageNo withPageSize:(NSString*)pageSize CompleteHandle:(void(^)(id resPonseObj,int code))completeHandle;
{
    NSString *path = @"exchange/order/history";
    NSMutableDictionary *dic = [NSMutableDictionary new];
    dic[@"pageNo"] = pageNo;
    dic[@"pageSize"] = pageSize;
    [self ylNonTokenRequestWithGET:path parameters:dic successBlock:^(id resultObject, int isSuccessed) {
        completeHandle(resultObject,isSuccessed);
    }];
}
//查看委托详情
+(void)historyEntrustDetailForOrderId:(NSString *)orderId  CompleteHandle:(void(^)(id resPonseObj,int code))completeHandle{
    NSString *path = orderId;
    NSMutableDictionary *dic = [NSMutableDictionary new];
    [self ylNonTokenRequestWithGET:path parameters:dic successBlock:^(id resultObject, int isSuccessed) {
        completeHandle(resultObject,isSuccessed);
    }];

}
//提交的身份认证信息获取
+(void)getIdentifyInfo:(void(^)(id resPonseObj,int code))completeHandle{
    NSString *path = @"uc/approve/real/detail";
    NSMutableDictionary *dic = [NSMutableDictionary new];
    [self ylNonTokenRequestWithGET:path parameters:dic successBlock:^(id resultObject, int isSuccessed) {
        completeHandle(resultObject,isSuccessed);
    }];
}
//获取交易对数据
+(void)getTradCoinForCompleteHandle:(void(^)(id resPonseObj,int code))completeHandle{
    NSString *path = @"market/symbol";
    NSMutableDictionary *dic = [NSMutableDictionary new];
    [self ylNonTokenRequestWithGET:path parameters:dic successBlock:^(id resultObject, int isSuccessed) {
        completeHandle(resultObject,isSuccessed);
    }];
}

//获取收款账户信息
+(void)getPayAccountInfoForCompleteHandle:(void(^)(id resPonseObj,int code))completeHandle{
    NSString *path = @"uc/approve/account/setting";
    NSMutableDictionary *dic = [NSMutableDictionary new];
    [self ylNonTokenRequestWithGET:path parameters:dic successBlock:^(id resultObject, int isSuccessed) {
        completeHandle(resultObject,isSuccessed);
    }];
}
//设置银行卡
+(void)setBankNumForUrlString:(NSString *)url withBank:(NSString *)bank withBranch:(NSString *)branch withJyPassword:(NSString *)jyPassword withRealName:(NSString *)realName withCardNo:(NSString *)cardNo CompleteHandle:(void(^)(id resPonseObj,int code))completeHandle{
    NSString *path = url;
    NSMutableDictionary *dic = [NSMutableDictionary new];
    dic[@"bank"] = bank;
    dic[@"branch"] = branch;
    dic[@"jyPassword"] = jyPassword;
    dic[@"realName"] = realName;
    dic[@"cardNo"] = cardNo;
    [self ylNonTokenRequestWithGET:path parameters:dic successBlock:^(id resultObject, int isSuccessed) {
        completeHandle(resultObject,isSuccessed);
    }];
}
//设置支付宝账号
+(void)setAliPayForUrlString:(NSString *)url withAli:(NSString *)ali withJyPassword:(NSString *)jyPassword withRealName:(NSString *)realName withQrCodeUrl:(NSString *)qrCodeUrl CompleteHandle:(void(^)(id resPonseObj,int code))completeHandle{
    NSString *path = url;
    NSMutableDictionary *dic = [NSMutableDictionary new];
    dic[@"ali"] = ali;
    dic[@"jyPassword"] = jyPassword;
    dic[@"realName"] = realName;
    dic[@"qrCodeUrl"] = qrCodeUrl;
    [self ylNonTokenRequestWithGET:path parameters:dic successBlock:^(id resultObject, int isSuccessed) {
        completeHandle(resultObject,isSuccessed);
    }];
}
//设置微信账号
+(void)setWeChatForUrlString:(NSString *)url withWechat:(NSString *)wechat withJyPassword:(NSString *)jyPassword withRealName:(NSString *)realName withQrCodeUrl:(NSString *)qrCodeUrl CompleteHandle:(void(^)(id resPonseObj,int code))completeHandle{
    NSString *path = url;
    NSMutableDictionary *dic = [NSMutableDictionary new];
    dic[@"wechat"] = wechat;
    dic[@"jyPassword"] = jyPassword;
    dic[@"realName"] = realName;
    dic[@"qrCodeUrl"] = qrCodeUrl;
    [self ylNonTokenRequestWithGET:path parameters:dic successBlock:^(id resultObject, int isSuccessed) {
        completeHandle(resultObject,isSuccessed);
    }];
}
//推广好友记录
+(void)getPromoteFriendsPageno:(NSString *)pageNo ForCompleteHandle:(void(^)(id resPonseObj,int code))completeHandle{
    NSString *path = @"uc/promotion/record";
    NSMutableDictionary *dic = [NSMutableDictionary new];
    dic[@"pageNo"] = pageNo;
    dic[@"pageSize"] = @"10";
    [self ylNonTokenRequestWithGET:path parameters:dic successBlock:^(id resultObject, int isSuccessed) {
        completeHandle(resultObject,isSuccessed);
    }];
}

//我的佣金记录
+(void)getMyCommissionPageno:(NSString *)pageNo ForCompleteHandle:(void(^)(id resPonseObj,int code))completeHandle{
    NSString *path = @"uc/promotion/reward/record";
    NSMutableDictionary *dic = [NSMutableDictionary new];
    dic[@"pageNo"] = pageNo;
    dic[@"pageSize"] = @"10";
    [self ylNonTokenRequestWithGET:path parameters:dic successBlock:^(id resultObject, int isSuccessed) {
        completeHandle(resultObject,isSuccessed);
    }];
}
//版本更新
+(void)versionUpdateForId:(NSString *)ID  CompleteHandle:(void(^)(id resPonseObj,int code))completeHandle{
    NSString *path = [NSString stringWithFormat:@"uc/ancillary/system/app/version/%@",ID];
    NSMutableDictionary *dic = [NSMutableDictionary new];
    [self requestWithGET:path parameters:dic successBlock:^(id resultObject, int isSuccessed) {
        completeHandle(resultObject,isSuccessed);
    }];
}
//删除我的广告
+(void)deleteAdvertiseForAdvertiseId:(NSString *)advertiseId  CompleteHandle:(void(^)(id resPonseObj,int code))completeHandle{
    NSString *path = @"otc/advertise/delete";
    NSMutableDictionary *dic = [NSMutableDictionary new];
    dic[@"id"] = advertiseId;
    [self ylNonTokenRequestWithGET:path parameters:dic successBlock:^(id resultObject, int isSuccessed) {
        completeHandle(resultObject,isSuccessed);
    }];
}

//持币分红
+(void)postBonusForAdvertiseId:(NSString *)advertiseId Param:(NSDictionary *)param CompleteHandle:(void(^)(id resPonseObj,int code))completeHandle{
    NSString *path = [NSString stringWithFormat:@"%@%@",HOST,@"uc/bonus/user/page"];
    NSMutableDictionary *dic = [NSMutableDictionary dictionaryWithDictionary:param];

    [self requestWithPost:path header:nil parameters:dic successBlock:^(id resultObject, int isSuccessed) {
        completeHandle(resultObject,isSuccessed);
    }];

}

//交易/邀请挖矿记录
+(void)getmainminingAdvertiseId:(NSString *)advertiseId inviterState:(NSString *)inviterState withPageSize:(NSString*)pageSize startime:(NSString *)startime endtime:(NSString *)endtime CompleteHandle:(void(^)(id resPonseObj,int code))completeHandle{
    NSString *path = @"uc/mine/list_es";
    NSMutableDictionary *dic = [NSMutableDictionary new];
    dic[@"memberId"] = advertiseId;
    dic[@"page"] = pageSize;
    dic[@"limit"] = @"10";
    if (startime.length > 0 && endtime.length > 0) {
        dic[@"startTime"] = startime;
        dic[@"endTime"] = endtime;
    }
    [self ylNonTokenRequestWithGET:path parameters:dic successBlock:^(id resultObject, int isSuccessed) {
        completeHandle(resultObject,isSuccessed);
    }];
}

//首页分配数据
+(void)Getindex_infoCompleteHandle:(void(^)(id resPonseObj,int code))completeHandle{
    NSString *path = @"market/index_info";
    NSMutableDictionary *dic = [NSMutableDictionary new];

    [self requesByAppendtWithGET:[NSString stringWithFormat:@"%@%@",HOST,path] parameters:dic successBlock:^(id resultObject, int isSuccessed) {
        completeHandle(resultObject,isSuccessed);

    }];
}

//账户状态(是否能发布广告)
+(void)userbusinessstatus:(void(^)(id resPonseObj,int code))completeHandle{
    NSString *path = @"uc/approve/certified/business/status";
    NSMutableDictionary *dic = [NSMutableDictionary new];

    [self requestWithGET:path parameters:dic successBlock:^(id resultObject, int isSuccessed) {
        completeHandle(resultObject,isSuccessed);
    }];
}
//币理财管理
+(void)financialorderlistParam:(NSDictionary *)param CompleteHandle:(void(^)(id resPonseObj,int code))completeHandle{



    NSString *path = [NSString stringWithFormat:@"%@%@",HOST,@"uc/financial/order/list"];
    NSMutableDictionary *dic = [NSMutableDictionary dictionaryWithDictionary:param];

    [self requestWithPost:path header:nil parameters:dic successBlock:^(id resultObject, int isSuccessed) {
        completeHandle(resultObject,isSuccessed);
    }];



}


//币理财项目
+(void)financialitemsCompleteHandle:(void(^)(id resPonseObj,int code))completeHandle{
    NSString *path = @"uc/financial/items/list";
    NSMutableDictionary *dic = [NSMutableDictionary new];

    [self requestWithGET:path parameters:dic successBlock:^(id resultObject, int isSuccessed) {
        completeHandle(resultObject,isSuccessed);
    }];
}

//资产流水
+(void)assettransactionParam:(NSDictionary *)param CompleteHandle:(void(^)(id resPonseObj,int code))completeHandle{
    NSString *path = [NSString stringWithFormat:@"%@%@",HOST,@"uc/asset/transaction/all"];
    NSMutableDictionary *dic = [NSMutableDictionary dictionaryWithDictionary:param];

    [self requestWithPost:path header:nil parameters:dic successBlock:^(id resultObject, int isSuccessed) {
        completeHandle(resultObject,isSuccessed);
    }];

}


//提币记录
+(void)withdrawrecordParam:(NSDictionary *)param CompleteHandle:(void(^)(id resPonseObj,int code))completeHandle{
    NSString *path = [NSString stringWithFormat:@"%@%@",HOST,@"uc/withdraw/record"];
    NSMutableDictionary *dic = [NSMutableDictionary dictionaryWithDictionary:param];

    [self requestWithPost:path header:nil parameters:dic successBlock:^(id resultObject, int isSuccessed) {
        completeHandle(resultObject,isSuccessed);
    }];

}
//币理财
+(void)financialitemsBHBCompleteHandle:(void(^)(id resPonseObj,int code))completeHandle{
    NSString *path = @"uc/financial/items/BHB";
    NSMutableDictionary *dic = [NSMutableDictionary new];
    [self requestWithGET:path parameters:dic successBlock:^(id resultObject, int isSuccessed) {
        completeHandle(resultObject,isSuccessed);
    }];
}



//申请成为商家
+(void)approvecertifiedbusinessParam:(NSDictionary *)param CompleteHandle:(void(^)(id resPonseObj,int code))completeHandle{
    NSString *path = [NSString stringWithFormat:@"%@%@",HOST,@"uc/approve/certified/business/apply"];
    NSMutableDictionary *dic = [NSMutableDictionary dictionaryWithDictionary:param];

    [self requestWithPost:path header:nil parameters:dic successBlock:^(id resultObject, int isSuccessed) {
        completeHandle(resultObject,isSuccessed);
    }];
}


//成为商家币种列表
+(void)approvebusinessauthdepositlist:(void(^)(id resPonseObj,int code))completeHandle{
    NSString *path = @"uc/approve/business-auth-deposit/list";
    NSMutableDictionary *dic = [NSMutableDictionary new];
    [self requestWithGET:path parameters:dic successBlock:^(id resultObject, int isSuccessed) {
        completeHandle(resultObject,isSuccessed);
    }];
}
//购买币理财
+(void)financialorderParam:(NSDictionary *)param CompleteHandle:(void(^)(id resPonseObj,int code))completeHandle{
    NSString *path = [NSString stringWithFormat:@"%@%@",HOST,@"uc/financial/order"];

    NSMutableDictionary *dic = [NSMutableDictionary dictionaryWithDictionary:param];

    [self requestWithPost:path header:nil parameters:dic successBlock:^(id resultObject, int isSuccessed) {
        completeHandle(resultObject,isSuccessed);
    }];
}

//用户协议
+(void)Userprotocol:(NSDictionary *)param CompleteHandle:(void(^)(id resPonseObj,int code))completeHandle{
    NSString *path = [NSString stringWithFormat:@"%@%@",HOST,@"uc/ancillary/more/help/detail"];

    NSMutableDictionary *dic = [NSMutableDictionary dictionaryWithDictionary:param];

    [self requestWithPost:path header:nil parameters:dic successBlock:^(id resultObject, int isSuccessed) {
        completeHandle(resultObject,isSuccessed);
    }];
}


//获取提币地址
+(void)getassetwalletresetaddress:(NSDictionary *)param CompleteHandle:(void(^)(id resPonseObj,int code))completeHandle{
    NSString *path = [NSString stringWithFormat:@"%@%@",HOST,@"uc/asset/wallet/reset-address"];
    NSMutableDictionary *dic = [NSMutableDictionary dictionaryWithDictionary:param];

    [self requestWithPost:path header:nil parameters:dic successBlock:^(id resultObject, int isSuccessed) {
        completeHandle(resultObject,isSuccessed);
    }];
}
//个人中心的竞猜类型条件接口
+(void)Getcoinguesstype:(void(^)(id resPonseObj,int code))completeHandle{
    NSString *path = @"uc/coin/guess/type";
    NSMutableDictionary *dic = [NSMutableDictionary new];
    [self requestWithGET:path parameters:dic successBlock:^(id resultObject, int isSuccessed) {
        completeHandle(resultObject,isSuccessed);
    }];
}

//个人中心投注记录
+(void)getcoinguessmemberId:(NSDictionary *)param CompleteHandle:(void(^)(id resPonseObj,int code))completeHandle{
    NSString *path = [NSString stringWithFormat:@"%@%@",HOST,@"uc/coin/guess/memberId"];
    NSMutableDictionary *dic = [NSMutableDictionary dictionaryWithDictionary:param];

    [self requestWithPost:path header:nil parameters:dic successBlock:^(id resultObject, int isSuccessed) {
        completeHandle(resultObject,isSuccessed);
    }];
}

//中奖详情
+(void)getcoinguessdetail:(NSDictionary *)param CompleteHandle:(void(^)(id resPonseObj,int code))completeHandle{
    NSString *path = [NSString stringWithFormat:@"%@%@",HOST,@"uc/coin/guess/detail"];
    NSMutableDictionary *dic = [NSMutableDictionary dictionaryWithDictionary:param];

    [self requestWithPost:path parameters:dic successBlock:^(id resultObject, int isSuccessed) {
        completeHandle(resultObject,isSuccessed);

    }];
}

//获取我的邀请用户级别数据
+(void)getMyPromotionCompleteHandle:(void(^)(id resPonseObj,int code))completeHandle{
    NSString *path = [NSString stringWithFormat:@"%@%@",HOST,@"uc/promotion/mypromotion"];
    [self requestWithPost:path header:nil parameters:nil successBlock:^(id resultObject, int isSuccessed) {
        completeHandle(resultObject,isSuccessed);

    }];
}

//获取币币账户usdt
+(void)getWalletUsdtdataCompleteHandle:(void(^)(id resPonseObj,int code))completeHandle{
    
    NSString *path = @"uc/asset/wallet/usdt";
       NSMutableDictionary *dic = [NSMutableDictionary new];
       [self ylNonTokenRequestWithGET:path parameters:dic successBlock:^(id resultObject, int isSuccessed) {
           completeHandle(resultObject,isSuccessed);
       }];
}

//账户划转
+(void)sentTransferCoinParam:(NSDictionary *)dict CompleteHandle:(void(^)(id resPonseObj,int code))completeHandle{
    NSString *path = @"swap/wallet/trans";
    [self ylNonTokenRequestWithGET:path parameters:dict successBlock:^(id resultObject, int isSuccessed) {
        completeHandle(resultObject,isSuccessed);
    }];
}
@end
