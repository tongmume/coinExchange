//
//  WalletSelectCurrencyViewController.m
//  digitalCurrency
//
//  Created by ios on 2020/10/9.
//  Copyright © 2020 BIZZAN. All rights reserved.
//

#import "WalletSelectCurrencyViewController.h"
#import "MineNetManager.h"
#import "MentionCoinInfoModel.h"
#import "WalletSelectCurrencyTableViewCell.h"
#import "WalletManageModel.h"
#import "ChargeMoneyViewController.h"
#import "MentionMoneyViewController.h"
#import "WalletContractCoinModel.h"

@interface WalletSelectCurrencyViewController ()<UITableViewDelegate,UITableViewDataSource>

@property (nonatomic, strong) UITableView *myTableView;


@end

@implementation WalletSelectCurrencyViewController

-(NSMutableArray *)dataArray{
        
    if (!_dataArray) {
        _dataArray=[NSMutableArray array];
    }
    return _dataArray;
}

- (void)viewDidLoad {
    [super viewDidLoad];
    // Do any additional setup after loading the view.
    if (_type==2) {
        self.navigationItem.title=LocalizationKey(@"tv_select_account");
    }else{
        self.navigationItem.title=LocalizationKey(@"selectCoinType");
    }
    
    if(self.type == 0) {
        NSMutableArray *arr= [NSMutableArray array];
        for ( WalletManageModel *model in  self.dataArray) {
            if ([model.coin.canRecharge isEqualToString:@"1"]) {
                [arr addObject:model];
            }
        }
        self.dataArray = arr;
    } else if (self.type == 1) {
        NSMutableArray *arr= [NSMutableArray array];
        for ( WalletManageModel *model in  self.dataArray) {
            if ([model.coin.canWithdraw isEqualToString:@"1"]) {
                [arr addObject:model];
            }
        }
        self.dataArray = arr;
    }
  
    self.view.backgroundColor=mainColor;
    
     _myTableView=[[UITableView alloc]init];
        [_myTableView  setSeparatorStyle:UITableViewCellSeparatorStyleNone];
           _myTableView.delegate=self;
           _myTableView.dataSource=self;
    _myTableView.backgroundColor = mainColor;
        _myTableView.showsVerticalScrollIndicator=NO;
        _myTableView.alwaysBounceVertical=YES;
           if (@available(iOS 11.0, *)) {
    
                      _myTableView.estimatedRowHeight = 0;
    
                      _myTableView.estimatedSectionFooterHeight = 0;
    
                      _myTableView.estimatedSectionHeaderHeight=0;
                   _myTableView.contentInsetAdjustmentBehavior= UIScrollViewContentInsetAdjustmentNever;
           }
       
        [self.view addSubview:_myTableView];
        [_myTableView mas_makeConstraints:^(MASConstraintMaker *make) {
            make.left.right.mas_equalTo(0);
            make.top.mas_equalTo(0);
            make.bottom.mas_equalTo(-SafeAreaBottomHeight);
        }];
    
    [self getCoinList];
}

-(CGFloat)tableView:(UITableView *)tableView heightForRowAtIndexPath:(NSIndexPath *)indexPath{
 
    return 50;
}

-(NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section{
    
    return self.dataArray.count;
}

- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath
{
    

    WalletSelectCurrencyTableViewCell *cell= [tableView dequeueReusableCellWithIdentifier:@"cell"];
    
    if (!cell) {
        cell=[[WalletSelectCurrencyTableViewCell alloc]initWithStyle:UITableViewCellStyleDefault reuseIdentifier:@"cell"];
        cell.selectionStyle=UITableViewCellSelectionStyleNone;
    }
    
    
    if (self.dataArray.count!=0&&indexPath.row<self.dataArray.count) {
        if (_type==2) {
            
            WalletContractCoinModel *model= self.dataArray[indexPath.row];
            cell.mtitlelabel.text=[NSString stringWithFormat:@"%@ %@%@",model.contractCoin.symbol,model.contractCoin.name,LocalizationKey(@"account")];
        }else{
        
            WalletManageModel *model= self.dataArray[indexPath.row];
            cell.mtitlelabel.text=model.coin.unit;
        }
    }
    
    
    return cell;
}

-(void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath{
   
    if (_type==2) {
        
        WalletContractCoinModel * model= self.dataArray[indexPath.row];
        if (self.selectCellItemBlock) {
            self.selectCellItemBlock(_type, model);
        }
        [self.navigationController popViewControllerAnimated:YES];
    }else if (_type==1) {
        
        WalletManageModel *model= self.dataArray[indexPath.row];
        if ([model.coin.canWithdraw isEqualToString:@"1"]) {
                  MentionMoneyViewController *mentionVC = [[MentionMoneyViewController alloc] init];
                                 mentionVC.unit = model.coin.unit;
                                 [self.navigationController pushViewController:mentionVC animated:YES];
        }else
               [self.view makeToast:LocalizationKey(@"notMentionMoneyTip") duration:1.5 position:CSToastPositionCenter];
        
    }else{
         WalletManageModel *model= self.dataArray[indexPath.row];
        if ([model.coin.canRecharge isEqualToString:@"1"]) {
            
                              ChargeMoneyViewController *chargeVC = [[ChargeMoneyViewController alloc] init];
                               chargeVC.unit = model.coin.unit;
                               chargeVC.address = model.address;
                               chargeVC.accountType = model.coin.accountType;
                               chargeVC.model = model;
                               chargeVC.hidesBottomBarWhenPushed = YES;
                               [self.navigationController pushViewController:chargeVC animated:YES];
            
        }else
            [self.view makeToast:LocalizationKey(@"notChargeMoneyTip") duration:1.5 position:CSToastPositionCenter];
        
    }
    
    
}

- (void)getCoinList {
    
//    [EasyShowLodingView showLodingText:LocalizationKey(@"loading")];
//    [MineNetManager mentionCoinInfoForCompleteHandle:^(id resPonseObj, int code) {
//    [EasyShowLodingView hidenLoding];
//        if (code) {
//                   if ([resPonseObj[@"code"] integerValue] == 0) {
//                       // [self.walletManageArr removeAllObjects];
//                       NSArray *dataArr = [MentionCoinInfoModel mj_objectArrayWithKeyValuesArray:resPonseObj[@"data"]];
//                       [self.dataArray addObjectsFromArray:dataArr];
//                       [self.myTableView reloadData];
//                   }else{
//                       [self.view makeToast:resPonseObj[MESSAGE] duration:1.5 position:CSToastPositionCenter];
//                   }
//               }else{
//                   [self.view makeToast:LocalizationKey(@"noNetworkStatus") duration:1.5 position:CSToastPositionCenter];
//               }
//
//
//    }];
    
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
