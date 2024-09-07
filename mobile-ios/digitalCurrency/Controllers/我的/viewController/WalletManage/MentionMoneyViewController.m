//
//  MentionMoneyViewController.m
//  digitalCurrency
//
//  Created by iDog on 2019/2/7.
//  Copyright © 2019年 BIZZAN. All rights reserved.
//

#import "MentionMoneyViewController.h"
#import "MineNetManager.h"
#import "MentionCoinInfoModel.h"
#import "AddMentionCoinAddressViewController.h"
#import "UITextView+Placeholder.h"

@interface MentionMoneyViewController ()<UITextFieldDelegate,AddMentionCoinAddressViewControllerDelegate>
@property (weak, nonatomic) IBOutlet UILabel *availableCoinNum; //可用货币数量
@property (weak, nonatomic) IBOutlet UILabel *availableCoinType;//可用货币类型
@property (weak, nonatomic) IBOutlet UITextView *mentionMoneyAddress;//提币地址
@property (weak, nonatomic) IBOutlet UIButton *QrCodeButton;//二维码按钮
@property (weak, nonatomic) IBOutlet UITextField *numTextField;//数量中的货币数
@property (weak, nonatomic) IBOutlet UILabel *numCoinType;//数量中的货币类型
@property (weak, nonatomic) IBOutlet UIButton *allButton;//全部按钮
@property (weak, nonatomic) IBOutlet UITextField *poundageNum;//手续费
@property (weak, nonatomic) IBOutlet UITextField *accountNum;//到账数量
@property (weak, nonatomic) IBOutlet UILabel *accountNumCoinType;//到账数量货币的类型
@property (weak, nonatomic) IBOutlet UITextField *moneyPassword;//资金密码
@property (weak, nonatomic) IBOutlet UITextField *mobilecodeTF;//手机验证码
@property(nonatomic,strong)NSMutableArray *mentionCoinArr;
@property(nonatomic,strong)MentionCoinInfoModel *model;
@property (weak, nonatomic) IBOutlet NSLayoutConstraint *viewHeight;
@property (weak, nonatomic) IBOutlet UIButton *getcodeButton;
@property (weak, nonatomic) IBOutlet UITextField *memoNum;
@property (weak, nonatomic) IBOutlet NSLayoutConstraint *viewMemo;
@property (weak, nonatomic) IBOutlet NSLayoutConstraint *viewmemoLine;

//国际化需要
@property (weak, nonatomic) IBOutlet UILabel *availableLabel;
@property (weak, nonatomic) IBOutlet UILabel *mentionCoinAdddressLabel;
@property (weak, nonatomic) IBOutlet UILabel *amountLabel;
@property (weak, nonatomic) IBOutlet UILabel *feeLabel;
@property (weak, nonatomic) IBOutlet UILabel *amountNumLabel;
@property (weak, nonatomic) IBOutlet UILabel *moneyPasswordLabel;
@property (weak, nonatomic) IBOutlet UIButton *mentionButton;
@property (weak, nonatomic) IBOutlet UILabel *mobilecodelabel;
@property (weak, nonatomic) IBOutlet UILabel *memo;

@end

@implementation MentionMoneyViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    NSLog(@"unit -- %@",self.unit);
    self.title = [NSString stringWithFormat:@"%@",[[ChangeLanguage bundle] localizedStringForKey:@"mentionMoney" value:nil table:@"English"]];
    self.availableCoinType.text = self.unit;
    self.accountNumCoinType.text = self.unit;
    self.numCoinType.text = self.unit;
    self.mentionMoneyAddress.placeholder = [[ChangeLanguage bundle] localizedStringForKey:@"inputAddAdress" value:nil table:@"English"];
    self.mobilecodeTF.placeholder = LocalizationKey(@"inputPhoneCode");
    self.numTextField.keyboardType = UIKeyboardTypeDecimalPad;
    self.moneyPassword.secureTextEntry = YES;
    [self mentionCoinInfo];
    self.viewHeight.constant = 70 * kWindowWHOne;
    self.availableLabel.text = [[ChangeLanguage bundle] localizedStringForKey:@"availableCoin" value:nil table:@"English"];
    self.mentionCoinAdddressLabel.text = [[ChangeLanguage bundle] localizedStringForKey:@"mentionMoneyAddress" value:nil table:@"English"];
    self.amountLabel.text = [[ChangeLanguage bundle] localizedStringForKey:@"amount" value:nil table:@"English"];
    self.feeLabel.text = [[ChangeLanguage bundle] localizedStringForKey:@"poundage" value:nil table:@"English"];
    self.amountNumLabel.text = [[ChangeLanguage bundle] localizedStringForKey:@"mentionMoneyAmount" value:nil table:@"English"];
    self.moneyPasswordLabel.text = [[ChangeLanguage bundle] localizedStringForKey:@"moneyPassword" value:nil table:@"English"];
    [self.mentionButton setTitle:[[ChangeLanguage bundle] localizedStringForKey:@"mentionMoney" value:nil table:@"English"] forState:UIControlStateNormal];
    self.mobilecodelabel.text = [[ChangeLanguage bundle] localizedStringForKey:@"phoneValidate" value:nil table:@"English"];
    self.memo.text = [[ChangeLanguage bundle] localizedStringForKey:@"memo" value:nil table:@"English"];
    
    
    [self.getcodeButton setTitle:[[ChangeLanguage bundle] localizedStringForKey:@"getVerifyCode" value:nil table:@"English"] forState:UIControlStateNormal];
     [self.allButton setTitle:[[ChangeLanguage bundle] localizedStringForKey:@"total" value:nil table:@"English"] forState:UIControlStateNormal];
//    [self.QrCodeButton setTitle:[[ChangeLanguage bundle] localizedStringForKey:@"addAddress" value:nil table:@"English"] forState:UIControlStateNormal];
    self.accountNum.placeholder = [[ChangeLanguage bundle] localizedStringForKey:@"inputMentionMonneyAmount" value:nil table:@"English"];
    self.moneyPassword.placeholder = [[ChangeLanguage bundle] localizedStringForKey:@"inputMoneyPassword" value:nil table:@"English"];

//    [_numTextField setValue:RGBOF(0x999999) forKeyPath:@"_placeholderLabel.textColor"];
//    [_moneyPassword setValue:RGBOF(0x999999) forKeyPath:@"_placeholderLabel.textColor"];
//    [_mobilecodeTF setValue:RGBOF(0x999999) forKeyPath:@"_placeholderLabel.textColor"];
//    [_poundageNum setValue:RGBOF(0x999999) forKeyPath:@"_placeholderLabel.textColor"];
//    [_accountNum setValue:RGBOF(0x999999) forKeyPath:@"_placeholderLabel.textColor"];
}
//MARK:--整理数据
-(void)arrageData{
    for (MentionCoinInfoModel *mentionModel in self.mentionCoinArr) {
        NSLog(@"--%@",mentionModel.unit);
        if ([mentionModel.unit isEqualToString:self.unit]) {
            if ([mentionModel.accountType isEqualToString:@"1"]) {
                _viewMemo.constant = 70;
                _viewmemoLine.constant = 1;
            }else{
                _viewMemo.constant = 0;
                _viewmemoLine.constant = 0;
            }
            self.model = mentionModel;
            self.availableCoinNum.text = self.model.balance;

            self.numTextField.placeholder = [NSString stringWithFormat:@"%@<=%@",[[ChangeLanguage bundle] localizedStringForKey:@"mentionMoneyAmount" value:nil table:@"English"],[ToolUtil formartScientificNotationWithString:self.model.balance]];

            if ([self.model.minTxFee isEqualToString:self.model.maxTxFee]) {
                self.poundageNum.text = self.model.minTxFee;
                self.poundageNum.enabled = NO;
            }else{
                self.poundageNum.placeholder = [NSString stringWithFormat:@"%@%@-%@%@",[[ChangeLanguage bundle] localizedStringForKey:@"mentionMoneyTip1" value:nil table:@"English"],[ToolUtil formartScientificNotationWithString:self.model.minTxFee],[ToolUtil formartScientificNotationWithString:self.model.maxTxFee],[[ChangeLanguage bundle] localizedStringForKey:@"poundage" value:nil table:@"English"]];
            }
            self.numTextField.delegate = self;
            self.poundageNum.delegate = self;
        }
    }
}
//MARK:--获取提币信息
-(void)mentionCoinInfo{
    [EasyShowLodingView showLodingText:[[ChangeLanguage bundle] localizedStringForKey:@"loading" value:nil table:@"English"]];
    [MineNetManager mentionCoinInfoForCompleteHandle:^(id resPonseObj, int code) {
        NSLog(@"获取提币信息 ---- %@",resPonseObj);
        [EasyShowLodingView hidenLoding];
        if (code) {
            if ([resPonseObj[@"code"] integerValue] == 0) {
                //[self.view makeToast:resPonseObj[MESSAGE] duration:1.5 position:CSToastPositionCenter];
                NSArray *dataArr = [MentionCoinInfoModel mj_objectArrayWithKeyValuesArray:resPonseObj[@"data"]];
                [self.mentionCoinArr addObjectsFromArray:dataArr];

                [self arrageData];
            }else if ([resPonseObj[@"code"] integerValue] == 3000 ||[resPonseObj[@"code"] integerValue] == 4000 ){
               // [ShowLoGinVC showLoginVc:self withTipMessage:resPonseObj[MESSAGE]];
                [YLUserInfo logout];
            }else{
                [self.view makeToast:resPonseObj[MESSAGE] duration:1.5 position:CSToastPositionCenter];
            }
        }else{
            [self.view makeToast:[[ChangeLanguage bundle] localizedStringForKey:@"noNetworkStatus" value:nil table:@"English"] duration:1.5 position:CSToastPositionCenter];
        }
    }];
}
- (NSMutableArray *)mentionCoinArr {
    if (!_mentionCoinArr) {
        _mentionCoinArr = [NSMutableArray array];
    }
    return _mentionCoinArr;
}
/*
-(void)textFieldDidEndEditing:(UITextField *)textField{
    if (self.numTextField == textField) {
        //提币数量
        if (![textField.text isEqualToString:@""]){
            if (![self.poundageNum.text isEqualToString:@""]){
                self.accountNum.text = [NSString stringWithFormat:@"%f",[self.numTextField.text floatValue]-[self.poundageNum.text floatValue]];
            }
        }else{
            self.accountNum.text = @"";
        }
    }else if (self.poundageNum == textField){
        if (![textField.text isEqualToString:@""]){
            if (![self.numTextField.text isEqualToString:@""]){
                self.accountNum.text = [NSString stringWithFormat:@"%f",[self.numTextField.text floatValue]-[self.poundageNum.text floatValue]];
            }
        }else{
           self.accountNum.text = @"";
        }
    }
}
 */

- (IBAction)changeTF:(UITextField *)sender {
    if (self.numTextField == sender) {
        //提币数量
        if (![sender.text isEqualToString:@""]){
            if (![self.poundageNum.text isEqualToString:@""]){
                NSDecimalNumber *selNum = [[NSDecimalNumber alloc] initWithString:self.numTextField.text];
                NSDecimalNumber *poundNum = [[NSDecimalNumber alloc] initWithString:self.poundageNum.text];

                NSDecimalNumber *selValue = [selNum decimalNumberBySubtracting:poundNum];
                NSLog(@"selValue --- %@",selValue);
                self.accountNum.text = [NSString stringWithFormat:@"%lf",[selValue doubleValue] < 0 ? 0 : [selValue doubleValue]];
            }
        }else{
            self.accountNum.text = @"";
        }
    }else if (self.poundageNum == sender){
        if (![sender.text isEqualToString:@""]){
            if (![self.numTextField.text isEqualToString:@""]){


                NSDecimalNumber *selNum = [[NSDecimalNumber alloc] initWithString:self.numTextField.text];
                NSDecimalNumber *poundNum = [[NSDecimalNumber alloc] initWithString:self.poundageNum.text];

                NSDecimalNumber *selValue = [selNum decimalNumberBySubtracting:poundNum];
                NSLog(@"selValue --- %@",selValue);
                self.accountNum.text = [NSString stringWithFormat:@"%lf",[selValue doubleValue] < 0 ? 0 : [selValue doubleValue]];
            }
        }else{
            self.accountNum.text = @"";
        }
    }
}


//MARK:--按钮的点击事件
- (IBAction)btnClick:(UIButton *)sender {
    if (sender.tag == 1){
        AddMentionCoinAddressViewController *addressVC = [[AddMentionCoinAddressViewController alloc] init];
        addressVC.hidesBottomBarWhenPushed = YES;
        addressVC.delegate = self;
        addressVC.addressInfoArr = self.model.addresses;
        [self.navigationController pushViewController:addressVC animated:YES];

    }else if (sender.tag == 2){
        //点击全部按钮
        self.numTextField.text = self.model.balance;

        NSDecimalNumber *selNum = [[NSDecimalNumber alloc] initWithString:self.numTextField.text];
        NSDecimalNumber *poundNum = [[NSDecimalNumber alloc] initWithString:self.poundageNum.text];

        NSDecimalNumber *selValue = [selNum decimalNumberBySubtracting:poundNum];
        NSLog(@"selValue --- %@",selValue);
        self.accountNum.text = [NSString stringWithFormat:@"%lf",[selValue doubleValue] < 0 ? 0 : [selValue doubleValue]];
    }else if (sender.tag == 3){
        //点击提币
        [self submitInfo];
    }
}

//获取手机验证码
- (IBAction)getmobilecodeaction:(id)sender {
    [EasyShowLodingView showLodingText:[[ChangeLanguage bundle] localizedStringForKey:@"hardLoading" value:nil table:@"English"]];
    [MineNetManager resetwithdrawCodeForCompleteHandle:^(id resPonseObj, int code) {
        [EasyShowLodingView hidenLoding];
        if (code) {
            if ([resPonseObj[@"code"] integerValue] == 0) {
                [self.view makeToast:[[ChangeLanguage bundle] localizedStringForKey:@"getMessageCodeTip" value:nil table:@"English"] duration:1.5 position:CSToastPositionCenter];
                __block int timeout=60; //倒计时时间
                dispatch_queue_t queue = dispatch_get_global_queue(DISPATCH_QUEUE_PRIORITY_DEFAULT, 0);
                dispatch_source_t _timer = dispatch_source_create(DISPATCH_SOURCE_TYPE_TIMER, 0, 0,queue);
                dispatch_source_set_timer(_timer,dispatch_walltime(NULL, 0),1.0*NSEC_PER_SEC, 0); //每秒执行
                dispatch_source_set_event_handler(_timer, ^{
                    if(timeout<=0){ //倒计时结束，关闭
                        dispatch_source_cancel(_timer);
                        dispatch_async(dispatch_get_main_queue(), ^{
                            [self.getcodeButton setTitle:[[ChangeLanguage bundle] localizedStringForKey:@"getVerifyCode" value:nil table:@"English"] forState:UIControlStateNormal];
                            self.getcodeButton.userInteractionEnabled = YES;
                        });
                    }else{
                        int seconds = timeout % 90;
                        NSString *strTime = [NSString stringWithFormat:@"%.2d", seconds];
                        dispatch_async(dispatch_get_main_queue(), ^{
                            [self.getcodeButton setTitle: [NSString stringWithFormat:@"%@s",strTime] forState:UIControlStateNormal];
                            self.getcodeButton.userInteractionEnabled = NO;
                        });
                        timeout--;
                    }
                });
                dispatch_resume(_timer);

            }else if ([resPonseObj[@"code"] integerValue] == 3000 ||[resPonseObj[@"code"] integerValue] == 4000 ){
                //[ShowLoGinVC showLoginVc:self withTipMessage:resPonseObj[MESSAGE]];
                [YLUserInfo logout];

            }else{
                [self.view makeToast:resPonseObj[MESSAGE] duration:1.5 position:CSToastPositionCenter];
            }
        }
    }];


}
//MARK:--添加地址的回调方法
-(void)AddAdressString:(NSString *)addAdressString{
    self.mentionMoneyAddress.text = addAdressString;
}
//MARK:--提币点击事件
-(void)submitInfo{
    if ([self.mentionMoneyAddress.text isEqualToString:@""]) {
         [self.view makeToast:LocalizationKey(@"inputMentionAddress") duration:1.5 position:CSToastPositionCenter];
        return;
    }
    if ([self.numTextField.text isEqualToString:@""]) {
        [self.view makeToast: LocalizationKey(@"inputMentionAmount") duration:1.5 position:CSToastPositionCenter];
        return;
    }
    if ([self.numTextField.text floatValue] > [self.model.balance floatValue]) {
        [self.view makeToast:LocalizationKey(@"mentionMoneyTip3") duration:1.5 position:CSToastPositionCenter];
        return;
    }
    if ([self.poundageNum.text isEqualToString:@""]){
        [self.view makeToast:LocalizationKey(@"inputFee") duration:1.5 position:CSToastPositionCenter];
        return;
    }
    if ([self.moneyPassword.text isEqualToString:@""]){
        [self.view makeToast:LocalizationKey(@"inputMoneyPassword") duration:1.5 position:CSToastPositionCenter];
        return;
    }
    if ([self.mobilecodeTF.text isEqualToString:@""]){
        [self.view makeToast:LocalizationKey(@"inputCode") duration:1.5 position:CSToastPositionCenter];
        return;
    }
    if ([self.model.accountType isEqualToString:@"1"] && [self.memoNum.text isEqualToString:@""]) {
            [self.view makeToast:LocalizationKey(@"pleaseConfirm") duration:1.5 position:CSToastPositionCenter];
            return;
        }
        NSString *remark = self.memoNum.text;
    //    for (AddressInfo *address in self.model.addresses) {
    //        if ([self.mentionMoneyAddress.text isEqualToString:address.address]) {
    //            remark = address.remark;
    //        }
    //    }
    [EasyShowLodingView showLodingText:[[ChangeLanguage bundle] localizedStringForKey:@"loading" value:nil table:@"English"]];
    [MineNetManager mentionCoinApplyForUnit:self.model.unit withAddress:self.mentionMoneyAddress.text withAmount:self.numTextField.text withFee:self.poundageNum.text withRemark:remark withJyPassword:self.moneyPassword.text mobilecode:self.mobilecodeTF.text  CompleteHandle:^(id resPonseObj, int code) {
        [EasyShowLodingView hidenLoding];
        if (code) {
            if ([resPonseObj[@"code"] integerValue] == 0) {
                [self.view makeToast:resPonseObj[MESSAGE] duration:1.5 position:CSToastPositionCenter];
                dispatch_after(dispatch_time(DISPATCH_TIME_NOW, (int64_t)(1.5 * NSEC_PER_SEC)), dispatch_get_main_queue(), ^{
                    [self.navigationController popViewControllerAnimated:YES];
                });
            }else if ([resPonseObj[@"code"] integerValue] == 3000 ||[resPonseObj[@"code"] integerValue] == 4000 ){
                //[ShowLoGinVC showLoginVc:self withTipMessage:resPonseObj[MESSAGE]];
                [YLUserInfo logout];
            }else{
                [self.view makeToast:resPonseObj[MESSAGE] duration:1.5 position:CSToastPositionCenter];
            }
        }else{
            [self.view makeToast:[[ChangeLanguage bundle] localizedStringForKey:@"noNetworkStatus" value:nil table:@"English"] duration:1.5 position:CSToastPositionCenter];
        }
    }];
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
