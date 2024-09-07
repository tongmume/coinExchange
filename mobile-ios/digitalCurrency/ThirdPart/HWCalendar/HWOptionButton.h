//
//  HWOptionButton.h
//  HWCalendar
//
//  Created by wqb on 2019/1/12.
//  Copyright © 2019年 hero_wqb. All rights reserved.
//

#import <UIKit/UIKit.h>

@class HWOptionButton;

@protocol HWOptionButtonDelegate <NSObject>

//确认选项后，如有其它特殊操作，用此代理事件
- (void)didSelectOptionInHWOptionButton:(HWOptionButton *)optionButton;

@end

@interface HWOptionButton : UIView

@property (nonatomic, strong) NSArray *array;
@property (nonatomic, copy) NSString *title;
@property (nonatomic, assign) NSInteger row;
@property (nonatomic, assign) BOOL showSearchBar; //default is NO.
@property (nonatomic, weak) id<HWOptionButtonDelegate> delegate;

@end

