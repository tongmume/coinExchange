//
//  NewTabBarViewController.m
//  digitalCurrency
//
//  Created by chan on 2021/1/6.
//  Copyright © 2021 BIZZAN. All rights reserved.
//

#import "NewTabBarViewController.h"
#import "YLNavigationController.h"
#import "HomeViewController.h"
#import "MarketViewController.h"
#import "TradeViewController.h"
#import "FrenchCurrencyViewController.h"
#import "MineViewController.h"

#import "ContractExchangeViewController.h"
#import "OptionViewController.h"
#import "RDVTabBarItem.h"

@interface NewTabBarViewController ()

@end

@implementation NewTabBarViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    // Do any additional setup after loading the view.
    self.view.backgroundColor = mainColor;
    [self setupViewControllers];
}


- (void)setupViewControllers {
    HomeViewController   *Section1VC = [[HomeViewController alloc] init];
    MarketViewController *Section2VC = [[MarketViewController alloc] init];
    TradeViewController  *Section3VC = [[TradeViewController alloc] init];
    FrenchCurrencyViewController  *SectionTestVC = [[FrenchCurrencyViewController alloc] init];
    ContractExchangeViewController *Section5VC=[[ContractExchangeViewController alloc]init];
    OptionViewController *Section4VC = [[OptionViewController alloc] init];
    MineViewController   *Section6VC = [[MineViewController alloc] init];
    
    
    
    YLNavigationController *Section1Navi = [[YLNavigationController alloc] initWithRootViewController:Section1VC];
    YLNavigationController *Section2Navi = [[YLNavigationController alloc] initWithRootViewController:Section2VC];
    YLNavigationController *Section3Navi = [[YLNavigationController alloc] initWithRootViewController:Section3VC];
//    YLNavigationController *SectionTestNavi = [[YLNavigationController alloc] initWithRootViewController:SectionTestVC];
    YLNavigationController *Section4Navi = [[YLNavigationController alloc] initWithRootViewController:Section4VC];
    YLNavigationController *Section5Navi = [[YLNavigationController alloc] initWithRootViewController:Section5VC];
    YLNavigationController *Section6Navi = [[YLNavigationController alloc] initWithRootViewController:Section6VC];
    if (SafeAreaBottomHeight > 0) {
        self.tabBar.contentEdgeInsets = UIEdgeInsetsMake(10, 0, 0, 0);
    }
    self.view.backgroundColor = mainColor;
    self.tabBar.backgroundColor = mainColor;
    [self setViewControllers:@[Section1Navi, Section2Navi,
                               Section3Navi, Section5Navi,
                               Section4Navi,Section6Navi]];

    
    [self customizeTabBarForController:self];
}

- (void)customizeTabBarForController:(RDVTabBarController *)tabBarController {

//    NSArray *tabBarItemImages = @[@"shouye", @"hangqiang", @"bibi",@"fabi",@"contracttab",@"wode"];
    NSArray *tabBarItemImages = @[@"shouye", @"hangqiang", @"bibi",@"contracttab",@"contracttab",@"wode"];
    NSArray *titles = @[LocalizationKey(@"tabbar1"), LocalizationKey(@"tabbar2"), LocalizationKey(@"tabbar3"),LocalizationKey(@"tabbarcontact"),LocalizationKey(@"option"),LocalizationKey(@"tabbar5")];

    NSInteger index = 0;
    for (RDVTabBarItem *item in [[tabBarController tabBar] items]) {
        UIImage *selectedimage = [UIImage imageNamed:[NSString stringWithFormat:@"%@_checked",
                                                      [tabBarItemImages objectAtIndex:index]]];
        UIImage *unselectedimage = [UIImage imageNamed:[NSString stringWithFormat:@"%@_un",
                                                        [tabBarItemImages objectAtIndex:index]]];
        [item setFinishedSelectedImage:selectedimage withFinishedUnselectedImage:unselectedimage];
        [item setTitle:titles[index]];

        index++;
    }
}

//重置tabar标题
- (void)resettabarItemsTitle{
    NSArray *titles = @[LocalizationKey(@"tabbar1"), LocalizationKey(@"tabbar2"), LocalizationKey(@"tabbar3"),LocalizationKey(@"tabbarcontact"),LocalizationKey(@"option"),LocalizationKey(@"tabbar5")];

    NSInteger index = 0;
    for (RDVTabBarItem *item in [[self tabBar] items]) {
        [item setTitle:titles[index]];
        index++;
    }
    [self.tabBar layoutSubviews];
}

- (void)customizeInterface {
    UINavigationBar *navigationBarAppearance = [UINavigationBar appearance];
    
    UIImage *backgroundImage = nil;
    NSDictionary *textAttributes = nil;
    
    if (NSFoundationVersionNumber > NSFoundationVersionNumber_iOS_6_1) {
        backgroundImage = [UIImage imageNamed:@"navigationbar_background_tall"];
        
        textAttributes = @{
                           NSFontAttributeName: [UIFont boldSystemFontOfSize:18],
                           NSForegroundColorAttributeName: [UIColor blackColor],
                           };
    } else {
#if __IPHONE_OS_VERSION_MIN_REQUIRED < __IPHONE_7_0
        backgroundImage = [UIImage imageNamed:@"navigationbar_background"];
        
        textAttributes = @{
                           UITextAttributeFont: [UIFont boldSystemFontOfSize:18],
                           UITextAttributeTextColor: [UIColor blackColor],
                           UITextAttributeTextShadowColor: [UIColor clearColor],
                           UITextAttributeTextShadowOffset: [NSValue valueWithUIOffset:UIOffsetZero],
                           };
#endif
    }
    
    [navigationBarAppearance setBackgroundImage:backgroundImage
                                  forBarMetrics:UIBarMetricsDefault];
    [navigationBarAppearance setTitleTextAttributes:textAttributes];
}



@end
