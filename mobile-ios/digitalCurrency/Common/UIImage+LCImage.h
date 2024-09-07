//
//  UIImage+LCImage.h
//  hongqiang
//
//  Created by 刘翀 on 16/4/8.
//  Copyright © 2016年 xinhuo. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface UIImage (LCImage)
/**
 *  获取不经过渲染的原图
 *
 *  @param imageName 本地图片名称
 *
 *  @return 返回原图片
 */
+(UIImage *)imageWithRenderOriginal:(NSString *)imageName;
@end
