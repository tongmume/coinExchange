//
//  NoticeCenterViewController.m
//  digitalCurrency
//
//  Created by chu on 2019/8/6.
//  Copyright © 2019年 BIZZAN. All rights reserved.
//

#import "NoticeCenterViewController.h"
#import "HelpCenterTableViewCell.h"
#import "PlatformMessageModel.h"
#import "MineNetManager.h"
#import "ZHParallaxHeader.h"
#import "PlatformMessageDetailViewController.h"
#import "HelpCenterDetailsViewController.h"
@interface NoticeCenterViewController ()<UITableViewDelegate, UITableViewDataSource,ZHParallaxHeaderDelegate>

@property (nonatomic, strong) UITableView *tableView;

@property (nonatomic, strong) NSMutableArray *platformMessageArr;

@property (nonatomic, strong) UIView *tableHeaderView;

@property (nonatomic, strong) UIView *naviBar;

@end

@implementation NoticeCenterViewController

- (void)viewWillAppear:(BOOL)animated{
    [super viewWillAppear:animated];
    [self.navigationController setNavigationBarHidden:YES animated:YES];
}

- (void)viewWillDisappear:(BOOL)animated{
    [super viewWillDisappear:animated];
    [self.navigationController setNavigationBarHidden:NO animated:YES];

}

- (void)viewDidAppear:(BOOL)animated{
    [super viewDidAppear:animated];
//    [self.tableView sendSubviewToBack:self.tableHeaderView];
}

- (void)viewDidLoad {
    [super viewDidLoad];
    self.title = [[ChangeLanguage bundle] localizedStringForKey:@"Noticecenter" value:nil table:@"English"];
    self.view.backgroundColor = mainColor;
    [self.view addSubview:self.tableView];
    self.tableView.parallaxHeader.height = 150 * kWindowHOne;
    self.tableView.parallaxHeader.delegate = self;
    self.tableView.parallaxHeader.contentView = self.tableHeaderView;
    self.tableView.estimatedRowHeight = 44.0;
    self.tableView.rowHeight = UITableViewAutomaticDimension;
    if (@available(iOS 11.0, *)) {
        self.tableView.contentInsetAdjustmentBehavior = UIScrollViewContentInsetAdjustmentNever;
    }
    [self customNavigationBar];
    [self getNotice];
//    [self headRefreshWithScrollerView:self.tableView];
}

- (void)refreshFooterAction{

}
- (void)refreshHeaderAction{
    [self getNotice];

}

- (void)parallaxHeaderDidScroll:(ZHParallaxHeader *)parallaxHeader {

    NSLog(@"CGFloat:%lf", parallaxHeader.progress);

//    CGFloat threshold = 0.6;
//    CGFloat progress = parallaxHeader.progress;
//    if (progress > threshold) {
//        self.naviBar.alpha = 0;
//        return;
//    }
//
//    self.naviBar.alpha = 1 - parallaxHeader.progress / threshold;
}

- (NSInteger)numberOfSectionsInTableView:(UITableView *)tableView{
    return 1;
}

- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section{
    return self.platformMessageArr.count;
}

- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath{
    static  NSString *identifier = @"helpCell";
    HelpCenterTableViewCell *cell = [tableView dequeueReusableCellWithIdentifier:identifier];
    cell.backgroundColor = [UIColor clearColor];
    cell.contentView.backgroundColor = [UIColor clearColor];

    if (!cell) {
        cell = [[NSBundle mainBundle] loadNibNamed:@"HelpCenterTableViewCell" owner:nil options:nil][0];
        cell.selectionStyle = UITableViewCellSelectionStyleNone;
    }
    cell.model = self.platformMessageArr[indexPath.row];
    return cell;
}

- (CGFloat)tableView:(UITableView *)tableView heightForRowAtIndexPath:(NSIndexPath *)indexPath{
    return UITableViewAutomaticDimension;
}

- (CGFloat)tableView:(UITableView *)tableView heightForHeaderInSection:(NSInteger)section{
    return 0.00001f;
}

- (UIView *)tableView:(UITableView *)tableView viewForHeaderInSection:(NSInteger)section{
    UIView *view = [[UIView alloc] init];
    return view;
}

-(void)getNotice{
    [MineNetManager getPlatformMessageForCompleteHandleWithPageNo:@"1" withPageSize:@"50" CompleteHandle:^(id resPonseObj, int code) {
        [EasyShowLodingView hidenLoding];
        NSLog(@"resPonseObj -- %@",resPonseObj);

        if (code) {
            if ([resPonseObj[@"code"] integerValue] == 0) {
                [self.platformMessageArr removeAllObjects];
                NSArray *arr = resPonseObj[@"data"][@"content"];
                NSMutableArray *muArr = [NSMutableArray arrayWithCapacity:0];
                for (NSDictionary *dic in arr) {
                      [muArr addObject:dic];
                }
                NSArray *dataArr = [PlatformMessageModel mj_objectArrayWithKeyValuesArray:muArr];
                [self.platformMessageArr addObjectsFromArray:dataArr];
                [self.tableView reloadData];
            }else{
                [self.view makeToast:resPonseObj[MESSAGE] duration:1.5 position:CSToastPositionCenter];
            }
        }else{
            [self.view makeToast:LocalizationKey(@"noNetworkStatus") duration:1.5 position:CSToastPositionCenter];
        }
    }];

}

- (BOOL)hasChinese:(NSString *)str {
    for(int i=0; i< [str length];i++){
        int a = [str characterAtIndex:i];
        if( a > 0x4e00 && a < 0x9fff)
        {
            return YES;
        }
    }
    return NO;
}

- (CGFloat)tableView:(UITableView *)tableView heightForFooterInSection:(NSInteger)section{
    return 0.0001f;
}

- (UIView *)tableView:(UITableView *)tableView viewForFooterInSection:(NSInteger)section{
    UIView *view = [[UIView alloc] init];
    return view;
}

- (void)moreAction:(UIButton *)sender{

}

- (void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath{

    PlatformMessageModel*model=self.platformMessageArr[indexPath.row];
    PlatformMessageDetailViewController *detailVC = [[PlatformMessageDetailViewController alloc] init];
    detailVC.content = model.content;
    detailVC.navtitle = model.title;
    detailVC.ID = model.ID;
    [[AppDelegate sharedAppDelegate] pushViewController:detailVC];
}

- (UITableView *)tableView{
    if (!_tableView) {
        _tableView = [[UITableView alloc] initWithFrame:CGRectMake(0, 0, kWindowW, kWindowH) style:UITableViewStyleGrouped];
        _tableView.delegate = self;
        _tableView.dataSource = self;
        _tableView.backgroundColor = mainColor;
        _tableView.separatorStyle = UITableViewCellSeparatorStyleNone;

    }
    return _tableView;
}

- (void)customNavigationBar{
    UIView *sysView = [UIView new];
    [sysView setFrame:CGRectMake(0, 0, kWindowW, NEW_NavHeight)];
    sysView.backgroundColor = [[UIColor whiteColor] colorWithAlphaComponent:0];
    [self.view insertSubview:sysView aboveSubview:self.tableView];

    UIView *upView = [UIView new];
    [upView setFrame:CGRectMake(0, 0, kWindowW, NEW_StatusBarHeight)];
    upView.backgroundColor = [[UIColor whiteColor] colorWithAlphaComponent:0];
    [sysView addSubview:upView];

    UIView *bottomView = [UIView new];
    [bottomView setFrame:CGRectMake(0, NEW_StatusBarHeight, kWindowW, 44)];
    bottomView.backgroundColor = [[UIColor whiteColor] colorWithAlphaComponent:0];
    [sysView addSubview:bottomView];

    UILabel *label = [[UILabel alloc] init];
    label.text = [[ChangeLanguage bundle] localizedStringForKey:@"notice" value:nil table:@"English"];
    label.textColor = [UIColor whiteColor];
    label.font = [UIFont fontWithName:@"PingFangSC-Medium" size:18];
    [bottomView addSubview:label];

    UIButton *btn = [UIButton buttonWithType:UIButtonTypeCustom];
    [btn setImage:[UIImage imageNamed:@"back3"] forState:UIControlStateNormal];
    [btn addTarget:self action:@selector(popAction) forControlEvents:UIControlEventTouchUpInside];
    [bottomView addSubview:btn];

    [label mas_makeConstraints:^(MASConstraintMaker *make) {
        make.centerX.mas_equalTo(bottomView);
        make.centerY.mas_equalTo(bottomView);
    }];
    [btn mas_makeConstraints:^(MASConstraintMaker *make) {
        make.width.mas_equalTo(40);
        make.left.equalTo(bottomView).with.offset(0);
        make.top.equalTo(bottomView).with.offset(0);
        make.bottom.equalTo(bottomView).with.offset(0);
    }];
}

- (void)popAction{
    [[AppDelegate sharedAppDelegate] popViewController];
}

- (UIView *)tableHeaderView{
    if (!_tableHeaderView) {
        _tableHeaderView = [[UIView alloc] initWithFrame:CGRectMake(0, 0, kWindowW, 150 * kWindowHOne)];
        UIImage *image = [UIImage imageNamed:@"矩形 1 拷贝"];
        _tableHeaderView.layer.contents = (id)image.CGImage;
    }
    return _tableHeaderView;
}

//绘制渐变色颜色的方法
- (CAGradientLayer *)setGradualChangingColor:(UIView *)view fromColor:(UIColor *)fromHexColor toColor:(UIColor *)toHexColor{


    //    CAGradientLayer类对其绘制渐变背景颜色、填充层的形状(包括圆角)
    CAGradientLayer *gradientLayer = [CAGradientLayer layer];
    gradientLayer.frame = view.bounds;

    //  创建渐变色数组，需要转换为CGColor颜色
    gradientLayer.colors = @[(__bridge id)fromHexColor.CGColor,(__bridge id)toHexColor.CGColor];

    //  设置渐变颜色方向，左上点为(0,0), 右下点为(1,1)
    gradientLayer.startPoint = CGPointMake(0, 0);
    gradientLayer.endPoint = CGPointMake(1, 0);

    //  设置颜色变化点，取值范围 0.0~1.0
    gradientLayer.locations = @[@0 ,@1];

    return gradientLayer;
}

- (NSMutableArray *)platformMessageArr{
    if (!_platformMessageArr) {
        _platformMessageArr = [NSMutableArray arrayWithCapacity:0];
    }
    return _platformMessageArr;
}

@end
