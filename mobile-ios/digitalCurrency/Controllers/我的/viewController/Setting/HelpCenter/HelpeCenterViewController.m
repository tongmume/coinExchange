//
//  HelpeCenterViewController.m
//  digitalCurrency
//
//  Created by chu on 2019/8/6.
//  Copyright © 2019年 BIZZAN. All rights reserved.
//

#import "HelpeCenterViewController.h"
#import "HelpCenterTableViewCell.h"
#import "HelpCenterModel.h"
#import "HelpCenterMoreViewController.h"
#import "HelpCenterDetailsViewController.h"
@interface HelpeCenterViewController ()<UITableViewDelegate, UITableViewDataSource>

@property (nonatomic, strong) UITableView *tableView;

@property (nonatomic, strong) UIView *headerView;

@property (nonatomic, strong) NSMutableArray *dataSourceArr;

@end

@implementation HelpeCenterViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    self.title = [[ChangeLanguage bundle] localizedStringForKey:@"Helpcenter" value:nil table:@"English"];
    [self.view addSubview:self.tableView];
    self.tableView.estimatedRowHeight = 44.0;
    self.tableView.rowHeight = UITableViewAutomaticDimension;
    [self getData];
}

- (NSInteger)numberOfSectionsInTableView:(UITableView *)tableView{
    return self.dataSourceArr.count;
}

- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section{
    HelpCenterModel *model = self.dataSourceArr[section];
    return model.content.count;
}

- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath{
    static  NSString *identifier = @"helpCell";
    HelpCenterTableViewCell *cell = [tableView dequeueReusableCellWithIdentifier:identifier];
    if (!cell) {
        cell = [[NSBundle mainBundle] loadNibNamed:@"HelpCenterTableViewCell" owner:nil options:nil][0];
        cell.selectionStyle = UITableViewCellSelectionStyleNone;
    }
    HelpCenterModel *model = self.dataSourceArr[indexPath.section];
    cell.model = model.content[indexPath.row];
    return cell;
}


- (CGFloat)tableView:(UITableView *)tableView heightForRowAtIndexPath:(NSIndexPath *)indexPath{
    return UITableViewAutomaticDimension;
}

- (CGFloat)tableView:(UITableView *)tableView heightForHeaderInSection:(NSInteger)section{
    return 48;
}

- (UIView *)tableView:(UITableView *)tableView viewForHeaderInSection:(NSInteger)section{
    HelpCenterModel *model = self.dataSourceArr[section];
    UIView *view = [[UIView alloc] init];
    if (section == 1) {
        view.frame = CGRectMake(0, 0, kWindowW, 38);
    }
    UILabel *label = [[UILabel alloc] init];
//    label.text = @[[[ChangeLanguage bundle] localizedStringForKey:@"Noviceguide" value:nil table:@"English"], [[ChangeLanguage bundle] localizedStringForKey:@"Commonproblem" value:nil table:@"English"]][section];
    
    label.text = [[ChangeLanguage userLanguage] isEqual:@"zh-Hans"] ? model.titleCN : model.titleEN;
    label.textColor = RGBOF(0xe6e6e6);
    label.font = [UIFont fontWithName:@"PingFangSC-Heavy" size:17];
    [view addSubview:label];

    UIButton *btn = [UIButton buttonWithType:UIButtonTypeCustom];
    [btn setTitle:[[ChangeLanguage bundle] localizedStringForKey:@"helpMore" value:nil table:@"English"] forState:UIControlStateNormal];
    [btn setBackgroundColor:RGBOF(0xF0A70A)];
    [btn setTitleColor:RGBOF(0xe6e6e6) forState:UIControlStateNormal];
    btn.titleLabel.font = [UIFont fontWithName:@"PingFangSC-Medium" size:13];
    [btn addTarget:self action:@selector(moreAction:) forControlEvents:UIControlEventTouchUpInside];
    btn.layer.cornerRadius = 13;
    btn.layer.borderColor = RGBOF(0xF0A70A).CGColor;
    btn.layer.borderWidth = 1;
    btn.tag = section;
    [view addSubview:btn];

    [label mas_makeConstraints:^(MASConstraintMaker *make) {
        make.left.equalTo(view).with.offset(16);
        make.centerY.mas_equalTo(view);
        make.height.mas_equalTo(18);
    }];

    [btn mas_makeConstraints:^(MASConstraintMaker *make) {
        make.right.equalTo(view).with.offset(-16);
        make.centerY.mas_equalTo(view);
        make.height.mas_equalTo(26);
        make.width.mas_equalTo(56);
    }];

    return view;
}

- (CGFloat)tableView:(UITableView *)tableView heightForFooterInSection:(NSInteger)section{
    return 0.0001f;
}

- (UIView *)tableView:(UITableView *)tableView viewForFooterInSection:(NSInteger)section{
    UIView *view = [[UIView alloc] init];
    return view;
}

- (void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath{
    HelpCenterModel *model = self.dataSourceArr[indexPath.section];

    HelpCenterDetailsViewController *detail = [[HelpCenterDetailsViewController alloc] init];
    detail.title = LocalizationKey(@"Article details");
    detail.contentModel = model.content[indexPath.row];
    [[AppDelegate sharedAppDelegate] pushViewController:detail];
    
}

- (void)moreAction:(UIButton *)sender{
    HelpCenterModel *model = self.dataSourceArr[sender.tag];
    HelpCenterMoreViewController *more = [[HelpCenterMoreViewController alloc] init];
    more.cate = model.cate;
    more.title = model.title;
    [[AppDelegate sharedAppDelegate] pushViewController:more];
}

- (void)getData{
    [EasyShowLodingView showLodingText:[[ChangeLanguage bundle] localizedStringForKey:@"loading" value:nil table:@"English"]];
    NSString *url = [NSString stringWithFormat:@"%@%@",HOST, @"uc/ancillary/more/help/"];
    NSDictionary *param = @{@"total":@"3",@"lang":[ChangeLanguage networkLanguage]};
    [[XBRequest sharedInstance] postDataWithUrl:url Parameter:param ResponseObject:^(NSDictionary *responseResult) {
        [EasyShowLodingView hidenLoding];
        NSLog(@"responseResult --- %@",responseResult);
        if (![responseResult objectForKey:@"resError"]) {
            NSArray *data = responseResult[@"data"];
            if (data.count > 0) {
                for (NSDictionary *dic in data) {
                    HelpCenterModel *model = [HelpCenterModel modelWithDictionary:dic];
                    [self.dataSourceArr addObject:model];
                }
                [self.tableView reloadData];
            }
        }
    }];
}

- (UITableView *)tableView{
    if (!_tableView) {
        _tableView = [[UITableView alloc] initWithFrame:CGRectMake(0, 0, kWindowW, kWindowH - NEW_NavHeight) style:UITableViewStyleGrouped];
        _tableView.delegate = self;
        _tableView.dataSource = self;
        _tableView.backgroundColor = mainColor;
        _tableView.separatorStyle = UITableViewCellSeparatorStyleNone;
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
