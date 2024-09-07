//
//  OptionViewController.m
//  digitalCurrency
//
//  Created by chan on 2020/12/29.
//  Copyright © 2020 BIZZAN. All rights reserved.
//

#import "OptionViewController.h"
#import "OptionLeftMenuViewController.h"
#import "OptionNetManager.h"
#import "OptionModel.h"
#import "OptionCoinModel.h"
#import "OptionOrderModel.h"
#import "OptionTableHeaderView.h"
#import "OptionTradeTableViewCell.h"
#import "OptionCurrentTableViewCell.h"
#import "OptionHistoryTableViewCell.h"
#import "TradeNetManager.h"
#import "OptionPopView.h"

@interface OptionViewController ()<UITableViewDelegate,UITableViewDataSource,ContractExchangeSocketManagerDelegate>

@property (nonatomic, strong) OptionLeftMenuViewController *menu;
@property (nonatomic, strong) UILabel *topTitlelabel;
@property (nonatomic, copy  ) NSString *symbol;
@property (nonatomic, strong) UITableView *myTableView;
@property (nonatomic, strong) OptionTableHeaderView *headerView;
@property (nonatomic, assign) OptionTableCellType cellType;
@property (nonatomic, strong) OptionCoinModel *infoModel;
@property (nonatomic, strong) OptionModel *openModel;
@property (nonatomic, strong) OptionModel *startModel;

@property (nonatomic, strong) OptionOrderModel *openMyModel;
@property (nonatomic, strong) OptionOrderModel *startMyModel;

@property (nonatomic, strong) NSMutableArray *historyList;
@property (nonatomic, assign) NSInteger pageNo;
@property (nonatomic, assign) CGFloat progress;
@property (nonatomic, weak  ) OptionCurrentTableViewCell *currentCell;
@property (nonatomic, weak  ) OptionTradeTableViewCell *tradeCell;
@property (nonatomic, copy  ) NSString *orderAmount; //下单数量
@property (nonatomic, strong) NSArray *amountArray; //投注金额数组 10 20 30

@end

@implementation OptionViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    self.view.backgroundColor = mainColor;
    _symbol = @"BTC/USDT";
    
    [self.myTableView mas_makeConstraints:^(MASConstraintMaker *make) {
        make.edges.equalTo(self.view);
    }];

    [self LeftsetupNavgationItemWithpictureName:@"tradeLeft"];
}

- (void)viewDidAppear:(BOOL)animated {
    [super viewDidAppear:animated];
    [[NSNotificationCenter defaultCenter] addObserver:self selector:@selector(timerAction) name:@"KTimerNotification" object:nil];
    [self setContractSoccket];
    [self setChangedSymbol:_symbol];
}

- (void)timerAction {
    if (self.openModel.openTime > 0 && self.infoModel.openTimeGap > 0) {
        NSString *currentTime = [ToolUtil timeToTimeIntervalString:[ToolUtil getCurrentDateString] WithDateFormat:@"yyyy-MM-dd HH:mm:ss"];
        self.progress = ([currentTime integerValue] - self.openModel.openTime) / (self.infoModel.openTimeGap*1000) ;
        if (self.progress >= 1) {
            self.currentCell.progress = 1;
            [self setChangedSymbol:_symbol];
            return;
        }
        if (self.currentCell && self.cellType == OptionTableCellTypeCurrent) {
            self.currentCell.progress =  self.progress;
        }
        if (self.cellType == OptionTableCellTypeCurrent) {
            [self.myTableView reloadData];
        }
    }else {
        [self getOptionOpeningSymbol:self.symbol];
    }
}

//切换币种
- (void)setChangedSymbol:(NSString *)symbol{
    _symbol = symbol;
    self.topTitlelabel.text = [NSString stringWithFormat:@"%@%@",symbol,LocalizationKey(@"Option_contract")];
    NSArray *arr = [symbol componentsSeparatedByString:@"/"];
    
    self.headerView.symbol = symbol;
    
    [self getOptionSymbolInfo:symbol];
    [self OptionHistorySymbol:symbol];
    [self getOptionOpeningSymbol:symbol];
    [self getOptionStartingSymbol:symbol];
   
    if (YLUserInfo.isLogIn) {
        if (self.cellType == OptionTableCellTypeHistory) {
            [self.myTableView scrollToRowAtIndexPath:[NSIndexPath indexPathForRow:0 inSection:0]  atScrollPosition:UITableViewScrollPositionTop animated:NO];
            [self.myTableView setContentOffset:CGPointMake(0, 0) animated:NO];
        }
        [self getCoinwalletwithcoin:arr.lastObject];
        [self getOptionOrderSymbol:symbol pageNo:0];
    }
}

- (void)viewDidDisappear:(BOOL)animated {
    [super viewDidDisappear:animated];
    [self cancelContractSocket];
    [[NSNotificationCenter defaultCenter] removeObserver:self];
}

#pragma  mark - UITableViewDataSource
- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section {
    switch (self.cellType) {
        case OptionTableCellTypeTrade:
            return  1;
            break;
        case OptionTableCellTypeCurrent:
            if (self.openMyModel && self.openMyModel.betAmount.floatValue > 0) {
                return  2;
            }
            return  1;
            break;
        case OptionTableCellTypeHistory:
            return self.historyList.count;
            break;
            
        default:
            return 0;
            break;
    }
}

- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath {
    switch (self.cellType) {
        case OptionTableCellTypeTrade: {
            OptionTradeTableViewCell *cell = [tableView dequeueReusableCellWithIdentifier:@"OptionTradeTableViewCellId" forIndexPath:indexPath];
            cell.symbol = self.symbol;
            self.tradeCell = cell;
            if (self.startModel) {
                cell.model = self.startModel;
            }
            cell.opScrollView.data = self.amountArray;
    
            if (self.startMyModel && self.startMyModel.betAmount.floatValue > 0){
                NSInteger direction = [self.startMyModel.direction isEqualToString:@"SELL"] ? 1 :0;
                [cell setBeAmount: self.startMyModel.betAmount direction:direction];
            }else {
                [cell setBeAmount: @"0" direction:0];
            }

            __weak typeof(self) weakself = self ;
            cell.opScrollView.didClickBlock = ^(NSString * _Nonnull orderAmount) {
                weakself.orderAmount =  orderAmount;
            };
            cell.upBlock = ^(NSInteger up) {
                [weakself addOrder:up];
            };
            return  cell;
        }
            break;
        case OptionTableCellTypeCurrent:{
            if (indexPath.row == 0) {
                OptionCurrentTableViewCell *cell = [tableView dequeueReusableCellWithIdentifier:@"OptionCurrentTableViewCellId" forIndexPath:indexPath];
                cell.symbol = self.symbol;
                self.currentCell = cell;
                cell.progress = self.progress;
                if (self.openModel) {
                    cell.model = self.openModel;
                }
                if (self.openMyModel && self.openMyModel.betAmount.floatValue > 0){
                    NSInteger direction = [self.openMyModel.direction isEqualToString:@"SELL"] ? 1 :0;
                    [cell setBeAmount: self.openMyModel.betAmount direction:direction];
                }else {
                    [cell setBeAmount: @"0" direction:0];
                }
                return  cell;
            }else {
                OptionHistoryTableViewCell *cell = [tableView dequeueReusableCellWithIdentifier:@"OptionHistoryTableViewCellId" forIndexPath:indexPath];
                cell.model = self.openMyModel;
                cell.isCurrent = YES;
                return  cell;
            }
        }
            break;
        case OptionTableCellTypeHistory: {
            OptionHistoryTableViewCell *cell = [tableView dequeueReusableCellWithIdentifier:@"OptionHistoryTableViewCellId" forIndexPath:indexPath];
            cell.model = self.historyList[indexPath.row];
            return  cell;
        }
            break;
            
        default:
            return nil;
            break;
    }
   
}

#pragma  mark - UITableViewDelegate
- (CGFloat)tableView:(UITableView *)tableView heightForRowAtIndexPath:(NSIndexPath *)indexPath {
    if (self.cellType == OptionTableCellTypeHistory)  {
        return  150;
    }
    if (self.cellType == OptionTableCellTypeTrade) {
        return 280;
    }
    if (self.cellType == OptionTableCellTypeCurrent) {
        return  indexPath.row == 0 ? 230 : 150;
    }
    return 280;
}

#pragma  mark - ContractExchangeSocketManagerDelegate
- (void)contractExchangeetdelegateSocket:(GCDAsyncSocket *)sock didReadData:(NSData *)data withTag:(long)tag{
    
    NSData *endData = [data subdataWithRange:NSMakeRange(SOCKETRESPONSE_LENGTH, data.length -SOCKETRESPONSE_LENGTH)];
    NSString *endStr = [[NSString alloc] initWithData:endData encoding:NSUTF8StringEncoding];
    NSData *cmdData = [data subdataWithRange:NSMakeRange(12,2)];
    uint16_t cmd = [SocketUtils uint16FromBytes:cmdData];
    
    if ( cmd == CONTRACT_PUSH_SYMBOL_THUMB) {
        
        NSDictionary*dic=[SocketUtils dictionaryWithJsonString:endStr];
        NSLog(@"%@",dic);
        if ([_symbol isEqualToString:dic[@"symbol"]]) {
            
            NSString *closeprice=dic[@"close"];
            if (self.currentCell) {
                [self.currentCell setCurrentPrice:closeprice];
            }
        }
   
    } else if (cmd==CONTRACT_PUSH_EXCHANGE_KLINE){
        self.headerView.symbol = self.symbol;
    }
}

#pragma mark - 左侧弹出菜单

-(void)LefttouchEvent{
    
    if (!self.menu) {
        self.menu = [[OptionLeftMenuViewController alloc]init];
        CGRect frame = self.menu.view.frame;
        frame.origin.x = - CGRectGetWidth(self.view.frame);
        self.menu.view.frame = CGRectMake(- CGRectGetWidth(self.view.frame), 0,  kWindowW, kWindowH);
        [[UIApplication sharedApplication].delegate.window addSubview:self.menu.view];
        MJWeakSelf
        self.menu.selcetContractcoinSymbolModelBlock = ^(OptionCoinModel * _Nonnull model) {
            weakSelf.infoModel = model;
            NSArray *numbers = [model.amount componentsSeparatedByString:@","];
            if (numbers.count > 0) {
                weakSelf.amountArray = numbers;
            }
         
            weakSelf.pageNo = 0;
            [weakSelf cancelContractSocket];
            weakSelf.currentCell.currentPrice = @"0.00";
            [weakSelf setChangedSymbol:model.symbol];
            [weakSelf setContractSoccket];
        };
    }else{
        self.menu.isObserverNotificantion=YES;
    }
    
    [self.menu showLeftContractMenu];
}

//MARK: - 上拉加载
- (void)refreshFooterAction{
    self.pageNo++;
    if (self.symbol.length > 0) {
        [self getOptionOrderSymbol:self.symbol pageNo: self.pageNo];
    }
}

#pragma  mark - innerMethod
/// 查询用户对应币种的余额
/// @param coin  名称
- (void)getCoinwalletwithcoin:(NSString*)coin {
    [TradeNetManager getwallettWithcoin:coin CompleteHandle:^(id resPonseObj, int code) {
        if (code) {
            if ([resPonseObj[@"code"] integerValue] == 0) {
                NSDictionary*dict = resPonseObj[@"data"];
                if (self.tradeCell) {
                    [self.tradeCell setAmount:dict[@"balance"]];
                }
            } else if ([resPonseObj[@"code"] integerValue] ==4000) {
                [YLUserInfo logout];
            } else{
                [self.view makeToast:resPonseObj[MESSAGE] duration:1.5 position:CSToastPositionCenter];
            }
        }else{
            [self.view makeToast:LocalizationKey(@"noNetworkStatus") duration:1.5 position:CSToastPositionCenter];
        }
    }];
}

//获取对应币种的详细信息
- (void)getOptionSymbolInfo:(NSString *)symbol {
    [OptionNetManager getOptionSymbolInfo:symbol CompleteHandle:^(id  _Nonnull resPonseObj, int code) {
        
        if (code) {
            if ([resPonseObj[@"code"] integerValue] == 0) {
                NSDictionary *dict = resPonseObj[@"data"];
                OptionCoinModel *model = [OptionCoinModel mj_objectWithKeyValues:dict];
                self.infoModel = model;
                NSArray *data = [self.infoModel.amount componentsSeparatedByString:@","];
                if (data.count > 0) {
                    self.amountArray = data;
                }
                if (self.cellType == OptionTableCellTypeTrade) {
                    [self.myTableView reloadData];
                }
            }else{
                [self.view makeToast:resPonseObj[MESSAGE] duration:1.5 position:CSToastPositionCenter];
            }
        }else{
            [self.view makeToast:LocalizationKey(@"noNetworkStatus") duration:1.5 position:CSToastPositionCenter];
        }
    }];
}

/// 获取我参与的预测
/// @param symbol  币种
/// @param optionId 期权 期数 id
- (void)getOptionOrderCurrentSymbol:(NSString *)symbol optionId:(NSString *)optionId {
    
    [OptionNetManager getOptionOrderCurrentSymbol:symbol optionId:optionId CompleteHandle:^(id  _Nonnull resPonseObj, int code) {
        if (code) {
            if ([resPonseObj[@"code"] integerValue] == 0) {
                NSArray*array=resPonseObj[@"data"];
                OptionOrderModel *model = nil;
                if (array.count > 0) {
                    model = [OptionOrderModel mj_objectWithKeyValues:array.firstObject];
                }
              
                if ([self.startModel.ID isEqualToString: optionId]) {
                    self.startMyModel = model;
                    [self.myTableView reloadData];

                }
                if ([self.openModel.ID isEqualToString: optionId]) {
                    self.openMyModel = model;
                    [self.myTableView reloadData];
                }
            }
            else if ([resPonseObj[@"code"] integerValue] ==4000){
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

//获取往期结果
- (void)OptionHistorySymbol:(NSString *)symbol{
    [OptionNetManager getOptionHistorySymbol:symbol CompleteHandle:^(id  _Nonnull resPonseObj, int code) {
        if (code) {
            if ([resPonseObj[@"code"] integerValue] == 0) {
                NSArray *arr=resPonseObj[@"data"][@"content"];
                if ([arr isKindOfClass:[NSArray class]]) {
                    NSArray * historyArr = [OptionModel mj_objectArrayWithKeyValuesArray:arr];
                    self.headerView.historyList = [[historyArr reverseObjectEnumerator] allObjects];
                }
            }
            else if ([resPonseObj[@"code"] integerValue] ==4000){
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

//获取正在进行中的预测
- (void)getOptionOpeningSymbol:(NSString *)symbol{
    [OptionNetManager getOptionOpeningSymbol:symbol CompleteHandle:^(id  _Nonnull resPonseObj, int code) {
        if (code) {
            id array = resPonseObj[@"data"];
            if ([array isKindOfClass:[NSArray class]]) {
                NSArray *arr = (NSArray *)array;
                OptionModel *model = [OptionModel mj_objectWithKeyValues:arr.firstObject ];
                self.openModel = model;
                if (YLUserInfo.isLogIn) {
                    [self getOptionOrderCurrentSymbol:self.symbol optionId:model.ID];
                }
                if (self.cellType == OptionTableCellTypeCurrent) {
                    [self.myTableView reloadData];
                }
            }
        }
       
    }];
}

//获取正在投注中的合约 要开始的预测
- (void)getOptionStartingSymbol:(NSString *)symbol{
    [OptionNetManager getOptionStartingSymbol:symbol CompleteHandle:^(id  _Nonnull resPonseObj, int code) {
        if (code) {
            id array = resPonseObj[@"data"];
            if ([array isKindOfClass:[NSArray class]]) {
                NSArray *arr = (NSArray *)array;
                OptionModel *model = [OptionModel mj_objectWithKeyValues:arr.firstObject ];
                self.startModel = model;
                if (YLUserInfo.isLogIn) {
                    [self getOptionOrderCurrentSymbol:self.symbol optionId:model.ID];
                }
                if (self.cellType == OptionTableCellTypeTrade) {
                    [self.myTableView reloadData];
                }
            }
        }
    }];
}
//获取历史交割 获取当前币种历史参与记录
- (void)getOptionOrderSymbol:(NSString *)symbol pageNo:(NSInteger)pageNo{
    [OptionNetManager getOptionOrderSymbol:symbol pageNo:pageNo CompleteHandle:^(id  _Nonnull resPonseObj, int code) {
        if ([self.myTableView.mj_footer isRefreshing]) {
            [self.myTableView.mj_footer endRefreshing];
        }
        
        if (code) {
            if ([resPonseObj[@"code"] intValue] == 0) {
                id array = resPonseObj[@"data"][@"content"];
                if ([array isKindOfClass:[NSArray class]]) {
                    NSArray *data = (NSArray *)array;
                    if (_pageNo == 0) {
                        [self.myTableView.mj_footer resetNoMoreData];
                        [self.historyList removeAllObjects];
                    }
                    
                    for (int i=0; i<data.count; i++) {
                        OptionOrderModel *model= [OptionOrderModel mj_objectWithKeyValues:data[i]];
                        [self.historyList addObject:model];
                    }
                    
                    if (data.count == 0) {
                        [self.myTableView.mj_footer endRefreshingWithNoMoreData];
                    }
                    if(self.cellType == OptionTableCellTypeHistory){
                        [self.myTableView reloadData];
                    }
                }
            } else if ([resPonseObj[@"code"] integerValue] == 4000){
                [YLUserInfo logout];
            }else{
                [self.view makeToast:resPonseObj[MESSAGE] duration:1.5 position:CSToastPositionCenter];
            }
        }
        else{
            [self.view makeToast:LocalizationKey(@"noNetworkStatus") duration:1.5 position:CSToastPositionCenter];
        }
    }];
}

//看涨看跌 下单
- (void)addOrder:(NSInteger)up {
    if (!YLUserInfo.isLogIn) {
        [self.view makeToast:LocalizationKey(@"please_login") duration:1.5 position:CSToastPositionBottom];
    }
    if (self.orderAmount.length > 0 && [self.orderAmount floatValue] > 0) {
        [OptionNetManager getOptionOrderAddSymbol:self.symbol optionId:self.startModel.ID direction:up amount:self.orderAmount CompleteHandle:^(id  _Nonnull resPonseObj, int code) {
            if (code) {
                
                if ([resPonseObj[@"code"] intValue] == 0) {
                    [self.view makeToast:resPonseObj[MESSAGE] duration:1.5 position:CSToastPositionCenter];
                    [self getOptionOrderCurrentSymbol:self.symbol optionId:self.startModel.ID];
                    [self getOptionStartingSymbol:self.symbol];
                    NSArray *arr = [self.symbol componentsSeparatedByString:@"/"];
                    [self getCoinwalletwithcoin:arr.lastObject];
                } else if ([resPonseObj[@"code"] integerValue] == 4000){
                    [YLUserInfo logout];
                }else{
                    [self.view makeToast:resPonseObj[MESSAGE] duration:1.5 position:CSToastPositionCenter];
                }
            }
            else{
                [self.view makeToast:LocalizationKey(@"noNetworkStatus") duration:1.5 position:CSToastPositionCenter];
            }
        }];
    }else {
        [self.view makeToast:LocalizationKey(@"please_amount") duration:1.5 position:CSToastPositionBottom];
    }
}

- (void)setContractSoccket{
    
    NSDictionary*dic;
    if ([YLUserInfo isLogIn]) {
        dic=[NSDictionary dictionaryWithObjectsAndKeys:_symbol,@"symbol",[YLUserInfo shareUserInfo].ID,@"uid",nil];
    }
    else{
        dic=[NSDictionary dictionaryWithObjectsAndKeys:_symbol,@"symbol",nil];
    }
    [[ContractExchangeSocketManager share] sendMsgWithLength:SOCKETREQUEST_LENGTH withsequenceId:0 withcmd:CONTRACT_SUBSCRIBE_SYMBOL_THUMB withVersion:COMMANDS_VERSION withRequestId:0 withbody:@{}];
    
    [[ContractExchangeSocketManager share] sendMsgWithLength:SOCKETREQUEST_LENGTH withsequenceId:0 withcmd:SUBSCRIBE_EXCHANGE_TRADE_CONTRACT withVersion:COMMANDS_VERSION withRequestId: 0 withbody:dic];
    [ContractExchangeSocketManager share].delegate = self;
    
}

- (void)cancelContractSocket{
    NSDictionary*dic;
    if ([YLUserInfo isLogIn]) {
        dic=[NSDictionary dictionaryWithObjectsAndKeys:_symbol,@"symbol",[YLUserInfo shareUserInfo].ID,@"uid",nil];
    }
    else{
        dic=[NSDictionary dictionaryWithObjectsAndKeys:_symbol,@"symbol",nil];
    }
    
    [[ContractExchangeSocketManager share] sendMsgWithLength:SOCKETREQUEST_LENGTH withsequenceId:0 withcmd:CONTRACT_UNSUBSCRIBE_SYMBOL_THUMB withVersion:COMMANDS_VERSION withRequestId:0 withbody:@{}];
    [[ContractExchangeSocketManager share] sendMsgWithLength:SOCKETREQUEST_LENGTH withsequenceId:0 withcmd:UNSUBSCRIBE_EXCHANGE_TRADE_CONTRACT withVersion:COMMANDS_VERSION withRequestId:0 withbody:dic];
    [ContractExchangeSocketManager share].delegate = nil;
}

#pragma mark - Getter or Setter
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

- (UITableView *)myTableView {
    if (!_myTableView) {
        if (!_myTableView) {
            _myTableView = [[UITableView alloc] initWithFrame:CGRectZero style:UITableViewStyleGrouped];
            _myTableView.tableHeaderView = self.headerView;
            _myTableView.dataSource = self;
            _myTableView.delegate = self;
            _myTableView.backgroundColor = mainColor;
            [_myTableView registerClass:[OptionTradeTableViewCell class] forCellReuseIdentifier:@"OptionTradeTableViewCellId"];
            [_myTableView registerClass:[OptionCurrentTableViewCell class] forCellReuseIdentifier:@"OptionCurrentTableViewCellId"];
            [_myTableView registerClass:[OptionHistoryTableViewCell class] forCellReuseIdentifier:@"OptionHistoryTableViewCellId"];
        
            [self.view addSubview:_myTableView];
        }
    }
    return  _myTableView;
}

- (OptionTableHeaderView *)headerView {
    if (!_headerView) {
        _headerView = [[OptionTableHeaderView alloc] initWithFrame:CGRectMake(0, 0, self.view.frame.size.width, 315)];
        __weak typeof(self)weskself = self;
        _headerView.bottomDidClick = ^(OptionTableCellType type) {
            weskself.cellType = type;
       
            if (type == OptionTableCellTypeHistory) {
                [weskself footRefreshWithScrollerView:weskself.myTableView];
            }else {
                weskself.myTableView.mj_footer = nil;
            }
            if (weskself.cellType == OptionTableCellTypeTrade) {
                [weskself getOptionStartingSymbol:weskself.symbol];
            }else if (weskself.cellType == OptionTableCellTypeCurrent){
                [weskself getOptionOpeningSymbol:weskself.symbol];
            }
            
            [weskself.myTableView reloadData];
        };
  
        _headerView.didClick = ^(OptionModel * model) {
            [OptionPopView showWithModel:model];
        };
    }
    return  _headerView;
}

- (NSMutableArray *)historyList {
    if (!_historyList) {
        _historyList = [NSMutableArray array];
    }
    return _historyList;
}

- (NSArray *)amountArray {
    if (!_amountArray) {
        _amountArray = @[@"10",@"20",@"50",@"100",@"200",@"500",@"1000",@"2000"];
    }
    return _amountArray;
}

@end
