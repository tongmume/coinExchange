//
//  PST_MenuView.m
//  DropMenu
//
//  Created by 彭双塔 on 2019/1/12.
//  Copyright © 2019年 pst. All rights reserved.
//

#import "PST_MenuView.h"
#define kWindow [UIApplication sharedApplication].keyWindow

@interface PST_MenuView()<UITableViewDelegate,UITableViewDataSource>
/** 蒙版 */
@property (nonatomic, strong) UIView *cover;
/** 箭头x偏移值 */
@property (nonatomic, assign) CGFloat arrowOffset;
/** tableView */
@property (nonatomic, strong) UITableView *tableView;
/** 存放标题和图片数组 */
@property (nonatomic, strong) NSMutableArray *titleImgArr;
/** rowHeight */
@property (nonatomic, assign) CGFloat rowHeight;
/** 代理 */
@property(nonatomic,weak)id <PST_MenuViewDelegate>delegate;
/** rgb的可变数组 */
@property (nonatomic, strong) NSMutableArray *rgbStrValueArr;
@end
@implementation PST_MenuView

-(instancetype)initWithFrame:(CGRect)frame titleArr:(NSArray *)titleArr imgArr:(NSArray *)imgArr arrowOffset:(CGFloat)arrowOffset rowHeight:(CGFloat)rowHeight layoutType:(PST_MenuViewLayoutType)layoutType directionType:(PST_MenuViewDirectionType)directionType delegate:(id<PST_MenuViewDelegate>)delegate{

    if (self = [super initWithFrame:frame]) {
        self.frame = frame;
        _arrowOffset = arrowOffset;//x的偏移量
        _rowHeight = rowHeight;//row行高
        _delegate = delegate;//点击cell的代理
        _layoutType = layoutType;//布局类型
        _directionType = directionType;//三角形弹出方向

        //字典转模型
        for (int i = 0; i < titleArr.count; i++) {
            NSMutableDictionary *tempDic = [NSMutableDictionary dictionary];
            [tempDic setObject:titleArr[i] forKey:@"title"];
            if (i != imgArr.count) {
                [tempDic setObject:imgArr[i] forKey:@"img"];
            }
            PST_MenuViewModel *model = [PST_MenuViewModel menuViewWithDictonary:tempDic];
            [self.titleImgArr addObject:model];
        }

        [kWindow addSubview:self.cover];//蒙版添加到主窗口
        //蒙版添加手势
        UITapGestureRecognizer *tap = [[UITapGestureRecognizer alloc] initWithTarget:self action:@selector(removeMenuList)];
        [self.cover addGestureRecognizer:tap];
        self.backgroundColor = [UIColor clearColor];
        [kWindow addSubview:self];
        [self addSubview:self.tableView];
    }
    return self;
}
#pragma mrak - 蒙版
-(UIView *)cover{
    if (!_cover) {
        _cover = [[UIView alloc] initWithFrame:kWindow.bounds];
        _cover.backgroundColor = [UIColor blackColor];
        _cover.alpha = 0.2;
    }
    return _cover;
}
#pragma mark - 移除菜单
-(void)removeMenuList{
    [self.tableView removeFromSuperview];
    [self removeFromSuperview];
    [self.cover removeFromSuperview];
}
#pragma mark - 重写drawRect 绘制三角形
-(void)drawRect:(CGRect)rect{
    //An opaque type that represents a Quartz 2D drawing environment.
    //一个不透明类型的Quartz 2D绘画环境,相当于一个画布,你可以在上面任意绘画
    CGContextRef context = UIGraphicsGetCurrentContext();
    /*画三角形*/
    CGPoint sPoints[3];//坐标点
    sPoints[0] = CGPointMake(_arrowOffset, 0);//坐标1
    sPoints[1] = CGPointMake(_arrowOffset - 8, 8);//坐标2
    sPoints[2] = CGPointMake(_arrowOffset + 8, 8);//坐标3
    if (_directionType == PST_MenuViewDirectionUp) {
        sPoints[0] = CGPointMake(_arrowOffset, rect.size.height);//坐标1
        sPoints[1] = CGPointMake(_arrowOffset - 8, rect.size.height - 8);//坐标2
        sPoints[2] = CGPointMake(_arrowOffset + 8, rect.size.height - 8);//坐标3
    }
    CGContextAddLines(context, sPoints, 3);//添加线
    //填充色
    float r = [@"255" floatValue] / 255.0;
    float g = [@"255" floatValue] / 255.0;
    float b = [@"255" floatValue] / 255.0;
    if (self.rgbStrValueArr.count > 0) {
        r = [self.rgbStrValueArr[0] floatValue] / 255.0;
        g = [self.rgbStrValueArr[1] floatValue] / 255.0;
        b = [self.rgbStrValueArr[2] floatValue] / 255.0;
    }
    UIColor *aColor = [UIColor colorWithRed:r green:g blue:b alpha:1];
    CGContextSetFillColorWithColor(context, aColor.CGColor);
    //画线笔颜色
    CGContextSetRGBStrokeColor(context,r, g, b,1.0);//画笔线的颜色
    CGContextClosePath(context);//封起来
    CGContextDrawPath(context, kCGPathFillStroke); //根据坐标绘制路径

//    CGAffineTransform transform = CGAffineTransformMakeRotation(180 * M_PI/180.0);
//    [self setTransform:transform];
}
#pragma mark - 懒加载
- (NSMutableArray *)titleImgArr {
    if (!_titleImgArr) {
        _titleImgArr = [NSMutableArray array];
    }
    return _titleImgArr;
}
-(NSMutableArray *)rgbStrValueArr{
    if (!_rgbStrValueArr) {
        _rgbStrValueArr = [NSMutableArray array];
    }
    return _rgbStrValueArr;
}
#pragma mark - UIColor转RGB
-(NSMutableArray *)rgbWithUIColor:(UIColor *)color{
    NSMutableArray *RGBStrValueArr = [[NSMutableArray alloc] init];
    NSString *RGBStr;
    //获得RGB值描述
    NSString *RGBValue = [NSString stringWithFormat:@"%@",color];
    //将RGB值描述分隔成字符串
    NSArray *RGBArr = [RGBValue componentsSeparatedByString:@" "];
    //获取红色值
    int r = [[RGBArr objectAtIndex:1] intValue] * 255;
    RGBStr = [NSString stringWithFormat:@"%d",r];
    [RGBStrValueArr addObject:RGBStr];
    //获取绿色值
    int g = [[RGBArr objectAtIndex:2] intValue] * 255;
    RGBStr = [NSString stringWithFormat:@"%d",g];
    [RGBStrValueArr addObject:RGBStr];
    //获取蓝色值
    int b = [[RGBArr objectAtIndex:3] intValue] * 255;
    RGBStr = [NSString stringWithFormat:@"%d",b];
    [RGBStrValueArr addObject:RGBStr];
    //返回保存RGB值的数组
    return RGBStrValueArr;
}
#pragma mark - tableView
-(UITableView *)tableView{
    if (!_tableView) {
        CGFloat h = 8;
        if (_directionType == PST_MenuViewDirectionUp) {
            h = 0;
        }
        _tableView = [[UITableView alloc] initWithFrame:CGRectMake(0, h, self.frame.size.width, self.frame.size.height - 8) style:UITableViewStylePlain];
        _tableView.delegate = self;
        _tableView.dataSource = self;
        _tableView.layer.cornerRadius = 6;
        _tableView.layer.masksToBounds = YES;//设置圆角
        _tableView.tableFooterView = [UIView new];//不显示多余分割线
        _tableView.backgroundColor = kRGBColor(17, 17, 17);
        _tableView.separatorStyle = UITableViewCellSeparatorStyleNone;//不显示分割线
        _tableView.scrollEnabled = YES;
        _tableView.rowHeight = _rowHeight;
    }
    return _tableView;
}
-(NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section{
    return self.titleImgArr.count;
}
#pragma mark - 代理数据源
-(UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath{
    PST_MenuViewCell *cell = [PST_MenuViewCell MenuViewCellWithTableView:tableView];
    cell.selectionStyle=UITableViewCellSelectionStyleNone;
    cell.menuViewModel = self.titleImgArr[indexPath.row];
    cell.layoutType = _layoutType;
    cell.titleLab.font = [UIFont systemFontOfSize:15];
    cell.titleLab.textAlignment=NSTextAlignmentCenter;
    cell.backgroundColor = _bgColor;
    cell.lineView.backgroundColor = _lineColor;
    cell.titleLab.textColor = _titleColor;
    return cell;
}
-(void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath{
    PST_MenuViewModel *model = self.titleImgArr[indexPath.row];
    if ([_delegate respondsToSelector:@selector(didSelectRowAtIndex:title:img:)]) {
        [_delegate didSelectRowAtIndex:indexPath.row title:model.title img:model.img];
    }
    [self removeMenuList];
}
#pragma mark - 文字颜色
- (void)setTitleColor:(UIColor *)titleColor {
    _titleColor = titleColor;
    [self.tableView reloadData];
}

#pragma mark - 线条颜色
- (void)setLineColor:(UIColor *)lineColor {
    _lineColor = lineColor;
    [self.tableView reloadData];
}
#pragma mark - 背景颜色
-(void)setBgColor:(UIColor *)bgColor{
    _bgColor = bgColor;
    [self.tableView reloadData];
}
#pragma mark - 三角形颜色
-(void)setArrowColor:(UIColor *)arrowColor{
    _arrowColor = arrowColor;
    self.rgbStrValueArr = [self rgbWithUIColor:arrowColor];
    [self setNeedsDisplay];
}
@end

/**
 *   自定义cell类
 */
@implementation PST_MenuViewCell

#pragma mark - 实例化cell的类方法
+(instancetype)MenuViewCellWithTableView:(UITableView *)tableView{
    static NSString *cellId = @"cellId";
    PST_MenuViewCell *cell = [tableView dequeueReusableCellWithIdentifier:cellId];
    if (!cell) {
        cell = [[PST_MenuViewCell alloc] initWithStyle:UITableViewCellStyleDefault reuseIdentifier:cellId];
    }
    return cell;
}
#pragma mark - 重写
-(instancetype)initWithStyle:(UITableViewCellStyle)style reuseIdentifier:(NSString *)reuseIdentifier{
    if (self = [super initWithStyle:style reuseIdentifier:reuseIdentifier]) {
        self.imgView = [[UIImageView alloc] init];
        [self.contentView addSubview:self.imgView];

        self.titleLab = [[UILabel alloc] init];
        [self.contentView addSubview:self.titleLab];

        self.lineView = [[UIView alloc] init];
        [self.contentView addSubview:self.lineView];
    }
    return self;
}
#pragma mark - 布局
-(void)layoutSubviews{
    [super layoutSubviews];
    CGFloat contentViewH = self.contentView.frame.size.height;

    if (self.layoutType == PST_MenuViewLayoutTypeTitle) {
        self.titleLab.frame = CGRectMake(10, 0, self.contentView.frame.size.width - 10, contentViewH);
    }
    else{
        self.imgView.frame = CGRectMake(10, contentViewH/2 - 8, 16, 16);

        self.titleLab.frame = CGRectMake( 10, 0, self.contentView.frame.size.width - 20, contentViewH);
    }
    self.lineView.frame = CGRectMake(0,contentViewH - 0.5 , self.contentView.frame.size.width, 0.5);
}
-(void)setMenuViewModel:(PST_MenuViewModel *)menuViewModel{
    self.imgView.image = [UIImage imageNamed:menuViewModel.img];
    self.titleLab.text = menuViewModel.title;
}
@end

/**
 *   自定义model类
 */
@implementation PST_MenuViewModel
-(instancetype)initWithDictionary:(NSDictionary *)dic{
    if (self = [super init]) {
        [self setValuesForKeysWithDictionary:dic];
    }
    return self;
}
+(instancetype)menuViewWithDictonary:(NSDictionary *)dic{
    return [[self alloc] initWithDictionary:dic];
}
@end

