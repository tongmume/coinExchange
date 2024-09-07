//
//  localizationDefine.h
//  digitalCurrency
//
//  Created by sunliang on 2019/3/14.
//  Copyright © 2019年 BIZZAN. All rights reserved.
//

#ifndef localizationDefine_h
#define localizationDefine_h
#define LocalLanguageKey         @"LocalLanguageKey" //选择语言
#define LanguageChange           NSLocalizedString(@"LanguageChange", @"") //切换语言
#define SHOWCNY                  @"isShowCNY"//显示为人民币
#define SOCKETTERMINAL           @"1002";  //安卓:1001,苹果:1002,WEB:1003,PC:1004
#define CURRENTSELECTED_SYMBOL  @"CURRENTSELECTED_SYMBOL"//当前选择的交易对
#define SUBSCRIBE_SYMBOL  @"SUBSCRIBE_SYMBOL_THUMB"
#define SUBSCRIBE_KLINE  @"SUBSCRIBE_SYMBOL_KLINE"
#define SUBSCRIBE_EXCHANGE  @"SUBSCRIBE_EXCHANGE_TRADE"
#define HIDEMONEY   @"HIDEMONEY" //是否隐藏总金额
#define BIGGER   @"BIGGER" //放大K线视图(全屏)
#define SMALL   @"SMALL" //缩小K线视图(非全屏)
static  int    COMMANDS_VERSION = 1;
static  short  SUBSCRIBE_SYMBOL_THUMB = 20001; //订阅缩略行情
static  short  UNSUBSCRIBE_SYMBOL_THUMB = 20002;//取消订阅
static  short  PUSH_SYMBOL_THUMB = 20003;
// static  short  SUBSCRIBE_SYMBOL_KLINE = 20011; //订阅K线
// static  short  UNSUBSCRIBE_SYMBOL_KLINE = 20012;
// static  short  PUSH_SYMBOL_KLINE = 20013;
static  short  SUBSCRIBE_EXCHANGE_TRADE = 20021; //订阅盘口信息
static  short  UNSUBSCRIBE_EXCHANGE_TRADE = 20022;
static  short  PUSH_EXCHANGE_TRADE = 20023;//成交记录
static  short  PUSH_EXCHANGE_PLATE = 20024;
static  short  PUSH_EXCHANGE_KLINE = 20025;//k线
static  short  PUSH_EXCHANGE_ORDER_COMPLETED = 20026;//当前委托完成
static  short  PUSH_EXCHANGE_ORDER_CANCELED = 20027;//当前委托取消
static  short  PUSH_EXCHANGE_ORDER_TRADE = 20028;//当前委托变化
static  short  SUBSCRIBE_CHAT = 20031;//订阅聊天
static  short  UNSUBSCRIBE_CHAT = 20032;
static  short  PUSH_CHAT = 20033;
static  short  SEND_CHAT = 20034;//发送聊天
static  short  SUBSCRIBE_GROUP_CHAT = 20035; //订阅组聊天
static  short  UNSUBSCRIBE_GROUP_CHAT = 20036; //取消订阅组聊天
static  short  SUBSCRIBE_APNS = 20037; //订阅APNS
static  short  UNSUBSCRIBE_APNS = 20038;
static  short  PUSH_GROUP_CHAT = 20039;
static  short  PUSH_EXCHANGE_DEPTH = 20029;//深度图
static  short  HEARTBEAT = 11004; //心跳包指令
static  int    SOCKETREQUEST_LENGTH = 26;//消息头固定字节长度
static  int    SOCKETRESPONSE_LENGTH = 22;//响应头固定字节长度

// 以下合约socket获取数据指令
static  short  CONTRACT_SUBSCRIBE_SYMBOL_THUMB = 30001; //订阅缩略行情
static  short  CONTRACT_PUSH_SYMBOL_THUMB = 30003; //推送币种行情
static  short  SUBSCRIBE_EXCHANGE_TRADE_CONTRACT = 30021; //订阅交易信息（盘口，K线、成交明细）   盘口信息 
static  short  UNSUBSCRIBE_EXCHANGE_TRADE_CONTRACT = 30022; //取消订阅交易信息（盘口，K线、成交明细）   盘口信息 
static  short  CONTRACT_PUSH_EXCHANGE_PLATE = 30024; //推送盘口数据 
static  short  CONTRACT_UNSUBSCRIBE_SYMBOL_THUMB = 30002; //取消订阅行情 
static  short  CONTRACT_PUSH_EXCHANGE_TRADE = 30023; //推送交易明细
static  short  CONTRACT_PUSH_EXCHANGE_ORDER_TRADE = 30028; //推送订单成交信息（指定用户） 
static  short  CONTRACT_PUSH_EXCHANGE_DEPTH = 30029; //推送订单成交信息（指定用户） 
static  short  CONTRACT_PUSH_EXCHANGE_KLINE =30025; //推送K线    
static  short  CONTRACT_PUSH_EXCHANGE_ORDER_COMPLETED =30026; // 指令：推送订单完
static  short  CONTRACT_PUSH_EXCHANGE_ORDER_CANCELED =30027; // / 指令：推送订单取消信息（指定用户）

#endif /* localizationDefine_h */
