//
//  ZLGestureLockView.m
//  GestureLockDemo
//
//  Created by ZL on 2019/4/5.
//  Copyright © 2019年 ZL. All rights reserved.
//

#import "ZLGestureLockView.h"

@interface ZLGestureLockView ()

@property (strong, nonatomic) NSMutableArray *selectBtns;
@property (assign, nonatomic) CGPoint currentPoint;

@end

@implementation ZLGestureLockView

#pragma mark - getter

- (NSMutableArray *)selectBtns {
    if (!_selectBtns) {
        _selectBtns = [NSMutableArray array];
    }
    return _selectBtns;
}

#pragma mark - initializer

- (instancetype)initWithFrame:(CGRect)frame {

    self = [super initWithFrame:frame];

    if (self) {

        [self initSubviews];
    }
    return self;
}

// 子视图初始化
- (void)initSubviews {
    self.backgroundColor = [UIColor clearColor];

    UIPanGestureRecognizer *pan = [[UIPanGestureRecognizer alloc] initWithTarget:self action:@selector(pan:)];
    [self addGestureRecognizer:pan];

    // 创建九宫格 9个按钮
    for (NSInteger i = 0; i < 9; i++) {
        UIButton *btn = [UIButton buttonWithType:UIButtonTypeCustom];
        btn.userInteractionEnabled = NO;
        [btn setBackgroundImage:[UIImage imageNamed:@"未选"] forState:UIControlStateNormal];
        [btn setBackgroundImage:[UIImage imageNamed:@"选中"] forState:UIControlStateSelected];
        [self addSubview:btn];
        btn.tag = i + 1;
    }
}

//为什么要在这个方法中布局子控件，因为只调用这个方法，就表示父控件的尺寸确定
- (void)layoutSubviews {
    [super layoutSubviews];

    NSUInteger count = self.subviews.count;

    int cols = 3;//总列数

    CGFloat x = 0,y = 0,w = 0,h = 0;

    if (Screen_Width == 320) {
        w = 50;
        h = 50;
    } else {
        w = 70;//58
        h = 70;
    }

    CGFloat margin = (self.bounds.size.width - cols * w) / (cols + 1);//间距

    CGFloat col = 0;
    CGFloat row = 0;
    for (int i = 0; i < count; i++) {

        col = i % cols;
        row = i / cols;

        x = margin + (w+margin)*col;

        y = margin + (w+margin)*row;
        if (Screen_Height == 480) { // 适配4寸屏幕
            y = (w + margin) * row;
        }else {
            y = margin +(w + margin) * row;
        }

        UIButton *btn = self.subviews[i];
        btn.frame = CGRectMake(x, y, w, h);
    }
}

// 只要调用这个方法就会把之前绘制的东西清空 重新绘制
- (void)drawRect:(CGRect)rect {

    if (_selectBtns.count == 0) return;

    // 把所有选中按钮中心点连线
    UIBezierPath *path = [UIBezierPath bezierPath];

    if (self.userInteractionEnabled) {
        [[UIColor yellowColor] set];
    } else {
        [[UIColor orangeColor] set];
    }
    for (int i = 0; i < self.selectBtns.count; i ++) {
        UIButton *btn = self.selectBtns[i];
        if (i == 0) {
            [path moveToPoint:btn.center]; // 设置起点
        } else {
            [path addLineToPoint:btn.center];
        }
    }
    [path addLineToPoint:_currentPoint];

    [baseColor set];
    //  设置路径属性
    path.lineWidth = 6;
    path.lineJoinStyle = kCGLineCapRound;
    path.lineCapStyle = kCGLineCapRound;
    //  渲染
    [path stroke];
}

#pragma mark - action pan

- (void)pan:(UIPanGestureRecognizer *)pan {
    _currentPoint = [pan locationInView:self];

    [self setNeedsDisplay];

    for (UIButton *button in self.subviews) {
        if (CGRectContainsPoint(button.frame, _currentPoint) && button.selected ==   NO) {

            button.selected = YES;
            [self.selectBtns addObject:button];
        }
    }

    [self layoutIfNeeded];

    if (pan.state == UIGestureRecognizerStateEnded) {

        // 保存输入密码
        // 注意：我们在密码判定过程中是通过根据先前布局按钮的时候定义的按钮tag值进行字符串拼接，密码传值是通过代理实现。
        NSMutableString *gesturePwd = @"".mutableCopy;
        for (UIButton *button in self.selectBtns) {
            [gesturePwd appendFormat:@"%ld",button.tag-1];
            button.selected = NO;
        }
        [self.selectBtns removeAllObjects];

        // 手势密码绘制完成后回调
        if ([self.delegate respondsToSelector:@selector(gestureLockView:drawRectFinished:)]) {
            [self.delegate gestureLockView:self drawRectFinished:gesturePwd];
        }

    }
}

@end
