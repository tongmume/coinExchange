//
//  HistoryCollectionViewCell.m
//  digitalCurrency
//
//  Created by chan on 2020/12/30.
//  Copyright © 2020 BIZZAN. All rights reserved.
//

#import "HistoryCollectionViewCell.h"

@interface HistoryCollectionViewCell()

@property (nonatomic, strong) UIImageView *iconImageView;

@end

@implementation HistoryCollectionViewCell

- (instancetype)initWithFrame:(CGRect)frame {
    if (self = [super initWithFrame:frame]) {
        [self.iconImageView mas_makeConstraints:^(MASConstraintMaker *make) {
            make.center.equalTo(self.contentView);
        }];
    }
    return self;
}

- (UIImageView *)iconImageView {
    if (!_iconImageView) {
        _iconImageView = [[UIImageView alloc] initWithImage:[UIImage imageNamed:@"option_up"]];
        _iconImageView.backgroundColor = UIColor.greenColor;
        _iconImageView.layer.cornerRadius = 15;
        _iconImageView.layer.masksToBounds = YES;
        [self.contentView addSubview:_iconImageView];
    }
    return _iconImageView;;
}

- (void)setIsUp:(BOOL)isUp {
    if (isUp) {
        _iconImageView.image = [UIImage imageNamed:@"option_up"];
        _iconImageView.backgroundColor =GreenColor ;
    }else {
        _iconImageView.backgroundColor = RedColor;
        _iconImageView.image = [UIImage imageNamed:@"option_down"];
    }
}


@end
