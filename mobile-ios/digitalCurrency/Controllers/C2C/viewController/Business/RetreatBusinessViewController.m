//
//  FailBusinessViewController.m
//  digitalCurrency
//
//  Created by startlink on 2019/8/13.
//  Copyright © 2019年 BIZZAN. All rights reserved.
//

#import "RetreatBusinessViewController.h"
#import "AuthenticationBusViewController.h"
#import "CQDeclareAlertView.h"
#import "MineNetManager.h"
#import "FrenchCurrencyViewController.h"
@interface RetreatBusinessViewController ()<UIScrollViewDelegate>

@property (weak, nonatomic) IBOutlet UILabel *namelabel;
@property (weak, nonatomic) IBOutlet UILabel *titlelabel1;
@property (weak, nonatomic) IBOutlet UILabel *titlelabel2;
@property (weak, nonatomic) IBOutlet UILabel *titlelabel3;
@property (weak, nonatomic) IBOutlet UIButton *Reapplybutton;
@property (weak, nonatomic) IBOutlet UILabel *Reasonlabel;
@property (weak, nonatomic) IBOutlet UIScrollView *scrollView;
@property (weak, nonatomic) IBOutlet UIPageControl *pagecontrol;
@property (weak, nonatomic) IBOutlet NSLayoutConstraint *topspec;
@property (weak, nonatomic) IBOutlet NSLayoutConstraint *topspec2;
@property (weak, nonatomic) IBOutlet NSLayoutConstraint *topspec3;
@property (weak, nonatomic) IBOutlet NSLayoutConstraint *topspec4;
@property (weak, nonatomic) IBOutlet NSLayoutConstraint *topspec5;
@property (weak, nonatomic) IBOutlet NSLayoutConstraint *topspec6;
@property (weak, nonatomic) IBOutlet NSLayoutConstraint *topspec7;
@end

@implementation RetreatBusinessViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    // Do any additional setup after loading the view from its nib.

    self.Reapplybutton.layer.masksToBounds = YES;
    self.Reapplybutton.layer.cornerRadius = 3;
    self.Reasonlabel.text = [NSString stringWithFormat:@"%@:%@",LocalizationKey(@"reason"),self.Reasonstring.length > 0 ? self.Reasonstring : LocalizationKey(@"nothing")];
    
    
    self.namelabel.text = LocalizationKey(@"cancellationApproved");
    self.titlelabel1.text = LocalizationKey(@"businessCancellation");
    self.titlelabel2.text = LocalizationKey(@"commitApply");
    self.titlelabel3.text = LocalizationKey(@"auditFailure");
    [self.Reapplybutton setTitle:LocalizationKey(@"review") forState:UIControlStateNormal];
    
    
    [self ImageUI];

}


-(void)ImageUI{
    self.namelabel.font = [UIFont systemFontOfSize:20 * kWindowWHOne];
    self.scrollView.contentSize = CGSizeMake(kWindowW * 3, 0);
    self.scrollView.bounces = NO;
    self.scrollView.pagingEnabled = YES;
    self.scrollView.showsVerticalScrollIndicator = NO;
    self.scrollView.showsHorizontalScrollIndicator = NO;
    self.scrollView.delegate = self;

    NSArray *array = @[@"专属展位",@"WechatIMG7",@"WechatIMG8"];
    for (int i = 0; i < 3; i ++) {
        UIImageView *imageview = [[UIImageView alloc]initWithFrame:CGRectMake( kWindowW * (0.1  + i), 0, kWindowW * 0.8,  kWindowW * 0.8)];
        imageview.image = [UIImage imageNamed:array[i]];
        [self.scrollView addSubview:imageview];
    }

    if (kWindowW == 320) {
        self.topspec.constant = self.topspec2.constant  = self.topspec3.constant = self.topspec4.constant = self.topspec5.constant = self.topspec6.constant = 20 * kWindowWHOne;
    }
}

#pragma mark---scrollviewdelegate
-(void)scrollViewDidEndDecelerating:(UIScrollView *)scrollView{

    int num = scrollView.contentOffset.x / scrollView.width;
    self.pagecontrol.currentPage = num;

}
- (IBAction)Reapplyaction:(id)sender {
    
    CQDeclareAlertView *alertView = [[CQDeclareAlertView alloc]initWithTitle:LocalizationKey(@"tips") initWithTitle:LocalizationKey(@"sureDone") message:LocalizationKey(@"pleaseCancellation") delegate:self leftButtonTitle:LocalizationKey(@"ok") rightButtonTitle:LocalizationKey(@"cancel")];
    [alertView show];
    
//    AuthenticationBusViewController *AuthenticationVC = [AuthenticationBusViewController new];
//
//    [self.navigationController pushViewController:AuthenticationVC animated:YES];

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
//cancelbusines
// 退保弹出框回调

- (void)CQDeclareAlertView:(CQDeclareAlertView *)alertView clickedButtonAtIndex:(NSInteger)buttonIndex msg:(NSString *)msg{
    if (buttonIndex == 0) {
        NSString *url = [NSString stringWithFormat:@"%@%@",HOST, @"uc/approve/cancel/business"];
        NSDictionary *param = @{@"detail":msg};
        [[XBRequest sharedInstance] postDataWithUrl:url Parameter:param ResponseObject:^(NSDictionary *responseResult) {
            if ([responseResult[@"code"] intValue] == 0) {
                [APPLICATION.window makeToast:LocalizationKey(@"submittedSuccessfully") duration:1.5 position:CSToastPositionCenter];
//                FrenchCurrencyViewController   *Section4VC = [[FrenchCurrencyViewController alloc] init];
//
//                Section4VC.hidesBottomBarWhenPushed = YES;
//                [self.navigationController pushViewController:Section4VC animated:YES];
                NSInteger num = self.navigationController.viewControllers.count;
                UIViewController *popVC =    self.navigationController.viewControllers[num - 2];
                [self.navigationController popToViewController:popVC animated:YES];
                
            }else{
                NSString *msg =  responseResult[@"message"];
                [APPLICATION.window makeToast:msg duration:1.5 position:CSToastPositionCenter];
            }
        }];
    } else {
        
    }
}


@end
