//
//  HistoryTransactionEntrustViewController.m
//  digitalCurrency
//
//  Created by iDog on 2019/4/25.
//  Copyright © 2019年 BIZZAN. All rights reserved.
//

#import "HistoryTransactionEntrustViewController.h"
#import "MyEntrustTableViewCell.h"
#import "TradeNetManager.h"
#import "MyEntrustInfoModel.h"
#import "MyEntrustDetailViewController.h"
#import "marketManager.h"

@interface HistoryTransactionEntrustViewController ()<UITableViewDelegate,UITableViewDataSource>
@property (weak, nonatomic) IBOutlet UITableView *tableView;
@property (weak, nonatomic) IBOutlet NSLayoutConstraint *bottomViewHeight;
@property(nonatomic,assign)NSInteger pageNo;
@property(nonatomic,strong)NSMutableArray *dataArr;
@property (nonatomic, strong) NSMutableDictionary *muDic;
@end

@implementation HistoryTransactionEntrustViewController

- (NSMutableDictionary *)muDic{
    if (!_muDic) {
        _muDic = [NSMutableDictionary dictionaryWithCapacity:0];
    }
    return _muDic;
}

- (void)viewWillAppear:(BOOL)animated{
    [super viewWillAppear:animated];
    NSLog(@"HistoryTransactionEntrustViewController");
}

- (void)viewDidLoad {
    [super viewDidLoad];
    self.view.backgroundColor = mainColor;
    [[NSNotificationCenter defaultCenter] addObserver:self selector:@selector(shanxuan:) name:@"historyEntrust" object:nil];
    if (@available(iOS 11.0, *)) {

        _tableView.estimatedRowHeight = 0;

        _tableView.estimatedSectionFooterHeight = 0;

        _tableView.estimatedSectionHeaderHeight=0;  _tableView.contentInsetAdjustmentBehavior= UIScrollViewContentInsetAdjustmentNever;

    }
    self.pageNo = 1;
    self.dataArr = [[NSMutableArray alloc] init];
    _tableView.delegate = self;
    _tableView.dataSource = self;
    [self.tableView setSeparatorColor:RGBOF(0xf0f0f0)];
    LYEmptyView*emptyView=[LYEmptyView emptyViewWithImageStr:@"emptyData" titleStr:LocalizationKey(@"nohistoryData")];
    self.tableView.ly_emptyView = emptyView;
    [self footRefreshWithScrollerView:_tableView];
    [self headRefreshWithScrollerView:_tableView];
    [self.tableView registerNib:[UINib nibWithNibName:@"MyEntrustTableViewCell" bundle:nil] forCellReuseIdentifier:NSStringFromClass([MyEntrustTableViewCell class])];
    [self getDataWithParam:@{@"pageNo":@"1", @"pageSize":@"10"}];
}

- (void)shanxuan:(NSNotification *)info{
    NSLog(@"info --- %@",info.object);
    self.pageNo = 1;
    [self.muDic removeAllObjects];
    [self.muDic setDictionary:info.object];
    [self getDataWithParam:self.muDic];
}
//MARK:--上拉加载
- (void)refreshFooterAction{
    self.pageNo++;
    if (self.muDic.count > 0) {
        [self.muDic setValue:@"pageNo" forKey:[NSString stringWithFormat:@"%ld",self.pageNo]];
        [self getDataWithParam:self.muDic];
    }else{
        [self getDataWithParam:@{@"pageNo":[NSNumber numberWithInteger:self.pageNo], @"pageSize":@"10"}];

    }
}
//MARK:--下拉刷新
- (void)refreshHeaderAction{
    self.pageNo = 1;
    if (self.muDic.count > 0) {
        [self getDataWithParam:self.muDic];

    }else{
        [self getDataWithParam:@{@"pageNo":[NSNumber numberWithInteger:self.pageNo], @"pageSize":@"10"}];

    }
}
//MARK:--获取数据
-(void)getDataWithParam:(NSDictionary *)param{
    [EasyShowLodingView showLodingText:LocalizationKey(@"loading")];
    [TradeNetManager historyEntrustForParam:param CompleteHandle:^(id resPonseObj, int code) {
        if ([self.tableView.mj_footer isRefreshing]) {
            [self.tableView.mj_footer endRefreshing];
        }
        if ([self.tableView.mj_header isRefreshing]) {
            [self.tableView.mj_header endRefreshing];
        }
        [EasyShowLodingView hidenLoding];
        NSLog(@"历史委托记录---%@",resPonseObj);
        if (code) {
            if ([resPonseObj[@"code"] integerValue] == 0) {
                //解析数据
                if (_pageNo == 1) {
                    [_dataArr removeAllObjects];
                }
                NSArray *dataArr = [MyEntrustInfoModel mj_objectArrayWithKeyValuesArray:resPonseObj[@"content"]];
                for (MyEntrustInfoModel *model in dataArr) {
                    [self.dataArr addObject:model];
                }
                [self.tableView reloadData];

            }else if ([resPonseObj[@"code"] integerValue]==4000){
                // [ShowLoGinVC showLoginVc:self withTipMessage:resPonseObj[MESSAGE]];
                [YLUserInfo logout];
            }else{
                [self.view makeToast:resPonseObj[MESSAGE] duration:1.5 position:CSToastPositionCenter];
            }
        }else{
            [self.view makeToast:LocalizationKey(@"noNetworkStatus") duration:1.5 position:CSToastPositionCenter];
        }
    }];

}
-(NSInteger)numberOfSectionsInTableView:(UITableView *)tableView{
    return 1;
}
-(NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section{
    return self.dataArr.count;
}

- (CGFloat)tableView:(UITableView *)tableView heightForHeaderInSection:(NSInteger)section{
    return 0.0001f;
}

- (UIView *)tableView:(UITableView *)tableView viewForHeaderInSection:(NSInteger)section{
    UIView *view = [[UIView alloc] init];
    return view;
}

- (CGFloat)tableView:(UITableView *)tableView heightForFooterInSection:(NSInteger)section{
    return 0.0001f;
}

- (UIView *)tableView:(UITableView *)tableView viewForFooterInSection:(NSInteger)section{
    UIView *view = [[UIView alloc] init];
    return view;
}

-(UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath{
    MyEntrustTableViewCell *cell = [tableView dequeueReusableCellWithIdentifier:NSStringFromClass([MyEntrustTableViewCell class]) forIndexPath:indexPath];
    cell.selectionStyle = UITableViewCellSelectionStyleNone;
    MyEntrustInfoModel *model = self.dataArr[indexPath.row];

    cell.infomodel = model;
    cell.entrustBlock = ^{
        MyEntrustDetailViewController *detailVC = [[MyEntrustDetailViewController alloc] init];
        detailVC.hidesBottomBarWhenPushed = YES;
        detailVC.model = model;
        [[AppDelegate sharedAppDelegate] pushViewController:detailVC];
    };
    return cell;
}

-(CGFloat)tableView:(UITableView *)tableView heightForRowAtIndexPath:(NSIndexPath *)indexPath{
    return 175;
}


@end
