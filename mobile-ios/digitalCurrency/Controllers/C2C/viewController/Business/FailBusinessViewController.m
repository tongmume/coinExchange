//
//  FailBusinessViewController.m
//  digitalCurrency
//
//  Created by startlink on 2019/8/13.
//  Copyright © 2019年 BIZZAN. All rights reserved.
//

#import "FailBusinessViewController.h"
#import "AuthenticationBusViewController.h"
@interface FailBusinessViewController ()<UIScrollViewDelegate>

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

@implementation FailBusinessViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    // Do any additional setup after loading the view from its nib.

    self.Reapplybutton.layer.masksToBounds = YES;
    self.Reapplybutton.layer.cornerRadius = 3;
    self.Reasonlabel.text = [NSString stringWithFormat:@"%@:%@",LocalizationKey(@"reason"),self.Reasonstring.length > 0 ? self.Reasonstring : LocalizationKey(@"nothing")];
    
    
    self.namelabel.text = LocalizationKey(@"certificationFailed");
    self.titlelabel1.text = LocalizationKey(@"submissionInformation");
    self.titlelabel2.text = LocalizationKey(@"submitReview");
    self.titlelabel3.text = LocalizationKey(@"auditFailure");
    [self.Reapplybutton setTitle:LocalizationKey(@"toApplyFor") forState:UIControlStateNormal];
    
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
