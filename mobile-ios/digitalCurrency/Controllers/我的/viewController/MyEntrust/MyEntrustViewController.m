//
//  MyEntrustViewController.m
//  digitalCurrency
//
//  Created by iDog on 2019/4/10.
//  Copyright © 2019年 BIZZAN. All rights reserved.
//

#import "MyEntrustViewController.h"
#import "MyEntrustTableViewCell.h"
#import "MineNetManager.h"
#import "MyEntrustInfoModel.h"
#import "MyEntrustDetailViewController.h"
#import "YLTabBarController.h"
#import "TradeNetManager.h"
#import "PST_MenuView.h"

@interface MyEntrustViewController ()<UITableViewDelegate,UITableViewDataSource,PST_MenuViewDelegate>
@property(nonatomic,strong)UITableView *tableView;
@property(nonatomic,strong)NSMutableArray *tradCoinArr;
@property(nonatomic,strong)NSMutableArray *dataArr;
@property(nonatomic,copy)NSString *clickIndex;//点击标记
@property(nonatomic,strong)PST_MenuView *menuView;
@property(nonatomic,assign)NSInteger pageNo;
@property(nonatomic,assign)NSInteger indexPathRow;
@property (weak, nonatomic) IBOutlet UIImageView *pullimageV;
@property (weak, nonatomic) IBOutlet UIButton *titleBtn;

//国家化需要
@property (weak, nonatomic) IBOutlet UILabel *noDataLabel;

@end

@implementation MyEntrustViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    self.title = [[ChangeLanguage bundle] localizedStringForKey:@"myEntrust" value:nil table:@"English"];
    [self.view addSubview:[self tableView]];
    self.tradCoinArr = [[NSMutableArray alloc] init];
    self.dataArr = [[NSMutableArray alloc] init];
    self.pageNo = 0;
    [self getTradCoinData];
    [self.view bringSubviewToFront:self.backView];
    self.noDataLabel.text = LocalizationKey(@"noTrustData");
    self.indexPathRow = 0;
}
//MARK:--获取交易对数据
-(void)getTradCoinData{
    [EasyShowLodingView showLodingText:[[ChangeLanguage bundle] localizedStringForKey:@"loading" value:nil table:@"English"]];
    [MineNetManager getTradCoinForCompleteHandle:^(id resPonseObj, int code) {
        NSLog(@"--%@",resPonseObj);
        if ([resPonseObj isKindOfClass:[NSArray class]]) {
            NSArray *dataArr = [MyEntrustInfoModel mj_objectArrayWithKeyValuesArray:resPonseObj];
            if (dataArr.count > 0) {
                for (MyEntrustInfoModel *model in dataArr) {
                    [self.tradCoinArr addObject:model.symbol];
                }
                [self.titleBtn setTitle:self.tradCoinArr[0] forState:UIControlStateNormal];

                [self getData];
            }
        }else{
            [self.view makeToast:[[ChangeLanguage bundle] localizedStringForKey:@"noNetworkStatus" value:nil table:@"English"] duration:1.5 position:CSToastPositionCenter];
        }
    }];
}
-(UITableView *)tableView{
    if (!_tableView) {
    YLTabBarController*tabbar=(YLTabBarController*)APPLICATION.window.rootViewController;
        if (tabbar.selectedIndex==2) {
           _tableView = [[UITableView alloc] initWithFrame:CGRectMake(0, 60, kWindowW, kWindowH-SafeAreaBottomHeight-60) style:UITableViewStylePlain];
        }else{
           _tableView = [[UITableView alloc] initWithFrame:CGRectMake(0, 60, kWindowW, kWindowH-SafeAreaBottomHeight-SafeAreaTopHeight-60) style:UITableViewStylePlain];
        }
        _tableView.delegate = self;
        _tableView.dataSource = self;
        _tableView.rowHeight=185;
        _tableView.tableFooterView=[UIView new];
        _tableView.separatorStyle=UITableViewCellSeparatorStyleNone;
        [self footRefreshWithScrollerView:_tableView];
        [self headRefreshWithScrollerView:_tableView];
        [self.tableView registerNib:[UINib nibWithNibName:@"MyEntrustTableViewCell" bundle:nil] forCellReuseIdentifier:NSStringFromClass([MyEntrustTableViewCell class])];
    }
    return _tableView;
}
//MARK:--上拉加载
- (void)refreshFooterAction{
    self.pageNo++;
    [self getData];
}
//MARK:--下拉刷新
- (void)refreshHeaderAction{
    self.pageNo = 0;
    [self getData];
}
//MARK:--获取数据
-(void)getData{
    [EasyShowLodingView showLodingText:[[ChangeLanguage bundle] localizedStringForKey:@"loading" value:nil table:@"English"]];
    NSString *pageNoStr = [[NSString alloc] initWithFormat:@"%ld",(long)_pageNo];

    [TradeNetManager historyEntrustForParam:@{@"pageNo":pageNoStr, @"pageSize":@"10", @"symbol":[self.tradCoinArr objectAtIndex:self.indexPathRow]} CompleteHandle:^(id resPonseObj, int code) {
        [EasyShowLodingView hidenLoding];
        if (code) {
            if ([resPonseObj[@"code"] integerValue] == 0) {
                //解析数据
                if (_pageNo == 0) {
                    [_dataArr removeAllObjects];
                }
                NSArray *dataArr = [MyEntrustInfoModel mj_objectArrayWithKeyValuesArray:resPonseObj[@"content"]];
                for (MyEntrustInfoModel *model in dataArr) {
                    [self.dataArr addObject:model];
                }
                if (self.dataArr.count > 0) {
                    self.backView.hidden = YES;
                }else{
                    self.backView.hidden = NO;
                }
                [self.tableView reloadData];

            }else if ([resPonseObj[@"code"] integerValue]==4000){
                [YLUserInfo logout];
            }else{
                [self.view makeToast:resPonseObj[MESSAGE] duration:1.5 position:CSToastPositionCenter];
            }
        }else{
            [self.view makeToast:[[ChangeLanguage bundle] localizedStringForKey:@"noNetworkStatus" value:nil table:@"English"] duration:1.5 position:CSToastPositionCenter];
        }
    }];
}
-(NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section{
    return self.dataArr.count;
}

-(UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath{
    MyEntrustTableViewCell *cell = [tableView dequeueReusableCellWithIdentifier:NSStringFromClass([MyEntrustTableViewCell class]) forIndexPath:indexPath];
    cell.selectionStyle = UITableViewCellSelectionStyleNone;
    MyEntrustInfoModel *model = self.dataArr[indexPath.row];
    cell.infomodel = model;
    cell.entrustBlock = ^{
        MyEntrustDetailViewController *detailVC = [[MyEntrustDetailViewController alloc] init];
        detailVC.model = model;
        [[AppDelegate sharedAppDelegate] pushViewController:detailVC];
    };
    return cell;
}


-(void)didSelectRowAtIndex:(NSInteger)index title:(NSString *)title img:(NSString *)img{
//    NSLog(@"index----%zd,  title---%@, image---%@", index, title, img);
    if ([self.clickIndex isEqualToString:@"1"]) {
        self.clickIndex = @"0";
    }else{
        self.clickIndex = @"1";
    }

    self.indexPathRow = index;
    [self.titleBtn setTitle:title forState:UIControlStateNormal];
    _pageNo=0;
    [self getData];
}

//MARK:切换标题选项
- (IBAction)touchEvent:(UIButton *)sender {
    if ([self.clickIndex isEqualToString:@"1"]) {

        self.pullimageV.image=[UIImage imageNamed:@"downBlackImage"];
        self.clickIndex = @"0";
    }else{

        self.pullimageV.image=[UIImage imageNamed:@"pullBlackImage"];
        self.clickIndex = @"1";
    }
    //点击出现弹框
    NSMutableArray *imageArr = [[NSMutableArray alloc] init];
    for (NSString *str  in self.tradCoinArr) {
        NSLog(@"str -- %@",str);
        [imageArr addObject:@""];
    }
    PST_MenuView *menuView = [[PST_MenuView alloc] initWithFrame:CGRectMake(10, SafeAreaTopHeight+40, 120, 260) titleArr:self.tradCoinArr imgArr:imageArr arrowOffset:100 rowHeight:50 layoutType:0 directionType:0 delegate:self];
    menuView.backgroundColor = [UIColor clearColor];
    menuView.lineColor = [UIColor darkGrayColor];
    menuView.titleColor = [UIColor whiteColor];
    menuView.arrowColor = [UIColor colorWithRed:0/255.0 green:96/255.0 blue:96/255.0 alpha:1];

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
