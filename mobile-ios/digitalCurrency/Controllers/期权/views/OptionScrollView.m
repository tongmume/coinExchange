//
//  OptionScrollView.m
//  digitalCurrency
//
//  Created by chan on 2021/1/4.
//  Copyright © 2021 BIZZAN. All rights reserved.
//

#import "OptionScrollView.h"

@interface OptionScrollView ();

@property (nonatomic, strong) NSMutableArray *butttonArray;
@property (nonatomic, strong) UIScrollView *contentView;
@property (nonatomic, strong) UIButton *leftIcon;
@property (nonatomic, strong) UIButton *rightIcon;
@end

@implementation OptionScrollView

- (instancetype)initWithFrame:(CGRect)frame {
    if (self = [super initWithFrame:frame]) {
        self.backgroundColor = mainColor;
        self.layer.cornerRadius = 22.5;
        self.layer.masksToBounds = YES;
        self.layer.borderColor = UIColor.grayColor.CGColor;
        self.layer.borderWidth = 1;
        [self.leftIcon mas_makeConstraints:^(MASConstraintMaker *make) {
            make.left.equalTo(self).offset(5);
            make.centerY.equalTo(self);
            make.width.equalTo(@12);
            make.height.equalTo(@20);
        }];
        
        [self.rightIcon mas_makeConstraints:^(MASConstraintMaker *make) {
            make.right.equalTo(self).offset(-5);
            make.centerY.equalTo(self);
            make.width.equalTo(@12);
            make.height.equalTo(@20);
        }];
    }
    return  self;
}


- (void)setData:(NSArray *)data {
    _data = data;
    self.butttonArray = nil;
    [self.contentView removeFromSuperview];
    _contentView = nil;
    [self addSubview:self.contentView];
    CGFloat x = 0;
    for (int i = 0; i <data.count; i ++) {
        UIButton *button = [[UIButton alloc] init];
        [button addTarget:self action:@selector(buttonDidClick:) forControlEvents:UIControlEventTouchUpInside];
        [button setTitle:data[i] forState:UIControlStateNormal];
        button.titleLabel.font = [UIFont systemFontOfSize:14];
        CGFloat w = [data[i] sizeWithAttributes:@{NSAttachmentAttributeName:[UIFont systemFontOfSize:14]}].width +20;
        w = w < 40 ? 40 : w;
        button.frame = CGRectMake(x, 0, w, 40);
        x = x + w;
        button.tag = i + 1000;
        button.layer.cornerRadius = 20;
        button.layer.masksToBounds = YES;
        [self.contentView addSubview:button];
        [self.butttonArray addObject:button];
    }
    self.contentView.contentSize = CGSizeMake(x, 40);
}

- (void)buttonDidClick:(UIButton *)sender {
    if (sender.isSelected) {
        return;
    }
    for (UIButton *button in self.butttonArray) {
        if (button.tag ==  sender.tag) {
            button.selected = YES;
            [button setBackgroundColor:RGBOF(0xF0A70A)];
        }else {
            button.selected = NO;
            [button setBackgroundColor:mainColor];
        }
    }
    if (self.didClickBlock) {
        self.didClickBlock(_data[sender.tag - 1000]);
    }
}

- (NSMutableArray *)butttonArray {
    if (!_butttonArray) {
        _butttonArray = [NSMutableArray array];
    }
    return  _butttonArray;
}

- (UIScrollView *)contentView {
    if (!_contentView) {
        _contentView = [[UIScrollView alloc] init];
        _contentView.frame = CGRectMake(20, 3, self.frame.size.width - 40, 60);
    }
    return _contentView;
}

- (UIButton *)rightIcon{
    if (!_rightIcon) {
        _rightIcon  = [[UIButton alloc] init];
        [_rightIcon setImage:[UIImage imageNamed:@"goback1"] forState:UIControlStateNormal];
        [self addSubview:_rightIcon];
    }
    return _rightIcon;
}

- (UIButton *)leftIcon{
    if (!_leftIcon) {
        _leftIcon  = [[UIButton alloc] init];
        [_leftIcon setImage:[UIImage imageNamed:@"goback"] forState:UIControlStateNormal];
        [self addSubview:_leftIcon];
    }
    return _leftIcon;
}

@end
