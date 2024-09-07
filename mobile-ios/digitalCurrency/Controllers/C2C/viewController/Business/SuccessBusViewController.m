//
//  SuccessBusViewController.m
//  digitalCurrency
//
//  Created by startlink on 2019/8/10.
//  Copyright © 2019年 BIZZAN. All rights reserved.
//

#import "SuccessBusViewController.h"
#import "AdvertisingBGView.h"
#import "AdvertisingBuyViewController.h"
#import "AdvertisingSellViewController.h"
#import "CQDeclareAlertView.h"

@interface SuccessBusViewController ()<UIScrollViewDelegate>{
    AdvertisingBGView *_adView; //广告
}
@property (weak, nonatomic) IBOutlet UILabel *namelabel;
@property (weak, nonatomic) IBOutlet UILabel *titlelabel1;
@property (weak, nonatomic) IBOutlet UILabel *titlelabel2;
@property (weak, nonatomic) IBOutlet UILabel *titlelabel3;
@property (weak, nonatomic) IBOutlet UIButton *Advertisebutton;
@property (weak, nonatomic) IBOutlet UIScrollView *scrollView;
@property (weak, nonatomic) IBOutlet UIPageControl *pagecontrol;
@property (weak, nonatomic) IBOutlet UILabel *ApplySurrender;


@end

@implementation SuccessBusViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    // Do any additional setup after loading the view from its nib.
    
    self.namelabel.text = LocalizationKey(@"certificationPassed");
    self.titlelabel1.text = LocalizationKey(@"prepareMaterials");
    self.titlelabel2.text = LocalizationKey(@"submitReview");
    self.titlelabel3.text = LocalizationKey(@"alreadyCertified");
     self.ApplySurrender.text = LocalizationKey(@"applyForLoan");
    [self.Advertisebutton setTitle:LocalizationKey(@"advertising") forState:UIControlStateNormal];
    
    self.Advertisebutton.layer.masksToBounds = YES;
    self.Advertisebutton.layer.cornerRadius = 3;
    //创建手势实例，并连接方法UITapGestureRecognizer,点击手势
    
    UITapGestureRecognizer *tapGesture=[[UITapGestureRecognizer alloc]initWithTarget:self action:@selector(onClickUILable:)];
    
    _ApplySurrender.userInteractionEnabled=YES;
    
    [_ApplySurrender addGestureRecognizer:tapGesture];
    [self ImageUI];
}
-(void)advertisingBGView{
    
    if (!_adView) {
        _adView = [[NSBundle mainBundle] loadNibNamed:@"AdvertisingBGView" owner:nil options:nil].firstObject;
        _adView.frame=[UIScreen mainScreen].bounds;
        
        [_adView.buyButton addTarget:self action:@selector(push:) forControlEvents:UIControlEventTouchUpInside];
        [_adView.sellButton addTarget:self action:@selector(push:) forControlEvents:UIControlEventTouchUpInside];
        [_adView.cancelButton addTarget:self action:@selector(push:) forControlEvents:UIControlEventTouchUpInside];
        [_adView.buyButton setTitle:LocalizationKey(@"buy") forState:UIControlStateNormal];
        [_adView.sellButton setTitle:LocalizationKey(@"sell") forState:UIControlStateNormal];
    }
    [_adView setMenu];
    
    [UIApplication.sharedApplication.keyWindow addSubview:_adView];
}
-(void)push:(UIButton*)sender{
    
    [_adView dismissMenu];
    
    if(![YLUserInfo isLogIn]&&sender.tag != 3){
        [self showLoginViewController];
    }else{
        
        if(sender.tag == 1){
            //购买
            AdvertisingBuyViewController *buyVC = [[AdvertisingBuyViewController alloc] init];
            buyVC.hidesBottomBarWhenPushed = YES;
            [self.navigationController pushViewController:buyVC animated:YES];
        }else if (sender.tag == 2){
            //出售
            AdvertisingSellViewController *sellVC = [[AdvertisingSellViewController alloc] init];
            sellVC.hidesBottomBarWhenPushed = YES;
            [self.navigationController pushViewController:sellVC animated:YES];
        }else if (sender.tag == 3){
            //取消
            NSLog(@"取消发布");
        }else{
            //其他
        }
    }
}

//发布广告
- (IBAction)Advertiseaction:(id)sender {
    
    [self advertisingBGView];
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

//定义响应事件

-(void)onClickUILable:(UITapGestureRecognizer *)sender{
    
    CQDeclareAlertView *alertView = [[CQDeclareAlertView alloc]initWithTitle:LocalizationKey(@"tips") initWithTitle:LocalizationKey(@"sureDone") message:LocalizationKey(@"pleaseCancellation") delegate:self leftButtonTitle:LocalizationKey(@"ok") rightButtonTitle:LocalizationKey(@"cancel")];
    [alertView show];
    
}

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

/*
 #pragma mark - Navigation
 
 // In a storyboard-based application, you will often want to do a little preparation before navigation
 - (void)prepareForSegue:(UIStoryboardSegue *)segue sender:(id)sender {
 // Get the new view controller using [segue destinationViewController].
 // Pass the selected object to the new view controller.
 }
 */

@end
