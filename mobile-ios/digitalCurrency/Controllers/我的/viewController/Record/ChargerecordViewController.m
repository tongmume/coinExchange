//
//  ChargerecordViewController.m
//  digitalCurrency
//
//  Created by startlink on 2019/8/7.
//  Copyright © 2019年 BIZZAN. All rights reserved.
//

#import "ChargerecordViewController.h"
#import "ChargerecordTableViewCell.h"
#import "MineNetManager.h"
#import "TradeHistoryModel.h"
@interface ChargerecordViewController ()<UITableViewDelegate,UITableViewDataSource>

@property (weak, nonatomic) IBOutlet UITableView *tableView;
@property (weak, nonatomic) IBOutlet NSLayoutConstraint *bottomhieght;
@property (nonatomic, strong) LYEmptyView *emptyView;
@property (nonatomic,strong)NSMutableArray *dataarray;
@property(nonatomic,assign)NSInteger pageNo;
@property(nonatomic,strong)NSMutableArray *mentionCoinArr;

@end

@implementation ChargerecordViewController
-(NSMutableArray *)dataarray{
    if (!_dataarray) {
        _dataarray = [NSMutableArray array];
    }
    return _dataarray;
}
- (void)viewDidLoad {
    [super viewDidLoad];
    // Do any additional setup after loading the view from its nib.
    self.view.backgroundColor = kRGBColor(18, 22, 28);
    self.bottomhieght.constant = SafeAreaBottomHeight;
//    [self RightsetupNavgationItemWithpictureName:@"zicanliushui"];
    self.title = LocalizationKey(@"Chargerecord");

    self.tableView.delegate = self;
    self.tableView.dataSource = self;
    self.tableView.separatorStyle = UITableViewCellSeparatorStyleNone;
    [self.tableView registerNib:[UINib nibWithNibName:@"ChargerecordTableViewCell" bundle:nil] forCellReuseIdentifier:@"ChargerecordTableViewCell"];
    self.pageNo = 1;

    [self headRefreshWithScrollerView:_tableView];
    [self footRefreshWithScrollerView:_tableView];

    [self getadata];
}
//MARK:--上拉加载
- (void)refreshFooterAction{
    self.pageNo++;
    [self getadata];
}
//MARK:--下拉刷新
- (void)refreshHeaderAction{
    self.pageNo = 1;
    [self getadata];
}
-(void)RighttouchEvent{


}


-(void)getadata{

    [EasyShowLodingView showLodingText:[[ChangeLanguage bundle] localizedStringForKey:@"loading" value:nil table:@"English"]];
    NSString *pageNoStr = [[NSString alloc] initWithFormat:@"%ld",(long)_pageNo];
    NSMutableDictionary *bodydic = [NSMutableDictionary dictionary];
    [bodydic setValue:pageNoStr forKey:@"pageNo"];
    [bodydic setValue:@"10" forKey:@"pageSize"];
    [bodydic setValue:[YLUserInfo shareUserInfo].ID forKey:@"memberId"];
    [bodydic setValue:@"0" forKey:@"type"];
    [MineNetManager assettransactionParam:bodydic CompleteHandle:^(id resPonseObj, int code) {
        [EasyShowLodingView hidenLoding];
        if (code) {
            if ([resPonseObj[@"code"] integerValue]==0) {
                //获取数据成功
                if (_pageNo == 1) {
                    [_dataarray removeAllObjects];
                }

                NSArray *dataArr = [assetsourcemodel mj_objectArrayWithKeyValuesArray:resPonseObj[@"data"][@"content"]];
                [self.dataarray addObjectsFromArray:dataArr];
                self.tableView.ly_emptyView = self.emptyView;
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
- (LYEmptyView *)emptyView{
    if (!_emptyView) {
        _emptyView = [LYEmptyView emptyViewWithImageStr:@"emptyData" titleStr:[[ChangeLanguage bundle] localizedStringForKey:@"noDada" value:nil table:@"English"]];
    }
    return _emptyView;
}
-(NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section{
        return self.dataarray.count;

}

- (CGFloat)tableView:(UITableView *)tableView heightForRowAtIndexPath:(NSIndexPath *)indexPath{
    return 130;
}

- (CGFloat)tableView:(UITableView *)tableView heightForHeaderInSection:(NSInteger)section{

    return 0.01;

}

- (CGFloat)tableView:(UITableView *)tableView heightForFooterInSection:(NSInteger)section{
    return 0.01;
}

- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath{
     ChargerecordTableViewCell *cell = [tableView dequeueReusableCellWithIdentifier:@"ChargerecordTableViewCell" forIndexPath:indexPath];
    assetsourcemodel *model = self.dataarray[indexPath.row];
    cell.typelabel.text = model.symbol;
    cell.timelabel.text = model.createTime;
    cell.addresslabel.text = model.address;
    cell.numlabel.text = [ToolUtil judgeStringForDecimalPlaces:model.amount];
    return cell;

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
