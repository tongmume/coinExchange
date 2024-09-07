//
//  CustomSectionHeader.h
//  digitalCurrency
//
//  Created by sunliang on 2019/4/11.
//  Copyright © 2019年 BIZZAN. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface CustomSectionHeader : UIView
+(CustomSectionHeader *)instancesectionHeaderViewWithFrame:(CGRect)Rect;
@property (weak, nonatomic) IBOutlet UIButton *morebut;

@property (nonatomic,copy)void (^moreBlock)(void);
@end
