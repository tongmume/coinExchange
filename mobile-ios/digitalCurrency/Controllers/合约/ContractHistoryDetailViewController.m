//
//  ContractHistoryDetailViewController.m
//  digitalCurrency
//
//  Created by ios on 2020/9/24.
//  Copyright © 2020 BIZZAN. All rights reserved.
//

#import "ContractHistoryDetailViewController.h"

@interface ContractHistoryDetailViewController (){
    
    
    CGFloat _pointY;
    
}

@end

@implementation ContractHistoryDetailViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    // Do any additional setup after loading the view.
    self.navigationItem.title=LocalizationKey(@"text_entrust_details");
    self.view.backgroundColor=mainColor;
    
    UIView *line=[[UIView alloc]initWithFrame:CGRectMake(0,40,SCREEN_WIDTH_S,1)];
    line.backgroundColor=ViewBackgroundColor;
    [self.view addSubview:line];
    _pointY=line.frame.origin.y+line.frame.size.height+5;
    
    if (_model) {
        CGFloat width=(SCREEN_WIDTH_S-20)/2;
        
        UILabel *leftlabel=[[UILabel alloc]initWithFrame:CGRectMake(10,10,width,20)];
        leftlabel.textColor=VCBackgroundColor;
        leftlabel.textAlignment=NSTextAlignmentLeft;
        leftlabel.text=LocalizationKey(@"ent_status4");
        leftlabel.font=[UIFont systemFontOfSize:14*kWindowWHOne];
        [self.view addSubview:leftlabel];
        
        //委托状态
        if ([_model.isBlast isEqualToString:@"1"]) {
            leftlabel.text=LocalizationKey(@"ent_statusblast");
        } else {
            if ([_model.status isEqualToString:@"ENTRUST_ING"]) {
                leftlabel.text=LocalizationKey(@"ent_status1");
            } else if ([_model.status isEqualToString:@"ENTRUST_CANCEL"]) {
                leftlabel.text=LocalizationKey(@"ent_status2");
            } else if ([_model.status isEqualToString:@"ENTRUST_FAILURE"]) {
                leftlabel.text=LocalizationKey(@"ent_status3");
            } else if ([_model.status isEqualToString:@"ENTRUST_SUCCESS"]) {
                leftlabel.text=LocalizationKey(@"ent_status4");
            } else {
            }
        }
        
            NSDateFormatter * _formatter = [NSDateFormatter new];
            _formatter.dateFormat = @"yyyy-MM-dd HH:mm";
        [_formatter setTimeZone:ChangeLanguage.timeZone];
             NSDate *date = [NSDate dateWithTimeIntervalSince1970:_model.createTime.integerValue/1000];
                  NSString *dateStr = [_formatter stringFromDate:date];
          [self setleftTitle:LocalizationKey(@"depthtime") rightTitle:dateStr rightTextColor:AppTextColor_Level_2];
        
        if ([_model.entrustType isEqualToString:@"OPEN"]) {
              [self setleftTitle:LocalizationKey(@"text_open_flat") rightTitle:LocalizationKey(@"text_open") rightTextColor:AppTextColor_Level_2];
            if ([_model.direction isEqualToString:@"BUY"]) {
                [self setleftTitle:LocalizationKey(@"depthDirection") rightTitle:LocalizationKey(@"buyOpenmore") rightTextColor:GreenColor];
            }else
                [self setleftTitle:LocalizationKey(@"depthDirection") rightTitle:LocalizationKey(@"sellOpennull") rightTextColor:RedColor];
        }else{
             [self setleftTitle:LocalizationKey(@"text_open_flat") rightTitle:LocalizationKey(@"text_flat") rightTextColor:AppTextColor_Level_2];
            if ([_model.direction isEqualToString:@"BUY"]) {
                          [self setleftTitle:LocalizationKey(@"depthDirection") rightTitle:LocalizationKey(@"buyflatnull") rightTextColor:GreenColor];
                      }else
                          [self setleftTitle:LocalizationKey(@"depthDirection") rightTitle:LocalizationKey(@"sellflatmore") rightTextColor:RedColor];
        }
            
      if ([_model.type isEqualToString:@"SPOT_LIMIT"]) {
          [self setleftTitle:LocalizationKey(@"text_entrust_type") rightTitle:LocalizationKey(@"text_plan_entrust") rightTextColor:AppTextColor_Level_2];
          [self setleftTitle:LocalizationKey(@"text_trigger_price_constract") rightTitle:[ToolUtil stringFromNumber:_model.triggerPrice.doubleValue withlimit:4] rightTextColor:AppTextColor_Level_2];
           
        }else if ([_model.type isEqualToString:@"LIMIT_PRICE"]){
            [self setleftTitle:LocalizationKey(@"text_entrust_type") rightTitle:LocalizationKey(@"text_plan_entrust") rightTextColor:AppTextColor_Level_2];
             [self setleftTitle:LocalizationKey(@"text_trigger_price_constract") rightTitle:@"--" rightTextColor:AppTextColor_Level_2];
//            cell.entrustTypeStrlabel.text=LocalizationKey(@"限价委托");
//            cell.triggerPricelabel.text=@"--";
        }else{
            [self setleftTitle:LocalizationKey(@"text_entrust_type") rightTitle:LocalizationKey(@"text_Market_entrust") rightTextColor:AppTextColor_Level_2];
             [self setleftTitle:LocalizationKey(@"text_trigger_price_constract") rightTitle:@"--" rightTextColor:AppTextColor_Level_2];
//            cell.entrustTypeStrlabel.text=LocalizationKey(@"市价委托");
//            cell.triggerPricelabel.text=@"--";
        }
          [self setleftTitle:LocalizationKey(@"entrustPrice") rightTitle:[ToolUtil stringFromNumber:_model.entrustPrice.doubleValue withlimit:4] rightTextColor:AppTextColor_Level_2];
          [self setleftTitle:LocalizationKey(@"dealPrice") rightTitle:[ToolUtil stringFromNumber:_model.tradedPrice.doubleValue withlimit:4] rightTextColor:AppTextColor_Level_2];
        [self setleftTitle:LocalizationKey(@"text_guarantee_money") rightTitle:[NSString stringWithFormat:@"%@%@",[ToolUtil stringFromNumber:_model.principalAmount.doubleValue withlimit:4],_model.principalUnit] rightTextColor:AppTextColor_Level_2];
        [self setleftTitle:LocalizationKey(@"commissionamount") rightTitle:[NSString stringWithFormat:@"%@%@",_model.volume,LocalizationKey(@"zhang")] rightTextColor:AppTextColor_Level_2];
        [self setleftTitle:LocalizationKey(@"shouxufei") rightTitle:[ToolUtil stringFromNumber:[_model.entrustType isEqualToString:@"OPEN"]?_model.openFee.doubleValue:_model.closeFee.doubleValue withlimit:4] rightTextColor:AppTextColor_Level_2];
        
        if (_model.profitAndLoss.doubleValue==0) {
                  [self setleftTitle:LocalizationKey(@"text_compute") rightTitle:@"--" rightTextColor:AppTextColor_Level_2];
               }else if (_model.profitAndLoss.doubleValue>0){
                  [self setleftTitle:LocalizationKey(@"text_compute") rightTitle:[ToolUtil stringFromNumber:_model.profitAndLoss.doubleValue withlimit:6] rightTextColor:GreenColor];
               }else{
                   [self setleftTitle:LocalizationKey(@"text_compute") rightTitle:[ToolUtil stringFromNumber:_model.profitAndLoss.doubleValue withlimit:6] rightTextColor:RedColor];
               }
    }
}


-(void)setModel:(ConatractCurrentEntrustModel *)model{
    
    _model=model;
    
    
}



-(void)setleftTitle:(NSString *)title rightTitle:(NSString *)righttitle rightTextColor:(UIColor*)color{
    
    CGFloat width=(SCREEN_WIDTH_S-20)/2;
    
    UILabel *leftlabel=[[UILabel alloc]initWithFrame:CGRectMake(10,_pointY+10,width,20)];
    leftlabel.textColor=AppTextColor_Level_2;
    leftlabel.textAlignment=NSTextAlignmentLeft;
    leftlabel.text=title;
    leftlabel.font=[UIFont systemFontOfSize:14*kWindowWHOne];
    [self.view addSubview:leftlabel];
    
    UILabel *rightlabel=[[UILabel alloc]initWithFrame:CGRectMake(SCREEN_WIDTH_S/2,leftlabel.frame.origin.y,width,20)];
    rightlabel.textAlignment=NSTextAlignmentRight;
    rightlabel.textColor=color;
    rightlabel.text=righttitle;
    rightlabel.font=[UIFont systemFontOfSize:14*kWindowWHOne];
    [self.view addSubview:rightlabel];
    
    _pointY+=30;
}


/*
#pragma mark - Navigation

// In a storyboard-based application, you will often want to do a little preparation before navigation
- (void)prepareForSegue:(UIStoryboardSegue *)segue sender:(id)sender {
    // Get the new view controller using [segue destinationViewController].
    // Pass the selected object to the new view controller.
}
*/

@end
