//
//  OptionCurrentTableViewCell.m
//  digitalCurrency
//
//  Created by chan on 2020/12/31.
//  Copyright © 2020 BIZZAN. All rights reserved.
//

#import "OptionCurrentTableViewCell.h"
#import "OptionTradeItem.h"


@interface OptionCurrentTableViewCell()

@property (nonatomic, strong) UILabel *titleLabel;
@property (nonatomic, strong) UILabel *timeLabel;
@property (nonatomic, strong) OptionTradeItem *openItem;
@property (nonatomic, strong) OptionTradeItem *currentItem;

@property (nonatomic, strong) OptionTradeItem *leftItem;
@property (nonatomic, strong) OptionTradeItem *centerItem;
@property (nonatomic, strong) OptionTradeItem *rightItem;
@property (nonatomic,strong)  UIProgressView *progressView;
@property (nonatomic, strong) UILabel *preogressLabel;

@end

@implementation OptionCurrentTableViewCell

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
 
    
    [self.openItem mas_makeConstraints:^(MASConstraintMaker *make) {
        make.top.equalTo(self.timeLabel.mas_bottom).offset(5);
        make.left.equalTo(@5);
        make.right.equalTo(self.contentView.mas_centerX);
        make.height.equalTo(@50);
    }];
    
    [self.currentItem mas_makeConstraints:^(MASConstraintMaker *make) {
        make.top.equalTo(self.timeLabel.mas_bottom).offset(5);
        make.right.equalTo(@-5);
        make.left.equalTo(self.contentView.mas_centerX);
        make.height.equalTo(@50);
    }];
    
    
    [self.leftItem mas_makeConstraints:^(MASConstraintMaker *make) {
        make.left.equalTo(@5);
        make.top.equalTo(self.openItem.mas_bottom).offset(5);
        make.height.equalTo(@50);
        make.width.equalTo(self.centerItem);
    }];
    
    [self.centerItem mas_makeConstraints:^(MASConstraintMaker *make) {
        make.left.equalTo(self.leftItem.mas_right).offset(-1);
        make.top.equalTo(self.openItem.mas_bottom).offset(5);
        make.height.equalTo(@50);
        make.width.equalTo(self.rightItem);
    }];
    
    [self.rightItem mas_makeConstraints:^(MASConstraintMaker *make) {
        make.right.equalTo(@-5);
        make.left.equalTo(self.centerItem.mas_right).offset(-1);
        make.top.equalTo(self.openItem.mas_bottom).offset(5);
        make.height.equalTo(@50);
    }];
    
    [self.progressView mas_makeConstraints:^(MASConstraintMaker *make) {
        make.left.equalTo(@10);
        make.right.equalTo(@-80);
        make.height.equalTo(@16);
        make.top.equalTo(self.leftItem.mas_bottom).offset(10);
    }];
    
    [self.preogressLabel mas_makeConstraints:^(MASConstraintMaker *make) {
        make.left.equalTo(self.progressView.mas_right);
        make.right.equalTo(self);
        make.centerY.equalTo(self.progressView);
    }];
}

- (void)buttonDidClick:(UIButton *)button {
    
}

- (OptionTradeItem *)currentItem {
    if (!_currentItem) {
        _currentItem = [[OptionTradeItem alloc] init];
        _currentItem.titleLabel.textColor = UIColor.grayColor;
        _currentItem.subLabel.textColor = UIColor.grayColor;
        _currentItem.subLabel.text = @"0.00 USDT";
        [self.contentView addSubview:_currentItem];
    }
    return _currentItem;
}


- (OptionTradeItem *)openItem {
    if (!_openItem) {
        _openItem = [[OptionTradeItem alloc] init];
        _openItem.titleLabel.textColor = UIColor.grayColor;
        _openItem.subLabel.textColor = UIColor.grayColor;
        _openItem.subLabel.text = @"0.00 USDT";
        [self.contentView addSubview:_openItem];
    }
    return _openItem;
}

- (OptionTradeItem *)centerItem {
    if (!_centerItem) {
        _centerItem = [[OptionTradeItem alloc] init];
        _centerItem.titleLabel.textColor = RedColor;
        _centerItem.subLabel.textColor = RedColor;
        _centerItem.subLabel.text = @"0.00 USDT";
        [self.contentView addSubview:_centerItem];
    }
    return _centerItem;
}

- (OptionTradeItem *)rightItem {
    if (!_rightItem) {
        _rightItem = [[OptionTradeItem alloc] init];
        _rightItem.titleLabel.textColor = RGBOF(0xF0A70A);
        _rightItem.subLabel.textColor = RGBOF(0xF0A70A);
        _rightItem.subLabel.text = @"0.00 USDT";
        [self.contentView addSubview:_rightItem];
    }
    return _rightItem;
}

- (OptionTradeItem *)leftItem {
    if (!_leftItem) {
        _leftItem = [[OptionTradeItem alloc] init];
        _leftItem.titleLabel.textColor = GreenColor;
        _leftItem.subLabel.text = @"0.00 USDT";
        _leftItem.subLabel.textColor = GreenColor;
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

- (UIProgressView *)progressView {
    if (!_progressView) {
        _progressView = [[UIProgressView alloc] init];
        _progressView.tintColor = RGBOF(0xF0A70A);
        _progressView.trackTintColor= [RGBOF(0xF0A70A) colorWithAlphaComponent:0.6];
        _progressView.progressTintColor= RGBOF(0xF0A70A);
        _progressView.progress = 0.4;
        _progressView.progressViewStyle = UIProgressViewStyleBar;
        [self.contentView addSubview:_progressView];
    }
    return  _progressView;
}

- (UILabel *)preogressLabel {
    if (!_preogressLabel) {
        _preogressLabel = [[UILabel alloc] init];
        _preogressLabel.text = @" ";
        _preogressLabel.textColor = UIColor.grayColor;
        _preogressLabel.font = [UIFont systemFontOfSize:15];
        _preogressLabel.textAlignment = NSTextAlignmentCenter;
        [self.contentView addSubview:_preogressLabel];
    }
    return _preogressLabel;
}

- (void)setProgress:(CGFloat)progress {
    self.progressView.progress = progress;
    self.preogressLabel.text = [NSString stringWithFormat:@"%.2f %@",progress * 100,@"%"];
}

- (void)setModel:(OptionModel *)model {
    _model = model;
    if (!model) {
        return;
    }
    NSArray *arr = [self.symbol componentsSeparatedByString:@"/"];
    _leftItem.titleLabel.text = LocalizationKey(@"up_total");
    _rightItem.titleLabel.text = LocalizationKey(@"my_total");
    _centerItem.titleLabel.text = LocalizationKey(@"down_total");
    _openItem.titleLabel.text = LocalizationKey(@"openplate");
    _currentItem.titleLabel.text = LocalizationKey(@"current_price");
    
    self.titleLabel.text = [LocalizationKey(@"di_qi") stringByReplacingOccurrencesOfString:@"n" withString:model.optionNo];
    
    NSDateFormatter * _formatter = [NSDateFormatter new];
    _formatter.dateFormat = @"yyyy-MM-dd HH:mm";
    [_formatter setTimeZone:ChangeLanguage.timeZone];
    NSDate *date = [NSDate dateWithTimeIntervalSince1970:model.openTime/ 1000];
    NSDate *date1 = [NSDate dateWithTimeIntervalSince1970:(model.openTime + model.openTimeGap* 1000)/1000];
    NSString *dateStr = [_formatter stringFromDate:date];
    NSString *dateStr1 = [_formatter stringFromDate:date1];
    self.timeLabel.text = [NSString stringWithFormat:@"%@ %@ ~ %@",LocalizationKey(@"option_date"),dateStr,dateStr1];
    self.openItem.subLabel.text = [NSString stringWithFormat:@"%@ %@",model.openPrice,arr.lastObject];
    
    self.leftItem.subLabel.text = [NSString stringWithFormat: @"%@ %@",model.totalBuy,arr.lastObject];
    self.centerItem.subLabel.text = [NSString stringWithFormat: @"%@ %@",model.totalSell,arr.lastObject];
}

- (void)setBeAmount:(NSString *)beAmount direction:(NSInteger)direction {
    NSArray *arr = [self.symbol componentsSeparatedByString:@"/"];
//    if ([beAmount integerValue] == 0) {
//        self.leftItem.subLabel.text = [NSString stringWithFormat: @"0.00 %@",arr.lastObject];
//        self.centerItem.subLabel.text = [NSString stringWithFormat: @"0.00 %@",arr.lastObject];
//        self.rightItem.subLabel.text = [NSString stringWithFormat: @"0.00 %@",arr.lastObject];
//    }else {
//        if (direction == 0) {
//            self.leftItem.subLabel.text = [NSString stringWithFormat: @"%@ %@",beAmount,arr.lastObject];
//            self.centerItem.subLabel.text = [NSString stringWithFormat: @"0.00 %@",arr.lastObject];
//            self.rightItem.subLabel.text = [NSString stringWithFormat: @"%@ %@",beAmount,arr.lastObject];
//        }else if (direction == 1) {
//            self.leftItem.subLabel.text = [NSString stringWithFormat: @"0.00 %@",arr.lastObject];
//            self.centerItem.subLabel.text = [NSString stringWithFormat: @"%@ %@",beAmount,arr.lastObject];
            self.rightItem.subLabel.text = [NSString stringWithFormat: @"%@ %@",beAmount,arr.lastObject];
//        }
//    }
}

- (void)setCurrentPrice:(NSString *)currentPrice {
    NSArray *arr = [self.symbol componentsSeparatedByString:@"/"];
    self.currentItem.subLabel.text = [NSString stringWithFormat:@"%@ %@" ,currentPrice,arr.lastObject];
    if (currentPrice.floatValue < self.model.openPrice.floatValue) {
        self.currentItem.subLabel.textColor = RedColor;
    }else {
        self.currentItem.subLabel.textColor = GreenColor;
    }
}

@end
