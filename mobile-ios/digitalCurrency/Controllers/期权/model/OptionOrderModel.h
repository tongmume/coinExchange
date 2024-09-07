//
//  OptionOrderModel.h
//  digitalCurrency
//
//  Created by chan on 2021/1/3.
//  Copyright © 2021 BIZZAN. All rights reserved.
//

#import <Foundation/Foundation.h>

NS_ASSUME_NONNULL_BEGIN

@interface OptionOrderModel : NSObject

@property (nonatomic, copy) NSString *ID;
@property (nonatomic, copy) NSString *optionId;
@property (nonatomic, copy) NSString *optionNo;
@property (nonatomic, copy) NSString *memberId;
@property (nonatomic, copy) NSString *symbol;
@property (nonatomic, copy) NSString *coinSymbol;
@property (nonatomic, copy) NSString *baseSymbol;
@property (nonatomic, copy) NSString *direction;
@property (nonatomic, copy) NSString *status;
@property (nonatomic, copy) NSString *result;
@property (nonatomic, copy) NSString *fee;
@property (nonatomic, copy) NSString *winFee;
@property (nonatomic, copy) NSString *rewardAmount;
@property (nonatomic, copy) NSString *betAmount;
@property (nonatomic, assign) NSTimeInterval createTime;



//private Long id;
//private Long optionId;预测合约ID
//private int optionNo; // 合约序号（如第1期，第2期，第3期 中的 1，2，3）
//private Long memberId; // 用户ID
//private String symbol;//交易对符号
//private String coinSymbol;//币单位
//private String baseSymbol;//结算单位
//private ContractOptionOrderDirection direction; // 0看涨 1看跌
//private ContractOptionOrderStatus status; // 0参与中  1已开奖
//private ContractOptionOrderResult result; // 0待开始  1胜利  2失败  3平局
//private BigDecimal fee;// 手续费（当局结束时收取，开盘不收取且平局不收取，该部分金额需要先冻结）
//private BigDecimal winFee;// 抽水
//private BigDecimal betAmount; // 投注金额（胜利返还，失败扣除，平局退回）
//private BigDecimal rewardAmount; // 获奖金额（根据投注金额占总投注金额的比例，瓜分对向奖池）
//private Long createTime;//创建时间

@end

NS_ASSUME_NONNULL_END
