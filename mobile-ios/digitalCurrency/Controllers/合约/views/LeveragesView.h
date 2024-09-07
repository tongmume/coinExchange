//
//  LeveragesView.h
//  digitalCurrency
//
//  Created by ios on 2020/9/18.
//  Copyright © 2020 BIZZAN. All rights reserved.
//

#import <UIKit/UIKit.h>

NS_ASSUME_NONNULL_BEGIN

@interface LeveragesView : UIView

@property (nonatomic, assign)NSNumber *leverageType;// 杠杆类型

@property (nonatomic, strong)NSArray *titles;

@property (nonatomic, strong)NSString *selectLeverString;

@property (nonatomic, copy) void (^selcetLeverageblock)(NSInteger idx , NSString *selectStr);

-(void)showsLevergesView;

-(void)showMyScrollView;

-(void)showSliderView;

-(instancetype)initTitles:(NSArray *)titles;

@end

NS_ASSUME_NONNULL_END
