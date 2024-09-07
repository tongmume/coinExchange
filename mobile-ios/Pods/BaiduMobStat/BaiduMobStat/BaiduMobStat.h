//
//  BaiduMobStat.h
//  BaiduMobStat
//
//  Created by Lidongdong on 15/7/22.
//  Copyright (c) 2015年 Baidu. All rights reserved.
//

#import <Foundation/Foundation.h>
#import <UIKit/UIKit.h>

NS_ASSUME_NONNULL_BEGIN

/**
 日志发送策略
 */
typedef enum _BaiduMobStatLogStrategy {
    BaiduMobStatLogStrategyAppLaunch = 0,   //每次程序启动时发送（默认策略，推荐使用!）
} BaiduMobStatLogStrategy;

/**
 推送平台定义
 */
typedef enum _BaiduMobStatPushPlatform{
    BaiduMobStatPushPlatformBaiduCloud = 0, //百度云推送平台
    BaiduMobStatPushPlatformJiGuang = 1,    //极光推送平台
    BaiduMobStatPushPlatformGeTui = 2,      //个推推送平台
    BaiduMobStatPushPlatformUmeng = 5,      //友盟推送平台
    BaiduMobStatPushPlatformXinGe = 6,      //信鸽推送平台
    BaiduMobStatPushPlatformAliYun = 7,     //阿里云推送平台
} BaiduMobStatPushPlatform;

/**
 百度移动应用统计接口
 当前版本 5.2.7_0
 */
@interface BaiduMobStat : NSObject
/**
 以下property属性，均为可选设置。
 如需设置，必须在startWithAppId:方法前调用才可生效。
 */

/**
 设置用户自定义的用户识别id，在startWithAppId之前调用
 设置一次UserId后，用户被永久标记。传入新的userId将替换老的userId。
 传入nil或空字符串@""，可清空标记。
 自定义规则的用户识别id（可以使登录用户账号、手机号等），长度限制256字节
 */
@property (nonatomic, copy, nullable) NSString *userId;

/**
 设置app的版本号
 从4.1版本开始，默认统计CFBundleShortVersionString中的版本号（即与AppStore上一致的版本号）
 如需统计自行设定的版本号，可由此传入
 */
@property (nonatomic, copy) NSString *shortAppVersion;

/**
 设置渠道Id
 默认值为 "AppStore"
 */
@property (nonatomic, copy) NSString *channelId;

/**
 是否启用Crash日志收集
 默认值 YES
 */
@property (nonatomic) BOOL enableExceptionLog;

/**
 是否仅在wifi网络状态下才发送日志
 默认值 NO
 */
@property (nonatomic) BOOL logSendWifiOnly;

/**
 设置应用进入后台再回到前台为同一次启动的最大间隔时间，有效值范围0～600s
 例如设置值30s，则应用进入后台后，30s内唤醒为同一次启动
 默认值 30s
 */
@property (nonatomic) int sessionResumeInterval;

/**
 设置日志发送策略
 默认值 BaiduMobStatLogStrategyAppLaunch
 */
@property (nonatomic) BaiduMobStatLogStrategy logStrategy;

/**
 设置是否打印SDK中的日志，用于调试
 默认值 NO
 */
@property (nonatomic) BOOL enableDebugOn;

/**
 设置设备adid
 若有需要，开发者可自行获取到adid后传入，使统计更精确
 默认值 空字符串:@""
 */
@property (nonatomic, copy) NSString *adid;

/**
 设置是否获取GPS信息（V4.1.1新增）
 默认为YES，获取GPS信息
 */
@property (nonatomic, assign) BOOL enableGps;

/**
 设置Crash日志中附带的信息
 长度限制256字节，超出截断
 */
@property (nonatomic, copy, nullable) NSString *crashExtraInfo;

/**
 获取统计对象的实例
 
 @return 一个统计对象实例
 */
+ (BaiduMobStat *)defaultStat;

/**
 设置用户自定义的session属性信息，设置后的下一个session将带上该属性。如果要当前session生效，需要在startWithAppId之前调用
 生效范围: 设置后到下一次冷启动之前生效，即热启动也会使用该属性
 传入nil，可清空标记。
 key值为用户提前在分析云网站创建的“属性名称”，若没有提前创建，则无统计效果。
 setSessionProperty、setPageProperty、setAutoEventProperty、setUserProperty共用属性个数，最多传入100个key-value值，超出部分无效
 每个value长度限制256字节,String格式传入，如果为是非类型则传入'1' '0'、如果是时间信息需要转为时间戳的string格式
 调用例子见demo工程
 */
- (void)setSessionProperty:(nullable NSDictionary *)sessionProperty;

/**
 分析云使用，添加页面属性。
 生效范围: 设置后下一次页面统计会带该属性
 传入nil，可清空标记。
 key值为用户提前在网站创建的“属性名称”，若没有提前创建，则无统计效果。
 setSessionProperty、setPageProperty、setAutoEventProperty、setUserProperty共用属性个数，最多传入100个key-value值，超出部分无效
 每个value长度限制256字节,String格式传入，如果为是非类型，则传入'1' '0'、如果是时间信息需要转为时间戳的string格式
 如果没有设置pageProperty，页面属性默认使用session属性
 调用例子见demo工程
 */
- (void)setPageProperty:(nullable NSDictionary *)pageProperty;

/**
 设置用户自定义的用户属性信息，在startWithAppId之前调用
 设置一次UserPorperty后，属性与该设备绑定。传入新的UserPorperty将替换老的UserPorperty内容。
 传入nil，可清空标记。
 key值为用户提前在网站创建的“属性名称”，若没有提前创建，则无统计效果。
 setSessionProperty、setPageProperty、setAutoEventProperty、setUserProperty共用属性个数，最多传入100个key-value值，超出部分无效
 每个value长度限制256字节,String格式传入，如果为是非类型，则传入'1' '0'、如果是时间信息需要转为时间戳的string格式
 调用例子见demo工程
 */
- (void)setUserProperty:(nullable NSDictionary *)userProperty;

/**
 设置应用的appkey，启动统计SDK。
 注意！！！以下行为Api调用前，必须先调用该接口。
 
 @param appKey 用户在mtj网站上创建应用，获取对应的appKey
 */
- (void)startWithAppId:(NSString *)appKey;

/**
 记录一次事件的点击，eventId请在网站上创建。未创建的evenId记录将无效。
 
 @param eventId 自定义事件Id，提前在网站端创建
 */
- (void)logEvent:(NSString *)eventId;

/**
 记录一次事件的时长，eventId请在网站上创建。未创建的evenId记录将无效。
 
 @param eventId 自定义事件Id，提前在网站端创建
 @param duration 已知的自定义事件时长，单位为毫秒（ms）
 */
- (void)logEventWithDurationTime:(NSString *)eventId durationTime:(unsigned long)duration;

/**
 记录一次事件的开始，eventId请在网站上创建。未创建的evenId记录将无效。
 
 @param eventId 自定义事件Id，提前在网站端创建
 */
- (void)eventStart:(NSString *)eventId;

/**
 记录一次事件的结束，eventId请在网站上创建。未创建的evenId记录将无效。
 
 @param eventId 自定义事件Id，提前在网站端创建
 */
- (void)eventEnd:(NSString *)eventId;

/**
 记录一次事件的点击，eventId和对应的attribute的key请在网站上创建，未创建的evenId和key将无法统计。
 
 @param eventId 事件Id，提前在网站端创建
 @param attributes 事件属性，对应的key需要在网站上创建，注意：value只接受NSString
 */
- (void)logEvent:(NSString *)eventId attributes:(nullable NSDictionary *)attributes;

/**
 记录一次事件的时长，eventId和对应的attribute的key请在网站上创建，未创建的evenId和key将无法统计。
 
 @param eventId 自定义事件Id，提前在网站端创建
 @param duration 已知的自定义事件时长，单位为毫秒（ms）
 @param attributes 事件属性，对应的key需要在网站上创建，注意：value只接受NSString
 */
- (void)logEventWithDurationTime:(NSString *)eventId durationTime:(unsigned long)duration attributes:(nullable NSDictionary *)attributes;

/**
 记录一次事件的结束，eventId和对应的attribute的key请在网站上创建，未创建的evenId和key将无法统计。
 
 @param eventId 自定义事件Id，提前在网站端创建
 @param attributes 事件属性，对应的key需要在网站上创建，注意：value只接受NSString
 */
- (void)eventEnd:(NSString *)eventId attributes:(nullable NSDictionary *)attributes;

/**
 记录某个页面访问的开始，请参见Example程序，在合适的位置调用。
 建议在ViewController的viewDidAppear函数中调用
 
 @param name 页面名称
 */
- (void)pageviewStartWithName:(NSString *)name;

/**
 记录某个页面访问的结束，与pageviewStartWithName配对使用，请参见Example程序，在合适的位置调用。
 建议在ViewController的viewDidDisappear函数中调用
 
 @param name 页面名称
 */
- (void)pageviewEndWithName:(NSString *)name;

/**
 记录WkWebView中的行为（需要在网页的JS代码中进行相应配置，详见文档与Demo程序）
 在WkWebview的代理方法:
 -(void)userContentController:(WKUserContentController *)userContentController didReceiveScriptMessage:(WKScriptMessage *)message
 中，调用此接口，传入参数为message.name和message.body，开始统计JS中的操作
 
 @param name WKScriptMessage的name
 @param body WKScriptMessage的body 只接受NSDictionary类型
 */
- (void)didReceiveScriptMessage:(NSString *)name body:(NSDictionary *)body;

/**
 主动上传的Exception信息记录
 
 @param exception 自己捕获的，需要上传的exception
 */
- (void)recordException:(NSException *)exception;

/**
 主动上传的NSError信息记录
 
 @param error 自己捕获的，需要上传的error
 */
- (void)recordError:(NSError *)error;

/**
 获取cuid的值
 返回SDK生成的cuid
 
 @return 设备Cuid
 */
- (NSString *)getDeviceCuid;


/**
 获取设备的测试ID，同时有Log打印
 返回SDK生成的设备测试ID
 
 @return 设备测试ID
 */
- (NSString *)getTestDeviceId;

/**
  上传第三方Push平台的Id，pushId长度限制1024字节。设置为nil或者空字符串，则清空对应平台的pushId
 
  @param pushId 从第三方Push SDK接口中获取的pushId
  @param platform 指定Push平台类型，详见枚举声明
 */
- (void)setPushId:(nullable NSString *)pushId platform:(BaiduMobStatPushPlatform)platform;
@end

/**
 以下方法未来可能会被弃用，请使用上边相应的无eventLabel参数的方法。

 eventId        自定义事件Id，提前在网站端创建
 eventLabel  自定义事件Label，不能为nil
 duration       已知的自定义事件时长，单位为毫秒（ms）
 attributes     事件属性，对应的key需要在网站上创建，注意：value只接受NSString
 
 @discussion eventLabel本质也是一个事件属性，所以可以弃用这个概念，仅使用attributes参数
 */
@interface BaiduMobStat (Deprecated)

- (void)logEvent:(NSString *)eventId eventLabel:(NSString *)eventLabel;
- (void)logEventWithDurationTime:(NSString *)eventId eventLabel:(NSString *)eventLabel durationTime:(unsigned long)duration;
- (void)eventStart:(NSString *)eventId eventLabel:(NSString *)eventLabel;
- (void)eventEnd:(NSString *)eventId eventLabel:(NSString *)eventLabel;

- (void)logEvent:(NSString *)eventId eventLabel:(NSString *)eventLabel attributes:(nullable NSDictionary *)attributes;
- (void)logEventWithDurationTime:(NSString *)eventId eventLabel:(NSString *)eventLabel durationTime:(unsigned long)duration attributes:(nullable NSDictionary *)attributes;
- (void)eventEnd:(NSString *)eventId eventLabel:(NSString *)eventLabel attributes:(nullable NSDictionary *)attributes;

@end

NS_ASSUME_NONNULL_END
