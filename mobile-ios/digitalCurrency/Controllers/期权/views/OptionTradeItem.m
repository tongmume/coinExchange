//
//  OptionTradeItem.m
//  digitalCurrency
//
//  Created by chan on 2020/12/31.
//  Copyright © 2020 BIZZAN. All rights reserved.
//

#import "OptionTradeItem.h"

@implementation OptionTradeItem

- (instancetype)init {
    if (self = [super init]) {
        [self.titleLabel mas_makeConstraints:^(MASConstraintMaker *make) {
            make.top.equalTo(@5);
            make.left.equalTo(@5);
            make.right.equalTo(@-5);
        }];
        
        [self.subLabel mas_makeConstraints:^(MASConstraintMaker *make) {
            make.bottom.equalTo(@-5);
            make.left.equalTo(@5);
            make.right.equalTo(@-5);
        }];
        
    }
    return self;
}

- (UILabel *)titleLabel {
    if (!_titleLabel) {
        _titleLabel = [[UILabel alloc] init];
        _titleLabel.text = @"0.00 USDT";
        _titleLabel.textColor = UIColor.whiteColor;
        _titleLabel.font = [UIFont systemFontOfSize:14];
        _titleLabel.textAlignment = NSTextAlignmentCenter;
        _titleLabel.adjustsFontSizeToFitWidth = YES;
        [self addSubview:_titleLabel];
    }
    return _titleLabel;
}

- (UILabel *)subLabel {
    if (!_subLabel) {
        _subLabel = [[UILabel alloc] init];
        _subLabel.text = @"买涨总额";
        _subLabel.adjustsFontSizeToFitWidth = YES;
        _subLabel.textColor = UIColor.whiteColor;
        _subLabel.font = [UIFont systemFontOfSize:13];
        _subLabel.textAlignment = NSTextAlignmentCenter;
        [self addSubview:_subLabel];
    }
    return _subLabel;
}

@end
