//
//  FrenchCurrencyViewController.m
//  digitalCurrency
//
//  Created by chu on 2019/8/2.
//  Copyright © 2019年 BIZZAN. All rights reserved.
//

#import "FrenchCurrencyViewController.h"
#import "FSBaseTableView.h"
#import "FSBaselineTableViewCell.h"
#import "FSScrollContentView.h"
#import "FSBottomTableViewCell.h"

#import "BuyCoinsChildViewController.h"
#import "SellCoinsChildViewController.h"
#import "YBPopupMenu.h"
#import "AdvertisingBuyViewController.h"
#import "AdvertisingSellViewController.h"
#import "MineNetManager.h"

#import "ApplyBusinessViewController.h"
#import "BeingauditedBusViewController.h"
#import "SuccessBusViewController.h"
#import "AccountSettingInfoModel.h"
#import "FailBusinessViewController.h"
#import "SuccessSurrenderViewController.h"
#import "WaitBusinessViewController.h"
#import "RetreatBusinessViewController.h"

@interface FrenchCurrencyViewController ()<UITableViewDelegate,UITableViewDataSource,FSPageContentViewDelegate,FSSegmentTitleViewDelegate,YBPopupMenuDelegate>

@property (nonatomic, strong) FSBaseTableView *tableView;
@property (nonatomic, strong) FSBottomTableViewCell *contentCell;
@property (nonatomic, strong) UISegmentedControl *seg;
@property (nonatomic, assign) BOOL canScroll;
@property (nonatomic,assign)NSInteger memberLevel;
@property(nonatomic,strong) AccountSettingInfoModel *accountInfo;//用户状态
@property (nonatomic,copy)NSString *reasonstr;
@end

@implementation FrenchCurrencyViewController

- (void)dealloc
{
    [[NSNotificationCenter defaultCenter] removeObserver:self];
}

- (void)viewWillAppear:(BOOL)animated{
    [super viewWillAppear:animated];
    self.navigationItem.title = LocalizationKey(@"Currencyexchange");

    if ([YLUserInfo isLogIn]) {
        [self businessstatus];
        [self accountSettingData];
    }
}


- (void)viewDidLoad {
    [super viewDidLoad];
    // Do any additional setup after loading the view.
    [[NSNotificationCenter defaultCenter] addObserver:self selector:@selector(changeScrollStatus) name:@"leaveTop" object:nil];
//    self.automaticallyAdjustsScrollViewInsets = NO;
    [self setupSubViews];
    [self RightsetupNavgationItemWithpictureName:@"fabu"];
//    self.hidesBottomBarWhenPushed = NO;
}

- (void)RighttouchEvent{
    NSArray *namearray = @[LocalizationKey(@"postPurchaseAdvertising"),LocalizationKey(@"sellAdvertising")];

    [YBPopupMenu showAtPoint:CGPointMake(kWindowW - 32, NEW_NavHeight - 15) titles:namearray icons:nil menuWidth:130 otherSettings:^(YBPopupMenu *popupMenu) {
        popupMenu.arrowDirection = YBPopupMenuArrowDirectionNone;
        popupMenu.delegate = self;
        popupMenu.textColor = RGBOF(0x999999);
        popupMenu.backColor = mainColor;

    }];
}

#pragma mark - YBPopupMenuDelegate
- (void)ybPopupMenu:(YBPopupMenu *)ybPopupMenu didSelectedAtIndex:(NSInteger)index
{
    if(![YLUserInfo isLogIn]){
        [self showLoginViewController];
        return;
    }
    if (self.memberLevel != 2) {
        [self.view makeToast:LocalizationKey(@"Uncertifiedbusinesses") duration:1.5 position:CSToastPositionCenter];
        return;
    }
    if (index == 0) {
        //购买
        AdvertisingBuyViewController *buyVC = [[AdvertisingBuyViewController alloc] init];
        buyVC.hidesBottomBarWhenPushed = YES;
        [self.navigationController pushViewController:buyVC animated:YES];
    }

    if (index == 1) {

        //出售
        AdvertisingSellViewController *sellVC = [[AdvertisingSellViewController alloc] init];
        sellVC.hidesBottomBarWhenPushed = YES;
        [self.navigationController pushViewController:sellVC animated:YES];

    }

}

//验证用户是否为验证商家
-(void)businessstatus{

    [MineNetManager userbusinessstatus:^(id resPonseObj, int code) {
        if (code) {
            if ([resPonseObj[@"code"] integerValue] == 0) {

                self.memberLevel = [[[resPonseObj objectForKey:@"data"] objectForKey:@"certifiedBusinessStatus"] integerValue];
                self.reasonstr = [[resPonseObj objectForKey:@"data"] objectForKey:@"detail"];
            }else{
                [self.view makeToast:resPonseObj[MESSAGE] duration:1.5 position:CSToastPositionCenter];
            }
        }else{
            [self.view makeToast:LocalizationKey(@"noNetworkStatus") duration:1.5 position:CSToastPositionCenter];
        }
    }];
}

//MARK:--账号设置的状态信息获取
-(void)accountSettingData{

    [MineNetManager accountSettingInfoForCompleteHandle:^(id resPonseObj, int code) {

        if (code) {
            if ([resPonseObj[@"code"] integerValue] == 0) {

                self.accountInfo = [AccountSettingInfoModel mj_objectWithKeyValues:resPonseObj[@"data"]];

            }else if ([resPonseObj[@"code"] integerValue]==4000){
                [YLUserInfo logout];
            }else{
                [self.view makeToast:resPonseObj[MESSAGE] duration:1.5 position:CSToastPositionCenter];
            }
        }else{
            [self.view makeToast:[[ChangeLanguage bundle] localizedStringForKey:@"noNetworkStatus" value:nil table:@"English"] duration:1.5 position:CSToastPositionCenter];
        }
    }];
}


- (void)setupSubViews
{
    self.canScroll = YES;
    self.tableView.backgroundColor = [UIColor redColor];
}

#pragma mark UITableView
- (NSInteger)numberOfSectionsInTableView:(UITableView *)tableView
{
    return 2;
}

- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section
{
    return 1;

}

- (CGFloat)tableView:(UITableView *)tableView heightForRowAtIndexPath:(NSIndexPath *)indexPath
{
    if (indexPath.section == 0) {
        return 219 * kWindowHOne;
    }
    return kWindowH - kTabbarHeight - SafeAreaTopHeight;
}

- (CGFloat)tableView:(UITableView *)tableView heightForHeaderInSection:(NSInteger)section
{
    if (section == 0) {
        return 0.000001f;
    }
    return 52;
}

- (UIView *)tableView:(UITableView *)tableView viewForHeaderInSection:(NSInteger)section
{
    UIView *view = [[UIView alloc] init];
    view.backgroundColor = mainColor;
    if (section == 1) {
        [view.subviews makeObjectsPerformSelector:@selector(removeFromSuperview)];
        [view addSubview:self.seg];
    }
    return view;
}

- (void)handelSegementControlAction:(UISegmentedControl *)segment
{
    self.contentCell.pageContentView.contentViewCurrentIndex = segment.selectedSegmentIndex;
}

- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath
{
    static NSString *FSBaselineTableViewCellIdentifier = @"FSBaselineTableViewCellIdentifier";
    if (indexPath.section == 1) {
        _contentCell = [tableView dequeueReusableCellWithIdentifier:@"cell"];
        if (!_contentCell) {
            _contentCell = [[FSBottomTableViewCell alloc]initWithStyle:UITableViewCellStyleDefault reuseIdentifier:@"cell"];
            NSArray *titles = @[LocalizationKey(@"Buy"),LocalizationKey(@"Sell")];
            NSMutableArray *contentVCs = [NSMutableArray array];
            for (NSString *title in titles) {
                if ([title isEqualToString:LocalizationKey(@"Buy")]) {
                    BuyCoinsChildViewController *buyVC = [[BuyCoinsChildViewController alloc] init];
                    buyVC.title = title;
                    [contentVCs addObject:buyVC];

                }else{
                    SellCoinsChildViewController *sellVC = [[SellCoinsChildViewController alloc] init];
                    sellVC.title = title;
                    [contentVCs addObject:sellVC];

                }
            }

            _contentCell.viewControllers = contentVCs;
            _contentCell.pageContentView = [[FSPageContentView alloc]initWithFrame:CGRectMake(0, 0, kWindowW, kWindowH - SafeAreaTopHeight) childVCs:contentVCs parentVC:nil delegate:self];
            [_contentCell.contentView addSubview:_contentCell.pageContentView];
        }
        return _contentCell;
    }
    FSBaselineTableViewCell *cell = [tableView dequeueReusableCellWithIdentifier:FSBaselineTableViewCellIdentifier];
    if (!cell) {
        cell = [[FSBaselineTableViewCell alloc]initWithStyle:UITableViewCellStyleDefault reuseIdentifier:FSBaselineTableViewCellIdentifier];
    }

    cell.BusinessBlock = ^{

        if(![YLUserInfo isLogIn]){
            [self showLoginViewController];
            return;
        }

        //实名认证
        if (![self.accountInfo.realVerified isEqualToString:@"1"]) {
            [self.view makeToast:LocalizationKey(@"validateYourID") duration:1.5 position:CSToastPositionCenter];

            return;
        }

        //资金密码
        if (![self.accountInfo.fundsVerified isEqualToString:@"1"]) {
            [self.view makeToast:LocalizationKey(@"bindingPwd") duration:1.5 position:CSToastPositionCenter];

            return;
        }
//        self.memberLevel = 0;
        if(self.memberLevel == 0){
           //未认证商家
            ApplyBusinessViewController *ApplyBusinessVC = [ApplyBusinessViewController new];
            ApplyBusinessVC.hidesBottomBarWhenPushed = YES;
            [self.navigationController pushViewController:ApplyBusinessVC animated:YES];
        }

        if(self.memberLevel == 1){
            //待审核
            BeingauditedBusViewController *BeingauditedBusVC = [BeingauditedBusViewController new];
            BeingauditedBusVC.hidesBottomBarWhenPushed = YES;
            [self.navigationController pushViewController:BeingauditedBusVC animated:YES];
        }

        if(self.memberLevel == 2){

            //审核成功
            SuccessBusViewController *SuccessVC = [SuccessBusViewController new];
            SuccessVC.hidesBottomBarWhenPushed = YES;
            [self.navigationController pushViewController:SuccessVC animated:YES];
        }

        if (self.memberLevel == 3) {
            //审核失败
            FailBusinessViewController *FailBusinessVC = [FailBusinessViewController new];
            FailBusinessVC.Reasonstring = self.reasonstr;
            FailBusinessVC.hidesBottomBarWhenPushed = YES;
            [self.navigationController pushViewController:FailBusinessVC animated:YES];
        }

        if(self.memberLevel == 4){
            //保证金不足

        }

        if(self.memberLevel == 5){
            //退保-待审核
            WaitBusinessViewController *WaitBusinessVC = [WaitBusinessViewController new];
            WaitBusinessVC.hidesBottomBarWhenPushed = YES;
            [self.navigationController pushViewController:WaitBusinessVC animated:YES];
        }

        if(self.memberLevel == 6){
            //退保-审核失败
            RetreatBusinessViewController *retreatBusinessVC = [RetreatBusinessViewController new];
            retreatBusinessVC.Reasonstring = self.reasonstr;
            retreatBusinessVC.hidesBottomBarWhenPushed = YES;
            [self.navigationController pushViewController:retreatBusinessVC animated:YES];
        }

        if(self.memberLevel == 7){
            //退保-审核成功
            SuccessSurrenderViewController *surrenderVC = [SuccessSurrenderViewController new];
            surrenderVC.hidesBottomBarWhenPushed = YES;
            [self.navigationController pushViewController:surrenderVC animated:YES];
        }

    };

    return cell;
}

#pragma mark FSSegmentTitleViewDelegate
- (void)FSContenViewDidEndDecelerating:(FSPageContentView *)contentView startIndex:(NSInteger)startIndex endIndex:(NSInteger)endIndex
{
    self.seg.selectedSegmentIndex = endIndex;
    _tableView.scrollEnabled = YES;//此处其实是监测scrollview滚动，pageView滚动结束主tableview可以滑动，或者通过手势监听或者kvo，这里只是提供一种实现方式
}

- (void)FSSegmentTitleView:(FSSegmentTitleView *)titleView startIndex:(NSInteger)startIndex endIndex:(NSInteger)endIndex
{
    self.contentCell.pageContentView.contentViewCurrentIndex = endIndex;
}

- (void)FSContentViewDidScroll:(FSPageContentView *)contentView startIndex:(NSInteger)startIndex endIndex:(NSInteger)endIndex progress:(CGFloat)progress
{
    _tableView.scrollEnabled = NO;//pageView开始滚动主tableview禁止滑动
}


#pragma mark notify
- (void)changeScrollStatus//改变主视图的状态
{
    self.canScroll = YES;
    self.contentCell.cellCanScroll = NO;
}

#pragma mark UIScrollView
- (void)scrollViewDidScroll:(UIScrollView *)scrollView
{

    CGFloat bottomCellOffset = [_tableView rectForSection:1].origin.y ;
    if (scrollView.contentOffset.y >= bottomCellOffset) {
        scrollView.contentOffset = CGPointMake(0, bottomCellOffset);
        if (self.canScroll) {
            self.canScroll = NO;
            self.contentCell.cellCanScroll = YES;
        }
    }else if (scrollView.contentOffset.y == 0){
        scrollView.contentOffset = CGPointMake(0, 0);
        self.canScroll = NO;
        self.contentCell.cellCanScroll = YES;
    }else{

    }

}

#pragma mark LazyLoad
- (FSBaseTableView *)tableView
{
    if (!_tableView) {
        _tableView = [[FSBaseTableView alloc]initWithFrame:CGRectMake(0, 0, CGRectGetWidth(self.view.bounds), kWindowH - SafeAreaTopHeight -SafeAreaBottomHeight) style:UITableViewStylePlain];
        _tableView.backgroundColor = [UIColor yellowColor];
        _tableView.separatorStyle = UITableViewCellSeparatorStyleNone;
        _tableView.delegate = self;
        _tableView.dataSource = self;
        _tableView.bounces = NO;
        [self.view addSubview:_tableView];
    }
    return _tableView;
}

- (UISegmentedControl *)seg{
    if (!_seg) {
        _seg = [[UISegmentedControl alloc] initWithItems:@[LocalizationKey(@"Buy"), LocalizationKey(@"Sell")]];
        [_seg addTarget:self action:@selector(handelSegementControlAction:) forControlEvents:(UIControlEventValueChanged)];
        _seg.frame = CGRectMake(0, 0, 140, 32);
        _seg.center = CGPointMake(self.view.frame.size.width / 2, 26);
        _seg.selectedSegmentIndex = 0;
        _seg.tintColor=RGBOF(0xF0A70A);
        _seg.selectedSegmentTintColor = baseColor;
        NSDictionary *dic = [NSDictionary dictionaryWithObjectsAndKeys:RGBOF(0xF0A70A),NSForegroundColorAttributeName,[UIFont fontWithName:@"AppleGothic"size:17],NSFontAttributeName ,nil];
        NSDictionary *seldic = [NSDictionary dictionaryWithObjectsAndKeys:[UIColor whiteColor],NSForegroundColorAttributeName,[UIFont fontWithName:@"AppleGothic"size:17],NSFontAttributeName ,nil];
        [_seg setTitleTextAttributes:dic forState:UIControlStateNormal];
        [_seg setTitleTextAttributes:seldic forState:UIControlStateSelected];
    }
    return _seg;
}

@end
