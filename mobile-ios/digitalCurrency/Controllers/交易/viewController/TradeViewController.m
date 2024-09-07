//
//  TradeViewController.m
//  digitalCurrency
//
//  Created by sunliang on 2019/1/26.
//  Copyright © 2019年 BIZZAN. All rights reserved.
//

#import "TradeViewController.h"
#import "FrenchCurrencyViewController.h"
#import "commissionViewController.h"
#import "LeftMenuViewController.h"
#import "marketManager.h"
#import "KchatViewController.h"
#import "symbolModel.h"
#import "tradeCell.h"
#import "EntrustCell.h"
#import "UIView+LLXAlertPop.h"
#import "HomeNetManager.h"
#import "TradeNetManager.h"
#import "commissionModel.h"
#import "plateModel.h"
#import "MarketNetManager.h"
#import "AppDelegate.h"
#import "StepSlider.h"
#import "MyEntrustInfoModel.h"
#import "MyEntrustTableViewCell.h"
#import "MyEntrustDetailViewController.h"
#import "HistoryTransactionEntrustViewController.h"
#import "EntrustmentRecordViewController.h"//委托记录
#import "AccountSettingInfoModel.h"
#import "MineNetManager.h"
#import <objc/runtime.h>
#import "RDVTabBarController.h"

#import "CustomSegmentView.h"
#define Handicap 5  //买入卖出显示数量
@interface TradeViewController ()<UITextFieldDelegate,SocketDelegate,StepSliderDelegate,UIScrollViewDelegate>
{
    BOOL _IsMarketprice;//按市价交易;
    BOOL _IsSell;//卖出;
    double _usdRate;
    NSString* _baseCoinName;
    NSString* _ObjectCoinName;
    int _baseCoinScale;//价格精确度(小数点后几位)
    int _coinScale;//数量精确度
    BOOL _isCollect;//是否收藏了
    
    double allDepthbuyAmount;
    double allDepthsellAmount;
    
}
@property(nonatomic,retain) dispatch_source_t timer;

@property (nonatomic,strong) LeftMenuViewController *menu;
@property (weak, nonatomic) IBOutlet UIScrollView *Myscrollview;
@property (weak, nonatomic) IBOutlet UIView *mainview;
@property (weak, nonatomic) IBOutlet UIView *titleview;
@property (weak, nonatomic) IBOutlet UITextField *PriceTF;
@property (weak, nonatomic) IBOutlet UILabel *CNYPrice;
@property (weak, nonatomic) IBOutlet UITextField *AmountTF;
@property (weak, nonatomic) IBOutlet UILabel *Useable;//可用
@property (weak, nonatomic) IBOutlet UILabel *TradeNumber;//委托额
@property (weak, nonatomic) IBOutlet UILabel *nowPrice;//现价
@property (weak, nonatomic) IBOutlet UILabel *nowCNY;
@property (weak, nonatomic) IBOutlet UIButton *TradeBtn;
@property (weak, nonatomic) IBOutlet UILabel *objectCoin;
@property (weak, nonatomic) IBOutlet NSLayoutConstraint *heightConstant;
@property (nonatomic,strong)NSMutableArray *contentArr;
@property (nonatomic,strong)NSMutableArray *askcontentArr;//卖出数组
@property (nonatomic,strong)NSMutableArray *bidcontentArr;//买入数组
@property (weak, nonatomic) IBOutlet UIView *line1;
@property (weak, nonatomic) IBOutlet UIView *line2;
@property (weak, nonatomic) IBOutlet UILabel *noDataLabel;
@property (weak, nonatomic) IBOutlet UIButton *buyBtn;
@property (weak, nonatomic) IBOutlet UIButton *sellBtn;
@property (weak, nonatomic) IBOutlet UILabel *plateLabel;
@property (weak, nonatomic) IBOutlet UILabel *pricelabel;
@property (weak, nonatomic) IBOutlet UILabel *amountlabel;
@property (weak, nonatomic) IBOutlet UIButton *allBtn;
@property (weak, nonatomic) IBOutlet UIButton *typeBtn;

//新加
@property (weak, nonatomic) IBOutlet StepSlider *slider;
@property (weak, nonatomic) IBOutlet UILabel *sliderMaxValue;
@property(nonatomic,copy)NSString *priceLimitBuy;//限价买入价格 货币
@property(nonatomic,strong)UIButton *shareButton;//导航栏右侧收藏按钮

@property (weak, nonatomic) IBOutlet UIButton *CurrentnowBut;//当前委托
@property (weak, nonatomic) IBOutlet UIButton *entrustmentHisBut;//历史委托
@property (weak, nonatomic) IBOutlet UILabel *linelabel;

@property(nonatomic,strong)NSMutableArray *hisdataArr;
@property (nonatomic,assign)BOOL isHistory;
@property(nonatomic,strong) AccountSettingInfoModel *accountInfo;//用户状态
@property (weak, nonatomic) IBOutlet NSLayoutConstraint *viewheights;

@property (strong, nonatomic) UILabel *toptitlelabel;

@end

@implementation TradeViewController

- (NSMutableArray *)askcontentArr
{
    if (!_askcontentArr) {
        _askcontentArr = [NSMutableArray array];
    }
    return _askcontentArr;
}
- (NSMutableArray *)bidcontentArr
{
    if (!_bidcontentArr) {
        _bidcontentArr = [NSMutableArray array];
    }
    return _bidcontentArr;
}

-(NSMutableArray *)hisdataArr{
    if (!_hisdataArr) {
        _hisdataArr = [NSMutableArray new];
    }
    return _hisdataArr;
}
- (void)viewDidLoad {
    [super viewDidLoad];
    
    self.Myscrollview.delegate = self;
    self.Myscrollview.showsVerticalScrollIndicator =NO;
    self.Myscrollview.showsHorizontalScrollIndicator =NO;
    
    [self.PriceTF setFont:[UIFont fontWithName:@"Helvetica-Bold" size:14.0 * kWindowWHOne]];
    [self.PriceTF addTarget:self action:@selector(textfieldValueChange:) forControlEvents:UIControlEventEditingChanged];
    self.PriceTF.tag = 1;
    [self.AmountTF setFont:[UIFont fontWithName:@"Helvetica-Bold" size:14.0 * kWindowWHOne]];
    [self.AmountTF addTarget:self action:@selector(textfieldValueChange:) forControlEvents:UIControlEventEditingChanged];
    self.AmountTF.tag = 2;
    [self getUSDTToCNYRate];
    
    self.topDistance.constant=40;
//        [self LeftsetupNavgationItemWithpictureName:@"tradeLeft"];
    self.isHistory = NO;
    self.viewheights.constant = 649;
    self.view.backgroundColor = mainColor;
    [self initTopView];
    
    //    UIButton *tradeButton = [UIButton buttonWithType:UIButtonTypeCustom];
    //    tradeButton.frame= CGRectMake(0, 0, 30, 30);
    //    [tradeButton setImage:[UIImage imageNamed:@"tradeRight"] forState:UIControlStateNormal];
    //    [tradeButton addTarget:self action:@selector(tradeRightBtnClick) forControlEvents:UIControlEventTouchUpInside];
    //
    //    _shareButton = [UIButton buttonWithType:UIButtonTypeCustom];
    //    _shareButton.frame= CGRectMake(0, 0, 30, 30);
    //    [_shareButton setImage:[UIImage imageNamed:@"uncollect"] forState:UIControlStateNormal];
    //    [_shareButton addTarget:self action:@selector(shareBtnClick) forControlEvents:UIControlEventTouchUpInside];
    //    UIBarButtonItem *tradeBtn= [[UIBarButtonItem alloc] initWithCustomView:tradeButton];
    //    UIBarButtonItem *share = [[UIBarButtonItem alloc] initWithCustomView:_shareButton];
    //    UIBarButtonItem *negativeSpacer = [[UIBarButtonItem alloc]initWithBarButtonSystemItem:UIBarButtonSystemItemFixedSpace target:nil action:nil];
    //    negativeSpacer.width = 10;
    //    NSArray *arr=[[NSArray alloc]initWithObjects:tradeBtn,negativeSpacer,share, nil];
    //    self.navigationItem.rightBarButtonItems=arr;
    
    [[NSNotificationCenter defaultCenter] addObserver:self selector:@selector(reloadShowData:)name:CURRENTSELECTED_SYMBOL object:nil];
    [self setTablewViewHeard];
    NSArray *coinArray = [[marketManager shareInstance].symbol componentsSeparatedByString:@"/"];
    _baseCoinName = [coinArray lastObject];
    _ObjectCoinName = [coinArray firstObject];
    [self.TradeBtn setTitle:LocalizationKey(@"Buy")forState:UIControlStateNormal];
    //language
    [[NSNotificationCenter defaultCenter] addObserver:self selector:@selector(languageSetting)name:LanguageChange object:nil];
    [self languageSetting];
    self.objectCoin.text=_ObjectCoinName;
    self.PriceTF.delegate=self;
    self.AmountTF.delegate=self;
    _IsMarketprice=NO;
    _IsSell=NO;
    self.buyBtn.selected = YES;
    self.sellBtn.selected = NO;
    self.TradeNumber.text=[NSString stringWithFormat:@"%@--",LocalizationKey(@"entrustment")];//默认
    
    self.CurrentnowBut.selected = YES;
    self.entrustmentHisBut.selected = NO;
    
    //    Ivar ivar =  class_getInstanceVariable([UITextField class], "_placeholderLabel");
    //    UILabel *placeholderLabel = object_getIvar(_AmountTF, ivar);
    //    placeholderLabel.textColor = [UIColor whiteColor];
    //设置sloder属性
    self.slider.delegate = self;
    self.slider.trackHeight = 3;
    self.slider.trackCircleRadius = 6;
    self.slider.sliderCircleRadius = 6;
    self.slider.trackColor= RGBOF(0xE6E6E6);
    self.slider.tintColor = GreenColor;
    self.slider.sliderCircleImage = [UIImage imageNamed:@"circularGreen"];
    [self.slider setMaxCount:5];
    [self.slider setIndex:0 animated:NO];
    self.slider.backgroundColor = mainColor;
    self.mainview.clipsToBounds = YES;
    //现价的手势设置
    UITapGestureRecognizer *tapRecognizerNowPrice=[[UITapGestureRecognizer alloc] initWithTarget:self action:@selector(nowPriceTapClick)];
    self.nowPrice.userInteractionEnabled=YES;
    [self.nowPrice addGestureRecognizer:tapRecognizerNowPrice];
    //    [self getData:[marketManager shareInstance].symbol];
    //
    //    [self getPersonAllCollection];
}

-(void)initTopView {
    
    CustomSegmentView *segmentControl=[[CustomSegmentView alloc]initWithFrame:CGRectMake(0,0,200,30) Items:@[LocalizationKey(@"tabbar3")]];
//    ,LocalizationKey(@"tabbar4")
    segmentControl.selectColor=baseColor;
    segmentControl.unselectColor=AppTextColor_Level_3;
    segmentControl.selectIndex=0;
    self.navigationItem.titleView = segmentControl;
    
    __weak typeof(segmentControl) segcl=segmentControl; //获取弱引用对象
    segmentControl.selectitemblock = ^(NSInteger selectIndex) {
        
        if (selectIndex==1) {
            FrenchCurrencyViewController   *Section4VC = [[FrenchCurrencyViewController alloc] init];
            
            Section4VC.hidesBottomBarWhenPushed = YES;
            [self.navigationController pushViewController:Section4VC animated:YES];
            
            segcl.selectIndex=segcl.unselectIndex;
        }
    };
    
    
    UIButton *tradeButton = [UIButton buttonWithType:UIButtonTypeCustom];
    tradeButton.frame= CGRectMake(SCREEN_WIDTH_S-40, 5, 30, 30);
    [tradeButton setImage:[UIImage imageNamed:@"tradeRight"] forState:UIControlStateNormal];
    [tradeButton addTarget:self action:@selector(tradeRightBtnClick) forControlEvents:UIControlEventTouchUpInside];
    [self.view addSubview:tradeButton];
    
    _shareButton = [UIButton buttonWithType:UIButtonTypeCustom];
    _shareButton.frame= CGRectMake(tradeButton.frame.origin.x -40, 5, 30, 30);
    [_shareButton setImage:[UIImage imageNamed:@"uncollect"] forState:UIControlStateNormal];
    [_shareButton addTarget:self action:@selector(shareBtnClick) forControlEvents:UIControlEventTouchUpInside];
    [self.view addSubview:_shareButton];
    
    UIButton *lefmenubtn=[UIButton buttonWithType:UIButtonTypeCustom];
    lefmenubtn.frame=CGRectMake(10,tradeButton.frame.origin.y,30,30);
    [lefmenubtn setImage:[UIImage imageNamed:@"tradeLeft"] forState:UIControlStateNormal];
    [lefmenubtn addTarget:self action:@selector(leftbtnclick:) forControlEvents:UIControlEventTouchUpInside];
    [self.view addSubview:lefmenubtn];
    
    _toptitlelabel=[[UILabel alloc]init];
    _toptitlelabel.font=[UIFont fontWithName:@"PingFangSC-Semibold" size:18];
    _toptitlelabel.textColor=AppTextColor_666666;
    _toptitlelabel.textAlignment=NSTextAlignmentCenter;
    _toptitlelabel.frame=CGRectMake(SCREEN_WIDTH_S/2-75,tradeButton.frame.origin.y,150,30);
    [self.view addSubview:_toptitlelabel];
}

//MARK:--导航栏右侧的进入K线按钮点击事件
-(void)tradeRightBtnClick{
    KchatViewController*klineVC=[[KchatViewController alloc]init];
    klineVC.symbol=[marketManager shareInstance].symbol;
    [[AppDelegate sharedAppDelegate] pushViewController:klineVC withBackTitle:[marketManager shareInstance].symbol];
}
//MARK:--导航栏右侧的收藏按钮点击事件
-(void)shareBtnClick{
    if (![YLUserInfo isLogIn]) {//未登录
        [self showLoginViewController];
        return;
    }
    if (_isCollect) {
        [self deleteCollectionWithsymbol:[marketManager shareInstance].symbol];
    }else{
        [self addCollectionWithsymbol:[marketManager shareInstance].symbol];
    }
}
/*删除
 */
-(void)deleteCollectionWithsymbol:(NSString*)symbol{
    [MarketNetManager deleteMyCollectionWithsymbol:symbol CompleteHandle:^(id resPonseObj, int code) {
        
        if (code) {
            if ([resPonseObj[@"code"] integerValue] == 0) {
                [_shareButton setImage:[UIImage imageNamed:@"uncollect"] forState:UIControlStateNormal];
                _isCollect=NO;
            }else{
                [self.view makeToast:resPonseObj[MESSAGE] duration:1.5 position:CSToastPositionCenter];
            }
        }else{
            [self.view makeToast:LocalizationKey(@"noNetworkStatus") duration:1.5 position:CSToastPositionCenter];
        }
    }];
}
/*添加
 */
-(void)addCollectionWithsymbol:(NSString*)symbol{
    [MarketNetManager addMyCollectionWithsymbol:symbol CompleteHandle:^(id resPonseObj, int code) {
        if (code) {
            if ([resPonseObj[@"code"] integerValue] == 0) {
                [_shareButton setImage:[UIImage imageNamed:@"collect"] forState:UIControlStateNormal];
                _isCollect=YES;
            }else{
                [self.view makeToast:resPonseObj[MESSAGE] duration:1.5 position:CSToastPositionCenter];
            }
        }else{
            [self.view makeToast:LocalizationKey(@"noNetworkStatus") duration:1.5 position:CSToastPositionCenter];
        }
    }];
}
#pragma mark-获取个人全部自选
-(void)getPersonAllCollection{
    _isCollect=NO;
    [MarketNetManager queryAboutMyCollectionCompleteHandle:^(id resPonseObj, int code) {
        if (code) {
            if ([resPonseObj isKindOfClass:[NSArray class]]) {
                NSArray*symbolArray=(NSArray*)resPonseObj;
                NSArray *dataArr = [symbolModel mj_objectArrayWithKeyValuesArray:symbolArray];
                [dataArr enumerateObjectsUsingBlock:^(symbolModel* obj, NSUInteger idx, BOOL * _Nonnull stop) {
                    if ([obj.symbol isEqualToString:[marketManager shareInstance].symbol]) {
                        _isCollect=YES;
                    }
                }];
                if (_isCollect) {
                    [_shareButton setImage:[UIImage imageNamed:@"collect"] forState:UIControlStateNormal];
                }else{
                    [_shareButton setImage:[UIImage imageNamed:@"uncollect"] forState:UIControlStateNormal];
                }
            }
            else if ([resPonseObj[@"code"] integerValue] == 3000 ||[resPonseObj[@"code"] integerValue] == 4000 ){
                
                [YLUserInfo logout];
            }else{
                [self.view makeToast:resPonseObj[MESSAGE] duration:1.5 position:CSToastPositionCenter];
            }
        }else{
            [self.view makeToast:LocalizationKey(@"noNetworkStatus") duration:1.5 position:CSToastPositionCenter];
        }
    } ];
}
//MARK:---现价的手势设置
-(void)nowPriceTapClick{
    if ([self.nowPrice.text floatValue] > 0) {
        self.PriceTF.text=[NSString stringWithFormat:@"%@",[ToolUtil stringFromNumber:[self.nowPrice.text floatValue] withlimit:_baseCoinScale]];
        NSString*cnyRate= [((AppDelegate*)[UIApplication sharedApplication].delegate).CNYRate stringValue];
        self.CNYPrice.text=[NSString stringWithFormat:@"≈%.2f USD",[self.PriceTF.text doubleValue]*_usdRate];
    }
}

-(void)getSliderValue:(CGFloat)sliderValue{
    NSArray *array = [self.sliderMaxValue.text componentsSeparatedByString:@" "]; //从字符A中分隔成2个元素的数组
    NSString *numStr = array[0];
    self.AmountTF.text=[NSString stringWithFormat:@"%@",[ToolUtil stringFromNumber:[numStr floatValue]*sliderValue/4 withlimit:_coinScale]];
    self.TradeNumber.text=[NSString stringWithFormat:@"%@ %@%@",LocalizationKey(@"entrustment"),[ToolUtil stringFromNumber:[self.PriceTF.text doubleValue]*[self.AmountTF.text doubleValue] withlimit:_baseCoinScale],_baseCoinName];
    
    
}
//MARK:--价格,数量输入输入框点击事件
- (void)textfieldValueChange:(UITextField *)textField{
    if (textField.tag == 1) {
        //价格
        if (!_IsMarketprice) {
            //            //限价
            //            NSArray *coinArray = [[marketManager shareInstance].symbol componentsSeparatedByString:@"/"];
            //            NSString *coinStr = [coinArray firstObject];
            //            if ([textField.text floatValue] <= 0) {
            //                self.sliderMaxValue.text=[NSString stringWithFormat:@"%@ %@",[ToolUtil stringFromNumber:0 withlimit:_baseCoinScale],coinStr];
            //                [self.slider setIndex:0 animated:NO];
            //            }else{
            //                self.sliderMaxValue.text=[NSString stringWithFormat:@"%@ %@",[ToolUtil stringFromNumber:[self.priceLimitBuy doubleValue]/[textField.text doubleValue] withlimit:_baseCoinScale],coinStr];
            //                NSArray *array = [self.sliderMaxValue.text componentsSeparatedByString:@" "]; //从字符A中分隔成2个元素的数组
            //                NSString *numStr = array[0];
            //                [self.slider setIndex:[textField.text floatValue]/[numStr floatValue]*4 animated:NO];
            //            }
        }
    }else if (textField.tag == 2){
        //数量
        NSArray *array = [self.sliderMaxValue.text componentsSeparatedByString:@" "]; //从字符A中分隔成2个元素的数组
        NSString *numStr = array[0];
        if ([textField.text floatValue] <= 0) {
            [self.slider setIndex:0 animated:NO];
        }else if ([textField.text floatValue] > [numStr floatValue]){
            [self.view makeToast:LocalizationKey(@"tradSlideTip1") duration:1.5 position:CSToastPositionCenter];
            textField.text = @"";
            self.TradeNumber.text=[NSString stringWithFormat:@"%@ %@%@",LocalizationKey(@"entrustment"),[ToolUtil stringFromNumber:0.00000000 withlimit:_baseCoinScale],_baseCoinName];
        }else{
            [self.slider setIndex:[textField.text floatValue]/[numStr floatValue]*4 animated:NO];
        }
    }
}
-(void)setTablewViewHeard{
    [self.asktableView registerNib:[UINib nibWithNibName:@"tradeCell" bundle:nil] forCellReuseIdentifier:@"Cell"];
    self.asktableView.tableFooterView=[UIView new];
    [self.bidtableView registerNib:[UINib nibWithNibName:@"tradeCell" bundle:nil] forCellReuseIdentifier:@"Cell"];
    self.bidtableView.tableFooterView=[UIView new];
    [self.entrusttableView registerNib:[UINib nibWithNibName:@"EntrustCell" bundle:nil] forCellReuseIdentifier:@"Cell"];
    [self.entrusttableView  registerNib:[UINib nibWithNibName:@"MyEntrustTableViewCell" bundle:nil] forCellReuseIdentifier:NSStringFromClass([MyEntrustTableViewCell class])];
    self.entrusttableView.tableFooterView=[UIView new];
}

-(void)viewWillAppear:(BOOL)animated{
    [super viewWillAppear:YES];
    if(![YLUserInfo isLogIn]){
        self.logBtn.hidden=NO;
        self.noDataLabel.hidden=YES;
    }else{
        self.logBtn.hidden=YES;
        if ([marketManager shareInstance].symbol) {
            [self getCommissionData:[marketManager shareInstance].symbol];//当前委托
            [self getHistoryCommissionData];//历史委托
            
        }
        [self accountSettingData];//账号信息
        [self getPersonAllCollection];//个人自选
//        [self initTopView];//   交易和法币按钮
    }
    if ([marketManager shareInstance].symbol) {
        [self getSingleAccuracy:[marketManager shareInstance].symbol];//获取单个交易对的精确度
        
        //        self.navigationItem.title=[marketManager shareInstance].symbol;
        self.toptitlelabel.text=[marketManager shareInstance].symbol;
    }
    [[self rdv_tabBarController] setTabBarHidden:NO animated:YES];
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


//MARK:--国际化通知处理事件
- (void)languageSetting{
    [self.buyBtn setTitle:LocalizationKey(@"Buy") forState:UIControlStateNormal];
    [self.sellBtn setTitle:LocalizationKey(@"Sell") forState:UIControlStateNormal];
    [self.allBtn setTitle:[NSString stringWithFormat:@"%@>>",LocalizationKey(@"morekline")] forState:UIControlStateNormal];
    [self.logBtn setTitle:LocalizationKey(@"login") forState:UIControlStateNormal];
    self.plateLabel.text=LocalizationKey(@"plate");
    self.pricelabel.text=[NSString stringWithFormat:@"%@(USDT)",LocalizationKey(@"price")];
    self.amountlabel.text=[NSString stringWithFormat:@"%@(%@)",LocalizationKey(@"amonut"),_ObjectCoinName];
    
    self.noDataLabel.text=LocalizationKey(@"noDada");
    if (_IsMarketprice) {
        [self.typeBtn setTitle:LocalizationKey(@"marketPrice") forState:UIControlStateNormal];
    }else{
        [self.typeBtn setTitle:LocalizationKey(@"limitPrice") forState:UIControlStateNormal];
    }
    
    self.PriceTF.placeholder=LocalizationKey(@"enterPrice");
    self.AmountTF.placeholder=LocalizationKey(@"commissionamount");
    self.Useable.text=[NSString stringWithFormat:@"%@--",LocalizationKey(@"usabel")];
    self.TradeNumber.text=[NSString stringWithFormat:@"%@ %@%@",LocalizationKey(@"entrustment"),[ToolUtil stringFromNumber:[self.PriceTF.text doubleValue]*[self.AmountTF.text doubleValue] withlimit:_baseCoinScale],_baseCoinName];
    [self.TradeBtn setTitle:LocalizationKey(@"Buy")forState:UIControlStateNormal];
    
    [self.CurrentnowBut setTitle:LocalizationKey(@"Current")forState:UIControlStateNormal];
    [self.entrustmentHisBut setTitle:LocalizationKey(@"HistoricalCurrent")forState:UIControlStateNormal];
}

- (void)viewDidDisappear:(BOOL)animated{
    [super viewDidDisappear:animated];
    if ([self.navigationController isKindOfClass:[YLNavigationController class]]) {
        YLNavigationController *nav = (YLNavigationController *)self.navigationController;
        nav.panGestureRec.enabled = YES;
    }
}

-(void)viewDidAppear:(BOOL)animated{
    [super viewDidAppear:YES];
    if ([self.navigationController isKindOfClass:[YLNavigationController class]]) {
        YLNavigationController *nav = (YLNavigationController *)self.navigationController;
        nav.panGestureRec.enabled = NO;
    }
    [[SocketManager share] sendMsgWithLength:SOCKETREQUEST_LENGTH withsequenceId:0 withcmd:SUBSCRIBE_SYMBOL_THUMB withVersion:COMMANDS_VERSION withRequestId: 0 withbody:nil];
    NSDictionary*dic;
    if ([YLUserInfo isLogIn]) {
        dic=[NSDictionary dictionaryWithObjectsAndKeys:[marketManager shareInstance].symbol,@"symbol",[YLUserInfo shareUserInfo].ID,@"uid",nil];
    }else{
        dic=[NSDictionary dictionaryWithObjectsAndKeys:[marketManager shareInstance].symbol,@"symbol",nil];
    }
    [[SocketManager share] sendMsgWithLength:SOCKETREQUEST_LENGTH withsequenceId:0 withcmd:SUBSCRIBE_EXCHANGE_TRADE withVersion:COMMANDS_VERSION withRequestId: 0 withbody:dic];
    [SocketManager share].delegate=self;
}
#pragma mark-左侧弹出菜单
//-(void)LefttouchEvent{
//
//    if (!self.menu) {
//        self.menu = [[LeftMenuViewController alloc]initWithNibName:@"LeftMenuViewController" bundle:nil];
//        CGRect frame = self.menu.view.frame;
//        frame.origin.x = - CGRectGetWidth(self.view.frame);
//        self.menu.view.frame = CGRectMake(- CGRectGetWidth(self.view.frame), 0,  kWindowW, kWindowH);
//        [[UIApplication sharedApplication].keyWindow addSubview:self.menu.view];
//    }
//    [self.menu showFromLeft];
//}
- (void)leftbtnclick:(UIButton *)button {
      if (!self.menu) {
          self.menu = [[LeftMenuViewController alloc]initWithNibName:@"LeftMenuViewController" bundle:nil];
          CGRect frame = self.menu.view.frame;
          frame.origin.x = - CGRectGetWidth(self.view.frame);
          self.menu.view.frame = CGRectMake(- CGRectGetWidth(self.view.frame), 0,  kWindowW, kWindowH);
          [[UIApplication sharedApplication].keyWindow addSubview:self.menu.view];
      }
      
      [self.menu showFromLeft];
}

-(NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section{
    if ([tableView isEqual:self.asktableView]) {
        return self.askcontentArr.count;
    }else  if ([tableView isEqual:self.bidtableView]) {
        return self.bidcontentArr.count;
    }else{
        if (self.isHistory) {
            return self.hisdataArr.count;
        }
        return self.contentArr.count;
    }
}
- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath
{
    if ([tableView isEqual:self.asktableView]) {
        tradeCell * cell = [tableView dequeueReusableCellWithIdentifier:@"Cell" forIndexPath:indexPath];
        cell.selectionStyle=UITableViewCellSelectionStyleNone;
        cell.backview.backgroundColor = kRGBColor(241, 80, 87);
        cell.backview.alpha = 0.1;
        if (self.askcontentArr.count>0 ) {
            plateModel*bidplatemodel=[[self.askcontentArr reverseObjectEnumerator] allObjects][indexPath.row];
            if (bidplatemodel.price<0) {
                cell.priceLabel.text=@"--";
                cell.amountLabel.text=@"--";
                //                cell.amountLabel.textColor=RedColor;
                cell.priceLabel.textColor=RedColor;
                
            }else{
                cell.priceLabel.text=[NSString stringWithFormat:@"%@",[ToolUtil stringFromNumber:bidplatemodel.price withlimit:_baseCoinScale]];
                if (bidplatemodel.amount>=1000) {
                    cell.amountLabel.text=[NSString stringWithFormat:@"%@K",[ToolUtil stringFromNumber:bidplatemodel.amount/1000 withlimit:_coinScale]];
                }else{
                    cell.amountLabel.text=[NSString stringWithFormat:@"%@",[ToolUtil stringFromNumber:bidplatemodel.amount withlimit:_coinScale]];
                }
                //                cell.amountLabel.textColor=RedColor;
                cell.priceLabel.textColor=RedColor;
            }
            
            if (bidplatemodel.amount>=0) {
                @try {
                    cell.backwidth.constant=bidplatemodel.totalAmount/allDepthsellAmount*cell.contentView.width;
                } @catch (NSException *exception) {
                    cell.backwidth.constant=0;
                }
                
            }else{
                cell.backwidth.constant=0;
            }
        }
        return cell;
    }else if ([tableView isEqual:self.bidtableView]){
        tradeCell * cell = [tableView dequeueReusableCellWithIdentifier:@"Cell" forIndexPath:indexPath];
        cell.selectionStyle=UITableViewCellSelectionStyleNone;
        cell.backview.backgroundColor = kRGBColor(42, 178, 116);
        cell.backview.alpha = 0.1;
        plateModel*askplatemodel=self.bidcontentArr[indexPath.row];
        if (askplatemodel.price<0) {
            cell.priceLabel.text=@"--";
            cell.amountLabel.text=@"--";
            //            cell.amountLabel.textColor=GreenColor;
            cell.priceLabel.textColor=GreenColor;
            cell.backwidth.constant=0;
            
        }else{
            cell.priceLabel.text=[NSString stringWithFormat:@"%@",[ToolUtil stringFromNumber:askplatemodel.price withlimit:_baseCoinScale]];
            if (askplatemodel.amount>=1000) {
                cell.amountLabel.text=[NSString stringWithFormat:@"%@K",[ToolUtil stringFromNumber:askplatemodel.amount/1000 withlimit:_coinScale]];
            }else{
                cell.amountLabel.text=[NSString stringWithFormat:@"%@",[ToolUtil stringFromNumber:askplatemodel.amount withlimit:_coinScale]];
            }
            //            cell.amountLabel.textColor=GreenColor;
            cell.priceLabel.textColor=GreenColor;
        }
        if (askplatemodel.amount>=0) {
            cell.backwidth.constant=askplatemodel.totalAmount/allDepthbuyAmount*cell.contentView.width;
        }else{
            cell.backwidth.constant=0;
        }
        return cell;
    }
    else{
        if (self.isHistory) {
            MyEntrustTableViewCell *cell = [tableView dequeueReusableCellWithIdentifier:NSStringFromClass([MyEntrustTableViewCell class]) forIndexPath:indexPath];
            cell.selectionStyle = UITableViewCellSelectionStyleNone;
            MyEntrustInfoModel *model = self.hisdataArr[indexPath.row];
            cell.infomodel = model;
            //委托金额
            cell.dealNumData.text = model.turnover;
            
            cell.entrustBlock = ^{
                MyEntrustDetailViewController *detailVC = [[MyEntrustDetailViewController alloc] init];
                detailVC.model = model;
                [[AppDelegate sharedAppDelegate] pushViewController:detailVC];
            };
            
            return cell;
        }else{
            EntrustCell * cell = [tableView dequeueReusableCellWithIdentifier:@"Cell" forIndexPath:indexPath];
            cell.selectionStyle=UITableViewCellSelectionStyleNone;
            if (_contentArr.count>0) {
                [cell configModel:_contentArr[indexPath.row]];
            }
            cell.withDraw.tag=indexPath.row;
            [cell.withDraw addTarget:self action:@selector(withDraw:) forControlEvents:UIControlEventTouchUpInside];
            return cell;
        }
        
        
    }
}

- (CGFloat)tableView:(UITableView *)tableView heightForRowAtIndexPath:(NSIndexPath*)indexPath
{
    if ([tableView isEqual:self.asktableView]||[tableView isEqual:self.bidtableView]) {
        return 35;
    }else{
        if (self.isHistory) {
            return  185;
            
        }
        return 115;
    }
}
-(void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath
{
    if (!_IsMarketprice) {
        //限价
        if ([tableView isEqual:self.asktableView]) {
            
            plateModel*bidplatemodel=[[self.askcontentArr reverseObjectEnumerator] allObjects][indexPath.row];
            if (bidplatemodel.price>=0) {
                self.PriceTF.text=[NSString stringWithFormat:@"%@",[ToolUtil stringFromNumber:bidplatemodel.price withlimit:_baseCoinScale]];
//                NSString*cnyRate= [((AppDelegate*)[UIApplication sharedApplication].delegate).CNYRate stringValue];
                self.CNYPrice.text=[NSString stringWithFormat:@"≈%.2f USD",[self.PriceTF.text doubleValue]*_usdRate];
            }
            
        }else if ([tableView isEqual:self.bidtableView]){
            plateModel*askplatemodel=self.bidcontentArr[indexPath.row];
            if (askplatemodel.price>=0) {
                self.PriceTF.text=[NSString stringWithFormat:@"%@",[ToolUtil stringFromNumber:askplatemodel.price withlimit:_baseCoinScale]];
//                NSString*cnyRate= [((AppDelegate*)[UIApplication sharedApplication].delegate).CNYRate stringValue];
                self.CNYPrice.text=[NSString stringWithFormat:@"≈%.2f USD",[self.PriceTF.text doubleValue]*_usdRate];
            }
        }else{
            
        }
    }
}
#pragma mark-撤销
-(void)withDraw:(UIButton*)sender{
    commissionModel*model=self.contentArr[sender.tag];
    __weak TradeViewController*weakSelf=self;
    NSString*price;//委托价
    NSString*commissionAmount;//委托量
    NSString*commissionTotal;//委托总额
    if ([model.type isEqualToString:@"LIMIT_PRICE"]) {
        price=[NSString stringWithFormat:@"%@%@",model.price,model.baseSymbol];
        commissionAmount=[NSString stringWithFormat:@"%@%@",model.amount,model.coinSymbol];
        
        NSDecimalNumber *price = [[NSDecimalNumber alloc] initWithString:model.price];
        NSDecimalNumber *tradedAmount = [[NSDecimalNumber alloc] initWithString:model.amount];
        commissionTotal=[NSString stringWithFormat:@"%@%@",[[price decimalNumberByMultiplyingBy:tradedAmount] stringValue],model.baseSymbol];
    }else{
        price=LocalizationKey(@"marketPrice");
        if ([model.direction isEqualToString:@"SELL"]) {
            commissionAmount=[NSString stringWithFormat:@"%.2f%@",[model.amount doubleValue],model.coinSymbol];
            commissionTotal=[NSString stringWithFormat:@"%@%@",@"--",model.baseSymbol];
        }else{
            commissionAmount=[NSString stringWithFormat:@"%@%@",@"--",model.coinSymbol];
            commissionTotal=[NSString stringWithFormat:@"%.2f%@",[model.amount doubleValue],model.baseSymbol];
        }
    }
    EasyShowAlertView *showView =[EasyShowAlertView showActionSheetWithTitle:LocalizationKey(@"Confirmation") left1message:price right1message:commissionAmount left2message:commissionTotal right2message:[model.direction isEqualToString:@"BUY"]==YES?LocalizationKey(@"Buy"):LocalizationKey(@"Sell")];
    [showView addItemWithTitle:LocalizationKey(@"Cancelorder") itemType:ShowAlertItemTypeBlack callback:^(EasyShowAlertView *showview) {
        [weakSelf cancelCommissionwithOrderID:model.orderId];//撤单
    }];
    [showView addItemWithTitle:LocalizationKey(@"cancel") itemType:ShowAlertItemTypeBlack callback:^(EasyShowAlertView *showview) {
    }];
    [showView show];
}

-(void)cancelCommissionwithOrderID:(NSString*)orderId{
    [EasyShowLodingView showLodingText:LocalizationKey(@"loading")];
    [TradeNetManager cancelCommissionwithOrderID:orderId CompleteHandle:^(id resPonseObj, int code) {
        [EasyShowLodingView hidenLoding];
        if (code) {
            if ([resPonseObj[@"code"] integerValue] == 0) {
                [self.view makeToast:@"撤销成功" duration:1.5 position:CSToastPositionCenter];
                //                [self.contentArr enumerateObjectsUsingBlock:^(commissionModel* obj, NSUInteger idx, BOOL * _Nonnull stop) {
                //                    if ([obj.orderId isEqualToString:orderId]) {
                //                        *stop = YES;
                //                        [self.contentArr removeObject:obj];
                //                        [self.entrusttableView reloadData];
                //                    }
                //                }];
                [self getAllCoinData:[marketManager shareInstance].symbol];//重新获取钱包余额
                if (self.CurrentnowBut.selected) {
                    [self getCommissionData:[marketManager shareInstance].symbol];
                }else{
                    [self getHistoryCommissionData];
                }
                
            }else{
                [self.view makeToast:resPonseObj[MESSAGE] duration:1.5 position:CSToastPositionCenter];
            }
        }else{
            [self.view makeToast:LocalizationKey(@"noNetworkStatus") duration:1.5 position:CSToastPositionCenter];
        }
        
    }];
}
#pragma mark-按钮点击事件
- (IBAction)touchEvents:(UIButton *)sender {
    switch (sender.tag) {
        case 100://买入
        {
            _IsSell=NO;
            self.buyBtn.selected = YES;
            self.sellBtn.selected = NO;
            if (!_timer) {
                self.TradeNumber.text=[NSString stringWithFormat:@"%@--",LocalizationKey(@"entrustment")];
                self.lineView.backgroundColor=GreenColor;
                [self.TradeBtn setTitle:LocalizationKey(@"Buy") forState:UIControlStateNormal];
                self.TradeBtn.backgroundColor=GreenColor;
            }
            
            [UIView animateWithDuration:0.3 animations:^{
                self.lineView.centerX=sender.centerX;
            }];
            
            NSArray *coinArray = [[marketManager shareInstance].symbol componentsSeparatedByString:@"/"];
            if ([YLUserInfo isLogIn]) {
                [self getBasewalletwithcoin:[coinArray lastObject]];
                
            }
        }
            if (_IsMarketprice) {
                self.heightConstant.constant=0;
                self.PriceTF.text=LocalizationKey(@"Optimal");
                self.PriceTF.enabled=NO;
                self.AmountTF.placeholder=LocalizationKey(@"entrustment");
                self.objectCoin.text=_baseCoinName;
            }else{
                self.heightConstant.constant=45;
                self.PriceTF.text=self.nowPrice.text;
                self.PriceTF.enabled=YES;
                self.AmountTF.placeholder=LocalizationKey(@"amonut");
                self.objectCoin.text=_ObjectCoinName;
            }
            self.slider.sliderCircleImage = [UIImage imageNamed:@"circularGreen"];
            [self.slider setIndex:0 animated:NO];
            self.slider.tintColor = GreenColor;
            self.AmountTF.text=@"";
            break;
        case 101://卖出
        {
            _IsSell=YES;
            self.buyBtn.selected = NO;
            self.sellBtn.selected = YES;
            if (!_timer) {
                self.TradeNumber.text=[NSString stringWithFormat:@"%@--",LocalizationKey(@"entrustment")];
                [self.TradeBtn setTitle:LocalizationKey(@"Sell") forState:UIControlStateNormal];
                self.TradeBtn.backgroundColor=RedColor;
                self.lineView.backgroundColor=RedColor;
            }
            
            [UIView animateWithDuration:0.3 animations:^{
                self.lineView.centerX=sender.centerX;
            } completion:nil];
            
            
            NSArray *coinArray = [[marketManager shareInstance].symbol componentsSeparatedByString:@"/"];
            if ([YLUserInfo isLogIn]) {
                [self getCoinwalletwithcoin:[coinArray firstObject]];
            }
            if (_IsMarketprice) {
                self.heightConstant.constant=0;
                self.PriceTF.text=LocalizationKey(@"Optimal");
                self.PriceTF.enabled=NO;
                self.AmountTF.placeholder=LocalizationKey(@"amonut");
                self.objectCoin.text=_ObjectCoinName;
            }else{
                self.heightConstant.constant=45;
                self.PriceTF.text=self.nowPrice.text;
                self.PriceTF.enabled=YES;
                self.AmountTF.placeholder=LocalizationKey(@"amonut");
                self.objectCoin.text=_ObjectCoinName;
            }
        }
            self.slider.sliderCircleImage = [UIImage imageNamed:@"circularRed"];
            [self.slider setIndex:0 animated:NO];
            self.slider.tintColor = RedColor;
            self.AmountTF.text=@"";
            break;
        case 102://限价Or市价
        {
            [self.PriceTF resignFirstResponder];
            [self.AmountTF resignFirstResponder];
            NSArray *arrayTitle = @[LocalizationKey(@"limitPrice"),LocalizationKey(@"marketPrice")];
            UIColor *color = [UIColor blackColor];
            [self.view createAlertViewTitleArray:arrayTitle textColor:color font:[UIFont systemFontOfSize:16] type:0 actionBlock:^(UIButton * _Nullable button, NSInteger didRow) {
                [sender setTitle:button.currentTitle forState:UIControlStateNormal];
                [self.slider setIndex:0 animated:NO];
                if (didRow==0) {
                    //限价
                    self.heightConstant.constant=45;
                    _IsMarketprice=NO;
                    self.PriceTF.text=self.nowPrice.text;
                    self.PriceTF.enabled=YES;
                    self.AmountTF.placeholder=LocalizationKey(@"amonut");
                    self.objectCoin.text=_ObjectCoinName;
                    self.CNYPrice.hidden=NO;
                    self.TradeNumber.hidden=NO;
                    self.TradeNumber.hidden=NO;
                    self.line1.hidden=NO;
                    self.line2.hidden=NO;
                    self.AmountTF.text=@"";
                    self.TradeNumber.text=[NSString stringWithFormat:@"%@--",LocalizationKey(@"entrustment")];
                    if (!_IsSell) {
                        NSArray *coinArray = [[marketManager shareInstance].symbol componentsSeparatedByString:@"/"];
                        if ([YLUserInfo isLogIn]) {
                            [self getBasewalletwithcoin:[coinArray lastObject]];
                        }
                    }else{
                        NSArray *coinArray = [[marketManager shareInstance].symbol componentsSeparatedByString:@"/"];
                        if ([YLUserInfo isLogIn]) {
                            [self getCoinwalletwithcoin:[coinArray firstObject]];
                        }
                    }
                    
                }else if (didRow==1){
                    //市价
                    _IsMarketprice=YES;
                    self.heightConstant.constant=0;
                    self.PriceTF.text=LocalizationKey(@"Optimal");
                    self.PriceTF.enabled=NO;
                    self.CNYPrice.hidden=YES;
                    self.TradeNumber.hidden=YES;
                    self.TradeNumber.hidden=YES;
                    self.line1.hidden=YES;
                    self.line2.hidden=YES;
                    self.AmountTF.text=@"";
                    self.TradeNumber.text=[NSString stringWithFormat:@"%@--",LocalizationKey(@"entrustment")];
                    if (!_IsSell) {
                        //买入
                        self.AmountTF.placeholder=LocalizationKey(@"entrustment");
                        self.objectCoin.text=_baseCoinName;
                        NSArray *coinArray = [[marketManager shareInstance].symbol componentsSeparatedByString:@"/"];
                        if ([YLUserInfo isLogIn]) {
                            [self getBasewalletwithcoin:[coinArray lastObject]];                }
                    }else{
                        //卖出
                        self.AmountTF.placeholder=LocalizationKey(@"amonut");
                        self.objectCoin.text=_ObjectCoinName;
                        NSArray *coinArray = [[marketManager shareInstance].symbol componentsSeparatedByString:@"/"];
                        if ([YLUserInfo isLogIn]) {
                            [self getCoinwalletwithcoin:[coinArray firstObject]];
                        }
                    }
                }
            }];
        }
            break;
        case 103://价格-
        {
            if ([self.PriceTF.text doubleValue]>0) {
                
                self.PriceTF.text=[ToolUtil stringFromNumber:[self.PriceTF.text doubleValue]-(1/(double)pow(10, _baseCoinScale)) withlimit:_baseCoinScale];//10的N次幂
                NSString*cnyRate= [((AppDelegate*)[UIApplication sharedApplication].delegate).CNYRate stringValue];
                self.CNYPrice.text=[NSString stringWithFormat:@"≈%.2f USD",[self.PriceTF.text doubleValue]*_usdRate];
                self.TradeNumber.text=[NSString stringWithFormat:@"%@ %@%@",LocalizationKey(@"entrustment"),[ToolUtil stringFromNumber:[self.PriceTF.text doubleValue]*[self.AmountTF.text doubleValue] withlimit:_baseCoinScale],_baseCoinName];
            }
        }
            break;
        case 104://价格+
        {
            if ([self.PriceTF.text doubleValue]>=0) {
                self.PriceTF.text= [ToolUtil stringFromNumber:[self.PriceTF.text doubleValue]+(1/(double)pow(10, _baseCoinScale)) withlimit:_baseCoinScale];//10的N次幂
                NSString*cnyRate= [((AppDelegate*)[UIApplication sharedApplication].delegate).CNYRate stringValue];
                self.CNYPrice.text=[NSString stringWithFormat:@"≈%.2f USD",[self.PriceTF.text doubleValue]*_usdRate];
                self.TradeNumber.text=[NSString stringWithFormat:@"%@ %@%@",LocalizationKey(@"entrustment"),[ToolUtil stringFromNumber:[self.PriceTF.text doubleValue]*[self.AmountTF.text doubleValue] withlimit:_baseCoinScale] ,_baseCoinName];
            }
        }
            break;
        case 105: //下单
        {
            [self.PriceTF resignFirstResponder];
            [self.AmountTF resignFirstResponder];
            if(![YLUserInfo isLogIn]){
                [self showLoginViewController];
                return;
            }
            
            //实名认证
            if (![self.accountInfo.realVerified isEqualToString:@"1"]) {
                [self.view makeToast:LocalizationKey(@"validateYourID") duration:1.5 position:CSToastPositionCenter];
                
                return;
            }
            if (!_IsSell) { /*买入*/
                
                if (!_IsMarketprice) {
                    
                    //限价买入
                    if ( [NSString stringIsNull:self.PriceTF.text]) {
                        [self.view makeToast:LocalizationKey(@"enterprice") duration:1.5 position:CSToastPositionCenter];
                        return;
                    }
                    if ([self.PriceTF.text doubleValue]<=0) {
                        [self.view makeToast:LocalizationKey(@"commissionPricezero") duration:1.5 position:CSToastPositionCenter];
                        return;
                    }
                    if ( [NSString stringIsNull:self.AmountTF.text]) {
                        [self.view makeToast:LocalizationKey(@"pleasecommissionamount") duration:1.5 position:CSToastPositionCenter];
                        return;
                    }
                    if ([self.AmountTF.text doubleValue]<=0) {
                        [self.view makeToast:LocalizationKey(@"commissionamountzero") duration:1.5 position:CSToastPositionCenter];
                        return;
                    }
                    __weak TradeViewController*weakSelf=self;
                    EasyShowAlertView *showView =[EasyShowAlertView showActionSheetWithTitle:LocalizationKey(@"commissionbuy") left1message:[NSString stringWithFormat:@"%@%@",[ToolUtil stringFromNumber:[self.PriceTF.text doubleValue] withlimit:_baseCoinScale],_baseCoinName] right1message:[NSString stringWithFormat:@"%@%@",[ToolUtil stringFromNumber:[self.AmountTF.text doubleValue] withlimit:_coinScale],_objectCoin.text] left2message:[NSString stringWithFormat:@"%@%@",[ToolUtil stringFromNumber:[self.PriceTF.text doubleValue]*[self.AmountTF.text doubleValue] withlimit:_baseCoinScale],_baseCoinName] right2message:LocalizationKey(@"buyDirection")];
                    [showView addItemWithTitle:LocalizationKey(@"confirm") itemType:ShowAlertItemTypeBlack callback:^(EasyShowAlertView *showview) {
                        [weakSelf commitBuyCommission:@"BUY"];
                    }];
                    [showView addItemWithTitle:LocalizationKey(@"cancel") itemType:ShowAlertItemTypeBlack callback:^(EasyShowAlertView *showview) {
                    }];
                    [EasyShowOptions sharedEasyShowOptions].alertTintColor = kRGBColor(18, 22, 28);
                    [EasyShowOptions sharedEasyShowOptions].alertTitleColor = RGBOF(0xe6e6e6);
                    [EasyShowOptions sharedEasyShowOptions].alertMessageColor = mainColor;
                    [showView show];
                }else{
                    //市价买入
                    if ( [NSString stringIsNull:self.AmountTF.text]) {
                        [self.view makeToast:LocalizationKey(@"commissionmoney") duration:1.5 position:CSToastPositionCenter];
                        return;
                    }
                    if ([self.AmountTF.text doubleValue]<=0) {
                        [self.view makeToast:LocalizationKey(@"commissionmoneyzero") duration:1.5 position:CSToastPositionCenter];
                        return;
                    }
                    
                    __weak TradeViewController*weakSelf=self;
                    EasyShowAlertView *showView =[EasyShowAlertView showActionSheetWithTitle:LocalizationKey(@"commissionbuy") left1message:LocalizationKey(@"marketPrice") right1message:[NSString stringWithFormat:@"%@%@",@"--",_ObjectCoinName] left2message:[NSString stringWithFormat:@"%.8f%@",[self.AmountTF.text doubleValue],_baseCoinName] right2message:LocalizationKey(@"Buy")];
                    [showView addItemWithTitle:LocalizationKey(@"confirm") itemType:ShowAlertItemTypeBlack callback:^(EasyShowAlertView *showview) {
                        [weakSelf commitBuyCommission:@"BUY"];
                    }];
                    [showView addItemWithTitle:LocalizationKey(@"cancel") itemType:ShowAlertItemTypeBlack callback:^(EasyShowAlertView *showview) {
                    }];
                    [showView show];
                }
            }
            else{
                
                /*卖出*/
                if (!_IsMarketprice) {
                    
                    
                    //限价卖出
                    if ( [NSString stringIsNull:self.PriceTF.text]) {
                        [self.view makeToast:LocalizationKey(@"pleaseenterPrice") duration:1.5 position:CSToastPositionCenter];
                        return;
                    }
                    if ([self.PriceTF.text doubleValue]<=0) {
                        [self.view makeToast:LocalizationKey(@"commissionPricezero") duration:1.5 position:CSToastPositionCenter];
                        return;
                    }
                    if ( [NSString stringIsNull:self.AmountTF.text]) {
                        [self.view makeToast:LocalizationKey(@"pleasecommissionamount") duration:1.5 position:CSToastPositionCenter];
                        return;
                    }
                    if ([self.AmountTF.text doubleValue]<=0) {
                        [self.view makeToast:LocalizationKey(@"commissionamountzero") duration:1.5 position:CSToastPositionCenter];
                        return;
                    }
                    
                    __weak TradeViewController*weakSelf=self;
                    EasyShowAlertView *showView =[EasyShowAlertView showActionSheetWithTitle:LocalizationKey(@"commissionsell") left1message:[NSString stringWithFormat:@"%@%@",[ToolUtil stringFromNumber:[self.PriceTF.text doubleValue] withlimit:_baseCoinScale],_baseCoinName] right1message:[NSString stringWithFormat:@"%@%@",[ToolUtil stringFromNumber:[self.AmountTF.text doubleValue] withlimit:_coinScale],_ObjectCoinName] left2message:[NSString stringWithFormat:@"%.8f%@",[self.PriceTF.text doubleValue]*[self.AmountTF.text doubleValue],_baseCoinName] right2message:LocalizationKey(@"sellDirection")];
                    [showView addItemWithTitle:LocalizationKey(@"confirm") itemType:ShowAlertItemTypeBlack callback:^(EasyShowAlertView *showview) {
                        [weakSelf commitBuyCommission:@"SELL"];
                    }];
                    [showView addItemWithTitle:LocalizationKey(@"cancel") itemType:ShowAlertItemTypeBlack callback:^(EasyShowAlertView *showview) {
                    }];
                    [showView show];
                }
                else{
                    //市价卖出
                    if ( [NSString stringIsNull:self.AmountTF.text]) {
                        [self.view makeToast:LocalizationKey(@"pleasecommissionamount") duration:1.5 position:CSToastPositionCenter];
                        return;
                    }
                    if ([self.AmountTF.text doubleValue]<=0) {
                        [self.view makeToast:LocalizationKey(@"commissionamountzero") duration:1.5 position:CSToastPositionCenter];
                        return;
                    }
                    
                    __weak TradeViewController*weakSelf=self;
                    EasyShowAlertView *showView =[EasyShowAlertView showActionSheetWithTitle:LocalizationKey(@"commissionsell") left1message:LocalizationKey(@"marketPrice") right1message:[NSString stringWithFormat:@"%@%@",[ToolUtil stringFromNumber:[self.AmountTF.text doubleValue] withlimit:_coinScale],_ObjectCoinName] left2message:[NSString stringWithFormat:@"%@%@",@"--",_baseCoinName] right2message:LocalizationKey(@"sellDirection")];
                    [showView addItemWithTitle:LocalizationKey(@"confirm") itemType:ShowAlertItemTypeBlack callback:^(EasyShowAlertView *showview) {
                        [weakSelf commitBuyCommission:@"SELL"];
                    }];
                    [showView addItemWithTitle:LocalizationKey(@"cancel") itemType:ShowAlertItemTypeBlack callback:^(EasyShowAlertView *showview) {
                    }];
                    [showView show];
                }
            }
        }
            break;
        case 106: //查看全部当前委托
        {
            if(![YLUserInfo isLogIn]){
                [self showLoginViewController];
                return;
            }
            if ([marketManager shareInstance].symbol) {
                EntrustmentRecordViewController *record = [[EntrustmentRecordViewController alloc] init];
                record.symbol = [marketManager shareInstance].symbol;
                [[AppDelegate sharedAppDelegate] pushViewController:record];
                return;
            }
        }
            break;
        default:
            break;
    }
}

#pragma mark-当前委托
- (IBAction)Currententrustmentaction:(id)sender {
    self.CurrentnowBut.selected = YES;
    self.entrustmentHisBut.selected = NO;
    [UIView animateWithDuration:0.3 animations:^{
        self.linelabel.centerX=self.CurrentnowBut.centerX;
    }];
    self.isHistory = NO;
    
    if ([YLUserInfo isLogIn]) {
        if (self.contentArr.count > 0) {
            self.noDataLabel.hidden = YES;
        }else{
            self.noDataLabel.hidden = NO;
            
        }
        self.viewheights.constant = [self getviewheightspce];
        
        [self.entrusttableView reloadData];
    }
    
}

#pragma mark-历史委托
- (IBAction)Historicalentrustmentaction:(id)sender {
    self.CurrentnowBut.selected = NO;
    self.entrustmentHisBut.selected = YES;
    [UIView animateWithDuration:0.3 animations:^{
        self.linelabel.centerX=self.entrustmentHisBut.centerX;
    }];
    self.isHistory = YES;
    
    if ([YLUserInfo isLogIn]) {
        if (self.hisdataArr.count > 0) {
            self.noDataLabel.hidden = YES;
        }else{
            self.noDataLabel.hidden = NO;
            
        }
        self.viewheights.constant = [self getviewheightspce];
        
        [self.entrusttableView reloadData];
        //        [self getHistoryCommissionData];
    }
    
}

#pragma mark-提交委托
-(void)commitBuyCommission:(NSString*)str{
    if (_IsMarketprice) {
        [TradeNetManager SubmissionOfentrustmentWithsymbol:[marketManager shareInstance].symbol withPrice:@"0" withAmount:self.AmountTF.text WithDirection:str withType:@"MARKET_PRICE" useDiscount:@"0" CompleteHandle:^(id resPonseObj, int code) {
            if (code) {
                if ([resPonseObj[@"code"] integerValue] == 0) {
                    //                    [self.view makeToast:resPonseObj[MESSAGE] duration:1.5 position:CSToastPositionCenter];
                    self.PriceTF.text=LocalizationKey(@"Optimal");
                    self.AmountTF.text=@"";
                    self.TradeNumber.text=[NSString stringWithFormat:@"%@--",LocalizationKey(@"entrustment")];
                    
                }else{
                    [self.view makeToast:resPonseObj[MESSAGE] duration:1.5 position:CSToastPositionCenter];
                }
            }else{
                [self.view makeToast:LocalizationKey(@"noNetworkStatus") duration:1.5 position:CSToastPositionCenter];
            }
        }];
        
    }else{
        [TradeNetManager SubmissionOfentrustmentWithsymbol:[marketManager shareInstance].symbol withPrice:self.PriceTF.text withAmount:self.AmountTF.text WithDirection:str withType:@"LIMIT_PRICE" useDiscount:@"0" CompleteHandle:^(id resPonseObj, int code) {
            if (code) {
                if ([resPonseObj[@"code"] integerValue] == 0) {
                    //                    [self.view makeToast:resPonseObj[MESSAGE] duration:1.5 position:CSToastPositionCenter];
                    self.AmountTF.text=@"";
                    self.TradeNumber.text=[NSString stringWithFormat:@"%@--",LocalizationKey(@"entrustment")];
                }else{
                    [self.view makeToast:resPonseObj[MESSAGE] duration:1.5 position:CSToastPositionCenter];
                }
            }else{
                [self.view makeToast:LocalizationKey(@"noNetworkStatus") duration:1.5 position:CSToastPositionCenter];
            }
        }];
    }
    dispatch_after( dispatch_time(DISPATCH_TIME_NOW, (int64_t)(0.5/*延迟执行时间*/ * NSEC_PER_SEC)), dispatch_get_main_queue(), ^{
        [self getCommissionData:[marketManager shareInstance].symbol];//刷新表格
        [self getAllCoinData:[marketManager shareInstance].symbol];//重新获取钱包余额
    });
}
#pragma mark-获取所有交易币种缩略行情
-(void)getAllCoinData:(NSString*)symbol {
    [EasyShowLodingView showLodingText:LocalizationKey(@"loading")];
    [HomeNetManager getsymbolthumbCompleteHandle:^(id resPonseObj, int code) {
        NSLog(@"获取所有交易币种缩略行情 --- %@",resPonseObj);
        [EasyShowLodingView hidenLoding];
        if (code) {
            if ([resPonseObj isKindOfClass:[NSArray class]]) {
                NSArray*symbolArray=(NSArray*)resPonseObj;
                for (int i=0; i<symbolArray.count; i++) {
                    symbolModel*model = [symbolModel mj_objectWithKeyValues:symbolArray[i]];
                    if ([model.symbol isEqualToString:symbol]) {
                        NSArray *coinArray = [[marketManager shareInstance].symbol componentsSeparatedByString:@"/"];
                        self.nowPrice.text=[ToolUtil stringFromNumber:[model.close doubleValue] withlimit:_baseCoinScale];
//                        NSString*cnyRate= [((AppDelegate*)[UIApplication sharedApplication].delegate).CNYRate stringValue];
                        NSDecimalNumber *close = [NSDecimalNumber decimalNumberWithDecimal:[model.close decimalValue]];
                        NSDecimalNumber *baseUsdRate = [NSDecimalNumber decimalNumberWithDecimal:[model.baseUsdRate decimalValue]];
                        self.nowCNY.text=[NSString stringWithFormat:@"≈%.2f USD",[[close decimalNumberByMultiplyingBy:baseUsdRate] doubleValue]];
                        _usdRate= [model.baseUsdRate doubleValue];
                        if (!_IsMarketprice) {
                            self.PriceTF.text=[ToolUtil stringFromNumber:[model.close doubleValue] withlimit:_baseCoinScale];
                            self.CNYPrice.text=[NSString stringWithFormat:@"≈%.2f USD",[self.PriceTF.text doubleValue]*_usdRate];
                            self.heightConstant.constant=45;
                        }else{
                            self.heightConstant.constant=0;
                            self.PriceTF.text=LocalizationKey(@"Optimal");
                        }
                        if (model.change <0) {
                            self.nowPrice.textColor=RedColor;
                        }else{
                            self.nowPrice.textColor=GreenColor;
                        }
                        if ([YLUserInfo isLogIn]) {
                            if (!_IsSell) {
                                [self getBasewalletwithcoin:[coinArray lastObject]];
                            }else{
                                [self getCoinwalletwithcoin:[coinArray firstObject]];
                            }
                        }else{
                            
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
#pragma mark-切换币种
-(void)reloadShowData:(NSNotification *)notif{
    
    //    self.navigationItem.title=[NSString stringWithFormat:@"%@/%@",notif.userInfo[@"object"],notif.userInfo[@"base"]];
    self.toptitlelabel.text=[NSString stringWithFormat:@"%@/%@",notif.userInfo[@"object"],notif.userInfo[@"base"]];
    [self getSingleAccuracy:[marketManager shareInstance].symbol];
    _baseCoinName=notif.userInfo[@"base"];
    _ObjectCoinName=notif.userInfo[@"object"];
    [self.slider setIndex:0 animated:NO];
    self.AmountTF.text=@"";
    self.TradeNumber.text=[NSString stringWithFormat:@"%@--",LocalizationKey(@"entrustment")];
    if (!_IsMarketprice) {
        //限价
        self.objectCoin.text=_ObjectCoinName;
    }else{
        //市价
        if (!_IsSell) {
            self.objectCoin.text=_baseCoinName;
        }else{
            self.objectCoin.text=_ObjectCoinName;
        }
    }
    NSDictionary*dic;
    if ([YLUserInfo isLogIn]) {
        dic=[NSDictionary dictionaryWithObjectsAndKeys:[marketManager shareInstance].symbol,@"symbol",[YLUserInfo shareUserInfo].ID,@"uid",nil];
    }else{
        dic=[NSDictionary dictionaryWithObjectsAndKeys:[marketManager shareInstance].symbol,@"symbol",nil];
    }
    [[SocketManager share] sendMsgWithLength:SOCKETREQUEST_LENGTH withsequenceId:0 withcmd:SUBSCRIBE_EXCHANGE_TRADE withVersion:COMMANDS_VERSION withRequestId: 0 withbody:dic];
    [SocketManager share].delegate=self;
    NSString*kind=notif.userInfo[@"kind"];
    if ([kind isEqualToString:@"buy"]) {
        
        //买入
        UIButton*buyBtn=(UIButton*)[self.view viewWithTag:100];
        [self touchEvents:buyBtn];
        
    }else if ([kind isEqualToString:@"sell"]){
        //卖出
        UIButton*sellBtn=(UIButton*)[self.view viewWithTag:101];
        [self touchEvents:sellBtn];
    }else{
        if ([YLUserInfo isLogIn]) {
            [self getCommissionData:[marketManager shareInstance].symbol];
            [self getHistoryCommissionData];
        }
    }
    [self getPersonAllCollection];
}
#pragma mark-查询当前委托
-(void)getCommissionData:(NSString*)symbol{
    [self.contentArr removeAllObjects];
    [TradeNetManager Querythecurrentdelegatesymbol:symbol withpageNo:1 withpageSize:10 CompleteHandle:^(id resPonseObj, int code) {
        NSLog(@"当前委托 --- %@",resPonseObj);
        if (code) {
            if (resPonseObj[@"content"]) {
                NSArray*contentArray=resPonseObj[@"content"];
                if (contentArray.count==0) {
                    [self.entrusttableView reloadData];
                    if ([YLUserInfo isLogIn]) {
                        if (!self.isHistory) {
                            self.noDataLabel.hidden=NO;
                        }
                        
                    }
                    return ;
                }
                if (!self.isHistory) {
                    self.noDataLabel.hidden=YES;
                }
                for (int i=0; i<contentArray.count; i++) {
                    commissionModel*model = [commissionModel mj_objectWithKeyValues:contentArray[i]];
                    [self.contentArr addObject:model];
                }
                self.viewheights.constant = [self getviewheightspce];
                [self.entrusttableView reloadData];
                
                
            }else if ([resPonseObj[@"code"] intValue]==4000){
                [YLUserInfo logout];
            }
            else{
                [self.view makeToast:resPonseObj[MESSAGE] duration:1.5 position:CSToastPositionCenter];
            }
        }else{
            [self.view makeToast:LocalizationKey(@"noNetworkStatus") duration:1.5 position:CSToastPositionCenter];
        }
    }];
}

#pragma mark-查询历史委托
-(void)getHistoryCommissionData{
    [self.hisdataArr removeAllObjects];
    
    [TradeNetManager historyEntrustForParam:@{@"pageNo":@"1", @"pageSize":@"10", @"symbol":[marketManager shareInstance].symbol} CompleteHandle:^(id resPonseObj, int code) {
        NSLog(@"历史委托 --- %@",resPonseObj);
        if (code) {
            if ([resPonseObj[@"code"] integerValue] == 0) {
                
                NSArray *dataArr = [MyEntrustInfoModel mj_objectArrayWithKeyValuesArray:resPonseObj[@"content"]];
                
                if (dataArr.count==0) {
                    [self.entrusttableView reloadData];
                    if ([YLUserInfo isLogIn]) {
                        if (self.isHistory) {
                            self.noDataLabel.hidden=NO;
                        }
                    }
                    return ;
                }
                if (self.isHistory) {
                    self.noDataLabel.hidden=YES;
                }
                for (MyEntrustInfoModel *model in dataArr) {
                    [self.hisdataArr addObject:model];
                }
                self.viewheights.constant = [self getviewheightspce];
                
                [self.entrusttableView reloadData];
                
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

//就是滑动高度
-(CGFloat)getviewheightspce{
    if (self.isHistory) {
        return 516 + (self.hisdataArr.count > 0 ? 185 * self.hisdataArr.count : 133);
    }else{
        return 516 + (self.contentArr.count > 0 ? 115 * self.contentArr.count : 133);
        
    }
}
#pragma mark-查询盘口信息
-(void)getData:(NSString*)symbol{
    allDepthsellAmount=0;
    allDepthbuyAmount=0;
    [EasyShowLodingView showLodingText:LocalizationKey(@"loading")];
    [TradeNetManager getexchangeplate:symbol CompleteHandle:^(id resPonseObj, int code) {
        NSLog(@"查询盘口信息 ---- %@",resPonseObj);
        [EasyShowLodingView hidenLoding];
        if (code) {
            [self.askcontentArr removeAllObjects];
            [self.bidcontentArr removeAllObjects];
            if (resPonseObj) {
                NSArray*bidArray=resPonseObj[@"bid"];//买入
                for (int i=0; i<bidArray.count; i++) {
                    plateModel*model = [plateModel mj_objectWithKeyValues:bidArray[i]];
                    [self.bidcontentArr addObject:model];
                }
                
                for (int i=0;i< (self.bidcontentArr.count < Handicap ? self.bidcontentArr.count : Handicap);i++) {
                    plateModel*model=self.bidcontentArr[i];
                    allDepthbuyAmount=allDepthbuyAmount+model.amount;
                    model.totalAmount=allDepthbuyAmount;
                }
                if (self.bidcontentArr.count>=Handicap) {
                    self.bidcontentArr = [NSMutableArray arrayWithArray:[self.bidcontentArr subarrayWithRange:NSMakeRange(0, Handicap)]];
                }else{
                    int amount=Handicap-(int)self.bidcontentArr.count;
                    for (int i=0; i<amount; i++) {
                        plateModel*model=[[plateModel alloc]init];
                        model.price=-1;
                        model.amount=-1;
                        model.totalAmount=-1;
                        [self.bidcontentArr insertObject:model atIndex:self.bidcontentArr.count];
                    }
                }
                NSArray*askArray=resPonseObj[@"ask"];//卖出
                for (int i=0; i<askArray.count; i++) {
                    plateModel*model = [plateModel mj_objectWithKeyValues:askArray[i]];
                    [self.askcontentArr addObject:model];
                }
                
                for (int i=0;i< (self.askcontentArr.count < Handicap ? self.askcontentArr.count : Handicap);i++) {
                    plateModel*model=self.askcontentArr[i];
                    allDepthsellAmount=allDepthsellAmount+model.amount;
                    model.totalAmount=allDepthsellAmount;
                }
                if (self.askcontentArr.count<Handicap) {
                    int amount=Handicap-(int)self.askcontentArr.count;
                    for (int i=0; i<amount; i++) {
                        plateModel*model=[[plateModel alloc]init];
                        model.price=-1;
                        model.amount=-1;
                        model.totalAmount=-1;
                        [self.askcontentArr insertObject:model atIndex:self.askcontentArr.count];
                    }
                }else{
                    self.askcontentArr = [NSMutableArray arrayWithArray:[self.askcontentArr subarrayWithRange:NSMakeRange(0, Handicap)]];
                }
                [self.asktableView reloadData];
                [self.bidtableView reloadData];
            }else{
                [self.view makeToast:resPonseObj[MESSAGE] duration:1.5 position:CSToastPositionCenter];
            }
        }else{
            [self.view makeToast:LocalizationKey(@"noNetworkStatus") duration:1.5 position:CSToastPositionCenter];
        }
    }];
}
#pragma mark-获取单个交易对的精确度
-(void)getSingleAccuracy:(NSString*)symbol{
    [TradeNetManager getSingleSymbol:symbol CompleteHandle:^(id resPonseObj, int code) {
        if ([resPonseObj isKindOfClass:[NSDictionary class]]) {
            _baseCoinScale=[resPonseObj[@"baseCoinScale"]intValue];
            _coinScale=[resPonseObj[@"coinScale"] intValue];
            [self getAllCoinData:[marketManager shareInstance].symbol];//币种行情
            [self getData:[marketManager shareInstance].symbol];//盘口信息
        }
    }];
    
}
- (IBAction)goLogin:(UIButton *)sender {
    if(![YLUserInfo isLogIn]){
        [self showLoginViewController];
    }
}
#pragma mark-编辑输入框
- (IBAction)ValueChange:(UITextField *)sender {
    if (!_IsMarketprice) {
        self.TradeNumber.text=[NSString stringWithFormat:@"%@ %@%@",LocalizationKey(@"entrustment"),[ToolUtil stringFromNumber:[self.PriceTF.text doubleValue]*[self.AmountTF.text doubleValue] withlimit:_baseCoinScale],_baseCoinName];
//        NSString*cnyRate= [((AppDelegate*)[UIApplication sharedApplication].delegate).CNYRate stringValue];
        self.CNYPrice.text=[NSString stringWithFormat:@"≈%.2f USD",[self.PriceTF.text doubleValue]*_usdRate];
    }
}
#pragma mark-UITextFieldDelegate
- (BOOL)textField:(UITextField *)textField shouldChangeCharactersInRange:(NSRange)range replacementString:(NSString *)string
{
    if (textField.tag==1) {
        //委托价格框
        NSMutableString * futureString = [NSMutableString stringWithString:textField.text];
        [futureString  insertString:string atIndex:range.location];
        NSInteger flag=0;
        const NSInteger limited = _baseCoinScale;//小数点后需要限制的个数
        for (int i = (int)futureString.length-1; i>=0; i--) {
            
            if ([futureString characterAtIndex:i] == '.') {
                if (flag > limited) {
                    return NO;
                }
                break;
            }
            flag++;
        }
        return YES;
    }
    else{
        //委托数量框
        if (_IsMarketprice) {
            if (!_IsSell) {
                //市价买入
                NSMutableString * futureString = [NSMutableString stringWithString:textField.text];
                [futureString  insertString:string atIndex:range.location];
                NSInteger flag=0;
                const NSInteger limited = _baseCoinScale;//小数点后需要限制的个数
                for (int i = (int)futureString.length-1; i>=0; i--) {
                    if ([futureString characterAtIndex:i] == '.') {
                        if (flag > limited) {
                            return NO;
                        }
                        break;
                    }
                    flag++;
                }
                return YES;
            }else{
                //市价卖出
                NSMutableString * futureString = [NSMutableString stringWithString:textField.text];
                [futureString  insertString:string atIndex:range.location];
                NSInteger flag=0;
                const NSInteger limited = _coinScale;//小数点后需要限制的个数
                for (int i = (int)futureString.length-1; i>=0; i--) {
                    if ([futureString characterAtIndex:i] == '.') {
                        if (flag > limited) {
                            return NO;
                        }
                        break;
                    }
                    flag++;
                }
                return YES;
                
            }
        }
        else{
            //限价买入或卖出
            NSMutableString * futureString = [NSMutableString stringWithString:textField.text];
            [futureString  insertString:string atIndex:range.location];
            NSInteger flag=0;
            const NSInteger limited = _coinScale;//小数点后需要限制的个数
            for (int i = (int)futureString.length-1; i>=0; i--) {
                if ([futureString characterAtIndex:i] == '.') {
                    if (flag > limited) {
                        return NO;
                    }
                    break;
                }
                flag++;
            }
            return YES;
            
        }
    }
}

#pragma mark-查询base钱包情况
-(void)getBasewalletwithcoin:(NSString*)coin {
    [TradeNetManager getwallettWithcoin:coin CompleteHandle:^(id resPonseObj, int code) {
        if (code) {
            if ([resPonseObj[@"code"] integerValue] == 0) {
                NSDictionary*dict=resPonseObj[@"data"];
                self.Useable.text=[NSString stringWithFormat:@"%@%@ %@",LocalizationKey(@"usabel"),[ToolUtil stringFromNumber:[dict[@"balance"] doubleValue] withlimit:_baseCoinScale],coin];
                if (!_IsMarketprice) {
                    //限价
                    NSArray *coinArray = [[marketManager shareInstance].symbol componentsSeparatedByString:@"/"];
                    NSString *coinStr = [coinArray firstObject];
                    self.sliderMaxValue.text=[NSString stringWithFormat:@"%@ %@",[ToolUtil stringFromNumber:[dict[@"balance"] doubleValue]/[self.PriceTF.text doubleValue] withlimit:_baseCoinScale],coinStr];
                    self.priceLimitBuy = dict[@"balance"];
                }else{
                    self.sliderMaxValue.text=[NSString stringWithFormat:@"%@ %@",[ToolUtil stringFromNumber:[dict[@"balance"] doubleValue] withlimit:_baseCoinScale],coin];
                }
            }else{
                [self.view makeToast:resPonseObj[MESSAGE] duration:1.5 position:CSToastPositionCenter];
            }
        }else{
            [self.view makeToast:LocalizationKey(@"noNetworkStatus") duration:1.5 position:CSToastPositionCenter];
        }
    }];
}
#pragma mark-查询coin钱包情况
-(void)getCoinwalletwithcoin:(NSString*)coin {
    [TradeNetManager getwallettWithcoin:coin CompleteHandle:^(id resPonseObj, int code) {
        NSLog(@"查询coin钱包情况 --- %@",resPonseObj);
        if (code) {
            if ([resPonseObj[@"code"] integerValue] == 0) {
                NSDictionary*dict=resPonseObj[@"data"];
                self.Useable.text=[NSString stringWithFormat:@"%@%@ %@",LocalizationKey(@"usabelSell"),[ToolUtil stringFromNumber:[dict[@"balance"] doubleValue] withlimit:_baseCoinScale],coin];
                self.sliderMaxValue.text=[NSString stringWithFormat:@"%@ %@",[ToolUtil stringFromNumber:[dict[@"balance"] doubleValue] withlimit:_baseCoinScale],coin];
            }
            else if ([resPonseObj[@"code"] integerValue] ==4000){
                // [ShowLoGinVC showLoginVc:self withTipMessage:resPonseObj[MESSAGE]];
                [YLUserInfo logout];
            }
            else{
                [self.view makeToast:resPonseObj[MESSAGE] duration:1.5 position:CSToastPositionCenter];
            }
        }else{
            [self.view makeToast:LocalizationKey(@"noNetworkStatus") duration:1.5 position:CSToastPositionCenter];
        }
    }];
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
            [self.view makeToast:LocalizationKey(@"noNetworkStatus") duration:1.5 position:CSToastPositionCenter];
        }
    }];
}

kRemoveCellSeparator
- (NSMutableArray *)contentArr
{
    if (!_contentArr) {
        _contentArr = [NSMutableArray array];
    }
    return _contentArr;
}
#pragma mark - SocketDelegate Delegate
- (void)delegateSocket:(GCDAsyncSocket *)sock didReadData:(NSData *)data withTag:(long)tag{
    
    NSData *endData = [data subdataWithRange:NSMakeRange(SOCKETRESPONSE_LENGTH, data.length -SOCKETRESPONSE_LENGTH)];
    NSString *endStr = [[NSString alloc] initWithData:endData encoding:NSUTF8StringEncoding];
    NSData *cmdData = [data subdataWithRange:NSMakeRange(12,2)];
    uint16_t cmd = [SocketUtils uint16FromBytes:cmdData];
    //缩略行情
    if (cmd==PUSH_SYMBOL_THUMB) {
        if (endStr) {
            NSDictionary*dic=[SocketUtils dictionaryWithJsonString:endStr];
            //            NSLog(@"盘口信息-- PUSH_SYMBOL_THUMB--%@",dic);
            symbolModel*model = [symbolModel mj_objectWithKeyValues:dic];
            if ([model.symbol isEqualToString:[marketManager shareInstance].symbol]) {
                self.nowPrice.text = [NSString stringWithFormat:@"%@",[ToolUtil stringFromNumber:[model.close doubleValue] withlimit:_baseCoinScale]];
                NSDecimalNumber *close = [NSDecimalNumber decimalNumberWithDecimal:[model.close decimalValue]];
                NSDecimalNumber *baseUsdRate = [NSDecimalNumber decimalNumberWithDecimal:[model.baseUsdRate decimalValue]];
                self.nowCNY.text=[NSString stringWithFormat:@"≈%.2f USD",[[close decimalNumberByMultiplyingBy:baseUsdRate]  doubleValue]];
            }
        }
    }
    else if (cmd==PUSH_EXCHANGE_DEPTH || cmd==PUSH_EXCHANGE_PLATE)
    {
        
        NSDictionary*dic=[SocketUtils dictionaryWithJsonString:endStr];
        NSString *symbolstr = dic[@"symbol"];
        if (![symbolstr isEqualToString:[marketManager shareInstance].symbol]) {
            return;
        }
        //        NSLog(@"盘口信息--PUSH_EXCHANGE_PLATE--%@",dic);
        
        if ([dic[@"direction"] isEqualToString:@"SELL"]) {
            [self.askcontentArr removeAllObjects];
            allDepthsellAmount=0;
            
            //卖
            NSArray*askArray=dic[@"items"];
            for (int i=0; i<askArray.count; i++) {
                plateModel*model = [plateModel mj_objectWithKeyValues:askArray[i]];
                [self.askcontentArr addObject:model];
            }
            for (int i=0;i< (self.askcontentArr.count < Handicap ? self.askcontentArr.count : Handicap);i++) {
                plateModel*model=self.askcontentArr[i];
                allDepthsellAmount=allDepthsellAmount+model.amount;
                model.totalAmount=allDepthsellAmount;
            }
            if (self.askcontentArr.count<Handicap) {
                int amount=Handicap-(int)self.askcontentArr.count;
                for (int i=0; i<amount; i++) {
                    plateModel*model=[[plateModel alloc]init];
                    model.price=-1;
                    model.amount=-1;
                    model.totalAmount=-1;
                    [self.askcontentArr insertObject:model atIndex:self.askcontentArr.count];
                }
            }else{
                self.askcontentArr = [NSMutableArray arrayWithArray:[self.askcontentArr subarrayWithRange:NSMakeRange(0, Handicap)]];
            }
            [self.asktableView reloadData];
            
        }else{
            [self.bidcontentArr removeAllObjects];
            allDepthbuyAmount=0;
            
            NSArray*bidArray=dic[@"items"];//买入
            for (int i=0; i<bidArray.count; i++) {
                plateModel*model = [plateModel mj_objectWithKeyValues:bidArray[i]];
                [self.bidcontentArr addObject:model];
            }
            for (int i=0;i< (self.bidcontentArr.count < Handicap ? self.bidcontentArr.count : Handicap);i++) {
                plateModel*model=self.bidcontentArr[i];
                allDepthbuyAmount=allDepthbuyAmount+model.amount;
                model.totalAmount=allDepthbuyAmount;
            }
            
            if (self.bidcontentArr.count>=Handicap) {
                self.bidcontentArr = [NSMutableArray arrayWithArray:[self.bidcontentArr subarrayWithRange:NSMakeRange(0, Handicap)]];
            }else{
                int amount=Handicap-(int)self.askcontentArr.count;
                for (int i=0; i<amount; i++) {
                    plateModel*model=[[plateModel alloc]init];
                    model.price=-1;
                    model.amount=-1;
                    model.totalAmount=-1;
                    [self.askcontentArr insertObject:model atIndex:self.askcontentArr.count];
                }
            }
            [self.bidtableView reloadData];
        }
    }else if (cmd==PUSH_EXCHANGE_ORDER_COMPLETED||cmd==PUSH_EXCHANGE_ORDER_CANCELED){
        //当前委托数据完成或取消
        //        NSDictionary*dic=[SocketUtils dictionaryWithJsonString:endStr];
        //        commissionModel*model = [commissionModel mj_objectWithKeyValues:dic];
        //        [self.contentArr enumerateObjectsUsingBlock:^(commissionModel* obj, NSUInteger idx, BOOL * _Nonnull stop) {
        //            if ([obj.orderId isEqualToString:model.orderId]) {
        //                [self.contentArr removeObject:obj];
        //                 *stop = YES;
        //                [self.entrusttableView reloadData];
        //            }
        //        }];
        [self getCommissionData:[marketManager shareInstance].symbol];
        [self getHistoryCommissionData];
    }else if (cmd==PUSH_EXCHANGE_ORDER_TRADE){
        //当前委托数据变化
        //        NSDictionary*dic=[SocketUtils dictionaryWithJsonString:endStr];
        //        commissionModel*model = [commissionModel mj_objectWithKeyValues:dic];
        //        [self.contentArr enumerateObjectsUsingBlock:^(commissionModel* obj, NSUInteger idx, BOOL * _Nonnull stop) {
        //            if ([obj.orderId isEqualToString:model.orderId]) {
        //                [self.contentArr replaceObjectAtIndex:idx withObject:model];
        //                *stop = YES;
        //                [self.entrusttableView reloadData];
        //            }
        //        }];
        [self getCommissionData:[marketManager shareInstance].symbol];
        [self getHistoryCommissionData];
        
    }else{
        
        
    }
    //    NSLog(@"交易消息-%@--%d",endStr,cmd);
}

-(void)viewWillDisappear:(BOOL)animated{
    [super viewWillDisappear:YES];
    NSDictionary*dic;
    if ([YLUserInfo isLogIn]) {
        dic=[NSDictionary dictionaryWithObjectsAndKeys:[marketManager shareInstance].symbol,@"symbol",[YLUserInfo shareUserInfo].ID,@"uid",nil];
    }else{
        dic=[NSDictionary dictionaryWithObjectsAndKeys:[marketManager shareInstance].symbol,@"symbol",nil];
    }
    [[SocketManager share] sendMsgWithLength:SOCKETREQUEST_LENGTH withsequenceId:0 withcmd:UNSUBSCRIBE_SYMBOL_THUMB withVersion:COMMANDS_VERSION withRequestId: 0 withbody:nil];
    [[SocketManager share] sendMsgWithLength:SOCKETREQUEST_LENGTH withsequenceId:0 withcmd:UNSUBSCRIBE_EXCHANGE_TRADE withVersion:COMMANDS_VERSION withRequestId: 0 withbody:dic];
    [SocketManager share].delegate = nil;
    
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
