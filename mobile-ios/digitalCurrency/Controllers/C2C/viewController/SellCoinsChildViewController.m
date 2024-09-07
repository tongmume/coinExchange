//
//  SellCoinsChildViewController.m
//  digitalCurrency
//
//  Created by iDog on 2019/2/25.
//  Copyright © 2019年 BIZZAN. All rights reserved.
//

#import "SellCoinsChildViewController.h"
#import "SellCoinsViewController.h"
#import "C2CNetManager.h"

@interface SellCoinsChildViewController ()<XLBasePageControllerDelegate,XLBasePageControllerDataSource,UIScrollViewDelegate>
{
    SellCoinsViewController *_sellVC;
}
@property (nonatomic, strong)  NSArray *menuList;
@property(nonatomic,strong)NSMutableArray *coinTypeArr;
@property (nonatomic, assign) BOOL fingerIsTouch;
@end

@implementation SellCoinsChildViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    self.delegate = self;
    self.dataSource = self;
    self.lineWidth = 2.0;//选中下划线宽度
    self.titleFont = [UIFont systemFontOfSize:17.0];
    self.defaultColor = RGBOF(0xE5E5E5);//默认字体颜色
    self.chooseColor = RGBOF(0xF0A70A);//选中字体颜色
    self.selectIndex = 0;//默认选中第几页
    [self getCoinTypeData];
}
//MARK:--获取全部货币种类
-(void)getCoinTypeData{
    [EasyShowLodingView showLodingText:[[ChangeLanguage bundle] localizedStringForKey:@"loading" value:nil table:@"English"]];
    [C2CNetManager selectCoinTypeForCompleteHandle:^(id resPonseObj, int code) {
        NSLog(@"获取全部货币种类 --- %@",resPonseObj);
        [EasyShowLodingView hidenLoding];
        if (code){
            if ([resPonseObj[@"code"] integerValue]==0) {
                //获取数据成功
                [_coinTypeArr removeAllObjects];
//                NSLog(@"--%@",resPonseObj);
                NSArray *dataArr = [SelectCoinTypeModel mj_objectArrayWithKeyValuesArray:resPonseObj[@"data"]];
                [self.coinTypeArr addObjectsFromArray:dataArr];
                [self switchViewUI];
            }else{
                [self.view makeToast:resPonseObj[MESSAGE] duration:1.5 position:CSToastPositionCenter];
            }
        }else{
            [self.view makeToast:[[ChangeLanguage bundle] localizedStringForKey:@"noNetworkStatus" value:nil table:@"English"] duration:1.5 position:CSToastPositionCenter];
        }
    }];
}
- (NSMutableArray *)coinTypeArr {
    if (!_coinTypeArr) {
        _coinTypeArr = [NSMutableArray array];
    }
    return _coinTypeArr;
}
//MARK:--第三方切换视图的封装方法
-(void)switchViewUI{
    NSMutableArray *arr = [[NSMutableArray alloc] init];
    for (SelectCoinTypeModel *model in _coinTypeArr) {
        [arr addObject:model.unit];
//        NSLog(@"---%@",model.unit);
    }
    self.menuList = arr;
    [self reloadScrollPage];
}
-(NSInteger)numberViewControllersInViewPager:(XLBasePageController *)viewPager
{
    return _menuList.count;
}

-(UIViewController *)viewPager:(XLBasePageController *)viewPager indexViewControllers:(NSInteger)index
{
    SellCoinsViewController *sellVC = [[SellCoinsViewController  alloc] init];
    sellVC.model = _coinTypeArr[index];
    return sellVC;
}
-(CGFloat)heightForTitleViewPager:(XLBasePageController *)viewPager
{
    return 40;
}
-(NSString *)viewPager:(XLBasePageController *)viewPager titleWithIndexViewControllers:(NSInteger)index
{
    return self.menuList[index];
}

-(void)viewPagerViewController:(XLBasePageController *)viewPager didFinishScrollWithCurrentViewController:(UIViewController *)viewController
{
    _sellVC = (SellCoinsViewController *)viewController;
    self.title = viewController.title;
}

- (void)setVcCanScroll:(BOOL)vcCanScroll{
    _vcCanScroll = vcCanScroll;
    _sellVC.vcCanScroll = vcCanScroll;
}

@end
