//
//  CurrencyrecordViewController.m
//  digitalCurrency
//
//  Created by startlink on 2019/8/7.
//  Copyright © 2019年 BIZZAN. All rights reserved.
//

#import "CurrencyrecordViewController.h"
#import "CurrencyrecordTableViewCell.h"
#import "MineNetManager.h"
#import "RecordModel.h"
#import "MentionCoinInfoModel.h"
#import "DownTheTabs.h"

@interface CurrencyrecordViewController ()<UITableViewDelegate,UITableViewDataSource>
@property (weak, nonatomic) IBOutlet UITableView *uitableView;

@property (weak, nonatomic) IBOutlet NSLayoutConstraint *bottomspec;
@property (nonatomic, strong) LYEmptyView *emptyView;
@property (nonatomic,strong)NSMutableArray *dataarray;
@property(nonatomic,assign)NSInteger pageNo;
@property(nonatomic,strong)NSMutableArray *mentionCoinArr;

@property (nonatomic,copy)NSString *symbol;
@property (nonatomic,strong)NSMutableArray *symbolarray;
@end

@implementation CurrencyrecordViewController
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
    self.bottomspec.constant = SafeAreaBottomHeight;
    [self RightsetupNavgationItemWithpictureName:@"zicanliushui"];
    self.title = LocalizationKey(@"Currencyrecord");
    self.symbolarray = [NSMutableArray array];
    self.uitableView.delegate = self;
    self.uitableView.dataSource = self;
    self.uitableView.separatorStyle = UITableViewCellSeparatorStyleNone;
    [self.uitableView registerNib:[UINib nibWithNibName:@"CurrencyrecordTableViewCell" bundle:nil] forCellReuseIdentifier:@"CurrencyrecordTableViewCell"];
    self.pageNo = 0;

    [self headRefreshWithScrollerView:_uitableView];
    [self footRefreshWithScrollerView:_uitableView];
    [self mentionCoinInfo];

    [self getadata];
}
//MARK:--上拉加载
- (void)refreshFooterAction{
    self.pageNo++;
    [self getadata];
}
//MARK:--下拉刷新
- (void)refreshHeaderAction{
    self.pageNo = 0;
    [self getadata];
}
-(void)RighttouchEvent{
    self.navigationItem.rightBarButtonItem.enabled = NO;
    self.symbol = nil;


    DownTheTabs *tabs = [[DownTheTabs alloc]initCurrencyRecordTabsWithContainerView:self.view Projects:self.mentionCoinArr];

    tabs.dismissBlock = ^{
        self.navigationItem.rightBarButtonItem.enabled = YES;
    };

    tabs.currencyRecordBlock = ^(NSString *symbol) {
        self.symbol = symbol;
        if(symbol != nil){
            [self.symbolarray removeAllObjects];
            for (RecordModel *model in self.dataarray) {
                if([model.coin.unit isEqualToString:symbol]){
                    [_symbolarray addObject:model];
                }
            }

        }
        [self.uitableView reloadData];


    };
}

- (NSMutableArray *)mentionCoinArr {
    if (!_mentionCoinArr) {
        _mentionCoinArr = [NSMutableArray array];
    }
    return _mentionCoinArr;
}
//MARK:--获取币种信息
-(void)mentionCoinInfo{
    [EasyShowLodingView showLodingText:[[ChangeLanguage bundle] localizedStringForKey:@"loading" value:nil table:@"English"]];
    [MineNetManager mentionCoinInfoForCompleteHandle:^(id resPonseObj, int code) {
        [EasyShowLodingView hidenLoding];
        if (code) {
            if ([resPonseObj[@"code"] integerValue] == 0) {
                //[self.view makeToast:resPonseObj[MESSAGE] duration:1.5 position:CSToastPositionCenter];
                NSArray *arrays = resPonseObj[@"data"];
                for(NSDictionary *dic in arrays){
                    MentionCoinInfoModel *model = [MentionCoinInfoModel mj_objectWithKeyValues:dic];
                                [self.mentionCoinArr addObject:model.unit];
                }



            }else if ([resPonseObj[@"code"] integerValue] == 3000 ||[resPonseObj[@"code"] integerValue] == 4000 ){
                // [ShowLoGinVC showLoginVc:self withTipMessage:resPonseObj[MESSAGE]];
                [YLUserInfo logout];
            }else{
                [self.view makeToast:resPonseObj[MESSAGE] duration:1.5 position:CSToastPositionCenter];
            }
        }else{
            [self.view makeToast:[[ChangeLanguage bundle] localizedStringForKey:@"noNetworkStatus" value:nil table:@"English"] duration:1.5 position:CSToastPositionCenter];
        }
    }];
}

-(void)getadata{

    [EasyShowLodingView showLodingText:[[ChangeLanguage bundle] localizedStringForKey:@"loading" value:nil table:@"English"]];
    NSString *pageNoStr = [[NSString alloc] initWithFormat:@"%ld",(long)_pageNo];
    NSMutableDictionary *bodydic = [NSMutableDictionary dictionary];
    [bodydic setValue:pageNoStr forKey:@"page"];
    [bodydic setValue:@"10" forKey:@"pageSize"];
    [MineNetManager withdrawrecordParam:bodydic CompleteHandle:^(id resPonseObj, int code) {
        [EasyShowLodingView hidenLoding];
        if (code) {
            if ([resPonseObj[@"code"] integerValue]==0) {
                //获取数据成功
                if (_pageNo == 0) {
                    [_dataarray removeAllObjects];
                }

                NSArray *dataArr = [RecordModel mj_objectArrayWithKeyValuesArray:resPonseObj[@"data"][@"content"]];
                [self.dataarray addObjectsFromArray:dataArr];
                self.uitableView.ly_emptyView = self.emptyView;
                [self.uitableView reloadData];
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
    if (self.symbol != nil){
        return self.symbolarray.count;
    }
    return self.dataarray.count;


}

- (CGFloat)tableView:(UITableView *)tableView heightForRowAtIndexPath:(NSIndexPath *)indexPath{
    return 150;
}

- (CGFloat)tableView:(UITableView *)tableView heightForHeaderInSection:(NSInteger)section{

    return 0.01;

}

- (CGFloat)tableView:(UITableView *)tableView heightForFooterInSection:(NSInteger)section{
    return 0.01;
}

- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath{
    CurrencyrecordTableViewCell *cell = [tableView dequeueReusableCellWithIdentifier:@"CurrencyrecordTableViewCell" forIndexPath:indexPath];
    RecordModel *model;
    if (self.symbol != nil){
        model = self.symbolarray[indexPath.row];
    }else{
        model = self.dataarray[indexPath.row];

    }
    cell.typelabel.text = model.coin.unit;
    cell.timelabel.text = model.createTime;
    cell.addresslabel.text = model.address;
    cell.numlabel.text = [ToolUtil judgeStringForDecimalPlaces:model.totalAmount];
    cell.freelabel.text = [ToolUtil judgeStringForDecimalPlaces:model.fee];

    if (model.status == 0) {
        cell.statuelabel.text = LocalizationKey(@"auditing");
    }else if (model.status == 1){
        cell.statuelabel.text = LocalizationKey(@"Assetstoreleased");

    }else if (model.status == 2){
        cell.statuelabel.text = LocalizationKey(@"failure");

    }else if(model.status == 3){
        cell.statuelabel.text = LocalizationKey(@"Success");

    }


    return cell;

}

-(void)viewWillAppear:(BOOL)animated{
    [super viewWillAppear:animated];
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
