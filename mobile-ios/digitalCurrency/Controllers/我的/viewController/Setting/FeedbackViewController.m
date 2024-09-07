//
//  FeedbackViewController.m
//  digitalCurrency
//
//  Created by iDog on 2019/2/2.
//  Copyright © 2019年 BIZZAN. All rights reserved.
//

#import "FeedbackViewController.h"
#import "UITextView+Placeholder.h"
#import "MineNetManager.h"

@interface FeedbackViewController ()<UITextViewDelegate>
@property (weak, nonatomic) IBOutlet UITextView *feedbackTextView;
@property (weak, nonatomic) IBOutlet UITextField *feedbackPhoneNum;
@property (weak, nonatomic) IBOutlet NSLayoutConstraint *bottomViewHeight;

//国际化需要
@property (weak, nonatomic) IBOutlet UILabel *feedBackTopViewLabel;
@property (weak, nonatomic) IBOutlet UIButton *submitButtom;

@end

@implementation FeedbackViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    self.view.backgroundColor = kRGBColor(18, 22, 28);
    self.title = [[ChangeLanguage bundle] localizedStringForKey:@"feedback" value:nil table:@"English"];
    self.bottomViewHeight.constant = SafeAreaBottomHeight;
    self.feedbackTextView.placeholder = [[ChangeLanguage bundle] localizedStringForKey:@"feedbackTip2" value:nil table:@"English"];
    self.feedBackTopViewLabel.text = [[ChangeLanguage bundle] localizedStringForKey:@"feedbackTip1" value:nil table:@"English"];
    self.feedbackPhoneNum.placeholder = [[ChangeLanguage bundle] localizedStringForKey:@"feedbackTip3" value:nil table:@"English"];
    [self.submitButtom setTitle:[[ChangeLanguage bundle] localizedStringForKey:@"submit" value:nil table:@"English"] forState:UIControlStateNormal];
    self.feedbackTextView.delegate = self;
//    [_feedbackPhoneNum setValue:RGBOF(0x999999) forKeyPath:@"_placeholderLabel.textColor"];

    // Do any additional setup after loading the view from its nib.
}
-(void)textViewDidChange:(UITextView *)textView{
    //字数限制操作
    if (textView.text.length >= 100) {
        [self.feedbackTextView resignFirstResponder];
        [self.view makeToast:[[ChangeLanguage bundle] localizedStringForKey:@"feedbackJudgeAlphanumeric" value:nil table:@"English"] duration:1.5 position:CSToastPositionCenter];
        return;
    }
}
//MARK:--提交的点击事件
- (IBAction)submitBtnClick:(UIButton *)sender {
    if ([_feedbackTextView.text isEqualToString:@""]) {
        [self.view makeToast:[[ChangeLanguage bundle] localizedStringForKey:@"inputFeedbackTip" value:nil table:@"English"] duration:1.5 position:CSToastPositionCenter];
        return ;
    }
    //上传反馈意见
    [EasyShowLodingView showLodingText:[[ChangeLanguage bundle] localizedStringForKey:@"loading" value:nil table:@"English"]];
    [MineNetManager takeUpFeedBackForRemark:self.feedbackTextView.text CompleteHandle:^(id resPonseObj, int code) {
        [EasyShowLodingView hidenLoding];
        if (code) {
            if ([resPonseObj[@"code"] integerValue]==0) {
                //上传成功
                [self.view makeToast:resPonseObj[MESSAGE] duration:1.0 position:CSToastPositionCenter];
                dispatch_after(dispatch_time(DISPATCH_TIME_NOW, (int64_t)(1.5 * NSEC_PER_SEC)), dispatch_get_main_queue(), ^{

                    [self.navigationController popViewControllerAnimated:YES];
                });

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
