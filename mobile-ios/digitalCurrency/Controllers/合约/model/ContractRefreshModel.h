//
//  ContractRefreshModel.h
//  digitalCurrency
//
//  Created by ios on 2020/10/7.
//  Copyright © 2020 BIZZAN. All rights reserved.
//

#import "BaseModel.h"

NS_ASSUME_NONNULL_BEGIN

@interface ContractRefreshModel : BaseModel

@property(nonatomic,assign)double resfreshEarning;
@property(nonatomic,assign)double resfreshRate;
@property(nonatomic,assign)NSInteger resfreshOne;
@property(nonatomic,assign)NSInteger resfreshTwo;
@property(nonatomic,assign)NSInteger resfreshThree;
@property(nonatomic,assign)double resfreshFour;
@property(nonatomic,assign)double resfreshFive;
@property(nonatomic,assign)double resfreshSix;

@property(nonatomic,strong)NSString * modelstr;

@end

NS_ASSUME_NONNULL_END
