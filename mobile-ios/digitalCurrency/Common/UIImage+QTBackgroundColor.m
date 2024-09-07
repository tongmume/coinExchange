//
//  UIImage+QTBackgroundColor.m
//  ReadingClub
//
//  Created by qtkj on 16/8/22.
//  Copyright © 2016年 qtkj. All rights reserved.
//

#import "UIImage+QTBackgroundColor.h"

@implementation UIImage (QTBackgroundColor)

+ (UIImage *)imageWithColor:(UIColor *)color
                       Size:(CGSize)size{

    //获得上下文
    UIGraphicsBeginImageContextWithOptions(CGSizeMake(size.width, size.height), NO, 0.0);
    //画一个color颜色的矩形
    [color set];
    UIRectFill(CGRectMake(0, 0, size.width, size.height));
    
    UIImage *image = UIGraphicsGetImageFromCurrentImageContext();
    
    UIGraphicsEndImageContext();
    
    return image;
}

@end
