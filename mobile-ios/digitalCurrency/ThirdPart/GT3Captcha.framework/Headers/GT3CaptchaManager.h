//
//  GTCaptchaManager.h
//  GTCaptcha
//
//  Created by NikoXu on 8/22/16.
//  Copyright © 2016 Geetest. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "GT3AsyncTaskProtocol.h"
#import "GT3Utils.h"
#import "GT3Error.h"

@protocol GT3CaptchaManagerDelegate, GT3CaptchaNetworkDelegate, GT3CaptchaManagerViewDelegate, GT3CaptchaManagerStatisticDelegate;

@interface GT3CaptchaManager : NSObject

/** SDK版本号 */
+ (NSString *)sdkVersion;

/** 验证管理代理 */
@property (nonatomic, weak) id<GT3CaptchaManagerDelegate> delegate;
/** 验证网络代理 */
@property (nonatomic, weak) id<GT3CaptchaNetworkDelegate> networkDelegate;
/** 验证视图代理 */
@property (nonatomic, weak) id<GT3CaptchaManagerViewDelegate> viewDelegate;
/** 验证统计代理 */
@property (nonatomic, weak) id<GT3CaptchaManagerStatisticDelegate> statisticDelegate;

/** 验证状态 */
@property (nonatomic, readonly) GT3CaptchaState captchaState;
/** 图形验证的展示状态 */
@property (nonatomic, readonly) BOOL isShowing;
/** 获取启动验证参数的接口 */
@property (nonatomic, readonly) NSURL *API_1;
/** 进行二次验证的接口 */
@property (nonatomic, readonly) NSURL *API_2;
/** 验证的ID */
@property (nonatomic, readonly, strong) NSString *gt_captcha_id;
/** 验证的会话标识 */
@property (nonatomic, readonly, strong) NSString *gt_challenge;
/** 验证的服务状态, 1正常/0宕机 */
@property (nonatomic, readonly, strong) NSNumber *gt_success_code;

/** 背景验证 */
@property (nonatomic, strong) UIColor *maskColor;

/**
 自定义 api2 验证结果显示时机，默认为 NO
 NO     - 验证弹窗显示后，在弹窗上操作验证成功后，不管 api2 具体的验证结果如何，立即显示验证成功
 YES    - 验证弹窗显示后，在弹窗上操作验证成功后，不显示验证结果，待 api2 验证完成之后，由调用者根据 api2 结果自行显示验证结果，若 api2 验证成功，请调用 `showGTCaptchaSuccessView` 方法显示成功，若 api2 验证失败，请调用 `destroyGTView` 方法直接关闭验证弹窗
 */
@property (nonatomic, assign) BOOL customAPI2ResultShowOccasion;

#pragma mark 基本方法

/**
 *  @abstract 验证初始化方法
 *
 *  @discussion 请不要在接口api_1和api_2的URL上带动态参数, 如果需要对api_1和api_2的请求做修改见GT3CaptchaManagerDelegate代理方法`gtCaptcha:willSendRequestAPI1:withReplacedHandler:`及 `gtCaptcha:willSendSecondaryCaptchaRequest:withReplacedRequest:`
 *
 *  @seealso `gtCaptcha:willSendRequestAPI1:withReplacedHandler:`及`gtCaptcha:willSendSecondaryCaptchaRequest:withReplacedRequest:`
 *
 *  @param api_1    获取验证参数的接口
 *  @param api_2    进行二次验证的接口
 *  @param timeout  超时时长
 *  @return GT3CaptchaManager 实例
 *
 */
- (instancetype)initWithAPI1:(NSString *)api_1
                        API2:(NSString *)api_2
                     timeout:(NSTimeInterval)timeout NS_DESIGNATED_INITIALIZER;

/**
 *  @abstract 取消异步请求
 *
 *  @discussion
 *  当希望取消正在执行的<b>NSURLSessionDataTask</b>时，调用此方法
 *
 *  ❗️<b>内部请求基于NSURLSeesion</b>
 */
- (void)cancelRequest;

/**
 *  @abstract 自定义配置验证参数
 *
 *  @discussion
 *  从后端sdk获取的验证参数, 其中单个challenge只能使用在同一次验证会话中
 *
 *  @param gt_id            在官网申请的captcha_id
 *  @param gt_challenge     根据极验服务器sdk生成的challenge
 *  @param gt_success_code  网站主服务器监测geetest服务的可用状态 0/1 不可用/可用
 *  @param api_2            用于二次验证的接口.网站主根据极验服务端sdk来部署.
 *
 */
- (void)configureGTest:(NSString *)gt_id
             challenge:(NSString *)gt_challenge
               success:(NSNumber *)gt_success_code
              withAPI2:(NSString *)api_2 API_DEPRECATED_WITH_REPLACEMENT("registerCaptchaWithCustomAsyncTask:completion:", ios(2.0, 6.0));

/**
 *
 *  @abstract 注册验证
 *
 *  @param completionHandler 注册成功后的回调
 */
- (void)registerCaptcha:(GT3CaptchaDefaultBlock)completionHandler;

/**
 *
 *  @abstract 注册验证，并且自定义 API1 及 API2 流程
 *
 *  @param customAsyncTask 自定义 API1 及 API2 任务对象
 *  @param completionHandler 注册成功后的回调
 *
 *  @discussion
 *  因管理器内部以弱引用方式持有 customAsyncTask，需要开发者自己
 *  在调用类中保持，以保证管理器在后续流程中能正常访问到该对象。
 *
 *  @seealso GT3AsyncTaskProtocol
 *
 */
- (void)registerCaptchaWithCustomAsyncTask:(id<GT3AsyncTaskProtocol>)customAsyncTask completion:(GT3CaptchaDefaultBlock)completionHandler;

/**
 *  ❗️<b>必要方法</b>❗️
 *  @abstract 开始验证
 *
 *  @discussion
 *  获取姿态, 提交分析后, 如有必要在`[[UIApplication sharedApplication].delegate window]`上显示极验验证的GTView验证视图
 *  极验验证GTWebView通过JS与SDK通信
 *  内部逻辑会根据当前的`captchaState`属性的状态不同而变更
 *
 */
- (void)startGTCaptchaWithAnimated:(BOOL)animated;

/**
 *  终止验证
 */
- (void)stopGTCaptcha;

/**
 *  @abstract 重置验证
 *
 *  @discussion
 *  内部先调用`stopGTCaptcha`后, 在主线程延迟0.3秒后
 *  执行`startCaptcha`的内部方法。
 *  只在`GT3CaptchaStateFail`,`GT3CaptchaStateError`,
 *  `GT3CaptchaStateSuccess`, `GT3CaptchaStateCancel`状态下执行。
 */
- (void)resetGTCaptcha;

/**
 *  若验证显示则关闭验证界面
 */
- (void)closeGTViewIfIsOpen;

/**
 *  获取cookie value
 *
 *  @param cookieName cookie的键名
 *  @return 对应的cookie的值
 */
- (NSString *)getCookieValue:(NSString *)cookieName;

#pragma mark 其他配置的方法

/**
 *  @abstract 图形验证超时的时长
 *
 *  @param timeout GT3WebView资源请求超时时间
 */
- (void)useGTViewWithTimeout:(NSTimeInterval)timeout;

/**
*  @abstract 设置图形验证的圆角大小
*
*  @param cornerRadius 圆角大小, 大小不超过 30 px
*/
- (void)useGTViewWithCornerRadius:(CGFloat)cornerRadius;

/**
 *  @abstract 验证静态参数
 *
 *  @discussion
 *  内部将参数转换成表单格式，并拼接到静态资源的请求上
 *
 *  @param params 自定义参数
 */
- (void)useGTViewWithParams:(NSDictionary *)params;

/**
 *  @abstract 验证标题
 *
 *  @discussion
 *  默认不开启. 字符长度不能超过28, 一个中文字符为两个2字符长度.
 *
 *  @param title 验证标题字符串
 */
- (void)useGTViewWithTitle:(NSString *)title;

/**
 *  @abstract 配置状态指示器
 *
 *  @discussion
 *  为了能方便的调试动画,真机调试模拟低速网络 Settings->Developer
 *  ->Status->Enable->Edge(😂)
 *
 *  @param animationBlock 自定义时需要实现的动画block,仅在type配置为GTIndicatorCustomType时才执行
 *  @param type           状态指示器的类型
 */
- (void)useAnimatedAcitvityIndicator:(GT3IndicatorAnimationViewBlock)animationBlock
                         withIndicatorType:(GT3ActivityIndicatorType)type;

/**
 *  @abstract 配置背景模糊
 *
 *  @discussion
 *  iOS8以上生效
 *
 *  @param blurEffect 模糊特效
 */
- (void)useVisualViewWithEffect:(UIBlurEffect *)blurEffect;

/**
 *  @abstract 切换验证语言
 *
 *  @discussion
 *  默认跟随系统语言。不支持的语言则显示为英文。
 *
 *  @param type 语言类型
 */
- (void)useLanguage:(GT3LanguageType)type;

/**
 *  @abstract 切换验证语言
 *
 *  @discussion
 *  未设置或传 nil，默认跟随系统语言。不支持的语言则使用英文。
 *
 *  @param lang 语言简码。请参考相关的语言简码列表。
 */
- (void)useLanguageCode:(NSString *)lang;

/**
 *  @abstract 切换验证服务集群节点
 *
 *  @discussion
 *  默认中国节点。使用其他节点需要使用相应的配置，否则无法正确访问验证服务。
 *  使用该方法前，请充分了解极验的服务集群节点。
 *
 *  @param node 集群节点
 */
- (void)useServiceNode:(GT3CaptchaServiceNode)node;

/**
 *  @abstract 完全使用HTTPS协议请求验证
 *
 *  @discussion
 *  默认开启HTTPS
 *
 *  @param disable 是否禁止https支持
 */
- (void)disableSecurityAuthentication:(BOOL)disable;

/**
 *  @abstract 验证背景交互事件的开关
 *
 *  @discussion 默认关闭
 *
 *  @param disable YES忽略交互事件/NO接受交互事件
 */
- (void)disableBackgroundUserInteraction:(BOOL)disable;

/**
 *  @abstract 控制验证管理器内部的网络可达性检测
 *
 *  @param enable YES 开启/NO 关闭. 默认YES.
 */
- (void)enableNetworkReachability:(BOOL)enable;

/**
 *  @abstract Debug Mode
 *
 *  @discussion
 *  开启debugMode,在开启验证之前调用此方法
 *  默认不开启
 *
 *  @param enable YES开启,NO关闭
 */
- (void)enableDebugMode:(BOOL)enable;

/**
 * @abstract 设置是否允许打印日志
 *
 * @param enabled YES，允许打印日志 NO，禁止打印日志
 */
+ (void)setLogEnabled:(BOOL)enabled;

/**
 * @abstract 是否允许打印日志
 *
 * @return YES，允许打印日志 NO，禁止打印日志
 */
+ (BOOL)isLogEnabled;

/**
 * @abstract 销毁验证弹窗视图
 */
- (void)destroyGTView;

/**
 * @abstract 隐藏验证弹窗视图
 */
- (void)hideGTView;

/**
 * @abstract 显示验证成功视图
 */
- (void)showGTCaptchaSuccessView;

@end

#pragma mark - 验证代理方法

@protocol GT3CaptchaManagerDelegate <NSObject>

@required
/**
 *  验证错误处理
 *
 *  @discussion 抛出内部错误, 比如GTWebView等错误
 *
 *  @param manager  验证管理器
 *  @param error    错误源
 */
- (void)gtCaptcha:(GT3CaptchaManager *)manager errorHandler:(GT3Error *)error;

/**
 *  @abstract 通知已经收到二次验证结果, 在此处理最终验证结果
 *
 *  @discussion
 *  二次验证的错误只在这里返回, `decisionHandler`需要处理
 *
 *  @param manager          验证管理器
 *  @param data             二次验证返回的数据
 *  @param response         二次验证的响应
 *  @param error            错误源
 *  @param decisionHandler  更新验证结果的视图
 */
- (void)gtCaptcha:(GT3CaptchaManager *)manager didReceiveSecondaryCaptchaData:(NSData *)data response:(NSURLResponse *)response error:(GT3Error *)error decisionHandler:(void (^)(GT3SecondaryCaptchaPolicy captchaPolicy))decisionHandler;

@optional

/**
 *  @abstract 是否使用内部默认的API1请求逻辑
 *
 *  @param manager 验证管理器
 *  @return YES使用,NO不使用
 */
- (BOOL)shouldUseDefaultRegisterAPI:(GT3CaptchaManager *)manager;

/**
 *  @abstract 将要向<b>API1</b>发送请求的时候调用此方法, 通过此方法可以修改将要发送的请求
 *
 *  @warning 不支持子线程操作。
 *
 *  @discussion 调用此方法的时候必须执行<b>requestHandler</b>, 否则可能导致内存泄露。
 *
 *  @param manager         验证管理器
 *  @param originalRequest 默认发送的请求对象
 *  @param replacedHandler 修改请求的执行block
 */
- (void)gtCaptcha:(GT3CaptchaManager *)manager willSendRequestAPI1:(NSURLRequest *)originalRequest withReplacedHandler:(void (^)(NSURLRequest * request))replacedHandler;

/**
 *  @abstract 当接收到从<b>API1</b>的数据, 通知返回字典, 包括<b>gt_public_key</b>,
 *  <b>gt_challenge</b>, <b>gt_success_code</b>
 *
 *  @warning 不支持子线程操作。
 *
 *  @discussion
 *  如果实现此方法, 需要解析验证需要的数据并返回。
    如果不返回验证初始化数据, 使用内部的解析规则进行解析。默认先解析一级结构, 再匹配键名为"data"或"gtcap"中的数据。
 *
 *  @param manager      验证管理器
 *  @param dictionary   API1返回的数据(未解析)
 *  @param error        返回的错误
 *
 *  @return 验证初始化数据, 格式见下方
 <pre>
 {
 "challenge" : "12ae1159ffdfcbbc306897e8d9bf6d06",
 "gt" : "ad872a4e1a51888967bdb7cb45589605",
 "success" : 1
 }
 </pre>
 */
- (NSDictionary *)gtCaptcha:(GT3CaptchaManager *)manager didReceiveDataFromAPI1:(NSDictionary *)dictionary withError:(GT3Error *)error;

/**
 *  @abstract 通知接收到返回的验证交互结果
 *
 *  @discussion 此方法仅仅是前端返回的初步结果, 并非验证最终结果。
 *
 *  @param manager 验证管理器
 *  @param code    验证交互结果, 0失败/1成功
 *  @param result  二次验证数据
 *  @param message 附带消息
 */
- (void)gtCaptcha:(GT3CaptchaManager *)manager didReceiveCaptchaCode:(NSString *)code result:(NSDictionary *)result message:(NSString *)message;

/**
 *  @abstract 是否使用内部默认的API2请求逻辑
 *
 *  @discussion 默认返回YES;
 *
 *  @param manager 验证管理器
 *  @return YES使用,NO不使用
 */
- (BOOL)shouldUseDefaultSecondaryValidate:(GT3CaptchaManager *)manager;

/**
 *  @abstract 通知即将进行二次验证, 再次修改发送至<b>API2</b>的验证。
 *
 *  @warning 不支持子线程操作。
 *
 *  @discussion
 *  请不要修改<b>requestHandler</b>执行所在的线程或队列, 否则可能导
 *  请求修改失败. 二次验证的请求方式应为<b>POST</b>, 头部信息应为:
 *  <pre>{"Content-Type":@"application/x-www-form-urlencoded;charset=UTF-8"}</pre>
 *
 *  @param manager        验证管理器
 *  @param replacedRequest 修改二次验证请求的block
 */
- (void)gtCaptcha:(GT3CaptchaManager *)manager willSendSecondaryCaptchaRequest:(NSURLRequest *)originalRequest withReplacedRequest:(void (^)(NSMutableURLRequest * request))replacedRequest;

/**
 *  @abstract 用户主动关闭了验证码界面
 *
 *  @param manager 验证管理器
 */
- (void)gtCaptchaUserDidCloseGTView:(GT3CaptchaManager *)manager;

@end

@protocol GT3CaptchaNetworkDelegate <NSObject>

- (void)gtCaptcha:(GT3CaptchaManager *)manager didReceiveChallenge:(NSURLAuthenticationChallenge *)challenge
 completionHandler:(void (^)(NSURLSessionAuthChallengeDisposition disposition, NSURLCredential * credential))completionHandler;

@end

@protocol GT3CaptchaManagerViewDelegate <NSObject>

@optional

/**
 *  @abstract 通知验证模式
 *
 *  @param manager 验证管理器
 *  @param mode    验证模式
 */
- (void)gtCaptcha:(GT3CaptchaManager *)manager notifyCaptchaMode:(GT3CaptchaMode)mode;

/**
 *  @abstract 通知将要显示图形验证
 *
 *  @param manager 验证管理器
 */
- (void)gtCaptchaWillShowGTView:(GT3CaptchaManager *)manager;

/**
 *  @abstract 更新验证状态
 *
 *  @param manager 验证管理器
 *  @param state   验证状态
 *  @param error   错误信息
 */
- (void)gtCaptcha:(GT3CaptchaManager *)manager updateCaptchaStatus:(GT3CaptchaState)state error:(GT3Error *)error;

/**
 *  @abstract 更新验证视图
 *
 *  @param manager         验证管理器
 *  @param fromValue       起始值
 *  @param toValue         终止值
 *  @param timeInterval    时间间隔
 */
- (void)gtCaptcha:(GT3CaptchaManager *)manager updateCaptchaViewWithFactor:(CGFloat)fromValue to:(CGFloat)toValue timeInterval:(NSTimeInterval)timeInterval;

@end

@protocol GT3CaptchaManagerStatisticDelegate <NSObject>

@optional

- (void)gtCaptchaDidStartCaptcha:(GT3CaptchaManager *)manager;
- (void)gtCaptcha:(GT3CaptchaManager *)manager didReceiveFullpageResult:(NSString *)result;
- (void)gtCaptchaNotifyGTViewDidReady:(GT3CaptchaManager *)manager;

- (void)gtCaptcha:(GT3CaptchaManager *)manager didReturnStatisticInfomation:(NSData *)data;

@end
