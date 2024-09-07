//
//  Y-StockChartView.m
//  BTC-Kline
//
//  Created by yate1996 on 16/4/30.
//  Copyright © 2016年 yate1996. All rights reserved.
//

#import "Y_StockChartView.h"
#import "Y_KLineView.h"
#import "Masonry.h"
#import "Y_StockChartSegmentView.h"
#import "Y_StockChartGlobalVariable.h"
@interface Y_StockChartView() <Y_StockChartSegmentViewDelegate>

{
    NSArray*stockData;
}
/**
 *  K线图View
 */
@property (nonatomic, strong) Y_KLineView *kLineView;

/**
 *  底部选择View
 */
@property (nonatomic, strong) Y_StockChartSegmentView *segmentView;

/**
 *  图表类型
 */
@property(nonatomic,assign) Y_StockChartCenterViewType currentCenterViewType;

/**
 *  当前索引
 */
@property(nonatomic,assign,readwrite) NSInteger currentIndex;
@end


@implementation Y_StockChartView

- (Y_KLineView *)kLineView
{
    if(!_kLineView)
    {
        _kLineView = [Y_KLineView new];
        [self addSubview:_kLineView];
        [_kLineView mas_makeConstraints:^(MASConstraintMaker *make) {
            make.left.right.bottom.equalTo(self);
            make.top.equalTo(self.segmentView.mas_bottom);
        }];
    }
    return _kLineView;
}

- (Y_StockChartSegmentView *)segmentView
{
    if(!_segmentView)
    {
        _segmentView = [Y_StockChartSegmentView new];
        _segmentView.delegate = self;
        [self addSubview:_segmentView];
        [_segmentView mas_makeConstraints:^(MASConstraintMaker *make) {
            make.right.left.top.equalTo(self);
            make.height.equalTo(@0);
        }];
    }
    return _segmentView;
}

- (void)setItemModels:(NSArray *)itemModels
{
    _itemModels = itemModels;
    if(itemModels)
    {
        NSMutableArray *items = [NSMutableArray array];
        for(Y_StockChartViewItemModel *item in itemModels)
        {
            [items addObject:item.title];
        }
        self.segmentView.items = items;
        Y_StockChartViewItemModel *firstModel = itemModels.firstObject;
        self.currentCenterViewType = firstModel.centerViewType;
    }
    if(self.dataSource)
    {
        self.segmentView.selectedIndex = self.DefalutselectedIndex;
    }
}

- (void)setDataSource:(id<Y_StockChartViewDataSource>)dataSource
{
    _dataSource = dataSource;
    if(self.itemModels)
    {
        self.segmentView.selectedIndex = self.DefalutselectedIndex;//默认分时图
    }
}
- (void)reloadData:(NSArray*)arry
{
    stockData=arry;
    self.segmentView.selectedIndex = self.segmentView.selectedIndex;
}

#pragma mark - 代理方法

- (void)y_StockChartSegmentView:(Y_StockChartSegmentView *)segmentView clickSegmentButtonIndex:(NSInteger)index
{
    self.currentIndex = index;//102或106是关闭指标
    
    if (index == 105) {//BOLL指标按钮
        
        [Y_StockChartGlobalVariable setisBOLLLine:Y_StockChartTargetLineStatusBOLL];
        self.kLineView.targetLineStatus = index;
        [self.kLineView reDraw];
        [self bringSubviewToFront:self.segmentView];
        
    } else  if(index >= 100 && index != 105) {//指标面板
        
        [Y_StockChartGlobalVariable setisEMALine:index];
//        if(index == Y_StockChartTargetLineStatusMA)
//        {
//            [Y_StockChartGlobalVariable setisEMALine:Y_StockChartTargetLineStatusMA];
//        } else {
//            [Y_StockChartGlobalVariable setisEMALine:Y_StockChartTargetLineStatusEMA];
//        }
        self.kLineView.targetLineStatus = index;
//        NSLog(@"默认的指标是---%ld",(long)index);
        [self.kLineView reDraw];
        [self bringSubviewToFront:self.segmentView];
    
    } else {//K线种类按钮
        if(self.dataSource && [self.dataSource respondsToSelector:@selector(stockDatasWithIndex:)])
        {
            //id stockData = [self.dataSource stockDatasWithIndex:index];
            
            if(!stockData)
            {
                [self.dataSource stockDatasWithIndex:index];
                return;
            }
            
            Y_StockChartViewItemModel *itemModel = self.itemModels[index];
            
            
            Y_StockChartCenterViewType type = itemModel.centerViewType;

            
            if(type != self.currentCenterViewType)
            {
                //移除当前View，设置新的View
                self.currentCenterViewType = type;
                switch (type) {
                    case Y_StockChartcenterViewTypeKline:
                    {
                        self.kLineView.hidden = NO;
                        //                    [self bringSubviewToFront:self.kLineView];
                        [self bringSubviewToFront:self.segmentView];
                        
                    }
                        break;
                        
                    default:
                        break;
                }
            }
            
            if(type == Y_StockChartcenterViewTypeOther)
            {
                
            } else {
                //K线或分时图
                self.kLineView.kLineModels = (NSArray *)stockData;
                self.kLineView.MainViewType = type;
                [self.kLineView reDraw];
            }
            [self bringSubviewToFront:self.segmentView];
            stockData=nil;
        }
    }

}

@end


/************************ItemModel类************************/
@implementation Y_StockChartViewItemModel

+ (instancetype)itemModelWithTitle:(NSString *)title type:(Y_StockChartCenterViewType)type
{
    Y_StockChartViewItemModel *itemModel = [Y_StockChartViewItemModel new];
    itemModel.title = title;
    itemModel.centerViewType = type;
    return itemModel;
}

@end
