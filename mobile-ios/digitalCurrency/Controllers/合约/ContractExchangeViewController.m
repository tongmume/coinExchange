//
//  ContractExchangeViewController.m
//  digitalCurrency
//
//  Created by ios on 2020/9/15.
//  Copyright © 2020 BIZZAN. All rights reserved.
//

#import "ContractExchangeViewController.h"
#import "marketManager.h"
#import "KchatViewController.h"
#import "symbolModel.h"
#import "UIView+LLXAlertPop.h"
#import "HomeNetManager.h"
#import "plateModel.h"
#import "AppDelegate.h"
#import "StepSlider.h"
#import "CustomContraclItem.h"
#import "tradeCell.h"
#import "LeveragesView.h"
#import "ContractLeftMenuViewController.h"
#import "ContractHoldOrderTableViewCell.h"
#import "ContractCurrtentEntrustTableViewCell.h"
#import "GestureRecognizerScrollView.h"
#import "ContractHistoryOrderViewController.h"
#import "ContractRefreshModel.h"
#import "ConatractCurrentEntrustModel.h"
#import "MineNetManager.h"
#import "ContractExchangeManager.h"
#import <objc/runtime.h>

#import "TouchBottomTableView.h"
#import "ContractPingView.h"

#define Handicap 5  //买入卖出显示数量
#define PostionCellHeight 160
#define CurrtentEntrustCellHeight 150
#define BottomOffsetY 10  //父scrollview 移动到下视图内的对应位置

@interface ContractExchangeViewController ()<StepSliderDelegate,UITableViewDelegate,UITableViewDataSource,UIScrollViewDelegate,ContractExchangeSocketManagerDelegate>{
    
    NSString *_symbol; //币种
    BOOL  _isOpen; //是否为开仓或平仓类型
    NSString *_leveragebuyStr; //购买多杠杆选择数据
    NSString *_leveragesellStr; //买空杠杆选择数据
    NSInteger _selectentrustIndex; //委托模式选择
    NSInteger _directionType; // 买卖类型
    int  _baseCoinScale;  //标准为USDT的计算保留小数点
    int  _coinScale;  //当前选择币种保留的小数位
    NSInteger _targetPatternType; //全仓或者逐仓 模式类型
    NSString *_currentCionPrice; //当前币种相对USDT价格
    
    BOOL _isinitFrist; //判断是否实例化是以避免重复调用数据接口
    
    BOOL _isentrustType;  // 0是当前持仓。1当前委托
    
    BOOL _istableScoll; //判断是否下面tableview在移动
    
    CGFloat _offsetY;
    
    NSDateFormatter *_formatter;
}

@property (nonatomic,retain) dispatch_source_t timer;

@property (nonatomic, strong) UIButton *shareBtn;

@property (nonatomic, strong) ContractLeftMenuViewController *menu;

@property (nonatomic, strong) UILabel *topTitlelabel;

@property (strong, nonatomic) GestureRecognizerScrollView *myscollView;

@property (nonatomic, strong) UIView *leftBackView;

@property (nonatomic, strong) UIView *rightBackView;

@property (nonatomic, strong) UIView *titleMenuView;

@property (nonatomic, strong) UIView *bottomBackView;

//全仓模式
@property (nonatomic, strong) UILabel *patternlabel;

@property (nonatomic, strong) UIButton *duobtn;

@property (nonatomic, strong) UIButton *kongbtn;

@property (nonatomic, strong) CustomContraclItem *leftcontraclItem;

@property (nonatomic, strong) CustomContraclItem *rightcontraclItem;

@property (nonatomic, strong) UITextField *inputPriceTextField;

@property (nonatomic, strong) UIButton *plusBtn;

@property (nonatomic, strong) UIButton *reduceBtn;

@property (nonatomic, strong) UITextField *inputNumberTextField;

@property (nonatomic, strong) UILabel *huilvlabel;

@property (strong, nonatomic) StepSlider *slider;

@property (nonatomic, strong) UILabel *keyonglabel;

@property (nonatomic, strong) UILabel *znumberslabel;

@property (nonatomic, strong) UILabel *znumberTiplabel;

@property (nonatomic, strong) UIButton *buySellbtn;

@property (strong, nonatomic)  UITableView *asktableView;//卖出
@property (strong, nonatomic)  UITableView *bidtableView;//买入
@property (nonatomic, strong) NSMutableArray *askcontentArr;//卖出数组
@property (nonatomic, strong) NSMutableArray *bidcontentArr;//买入数组
@property (nonatomic, strong) UILabel *rightnumberlabel;
@property (nonatomic, strong) UILabel *rightNewPricelabel;

@property (nonatomic, strong) UILabel *rightNewCnylabel;

@property (strong, nonatomic)  TouchBottomTableView *bottomtableView;//底部持仓委托列表

@property (nonatomic, strong) UILabel *noDatalabel;

@property (nonatomic, strong) UIButton *loginbtn;

@property (nonatomic, strong) UIView *toplineview;

@property (nonatomic, strong) UIView *titlemenulineview;

@property (nonatomic, strong) NSMutableArray *entrustArray; //委托数组

@property (nonatomic, strong) NSMutableArray *currentArray; //当前持仓数组

@property (nonatomic, strong) NSDictionary *symbolInfo; //合约币种详情

@property (nonatomic, strong) NSDictionary *coinInfo; //合约币种账户信息


@property (nonatomic, strong) LeveragesView *leveragesView;

@property (nonatomic, assign) NSInteger pageNo;

@property (nonatomic, strong) UIView *entrustPriceBackView;

@property (nonatomic, strong) UITextField *entrustPriceTextField;

@property (nonatomic, strong) UIView *priceBackView;

@property (nonatomic, strong) UIButton *kaibtn;

@property (nonatomic, strong) UIButton *pingbtn;

@property (nonatomic, strong) UILabel *lefttiplabel;

@property (nonatomic, strong) UILabel *zrightlabel;

@property (nonatomic, strong) UILabel *leftpricelabel;

@property (nonatomic, strong) UIButton *menubtn;

@property (nonatomic, strong) UIButton *menbutbn2;

@property (nonatomic, strong) UIButton *menubtn3;

@property (nonatomic,strong) NSString *rate;


@end

@implementation ContractExchangeViewController

-(NSMutableArray *)currentArray {
    
    if (!_currentArray) {
        _currentArray=[NSMutableArray array];
    }
    return _currentArray;
}


-(NSMutableArray *)entrustArray{
    
    if (!_entrustArray) {
        _entrustArray=[NSMutableArray array];
    }
    return _entrustArray;
}

-(NSMutableArray *)askcontentArr {
    
    if (!_askcontentArr) {
        _askcontentArr =[NSMutableArray array];
    }
    
    return _askcontentArr;
}

-(NSMutableArray *)bidcontentArr {
    
    if (!_bidcontentArr) {
        _bidcontentArr =[NSMutableArray array];
    }
    return _bidcontentArr;
}

-(UILabel *)topTitlelabel{
    
    if (!_topTitlelabel) {
        _topTitlelabel=[[UILabel alloc]init];
        _topTitlelabel.font=[UIFont fontWithName:@"PingFangSC-Semibold" size:18];
        _topTitlelabel.textAlignment=NSTextAlignmentLeft;
        _topTitlelabel.textColor=VCBackgroundColor;
        _topTitlelabel.frame=CGRectMake(65,0,SCREEN_WIDTH_S-105,30);
        self.navigationItem.titleView=_topTitlelabel;
    }
    return _topTitlelabel;
}

- (UIScrollView *)myscollView{
    
    if (!_myscollView) {
        _myscollView=[[GestureRecognizerScrollView alloc]init];
        _myscollView.backgroundColor=mainColor;
        _myscollView.delegate = self;
        _myscollView.alwaysBounceVertical=NO;
        _myscollView.showsVerticalScrollIndicator = NO;
        _myscollView.showsHorizontalScrollIndicator = NO;
        [self.view addSubview:_myscollView];
        
    }
    return _myscollView;
}


-(UIView *)leftBackView{
    
    if (!_leftBackView) {
        _leftBackView=[[UIView alloc]init];
        _leftBackView.frame=CGRectMake(10,41,195,375);
        [self.myscollView addSubview:_leftBackView];
    }
    return _leftBackView;;
}

-(UIView *)rightBackView{
    
    if (!_rightBackView) {
        _rightBackView=[[UIView alloc]init];
        _rightBackView.frame=CGRectMake(self.leftBackView.frame.origin.x+self.leftBackView.frame.size.width+10,self.leftBackView.frame.origin.y,SCREEN_WIDTH_S-self.leftBackView.frame.size.width-self.leftBackView.frame.origin.x-10,self.leftBackView.frame.size.height);
        [self.myscollView addSubview:_rightBackView];
    }
    return _rightBackView;
}

- (UIView*)titleMenuView{
    
    if (!_titleMenuView) {
        
        _titleMenuView =[[UIView alloc]init];
        _titleMenuView.frame=CGRectMake(0,self.leftBackView.frame.origin.y+self.leftBackView.frame.size.height,SCREEN_WIDTH_S,51);
        
        [self.myscollView addSubview:_titleMenuView];
        
    }
    return _titleMenuView;
}

-(UIView *)bottomBackView {
    
    
    if (!_bottomBackView) {
        
        _bottomBackView=[[UIView alloc]init];
        _bottomBackView.frame=CGRectMake(0,self.titleMenuView.frame.origin.y+self.titleMenuView.frame.size.height,SCREEN_WIDTH_S,80);
        [self.myscollView addSubview:_bottomBackView];
        
    }
    
    return _bottomBackView;;
}


- (UIButton *)loginbtn{
    
    
    if (!_loginbtn) {
        _loginbtn=[UIButton buttonWithType:UIButtonTypeCustom];
        _loginbtn.backgroundColor=kRGBColor(27,157, 66);
        [_loginbtn setTitle: LocalizationKey(@"userName") forState:UIControlStateNormal];
        _loginbtn.titleLabel.font=[UIFont systemFontOfSize:15];
        _loginbtn.titleLabel.textColor=[UIColor whiteColor];
        _loginbtn.layer.cornerRadius=5;
        _loginbtn.layer.masksToBounds=YES;
        _loginbtn.tag=112;
        [_loginbtn addTarget:self action:@selector(touchMenuclick:) forControlEvents:UIControlEventTouchUpInside];
        [self.bottomBackView addSubview:_loginbtn];
    }
    return _loginbtn;
}

-(UILabel *)noDatalabel {
    
    if (!_noDatalabel) {
        _noDatalabel=[[UILabel alloc]init];
        _noDatalabel.textAlignment=NSTextAlignmentCenter;
        _noDatalabel.backgroundColor=mainColor;
        _noDatalabel.font=[UIFont fontWithName:@"PingFangSC" size:14.0 * kWindowWHOne];
        _noDatalabel.text=LocalizationKey(@"noDada");
        _noDatalabel.textColor=AppTextColor_Level_3;
        [self.bottomBackView addSubview:_noDatalabel];
    }
    return _noDatalabel;;
}

-(UIView *)toplineview {
    
    if (!_toplineview) {
        _toplineview=[[UIView alloc]initWithFrame:CGRectMake(0,0,SCREEN_WIDTH_S/2,2)];
        _toplineview.backgroundColor=baseColor;
        [_myscollView addSubview:_toplineview];
    }
    return _toplineview;
}

-(UIView *)titlemenulineview {
    
    if (!_titlemenulineview) {
        _titlemenulineview=[[UIView alloc]initWithFrame:CGRectMake(0,0,80,1)];
        _titlemenulineview.backgroundColor=baseColor;
        
        [self.titleMenuView addSubview:_titlemenulineview];
    }
    return _titlemenulineview;
}

- (void)viewDidLoad {
    
    [super viewDidLoad];
    // Do any additional setup after loading the view.
    
    NSDateFormatter *formatter = [NSDateFormatter new];
    formatter.dateFormat = @"MM-dd HH:mm";
    _formatter=formatter;
    //初始化数据
    _symbol=@"BTC/USDT";
    _leveragebuyStr=@"10";
    _leveragesellStr=@"10";
    _selectentrustIndex=1;
    _isOpen=YES;
    _targetPatternType=0;
    _baseCoinScale=2;
    _coinScale=2;
    _directionType=0;
    self.pageNo=1;
    
    self.view.backgroundColor=mainColor;
    
    [self LeftsetupNavgationItemWithpictureName:@"tradeLeft"];
    
    [[NSNotificationCenter defaultCenter] addObserver:self selector:@selector(reloadShowData:)name:CURRENTSELECTED_SYMBOL object:nil];
    
    UIButton *tradeButton = [UIButton buttonWithType:UIButtonTypeCustom];
    tradeButton.frame= CGRectMake(0, 0, 30, 30);
    [tradeButton setImage:[UIImage imageNamed:@"tradeRight"] forState:UIControlStateNormal];
    [tradeButton addTarget:self action:@selector(tradeRightBtnClick) forControlEvents:UIControlEventTouchUpInside];
    UIBarButtonItem *tradeBtn = [[UIBarButtonItem alloc] initWithCustomView:tradeButton];
    self.navigationItem.rightBarButtonItem=tradeBtn;
    
    //self.topTitlelabel.text=@"BTC/USDT 永续";
    [self.myscollView mas_makeConstraints:^(MASConstraintMaker *make) {
        
        make.left.right.mas_equalTo(0);
        
        make.top.mas_equalTo(5);
        
        make.bottom.mas_equalTo(0);
    }];
    _kaibtn=[UIButton buttonWithType:UIButtonTypeCustom];
    self.kaibtn.frame=CGRectMake(0,0,SCREEN_WIDTH_S/2,40);
    self.kaibtn.backgroundColor=mainColor;
    [self.kaibtn setTitle:LocalizationKey(@"text_open") forState:UIControlStateNormal];
    self.kaibtn.titleLabel.font=[UIFont fontWithName:@"PingFangSC-Semibold" size:15.0 * kWindowWHOne];
    [self.kaibtn setTitleColor:VCBackgroundColor forState:UIControlStateNormal];
    [self.kaibtn setTitleColor:baseColor forState:UIControlStateSelected];
    self.kaibtn.tag=100;
    [self.kaibtn addTarget:self action:@selector(touchMenuclick:) forControlEvents:UIControlEventTouchUpInside];
    [_myscollView addSubview:self.kaibtn];
    //kaibtn.selected=_isOpen;
    
    _pingbtn =[UIButton buttonWithType:UIButtonTypeCustom];
    self.pingbtn.frame=CGRectMake(SCREEN_WIDTH_S/2,0,SCREEN_WIDTH_S/2,40);
    self.pingbtn.backgroundColor=mainColor;
    self.pingbtn.tag=101;
    
    [self.pingbtn setTitle:LocalizationKey(@"text_flat") forState:UIControlStateNormal];
    [self.pingbtn setTitleColor:VCBackgroundColor forState:UIControlStateNormal];
    [self.pingbtn setTitleColor:baseColor forState:UIControlStateSelected];
    self.pingbtn.titleLabel.font=[UIFont fontWithName:@"PingFangSC-Semibold" size:15.0 * kWindowWHOne];
    [self.pingbtn addTarget:self action:@selector(touchMenuclick:) forControlEvents:UIControlEventTouchUpInside];
    [_myscollView addSubview:self.pingbtn];
    UIView *line=[[UIView alloc]initWithFrame:CGRectMake(0,self.kaibtn.frame.origin.y+self.kaibtn.frame.size.height,SCREEN_WIDTH_S, 1)];
    line.backgroundColor=ViewBackgroundColor;
    [_myscollView addSubview:line];
    //self.myscollView.frame=CGRectMake(0,pingbtn.frame.origin.y+pingbtn.frame.size.height,SCREEN_WIDTH_S,SCREEN_HEIGHT_S-(pingbtn.frame.origin.y+pingbtn.frame.size.height)-kTabbarHeight-NavigationBarAdapterContentInsetTop);
    
    [self initleftView];
    [self initRightView];
    [self inintTitleMenuView];
    [self initBottomView];
    //    [self.view layoutIfNeeded];
    
    self.myscollView.contentSize=CGSizeMake(SCREEN_WIDTH_S,self.bottomBackView.origin.y+self.bottomBackView.frame.size.height);
    _isinitFrist=YES;
    
    
    //获取网络请求实例化需要的数据
    [self setChangedSymbol:_symbol];
    
    UIButton *button =_isOpen?_kaibtn:_pingbtn;
    [self touchMenuclick:button];
    
}

-(void)viewWillAppear:(BOOL)animated{
    [super viewWillAppear:YES];
    
    if (!_isinitFrist) {
        //获取合约成交数据
        // [self getLastetTradeData];
        if (self.symbolInfo&&[YLUserInfo isLogIn]) {
            NSString *stringid=self.symbolInfo[@"id"];
            [self getContractAccountDetailCoinId:stringid];
        }
        
    }else{
        _isinitFrist=NO;
    }
    
    if([YLUserInfo isLogIn]){
        self.loginbtn.hidden=YES;
        self.bottomtableView.hidden=NO;
        
        [self loadBottomViewTableViewHeight];
        
        [self getContractWalletList];
    }else{
        
        self.loginbtn.hidden=NO;
        self.bottomtableView.hidden=YES;
        self.noDatalabel.hidden=YES;
        
        CGRect rect=self.bottomBackView.frame;
        rect.size.height=80;
        self.bottomBackView.frame=rect;
    }
    //[self loadBottomViewTableViewHeight];
    //切换多语言重新文字赋值
    [self setChangedSymbol:_symbol];
    [self.kaibtn setTitle:LocalizationKey(@"text_open") forState:UIControlStateNormal];
    [self.pingbtn setTitle:LocalizationKey(@"text_flat") forState:UIControlStateNormal];
    [self.duobtn setTitle:LocalizationKey(@"text_open_many") forState:UIControlStateNormal];
    [self.kongbtn setTitle:LocalizationKey(@"text_open_null") forState:UIControlStateNormal];
    self.lefttiplabel.text=[NSString stringWithFormat:@"USDT %@",LocalizationKey(@"account")];
    self.leftcontraclItem.leftlabel.text=_selectentrustIndex==1?LocalizationKey( @"text_limit_entrust"):_selectentrustIndex==2?LocalizationKey(@"text_plan_entrust"):LocalizationKey(@"text_Market_entrust");
    self.zrightlabel.text=LocalizationKey(@"zhang");
    
    if (self.kaibtn.selected) {
        if (self.duobtn.selected) {
            [self.buySellbtn setTitle:LocalizationKey(@"buyOpenmore") forState:UIControlStateNormal];
            self.znumberTiplabel.text=LocalizationKey(@"Openmore");
        }else{
            [self.buySellbtn setTitle:LocalizationKey(@"sellOpennull") forState:UIControlStateNormal];
            self.znumberTiplabel.text=LocalizationKey(@"Opennull");
        }
    }else{
        if (self.duobtn.selected) {
            [self.buySellbtn setTitle:LocalizationKey(@"sellflatmore") forState:UIControlStateNormal];
            self.znumberTiplabel.text=LocalizationKey(@"flatmore");
        }else{
            [self.buySellbtn setTitle:LocalizationKey(@"buyflatnull") forState:UIControlStateNormal];
            self.znumberTiplabel.text=LocalizationKey(@"flatnull");
        }
    }
    
    [_menubtn setTitle:LocalizationKey(@"text_hold") forState:UIControlStateNormal];
    [_menbutbn2 setTitle:LocalizationKey(@"text_current_entrust") forState:UIControlStateNormal];
    [_menubtn3 setTitle:LocalizationKey(@"all") forState:UIControlStateNormal];
    [self.bottomtableView reloadData];
    
}

-(void)viewDidAppear:(BOOL)animated{
    
    [super viewDidAppear:animated];
    
    [self setContractSoccket];
    
}


-(void)viewWillDisappear:(BOOL)animated{
    [super viewWillDisappear:animated];
    
    [self cancelContractSocket];
}

//切换币种
- (void)setChangedSymbol:(NSString *)symbol{
    _symbol=symbol;
    self.topTitlelabel.text=[NSString stringWithFormat:@"%@%@",symbol,LocalizationKey(@"constract")];
    [self getContractSymbolInfo:symbol];
    NSDictionary*dic;
    if ([YLUserInfo isLogIn]) {
        dic=[NSDictionary dictionaryWithObjectsAndKeys:_symbol,@"symbol",[YLUserInfo shareUserInfo].ID,@"uid",nil];
    }
    else{
        dic=[NSDictionary dictionaryWithObjectsAndKeys:_symbol,@"symbol",nil];
    }
    [[ContractExchangeSocketManager share] sendMsgWithLength:SOCKETREQUEST_LENGTH withsequenceId:0 withcmd:SUBSCRIBE_EXCHANGE_TRADE_CONTRACT withVersion:COMMANDS_VERSION withRequestId: 0 withbody:dic];
}

#pragma mark-切换币种
-(void)reloadShowData:(NSNotification *)notif{
    self.topTitlelabel.text=[NSString stringWithFormat:@"%@/%@%@",notif.userInfo[@"object"],notif.userInfo[@"base"],LocalizationKey(@"constract")];
//    self.toptitlelabel.text=[NSString stringWithFormat:@"%@/%@",notif.userInfo[@"object"],notif.userInfo[@"base"]];
//    [self getSingleAccuracy:[marketManager shareInstance].symbol];
//    _baseCoinName=notif.userInfo[@"base"];
//    _ObjectCoinName=notif.userInfo[@"object"];
//    [self.slider setIndex:0 animated:NO];
//    self.AmountTF.text=@"";
//    self.TradeNumber.text=[NSString stringWithFormat:@"%@--",LocalizationKey(@"entrustment")];
//    if (!_IsMarketprice) {
//        //限价
//        self.objectCoin.text=_ObjectCoinName;
//    }else{
//        //市价
//        if (!_IsSell) {
//            self.objectCoin.text=_baseCoinName;
//        }else{
//            self.objectCoin.text=_ObjectCoinName;
//        }
//    }
    NSDictionary*dic;
    if ([YLUserInfo isLogIn]) {
        dic=[NSDictionary dictionaryWithObjectsAndKeys:_symbol,@"symbol",[YLUserInfo shareUserInfo].ID,@"uid",nil];
    }
    else{
        dic=[NSDictionary dictionaryWithObjectsAndKeys:_symbol,@"symbol",nil];
    }
    [[ContractExchangeSocketManager share] sendMsgWithLength:SOCKETREQUEST_LENGTH withsequenceId:0 withcmd:SUBSCRIBE_EXCHANGE_TRADE_CONTRACT withVersion:COMMANDS_VERSION withRequestId: 0 withbody:dic];
    [SocketManager share].delegate=self;
    NSString*kind=notif.userInfo[@"kind"];
    if ([kind isEqualToString:@"buy"]) {
        
        //买入
        UIButton*buyBtn=(UIButton*)[self.view viewWithTag:102];
        [self touchMenuclick:buyBtn];
        
    }else if ([kind isEqualToString:@"sell"]){
        //卖出
        UIButton*sellBtn=(UIButton*)[self.view viewWithTag:103];
        [self touchMenuclick:sellBtn];
    }else{
        if ([YLUserInfo isLogIn]) {
//            [self getCommissionData:[marketManager shareInstance].symbol];
//            [self getHistoryCommissionData];
        }
    }
//    [self getPersonAllCollection];
}


//修改更改布局调用系统调用方法
-(void)viewDidLayoutSubviews {
    
    [super viewDidLayoutSubviews];
}

-(void)initleftView{
    
    CGFloat width=self.leftBackView.frame.size.width;
    
    MJWeakSelf
    self.lefttiplabel=[[UILabel alloc]init];
    self.lefttiplabel.text=[NSString stringWithFormat:@"USDT %@",LocalizationKey(@"account")];
    self.lefttiplabel.font=[UIFont systemFontOfSize:12*kWindowWHOne];
    self.lefttiplabel.textColor=AppTextColor_Level_2;
    [self.leftBackView addSubview:self.lefttiplabel];
    
    [self.lefttiplabel mas_makeConstraints:^(MASConstraintMaker *make) {
        make.left.top.mas_equalTo(0);
        make.size.mas_equalTo(CGSizeMake(110,20));
    }];
    
    UIImage*arrowimg=[UIImage imageNamed:@"pullBlackImage"];
    UIImageView *imgview = [[UIImageView alloc]init];
    imgview.image=arrowimg;
    [self.leftBackView addSubview:imgview];
    
    [imgview mas_makeConstraints:^(MASConstraintMaker *make) {
        make.right.mas_equalTo(-0);
        make.centerY.equalTo(self.lefttiplabel.mas_centerY).offset(0);
        make.size.mas_equalTo(CGSizeMake(8,5));
    }];
    
    _patternlabel=[[UILabel alloc]init];
    _patternlabel.textAlignment=NSTextAlignmentRight;
    _patternlabel.font=[UIFont systemFontOfSize:12*kWindowWHOne];
    _patternlabel.textColor=AppTextColor_Level_2;
    _patternlabel.text=_targetPatternType?LocalizationKey(@"text_chase_model"):LocalizationKey(@"text_all_model");
    [self.leftBackView addSubview:_patternlabel];
    
    [_patternlabel mas_makeConstraints:^(MASConstraintMaker *make) {
        
        make.left.equalTo(self.lefttiplabel.mas_right).offset(5);
        make.right.equalTo(imgview.mas_left).offset(-1);
        make.height.mas_equalTo(self.lefttiplabel.mas_height);
        make.top.equalTo(self.lefttiplabel.mas_top);
    }];
    
    UIButton *moshibtn=[UIButton buttonWithType:UIButtonTypeCustom];
    moshibtn.tag=113;
    [moshibtn addTarget:self action:@selector(touchMenuclick:) forControlEvents:UIControlEventTouchUpInside];
    [self.leftBackView addSubview:moshibtn];
    
    [moshibtn mas_makeConstraints:^(MASConstraintMaker *make) {
        make.left.equalTo(self.lefttiplabel.mas_right).offset(5);
        make.right.mas_equalTo(0);
        make.height.mas_equalTo(self.lefttiplabel.mas_height);
        make.top.equalTo(self.lefttiplabel.mas_top);
    }];
    
    _duobtn=[UIButton buttonWithType:UIButtonTypeCustom];
    [_duobtn setTitleColor:AppTextColor_Level_2 forState:UIControlStateNormal];
    [_duobtn setTitleColor:GreenColor forState:UIControlStateSelected];
    [_duobtn setTitle:LocalizationKey(@"text_open_many") forState:UIControlStateNormal];
    [_duobtn setBackgroundImage:[UIImage imageNamed:@"kaiduo"] forState:UIControlStateNormal];
    [_duobtn setBackgroundImage:[UIImage imageNamed:@"kaiduo_green"] forState:UIControlStateSelected];
    
    _duobtn.titleLabel.font=[UIFont systemFontOfSize:14.0 * kWindowWHOne];
    _duobtn.titleLabel.textAlignment=NSTextAlignmentCenter;
    _duobtn.tag=102;
    [_duobtn addTarget:self action:@selector(touchMenuclick:) forControlEvents:UIControlEventTouchUpInside];
    [self.leftBackView addSubview:_duobtn];
    _duobtn.selected=_directionType?NO:YES;
    [_duobtn mas_makeConstraints:^(MASConstraintMaker *make) {
        
        make.left.mas_equalTo(0);
        make.top.equalTo(self.lefttiplabel.mas_bottom).offset(10);
        make.size.mas_equalTo(CGSizeMake(width/2,35));
    }];
    
    _kongbtn=[UIButton buttonWithType:UIButtonTypeCustom];
    [_kongbtn setTitleColor:AppTextColor_Level_2 forState:UIControlStateNormal];
    [_kongbtn setTitleColor:RedColor forState:UIControlStateSelected];
    [_kongbtn setTitle:LocalizationKey(@"text_open_null") forState:UIControlStateNormal];
    [_kongbtn setBackgroundImage:[UIImage imageNamed:@"kaikong_red"] forState:UIControlStateSelected];
    [_kongbtn setBackgroundImage:[UIImage imageNamed:@"kaikong"] forState:UIControlStateNormal];
    
    _kongbtn.titleLabel.font=[UIFont systemFontOfSize:14.0 * kWindowWHOne];
    _kongbtn.tag=103;
    [_kongbtn addTarget:self action:@selector(touchMenuclick:) forControlEvents:UIControlEventTouchUpInside];
    [self.leftBackView addSubview:_kongbtn];
    
    [_kongbtn mas_makeConstraints:^(MASConstraintMaker *make) {
        make.left.equalTo(weakSelf.duobtn.mas_right).offset(0);
        make.top.equalTo(weakSelf.duobtn.mas_top).offset(0);
        make.size.mas_equalTo(CGSizeMake(width/2,35));
    }];
    
    self.leftcontraclItem=[[CustomContraclItem alloc]init];
    self.leftcontraclItem.leftlabel.text=_selectentrustIndex==1?LocalizationKey( @"text_limit_entrust"):_selectentrustIndex==2?LocalizationKey(@"text_plan_entrust"):LocalizationKey(@"text_Market_entrust");
    self.leftcontraclItem.layer.borderWidth=1;
    self.leftcontraclItem.layer.borderColor=AppTextColor_E6E6E6.CGColor;
    [self.leftBackView addSubview:self.leftcontraclItem];
    self.leftcontraclItem.tag=104;
    [self.leftcontraclItem addTarget:self action:@selector(touchMenuclick:) forControlEvents:UIControlEventTouchUpInside];
    [self.leftcontraclItem mas_makeConstraints:^(MASConstraintMaker *make) {
        make.left.mas_equalTo(0);
        make.top.equalTo(weakSelf.duobtn.mas_bottom).offset(10);
        make.size.mas_equalTo(CGSizeMake((width-10)*0.7,20));
    }];
    self.rightcontraclItem=[[CustomContraclItem alloc]init];
    self.rightcontraclItem.leftlabel.text=[NSString stringWithFormat:@"%@x",_directionType?_leveragesellStr:_leveragebuyStr];
    self.rightcontraclItem.layer.borderWidth=1;
    self.rightcontraclItem.layer.borderColor=AppTextColor_E6E6E6.CGColor;
    [self.leftBackView addSubview:self.rightcontraclItem];
    self.rightcontraclItem.tag=105;
    [self.rightcontraclItem addTarget:self action:@selector(touchMenuclick:) forControlEvents:UIControlEventTouchUpInside];
    [self.rightcontraclItem mas_makeConstraints:^(MASConstraintMaker *make) {
        make.right.mas_equalTo(0);
        make.top.equalTo(weakSelf.leftcontraclItem.mas_top).offset(0);
        make.size.mas_equalTo(CGSizeMake((width-10)*0.3,20));
    }];
    
    //加减价格背景
    UIView *backview1=[[UIView alloc]init];
    backview1.layer.borderWidth=1;
    backview1.layer.borderColor=AppTextColor_E6E6E6.CGColor;
    [self.leftBackView addSubview:backview1];
    _priceBackView=backview1;
    [backview1 mas_makeConstraints:^(MASConstraintMaker *make) {
        
        make.left.right.mas_equalTo(0);
        make.top.equalTo(weakSelf.leftcontraclItem.mas_bottom).offset(10);
        make.height.mas_equalTo(35);
    }];
    
    UIView *yline=[[UIView alloc]init];
    yline.backgroundColor=AppTextColor_E6E6E6;
    [backview1 addSubview:yline];
    [yline mas_makeConstraints:^(MASConstraintMaker *make) {
        make.left.mas_equalTo((width-10)*0.7+5);
        make.top.bottom.mas_equalTo(0);
        make.width.mas_equalTo(1);
    }];
    
    _inputPriceTextField=[[UITextField alloc]init];
    _inputPriceTextField.placeholder=LocalizationKey(@"tradePrice");
    _inputPriceTextField.textColor=AppTextColor_Level_2;
    _inputPriceTextField.font=[UIFont systemFontOfSize:12.0 * kWindowWHOne];
    _inputPriceTextField.keyboardType=UIKeyboardTypeDecimalPad;
    _inputPriceTextField.textAlignment=NSTextAlignmentLeft;
    
    [backview1 addSubview:_inputPriceTextField];
    
    [_inputPriceTextField mas_makeConstraints:^(MASConstraintMaker *make) {
        make.left.mas_equalTo(5);
        make.top.mas_equalTo(7.5);
        make.height.mas_equalTo(20);
        make.right.equalTo(yline.mas_left).offset(-5);
    }];
    
    UIView *yline2=[[UIView alloc]init];
    yline2.backgroundColor=AppTextColor_E6E6E6;
    [backview1 addSubview:yline2];
    
    [yline2 mas_makeConstraints:^(MASConstraintMaker *make) {
        make.right.mas_equalTo(-(width-10)*0.3/2);
        make.top.mas_equalTo(5);
        make.bottom.mas_equalTo(-5);
        make.width.mas_equalTo(1);
    }];
    
    _plusBtn=[UIButton buttonWithType:UIButtonTypeCustom];
    [_plusBtn setImage:[UIImage imageNamed:@"add"] forState:UIControlStateNormal];
    [backview1 addSubview:_plusBtn];
    _plusBtn.tag=106;
    [_plusBtn addTarget:self action:@selector(touchMenuclick:) forControlEvents:UIControlEventTouchUpInside];
    [_plusBtn mas_makeConstraints:^(MASConstraintMaker *make) {
        make.left.equalTo(yline.mas_right).offset(5);
        make.right.equalTo(yline2.mas_left).offset(-5);
        make.top.mas_equalTo(5);
        make.bottom.mas_equalTo(-5);
    }];
    
    _reduceBtn =[UIButton buttonWithType:UIButtonTypeCustom];
    [_reduceBtn setImage:[UIImage imageNamed:@"减"] forState:UIControlStateNormal];
    [backview1 addSubview:_reduceBtn];
    _reduceBtn.tag=107;
    [_reduceBtn addTarget:self action:@selector(touchMenuclick:) forControlEvents:UIControlEventTouchUpInside];
    [_reduceBtn mas_makeConstraints:^(MASConstraintMaker *make) {
        
        make.right.mas_equalTo(-5);
        make.left.equalTo(yline2.mas_right).offset(5);
        make.top.mas_equalTo(5);
        make.bottom.mas_equalTo(-5);
    }];
    
    
    _entrustPriceBackView=[[UIView alloc]init];
    _entrustPriceBackView.layer.borderWidth=1;
    _entrustPriceBackView.layer.borderColor=AppTextColor_E6E6E6.CGColor;
    
    [self.leftBackView addSubview:_entrustPriceBackView];
    [_entrustPriceBackView mas_makeConstraints:^(MASConstraintMaker *make) {
        
        make.left.right.mas_equalTo(0);
        make.top.equalTo(backview1.mas_bottom).offset(_selectentrustIndex==2?10:0);
        make.height.mas_equalTo(_selectentrustIndex==2?35:0);
    }];
    
    
    _entrustPriceTextField=[[UITextField alloc]init];
    _entrustPriceTextField.placeholder=LocalizationKey(@"enterPrice");
    _entrustPriceTextField.textColor=AppTextColor_Level_2;
    _entrustPriceTextField.font=[UIFont systemFontOfSize:12.0 * kWindowWHOne];
    _entrustPriceTextField.keyboardType=UIKeyboardTypeDecimalPad;
    _entrustPriceTextField.textAlignment=NSTextAlignmentLeft;
    
    [_entrustPriceBackView addSubview:_entrustPriceTextField];
    _entrustPriceTextField.hidden=YES;
    [_entrustPriceTextField mas_makeConstraints:^(MASConstraintMaker *make) {
        make.left.mas_equalTo(5);
        make.top.mas_equalTo(7.5);
        make.height.mas_equalTo(20);
        make.right.mas_equalTo(-5);
    }];
    
    UIView *backview2=[[UIView alloc]init];
    backview2.layer.borderWidth=1;
    backview2.layer.borderColor=AppTextColor_E6E6E6.CGColor;
    
    [self.leftBackView addSubview:backview2];
    
    [backview2 mas_makeConstraints:^(MASConstraintMaker *make) {
        
        make.left.right.mas_equalTo(0);
        make.top.equalTo(weakSelf.entrustPriceBackView.mas_bottom).offset(10);
        make.height.mas_equalTo(35);
    }];
    
    self.zrightlabel=[[UILabel alloc]init];
    self.zrightlabel.textColor=AppTextColor_Level_3;
    self.zrightlabel.text=LocalizationKey(@"zhang");
    self.zrightlabel.font=[UIFont systemFontOfSize:12.0 * kWindowWHOne];
    self.zrightlabel.textAlignment=NSTextAlignmentRight;
    [backview2 addSubview:self.zrightlabel];
    
    [self.zrightlabel mas_makeConstraints:^(MASConstraintMaker *make) {
        make.right.mas_equalTo(-5);
        make.top.mas_equalTo(5);
        make.bottom.mas_equalTo(-5);
        make.width.mas_equalTo(30);
    }];
    
    _inputNumberTextField =[[UITextField alloc]init];
    _inputNumberTextField.keyboardType=UIKeyboardTypeNumberPad;
    _inputNumberTextField.textAlignment=NSTextAlignmentLeft;
    _inputNumberTextField.textColor=AppTextColor_Level_2;
    _inputNumberTextField.placeholder=LocalizationKey(@"amount");
    _inputNumberTextField.font=[UIFont systemFontOfSize:12.0 * kWindowWHOne];
    [backview2 addSubview:_inputNumberTextField];
    [_inputNumberTextField mas_makeConstraints:^(MASConstraintMaker *make) {
        make.left.mas_equalTo(5);
        make.right.equalTo(self.zrightlabel.mas_left).offset(-5);
        make.top.mas_equalTo(7.5);
        make.height.mas_equalTo(20);
    }];
    
    _huilvlabel=[[UILabel alloc]init];
    _huilvlabel.textAlignment=NSTextAlignmentRight;
    _huilvlabel.font=[UIFont systemFontOfSize:12.0 * kWindowWHOne];
    _huilvlabel.textColor=AppTextColor_Level_3;
    _huilvlabel.text=@"1张 = 100 USDT";
    [self.leftBackView addSubview:_huilvlabel];
    
    [_huilvlabel mas_makeConstraints:^(MASConstraintMaker *make) {
        
        make.top.equalTo(backview2.mas_bottom).offset(10);
        make.left.right.mas_equalTo(0);
        make.height.mas_equalTo(20);
    }];
    
    self.slider=[[StepSlider alloc]init];
    
    [self.leftBackView addSubview:self.slider];
    
    self.slider.labelOrientation=StepSliderTextOrientationDown;
    self.slider.labelOffset=5;
    self.slider.delegate = self;
    self.slider.trackHeight = 3;
    self.slider.trackCircleRadius = 6;
    self.slider.sliderCircleRadius = 6;
    self.slider.trackColor= AppTextColor_E6E6E6;
    self.slider.tintColor =_directionType?RedColor:GreenColor;
    self.slider.sliderCircleImage =_directionType?[UIImage imageNamed:@"circularRed"] :[UIImage imageNamed:@"circularGreen"];
    [self.slider setMaxCount:5];
    [self.slider setIndex:0 animated:YES];
    self.slider.backgroundColor = mainColor;
//    self.slider.labelColor=AppTextColor_Level_1;
    self.slider.labelFont=[UIFont systemFontOfSize:10.0 * kWindowWHOne];
    self.slider.labels=@[@"0%",@"25%",@"50%",@"75%",@"100%"];
    
    
    [self.slider mas_makeConstraints:^(MASConstraintMaker *make) {
        make.left.mas_equalTo(5);
        make.right.mas_equalTo(-5);
        make.top.equalTo(weakSelf.huilvlabel.mas_bottom).offset(10);
        make.height.mas_equalTo(40);
    }];
    
    _keyonglabel=[[UILabel alloc]init];
    _keyonglabel.textAlignment=NSTextAlignmentLeft;
    _keyonglabel.font=[UIFont systemFontOfSize:12.0 * kWindowWHOne];
    _keyonglabel.textColor=AppTextColor_Level_3;
    _keyonglabel.text=[NSString stringWithFormat:@"%@ --",LocalizationKey(@"text_used")];
    _keyonglabel.adjustsFontSizeToFitWidth=YES;
    [self.leftBackView addSubview:_keyonglabel];
    
    [_keyonglabel mas_makeConstraints:^(MASConstraintMaker *make) {
        
        make.left.mas_equalTo(0);
        make.top.equalTo(weakSelf.slider.mas_bottom).offset(10);
        make.size.mas_equalTo(CGSizeMake(width/2,20));
    }];
    
    
    _znumberslabel=[[UILabel alloc]init];
    _znumberslabel.textAlignment=NSTextAlignmentRight;
    _znumberslabel.font=[UIFont systemFontOfSize:12.0 * kWindowWHOne];
    _znumberslabel.textColor=AppTextColor_Level_3;
    _znumberslabel.text=@"0 张";
    _znumberslabel.adjustsFontSizeToFitWidth=YES;
    [self.leftBackView addSubview:_znumberslabel];
    
    [_znumberslabel mas_makeConstraints:^(MASConstraintMaker *make) {
        
        make.right.mas_equalTo(0);
        make.top.equalTo(weakSelf.keyonglabel.mas_top).offset(0);
        make.height.mas_equalTo(20);
    }];
    
    _znumberTiplabel=[[UILabel alloc]init];
    _znumberTiplabel.textAlignment=NSTextAlignmentRight;
    _znumberTiplabel.font=[UIFont systemFontOfSize:12.0 * kWindowWHOne];
    _znumberTiplabel.textColor=GreenColor;
    _znumberTiplabel.text=LocalizationKey(@"Openmore");
    [self.leftBackView addSubview:_znumberTiplabel];
    
    [_znumberTiplabel mas_makeConstraints:^(MASConstraintMaker *make) {
        
        make.right.equalTo(weakSelf.znumberslabel.mas_left).offset(0);
        make.top.equalTo(weakSelf.znumberslabel.mas_top).offset(0);
        make.size.mas_equalTo(CGSizeMake(60,20));
    }];
    
    _buySellbtn =[UIButton buttonWithType:UIButtonTypeCustom];
    [_buySellbtn setTitleColor:VCBackgroundColor forState:UIControlStateNormal];
    _buySellbtn.backgroundColor=GreenColor;
    _buySellbtn.layer.cornerRadius=5;
    _buySellbtn.layer.masksToBounds=YES;
    [_buySellbtn setTitle:LocalizationKey(@"buyOpenmore") forState:UIControlStateNormal];
    _buySellbtn.tag=108;
    [_buySellbtn addTarget:self action:@selector(touchMenuclick:) forControlEvents:UIControlEventTouchUpInside];
    [self.leftBackView addSubview:_buySellbtn];
    [_buySellbtn mas_makeConstraints:^(MASConstraintMaker *make) {
        make.left.right.mas_equalTo(0);
        make.top.equalTo(weakSelf.keyonglabel.mas_bottom).offset(15);
        make.height.mas_equalTo(40);
    }];
    
    //初始化界面样式
    UIButton *buton=_directionType?_kongbtn:_duobtn;
    
    [self touchMenuclick:buton];
}


-(void)initRightView{
    
    CGFloat width= self.rightBackView.frame.size.width;
    MJWeakSelf
    _rightnumberlabel=[[UILabel alloc]init];
    _rightnumberlabel.textAlignment=NSTextAlignmentRight;
    _rightnumberlabel.font=[UIFont systemFontOfSize:12.0 * kWindowWHOne];
    _rightnumberlabel.textColor=AppTextColor_Level_2;
    _rightnumberlabel.text= [NSString stringWithFormat:@"%@(BTC11)",LocalizationKey(@"1amount")]; //@"数量(BTC)";
    [self.rightBackView addSubview:_rightnumberlabel];
    
    [_rightnumberlabel mas_makeConstraints:^(MASConstraintMaker *make) {
        
        make.right.mas_equalTo(0);
        make.top.mas_equalTo(0);
        make.size.mas_equalTo(CGSizeMake(width/2,20));
    }];
    
    _leftpricelabel=[[UILabel alloc]init];
    _leftpricelabel.textAlignment=NSTextAlignmentLeft;
    _leftpricelabel.font=[UIFont systemFontOfSize:12.0 * kWindowWHOne];
    _leftpricelabel.textColor=AppTextColor_Level_2;
    _leftpricelabel.text=[NSString stringWithFormat:@"%@(USDT)",LocalizationKey(@"tradePrice")];  //@"价格(USDT)";
    [self.rightBackView addSubview:_leftpricelabel];
    
    [_leftpricelabel mas_makeConstraints:^(MASConstraintMaker *make) {
        make.left.mas_equalTo(0);
        make.top.equalTo(weakSelf.rightnumberlabel.mas_top).offset(0);
        make.size.mas_equalTo(CGSizeMake(width/2,20));
    }];
    
    _asktableView=[self getmyTableview];
    _asktableView.backgroundColor=mainColor;
    _asktableView.scrollEnabled=NO;
    [self.rightBackView addSubview:_asktableView];
    
    _bidtableView=[self getmyTableview];
    _bidtableView.backgroundColor=mainColor;
    _bidtableView.scrollEnabled=NO;
    
    [self.rightBackView addSubview:_bidtableView];
    [self setTablewViewHeardcell];
    
    _rightNewPricelabel=[[UILabel alloc]init];
    _rightNewPricelabel.textAlignment=NSTextAlignmentLeft;
    _rightNewPricelabel.backgroundColor=mainColor;
    _rightNewPricelabel.textColor= baseColor;
    _rightNewPricelabel.font=[UIFont systemFontOfSize:18*kWindowWHOne];
    _rightNewPricelabel.text=@"10841.61";
    [self.rightBackView addSubview:_rightNewPricelabel];
    
    _rightNewCnylabel = [[UILabel alloc] init];
    _rightNewCnylabel.textAlignment=NSTextAlignmentLeft;
    _rightNewCnylabel.backgroundColor=mainColor;
    _rightNewCnylabel.textColor= AppTextColor_Level_3;
    _rightNewCnylabel.font=[UIFont systemFontOfSize:12*kWindowWHOne];
    _rightNewCnylabel.text=@"≈10841.61 CNY";
    _rightNewCnylabel.hidden = YES;
    [self.rightBackView addSubview:_rightNewCnylabel];
    
    [_asktableView mas_makeConstraints:^(MASConstraintMaker *make) {
        make.left.mas_equalTo(0);
        make.top.equalTo(_leftpricelabel.mas_bottom).offset(0);
        make.right.mas_equalTo(0);
        make.height.mas_equalTo(30*5);
    }];
    
    [_rightNewPricelabel mas_makeConstraints:^(MASConstraintMaker *make) {
        
        make.left.right.mas_equalTo(0);
        make.top.equalTo(weakSelf.asktableView.mas_bottom).offset(0);
        make.height.mas_equalTo(30);
        
    }];
    
    [_rightNewCnylabel mas_makeConstraints:^(MASConstraintMaker *make) {
        
        make.left.right.mas_equalTo(0);
        make.top.equalTo(_rightNewPricelabel.mas_bottom).offset(0);
        make.height.mas_equalTo(25);
        
    }];
    
    [_bidtableView mas_makeConstraints:^(MASConstraintMaker *make) {
        
        make.left.right.mas_equalTo(0);
        make.top.equalTo(weakSelf.rightNewCnylabel.mas_bottom).offset(0);
        make.height.mas_equalTo(30*5);
        
    }];
}


- (void)inintTitleMenuView {
    
    UIView *line1=[[UIView alloc]initWithFrame:CGRectMake(0,0,SCREEN_WIDTH_S,10)];
    line1.backgroundColor=self.view.backgroundColor;
    [self.titleMenuView addSubview:line1];
    
    _menubtn=[UIButton buttonWithType:UIButtonTypeCustom];
    _menubtn.frame=CGRectMake(10,line1.frame.origin.y+line1.frame.size.height+5,120,30);
    _menubtn.tag=109;
    [self.titleMenuView addSubview:_menubtn];
    
    _menbutbn2=[UIButton buttonWithType:UIButtonTypeCustom];
    _menbutbn2.frame=CGRectMake(_menubtn.frame.size.width+_menubtn.frame.origin.x+10,_menubtn.frame.origin.y,_menubtn.frame.size.width,_menubtn.frame.size.height);
    [self.titleMenuView addSubview:_menbutbn2];
    _menbutbn2.tag=110;
    [_menubtn setTitleColor:baseColor forState:UIControlStateSelected];
    [_menbutbn2 setTitleColor:baseColor forState:UIControlStateSelected];
    [_menubtn setTitleColor:AppTextColor_Level_2 forState:UIControlStateNormal];
    [_menbutbn2 setTitleColor:AppTextColor_Level_2 forState:UIControlStateNormal];
    
    _menubtn3=[UIButton buttonWithType:UIButtonTypeCustom];
    _menubtn3.frame=CGRectMake(SCREEN_WIDTH_S-50,_menubtn.frame.origin.y,40,30);
    [_menubtn3 setTitleColor:baseColor forState:UIControlStateNormal];
    _menubtn3.tag=111;
    [self.titleMenuView addSubview:_menubtn3];
    _menubtn.titleLabel.font=[UIFont fontWithName:@"Helvetica-Bold" size:15];
    _menbutbn2.titleLabel.font=[UIFont fontWithName:@"Helvetica-Bold" size:15];
    _menubtn3.titleLabel.font=[UIFont fontWithName:@"Helvetica-Bold" size:12];
    [_menubtn setTitle:LocalizationKey(@"text_hold") forState:UIControlStateNormal];
    [_menbutbn2 setTitle:LocalizationKey(@"text_current_entrust") forState:UIControlStateNormal];
    [_menubtn3 setTitle:LocalizationKey(@"all") forState:UIControlStateNormal];
    UIView *line2=[[UIView alloc]init];
    line2.frame=CGRectMake(0,_menubtn.frame.origin.y+_menubtn.frame.size.height+5,SCREEN_WIDTH_S,1);
    line2.backgroundColor=self.view.backgroundColor;
    [self.titleMenuView addSubview:line2];
    [_menubtn addTarget:self action:@selector(touchMenuclick:) forControlEvents:UIControlEventTouchUpInside];
    [_menbutbn2 addTarget:self action:@selector(touchMenuclick:) forControlEvents:UIControlEventTouchUpInside];
    [_menubtn3 addTarget:self action:@selector(touchMenuclick:) forControlEvents:UIControlEventTouchUpInside];
    
    //初始化界面样式
    UIButton *button = _isentrustType?_menbutbn2:_menubtn;
    [self touchMenuclick:button];
}


- (void)initBottomView {
    
    CGFloat width= self.bottomBackView.frame.size.width;
    MJWeakSelf
    
    _bottomtableView=[[TouchBottomTableView alloc]init];
    [_bottomtableView  setSeparatorStyle:UITableViewCellSeparatorStyleNone];
    
    _bottomtableView.delegate=self;
    _bottomtableView.dataSource=self;
    _bottomtableView.showsVerticalScrollIndicator=NO;
    _bottomtableView.alwaysBounceVertical=YES;
    if (@available(iOS 11.0, *)) {
        
        _bottomtableView.estimatedRowHeight = 0;
        
        _bottomtableView.estimatedSectionFooterHeight = 0;
        
        _bottomtableView.estimatedSectionHeaderHeight=0;
        _bottomtableView.contentInsetAdjustmentBehavior= UIScrollViewContentInsetAdjustmentNever;
    }
    
    [self.bottomBackView addSubview:_bottomtableView];
    
    [self footRefreshWithScrollerView:_myscollView];
    [self headRefreshWithScrollerView:_myscollView];
    self.myscollView.mj_footer.hidden=YES;
    self.myscollView.mj_header.hidden=NO;
    [_myscollView touchesShouldCancelInContentView:self.bottomBackView];
    
    [_bottomtableView mas_makeConstraints:^(MASConstraintMaker *make) {
        make.top.right.left.bottom.mas_equalTo(0);
    }];
    
    [self.loginbtn mas_makeConstraints:^(MASConstraintMaker *make) {
        // make.center.mas_equalTo(weakSelf.bottomtableView.center);
        make.centerX.equalTo(weakSelf.bottomBackView.mas_centerX).offset(0);
        make.centerY.equalTo(weakSelf.bottomBackView.mas_centerY).offset(30);
        make.size.mas_equalTo(CGSizeMake(width-120,40));
    }];
    
    [self.noDatalabel mas_makeConstraints:^(MASConstraintMaker *make) {
        
        //make.center.mas_equalTo(weakSelf.bottomtableView.center);
        make.centerX.equalTo(weakSelf.bottomBackView.mas_centerX).offset(0);
        make.centerY.equalTo(weakSelf.bottomBackView.mas_centerY).offset(30);
        make.size.mas_equalTo(CGSizeMake(width,40));
        
    }];
    self.noDatalabel.hidden=YES;
    self.loginbtn.hidden=YES;
    // self.bottomtableView.hidden=YES;
}


-(UITableView *)getmyTableview{
    
    UITableView *tabv=[[UITableView alloc]init];
    [tabv  setSeparatorStyle:UITableViewCellSeparatorStyleNone];
    
    tabv.delegate=self;
    tabv.dataSource=self;
    
    if (@available(iOS 11.0, *)) {
        
        tabv.estimatedRowHeight = 0;
        
        tabv.estimatedSectionFooterHeight = 0;
        
        tabv.estimatedSectionHeaderHeight=0;
        tabv.contentInsetAdjustmentBehavior= UIScrollViewContentInsetAdjustmentNever;
    }
    return tabv;
}


-(void)setTablewViewHeardcell{
    [self.asktableView registerNib:[UINib nibWithNibName:@"tradeCell" bundle:nil] forCellReuseIdentifier:@"Cell"];
    self.asktableView.tableFooterView=[UIView new];
    [self.bidtableView registerNib:[UINib nibWithNibName:@"tradeCell" bundle:nil] forCellReuseIdentifier:@"Cell"];
    self.bidtableView.tableFooterView=[UIView new];
}

#pragma mark -所有该界面按钮触发方法
- (void)touchMenuclick:(id) sender {
    MJWeakSelf
    UIControl *menu= (UIControl *)sender;
    switch (menu.tag) {
        case 100: //开仓按钮
        {
            UIButton *menubtn = (UIButton *)sender;
            UIButton *btn= [self.view viewWithTag:101];
            menubtn.selected=YES;
            btn.selected=NO;
            CGRect rect= self.toplineview.frame;
            rect.origin.y=menubtn.frame.origin.y+menubtn.frame.size.height-rect.size.height;
            rect.origin.x=menubtn.frame.origin.x;
            [UIView animateWithDuration:0.3 animations:^{
                weakSelf.toplineview.frame=rect;
            }];
            [self.duobtn setTitle:LocalizationKey(@"text_open_many") forState:UIControlStateNormal];
            [self.kongbtn setTitle:LocalizationKey(@"text_open_null") forState:UIControlStateNormal];
            [self.duobtn setTitleColor:AppTextColor_Level_2 forState:UIControlStateNormal];
            [self.duobtn setTitleColor:GreenColor forState:UIControlStateSelected];
            [self.kongbtn setTitleColor:AppTextColor_Level_2 forState:UIControlStateNormal];
            [self.kongbtn setTitleColor:RedColor forState:UIControlStateSelected];
            
            [_duobtn setBackgroundImage:[UIImage imageNamed:@"kaiduo"] forState:UIControlStateNormal];
            [_duobtn setBackgroundImage:[UIImage imageNamed:@"kaiduo_green"] forState:UIControlStateSelected];
            [_kongbtn setBackgroundImage:[UIImage imageNamed:@"kaikong_red"] forState:UIControlStateSelected];
            [_kongbtn setBackgroundImage:[UIImage imageNamed:@"kaikong"] forState:UIControlStateNormal];
            
            NSInteger number=0;
            if (self.duobtn.selected) {
                self.slider.sliderCircleImage = [UIImage imageNamed:@"circularGreen"];
                self.slider.tintColor = GreenColor;
                
                [self.buySellbtn setTitle:LocalizationKey(@"buyOpenmore") forState:UIControlStateNormal];
                self.znumberTiplabel.text=LocalizationKey(@"Openmore");
                [self.buySellbtn setBackgroundColor:GreenColor];
                [self.znumberTiplabel setTextColor:GreenColor];
                number=[self getOpenBuyNumber];
                
            }else{
                self.slider.sliderCircleImage = [UIImage imageNamed:@"circularRed"];
                self.slider.tintColor = RedColor;
                
                [self.buySellbtn setTitle:LocalizationKey(@"sellOpennull") forState:UIControlStateNormal];
                self.znumberTiplabel.text=LocalizationKey(@"Opennull");
                [self.buySellbtn setBackgroundColor:RedColor];
                [self.znumberTiplabel setTextColor:RedColor];
                number=[self getOpenSellNumber];
                
            }
            _isOpen=YES;
            self.znumberslabel.text=[NSString stringWithFormat:@"%ld %@",number,LocalizationKey(@"zhang")];
            
        }
            break;
        case 101: //平仓按钮
        {
            UIButton *menubtn = (UIButton *)sender;
            UIButton *btn= [self.view viewWithTag:100];
            menubtn.selected=YES;
            btn.selected=NO;
            CGRect rect= self.toplineview.frame;
            rect.origin.y=menubtn.frame.origin.y+menubtn.frame.size.height-rect.size.height;
            rect.origin.x=menubtn.frame.origin.x;
            [UIView animateWithDuration:0.3 animations:^{
                weakSelf.toplineview.frame=rect;
            }];
            [self.duobtn setTitle:LocalizationKey(@"text_flatmore") forState:UIControlStateNormal];
            [self.kongbtn setTitle:LocalizationKey(@"text_flatnull") forState:UIControlStateNormal];
            [self.duobtn setTitleColor:AppTextColor_Level_2 forState:UIControlStateNormal];
            [self.duobtn setTitleColor:RedColor forState:UIControlStateSelected];
            [self.kongbtn setTitleColor:AppTextColor_Level_2 forState:UIControlStateNormal];
            [self.kongbtn setTitleColor:GreenColor forState:UIControlStateSelected];
            
            [_duobtn setBackgroundImage:[UIImage imageNamed:@"kaiduo"] forState:UIControlStateNormal];
            [_duobtn setBackgroundImage:[UIImage imageNamed:@"kaiduo_red"] forState:UIControlStateSelected];
            [_kongbtn setBackgroundImage:[UIImage imageNamed:@"kaikong_green"] forState:UIControlStateSelected];
            [_kongbtn setBackgroundImage:[UIImage imageNamed:@"kaikong"] forState:UIControlStateNormal];
            
            NSInteger number=0;
            
            if (self.duobtn.selected) {
                self.slider.sliderCircleImage = [UIImage imageNamed:@"circularRed"];
                self.slider.tintColor = RedColor;
                
                [self.buySellbtn setTitle:LocalizationKey(@"sellflatmore") forState:UIControlStateNormal];
                self.znumberTiplabel.text=LocalizationKey(@"flatmore");
                [self.buySellbtn setBackgroundColor:RedColor];
                [self.znumberTiplabel setTextColor:RedColor];
                number=[self.coinInfo[@"usdtBuyPosition"] integerValue];
            }else{
                self.slider.sliderCircleImage = [UIImage imageNamed:@"circularGreen"];
                self.slider.tintColor = GreenColor;
                
                [self.buySellbtn setTitle:LocalizationKey(@"buyflatnull") forState:UIControlStateNormal];
                self.znumberTiplabel.text=LocalizationKey(@"flatnull");
                [self.buySellbtn setBackgroundColor:GreenColor];
                [self.znumberTiplabel setTextColor:GreenColor];
                number=[self.coinInfo[@"usdtSellPosition"] integerValue];
            }
            _isOpen=NO;
            self.znumberslabel.text=[NSString stringWithFormat:@"%ld %@",number,LocalizationKey(@"zhang")];
        }
            break;
        case 102: //开多按钮
        {
            UIButton *menubtn = (UIButton *)sender;
            UIButton *btn= [self.leftBackView viewWithTag:103];
            menubtn.selected=YES;
            btn.selected=NO;
            self.buySellbtn.backgroundColor=GreenColor;
            self.znumberTiplabel.textColor=GreenColor;
            
            UIButton *topbtn= [self.view viewWithTag:100];
            NSInteger number=0;
            
            if (topbtn.selected) {
                self.slider.sliderCircleImage = [UIImage imageNamed:@"circularGreen"];
                self.slider.tintColor = GreenColor;
                [self.buySellbtn setTitle:LocalizationKey(@"buyOpenmore") forState:UIControlStateNormal];
                self.znumberTiplabel.text=LocalizationKey(@"Openmore");
                [self.buySellbtn setBackgroundColor:GreenColor];
                [self.znumberTiplabel setTextColor:GreenColor];
                number= [self getOpenBuyNumber];
                
                [_duobtn setBackgroundImage:[UIImage imageNamed:@"kaiduo"] forState:UIControlStateNormal];
                [_duobtn setBackgroundImage:[UIImage imageNamed:@"kaiduo_green"] forState:UIControlStateSelected];
                [_kongbtn setBackgroundImage:[UIImage imageNamed:@"kaikong_red"] forState:UIControlStateSelected];
                [_kongbtn setBackgroundImage:[UIImage imageNamed:@"kaikong"] forState:UIControlStateNormal];
            }else{
                self.slider.sliderCircleImage = [UIImage imageNamed:@"circularRed"];
                self.slider.tintColor = RedColor;
                [self.buySellbtn setTitle:LocalizationKey(@"sellflatmore") forState:UIControlStateNormal];
                self.znumberTiplabel.text=LocalizationKey(@"flatmore");
                [self.buySellbtn setBackgroundColor:RedColor];
                [self.znumberTiplabel setTextColor:RedColor];
                number= [self.coinInfo[@"usdtBuyPosition"] integerValue];
                
                [_duobtn setBackgroundImage:[UIImage imageNamed:@"kaiduo"] forState:UIControlStateNormal];
                [_duobtn setBackgroundImage:[UIImage imageNamed:@"kaiduo_red"] forState:UIControlStateSelected];
                [_kongbtn setBackgroundImage:[UIImage imageNamed:@"kaikong_green"] forState:UIControlStateSelected];
                [_kongbtn setBackgroundImage:[UIImage imageNamed:@"kaikong"] forState:UIControlStateNormal];
                
            }
            _directionType=0;
            self.znumberslabel.text=[NSString stringWithFormat:@"%ld %@",number,LocalizationKey(@"zhang")];
            self.rightcontraclItem.leftlabel.text=[NSString stringWithFormat:@"%@x",_leveragebuyStr];
            
        }
            break;
        case 103: //开空按钮
        {
            UIButton *menubtn = (UIButton *)sender;
            UIButton *btn= [self.leftBackView viewWithTag:102];
            menubtn.selected=YES;
            btn.selected=NO;
            self.buySellbtn.backgroundColor=RedColor;
            self.znumberTiplabel.textColor=RedColor;
            UIButton *topbtn= [self.view viewWithTag:100];
            NSInteger number=0;
            if (topbtn.selected) {
                self.slider.sliderCircleImage = [UIImage imageNamed:@"circularRed"];
                self.slider.tintColor = RedColor;
                [self.buySellbtn setTitle:LocalizationKey(@"sellOpennull") forState:UIControlStateNormal];
                self.znumberTiplabel.text=LocalizationKey(@"Opennull");
                [self.buySellbtn setBackgroundColor:RedColor];
                [self.znumberTiplabel setTextColor:RedColor];
                number= [self getOpenSellNumber];
                
               [_duobtn setBackgroundImage:[UIImage imageNamed:@"kaiduo"] forState:UIControlStateNormal];
                [_duobtn setBackgroundImage:[UIImage imageNamed:@"kaiduo_green"] forState:UIControlStateSelected];
                [_kongbtn setBackgroundImage:[UIImage imageNamed:@"kaikong_red"] forState:UIControlStateSelected];
                [_kongbtn setBackgroundImage:[UIImage imageNamed:@"kaikong"] forState:UIControlStateNormal];
            }else{
                self.slider.sliderCircleImage = [UIImage imageNamed:@"circularGreen"];
                self.slider.tintColor = GreenColor;
                [self.buySellbtn setTitle:LocalizationKey(@"buyflatnull") forState:UIControlStateNormal];
                self.znumberTiplabel.text=LocalizationKey(@"flatnull");
                [self.buySellbtn setBackgroundColor:GreenColor];
                [self.znumberTiplabel setTextColor:GreenColor];
                number= [self.coinInfo[@"usdtSellPosition"] integerValue];
                
                [_duobtn setBackgroundImage:[UIImage imageNamed:@"kaiduo"] forState:UIControlStateNormal];
                [_duobtn setBackgroundImage:[UIImage imageNamed:@"kaiduo_red"] forState:UIControlStateSelected];
                [_kongbtn setBackgroundImage:[UIImage imageNamed:@"kaikong_green"] forState:UIControlStateSelected];
                [_kongbtn setBackgroundImage:[UIImage imageNamed:@"kaikong"] forState:UIControlStateNormal];
                
            }
            _directionType=1;
            
            self.znumberslabel.text=[NSString stringWithFormat:@"%ld %@",number,LocalizationKey(@"zhang")];
            self.rightcontraclItem.leftlabel.text=[NSString stringWithFormat:@"%@x",_leveragesellStr];
        }
            break;
        case 104: //价格委托类型按钮
        {
            CustomContraclItem *menubtn=(CustomContraclItem*)menu;
            NSArray *titles=@[LocalizationKey(@"text_Market_entrust"),LocalizationKey(@"text_limit_entrust"),LocalizationKey(@"text_plan_entrust")];
            [self.view createAlertViewTitleArray:titles textColor:AppTextColor_Level_2 font:[UIFont systemFontOfSize:16] type:0 actionBlock:^(UIButton * _Nullable button, NSInteger didRow) {
                menubtn.leftlabel.text=titles[didRow];
                NSInteger oldselectindex=_selectentrustIndex;
                _selectentrustIndex=didRow;
                
                if ((oldselectindex==2&&didRow!=2)||(oldselectindex!=2&&didRow==2)) {
                    [weakSelf updateSubViewRect];
                }
                
                
                
            }];
        }
            break;
        case 105: //杠杆倍率按钮
        {
            
            CustomContraclItem *menubtn=(CustomContraclItem*)menu;
            if (self.symbolInfo) {
                
                NSString *leverage=self.symbolInfo[@"leverage"];
                NSNumber *leverageType=self.symbolInfo[@"leverageType"];
                
                if (leverage&&leverage.length!=0) {
                    
                    NSArray *titles= [leverage componentsSeparatedByString:@","];
                    
                    if (!_leveragesView) {
                        
                        _leveragesView=[[LeveragesView alloc]init];
                        
                        _leveragesView.selcetLeverageblock = ^(NSInteger idx, NSString * _Nonnull selectStr) {
                            __strong __typeof(weakSelf) strongSelf = weakSelf;
                            // menubtn.leftlabel.text=[NSString stringWithFormat:@"%@x",selectStr];
                            
                            
                            
                            [weakSelf setleverageNumbers:selectStr direction:strongSelf ->_directionType];
                        };
                    }
                    _leveragesView.leverageType = leverageType;
                    _leveragesView.titles=titles;
                    _leveragesView.selectLeverString=_directionType?_leveragesellStr :_leveragebuyStr;
                    [_leveragesView showsLevergesView];
                    if(leverageType.longValue == 1){
                        [_leveragesView showMyScrollView];
                    }else {
                        [_leveragesView showSliderView];
                    }
                }
            }
        }
            break;
        case 106: //加按钮
        {
            CGFloat inputtprice=[self.inputPriceTextField.text doubleValue];
            inputtprice+=1;
            self.inputPriceTextField.text=[ToolUtil stringFromNumber:inputtprice withlimit:_baseCoinScale];
        }
            break;
        case 107: //减按钮
        {
            CGFloat inputtprice=[self.inputPriceTextField.text doubleValue];
            inputtprice-=1;
            self.inputPriceTextField.text=[ToolUtil stringFromNumber:inputtprice withlimit:_baseCoinScale];
        }
            break;
        case 108: //买入开多按钮
        {
            if ([YLUserInfo isLogIn]) {
                
                if (_inputNumberTextField.text.length==0) {
                    [self.view makeToast:LocalizationKey(@"input_number") duration:1.5 position:CSToastPositionCenter];
                    return;
                }
                
                if (_inputPriceTextField.text.length==0&&_selectentrustIndex!=0) {
                    [self.view makeToast:LocalizationKey(@"input_price") duration:1.5 position:CSToastPositionCenter];
                    return;
                }
                
                NSString *stringcoinid=self.symbolInfo[@"id"];
                NSMutableDictionary *mdict=[NSMutableDictionary dictionary];
                [mdict setObject:@(_selectentrustIndex) forKey:@"type"];
                
                if (_isOpen) {
                    [mdict setObject:@(_directionType) forKey:@"direction"];
                }else  //平仓时 多空应传相反
                    [mdict setObject:_directionType?@(0):@(1) forKey:@"direction"];
                
                [mdict setObject:stringcoinid forKey:@"contractCoinId"];
                
                if (_selectentrustIndex==2) {
                    [mdict setObject:_inputPriceTextField.text forKey:@"triggerPrice"];
                    [mdict setObject:_entrustPriceTextField.text.length==0?@(0):_entrustPriceTextField.text forKey:@"entrustPrice"];
                }else{
                    
                    [mdict setObject:@(0) forKey:@"triggerPrice"];
                    [mdict setObject:_selectentrustIndex==0?@(0):_inputPriceTextField.text forKey:@"entrustPrice"];
                }
                [mdict setObject:_directionType==1?_leveragesellStr:_leveragebuyStr forKey:@"leverage"];
                [mdict setObject:_inputNumberTextField.text forKey:@"volume"];
                
                [self sendOrderParam:mdict isOpen:_isOpen];
                
            }else
                [self showLoginViewController];
            
        }
            break;
        case 109: //当前持仓按钮
        {
            UIButton *menubtn = (UIButton *)sender;
            UIButton *btn= [self.titleMenuView viewWithTag:110];
            menubtn.selected=YES;
            btn.selected=NO;

            CGRect rect= self.titlemenulineview.frame;

            rect.size.width=menubtn.frame.size.width;
            rect.origin.y=menubtn.frame.origin.y+menubtn.frame.size.height-rect.size.height;
            rect.origin.x=menubtn.frame.origin.x;
            [UIView animateWithDuration:0.3 animations:^{
                weakSelf.titlemenulineview.frame=rect;
            }];
            _isentrustType=0;
            self.myscollView.mj_footer.hidden=YES;
            //                [self getPositionEntrustListSymbol:_symbol];
            [self.bottomtableView reloadData];
        }
            break;
        case 110: //当前委托
        {
            UIButton *menubtn = (UIButton *)sender;
            UIButton *btn= [self.titleMenuView viewWithTag:109];
            menubtn.selected=YES;
            btn.selected=NO;
            
            CGRect rect= self.titlemenulineview.frame;
            rect.size.width=menubtn.frame.size.width;
            rect.origin.y=menubtn.frame.origin.y+menubtn.frame.size.height-rect.size.height;
            rect.origin.x=menubtn.frame.origin.x;
            [UIView animateWithDuration:0.3 animations:^{
                weakSelf.titlemenulineview.frame=rect;
            }];
            _isentrustType=1;
            
            if ([YLUserInfo isLogIn]) {
                NSString *stringid=self.symbolInfo[@"id"];
                
                [self getCurrentEntrustContractCoinId:stringid PageNo:@(self.pageNo) isshowLoading:YES];
            }else
                [self.bottomtableView reloadData];
        }
            break;
        case 111: //全部
        {
            if([YLUserInfo isLogIn]){
                NSString *stringid=self.symbolInfo[@"id"];
                ContractHistoryOrderViewController *chovc=[[ContractHistoryOrderViewController alloc]init];
                chovc.contractCoinId=stringid;
                chovc.hidesBottomBarWhenPushed=YES;
                [self.navigationController pushViewController:chovc animated:YES];
            }else
                [self showLoginViewController];
        }
            break;
        case 112: //登录
        {
            if(![YLUserInfo isLogIn]){
                [self showLoginViewController];
            }
        }
            break;
        case 113: //全逐仓的切换
        {
            NSArray *titles=@[LocalizationKey(@"text_all_model"),LocalizationKey(@"text_chase_model")];
            [self.view createAlertViewTitleArray:titles textColor:AppTextColor_Level_2 font:[UIFont systemFontOfSize:16] type:0 actionBlock:^(UIButton * _Nullable button, NSInteger didRow) {
                
                //                    weakSelf.patternlabel.text =titles[didRow];
                //                    _targetPatternType=didRow;
                [weakSelf setTypePattern:didRow];
            }];
        }
            break;
        default:
            break;
    }
    
}

-(void)setContractSoccket{
    
    NSDictionary*dic;
    if ([YLUserInfo isLogIn]) {
        dic=[NSDictionary dictionaryWithObjectsAndKeys:_symbol,@"symbol",[YLUserInfo shareUserInfo].ID,@"uid",nil];
    }
    else{
        dic=[NSDictionary dictionaryWithObjectsAndKeys:_symbol,@"symbol",nil];
    }
    
    [[ContractExchangeSocketManager share] sendMsgWithLength:SOCKETREQUEST_LENGTH withsequenceId:0 withcmd:CONTRACT_SUBSCRIBE_SYMBOL_THUMB withVersion:COMMANDS_VERSION withRequestId:0 withbody:nil];
    
    [[ContractExchangeSocketManager share] sendMsgWithLength:SOCKETREQUEST_LENGTH withsequenceId:0 withcmd:SUBSCRIBE_EXCHANGE_TRADE_CONTRACT withVersion:COMMANDS_VERSION withRequestId: 0 withbody:dic];
    [ContractExchangeSocketManager share].delegate = self;
    
}

-(void)cancelContractSocket{
    NSDictionary*dic;
    if ([YLUserInfo isLogIn]) {
        dic=[NSDictionary dictionaryWithObjectsAndKeys:_symbol,@"symbol",[YLUserInfo shareUserInfo].ID,@"uid",nil];
    }
    else{
        dic=[NSDictionary dictionaryWithObjectsAndKeys:_symbol,@"symbol",nil];
    }
    
    [[ContractExchangeSocketManager share] sendMsgWithLength:SOCKETREQUEST_LENGTH withsequenceId:0 withcmd:CONTRACT_UNSUBSCRIBE_SYMBOL_THUMB withVersion:COMMANDS_VERSION withRequestId:0 withbody:nil];
    
    [[ContractExchangeSocketManager share] sendMsgWithLength:SOCKETREQUEST_LENGTH withsequenceId:0 withcmd:UNSUBSCRIBE_EXCHANGE_TRADE_CONTRACT withVersion:COMMANDS_VERSION withRequestId:0 withbody:dic];
    [ContractExchangeSocketManager share].delegate = nil;
}

//setpSlider 代理
-(void)getSliderValue:(CGFloat)sliderValue{
    NSLog(@"%f",sliderValue/4 );
    // NSArray *array = [self.znumberslabel.text componentsSeparatedByString:@" "];
    NSString *valueStr=self.znumberslabel.text;
    NSString *newstr=[valueStr stringByReplacingOccurrencesOfString:LocalizationKey(@"zhang") withString:@""];
    if (newstr.doubleValue!=0) {
        NSInteger number=sliderValue/4 * newstr.doubleValue;
        if (number==0) {
            self.inputNumberTextField.text=@"";
        }else{
            self.inputNumberTextField.text= [NSString stringWithFormat:@"%ld",number];
            
        }
    }
}


//MARK:--导航栏右侧的进入K线按钮点击事件
-(void)tradeRightBtnClick{
    [self cancelContractSocket];
    KchatViewController*klineVC=[[KchatViewController alloc]init];
    klineVC.isShowContract=YES;
    klineVC.symbol=_symbol;
    klineVC.istype = @"2";
    [[AppDelegate sharedAppDelegate] pushViewController:klineVC withBackTitle:_symbol];
}


#pragma mark-左侧弹出菜单
-(void)LefttouchEvent{
    
    if (!self.menu) {
        self.menu = [[ContractLeftMenuViewController alloc]init];
        CGRect frame = self.menu.view.frame;
        frame.origin.x = - CGRectGetWidth(self.view.frame);
        self.menu.view.frame = CGRectMake(- CGRectGetWidth(self.view.frame), 0,  kWindowW, kWindowH);
        [[UIApplication sharedApplication].keyWindow addSubview:self.menu.view];
        MJWeakSelf
        self.menu.selcetContractcoinSymbolModelBlock = ^(symbolModel * _Nonnull model) {
            
            [weakSelf setChangedSymbol:model.symbol];
            
        };
    }else{
        self.menu.isObserverNotificantion=YES;
    }
    
    [self.menu showLeftContractMenu];
}


-(NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section{
    if ([tableView isEqual:self.asktableView]) {
        
        return self.askcontentArr.count;
    }
    else if ([tableView isEqual:self.bidtableView]) {
        
        return self.bidcontentArr.count;
    }
    else{
        
        if (_isentrustType) {
            return self.entrustArray.count;
        }else
            return self.currentArray.count;
    }
}
- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath
{
    if ([tableView isEqual:self.asktableView]) {
        
        tradeCell * cell = [tableView dequeueReusableCellWithIdentifier:@"Cell" forIndexPath:indexPath];
        cell.selectionStyle = UITableViewCellSelectionStyleNone;
        cell.backview.backgroundColor = kRGBColor(241, 80, 87);
        cell.backview.alpha = 0.1;
        
        cell.priceLabel.textAlignment=NSTextAlignmentLeft;
        if (self.askcontentArr.count>0 ) {
            plateModel*bidplatemodel=self.askcontentArr[indexPath.row];
            
            
            if (bidplatemodel.price<0) {
                cell.priceLabel.text=@"--";
                cell.amountLabel.text=@"--";
                cell.priceLabel.textColor=RedColor;
            }
            else{
                cell.priceLabel.text=[NSString stringWithFormat:@"%@",[ToolUtil stringFromNumber:bidplatemodel.price withlimit:_baseCoinScale]];
                if (bidplatemodel.amount>=1000) {
                    cell.amountLabel.text=[NSString stringWithFormat:@"%@K",[ToolUtil stringFromNumber:bidplatemodel.amount/1000 withlimit:_coinScale]];
                }
                else{
                    cell.amountLabel.text=[NSString stringWithFormat:@"%@",[ToolUtil stringFromNumber:bidplatemodel.amount withlimit:_coinScale]];
                }
                
                cell.priceLabel.textColor=RedColor;
            }
            
            
            
            if (bidplatemodel.amount>=0) {
                cell.backwidth.constant=bidplatemodel.amount/bidplatemodel.totalAmount*cell.contentView.width;
            }
            else{
                cell.backwidth.constant=0;
            }
        }
        return cell;
    }
    else if ([tableView isEqual:self.bidtableView]){
        tradeCell * cell = [tableView dequeueReusableCellWithIdentifier:@"Cell" forIndexPath:indexPath];
        cell.selectionStyle=UITableViewCellSelectionStyleNone;
        
        cell.backview.backgroundColor = kRGBColor(42, 178, 116);
        cell.backview.alpha = 0.1;
        
        cell.priceLabel.textAlignment=NSTextAlignmentLeft;
        
        if (self.bidcontentArr.count>0) {
            plateModel*askplatemodel=self.bidcontentArr[indexPath.row];
            
            //    cell.kindName.text=[NSString stringWithFormat:@"%ld",indexPath.row+1];
            if (askplatemodel.price<0) {
                cell.priceLabel.text=@"--";
                cell.amountLabel.text=@"--";
                //            cell.amountLabel.textColor=GreenColor;
                cell.priceLabel.textColor=GreenColor;
                cell.backwidth.constant=0;
                
            }
            else{
                cell.priceLabel.text=[NSString stringWithFormat:@"%@",[ToolUtil stringFromNumber:askplatemodel.price withlimit:_baseCoinScale]];
                if (askplatemodel.amount>=1000) {
                    cell.amountLabel.text=[NSString stringWithFormat:@"%@K",[ToolUtil stringFromNumber:askplatemodel.amount/1000 withlimit:_coinScale]];
                }
                else{
                    cell.amountLabel.text=[NSString stringWithFormat:@"%@",[ToolUtil stringFromNumber:askplatemodel.amount withlimit:_coinScale]];
                }
                //            cell.amountLabel.textColor=GreenColor;
                cell.priceLabel.textColor=GreenColor;
            }
            if (askplatemodel.amount>=0) {
                cell.backwidth.constant=askplatemodel.amount/askplatemodel.totalAmount*cell.contentView.width;
            }
            else{
                cell.backwidth.constant=0;
            }
        }
        
        return cell;
    }
    else{
        
        if (!_isentrustType) {
            
            ContractHoldOrderTableViewCell *cell = [tableView dequeueReusableCellWithIdentifier:@"Cellhold"];
            if (!cell) {
                cell = [[ContractHoldOrderTableViewCell alloc] initWithStyle:UITableViewCellStyleDefault reuseIdentifier:@"Cellhold"];
                cell.selectionStyle=UITableViewCellSelectionStyleNone;
            }
            cell.shouyilabeltop.text = LocalizationKey(@"text_earnings");
            cell.shouyilvlabeltop.text = LocalizationKey(@"Rateofreturn");
            cell.leveragetiplabeltop.text=LocalizationKey(@"text_leverage");
            cell.chiNumberlabeltop.text=LocalizationKey(@"text_inventory");
            cell.kepingNumberlabeltop.text=LocalizationKey(@"text_Amount");
            cell.openpricelabeltop.text=LocalizationKey(@"text_average_open");
            cell.currtentPriceDepositlabeltop.text=LocalizationKey(@"text_Current_margin");
            cell.priceDepositlabeltop.text=LocalizationKey(@"text_margin_rate");
            [cell.pingButton setTitle:LocalizationKey(@"text_flat") forState:UIControlStateNormal];
       
            if (self.currentArray.count!=0&&indexPath.row<self.currentArray.count) {
                
                ContractRefreshModel *model= self.currentArray[indexPath.row];
                __weak typeof(self) weakself = self;
                cell.pingBlock = ^{
                    ContractPingView *view = [ContractPingView showWithModel:model];
                    view.doneBlock = ^(NSMutableDictionary * mdict) {
                        __weak typeof(weakself) strongself = weakself;
                        [mdict setObject:strongself.symbolInfo[@"id"] forKey:@"contractCoinId"];
                        [strongself sendOrderParam:mdict isOpen:NO];
                    };
                };
                
                if ([model.modelstr isEqualToString:@"buyid"]) {
                    cell.levergeslabel.backgroundColor=GreenColor;
                    cell.levergeslabel.text=[NSString stringWithFormat:@"%@ %ldx",LocalizationKey(@"more_head"),model.resfreshOne];
                }else{
                    cell.levergeslabel.backgroundColor=RedColor;
                    cell.levergeslabel.text=[NSString stringWithFormat:@"%@ %ldx",LocalizationKey(@"null_head"),model.resfreshOne];
                }
                CGSize maxsize = [cell.levergeslabel sizeThatFits:CGSizeMake(MAXFLOAT,20)];
                [cell.levergeslabel mas_updateConstraints:^(MASConstraintMaker *make) {
                    make.width.mas_equalTo(maxsize.width+5);
                }];
                
                if (model.resfreshEarning>0) {
                    cell.shouyilabel.textColor=GreenColor;
                }else
                    cell.shouyilabel.textColor=RedColor;
                
                cell.shouyilabel.text=[NSString stringWithFormat:@"%@ USDT",[ToolUtil stringFromNumber:model.resfreshEarning withlimit:_baseCoinScale]];
                
                if (model.resfreshRate>0) {
                    cell.shouyilvlabel.textColor=GreenColor;
                }else
                    cell.shouyilvlabel.textColor=RedColor;
                cell.shouyilvlabel.text=[NSString stringWithFormat:@"%@%%",[ToolUtil stringFromNumber:model.resfreshRate withlimit:2]];
                
                cell.chiNumberlabel.text=[NSString stringWithFormat:@"%ld%@",model.resfreshTwo,LocalizationKey(@"zhang")];
                cell.kepingNumberlabel.text=[NSString stringWithFormat:@"%ld%@",model.resfreshThree,LocalizationKey(@"zhang")];
                cell.openpricelabel.text=[NSString stringWithFormat:@"%@USDT",[ToolUtil stringFromNumber:model.resfreshFour withlimit:_baseCoinScale]];
                cell.currtentPriceDepositlabel.text=[NSString stringWithFormat:@"%@USDT",[ToolUtil stringFromNumber:model.resfreshFive withlimit:_baseCoinScale]];
                cell.priceDepositlabel.text=[NSString stringWithFormat:@"%@%%",[ToolUtil stringFromNumber:model.resfreshSix withlimit:2]];
            }
            
            return cell;
        }else{
            
            ContractCurrtentEntrustTableViewCell *cell = [tableView dequeueReusableCellWithIdentifier:@"CellEntrust"];
            if (!cell) {
                cell = [[ContractCurrtentEntrustTableViewCell alloc] initWithStyle:UITableViewCellStyleDefault reuseIdentifier:@"CellEntrust"];
                cell.selectionStyle=UITableViewCellSelectionStyleNone;
                [cell.revokebtn addTarget:self action:@selector(chexiaobtnclick:) forControlEvents:UIControlEventTouchUpInside];
            }
            cell.entrustTypetiplabel.text=LocalizationKey(@"text_entrust_type");
            cell.triggertiplabel.text=LocalizationKey(@"text_trigger_price_constract");
            cell.entrusttiplabel.text=LocalizationKey(@"entrustPrice");
            cell.dealtiplabel.text=LocalizationKey(@"dealPrice");
            cell.deposittiplabel.text=LocalizationKey(@"text_guarantee_money");
            cell.entrustnumbertiplabel.text=LocalizationKey(@"commissionamount");
            [cell.revokebtn setTitle:LocalizationKey(@"undo") forState:UIControlStateNormal];
            
            if (self.entrustArray.count!=0&&indexPath.row<self.entrustArray.count) {
                
                ConatractCurrentEntrustModel *model= self.entrustArray[indexPath.row];
                
                if ([model.entrustType isEqualToString:@"OPEN"]) {
                    
                    if ([model.direction isEqualToString:@"BUY"]) {
                        cell.mtitilelabel.textColor=GreenColor;
                        cell.mtitilelabel.text= LocalizationKey(@"buyOpenmore");
                    }else{
                        cell.mtitilelabel.text=LocalizationKey(@"sellOpennull");
                        cell.mtitilelabel.textColor=RedColor;
                    }
                    
                }else{
                    if ([model.direction isEqualToString:@"BUY"]) {
                        cell.mtitilelabel.textColor=GreenColor;
                        cell.mtitilelabel.text= LocalizationKey(@"buyflatnull");
                    }else{
                        cell.mtitilelabel.text=LocalizationKey(@"sellflatmore");
                        cell.mtitilelabel.textColor=RedColor;
                    }
                }
                CGSize maxsize = [cell.mtitilelabel sizeThatFits:CGSizeMake(MAXFLOAT,20)];
                [cell.mtitilelabel mas_updateConstraints:^(MASConstraintMaker *make) {
                    make.width.mas_equalTo(maxsize.width+5);
                }];
                
                //日期
                NSDate *date = [NSDate dateWithTimeIntervalSince1970:model.createTime.doubleValue/1000];
                NSString *dateStr = [_formatter stringFromDate:date];
                cell.timelabel.text=dateStr;
                
                if ([model.type isEqualToString:@"SPOT_LIMIT"]) {
                    cell.entrustTypeStrlabel.text=LocalizationKey(@"text_plan_entrust");
                    cell.triggerPricelabel.text=[ToolUtil stringFromNumber:model.triggerPrice.doubleValue withlimit:_baseCoinScale];
                }else if ([model.type isEqualToString:@"LIMIT_PRICE"]){
                    
                    cell.entrustTypeStrlabel.text=LocalizationKey(@"text_limit_entrust");
                    cell.triggerPricelabel.text=@"--";
                }else{
                    cell.entrustTypeStrlabel.text=LocalizationKey(@"text_Market_entrust");
                    cell.triggerPricelabel.text=@"--";
                }
                
                cell.entrustPricelabel.text=[ToolUtil stringFromNumber:model.entrustPrice.doubleValue withlimit:_baseCoinScale];
                cell.dealPricelabel.text=[ToolUtil stringFromNumber:model.tradedPrice.doubleValue withlimit:_baseCoinScale];
                cell.depositPricelabel.text=[ToolUtil stringFromNumber:model.principalAmount.doubleValue withlimit:_baseCoinScale];
                cell.entrustNumberlabel.text=model.volume;
            }
            return cell;
        }
        
    }
}

- (CGFloat)tableView:(UITableView *)tableView heightForRowAtIndexPath:(NSIndexPath*)indexPath {
    if ([tableView isEqual:self.asktableView]||[tableView isEqual:self.bidtableView]) {
        return 30;
    }
    else{
        
        if (_isentrustType) {
            return CurrtentEntrustCellHeight;
        }else
            return PostionCellHeight;
    }
}

-(void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath {
    
    //限价
    if ([tableView isEqual:self.asktableView]) {
        
        plateModel*bidplatemodel=[[self.askcontentArr reverseObjectEnumerator] allObjects][indexPath.row];
        
    }
    else if ([tableView isEqual:self.bidtableView]){
        
        plateModel*askplatemodel=self.bidcontentArr[indexPath.row];
        
    }
    else{
        
    }
}

- (void)chexiaobtnclick:(UIButton *)button {
    
    ContractCurrtentEntrustTableViewCell *cell =(ContractCurrtentEntrustTableViewCell*) button.superview;
    NSIndexPath *indxpath= [_bottomtableView indexPathForCell:cell];
    ConatractCurrentEntrustModel *model= self.entrustArray[indxpath.row];
    MJWeakSelf;
    UIAlertController *controller=[UIAlertController alertControllerWithTitle:LocalizationKey(@"cancelDelegation") message:nil preferredStyle:UIAlertControllerStyleAlert];
    UIAlertAction *act1=[UIAlertAction actionWithTitle:LocalizationKey(@"cancel") style:UIAlertActionStyleDefault handler:^(UIAlertAction * _Nonnull action) {
        
        [controller dismissViewControllerAnimated:YES completion:nil];
    }];
    UIAlertAction *act2= [UIAlertAction actionWithTitle:LocalizationKey(@"ok") style:UIAlertActionStyleDefault handler:^(UIAlertAction * _Nonnull action) {
        [EasyShowLodingView showLodingText:LocalizationKey(@"loading")];
        
        [ContractExchangeManager revokeHistoryConracEntrustld:model.id CompleteHandle:^(id  _Nonnull resPonseObj, int code) {
            [EasyShowLodingView hidenLoding];
            
            if (code) {
                
                if ([resPonseObj[@"code"] intValue] == 0) {
                    //  NSDictionary*dict=resPonseObj[@"data"];
                    NSString *stringid=self.symbolInfo[@"id"];
                    [weakSelf getContractAccountDetailCoinId:stringid];
                    
                    [weakSelf.entrustArray removeObject:model];
                    [weakSelf.bottomtableView deleteRowsAtIndexPaths:@[indxpath] withRowAnimation:UITableViewRowAnimationNone];
                    [weakSelf loadBottomViewTableViewHeight];
                }
                
                else if ([resPonseObj[@"code"] integerValue] ==4000){
                    
                    [YLUserInfo logout];
                }
                else{
                    [weakSelf.view makeToast:resPonseObj[MESSAGE] duration:1.5 position:CSToastPositionCenter];
                }
            }
            else{
                [weakSelf.view makeToast:LocalizationKey(@"noNetworkStatus") duration:1.5 position:CSToastPositionCenter];
            }
            
        }];
        
    }];
    [controller addAction:act1];
    [controller addAction:act2];
    [self presentViewController:controller animated:YES completion:nil];
}

#pragma mark - 输入最新价格进行动画处理方法

- (void)setNewPrice:(double)price {
    
    CGFloat oldprice=[self.rightNewPricelabel.text doubleValue];
    self.rightNewPricelabel.text=[ToolUtil stringFromNumber:price withlimit:_baseCoinScale];
    
    [UIView animateWithDuration:1 animations:^{
        
        self.rightNewPricelabel.alpha=0.9;
        if (oldprice<price) {
            self.rightNewPricelabel.textColor=GreenColor;
        }
        
        if (oldprice>price) {
            self.rightNewPricelabel.textColor=RedColor;
        }
        
    }completion:^(BOOL finished) {
        
        self.rightNewPricelabel.alpha=1;
        // self.rightNewPricelabel.textColor=AppTextColor_Level_1;
        
    }];
//    NSDecimalNumber *close = [NSDecimalNumber decimalNumberWithDecimal:[model.close decimalValue]];
//    self.nowPrice.text=[ToolUtil stringFromNumber:[model.close doubleValue] withlimit:_baseCoinScale];
    if (_rate != nil) {
        self.rightNewCnylabel.text=[NSString stringWithFormat:@"≈%.2f CNY",price * _rate.doubleValue];
    }
    
}

#pragma mark - 根据持仓或者当前委托获取数据来进行底部高度变化

-(void)loadBottomViewTableViewHeight{
    
    CGFloat maxBottomHeight= SCREEN_HEIGHT_S - kTabbarHeight-NavigationBarAdapterContentInsetTop-self.titleMenuView.frame.size.height-5+BottomOffsetY;
    
    CGRect rect=self.bottomBackView.frame;
    CGFloat height=0;
    if (_isentrustType) {
        
        height=self.entrustArray.count==0?80:CurrtentEntrustCellHeight*self.entrustArray.count;
        
    }else{
        height=self.currentArray.count==0?80:PostionCellHeight*self.currentArray.count;
    }
    if (height>=maxBottomHeight) {
        rect.size.height=maxBottomHeight;
    }else
        rect.size.height=height;
    
    if ([YLUserInfo isLogIn]) {
        //判断是否展示有无数据
        if (height==80) {
            self.bottomtableView.hidden=YES;
            self.noDatalabel.hidden=NO;
        }else{
            self.bottomtableView.hidden=NO;
            self.noDatalabel.hidden=YES;
        }
    }
    
    
    self.bottomBackView.frame=rect;
    self.myscollView.contentSize=CGSizeMake(SCREEN_WIDTH_S,rect.origin.y+rect.size.height);
}

#pragma mark - 获取合约深度图

-(void)getLastetTradeData {
    
    [EasyShowLodingView showLodingText:LocalizationKey(@"loading")];
    [ContractExchangeManager getConractPlateFullSymbol:_symbol CompleteHandle:^(id  _Nonnull resPonseObj, int code) {
        [EasyShowLodingView hidenLoding];
        if (code) {
            if ([resPonseObj[@"code"] intValue] == 0) {
                [self.askcontentArr removeAllObjects];
                [self.bidcontentArr removeAllObjects];
                //  NSDictionary*dict=resPonseObj[@"data"];
                // self.symbolInfo=(NSDictionary *)resPonseObj;
                if ([resPonseObj isKindOfClass:[NSDictionary class]]) {
                    NSDictionary *dict=(NSDictionary *)resPonseObj;
                    NSDictionary *selldict=dict[@"ask"];
                    NSDictionary *buydict=dict[@"bid"];
                    NSMutableArray *sellArr=[NSMutableArray arrayWithArray:selldict[@"items"]];
                    NSMutableArray *buyArr=[NSMutableArray arrayWithArray:buydict[@"items"]];
                    NSDictionary *sellfristdict=[sellArr firstObject];
                    NSDictionary *buyfristdict=[buyArr firstObject];
                    __block CGFloat buyTotalAmount=[sellfristdict[@"amount"] doubleValue];
                    __block CGFloat sellTotalAmount=[buyfristdict[@"amount"] doubleValue];
                    NSString *currentPrice=sellfristdict[@"price"];
                    NSMutableArray *askarr=[NSMutableArray array];
                    NSMutableArray *bidarr=[NSMutableArray array];
                    
                    [sellArr enumerateObjectsUsingBlock:^(id  _Nonnull obj, NSUInteger idx, BOOL * _Nonnull stop) {
                        if (idx!=0) {
                            NSDictionary *dict=(NSDictionary*)obj;
                            sellTotalAmount+=[dict[@"amount"] floatValue];
                        }
                        if (idx==5) {
                            *stop=YES;
                        }
                    }];
                    
                    [sellArr enumerateObjectsUsingBlock:^(id  _Nonnull obj, NSUInteger idx, BOOL * _Nonnull stop) {
                        if (idx!=0) {
                            NSDictionary *dict=(NSDictionary*)obj;
                            //  sellTotalAmount+=[dict[@"amount"] floatValue];
                            plateModel *model=[plateModel mj_objectWithKeyValues:dict];
                            model.totalAmount=sellTotalAmount;
                            [askarr addObject:model];
                        }
                        if (idx==5) {
                            *stop=YES;
                        }
                    }];
                    [buyArr enumerateObjectsUsingBlock:^(id  _Nonnull obj, NSUInteger idx, BOOL * _Nonnull stop) {
                        
                        if (idx!=0) {
                            NSDictionary *dict=(NSDictionary*)obj;
                            buyTotalAmount+=[dict[@"amount"] floatValue];
                            
                        }
                        if (idx==5) {
                            *stop=YES;
                        }
                    }];
                    [buyArr enumerateObjectsUsingBlock:^(id  _Nonnull obj, NSUInteger idx, BOOL * _Nonnull stop) {
                        
                        if (idx!=0) {
                            NSDictionary *dict=(NSDictionary*)obj;
                            buyTotalAmount+=[dict[@"amount"] floatValue];
                            plateModel *model=[plateModel mj_objectWithKeyValues:dict];
                            model.totalAmount=buyTotalAmount;
                            [bidarr addObject:model];
                        }
                        if (idx==5) {
                            *stop=YES;
                        }
                    }];
                    //反转数据
                    NSArray *asks= [[askarr reverseObjectEnumerator] allObjects];
                    
                    [self.askcontentArr addObjectsFromArray:asks];
                    [self.bidcontentArr addObjectsFromArray:bidarr];
                    [self.asktableView reloadData];
                    [self.bidtableView reloadData];
                    [self setNewPrice:currentPrice.doubleValue];
                    
                    if (![currentPrice isEqual:[NSNull null]]&&[currentPrice doubleValue]!=0) {
                        _currentCionPrice=currentPrice;
                        _huilvlabel.text=[NSString stringWithFormat:@"1 %@ = %@ USDT",LocalizationKey(@"zhang"),self.symbolInfo[@"shareNumber"]];
                        _inputPriceTextField.text=[NSString stringWithFormat:@"%@",[ToolUtil stringFromNumber:currentPrice.floatValue withlimit:_baseCoinScale]];
                    }
                        
                    
                }
            }
            else if ([resPonseObj[@"code"] integerValue] ==4000){
                
                [YLUserInfo logout];
            }
            else{
                [self.view makeToast:resPonseObj[MESSAGE] duration:1.5 position:CSToastPositionCenter];
            }
        }
        else{
            [self.view makeToast:LocalizationKey(@"noNetworkStatus") duration:1.5 position:CSToastPositionCenter];
        }
    }];
    
    
}



#pragma mark - 获取币种详情

- (void)getContractSymbolInfo:(NSString *)symbol {
    
    if (symbol.length==0) {
        return;
    }
    
    [ContractExchangeManager getContractSymbolinfo:symbol CompleteHandle:^(id  _Nonnull resPonseObj, int code) {
        if (code) {
            if ([resPonseObj[@"code"] intValue] == 0) {
//                  NSDictionary*dict=resPonseObj[@"data"];
                self.symbolInfo=(NSDictionary *)resPonseObj;
                _baseCoinScale=[self.symbolInfo[@"baseCoinScale"] intValue];
                _coinScale=[self.symbolInfo[@"coinScale"] intValue];
                _rightnumberlabel.text=[NSString stringWithFormat:@"%@(%@)",LocalizationKey(@"amount"),self.symbolInfo[@"coinSymbol"]];
                _leftpricelabel.text=[NSString stringWithFormat:@"%@(USDT)",LocalizationKey(@"tradePrice")];
                //获取合约成交深度数据
                [self getLastetTradeData];
                NSString *stringid=self.symbolInfo[@"id"];
                _rate = resPonseObj[@"usdtRate"];
                
                [self getContractAccountDetailCoinId:stringid];
                
            }
            
            else if ([resPonseObj[@"code"] integerValue] ==4000){
                
                [YLUserInfo logout];
            }
            else{
                [self.view makeToast:resPonseObj[MESSAGE] duration:1.5 position:CSToastPositionCenter];
            }
        }
        else{
            [self.view makeToast:LocalizationKey(@"noNetworkStatus") duration:1.5 position:CSToastPositionCenter];
        }
    }];
    
}

#pragma mark -根据币种获取用户合约账户信息（包含合约账户资产，持仓信息）

- (void)getContractAccountDetailCoinId:(NSString *)contractCoinId{
    
    if (![YLUserInfo isLogIn]||!contractCoinId) {
        return;
    }
    
    [ContractExchangeManager getWalletDatailContractConid:contractCoinId CompleteHandle:^(id  _Nonnull resPonseObj, int code) {
        if (code) {
            if ([resPonseObj[@"code"] intValue] == 0) {
                
                NSDictionary*dict=resPonseObj[@"data"];
                self.coinInfo=dict;
                NSString *str=[ToolUtil stringFromNumber:[dict[@"usdtBalance"] doubleValue] withlimit:2];
                self.keyonglabel.text=[NSString stringWithFormat:@"%@ %@",LocalizationKey(@"text_used"),str];
                
                NSInteger number=0;
                
                _leveragesellStr=[NSString stringWithFormat:@"%@",self.coinInfo[@"usdtSellLeverage"]];
                _leveragebuyStr=[NSString stringWithFormat:@"%@",self.coinInfo[@"usdtBuyLeverage"]];
                
                self.rightcontraclItem.leftlabel.text=[NSString stringWithFormat:@"%@x",_directionType?_leveragesellStr:_leveragebuyStr];
                if (_isOpen) {
                    if (_directionType) {
                        number= [self getOpenSellNumber];
                    }else
                        number=[self getOpenBuyNumber];
                }else{
                    
                    if (_directionType) {
                        number= [self.coinInfo[@"usdtSellPosition"] integerValue];
                    }else
                        number= [self.coinInfo[@"usdtBuyPosition"] integerValue];
                }
                
                if ([self.coinInfo[@"usdtPattern"] isEqualToString:@"FIXED"]) {
                    _targetPatternType=1;
                }else
                    _targetPatternType=0;
                
                self.znumberslabel.text=[NSString stringWithFormat:@"%ld %@",number,LocalizationKey(@"zhang")];
                
                _patternlabel.text=_targetPatternType?LocalizationKey(@"text_chase_model"):LocalizationKey(@"text_all_model");
                
                if (_isentrustType&&[YLUserInfo isLogIn]) {
                    
                    NSString *stringid=self.symbolInfo[@"id"];
                    
                    [self getCurrentEntrustContractCoinId:stringid PageNo:@(self.pageNo) isshowLoading:YES];
                }
                
            }
            
            else if ([resPonseObj[@"code"] integerValue] ==4000){
                
                [YLUserInfo logout];
            }
            else{
                [self.view makeToast:resPonseObj[MESSAGE] duration:1.5 position:CSToastPositionCenter];
            }
        }
        else{
            [self.view makeToast:LocalizationKey(@"noNetworkStatus") duration:1.5 position:CSToastPositionCenter];
        }
    }];
}


//切换仓位模式
- (void)setTypePattern:(NSInteger )patternumber{
    
    if (!self.symbolInfo) {
        return;
    }
    
    NSString *stringid=self.symbolInfo[@"id"];
    [EasyShowLodingView showLodingText:LocalizationKey(@"loading")];
    [ContractExchangeManager changedPatternSymbolContractId:stringid targetPattern:patternumber CompleteHandle:^(id  _Nonnull resPonseObj, int code) {
        [EasyShowLodingView hidenLoding];
        
        if (code) {
            if ([resPonseObj[@"code"] intValue] == 0) {
                
                NSDictionary*dict=resPonseObj[@"data"];
                _targetPatternType=patternumber;
                _patternlabel.text=_targetPatternType?LocalizationKey(@"text_chase_model"):LocalizationKey(@"text_all_model");
                [self getContractAccountDetailCoinId:stringid];
                
            }
            else if ([resPonseObj[@"code"] integerValue] ==4000){
                
                [YLUserInfo logout];
            }
            else{
                [self.view makeToast:resPonseObj[MESSAGE] duration:1.5 position:CSToastPositionCenter];
            }
        }
        else{
            [self.view makeToast:LocalizationKey(@"noNetworkStatus") duration:1.5 position:CSToastPositionCenter];
        }
        
    }];
}

//修改杠杆倍率
-(void)setleverageNumbers:(NSString *)leverage  direction:(NSInteger)direction {
    
    if (!self.symbolInfo) {
        return;
    }
    NSString *stringid=self.symbolInfo[@"id"];
    [EasyShowLodingView showLodingText:LocalizationKey(@"loading")];
    [ContractExchangeManager changedLeverageContractConid:stringid leverage:[leverage integerValue] direction:direction CompleteHandle:^(id  _Nonnull resPonseObj, int code) {
        [EasyShowLodingView hidenLoding];
        
        if (code) {
            if ([resPonseObj[@"code"] intValue] == 0) {
                
                NSDictionary*dict=resPonseObj[@"data"];
                if (_directionType) {
                    
                    _leveragesellStr=leverage;
                    
                    self.znumberslabel.text=[NSString stringWithFormat:@"%ld %@",[self getOpenSellNumber],LocalizationKey(@"zhang")];
                }else{
                    _leveragebuyStr=leverage;
                    
                    self.znumberslabel.text=[NSString stringWithFormat:@"%ld %@",[self getOpenBuyNumber],LocalizationKey(@"zhang")];
                }
                self.rightcontraclItem.leftlabel.text=[NSString stringWithFormat:@"%@x",_directionType?_leveragesellStr:_leveragebuyStr];
                
                [self getContractAccountDetailCoinId:stringid];
                
            }
            else if ([resPonseObj[@"code"] integerValue] ==4000){
                
                [YLUserInfo logout];
            }
            else{
                self.rightcontraclItem.leftlabel.text=[NSString stringWithFormat:@"%@x",_directionType?_leveragesellStr:_leveragebuyStr];
                [self.view makeToast:resPonseObj[MESSAGE] duration:1.5 position:CSToastPositionCenter];
            }
        }
        else{
            [self.view makeToast:LocalizationKey(@"noNetworkStatus") duration:1.5 position:CSToastPositionCenter];
        }
    }];
}

//获取合约币种账户列表
- (void)getContractWalletList {
    
    [ContractExchangeManager getWalletListCompleteHandle:^(id  _Nonnull resPonseObj, int code) {
        
        if (code) {
            if ([resPonseObj[@"code"] intValue] == 0) {
                
                NSDictionary*dict=resPonseObj[@"data"];
                
            }
            
            else if ([resPonseObj[@"code"] integerValue] ==4000){
                
                [YLUserInfo logout];
            }
            else{
                [self.view makeToast:resPonseObj[MESSAGE] duration:1.5 position:CSToastPositionCenter];
            }
        }
        else{
            [self.view makeToast:LocalizationKey(@"noNetworkStatus") duration:1.5 position:CSToastPositionCenter];
        }
        
    }];
}

//开仓/平仓 买卖
- (void)sendOrderParam:(NSMutableDictionary*)dict isOpen:(BOOL)isopen {
    NSString *stringid=self.symbolInfo[@"id"];
    [EasyShowLodingView showLodingText:LocalizationKey(@"loading")];
    [ContractExchangeManager  choiceOpenAndCloseOrderParam:dict isOpen:isopen CompleteHandle:^(id  _Nonnull resPonseObj, int code) {
        [EasyShowLodingView hidenLoding];
        
        if (code) {
            if ([resPonseObj[@"code"] intValue] == 0) {
                
                NSDictionary*dict=resPonseObj[@"data"];
                [self.view makeToast:resPonseObj[MESSAGE] duration:1.5 position:CSToastPositionCenter];
                self.inputNumberTextField.text=@"";
                [self.slider setIndex:0 animated:NO];
                [self getContractAccountDetailCoinId:stringid];
            }
            
            else if ([resPonseObj[@"code"] integerValue] ==4000){
                
                [YLUserInfo logout];
            }
            else{
                [self.view makeToast:resPonseObj[MESSAGE] duration:1.5 position:CSToastPositionCenter];
            }
        }
        else{
            [self.view makeToast:LocalizationKey(@"noNetworkStatus") duration:1.5 position:CSToastPositionCenter];
        }
        
    }];
}

//获取当前委托
-(void)getCurrentEntrustContractCoinId:(NSString *)coinid  PageNo:(NSNumber *)pageNo isshowLoading:(BOOL)ishow{
    
    NSMutableDictionary *dict=[NSMutableDictionary dictionary];
    if (coinid) {
        [dict setObject:coinid forKey:@"contractCoinId"];
    }
    [dict setObject:pageNo forKey:@"pageNo"];
    [dict setObject:@(10) forKey:@"pageSize"];
    if (ishow) {
        [EasyShowLodingView showLodingText:LocalizationKey(@"loading")];
    }
    
    [ContractExchangeManager getCurrentContractParam:dict CompleteHandle:^(id  _Nonnull resPonseObj, int code) {
        if (ishow) {
            [EasyShowLodingView hidenLoding];
        }
        
        if ([self.myscollView.mj_footer isRefreshing]) {
            [self.myscollView.mj_footer endRefreshing];
        }
        if ([self.myscollView.mj_header isRefreshing]) {
            [self.myscollView.mj_header endRefreshing];
        }
        if (code) {
            if ([resPonseObj[@"code"] intValue] == 0) {
                
                if ([resPonseObj[@"content"] isKindOfClass:[NSArray class]]) {
                    
                    NSArray *data= [ConatractCurrentEntrustModel mj_objectArrayWithKeyValuesArray:resPonseObj[@"content"]];
                    
                    if (_pageNo==1&&self.entrustArray.count!=0) {
                        [self.entrustArray removeAllObjects];
                    }
                    [self.entrustArray addObjectsFromArray:data];
                    if (data.count==0) {
                        [_myscollView.mj_footer endRefreshingWithNoMoreData];
                    }
                }
                // [self getContractAccountDetailCoinId:stringid];
                [self loadBottomViewTableViewHeight];
                [self.bottomtableView reloadData];
            }
            
            else if ([resPonseObj[@"code"] integerValue] ==4000){
                
                [YLUserInfo logout];
            }
            else{
                [self.view makeToast:resPonseObj[MESSAGE] duration:1.5 position:CSToastPositionCenter];
            }
        }
        else{
            [self.view makeToast:LocalizationKey(@"noNetworkStatus") duration:1.5 position:CSToastPositionCenter];
        }
    }];
    
}

//MARK:--上拉加载
- (void)refreshFooterAction{
    
    self.pageNo++;
    if (self.symbolInfo) {
        NSString *stringid=self.symbolInfo[@"id"];
        [self getCurrentEntrustContractCoinId:stringid PageNo:@(self.pageNo) isshowLoading:NO];
        
    }
    
}
//MARK:--下拉刷新
- (void)refreshHeaderAction{
    self.pageNo = 1;
    if (self.symbolInfo) {
        NSString *stringid=self.symbolInfo[@"id"];
        if (_isentrustType) {
            [_myscollView.mj_footer resetNoMoreData];
            [self getCurrentEntrustContractCoinId:stringid PageNo:@(self.pageNo) isshowLoading:NO];
        }else
            [self getContractAccountDetailCoinId:stringid];
    }
}

#pragma mark - SocketDelegate Delegate
- (void)contractExchangeetdelegateSocket:(GCDAsyncSocket *)sock didReadData:(NSData *)data withTag:(long)tag{
    
    NSData *endData = [data subdataWithRange:NSMakeRange(SOCKETRESPONSE_LENGTH, data.length -SOCKETRESPONSE_LENGTH)];
    NSString *endStr = [[NSString alloc] initWithData:endData encoding:NSUTF8StringEncoding];
    NSData *cmdData = [data subdataWithRange:NSMakeRange(12,2)];
    uint16_t cmd = [SocketUtils uint16FromBytes:cmdData];
    NSLog(@"盘口信息-- PUSH_SYMBOL_THUMB--");
    if (cmd==CONTRACT_PUSH_EXCHANGE_PLATE) {
        NSDictionary*dic=[SocketUtils dictionaryWithJsonString:endStr];
                    
        if ([dic[@"direction"] isEqualToString:@"SELL"]&&[dic[@"symbol"] isEqualToString:_symbol]) {
            [self.askcontentArr removeAllObjects];
            NSArray *sellarr=dic[@"items"];
            __block CGFloat sellTotalAmount=0;
            NSMutableArray *askarr=[NSMutableArray array];
            [sellarr enumerateObjectsUsingBlock:^(id  _Nonnull obj, NSUInteger idx, BOOL * _Nonnull stop) {
                //if (idx!=0) {
                NSDictionary *dict=(NSDictionary*)obj;
                sellTotalAmount+=[dict[@"amount"] floatValue];
                //                               plateModel *model=[plateModel mj_objectWithKeyValues:dict];
                //                               model.totalAmount=sellTotalAmount;
                //                               [askarr addObject:model];
                //   }
                if (idx==4) {
                    *stop=YES;
                }
            }];
            [sellarr enumerateObjectsUsingBlock:^(id  _Nonnull obj, NSUInteger idx, BOOL * _Nonnull stop) {
                //if (idx!=0) {
                NSDictionary *dict=(NSDictionary*)obj;
                //   sellTotalAmount+=[dict[@"amount"] floatValue];
                plateModel *model=[plateModel mj_objectWithKeyValues:dict];
                model.totalAmount=sellTotalAmount;
                [askarr addObject:model];
                //   }
                if (idx==4) {
                    *stop=YES;
                }
            }];
            //反转数据
            NSArray *asks= [[askarr reverseObjectEnumerator] allObjects];
            [self.askcontentArr addObjectsFromArray:asks];
            [self.asktableView reloadData];
        }
        
        if ([dic[@"direction"] isEqualToString:@"BUY"]&&[dic[@"symbol"] isEqualToString:_symbol]) {
            [self.bidcontentArr removeAllObjects];
            NSArray *buyarr=dic[@"items"];
            __block CGFloat buyTotalAmount=0;
            NSMutableArray *bidarr=[NSMutableArray array];
            [buyarr enumerateObjectsUsingBlock:^(id  _Nonnull obj, NSUInteger idx, BOOL * _Nonnull stop) {
                //if (idx!=0) {
                NSDictionary *dict=(NSDictionary*)obj;
                buyTotalAmount+=[dict[@"amount"] floatValue];
                //                              plateModel *model=[plateModel mj_objectWithKeyValues:dict];
                //                              model.totalAmount=buyTotalAmount;
                //                              [bidarr addObject:model];
                //  }
                if (idx==4) {
                    *stop=YES;
                }
            }];
            [buyarr enumerateObjectsUsingBlock:^(id  _Nonnull obj, NSUInteger idx, BOOL * _Nonnull stop) {
                //if (idx!=0) {
                NSDictionary *dict=(NSDictionary*)obj;
                //  buyTotalAmount+=[dict[@"amount"] floatValue];
                plateModel *model=[plateModel mj_objectWithKeyValues:dict];
                model.totalAmount=buyTotalAmount;
                [bidarr addObject:model];
                //  }
                if (idx==4) {
                    *stop=YES;
                }
            }];
            
            [self.bidcontentArr addObjectsFromArray:bidarr];
            [self.bidtableView reloadData];
        }
    }
    
    if (cmd==CONTRACT_PUSH_SYMBOL_THUMB) {
        
        NSDictionary*dic=[SocketUtils dictionaryWithJsonString:endStr];
        
        if ([_symbol isEqualToString:dic[@"symbol"]]) {
            
            NSString *closeprice=dic[@"close"];
            
            [self setNewPrice:closeprice.doubleValue];
            
            if (!_isentrustType) {
                
                [self setNewModelPrice:closeprice.doubleValue];
            }
            
        }
        
        if (self.menu.isObserverNotificantion) {
            NSNotification *notification =[NSNotification notificationWithName:NSNotification_CONTRACTMENULIST object:nil userInfo:dic];
            [[NSNotificationCenter defaultCenter] postNotification:notification];
            
            
        }
    }
    
    //持有仓数据信息
    if (cmd==CONTRACT_PUSH_EXCHANGE_ORDER_COMPLETED) {
        NSDictionary*dic=[SocketUtils dictionaryWithJsonString:endStr];
        
    }
    
    //持有单取消信息
    if (cmd==CONTRACT_PUSH_EXCHANGE_ORDER_CANCELED) {
        NSDictionary*dic=[SocketUtils dictionaryWithJsonString:endStr];
        
    }
}



-(void)scrollViewDidScroll:(UIScrollView *)scrollView{
    
    if ([self.myscollView isEqual:scrollView]) {
        
        if (self.myscollView.contentOffset.y<=self.titleMenuView.frame.origin.y+BottomOffsetY) {
            
            if (!_istableScoll) {
                self.bottomtableView.contentOffset=CGPointMake(0,0);
            }
            
            if (self.bottomtableView.contentSize.height<=(SCREEN_HEIGHT_S - kTabbarHeight-NavigationBarAdapterContentInsetTop-self.titleMenuView.frame.size.height-5+BottomOffsetY)&&_isentrustType) {
                self.myscollView.mj_footer.hidden=NO;
            }
            
        }else{
            _istableScoll=YES;
            
        }
    }
    
    if ([scrollView isEqual:self.bottomtableView]) {
        
        if (_istableScoll) {
            
            if (self.bottomtableView.contentOffset.y<=0) {
                _istableScoll=NO;
                self.bottomtableView.contentOffset=CGPointMake(0,0);
            }else{
                
                _myscollView.contentOffset=CGPointMake(0, self.titleMenuView.frame.origin.y+BottomOffsetY);
            }
            
            if((self.bottomtableView.contentSize.height-self.bottomtableView.frame.size.height)<=self.bottomtableView.contentOffset.y) {
                self.myscollView.mj_footer.hidden=NO;
            }else{
                self.myscollView.mj_footer.hidden=YES;
            }
            
        }else{
            self.bottomtableView.contentOffset=CGPointMake(0,0);
            
        }
        
    }
}



//可开多张数
-(NSInteger )getOpenBuyNumber {
    
    if (![YLUserInfo isLogIn]||!self.coinInfo) {
        return 0;
    }
    if ([self.coinInfo[@"usdtPattern"] isEqualToString:@"FIXED"]) {
        
        NSInteger number=[_leveragebuyStr integerValue]*[self.coinInfo[@"usdtBalance"] doubleValue]/[self.coinInfo[@"usdtShareNumber"] integerValue];
        return  number;
    }else{
        
        NSInteger number=0;
        double currencyprice= [self.inputPriceTextField.text doubleValue];
        double buypl=0;
        
        if ([self.coinInfo[@"usdtBuyPrice"] doubleValue]>0) {
            buypl= (currencyprice/[self.coinInfo[@"usdtBuyPrice"] doubleValue] -1)*([self.coinInfo[@"usdtBuyPosition"] doubleValue]+[self.coinInfo[@"usdtFrozenBuyPosition"] doubleValue]*[self.coinInfo[@"usdtShareNumber"] integerValue]);
        }
        double sellpl=0;
        if ([self.coinInfo[@"usdtSellPrice"] doubleValue]>0) {
            sellpl = (1- currencyprice/[self.coinInfo[@"usdtSellPrice"] doubleValue])*([self.coinInfo[@"usdtSellPosition"] doubleValue]+[self.coinInfo[@"usdtFrozenSellPosition"] doubleValue])*[self.coinInfo[@"usdtShareNumber"] integerValue];
        }
        
        double lossandp= buypl+sellpl+[self.coinInfo[@"usdtBuyPrincipalAmount"] doubleValue]+[self.coinInfo[@"usdtSellPrincipalAmount"] doubleValue];
        
        if (lossandp>=0) {
            number=[self.coinInfo[@"usdtBuyLeverage"] integerValue]* [self.coinInfo[@"usdtBalance"] doubleValue]/[self.coinInfo[@"usdtShareNumber"] integerValue];
        }else
            number=[self.coinInfo[@"usdtBuyLeverage"] integerValue]* ([self.coinInfo[@"usdtBalance"] doubleValue]-lossandp)/[self.coinInfo[@"usdtShareNumber"] integerValue];
        
        return number;
    }
}

//可开空张数
- (NSInteger) getOpenSellNumber {
    
    if (![YLUserInfo isLogIn]||!self.coinInfo) {
        return 0;
    }
    //逐仓模式
    if ([self.coinInfo[@"usdtPattern"] isEqualToString:@"FIXED"]) {
        
        NSInteger number=[_leveragesellStr integerValue]*[self.coinInfo[@"usdtBalance"] doubleValue]/[self.coinInfo[@"usdtShareNumber"] integerValue];
        return  number;
    }else{
        
        NSInteger number=0;
        double currencyprice= [self.inputPriceTextField.text doubleValue];
        double buypl=0;
        
        if ([self.coinInfo[@"usdtBuyPrice"] doubleValue]>0) {
            buypl= (currencyprice/[self.coinInfo[@"usdtBuyPrice"] doubleValue] -1)*([self.coinInfo[@"usdtBuyPosition"] doubleValue]+[self.coinInfo[@"usdtFrozenBuyPosition"] doubleValue]*[self.coinInfo[@"usdtShareNumber"] integerValue]);
        }
        double sellpl=0;
        if ([self.coinInfo[@"usdtSellPrice"] doubleValue]>0) {
            sellpl = (1- currencyprice/[self.coinInfo[@"usdtSellPrice"] doubleValue])*([self.coinInfo[@"usdtSellPosition"] doubleValue]+[self.coinInfo[@"usdtFrozenSellPosition"] doubleValue])*[self.coinInfo[@"usdtShareNumber"] integerValue];
        }
        
        double lossandp= buypl+sellpl+[self.coinInfo[@"usdtBuyPrincipalAmount"] doubleValue]+[self.coinInfo[@"usdtSellPrincipalAmount"] doubleValue];
        
        if (lossandp>=0) {
            number=[self.coinInfo[@"usdtSellLeverage"] integerValue]* [self.coinInfo[@"usdtBalance"] doubleValue]/[self.coinInfo[@"usdtShareNumber"] integerValue];
        }else
            number=[self.coinInfo[@"usdtSellLeverage"] integerValue]* ([self.coinInfo[@"usdtBalance"] doubleValue]-lossandp)/[self.coinInfo[@"usdtShareNumber"] integerValue];
        
        return number;
    }
}



//计算构建合约持仓单数据
- (void)setNewModelPrice:(double)price {
    
    if ([self.coinInfo[@"usdtBuyPosition"] doubleValue]>0||[self.coinInfo[@"usdtFrozenBuyPosition"] doubleValue]>0) {
        ContractRefreshModel *model=nil;
        for (ContractRefreshModel *_rmodel in self.currentArray) {
            if ([_rmodel.modelstr isEqualToString:@"buyid"]) {
                model=_rmodel;
            }
        }
        if (!model) {
            model=[[ContractRefreshModel alloc]init];
            model.modelstr=@"buyid";
            [self.currentArray addObject:model];
        }
        double buyPL=(price/[self.coinInfo[@"usdtBuyPrice"] doubleValue]-1)*([self.coinInfo[@"usdtBuyPosition"]doubleValue]+[self.coinInfo[@"usdtFrozenBuyPosition"]doubleValue])*[self.coinInfo[@"usdtShareNumber"] integerValue];
        double rate= buyPL/[self.coinInfo[@"usdtBuyPrincipalAmount"] doubleValue]*100;
        //收益
        model.resfreshEarning=buyPL;
        //收益率
        model.resfreshRate=rate;
        model.resfreshOne = [_leveragebuyStr integerValue];
        //持仓量
        model.resfreshTwo= [self.coinInfo[@"usdtBuyPosition"]integerValue]+[self.coinInfo[@"usdtFrozenBuyPosition"]integerValue];
        //可平量
        model.resfreshThree=[self.coinInfo[@"usdtBuyPosition"]integerValue];
        //开仓均价
        model.resfreshFour=[self.coinInfo[@"usdtBuyPrice"]doubleValue];
        //保证金
        model.resfreshFive= [self.coinInfo[@"usdtBuyPrincipalAmount"] doubleValue];
        //保证金率
        model.resfreshSix= (buyPL+[self.coinInfo[@"usdtBuyPrincipalAmount"] doubleValue])/[self.coinInfo[@"usdtBuyPrincipalAmount"] doubleValue]/[_leveragebuyStr integerValue] *100;
    }else{
        ContractRefreshModel *model=nil;
        for (ContractRefreshModel *_rmodel in self.currentArray) {
            if ([_rmodel.modelstr isEqualToString:@"buyid"]) {
                model=_rmodel;
            }
        }
        if (model) {
            
            [self.currentArray removeObject:model];
        }
    }
    
    if ([self.coinInfo[@"usdtSellPosition"] doubleValue]>0||[self.coinInfo[@"usdtFrozenSellPosition"] doubleValue]>0){
        ContractRefreshModel *model=nil;
        for (ContractRefreshModel *_rmodel in self.currentArray) {
            if ([_rmodel.modelstr isEqualToString:@"sellid"]) {
                model=_rmodel;
            }
        }
        if (!model) {
            model=[[ContractRefreshModel alloc]init];
            
            model.modelstr=@"sellid";
            [self.currentArray addObject:model];
        }
        
        double sellpl=(1 - price/[self.coinInfo[@"usdtSellPrice"] doubleValue])*([self.coinInfo[@"usdtSellPosition"]doubleValue]+[self.coinInfo[@"usdtFrozenSellPosition"]doubleValue])*[self.coinInfo[@"usdtShareNumber"] integerValue];
        //收益
        model.resfreshEarning=sellpl;
        
        double rate = sellpl/[self.coinInfo[@"usdtSellPrincipalAmount"] doubleValue]*100;
        //收益率
        model.resfreshRate=rate;
        //杠杆倍数
        model.resfreshOne=[_leveragesellStr integerValue];
        //持仓量
        model.resfreshTwo= [self.coinInfo[@"usdtSellPosition"]integerValue]+[self.coinInfo[@"usdtFrozenSellPosition"]integerValue];
        //可平量
        model.resfreshThree=[self.coinInfo[@"usdtSellPosition"]integerValue];
        //开仓均价
        model.resfreshFour=[self.coinInfo[@"usdtSellPrice"]doubleValue];
        //保证金
        model.resfreshFive= [self.coinInfo[@"usdtSellPrincipalAmount"] doubleValue];
        //保证金率
        model.resfreshSix= (sellpl+[self.coinInfo[@"usdtSellPrincipalAmount"] doubleValue])/[self.coinInfo[@"usdtSellPrincipalAmount"] doubleValue]/[_leveragesellStr integerValue] *100;
    }else{
        
        ContractRefreshModel *model=nil;
        for (ContractRefreshModel *_rmodel in self.currentArray) {
            if ([_rmodel.modelstr isEqualToString:@"sellid"]) {
                model=_rmodel;
            }
        }
        if (model) {
            [self.currentArray removeObject:model];
        }
    }
    
    [self loadBottomViewTableViewHeight];
    [self.bottomtableView reloadData];
}


- (void)updateSubViewRect {
    
    MJWeakSelf;
    _entrustPriceTextField.text=@"";
    CGRect rect= self.leftBackView.frame;
    if (_selectentrustIndex==2) {
        _entrustPriceTextField.hidden=NO;
        [_entrustPriceBackView mas_updateConstraints:^(MASConstraintMaker *make) {
            make.top.equalTo(weakSelf.priceBackView.mas_bottom).offset(10);
            make.height.mas_equalTo(35);
        }];
        rect.size.height=rect.size.height+45;
    }else{
        _entrustPriceTextField.hidden=YES;
        
        [_entrustPriceBackView mas_updateConstraints:^(MASConstraintMaker *make) {
            make.top.equalTo(weakSelf.priceBackView.mas_bottom).offset(0);
            make.height.mas_equalTo(0);
        }];
        rect.size.height=rect.size.height-45;
    }
    self.leftBackView.frame=rect;
    CGRect rect2=self.rightBackView.frame;
    rect2.size.height=rect.size.height;
    self.rightBackView.frame=rect2;
    CGRect rect3 =self.titleMenuView.frame;
    rect3.origin.y=rect2.origin.y+rect2.size.height;
    self.titleMenuView.frame=rect3;
    CGRect rect4=self.bottomBackView.frame;
    rect4.origin.y=rect3.origin.y+rect3.size.height;
    self.bottomBackView.frame=rect4;
    self.myscollView.contentSize=CGSizeMake(SCREEN_WIDTH_S,self.bottomBackView.origin.y+self.bottomBackView.frame.size.height);
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
