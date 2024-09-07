//
//  BeingauditedBusViewController.m
//  digitalCurrency
//
//  Created by startlink on 2019/8/10.
//  Copyright © 2019年 BIZZAN. All rights reserved.
//

#import "BeingauditedBusViewController.h"

@interface BeingauditedBusViewController ()<UIScrollViewDelegate>
@property (weak, nonatomic) IBOutlet UILabel *namelabel;
@property (weak, nonatomic) IBOutlet UILabel *titlelabel1;
@property (weak, nonatomic) IBOutlet UILabel *titlelabel2;
@property (weak, nonatomic) IBOutlet UILabel *titlelabel3;

@property (weak, nonatomic) IBOutlet UIScrollView *scrollview;
@property (weak, nonatomic) IBOutlet UIPageControl *pagecontrol;
@end

@implementation BeingauditedBusViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    // Do any additional setup after loading the view from its nib.
    
    self.namelabel.text = LocalizationKey(@"reviewSubmitted");
    self.titlelabel1.text = LocalizationKey(@"prepareMaterials");
    self.titlelabel2.text = LocalizationKey(@"submitReview");
    self.titlelabel3.text = LocalizationKey(@"waitingResults");
    
    
    [self ImageUI];

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
