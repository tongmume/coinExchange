//
//  YStockChartViewController.h
//  BTC-Kline
//
//  Created by yate1996 on 16/4/27.
//  Copyright © 2016年 yate1996. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface Y_StockChartViewController : UIViewController
@property(nonatomic,copy)NSString*symbol;//交易对
@property (weak, nonatomic) IBOutlet UIButton *closeBtn;
@property(nonatomic,assign) NSUInteger DefalutselectedIndex;
@property (weak, nonatomic) IBOutlet UIView *moreView;
@property (weak, nonatomic) IBOutlet UIView *mainView;
@property (weak, nonatomic) IBOutlet UIButton *KlineTimeBtn;//分时按钮
@property (weak, nonatomic) IBOutlet UIButton *maBtn;
@property (weak, nonatomic) IBOutlet UIButton *macdBtn;
@property (weak, nonatomic) IBOutlet UIButton *moreBtn;//更多按钮
@property (weak, nonatomic) IBOutlet UIButton *monthBtn;


@property (weak, nonatomic) IBOutlet UIButton *btn1;
@property (weak, nonatomic) IBOutlet UIButton *btn2;
@property (weak, nonatomic) IBOutlet UIButton *btn3;
@property (weak, nonatomic) IBOutlet UIButton *btn4;
@property (weak, nonatomic) IBOutlet UIButton *btn5;
@property (weak, nonatomic) IBOutlet UIButton *btn6;
@property (weak, nonatomic) IBOutlet UIButton *btn7;
@property (weak, nonatomic) IBOutlet UIButton *btn8;
@property (weak, nonatomic) IBOutlet UIButton *btn9;
@property (weak, nonatomic) IBOutlet UIButton *btn10;
@property (weak, nonatomic) IBOutlet UIButton *btn11;

@property (nonatomic,assign)int baseCoinScale;
@property (nonatomic,assign)int coinScale;

@end
