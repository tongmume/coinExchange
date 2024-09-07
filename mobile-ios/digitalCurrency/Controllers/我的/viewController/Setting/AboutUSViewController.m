//
//  AboutUSViewController.m
//  digitalCurrency
//
//  Created by iDog on 2019/2/2.
//  Copyright © 2019年 BIZZAN. All rights reserved.
//

#import "AboutUSViewController.h"
#import "MineNetManager.h"
#import "AboutUSModel.h"
#import "AboutUsTableViewCell.h"
@interface AboutUSViewController ()

@end

@implementation AboutUSViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    UIImage *image = [UIImage imageNamed:@"形状 19 拷贝 2"];
    self.view.layer.contents = (id)image.CGImage;
    self.title = [[ChangeLanguage bundle] localizedStringForKey:@"Companyprofile" value:nil table:@"English"];

}


@end
