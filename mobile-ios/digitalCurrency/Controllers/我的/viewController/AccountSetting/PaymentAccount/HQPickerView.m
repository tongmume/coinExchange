//
//  HQPickerView.m
//  HQPickerView
//
//  Created by admin on 2019/8/29.
//  Copyright © 2019年 judian. All rights reserved.
//

#import "HQPickerView.h"

#import <Masonry.h>
/** 屏幕宽高 */
#define kScreenBounds [UIScreen mainScreen].bounds
#define KScreenWidth [[UIScreen mainScreen]bounds].size.width
#define KScreenHeight [[UIScreen mainScreen]bounds].size.height

//RGB
#define RGBA(r, g, b, a) [UIColor colorWithRed:(r)/255.0f green:(g)/255.0f blue:(b)/255.0f alpha:(a)]

@interface HQPickerView ()<UIPickerViewDelegate, UIPickerViewDataSource>

@property (nonatomic, strong) UIPickerView *pickerView;
@property (nonatomic, strong) UIView *bgView;
@property (nonatomic, strong) UIButton *cancelBtn;
@property (nonatomic, strong) UIButton *completionBtn;
@property (nonatomic, strong) UIView* line;
@property (nonatomic, strong) NSMutableArray *array;
@property (nonatomic, copy) NSString *selectedStr;

@end

@implementation HQPickerView

- (instancetype)initWithFrame:(CGRect)frame {
    if (self == [super initWithFrame:frame]) {
        self.frame = CGRectMake(0, 0, KScreenWidth, KScreenHeight - NEW_NavHeight);
        self.backgroundColor = self.backgroundColor = RGBA(51, 51, 51, 0.3);
        self.bgView = [[UIView alloc] initWithFrame:CGRectMake(5, KScreenHeight, KScreenWidth - 10, 250)];
        self.bgView.layer.cornerRadius = 10;
        self.bgView.layer.masksToBounds = YES;
        self.bgView.backgroundColor = mainColor;
        [self addSubview:self.bgView];

        //显示动画
        [self showAnimation];

        self.cancelBtn = [UIButton buttonWithType:UIButtonTypeCustom];
        [self.bgView addSubview:self.cancelBtn];
        [self.cancelBtn mas_makeConstraints:^(MASConstraintMaker *make) {
            make.top.mas_equalTo(0);
            make.left.mas_equalTo(15);
            make.width.mas_equalTo(40);
            make.height.mas_equalTo(44);
        }];
        self.cancelBtn.titleLabel.font = [UIFont systemFontOfSize:14];
        [self.cancelBtn setTitle:@"取消" forState:UIControlStateNormal];
        [self.cancelBtn setTitleColor:[UIColor colorWithRed:123.999/255.0 green:123.999/255.0 blue:123.999/255.0 alpha:1] forState:UIControlStateNormal];
        [self.cancelBtn addTarget:self action:@selector(cancelBtnClick) forControlEvents:UIControlEventTouchUpInside];

        self.completionBtn = [UIButton buttonWithType:UIButtonTypeCustom];
        [self.bgView addSubview:self.completionBtn];
        [self.completionBtn mas_makeConstraints:^(MASConstraintMaker *make) {
            make.top.mas_equalTo(0);
            make.right.mas_equalTo(-15);
            make.width.mas_equalTo(40);
            make.height.mas_equalTo(44);
        }];
        self.completionBtn.titleLabel.font = [UIFont systemFontOfSize:14];
        [self.completionBtn setTitle:@"确定" forState:UIControlStateNormal];
        [self.completionBtn setTitleColor:[UIColor colorWithRed:239.996/255.0 green:167/255.0 blue:10.0011/255.0 alpha:1] forState:UIControlStateNormal];
        [self.completionBtn addTarget:self action:@selector(completionBtnClick) forControlEvents:UIControlEventTouchUpInside];
        //线
        UIView *line = [UIView new];
        [self.bgView addSubview:line];
        [line mas_makeConstraints:^(MASConstraintMaker *make) {

            make.top.mas_equalTo(self.cancelBtn.mas_bottom).offset(0);
            make.left.mas_equalTo(0);
            make.width.mas_equalTo(KScreenWidth);
            make.height.mas_equalTo(0.5);

        }];
        line.backgroundColor = RGBA(18, 22, 28, 1);
        self.line = line ;
    }
    return self;
}


#pragma mark-----UIPickerViewDataSource
- (NSInteger)numberOfComponentsInPickerView:(UIPickerView *)pickerView {
    return 1;
}

- (NSInteger)pickerView:(UIPickerView *)pickerView numberOfRowsInComponent:(NSInteger)component {
    return self.customArr.count;
}

- (NSString *)pickerView:(UIPickerView *)pickerView titleForRow:(NSInteger)row forComponent:(NSInteger)component {
    return self.customArr[row];
}

- (void)pickerView:(UIPickerView *)pickerView didSelectRow:(NSInteger)row inComponent:(NSInteger)component {
    self.selectedStr = self.customArr[row];
}

- (CGFloat)pickerView:(UIPickerView *)pickerView rowHeightForComponent:(NSInteger)component{
    return 40;
}

- (UIView *)pickerView:(UIPickerView *)pickerView viewForRow:(NSInteger)row forComponent:(NSInteger)component reusingView:(UIView *)view
{
//    //设置分割线的颜色
//    for(UIView *singleLine in pickerView.subviews)
//    {
//        if (singleLine.frame.size.height < 2)
//        {
//            singleLine.backgroundColor = [UIColor colorWithRed:0.75f green:0.75f blue:0.75f alpha:1.00f];
//        }
//    }
    //设置文字的属性（改变picker中字体的颜色大小）
    UILabel *reasonLabel = [UILabel new];
    reasonLabel.textAlignment = NSTextAlignmentCenter;
    reasonLabel.text = self.customArr[row];
    reasonLabel.font = [UIFont systemFontOfSize:14];
    reasonLabel.textColor = RGBOF(0xe6e6e6);
    //改变选中行颜色（设置一个全局变量，在选择结束后获取到当前显示行，记录下来，刷新picker）

    return reasonLabel;
}


- (void)setCustomArr:(NSArray *)customArr {
    _customArr = customArr;
    self.pickerView = [UIPickerView new];
    [self.bgView addSubview:self.pickerView];
    [self.pickerView mas_makeConstraints:^(MASConstraintMaker *make) {
        make.bottom.mas_equalTo(0);
        make.top.mas_equalTo(self.line);
        make.left.right.mas_equalTo(0);
    }];
    self.pickerView.delegate = self;
    self.pickerView.dataSource = self;
    [self.array addObject:customArr];
}


#pragma mark-----取消
- (void)cancelBtnClick{
    [self hideAnimation];
}

#pragma mark-----取消
- (void)completionBtnClick{
    NSString *str = [self.customArr objectAtIndex:[self.pickerView selectedRowInComponent:0]];
    if (_delegate && [_delegate respondsToSelector:@selector(pickerView:didSelectText:)]) {
        [self.delegate pickerView:self.pickerView didSelectText:str];
    }
    [self hideAnimation];
}

#pragma mark-----隐藏的动画
- (void)hideAnimation{
    [UIView animateWithDuration:0.3 animations:^{
        CGRect frame = self.bgView.frame;
        frame.origin.y = KScreenHeight;
        self.bgView.frame = frame;
    } completion:^(BOOL finished) {

        [self.bgView removeFromSuperview];
        [self removeFromSuperview];
    }];
}

#pragma mark-----显示的动画
- (void)showAnimation{

    [UIView animateWithDuration:0.3 animations:^{
        CGRect frame = self.bgView.frame;
        frame.origin.y = KScreenHeight - NEW_NavHeight - 250 - 5;
        self.bgView.frame = frame;
    }];
}

@end
