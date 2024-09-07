//
//  pageScrollView.m
//  digitalCurrency
//
//  Created by sunliang on 2019/4/11.
//  Copyright © 2019年 BIZZAN. All rights reserved.
//

#import "pageScrollView.h"
@interface pageScrollView ()<UIScrollViewDelegate>
/**
 *  滚动视图
 */
@property (nonatomic,strong) UIScrollView *ccpScrollView;
/**
 *  label的宽度
 */
@property (nonatomic,assign) CGFloat labelW;
/**
 *  label的高度
 */
@property (nonatomic,assign) CGFloat labelH;
/**
 *  定时器
 */
@property (nonatomic,strong) NSTimer *timer;
/**
 *  记录滚动的页码
 */
@property (nonatomic,assign) int page;

@end
@implementation pageScrollView

- (UIScrollView *)ccpScrollView {

    if (_ccpScrollView == nil) {

        _ccpScrollView = [[UIScrollView alloc] initWithFrame:self.bounds];
        _ccpScrollView.showsHorizontalScrollIndicator = NO;
        _ccpScrollView.showsVerticalScrollIndicator = NO;
        _ccpScrollView.scrollEnabled = NO;
        _ccpScrollView.pagingEnabled = YES;
        [self addSubview:_ccpScrollView];

        [_ccpScrollView setContentOffset:CGPointMake(0 , self.labelH) animated:YES];
    }

    return _ccpScrollView;
}


- (instancetype)initWithFrame:(CGRect)frame {

    if (self = [super initWithFrame:frame]) {

        self.labelW = frame.size.width;

        self.labelH = frame.size.height;

        self.ccpScrollView.delegate = self;

        [self addTimer];

    }

    return self;
}

//重写set方法 创建对应的label
- (void)setTitleArray:(NSArray *)titleArray {

    _titleArray = titleArray;

    if (titleArray == nil) {
        [self removeTimer];
        return;
    }

    if (titleArray.count == 1) {
        [self removeTimer];
    }

    id lastObj = [titleArray lastObject];

    NSMutableArray *objArray = [[NSMutableArray alloc] init];

    [objArray addObject:lastObj];
    [objArray addObjectsFromArray:titleArray];

    self.titleNewArray = objArray;

    //CGFloat contentW = 0;
    CGFloat contentH = self.labelH *objArray.count;

    self.ccpScrollView.contentSize = CGSizeMake(0, contentH);

    CGFloat labelW = self.ccpScrollView.frame.size.width;
    self.labelW = labelW;
    CGFloat labelH = self.ccpScrollView.frame.size.height;
    self.labelH = labelH;
    CGFloat labelX = 0;

    //防止重复赋值数据叠加
    for (id label in self.ccpScrollView.subviews) {

        [label removeFromSuperview];

    }

    for (int i = 0; i < objArray.count; i++) {

        UILabel *titleLabel = [[UILabel alloc] init];

        titleLabel.userInteractionEnabled = YES;

        titleLabel.tag = 100 + i;

        UITapGestureRecognizer *tap = [[UITapGestureRecognizer alloc] initWithTarget:self action:@selector(clickTheLabel:)];
        titleLabel.font=[UIFont systemFontOfSize:14];
        [titleLabel addGestureRecognizer:tap];

        titleLabel.textAlignment = NSTextAlignmentLeft;

        CGFloat labelY = i * labelH;

        titleLabel.frame = CGRectMake(labelX, labelY, labelW, labelH);
        titleLabel.textColor=AppTextColor_999999;
        titleLabel.text = objArray[i];

        [self.ccpScrollView addSubview:titleLabel];

    }

}

- (void)clickTheLabel:(UITapGestureRecognizer *)tap {

    if (self.clickLabelBlock) {

        NSInteger tag = tap.view.tag - 1;

        if (tag < 100) {

            tag = 100 + (self.titleArray.count - 1);

        }

        self.clickLabelBlock(tag,self.titleArray[tag - 100]);

    }

}

- (void) clickTitleLabel:(clickLabelBlock) clickLabelBlock {

    self.clickLabelBlock = clickLabelBlock;

}

- (void)setIsCanScroll:(BOOL)isCanScroll {

    if (isCanScroll) {

        self.ccpScrollView.scrollEnabled = YES;

    } else {

        self.ccpScrollView.scrollEnabled = NO;

    }

}

- (void)setTitleColor:(UIColor *)titleColor {

    _titleColor = titleColor;

    for (UILabel *label in self.ccpScrollView.subviews) {

        label.textColor = titleColor;

    }
}

- (void)setTitleFont:(CGFloat )titleFont {

    _titleFont = titleFont;

    for (UILabel *label in self.ccpScrollView.subviews) {

        label.font = [UIFont systemFontOfSize: titleFont];;

    }

}

- (void)setBGColor:(UIColor *)BGColor {

    _BGColor = BGColor;

    self.backgroundColor = BGColor;

}

- (void)nextLabel {

    CGPoint oldPoint = self.ccpScrollView.contentOffset;
    oldPoint.y += self.ccpScrollView.frame.size.height;
    [self.ccpScrollView setContentOffset:oldPoint animated:YES];

}
//当图片滚动时调用scrollView的代理方法
- (void)scrollViewDidScroll:(UIScrollView *)scrollView {

    if (self.ccpScrollView.contentOffset.y == self.ccpScrollView.frame.size.height*(self.titleArray.count )) {

        [self.ccpScrollView setContentOffset:CGPointMake(0, 0) animated:NO];

    }

}


// 开始拖拽的时候调用
- (void)scrollViewWillBeginDragging:(UIScrollView *)scrollView {

    [self removeTimer];
}

- (void)scrollViewDidEndDragging:(UIScrollView *)scrollView willDecelerate:(BOOL)decelerate {
    //开启定时器
    [self addTimer];
}

- (void)addTimer{

    /*
     scheduledTimerWithTimeInterval:  滑动视图的时候timer会停止
     这个方法会默认把Timer以NSDefaultRunLoopMode添加到主Runloop上，而当你滑tableView的时候，就不是NSDefaultRunLoopMode了，这样，你的timer就会停了。
     self.timer = [NSTimer scheduledTimerWithTimeInterval:3.0 target:self selector:@selector(nextLabel) userInfo:nil repeats:YES];
     */

    self.timer = [NSTimer timerWithTimeInterval:3.0 target:self selector:@selector(nextLabel) userInfo:nil repeats:YES];
    [[NSRunLoop mainRunLoop] addTimer:self.timer forMode:NSRunLoopCommonModes];
}

- (void)removeTimer {

    [self.timer invalidate];
    self.timer = nil;
}

- (void)dealloc {

    [self.timer invalidate];
    self.timer = nil;

}

@end
