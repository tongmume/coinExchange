//
//  UIViewController+navStyle.m
//  horizonLoan
//
//  Created by sunliang on 2019/9/30.
//  Copyright © 2019年 BIZZAN. All rights reserved.
//

#import "UIViewController+navStyle.h"
#import <objc/runtime.h>
#import "ToolUtil.h"
#import "YLNavigationController.h"
@implementation UIViewController (navStyle)

-(void)setNavigationControllerStyle{
    [self.navigationController.navigationBar setBackgroundImage:[UIImage new] forBarMetrics:UIBarMetricsDefault];//去除导航栏黑线
    [self.navigationController.navigationBar setShadowImage:[UIImage new]];
}
-(void)cancelNavigationControllerStyle{
    [self.navigationController.navigationBar setBackgroundImage:nil forBarMetrics:UIBarMetricsDefault];
    [self.navigationController.navigationBar setShadowImage:nil];
}
static char *BackBtnKey = "backBtnKey";
- (void)setBackBtn:(UIButton *)backBtn {
    objc_setAssociatedObject(self, BackBtnKey, backBtn, OBJC_ASSOCIATION_ASSIGN);
}
- (UIButton *)backBtn {
    return objc_getAssociatedObject(self, BackBtnKey);
}
//集成nav上右侧的控件
-(void)RightsetupNavgationItemWithpictureName:(NSString*)picturename{
    UIBarButtonItem *rightItem = [[UIBarButtonItem alloc]initWithImage:[UIImage imageNamed:picturename] style:UIBarButtonItemStylePlain target:self action:@selector(RighttouchEvent)];
    self.navigationItem.rightBarButtonItem = rightItem;
}
-(void)RighttouchEvent{

}
//集成nav上左侧的控件
- (void)LeftsetupNavgationItemWithpictureName:(NSString*)picturename{
    UIBarButtonItem *leftItem = [[UIBarButtonItem alloc]initWithImage:[UIImage imageNamed:picturename] style:UIBarButtonItemStylePlain target:self action:@selector(LefttouchEvent)];
    self.navigationItem.leftBarButtonItem = leftItem;
}
-(void)LefttouchEvent{


}
/** 导航左侧按钮-文字
 */
-(void)leftBarItemWithTitle:(NSString *)title{

    UIBarButtonItem*item=[[UIBarButtonItem alloc]initWithTitle:title style:UIBarButtonItemStylePlain target:self action:@selector(LefttouchEvent)];
    [item setTintColor:[UIColor whiteColor]];
    [item setTitleTextAttributes:[NSDictionary dictionaryWithObjectsAndKeys:[UIFont boldSystemFontOfSize:14],NSFontAttributeName, nil] forState:UIControlStateNormal];
    [item setTitleTextAttributes:[NSDictionary dictionaryWithObjectsAndKeys:[UIFont boldSystemFontOfSize:14],NSFontAttributeName, nil] forState:UIControlStateSelected];
    self.navigationItem.leftBarButtonItem = item;

}
/** 导航右侧按钮-文字
 */
-(void)rightBarItemWithTitle:(NSString *)title{

    UIBarButtonItem*item=[[UIBarButtonItem alloc]initWithTitle:title style:UIBarButtonItemStylePlain target:self action:@selector(RighttouchEvent)];
    [item setTintColor:kRGBColor(153, 153, 153)];
    [item setTitleTextAttributes:[NSDictionary dictionaryWithObjectsAndKeys:[UIFont boldSystemFontOfSize:14],NSFontAttributeName, nil] forState:UIControlStateNormal];
    [item setTitleTextAttributes:[NSDictionary dictionaryWithObjectsAndKeys:[UIFont boldSystemFontOfSize:14],NSFontAttributeName, nil] forState:UIControlStateSelected];
    self.navigationItem.rightBarButtonItem = item;
}
// 返回按钮：自定义图片
-(void)backBtnNoNavBar:(BOOL)noNavBar normalBack:(BOOL)normalBack
{
    CGFloat ww=25, hh=28;
    //隐藏系统的
    //self.navigationItem.hidesBackButton = YES;
    //返回按钮
    UIButton *backBtn = [[UIButton alloc] initWithFrame:CGRectMake(0,0,ww, hh)];
    [backBtn setImage:[UIImage imageNamed:@"back"] forState:UIControlStateNormal];
    [backBtn addTarget:self action:@selector(backNav:) forControlEvents:UIControlEventTouchUpInside];
    backBtn.tag = normalBack;
    backBtn.backgroundColor = [UIColor clearColor];
    if (noNavBar) {
        backBtn.frame = CGRectMake(10,27,ww, hh);
        [self.view addSubview:backBtn];
        self.backBtn = backBtn;
    }else{
        UIImage *img = [UIImage imageNamed:@"back"];
        UIBarButtonItem *baritem = [[UIBarButtonItem alloc]init];
        baritem.image = [img imageWithRenderingMode:UIImageRenderingModeAlwaysOriginal];
        [baritem setTarget:self];
        [baritem setAction:@selector(ItemBackAction)];
        self.navigationItem.leftBarButtonItem= baritem;

//        UIBarButtonItem *leftBarBtn= [[UIBarButtonItem alloc]initWithCustomView:backBtn];
//        self.navigationItem.leftBarButtonItem= leftBarBtn;
//        self.backBtn = backBtn;
//        if ([[UIDevice currentDevice].systemVersion floatValue] >= 11.0) {
//            self.backBtn.contentHorizontalAlignment =UIControlContentHorizontalAlignmentLeft;
//            [self.backBtn setImageEdgeInsets:UIEdgeInsetsMake(0, -5, 0, 0)];
//        }
    }
}

-(void)ItemBackAction{
    [self.navigationController popViewControllerAnimated:YES];

}
-(void)backNav:(UIButton *)Btn {
    //正常返回
    if (Btn.tag==1) {
        [self.navigationController popViewControllerAnimated:YES];
    }
   else {
        //其他情况返回
        if (self.backBlock) {
            self.backBlock(Btn);
        }
    }
}

// BackBlock -> backBlock
- (BackBlock)backBlock {
    return objc_getAssociatedObject(self, @selector(backBlock));
}
- (void)setBackBlock:(BackBlock)backBlock {
    objc_setAssociatedObject(self,
                             @selector(backBlock),
                             backBlock,
                             OBJC_ASSOCIATION_COPY_NONATOMIC);
}

/** 下拉刷新
 */
- (void)headRefreshWithScrollerView:(UIScrollView *)scrol{
    MJRefreshNormalHeader *header = [MJRefreshNormalHeader headerWithRefreshingBlock:^{
        dispatch_after(dispatch_time(DISPATCH_TIME_NOW, (int64_t)(1.0 * NSEC_PER_SEC)), dispatch_get_main_queue(), ^{
            [self refreshHeaderAction];
            [scrol.mj_header endRefreshing];
        });
    }];
    header.lastUpdatedTimeLabel.hidden = YES;
    header.stateLabel.hidden = YES;
    header.activityIndicatorViewStyle = UIActivityIndicatorViewStyleWhite;
//    header.automaticallyChangeAlpha = YES;
    scrol.mj_header = header;
}
/** 上拉加载
 */
- (void)footRefreshWithScrollerView:(UIScrollView *)scrol{
    MJRefreshBackNormalFooter *footer = [MJRefreshBackNormalFooter footerWithRefreshingBlock:^{
        dispatch_after(dispatch_time(DISPATCH_TIME_NOW, (int64_t)(1.0 * NSEC_PER_SEC)), dispatch_get_main_queue(), ^{
            [self refreshFooterAction];
            [scrol.mj_footer endRefreshing];// 结束刷新
        });
    }];
    footer.stateLabel.hidden = YES;
    scrol.mj_footer = footer;
    footer.activityIndicatorViewStyle = UIActivityIndicatorViewStyleWhite;
    scrol.mj_footer.automaticallyHidden = YES;
}
- (void)refreshFooterAction{

}
- (void)refreshHeaderAction{


}

#pragma mark -若用户未登录，则显示登录界面
-(void)showLoginViewController{
    LoginViewController*loginVC=[[LoginViewController alloc]init];
    YLNavigationController *nav = [[YLNavigationController alloc]initWithRootViewController:loginVC];
    nav.modalPresentationStyle=UIModalPresentationFullScreen;
    [self presentViewController:nav animated:YES completion:nil];
}

-(void)hideNavBar{
    UIImage *image = [[UIImage alloc] init];
    //设置导航栏背景图片为一个空的image，这样就透明了
    [self.navigationController.navigationBar setBackgroundImage:image forBarMetrics:UIBarMetricsDefault];
    //去掉透明后导航栏下边的黑边
    [self.navigationController.navigationBar setShadowImage:image];
    self.navigationController.navigationBar.barStyle = UIBarStyleBlack;
    self.navigationController.navigationBar.translucent = YES;
}
//MARK:--显示导航栏
-(void)showNavBar{
    [self.navigationController.navigationBar setBackgroundImage:nil forBarMetrics:UIBarMetricsDefault];
    [self.navigationController.navigationBar setShadowImage:nil];
    self.navigationController.navigationBar.barStyle = UIBarStyleDefault;
    self.navigationController.navigationBar.translucent = NO;
}
@end
