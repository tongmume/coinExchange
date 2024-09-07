//
//  marketManager.h
//  digitalCurrency
//
//  Created by sunliang on 2019/2/24.
//  Copyright © 2019年 BIZZAN. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface marketManager : NSObject
+ (marketManager *)shareInstance;
@property(nonatomic,strong)NSMutableArray* AllCoinArray;//全部交易对
@property(nonatomic,strong)NSMutableArray* USDTArray;
@property(nonatomic,strong)NSMutableArray* BTCArray;
@property(nonatomic,strong)NSMutableArray* ETHArray;
@property(nonatomic,strong)NSMutableArray* CollectionArray;
@property(nonatomic,copy)NSString* symbol;//当前选择的交易对名称
@end
