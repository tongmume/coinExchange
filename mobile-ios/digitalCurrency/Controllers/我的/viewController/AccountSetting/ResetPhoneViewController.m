//
//  ResetPhoneViewController.m
//  digitalCurrency
//
//  Created by iDog on 2019/3/16.
//  Copyright © 2019年 BIZZAN. All rights reserved.
//

#import "ResetPhoneViewController.h"
#import "ChangePhoneViewController.h"

@interface ResetPhoneViewController ()
@property (weak, nonatomic) IBOutlet UILabel *phoneNumLabel;
@property (weak, nonatomic) IBOutlet UIButton *certainButton;
@property (weak, nonatomic) IBOutlet NSLayoutConstraint *bottomViewHeight;

//国际化需要
@property (weak, nonatomic) IBOutlet UILabel *phoneTitle;

@end

@implementation ResetPhoneViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    self.view.backgroundColor = mainColor;
    self.title = [[ChangeLanguage bundle] localizedStringForKey:@"changePhoneNum" value:nil table:@"English"];
    [self.certainButton setTitle:[[ChangeLanguage bundle] localizedStringForKey:@"changePhoneNum" value:nil table:@"English"] forState:UIControlStateNormal];
    self.phoneTitle.font = self.phoneNumLabel.font = [UIFont systemFontOfSize:15 * kWindowWHOne];
    self.phoneTitle.text = [[ChangeLanguage bundle] localizedStringForKey:@"phoneNum" value:nil table:@"English"];
    self.bottomViewHeight.constant = SafeAreaBottomHeight;

    NSString *beforeStr = [self.phoneNum substringToIndex:3];
    NSString *backStr = [self.phoneNum substringFromIndex:self.phoneNum.length- 4 ];
    self.phoneNumLabel.text = [NSString stringWithFormat:@"%@****%@",beforeStr,backStr];;
    // Do any additional setup after loading the view from its nib.
}
//MARK:--修改手机号的点击事件
- (IBAction)certainBtnClick:(UIButton *)sender {

    ChangePhoneViewController *changeVC = [[ChangePhoneViewController alloc] init];
    changeVC.hidesBottomBarWhenPushed = YES;
    changeVC.phoneNum = self.phoneNum;
    [self.navigationController pushViewController:changeVC animated:YES];
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

@end
