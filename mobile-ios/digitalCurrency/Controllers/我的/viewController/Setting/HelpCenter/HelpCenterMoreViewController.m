//
//  HelpCenterMoreViewController.m
//  digitalCurrency
//
//  Created by chu on 2019/8/10.
//  Copyright © 2019年 BIZZAN. All rights reserved.
//

#import "HelpCenterMoreViewController.h"
#import "HelpCenterTableViewCell.h"
#import "HelpCenterModel.h"
#import "HelpCenterDetailsViewController.h"
@interface HelpCenterMoreViewController ()<UITableViewDelegate, UITableViewDataSource>
{
    NSInteger _page;
}
@property (nonatomic, strong) UITableView *tableView;

@property (nonatomic, strong) UIView *headerView;

@property (nonatomic, strong) NSMutableArray *dataSourceArr;

@end

@implementation HelpCenterMoreViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    _page = 1;
    self.title = [[ChangeLanguage bundle] localizedStringForKey:@"Helpcenter" value:nil table:@"English"];
    [self.view addSubview:self.tableView];
    self.tableView.estimatedRowHeight = 44.0;
    self.tableView.rowHeight = UITableViewAutomaticDimension;
    [self getData];
}

- (NSInteger)numberOfSectionsInTableView:(UITableView *)tableView{
    return 1;
}

- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section{
    return self.dataSourceArr.count;
}

- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath{
    static  NSString *identifier = @"helpCell";
    HelpCenterTableViewCell *cell = [tableView dequeueReusableCellWithIdentifier:identifier];
    if (!cell) {
        cell = [[NSBundle mainBundle] loadNibNamed:@"HelpCenterTableViewCell" owner:nil options:nil][0];
        cell.selectionStyle = UITableViewCellSelectionStyleNone;
    }
    HelpCenterContentModel *contentmodel = self.dataSourceArr[indexPath.row];
    cell.model = contentmodel;
    return cell;
}

- (CGFloat)tableView:(UITableView *)tableView heightForRowAtIndexPath:(NSIndexPath *)indexPath{
    return UITableViewAutomaticDimension;
}

- (CGFloat)tableView:(UITableView *)tableView heightForHeaderInSection:(NSInteger)section{
    return 10;
}

- (UIView *)tableView:(UITableView *)tableView viewForHeaderInSection:(NSInteger)section{
    UIView *view = [[UIView alloc] init];
    return view;
}

- (CGFloat)tableView:(UITableView *)tableView heightForFooterInSection:(NSInteger)section{
    return 10;
}

- (UIView *)tableView:(UITableView *)tableView viewForFooterInSection:(NSInteger)section{
    UIView *view = [[UIView alloc] init];
    return view;
}

- (void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath{
    HelpCenterContentModel *model = self.dataSourceArr[indexPath.row];

    HelpCenterDetailsViewController *detail = [[HelpCenterDetailsViewController alloc] init];
    detail.title = LocalizationKey(@"Article details");
    detail.contentModel = model;
    [[AppDelegate sharedAppDelegate] pushViewController:detail];
}


- (void)getData{
    [EasyShowLodingView showLodingText:[[ChangeLanguage bundle] localizedStringForKey:@"loading" value:nil table:@"English"]];
    NSString *url = [NSString stringWithFormat:@"%@%@",HOST, @"uc/ancillary/more/help/page"];
    NSDictionary *param = @{@"pageNo":[NSNumber numberWithInteger:_page], @"pageSize": @"10", @"cate":self.cate,@"lang":[ChangeLanguage networkLanguage]};
    [[XBRequest sharedInstance] postDataWithUrl:url Parameter:param ResponseObject:^(NSDictionary *responseResult) {
        [EasyShowLodingView hidenLoding];
        if ([self.tableView.mj_header isRefreshing]) {
            [self.tableView.mj_header endRefreshing];
        }
        _page = 1;
        NSLog(@"responseResult --- %@",responseResult);
        if (![responseResult objectForKey:@"resError"]) {
            NSDictionary *data = responseResult[@"data"];
            NSArray *conten = data[@"content"];
            [self.dataSourceArr removeAllObjects];
            if (conten.count > 0) {
                for (NSDictionary *dic in conten) {
                    HelpCenterContentModel *model = [HelpCenterContentModel modelWithDictionary:dic];
                    [self.dataSourceArr addObject:model];
                }
                [self.tableView reloadData];
            }
        }
    }];
}

- (void)getMoreData{
    _page ++;
    [EasyShowLodingView showLodingText:[[ChangeLanguage bundle] localizedStringForKey:@"loading" value:nil table:@"English"]];
    NSString *url = [NSString stringWithFormat:@"%@%@",HOST, @"uc/ancillary/more/help/page"];
    NSDictionary *param = @{@"pageNo":[NSNumber numberWithInteger:_page], @"pageSize": @"10", @"cate":self.cate};
    [[XBRequest sharedInstance] postDataWithUrl:url Parameter:param ResponseObject:^(NSDictionary *responseResult) {
        [EasyShowLodingView hidenLoding];
        if ([self.tableView.mj_footer isRefreshing]) {
            [self.tableView.mj_footer endRefreshing];
        }
        NSLog(@"responseResult --- %@",responseResult);
        if (![responseResult objectForKey:@"resError"]) {
            NSDictionary *data = responseResult[@"data"];
            NSNumber *totalPage = data[@"totalPage"];
            if (_page > [totalPage intValue]) {
                return;
            }
            NSArray *conten = data[@"content"];
            if (conten.count > 0) {
                for (NSDictionary *dic in conten) {
                    HelpCenterContentModel *model = [HelpCenterContentModel modelWithDictionary:dic];
                    [self.dataSourceArr addObject:model];
                }
                [self.tableView reloadData];
            }
        }else{
            _page = 1;
        }
    }];
}

- (UITableView *)tableView{
    if (!_tableView) {
        _tableView = [[UITableView alloc] initWithFrame:CGRectMake(0, 0, kWindowW, kWindowH - NEW_NavHeight) style:UITableViewStyleGrouped];
        _tableView.delegate = self;
        _tableView.dataSource = self;
        _tableView.backgroundColor = kRGBColor(18, 22, 28);
        _tableView.separatorStyle = UITableViewCellSeparatorStyleNone;
        _tableView.mj_header = [MJRefreshNormalHeader headerWithRefreshingBlock:^{
            [self getData];
        }];
        _tableView.mj_footer = [MJRefreshBackStateFooter footerWithRefreshingBlock:^{
            [self getMoreData];
        }];
    }
    return _tableView;
}

- (NSMutableArray *)dataSourceArr{
    if (!_dataSourceArr) {
        _dataSourceArr = [NSMutableArray arrayWithCapacity:0];
    }
    return _dataSourceArr;
}
@end
