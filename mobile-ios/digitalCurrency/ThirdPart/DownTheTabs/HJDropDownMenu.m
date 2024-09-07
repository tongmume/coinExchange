//
//  HJDropDownMenu.m
//  digitalCurrency
//
//  Created by chu on 2019/8/7.
//  Copyright © 2019年 BIZZAN. All rights reserved.
//

#import "HJDropDownMenu.h"
#import "UIView+MJExtension.h"

@interface HJDropDownMenu ()<UITableViewDelegate,UITableViewDataSource>

@property (nonatomic, strong) UITableView *listTable;

@property (nonatomic, strong) UIButton *headerBtn;

@property (nonatomic, assign) BOOL headerSelected;

@property (nonatomic, strong) CAShapeLayer *shapeLayer;


@end

#define dropBackColor  RGBOF(0x1F2833)


static NSString * const kDropMenuCellIdentifier = @"DropMenuCellIdentifier";
static const CGFloat kCellDefaultHeight = 44.f;

@implementation HJDropDownMenu

#pragma mark - Life Circle
-(instancetype)initWithFrame:(CGRect)frame;{
    self = [super initWithFrame:frame];
    if(self){
        [self configData];
        [self setupUI];
    }
    return self;
}

- (instancetype)init
{
    self = [super init];
    if (self) {
        [self configData];
        [self setupUI];
    }
    return self;
}


- (void)configData{

    self.autoCloseWhenSelected = YES;

    self.indicatorColor = RGBOF(0x999999);

    self.textColor = RGBOF(0x333333);

    self.font = [UIFont systemFontOfSize:14.f];

}

#pragma mark - About UI
- (void)setupUI{

    self.layer.borderWidth = 0.5f;
    self.layer.borderColor = dropBackColor.CGColor;
    self.layer.cornerRadius = 5.f;
    self.layer.masksToBounds = YES;

    [self addSubview:self.listTable];


    [self.listTable setFrame:CGRectMake(0, 0, self.mj_h, kCellDefaultHeight)];
    [self.listTable registerClass:[UITableViewCell class] forCellReuseIdentifier:kDropMenuCellIdentifier];
    self.listTable.bounces = NO;
    self.listTable.backgroundColor = dropBackColor;
}


- (void)layoutSubviews{
    [super layoutSubviews];

    if (self.headerSelected) {
        if (self.datas.count > 4) {
            self.mj_h = 5*self.rowHeight + self.rowHeight;

        }else{
            self.mj_h = self.datas.count*self.rowHeight + self.rowHeight;
        }
    }else{
        self.mj_h = self.rowHeight;
    }
    self.listTable.frame = self.bounds;
}
#pragma mark - Event response
- (void)sectionHeaderClicked{
    if (self.headerClickedBlock) {
        self.headerClickedBlock();
    }
    self.headerSelected = !self.headerSelected;
    [self setNeedsLayout];
    __weak typeof(self) weakSelf = self;
    [self animateIndicator:self.shapeLayer Forward:self.headerSelected complete:^{
        [weakSelf cellInsertOrDelete:self.headerSelected];
    }];
}
#pragma mark - Pravite Method
- (CAShapeLayer *)createIndicatorWithColor:(UIColor *)color andPosition:(CGPoint)point {
    CAShapeLayer *layer = [CAShapeLayer new];

    UIBezierPath *path = [UIBezierPath new];
    [path moveToPoint:CGPointMake(0, 0)];
    [path addLineToPoint:CGPointMake(8, 0)];
    [path addLineToPoint:CGPointMake(4, 5)];
    [path closePath];

    layer.path = path.CGPath;
    layer.lineWidth = 1.0;
    layer.fillColor = color.CGColor;

    CGPathRef bound = CGPathCreateCopyByStrokingPath(layer.path, nil, layer.lineWidth, kCGLineCapButt, kCGLineJoinMiter, layer.miterLimit);
    layer.bounds = CGPathGetBoundingBox(bound);

    CGPathRelease(bound);

    layer.position = point;

    return layer;
}


- (void)animateIndicator:(CAShapeLayer *)indicator Forward:(BOOL)forward complete:(void(^)(void))complete {
    [CATransaction begin];
    [CATransaction setAnimationDuration:0.25];
    [CATransaction setAnimationTimingFunction:[CAMediaTimingFunction functionWithControlPoints:0.4 :0.0 :0.2 :1.0]];

    CAKeyframeAnimation *anim = [CAKeyframeAnimation animationWithKeyPath:@"transform.rotation"];
    anim.values = forward ? @[ @0, @(M_PI) ] : @[ @(M_PI), @0 ];

    if (!anim.removedOnCompletion) {
        [indicator addAnimation:anim forKey:anim.keyPath];
    } else {
        [indicator addAnimation:anim forKey:anim.keyPath];
        [indicator setValue:anim.values.lastObject forKeyPath:anim.keyPath];
    }

    [CATransaction commit];

    complete();
}


- (void)cellInsertOrDelete:(BOOL)insert{

    [self.listTable beginUpdates];
    NSMutableArray *indexPaths = [NSMutableArray arrayWithCapacity:self.datas.count];

    [self.datas enumerateObjectsUsingBlock:^(id  _Nonnull obj, NSUInteger idx, BOOL * _Nonnull stop) {
        NSIndexPath *indexP = [NSIndexPath indexPathForRow:idx inSection:0];
        [indexPaths addObject:indexP];
    }];

    if (insert) {
        [self.listTable insertRowsAtIndexPaths:indexPaths withRowAnimation:UITableViewRowAnimationTop];
    }else{
        [self.listTable deleteRowsAtIndexPaths:indexPaths withRowAnimation:UITableViewRowAnimationBottom];
    }
    [self.listTable endUpdates];
}
#pragma mark - Public Method
- (void)closeMenu{
    if (self.headerSelected) {
        [self sectionHeaderClicked];
    }
}
#pragma mark - Getters/Setters/Lazy
- (UITableView *)listTable{
    if (!_listTable) {
        _listTable = [[UITableView alloc] init];
        _listTable.separatorStyle = UITableViewCellSeparatorStyleNone;
        _listTable.delegate = self;
        _listTable.dataSource = self;
        _listTable.backgroundColor = dropBackColor;
    }
    return _listTable;
}


- (void)setDatas:(NSArray *)datas{
    _datas = datas;

    [self.listTable reloadData];
}


- (void)setRowHeight:(CGFloat)rowHeight{
    _rowHeight = rowHeight;

    [self setNeedsDisplay];
}
#pragma mark - Delegate methods

#pragma mark - UITableViewDataSource
- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section{

    return self.headerSelected?self.datas.count:0;
}

- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath{
    UITableViewCell *cell = [tableView dequeueReusableCellWithIdentifier:kDropMenuCellIdentifier];
    cell.backgroundColor = dropBackColor;
    cell.textLabel.text = self.datas[indexPath.row];
    cell.textLabel.font = self.font;
    cell.textLabel.textColor = self.textColor;
    cell.contentView.layer.borderColor = dropBackColor.CGColor;
    return cell;
}


- (NSInteger)numberOfSectionsInTableView:(UITableView *)tableView{

    return 1;
}

#pragma mark - UITableViewDelegate
- (void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath{

    [self.headerBtn setTitle:self.datas[indexPath.row] forState:UIControlStateNormal];

    if(self.cellClickedBlock){
        self.cellClickedBlock(self.datas[indexPath.row], indexPath.row);
    }


    if (self.autoCloseWhenSelected) {
        [self closeMenu];
    }

}


- (UIView *)tableView:(UITableView *)tableView viewForHeaderInSection:(NSInteger)section{

//    UIButton *headerBtn = [[UIButton alloc] initWithFrame:CGRectMake(0, 0, tableView.mj_w, self.rowHeight==0?kCellDefaultHeight:self.rowHeight)];
//    [headerBtn setBackgroundColor:[UIColor whiteColor]];
//    headerBtn.titleLabel.font = self.font;
//    [headerBtn setTitleColor:self.textColor forState:UIControlStateNormal];
//    [headerBtn setTitle:self.datas[0] forState:UIControlStateNormal];
//    [headerBtn setContentHorizontalAlignment:UIControlContentHorizontalAlignmentLeft];
//    [headerBtn setTitleEdgeInsets:UIEdgeInsetsMake(0, 15, 0, 0)];
//    [headerBtn addTarget:self action:@selector(sectionHeaderClicked) forControlEvents:UIControlEventTouchUpInside];
////
//    CGPoint position = CGPointMake(self.headerBtn.mj_w-10,self.headerBtn.mj_h/2.f);
//    CAShapeLayer *shapeLayer = [self createIndicatorWithColor:self.indicatorColor andPosition:position];
//    [self.headerBtn.layer addSublayer:shapeLayer];
//
//
//    self.shapeLayer = shapeLayer;
//    self.headerBtn = headerBtn;
    return self.headerBtn;
}

- (UIButton *)headerBtn{
    if (!_headerBtn) {
        _headerBtn = [[UIButton alloc] initWithFrame:CGRectMake(0, 0, kWindowW - 30, self.rowHeight==0?kCellDefaultHeight:self.rowHeight)];
        [_headerBtn setBackgroundColor:dropBackColor];
        _headerBtn.titleLabel.font = self.font;
        [_headerBtn setTitleColor:self.textColor forState:UIControlStateNormal];
        [_headerBtn setTitle:self.placeHolde forState:UIControlStateNormal];
        [_headerBtn setContentHorizontalAlignment:UIControlContentHorizontalAlignmentLeft];
        [_headerBtn setTitleEdgeInsets:UIEdgeInsetsMake(0, 15, 0, 0)];
        [_headerBtn addTarget:self action:@selector(sectionHeaderClicked) forControlEvents:UIControlEventTouchUpInside];

//        CGPoint position = CGPointMake(_headerBtn.mj_w-10,_headerBtn.mj_h/2.f);
//        CAShapeLayer *shapeLayer = [self createIndicatorWithColor:self.indicatorColor andPosition:position];
        [_headerBtn.layer addSublayer:self.shapeLayer];

//        self.shapeLayer = shapeLayer;
    }
    return _headerBtn;
}

- (CAShapeLayer *)shapeLayer{
    if (!_shapeLayer) {
        CGPoint position = CGPointMake(self.headerBtn.mj_w-10,self.headerBtn.mj_h/2.f);
        _shapeLayer = [self createIndicatorWithColor:self.indicatorColor andPosition:position];
    }
    return _shapeLayer;
}

- (CGFloat)tableView:(UITableView *)tableView heightForRowAtIndexPath:(NSIndexPath *)indexPath{

    return self.rowHeight==0?kCellDefaultHeight:self.rowHeight;
}

- (CGFloat)tableView:(UITableView *)tableView heightForHeaderInSection:(NSInteger)section{

    return self.rowHeight==0?kCellDefaultHeight:self.rowHeight;
}

- (CGFloat)tableView:(UITableView *)tableView heightForFooterInSection:(NSInteger)section{

    return 0.001f;
}

- (void)setPlaceHolde:(NSString *)placeHolde{
    _placeHolde = placeHolde;
    [self.headerBtn setTitle:placeHolde forState:UIControlStateNormal];
}


@end
