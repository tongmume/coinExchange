//
//  ChangePhoneViewController.m
//  digitalCurrency
//
//  Created by iDog on 2019/3/21.
//  Copyright © 2019年 BIZZAN. All rights reserved.
//

#import "ChangePhoneViewController.h"
#import "ChangePhoneDetailViewController.h"

@interface ChangePhoneViewController ()
//国际化需要
@property (weak, nonatomic) IBOutlet UILabel *receivePhoneCode;
@property (weak, nonatomic) IBOutlet UILabel *tips;
@property (weak, nonatomic) IBOutlet UILabel *unReceivePhoneCode;

@end

@implementation ChangePhoneViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    self.view.backgroundColor = kRGBColor(18, 22, 28);
    self.title = [[ChangeLanguage bundle] localizedStringForKey:@"changeBindPhoneNum" value:nil table:@"English"];
    self.receivePhoneCode.text = [[ChangeLanguage bundle] localizedStringForKey:@"receivePhoneCode" value:nil table:@"English"];
    self.tips.text = [[ChangeLanguage bundle] localizedStringForKey:@"changeBindPhoneNumTip" value:nil table:@"English"];
    self.unReceivePhoneCode.text = [[ChangeLanguage bundle] localizedStringForKey:@"unReceivePhoneCode" value:nil table:@"English"];
    self.receivePhoneCode.font = self.unReceivePhoneCode.font = [UIFont systemFontOfSize:15 * kWindowWHOne];

    // Do any additional setup after loading the view from its nib.
}
//MARK:--按钮的点击事件
- (IBAction)btnClick:(UIButton *)sender {
    if (sender.tag == 1) {
        //可以接收验证码
        ChangePhoneDetailViewController *changeVC = [[ChangePhoneDetailViewController alloc] init];
        changeVC.hidesBottomBarWhenPushed = YES;
        changeVC.phoneNum = self.phoneNum;
        [self.navigationController pushViewController:changeVC animated:YES];
    }else if (sender.tag == 2){
        //不可以接收验证码
        [self.view makeToast:[[ChangeLanguage bundle] localizedStringForKey:@"unReceivePhoneCodeTip" value:nil table:@"English"] duration:1.5 position:CSToastPositionCenter];
        return;
    }
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
