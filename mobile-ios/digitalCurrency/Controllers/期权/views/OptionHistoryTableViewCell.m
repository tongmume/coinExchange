//
//  OptionHistoryTableViewCell.m
//  digitalCurrency
//
//  Created by chan on 2021/1/1.
//  Copyright © 2021 BIZZAN. All rights reserved.
//

#import "OptionHistoryTableViewCell.h"
#import "OptionOrderModel.h"

@interface OptionHistoryTableViewCell ()

@property (nonatomic, strong) UILabel *lefLabel1;
@property (nonatomic, strong) UILabel *lefLabel2;
@property (nonatomic, strong) UILabel *lefLabel3;
@property (nonatomic, strong) UILabel *lefLabel4;
@property (nonatomic, strong) UILabel *lefLabel5;

@property (nonatomic, strong) UILabel *rightLabel1;
@property (nonatomic, strong) UILabel *rightLabel2;
@property (nonatomic, strong) UILabel *rightLabel3;
@property (nonatomic, strong) UILabel *rightLabel4;
@property (nonatomic, strong) UILabel *rightLabel5;

@property (nonatomic, strong) UILabel *centerLabel2;
@property (nonatomic, strong) UILabel *centerLabel3;
@property (nonatomic, strong) UILabel *centerLabel4;
@property (nonatomic, strong) UILabel *centerLabel5;

@end

@implementation OptionHistoryTableViewCell

 - (instancetype)initWithStyle:(UITableViewCellStyle)style reuseIdentifier:(NSString *)reuseIdentifier {
    if (self = [super initWithStyle:style reuseIdentifier:reuseIdentifier]) {
        self.backgroundColor = mainColor;
        self.selectionStyle = UITableViewCellSelectionStyleNone;
        [self initUI];
    }
    return self;
}

- (void)initUI {
    [self.lefLabel1 mas_makeConstraints:^(MASConstraintMaker *make) {
        make.left.equalTo(@10);
        make.top.equalTo(@15);
    }];
    
    [self.lefLabel2 mas_makeConstraints:^(MASConstraintMaker *make) {
        make.left.equalTo(self.lefLabel1);
        make.top.equalTo(self.lefLabel1.mas_bottom).offset(10);
    }];
    
    [self.lefLabel3 mas_makeConstraints:^(MASConstraintMaker *make) {
        make.left.equalTo(self.lefLabel1);
        make.top.equalTo(self.lefLabel2.mas_bottom).offset(10);
    }];
    
    [self.lefLabel4 mas_makeConstraints:^(MASConstraintMaker *make) {
        make.left.equalTo(self.lefLabel1);
        make.top.equalTo(self.lefLabel3.mas_bottom).offset(10);
    }];
    
    [self.lefLabel5 mas_makeConstraints:^(MASConstraintMaker *make) {
        make.left.equalTo(self.lefLabel1);
        make.top.equalTo(self.lefLabel4.mas_bottom).offset(10);
    }];
    
    [self.rightLabel1 mas_makeConstraints:^(MASConstraintMaker *make) {
        make.right.equalTo(@-15);
        make.top.equalTo(@15);
    }];
    
    [self.rightLabel2 mas_makeConstraints:^(MASConstraintMaker *make) {
        make.right.equalTo(self.rightLabel1);
        make.top.equalTo(self.rightLabel1.mas_bottom).offset(10);
    }];
    
    [self.rightLabel3 mas_makeConstraints:^(MASConstraintMaker *make) {
        make.right.equalTo(self.rightLabel1);
        make.top.equalTo(self.rightLabel2.mas_bottom).offset(10);
    }];
    
    [self.rightLabel4 mas_makeConstraints:^(MASConstraintMaker *make) {
        make.right.equalTo(self.rightLabel1);
        make.top.equalTo(self.rightLabel3.mas_bottom).offset(10);
    }];
    
    [self.rightLabel5 mas_makeConstraints:^(MASConstraintMaker *make) {
        make.right.equalTo(self.rightLabel1);
        make.top.equalTo(self.rightLabel4.mas_bottom).offset(10);
    }];
    
    [self.centerLabel2 mas_makeConstraints:^(MASConstraintMaker *make) {
        make.top.equalTo(self.lefLabel2.mas_top);
        make.centerX.equalTo(self.contentView);
    }];
    
    [self.centerLabel3 mas_makeConstraints:^(MASConstraintMaker *make) {
        make.top.equalTo(self.lefLabel3.mas_top);
        make.centerX.equalTo(self.contentView);
    }];
    
    [self.centerLabel4 mas_makeConstraints:^(MASConstraintMaker *make) {
        make.top.equalTo(self.lefLabel4.mas_top);
        make.centerX.equalTo(self.contentView);
    }];
    
    [self.centerLabel5 mas_makeConstraints:^(MASConstraintMaker *make) {
        make.top.equalTo(self.lefLabel5.mas_top);
        make.centerX.equalTo(self.contentView);
    }];

}

- (UILabel *)lefLabel1 {
    if (!_lefLabel1) {
        _lefLabel1 = [[UILabel alloc] init];
        _lefLabel1.text = @" ";
        _lefLabel1.textColor = UIColor.grayColor;
        _lefLabel1.font = [UIFont systemFontOfSize:16];
        _lefLabel1.textAlignment = NSTextAlignmentLeft;
        [self.contentView addSubview:_lefLabel1];
    }
    return _lefLabel1;
}

- (UILabel *)lefLabel2 {
    if (!_lefLabel2) {
        _lefLabel2 = [[UILabel alloc] init];
        _lefLabel2.textColor = UIColor.grayColor;
        _lefLabel2.font = [UIFont systemFontOfSize:14];
        _lefLabel2.textAlignment = NSTextAlignmentLeft;
        [self.contentView addSubview:_lefLabel2];
    }
    return _lefLabel2;
}


- (UILabel *)lefLabel3 {
    if (!_lefLabel3) {
        _lefLabel3 = [[UILabel alloc] init];
        _lefLabel3.text = @" ";
        _lefLabel3.textColor = UIColor.grayColor;
        _lefLabel3.font = [UIFont systemFontOfSize:14];
        _lefLabel3.textAlignment = NSTextAlignmentLeft;
        [self.contentView addSubview:_lefLabel3];
    }
    return _lefLabel3;
}

- (UILabel *)lefLabel4 {
    if (!_lefLabel4) {
        _lefLabel4 = [[UILabel alloc] init];
        _lefLabel4.textColor = UIColor.grayColor;
        _lefLabel4.font = [UIFont systemFontOfSize:14];
        _lefLabel4.textAlignment = NSTextAlignmentLeft;
        [self.contentView addSubview:_lefLabel4];
    }
    return _lefLabel4;
}

- (UILabel *)lefLabel5{
    if (!_lefLabel5) {
        _lefLabel5 = [[UILabel alloc] init];
        _lefLabel5.text = @"0";
        _lefLabel5.textColor = UIColor.grayColor;
        _lefLabel5.font = [UIFont systemFontOfSize:14];
        _lefLabel5.textAlignment = NSTextAlignmentLeft;
        [self.contentView addSubview:_lefLabel5];
    }
    return _lefLabel5;
}

- (UILabel *)rightLabel1 {
    if (!_rightLabel1) {
        _rightLabel1 = [[UILabel alloc] init];
        _rightLabel1 = [[UILabel alloc] init];
        _rightLabel1.text = @" ";
        _rightLabel1.textColor = UIColor.grayColor;
        _rightLabel1.font = [UIFont systemFontOfSize:16];
        _rightLabel1.textAlignment = NSTextAlignmentRight;
        [self.contentView addSubview:_rightLabel1];
    }
    return  _rightLabel1;
}


- (UILabel *)rightLabel2 {
    if (!_rightLabel2) {
        _rightLabel2 = [[UILabel alloc] init];
        _rightLabel2 = [[UILabel alloc] init];
        _rightLabel2.textColor = UIColor.grayColor;
        _rightLabel2.font = [UIFont systemFontOfSize:14];
        _rightLabel2.textAlignment = NSTextAlignmentRight;
        [self.contentView addSubview:_rightLabel2];
    }
    return  _rightLabel2;
}

- (UILabel *)rightLabel3 {
    if (!_rightLabel3) {
        _rightLabel3 = [[UILabel alloc] init];
        _rightLabel3 = [[UILabel alloc] init];
        _rightLabel3.text = @" ";
        _rightLabel3.textColor = UIColor.grayColor;
        _rightLabel3.font = [UIFont systemFontOfSize:14];
        _rightLabel3.textAlignment = NSTextAlignmentRight;
        [self.contentView addSubview:_rightLabel3];
    }
    return  _rightLabel3;
}

- (UILabel *)rightLabel4 {
    if (!_rightLabel4) {
        _rightLabel4 = [[UILabel alloc] init];
        _rightLabel4 = [[UILabel alloc] init];

        _rightLabel4.textColor = UIColor.grayColor;
        _rightLabel4.font = [UIFont systemFontOfSize:14];
        _rightLabel4.textAlignment = NSTextAlignmentRight;
        [self.contentView addSubview:_rightLabel4];
    }
    return  _rightLabel4;
}


- (UILabel *)rightLabel5 {
    if (!_rightLabel5) {
        _rightLabel5 = [[UILabel alloc] init];
        _rightLabel5 = [[UILabel alloc] init];
        _rightLabel5.text = @"0";
        _rightLabel5.textColor = UIColor.grayColor;
        _rightLabel5.font = [UIFont systemFontOfSize:14];
        _rightLabel5.textAlignment = NSTextAlignmentRight;
        [self.contentView addSubview:_rightLabel5];
    }
    return  _rightLabel5;
}

- (UILabel *)centerLabel2 {
    if (!_centerLabel2) {
        _centerLabel2 = [[UILabel alloc] init];
        _centerLabel2 = [[UILabel alloc] init];
        _centerLabel2.textColor = UIColor.grayColor;
        _centerLabel2.font = [UIFont systemFontOfSize:14];
        _centerLabel2.textAlignment = NSTextAlignmentRight;
        [self.contentView addSubview:_centerLabel2];
    }
    return  _centerLabel2;
}

- (UILabel *)centerLabel3 {
    if (!_centerLabel3) {
        _centerLabel3 = [[UILabel alloc] init];
        _centerLabel3 = [[UILabel alloc] init];
        _centerLabel3.textColor = UIColor.grayColor;
        _centerLabel3.font = [UIFont systemFontOfSize:14];
        _centerLabel3.textAlignment = NSTextAlignmentRight;
        [self.contentView addSubview:_centerLabel3];
    }
    return  _centerLabel3;
}

- (UILabel *)centerLabel4 {
    if (!_centerLabel4) {
        _centerLabel4 = [[UILabel alloc] init];
        _centerLabel4 = [[UILabel alloc] init];
        _centerLabel4.textColor = UIColor.grayColor;
        _centerLabel4.font = [UIFont systemFontOfSize:14];
        _centerLabel4.textAlignment = NSTextAlignmentRight;
        [self.contentView addSubview:_centerLabel4];
    }
    return  _centerLabel4;
}


- (UILabel *)centerLabel5 {
    if (!_centerLabel5) {
        _centerLabel5 = [[UILabel alloc] init];
        _centerLabel5 = [[UILabel alloc] init];
        _centerLabel5.text = @"0";
        _centerLabel5.textColor = UIColor.grayColor;
        _centerLabel5.font = [UIFont systemFontOfSize:14];
        _centerLabel5.textAlignment = NSTextAlignmentRight;
        [self.contentView addSubview:_centerLabel5];
    }
    return  _centerLabel5;
}

- (void)setModel:(OptionOrderModel *)model {
    _model = model;
    if (![model isKindOfClass:[OptionOrderModel class]] || !model) {
        return;
    }
    _centerLabel4.text = LocalizationKey(@"open_free");
    _centerLabel2.text = LocalizationKey(@"redictor_direction");
    
    _rightLabel4.text = LocalizationKey(@"draw_water");
    _rightLabel2.text = LocalizationKey(@"edictor_result");
    
    _lefLabel4.text = LocalizationKey(@"jiangjin_amount");
    _lefLabel2.text = LocalizationKey(@"open_amount");
    
    _lefLabel1.text = [LocalizationKey(@"di_qi") stringByReplacingOccurrencesOfString:@"n" withString:model.optionNo];
    _lefLabel3.text = model.betAmount;
    _lefLabel5.text = model.rewardAmount;
    
    _centerLabel3.text = [model.direction isEqualToString:@"BUY"]? LocalizationKey(@"option_up"):LocalizationKey(@"option_down");
    _centerLabel3.textColor =  [model.direction isEqualToString:@"BUY"]? GreenColor:RedColor;
    _centerLabel5.text = model.fee;
    
    NSDateFormatter * _formatter = [NSDateFormatter new];
    _formatter.dateFormat = @"yyyy-MM-dd HH:mm:ss";
    [_formatter setTimeZone:ChangeLanguage.timeZone];
    NSDate *date = [NSDate dateWithTimeIntervalSince1970:model.createTime/1000];
    NSString *dateStr = [_formatter stringFromDate:date];
    _rightLabel1.text = dateStr;
    
    _rightLabel3.text = LocalizationKey(@"openging");
    _rightLabel3.textColor = UIColor.grayColor;
    if ([model.result isEqualToString:@"WIN"]) {
        _rightLabel3.textColor = GreenColor;
        _rightLabel3.text = LocalizationKey(@"success");
    }
    if ([model.result isEqualToString:@"LOSE"]) {
        _rightLabel3.text = LocalizationKey(@"failed");
        _rightLabel3.textColor = RedColor;
    }
    _rightLabel5.text = model.winFee;
}

- (void)setIsCurrent:(BOOL)isCurrent {
    if (isCurrent) {
        _lefLabel5.text = @"-";
        _centerLabel5.text = @"-";
        _rightLabel5.text = @"-";
    }
}

@end
