//
//  HWCalendar.m
//  HWCalendar
//
//  Created by wqb on 2019/1/12.
//  Copyright © 2019年 hero_wqb. All rights reserved.
//

#import "HWCalendar.h"
#import "HWOptionButton.h"

#define KCol 7
#define KBtnW (kWindowW == 320.0 ? 33.0 : kWindowW == 375.0 ? 40.0 :44)
#define KBtnH (kWindowW == 320.0 ? 33.0 : kWindowW == 375.0 ? 40.0 :44)
#define KMaxCount 37
#define KBtnTag 100
#define KTipsW (kWindowW == 320.0 ? 80.0 : kWindowW == 375.0 ? 95.0 :100)
#define KShowYearsCount 100
#define KMainColor [UIColor colorWithRed:0.0f green:139/255.0f blue:125/255.0f alpha:1.0f]
#define KbackColor [UIColor colorWithRed:173/255.0f green:212/255.0f blue:208/255.0f alpha:1.0f]

@interface HWCalendar ()<UIPickerViewDelegate, UIPickerViewDataSource, HWOptionButtonDelegate>

@property (nonatomic, strong) NSArray *weekArray;
@property (nonatomic, strong) NSArray *timeArray;
@property (nonatomic, strong) NSArray *yearArray;
@property (nonatomic, strong) NSArray *monthArray;
@property (nonatomic, strong) UIPickerView *timePicker;
@property (nonatomic, weak) UIView *calendarView;
@property (nonatomic, weak) HWOptionButton *yearBtn;
@property (nonatomic, weak) HWOptionButton *monthBtn;
@property (nonatomic, weak) UILabel *weekLabel;
@property (nonatomic, weak) UILabel *yearLabel;
@property (nonatomic, weak) UILabel *monthLabel;
@property (nonatomic, weak) UILabel *dayLabel;
@property (nonatomic, assign) NSInteger year;
@property (nonatomic, assign) NSInteger month;
@property (nonatomic, assign) NSInteger day;
@property (nonatomic, assign) NSInteger hour;
@property (nonatomic, assign) NSInteger minute;
@property (nonatomic, assign) NSInteger currentYear;
@property (nonatomic, assign) NSInteger currentMonth;
@property (nonatomic, assign) NSInteger currentDay;

@end

@implementation HWCalendar

- (instancetype)initWithFrame:(CGRect)frame
{
    if (self = [super initWithFrame:frame]) {
        //获取当前时间
        [self getCurrentDate];

        //获取数据源
        [self getDataSource];

        //创建控件
        [self creatControl];

        //初始化设置
        [self setDefaultInfo];

        //刷新数据
        [self reloadData];
    }

    return self;
}

- (void)getDataSource
{
    _weekArray = @[@"日", @"一", @"二", @"三", @"四", @"五", @"六"];
    _timeArray = @[@[@"00", @"01", @"02", @"03", @"04", @"05", @"06", @"07", @"08", @"09", @"10", @"11", @"12", @"13", @"14", @"15", @"16", @"17", @"18", @"19", @"20", @"21", @"22", @"23"], @[@"01", @"02", @"03", @"04", @"05", @"06", @"07", @"08", @"09", @"10", @"11", @"12", @"13", @"14", @"15", @"16", @"17", @"18", @"19", @"20", @"21", @"22", @"23", @"24", @"25", @"26", @"27", @"28", @"29", @"30", @"31", @"32", @"33", @"34", @"35", @"36", @"37", @"38", @"39", @"40", @"41", @"42", @"43", @"44", @"45", @"46", @"47", @"48", @"49", @"50", @"51", @"52", @"53", @"54", @"55", @"56", @"57", @"58", @"59"]];

    NSInteger firstYear = _year - KShowYearsCount / 2;
    NSMutableArray *yearArray = [NSMutableArray array];
    for (int i = 0; i < KShowYearsCount; i++) {
        [yearArray addObject:[NSString stringWithFormat:@"%ld", firstYear + i]];
    }
    _yearArray = yearArray;
    _monthArray = @[@"1", @"2", @"3", @"4", @"5", @"6", @"7", @"8", @"9", @"10", @"11", @"12"];
}

- (void)setDefaultInfo
{
    self.backgroundColor = [UIColor whiteColor];
    [_timePicker selectRow:_hour inComponent:0 animated:NO];
    [_timePicker selectRow:_minute - 1 inComponent:1 animated:NO];
    _currentYear = _year;
    _currentMonth = _month;
    _currentDay = _day;
}

- (void)creatControl
{
    //左侧显示视图
    UIView *tipsView = [[UIView alloc] initWithFrame:CGRectMake(0, 0, KTipsW, KBtnH * 8)];
    tipsView.backgroundColor = KMainColor;
    [self addSubview:tipsView];

    //星期标签
    UILabel *weekLabel = [[UILabel alloc] initWithFrame:CGRectMake(0, 0, KTipsW, KBtnH)];
    weekLabel.backgroundColor = [UIColor colorWithRed:0.0f green:130/255.0f blue:116/255.0f alpha:1.0f];
    weekLabel.textColor = [UIColor whiteColor];
    weekLabel.textAlignment = NSTextAlignmentCenter;
    [tipsView addSubview:weekLabel];
    self.weekLabel = weekLabel;

    //年份标签
    UILabel *yearLabel = [[UILabel alloc] initWithFrame:CGRectMake(0, CGRectGetMaxY(weekLabel.frame) + 20, KTipsW, KBtnH)];
    yearLabel.textColor = KbackColor;
    yearLabel.textAlignment = NSTextAlignmentCenter;
    yearLabel.font = [UIFont systemFontOfSize:26.0f];
    [tipsView addSubview:yearLabel];
    self.yearLabel = yearLabel;

    //月份标签
    UILabel *monthLabel = [[UILabel alloc] initWithFrame:CGRectMake(0, CGRectGetMaxY(yearLabel.frame), KTipsW, 26)];
    monthLabel.textColor = [UIColor whiteColor];
    monthLabel.textAlignment = NSTextAlignmentCenter;
    monthLabel.font = [UIFont systemFontOfSize:26.0f];
    [tipsView addSubview:monthLabel];
    self.monthLabel = monthLabel;

    //日期标签
    UILabel *dayLabel = [[UILabel alloc] initWithFrame:CGRectMake(0, CGRectGetMaxY(monthLabel.frame) + 30, KTipsW, 120)];
    dayLabel.textColor = [UIColor whiteColor];
    dayLabel.textAlignment = NSTextAlignmentCenter;
    dayLabel.font = [UIFont systemFontOfSize:76.0f];
    [tipsView addSubview:dayLabel];
    self.dayLabel = dayLabel;

    CGFloat yearBtnW = 60.0f;
    CGFloat monthbtnW = 50.0f;
    CGFloat todayBtnW = 60.0f;
    CGFloat padding = (self.bounds.size.width - KTipsW - yearBtnW - monthbtnW - todayBtnW - KBtnW * 2) * 0.25;

    //年份按钮
    HWOptionButton *yearBtn = [[HWOptionButton alloc] initWithFrame:CGRectMake(KTipsW + padding, 0, yearBtnW, KBtnH)];
    yearBtn.array = _yearArray;
    yearBtn.row = KShowYearsCount / 2;
    yearBtn.delegate = self;
    [self addSubview:yearBtn];
    self.yearBtn = yearBtn;

    //上一月
    UIButton *preBtn = [[UIButton alloc] initWithFrame:CGRectMake(CGRectGetMaxX(yearBtn.frame) + padding, 0, KBtnW, KBtnH)];
    [preBtn setImage:[UIImage imageNamed:@"leftCalendarImage"] forState:UIControlStateNormal];
    [preBtn addTarget:self action:@selector(preBtnOnClick) forControlEvents:UIControlEventTouchUpInside];
    [self addSubview:preBtn];

    //月份按钮
    HWOptionButton *monthBtn = [[HWOptionButton alloc] initWithFrame:CGRectMake(CGRectGetMaxX(preBtn.frame), 0, monthbtnW, KBtnH)];
    monthBtn.array = _monthArray;
    monthBtn.row = _month - 1;
    monthBtn.delegate = self;
    [self addSubview:monthBtn];
    self.monthBtn = monthBtn;

    //下一月
    UIButton *nextBtn = [[UIButton alloc] initWithFrame:CGRectMake(CGRectGetMaxX(monthBtn.frame), 0, KBtnW, KBtnH)];
    [nextBtn setImage:[UIImage imageNamed:@"rightCalendarImage"] forState:UIControlStateNormal];
    [nextBtn addTarget:self action:@selector(nextBtnOnClick) forControlEvents:UIControlEventTouchUpInside];
    [self addSubview:nextBtn];

    //返回今天按钮
    UIButton *backTodayBtn = [[UIButton alloc] initWithFrame:CGRectMake(CGRectGetMaxX(nextBtn.frame) + padding, 0, todayBtnW, KBtnH)];
    backTodayBtn.titleLabel.font = [UIFont systemFontOfSize:14.0f];
    [backTodayBtn setTitleColor:[UIColor blackColor] forState:UIControlStateNormal];
    [backTodayBtn setTitle:@"返回今天" forState:UIControlStateNormal];
    [backTodayBtn addTarget:self action:@selector(backTodayBtnOnClick) forControlEvents:UIControlEventTouchUpInside];
    [self addSubview:backTodayBtn];

    //星期标签
    for (int i = 0; i < _weekArray.count; i++) {
        UILabel *week = [[UILabel alloc] initWithFrame:CGRectMake(KTipsW + KBtnH * i, KBtnH, KBtnH, KBtnH)];
        week.textAlignment = NSTextAlignmentCenter;
        week.text = _weekArray[i];
        [self addSubview:week];
    }

    //日历核心视图
    UIView *calendarView = [[UIView alloc] initWithFrame:CGRectMake(KTipsW, KBtnH * 2, KBtnW * 7, KBtnH * 6)];
    [self addSubview:calendarView];
    self.calendarView = calendarView;

    //每一个日期用一个按钮去创建，当一个月的第一天是星期六并且有31天时为最多个数，5行零2个，共37个
    for (int i = 0; i < KMaxCount; i++) {
        CGFloat btnX = i % KCol * KBtnW;
        CGFloat btnY = i / KCol * KBtnH;

        UIButton *btn = [[UIButton alloc] initWithFrame:CGRectMake(btnX, btnY, KBtnW, KBtnH)];
        btn.tag = i + KBtnTag;
        btn.layer.cornerRadius = KBtnW * 0.5;
        btn.layer.masksToBounds = YES;
        [btn setTitle:[NSString stringWithFormat:@"%d", i + 1] forState:UIControlStateNormal];
        [btn setTitleColor:[UIColor blackColor] forState:UIControlStateNormal];
        [btn setTitleColor:KMainColor forState:UIControlStateSelected];
        [btn setBackgroundImage:[self imageWithColor:KbackColor] forState:UIControlStateHighlighted];
        [btn setBackgroundImage:[self imageWithColor:KbackColor] forState:UIControlStateSelected];
        [btn addTarget:self action:@selector(dateBtnOnClick:) forControlEvents:UIControlEventTouchUpInside];
        [calendarView addSubview:btn];
    }

    //确认按钮
    UIButton *sureBtn = [[UIButton alloc] initWithFrame:CGRectMake(CGRectGetMinX(backTodayBtn.frame), CGRectGetMaxY(calendarView.frame), yearBtnW, KBtnH)];
    sureBtn.titleLabel.font = [UIFont systemFontOfSize:16.0f];
    [sureBtn setTitle:@"确定" forState:UIControlStateNormal];
    [sureBtn setTitleColor:KMainColor forState:UIControlStateNormal];
    [sureBtn addTarget:self action:@selector(sureBtnOnClick) forControlEvents:UIControlEventTouchUpInside];
    [self addSubview:sureBtn];

    //取消按钮
    UIButton *cancelBtn = [[UIButton alloc] initWithFrame:CGRectMake(CGRectGetMinX(sureBtn.frame) - yearBtnW, CGRectGetMinY(sureBtn.frame), yearBtnW, KBtnH)];
    cancelBtn.titleLabel.font = [UIFont systemFontOfSize:16.0f];
    [cancelBtn setTitle:@"取消" forState:UIControlStateNormal];
    [cancelBtn setTitleColor:KMainColor forState:UIControlStateNormal];
    [cancelBtn addTarget:self action:@selector(cancelBtnOnClick) forControlEvents:UIControlEventTouchUpInside];
    [self addSubview:cancelBtn];

    //时间选择器
    _timePicker = [[UIPickerView alloc] init];
    _timePicker.backgroundColor = KMainColor;
    _timePicker.hidden = YES;
    _timePicker.delegate = self;
    _timePicker.dataSource = self;
    [self addSubview:_timePicker];
}

//set方法
- (void)setShowTimePicker:(BOOL)showTimePicker
{
    _showTimePicker = showTimePicker;

    if (showTimePicker) {
        _timePicker.hidden = NO;
        _dayLabel.frame = CGRectMake(0, CGRectGetMaxY(_monthLabel.frame) + 10, KTipsW, 120);
        _timePicker.frame = CGRectMake(10, CGRectGetMaxY(_dayLabel.frame), KTipsW - 20, 88);
    }else {
        _timePicker.hidden = YES;
        _dayLabel.frame = CGRectMake(0, CGRectGetMaxY(_monthLabel.frame) + 30, 200, 120);
    }
}

//上一月按钮点击事件
- (void)preBtnOnClick
{
    if (_month == 1) {
        if (_yearBtn.row == 0) return;
        _year --;
        _month = 12;
        _yearBtn.row --;
    }else {
        _month --;
    }

    _monthBtn.row = _month - 1;
    [self reloadData];
}

//下一月按钮点击事件
- (void)nextBtnOnClick
{
    if (_month == 12) {
        if (_yearBtn.row == KShowYearsCount - 1) return;
        _year ++;
        _month = 1;
        _yearBtn.row ++;
    }else {
        _month ++;
    }

    _monthBtn.row = _month - 1;
    [self reloadData];
}

//返回今天
- (void)backTodayBtnOnClick
{
    _year = _currentYear;
    _month = _currentMonth;
    _monthBtn.row = _month - 1;
    _yearBtn.row = KShowYearsCount / 2;

    [self reloadData];
}

//刷新数据
- (void)reloadData
{
    NSInteger totalDays = [self numberOfDaysInMonth];
    NSInteger firstDay = [self firstDayOfWeekInMonth];

    _yearLabel.text = [NSString stringWithFormat:@"%ld", _year];
    _monthLabel.text = [NSString stringWithFormat:@"%ld月", _month];
    _yearBtn.title = [NSString stringWithFormat:@"%ld年", _year];
    _monthBtn.title = [NSString stringWithFormat:@"%ld月", _month];

    for (int i = 0; i < KMaxCount; i++) {
        UIButton *btn = (UIButton *)[self.calendarView viewWithTag:i + KBtnTag];
        btn.selected = NO;

        if (i < firstDay - 1 || i > totalDays + firstDay - 2) {
            btn.enabled = NO;
            [btn setTitle:@"" forState:UIControlStateNormal];
        }else {
            if (_year == _currentYear && _month == _currentMonth) {
                if (btn.tag - KBtnTag - (firstDay - 2) == _currentDay) {
                    btn.selected = YES;
                    _day = _currentDay;
                    _weekLabel.text = [NSString stringWithFormat:@"星期%@", _weekArray[(btn.tag - KBtnTag) % 7]];
                    _dayLabel.text = [NSString stringWithFormat:@"%ld", _day];
                }
            }else {
                if (i == firstDay - 1) {
                    btn.selected = YES;
                    _day = btn.tag - KBtnTag - (firstDay - 2);
                    _weekLabel.text = [NSString stringWithFormat:@"星期%@", _weekArray[(btn.tag - KBtnTag) % 7]];
                    _dayLabel.text = [NSString stringWithFormat:@"%ld", _day];
                }
            }
            btn.enabled = YES;
            [btn setTitle:[NSString stringWithFormat:@"%ld", i - (firstDay - 1) + 1] forState:UIControlStateNormal];
        }
    }
}

//获取当前时间
- (void)getCurrentDate
{
    NSDateComponents *components = [[NSCalendar currentCalendar] components:NSCalendarUnitYear | NSCalendarUnitMonth | NSCalendarUnitDay | NSCalendarUnitHour | NSCalendarUnitMinute fromDate:[NSDate date]];
    _year = [components year];
    _month = [components month];
    _day = [components day];
    _hour = [components hour];
    _minute = [components minute];
}

//根据选中日期，获取相应NSDate
- (NSDate *)getSelectDate
{
    //初始化NSDateComponents，设置为选中日期
    NSDateComponents *dateComponents = [[NSDateComponents alloc] init];
    dateComponents.year = _year;
    dateComponents.month = _month;

    return [[[NSCalendar alloc] initWithCalendarIdentifier:NSCalendarIdentifierGregorian] dateFromComponents:dateComponents];
}

//获取目标月份的天数
- (NSInteger)numberOfDaysInMonth
{
    //获取选中日期月份的天数
    return [[NSCalendar currentCalendar] rangeOfUnit:NSCalendarUnitDay inUnit:NSCalendarUnitMonth forDate:[self getSelectDate]].length;
}

//获取目标月份第一天星期几
- (NSInteger)firstDayOfWeekInMonth
{
    //获取选中日期月份第一天星期几，因为默认日历顺序为“日一二三四五六”，所以这里返回的1对应星期日，2对应星期一，依次类推
    return [[NSCalendar currentCalendar] ordinalityOfUnit:NSCalendarUnitDay inUnit:NSCalendarUnitWeekOfYear forDate:[self getSelectDate]];
}

//根据颜色返回图片
- (UIImage *)imageWithColor:(UIColor *)color
{
    CGRect rect = CGRectMake(0.0f, 0.0f, 1.0f, 1.0f);
    UIGraphicsBeginImageContext(rect.size);
    CGContextRef context = UIGraphicsGetCurrentContext();
    CGContextSetFillColorWithColor(context, [color CGColor]);
    CGContextFillRect(context, rect);

    UIImage *image = UIGraphicsGetImageFromCurrentImageContext();
    UIGraphicsEndImageContext();

    return image;
}

//选中日期时调用
- (void)dateBtnOnClick:(UIButton *)btn
{
    _day = btn.tag - KBtnTag - ([self firstDayOfWeekInMonth] - 2);
    _weekLabel.text = [NSString stringWithFormat:@"星期%@", _weekArray[(btn.tag - KBtnTag) % 7]];
    _dayLabel.text = [NSString stringWithFormat:@"%ld", _day];

    if (btn.selected) return;

    for (int i = 0; i < KMaxCount; i++) {
        UIButton *button = [self.calendarView viewWithTag:i + KBtnTag];
        button.selected = NO;
    }

    btn.selected = YES;
}

#pragma mark - UIPickerViewDelegate
- (NSInteger)numberOfComponentsInPickerView:(UIPickerView *)pickerView
{
    return _timeArray.count;
}

- (NSInteger)pickerView:(UIPickerView *)pickerView numberOfRowsInComponent:(NSInteger)component
{
    return [(NSArray *)_timeArray[component] count];
}

- (NSString *)pickerView:(UIPickerView *)pickerView titleForRow:(NSInteger)row forComponent:(NSInteger)component
{
    return _timeArray[component][row];
}

- (void)pickerView:(UIPickerView *)pickerView didSelectRow:(NSInteger)row inComponent:(NSInteger)component
{
    NSString *time = _timeArray[component][row];
    if (component == 0) {
        _hour = [time integerValue];
    } else if (component == 1) {
        _minute = [time integerValue];
    }
}

- (UIView *)pickerView:(UIPickerView *)pickerView viewForRow:(NSInteger)row forComponent:(NSInteger)component reusingView:(UIView *)view
{
    UILabel *pickerLabel = (UILabel *)view;
    if (!pickerLabel) {
        pickerLabel = [[UILabel alloc] init];
        pickerLabel.textColor = [UIColor whiteColor];
        pickerLabel.textAlignment = NSTextAlignmentCenter;
        pickerLabel.font = [UIFont systemFontOfSize:20.0f];
    }
    pickerLabel.text = [self pickerView:pickerView titleForRow:row forComponent:component];

    return pickerLabel;
}

#pragma mark - HWOptionButtonDelegate
- (void)didSelectOptionInHWOptionButton:(HWOptionButton *)optionButton
{
    if (optionButton.title.length > 2) {
        _year = [optionButton.title integerValue];
        _yearBtn.title = [NSString stringWithFormat:@"%ld年", _year];
    }else {
        _month = [optionButton.title integerValue];
        _monthBtn.title = [NSString stringWithFormat:@"%ld月", _month];
    }

    [self reloadData];
}

//确认按钮点击事件
- (void)sureBtnOnClick
{
    [self dismiss];

    NSString *date;
    if (_showTimePicker) {
        date = [NSString stringWithFormat:@"%ld-%02ld-%02ld %02ld:%02ld", _year, _month, _day, _hour, _minute];
    }else {
        date = [NSString stringWithFormat:@"%ld-%02ld-%02ld", _year, _month, _day];
    }

    if (_delegate && [_delegate respondsToSelector:@selector(calendar:didClickSureButtonWithDate:)]) {
        [_delegate calendar:self didClickSureButtonWithDate:date];
    }
}

//取消按钮点击事件
- (void)cancelBtnOnClick
{
    [self dismiss];

    if (_delegate) {
        [_delegate didClickCancelButton];
    }
}

//弹入视图
- (void)show
{
    [UIView animateWithDuration:0.3f animations:^{
        self.frame = CGRectMake(7, 140, self.bounds.size.width, self.bounds.size.height);
    }];
}

//弹出视图
- (void)dismiss
{
    [UIView animateWithDuration:0.3f animations:^{
        self.frame = CGRectMake(7, [UIScreen mainScreen].bounds.size.height, self.bounds.size.width, self.bounds.size.height);
    }];
}

@end
