//
//  ZWMSegmentView.m
//  ZWMSegmentController
//
//  Created by 伟明 on 2019/11/23.
//  Copyright © 2019年 伟明. All rights reserved.
//

#import "ZWMSegmentView.h"

typedef void(^ZWMIndexBlock)(NSUInteger ,UIButton *);
int const ZWMSegmentHeight = 40;//可根据项目需求设置高度

@interface ZWMSegmentView ()
@property (nonatomic, strong, readwrite) NSMutableArray *buttons;
@property (nonatomic, strong) UIImageView *backgroundImageView;
@property (nonatomic, strong, readwrite) UIScrollView *contentView;
@property (nonatomic, assign) int segmentHeight;
@property (nonatomic, readwrite) NSUInteger index;
@property (nonatomic, strong) NSArray *titles;
@property (nonatomic, strong) UIView *separateLine;
@property (nonatomic, strong) UIButton *selectedButton; /**< 当前被选中的按钮*/
@property (nonatomic, strong) UIView *indicateView;     /**< 指示杆*/
@property (nonatomic, copy)   ZWMIndexBlock indexBlock;
@property (nonatomic, assign) CGFloat indicateHeight;   /**< 指示杆高度*/
@property (nonatomic, assign, readwrite) NSTimeInterval duration;  /**< 滑动时间*/
@property (nonatomic, assign) CGSize size;
@property (nonatomic, assign, readwrite) CGFloat buttonSpace;      /**< 按钮title到边的间距*/
@property (nonatomic, assign) CGFloat minItemSpace;     /**< 最小Item之间的间距*/
@property (nonatomic, strong) UIFont *font;
@end

@implementation ZWMSegmentView

+ (instancetype)segmentViewWithFrame:(CGRect)frame titles:(NSArray<NSString *> *)titles {
    return [[self alloc] initWithFrame:frame titles:titles];
}

- (instancetype)initWithFrame:(CGRect)frame titles:(NSArray <NSString *>*)titles {
    self = [super initWithFrame:frame];
    if (!titles.count || !self) {
        return nil;
    }

    _titles = titles;
    _size = frame.size;
    [self segmentBasicSetting];
    [self segmentPageSetting];

    return self;
}

- (void)segmentBasicSetting {
    self.backgroundColor = [UIColor colorWithWhite:1. alpha:0.5];
    _buttons = [NSMutableArray array];
    _segmentHeight = ZWMSegmentHeight;
    _minItemSpace = 40.;
    _segmentTintColor = [UIColor blackColor];
    _segmentNormalColor = [UIColor blackColor];
    _font = [UIFont systemFontOfSize:17];
    _buttonSpace = [self calculateSpace];
    _indicateHeight = 2.;
    _duration = .3;
    _scrollEnabled = YES;
    _showSeparateLine = NO;
    self.frame = CGRectMake(self.frame.origin.x, self.frame.origin.y, _size.width, _segmentHeight);
}

- (CGFloat)calculateSpace {
    CGFloat space = 0;
    CGFloat totalWidth = 0;

    for (NSString *title in _titles) {
        CGSize titleSize = [title sizeWithAttributes:@{NSFontAttributeName : _font}];
        totalWidth += titleSize.width;
    }

    space = (_size.width - totalWidth) / _titles.count / 2;
    if (space > _minItemSpace / 2) {
        return space;
    } else {
        return _minItemSpace / 2;
    }
}

- (void)segmentPageSetting {
    _backgroundImageView = [[UIImageView alloc] initWithFrame:self.bounds];
    _backgroundImageView.contentMode = UIViewContentModeScaleAspectFill;
    _backgroundImageView.clipsToBounds = YES;
    [self addSubview:_backgroundImageView];

    _contentView = [[UIScrollView alloc] initWithFrame:CGRectMake(0, 0, _size.width, _segmentHeight)];
    _contentView.backgroundColor = kRGBColor(228, 231, 232);//背景色
    _contentView.showsHorizontalScrollIndicator = NO;
    _contentView.showsVerticalScrollIndicator = NO;
    _contentView.scrollEnabled = _scrollEnabled;

    _indicateView = [[UIView alloc] initWithFrame:CGRectMake(0, _segmentHeight-_indicateHeight, _size.width, _indicateHeight)];
    _indicateView.backgroundColor = _segmentTintColor;

    CGFloat item_x = 0;
    for (int i = 0; i < _titles.count; i++) {
        NSString *title = _titles[i];
        CGSize titleSize = [title sizeWithAttributes:@{NSFontAttributeName: _font}];
        UIButton *button = [UIButton buttonWithType:UIButtonTypeCustom];
        button.frame = CGRectMake(item_x, 0, _buttonSpace * 2 + titleSize.width, _segmentHeight);
        [button.titleLabel   setFont :[UIFont   fontWithName:@"Helvetica"  size:17]];
        [button setTag:i];
        //[button.titleLabel setFont:_font];
        [button setTitle:title forState:UIControlStateNormal];
        [button setTitleColor:_segmentNormalColor forState:UIControlStateNormal];
        [button setTitleColor:_segmentTintColor forState:UIControlStateSelected];
        [button addTarget:self action:@selector(didClickButton:) forControlEvents:UIControlEventTouchUpInside];
        [_contentView addSubview:button];

        [_buttons addObject:button];
        item_x += _buttonSpace * 2 + titleSize.width;

        if (i == 0) {
            button.selected = YES;
            _selectedButton = button;

            // 添加指示杆
            _indicateView.frame = CGRectMake(_buttonSpace-50, _segmentHeight - _indicateHeight, titleSize.width+50+50, _indicateHeight);
            [_contentView addSubview:_indicateView];
        }
    }
    self.contentView.contentSize = CGSizeMake(item_x, _segmentHeight);

    _separateLine = [[UIView alloc] initWithFrame:CGRectMake(0, _segmentHeight - 0.5, self.contentView.contentSize.width, 0.5)];
    _separateLine.hidden = !_showSeparateLine;
//    _separateLine.backgroundColor = ZJYColorHexAndAlpha(@"A3A3AE", 0.9);
    [_contentView addSubview:_separateLine];
    [self addSubview:_contentView];
}

#pragma mark - 按钮点击

- (void)didClickButton:(UIButton *)button {
    if (button != _selectedButton) {
        button.selected = YES;
        _selectedButton.selected = NO;
        _selectedButton = button;

        [self scrollIndicateView];
        [self scrollSegementView];
    }

    if (_indexBlock) {
        _indexBlock(_selectedButton.tag, button);
    }
}

#pragma mark - 滑动
/**
 根据选中的按钮滑动指示杆
 */
- (void)scrollIndicateView {
    NSUInteger index = self.index;
    CGSize titleSize = [_selectedButton.titleLabel.text sizeWithAttributes:@{NSFontAttributeName: _font}];
    [UIView animateWithDuration:_duration delay:0 options:UIViewAnimationOptionCurveEaseInOut animations:^{
        if (_style == ZWMSegmentStyleDefault) {
            _indicateView.frame = CGRectMake(CGRectGetMinX(_selectedButton.frame) + _buttonSpace, CGRectGetMinY(_indicateView.frame), titleSize.width, _indicateHeight);
        } else {
            _indicateView.frame = CGRectMake(CGRectGetMinX(_selectedButton.frame), CGRectGetMinY(_indicateView.frame), [self widthAtIndex:index], _indicateHeight);
        }
    } completion:nil];
}

/**
 根据选中调整segementView的offset
 */
- (void)scrollSegementView {
    CGFloat selectedWidth = _selectedButton.frame.size.width;
    CGFloat offsetX = (_size.width - selectedWidth) / 2;

    if (_selectedButton.frame.origin.x <= _size.width / 2) {
        [_contentView setContentOffset:CGPointMake(0, 0) animated:YES];
    } else if (CGRectGetMaxX(_selectedButton.frame) >= (_contentView.contentSize.width - _size.width / 2)) {
        [_contentView setContentOffset:CGPointMake(_contentView.contentSize.width - _size.width, 0) animated:YES];
    } else {
        [_contentView setContentOffset:CGPointMake(CGRectGetMinX(_selectedButton.frame) - offsetX, 0) animated:YES];
    }
}

- (void)adjustOffsetXToFixIndicatePosition:(CGFloat)offsetX {
    NSInteger currentIndex = [self index];

    // 当当前的偏移量大于被选中index的偏移量的时候，就是在右侧
    CGFloat offset; // 在同一侧的偏移量
    NSInteger buttonIndex = currentIndex;
    if (offsetX >= [self index] * _size.width) {
        offset = offsetX - [self index] * _size.width;
        buttonIndex += 1;
    } else {
        offset = [self index] * _size.width - offsetX;
        buttonIndex -= 1;
        currentIndex -= 1;
    }

    CGFloat originMovedX = _style == ZWMSegmentStyleDefault? (CGRectGetMinX(_selectedButton.frame) + _buttonSpace) : CGRectGetMinX(_selectedButton.frame);
    CGFloat targetMovedWidth = [self widthAtIndex:currentIndex];//需要移动的距离

    CGFloat targetButtonWidth = _style == ZWMSegmentStyleDefault? ([self widthAtIndex:buttonIndex] - 2 * _buttonSpace) : [self widthAtIndex:buttonIndex]; // 这个会影响width
    CGFloat originButtonWidth = _style == ZWMSegmentStyleDefault? ([self widthAtIndex:[self index]] - 2 * _buttonSpace) : [self widthAtIndex:[self index]];


    CGFloat moved; // 移动的距离
    moved = offsetX - [self index] * _size.width;
    _indicateView.frame = CGRectMake(originMovedX + targetMovedWidth / _size.width * moved, _indicateView.frame.origin.y,  originButtonWidth + (targetButtonWidth - originButtonWidth) / _size.width * offset, _indicateView.frame.size.height);
}

#pragma mark - index

- (NSUInteger)index {
    return _selectedButton.tag;
}

- (void)setSelectedAtIndex:(NSUInteger)index {
    for (UIView *view in _contentView.subviews) {
        if ([view isKindOfClass:[UIButton class]] && view.tag == index) {
            UIButton *button = (UIButton *)view;
            [self didClickButton:button];
            break;
        }
    }
}

- (CGFloat)widthAtIndex:(NSUInteger)index {
    if (index > _titles.count - 1) {
        return 0;
    }
    UIButton *button = [_buttons objectAtIndex:index];
    return CGRectGetWidth(button.frame);
}

- (void)selectedAtIndex:(void (^)(NSUInteger, UIButton *))indexBlock {
    if (indexBlock) {
        _indexBlock = indexBlock;
    }
}

#pragma mark - set

- (void)setSeparateColor:(UIColor *)separateColor {
    _separateColor = separateColor;

    self.separateLine.backgroundColor = separateColor;
}

- (void)setSegmentTintColor:(UIColor *)segmentTintColor {
    _segmentTintColor = segmentTintColor;
    _indicateView.backgroundColor = segmentTintColor;
    for (UIView *view in _contentView.subviews) {
        if ([view isKindOfClass:[UIButton class]]) {
            UIButton *button = (UIButton *)view;
            [button setTitleColor:segmentTintColor
                         forState:UIControlStateSelected];
        }
    }
}

- (void)setSegmentNormalColor:(UIColor *)segmentNormalColor {
    _segmentNormalColor = segmentNormalColor;
    for (UIView *view in _contentView.subviews) {
        if ([view isKindOfClass:[UIButton class]]) {
            UIButton *button = (UIButton *)view;
            [button setTitleColor:segmentNormalColor
                         forState:UIControlStateNormal];
        }
    }
}

- (void)setStyle:(ZWMSegmentStyle)style {
    _style = style;

    if (style==ZWMSegmentStyleFlush) {
        _indicateView.frame = CGRectMake(_selectedButton.frame.origin.x, _segmentHeight - _indicateHeight, [self widthAtIndex:0], _indicateHeight);
    }
}

- (void)setScrollEnabled:(BOOL)scrollEnabled {
    _scrollEnabled = scrollEnabled;

    _contentView.scrollEnabled = scrollEnabled;
}

- (void)setShowSeparateLine:(BOOL)showSeparateLine {
    _showSeparateLine = showSeparateLine;

    _separateLine.hidden = !showSeparateLine;
}

- (void)setBackgroundImage:(UIImage *)backgroundImage {
    _backgroundImage = backgroundImage;

    if (backgroundImage) {
        self.backgroundImageView.image = backgroundImage;
        self.contentView.backgroundColor = [UIColor clearColor];
    }
}

#pragma mark - get

- (int)segmentHeight {
    return _segmentHeight;
}

@end
