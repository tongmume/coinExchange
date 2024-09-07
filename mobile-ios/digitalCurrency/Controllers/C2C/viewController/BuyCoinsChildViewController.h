//
//  BuyCoinsChildViewController.h
//  digitalCurrency
//
//  Created by iDog on 2019/2/25.
//  Copyright © 2019年 BIZZAN. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "SelectCoinTypeModel.h"
#import "XLBasePageController.h"

@interface BuyCoinsChildViewController :XLBasePageController
@property(nonatomic,strong)NSMutableArray *coinTypeArr;
@property (nonatomic, assign) BOOL vcCanScroll;
@end
