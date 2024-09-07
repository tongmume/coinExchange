//
//  UpdateIDCardViewController.h
//  digitalCurrency
//
//  Created by chu on 2019/8/9.
//  Copyright © 2019年 BIZZAN. All rights reserved.
//

#import "BaseViewController.h"

typedef void(^UpdateSucess)(NSDictionary *dic);

@interface UpdateIDCardViewController : BaseViewController

@property (nonatomic, copy) UpdateSucess block;

@end
