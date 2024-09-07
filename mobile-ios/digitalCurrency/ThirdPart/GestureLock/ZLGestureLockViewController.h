//
//  ZLGestureLockViewController.h
//  GestureLockDemo
//
//  Created by ZL on 2019/4/5.
//  Copyright © 2019年 ZL. All rights reserved.
//  手势密码界面 控制器

#import <UIKit/UIKit.h>

typedef NS_ENUM(NSInteger,ZLUnlockType) {
    ZLUnlockTypeCreatePsw, // 创建手势密码
    ZLUnlockTypeValidatePsw,// 登录校验手势密码
    ZLUnlockTypeDeletePsw // 校验手势密码并关闭手势解锁
};

@interface ZLGestureLockViewController : UIViewController

+ (void)deleteGesturesPassword;//删除手势密码
+ (NSString *)gesturesPassword;//获取手势密码

- (instancetype)initWithUnlockType:(ZLUnlockType)unlockType;

@end
