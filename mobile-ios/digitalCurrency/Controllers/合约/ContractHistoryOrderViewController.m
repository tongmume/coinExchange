//
//  ContractHistoryOrderViewController.m
//  digitalCurrency
//
//  Created by ios on 2020/9/24.
//  Copyright © 2020 BIZZAN. All rights reserved.
//

#import "ContractHistoryOrderViewController.h"
#import "ContractHistoryTableViewCell.h"
#import "ContractHistoryDetailViewController.h"
#import "ContractExchangeManager.h"
#import "ConatractCurrentEntrustModel.h"

@interface ContractHistoryOrderViewController ()<UITableViewDelegate,UITableViewDataSource>

@property (nonatomic, strong) UITableView *mytableView;

@property (nonatomic, strong) UILabel *noDatalabel;

@property (nonatomic, strong) NSMutableArray *dataArray;

@property (nonatomic, assign) NSInteger pageNo;

@property (nonatomic, strong) NSDateFormatter *formatter;

@end

@implementation ContractHistoryOrderViewController

- (NSMutableArray *)dataArray{
    
    if (!_dataArray) {
        _dataArray=[NSMutableArray array];
    }
    return _dataArray;
}

- (void)viewDidLoad {
    [super viewDidLoad];
    // Do any additional setup after loading the view.
    _formatter = [NSDateFormatter new];
    _formatter.dateFormat = @"MM-dd HH:mm";
    
    _pageNo=1;
    self.navigationItem.title=LocalizationKey(@"HistoricalCurrent");
    self.view.backgroundColor=mainColor;
    UITableView *tabelev=[[UITableView alloc]init];
    [tabelev  setSeparatorStyle:UITableViewCellSeparatorStyleNone];
    tabelev.backgroundColor = ViewBackgroundColor;
    tabelev.delegate=self;
    tabelev.dataSource=self;
    
    [self.view addSubview:tabelev];
    if (@available(iOS 11.0, *)) {

        tabelev.estimatedRowHeight = 0;

        tabelev.estimatedSectionFooterHeight = 0;

        tabelev.estimatedSectionHeaderHeight=0;
        tabelev.contentInsetAdjustmentBehavior= UIScrollViewContentInsetAdjustmentNever;
    }
    _mytableView=tabelev;
    [self footRefreshWithScrollerView:tabelev];
    [self headRefreshWithScrollerView:tabelev];
    [_mytableView mas_makeConstraints:^(MASConstraintMaker *make) {
        make.left.right.bottom.mas_equalTo(0);
        make.top.mas_equalTo(1);
    }];
    
    
    [self getHistoryListContractCoinid:_contractCoinId PageNo:_pageNo isShowLoading:YES];
}

//MARK:--上拉加载
- (void)refreshFooterAction{
    [self.mytableView.mj_footer resetNoMoreData];;

    self.pageNo++;
    if (_contractCoinId) {
       
        [self getHistoryListContractCoinid:_contractCoinId PageNo:_pageNo isShowLoading:NO];
    }
    
}
//MARK:--下拉刷新
- (void)refreshHeaderAction{
    self.pageNo = 1;
    if (_contractCoinId) {
        [self getHistoryListContractCoinid:_contractCoinId PageNo:_pageNo isShowLoading:NO];
    }
}


#pragma mark - UITableViewDelegate,UITableViewDataSource
-(NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section{
    
    
    return _dataArray.count;
}

- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath
{
    ContractHistoryTableViewCell *cell = [tableView dequeueReusableCellWithIdentifier:@"Cell"];
               if (!cell) {
                   cell = [[ContractHistoryTableViewCell alloc] initWithStyle:UITableViewCellStyleDefault reuseIdentifier:@"Cell"];
                           cell.selectionStyle=UITableViewCellSelectionStyleNone;
               }
    
    if (self.dataArray.count!=0&&indexPath.row<self.dataArray.count) {
        
        ConatractCurrentEntrustModel *model= self.dataArray[indexPath.row];
            
        if ([model.entrustType isEqualToString:@"OPEN"]) {
                               
            if ([model.direction isEqualToString:@"BUY"]) {
                cell.mtitlelabel.textColor=GreenColor;
                cell.mtitlelabel.text= LocalizationKey(@"buyOpenmore");
            }else{
                cell.mtitlelabel.text=LocalizationKey(@"sellOpennull");
                cell.mtitlelabel.textColor=RedColor;
            }
            
        }else{
            if ([model.direction isEqualToString:@"BUY"]) {
                cell.mtitlelabel.textColor=GreenColor;
                cell.mtitlelabel.text= LocalizationKey(@"buyflatnull");
            }else{
                cell.mtitlelabel.text=LocalizationKey(@"sellflatmore");
                cell.mtitlelabel.textColor=RedColor;
            }
        }
        CGSize maxsize = [cell.mtitlelabel sizeThatFits:CGSizeMake(MAXFLOAT,20)];
        [cell.mtitlelabel mas_updateConstraints:^(MASConstraintMaker *make) {
            make.width.mas_equalTo(maxsize.width+5);
        }];
        //日期
        NSDate *date = [NSDate dateWithTimeIntervalSince1970:model.createTime.integerValue/1000];
        NSString *dateStr = [_formatter stringFromDate:date];
        cell.timelabel.text=dateStr;
        cell.entrustNumberlabel.text=[NSString stringWithFormat:@"%@%@",model.volume,LocalizationKey(@"zhang")];
        cell.entrustPricelabel.text=[ToolUtil stringFromNumber:model.entrustPrice.doubleValue withlimit:2];
        cell.dealPricelabel.text=[ToolUtil stringFromNumber:model.tradedPrice.doubleValue withlimit:2];
        
        if (model.profitAndLoss.doubleValue==0) {
            cell.profitlabel.text=@"--";
            cell.profitlabel.textColor=AppTextColor_Level_2;
        }else if (model.profitAndLoss.doubleValue>0){
            cell.profitlabel.text=[ToolUtil stringFromNumber:model.profitAndLoss.doubleValue withlimit:6];
            cell.profitlabel.textColor=GreenColor;
        }else{
            cell.profitlabel.text=[ToolUtil stringFromNumber:model.profitAndLoss.doubleValue withlimit:6];
            cell.profitlabel.textColor=RedColor;
        }
        
        
    }
               
        return cell;
}

- (CGFloat)tableView:(UITableView *)tableView heightForRowAtIndexPath:(NSIndexPath*)indexPath{
    return 90;
}

-(void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath{
    ConatractCurrentEntrustModel *model= self.dataArray[indexPath.row];
    ContractHistoryDetailViewController *contrcthdeatailVC=[[ContractHistoryDetailViewController alloc]init];
    contrcthdeatailVC.model=model;
    [self.navigationController pushViewController:contrcthdeatailVC animated:YES];
}


- (void)getHistoryListContractCoinid:(NSString *)coinid PageNo:(NSInteger)pageNo isShowLoading:(BOOL)isshow {
    
    NSMutableDictionary *mdict=[NSMutableDictionary dictionary];
    
    if (coinid) {
            [mdict setObject:coinid forKey:@"contractCoinId"];
    }
    [mdict setObject:@(pageNo) forKey:@"pageNo"];
    [mdict setObject:@(10) forKey:@"pageSize"];
    if (_pageNo==1) {
        if (self.dataArray.count!=0) {
            [self.dataArray removeAllObjects];
        }
    }
    if (isshow) {
            [EasyShowLodingView showLodingText:LocalizationKey(@"loading")];
    }
    [ContractExchangeManager getHistoryContractParam:mdict CompleteHandle:^(id  _Nonnull resPonseObj, int code) {
        if (isshow) {
            [EasyShowLodingView hidenLoding];
        }
        
        if ([self.mytableView.mj_footer isRefreshing]) {
                   [self.mytableView.mj_footer endRefreshing];
               }
               if ([self.mytableView.mj_header isRefreshing]) {
                   [self.mytableView.mj_header endRefreshing];
               }
        if (code) {
                   if ([resPonseObj[@"code"] intValue] == 0) {
                       NSArray *data= [ConatractCurrentEntrustModel mj_objectArrayWithKeyValuesArray:resPonseObj[@"content"]];
                       
                       if (data.count==0) {
                           [self.mytableView.mj_footer endRefreshingWithNoMoreData];
                       }else
                        [self.dataArray addObjectsFromArray:data];
                                         
                       [self.mytableView reloadData];
                       [self loadNoData];
                   }
                               
                   else if ([resPonseObj[@"code"] integerValue] ==4000){

                       [YLUserInfo logout];
                   }
                   else{
                       [self.view makeToast:resPonseObj[MESSAGE] duration:1.5 position:CSToastPositionCenter];
                   }
               }
               else{
                   [self.view makeToast:LocalizationKey(@"noNetworkStatus") duration:1.5 position:CSToastPositionCenter];
               }
        
    }];
    
    
}
- (void)loadNoData {
    
    if (self.dataArray.count==0) {
        self.mytableView.hidden=YES;
        self.noDatalabel.hidden=NO;
    }else{
        self.mytableView.hidden=NO;
        self.noDatalabel.hidden=YES;
    }
    
}

-(UILabel *)noDatalabel {
    
    if (!_noDatalabel) {
        _noDatalabel=[[UILabel alloc]init];
        _noDatalabel.textAlignment=NSTextAlignmentCenter;
       // _noDatalabel.backgroundColor=mainColor;
        _noDatalabel.font=[UIFont fontWithName:@"PingFangSC" size:14.0 * kWindowWHOne];
        _noDatalabel.text=LocalizationKey(@"noDada");
        _noDatalabel.textColor=AppTextColor_Level_3;
        [self.view addSubview:_noDatalabel];
        MJWeakSelf;
        [self.noDatalabel mas_makeConstraints:^(MASConstraintMaker *make) {
             make.centerX.equalTo(weakSelf.mytableView.mas_centerX).offset(0);
                            make.centerY.equalTo(weakSelf.mytableView.mas_centerY).offset(0);
            make.size.mas_equalTo(CGSizeMake(SCREEN_WIDTH_S,40));
        }];
    }
    return _noDatalabel;;
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
