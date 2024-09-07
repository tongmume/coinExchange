//
//  WalletManageViewController.m
//  digitalCurrency
//
//  Created by iDog on 2019/2/5.
//  Copyright © 2019年 BIZZAN. All rights reserved.
//

#import "WalletManageViewController.h"
#import "WalletManageTableViewCell.h"
#import "WalletManageDetailViewController.h"
#import "MentionMoneyViewController.h"
#import "ChargeMoneyViewController.h"
#import "MineNetManager.h"
#import "WalletManageModel.h"
#import "WalletManageTableHeadView.h"
#import "UIView+LLXAlertPop.h"
#import "YBPopupMenu.h"
#import "WalletTitleTableViewCell.h"
#import "WalletButTableViewCell.h"
#import "CurrencyrecordViewController.h"
#import "ChargerecordViewController.h"
#import "WalletManageContractTableViewCell.h"
#import "ContractExchangeManager.h"
#import "WalletContractCoinModel.h"
#import "WalletSelectCurrencyViewController.h"
#import "AssetTransferViewController.h"

@interface WalletManageViewController ()<UITableViewDelegate,UITableViewDataSource,UISearchBarDelegate,WalletManageTableViewCellDelegate,YBPopupMenuDelegate>{
    BOOL _refreshFlag;
    UIView *_tableHeadView;
}
@property (weak, nonatomic) IBOutlet UITableView *tableview;
@property (weak, nonatomic) IBOutlet UIView *backView;
@property (weak, nonatomic) IBOutlet UILabel *totalAssets; //总资产
@property (weak, nonatomic) IBOutlet UILabel *asset1; //资产 橙色数字
@property (weak, nonatomic) IBOutlet UILabel *asset2; //资产 换算数字
@property (weak, nonatomic) IBOutlet UIButton *eyeButton;  //眼睛按钮
@property (weak, nonatomic) IBOutlet NSLayoutConstraint *bottomViewHeight;//底部的高度区分iPhoneX和普通机型
@property(nonatomic,strong) WalletManageTableHeadView *headerView;

@property(nonatomic,strong)NSMutableArray *walletManageArr;
@property(nonatomic,strong)NSMutableArray *selectArr;
@property(nonatomic,strong)NSMutableArray *searchArr;
@property(nonatomic,assign)NSInteger selectIndex;//0未隐藏 1 隐藏
@property(nonatomic,assign)NSInteger searchIndex;//0未搜索 1 搜索
@property(nonatomic,assign)NSInteger flagIndex;//1搜索 2隐藏 0没有
@property(nonatomic,strong)NSIndexPath *clickIndexPath;
@property(nonatomic,strong)WalletManageModel *clickModel;

@property (weak, nonatomic) IBOutlet UIView *topBackView;

@property (nonatomic, strong) UIView *menuView;

@property (nonatomic, strong) UIView *moveLine;

@property (nonatomic, strong) UITableView *rightTableView;

@property (nonatomic, strong) NSMutableArray *contractDataArray;

@end

@implementation WalletManageViewController

-(NSMutableArray *)contractDataArray {
    
    if (!_contractDataArray) {
        _contractDataArray=[NSMutableArray array];
    }
    return _contractDataArray;
}

- (void)viewDidLoad {
    [super viewDidLoad];
    self.title = [[ChangeLanguage bundle] localizedStringForKey:@"Myassets" value:nil table:@"English"];
    
    if (!self.assetUSD ) {
        self.asset1.text = @"0.000000";
        self.assetUSD=@"0.000000";
    }else{
        self.asset1.text = self.assetUSD;
    }
    if (!self.assetCNY) {
        self.asset2.text = @"≈0.00CNY";
        self.assetCNY=@"≈0.00CNY";
    }else{
        self.asset2.text = self.assetCNY;
    }
    
    self.bottomViewHeight.constant = SafeAreaBottomHeight;
    //    self.topBgViewHeight.constant = SafeAreaTopHeight+10+(kWindowW-40)*2/5 -40;
    self.tableview.delegate = self;
    self.tableview.dataSource = self;
    //    self.tableview.estimatedRowHeight = 44;
    self.tableview.separatorStyle = UITableViewCellSeparatorStyleNone;
    
    self.tableview.backgroundColor = mainColor;
    [self.tableview registerNib:[UINib nibWithNibName:@"WalletManageTableViewCell" bundle:nil] forCellReuseIdentifier:NSStringFromClass([WalletManageTableViewCell class])];
    
    _tableHeadView = [[UIView alloc]initWithFrame:CGRectMake(0, 0, kWindowW, 40)];
    [self.backView addSubview:_tableHeadView];
    self.headerView=[[[WalletManageTableHeadView alloc]init] instancetableHeardViewWithFrame:_tableHeadView.frame];
    self.headerView.searchBar.delegate = self;
    [self.headerView.selectButton addTarget:self action:@selector(selectBtnClick:) forControlEvents:UIControlEventTouchUpInside];
    
    //    UITextField * searchField = [self.headerView.searchBar valueForKey:@"_searchField"];
    //    searchField.font = [UIFont systemFontOfSize:13];
    //     [searchField setValue:[UIFont boldSystemFontOfSize:13] forKeyPath:@"_placeholderLabel.font"];
    self.headerView.searchBar.keyboardType = UIKeyboardTypeASCIICapable;
    [_tableHeadView addSubview:self.headerView];
    [self doneForSearchBar];
    
    self.totalAssets.text = [[ChangeLanguage bundle] localizedStringForKey:@"totalAssets" value:nil table:@"English"];
    [self rightBarItemWithTitle:[[ChangeLanguage bundle] localizedStringForKey:@"detail" value:nil table:@"English"]];
    [self getData];
    self.selectArr = [[NSMutableArray alloc] init];
    self.searchArr = [[NSMutableArray alloc] init];
    self.selectIndex = 0;
    self.searchIndex = 0;
    self.flagIndex = 0;
    
    
    [self loadWalletMenuView];
    
    [self getContractWalletList];
    
}
//MARK:--给searchBar的键盘加上完成按钮
-(void)doneForSearchBar{
    UIToolbar * toobar = [[UIToolbar alloc] initWithFrame:CGRectMake(0, 0, [UIScreen mainScreen].bounds.size.width, 38.0f)];
    toobar.backgroundColor = mainColor;
    toobar.translucent = YES;
    toobar.barStyle = UIBarStyleDefault;
    UIBarButtonItem * spaceBarButtonItem = [[UIBarButtonItem alloc] initWithBarButtonSystemItem:UIBarButtonSystemItemFlexibleSpace target:nil action:nil];
    UIBarButtonItem * doneBarButtonItem = [[UIBarButtonItem alloc] initWithTitle:LocalizationKey(@"done") style:UIBarButtonItemStyleDone target:self action:@selector(resignKeyboard:)];
    NSDictionary *dic = [NSDictionary dictionaryWithObject:[UIColor colorWithRed:12/255.0 green:95/255.0 blue:255/255.0 alpha:1] forKey:NSForegroundColorAttributeName];
    [doneBarButtonItem setTitleTextAttributes:dic forState:UIControlStateNormal];
    [doneBarButtonItem setTitleTextAttributes:dic forState:UIControlStateSelected];
    [toobar setItems:@[spaceBarButtonItem,doneBarButtonItem]];
    self.headerView.searchBar.inputAccessoryView = toobar;
}
- (void)resignKeyboard:(id)sender{
    [self.headerView.searchBar resignFirstResponder];
}
//MARK:--隐藏为0的币种
-(void)selectBtnClick:(UIButton *)button{
    button.selected = !button.selected;
    if (button.selected) {
        self.flagIndex = 2;
        _selectIndex = 1;
        //被选择
        [self.headerView.selectButton setImage:[UIImage imageNamed:@"walletSelected"] forState:UIControlStateNormal];
        [_selectArr removeAllObjects];
        if (_searchIndex == 0) {
            //未搜索
            for (WalletManageModel *model in _walletManageArr) {
                if ([model.balance floatValue] > 0) {
                    [self.selectArr addObject:model];
                }
            }
        }else if (_searchIndex == 1) {
            //搜索
            for (WalletManageModel *model in _searchArr) {
                if ([model.balance floatValue] > 0) {
                    [self.selectArr addObject:model];
                }
            }
        }
    }else{
        self.flagIndex = 0;
        //未被选择
        [self.headerView.selectButton setImage:[UIImage imageNamed:@"walletNoSelect"] forState:UIControlStateNormal];
        [self.selectArr removeAllObjects];
        if (_searchIndex == 0) {
            //未搜索
            for (WalletManageModel *model in _walletManageArr) {
                if ([model.balance floatValue] > 0) {
                    [self.selectArr addObject:model];
                }
            }
        }else if (_searchIndex == 1) {
            //搜索
            for (WalletManageModel *model in _searchArr) {
                if ([model.balance floatValue] > 0) {
                    [self.selectArr addObject:model];
                }
            }
        }
        _selectIndex = 0;
    }
    [_tableview reloadData];
}
-(void)searchBar:(UISearchBar *)searchBar textDidChange:(NSString *)searchText{
    if (searchText.length <= 0) {
        //不处理
        self.flagIndex = 0;
        _searchIndex = 0;
    }else{
        self.flagIndex = 1;
        [self.searchArr removeAllObjects];
        //小写转大写
        NSString *transformString = [searchBar.text uppercaseString];
        if (_selectIndex == 0) {
            //未隐藏
            for (WalletManageModel *model in _walletManageArr) {
                if ([model.coin.unit containsString:transformString]) {
                    [self.searchArr addObject:model];
                }
            }
        }else if (_selectIndex == 1) {
            //已隐藏
            for (WalletManageModel *model in _selectArr) {
                if ([model.coin.unit containsString:transformString]) {
                    [self.searchArr addObject:model];
                }
            }
        }
        _searchIndex = 1;
    }
    
    [_tableview reloadData];
    
}



//键盘上搜索事件的响应
-(void)searchBarSearchButtonClicked:(UISearchBar *)searchBar{
    [searchBar resignFirstResponder];
}
//MARK:---获取我的钱包所有数据
-(void)getData{
    [self.walletManageArr removeAllObjects];
    [EasyShowLodingView showLodingText:LocalizationKey(@"loading")];
    [MineNetManager getMyWalletInfoForCompleteHandle:^(id resPonseObj, int code) {
        
        
        NSLog(@"请求总资产的接口 --- %@",resPonseObj);
        [EasyShowLodingView hidenLoding];
        if (code) {
            if ([resPonseObj[@"code"] integerValue] == 0) {
                //                 [self.walletManageArr removeAllObjects];
                NSArray *dataArr = [WalletManageModel mj_objectArrayWithKeyValuesArray:resPonseObj[@"data"]];
                
                [self.walletManageArr addObjectsFromArray:dataArr];
                [self.tableview reloadData];
                
            }else{
                [self.view makeToast:resPonseObj[MESSAGE] duration:1.5 position:CSToastPositionCenter];
            }
        }else{
            [self.view makeToast:LocalizationKey(@"noNetworkStatus") duration:1.5 position:CSToastPositionCenter];
        }
    }];
}


- (NSMutableArray *)walletManageArr {
    if (!_walletManageArr) {
        _walletManageArr = [NSMutableArray array];
    }
    return _walletManageArr;
}
//MARK:--明细的点击事件
-(void)RighttouchEvent{
    NSArray *namearray = @[LocalizationKey(@"Currencyrecord"),LocalizationKey(@"Chargerecord")];
    
    [YBPopupMenu showAtPoint:CGPointMake(kWindowW - 32, NEW_NavHeight - 15) titles:namearray icons:nil menuWidth:100 otherSettings:^(YBPopupMenu *popupMenu) {
        popupMenu.arrowDirection = YBPopupMenuArrowDirectionNone;
        popupMenu.delegate = self;
        popupMenu.textColor = RGBOF(0xe6e6e6);
        popupMenu.backColor = mainColor;
        
    }];
    
    
}

#pragma mark - YBPopupMenuDelegate
- (void)ybPopupMenu:(YBPopupMenu *)ybPopupMenu didSelectedAtIndex:(NSInteger)index
{
    if (index == 0) {
        
        
        //提币记录
        CurrencyrecordViewController *detailVC = [[CurrencyrecordViewController alloc] init];
        [self.navigationController pushViewController:detailVC animated:YES];
    }
    
    if (index == 1) {
        
        //冲币记录
        ChargerecordViewController *detailVC = [[ChargerecordViewController alloc] init];
        [self.navigationController pushViewController:detailVC animated:YES];
    }
    
}
-(NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section{
    if ([tableView isEqual:_rightTableView]) {
        return self.contractDataArray.count;
    }else{
        if (_selectIndex == 0){
            if (_searchIndex == 0) {
                return _walletManageArr.count;
            }else{
                return _searchArr.count;
            }
        }
        else{
            if (_searchIndex == 0) {
                return _selectArr.count;
            }
            else{
                if (_flagIndex == 1) {
                    return _searchArr.count;
                }
                else{
                    return _selectArr.count;
                }
            }
        }
    }
}

-(CGFloat)tableView:(UITableView *)tableView heightForFooterInSection:(NSInteger)section{
    return 0.01;
}

-(CGFloat)tableView:(UITableView *)tableView heightForHeaderInSection:(NSInteger)section{
    return 0.01;
}
-(NSInteger)numberOfSectionsInTableView:(UITableView *)tableView{
    return 1;
}
- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath
{
    if ([tableView isEqual:_rightTableView]) {
        
        WalletManageContractTableViewCell *cell=[tableView dequeueReusableCellWithIdentifier:@"walletcontractcell"];
        
        if (!cell) {
            cell=[[WalletManageContractTableViewCell alloc]initWithStyle:UITableViewCellStyleDefault reuseIdentifier:@"walletcontractcell"];
            cell.selectionStyle=UITableViewCellSelectionStyleNone;
        }
        
        if (self.contractDataArray.count!=0&&indexPath.row<self.contractDataArray.count) {
            
            WalletContractCoinModel *model=self.contractDataArray[indexPath.row];
            [self setWalletContractModel:model WalletManageContractTableViewCell:cell];
        }
        
        return cell;
        
    }else{
        
        WalletManageModel *model;
        if (_selectIndex == 0){
            if (_searchIndex == 0) {
                model = _walletManageArr[indexPath.row];
            }else{
                model = _searchArr[indexPath.row];
            }
        }else{
            if (_searchIndex == 0) {
                model = _selectArr[indexPath.row];
            }else{
                if (_flagIndex == 1) {
                    model = _searchArr[indexPath.row];
                }else{
                    model = _selectArr[indexPath.row];
                }
            }
        }
        WalletManageCoinInfoModel *infoModel = model.coin;
        
        WalletManageTableViewCell *cell = [tableView dequeueReusableCellWithIdentifier:NSStringFromClass([WalletManageTableViewCell class]) forIndexPath:indexPath];
        cell.selectionStyle = UITableViewCellSelectionStyleNone;
        
        cell.coinType.text = infoModel.unit;
        cell.availableNum.text = [ToolUtil judgeStringForDecimalPlaces:model.balance];
        cell.freezeNum.text = [ToolUtil judgeStringForDecimalPlaces:model.frozenBalance];
        cell.lockingNum.text = model.toReleased ? [ToolUtil judgeStringForDecimalPlaces:model.toReleased] : @"0";
        cell.delegate = self;
        cell.index = indexPath;
        cell.model = model;
        //        if ([model.clickIndex isEqualToString:@"1"]) {
        //            [cell.clickButton setImage:[UIImage imageNamed:@"downMoreImage"] forState:UIControlStateNormal];
        //        }else{
        //            [cell.clickButton setImage:[UIImage imageNamed:@"leftMoreImage"] forState:UIControlStateNormal];
        //        }
        return cell;
    }
}


-(void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath{
    if ([tableView isEqual:_rightTableView]) {
        
    }else{
        
        WalletManageModel *model;
        if (_selectIndex == 0){
            if (_searchIndex == 0) {
                model = _walletManageArr[indexPath.row];
            }else{
                model = _searchArr[indexPath.row];
            }
        }else{
            if (_searchIndex == 0) {
                model = _selectArr[indexPath.row];
            }else{
                if (_flagIndex == 1) {
                    model = _searchArr[indexPath.row];
                }else{
                    model = _selectArr[indexPath.row];
                }
            }
        }
        
        if ([model.clickIndex isEqualToString:@"1"]) {
            model.clickIndex = @"0";
        }else{
            model.clickIndex = @"1";
        }
        
        [self buttonIndex:indexPath withModel:model];
    }
    
}


-(void)buttonIndex:(NSIndexPath *)indexPath withModel:(WalletManageModel *)model{
    // canRecharge 充币
    if (![model.coin.canRecharge isEqualToString:@"1"] && ![model.coin.canWithdraw isEqualToString:@"1"]) {
        [self.view makeToast:[[ChangeLanguage bundle] localizedStringForKey:@"notRaiseMoneyTip" value:nil table:@"English"] duration:1.5 position:CSToastPositionCenter];
        model.clickIndex = @"0";
    }else{
        //可以充币或提币
        [self coinAlterView:indexPath withModel:model];
    }
    [self.tableview reloadData];
    
}
//MARK:-- 提币充币
-(void)coinAlterView:(NSIndexPath*)indexPath withModel:(WalletManageModel *)model{
    self.clickIndexPath = indexPath;
    self.clickModel = model;
    NSArray *arrayTitle;
    NSArray *arrayColor;
    if ([model.coin.canRecharge isEqualToString:@"1"] &&[model.coin.canWithdraw isEqualToString:@"1"]) {
        //可以充币，提币
        arrayTitle = @[[[ChangeLanguage bundle] localizedStringForKey:@"chargeMoney" value:nil table:@"English"] ,[[ChangeLanguage bundle] localizedStringForKey:@"mentionMoney" value:nil table:@"English"] ];
        arrayColor = @[GreenColor,RedColor];
    }else if ([model.coin.canRecharge isEqualToString:@"1"] && ![model.coin.canWithdraw isEqualToString:@"1"]){
        arrayTitle = @[[[ChangeLanguage bundle] localizedStringForKey:@"chargeMoney" value:nil table:@"English"]];
        arrayColor = @[GreenColor];
    }else{
        arrayTitle = @[[[ChangeLanguage bundle] localizedStringForKey:@"mentionMoney" value:nil table:@"English"]];
        arrayColor = @[GreenColor];
    }
    [self.view createAlertViewTitleArray:arrayTitle textColor:arrayColor font:[UIFont systemFontOfSize:16] type:1  actionBlock:^(UIButton * _Nullable button, NSInteger didRow) {
        if ([button.currentTitle isEqualToString:[[ChangeLanguage bundle] localizedStringForKey:@"chargeMoney" value:nil table:@"English"]]) {
            //充币
            [self chargeMoneyBtnClick];
            
        }else if ([button.currentTitle isEqualToString:[[ChangeLanguage bundle] localizedStringForKey:@"mentionMoney" value:nil table:@"English"]]){
            //提币
            [self mentionMoneyBtnClick];
        }else if ([button.currentTitle isEqualToString:[[ChangeLanguage bundle] localizedStringForKey:@"cancel" value:nil table:@"English"]]){
            //取消
            [self cancelBtnClick];
        }
    }];
}
-(void)cancelBtnClick{
    self.clickModel.clickIndex = @"0";
    [self.tableview reloadData];
}
//MARK:--充币点击事件
-(void)chargeMoneyBtnClick{
    _refreshFlag = YES;
    ChargeMoneyViewController *chargeVC = [[ChargeMoneyViewController alloc] init];
    chargeVC.unit = self.clickModel.coin.unit;
    chargeVC.address = self.clickModel.address;
    chargeVC.accountType = self.clickModel.coin.accountType;
    chargeVC.model = self.clickModel;
    chargeVC.hidesBottomBarWhenPushed = YES;
    [self.navigationController pushViewController:chargeVC animated:YES];
    self.clickModel.clickIndex = @"0";
    [self.tableview reloadData];
}
//MARK:--提币点击事件
-(void)mentionMoneyBtnClick{
    MentionMoneyViewController *mentionVC = [[MentionMoneyViewController alloc] init];
    _refreshFlag = YES;
    mentionVC.unit = self.clickModel.coin.unit;
    mentionVC.hidesBottomBarWhenPushed = YES;
    [self.navigationController pushViewController:mentionVC animated:YES];
    self.clickModel.clickIndex = @"0";
    [self.tableview reloadData];
    
}
//-(CGFloat)tableView:(UITableView *)tableView estimatedHeightForRowAtIndexPath:(NSIndexPath *)indexPath{
//
////    WalletManageModel *model = _walletManageArr[indexPath.row];
////    CGFloat height=  [self getLabelHeightWithText:model.balance WithotherText:model.frozenBalance width:(kWindowW-170)/2 font:17];
////    return height+70;
//    return 105 * kWindowWHOne;
//}
-(CGFloat)tableView:(UITableView *)tableView heightForRowAtIndexPath:(NSIndexPath *)indexPath{
    //    WalletManageModel *model = _walletManageArr[indexPath.row];
    //    CGFloat height=  [self getLabelHeightWithText:model.balance WithotherText:model.frozenBalance width:(kWindowW-170)/2 font:17];
    //    return/var/folders/r_/qt7ndt290sg35j5pl6zflc180000gp/T/AppIconMaker/appicon.png height+70;
    //    return UITableViewAutomaticDimension;
    
    return 105 * kWindowWHOne;
    
}
//MARK:--眼睛按钮的点击事件
- (IBAction)eyeBtnClick:(UIButton *)sender {
    
    if (sender.selected) {
        [_eyeButton setBackgroundImage:[UIImage imageNamed:@"closeEye"] forState:UIControlStateNormal];
        self.asset1.text = @"********";
        self.asset2.text = @"********";
        [NSUserDefaultUtil PutBoolDefaults:HIDEMONEY Value:YES];
    }else{
        [_eyeButton setBackgroundImage:[UIImage imageNamed:@"openEye"] forState:UIControlStateNormal];
        if(![YLUserInfo isLogIn]){
            //没登录不做处理
            self.asset1.text = @"0.000000";
            self.asset2.text = @"≈0.000000CNY";
            
        }else{
            self.asset1.text = self.assetUSD;
            self.asset2.text = self.assetCNY;
        }
        [NSUserDefaultUtil PutBoolDefaults:HIDEMONEY Value:NO];
    }
    sender.selected = !sender.selected;
    
}


//MARK:--点击隐藏展开总资产
-(void)eyeButtonArrageData{
    if (!self.eyeButton.selected) {
        [self.eyeButton setBackgroundImage:[UIImage imageNamed:@"closeEye"] forState:UIControlStateNormal];
        self.asset1.text = @"********";
        self.asset2.text = @"********";
    }else{
        [self.eyeButton setBackgroundImage:[UIImage imageNamed:@"openEye"] forState:UIControlStateNormal];
        if(![YLUserInfo isLogIn]){
            //没登录不做处理
            self.asset1.text = @"0.000000";
            self.asset2.text = @"≈0.00CNY";
        }else{
            self.asset1.text = self.assetUSD;
            self.asset2.text = self.assetCNY;
        }
    }
}

-(void)viewWillAppear:(BOOL)animated{
    [super viewWillAppear:animated];
    if ([[NSUserDefaultUtil GetDefaults:HIDEMONEY] boolValue]) {
        self.eyeButton.selected = NO;
    }else{
        self.eyeButton.selected = YES;
    }
    [self eyeButtonArrageData];
    
    if (_refreshFlag) {
        [self getData];
        //获取合约账户信息
        [self getContractWalletList];
        _refreshFlag=NO;
    }
}


- (void)loadWalletMenuView {
    
    UIView*menubackview=[[UIView alloc]init];
    [self.view addSubview:menubackview];
    MJWeakSelf;
    [menubackview mas_makeConstraints:^(MASConstraintMaker *make) {
        make.left.mas_equalTo(15);
        make.right.mas_equalTo(-15);
        //        make.top.mas_equalTo(15);
        make.top.equalTo(weakSelf.topBackView.mas_bottom).offset(10);
        make.bottom.equalTo(weakSelf.backView.mas_top).offset(0);
    }];
    _menuView=menubackview;
    NSArray *menutitles=@[LocalizationKey(@"chargeMoney"),LocalizationKey(@"mentionMoney"),LocalizationKey(@"tv_overturn")];
    NSArray *choiceTitles=@[LocalizationKey(@"tv_coins"),LocalizationKey(@"tv_constract")];
    
    CGFloat interval= 20;
    CGSize btnsize= CGSizeMake((SCREEN_WIDTH_S-30-interval*(menutitles.count-1))/menutitles.count,30);
    for (NSInteger i=0; i<menutitles.count; i++) {
        
        UIButton *btn=[UIButton buttonWithType:UIButtonTypeCustom];
        [btn setTitle:LocalizationKey(menutitles[i]) forState:UIControlStateNormal];
        btn.backgroundColor=baseColor;
        [btn setTitleColor:[UIColor whiteColor] forState:UIControlStateNormal];
        btn.layer.cornerRadius=3;
        btn.layer.masksToBounds=YES;
        btn.tag=100+i;
        [btn addTarget:self action:@selector(menubtnclick:) forControlEvents:UIControlEventTouchUpInside];
        [menubackview addSubview:btn];
        
        [btn mas_makeConstraints:^(MASConstraintMaker *make) {
            make.left.mas_equalTo(i*btnsize.width+i*interval);
            make.top.mas_equalTo(0);
            make.size.mas_equalTo(btnsize);
        }];
    }
    CGSize cbtnsize=CGSizeMake(80,30);
    
    for (NSInteger i=0; i<choiceTitles.count; i++) {
        
        UIButton *btn=[UIButton buttonWithType:UIButtonTypeCustom];
        [btn setTitle:LocalizationKey(choiceTitles[i]) forState:UIControlStateNormal];
        [btn setTitleColor:VCBackgroundColor forState:UIControlStateNormal];
        [btn setTitleColor:baseColor forState:UIControlStateSelected];
        [menubackview addSubview:btn];
        btn.tag=100+menutitles.count+i;
        [btn addTarget:self action:@selector(menubtnclick:) forControlEvents:UIControlEventTouchUpInside];
        [btn mas_makeConstraints:^(MASConstraintMaker *make) {
            make.bottom.mas_equalTo(-5);
            make.left.mas_equalTo(i*cbtnsize.width+i*10);
            make.size.mas_equalTo(cbtnsize);
        }];
    }
    
    _moveLine=[[UIView alloc]init];
    _moveLine.backgroundColor=baseColor;
    [menubackview addSubview:_moveLine];
    
    [_moveLine mas_makeConstraints:^(MASConstraintMaker *make) {
        make.bottom.mas_equalTo(-5);
        make.size.mas_equalTo(CGSizeMake(cbtnsize.width,2));
    }];
    
    
    
    UIView *line=[[UIView alloc]init];
    line.backgroundColor=mainColor;
    [menubackview addSubview:line];
    
    [line mas_makeConstraints:^(MASConstraintMaker *make) {
        make.right.left.bottom.mas_equalTo(0);
        make.height.mas_equalTo(1);
    }];
    
    _rightTableView=[[UITableView alloc]init];
    [_rightTableView  setSeparatorStyle:UITableViewCellSeparatorStyleNone];
    _rightTableView.backgroundColor = mainColor;
    _rightTableView.delegate=self;
    _rightTableView.dataSource=self;
    _rightTableView.showsVerticalScrollIndicator=NO;
    _rightTableView.alwaysBounceVertical=YES;
    if (@available(iOS 11.0, *)) {
        
        _rightTableView.estimatedRowHeight = 0;
        
        _rightTableView.estimatedSectionFooterHeight = 0;
        
        _rightTableView.estimatedSectionHeaderHeight=0;
        _rightTableView.contentInsetAdjustmentBehavior= UIScrollViewContentInsetAdjustmentNever;
    }
    
    [self.view addSubview:_rightTableView];
    [_rightTableView mas_makeConstraints:^(MASConstraintMaker *make) {
        make.left.right.mas_equalTo(0);
        make.top.equalTo(menubackview.mas_bottom).offset(0);
        make.bottom.mas_equalTo(-SafeAreaBottomHeight);
    }];
    
    UIButton *button = [_menuView viewWithTag:103];
    [self menubtnclick:button];
    
}


-(void)menubtnclick:(UIButton *)button {
    MJWeakSelf;
    switch (button.tag) {
        case 100:{ //充币
            _refreshFlag=YES;
            WalletSelectCurrencyViewController *vc=[[WalletSelectCurrencyViewController alloc]init];
            [vc.dataArray addObjectsFromArray:self.walletManageArr];
            vc.type=0;
            [self.navigationController pushViewController:vc animated:YES];
            //                  ChargeMoneyViewController *chargeVC = [[ChargeMoneyViewController alloc] init];
            //                   chargeVC.unit = self.clickModel.coin.unit;
            //                   chargeVC.address = self.clickModel.address;
            //                   chargeVC.hidesBottomBarWhenPushed = YES;
            //                   [self.navigationController pushViewController:chargeVC animated:YES];
            //                   self.clickModel.clickIndex = @"0";
            
        }
            break;
        case 101:{ //提币
            _refreshFlag=YES;
            WalletSelectCurrencyViewController *vc=[[WalletSelectCurrencyViewController alloc]init];
            [vc.dataArray addObjectsFromArray:self.walletManageArr];
            vc.type=1;
            [self.navigationController pushViewController:vc animated:YES];
            //                   MentionMoneyViewController *mentionVC = [[MentionMoneyViewController alloc] init];
            //                   _refreshFlag = YES;
            //                   mentionVC.unit = self.clickModel.coin.unit;
            //                   mentionVC.hidesBottomBarWhenPushed = YES;
            //                   [self.navigationController pushViewController:mentionVC animated:YES];
            //                   self.clickModel.clickIndex = @"0";
            
            
        }
            break;
        case 102:{ //划转
            _refreshFlag=YES;
            
            AssetTransferViewController *avc=[[AssetTransferViewController alloc]init];
            avc.contractAccountArray=self.contractDataArray;
            [self.navigationController pushViewController:avc animated:YES];
            
        }
            break;
        case 103:{ //币币账户
            
            self.backView.hidden=NO;
            self.tableview.hidden=NO;
            self.rightTableView.hidden=YES;
            button.selected=YES;
            UIButton *btn= [_menuView viewWithTag:104];
            btn.selected=NO;
            
            CGRect rect= _moveLine.frame;
            rect.origin.x=button.frame.origin.x;
            
            [UIView animateWithDuration:0.3 animations:^{
                
                weakSelf.moveLine.frame=rect;
                
            }];
            [weakSelf.moveLine mas_updateConstraints:^(MASConstraintMaker *make) {
                make.left.mas_equalTo(rect.origin.x);
            }];
            
            
        }
            break;
        case 104:{ //永续账户
            
            self.backView.hidden=YES;
            self.tableview.hidden=YES;
            self.rightTableView.hidden=NO;
            
            button.selected=YES;
            UIButton *btn= [_menuView viewWithTag:103];
            btn.selected=NO;
            
            CGRect rect= _moveLine.frame;
            rect.origin.x=button.frame.origin.x;
            
            [UIView animateWithDuration:0.3 animations:^{
                
                weakSelf.moveLine.frame=rect;
                
            }];
            [weakSelf.moveLine mas_updateConstraints:^(MASConstraintMaker *make) {
                make.left.mas_equalTo(rect.origin.x);
            }];
        }
            break;
        default:
            break;
    }
}


- (void)getContractWalletList{
    [self.contractDataArray removeAllObjects];
    [ContractExchangeManager getWalletListCompleteHandle:^(id  _Nonnull resPonseObj, int code) {
        if (code) {
            if ([resPonseObj[@"code"] intValue] == 0) {
                NSArray *data=resPonseObj[@"data"];
                NSArray *dataArr = [WalletContractCoinModel mj_objectArrayWithKeyValuesArray:data];
                
                [self.contractDataArray addObjectsFromArray:dataArr];
                [_rightTableView reloadData];
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

-(void)setWalletContractModel:(WalletContractCoinModel*)model WalletManageContractTableViewCell:(WalletManageContractTableViewCell*)cell {
    
    cell.mtitlelabel.text=[NSString stringWithFormat:@"%@ %@",model.contractCoin.symbol,LocalizationKey(@"tv_constract")];
    //账户权益
    float onePrice =  model.usdtBalance.doubleValue+model.usdtFrozenBalance.doubleValue+model.usdtBuyPrincipalAmount.doubleValue+model.usdtSellPrincipalAmount.doubleValue+model.usdtTotalProfitAndLoss.doubleValue;
    //可用保证金
    float twoPrice=0;
    if (model.usdtTotalProfitAndLoss.doubleValue<0&&[model.usdtPattern isEqualToString:@"CROSSED"]) {
        twoPrice= model.usdtBalance.doubleValue+model.usdtTotalProfitAndLoss.doubleValue;
    }else
        twoPrice=model.usdtBalance.doubleValue;
    
    //已用保证金
    float threePrice= model.usdtBuyPrincipalAmount.doubleValue+model.usdtSellPrincipalAmount.doubleValue;
    //冻结保证金
    float fourPrice=model.usdtFrozenBalance.doubleValue;
    
    NSString *one=[NSString stringWithFormat:@"%.4f", onePrice];
    NSString *two=[NSString stringWithFormat:@"%.4f", twoPrice];
    NSString *three=[NSString stringWithFormat:@"%.4f", threePrice];
    NSString *four=[NSString stringWithFormat:@"%.4f", fourPrice];
    
    cell.onelabel.text=[ToolUtil judgeStringForDecimalPlaces:[NSString stringWithFormat:@"%@",one]];    //[ToolUtil stringFromNumber:onePrice withlimit:6];
    cell.twolabel.text=[ToolUtil judgeStringForDecimalPlaces:[NSString stringWithFormat:@"%@",two]];  //[ToolUtil stringFromNumber:twoPrice withlimit:6];
    cell.threelabel.text=[ToolUtil judgeStringForDecimalPlaces:[NSString stringWithFormat:@"%@",three]];  //[ToolUtil stringFromNumber:threePrice withlimit:6];
    cell.fourlabel.text=[ToolUtil judgeStringForDecimalPlaces:[NSString stringWithFormat:@"%@",four]];  //[ToolUtil stringFromNumber:fourPrice withlimit:6];
    
}


//根据宽度求高度  content 计算的内容  width 计算的宽度 font字体大小
-(CGFloat)getLabelHeightWithText:(NSString *)text WithotherText:(NSString *)othertext  width:(CGFloat)width font: (CGFloat)font
{
    CGRect rect = [text boundingRectWithSize:CGSizeMake(width, MAXFLOAT) options:NSStringDrawingUsesLineFragmentOrigin attributes:@{NSFontAttributeName:[UIFont systemFontOfSize:font]} context:nil];
    CGRect rect1 = [othertext boundingRectWithSize:CGSizeMake(width, MAXFLOAT) options:NSStringDrawingUsesLineFragmentOrigin attributes:@{NSFontAttributeName:[UIFont systemFontOfSize:font]} context:nil];
    CGFloat lastRect=rect.size.height>=rect1.size.height?rect.size.height:rect1.size.height;
    return lastRect;
}
-(void)viewWillDisappear:(BOOL)animated{
    [super viewWillDisappear:animated];
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
