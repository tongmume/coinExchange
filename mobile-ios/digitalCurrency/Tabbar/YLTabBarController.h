//
//  YLTabBarController.h
//  BaseProject
//
//  Created by YLCai on 16/11/23.
//  Copyright © 2016年 YLCai. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface YLTabBarController : UITabBarController

+(YLTabBarController *)defaultTabBarContrller;
//重置tabar标题
-(void)resettabarItemsTitle;
@end
