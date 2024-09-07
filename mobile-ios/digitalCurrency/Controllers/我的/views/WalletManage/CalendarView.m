//
//  CalendarView.m
//  digitalCurrency
//
//  Created by iDog on 2019/2/6.
//  Copyright © 2019年 BIZZAN. All rights reserved.
//

#import "CalendarView.h"

@implementation CalendarView

-(void)awakeFromNib{
    [super awakeFromNib];

    self.backgroundColor=[[UIColor blackColor] colorWithAlphaComponent:0.4];
    HWCalendar *calendar = [[HWCalendar alloc] initWithFrame:CGRectMake(0, SafeAreaTopHeight+30, kWindowW, kWindowH-SafeAreaTopHeight-60-SafeAreaBottomHeight)];
    calendar.delegate = self;
    calendar.showTimePicker = YES;
    [self addSubview:calendar];

}
#pragma mark - HWCalendarDelegate
- (void)calendar:(HWCalendar *)calendar didClickSureButtonWithDate:(NSString *)date
{
    [self removeFromSuperview];
}
-(void)didClickCancelButton{

    [self removeFromSuperview];
}
/*
// Only override drawRect: if you perform custom drawing.
// An empty implementation adversely affects performance during animation.
- (void)drawRect:(CGRect)rect {
    // Drawing code
}
*/

@end
