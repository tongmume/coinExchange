//
//  OptionPopView.m
//  digitalCurrency
//
//  Created by chan on 2021/1/5.
//  Copyright © 2021 BIZZAN. All rights reserved.
//

#import "OptionPopView.h"

@interface OptionPopView()

@property (nonatomic, strong) UIView *coverView;

@property (nonatomic, strong) UIView *contentView;

@property (nonatomic, strong) UILabel *titleLabel;

@property (nonatomic, strong) UILabel *lefLabel1;
@property (nonatomic, strong) UILabel *lefLabel2;
@property (nonatomic, strong) UILabel *lefLabel3;

@property (nonatomic, strong) UILabel *lefsub1;
@property (nonatomic, strong) UILabel *lefsub2;
@property (nonatomic, strong) UILabel *lefsub3;


@property (nonatomic, strong) OptionModel *model;


@property (nonatomic, strong) UILabel *rightLabel1;
@property (nonatomic, strong) UILabel *rightLabel2;
@property (nonatomic, strong) UILabel *rightLabel3;


@property (nonatomic, strong) UILabel *rightsub1;
@property (nonatomic, strong) UILabel *rightsub2;
@property (nonatomic, strong) UILabel *rightsub3;
@property (nonatomic, strong) UIButton *cancelButton;

@end

@implementation OptionPopView

+ (void)showWithModel:(OptionModel *)model {
    UIWindow *window = [UIApplication sharedApplication].delegate.window;
    OptionPopView *popView = [[OptionPopView alloc] init];
    popView.model = model;
    [window addSubview:popView ];
    [popView mas_makeConstraints:^(MASConstraintMaker *make) {
        make.edges.equalTo(window);
    }];
}

- (void)setModel:(OptionModel *)model {
    _model = model;
    
//    "openTime" = "开盘时间";
//    "openPrice" = "开盘价格";
//    "closeTime" = "收盘时间";
//    "closePrice"= "收盘价格";
    NSArray *sybols = [self.model.symbol componentsSeparatedByString:@"\/"];
    
    self.lefLabel1.text = [NSString stringWithFormat:@"%@:",LocalizationKey(@"openTime")];
    self.lefLabel2.text = [NSString stringWithFormat:@"%@:",LocalizationKey(@"openPrice")];
    self.lefLabel3.text = [NSString stringWithFormat:@"%@:",LocalizationKey(@"up_total")];
    
    self.rightLabel1.text = [NSString stringWithFormat:@"%@:",LocalizationKey(@"closeTime")];
    self.rightLabel2.text = [NSString stringWithFormat:@"%@:",LocalizationKey(@"closePrice")];
    self.rightLabel3.text = [NSString stringWithFormat:@"%@:",LocalizationKey(@"down_total")];
    
    self.titleLabel.text = [LocalizationKey(@"di_qi") stringByReplacingOccurrencesOfString:@"n" withString:model.optionNo];
    
    NSDateFormatter * _formatter = [NSDateFormatter new];
    _formatter.dateFormat = @"yyyy-MM-dd \n HH:mm:ss";
    NSDate *date = [NSDate dateWithTimeIntervalSince1970:model.openTime/ 1000];
    NSString *dateStr = [_formatter stringFromDate:date];
    self.lefsub1.text  = dateStr;
    self.lefsub2.text = [NSString stringWithFormat:@"%@ %@",model.openPrice, sybols.lastObject];
    self.lefsub3.text = [NSString stringWithFormat:@"%@ %@",model.totalBuy, sybols.lastObject];
    
    
    NSDate *date1 = [NSDate dateWithTimeIntervalSince1970:model.closeTime/ 1000];
    NSString *dateStr1 = [_formatter stringFromDate:date1];
    self.rightsub1.text  = dateStr1;
    self.rightsub2.text = [NSString stringWithFormat:@"%@ %@",model.closePrice, sybols.lastObject];
    self.rightsub3.text = [NSString stringWithFormat:@"%@ %@",model.totalSell, sybols.lastObject];
}

- (instancetype)init {
    if (self = [super init]) {
        [self setUI];
        [self.coverView mas_makeConstraints:^(MASConstraintMaker *make) {
            make.edges.equalTo(self);
        }];
        [self.contentView mas_makeConstraints:^(MASConstraintMaker *make) {
            make.bottom.equalTo(self);
            make.left.right.equalTo(self);
            make.height.equalTo(@(230 + SafeAreaBottomHeight));
        }];
        [self.titleLabel mas_makeConstraints:^(MASConstraintMaker *make) {
            make.left.right.equalTo(self.contentView);
            make.top.equalTo(self.contentView).offset(15);
        }];
        
        [self.lefLabel1 mas_makeConstraints:^(MASConstraintMaker *make) {
            make.left.equalTo(self.contentView).offset(10);
            make.width.equalTo(@65);
            make.top.equalTo(self.titleLabel.mas_bottom).offset(40);
        }];
        
        [self.lefLabel2 mas_makeConstraints:^(MASConstraintMaker *make) {
            make.left.equalTo(self.lefLabel1);
            make.top.equalTo(self.lefLabel1.mas_bottom).offset(40);
            make.width.equalTo(self.lefLabel1);
        }];
        
        [self.lefLabel3 mas_makeConstraints:^(MASConstraintMaker *make) {
            make.left.equalTo(self.lefLabel1);
            make.top.equalTo(self.lefLabel2.mas_bottom).offset(10);
            make.width.equalTo(self.lefLabel1);
        }];
        
        [self.lefsub1 mas_makeConstraints:^(MASConstraintMaker *make) {
            make.left.equalTo(self.lefLabel1.mas_right);
            make.right.equalTo(self.mas_centerX);
            make.top.equalTo(self.lefLabel1);
        }];
        
        [self.lefsub2 mas_makeConstraints:^(MASConstraintMaker *make) {
            make.left.equalTo(self.lefLabel2.mas_right);
            make.right.equalTo(self.lefsub1);
            make.top.equalTo(self.lefLabel2);
        }];
        
        [self.lefsub3 mas_makeConstraints:^(MASConstraintMaker *make) {
            make.left.equalTo(self.lefLabel3.mas_right);
            make.right.equalTo(self.lefsub1);
            make.top.equalTo(self.lefLabel3);
        }];
        
        [self.rightLabel1 mas_makeConstraints:^(MASConstraintMaker *make) {
            make.left.equalTo(self.mas_centerX);
            make.width.equalTo(@65);
            make.top.equalTo(self.lefLabel1);
        }];
        
        [self.rightLabel2 mas_makeConstraints:^(MASConstraintMaker *make) {
            make.left.equalTo(self.rightLabel1);
            make.width.equalTo(@65);
            make.top.equalTo(self.lefLabel2);
        }];
        
        [self.rightLabel3 mas_makeConstraints:^(MASConstraintMaker *make) {
            make.left.equalTo(self.rightLabel1);
            make.width.equalTo(@65);
            make.top.equalTo(self.lefLabel3);
        }];
        
        [self.rightsub1 mas_makeConstraints:^(MASConstraintMaker *make) {
            make.left.equalTo(self.rightLabel1.mas_right);
            make.top.equalTo(self.rightLabel1);
            make.right.equalTo(self).offset(-10);
        }];
        
        [self.rightsub2 mas_makeConstraints:^(MASConstraintMaker *make) {
            make.left.equalTo(self.rightsub1);
            make.top.equalTo(self.rightLabel2);
            make.right.equalTo(self.rightsub1);
        }];
        
        
        [self.rightsub3 mas_makeConstraints:^(MASConstraintMaker *make) {
            make.left.equalTo(self.rightsub1);
            make.top.equalTo(self.rightLabel3);
            make.right.equalTo(self.rightsub1);
        }];
        
        [self.cancelButton mas_makeConstraints:^(MASConstraintMaker *make) {
            make.top.equalTo(self.lefLabel3.mas_bottom).offset(10);
            make.left.right.equalTo(self);
            make.height.equalTo(@48);
        }];
        
    }
    return  self;
}

- (void)setUI {
    
    
}

- (void)cancelAction {
    [self removeFromSuperview];
}

- (UIView *)coverView {
    if (!_coverView) {
        _coverView = [[UIView alloc] init];
        _coverView.backgroundColor = [[UIColor blackColor] colorWithAlphaComponent:0.4];
        UITapGestureRecognizer *tap = [[UITapGestureRecognizer alloc] initWithTarget:self action:@selector(cancelAction)];
        [_coverView addGestureRecognizer:tap];
        [self addSubview:_coverView];
    }
    return _coverView;;
}

- (UIView *)contentView {
    if (!_contentView) {
        _contentView = [[UIView alloc] init];
        _contentView.backgroundColor = mainColor;
        [self addSubview:_contentView];
    }
    return _contentView;;
}

- (UILabel *)titleLabel {
    if (!_titleLabel) {
        _titleLabel = [[UILabel alloc] init];
        _titleLabel.text = @" ";
        _titleLabel.textColor = UIColor.whiteColor;
        _titleLabel.font = [UIFont systemFontOfSize:16];
        _titleLabel.textAlignment = NSTextAlignmentCenter;
        [self.contentView addSubview:_titleLabel];
    }
    return _titleLabel;
}

- (UILabel *)lefLabel1 {
    if (!_lefLabel1) {
        _lefLabel1 = [[UILabel alloc] init];
        _lefLabel1.textColor = UIColor.whiteColor;
        _lefLabel1.font = [UIFont systemFontOfSize:13];
        _lefLabel1.textAlignment = NSTextAlignmentLeft;
        [self.contentView addSubview:_lefLabel1];
    }
    return _lefLabel1;
}

- (UILabel *)lefLabel2 {
    if (!_lefLabel2) {
        _lefLabel2 = [[UILabel alloc] init];
        _lefLabel2.textColor = UIColor.whiteColor;
        _lefLabel2.font = [UIFont systemFontOfSize:13];
        _lefLabel2.textAlignment = NSTextAlignmentLeft;
        [self.contentView addSubview:_lefLabel2];
    }
    return _lefLabel2;
}

- (UILabel *)lefLabel3 {
    if (!_lefLabel3) {
        _lefLabel3 = [[UILabel alloc] init];
        _lefLabel3.textColor = UIColor.whiteColor;
        _lefLabel3.font = [UIFont systemFontOfSize:13];
        _lefLabel3.textAlignment = NSTextAlignmentLeft;
        _lefLabel3.adjustsFontSizeToFitWidth = YES;
        [self.contentView addSubview:_lefLabel3];
    }
    return _lefLabel3;
}

- (UILabel *)lefsub1 {
    if (!_lefsub1) {
        _lefsub1 = [[UILabel alloc] init];
        _lefsub1.textColor = UIColor.grayColor;
        _lefsub1.font = [UIFont systemFontOfSize:13];
        _lefsub1.textAlignment = NSTextAlignmentLeft;
        _lefsub1.numberOfLines = 0 ;
        [self.contentView addSubview:_lefsub1];
    }
    return _lefsub1;
}

- (UILabel *)lefsub2 {
    if (!_lefsub2) {
        _lefsub2 = [[UILabel alloc] init];
        _lefsub2.textColor = UIColor.grayColor;
        _lefsub2.font = [UIFont systemFontOfSize:13];
        _lefsub2.textAlignment = NSTextAlignmentLeft;
        _lefsub2.numberOfLines = 0 ;
        [self.contentView addSubview:_lefsub2];
    }
    return _lefsub2;
}

- (UILabel *)lefsub3 {
    if (!_lefsub3) {
        _lefsub3 = [[UILabel alloc] init];
        _lefsub3.textColor = UIColor.grayColor;
        _lefsub3.font = [UIFont systemFontOfSize:13];
        _lefsub3.textAlignment = NSTextAlignmentLeft;
        _lefsub3.numberOfLines = 0 ;
        [self.contentView addSubview:_lefsub3];
    }
    return _lefsub3;
}


- (UILabel *)rightLabel1 {
    if (!_rightLabel1) {
        _rightLabel1 = [[UILabel alloc] init];
        _rightLabel1.textColor = UIColor.whiteColor;
        _rightLabel1.font = [UIFont systemFontOfSize:13];
        _rightLabel1.textAlignment = NSTextAlignmentLeft;
        [self.contentView addSubview:_rightLabel1];
    }
    return _rightLabel1;
}

- (UILabel *)rightLabel2 {
    if (!_rightLabel2) {
        _rightLabel2 = [[UILabel alloc] init];
        _rightLabel2.textColor = UIColor.whiteColor;
        _rightLabel2.font = [UIFont systemFontOfSize:13];
        _rightLabel2.textAlignment = NSTextAlignmentLeft;
        [self.contentView addSubview:_rightLabel2];
    }
    return _rightLabel2;
}

- (UILabel *)rightLabel3 {
    if (!_rightLabel3) {
        _rightLabel3 = [[UILabel alloc] init];
        _rightLabel3.textColor = UIColor.whiteColor;
        _rightLabel3.font = [UIFont systemFontOfSize:13];
        _rightLabel3.textAlignment = NSTextAlignmentLeft;
        [self.contentView addSubview:_rightLabel3];
    }
    return _rightLabel3;
}

- (UILabel *)rightsub1 {
    if (!_rightsub1) {
        _rightsub1 = [[UILabel alloc] init];
        _rightsub1.textColor = UIColor.grayColor;
        _rightsub1.font = [UIFont systemFontOfSize:13];
        _rightsub1.textAlignment = NSTextAlignmentLeft;
        _rightsub1.numberOfLines = 0 ;
        [self.contentView addSubview:_rightsub1];
    }
    return _rightsub1;
}

- (UILabel *)rightsub2 {
    if (!_rightsub2) {
        _rightsub2 = [[UILabel alloc] init];
        _rightsub2.textColor = UIColor.grayColor;
        _rightsub2.font = [UIFont systemFontOfSize:13];
        _rightsub2.textAlignment = NSTextAlignmentLeft;
        _rightsub2.numberOfLines = 0 ;
        [self.contentView addSubview:_rightsub2];
    }
    return _rightsub2;
}

- (UILabel *)rightsub3 {
    if (!_rightsub3) {
        _rightsub3 = [[UILabel alloc] init];
        _rightsub3.textColor = UIColor.grayColor;
        _rightsub3.font = [UIFont systemFontOfSize:13];
        _rightsub3.textAlignment = NSTextAlignmentLeft;
        _rightsub3.numberOfLines = 0 ;
        [self.contentView addSubview:_rightsub3];
    }
    return _rightsub3;
}

- (UIButton *)cancelButton {
    if (!_cancelButton) {
        _cancelButton = [[UIButton alloc] init];
        [_cancelButton setTitle:LocalizationKey(@"cancel") forState:UIControlStateNormal];
        [_cancelButton setTitleColor:UIColor.whiteColor forState:UIControlStateNormal];
        _cancelButton.titleLabel.font = [UIFont systemFontOfSize:16];
        [_cancelButton addTarget:self action:@selector(cancelAction) forControlEvents:UIControlEventTouchUpInside];
        [self.contentView addSubview:_cancelButton];
    }
    return _cancelButton;
}

@end
