//
//  GTUtils.h
//  GTFramework
//
//  Created by LYJ on 15/5/18.
//  Copyright (c) 2015年 gt. All rights reserved.
//

#ifndef GTFramework_GTUtils_h
#define GTFramework_GTUtils_h

#if __has_feature(objc_generics)
#define GT3_GENERICS(class, ...) class<__VA_ARGS__>
#define GT3_GENERICS_TYPE(type) type
#else
#define GT3_GENERICS(class, ...) class
#define GT3_GENERICS_TYPE(type) id
#endif

#import <UIKit/UIKit.h>


/**
 极验验证状态的枚举量
 */
typedef NS_ENUM(NSInteger, GT3CaptchaState) {
    /** 验证未激活 */
    GT3CaptchaStateInactive = 0,
    /** 验证激活 */
    GT3CaptchaStateActive,
    /** 验证初始化中 */
    GT3CaptchaStateInitial,
    /** 验证等待交互中 */
    GT3CaptchaStateWaiting,
    /** 验证检测数据中 */
    GT3CaptchaStateCollecting,
    /** 验证结果判定中 */
    GT3CaptchaStateComputing,
    /** 验证通过 */
    GT3CaptchaStateSuccess,
    /** 验证失败 */
    GT3CaptchaStateFail,
    /** 验证取消 */
    GT3CaptchaStateCancel,
    /** 验证发生错误 */
    GT3CaptchaStateError
};

/**
 * 验证集群节点
 */
typedef NS_ENUM(NSInteger, GT3CaptchaServiceNode) {
    /** 中国服务集群*/
    GT3CaptchaServiceNodeCN = 0,
    /** 中国服务集群（支持 IPv6）*/
    GT3CaptchaServiceNodeCN_IPv6,
    /** 北美亚马逊云服务集群*/
    GT3CaptchaServiceNodeNA,
    /** 北美谷歌云服务集群*/
    GT3CaptchaServiceNodeNG,
    /** 默认服务集群*/
    GT3CaptchaServiceNodeDefault = GT3CaptchaServiceNodeCN
};

/**
 * 验证模式枚举量
 */
typedef NS_ENUM(NSInteger, GT3CaptchaMode) {
    /** 验证默认模式*/
    GT3CaptchaModeDefault,
    /** 验证宕机模式*/
    GT3CaptchaModeFailback,
    GT3CaptchaModeNoLogo,
    GT3CaptchaModeLogo
};

/**
 *  视图上结果的更新策略
 */
typedef NS_ENUM(NSInteger, GT3SecondaryCaptchaPolicy) {
    /** 二次验证通过 */
    GT3SecondaryCaptchaPolicyAllow,
    /** 二次验证拒绝 */
    GT3SecondaryCaptchaPolicyForbidden
};

/**
 *  图形验证的语言选项
 */
typedef NS_ENUM(NSInteger, GT3LanguageType) {
    /** Simplified Chinese 简体中文 */
    GT3LANGTYPE_ZH_CN = 0,
    /** Traditional Chinese 繁体中文 */
    GT3LANGTYPE_ZH_TW,
    /** Traditional Chinese 繁体中文 */
    GT3LANGTYPE_ZH_HK,
    /** Korean 韩语 */
    GT3LANGTYPE_KO_KR,
    /** Japenese 日语 */
    GT3LANGTYPE_JA_JP,
    /** English 英语 */
    GT3LANGTYPE_EN,
    /** Indonesian 印度尼西亚语 */
    GT3LANGTYPE_ID,
    /** Arabic 阿拉伯语 */
    GT3LANGTYPE_AR,
    /** German 德语 */
    GT3LANGTYPE_DE,
    /** Spanish 西班牙语 */
    GT3LANGTYPE_ES,
    /** French 法语 */
    GT3LANGTYPE_FR,
    /** Portuguese 葡萄牙语 */
    GT3LANGTYPE_PT_PT,
    /** Russian 俄语 */
    GT3LANGTYPE_RU,
    /** System language 跟随系统语言*/
    GT3LANGTYPE_AUTO = 999
};

/**
 *  活动指示器类型
 */
typedef NS_ENUM(NSInteger, GT3ActivityIndicatorType) {
    /** Geetest Defualt Indicator Type */
    GT3IndicatorTypeDefault = 0,
    /** System Indicator Type */
    GT3IndicatorTypeSystem,
    /** Cirle */
    GT3IndicatorTypeCirle,
    /** Custom Indicator Type */
    GT3IndicatorTypeCustom
};

/**
 *  验证默认回调
 */
typedef void(^GT3CaptchaDefaultBlock)(void);

/**
 *  自定义状态指示器的动画实现block
 *
 *  @param layer 状态指示器视图的layer
 *  @param size  layer的大小,默认 {64, 64}
 *  @param color layer的颜色,默认 蓝色 [UIColor colorWithRed:0.3 green:0.6 blue:0.9 alpha:1]
 */
typedef void(^GT3IndicatorAnimationViewBlock)(CALayer *layer, CGSize size, UIColor *color);

#endif
