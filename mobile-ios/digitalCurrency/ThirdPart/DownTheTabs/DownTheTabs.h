//
//  DownTheTabs.h
//  digitalCurrency
//
//  Created by chu on 2019/8/7.
//  Copyright © 2019年 BIZZAN. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "HJDropDownMenu.h"

typedef void(^DownTheTabsDismissBlock)(void);//点击空白消失回调

//委托选项卡 回调
typedef void(^EntrustTabsSelecteComplet)(NSString *symbol, NSString *type, NSString *direction, NSString *startTime, NSString *endTime);
//资产流水 回调
typedef void(^AssetFlowSelecteComplet)(NSString *type, NSString *startTime, NSString *endTime);
//交易挖矿回调
typedef void(^TradeMiningSelecteComplet)(NSString *startTime, NSString *endTime);
//提币记录回调
typedef void(^CurrencyRecordSelecteComplet)(NSString *symbol);
//币理财回调
typedef void(^MoneyManagementSelecteComplet)(NSString *startTime, NSString *symbol);

//币竞猜回调
typedef void(^CoinguessingSelecteComplet)(NSString *startTime,NSString *endTime, NSString *symbol,NSString *Periodstr);

@interface DownTheTabs : UIView

@property (nonatomic, copy) EntrustTabsSelecteComplet entrustBlock;

@property (nonatomic, copy) AssetFlowSelecteComplet assetFlowBlock;

@property (nonatomic, copy) TradeMiningSelecteComplet tradeMiningBlock;

@property (nonatomic, copy) CurrencyRecordSelecteComplet currencyRecordBlock;

@property (nonatomic, copy) MoneyManagementSelecteComplet moneyManagementBlock;

@property (nonatomic, copy) CoinguessingSelecteComplet CoinguessingBlock;

@property (nonatomic, copy) DownTheTabsDismissBlock dismissBlock;

//委托记录筛选 选项卡
- (instancetype)initEntrustTabsWithContainerView:(UIView *)containerView Symbols:(NSArray *)symbols;
//资产流水 选项卡
- (instancetype)initAssetFlowTabsWithContainerView:(UIView *)containerView Types:(NSArray *)types;
//挖矿交易 选项卡
- (instancetype)initTradeMiningTabsWithContainerView:(UIView *)containerView Projects:(NSArray *)project;
//提币记录选项卡
- (instancetype)initCurrencyRecordTabsWithContainerView:(UIView *)containerView Projects:(NSArray *)project;
//币理财选项卡
- (instancetype)initMoneyManagementTabsWithContainerView:(UIView *)containerView Projects:(NSArray *)project;
//币竞猜选项卡
- (instancetype)initCoinguessingView:(UIView *)containerView Projects:(NSArray *)project;
- (void)tabsDismiss;

@end
