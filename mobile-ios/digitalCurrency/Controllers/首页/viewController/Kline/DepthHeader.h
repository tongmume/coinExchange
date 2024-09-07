//
//  DepthHeader.h
//  digitalCurrency
//
//  Created by sunliang on 2019/6/1.
//  Copyright © 2019年 BIZZAN. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface DepthHeader : UIView
+(DepthHeader *)instancetableHeardViewWithFrame:(CGRect)Rect;
@property (weak, nonatomic) IBOutlet UIButton *deepthBtn;
@property (weak, nonatomic) IBOutlet UIButton *tradeBtn;
@property (weak, nonatomic) IBOutlet UIView *lineView;
@property (weak, nonatomic) IBOutlet UIView *lineview2;

@end
