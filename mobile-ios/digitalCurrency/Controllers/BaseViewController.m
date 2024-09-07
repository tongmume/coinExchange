//
//  BaseViewController.m
//  digitalCurrency
//
//  Created by sunliang on 2019/1/26.
//  Copyright © 2019年 BIZZAN. All rights reserved.
//

#import "BaseViewController.h"
 #import "RDVTabBarController.h"

@interface BaseViewController ()

@end

@implementation BaseViewController

- (void)viewDidLoad {
    [super viewDidLoad];
//    [self backBtnNoNavBar:NO normalBack:YES];
    self.view.backgroundColor = [UIColor clearColor];
}

-(void)addUIAlertControlWithString:(NSString *)string withActionBlock:(void(^)(void))actionBlock andCancel:(void(^)(void))cancelBlock{
    UIAlertController *alert = [UIAlertController alertControllerWithTitle:[[ChangeLanguage bundle] localizedStringForKey:@"warmPrompt" value:nil table:@"English"] message:string preferredStyle:UIAlertControllerStyleAlert];
    [alert addAction:[UIAlertAction actionWithTitle:[[ChangeLanguage bundle] localizedStringForKey:@"ok" value:nil table:@"English"] style:UIAlertActionStyleDestructive handler:^(UIAlertAction * _Nonnull action) {
        actionBlock();
    }]];
    [alert addAction:[UIAlertAction actionWithTitle:[[ChangeLanguage bundle] localizedStringForKey:@"cancel" value:nil table:@"English"] style:UIAlertActionStyleCancel handler:^(UIAlertAction * _Nonnull action) {
        cancelBlock();
    }]];
    dispatch_async(dispatch_get_main_queue(), ^{
        [self presentViewController: alert animated: YES completion: nil];
    });
}
-(void)addAlterViewWithTitle:(NSString *)title withMessage:(NSString *)message withCertainBtnTitle:(NSString *)certainBtnTilte withCancelBtnTitle:(NSString *)cancelBtnTitle withActionBlock:(void(^)(void))actionBlock andCancel:(void(^)(void))cancelBlock{

    UIAlertController* alert = [UIAlertController alertControllerWithTitle:title
                                                                   message:message
                                                            preferredStyle:UIAlertControllerStyleAlert];

    UIAlertAction* defaultAction = [UIAlertAction actionWithTitle:certainBtnTilte style:UIAlertActionStyleDefault
                                                          handler:^(UIAlertAction * action) {
                                                              //响应事件
                                                               actionBlock();
                                                          }];
    UIAlertAction* cancelAction = [UIAlertAction actionWithTitle:cancelBtnTitle style:UIAlertActionStyleDefault
                                                         handler:^(UIAlertAction * action) {
                                                             //响应事件
                                                             cancelBlock();
                                                         }];

    [alert addAction:defaultAction];
    [alert addAction:cancelAction];
    [self presentViewController:alert animated:YES completion:nil];
}
- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

/*
#pragma mark - Navigation

// In a storyboard-based application, you will often want to do a little preparation before navigation
- (void)prepareForSegue:(UIStoryboardSegue *)segue sender:(id)sender {
    // Get the new view controller using [segue destinationViewController].
    // Pass the selected object to the new view controller.
}
*/

- (void)viewWillAppear:(BOOL)animated {
    [super viewWillAppear:animated];
    [[self rdv_tabBarController] setTabBarHidden:YES animated:YES];
}

- (void)viewWillDisappear:(BOOL)animated {
    [super viewWillDisappear:animated];
    [[self rdv_tabBarController] setTabBarHidden:NO animated:YES];
}
@end
