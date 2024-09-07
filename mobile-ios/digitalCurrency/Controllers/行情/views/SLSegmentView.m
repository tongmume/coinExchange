//
//  SLSegmentView.m
//  digitalCurrency
//
//  Created by sunliang on 2019/1/26.
//  Copyright © 2019年 BIZZAN. All rights reserved.
//

#import "SLSegmentView.h"
#import "UIView+Extension.h"

#define kCanSliderLineWidth  kWindowW/self.segementTitleArray.count


@interface SLSegmentView()
@property (nonatomic, strong)NSArray *segementTitleArray;/**<按钮标题数组*/
@property (nonatomic, strong)NSMutableArray *segementButtonArray;/**<按钮数组 */
@property (nonatomic, strong)UIView *segementContainer;/**<按钮容器*/
@property (nonatomic, strong)UIView *canSliderLine; /**<底部可滑动的线*/
@property (nonatomic, strong)UIView *bottomLine; /**<底部分割线*/

@end

@implementation SLSegmentView
- (instancetype)initWithSegmentWithTitleArray:(NSArray *)segementTitleArray{
    self = [super init];
    if (self) {
        self.segementTitleArray = segementTitleArray;
        self.backgroundColor = RGBOF(0x1A212A);

        [self createSegments];
        [self setUpViews];
    }
    return self;
}

- (void)setUpViews
{
    [self addSubview:self.segementContainer];
    //添加segement到view
    for (UIButton *button in self.segementButtonArray) {
        [self.segementContainer addSubview:button];
    }
    [self addSubview:self.bottomLine];
    [self addSubview:self.canSliderLine];
}

- (void)layoutSubviews
{
    [super layoutSubviews];
    self.segementContainer.top = 0;
    self.segementContainer.left = 0;
    self.segementContainer.width = self.width;
    self.segementContainer.height = self.height;

    self.bottomLine.frame = CGRectMake(0, self.height-APPCONFIG_UNIT_LINE_WIDTH, self.width, APPCONFIG_UNIT_LINE_WIDTH);
    //每个子segment的宽度
    CGFloat segmentWith = (self.width) / self.segementTitleArray.count;
    for (int i=0; i<self.segementButtonArray.count; i++) {
        UIButton *button = (UIButton *)self.segementButtonArray[i];
        button.top = 0;
        button.left = i * segmentWith;
        button.width = segmentWith;
        button.height = 40;
    }
    //设置底部红色滑动线条的位置
    self.canSliderLine.bottom = self.bottomLine.top;
    self.canSliderLine.left = self.segementContainer.left;
    self.canSliderLine.width = segmentWith;
    self.canSliderLine.height = 1.0f;
}

- (void)createSegments
{
    self.segementButtonArray = [NSMutableArray array];
    for (int i=0; i<self.segementTitleArray.count; i++) {
        UIButton *button = [UIButton buttonWithType:UIButtonTypeCustom];
        [button setBackgroundColor:RGBOF(0x1A212A)];
        button.contentVerticalAlignment = UIControlContentVerticalAlignmentCenter;
        [button setTitle:self.segementTitleArray[i] forState:UIControlStateNormal];
        [button setTitle:self.segementTitleArray[i] forState:UIControlStateSelected];
        [button setTitleColor:RGBOF(0xe5e5e5) forState:UIControlStateNormal];
        [button.titleLabel setFont:[UIFont fontWithName:@"PingFang-SC-Medium" size:15]];
        [button setTitleColor:RGBOF(0xF0A70A) forState:UIControlStateSelected];
        //button.titleLabel.font = [UIFont systemFontOfSize:17.0f];
        button.titleLabel.adjustsFontSizeToFitWidth = YES;
        if (i==0) {
            button.selected = YES;
        }else
        {
            button.selected = NO;
        }
        button.tag = i;

        [button addTarget:self action:@selector(clickSegmentButton:) forControlEvents:UIControlEventTouchUpInside];
        [self.segementButtonArray addObject:button];
    }
}

- (void)clickSegmentButton:(UIButton *)segmentButton
{
    [self selectSegmentButton:segmentButton];
    //回调给控制器
    if (self.clickSegmentButton) {
        self.clickSegmentButton(segmentButton.tag);
    }
}

- (void)selectSegmentButton:(UIButton *)segmentButton
{
    segmentButton.selected = YES;
    [self changeSelectedWithSelectedButton:segmentButton];
}
- (void)changeSelectedWithSelectedButton:(UIButton *)selectedButton
{
    for (UIButton *button in self.segementButtonArray) {
        if (![button isEqual:selectedButton]) {
            button.selected = NO;
        }
    }
    //设置可滑动线条的位置
    [self setCanSliderLinePosition];
}

- (void)setCanSliderLinePosition
{
    UIButton *button = [self selectedButton];
    [UIView animateWithDuration:0.3f animations:^{
        self.canSliderLine.left = button.tag * kCanSliderLineWidth;
    }];
}

- (UIButton *)selectedButton
{
    NSInteger selectedIndex = [self selectedIndex];
    if (selectedIndex == -1 || self.segementButtonArray.count <= selectedIndex) {
        return nil;
    }
    return self.segementButtonArray[selectedIndex];
}

- (void)movieToCurrentSelectedSegment:(NSInteger)index
{
    UIButton *button = self.segementButtonArray[index];
    [self selectSegmentButton:button];
}

- (NSInteger)selectedIndex
{
    for(int i = 0; i < self.segementButtonArray.count; i++) {
        UIButton *button = (UIButton *)self.segementButtonArray[i];

        if (button.selected) {
            return i;
        }
    }
    return -1;
}

#pragma mark - 懒加载
- (UIView *)segementContainer
{
    if (!_segementContainer) {
        _segementContainer = [[UIView alloc] init];
        _segementContainer.backgroundColor = RGBOF(0x1A212A);
    }
    return _segementContainer;
}

-(UIView *)canSliderLine
{
    if (!_canSliderLine) {
        _canSliderLine = [[UIView alloc] init];
        _canSliderLine.backgroundColor = RGBOF(0xF0A70A);
    }
    return _canSliderLine;
}

-(UIView *)bottomLine
{
    if (!_bottomLine) {
        _bottomLine = [[UIView alloc] init];
        _bottomLine.backgroundColor = RGBOF(0x1A212A);
    }
    return _bottomLine;
}
-(void)modifyButtonTitle:(NSString*)title{
          UIButton*button=(UIButton*)[self viewWithTag:3];
          [button setTitle:title forState:UIControlStateNormal];
          [button setTitle:title forState:UIControlStateSelected];
}
@end
