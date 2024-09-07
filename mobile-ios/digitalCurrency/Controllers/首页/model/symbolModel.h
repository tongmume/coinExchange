//
//  symbolModel.h
//  digitalCurrency
//
//  Created by sunliang on 2019/2/10.
//  Copyright © 2019年 BIZZAN. All rights reserved.
//

#import "BaseModel.h"

@interface symbolModel : BaseModel
@property(nonatomic,copy)NSString *symbol;
@property(nonatomic,assign)double open;
@property(nonatomic,assign)double high;
@property(nonatomic,assign)double low;
@property(nonatomic,strong)NSNumber *close;
@property(nonatomic,assign)double chg;
@property(nonatomic,assign)double change;
@property(nonatomic,assign)double volume;  //成交量
@property(nonatomic,assign)double turnover;//成交额
@property(nonatomic,assign)BOOL isCollect;//是否收藏
@property(nonatomic,assign)double lastDayClose;
@property(nonatomic,assign)double usdRate;
@property(nonatomic,assign)double usdMoney;
@property(nonatomic,strong)NSNumber *baseUsdRate;
@end
