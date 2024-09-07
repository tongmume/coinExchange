//
//  BaseViewController.h
//  digitalCurrency
//
//  Created by sunliang on 2019/1/26.
//  Copyright © 2019年 BIZZAN. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface BaseViewController : UIViewController

-(void)addUIAlertControlWithString:(NSString *)string withActionBlock:(void(^)(void))actionBlock andCancel:(void(^)(void))cancelBlock;

-(void)addAlterViewWithTitle:(NSString *)title withMessage:(NSString *)message withCertainBtnTitle:(NSString *)certainBtnTilte withCancelBtnTitle:(NSString *)cancelBtnTitle withActionBlock:(void(^)(void))actionBlock andCancel:(void(^)(void))cancelBlock;
@end
