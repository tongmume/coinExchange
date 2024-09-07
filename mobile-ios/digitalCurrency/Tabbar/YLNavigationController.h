//
//  YLNavigationController.h
//  BaseProject
//
//  Created by YLCai on 16/11/23.
//  Copyright © 2016年 YLCai. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface YLNavigationController : UINavigationController
//- (void)navigationController:(UINavigationController *)navigationController didShowViewController:(UIViewController *)viewController animated:(BOOL)animated;
@property(strong,nonatomic)UIScreenEdgePanGestureRecognizer *panGestureRec;

@end
