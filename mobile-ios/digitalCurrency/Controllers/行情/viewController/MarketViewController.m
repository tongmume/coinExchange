//
//  MarketViewController.m
//  digitalCurrency
//
//  Created by sunliang on 2019/1/26.
//  Copyright © 2019年 BIZZAN. All rights reserved.
//

#import "MarketViewController.h"
#import "configViewController.h"
#import "SLSegmentView.h"
#import "MarketNetManager.h"
#import "AppDelegate.h"
@interface MarketViewController ()<UIScrollViewDelegate,SocketDelegate>

@property (nonatomic, strong)SLSegmentView *segment;
@property (nonatomic, strong)UIScrollView *ctrlContanier;/**<控制器容器*/
@property(nonatomic,strong) NSArray*segmentTitleArray;
@end

@implementation MarketViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    self.view.backgroundColor = [UIColor whiteColor];
    self.segmentTitleArray= @[@"USDT",@"BTC",@"ETH",LocalizationKey(@"collect")];
    [self setUpViews];

    // Do any additional setup after loading the view from its nib.
}
-(void)viewWillAppear:(BOOL)animated{
    [super viewWillAppear:YES];
    self.navigationItem.title = LocalizationKey(@"tabbar2");
    [SocketManager share].delegate=self;
    //language
    [[NSNotificationCenter defaultCenter] addObserver:self selector:@selector(languageSetting)name:LanguageChange object:nil];
//    self.navigationController.navigationBar.translucent = NO;
}
//MARK:--国际化通知处理事件
- (void)languageSetting{
    [self.segment modifyButtonTitle:LocalizationKey(@"collect")];
}
- (void)viewDidAppear:(BOOL)animated
{
    [super viewDidAppear:animated];
    if (![YLUserInfo isLogIn]) {
        CGFloat width = self.ctrlContanier.frame.size.width;
        CGFloat offsetX = self.ctrlContanier.contentOffset.x;
        //当前位置需要显示的控制器的索引
        NSInteger index = offsetX / width;
        if (index==3) {
            [self.segment movieToCurrentSelectedSegment:0];
            self.ctrlContanier.mj_offsetX=0;
            [self scrollViewDidEndScrollingAnimation:self.ctrlContanier];
            return;
        }
    }
    [self scrollViewDidEndScrollingAnimation:self.ctrlContanier];
    //只有这个方法中拿到了ctrlContanier的frame后才能去设置当前的控制器
    [self getUSDTToCNYRate];
    [[SocketManager share] sendMsgWithLength:SOCKETREQUEST_LENGTH withsequenceId:0 withcmd:SUBSCRIBE_SYMBOL_THUMB withVersion:COMMANDS_VERSION withRequestId: 0 withbody:nil];
}
#pragma mark-获取USDT对CNY汇率
-(void)getUSDTToCNYRate{
    [MarketNetManager getusdTocnyRateCompleteHandle:^(id resPonseObj, int code) {
        if (code) {
            if ([resPonseObj[@"code"] integerValue] == 0) {
               ((AppDelegate*)[UIApplication sharedApplication].delegate).CNYRate=[NSDecimalNumber decimalNumberWithString:[resPonseObj[@"data"] stringValue]];
            }else{
                [self.view makeToast:resPonseObj[MESSAGE] duration:1.5 position:CSToastPositionCenter];
            }
        }else{
            [self.view makeToast:LocalizationKey(@"networkAbnormal") duration:1.5 position:CSToastPositionCenter];
        }
    }];
}

-(void)setUpViews
{
    [self.view addSubview:self.segment];
    [self.view addSubview:self.ctrlContanier];
    [self createChildCtrls];
}
- (void)createChildCtrls
{
    //添加子控制器
    for (int i=0; i<self.segmentTitleArray.count; i++) {
        configViewController *childCtrl = [[configViewController alloc] initWithChildViewType:i];
        [self addChildViewController:childCtrl];
    }
     self.ctrlContanier.contentSize = CGSizeMake(self.childViewControllers.count*kWindowW, 0);
}
#pragma mark - UIScrollViewDelegate
- (void)scrollViewDidEndScrollingAnimation:(UIScrollView *)scrollView
{
    CGFloat width = scrollView.frame.size.width;
    CGFloat height = scrollView.frame.size.height;
    CGFloat offsetX = scrollView.contentOffset.x;
    // 当前位置需要显示的控制器的索引
    NSInteger index = offsetX / width;
    //设置optionalNewsSegment滚动到控制器对应的位置
    [self.segment movieToCurrentSelectedSegment:index];
    if (index<0)
    return;
    // 取出需要显示的控制器
    configViewController *willShowVC = self.childViewControllers[index];
    // 如果当前位置已经显示过了，就直接返回
    if ([willShowVC isViewLoaded]){
        [willShowVC reloadData];//刷新数据
        return;
    }
    // 添加控制器的view到scrollView中;
    willShowVC.view.frame = CGRectMake(offsetX, 0, width, height);
    [scrollView addSubview:willShowVC.view];

}

- (void)scrollViewDidEndDecelerating:(UIScrollView *)scrollView
{
    [self scrollViewDidEndScrollingAnimation:scrollView];
}


#pragma mark - 懒加载
- (SLSegmentView *)segment
{
    if (!_segment) {
        _segment = [[SLSegmentView alloc] initWithSegmentWithTitleArray:self.segmentTitleArray];
        _segment.frame=CGRectMake(0,0, kWindowW, 40);
        __weak typeof(self)weakSelf = self;
        _segment.clickSegmentButton = ^(NSInteger index) {
            //点击segment回调,让底部的内容scrollView滚动到对应位置
            CGPoint offset = weakSelf.ctrlContanier.contentOffset;
            offset.x = index * weakSelf.ctrlContanier.frame.size.width;
            [weakSelf.ctrlContanier setContentOffset:offset animated:YES];
        };
    }
    return _segment;
}
-(UIScrollView *)ctrlContanier
{
    if (!_ctrlContanier) {
        _ctrlContanier = [[UIScrollView alloc] init];
        _ctrlContanier.backgroundColor = [UIColor blackColor];
        _ctrlContanier.frame=CGRectMake(0, CGRectGetMaxY(_segment.frame), kWindowW, kWindowH - NEW_NavHeight -SafeAreaBottomHeight - 40);
        _ctrlContanier.scrollsToTop = NO;
        _ctrlContanier.showsVerticalScrollIndicator = NO;
        _ctrlContanier.showsHorizontalScrollIndicator = NO;
        _ctrlContanier.pagingEnabled = YES;
        _ctrlContanier.delegate = self;
    }

    return _ctrlContanier;
}
#pragma mark - SocketDelegate Delegate
- (void)delegateSocket:(GCDAsyncSocket *)sock didReadData:(NSData *)data withTag:(long)tag{
    NSData *endData = [data subdataWithRange:NSMakeRange(SOCKETRESPONSE_LENGTH, data.length -SOCKETRESPONSE_LENGTH)];
    NSString *endStr= [[NSString alloc] initWithData:endData encoding:NSUTF8StringEncoding];
    NSData *cmdData = [data subdataWithRange:NSMakeRange(12,2)];
    uint16_t cmd=[SocketUtils uint16FromBytes:cmdData];
    //缩略行情
    if (cmd==PUSH_SYMBOL_THUMB) {
        if (endStr) {
           NSDictionary *dic = [NSDictionary dictionaryWithObject:endStr forKey:@"param"];
            [[NSNotificationCenter defaultCenter] postNotificationName:SUBSCRIBE_SYMBOL object:nil userInfo:dic];
        }
    }
//    NSLog(@"行情消息-%@--%d",endStr,cmd);
}
-(void)viewWillDisappear:(BOOL)animated{
    [super viewWillDisappear:YES];
    [[SocketManager share] sendMsgWithLength:SOCKETREQUEST_LENGTH withsequenceId:0 withcmd:UNSUBSCRIBE_SYMBOL_THUMB withVersion:COMMANDS_VERSION withRequestId: 0 withbody:nil];
//    self.navigationController.navigationBar.translucent = YES;
}
-(void)dealloc{
    [[NSNotificationCenter defaultCenter] removeObserver:self];
}
- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

/*
#pragma mark - Navigation

// In a storyboard-based application, you will often want to do a little preparation before navigation
- (void)prepareForSegue:(UIStoryboardSegue *)segue sender:(id)sender {
    // Get the new view controller using [segue destinationViewController].
    // Pass the selected object to the new view controller.
}
*/

@end
