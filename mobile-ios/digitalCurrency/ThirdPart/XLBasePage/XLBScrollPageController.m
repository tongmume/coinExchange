//
//  XLBasePageController.m
//  XLPackKnowledge
//
//  Created by apple on 16/12/7.
//  Copyright © 2016年 apple. All rights reserved.
//

#import "XLBScrollPageController.h"

@interface XLBScrollPageController ()<UIPageViewControllerDelegate,UIPageViewControllerDataSource>
{
    NSInteger totalVC;         //VC的总数量
    NSArray *VCArray;          //存放VC的数组
    NSArray *buttonArray;      //存放VC Button的数组
    UIView *headerView;        //头部视图
    CGRect oldRect;            //用来保存title布局的Rect
    XLScrollBasePageTitleButton *oldButton;
    NSInteger currentVCIndex;  //当前VC索引
    
}
@property (nonatomic,strong) UIPageViewController *pageViewController;
@property (nonatomic,strong) UIScrollView *titleBGScroll;
@end

@implementation XLBScrollPageController

- (void)viewDidLoad {
    [super viewDidLoad];
    self.isScroll = self.isScroll?YES:NO;
    self.lineWidth = self.lineWidth>0?self.lineWidth:1.5;
    self.titleFont = self.titleFont?self.titleFont:[UIFont systemFontOfSize:14.0];
    self.defaultColor = self.defaultColor?self.defaultColor:[UIColor blackColor];
    self.chooseColor = self.chooseColor?self.chooseColor:[UIColor redColor];
    
    [self addChildViewController:self.pageViewController];
    [self.view addSubview:self.pageViewController.view];
    [self.view addSubview:self.titleBackground];
    
}

-(UIPageViewController *)pageViewController
{
    if (!_pageViewController) {

        _pageViewController = [[UIPageViewController alloc] initWithTransitionStyle:UIPageViewControllerTransitionStyleScroll navigationOrientation:UIPageViewControllerNavigationOrientationHorizontal options:nil];
        _pageViewController.delegate = self;
        _pageViewController.dataSource = self;
            
        for (UIView *view in self.pageViewController.view.subviews) {
                
                UIScrollView *scrollView = (UIScrollView *)view;
                
                scrollView.scrollEnabled = NO;
            }
        
    }
    return _pageViewController;
}

-(UIScrollView *)titleBackground
{
    if (!_titleBGScroll) {
        _titleBGScroll = [[UIScrollView alloc] init];
        _titleBGScroll.backgroundColor = kRGBColor(228, 231, 232);
        _titleBGScroll.showsHorizontalScrollIndicator = NO;
        _titleBGScroll.showsVerticalScrollIndicator = NO;

    }
    return _titleBGScroll;
}

-(void)setDataSource:(id<XLBScrollPageControllerDataSource>)dataSource
{
    _dataSource = dataSource;
    //[self reloadScrollPage];
}

-(void)reloadScrollPage
{
    if ([self.dataSource respondsToSelector:@selector(numberViewControllersInViewPager:)]) {
        oldRect = CGRectZero;
        totalVC = [self.dataSource numberViewControllersInViewPager:self];
        NSMutableArray *VCList = [NSMutableArray array];
        NSMutableArray *buttonList = [NSMutableArray array];
        for (int i = 0; i<totalVC; i++) {
            if ([self.dataSource respondsToSelector:@selector(viewPager:indexViewControllers:)]) {
                
                id viewcontroller = [self.dataSource viewPager:self indexViewControllers:i];
                if ([viewcontroller isKindOfClass:[UIViewController class]]) {
                    [VCList addObject:viewcontroller];
                }
            }
            
            if ([self.dataSource respondsToSelector:@selector(viewPager:titleWithIndexViewControllers:)]) {
                NSString *buttonTitle = [self.dataSource viewPager:self titleWithIndexViewControllers:i];
                if (buttonArray.count > i) {
                    [[buttonArray objectAtIndex:i] removeFromSuperview];
                }
                UIButton *button;
                if ([self.dataSource respondsToSelector:@selector(viewPager:titleButtonStyle:)])
                {
                    if ([[self.dataSource viewPager:self titleButtonStyle:i] isKindOfClass:[UIButton class]]) {
                        button = [self.dataSource viewPager:self titleButtonStyle:i];
                    }
                }else{
                    
                    XLScrollBasePageTitleButton *autoButton = [[XLScrollBasePageTitleButton alloc] init];
                    
                    autoButton.buttonlineWidth = self.lineWidth;
                    [autoButton.titleLabel setFont:self.titleFont];
                    [autoButton setTitleColor:self.defaultColor forState:UIControlStateNormal];
                    [autoButton setTitleColor:self.chooseColor forState:UIControlStateSelected];
                    autoButton.backgroundColor=[UIColor clearColor];
                    button = autoButton;
                    
                }
                [button addTarget:self action:@selector(titleButtonClick:) forControlEvents:UIControlEventTouchUpInside];
                
                
                button.frame = CGRectMake(oldRect.origin.x+oldRect.size.width, 0, [self textString:buttonTitle withFontHeight:20], [self.dataSource respondsToSelector:@selector(heightForTitleViewPager:)]?[self.dataSource heightForTitleViewPager:self]:0);
                oldRect = button.frame;
                [button setTitle:buttonTitle forState:UIControlStateNormal];
                [buttonList addObject:button];
                [_titleBGScroll addSubview:button];
                if (i == self.selectIndex) {
                    oldButton = [buttonList objectAtIndex:self.selectIndex];
                    oldButton.selected = YES;
                }
            }
        }
        //当所有按钮尺寸小于屏幕宽度的时候要重新布局
        if (buttonList.count && ((UIButton *)buttonList.lastObject).frame.origin.x + ((UIButton *)buttonList.lastObject).frame.size.width<self.view.frame.size.width)
        {
            oldRect = CGRectZero;
            CGFloat padding = self.view.frame.size.width-(((UIButton *)buttonList.lastObject).frame.origin.x + ((UIButton *)buttonList.lastObject).frame.size.width);
            for (XLScrollBasePageTitleButton *button in buttonList) {
                button.frame = CGRectMake(oldRect.origin.x+oldRect.size.width, 0,button.frame.size.width+padding/buttonList.count, [self.dataSource respondsToSelector:@selector(heightForTitleViewPager:)]?[self.dataSource heightForTitleViewPager:self]:0);
                oldRect = button.frame;
            }
        }
        buttonArray = [buttonList copy];
        VCArray = [VCList copy];
    }
    if ([self.dataSource respondsToSelector:@selector(headerViewForInViewPager:)]) {
        [headerView removeFromSuperview];
        headerView = [self.dataSource headerViewForInViewPager:self];
        [self.view addSubview:headerView];
    }
    if (VCArray.count) {
        [_pageViewController setViewControllers:@[VCArray[self.selectIndex]] direction:UIPageViewControllerNavigationDirectionForward animated:YES completion:nil];
    }
}

-(void)titleButtonClick:(XLScrollBasePageTitleButton *)sender
{
    oldButton.selected = NO;
    sender.selected = YES;
    oldButton = sender;
    NSInteger index = [buttonArray indexOfObject:sender];
    [_pageViewController setViewControllers:@[VCArray[index]] direction:UIPageViewControllerNavigationDirectionForward animated:NO completion:nil];
    [self scrollViewOffset:sender];
}

-(void)titleButtonConvert:(XLScrollBasePageTitleButton *)sender
{
    oldButton.selected = NO;
    sender.selected = YES;
    oldButton = sender;
    [self scrollViewOffset:sender];
    
}

-(void)scrollViewOffset:(UIButton *)button
{
    if (!(_titleBGScroll.contentSize.width>CGRectGetWidth(self.view.frame))) {
        return;
    }
    if (CGRectGetMidX(button.frame)>CGRectGetMidX(self.view.frame)) {
        if (_titleBGScroll.contentSize.width<CGRectGetMaxX(self.view.frame)/2+CGRectGetMidX(button.frame)) {
            [_titleBGScroll setContentOffset:CGPointMake(_titleBGScroll.contentSize.width-CGRectGetWidth(self.view.frame), 0) animated:YES];
        }
        else{
            [_titleBGScroll setContentOffset:CGPointMake(CGRectGetMidX(button.frame)-CGRectGetWidth(self.view.frame)/2, 0) animated:YES];
        }
    }
    else{
        [_titleBGScroll setContentOffset:CGPointMake(0, 0) animated:YES];
    }
}

#pragma mark -UIPageViewControllerDelegate
- (void)pageViewController:(UIPageViewController *)pageViewController didFinishAnimating:(BOOL)finished previousViewControllers:(NSArray<UIViewController *> *)previousViewControllers transitionCompleted:(BOOL)completed
{
    if (completed) {
        if (currentVCIndex != [VCArray indexOfObject:previousViewControllers[0]]) {
            [self chooseTitleIndex:currentVCIndex];
            if ([self.delegate respondsToSelector:@selector(viewPagerViewController:didFinishScrollWithCurrentViewController:)]) {
                [self.delegate viewPagerViewController:self didFinishScrollWithCurrentViewController:[VCArray objectAtIndex:currentVCIndex]];
            }
        }
        
    }
}

- (void)pageViewController:(UIPageViewController *)pageViewController willTransitionToViewControllers:(NSArray<UIViewController *> *)pendingViewControllers
{
    currentVCIndex = [VCArray indexOfObject:pendingViewControllers[0]];
    if ([self.delegate respondsToSelector:@selector(viewPagerViewController:willScrollerWithCurrentViewController:)]) {
        [self.delegate viewPagerViewController:self willScrollerWithCurrentViewController:pageViewController.viewControllers[0]];
    }
}

#pragma mark -UIPageViewControllerDataSource
- (nullable UIViewController *)pageViewController:(UIPageViewController *)pageViewController viewControllerBeforeViewController:(UIViewController *)viewController
{
    NSInteger index = [VCArray indexOfObject:viewController];
    if (index == 0 || index == NSNotFound) {
        return nil;
        
    }else{
        return VCArray[--index];
    }
}

- (nullable UIViewController *)pageViewController:(UIPageViewController *)pageViewController viewControllerAfterViewController:(UIViewController *)viewController
{
    NSInteger index = [VCArray indexOfObject:viewController];
    if (index == VCArray.count-1 || index == NSNotFound) {
        return nil;
        
    }else{
        return VCArray[++index];
    }
}

-(void)chooseTitleIndex:(NSInteger)index
{
    [self titleButtonConvert:buttonArray[index]];
}

-(void)viewDidLayoutSubviews
{
    headerView.frame = CGRectMake(0, self.topLayoutGuide.length, self.view.frame.size.width,[self.dataSource respondsToSelector:@selector(heightForHeaderViewPager:)]?[self.dataSource heightForHeaderViewPager:self]:0);
    
    _titleBGScroll.frame = CGRectMake(0, (headerView.frame.size.height)?headerView.frame.origin.y+headerView.frame.size.height:self.topLayoutGuide.length, self.view.frame.size.width,[self.dataSource respondsToSelector:@selector(heightForTitleViewPager:)]?[self.dataSource heightForTitleViewPager:self]:0);
    
    if (buttonArray.count) {
        
        _titleBGScroll.contentSize = CGSizeMake(((UIButton *)buttonArray.lastObject).frame.size.width+((UIButton *)buttonArray.lastObject).frame.origin.x, _titleBGScroll.frame.size.height);
    }
    
    _pageViewController.view.frame = CGRectMake(0, _titleBGScroll.frame.origin.y+_titleBGScroll.frame.size.height, self.view.frame.size.width, self.view.frame.size.height-(_titleBGScroll.frame.origin.y+_titleBGScroll.frame.size.height));
}

#pragma mark 计算字体宽度
-(CGFloat)textString:(NSString *)text withFontHeight:(CGFloat)height
{
    CGFloat padding = 20;
    NSDictionary *fontAttribute = @{NSFontAttributeName : self.titleFont};
    CGSize fontSize = [text boundingRectWithSize:CGSizeMake(MAXFLOAT, height) options:NSStringDrawingUsesLineFragmentOrigin|NSStringDrawingUsesFontLeading attributes:fontAttribute context:nil].size;
    return fontSize.width+padding;
}

@end

#pragma mark 标题按钮的属性设置
@implementation XLScrollBasePageTitleButton
- (instancetype)init
{
    self = [super init];
    if (self) {
        
    }
    return self;
}

-(void)drawRect:(CGRect)rect
{
    if (self.selected) {
        CGFloat lineWidth = 5.0;
        CGColorRef color = mainColor.CGColor;
        CGContextRef ctx = UIGraphicsGetCurrentContext();
        CGContextSetStrokeColorWithColor(ctx, color);
        CGContextSetLineWidth(ctx, lineWidth);
        CGContextMoveToPoint(ctx, 0, self.frame.size.height);
        CGContextAddLineToPoint(ctx, self.frame.size.width, self.frame.size.height);
        CGContextStrokePath(ctx);
    }
}

@end
