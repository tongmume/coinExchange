//
//  BusscoinModel.h
//  digitalCurrency
//
//  Created by startlink on 2019/8/10.
//  Copyright © 2019年 BIZZAN. All rights reserved.
//

#import <Foundation/Foundation.h>
@class MyCoin;
@interface BusscoinModel : NSObject

@property (nonatomic,copy)NSString *ID;
@property (nonatomic,copy)NSString *amount;
@property (nonatomic,strong)MyCoin *coin;
@end


@interface MyCoin : NSObject
@property (nonatomic,copy)NSString *unit;

@end
