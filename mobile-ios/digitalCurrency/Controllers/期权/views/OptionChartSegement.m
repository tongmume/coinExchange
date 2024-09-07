//
//  OptionChartSegement.m
//  digitalCurrency
//
//  Created by chan on 2021/1/5.
//  Copyright © 2021 BIZZAN. All rights reserved.
//

#import "OptionChartSegement.h"

@interface OptionChartSegement()

@property (nonatomic, strong) NSArray *listArray;

@property (nonatomic, strong) NSMutableArray *butttonArray;

@property (nonatomic, strong) UIView *buttonLine;

@end

@implementation OptionChartSegement

- (instancetype)initWithFrame:(CGRect)frame array:(NSArray *)array {
    if (self = [super initWithFrame:frame]) {
        self.listArray = array;
        [self setUI];
    }
    return  self;
}

- (void)setUI {
    CGFloat w = self.frame.size.width / self.listArray.count;
    for (int i = 0; i <self.listArray.count; i ++) {
        UIButton *button = [[UIButton alloc] init];
        [button addTarget:self action:@selector(buttonDidClick:) forControlEvents:UIControlEventTouchUpInside];
        [button setTitle:self.listArray[i] forState:UIControlStateNormal];
        button.titleLabel.font = [UIFont systemFontOfSize:14];
        [button setTitleColor:UIColor.grayColor forState:UIControlStateNormal];
        button.frame = CGRectMake(w *i, 0, w, self.frame.size.height);
    
        button.tag = i + 1000;
        button.layer.cornerRadius = 20;
        button.layer.masksToBounds = YES;
        [self addSubview:button];
        [self.butttonArray addObject:button];
    }
    [self.buttonLine mas_makeConstraints:^(MASConstraintMaker *make) {
        make.bottom.equalTo(self);
        make.width.equalTo(@(w));
        make.height.equalTo(@1);
        make.left.equalTo(@0);
    }];
}


- (void)buttonDidClick:(UIButton *)sender {
    if (sender.isSelected) {
        return;
    }
    for (UIButton *button in self.butttonArray) {
        if (button.tag ==  sender.tag) {
            button.selected = YES;
            [self.buttonLine mas_remakeConstraints:^(MASConstraintMaker *make) {
                make.bottom.equalTo(self);
                make.height.equalTo(@1);
                make.left.right.equalTo(button);
            }];
        }else {
            button.selected = NO;
        }
    }
    if (self.didClick) {
        self.didClick(sender.tag - 1000);
    }
}

- (NSMutableArray *)butttonArray {
    if (!_butttonArray) {
        _butttonArray = [NSMutableArray array];
    }
    return  _butttonArray;
}

- (UIView *)buttonLine{
    if (!_buttonLine) {
        _buttonLine = [[UIView alloc]init];
        _buttonLine.backgroundColor = RGBOF(0xF0A70A);
        [self addSubview:_buttonLine];
    }
    return _buttonLine;
}

- (void)relodData {
    self.listArray = @[
        LocalizationKey(@"line"),
        LocalizationKey(@"min"),
        LocalizationKey(@"fivemin"),
        LocalizationKey(@"thirtymin"),
        LocalizationKey(@"hours"),
        LocalizationKey(@"days"),
    ];
    for (int i = 0 ; i < self.butttonArray.count; i++) {
        UIButton *button = self.butttonArray[i];
        [button setTitle:self.listArray[i] forState:UIControlStateNormal];
    }
}

@end
