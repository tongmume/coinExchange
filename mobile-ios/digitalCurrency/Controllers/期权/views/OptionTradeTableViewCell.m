//
//  OptionTradeTableViewCell.m
//  digitalCurrency
//
//  Created by chan on 2020/12/31.
//  Copyright © 2020 BIZZAN. All rights reserved.
//

#import "OptionTradeTableViewCell.h"
#import "OptionTradeItem.h"
#import "OptionScrollView.h"


@interface OptionTradeTableViewCell()

@property (nonatomic, strong) UILabel *titleLabel;
@property (nonatomic, strong) UILabel *timeLabel;
@property (nonatomic, strong) OptionTradeItem *leftItem;
@property (nonatomic, strong) OptionTradeItem *centerItem;
@property (nonatomic, strong) OptionTradeItem *rightItem;
@property (nonatomic, strong) UILabel *avalibAmount;
@property (nonatomic, strong) UIButton *leftBottom;
@property (nonatomic, strong) UIButton *rightBottom;


@end

@implementation OptionTradeTableViewCell

- (instancetype)initWithStyle:(UITableViewCellStyle)style reuseIdentifier:(NSString *)reuseIdentifier {
    if (self = [super initWithStyle:style reuseIdentifier:reuseIdentifier]) {
        self.contentView.backgroundColor = mainColor;
        [self initUI];
    }
    return self;
}

- (void)initUI {
    [self.titleLabel mas_makeConstraints:^(MASConstraintMaker *make) {
        make.top.equalTo(@15);
        make.centerX.equalTo(self.contentView);
    }];
    
    [self.timeLabel mas_makeConstraints:^(MASConstraintMaker *make) {
        make.top.equalTo(self.titleLabel.mas_bottom).offset(10);
        make.left.equalTo(@20);
        make.right.equalTo(@-20);
    }];
    
    [self.leftItem mas_makeConstraints:^(MASConstraintMaker *make) {
        make.left.equalTo(@10);
        make.top.equalTo(self.timeLabel.mas_bottom).offset(5);
        make.height.equalTo(@50);
        make.width.equalTo(self.centerItem);
    }];
    
    [self.centerItem mas_makeConstraints:^(MASConstraintMaker *make) {
        make.left.equalTo(self.leftItem.mas_right).offset(-1);
        make.top.equalTo(self.timeLabel.mas_bottom).offset(5);
        make.height.equalTo(@50);
        make.width.equalTo(self.rightItem);
    }];
    
    [self.rightItem mas_makeConstraints:^(MASConstraintMaker *make) {
        make.right.equalTo(@-10);
        make.left.equalTo(self.centerItem.mas_right).offset(-1);
        make.top.equalTo(self.timeLabel.mas_bottom).offset(5);
        make.height.equalTo(@50);
    }];
    
    [self.avalibAmount mas_makeConstraints:^(MASConstraintMaker *make) {
        make.top.equalTo(self.leftItem.mas_bottom).offset(80);
        make.left.equalTo(@10);
        make.right.equalTo(@-10);
    }];
    
    [self.leftBottom mas_makeConstraints:^(MASConstraintMaker *make) {
        make.left.equalTo(@10);
        make.top.equalTo(self.avalibAmount.mas_bottom).offset(5);
        make.height.equalTo(@40);
        make.right.equalTo(self.contentView.mas_centerX);
    }];
    
    [self.rightBottom mas_makeConstraints:^(MASConstraintMaker *make) {
        make.left.equalTo(self.contentView.mas_centerX);
        make.top.equalTo(self.avalibAmount.mas_bottom).offset(5);
        make.height.equalTo(@40);
        make.right.equalTo(@-10);
    }];
    [self.opScrollView mas_makeConstraints:^(MASConstraintMaker *make) {
        make.top.equalTo(self.leftItem.mas_bottom).offset(10);
        make.left.equalTo(@10);
        make.right.equalTo(@-10);
        make.height.equalTo(@46);
    }];

}

- (void)buttonDidClick:(UIButton *)button {
    if (self.upBlock) {
        self.upBlock(button.tag - 10001);
    }
}

- (void)setModel:(OptionModel *)model {
    _model = model;
    if (!model) {
        return;
    }
    [_leftBottom setTitle:LocalizationKey(@"option_up") forState:UIControlStateNormal];
    _centerItem.subLabel.text = LocalizationKey(@"down_total");
    _rightItem.subLabel.text = LocalizationKey(@"my_total");
    _leftItem.subLabel.text = LocalizationKey(@"up_total");
    [_rightBottom setTitle:LocalizationKey(@"option_down") forState:UIControlStateNormal];
    
    self.titleLabel.text = [LocalizationKey(@"di_qi") stringByReplacingOccurrencesOfString:@"n" withString:model.optionNo];
    
    
    NSDateFormatter * _formatter = [NSDateFormatter new];
    _formatter.dateFormat = @"yyyy-MM-dd HH:mm";
    [_formatter setTimeZone:ChangeLanguage.timeZone];
    NSDate *date = [NSDate dateWithTimeIntervalSince1970:(model.createTime + model.openTimeGap * 1000)/ 1000];
    NSDate *date1 = [NSDate dateWithTimeIntervalSince1970:(model.createTime + (model.openTimeGap + model.closeTimeGap) * 1000)/1000];
    NSString *dateStr = [_formatter stringFromDate:date];
    NSString *dateStr1 = [_formatter stringFromDate:date1];
    self.timeLabel.text = [NSString stringWithFormat:@"%@ %@ ~ %@",LocalizationKey(@"option_date"),dateStr,dateStr1];
    NSArray *arr = [self.symbol componentsSeparatedByString:@"/"];
    self.leftItem.titleLabel.text = [NSString stringWithFormat: @"%@ %@",model.totalBuy,arr.lastObject];
    self.centerItem.titleLabel.text = [NSString stringWithFormat: @"%@ %@",model.totalSell,arr.lastObject];
    
}

- (OptionTradeItem *)centerItem {
    if (!_centerItem) {
        _centerItem = [[OptionTradeItem alloc] init];
        _centerItem.titleLabel.textColor = RedColor;
        _centerItem.subLabel.textColor = RedColor;
        _centerItem.layer.borderColor = UIColor.grayColor.CGColor;
        _centerItem.layer.borderWidth = 1;
        [self.contentView addSubview:_centerItem];
    }
    return _centerItem;
}

- (OptionTradeItem *)rightItem {
    if (!_rightItem) {
        _rightItem = [[OptionTradeItem alloc] init];
        _rightItem.titleLabel.textColor = RGBOF(0xF0A70A);
        _rightItem.subLabel.textColor = RGBOF(0xF0A70A);
        _rightItem.layer.borderColor = UIColor.grayColor.CGColor;
        _rightItem.layer.borderWidth = 1;
        [self.contentView addSubview:_rightItem];
    }
    return _rightItem;
}

- (OptionTradeItem *)leftItem {
    if (!_leftItem) {
        _leftItem = [[OptionTradeItem alloc] init];
        _leftItem.titleLabel.textColor = GreenColor;
        _leftItem.subLabel.textColor = GreenColor;
        _leftItem.layer.borderColor = UIColor.grayColor.CGColor;
        _leftItem.layer.borderWidth = 1;
        [self.contentView addSubview:_leftItem];
    }
    return _leftItem;
}

- (UILabel *)titleLabel {
    if (!_titleLabel) {
        _titleLabel = [[UILabel alloc] init];
        _titleLabel.text = @" ";
        _titleLabel.textColor = UIColor.grayColor;
        _titleLabel.font = [UIFont systemFontOfSize:18];
        _titleLabel.textAlignment = NSTextAlignmentCenter;
        [self.contentView addSubview:_titleLabel];
    }
    return _titleLabel;
}

- (UILabel *)timeLabel {
    if (!_timeLabel) {
        _timeLabel = [[UILabel alloc] init];
        _timeLabel.text = @" ";
        _timeLabel.textColor = UIColor.grayColor;
        _timeLabel.font = [UIFont systemFontOfSize:12];
        _timeLabel.textAlignment = NSTextAlignmentCenter;
        _timeLabel.adjustsFontSizeToFitWidth = YES;
        [self.contentView addSubview:_timeLabel];
    }
    return _timeLabel;
}

- (UILabel *)avalibAmount {
    if (!_avalibAmount) {
        _avalibAmount = [[UILabel alloc] init];
        _avalibAmount.text = @" ";
        _avalibAmount.textColor = UIColor.grayColor;
        _avalibAmount.font = [UIFont systemFontOfSize:13];
        _avalibAmount.textAlignment = NSTextAlignmentCenter;
        _avalibAmount.adjustsFontSizeToFitWidth = YES;
        [self.contentView addSubview:_avalibAmount];
    }
    return _avalibAmount;
    
}

- (UIButton *)leftBottom {
    if (!_leftBottom) {
        _leftBottom = [UIButton buttonWithType:UIButtonTypeCustom];
        [_leftBottom setBackgroundColor:GreenColor];
        [_rightBottom setTitleColor:UIColor.whiteColor forState:UIControlStateNormal];
        [_leftBottom addTarget:self action:@selector(buttonDidClick:) forControlEvents:UIControlEventTouchUpInside];
        _leftBottom.titleLabel.font = [UIFont systemFontOfSize:14];
        _leftBottom.selected = YES;
        _leftBottom.tag = 10001;
        [self addSubview:_leftBottom];
    }
    return  _leftBottom;
}

- (UIButton *)rightBottom {
    if (!_rightBottom) {
        _rightBottom = [UIButton buttonWithType:UIButtonTypeCustom];
        _rightBottom.titleLabel.font = [UIFont systemFontOfSize:14];
        [_rightBottom setTitleColor:UIColor.whiteColor forState:UIControlStateNormal];
        [_rightBottom setBackgroundColor:RedColor];
        _rightBottom.tag = 10002;
        [_rightBottom addTarget:self action:@selector(buttonDidClick:) forControlEvents:UIControlEventTouchUpInside];
        [self addSubview:_rightBottom];
    }
    return  _rightBottom;
}

- (OptionScrollView *)opScrollView {
    if (!_opScrollView) {
        _opScrollView = [[OptionScrollView alloc] init];
        [self addSubview:_opScrollView];
    }
    return _opScrollView;;
}

- (void)setAmount:(NSString *)amount {
    NSArray *arr = [self.symbol componentsSeparatedByString:@"/"];
    self.avalibAmount.text = [NSString stringWithFormat:@"%@%@ %@",LocalizationKey(@"option_avalib_amount"),amount,arr.lastObject];
}

- (void)setBeAmount:(NSString *)beAmount direction:(NSInteger)direction {
    NSArray *arr = [self.symbol componentsSeparatedByString:@"/"];
//    if ([beAmount integerValue] == 0) {
//        self.leftItem.titleLabel.text = [NSString stringWithFormat: @"0.00 %@",arr.lastObject];
//        self.centerItem.titleLabel.text = [NSString stringWithFormat: @"0.00 %@",arr.lastObject];
//        self.rightItem.titleLabel.text = [NSString stringWithFormat: @"0.00 %@",arr.lastObject];
//    }else {
//        if (direction == 0) {
//            self.leftItem.titleLabel.text = [NSString stringWithFormat: @"%@ %@",beAmount,arr.lastObject];
//            self.centerItem.titleLabel.text = [NSString stringWithFormat: @"0.00 %@",arr.lastObject];
//            self.rightItem.titleLabel.text = [NSString stringWithFormat: @"%@ %@",beAmount,arr.lastObject];
//        }else if (direction == 1) {
////            self.leftItem.titleLabel.text = [NSString stringWithFormat: @"0.00 %@",arr.lastObject];
////            self.centerItem.titleLabel.text = [NSString stringWithFormat: @"%@ %@",beAmount,arr.lastObject];
            self.rightItem.titleLabel.text = [NSString stringWithFormat: @"%@ %@",beAmount,arr.lastObject];
//        }
//    }
}
@end
