//
//  PGPickerColumnView.m
//  PGPickerView
//
//  Created by piggybear on 2017/7/26.
//  Copyright © 2017年 piggybear. All rights reserved.
//

#import "PGPickerColumnView.h"
#import "PGPickerColumnCell.h"
#import "PGPickerTableView.h"

@interface PGPickerColumnView()<UITableViewDelegate, UITableViewDataSource>
@property (nonatomic) CGFloat rowHeight;

@property (nonatomic, weak) UIView *upView;
@property (nonatomic, weak) UIView *centerView;
@property (nonatomic, weak) UIView *downView;

@property (nonatomic, weak) PGPickerTableView *upTableView;
@property (nonatomic, weak) PGPickerTableView *centerTableView;
@property (nonatomic, weak) PGPickerTableView *downTableView;

@property (nonatomic, assign) CGFloat offset;
@property (nonatomic, assign) NSInteger offsetCount;
@property (nonatomic) CGFloat upLinePosY;

@property (nonatomic, assign) CGFloat upLineHeight;
@property (nonatomic, assign) CGFloat downLineHeight;

@property (nonatomic, assign) CGFloat showCount;

@property (nonatomic, assign) BOOL isSubViewLayouted;
@property (nonatomic, assign) BOOL isAnimationOfSelectedRow;
@property (nonatomic, assign) CGFloat numberOfSelectedRow;
@property (nonatomic, assign) BOOL isSelected;

@property (nonatomic, assign) CGFloat circumference;
@property (nonatomic, assign) CGFloat radius;
@end

@implementation PGPickerColumnView

#define kWidth self.frame.size.width
#define kHeight self.frame.size.height

static NSString *const cellReuseIdentifier = @"PGPickerColumnCell";

- (instancetype)initWithFrame:(CGRect)frame rowHeight:(CGFloat)rowHeight upLineHeight:(CGFloat)upLineHeight downLineHeight:(CGFloat)downLineHeight {
    if (self = [super initWithFrame:frame]) {
        self.rowHeight = rowHeight;
        self.upLineHeight = upLineHeight;
        self.downLineHeight = downLineHeight;
        self.backgroundColor = [UIColor clearColor];
        self.upLinePosY = (self.bounds.size.height - self.rowHeight) / 2 - self.upLineHeight;
        NSInteger index = self.upLinePosY / self.rowHeight;
        self.offsetCount = index + 1;
        self.offset = self.offsetCount * self.rowHeight - self.upLinePosY;
        if (self.offset == self.rowHeight) {
            self.offset = 0;
            self.offsetCount -= 1;
        }
        self.showCount = (frame.size.height / self.rowHeight - 1) / 2;
        self.circumference = self.rowHeight * self.showCount * 2 - 25;
        self.radius = self.circumference / M_PI * 2;
        [self setupView];
    }
    return self;
}

- (void)layoutSubviews {
    [super layoutSubviews];
    if (self.isSubViewLayouted) {
        return;
    }
    self.isSubViewLayouted = true;
    if (!self.isSelected) {
        return;
    }
    if (_isAnimationOfSelectedRow) {
        __block id blockSelf = self;
        dispatch_time_t delayTime = dispatch_time(DISPATCH_TIME_NOW, (int64_t)(0.26 * NSEC_PER_SEC));
        dispatch_after(delayTime, dispatch_get_main_queue(), ^{
            [blockSelf selectRow:self.numberOfSelectedRow animated:self.isAnimationOfSelectedRow];
            blockSelf = nil;
        });
    }
}

- (void)setupView {
    CGFloat upViewHeight = kHeight / 2 - self.rowHeight / 2 - self.upLineHeight;
    CGFloat centerViewPosY = upViewHeight + self.upLineHeight;
    CGFloat downViewPosY = centerViewPosY + self.rowHeight + self.downLineHeight;
    
    UIView *upView = [[UIView alloc]initWithFrame:CGRectMake(0, 0, kWidth, upViewHeight)];
    upView.backgroundColor = [UIColor clearColor];
    upView.clipsToBounds = true;
    [self addSubview:upView];
    self.upView = upView;
    
    UIView *downView = [[UIView alloc]initWithFrame:CGRectMake(0, downViewPosY, kWidth, kHeight - downViewPosY)];
    downView.backgroundColor = [UIColor clearColor];
    downView.clipsToBounds = true;
    [self addSubview:downView];
    self.downView = downView;
    
    UIView *centerView = [[UIView alloc]initWithFrame:CGRectMake(0, centerViewPosY, kWidth, self.rowHeight)];
    centerView.backgroundColor = [UIColor clearColor];
    centerView.clipsToBounds = true;
    [self addSubview:centerView];
    self.centerView = centerView;
    [self setupTableView];
}

- (void)setupTableView {
    {
        CGRect frame = self.bounds;
        frame.origin.y = -self.offset;
        frame.size.height += self.offset;
        PGPickerTableView *tableView = [[PGPickerTableView alloc]initWithFrame:frame style:UITableViewStylePlain];
        [tableView registerClass:[PGPickerColumnCell class] forCellReuseIdentifier:cellReuseIdentifier];
        tableView.delegate = self;
        tableView.dataSource = self;
        tableView.showsVerticalScrollIndicator = false;
        tableView.showsHorizontalScrollIndicator = false;
        [self.upView addSubview:tableView];
        self.upTableView = tableView;
    }
    {
        CGRect frame = [self convertRect:self.upTableView.frame toView:self.centerView];
        PGPickerTableView *tableView = [[PGPickerTableView alloc]initWithFrame:frame style:UITableViewStylePlain];
        [tableView registerClass:[PGPickerColumnCell class] forCellReuseIdentifier:cellReuseIdentifier];
        tableView.delegate = self;
        tableView.dataSource = self;
        tableView.showsVerticalScrollIndicator = false;
        tableView.showsHorizontalScrollIndicator = false;
        [self.centerView addSubview:tableView];
        self.centerTableView = tableView;
        [self bringSubviewToFront:tableView];
    }
    
    {
        CGRect frame = [self convertRect:self.upTableView.frame toView:self.downView];
        PGPickerTableView *tableView = [[PGPickerTableView alloc]initWithFrame:frame style:UITableViewStylePlain];
        [tableView registerClass:[PGPickerColumnCell class] forCellReuseIdentifier:cellReuseIdentifier];
        tableView.delegate = self;
        tableView.dataSource = self;
        tableView.showsVerticalScrollIndicator = false;
        tableView.showsHorizontalScrollIndicator = false;
        [self.downView addSubview:tableView];
        self.downTableView = tableView;
    }
}

- (void)selectRow:(NSInteger)row animated:(BOOL)animated {
    _numberOfSelectedRow = row;
    _isAnimationOfSelectedRow = animated;
    _isSelected = true;
    if (!animated) {
        if (self.isSubViewLayouted) {
            self.centerTableView.contentOffset = CGPointMake(0, row * self.rowHeight);
            _isSelected = false;
            self.selectedRow = row;
        }else {
            __block PGPickerColumnView *blockSelf = self;
            dispatch_time_t delayTime = dispatch_time(DISPATCH_TIME_NOW, (int64_t)(0.05 * NSEC_PER_SEC));
            dispatch_after(delayTime, dispatch_get_main_queue(), ^{
                blockSelf.centerTableView.contentOffset = CGPointMake(0, row * blockSelf.rowHeight);
                blockSelf.isSelected = false;
                blockSelf.selectedRow = row;
                blockSelf = nil;
            });
        }
        return;
    }
    if (self.isSubViewLayouted) {
        [self.centerTableView selectRowAtIndexPath: [NSIndexPath indexPathForRow:row + self.offsetCount inSection:0] animated: _isAnimationOfSelectedRow scrollPosition: UITableViewScrollPositionMiddle];
        
        __block PGPickerColumnView *blockSelf = self;
        dispatch_time_t delayTime = dispatch_time(DISPATCH_TIME_NOW, (int64_t)(0.35 * NSEC_PER_SEC));
        dispatch_after(delayTime, dispatch_get_main_queue(), ^{
            [blockSelf scrollViewDidEndDecelerating:blockSelf.centerTableView];
            blockSelf.isSelected = false;
            blockSelf.selectedRow = row;
            blockSelf = nil;
        });
    }
}

- (UIFont *)textFontOfOtherRow:(NSInteger)row {
    if (self.delegate && [self.delegate respondsToSelector:@selector(pickerColumnView:textFontOfOtherRow:InComponent:)]) {
        return [self.delegate pickerColumnView:self textFontOfOtherRow:row InComponent:self.component];
    }
    return self.textFontOfOtherRow;
}

- (UIColor *)textColorOfOtherRow:(NSInteger)row {
    if (self.delegate && [self.delegate respondsToSelector:@selector(pickerColumnView:textColorOfOtherRow:InComponent:)]) {
        return [self.delegate pickerColumnView:self textColorOfOtherRow:row InComponent:self.component];
    }
    return self.textColorOfOtherRow;
}

#pragma mark - UIScrollViewDelegate
- (void)scrollViewDidScroll:(UITableView *)tableView {
    CGPoint offset = tableView.contentOffset;
    NSInteger rowHeight = self.rowHeight;
    NSInteger posY = offset.y;
    NSInteger value = posY % rowHeight;
    CGFloat itemAngle = value * ((rowHeight / self.radius) / rowHeight);
    
    if (self.centerTableView == tableView) {
        self.upTableView.contentOffset = CGPointMake(0, offset.y);
        self.downTableView.contentOffset = CGPointMake(0, offset.y);
        return;
    }
    if (tableView == self.downTableView) {
        self.centerTableView.contentOffset = CGPointMake(0, offset.y);
        if (!self.isHiddenWheels) {
            [tableView.visibleCells enumerateObjectsUsingBlock:^(__kindof PGPickerColumnCell * _Nonnull obj, NSUInteger idx, BOOL * _Nonnull stop) {
                NSUInteger index = idx - self.showCount;
                NSInteger length = index * rowHeight;
                CGFloat angle = length / self.radius - itemAngle;
                CGFloat scale = cos(angle / 2);
                [obj transformWith:angle scale:scale];
            }];
        }
        return;
    }
    if (tableView == self.upTableView) {
        self.centerTableView.contentOffset = CGPointMake(0, offset.y);
        if (!self.isHiddenWheels) {
            [tableView.visibleCells enumerateObjectsUsingBlock:^(__kindof PGPickerColumnCell * _Nonnull obj, NSUInteger idx, BOOL * _Nonnull stop) {
                NSUInteger index = self.showCount - idx;
                NSInteger length = index * rowHeight;
                CGFloat angle = length / self.radius + itemAngle;
                CGFloat scale = cos(angle / 2);
                [obj transformWith:angle scale: scale];
            }];
        }
    }
}

- (void)scrollViewDidEndDragging:(UITableView *)scrollView willDecelerate:(BOOL)decelerate{
    if (decelerate) return;
    [self scrollViewDidEndDecelerating:scrollView];
}

- (void)scrollViewDidEndDecelerating:(UITableView *)tableView {
    NSIndexPath *indexPath = [tableView indexPathForRowAtPoint:CGPointMake(tableView.contentOffset.x, tableView.contentOffset.y + self.rowHeight / 2)];
    [tableView scrollToRowAtIndexPath: indexPath atScrollPosition: UITableViewScrollPositionTop animated:YES];
    if (!_isSelected) {
        NSInteger row =  self.centerTableView.contentOffset.y / self.rowHeight +  0.5;
        self.selectedRow = row;
    }
}

- (void)scrollViewDidEndScrollingAnimation:(UIScrollView *)scrollView {
    NSInteger row =  self.centerTableView.contentOffset.y / self.rowHeight +  0.5;
    self.selectedRow = row;
}

#pragma mark - row logic
- (NSUInteger)numberOfRowsInTableView {
    return self.datas.count + self.offsetCount * 2;
}

- (void)safeReloadData {
    [self.centerTableView reloadData];
    [self.upTableView reloadData];
    [self.downTableView reloadData];
    
    NSInteger index =  self.centerTableView.contentOffset.y / self.rowHeight + 0.5;
    NSAttributedString *attriString = [[NSAttributedString alloc]initWithString:@""];
    if (self.datas.count > index) {
        attriString = self.datas[index];
        self.textOfSelectedRow = attriString.string;
    }
}

#pragma mark - UITableViewDelegate
- (void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath {
    NSInteger row = indexPath.row;
    if (row < self.offsetCount || row >= self.datas.count + self.offsetCount) {
        return;
    }
    [tableView selectRowAtIndexPath:[NSIndexPath indexPathForRow:indexPath.row - self.offsetCount inSection:0] animated:true scrollPosition:UITableViewScrollPositionTop];
    
    self.selectedRow = indexPath.row - self.offsetCount;
}

- (CGFloat)tableView:(UITableView *)tableView heightForRowAtIndexPath:(NSIndexPath *)indexPath {
    if ([self numberOfRowsInTableView] - 1 == indexPath.row) {
        CGFloat tmp = self.offsetCount * self.rowHeight - self.upLinePosY;
        if (self.rowHeight > 44) {
            return fabs(tmp - self.rowHeight);
        }
    }
    return self.rowHeight;
}

- (CGFloat)tableView:(UITableView *)tableView estimatedHeightForRowAtIndexPath:(NSIndexPath *)indexPath {
    return self.rowHeight;
}

#pragma mark - UITableViewDataSource
- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section {
    return [self numberOfRowsInTableView];
}

- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath {
    tableView.separatorStyle = UITableViewCellSeparatorStyleNone;
    PGPickerColumnCell *cell = [tableView dequeueReusableCellWithIdentifier:cellReuseIdentifier];
    NSInteger row = indexPath.row - self.offsetCount;
    if (indexPath.row < self.offsetCount || row >= self.datas.count) {
        cell.label.attributedText = [[NSAttributedString alloc] initWithString: @""];
        cell.contentView.backgroundColor = [UIColor clearColor];
    }else {
        cell.label.attributedText = self.datas[row];
        cell.contentView.backgroundColor = self.viewBackgroundColors[row];
        
        if (!self.isHiddenWheels) {
            if (tableView == self.downTableView) {
                NSUInteger index = row - self.selectedRow;
                NSInteger length = index * self.rowHeight;
                CGFloat angle = length / self.radius;
                CGFloat scale = cos(angle / 2);
                [cell transformWith:angle scale:scale];
            }else if (tableView == self.upTableView) {
                NSUInteger index = self.selectedRow - row;
                NSInteger length = index * self.rowHeight;
                CGFloat angle = length / self.radius;
                CGFloat scale = cos(angle / 2);
                [cell transformWith:angle scale:scale];
            }
        }
    }
    if (tableView == self.centerTableView) {
        cell.label.textColor = self.textColorOfSelectedRow;
        cell.label.font = self.textFontOfSelectedRow;
    }else {
        cell.label.textColor = [self textColorOfOtherRow:row];
        cell.label.font = [self textFontOfOtherRow:row];
    }
    return cell;
}

#pragma mark - Setter
- (void)setSelectedRow:(NSUInteger)selectedRow {
    _selectedRow = selectedRow;
    if (self.datas.count > selectedRow) {
        NSAttributedString *attriString = self.datas[selectedRow];
        self.textOfSelectedRow = attriString.string;
    }
    if (self.delegate && [self.delegate respondsToSelector:@selector(pickerColumnView:didSelectRow:)]) {
        [self.delegate pickerColumnView:self didSelectRow:selectedRow];
    }
    if (self.delegate && [self.delegate respondsToSelector:@selector(pickerColumnView:title:didSelectRow:)]) {
        [self.delegate pickerColumnView:self title:self.textOfSelectedRow didSelectRow:selectedRow];
    }
}

- (void)setDatas:(NSArray *)datas {
    _datas = datas;
    [self safeReloadData];
}

@end
