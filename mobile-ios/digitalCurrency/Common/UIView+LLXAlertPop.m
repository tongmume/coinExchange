//
//  UIView+LLXAlertPop.m
//  LLXAlertPop
//
//  Created by 李林轩 on 2019/3/5.
//  Copyright © 2019年 李林轩. All rights reserved.
//

#import "UIView+LLXAlertPop.h"
#import <objc/runtime.h>
//#import "pop/POP.h"

#define APPSIZE [[UIScreen mainScreen] bounds].size
#define RGBA(r,g,b,a)                     [UIColor colorWithRed:r/255.0f green:g/255.0f blue:b/255.0f alpha:a]

@implementation UIView (LLXAlertPop)
UIView *backgroundV;
UIView *bottomView;
int _type;
float height;

static NSString *keyOfMethod; //关联者的索引key-用于获取block




/** 只弹出文字列表
 *  array ：弹出的选项标题
 *  textColor ：选项标题的字体颜色 可设置两种类型，数组颜色或者单个颜色（NSArray/UIColor）
 *  font ：选项标题的字体
 *  取消 按钮字体请到.m文件自行设置。默认黑色-16号
 **/
-(void)createAlertViewTitleArray:(NSArray* _Nullable )array textColor:(id _Nullable )color font:(UIFont*_Nullable)font type:(int)type actionBlock:(LLXAlertBlock _Nullable )actionBlock{

    [self createAlertViewTitleArray:array arrayImage:nil textColor:color font:font type:type spacing:0  actionBlock:actionBlock];

}



/**
 *  array ：弹出的选项标题
 *  arrayImage ：数组图标，没有写nil
 *  textColor ：选项标题的字体颜色 可设置两种类型，数组颜色或者单个颜色（NSArray/UIColor）
 *  font ：选项标题的字体
 *  spacing ：文字与图片间距自行调试（无图片可填0）
 *  取消 按钮字体请到.m文件自行设置。默认黑色-16号
 **/
-(void)createAlertViewTitleArray:(NSArray* _Nullable )array arrayImage:(NSArray* _Nullable )arrayImage textColor:(id _Nullable )color font:(UIFont*_Nullable)font type:(int)type spacing:(CGFloat)spacing actionBlock:(LLXAlertBlock _Nullable )actionBlock{
    _type=type;
    UIWindow *window = [UIApplication sharedApplication].keyWindow;


    backgroundV = [[UIView alloc]initWithFrame:window.bounds];
    backgroundV.backgroundColor = RGBA(0, 0, 0, 0);

    [window addSubview:backgroundV];

    //点击手势
    UITapGestureRecognizer *touchDown = [[UITapGestureRecognizer alloc]initWithTarget:self action:@selector(cancelDismiss)];
    touchDown.numberOfTapsRequired = 1;
    [backgroundV addGestureRecognizer:touchDown];

    height = array.count*50+array.count*0.5+57.5;

    bottomView = [[UIView alloc]initWithFrame:CGRectMake(0, APPSIZE.height, APPSIZE.width, height+50)];
    bottomView.backgroundColor = mainColor;
    [window addSubview:bottomView];

    NSArray *arrayColor;
    UIColor *colors;

    for (int i=0; i<array.count; i++) {

        UIButton *btn = [[UIButton alloc]initWithFrame:CGRectMake(0, i*50+i*0.5, APPSIZE.width, 50)];

        [btn setTitle:array[i] forState:UIControlStateNormal];


        if (arrayImage) {
            if (i>=arrayImage.count) {
                NSLog(@"数组越界-数组图片数量有误，请仔细检查");
                return;
            }
            [btn setImage:[UIImage imageNamed:arrayImage[i]] forState:UIControlStateNormal];
            btn.imageView.contentMode = UIViewContentModeScaleAspectFit;
            btn.imageEdgeInsets = UIEdgeInsetsMake(12, - 0.5 * spacing, 12, 0.5 * spacing);
            btn.titleEdgeInsets = UIEdgeInsetsMake(0, 0.5 * spacing, 0, - 0.5 * spacing);
        }


        btn.tag = 10000+i;
        btn.titleLabel.font = font;
        [bottomView addSubview:btn];
        [btn addTarget:self action:@selector(didTitleBtn:) forControlEvents:UIControlEventTouchUpInside];
        [btn addTarget:self action:@selector(didTitleBtnTouchDragInside:) forControlEvents:UIControlEventTouchDragInside];
        [btn addTarget:self action:@selector(didTitleBtnTouchDragOutsid:) forControlEvents:UIControlEventTouchDragOutside];


        //关联 block
        objc_setAssociatedObject (btn , &keyOfMethod, actionBlock, OBJC_ASSOCIATION_COPY_NONATOMIC);
        if (i!=array.count-1) {
            UIView *line = [[UIView alloc]initWithFrame:CGRectMake(0, CGRectGetMaxY(btn.frame), APPSIZE.width, 0.5)];
            line.backgroundColor = RGBA(18, 22, 28, 1);
            [bottomView addSubview:line];
        }
        //如果是数组颜色
        if ([color isKindOfClass:[NSArray class]]) {
            arrayColor = [NSArray arrayWithArray:color];
            if (i>=arrayColor.count) {
                NSLog(@"数组越界-数组图片颜色数量有误，请仔细检查");
                return;
            }
            colors = arrayColor[i];
        }else{
            colors = (UIColor*)color;
        }
         [btn setTitleColor:RGBOF(0x999999) forState:UIControlStateNormal];

        [btn setBackgroundColor:mainColor];

    }

    UIView *line = [[UIView alloc]initWithFrame:CGRectMake(0, array.count*50.5-0.5, APPSIZE.width, 8)];
    line.backgroundColor = mainColor;
    [bottomView addSubview:line];
    UIButton *btn = [[UIButton alloc]initWithFrame:CGRectMake(0, CGRectGetMaxY(line.frame), APPSIZE.width, 50)];
    btn.tag = 6;
    [btn setTitle:[[ChangeLanguage bundle] localizedStringForKey:@"cancel" value:nil table:@"English"] forState:UIControlStateNormal];
    [btn setTitleColor:RGBOF(0xe6e6e6) forState:UIControlStateNormal];
    btn.titleLabel.font = [UIFont systemFontOfSize:16];
    [bottomView addSubview:btn];
    [btn addTarget:self action:@selector(cancelDismiss) forControlEvents:UIControlEventTouchUpInside];
    [btn setBackgroundColor:mainColor];
    objc_setAssociatedObject (btn , &keyOfMethod, actionBlock, OBJC_ASSOCIATION_COPY_NONATOMIC);

    /**
     如果您项目中没有pop动画库，或者您不想引入pop库，请注释掉pop动画和头文件， 并且打开下面的注释

    */
   // 无动画
    [UIView animateWithDuration:0.3 animations:^{
        bottomView.frame = CGRectMake(0, APPSIZE.height-height, APPSIZE.width, height);
    }];

//    // pop动画
//    POPSpringAnimation *anSpring = [POPSpringAnimation animationWithPropertyNamed:kPOPLayerPositionY];
//    anSpring.toValue = @(bottomView.center.y-height);
//    anSpring.springBounciness = 7.0f;
////    anSpring.springSpeed = 10.0f;
//    [bottomView pop_addAnimation:anSpring forKey:@"position"];
//


    [UIView animateWithDuration:0.3 animations:^{
        backgroundV.backgroundColor = RGBA(0, 0, 0, 0.3);
    } completion:^(BOOL finished) {

    }];

}

-(void)didTitleBtnTouchDragOutsid:(UIButton*)btn{

    btn.backgroundColor = [UIColor clearColor];
}
-(void)didTitleBtnTouchDragInside:(UIButton*)btn{
    btn.backgroundColor = RGBA(245, 245, 245, 1);
}
//点击事件
-(void)didTitleBtn:(UIButton*)btn{

    btn.backgroundColor = [UIColor clearColor];
    //获取关联
    LLXAlertBlock block1 = (LLXAlertBlock)objc_getAssociatedObject(btn, &keyOfMethod);
    if(block1){
        block1(btn,btn.tag-10000);
        [self didMiss];

    }


}
//页面消失
-(void)didMiss{

    [UIView animateWithDuration:0.3 animations:^{
        backgroundV.backgroundColor = RGBA(0, 0, 0, 0);
        bottomView.frame = CGRectMake(0, APPSIZE.height, APPSIZE.width, height+50);
    } completion:^(BOOL finished) {
        [backgroundV removeFromSuperview];
        [bottomView removeFromSuperview];
    }];

}
//点击取消按钮或者背景
-(void)cancelDismiss{
    if (_type==1) {
        UIButton *button = (UIButton *) [bottomView viewWithTag:6];
        LLXAlertBlock block1 = (LLXAlertBlock)objc_getAssociatedObject(button, &keyOfMethod);
        if(block1){
            block1(button,button.tag);
        }
    }

    [UIView animateWithDuration:0.3 animations:^{
        backgroundV.backgroundColor = RGBA(0, 0, 0, 0);
        bottomView.frame = CGRectMake(0, APPSIZE.height, APPSIZE.width, height+50);
    } completion:^(BOOL finished) {
        [backgroundV removeFromSuperview];
        [bottomView removeFromSuperview];
    }];

}




@end
