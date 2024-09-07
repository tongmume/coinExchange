//
//  OptionTableHeaderView.m
//  digitalCurrency
//
//  Created by chan on 2020/12/30.
//  Copyright © 2020 BIZZAN. All rights reserved.
//

#import "OptionTableHeaderView.h"
#import "HistoryCollectionViewCell.h"
#import "OptionModel.h"
#import "Y_StockChartView.h"
#import "Y_KLineGroupModel.h"
#import "UIColor+Y_StockChart.h"
#import "HomeNetManager.h"
#import "OptionChartSegement.h"

@interface OptionTableHeaderView()<UICollectionViewDelegate,UICollectionViewDataSource,Y_StockChartViewDataSource>

@property (nonatomic, strong) UIButton *leftButton;   //往期结果
@property (nonatomic, strong) UIButton *rightButton;
@property (nonatomic, strong) UIView *buttonLine;
@property (nonatomic, strong) UICollectionView *leftView;
@property (nonatomic, strong) UIButton *bottomButton1; //交易
@property (nonatomic, strong) UIButton *bottomButton2; //当前交割
@property (nonatomic, strong) UIButton *bottomButton3; //历史交割
@property (nonatomic, strong) UIView *bottomButtonLine;
@property (nonatomic, strong)  OptionChartSegement*chartSegement;
@property (nonatomic, strong) Y_StockChartView *stockChartView;
@property (nonatomic, strong) Y_KLineGroupModel *groupModel;
@property (nonatomic, copy) NSMutableDictionary <NSString*, Y_KLineGroupModel*> *modelsDict;
@property (nonatomic, assign) NSInteger currentIndex;
@property (nonatomic, copy  ) NSString *type;
@property (nonatomic, strong) UIView *line;
@end

@implementation OptionTableHeaderView

- (instancetype)initWithFrame:(CGRect)frame {
    if (self = [super initWithFrame:frame]) {
        self.backgroundColor = mainColor;
        [self.leftButton mas_makeConstraints:^(MASConstraintMaker *make) {
            make.left.top.equalTo(self);
            make.height.equalTo(@44);
            make.right.equalTo(self.mas_centerX);
        }];
        
        [self.rightButton mas_makeConstraints:^(MASConstraintMaker *make) {
            make.right.top.equalTo(self);
            make.height.equalTo(@44);
            make.left.equalTo(self.mas_centerX);
        }];
        [self.leftView mas_makeConstraints:^(MASConstraintMaker *make) {
            make.left.right.equalTo(self);
            make.top.equalTo(self.leftButton.mas_bottom);
            make.height.equalTo(@(45 * 5));
        }];
        
        [self.bottomButton1 mas_makeConstraints:^(MASConstraintMaker *make) {
            make.left.equalTo(self).offset(10);
            make.top.equalTo(self.leftView.mas_bottom).offset(10);
            make.height.equalTo(@40);
        }];
        
        [self.bottomButton2 mas_makeConstraints:^(MASConstraintMaker *make) {
            make.left.equalTo(self.bottomButton1.mas_right).offset(20);
            make.top.equalTo(self.leftView.mas_bottom).offset(10);
            make.height.equalTo(@40);
        }];
        
        [self.bottomButton3 mas_makeConstraints:^(MASConstraintMaker *make) {
            make.left.equalTo(self.bottomButton2.mas_right).offset(20);
            make.top.equalTo(self.leftView.mas_bottom).offset(10);
            make.height.equalTo(@40);
        }];
        [self.bottomButtonLine mas_makeConstraints:^(MASConstraintMaker *make) {
            make.left.right.equalTo(self.bottomButton1);
            make.height.equalTo(@1);
            make.bottom.equalTo(self.bottomButton1.mas_bottom);
        }];
        
        [self.buttonLine mas_remakeConstraints:^(MASConstraintMaker *make) {
            make.left.right.equalTo(self.leftButton);
            make.height.equalTo(@1);
            make.bottom.equalTo(self.leftButton.mas_bottom);
        }];
        [self addSubview:self.chartSegement];
        
        [self addSubview:self.stockChartView];
        [self.stockChartView mas_makeConstraints:^(MASConstraintMaker *make) {
            make.left.right.equalTo(self.leftView);
            make.top.equalTo(self.chartSegement.mas_bottom);
            make.bottom.equalTo(self.leftView);
        }];
        [self.line mas_makeConstraints:^(MASConstraintMaker *make) {
            make.top.equalTo(self.bottomButton1.mas_bottom);
            make.left.right.equalTo(self);
            make.height.equalTo(@1);
        }];
    }
    return self;
}

- (void)setSymbol:(NSString *)symbol {
    _symbol = symbol;
    [self stockDatasWithIndex:self.currentIndex];
}

- (NSInteger)collectionView:(UICollectionView *)collectionView numberOfItemsInSection:(NSInteger)section {
    return  self.historyList.count;
}
- (__kindof UICollectionViewCell *)collectionView:(UICollectionView *)collectionView cellForItemAtIndexPath:(NSIndexPath *)indexPath {
    HistoryCollectionViewCell *cell = [collectionView dequeueReusableCellWithReuseIdentifier:@"UICollectionViewCellId" forIndexPath:indexPath];
    OptionModel *model = self.historyList[indexPath.row];
    cell.isUp = [model.result isEqualToString:@"WIN"];
    return  cell;
}

- (void)collectionView:(UICollectionView *)collectionView didSelectItemAtIndexPath:(NSIndexPath *)indexPath {
    OptionModel *model = self.historyList[indexPath.row];
    if (self.didClick) {
        self.didClick(model);
    }
}

- (void)buttonDidClick:(UIButton *)sender {
    switch (sender.tag) {
        case 10001:{
            if (self.leftButton.isSelected) {
                return;
            }
            self.leftButton.selected = YES;
            self.rightButton.selected = NO;
            self.leftView.hidden = NO;
            self.stockChartView.hidden = YES;
            self.chartSegement.hidden = YES;
            [self.buttonLine mas_remakeConstraints:^(MASConstraintMaker *make) {
                make.left.right.equalTo(self.leftButton);
                make.height.equalTo(@1);
                make.bottom.equalTo(self.leftButton.mas_bottom);
            }];
        }
            
            break;
        case 10002:{
            if (self.rightButton.isSelected) {
                return;
            }
            self.leftButton.selected = NO;
            self.rightButton.selected = YES;
            self.leftView.hidden = YES;
            self.stockChartView.hidden = NO;
            self.chartSegement.hidden = NO;
            [self.buttonLine mas_remakeConstraints:^(MASConstraintMaker *make) {
                make.left.right.equalTo(self.rightButton);
                make.height.equalTo(@1);
                make.bottom.equalTo(self.rightButton.mas_bottom);
            }];
            
        }
            break;
            
        default:
            break;
    }
}

- (void)bottomButtonDidCkick:(UIButton *)sender {
    switch (sender.tag - 20000) {
        case 0:{
            
                if (self.bottomButton1.isSelected) {
                    return;;
                }
                self.bottomButton1.selected = YES;
                self.bottomButton2.selected = NO;
                self.bottomButton3.selected = NO;
                [self.bottomButtonLine mas_remakeConstraints:^(MASConstraintMaker *make) {
                    make.left.right.equalTo(self.bottomButton1);
                    make.height.equalTo(@1);
                    make.bottom.equalTo(self.bottomButton1.mas_bottom);
                }];
                break;
        }
            
        case 1:{
               if (!YLUserInfo.isLogIn) {
                 return;
               }
                if (self.bottomButton2.isSelected) {
                    return;;
                }
                self.bottomButton1.selected = NO;
                self.bottomButton2.selected = YES;
                self.bottomButton3.selected = NO;
                [self.bottomButtonLine mas_remakeConstraints:^(MASConstraintMaker *make) {
                    make.left.right.equalTo(self.bottomButton2);
                    make.height.equalTo(@1);
                    make.bottom.equalTo(self.bottomButton2.mas_bottom);
                }];
        }
            
            break;
        case 2:{
            if (!YLUserInfo.isLogIn) {
                return;
            }
            if (self.bottomButton3.isSelected) {
                return;;
            }
            self.bottomButton1.selected = NO;
            self.bottomButton2.selected = NO;
            self.bottomButton3.selected = YES;
            [self.bottomButtonLine mas_remakeConstraints:^(MASConstraintMaker *make) {
                make.left.right.equalTo(self.bottomButton3);
                make.height.equalTo(@1);
                make.bottom.equalTo(self.bottomButton3.mas_bottom);
            }];
            
        }
            
            break;
            
        default:
            break;
    }
    if (self.bottomDidClick) {
        self.bottomDidClick(sender.tag - 20000);
    }
}

- (void)setHistoryList:(NSArray *)historyList {
    _historyList = historyList;
    [_leftButton setTitle:LocalizationKey(@"Option_history") forState:UIControlStateNormal];
    [_rightButton setTitle:LocalizationKey(@"RealTime_Quotes") forState:UIControlStateNormal];
    [_bottomButton1 setTitle:LocalizationKey(@"trading") forState:UIControlStateNormal];
    [_bottomButton2 setTitle:LocalizationKey(@"current_elivery") forState:UIControlStateNormal];
    [_bottomButton3 setTitle:LocalizationKey(@"history_elivery") forState:UIControlStateNormal];
    [_chartSegement relodData];
    [self.leftView reloadData];
}

- (UIButton *)leftButton {
    if (!_leftButton) {
        _leftButton = [UIButton buttonWithType:UIButtonTypeCustom];
        [_leftButton setTitle:LocalizationKey(@"Option_history") forState:UIControlStateNormal];
        [_leftButton setTitleColor:UIColor.grayColor forState:UIControlStateNormal];
        [_leftButton setTitleColor:RGBOF(0xF0A70A) forState:UIControlStateSelected];
        [_leftButton addTarget:self action:@selector(buttonDidClick:) forControlEvents:UIControlEventTouchUpInside];
        _leftButton.titleLabel.font = [UIFont systemFontOfSize:15];
        _leftButton.selected = YES;
        _leftButton.tag = 10001;
        [_leftButton setBackgroundColor:mainColor];
        [self addSubview:_leftButton];
    }
    return  _leftButton;
}

- (UIButton *)rightButton {
    if (!_rightButton) {
        _rightButton = [UIButton buttonWithType:UIButtonTypeCustom];
        _rightButton.titleLabel.font = [UIFont systemFontOfSize:15];
        [_rightButton setTitleColor:UIColor.grayColor forState:UIControlStateNormal];
        [_rightButton setTitleColor:RGBOF(0xF0A70A) forState:UIControlStateSelected];
        [_rightButton setTitle:LocalizationKey(@"RealTime_Quotes") forState:UIControlStateNormal];
        [_rightButton setBackgroundColor:mainColor];
        _rightButton.tag = 10002;
        [_rightButton addTarget:self action:@selector(buttonDidClick:) forControlEvents:UIControlEventTouchUpInside];
        [self addSubview:_rightButton];
    }
    return  _rightButton;
}

- (UICollectionView *)leftView {
    if (!_leftView) {
        UICollectionViewFlowLayout *layout = [[UICollectionViewFlowLayout alloc] init];
        CGFloat w = self.frame.size.width / 9.0;
        layout.itemSize= CGSizeMake(w, 45);
        layout.minimumLineSpacing = 0;
        layout.minimumInteritemSpacing = 0;
        layout.sectionInset = UIEdgeInsetsMake(0, 0, 0, 0);
        _leftView = [[UICollectionView alloc] initWithFrame:CGRectZero collectionViewLayout:layout];
        _leftView.backgroundColor = mainColor;
        _leftView.delegate = self;
        [_leftView registerClass:[HistoryCollectionViewCell class] forCellWithReuseIdentifier:@"UICollectionViewCellId"];
        _leftView.dataSource = self;
        [self addSubview:_leftView];
    }
    return _leftView;
}

- (UIButton *)bottomButton3 {
    if (!_bottomButton3) {
        _bottomButton3 = [UIButton buttonWithType:UIButtonTypeCustom];
        [_bottomButton3 setTitle:LocalizationKey(@"history_elivery") forState:UIControlStateNormal];
        [_bottomButton3 setTitleColor:UIColor.whiteColor forState:UIControlStateNormal];
        [_bottomButton3 setTitleColor:RGBOF(0xF0A70A) forState:UIControlStateSelected];
        [_bottomButton3 addTarget:self action:@selector(bottomButtonDidCkick:) forControlEvents:UIControlEventTouchUpInside];
        _bottomButton3.titleLabel.font = [UIFont systemFontOfSize:16];
        _bottomButton3.tag = 20002;
        [_bottomButton3 setBackgroundColor:mainColor];
        [self addSubview:_bottomButton3];
    }
    return  _bottomButton3;
}

- (UIButton *)bottomButton2 {
    if (!_bottomButton2) {
        _bottomButton2 = [UIButton buttonWithType:UIButtonTypeCustom];
        [_bottomButton2 setTitle:LocalizationKey(@"current_elivery") forState:UIControlStateNormal];
        [_bottomButton2 setTitleColor:UIColor.whiteColor forState:UIControlStateNormal];
        [_bottomButton2 setTitleColor:RGBOF(0xF0A70A) forState:UIControlStateSelected];
        [_bottomButton2 addTarget:self action:@selector(bottomButtonDidCkick:) forControlEvents:UIControlEventTouchUpInside];
        _bottomButton2.titleLabel.font = [UIFont systemFontOfSize:16];
        _bottomButton2.tag = 20001;
        [_bottomButton2 setBackgroundColor:mainColor];
        [self addSubview:_bottomButton2];
    }
    return  _bottomButton2;
}

- (UIButton *)bottomButton1 {
    if (!_bottomButton1) {
        _bottomButton1 = [UIButton buttonWithType:UIButtonTypeCustom];
        [_bottomButton1 setTitle:LocalizationKey(@"trading") forState:UIControlStateNormal];
        [_bottomButton1 setTitleColor:UIColor.whiteColor forState:UIControlStateNormal];
        [_bottomButton1 setTitleColor:RGBOF(0xF0A70A) forState:UIControlStateSelected];
        [_bottomButton1 addTarget:self action:@selector(bottomButtonDidCkick:) forControlEvents:UIControlEventTouchUpInside];
        _bottomButton1.titleLabel.font = [UIFont systemFontOfSize:15];
        _bottomButton1.selected = YES;
        _bottomButton1.tag = 20000;
        [_bottomButton1 setBackgroundColor:mainColor];
        [self addSubview:_bottomButton1];
    }
    return  _bottomButton1;
}

- (UIView *)bottomButtonLine {
    if (!_bottomButtonLine) {
        _bottomButtonLine = [[UIView alloc]init];
        _bottomButtonLine.backgroundColor = RGBOF(0xF0A70A);
        [self addSubview:_bottomButtonLine];
    }
    return _bottomButtonLine;
}

- (UIView *)buttonLine{
    if (!_buttonLine) {
        _buttonLine = [[UIView alloc]init];
        _buttonLine.backgroundColor = RGBOF(0xF0A70A);
        [self addSubview:_buttonLine];
    }
    return _buttonLine;
}

- (Y_StockChartView *)stockChartView
{
    if(!_stockChartView) {
        _stockChartView = [Y_StockChartView new];
        _stockChartView.hidden = YES;
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
        _stockChartView.backgroundColor = mainColor;;
        _stockChartView.DefalutselectedIndex=2;//默认显示1分钟K线图
        _stockChartView.dataSource = self;
    }
    return _stockChartView;
}

- (id) stockDatasWithIndex:(NSInteger)index {
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
            type = @"5min";//1分
        }
            break;
        case 3:
        {
            type = @"30min";//5分钟
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
        default:
            break;
    }
    self.currentIndex = index;
    self.type = type;
    [self reloadData:type];
    return nil;
}
#pragma mark-请求K线数据
- (void)reloadData:(NSString*)type {
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
- (void)getDatawithSymbol:(NSString*)symbol withFromtime:(NSString*)time withResolution:(NSString*)resolution{
//    [EasyShowLodingView showLodingText:LocalizationKey(@"loading")];
    [HomeNetManager historyKlineWithsymbol:symbol withFrom:time withTo:[self getStringWithDate:[NSDate date]] withResolution:resolution CompleteHandle:^(id resPonseObj, int code) {
//        [EasyShowLodingView hidenLoding];
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
                [self makeToast:resPonseObj[MESSAGE] duration:1.5 position:CSToastPositionCenter];
            }
        }else{
            [self makeToast:LocalizationKey(@"noNetworkStatus") duration:1.5 position:CSToastPositionCenter];
        }
    }];
}

-(NSString*)getStringWithDate:(NSDate*)date{
    NSTimeInterval nowtime = [date timeIntervalSince1970]*1000;
    long long theTime = [[NSNumber numberWithDouble:nowtime] longLongValue];
    return  [NSString stringWithFormat:@"%llu",theTime];//当前时间的毫秒数
}

- (NSMutableDictionary<NSString *,Y_KLineGroupModel *> *)modelsDict
{
    if (!_modelsDict) {
        _modelsDict = @{}.mutableCopy;
    }
    return _modelsDict;
}

- (OptionChartSegement *)chartSegement {
    if (!_chartSegement) {
        _chartSegement = [[OptionChartSegement alloc] initWithFrame:CGRectMake(0, 44, self.frame.size.width, 40)
                                                              array:@[
                                                                  LocalizationKey(@"line"),
                                                                  LocalizationKey(@"min"),
                                                                  LocalizationKey(@"fivemin"),
                                                                  LocalizationKey(@"thirtymin"),
                                                                  LocalizationKey(@"hours"),
                                                                  LocalizationKey(@"days"),
                                                              ]];
        _chartSegement.hidden = YES;
        __weak typeof(self) weakself = self;
        _chartSegement.didClick = ^(NSInteger index) {
            __weak typeof(weakself) strongself = weakself;
            [strongself stockDatasWithIndex:index];
        };
    }
    return  _chartSegement;;
}

- (UIView *)line {
    if (!_line) {
        _line = [[UIView alloc] init];
        _line.backgroundColor = UIColor.blackColor;
        [self addSubview:_line];
    }
    return _line;
}

@end
