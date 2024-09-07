//
//  HWCalendar.h
//  HWCalendar
//
//  Created by wqb on 2019/1/12.
//  Copyright © 2019年 hero_wqb. All rights reserved.
//

#import <UIKit/UIKit.h>

@class HWCalendar;

@protocol HWCalendarDelegate <NSObject>

- (void)calendar:(HWCalendar *)calendar didClickSureButtonWithDate:(NSString *)date;
- (void)didClickCancelButton;

@end

@interface HWCalendar : UIView

@property (nonatomic, assign) BOOL showTimePicker; //default is NO. doesn't show timePicker

@property (nonatomic, weak) id<HWCalendarDelegate> delegate;

- (void)show;
- (void)dismiss;

@end
