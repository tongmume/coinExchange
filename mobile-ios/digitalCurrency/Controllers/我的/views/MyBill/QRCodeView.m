//
//  QRCodeView.m
//  digitalCurrency
//
//  Created by iDog on 2019/4/11.
//  Copyright © 2019年 BIZZAN. All rights reserved.
//

#import "QRCodeView.h"

@implementation QRCodeView
-(void)awakeFromNib{
    [super awakeFromNib];
    self.backgroundColor=[[UIColor blackColor] colorWithAlphaComponent:0.4];

    self.QRCodeImage.userInteractionEnabled = YES;
    UILongPressGestureRecognizer *longPress = [[UILongPressGestureRecognizer alloc]initWithTarget:self
                                                                                           action:@selector(longTapAction:)];
    longPress.minimumPressDuration = 0.5;
    [self.QRCodeImage addGestureRecognizer:longPress];
}
//MARK:--长按保存图片
- (void) longTapAction:(UILongPressGestureRecognizer *)longPress {
    if (longPress.state == UIGestureRecognizerStateBegan) {
        [self saveImage:self.QRCodeImage.image];
    }
}
-(void)whenClickImage{

}
//image是要保存的图片
- (void) saveImage:(UIImage *)image{
    if (image) {
        UIImageWriteToSavedPhotosAlbum(image, self, @selector(savedPhotoImage:didFinishSavingWithError:contextInfo:), nil);
    };
}
//保存完成后调用的方法
- (void)savedPhotoImage:(UIImage*)image didFinishSavingWithError: (NSError *)error contextInfo: (void *)contextInfo {
    if (error) {
        [self makeToast:@"保存图片失败" duration:1.5 position:CSToastPositionCenter];
    }
    else {
//        NSLog(@"保存图片成功");
        [self makeToast:@"保存图片成功" duration:1.5 position:CSToastPositionCenter];
    }
}
-(void)touchesBegan:(NSSet<UITouch *> *)touches withEvent:(UIEvent *)event{

    CGPoint point = [[touches anyObject] locationInView:self];
    point = [_bgView.layer convertPoint:point fromLayer:self.layer];
    if (![_bgView.layer containsPoint:point]) {
        [self removeFromSuperview];
    }
}
/*
// Only override drawRect: if you perform custom drawing.
// An empty implementation adversely affects performance during animation.
- (void)drawRect:(CGRect)rect {
    // Drawing code
}
*/

@end
