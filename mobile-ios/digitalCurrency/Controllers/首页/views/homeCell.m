//
//  homeCell.m
//  digitalCurrency
//
//  Created by sunliang on 2019/1/26.
//  Copyright © 2019年 BIZZAN. All rights reserved.
//

#import "homeCell.h"
#import "marketManager.h"
#import "AppDelegate.h"
@implementation homeCell

- (void)awakeFromNib {
    [super awakeFromNib];
    // Initialization code
}
#pragma mark--跳转到交易界面
- (IBAction)touchEvent:(UIButton *)sender {

}
//获取当前屏幕显示的viewcontroller
- (UIViewController *)getCurrentVC
{
    UIViewController *result = nil;

    UIWindow * window = [[UIApplication sharedApplication] keyWindow];
    if (window.windowLevel != UIWindowLevelNormal)
    {
        NSArray *windows = [[UIApplication sharedApplication] windows];
        for(UIWindow * tmpWin in windows)
        {
            if (tmpWin.windowLevel == UIWindowLevelNormal)
            {
                window = tmpWin;
                break;
            }
        }
    }

    UIView *frontView = [[window subviews] objectAtIndex:0];
    id nextResponder = [frontView nextResponder];

    if ([nextResponder isKindOfClass:[UIViewController class]])
        result = nextResponder;
    else
        result = window.rootViewController;

    return result;
}

//配置数据
-(void)configModel:(NSArray*)modelArray withIndex:(int)index{

    self.symbolLabel1.text=@"";
    self.PriceLabel1.text=@"";
    self.chgLabel1.text=@"";
    self.changelabel1.text=@"";
    self.collectBtn1.hidden=YES;
    self.symbolLabel2.text=@"";
    self.PriceLabel2.text=@"";
    self.chgLabel2.text=@"";
    self.changelabel2.text=@"";
    self.collectBtn2.hidden=YES;
    self.symbolLabel3.text=@"";
    self.PriceLabel3.text=@"";
    self.chgLabel3.text=@"";
    self.changelabel3.text=@"";
    self.collectBtn3.hidden=YES;
    self.btnClick1.backgroundColor=[UIColor clearColor];
    self.btnClick2.backgroundColor=[UIColor clearColor];
    self.btnClick3.backgroundColor=[UIColor clearColor];
    for (int i = 0; i < 3; i ++)
    {
        //超过了资源个数就不再遍历了
        if ((index + i) >= modelArray.count)
        {
            break;
        }
        symbolModel*model = [modelArray objectAtIndex:index + i];
        if (i==0) {
            if (![marketManager shareInstance].symbol) {
                [marketManager shareInstance].symbol=model.symbol;//默认第一个
            }
        }
        //显示数据
        UILabel* symbolLabel = nil;
        UILabel* PriceLabel = nil;
        UILabel* chgLabel = nil;
        UILabel* changelabel1 = nil;
        UIButton*collectBtn=nil;
        UIButton*btnClick=nil;
        if (i == 0)
        {
            symbolLabel = self.symbolLabel1;
            PriceLabel = self.PriceLabel1;
            chgLabel=self.chgLabel1;
            changelabel1=self.changelabel1;
            collectBtn=self.collectBtn1;
            btnClick=self.btnClick1;
            collectBtn.hidden=NO;//kRGBColor(22, 22, 22)
            self.btnClick1.backgroundColor=[ToolUtil colorWithHexString:@"20232c"];
        }else if (i == 1)
        {
            symbolLabel = self.symbolLabel2;
            PriceLabel = self.PriceLabel2;
            chgLabel=self.chgLabel2;
            changelabel1=self.changelabel2;
            collectBtn=self.collectBtn2;
            btnClick=self.btnClick2;
            collectBtn.hidden=NO;
            self.btnClick2.backgroundColor=[ToolUtil colorWithHexString:@"20232c"];
        }
        else if (i == 2)
        {
            symbolLabel = self.symbolLabel3;
            PriceLabel = self.PriceLabel3;
            chgLabel=self.chgLabel3;
            changelabel1=self.changelabel3;
            collectBtn=self.collectBtn3;
            btnClick=self.btnClick3;
            collectBtn.hidden=NO;
            self.btnClick3.backgroundColor=[ToolUtil colorWithHexString:@"20232c"];
        }
          symbolLabel.text = model.symbol;
          PriceLabel.text = [NSString stringWithFormat:@"%.2f",[model.close doubleValue]];
          chgLabel.text = [NSString stringWithFormat:@"%.2f%%", model.chg*100];

        NSDecimalNumber *close = [NSDecimalNumber decimalNumberWithDecimal:[model.close decimalValue]];
        NSDecimalNumber *baseUsdRate = [NSDecimalNumber decimalNumberWithDecimal:[model.baseUsdRate decimalValue]];
        changelabel1.text = [NSString stringWithFormat:@"≈%.2f CNY",[[[close decimalNumberByMultiplyingBy:baseUsdRate] decimalNumberByMultiplyingBy:((AppDelegate*)[UIApplication sharedApplication].delegate).CNYRate] doubleValue]];

        [collectBtn addTarget:self action:@selector(changeCollect:) forControlEvents:UIControlEventTouchUpInside];
          collectBtn.tag = 10086 + index + i;
          [btnClick addTarget:self action:@selector(goDetail:) forControlEvents:UIControlEventTouchUpInside];
           btnClick.tag = 10086 + index + i;
        if (model.change <0) {
            PriceLabel.textColor=RedColor;
            chgLabel.textColor=RedColor;
        }else{
            PriceLabel.textColor=GreenColor;
            chgLabel.textColor=GreenColor;
        }
    }
}
//收藏
-(void)changeCollect:(UIButton*)sender{

//    if ([self.delegate respondsToSelector:@selector(CollectWithIndex:)]) {
//        [self.delegate CollectWithIndex:(int)sender.tag -10086];
//    }
}

//查看行情
-(void)goDetail:(UIButton*)sender{
    if ([self.delegate respondsToSelector:@selector(LookatthemarketWithIndex:)]) {
        [self.delegate LookatthemarketWithIndex:(int)sender.tag -10086];
    }
}
- (void)setSelected:(BOOL)selected animated:(BOOL)animated {
    [super setSelected:selected animated:animated];

    // Configure the view for the selected state
}

@end
