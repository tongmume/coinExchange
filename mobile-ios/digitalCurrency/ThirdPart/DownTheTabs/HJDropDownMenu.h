//
//  HJDropDownMenu.h
//  digitalCurrency
//
//  Created by chu on 2019/8/7.
//  Copyright © 2019年 BIZZAN. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface HJDropDownMenu : UIView

@property (nonatomic, assign) CGFloat rowHeight;

@property (nonatomic, strong) NSArray * datas;

@property (nonatomic, strong) UIColor *textColor;

@property (nonatomic, strong) UIColor *indicatorColor;

@property (nonatomic, strong) UIFont * font;

@property (nonatomic, copy) NSString *placeHolde;
//选中后自动收起
@property (nonatomic, assign) BOOL autoCloseWhenSelected;

//选中回调
@property (nonatomic, copy) void(^cellClickedBlock)(NSString *title,NSInteger index);

@property (nonatomic, copy) void(^headerClickedBlock)(void);

- (void)closeMenu;

@end
