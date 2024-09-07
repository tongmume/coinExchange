//
//  AdvertisingBGView.m
//  digitalCurrency
//
//  Created by iDog on 2019/1/31.
//  Copyright © 2019年 BIZZAN. All rights reserved.
//

#import "AdvertisingBGView.h"

@implementation AdvertisingBGView
- (NSMutableArray *)itemButtons
{
    if (_itemButtons == nil) {
        _itemButtons = [NSMutableArray array];
    }
    return _itemButtons;
}

-(NSArray *)ary{

    if (_ary==nil) {

        _ary = [NSArray array];

        _ary = @[@"buyAdvertisingImage",@"sellAdvertisingImage"];
    }

    return _ary;
}
-(void)awakeFromNib{
    [super awakeFromNib];
    self.buyButtonLeftWidth.constant = (kWindowW-40-240)/2;
    self.bottomViewHeight.constant = SafeAreaBottomHeight;
    self.backgroundColor=[[UIColor blackColor] colorWithAlphaComponent:0.4];
    self.buyButton.backgroundColor = kRGBColor(7, 192, 135);
    self.sellButton.backgroundColor = kRGBColor(250, 70, 68);
    [self insertCloseImg];
}


- (void)setMenu{
    self.buyButton.transform = CGAffineTransformMakeTranslation(0, self.bounds.size.height);
    self.sellButton.transform = CGAffineTransformMakeTranslation(0, self.bounds.size.height);
    self.itemButtons=[NSMutableArray arrayWithObjects:self.buyButton,self.sellButton, nil];
    //定时器控制每个按钮弹出的时间
    self.timer = [NSTimer scheduledTimerWithTimeInterval:0.1 target:self selector:@selector(popupBtn) userInfo:nil repeats:YES];

    [UIView animateWithDuration:0.6 animations:^{

        _closeImgView.transform = CGAffineTransformRotate(_closeImgView.transform, M_PI);
    }];
}

- (void)popupBtn{

    if (_upIndex == self.itemButtons.count) {

        [self.timer invalidate];
        self.timer=nil;
        _upIndex = 0;

        return;
    }

    UIButton *btn = self.itemButtons[_upIndex];

    [self setUpOneBtnAnim:btn];

    _upIndex++;
}
//设置按钮从第一个开始向上滑动显示
- (void)setUpOneBtnAnim:(UIButton *)btn
{

    [UIView animateWithDuration:0.8 delay:0 usingSpringWithDamping:0.7 initialSpringVelocity:0 options:UIViewAnimationOptionCurveEaseIn animations:^{
        btn.transform = CGAffineTransformIdentity;
    } completion:^(BOOL finished){

        //获取当前显示的菜单控件的索引
        _downIndex = (int)self.itemButtons.count - 1;
    }];

}


-(void)dismissMenu{

   self.timer = [NSTimer scheduledTimerWithTimeInterval:0.1 target:self selector:@selector(returnUpVC) userInfo:nil repeats:YES];
    [UIView animateWithDuration:0.3 animations:^{
        //-M_PI_2*1.5
        _closeImgView.transform = CGAffineTransformRotate(_closeImgView.transform, -M_PI_2);
    }];
}
//设置按钮从后往前下落
- (void)returnUpVC{

    if (_downIndex == -1) {

        [self.timer invalidate];
        self.timer=nil;
        [self removeFromSuperview];

        return;
    }

    UIButton *btn = self.itemButtons[_downIndex];

    [self setDownOneBtnAnim:btn];

    _downIndex--;

}

//按钮下滑
- (void)setDownOneBtnAnim:(UIButton *)btn
{

    [UIView animateWithDuration:0.6 animations:^{

        btn.transform = CGAffineTransformMakeTranslation(0, self.bounds.size.height);

    } completion:^(BOOL finished) {



    }];
}
//关闭图片
- (void)insertCloseImg{

    UIImage *img = [UIImage imageNamed:@"popPlus"];
    UIImageView *imgView = [[UIImageView alloc]initWithImage:img];
    imgView.frame = CGRectMake((kWindowW-25)/2.0, 12.5, 25, 25);
    [self.cancelButton addSubview:imgView];
    [self bringSubviewToFront:imgView];
    _closeImgView = imgView;

}
/*
// Only override drawRect: if you perform custom drawing.
// An empty implementation adversely affects performance during animation.
- (void)drawRect:(CGRect)rect {
    // Drawing code
}
*/

@end
