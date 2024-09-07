//
//  XLBasePageController.h
//  XLPackKnowledge
//
//  Created by apple on 16/12/7.
//  Copyright © 2016年 apple. All rights reserved.
//

#import <UIKit/UIKit.h>

@class XLBScrollPageController;

#pragma mark View Pager Delegate
@protocol  XLBScrollPageControllerDelegate <NSObject>
@optional
///返回当前显示的视图控制器
-(void)viewPagerViewController:(XLBScrollPageController *)viewPager didFinishScrollWithCurrentViewController:(UIViewController *)viewController;
///返回当前将要滑动的视图控制器
-(void)viewPagerViewController:(XLBScrollPageController *)viewPager willScrollerWithCurrentViewController:(UIViewController *)ViewController;
@end

#pragma mark View Pager DataSource
@protocol XLBScrollPageControllerDataSource <NSObject>
@required
-(NSInteger)numberViewControllersInViewPager:(XLBScrollPageController *)viewPager;
-(UIViewController *)viewPager:(XLBScrollPageController *)viewPager indexViewControllers:(NSInteger)index;
-(NSString *)viewPager:(XLBScrollPageController *)viewPager titleWithIndexViewControllers:(NSInteger)index;
@optional
///设置控制器标题按钮的样式,不设置为默认
-(UIButton *)viewPager:(XLBScrollPageController *)viewPager titleButtonStyle:(NSInteger)index;
-(CGFloat)heightForTitleViewPager:(XLBScrollPageController *)viewPager;

///预留数据源
-(UIView *)headerViewForInViewPager:(XLBScrollPageController *)viewPager;
-(CGFloat)heightForHeaderViewPager:(XLBScrollPageController *)viewPager;
@end

@interface XLBScrollPageController : UIViewController
@property (nonatomic,weak) id<XLBScrollPageControllerDataSource> dataSource;
@property (nonatomic,weak) id<XLBScrollPageControllerDelegate> delegate;


///刷新
-(void)reloadScrollPage;

///默认选中
@property(nonatomic,assign) NSInteger selectIndex;

///按钮下划线的高度 字体大小 默认颜色  选中颜色
@property (nonatomic,assign) BOOL isScroll;
@property (nonatomic,assign) CGFloat lineWidth;
@property (nonatomic,strong) UIFont *titleFont;
@property (nonatomic,strong) UIColor *defaultColor;
@property (nonatomic,strong) UIColor *chooseColor;

@end

#pragma mark 标题按钮
@interface XLScrollBasePageTitleButton : UIButton

@property (nonatomic,assign) CGFloat buttonlineWidth;

@end
