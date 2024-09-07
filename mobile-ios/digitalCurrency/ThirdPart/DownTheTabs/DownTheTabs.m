    //
//  DownTheTabs.m
//  digitalCurrency
//
//  Created by chu on 2019/8/7.
//  Copyright © 2019年 BIZZAN. All rights reserved.
//

#import "DownTheTabs.h"
#import "PGDatePickManager.h"
@interface DownTheTabs()<PGDatePickerDelegate>

{
    HJDropDownMenu *_symbolMenu;
    HJDropDownMenu *_typeMenu;
    HJDropDownMenu *_directionMenu;
    NSString *_symbolTitle;
    NSString *_typeTitle;
    NSString *_directionTitle;
    NSString *_startTimeTitle;
    NSString *_endTimeTitle;
    UIButton *_startBtn;
    UIButton *_endBtn;
    NSArray *_symbols;
    PGDatePicker *_startPicker;
    PGDatePicker *_endPicker;

    //资产流水
    NSString *_assetFlowTypeTitle;
    HJDropDownMenu *_assetFlowTypeMenu;

    //交易挖矿
    NSString *_tradeMiningProjectTitle;
    HJDropDownMenu *_tradeMiningProjectMenu;

    //提币记录
    NSString *_currencyRecordTitle;
    HJDropDownMenu *_currencyRecordMenu;

    //币理财
    NSString *_moneyManagementTitle;
    HJDropDownMenu *_moneyManagementMenu;

    //币竞猜
    NSString *_CoinguessitypeTitle;//类型
    NSString *_CoinguessiPeriodTitle;//期次
    UITextField *_textFied;
    HJDropDownMenu *_CoinguessingMenu;
}

@end

#define tabsBackColor  RGBOF(0x1A212A)

@implementation DownTheTabs

- (void)tabsDismiss{
    [self dismissAction];
}

- (void)dismissAction{
    if (self.dismissBlock) {
        self.dismissBlock();
    }
    [self removeFromSuperview];
}

#pragma mark - 委托记录筛选
- (instancetype)initEntrustTabsWithContainerView:(UIView *)containerView Symbols:(NSArray *)symbols{
    if (self = [super init]) {
        _symbolTitle = @"";
        _typeTitle = @"";
        _directionTitle = @"";
        _startTimeTitle = @"";
        _endTimeTitle = @"";
        self.frame = CGRectMake(0, 0, kWindowW, kWindowH - NEW_NavHeight);
        self.backgroundColor = [[UIColor blackColor] colorWithAlphaComponent:0.4f];
        [self addEntrustSubViewsWithSymbols:symbols];
        [containerView addSubview:self];
    }
    return self;
}

- (void)addEntrustSubViewsWithSymbols:(NSArray *)symbols{
    UIView *containerView = [[UIView alloc] init];
    containerView.tag = 1987652;
    containerView.backgroundColor = tabsBackColor;
    [self addSubview:containerView];
    [containerView mas_makeConstraints:^(MASConstraintMaker *make) {
        make.left.equalTo(self).with.offset(0);
        make.top.equalTo(self).with.offset(0);
        make.right.equalTo(self).with.offset(0);
        make.height.mas_equalTo(479);
    }];


    UILabel *symbolLabel = [self creatLabelWithTitle:LocalizationKey(@"tradePair")];
    [containerView addSubview:symbolLabel];
    [symbolLabel mas_makeConstraints:^(MASConstraintMaker *make) {
        make.left.equalTo(containerView).with.offset(15);
        make.top.equalTo(containerView).with.offset(26);
        make.right.equalTo(containerView).with.offset(-15);
        make.height.mas_equalTo(15);
    }];

    HJDropDownMenu *symbolMenu = [self creatMenuWithTitles:symbols];
    symbolMenu.cellClickedBlock = ^(NSString *title, NSInteger index) {
        _symbolTitle = title;
    };
    _symbolMenu = symbolMenu;
    symbolMenu.placeHolde = LocalizationKey(@"PleaseEnterTheTransactionPair");
    __weak typeof(symbolMenu)weakSym = symbolMenu;
    symbolMenu.headerClickedBlock = ^{
        [_directionMenu closeMenu];
        [_typeMenu closeMenu];
        [containerView bringSubviewToFront:weakSym];

    };
    [containerView addSubview:symbolMenu];
    [symbolMenu mas_makeConstraints:^(MASConstraintMaker *make) {
        make.left.equalTo(containerView).with.offset(15);
        make.top.equalTo(symbolLabel.mas_bottom).with.offset(12);
        make.right.equalTo(containerView).with.offset(-15);
        make.height.mas_equalTo(35);
    }];
    [containerView bringSubviewToFront:symbolMenu];

    UILabel *typeLabel = [self creatLabelWithTitle:LocalizationKey(@"type")];
    [containerView addSubview:typeLabel];
    [typeLabel mas_makeConstraints:^(MASConstraintMaker *make) {
        make.left.equalTo(containerView).with.offset(15);
        make.top.equalTo(symbolMenu.mas_bottom).with.offset(20);
        make.right.equalTo(containerView).with.offset(-15);
        make.height.mas_equalTo(15);
    }];

    HJDropDownMenu *typeMenu = [self creatMenuWithTitles:@[LocalizationKey(@"marketPrice"), LocalizationKey(@"limitPrice")]];
    typeMenu.cellClickedBlock = ^(NSString *title, NSInteger index) {
        if ([title isEqualToString:@"市价"]) {
            _typeTitle = @"MARKET_PRICE";
        }else{
            _typeTitle = @"LIMIT_PRICE";
        }
    };
    _typeMenu = typeMenu;
    typeMenu.placeHolde = LocalizationKey(@"PleaseEnterTheType");
    __weak typeof(typeMenu)weaktype = typeMenu;
    typeMenu.headerClickedBlock = ^{
        [_symbolMenu closeMenu];
        [_directionMenu closeMenu];
        [containerView bringSubviewToFront:weaktype];
    };
    [containerView addSubview:typeMenu];
    [typeMenu mas_makeConstraints:^(MASConstraintMaker *make) {
        make.left.equalTo(containerView).with.offset(15);
        make.top.equalTo(typeLabel.mas_bottom).with.offset(12);
        make.right.equalTo(containerView).with.offset(-15);
        make.height.mas_equalTo(35);
    }];

    UILabel *directionLabel = [self creatLabelWithTitle:LocalizationKey(@"tradeDirection")];
    [containerView addSubview:directionLabel];
    [directionLabel mas_makeConstraints:^(MASConstraintMaker *make) {
        make.left.equalTo(containerView).with.offset(15);
        make.top.equalTo(typeMenu.mas_bottom).with.offset(20);
        make.right.equalTo(containerView).with.offset(-15);
        make.height.mas_equalTo(15);
    }];

    HJDropDownMenu *directionMenu = [self creatMenuWithTitles:@[LocalizationKey(@"Buy"), LocalizationKey(@"Sell")]];
    directionMenu.cellClickedBlock = ^(NSString *title, NSInteger index) {
        if ([title isEqualToString:LocalizationKey(@"Buy")]) {
            _directionTitle = @"0";
        }else{
            _directionTitle = @"1";
        }
    };
    _directionMenu = directionMenu;
    directionMenu.placeHolde = LocalizationKey(@"PleaseEnterTheDirection");
    __weak typeof(directionMenu)weakDirection = directionMenu;
    directionMenu.headerClickedBlock = ^{
        [_symbolMenu closeMenu];
        [_typeMenu closeMenu];
        [containerView bringSubviewToFront:weakDirection];
    };
    [containerView addSubview:directionMenu];
    [directionMenu mas_makeConstraints:^(MASConstraintMaker *make) {
        make.left.equalTo(containerView).with.offset(15);
        make.top.equalTo(directionLabel.mas_bottom).with.offset(12);
        make.right.equalTo(containerView).with.offset(-15);
        make.height.mas_equalTo(35);
    }];

    UILabel *timeLabel = [self creatLabelWithTitle:LocalizationKey(@"Startandstoptime")];
    [containerView addSubview:timeLabel];
    [timeLabel mas_makeConstraints:^(MASConstraintMaker *make) {
        make.left.equalTo(containerView).with.offset(15);
        make.top.equalTo(directionMenu.mas_bottom).with.offset(20);
        make.right.equalTo(containerView).with.offset(-15);
        make.height.mas_equalTo(15);
    }];

    UIButton *startBtn = [self creatTimeBtnWithTag:0];
    _startBtn = startBtn;
    [containerView addSubview:startBtn];
    [startBtn mas_makeConstraints:^(MASConstraintMaker *make) {
        make.left.equalTo(containerView).with.offset(15);
        make.top.equalTo(timeLabel.mas_bottom).with.offset(12);
        make.right.equalTo(containerView).with.offset(-15);
        make.height.mas_equalTo(35);
    }];

    UIButton *endBtn = [self creatTimeBtnWithTag:1];
    _endBtn = endBtn;
    [containerView addSubview:endBtn];
    [endBtn mas_makeConstraints:^(MASConstraintMaker *make) {
        make.left.equalTo(containerView).with.offset(15);
        make.top.equalTo(startBtn.mas_bottom).with.offset(10);
        make.right.equalTo(containerView).with.offset(-15);
        make.height.mas_equalTo(35);
    }];

    UIView *bottomView = [self bottomCancleBtn];
    [containerView addSubview:bottomView];
    [bottomView mas_makeConstraints:^(MASConstraintMaker *make) {
        make.left.equalTo(containerView).with.offset(15);
        make.top.equalTo(endBtn.mas_bottom).with.offset(35);
        make.right.equalTo(containerView).with.offset(-15);
        make.height.mas_equalTo(40);
    }];

}

- (UILabel *)creatLabelWithTitle:(NSString *)title{
    UILabel *label = [[UILabel alloc] init];
    label.text = title;
    label.textColor = RGBOF(0xe6e6e6);
    label.font = [UIFont fontWithName:@"PingFangSC-Medium" size:14];
    return label;
}

- (HJDropDownMenu *)creatMenuWithTitles:(NSArray *)titles{
    HJDropDownMenu * yearMenu = [[HJDropDownMenu alloc] init];
    yearMenu.backgroundColor = RGBOF(0x1F2833);
    yearMenu.rowHeight = 35;
    yearMenu.datas = titles;
    yearMenu.textColor = RGBOF(0x999999);
    return yearMenu;
}

- (UIButton *)creatTimeBtnWithTag:(NSInteger)tag{
    NSArray *titles = @[LocalizationKey(@"startTime"), LocalizationKey(@"endTime")];
    UIButton *headerBtn = [[UIButton alloc] init];
    headerBtn.tag = tag;
    headerBtn.layer.cornerRadius = 5;
    headerBtn.layer.borderColor = RGBOF(0x1F2833).CGColor;
    headerBtn.layer.borderWidth = 0.5;

    [headerBtn setBackgroundColor:RGBOF(0x1F2833)];
    headerBtn.titleLabel.font = [UIFont systemFontOfSize:12];
    [headerBtn setTitleColor:RGBOF(0x999999) forState:UIControlStateNormal];
    [headerBtn setTitle:titles[tag] forState:UIControlStateNormal];
    [headerBtn setContentHorizontalAlignment:UIControlContentHorizontalAlignmentLeft];
    [headerBtn setTitleEdgeInsets:UIEdgeInsetsMake(0, 15, 0, 0)];
    [headerBtn addTarget:self action:@selector(timeBtnClicked:) forControlEvents:UIControlEventTouchUpInside];
    return headerBtn;
}


- (void)timeBtnClicked:(UIButton *)sender{
    if (sender.tag == 0) {
        //开始时间
        [self staretimeaction];
    }else{
        //结束时间
        [self endtimeaction];
    }
}


#pragma mark - 底部确定和重置按钮
- (UIView *)bottomCancleBtn{
    UIView *view = [[UIView alloc] init];
    NSArray *titles = @[LocalizationKey(@"Reset"), LocalizationKey(@"ok")];
    NSMutableArray *muArr = [NSMutableArray arrayWithCapacity:0];
    for (int i = 0; i < 2; i++) {
        UIButton *headerBtn = [[UIButton alloc] init];
        headerBtn.tag = i;
        headerBtn.layer.cornerRadius = 20;
        if (i == 0) {
            [headerBtn setBackgroundColor:RGBOF(0x213144)];
            [headerBtn setTitleColor:RGBOF(0xe6e6e6) forState:UIControlStateNormal];
        }else{
            [headerBtn setBackgroundColor:RGBOF(0xF0A70A)];
            [headerBtn setTitleColor:RGBOF(0xe6e6e6) forState:UIControlStateNormal];
        }
        headerBtn.titleLabel.font = [UIFont systemFontOfSize:15];
        [headerBtn setTitle:titles[i] forState:UIControlStateNormal];
        [headerBtn addTarget:self action:@selector(cancleOrResetClicked:) forControlEvents:UIControlEventTouchUpInside];
        [muArr addObject:headerBtn];
        [view addSubview:headerBtn];
    }

    [muArr mas_distributeViewsAlongAxis:MASAxisTypeHorizontal withFixedSpacing:10 leadSpacing:0 tailSpacing:0];
    [muArr mas_makeConstraints:^(MASConstraintMaker *make) {
        make.centerY.mas_equalTo(view.centerY);
        make.height.mas_equalTo(40);
    }];
    return view;
}

#pragma mark - 确定和重置按钮
- (void)cancleOrResetClicked:(UIButton *)sender{
    if (sender.tag == 0) {
        //重置
        [self clear];
    }else{
        if (_symbolMenu) {
            if (self.entrustBlock) {
                self.entrustBlock(_symbolTitle, _typeTitle, _directionTitle, _startTimeTitle, _endTimeTitle);
            }
        }else if (_assetFlowTypeMenu)
        {
            if (self.assetFlowBlock) {
                self.assetFlowBlock(_assetFlowTypeTitle, _startTimeTitle, _endTimeTitle);
            }
        }else if (_tradeMiningProjectMenu){
            if (self.tradeMiningBlock) {
                self.tradeMiningBlock(_startTimeTitle, _endTimeTitle);
            }
        }else if (_currencyRecordMenu){
            if (self.currencyRecordBlock) {
                self.currencyRecordBlock(_currencyRecordTitle);
            }
        }else if (_moneyManagementMenu){
            if (self.moneyManagementBlock) {
                self.moneyManagementBlock(_startTimeTitle, _moneyManagementTitle);
            }
        }else if (_CoinguessingMenu){
            if (self.CoinguessingBlock) {
                self.CoinguessingBlock(_startTimeTitle,_endTimeTitle, _CoinguessitypeTitle, _textFied.text);
            }
        }
        [self dismissAction];
    }
}

- (void)clear{
    //委托记录
    if (_symbolMenu) {
        _symbolMenu.placeHolde = LocalizationKey(@"PleaseEnterTheTransactionPair");
        _typeMenu.placeHolde = LocalizationKey(@"PleaseEnterTheType");
        _directionMenu.placeHolde = LocalizationKey(@"PleaseEnterTheDirection");
        [_startBtn setTitle:LocalizationKey(@"startTime") forState:UIControlStateNormal];
        [_endBtn setTitle:LocalizationKey(@"endTime") forState:UIControlStateNormal];
        _symbolTitle = @"";
        _typeTitle = @"";
        _directionTitle = @"";
        _startTimeTitle = @"";
        _endTimeTitle = @"";
    }

    //资产流水
    if (_assetFlowTypeMenu) {
        _assetFlowTypeMenu.placeHolde = LocalizationKey(@"PleaseEnterTheType");
        [_startBtn setTitle:LocalizationKey(@"startTime") forState:UIControlStateNormal];
        [_endBtn setTitle:LocalizationKey(@"endTime") forState:UIControlStateNormal];
        _assetFlowTypeTitle = @"";
        _startTimeTitle = @"";
        _endTimeTitle = @"";
    }

    //交易挖矿
    if (_tradeMiningProjectMenu) {
        [_startBtn setTitle:LocalizationKey(@"startTime") forState:UIControlStateNormal];
        [_endBtn setTitle:LocalizationKey(@"endTime") forState:UIControlStateNormal];
        _startTimeTitle = @"";
        _endTimeTitle = @"";
    }

    //币理财
    if (_moneyManagementMenu) {
        [_startBtn setTitle:LocalizationKey(@"EnterYouWantChooseTime") forState:UIControlStateNormal];
        _moneyManagementMenu.placeHolde = LocalizationKey(@"select");
        _moneyManagementTitle = @"";
        _startTimeTitle = @"";
    }

    //提币记录
    if (_currencyRecordMenu) {
        _currencyRecordMenu.placeHolde = LocalizationKey(@"PleaseEnterTheTransactionPair");
        _currencyRecordTitle = @"";
    }

    //币竞猜
    if (_CoinguessingMenu) {
        _CoinguessingMenu.placeHolde = LocalizationKey(@"PleaseEnterTheType");
        [_startBtn setTitle:LocalizationKey(@"startTime") forState:UIControlStateNormal];
        [_endBtn setTitle:LocalizationKey(@"endTime") forState:UIControlStateNormal];

        _textFied.text = @"";
        _CoinguessiPeriodTitle = @"";
        _CoinguessitypeTitle = @"";
    }
}

#pragma PGDatePickerDelegate
- (void)datePicker:(PGDatePicker *)datePicker didSelectDate:(NSDateComponents *)dateComponents {
    NSString *timestr = [NSString stringWithFormat:@"%ld-%ld-%ld %ld",[dateComponents year],[dateComponents month],[dateComponents day],[dateComponents hour]];
    NSString *timestring = [NSString stringWithFormat:@"%ld-%ld-%ld %ld:00:00",[dateComponents year],[dateComponents month],[dateComponents day],[dateComponents hour]];
    NSString *shijianchuo = [ToolUtil timeToTimeIntervalString:timestring WithDateFormat:@"yyyy-MM-dd HH:mm:ss"];
    if (datePicker == _startPicker) {
        [_startBtn setTitle:timestring forState:UIControlStateNormal];
        _startTimeTitle = shijianchuo;
    }else{
        [_endBtn setTitle:timestring forState:UIControlStateNormal];
        _endTimeTitle = shijianchuo;
    }
}

#pragma mark - 资产流水 选项卡初始化方法
- (instancetype)initAssetFlowTabsWithContainerView:(UIView *)containerView Types:(NSArray *)types{
    if (self = [super init]) {
        _assetFlowTypeTitle = @"";
        _startTimeTitle = @"";
        _endTimeTitle = @"";
        self.frame = CGRectMake(0, 0, kWindowW, kWindowH - NEW_NavHeight);
        self.backgroundColor = [[UIColor blackColor] colorWithAlphaComponent:0.4f];
        [self addEntrustSubViewsWithTypes:types];
        [containerView addSubview:self];
    }
    return self;
}

- (void)addEntrustSubViewsWithTypes:(NSArray *)types{
    UIView *containerView = [[UIView alloc] init];
    containerView.tag = 1987652;
    containerView.backgroundColor = tabsBackColor;
    [self addSubview:containerView];
    [containerView mas_makeConstraints:^(MASConstraintMaker *make) {
        make.left.equalTo(self).with.offset(0);
        make.top.equalTo(self).with.offset(0);
        make.right.equalTo(self).with.offset(0);
        make.height.mas_equalTo(337);
    }];

    UILabel *symbolLabel = [self creatLabelWithTitle:LocalizationKey(@"type")];
    [containerView addSubview:symbolLabel];
    [symbolLabel mas_makeConstraints:^(MASConstraintMaker *make) {
        make.left.equalTo(containerView).with.offset(15);
        make.top.equalTo(containerView).with.offset(26);
        make.right.equalTo(containerView).with.offset(-15);
        make.height.mas_equalTo(15);
    }];

    HJDropDownMenu *symbolMenu = [self creatMenuWithTitles:types];
    symbolMenu.cellClickedBlock = ^(NSString *title, NSInteger index) {
        _assetFlowTypeTitle = title;
    };
    _assetFlowTypeMenu = symbolMenu;
    symbolMenu.placeHolde = LocalizationKey(@"PleaseEnterTheType");
    __weak typeof(symbolMenu)weakSym = symbolMenu;
    symbolMenu.headerClickedBlock = ^{
        [containerView bringSubviewToFront:weakSym];
    };
    [containerView addSubview:symbolMenu];
    [symbolMenu mas_makeConstraints:^(MASConstraintMaker *make) {
        make.left.equalTo(containerView).with.offset(15);
        make.top.equalTo(symbolLabel.mas_bottom).with.offset(12);
        make.right.equalTo(containerView).with.offset(-15);
        make.height.mas_equalTo(35);
    }];
    [containerView bringSubviewToFront:symbolMenu];

    UILabel *timeLabel = [self creatLabelWithTitle:LocalizationKey(@"Startandstoptime")];
    [containerView addSubview:timeLabel];
    [timeLabel mas_makeConstraints:^(MASConstraintMaker *make) {
        make.left.equalTo(containerView).with.offset(15);
        make.top.equalTo(symbolMenu.mas_bottom).with.offset(20);
        make.right.equalTo(containerView).with.offset(-15);
        make.height.mas_equalTo(15);
    }];

    UILabel *subTimeLabel = [self creatLabelWithTitle:LocalizationKey(@"startAndEndTime")];
    subTimeLabel.textColor = RGBOF(0x929294);
    subTimeLabel.font = [UIFont fontWithName:@"PingFangSC-Medium" size:11];

    [containerView addSubview:subTimeLabel];
    [subTimeLabel mas_makeConstraints:^(MASConstraintMaker *make) {
        make.left.equalTo(containerView).with.offset(15);
        make.top.equalTo(timeLabel.mas_bottom).with.offset(7);
        make.right.equalTo(containerView).with.offset(-15);
        make.height.mas_equalTo(12);
    }];

    UIButton *startBtn = [self creatTimeBtnWithTag:0];
    _startBtn = startBtn;
    [containerView addSubview:startBtn];
    [startBtn mas_makeConstraints:^(MASConstraintMaker *make) {
        make.left.equalTo(containerView).with.offset(15);
        make.top.equalTo(subTimeLabel.mas_bottom).with.offset(14);
        make.right.equalTo(containerView).with.offset(-15);
        make.height.mas_equalTo(35);
    }];

    UIButton *endBtn = [self creatTimeBtnWithTag:1];
    _endBtn = endBtn;
    [containerView addSubview:endBtn];
    [endBtn mas_makeConstraints:^(MASConstraintMaker *make) {
        make.left.equalTo(containerView).with.offset(15);
        make.top.equalTo(startBtn.mas_bottom).with.offset(10);
        make.right.equalTo(containerView).with.offset(-15);
        make.height.mas_equalTo(35);
    }];

    UIView *bottomView = [self bottomCancleBtn];
    [containerView addSubview:bottomView];
    [bottomView mas_makeConstraints:^(MASConstraintMaker *make) {
        make.left.equalTo(containerView).with.offset(15);
        make.top.equalTo(endBtn.mas_bottom).with.offset(35);
        make.right.equalTo(containerView).with.offset(-15);
        make.height.mas_equalTo(40);
    }];

}

#pragma mark - 挖矿交易选项卡
- (instancetype)initTradeMiningTabsWithContainerView:(UIView *)containerView Projects:(NSArray *)project{
    if (self = [super init]) {
        _tradeMiningProjectTitle = @"";
        _startTimeTitle = @"";
        _endTimeTitle = @"";
        self.frame = CGRectMake(0, 0, kWindowW, kWindowH - NEW_NavHeight);
        self.backgroundColor = [[UIColor blackColor] colorWithAlphaComponent:0.4f];
        [self addTradeMiningSubViewsWithProjicts:project];
        [containerView addSubview:self];
    }
    return self;
}

- (void)addTradeMiningSubViewsWithProjicts:(NSArray *)project{
    UIView *containerView = [[UIView alloc] init];
    containerView.tag = 1987652;
    containerView.backgroundColor = tabsBackColor;
    [self addSubview:containerView];
    [containerView mas_makeConstraints:^(MASConstraintMaker *make) {
        make.left.equalTo(self).with.offset(0);
        make.top.equalTo(self).with.offset(0);
        make.right.equalTo(self).with.offset(0);
        make.height.mas_equalTo(337);
    }];
    //此初始化方法只是为了出发回调
    _tradeMiningProjectMenu = [HJDropDownMenu new];

    UILabel *timeLabel = [self creatLabelWithTitle:LocalizationKey(@"Startandstoptime")];
    [containerView addSubview:timeLabel];
    [timeLabel mas_makeConstraints:^(MASConstraintMaker *make) {
        make.left.equalTo(containerView).with.offset(15);
        make.top.equalTo(containerView).with.offset(20);
        make.right.equalTo(containerView).with.offset(-15);
        make.height.mas_equalTo(15);
    }];

    UIButton *startBtn = [self creatTimeBtnWithTag:0];
    _startBtn = startBtn;
    [containerView addSubview:startBtn];
    [startBtn mas_makeConstraints:^(MASConstraintMaker *make) {
        make.left.equalTo(containerView).with.offset(15);
        make.top.equalTo(timeLabel.mas_bottom).with.offset(14);
        make.right.equalTo(containerView).with.offset(-15);
        make.height.mas_equalTo(35);
    }];

    UIButton *endBtn = [self creatTimeBtnWithTag:1];
    _endBtn = endBtn;
    [containerView addSubview:endBtn];
    [endBtn mas_makeConstraints:^(MASConstraintMaker *make) {
        make.left.equalTo(containerView).with.offset(15);
        make.top.equalTo(startBtn.mas_bottom).with.offset(10);
        make.right.equalTo(containerView).with.offset(-15);
        make.height.mas_equalTo(35);
    }];

    UIView *bottomView = [self bottomCancleBtn];
    [containerView addSubview:bottomView];
    [bottomView mas_makeConstraints:^(MASConstraintMaker *make) {
        make.left.equalTo(containerView).with.offset(15);
    make.bottom.equalTo(containerView.mas_bottom).with.offset(-20);
        make.right.equalTo(containerView).with.offset(-15);
        make.height.mas_equalTo(40);
    }];

}
#pragma mark - 提币记录选项卡
- (instancetype)initCurrencyRecordTabsWithContainerView:(UIView *)containerView Projects:(NSArray *)project{
    if (self = [super init]) {
        _currencyRecordTitle = @"";
        _startTimeTitle = @"";
        _endTimeTitle = @"";
        self.frame = CGRectMake(0, 0, kWindowW, kWindowH - NEW_NavHeight);
        self.backgroundColor = [[UIColor blackColor] colorWithAlphaComponent:0.4f];
        [self addCurrencyRecordSubViewsWithProjicts:project];
        [containerView addSubview:self];
    }
    return self;
}

- (void)addCurrencyRecordSubViewsWithProjicts:(NSArray *)project{
    UIView *containerView = [[UIView alloc] init];
    containerView.tag = 1987652;
    containerView.backgroundColor = tabsBackColor;
    [self addSubview:containerView];
    [containerView mas_makeConstraints:^(MASConstraintMaker *make) {
        make.left.equalTo(self).with.offset(0);
        make.top.equalTo(self).with.offset(0);
        make.right.equalTo(self).with.offset(0);
        make.height.mas_equalTo(337);
    }];


    UILabel *symbolLabel = [self creatLabelWithTitle:LocalizationKey(@"tradePair")];
    [containerView addSubview:symbolLabel];
    [symbolLabel mas_makeConstraints:^(MASConstraintMaker *make) {
        make.left.equalTo(containerView).with.offset(15);
        make.top.equalTo(containerView).with.offset(26);
        make.right.equalTo(containerView).with.offset(-15);
        make.height.mas_equalTo(15);
    }];

    HJDropDownMenu *symbolMenu = [self creatMenuWithTitles:project];
    _currencyRecordMenu = symbolMenu;
    symbolMenu.cellClickedBlock = ^(NSString *title, NSInteger index) {
        _currencyRecordTitle = title;
    };
    symbolMenu.placeHolde = LocalizationKey(@"PleaseEnterTheTransactionPair");
    __weak typeof(symbolMenu)weakSym = symbolMenu;
    symbolMenu.headerClickedBlock = ^{
        [containerView bringSubviewToFront:weakSym];
    };
    [containerView addSubview:symbolMenu];
    [symbolMenu mas_makeConstraints:^(MASConstraintMaker *make) {
        make.left.equalTo(containerView).with.offset(15);
        make.top.equalTo(symbolLabel.mas_bottom).with.offset(12);
        make.right.equalTo(containerView).with.offset(-15);
        make.height.mas_equalTo(35);
    }];
    [containerView bringSubviewToFront:symbolMenu];

    UIView *bottomView = [self bottomCancleBtn];
    [containerView addSubview:bottomView];
    [bottomView mas_makeConstraints:^(MASConstraintMaker *make) {
        make.left.equalTo(containerView).with.offset(15);
    make.bottom.equalTo(containerView.mas_bottom).with.offset(-20);
        make.right.equalTo(containerView).with.offset(-15);
        make.height.mas_equalTo(40);
    }];
}

#pragma mark - 币理财选项卡
- (instancetype)initMoneyManagementTabsWithContainerView:(UIView *)containerView Projects:(NSArray *)project{
    if (self = [super init]) {
        _moneyManagementTitle = @"";
        _startTimeTitle = @"";
        self.frame = CGRectMake(0, 0, kWindowW, kWindowH - NEW_NavHeight);
        self.backgroundColor = [[UIColor blackColor] colorWithAlphaComponent:0.4f];
        [self addMoneyManagementSubViewsWithProjicts:project];
        [containerView addSubview:self];
    }
    return self;
}

- (void)addMoneyManagementSubViewsWithProjicts:(NSArray *)project{
    UIView *containerView = [[UIView alloc] init];
    containerView.tag = 1987652;
    containerView.backgroundColor = tabsBackColor;
    [self addSubview:containerView];
    [containerView mas_makeConstraints:^(MASConstraintMaker *make) {
        make.left.equalTo(self).with.offset(0);
        make.top.equalTo(self).with.offset(0);
        make.right.equalTo(self).with.offset(0);
        make.height.mas_equalTo(337);
    }];

    UILabel *timeLabel = [self creatLabelWithTitle:LocalizationKey(@"LockTime")];
    [containerView addSubview:timeLabel];
    [timeLabel mas_makeConstraints:^(MASConstraintMaker *make) {
        make.left.equalTo(containerView).with.offset(15);
        make.top.equalTo(containerView).with.offset(26);
        make.right.equalTo(containerView).with.offset(-15);
        make.height.mas_equalTo(15);
    }];


    UIButton *headerBtn = [[UIButton alloc] init];
    headerBtn.tag = 0;
    headerBtn.layer.cornerRadius = 5;
    headerBtn.layer.borderColor = RGBOF(0xd9d9d9).CGColor;
    headerBtn.layer.borderWidth = 0.5;
    [headerBtn setBackgroundColor:[UIColor whiteColor]];
    headerBtn.titleLabel.font = [UIFont systemFontOfSize:12];
    [headerBtn setTitleColor:RGBOF(0x929292) forState:UIControlStateNormal];
    [headerBtn setTitle:LocalizationKey(@"EnterYouWantChooseTime") forState:UIControlStateNormal];
    [headerBtn setContentHorizontalAlignment:UIControlContentHorizontalAlignmentLeft];
    [headerBtn setTitleEdgeInsets:UIEdgeInsetsMake(0, 15, 0, 0)];
    [headerBtn addTarget:self action:@selector(timeBtnClicked:) forControlEvents:UIControlEventTouchUpInside];
    _startBtn = headerBtn;
    [containerView addSubview:headerBtn];
    [headerBtn mas_makeConstraints:^(MASConstraintMaker *make) {
        make.left.equalTo(containerView).with.offset(15);
        make.top.equalTo(timeLabel.mas_bottom).with.offset(15);
        make.right.equalTo(containerView).with.offset(-15);
        make.height.mas_equalTo(35);
    }];

    UILabel *symbolLabel = [self creatLabelWithTitle:LocalizationKey(@"project")];
    [containerView addSubview:symbolLabel];
    [symbolLabel mas_makeConstraints:^(MASConstraintMaker *make) {
        make.left.equalTo(containerView).with.offset(15);
        make.top.equalTo(headerBtn.mas_bottom).with.offset(20);
        make.right.equalTo(containerView).with.offset(-15);
        make.height.mas_equalTo(15);
    }];

    HJDropDownMenu *symbolMenu = [self creatMenuWithTitles:project];
    symbolMenu.cellClickedBlock = ^(NSString *title, NSInteger index) {
        _moneyManagementTitle = title;
    };
    _moneyManagementMenu = symbolMenu;
    symbolMenu.placeHolde = LocalizationKey(@"select");
    __weak typeof(symbolMenu)weakSym = symbolMenu;
    symbolMenu.headerClickedBlock = ^{
        [containerView bringSubviewToFront:weakSym];
    };
    [containerView addSubview:symbolMenu];
    [symbolMenu mas_makeConstraints:^(MASConstraintMaker *make) {
        make.left.equalTo(containerView).with.offset(15);
        make.top.equalTo(symbolLabel.mas_bottom).with.offset(15);
        make.right.equalTo(containerView).with.offset(-15);
        make.height.mas_equalTo(35);
    }];
    [containerView bringSubviewToFront:symbolMenu];


    UIView *bottomView = [self bottomCancleBtn];
    [containerView addSubview:bottomView];
    [bottomView mas_makeConstraints:^(MASConstraintMaker *make) {
        make.left.equalTo(containerView).with.offset(15);
        make.top.equalTo(symbolMenu.mas_bottom).with.offset(100);
        make.right.equalTo(containerView).with.offset(-15);
        make.height.mas_equalTo(40);
    }];

}

#pragma mark - 币竞猜选项卡
- (instancetype)initCoinguessingView:(UIView *)containerView Projects:(NSArray *)project{
    if (self = [super init]) {
       _CoinguessitypeTitle = @"";
        _CoinguessiPeriodTitle = @"";
        _startTimeTitle = @"";
        self.frame = CGRectMake(0, 0, kWindowW, kWindowH - NEW_NavHeight);
        self.backgroundColor = [[UIColor blackColor] colorWithAlphaComponent:0.4f];
        [self addCoinguessingSubViewsWithProjicts:project];
        [containerView addSubview:self];
    }
    return self;
}

-(void)addCoinguessingSubViewsWithProjicts:(NSArray *)project{
    UIView *containerView = [[UIView alloc] init];
    containerView.tag = 1987652;
    containerView.backgroundColor = tabsBackColor;
    [self addSubview:containerView];
    [containerView mas_makeConstraints:^(MASConstraintMaker *make) {
        make.left.equalTo(self).with.offset(0);
        make.top.equalTo(self).with.offset(0);
        make.right.equalTo(self).with.offset(0);
        make.height.mas_equalTo(400);
    }];

    UILabel *typeLabel = [self creatLabelWithTitle:LocalizationKey(@"type")];
    [containerView addSubview:typeLabel];
    [typeLabel mas_makeConstraints:^(MASConstraintMaker *make) {
        make.left.equalTo(containerView).with.offset(15);
        make.top.equalTo(containerView).with.offset(26);
        make.right.equalTo(containerView).with.offset(-15);
        make.height.mas_equalTo(15);
    }];


    HJDropDownMenu *symbolMenu = [self creatMenuWithTitles:project];
    symbolMenu.cellClickedBlock = ^(NSString *title, NSInteger index) {
        _CoinguessitypeTitle = title;
    };
    _CoinguessingMenu = symbolMenu;
    symbolMenu.placeHolde = LocalizationKey(@"PleaseEnterTheType");
    __weak typeof(symbolMenu)weakSym = symbolMenu;
    symbolMenu.headerClickedBlock = ^{
        [containerView bringSubviewToFront:weakSym];
    };
    [containerView addSubview:symbolMenu];
    [symbolMenu mas_makeConstraints:^(MASConstraintMaker *make) {
        make.left.equalTo(containerView).with.offset(15);
        make.top.equalTo(typeLabel.mas_bottom).with.offset(12);
        make.right.equalTo(containerView).with.offset(-15);
        make.height.mas_equalTo(35);
    }];
    [containerView bringSubviewToFront:symbolMenu];


    UILabel *directionLabel = [self creatLabelWithTitle:LocalizationKey(@"Period")];
    [containerView addSubview:directionLabel];
    [directionLabel mas_makeConstraints:^(MASConstraintMaker *make) {
        make.left.equalTo(containerView).with.offset(15);
        make.top.equalTo(symbolMenu.mas_bottom).with.offset(20);
        make.right.equalTo(containerView).with.offset(-15);
        make.height.mas_equalTo(15);
    }];

    UITextField *textfield = [[UITextField alloc]init];
    textfield.textColor = RGBOF(0x929292);
    textfield.font = [UIFont systemFontOfSize:12];
    textfield.placeholder = @"请输入期次";
//    [textfield setValue:RGBOF(0x929292) forKeyPath:@"_placeholderLabel.textColor"];
    textfield.layer.cornerRadius = 5;
    textfield.layer.borderColor = RGBOF(0xd9d9d9).CGColor;
    textfield.layer.borderWidth = 0.5;
    textfield.keyboardType = UIKeyboardTypeNumberPad;
    _textFied = textfield;
    [containerView addSubview:textfield];
    [textfield mas_makeConstraints:^(MASConstraintMaker *make) {
        make.left.equalTo(containerView).with.offset(15);
        make.top.equalTo(directionLabel.mas_bottom).with.offset(15);
        make.right.equalTo(containerView).with.offset(-15);
        make.height.mas_equalTo(35);
    }];
    textfield.leftView = [[UIView alloc]initWithFrame:CGRectMake(0, 0, 15, 0)];
    textfield.leftViewMode = UITextFieldViewModeAlways;

    UILabel *timeLabel = [self creatLabelWithTitle:@"下注时间"];
    [containerView addSubview:timeLabel];
    [timeLabel mas_makeConstraints:^(MASConstraintMaker *make) {
        make.left.equalTo(containerView).with.offset(15);
        make.top.equalTo(textfield.mas_bottom).with.offset(20);
        make.right.equalTo(containerView).with.offset(-15);
        make.height.mas_equalTo(15);
    }];


    UIButton *headerBtn = [[UIButton alloc] init];
    headerBtn.tag = 0;
    headerBtn.layer.cornerRadius = 5;
    headerBtn.layer.borderColor = RGBOF(0xd9d9d9).CGColor;
    headerBtn.layer.borderWidth = 0.5;
    [headerBtn setBackgroundColor:[UIColor whiteColor]];
    headerBtn.titleLabel.font = [UIFont systemFontOfSize:12];
    [headerBtn setTitleColor:RGBOF(0x929292) forState:UIControlStateNormal];
    [headerBtn setTitle:LocalizationKey(@"startTime") forState:UIControlStateNormal];
    [headerBtn setContentHorizontalAlignment:UIControlContentHorizontalAlignmentLeft];
    [headerBtn setTitleEdgeInsets:UIEdgeInsetsMake(0, 15, 0, 0)];
    [headerBtn addTarget:self action:@selector(timeBtnClicked:) forControlEvents:UIControlEventTouchUpInside];
    _startBtn = headerBtn;
    [containerView addSubview:headerBtn];
    [headerBtn mas_makeConstraints:^(MASConstraintMaker *make) {
        make.left.equalTo(containerView).with.offset(15);
        make.top.equalTo(timeLabel.mas_bottom).with.offset(15);
        make.right.equalTo(containerView).with.offset(-15);
        make.height.mas_equalTo(35);
    }];

    UIButton *endBtn = [[UIButton alloc] init];
    endBtn.tag = 1;
    endBtn.layer.cornerRadius = 5;
    endBtn.layer.borderColor = RGBOF(0xd9d9d9).CGColor;
    endBtn.layer.borderWidth = 0.5;
    [endBtn setBackgroundColor:[UIColor whiteColor]];
    endBtn.titleLabel.font = [UIFont systemFontOfSize:12];
    [endBtn setTitleColor:RGBOF(0x929292) forState:UIControlStateNormal];
    [endBtn setTitle:LocalizationKey(@"endTime") forState:UIControlStateNormal];
    [endBtn setContentHorizontalAlignment:UIControlContentHorizontalAlignmentLeft];
    [endBtn setTitleEdgeInsets:UIEdgeInsetsMake(0, 15, 0, 0)];
    [endBtn addTarget:self action:@selector(timeBtnClicked:) forControlEvents:UIControlEventTouchUpInside];
    _endBtn = endBtn;
    [containerView addSubview:endBtn];
    [endBtn mas_makeConstraints:^(MASConstraintMaker *make) {
        make.left.equalTo(containerView).with.offset(15);
        make.top.equalTo(headerBtn.mas_bottom).with.offset(15);
        make.right.equalTo(containerView).with.offset(-15);
        make.height.mas_equalTo(35);
    }];

    UIView *bottomView = [self bottomCancleBtn];
    [containerView addSubview:bottomView];
    [bottomView mas_makeConstraints:^(MASConstraintMaker *make) {
        make.left.equalTo(containerView).with.offset(15);
        make.top.equalTo(endBtn.mas_bottom).with.offset(20);
        make.right.equalTo(containerView).with.offset(-15);
        make.height.mas_equalTo(40);
    }];
}

- (void)touchesBegan:(NSSet<UITouch *> *)touches withEvent:(UIEvent *)event{

    [touches enumerateObjectsUsingBlock:^(UITouch * _Nonnull obj, BOOL * _Nonnull stop) {
        if ([obj.view isKindOfClass:[self class]]) {
            [self dismissAction];
            *stop = YES;
        }
        if (obj.view.tag == 1987652) {
            [obj.view.subviews enumerateObjectsUsingBlock:^(__kindof UIView * _Nonnull obj, NSUInteger idx, BOOL * _Nonnull stop) {
                if ([obj isKindOfClass:[HJDropDownMenu class]]) {
                    HJDropDownMenu *menu = (HJDropDownMenu *)obj;
                    [menu closeMenu];
                }
            }];
        }
    }];
}

//选择开始时间
-(void)staretimeaction{
    PGDatePickManager *datePickManager = [[PGDatePickManager alloc]init];
    //    datePickManager.view.backgroundColor = [UIColor clearColor];
    datePickManager.isShadeBackgroud = NO;
    PGDatePicker *datePicker = datePickManager.datePicker;
    _startPicker = datePicker;
    datePicker.delegate = self;
    datePicker.datePickerMode = PGDatePickerModeDateHour;
    datePicker.backgroundColor = mainColor;
    [[AppDelegate sharedAppDelegate] presentViewController:datePickManager animated:YES completion:^{

    }];
    datePickManager.headerViewBackgroundColor = kRGBColor(18, 22, 28);
    datePickManager.titleLabel.text = LocalizationKey(@"startTime");
    //设置确定按钮的字体颜色
    datePickManager.confirmButtonTextColor = baseColor;
    //设置选中行的字体颜色
    datePicker.textColorOfSelectedRow = baseColor;
    //设置线条的颜色
    datePicker.lineBackgroundColor = kRGBColor(237, 238, 239);
    //设置确定按钮的字
    datePickManager.confirmButtonText = LocalizationKey(@"ok");
    //设置取消按钮的字
    datePickManager.cancelButtonText = LocalizationKey(@"cancel");
}

//选择结束时间
-(void)endtimeaction{
    PGDatePickManager *datePickManager = [[PGDatePickManager alloc]init];
    datePickManager.isShadeBackgroud = NO;
    PGDatePicker *datePicker = datePickManager.datePicker;
    datePicker.backgroundColor = mainColor;
    _endPicker = datePicker;
    datePicker.delegate = self;
    datePicker.datePickerMode = PGDatePickerModeDateHour;
    [[AppDelegate sharedAppDelegate] presentViewController:datePickManager animated:YES completion:^{

    }];
    datePickManager.headerViewBackgroundColor = kRGBColor(18, 22, 28);
    datePickManager.titleLabel.text = LocalizationKey(@"endTime");
    //设置确定按钮的字体颜色
    datePickManager.confirmButtonTextColor = baseColor;
    //设置选中行的字体颜色
    datePicker.textColorOfSelectedRow = baseColor;
    //设置线条的颜色
    datePicker.lineBackgroundColor = kRGBColor(237, 238, 239);
    //设置确定按钮的字
    datePickManager.confirmButtonText = LocalizationKey(@"ok");
    //设置取消按钮的字
    datePickManager.cancelButtonText = LocalizationKey(@"cancel");
}

- (BOOL)canBecomeFirstResponder{
    return YES;
}

@end
