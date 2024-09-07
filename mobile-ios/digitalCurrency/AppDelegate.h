//
//  AppDelegate.h
//  digitalCurrency
//
//  Created by sunliang on 2019/1/26.
//  Copyright © 2019年 BIZZAN. All rights reserved.
//

#import <UIKit/UIKit.h>
typedef enum : NSUInteger {
   MINUTELINE=0,//1分钟
   HOURLINE,//1小时
   DAYLINE,//日K
   WEEKLINE //周K
} KlineType;

typedef void (^CommonVoidBlock)(void);

@interface AppDelegate : UIResponder <UIApplicationDelegate>
@property (strong, nonatomic) UIWindow *window;
@property(nonatomic,assign)KlineType klineType;
@property(nonatomic,assign)NSInteger startDrawIndex;//开始画K线点的序号
@property(nonatomic,assign)NSInteger kLineDrawNum;//画K线点的数量
@property(nonatomic,assign)BOOL isShowCNY;//行情价格是否显示为CNY
@property(nonatomic,strong)NSDecimalNumber* CNYRate;
@property (nonatomic, assign) BOOL isEable;

+ (instancetype)sharedAppDelegate;
// 代码中尽量改用以下方式去push/pop/present界面
- (UINavigationController *)navigationViewController;

- (UIViewController *)topViewController;

- (void)pushViewController:(UIViewController *)viewController;

- (void)pushViewController:(UIViewController *)viewController withBackTitle:(NSString *)title;

- (void)pushViewController:(UIViewController *)viewController withBackTitle:(NSString *)title backAction:(CommonVoidBlock)action;

- (NSArray *)popToViewController:(UIViewController *)viewController;

- (UIViewController *)popViewController;

- (NSArray *)popToRootViewController;

- (void)presentViewController:(UIViewController *)vc animated:(BOOL)animated completion:(void (^)(void))completion;
- (void)dismissViewController:(UIViewController *)vc animated:(BOOL)animated completion:(void (^)(void))completion;

@end

