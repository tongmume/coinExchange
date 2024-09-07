//
//  BindBankViewController.m
//  digitalCurrency
//
//  Created by iDog on 2019/5/2.
//  Copyright © 2019年 BIZZAN. All rights reserved.
//

#import "BindBankViewController.h"
#import "MineNetManager.h"
#import "HQPickerView.h"
@interface BindBankViewController ()<HQPickerViewDelegate>

@property (weak, nonatomic) IBOutlet UILabel *realName;
@property (weak, nonatomic) IBOutlet UILabel *bankName;
@property (weak, nonatomic) IBOutlet UITextField *branchName;
@property (weak, nonatomic) IBOutlet UITextField *bankNum;
@property (weak, nonatomic) IBOutlet UITextField *certainBankNum;
@property (weak, nonatomic) IBOutlet UITextField *moneyPassword;
//国际化需要
@property (weak, nonatomic) IBOutlet UILabel *name;
@property (weak, nonatomic) IBOutlet UILabel *bankLabel;
@property (weak, nonatomic) IBOutlet UILabel *branchLabel;
@property (weak, nonatomic) IBOutlet UILabel *bankNumLabel;
@property (weak, nonatomic) IBOutlet UILabel *certainBankNumLabel;
@property (weak, nonatomic) IBOutlet UILabel *moneyPasswordLabel;

@end

@implementation BindBankViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    [self setRightItem];
    self.view.backgroundColor = kRGBColor(18, 22, 28);
    self.realName.text = self.model.realName;
    if ([self.model.bankVerified isEqualToString:@"1"]) {
        //已绑定 ，可修改
        self.title = LocalizationKey(@"modifyBankNum");
        self.bankName.text = self.model.bankInfo.bank;
        self.branchName.text = self.model.bankInfo.branch;
        self.bankNum.text = self.model.bankInfo.cardNo;
    }else{
        self.title = LocalizationKey(@"setBankNum");
    }

    self.name.text = LocalizationKey(@"name");
    self.bankLabel.text = LocalizationKey(@"bankName");
    self.branchLabel.text = LocalizationKey(@"branchName");
    self.bankNumLabel.text = LocalizationKey(@"bankNum");
    self.certainBankNumLabel.text = LocalizationKey(@"certainBankNum");
    self.moneyPasswordLabel.text = LocalizationKey(@"moneyPassword");

    self.branchName.placeholder = LocalizationKey(@"inputBranchInfo");
    self.bankNum.placeholder = LocalizationKey(@"inputBankNum");
    self.certainBankNum.placeholder = LocalizationKey(@"inputCertainBankNum");
     self.moneyPassword.placeholder = LocalizationKey(@"inputMoneyPassword");
    self.moneyPassword.secureTextEntry = YES;

//    [_branchName setValue:RGBOF(0x999999) forKeyPath:@"_placeholderLabel.textColor"];
//    [_bankNum setValue:RGBOF(0x999999) forKeyPath:@"_placeholderLabel.textColor"];
//    [_certainBankNum setValue:RGBOF(0x999999) forKeyPath:@"_placeholderLabel.textColor"];
//    [_moneyPassword setValue:RGBOF(0x999999) forKeyPath:@"_placeholderLabel.textColor"];

}

- (void)setRightItem{
    UIButton *btn = [UIButton buttonWithType:UIButtonTypeCustom];
    btn.frame = CGRectMake(0, 0, 30, 44);
    [btn setTitle:LocalizationKey(@"save") forState:UIControlStateNormal];
    [btn setTitleColor:RGBOF(0xF0A70A) forState:UIControlStateNormal];
    btn.titleLabel.font = [UIFont systemFontOfSize:14];
    [btn addTarget:self action:@selector(RighttouchEvent) forControlEvents:UIControlEventTouchUpInside];

    UIBarButtonItem*item=[[UIBarButtonItem alloc] initWithCustomView:btn];
    self.navigationItem.rightBarButtonItem = item;
}

//MARK:--点击保存按钮点击事件
- (void)RighttouchEvent{
    if ([self.bankName.text isEqualToString:@""]) {
        [self.view makeToast:LocalizationKey(@"inputBankName") duration:1.5 position:CSToastPositionCenter];
        return;
    }
    if ([self.branchName.text isEqualToString:@""]) {
        [self.view makeToast:LocalizationKey(@"inputBranchInfo") duration:1.5 position:CSToastPositionCenter];
        return;
    }
    if ([self.bankNum.text isEqualToString:@""]) {
        [self.view makeToast:LocalizationKey(@"inputBankNum") duration:1.5 position:CSToastPositionCenter];
        return;
    }
    if ([self.certainBankNum.text isEqualToString:@""]) {
        [self.view makeToast:LocalizationKey(@"inputCertainBankNum") duration:1.5 position:CSToastPositionCenter];
        return;
    }
    if (![self.certainBankNum.text isEqualToString:self.bankNum.text]) {
        [self.view makeToast:LocalizationKey(@"judgeBankNumAndCertainBankNum") duration:1.5 position:CSToastPositionCenter];
        return;
    }
    if ([self.moneyPassword.text isEqualToString:@""]) {
        [self.view makeToast:LocalizationKey(@"inputMoneyPassword") duration:1.5 position:CSToastPositionCenter];
        return;
    }
    [EasyShowLodingView showLodingText:LocalizationKey(@"hardUpLoading")];
    NSString *urlStr = @"";
    if ([self.model.bankVerified isEqualToString:@"1"]) {
        //已绑定，可修改
        urlStr = @"uc/approve/update/bank";
    }else{
        urlStr = @"uc/approve/bind/bank";
    }
    [MineNetManager setBankNumForUrlString:urlStr withBank:self.bankName.text withBranch:self.branchName.text withJyPassword:self.moneyPassword.text withRealName:self.realName.text withCardNo:self.bankNum.text CompleteHandle:^(id resPonseObj, int code) {
        [EasyShowLodingView hidenLoding];
        if (code) {
            if ([resPonseObj[@"code"] integerValue]==0) {
                //上传成功
                [self.view makeToast:resPonseObj[MESSAGE] duration:1.0 position:CSToastPositionCenter];
                dispatch_after(dispatch_time(DISPATCH_TIME_NOW, (int64_t)(1.5 * NSEC_PER_SEC)), dispatch_get_main_queue(), ^{

                    [self.navigationController popViewControllerAnimated:YES];
                });

            }else if ([resPonseObj[@"code"] integerValue] == 3000 ||[resPonseObj[@"code"] integerValue] == 4000 ){
                [YLUserInfo logout];
            }else{
                [self.view makeToast:resPonseObj[MESSAGE] duration:1.5 position:CSToastPositionCenter];
            }
        }else{
            [self.view makeToast:LocalizationKey(@"noNetworkStatus") duration:1.5 position:CSToastPositionCenter];
        }
    }];
}

//MARK:--选择开户银行的点击事件
- (IBAction)selectBankName:(id)sender {
    [self selectCoinTypeView];
}
//MARK:--点击购买弹出的提示框
-(void)selectCoinTypeView{
    NSArray *zhBankName = @[@"中国工商银行",@"中国农业银行",@"中国建设银行",@"中国邮政储蓄银行",@"招商银行",@"中国银行",@"交通银行",@"中信银行",@"华夏银行",@"中国民生银行",@"广发银行",@"平安银行",@"兴业银行",@"上海浦东发展银行",@"浙商银行",@"渤海银行",@"恒丰银行",@"花旗银行",@"渣打银行",@"汇丰银行",@"中国光大银行",@"上海银行",@"江苏银行",@"重庆银行",@"天津银行",@"厦门银行",@"城市商业银行",@"农村商业银行",@"徽商银行"];
    NSArray *enBankName = @[@"ICBC",@"ABC",@"CCB",@"PSBC",@"CMB",@"BOC",@"BOCOM",@"ECITIC",@"HXB",@"CMBC",@"Guangdong development bank",@"PAB",@"CIB",@"SPDB",@"CZB",@"CBB",@"Hengfeng bank",@"citibank",@"SCB",@"HSBC",@"CEB",@"Bank of Shanghai",@"Bank of jiangsu",@"Chongqing bank",@"Bank of tianjin",@"Xiamen bank",@"City commercial bank",@"Rural commercial bank",@"Huishang Bank"];

    HQPickerView *picker = [[HQPickerView alloc]initWithFrame:[UIApplication sharedApplication].keyWindow.frame];
    picker.delegate = self ;
    if ([[ChangeLanguage userLanguage] isEqualToString:@"en"]) {
        picker.customArr = enBankName;
    }else{
        picker.customArr = zhBankName;
    }
    [self.view addSubview:picker];

}

- (void)pickerView:(UIPickerView *)pickerView didSelectText:(NSString *)text {
    self.bankName.text = text;
}



@end
