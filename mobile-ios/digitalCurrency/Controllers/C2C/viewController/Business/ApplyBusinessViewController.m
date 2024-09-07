//
//  ApplyBusinessViewController.m
//  digitalCurrency
//
//  Created by startlink on 2019/8/10.
//  Copyright © 2019年 BIZZAN. All rights reserved.
//

#import "ApplyBusinessViewController.h"
#import "AuthenticationBusViewController.h"
#import "MineNetManager.h"
#import "PlatformMessageDetailViewController.h"

@interface ApplyBusinessViewController ()<UIScrollViewDelegate>
@property (weak, nonatomic) IBOutlet UILabel *titlelabel;
@property (weak, nonatomic) IBOutlet UIScrollView *scrollview;

@property (weak, nonatomic) IBOutlet UIPageControl *pagecontrol;
@property (weak, nonatomic) IBOutlet UIButton *agreebutton;
@property (weak, nonatomic) IBOutlet UIButton *agreementButton;
@property (weak, nonatomic) IBOutlet UIButton *Applybutton;

@property (nonatomic,copy)NSString *contentstr;

@end

@implementation ApplyBusinessViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    // Do any additional setup after loading the view from its nib.
    [self.agreebutton setTitle:LocalizationKey(@"readandagree") forState:UIControlStateNormal];
    [self.agreementButton setTitle:LocalizationKey(@"Certificationagreement") forState:UIControlStateNormal];
    [self.Applybutton setTitle:LocalizationKey(@"Applynow") forState:UIControlStateNormal];
    self.titlelabel.text = LocalizationKey(@"Applyforbusiness");
    [self ImageUI];
    self.Applybutton.layer.masksToBounds = YES;
    self.Applybutton.layer.cornerRadius = 22.5;
    [self getUserprotocol];

}

-(void)viewWillAppear:(BOOL)animated{
    [super viewWillAppear:animated];

//    [self setNavigationControllerStyle];
}

-(void)ImageUI{
    self.scrollview.contentSize = CGSizeMake(kWindowW * 3, 0);
    self.scrollview.bounces = NO;
    self.scrollview.pagingEnabled = YES;
    self.scrollview.showsVerticalScrollIndicator = NO;
    self.scrollview.showsHorizontalScrollIndicator = NO;
    self.scrollview.delegate = self;
    NSArray *array = @[@"专属展位",@"WechatIMG7",@"WechatIMG8"];
    for (int i = 0; i < 3; i ++) {
        UIImageView *imageview = [[UIImageView alloc]initWithFrame:CGRectMake( kWindowW * (0.1  + i), 0, kWindowW * 0.8,  kWindowW * 0.8)];
        imageview.image = [UIImage imageNamed:array[i]];
        [self.scrollview addSubview:imageview];
    }

}

#pragma mark---scrollviewdelegate
-(void)scrollViewDidEndDecelerating:(UIScrollView *)scrollView{

    int num = scrollView.contentOffset.x / scrollView.width;
    self.pagecontrol.currentPage = num;

}

//阅读并同意
- (IBAction)agreeaction:(id)sender {
    self.agreebutton.selected = !self.agreebutton.selected;
}

//商家协议
- (IBAction)agreementaction:(id)sender {
    PlatformMessageDetailViewController *detailVC = [[PlatformMessageDetailViewController alloc] init];
    detailVC.content = self.contentstr;
    detailVC.navtitle = LocalizationKey(@"Certificationagreement");
    detailVC.ID = @"11";
    [self.navigationController pushViewController:detailVC animated:YES];
}

//获取商家协议
-(void)getUserprotocol{

    [MineNetManager Userprotocol:@{@"id":@"11"} CompleteHandle:^(id resPonseObj, int code) {
        if (code) {
            if ([resPonseObj[@"code"] integerValue] == 0) {

                self.contentstr = resPonseObj[@"data"][@"content"];
            }else{
                [UIApplication.sharedApplication.keyWindow makeToast:resPonseObj[MESSAGE] duration:1.5 position:CSToastPositionCenter];
            }
        }else{
            [UIApplication.sharedApplication.keyWindow makeToast:[[ChangeLanguage bundle] localizedStringForKey:@"noNetworkStatus" value:nil table:@"English"] duration:1.5 position:CSToastPositionCenter];
        }
    }];
}


- (IBAction)Applyaction:(id)sender {

    if (!self.agreebutton.selected) {
        [self.view makeToast:LocalizationKey(@"PleaseUserprotocol") duration:1.5 position:CSToastPositionCenter];
        return;
    }

    AuthenticationBusViewController *AuthenticationVC = [AuthenticationBusViewController new];

    [self.navigationController pushViewController:AuthenticationVC animated:YES];
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
