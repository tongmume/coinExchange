//
//  MyBillPublicViewController.m
//  digitalCurrency
//
//  Created by iDog on 2019/1/31.
//  Copyright © 2019年 BIZZAN. All rights reserved.
//

#import "MyBillPublicViewController.h"
#import "MyBillTableViewCell.h"
#import "MineNetManager.h"
#import "MyBillInfoModel.h"
#import "MyBillDetail1ViewController.h"

@interface MyBillPublicViewController ()<UITableViewDataSource,UITableViewDelegate>
@property (weak, nonatomic) IBOutlet UITableView *tableView;
@property (weak, nonatomic) IBOutlet NSLayoutConstraint *bottomViewHeight;
@property(nonatomic,assign)NSInteger pageNo;
@property (nonatomic, strong)  NSArray *tipStringArr;
@property(nonatomic,strong)NSMutableArray *myBillInfoArr;
@property(nonatomic,copy)NSString *status; //出售或购买的状态
@end

@implementation MyBillPublicViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    self.edgesForExtendedLayout = UIRectEdgeNone;
    self.tableView.delegate = self;
    self.tableView.dataSource = self;
    [self.tableView registerNib:[UINib nibWithNibName:@"MyBillTableViewCell" bundle:nil] forCellReuseIdentifier:NSStringFromClass([MyBillTableViewCell class])];
    self.bottomViewHeight.constant = SafeAreaBottomHeight;
    self.tipStringArr = @[[[ChangeLanguage bundle] localizedStringForKey:@"unPayingTip" value:nil table:@"English"],[[ChangeLanguage bundle] localizedStringForKey:@"paidTip" value:nil table:@"English"],[[ChangeLanguage bundle] localizedStringForKey:@"completedTip" value:nil table:@"English"],[[ChangeLanguage bundle] localizedStringForKey:@"cancelledTip" value:nil table:@"English"],[[ChangeLanguage bundle] localizedStringForKey:@"complaintTip" value:nil table:@"English"]];
    [self headRefreshWithScrollerView:self.tableView];
    [self footRefreshWithScrollerView:self.tableView];
    LYEmptyView *emptyView = [LYEmptyView emptyViewWithImageStr:@"emptyData" titleStr:self.tipStringArr[_index]];
    self.tableView.ly_emptyView = emptyView;


}
-(void)viewWillAppear:(BOOL)animated{
    [super viewWillAppear:animated];
    self.pageNo = 0;
    [self loadData];
}
//MARK:--上拉加载
- (void)refreshFooterAction{
    self.pageNo++;
    [self loadData];
}
//MARK:--下拉刷新
- (void)refreshHeaderAction{
    self.pageNo = 0;
    [self loadData];
}
-(void)loadData{
    [EasyShowLodingView showLodingText:[[ChangeLanguage bundle] localizedStringForKey:@"loading" value:nil table:@"English"]];
    NSString *pageNoStr = [[NSString alloc] initWithFormat:@"%ld",(long)_pageNo];
    [MineNetManager myBillInfoForStatus:self.billStatus withPageNo:pageNoStr withPageSize:@"20" CompleteHandle:^(id resPonseObj, int code) {
        [EasyShowLodingView hidenLoding];
        if (code) {
            if ([resPonseObj[@"code"] integerValue]==0) {
                //获取数据成功
                if (_pageNo == 0) {
                    [_myBillInfoArr removeAllObjects];
                }
                NSArray *dataArr = [MyBillInfoModel mj_objectArrayWithKeyValuesArray:resPonseObj[@"data"][@"content"]];
                [self.myBillInfoArr addObjectsFromArray:dataArr];
                [self.tableView reloadData];
            }else if ([resPonseObj[@"code"] integerValue] == 3000 ||[resPonseObj[@"code"] integerValue] == 4000 ){
                //[ShowLoGinVC showLoginVc:self withTipMessage:resPonseObj[MESSAGE]];
                [YLUserInfo logout];
            }else{
                [self.view makeToast:resPonseObj[MESSAGE] duration:1.5 position:CSToastPositionCenter];
            }
        }else{
            [self.view makeToast:[[ChangeLanguage bundle] localizedStringForKey:@"noNetworkStatus" value:nil table:@"English"] duration:1.5 position:CSToastPositionCenter];
        }
    }];

}
- (NSMutableArray *)myBillInfoArr {
    if (!_myBillInfoArr) {
        _myBillInfoArr = [NSMutableArray array];
    }
    return _myBillInfoArr;
}
-(NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section{
    return _myBillInfoArr.count;
}
-(NSInteger)numberOfSectionsInTableView:(UITableView *)tableView{
    return 1;
}
- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath
{
    MyBillTableViewCell *cell = [tableView dequeueReusableCellWithIdentifier:NSStringFromClass([MyBillTableViewCell class]) forIndexPath:indexPath];
    cell.selectionStyle = UITableViewCellSelectionStyleNone;
    MyBillInfoModel *model = _myBillInfoArr[indexPath.row];
    cell.userName.text = model.name;
    if ([model.type isEqualToString:@"0"]) {
        //买入
        self.status = [[ChangeLanguage bundle] localizedStringForKey:@"buy" value:nil table:@"English"];
        cell.billStatus.backgroundColor=GreenColor;
    }else{
        self.status = [[ChangeLanguage bundle] localizedStringForKey:@"sell" value:nil table:@"English"];
        cell.billStatus.backgroundColor=RedColor;
    }
    cell.billStatus.text = [NSString stringWithFormat:@"%@%@",self.status,model.unit];
    cell.coinNum.text = [NSString stringWithFormat:@"%@%@",model.amount,model.unit];
    cell.priceNum.text = [NSString stringWithFormat:@"%@CNY",model.money];
    cell.payStatus.text = self.payStatus;
    [cell.headImage sd_setImageWithURL:[NSURL URLWithString:model.avatar] placeholderImage:[UIImage imageNamed:@"defaultImage"]];
    return cell;
}
-(void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath{
    MyBillInfoModel *model = _myBillInfoArr[indexPath.row];
    MyBillDetail1ViewController *detailVC = [[MyBillDetail1ViewController alloc] init];
    detailVC.orderId = model.orderSn;
    detailVC.avatar = model.avatar;
    detailVC.hidesBottomBarWhenPushed = YES;
    [self.navigationController pushViewController:detailVC animated:YES];
}
-(CGFloat)tableView:(UITableView *)tableView heightForRowAtIndexPath:(NSIndexPath *)indexPath{
    return 70;
}
- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

@end
