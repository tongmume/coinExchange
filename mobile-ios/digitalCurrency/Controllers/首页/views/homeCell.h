//
//  homeCell.h
//  digitalCurrency
//
//  Created by sunliang on 2019/1/26.
//  Copyright © 2019年 BIZZAN. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "symbolModel.h"
@protocol HomeCellDegate <NSObject>

- (void)LookatthemarketWithIndex:(int)index;//查看行情
//- (void)CollectWithIndex:(int)index;//收藏
@end

@interface homeCell : UITableViewCell

@property (weak, nonatomic) IBOutlet UILabel *symbolLabel1;//交易对名字
@property (weak, nonatomic) IBOutlet UILabel *PriceLabel1;//最新价
@property (weak, nonatomic) IBOutlet UILabel *chgLabel1;//涨幅百分数
@property (weak, nonatomic) IBOutlet UILabel *changelabel1;//涨幅数
@property (weak, nonatomic) IBOutlet UIButton *collectBtn1;//收藏按钮

@property (weak, nonatomic) IBOutlet UILabel *symbolLabel2;
@property (weak, nonatomic) IBOutlet UILabel *PriceLabel2;
@property (weak, nonatomic) IBOutlet UILabel *chgLabel2;
@property (weak, nonatomic) IBOutlet UILabel *changelabel2;
@property (weak, nonatomic) IBOutlet UIButton *collectBtn2;

@property (weak, nonatomic) IBOutlet UILabel *symbolLabel3;
@property (weak, nonatomic) IBOutlet UILabel *PriceLabel3;
@property (weak, nonatomic) IBOutlet UILabel *chgLabel3;
@property (weak, nonatomic) IBOutlet UILabel *changelabel3;
@property (weak, nonatomic) IBOutlet UIButton *collectBtn3;

@property (weak, nonatomic) IBOutlet UIButton *btnClick1;
@property (weak, nonatomic) IBOutlet UIButton *btnClick2;
@property (weak, nonatomic) IBOutlet UIButton *btnClick3;

@property (nonatomic, weak) id<HomeCellDegate> delegate;
//配置数据
-(void)configModel:(NSArray*)modelArray withIndex:(int)index;
@end
