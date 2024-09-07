//
//  ZLAdvertView.h
//  ZLAdvertDemo
//
//  Created by zhangli on 2019/2/28.
//  Copyright © 2019年 YSMX. All rights reserved.
//

#import <UIKit/UIKit.h>

#define kscreenWidth [UIScreen mainScreen].bounds.size.width
#define kscreenHeight [UIScreen mainScreen].bounds.size.height
#define kUserDefaults [NSUserDefaults standardUserDefaults]
static NSString *const adImageName = @"adImageName";
static NSString *const adUrl = @"adUrl";

@interface ZLAdvertView : UIView

/**
*  显示广告页面方法
*/
- (void)show;

/**
 *  图片路径
 */
@property (nonatomic, copy) NSString *filePath;

@end
