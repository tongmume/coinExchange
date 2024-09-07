//
//  JKBannarView.m
//
//  Created by 王冲 on 2019/9/8.
//  Copyright © 2019年 yangsq. All rights reserved.
//

#import "JKBannarView.h"
/** SDWebImage里面的类*/
#import "UIImageView+WebCache.h"
#import "SDImageCache.h"
#import "UIButton+WebCache.h"
#define MaxSections 100
#import "JKBannerCollectionViewCell.h"

/** 自定义颜色 */
#define JKRGBCOLOR(r,g,b,a) [UIColor colorWithRed:(r)/255.0 green:(g)/255.0 blue:(b)/255.0 alpha:a]

@interface JKBannarView ()<UICollectionViewDataSource,UICollectionViewDelegate>

@property (strong, nonatomic) NSMutableArray *imageViews;
@property (strong, nonatomic) UICollectionView *myCollectionView;
@property (strong, nonatomic) UIPageControl *mypageControl;
@property (assign, nonatomic) CGSize viewSize;
@property (strong, nonatomic) NSTimer *timer;

/** index */
@property (nonatomic ,assign) NSInteger index;
/** index2 */
@property (nonatomic ,assign) NSInteger index2;


/** pageControl */
//@property (nonatomic ,strong) PageControl * pageControl;
@end

@implementation JKBannarView

- (void)dealloc{
    _imageViews = nil;
    [self removeNSTimer];
}

- (NSMutableArray *)imageViews{
    if (!_imageViews) {
        _imageViews = @[].mutableCopy;
    }

    return _imageViews;
}


- (id)initWithFrame:(CGRect)frame viewSize:(CGSize)viewSize{
    self = [super initWithFrame:frame];
    if (self) {
        self.viewSize = viewSize;
        [self addNSTimer];
        self.index = 0;

    }

    return self;
}


- (UICollectionView *)myCollectionView{

    if (!_myCollectionView) {
        UICollectionViewFlowLayout *flowLayout = [[UICollectionViewFlowLayout alloc] init];
        flowLayout.itemSize = CGSizeMake(self.viewSize.width, self.viewSize.height);
        flowLayout.minimumLineSpacing = 0;
        flowLayout.scrollDirection = UICollectionViewScrollDirectionHorizontal;
        _myCollectionView = [[UICollectionView alloc] initWithFrame:CGRectMake(0, 0, self.viewSize.width, self.viewSize.height) collectionViewLayout:flowLayout];
        _myCollectionView.delegate = self;
        _myCollectionView.dataSource = self;
        _myCollectionView.showsHorizontalScrollIndicator = NO;
        _myCollectionView.pagingEnabled = YES;
        _myCollectionView.backgroundColor = [UIColor clearColor];

        [_myCollectionView registerClass:[JKBannerCollectionViewCell class] forCellWithReuseIdentifier:@"ID"];
        [_myCollectionView scrollToItemAtIndexPath:[NSIndexPath indexPathForItem:0 inSection:MaxSections / 2] atScrollPosition:UICollectionViewScrollPositionLeft animated:YES];
        [self addSubview:_myCollectionView];
        _mypageControl = [[UIPageControl alloc]init];
        _mypageControl.currentPageIndicatorTintColor = baseColor;
        _mypageControl.pageIndicatorTintColor = [UIColor whiteColor];

        [self addSubview:_mypageControl];
        [_mypageControl mas_makeConstraints:^(MASConstraintMaker *make) {
            make.left.right.equalTo(self);
            make.height.equalTo(@30);
            make.bottom.equalTo(self).offset(-5);
        }];

//        self.pageControl = [[PageControl alloc]initWithFrame:CGRectMake(100, 100, 84, 10) andDotCount:_items.count];
//        self.pageControl.center=CGPointMake(self.frame.size.width/2.0, self.frame.size.height-15);
//        self.pageControl.backgroundColor=[UIColor clearColor];
//        [self addSubview:self.pageControl];

    }
    return _myCollectionView;

}

- (NSInteger)numberOfSectionsInCollectionView:(UICollectionView *)collectionView
{
    return MaxSections;
}
- (NSInteger)collectionView:(UICollectionView *)collectionView numberOfItemsInSection:(NSInteger)section
{
    return _items.count;
}

- (UICollectionViewCell *)collectionView:(UICollectionView *)collectionView cellForItemAtIndexPath:(NSIndexPath *)indexPath
{

    JKBannerCollectionViewCell *cell = [collectionView dequeueReusableCellWithReuseIdentifier:@"ID" forIndexPath:indexPath];
    [cell.imageView sd_setImageWithURL:[NSURL URLWithString:_items[indexPath.row]] placeholderImage:[UIImage imageNamed:@"noplaceholderImage.png"]];
    return cell;

}

- (void)setItems:(NSArray *)items{
    _items = items;
    if (_items.count<2) {
        self.myCollectionView.scrollEnabled = NO;
        [self.mypageControl setHidden:YES];
      //  [_pageControl setHidden:YES];
    }else{
        NSMutableArray *muArr = [NSMutableArray arrayWithArray:items];
//        [muArr addObject:[items firstObject]];
        _items = muArr;
        self.myCollectionView.scrollEnabled = YES;
        [self.mypageControl setHidden:NO];
        NO;
    }
    [self.myCollectionView reloadData];
    self.mypageControl.numberOfPages = _items.count;
}

- (void)scrollViewDidScroll:(UIScrollView *)scrollView{

    int page = (int)(scrollView.contentOffset.x / scrollView.frame.size.width + 0.5) % _items.count;
    self.mypageControl.currentPage = page;

     self.index2 = (int)(scrollView.contentOffset.x / scrollView.frame.size.width + 0.5) % _items.count;
    if (self.index2  == _items.count) {
        self.index2 = 0;
    }
   // [_pageControl setSelectedIndex:self.index2];

}

#pragma mark -添加定时器
-(void)addNSTimer{

    _timer =[NSTimer scheduledTimerWithTimeInterval:5.0 target:self selector:@selector(nextPage) userInfo:nil repeats:YES];
    [[NSRunLoop mainRunLoop]addTimer:_timer forMode:NSRunLoopCommonModes];
}

- (void)nextPage{

    if (_items.count<2) {
        return;
    }

    NSIndexPath *currentIndexPath = [[self.myCollectionView indexPathsForVisibleItems] lastObject];
    NSIndexPath *currentIndexPathSet = [NSIndexPath indexPathForItem:currentIndexPath.item inSection:MaxSections / 2];

    [self.myCollectionView scrollToItemAtIndexPath:currentIndexPathSet atScrollPosition:UICollectionViewScrollPositionLeft animated:NO];
    NSInteger nextItem = currentIndexPathSet.item + 1;
    NSInteger nextSection = currentIndexPathSet.section;
    if (nextItem == _items.count) {
        nextItem = 0;
        nextSection ++;
    }
    NSIndexPath *nextIndexPath = [NSIndexPath indexPathForItem:nextItem inSection:nextSection];
    [self.myCollectionView scrollToItemAtIndexPath:nextIndexPath atScrollPosition:UICollectionViewScrollPositionLeft animated:YES];
}

- (void)collectionView:(UICollectionView *)collectionView didSelectItemAtIndexPath:(NSIndexPath *)indexPath{
    if (self.imageViewClick) {
        self.imageViewClick(self,indexPath.row);
    }
}


#pragma mark -删除定时器

-(void)removeNSTimer{
    [_timer invalidate];
    _timer =nil;
}

#pragma mark -当用户开始拖拽的时候就调用移除计时器
-(void)scrollViewWillBeginDragging:(UIScrollView *)scrollView
{
    [self removeNSTimer];
}
#pragma mark -当用户停止拖拽的时候调用添加定时器
-(void)scrollViewDidEndDragging:(UIScrollView *)scrollView willDecelerate:(BOOL)decelerate
{
    [self addNSTimer];
}



- (void)imageViewClick:(void (^)(JKBannarView *, NSInteger))block{
    self.imageViewClick = block;
}


@end
