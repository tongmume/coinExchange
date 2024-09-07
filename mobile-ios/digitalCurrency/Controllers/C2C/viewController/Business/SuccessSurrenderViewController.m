//
//  SuccessSurrenderViewController.m
//  digitalCurrency
//
//  Created by 邵贤军 on 2020/11/4.
//  Copyright © 2020 BIZZAN. All rights reserved.
//

#import "SuccessSurrenderViewController.h"
#import "AdvertisingBGView.h"
#import "AdvertisingBuyViewController.h"
#import "AdvertisingSellViewController.h"
#import "AuthenticationBusViewController.h"

@interface SuccessSurrenderViewController ()<UIScrollViewDelegate>{
}

@property (weak, nonatomic) IBOutlet UILabel *namelabel;
@property (weak, nonatomic) IBOutlet UILabel *titlelabel1;
@property (weak, nonatomic) IBOutlet UILabel *titlelabel2;
@property (weak, nonatomic) IBOutlet UILabel *titlelabel3;
@property (weak, nonatomic) IBOutlet UIButton *Advertisebutton;
@property (weak, nonatomic) IBOutlet UIScrollView *scrollView;
@property (weak, nonatomic) IBOutlet UIPageControl *pagecontrol;
@end

@implementation SuccessSurrenderViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    // Do any additional setup after loading the view from its nib.
    
    
    self.namelabel.text = LocalizationKey(@"yourApproved");
    self.titlelabel1.text = LocalizationKey(@"businessCancellation");
    self.titlelabel2.text = LocalizationKey(@"commitApply");
    self.titlelabel3.text = LocalizationKey(@"reviewPassed");
    [self.Advertisebutton setTitle:LocalizationKey(@"toApplyFor") forState:UIControlStateNormal];
    
        self.Advertisebutton.layer.masksToBounds = YES;
        self.Advertisebutton.layer.cornerRadius = 3;
         [self ImageUI];
}



//发布广告
- (IBAction)Advertiseaction:(id)sender {

//    if (!self.agreebutton.selected) {
//            [self.view makeToast:LocalizationKey(@"PleaseUserprotocol") duration:1.5 position:CSToastPositionCenter];
//            return;
//        }
    
        AuthenticationBusViewController *AuthenticationVC = [AuthenticationBusViewController new];
    
        [self.navigationController pushViewController:AuthenticationVC animated:YES];
//   [self advertisingBGView];
}
-(void)viewWillAppear:(BOOL)animated{
    [super viewWillAppear:animated];

//    [self setNavigationControllerStyle];
}

-(void)ImageUI{
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

}

#pragma mark---scrollviewdelegate
-(void)scrollViewDidEndDecelerating:(UIScrollView *)scrollView{

    int num = scrollView.contentOffset.x / scrollView.width;
    self.pagecontrol.currentPage = num;

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
