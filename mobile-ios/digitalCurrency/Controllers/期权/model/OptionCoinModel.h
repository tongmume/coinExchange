//
//  OptionCoinModel.h
//  digitalCurrency
//
//  Created by chan on 2021/1/4.
//  Copyright © 2021 BIZZAN. All rights reserved.
//

#import <Foundation/Foundation.h>

NS_ASSUME_NONNULL_BEGIN

@interface OptionCoinModel : NSObject

@property (nonatomic, copy) NSString *symbol;
@property (nonatomic, copy) NSString *name;
@property (nonatomic, copy) NSString *coinSymbol;
@property (nonatomic, copy) NSString *baseSymbol;
@property (nonatomic, copy) NSString *maxOptionNo;
@property (nonatomic, copy) NSString *sort;
@property (nonatomic, copy) NSString *coinScale;
@property (nonatomic, copy) NSString *baseCoinScale;
@property (nonatomic, copy) NSString *enable;
@property (nonatomic, copy) NSString *visible;
@property (nonatomic, copy) NSString *tiedType;
@property (nonatomic, copy) NSString *enableBuy;
@property (nonatomic, copy) NSString *enableSell;
@property (nonatomic, copy) NSString *amount;
@property (nonatomic, copy) NSString *feePercent;
@property (nonatomic, copy) NSString *winFeePercent;
@property (nonatomic, copy) NSString *ngnorePercent;

@property (nonatomic, copy) NSString *totalProfit;
@property (nonatomic, assign) NSTimeInterval openTimeGap;
@property (nonatomic, assign) NSTimeInterval closeTimeGap;
@property (nonatomic, copy  ) NSString*createTime;
@property (nonatomic, assign) NSTimeInterval currentTime;

@property (nonatomic, copy) NSString *buyReward;
@property (nonatomic, copy) NSString *sellReward;

//private String symbol;//交易币种名称，格式：BTC/USDT
//private String name; // 合约名称（如：BTC/USDT永续合约）
//private String coinSymbol;//交易币种符号，如BTC
//private String baseSymbol;//结算币种符号，如USDT
//private int maxOptionNo;//最新期号
//private int sort;//排序，从小到大
//private int coinScale;//交易币小数精度
//private int baseCoinScale;//基币小数精度
//private int enable;//状态：1启用 2禁止
//private int visible;//前台可见状态，1：可见，2：不可见
//private int tiedType;//状态：1退回资金 2平台通吃
//private BooleanEnum enableBuy = BooleanEnum.IS_TRUE;
//private BooleanEnum  enableSell = BooleanEnum.IS_TRUE;
//private String amount = "10,20,50,100,200,500,1000,2000";// 允许投注数(默认：10,20,50,100,200,500,1000,2000)
//private BigDecimal feePercent;// 开仓手续费(默认0)
//private BigDecimal winFeePercent;// 赢家手续费(默认千分之一)
//private BigDecimal ngnorePercent;// 忽视涨跌幅度（如设置为0.005，则涨跌幅度小于0.5%的会被视为平局）
//private BigDecimal initBuyReward;// 初始买涨奖池金额
//private BigDecimal initSellReward;// 初始买跌奖池金额
//private BigDecimal totalProfit;// 预测合约总盈利
//private int openTimeGap;// 开始到开盘时间间隔（单位：秒， 默认5分钟）
//private int closeTimeGap;// 开盘到收盘时间间隔（单位：秒， 默认5分钟）
//
//// openTimeGap + closeTimeGap就是一期预测合约的总时间周期
//// 默认为：一期开始后，5分钟内下单，然后停止下单，再等待5分钟出结果。出结果期间不允许下单。
//private Date createTime;//创建时间
///**
// * 服务器当前市价戳
// */
//private Long currentTime;

@end

NS_ASSUME_NONNULL_END
