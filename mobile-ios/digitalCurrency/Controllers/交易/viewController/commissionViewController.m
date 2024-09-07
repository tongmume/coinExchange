//
//  commissionViewController.m
//  digitalCurrency
//
//  Created by sunliang on 2019/1/30.
//  Copyright © 2019年 BIZZAN. All rights reserved.
//

#import "commissionViewController.h"
#import "TradeNetManager.h"
#import "commissionModel.h"
#import "marketManager.h"
#import "sectionHeaderView.h"
#import "HistoryTransactionEntrustViewController.h"
#import "MyEntrustTableViewCell.h"

@interface commissionViewController ()
{
    int _page;
}
@property (nonatomic,strong)NSMutableArray *contentArr;
@property (nonatomic, strong) NSMutableDictionary *muDic;
@end

@implementation commissionViewController

- (NSMutableDictionary *)muDic{
    if (!_muDic) {
        _muDic = [NSMutableDictionary dictionaryWithCapacity:0];
    }
    return _muDic;
}

- (void)viewDidLoad {
    [super viewDidLoad];
    [[NSNotificationCenter defaultCenter] addObserver:self selector:@selector(shanxuan:) name:@"Querythecurrent" object:nil];

    _page = 1;
    [self setTablewViewHeard];
    LYEmptyView*emptyView=[LYEmptyView emptyViewWithImageStr:@"emptyData" titleStr:LocalizationKey(@"nocurrentData")];
    self.tableView.ly_emptyView = emptyView;
    [self getCommissionData:@{@"pageNo":[NSNumber numberWithInt:_page], @"pageSize":@"10"}];
    [self headRefreshWithScrollerView:self.tableView];
    [self footRefreshWithScrollerView:self.tableView];

    if (@available(iOS 11.0, *)) {

        _tableView.estimatedRowHeight = 0;

        _tableView.estimatedSectionFooterHeight = 0;

        _tableView.estimatedSectionHeaderHeight=0;  _tableView.contentInsetAdjustmentBehavior= UIScrollViewContentInsetAdjustmentNever;

    }
}

- (void)shanxuan:(NSNotification *)info{
    NSLog(@"info --- %@",info.object);
    _page = 1;
    [self.contentArr removeAllObjects];
    [self.muDic removeAllObjects];
    [self.muDic setDictionary:info.object];
    [self getCommissionData:self.muDic];
}

-(void)setTablewViewHeard{
    [self.tableView registerNib:[UINib nibWithNibName:@"MyEntrustTableViewCell" bundle:nil] forCellReuseIdentifier:@"Cell"];
    self.tableView.rowHeight=175;
    self.tableView.tableFooterView=[UIView new];
}

#pragma mark-查询委托历史
-(void)RighttouchEvent{
    HistoryTransactionEntrustViewController *historyVC = [[HistoryTransactionEntrustViewController alloc] init];
    historyVC.hidesBottomBarWhenPushed = YES;
    historyVC.symbol = [marketManager shareInstance].symbol;
    [self.navigationController pushViewController:historyVC animated:YES];
}
#pragma mark-上拉加载更多
- (void)refreshFooterAction{
    _page ++;
    if (self.muDic.count > 0) {
        [self.muDic setObject:@"pageNo" forKey:[NSNumber numberWithInt:_page]];
        [self getCommissionData:self.muDic];

    }else{
        [self getCommissionData:@{@"pageNo":[NSNumber numberWithInt:_page], @"pageSize":@"10"}];

    }
}
#pragma mark-下拉刷新
- (void)refreshHeaderAction{
    _page = 1;
    [self.contentArr removeAllObjects];
    if (self.muDic.count > 0) {
        [self getCommissionData:self.muDic];

    }else{
        [self getCommissionData:@{@"pageNo":[NSNumber numberWithInt:_page], @"pageSize":@"10"}];

    }
}
-(void)getCommissionData:(NSDictionary *)param{
    [EasyShowLodingView showLodingText:LocalizationKey(@"loading")];

    [TradeNetManager QuerythecurrentdelegateParam:param CompleteHandle:^(id resPonseObj, int code) {
        NSLog(@"resPonseObj ---- %@",resPonseObj);
        [EasyShowLodingView hidenLoding];
        if (code) {
            if ([resPonseObj[@"code"] intValue]==0) {
                NSArray*contentArray=resPonseObj[@"content"];
                if (contentArray.count==0) {
                    [self.tableView reloadData];
                    return ;
                }
                for (int i=0; i<contentArray.count; i++) {
                    commissionModel*model = [commissionModel mj_objectWithKeyValues:contentArray[i]];
                    [self.contentArr addObject:model];
                }
                [self.tableView reloadData];

            }else if ([resPonseObj[@"code"] intValue]==4000){
                [YLUserInfo logout];
            }
            else{
                [self.view makeToast:resPonseObj[MESSAGE] duration:1.5 position:CSToastPositionCenter];
            }
        }else{
            [self.view makeToast:LocalizationKey(@"noNetworkStatus") duration:1.5 position:CSToastPositionCenter];
        }
    }];
}

-(NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section{

    return self.contentArr.count;
}


- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath
{
    MyEntrustTableViewCell * cell = [tableView dequeueReusableCellWithIdentifier:@"Cell" forIndexPath:indexPath];
    if (!cell) {
        cell = [[NSBundle mainBundle] loadNibNamed:@"MyEntrustTableViewCell" owner:nil options:nil][0];
    }
    cell.selectionStyle = UITableViewCellSelectionStyleNone;

    cell.model = self.contentArr[indexPath.row];
    __weak typeof(self)weakself = self;
    cell.entrustBlock = ^{
        commissionModel*model=weakself.contentArr[indexPath.row];
        NSString*price;//委托价
        NSString*commissionAmount;//委托量
        NSString*commissionTotal;//委托总额
        if ([model.type isEqualToString:@"LIMIT_PRICE"]) {


            price=[NSString stringWithFormat:@"%@%@",model.price,model.baseSymbol];
            commissionAmount=[NSString stringWithFormat:@"%@%@",model.amount,model.coinSymbol];

            NSDecimalNumber *price = [[NSDecimalNumber alloc] initWithString:model.price];
            NSDecimalNumber *tradedAmount = [[NSDecimalNumber alloc] initWithString:model.amount];
            commissionTotal=[NSString stringWithFormat:@"%@%@",[[price decimalNumberByMultiplyingBy:tradedAmount] stringValue],model.baseSymbol];
        }else{
            price=LocalizationKey(@"marketPrice");
            if ([model.direction isEqualToString:@"SELL"]) {
                commissionAmount=[NSString stringWithFormat:@"%.2f%@",[model.amount doubleValue],model.coinSymbol];
                commissionTotal=[NSString stringWithFormat:@"%@%@",@"--",model.baseSymbol];
            }else{
                commissionAmount=[NSString stringWithFormat:@"%@%@",@"--",model.coinSymbol];
                commissionTotal=[NSString stringWithFormat:@"%.2f%@",[model.amount doubleValue],model.baseSymbol];
            }
        }
        EasyShowAlertView *showView =[EasyShowAlertView showActionSheetWithTitle:LocalizationKey(@"Confirmation") left1message:price right1message:commissionAmount left2message:commissionTotal right2message:[model.direction isEqualToString:@"BUY"]==YES?LocalizationKey(@"Buy"):LocalizationKey(@"Sell")];
        [showView addItemWithTitle:LocalizationKey(@"Cancelorder") itemType:ShowAlertItemTypeBlack callback:^(EasyShowAlertView *showview) {
            [weakself cancelCommissionwithOrderID:model.orderId];//撤单
        }];
        [showView addItemWithTitle:LocalizationKey(@"cancel") itemType:ShowAlertItemTypeBlack callback:^(EasyShowAlertView *showview) {
        }];
        [showView show];
    };
    return cell;
}
- (CGFloat)tableView:(UITableView *)tableView heightForHeaderInSection:(NSInteger)section
{
    return 0.0001f;

}
-(UIView*)tableView:(UITableView *)tableView viewForHeaderInSection:(NSInteger)section{
    UIView *view = [[UIView alloc] init];
    return view;

}
#pragma mark-撤销按钮
-(void)withDraw:(UIButton*)sender{
    commissionModel*model=self.contentArr[sender.tag];
    __weak commissionViewController*weakSelf=self;
    NSString*price;//委托价
    NSString*commissionAmount;//委托量
    NSString*commissionTotal;//委托总额
    if ([model.type isEqualToString:@"LIMIT_PRICE"]) {
        price=[NSString stringWithFormat:@"%.2f%@",[model.price doubleValue],model.baseSymbol];
        commissionAmount=[NSString stringWithFormat:@"%.2f%@",[model.amount doubleValue],model.coinSymbol];
        commissionTotal=[NSString stringWithFormat:@"%.2f%@",[model.price doubleValue]*[model.amount doubleValue],model.baseSymbol];
    }else{
        price=LocalizationKey(@"marketPrice");
        if ([model.direction isEqualToString:@"SELL"]) {
            commissionAmount=[NSString stringWithFormat:@"%.2f%@",[model.amount doubleValue],model.coinSymbol];
            commissionTotal=[NSString stringWithFormat:@"%@%@",@"--",model.baseSymbol];
        }else{
            commissionAmount=[NSString stringWithFormat:@"%@%@",@"--",model.coinSymbol];
            commissionTotal=[NSString stringWithFormat:@"%.2f%@",[model.amount doubleValue],model.coinSymbol];
        }
    }
    EasyShowAlertView *showView =[EasyShowAlertView showActionSheetWithTitle:LocalizationKey(@"Confirmation") left1message:price right1message:commissionAmount left2message:commissionTotal right2message:[model.direction isEqualToString:@"BUY"]==YES?LocalizationKey(@"buyDirection"):LocalizationKey(@"sellDirection")];
    [showView addItemWithTitle:LocalizationKey(@"Cancelorder") itemType:ShowAlertItemTypeBlack callback:^(EasyShowAlertView *showview) {
        [weakSelf cancelCommissionwithOrderID:model.orderId];//撤单
    }];
    [showView addItemWithTitle:LocalizationKey(@"cancel") itemType:ShowAlertItemTypeBlack callback:^(EasyShowAlertView *showview) {
    }];
    [showView show];
}

#pragma mark-撤单
-(void)cancelCommissionwithOrderID:(NSString*)orderId{
     [EasyShowLodingView showLodingText:LocalizationKey(@"loading")];
    [TradeNetManager cancelCommissionwithOrderID:orderId CompleteHandle:^(id resPonseObj, int code) {
        if (code) {
            [EasyShowLodingView hidenLoding];
            if ([resPonseObj[@"code"] integerValue] == 0) {
             [self.view makeToast:@"撤销成功" duration:1.5 position:CSToastPositionCenter];
                [self.contentArr enumerateObjectsUsingBlock:^(commissionModel* obj, NSUInteger idx, BOOL * _Nonnull stop) {
                    if ([obj.orderId isEqualToString:orderId]) {
                        *stop = YES;
                        [self.contentArr removeObject:obj];
                        [self.tableView reloadData];
                    }
                }];

            }else{
                [self.view makeToast:resPonseObj[MESSAGE] duration:1.5 position:CSToastPositionCenter];
            }
        }else{
            [self.view makeToast:LocalizationKey(@"noNetworkStatus") duration:1.5 position:CSToastPositionCenter];
        }

    }];
}
#pragma mark-刷新数据
-(void)reloadShowData:(NSNotification *)text{
    _page = 1;
    [self.contentArr removeAllObjects];
    [self getCommissionData:[NSString stringWithFormat:@"%@/%@",text.userInfo[@"object"],text.userInfo[@"base"]]];
}
#pragma mark-每次滑动
-(void)reloadNewData{
    _page = 1;
    [self.contentArr removeAllObjects];
}
- (NSMutableArray *)contentArr
{
    if (!_contentArr) {
        _contentArr = [NSMutableArray array];
    }
    return _contentArr;
}
kRemoveCellSeparator
- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}
-(void)dealloc{
    [[NSNotificationCenter defaultCenter] removeObserver:self];
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
