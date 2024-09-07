//
//  MyBillViewController.m
//  digitalCurrency
//
//  Created by iDog on 2019/1/30.
//  Copyright © 2019年 BIZZAN. All rights reserved.
//

#import "MyBillViewController.h"
#import "MyBillPublicViewController.h"

@interface MyBillViewController ()<XLBasePageControllerDelegate,XLBasePageControllerDataSource>
@property (nonatomic, strong)  NSArray *menuList;
@property (nonatomic, strong)  NSArray *billStatusArr;
@property (nonatomic, assign)  BOOL autoSwitch;

@end

@implementation MyBillViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    self.view.backgroundColor = mainColor;
    [self backBtnNoNavBar:NO normalBack:YES];
    self.title = [[ChangeLanguage bundle] localizedStringForKey:@"myBill" value:nil table:@"English"];
    self.menuList = @[[[ChangeLanguage bundle] localizedStringForKey:@"unPaying" value:nil table:@"English"],[[ChangeLanguage bundle] localizedStringForKey:@"paid" value:nil table:@"English"],[[ChangeLanguage bundle] localizedStringForKey:@"completed" value:nil table:@"English"],[[ChangeLanguage bundle] localizedStringForKey:@"cancelled" value:nil table:@"English"],[[ChangeLanguage bundle] localizedStringForKey:@"complaint" value:nil table:@"English"]];
    self.billStatusArr = @[@"NONPAYMENT",@"PAID",@"COMPLETED",@"CANCELLED",@"APPEAL"];
    self.delegate = self;
    self.dataSource = self;
    self.lineWidth = 2.0;//选中下划线宽度
    self.titleFont = [UIFont systemFontOfSize:16.0];
    self.defaultColor = RGBOF(0xe6e6e6);//默认字体颜色
    self.chooseColor = baseColor;//选中字体颜色
    self.selectIndex = 0;//默认选中第几页
    [self reloadScrollPage];

    // Do any additional setup after loading the view.
}
-(NSInteger)numberViewControllersInViewPager:(XLBasePageController *)viewPager
{
    return _menuList.count;
}

-(UIViewController *)viewPager:(XLBasePageController *)viewPager indexViewControllers:(NSInteger)index
{
    MyBillPublicViewController *publicVC = [[MyBillPublicViewController  alloc] init];
    publicVC.index = index;
    publicVC.payStatus = self.menuList[index];
    publicVC.billStatus = self.billStatusArr[index];
    return publicVC;
}

-(CGFloat)heightForTitleViewPager:(XLBasePageController *)viewPager
{
    return 44;
}
-(NSString *)viewPager:(XLBasePageController *)viewPager titleWithIndexViewControllers:(NSInteger)index
{
    return self.menuList[index];
}
- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
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
