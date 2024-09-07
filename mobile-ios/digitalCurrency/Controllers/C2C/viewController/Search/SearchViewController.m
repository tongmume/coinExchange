//
//  SearchViewController.m
//  digitalCurrency
//
//  Created by iDog on 2019/1/31.
//  Copyright © 2019年 BIZZAN. All rights reserved.
//

#import "SearchViewController.h"
#import "SearchAdvertisingViewController.h"
#import "SearchUserViewController.h"

@interface SearchViewController ()
@property (nonatomic, strong)  NSArray *menuList;
@property (nonatomic, assign)  BOOL autoSwitch;

@end

@implementation SearchViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    self.title = @"搜索";
    self.menuList = @[@"搜索广告",@"搜索用户"];
    [self LeftsetupNavgationItemWithpictureName:@"goback"];
    self.magicView.navigationHeight = SafeAreaTopHeight+ 44;
    self.magicView.navigationColor = RGBCOLOR(218, 223, 224);
    self.magicView.navigationInset = UIEdgeInsetsMake(SafeAreaTopHeight, 0, 0, 0);
    self.magicView.sliderStyle = VTSliderStyleDefault;
    self.magicView.sliderColor = RGBCOLOR(174, 124, 0);
    self.magicView.layoutStyle = VTLayoutStyleDivide;
    self.edgesForExtendedLayout = UIRectEdgeAll;
    [self.magicView reloadData];
    // Do any additional setup after loading the view.
}
-(void)LefttouchEvent{
    [self.navigationController popViewControllerAnimated:YES];
}
- (void)viewWillAppear:(BOOL)animated {
    [super viewWillAppear:animated];

    [self.navigationController setNavigationBarHidden:NO animated:YES];
    _autoSwitch = 0 != self.tabBarController.selectedIndex;
}
- (void)viewDidAppear:(BOOL)animated {
    [super viewDidAppear:animated];

    if (_autoSwitch) {
        [self.magicView switchToPage:0 animated:YES];
        _autoSwitch = NO;
    }
}
#pragma mark - VTMagicViewDataSource
- (NSArray<NSString *> *)menuTitlesForMagicView:(VTMagicView *)magicView {
    return _menuList;
}

- (UIButton *)magicView:(VTMagicView *)magicView menuItemAtIndex:(NSUInteger)itemIndex {
    static NSString *itemIdentifier = @"itemIdentifier";
    UIButton *menuItem = [magicView dequeueReusableItemWithIdentifier:itemIdentifier];
    if (!menuItem) {
        menuItem = [UIButton buttonWithType:UIButtonTypeCustom];
        [menuItem setTitleColor:[UIColor blackColor] forState:UIControlStateNormal];
        [menuItem setTitleColor:RGBCOLOR(174, 124, 0) forState:UIControlStateSelected];
        menuItem.titleLabel.font = [UIFont fontWithName:@"Helvetica" size:15.f];
    }
    return menuItem;
}

- (UIViewController *)magicView:(VTMagicView *)magicView viewControllerAtPage:(NSUInteger)pageIndex {

    if (pageIndex == 0) {
        //搜索广告
        static NSString *recomId = @"advertisingVC";
        SearchAdvertisingViewController *advertisingVC = [magicView dequeueReusablePageWithIdentifier:recomId];
        if (!advertisingVC) {
            advertisingVC = [[SearchAdvertisingViewController alloc] init];
        }
        return advertisingVC;
    }else{
        //搜索用户
        static NSString *recomId = @"userVC";
        SearchUserViewController *userVC = [magicView dequeueReusablePageWithIdentifier:recomId];
        if (!userVC) {
            userVC = [[SearchUserViewController alloc] init];
        }
        return userVC;
    }
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
