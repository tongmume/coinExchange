//
//  Y_KLineMAView.m
//  BTC-Kline
//
//  Created by yate1996 on 16/5/2.
//  Copyright © 2016年 yate1996. All rights reserved.
//

#import "Y_KLineMAView.h"
#import "Masonry.h"
#import "UIColor+Y_StockChart.h"
#import "Y_KLineModel.h"
#import "AppDelegate.h"
@interface Y_KLineMAView ()

@property (strong, nonatomic) UILabel *MA7Label;

@property (strong, nonatomic) UILabel *MA30Label;

@property (strong, nonatomic) UILabel *dateDescLabel;

@property (strong, nonatomic) UILabel *openDescLabel;

@property (strong, nonatomic) UILabel *closeDescLabel;

@property (strong, nonatomic) UILabel *highDescLabel;

@property (strong, nonatomic) UILabel *lowDescLabel;

@property (strong, nonatomic) UILabel *openLabel;

@property (strong, nonatomic) UILabel *closeLabel;

@property (strong, nonatomic) UILabel *highLabel;

@property (strong, nonatomic) UILabel *lowLabel;

@end

@implementation Y_KLineMAView

- (instancetype)init
{
    self = [super init];
    if (self) {
        _MA7Label = [self private_createLabel];
        _MA30Label = [self private_createLabel];
        _dateDescLabel = [self private_createLabel];
        _openDescLabel = [self private_createLabel];
        _openDescLabel.text = [NSString stringWithFormat:@" %@:",LocalizationKey(@"nowopen")];

        _closeDescLabel = [self private_createLabel];
        _closeDescLabel.text = [NSString stringWithFormat:@" %@:",LocalizationKey(@"closingprice")];

        _highDescLabel = [self private_createLabel];
        _highDescLabel.text = [NSString stringWithFormat:@" %@:",LocalizationKey(@"highest")];

        _lowDescLabel = [self private_createLabel];
        _lowDescLabel.text = [NSString stringWithFormat:@" %@:",LocalizationKey(@"minimumest")];

        _openLabel = [self private_createLabel];
        _closeLabel = [self private_createLabel];
        _highLabel = [self private_createLabel];
        _lowLabel = [self private_createLabel];
        
        
        _MA7Label.textColor = [UIColor ma7Color];
        _MA30Label.textColor = [UIColor ma30Color];
        _openLabel.textColor = kRGBColor(102, 102, 102);
        _highLabel.textColor = kRGBColor(102, 102, 102);
        _lowLabel.textColor = kRGBColor(102, 102, 102);
        _closeLabel.textColor = kRGBColor(102, 102, 102);

        NSNumber *labelWidth = [NSNumber numberWithInt:47];
        
        [_dateDescLabel mas_makeConstraints:^(MASConstraintMaker *make) {
            make.left.equalTo(self.mas_left);
            make.top.equalTo(self.mas_top);
            make.bottom.equalTo(self.mas_bottom);
            make.width.equalTo(@100);

        }];
        
        [_openDescLabel mas_makeConstraints:^(MASConstraintMaker *make) {
            make.left.equalTo(_dateDescLabel.mas_right);
            make.top.equalTo(self.mas_top);
            make.bottom.equalTo(self.mas_bottom);
        }];
        
        [_openLabel mas_makeConstraints:^(MASConstraintMaker *make) {
            make.left.equalTo(_openDescLabel.mas_right);
            make.top.equalTo(self.mas_top);
            make.bottom.equalTo(self.mas_bottom);
            make.width.equalTo(labelWidth);
 
        }];
        
        [_highDescLabel mas_makeConstraints:^(MASConstraintMaker *make) {
            make.left.equalTo(_openLabel.mas_right);
            make.top.equalTo(self.mas_top);
            make.bottom.equalTo(self.mas_bottom);
        }];
        
        [_highLabel mas_makeConstraints:^(MASConstraintMaker *make) {
            make.left.equalTo(_highDescLabel.mas_right);
            make.top.equalTo(self.mas_top);
            make.bottom.equalTo(self.mas_bottom);
            make.width.equalTo(labelWidth);

        }];
        
        [_lowDescLabel mas_makeConstraints:^(MASConstraintMaker *make) {
            make.left.equalTo(_highLabel.mas_right);
            make.top.equalTo(self.mas_top);
            make.bottom.equalTo(self.mas_bottom);
        }];
        
        [_lowLabel mas_makeConstraints:^(MASConstraintMaker *make) {
            make.left.equalTo(_lowDescLabel.mas_right);
            make.top.equalTo(self.mas_top);
            make.bottom.equalTo(self.mas_bottom);
            make.width.equalTo(labelWidth);

        }];
        
        [_closeDescLabel mas_makeConstraints:^(MASConstraintMaker *make) {
            make.left.equalTo(_lowLabel.mas_right);
            make.top.equalTo(self.mas_top);
            make.bottom.equalTo(self.mas_bottom);
        }];
        
        [_closeLabel mas_makeConstraints:^(MASConstraintMaker *make) {
            make.left.equalTo(_closeDescLabel.mas_right);
            make.top.equalTo(self.mas_top);
            make.bottom.equalTo(self.mas_bottom);
            make.width.equalTo(labelWidth);

        }];
        
        [_MA7Label mas_makeConstraints:^(MASConstraintMaker *make) {
            make.left.equalTo(_closeLabel.mas_right);
            make.top.equalTo(self.mas_top);
            make.bottom.equalTo(self.mas_bottom);
            
        }];
        
        [_MA30Label mas_makeConstraints:^(MASConstraintMaker *make) {
            make.left.equalTo(_MA7Label.mas_right);
            make.top.equalTo(self.mas_top);
            make.bottom.equalTo(self.mas_bottom);
        }];
        
    }
    return self;
}

+(instancetype)view
{
    Y_KLineMAView *MAView = [[Y_KLineMAView alloc]init];

    return MAView;
}
-(void)maProfileWithModel:(Y_KLineModel *)model
{
    NSDate *date = [NSDate dateWithTimeIntervalSince1970:model.Date.doubleValue/1000];
    NSDateFormatter *formatter = [[NSDateFormatter alloc]init];
    formatter.dateFormat = @"yyyy-MM-dd HH:mm";
    [formatter setTimeZone:ChangeLanguage.timeZone];
    NSString *dateStr = [formatter stringFromDate:date];
    
    _dateDescLabel.text = [@" " stringByAppendingString: dateStr];
    
    _openLabel.text = [NSString stringWithFormat:@"%.2f",model.Open.floatValue];
    _highLabel.text = [NSString stringWithFormat:@"%.2f",model.High.floatValue];
    _lowLabel.text = [NSString stringWithFormat:@"%.2f",model.Low.floatValue];
    _closeLabel.text = [NSString stringWithFormat:@"%.2f",model.Close.floatValue];
      AppDelegate *appDelegate = (AppDelegate *)[[UIApplication sharedApplication] delegate];
    if (appDelegate.isEable) {
        _MA7Label.text = [NSString stringWithFormat:@" MA5：%.2f ",model.MA7.floatValue];
        _MA30Label.text = [NSString stringWithFormat:@" MA10：%.2f",model.MA30.floatValue];
    }else{
        _MA7Label.text = @"";
        _MA30Label.text = @"";
    }
    
}
- (UILabel *)private_createLabel
{
    UILabel *label = [UILabel new];
    label.font = [UIFont systemFontOfSize:10];
    label.textColor = [UIColor assistTextColor];
    [self addSubview:label];
    return label;
}
@end
