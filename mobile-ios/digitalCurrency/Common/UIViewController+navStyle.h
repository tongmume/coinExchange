//
//  UIViewController+navStyle.h
//  horizonLoan
//
//  Created by sunliang on 2019/9/30.
//  Copyright © 2019年 BIZZAN. All rights reserved.
//

#import <UIKit/UIKit.h>
typedef void(^BackBlock) (UIButton *);
@interface UIViewController (navStyle)
//返回按钮
@property (nonatomic, weak) UIButton *backBtn;
//返回按钮：响应回调
@property (nonatomic, copy) BackBlock backBlock;
-(void)setNavigationControllerStyle;//设置导航栏样式
-(void)cancelNavigationControllerStyle;//还原导航栏样式
// 返回按钮：自定义图片
-(void)backBtnNoNavBar:(BOOL)noNavBar normalBack:(BOOL)normalBack;

/** 下拉刷新
 */
- (void)headRefreshWithScrollerView:(UIScrollView *)scrol;
//上拉加载
- (void)footRefreshWithScrollerView:(UIScrollView *)scrol;
//集成nav上右侧的控件点击
- (void)RightsetupNavgationItemWithpictureName:(NSString*)picturename;
//集成nav上左侧的控件
- (void)LeftsetupNavgationItemWithpictureName:(NSString*)picturenam;

/** 导航左侧按钮-文字
 */
-(void)leftBarItemWithTitle:(NSString *)title;
/** 导航右侧按钮-文字
 */
-(void)rightBarItemWithTitle:(NSString *)title;
//若用户未登录，则显示登录界面
-(void)showLoginViewController;


-(void)hideNavBar; //隐藏导航栏
-(void)showNavBar; //显示导航栏

@end
