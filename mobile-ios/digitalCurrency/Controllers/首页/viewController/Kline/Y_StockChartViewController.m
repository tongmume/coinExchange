//
//  YStockChartViewController.m
//  BTC-Kline
//
//  Created by yate1996 on 16/4/27.
//  Copyright © 2016年 yate1996. All rights reserved.
//

#import "Y_StockChartViewController.h"
#import "Masonry.h"
#import "Y_StockChartView.h"
#import "Y_StockChartView.h"
#import "Y_KLineGroupModel.h"
#import "UIColor+Y_StockChart.h"
#import "AppDelegate.h"
#import "HomeNetManager.h"
#import "symbolModel.h"
#define IS_IPHONE (UI_USER_INTERFACE_IDIOM() == UIUserInterfaceIdiomPhone)

@interface Y_StockChartViewController ()<Y_StockChartViewDataSource,SocketDelegate>
{
    UIButton*_KlineCurrentBtn;//选中的K线种类
    UIButton*_mainCurrentBtn;//选中的主图
    UIButton*_subCurrentBtn;//选中的副图
}
@property (nonatomic, strong) Y_StockChartView *stockChartView;

@property (nonatomic, strong) Y_KLineGroupModel *groupModel;

@property (nonatomic, copy) NSMutableDictionary <NSString*, Y_KLineGroupModel*> *modelsDict;


@property (nonatomic, assign) NSInteger currentIndex;

@property (weak, nonatomic) IBOutlet UIView *backView;
@property (nonatomic, copy) NSString *type;
@property (weak, nonatomic) IBOutlet UILabel *symbolLabel;
@property (weak, nonatomic) IBOutlet UILabel *PriceLabel;
@property (weak, nonatomic) IBOutlet UILabel *CNYLabel;
@property (weak, nonatomic) IBOutlet UILabel *highLabel;
@property (weak, nonatomic) IBOutlet UILabel *lowLabel;
@property (weak, nonatomic) IBOutlet UILabel *amountLabel;
@property (weak, nonatomic) IBOutlet UILabel *changeLabel;
@property(nonatomic,strong)UIView*klineView;

@end

@implementation Y_StockChartViewController



- (void)viewDidLoad {
    [super viewDidLoad];
    [self.KlineTimeBtn setTitle:LocalizationKey(@"line") forState:UIControlStateNormal];
    [self.btn1 setTitle:LocalizationKey(@"min") forState:UIControlStateNormal];
    [self.btn2 setTitle:LocalizationKey(@"fivemin") forState:UIControlStateNormal];
    [self.btn3 setTitle:LocalizationKey(@"hours") forState:UIControlStateNormal];
    [self.btn4 setTitle:LocalizationKey(@"days") forState:UIControlStateNormal];
    [self.btn5 setTitle:LocalizationKey(@"weeks") forState:UIControlStateNormal];
    [self.btn6 setTitle:LocalizationKey(@"fivethmin") forState:UIControlStateNormal];
    [self.btn7 setTitle:LocalizationKey(@"thirtymin") forState:UIControlStateNormal];
    [self.btn8 setTitle:LocalizationKey(@"mainKline") forState:UIControlStateNormal];
    [self.btn9 setTitle:LocalizationKey(@"hideKline") forState:UIControlStateNormal];
    [self.btn10 setTitle:LocalizationKey(@"hideKline") forState:UIControlStateNormal];
    [self.btn11 setTitle:LocalizationKey(@"subKline") forState:UIControlStateNormal];
    [self.monthBtn setTitle:LocalizationKey(@"monthkline") forState:UIControlStateNormal];
    [self.moreBtn setTitle:LocalizationKey(@"days") forState:UIControlStateNormal];
    self.backView.backgroundColor=[UIColor backgroundColor];
    self.currentIndex = -1;
    self.stockChartView.backgroundColor = [UIColor backgroundColor];
    self.mainView.backgroundColor=[UIColor backgroundColor];
    [self.view bringSubviewToFront:_closeBtn];
    self.view.backgroundColor=[UIColor backgroundColor];
    self.symbolLabel.text=self.symbol;
    [self getAllCoinData:self.symbol];
    self.moreView.backgroundColor=[[UIColor whiteColor] colorWithAlphaComponent:1];
    [self.view bringSubviewToFront:self.moreView];
    [self.KlineTimeBtn setTitleColor:[UIColor ma30Color] forState:UIControlStateNormal];
    _KlineCurrentBtn=self.KlineTimeBtn;
    self.klineView=[[UIView alloc]initWithFrame:CGRectMake(0, kWindowW-1, self.KlineTimeBtn.mj_w/2, 1)];
    self.klineView.centerX=self.KlineTimeBtn.centerX;
    self.klineView.backgroundColor=[UIColor ma30Color];
    self.moreBtn.backgroundColor=[UIColor backgroundColor];
    self.monthBtn.backgroundColor=[UIColor backgroundColor];
    [self.view addSubview:self.klineView];
    [self.maBtn setTitleColor:[UIColor ma30Color] forState:UIControlStateNormal];
    _mainCurrentBtn=self.maBtn;
    //[self.macdBtn setTitleColor:[UIColor ma30Color] forState:UIControlStateNormal];
     // _subCurrentBtn=self.macdBtn;
      [[NSNotificationCenter defaultCenter] addObserver:self selector:@selector(hideindex)name:@"HIDEINDEX" object:nil];
}
-(void)hideindex{
    self.moreView.hidden=YES;
}
- (void)viewWillAppear:(BOOL)animated
{
    [super viewWillAppear:animated];
    [UIApplication sharedApplication].statusBarHidden = YES;
 
}
-(void)viewDidAppear:(BOOL)animated{
    [super viewDidAppear:YES];
    [[SocketManager share] sendMsgWithLength:SOCKETREQUEST_LENGTH withsequenceId:0 withcmd:SUBSCRIBE_SYMBOL_THUMB withVersion:COMMANDS_VERSION withRequestId: 0 withbody:nil];
    NSDictionary*dic=[NSDictionary dictionaryWithObjectsAndKeys:self.symbol,@"symbol",nil];
    [[SocketManager share] sendMsgWithLength:SOCKETREQUEST_LENGTH withsequenceId:0 withcmd:SUBSCRIBE_EXCHANGE_TRADE withVersion:COMMANDS_VERSION withRequestId: 0 withbody:dic];
    [SocketManager share].delegate=self;
}
- (NSMutableDictionary<NSString *,Y_KLineGroupModel *> *)modelsDict
{
    if (!_modelsDict) {
        _modelsDict = @{}.mutableCopy;
    }
    return _modelsDict;
}


-(id) stockDatasWithIndex:(NSInteger)index
{
    NSString *type;
    switch (index) {
        case 0:
        {
            type = @"1min";
        }
            break;
        case 1:
        {
            type = @"1min";//分时
        }
            break;
        case 2:
        {
            type = @"1min";//1分
        }
            break;
        case 3:
        {
            type = @"5min";//5分钟
        }
            break;
        case 4:
        {
            type = @"1hour";//1小时
        }
            break;
        case 5:
        {
            type = @"1day";//1天
        }
            break;
        case 6:
        {
            type = @"15min";//15分钟
            
        }
            break;
        case 7:
        {
            type = @"30min";//30分钟
        }
            break;
        case 8:
        {
            type = @"1week";//1周
        }
            break;
        case 9:
        {
            type = @"1month";//1月
        }
            break;
        default:
            break;
    }
    self.currentIndex = index;
    self.type = type;
    [self reloadData:type];
    return nil;
}
#pragma mark-请求K线数据
- (void)reloadData:(NSString*)type
{
    if ([type isEqualToString:@"1min"]) {
        [self getDatawithSymbol:self.symbol withFromtime:[self getStringWithDate:[NSDate dateWithTimeIntervalSinceNow: -(6*1*60*60*2)]] withResolution:@"1"];
    }
    else if ([type isEqualToString:@"5min"])
    {
        [self getDatawithSymbol:self.symbol withFromtime:[self getStringWithDate:[NSDate dateWithTimeIntervalSinceNow: -(6*1*60*60*2*5)]] withResolution:@"5"];
    }
    else if ([type isEqualToString:@"30min"])
    {
        [self getDatawithSymbol:self.symbol withFromtime:[self getStringWithDate:[NSDate dateWithTimeIntervalSinceNow: -(6*1*60*60*2*30)]] withResolution:@"30"];
    }
    else if ([type isEqualToString:@"1hour"])
    {
        [self getDatawithSymbol:self.symbol withFromtime:[self getStringWithDate:[NSDate dateWithTimeIntervalSinceNow: -(6*1*60*60*2*60)]] withResolution:@"1H"];
    }
    else if ([type isEqualToString:@"1day"])
    {
        [self getDatawithSymbol:self.symbol withFromtime:[self getStringWithDate:[NSDate dateWithTimeIntervalSinceNow: -(6*1*60*60*2*24*60)]] withResolution:@"1D"];
    }
    else if ([type isEqualToString:@"1week"])
    {
        [self getDatawithSymbol:self.symbol withFromtime:[self getStringWithDate:[NSDate dateWithTimeIntervalSinceNow: -(6*1*60*60*2*24*60*7)]] withResolution:@"1W"];
    }
    else if ([type isEqualToString:@"15min"])
    {
        [self getDatawithSymbol:self.symbol withFromtime:[self getStringWithDate:[NSDate dateWithTimeIntervalSinceNow: -(6*1*60*60*2*15)]] withResolution:@"15"];
    }
    else if ([type isEqualToString:@"1month"])
    {
       [self getDatawithSymbol:self.symbol withFromtime:[self getStringWithDate:[NSDate dateWithTimeIntervalSinceNow: -(6*1*60*60*2*24*60)]] withResolution:@"1M"];
    }else{
        
    }
}
#pragma --K线数据
-(void)getDatawithSymbol:(NSString*)symbol withFromtime:(NSString*)time withResolution:(NSString*)resolution{
    dispatch_after(dispatch_time(DISPATCH_TIME_NOW, (int64_t)(0.01/*延迟执行时间*/ * NSEC_PER_SEC)), dispatch_get_main_queue(), ^{
        [EasyShowLodingView showLodingText:LocalizationKey(@"loading")];
    });
    [HomeNetManager historyKlineWithsymbol:symbol withFrom:time withTo:[self getStringWithDate:[NSDate date]] withResolution:resolution CompleteHandle:^(id resPonseObj, int code) {
        [EasyShowLodingView hidenLoding];
        if (code) {
            /**时间--开盘价--最高价--最低价--收盘价--成交量**/
            if ([resPonseObj isKindOfClass:[NSArray class]]) {
                NSArray*array=(NSArray*)resPonseObj;
                if (array.count<=2) {
                    [self.stockChartView reloadData:[NSArray array]];
                    return ;
                }
                Y_KLineGroupModel *groupModel = [Y_KLineGroupModel objectWithArray:resPonseObj];
                self.groupModel = groupModel;
                [self.stockChartView reloadData:self.groupModel.models];
                
            }else{
                [self.view makeToast:resPonseObj[MESSAGE] duration:1.5 position:CSToastPositionCenter];
            }
        }else{
            [self.view makeToast:LocalizationKey(@"noNetworkStatus") duration:1.5 position:CSToastPositionCenter];
        }
    }];
}
-(NSString*)getStringWithDate:(NSDate*)date{
    NSTimeInterval nowtime = [date timeIntervalSince1970]*1000;
    long long theTime = [[NSNumber numberWithDouble:nowtime] longLongValue];
    return  [NSString stringWithFormat:@"%llu",theTime];//当前时间的毫秒数
}
- (Y_StockChartView *)stockChartView
{
    if(!_stockChartView) {
        _stockChartView = [Y_StockChartView new];
        _stockChartView.itemModels = @[
                                       [Y_StockChartViewItemModel itemModelWithTitle:@"指标" type:Y_StockChartcenterViewTypeOther],
                                       [Y_StockChartViewItemModel itemModelWithTitle:@"分时" type:Y_StockChartcenterViewTypeTimeLine],
                                       [Y_StockChartViewItemModel itemModelWithTitle:@"1分" type:Y_StockChartcenterViewTypeKline],
                                       [Y_StockChartViewItemModel itemModelWithTitle:@"5分" type:Y_StockChartcenterViewTypeKline],
                                       [Y_StockChartViewItemModel itemModelWithTitle:@"1小时" type:Y_StockChartcenterViewTypeKline],
                                       [Y_StockChartViewItemModel itemModelWithTitle:@"1天" type:Y_StockChartcenterViewTypeKline],
                                       [Y_StockChartViewItemModel itemModelWithTitle:@"15分" type:Y_StockChartcenterViewTypeKline],
                                       [Y_StockChartViewItemModel itemModelWithTitle:@"30分钟" type:Y_StockChartcenterViewTypeKline],
                                       [Y_StockChartViewItemModel itemModelWithTitle:@"1周" type:Y_StockChartcenterViewTypeKline],
                                       [Y_StockChartViewItemModel itemModelWithTitle:@"1月" type:Y_StockChartcenterViewTypeKline],
                                       ];
        _stockChartView.backgroundColor = [UIColor orangeColor];
        //_stockChartView.DefalutselectedIndex=_DefalutselectedIndex;
        _stockChartView.DefalutselectedIndex=1;
        _stockChartView.dataSource = self;
        [self.view addSubview:_stockChartView];
        [_stockChartView mas_makeConstraints:^(MASConstraintMaker *make) {
            if (IS_IPHONE_X) {
                    make.edges.equalTo(self.view).insets(UIEdgeInsetsMake(30, 24, 31.5, 50));
            } else {
                    make.edges.equalTo(self.view).insets(UIEdgeInsetsMake(30, 0, 31.5, 50));;
            }
        }];
        /*
        //双击手势退出
        UITapGestureRecognizer *tap = [[UITapGestureRecognizer alloc]initWithTarget:self action:@selector(dismiss)];
        tap.numberOfTapsRequired = 2;
        [self.view addGestureRecognizer:tap];
         */
    }
    return _stockChartView;
}
- (IBAction)close:(id)sender
{
    [self dismiss];
}
- (void)dismiss
{
    AppDelegate *appdelegate = (AppDelegate*)[UIApplication sharedApplication].delegate;
    appdelegate.isEable = NO;
    [self dismissViewControllerAnimated:YES completion:nil];
}
- (UIInterfaceOrientationMask)supportedInterfaceOrientations
{
    return UIInterfaceOrientationMaskLandscape;
}
- (BOOL)shouldAutorotate
{
    return NO;
}
//获取所有交易币种缩略行情
-(void)getAllCoinData:(NSString*)symbol {
    [EasyShowLodingView showLodingText:LocalizationKey(@"loading")];
    [HomeNetManager getsymbolthumbCompleteHandle:^(id resPonseObj, int code) {
        [EasyShowLodingView hidenLoding];
        if (code) {
            if ([resPonseObj isKindOfClass:[NSArray class]]) {
                NSArray*symbolArray=(NSArray*)resPonseObj;
                for (int i=0; i<symbolArray.count; i++) {
                    symbolModel*model = [symbolModel mj_objectWithKeyValues:symbolArray[i]];
                    if ([model.symbol isEqualToString:symbol]) {
                        self.PriceLabel.text=[ToolUtil stringFromNumber:[model.close doubleValue] withlimit:self.baseCoinScale];
                        
                        NSDecimalNumber *close = [NSDecimalNumber decimalNumberWithDecimal:[model.close decimalValue]];
                        NSDecimalNumber *baseUsdRate = [NSDecimalNumber decimalNumberWithDecimal:[model.baseUsdRate decimalValue]];
                        
                        self.CNYLabel.text=[NSString stringWithFormat:@"≈%.2f CNY",[[[close decimalNumberByMultiplyingBy:baseUsdRate] decimalNumberByMultiplyingBy:((AppDelegate*)[UIApplication sharedApplication].delegate).CNYRate] doubleValue]];
                        self.highLabel.text=[NSString stringWithFormat:@"%@:%@",LocalizationKey(@"highest"),[ToolUtil stringFromNumber:model.high withlimit:self.baseCoinScale]];
                        self.lowLabel.text=[NSString stringWithFormat:@"%@:%@",LocalizationKey(@"minimumest"),[ToolUtil stringFromNumber:model.low withlimit:self.baseCoinScale]];
                        self.amountLabel.text=[NSString stringWithFormat:@"%@:%@",LocalizationKey(@"24H"),[ToolUtil stringFromNumber:model.volume withlimit:self.coinScale]];
                        if (model.change <0) {
                            self.changeLabel.textColor=RedColor;
                            self.PriceLabel.textColor=RedColor;
                            self.changeLabel.text= [NSString stringWithFormat:@"%@%.2f%%",LocalizationKey(@"increase"), model.chg*100];
                        }else{
                            self.changeLabel.textColor=GreenColor;
                            self.PriceLabel.textColor=GreenColor;
                            self.changeLabel.text= [NSString stringWithFormat:@"%@%.2f%%", LocalizationKey(@"increase"),model.chg*100];
                        }
                        
                    }
                }
            }else{
                [self.view makeToast:resPonseObj[MESSAGE] duration:1.5 position:CSToastPositionCenter];
            }
        }else{
            [self.view makeToast:LocalizationKey(@"noNetworkStatus") duration:1.5 position:CSToastPositionCenter];
        }
    }];
}
#pragma mark - SocketDelegate Delegate
- (void)delegateSocket:(GCDAsyncSocket *)sock didReadData:(NSData *)data withTag:(long)tag{

    NSData *endData = [data subdataWithRange:NSMakeRange(SOCKETRESPONSE_LENGTH, data.length -SOCKETRESPONSE_LENGTH)];
    NSString *endStr= [[NSString alloc] initWithData:endData encoding:NSUTF8StringEncoding];
    NSData *cmdData = [data subdataWithRange:NSMakeRange(12,2)];
    uint16_t cmd=[SocketUtils uint16FromBytes:cmdData];
    //缩略行情
    if (cmd==PUSH_SYMBOL_THUMB) {
        NSDictionary*dic=[SocketUtils dictionaryWithJsonString:endStr];
        symbolModel*model = [symbolModel mj_objectWithKeyValues:dic];
        if ([model.symbol isEqualToString:self.symbol]) {
            self.PriceLabel.text=[ToolUtil stringFromNumber:[model.close doubleValue] withlimit:self.baseCoinScale];

            NSDecimalNumber *close = [NSDecimalNumber decimalNumberWithDecimal:[model.close decimalValue]];
            NSDecimalNumber *baseUsdRate = [NSDecimalNumber decimalNumberWithDecimal:[model.baseUsdRate decimalValue]];
            self.CNYLabel.text=[NSString stringWithFormat:@"≈%.2f CNY",[[[close decimalNumberByMultiplyingBy:baseUsdRate] decimalNumberByMultiplyingBy:((AppDelegate*)[UIApplication sharedApplication].delegate).CNYRate] doubleValue]];
            self.highLabel.text=[NSString stringWithFormat:@"%@:%@",LocalizationKey(@"highest"),[ToolUtil stringFromNumber:model.high withlimit:self.baseCoinScale]];
            self.lowLabel.text=[NSString stringWithFormat:@"%@:%@",LocalizationKey(@"minimumest"),[ToolUtil stringFromNumber:model.low withlimit:self.baseCoinScale]];
            self.amountLabel.text=[NSString stringWithFormat:@"%@:%@",LocalizationKey(@"24H"),[ToolUtil stringFromNumber:model.volume withlimit:self.coinScale]];
            if (model.change <0) {
                self.changeLabel.textColor=RedColor;
                self.PriceLabel.textColor=RedColor;
                self.changeLabel.text= [NSString stringWithFormat:@"%@%.2f%%",LocalizationKey(@"increase"), model.chg*100];
            }else{
                self.changeLabel.textColor=GreenColor;
                self.PriceLabel.textColor=GreenColor;
                self.changeLabel.text= [NSString stringWithFormat:@"%@%.2f%%", LocalizationKey(@"increase"),model.chg*100];
            }
        }
    }else if (cmd==UNSUBSCRIBE_SYMBOL_THUMB){
        NSLog(@"取消订阅K线缩略行情消息");
        
    }else if (cmd==PUSH_EXCHANGE_KLINE){
        NSLog(@"订阅K线消息");
        
    }
    else if (cmd==UNSUBSCRIBE_EXCHANGE_TRADE){
        NSLog(@"取消K线消息");
        
    }else{
        
    }
//    NSLog(@"K线消息-%@--%d",endStr,cmd);
}

- (void)viewWillDisappear:(BOOL)animated
{
    [super viewWillDisappear:animated];
    [UIApplication sharedApplication].statusBarHidden = NO;
    [[SocketManager share] sendMsgWithLength:SOCKETREQUEST_LENGTH withsequenceId:0 withcmd:UNSUBSCRIBE_SYMBOL_THUMB withVersion:COMMANDS_VERSION withRequestId: 0 withbody:nil];
    NSDictionary*dic=[NSDictionary dictionaryWithObjectsAndKeys:self.symbol,@"symbol",nil];
    [[SocketManager share] sendMsgWithLength:SOCKETREQUEST_LENGTH withsequenceId:0 withcmd:UNSUBSCRIBE_EXCHANGE_TRADE withVersion:COMMANDS_VERSION withRequestId: 0 withbody:dic];
}

//k线菜单
- (IBAction)KbtnClick:(UIButton *)sender {
    
    [_KlineCurrentBtn setTitleColor:[UIColor mainTextColor] forState:UIControlStateNormal];
    [sender setTitleColor:[UIColor ma30Color] forState:UIControlStateNormal];
   _KlineCurrentBtn=sender;
    [UIView animateWithDuration:0.2 animations:^{
        self.klineView.centerX=sender.centerX;
    }];
    switch (sender.tag) {
        case 1:
             [self.moreBtn setTitleColor:[UIColor mainTextColor] forState:UIControlStateNormal];
             [self.moreBtn setTitle:LocalizationKey(@"morekline") forState:UIControlStateNormal];
            break;
        case 2:
            [self.moreBtn setTitleColor:[UIColor mainTextColor] forState:UIControlStateNormal];
            [self.moreBtn setTitle:LocalizationKey(@"morekline") forState:UIControlStateNormal];
            break;
        case 3:
            [self.moreBtn setTitleColor:[UIColor mainTextColor] forState:UIControlStateNormal];
            [self.moreBtn setTitle:LocalizationKey(@"morekline") forState:UIControlStateNormal];
            break;
        case 4:
            [self.moreBtn setTitleColor:[UIColor mainTextColor] forState:UIControlStateNormal];
            [self.moreBtn setTitle:LocalizationKey(@"morekline") forState:UIControlStateNormal];
            break;
        case 5:
            [self.moreBtn setTitleColor:[UIColor mainTextColor] forState:UIControlStateNormal];
            [self.moreBtn setTitle:LocalizationKey(@"morekline") forState:UIControlStateNormal];
            break;
        case 6:
        {
            [self.moreBtn setTitle:LocalizationKey(@"fivethmin") forState:UIControlStateNormal];
            [self.moreBtn setTitleColor:[UIColor ma30Color] forState:UIControlStateNormal];
            [UIView animateWithDuration:0.2 animations:^{
                self.klineView.centerX=self.moreBtn.centerX;
            }];
        }
            break;
        case 7:
        {
            [self.moreBtn setTitle:LocalizationKey(@"thirtymin") forState:UIControlStateNormal];
            [self.moreBtn setTitleColor:[UIColor ma30Color] forState:UIControlStateNormal];
            [UIView animateWithDuration:0.2 animations:^{
                self.klineView.centerX=self.moreBtn.centerX;
            }];
        }
            break;
        case 8:
            [self.moreBtn setTitleColor:[UIColor mainTextColor] forState:UIControlStateNormal];
            [self.moreBtn setTitle:LocalizationKey(@"morekline") forState:UIControlStateNormal];
            break;
        case 9:
            [self.moreBtn setTitleColor:[UIColor mainTextColor] forState:UIControlStateNormal];
            [self.moreBtn setTitle:LocalizationKey(@"morekline") forState:UIControlStateNormal];
            break;
            
        default:
            break;
            
    }
    self.moreView.hidden=YES;
    NSDictionary *dict =[[NSDictionary alloc]initWithObjectsAndKeys:[NSNumber numberWithInteger:sender.tag],@"buttonTag",nil];
    NSNotification *notification =[NSNotification notificationWithName:@"tongzhi" object:nil userInfo:dict];
    //通过通知中心发送通知
    [[NSNotificationCenter defaultCenter] postNotification:notification];
}
//更多
- (IBAction)moreBtn:(UIButton *)sender {
    self.moreView.hidden=!self.moreView.hidden;
}
//主图
- (IBAction)indexBtn:(UIButton *)sender {
    if (sender.tag==106) {//隐藏
        [_mainCurrentBtn setTitleColor:[UIColor mainTextColor] forState:UIControlStateNormal];
        [sender setTitleColor:[UIColor mainTextColor] forState:UIControlStateNormal];
    }else{
        [_mainCurrentBtn setTitleColor:[UIColor mainTextColor] forState:UIControlStateNormal];
        [sender setTitleColor:[UIColor ma30Color] forState:UIControlStateNormal];
        _mainCurrentBtn=sender;
    }
    NSDictionary *dict =[[NSDictionary alloc]initWithObjectsAndKeys:[NSNumber numberWithInteger:sender.tag],@"buttonTag",nil];
    NSNotification *notification =[NSNotification notificationWithName:@"tongzhi" object:nil userInfo:dict];
    //通过通知中心发送通知
    [[NSNotificationCenter defaultCenter] postNotification:notification];
}
//副图
- (IBAction)Subgraph:(UIButton *)sender {
    if (sender.tag==102) {//隐藏
        [_subCurrentBtn setTitleColor:[UIColor mainTextColor] forState:UIControlStateNormal];
        [sender setTitleColor:[UIColor mainTextColor] forState:UIControlStateNormal];
    }else{
        [_subCurrentBtn setTitleColor:[UIColor mainTextColor] forState:UIControlStateNormal];
        [sender setTitleColor:[UIColor ma30Color] forState:UIControlStateNormal];
        _subCurrentBtn=sender;
    }
    NSDictionary *dict =[[NSDictionary alloc]initWithObjectsAndKeys:[NSNumber numberWithInteger:sender.tag],@"buttonTag",nil];
    NSNotification *notification =[NSNotification notificationWithName:@"tongzhi" object:nil userInfo:dict];
    //通过通知中心发送通知
    [[NSNotificationCenter defaultCenter] postNotification:notification];
}

- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of  nmthat can be recreated.
}
-(void)dealloc{
    
    NSLog(@"页面释放了");
}

@end
