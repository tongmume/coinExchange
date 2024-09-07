//
//  MineViewController.m
//  digitalCurrency
//
//  Created by sunliang on 2019/1/26.
//  Copyright © 2019年 BIZZAN. All rights reserved.
//

#import "MineViewController.h"
#import "MineTableViewCell.h"
#import "AccountSettingViewController.h"
#import "MyBillViewController.h"
#import "MyAdvertisingViewController.h"
#import "SettingCenterViewController.h"
#import "WalletManageViewController.h"
#import "IdentityAuthenticationViewController.h"
#import "GestureViewController.h"
#import "MineNetManager.h"
#import "AccountSettingInfoModel.h"
#import "UIImageView+WebCache.h"
#import "WalletManageModel.h"
#import "NSUserDefaultUtil.h"
#import "MyEntrustViewController.h"
#import "VersionUpdateModel.h"
#import "MineTableHeadView.h"
#import "LoginNetManager.h"
#import "MyassetsTableViewCell.h"
#import "CurrencyexchangeTableViewCell.h"
#import "AdministrationTableViewCell.h"
#import "WalletManageDetailViewController.h"
#import "PaymentAccountViewController.h"
#import "EntrustmentRecordViewController.h"
#import "MineInvitationTableViewCell.h"
#import "MoneyPasswordViewController.h"
#import "BZShareView.h"

@interface MineViewController ()<UITableViewDataSource,UITableViewDelegate,chatSocketDelegate>{
    BOOL updateFlag;
    UIView *_tableHeadView;
    UIView *_tableFootView;
    BOOL _isPush;//防止每次点击进入出现动画
    BOOL _realVerified;
    BOOL _fundsVerified;
    RKNotificationHub *_updateHub;
}
@property (weak, nonatomic) IBOutlet UITableView *tableView;
@property (weak, nonatomic) IBOutlet NSLayoutConstraint *topViewHeight;
@property (nonatomic, strong) AccountSettingInfoModel *accountInfo;
@property (nonatomic, strong) NSMutableArray *assetTotalArr;
@property (nonatomic, copy) NSString *assetUSD;
@property (nonatomic, copy) NSString *assetCNY;
@property (nonatomic, strong) VersionUpdateModel *versionModel;
@property (nonatomic, strong) MineTableHeadView *headerView;
@property (nonatomic, assign) NSInteger memberLevel;
@property (nonatomic, strong) NSDictionary *promotionDict;
@end

@implementation MineViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    self.view.backgroundColor = mainColor;
    [[NSNotificationCenter defaultCenter] addObserver:self selector:@selector(changeState) name:@"pushToMine" object:nil];
    
    if (@available(iOS 11.0, *)) {
        self.tableView.contentInsetAdjustmentBehavior = UIScrollViewContentInsetAdjustmentNever;
    } else {
        // Fallback on earlier versions
        self.automaticallyAdjustsScrollViewInsets = false;
    }
    
    self.edgesForExtendedLayout = UIRectEdgeNone;
    self.tableView.delegate = self;
    self.tableView.dataSource = self;
    [self.tableView registerNib:[UINib nibWithNibName:@"MineTableViewCell" bundle:nil] forCellReuseIdentifier:NSStringFromClass([MineTableViewCell class])];
    [self.tableView registerNib:[UINib nibWithNibName:@"MyassetsTableViewCell" bundle:nil] forCellReuseIdentifier:@"MyassetsTableViewCell"];
    [self.tableView registerNib:[UINib nibWithNibName:@"CurrencyexchangeTableViewCell" bundle:nil] forCellReuseIdentifier:@"CurrencyexchangeTableViewCell"];
    [self.tableView registerNib:[UINib nibWithNibName:@"AdministrationTableViewCell" bundle:nil] forCellReuseIdentifier:@"AdministrationTableViewCell"];
    _tableHeadView = [[UIView alloc]initWithFrame:CGRectMake(0, 0, kWindowW, 200 * kWindowWHOne + (kWindowH == 812.0 ? 24 : 0))];
    self.tableView.tableHeaderView = _tableHeadView;
    self.headerView=[[[MineTableHeadView alloc]init] instancetableHeardViewWithFrame:_tableHeadView.frame];
    _updateHub = [[RKNotificationHub alloc] initWithView:self.headerView.setbutton];
    [_updateHub scaleCircleSizeBy:0.3];
    [_updateHub hideCount];
    [self.headerView.headButton addTarget:self action:@selector(btnClick:) forControlEvents:UIControlEventTouchUpInside];
    self.headerView.headButton.tag = 1;
    
    [self.headerView.setbutton addTarget:self action:@selector(btnClick:) forControlEvents:UIControlEventTouchUpInside];
    self.headerView.setbutton.tag = 2;
    
    [self.headerView.safebutton addTarget:self action:@selector(btnClick:) forControlEvents:UIControlEventTouchUpInside];
    self.headerView.safebutton.tag = 3;
    
    [_tableHeadView addSubview:self.headerView];
    self.assetTotalArr = [[NSMutableArray alloc] init];
    //language
    [[NSNotificationCenter defaultCenter] addObserver:self selector:@selector(languageSetting)name:LanguageChange object:nil];
}

#pragma mark - 版本更新接口请求
//MARK:--版本更新接口请求
-(void)versionUpdate{
    [MineNetManager versionUpdateForId:@"1" CompleteHandle:^(id resPonseObj, int code) {
        NSLog(@"版本更新接口请求 --- %@",resPonseObj);
        if (code) {
            if ([resPonseObj[@"code"] integerValue] == 0) {
                self.versionModel = [VersionUpdateModel mj_objectWithKeyValues:resPonseObj[@"data"]];
                // app当前版本
                NSDictionary *infoDictionary = [[NSBundle mainBundle] infoDictionary];
                NSString *app_Version = [infoDictionary objectForKey:@"CFBundleShortVersionString"];
                NSLog(@"app_Version ---- %@",app_Version);
                if ([app_Version compare:_versionModel.version] == NSOrderedSame ||[app_Version compare:_versionModel.version] == NSOrderedDescending) {
                    //不需要更新
                    updateFlag = NO;
                    [_updateHub decrementBy:0];
                }else{
                    [_updateHub increment];
                    updateFlag = YES;
                }
                if (!kUpdateAppStore) {
                    
                    [self.tableView reloadData];
                }
                
            }else if ([resPonseObj[@"code"] integerValue]==4000||[resPonseObj[@"code"] integerValue]==3000){
                [YLUserInfo logout];
                
            }else if ([resPonseObj[@"code"] integerValue] == 500) {
                //无版本更新，不提示
            }else{
                
            }
        }else{
            
        }
    }];
    
}
//MARK:--国际化通知处理事件
- (void)languageSetting{
    
    [self.tableView reloadData];
}
-(void)dealloc{
    [[NSNotificationCenter defaultCenter] removeObserver:self name:LanguageChange object:nil];
}

- (void)changeState{
    _isPush = NO;
}

-(void)viewWillAppear:(BOOL)animated{
    [super viewWillAppear:animated];
    [self versionUpdate];
    
    if (!_isPush) {
        [self.navigationController setNavigationBarHidden:YES animated:NO];
    }else{
        [self.navigationController setNavigationBarHidden:YES animated:YES];
    }
    if(![YLUserInfo isLogIn]){
        _tableFootView.hidden = YES;
        //没登录不做处理
        self.headerView.headImage.image = [UIImage imageNamed:@"defaultImage"];
        self.headerView.userName.text = [[ChangeLanguage bundle] localizedStringForKey:@"userName" value:nil table:@"English"];
        self.headerView.account.text = [[ChangeLanguage bundle] localizedStringForKey:@"accounting" value:nil table:@"English"];
        
    }else{
        _tableFootView.hidden = NO;
        [self accountSettingData];
        [self getTotalAssets];
        [self businessstatus];
        [self.tableView reloadData];
    }
}
-(NSArray *)getImageArr{
    NSArray * imageArr = @[@"orderImage",@"advertisingImage",@"entrustImage",@"promoteImage",@"safeImage",@"setting",@"versionUpdate"];
    return imageArr;
}
-(NSArray *)getNameArr{
    
    NSArray * nameArr = @[LocalizationKey(@"myBill"),[[ChangeLanguage bundle] localizedStringForKey:@"myAdvertising" value:nil table:@"English"],[[ChangeLanguage bundle] localizedStringForKey:@"myEntrust" value:nil table:@"English"],LocalizationKey(@"myPromotion"),[[ChangeLanguage bundle] localizedStringForKey:@"safeCenter" value:nil table:@"English"],[[ChangeLanguage bundle] localizedStringForKey:@"settingCenter" value:nil table:@"English"],LocalizationKey(@"versionUpdate")];
    return nameArr;
}
-(NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section{
    
    return 1;
}

-(NSInteger)numberOfSectionsInTableView:(UITableView *)tableView{
    return 3;
}

- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath
{
    __weak MineViewController (* weakself) = self;
    
    if (indexPath.section == 0) {
        MyassetsTableViewCell *cell = [tableView dequeueReusableCellWithIdentifier:@"MyassetsTableViewCell" forIndexPath:indexPath];
        
        cell.titleview.text = LocalizationKey(@"assets");
        
        cell.selectBlock = ^(NSInteger num) {
            if(![YLUserInfo isLogIn]){
                [self showLoginViewController];
            }else{
                switch (num) {
                    case 0:{
                        //我的资产
                        WalletManageViewController *walletVC = [WalletManageViewController new];
                        walletVC.assetUSD = self.assetUSD;
                        walletVC.assetCNY = self.assetCNY;
                        walletVC.hidesBottomBarWhenPushed = YES;
                        [weakself.navigationController pushViewController:walletVC animated:YES];
                    }
                        
                        break;
                    case 1:{
                        //资产流水
                        WalletManageDetailViewController *detailVC = [[WalletManageDetailViewController alloc] init];
                        detailVC.hidesBottomBarWhenPushed = YES;
                        [self.navigationController pushViewController:detailVC animated:YES];
                    }
                        
                        break;
                    case 2:{
                        //收款设置
                        PaymentAccountViewController *payVC = [[PaymentAccountViewController alloc] init];
                        payVC.hidesBottomBarWhenPushed = YES;
                        [self.navigationController pushViewController:payVC animated:YES];
                    }
                        break;
                    case 3:{
                        //资金密码
                        
                        MoneyPasswordViewController *moneyVC = [[MoneyPasswordViewController alloc] init];
                        moneyVC.hidesBottomBarWhenPushed = YES;
                        if (_fundsVerified == YES) {
                            moneyVC.setStatus = 1;
                        }else{
                            moneyVC.setStatus = 0;
                        }
                        [self.navigationController pushViewController:moneyVC animated:YES];
                    }
                        break;
                    default:
                        break;
                }
            }
            
        };
        return cell;
    }
    else if (indexPath.section==1){
        
        MineInvitationTableViewCell*cell=[tableView dequeueReusableCellWithIdentifier:@"MineInvitationTableViewCell"];
        
        if (!cell) {
            cell=[[MineInvitationTableViewCell alloc]initWithStyle:UITableViewCellStyleDefault reuseIdentifier:@"MineInvitationTableViewCell"];
            [cell.myInvitationBtn addTarget:self action:@selector(fuzhibtnclick:) forControlEvents:UIControlEventTouchUpInside];
            cell.selectionStyle=UITableViewCellSelectionStyleNone;
        }
        cell.mtitlelabel.text=LocalizationKey(@"myInvitation");
        cell.tiplabel1.text=LocalizationKey(@"first");
        cell.tiplabel2.text=LocalizationKey(@"second");
        cell.tiplabel3.text=LocalizationKey(@"commission");
        cell.tiplabel4.text=LocalizationKey(@"level2");
        [cell.myInvitationBtn setTitle:LocalizationKey(@"myInvitationLink") forState:UIControlStateNormal];
        
        
        if (self.promotionDict&&self.promotionDict.allKeys.count!=0) {
            
            if ([YLUserInfo isLogIn]) {
                
                cell.titlelabel1.text=[NSString stringWithFormat:@"%@",self.promotionDict[@"levelOne"]];
                cell.titlelabel2.text=[NSString stringWithFormat:@"%@",self.promotionDict[@"levelTwo"]];
                cell.titlelabel3.text=[NSString stringWithFormat:@"%.3f",[self.promotionDict[@"estimatedReward"] doubleValue]];
                cell.titlelbal4.text=[NSString stringWithFormat:@"Lv%@",[YLUserInfo shareUserInfo].memberLevel];
                
                
            }else{
                cell.titlelabel1.text=@"0";
                cell.titlelabel2.text=@"0";
                cell.titlelabel3.text=@"0.000";
                cell.titlelbal4.text=@"Lv0";
                self.promotionDict=nil;
            }
        }
        
        return cell;
        
    }else if (indexPath.section == 2){
        CurrencyexchangeTableViewCell *cell = [tableView dequeueReusableCellWithIdentifier:@"CurrencyexchangeTableViewCell" forIndexPath:indexPath];
        cell.title.text = LocalizationKey(@"Currency management");
        cell.title1.text = LocalizationKey(@"Click to enter");
        return cell;
    }else{
        AdministrationTableViewCell *cell = [tableView dequeueReusableCellWithIdentifier:@"AdministrationTableViewCell" forIndexPath:indexPath];
        cell.titlelabel.text = LocalizationKey(@"management");
        [cell.Advertisementbut setTitle:LocalizationKey(@"myAdvertising") forState:UIControlStateNormal];
        [cell.orderbut setTitle:LocalizationKey(@"myBill") forState:UIControlStateNormal];
        cell.butBlock = ^(NSInteger num) {
            if(![YLUserInfo isLogIn]){
                [self showLoginViewController];
            }else{
                if (num == 0) {
                    if (self.memberLevel != 2) {
                        [self.view makeToast:LocalizationKey(@"Uncertifiedbusinesses") duration:1.5 position:CSToastPositionCenter];
                        return;
                    }
                    //我的广告
                    MyAdvertisingViewController *advertisingVC = [[MyAdvertisingViewController alloc] init];
                    advertisingVC.avatar=self.accountInfo.avatar;
                    advertisingVC.hidesBottomBarWhenPushed = YES;
                    [self.navigationController pushViewController:advertisingVC animated:YES];
                }else{
                    //我的订单
                    MyBillViewController *billVC = [[MyBillViewController alloc] init];
                    billVC.hidesBottomBarWhenPushed = YES;
                    [self.navigationController pushViewController:billVC animated:YES];
                }
            }
            
        };
        return cell;
    }
    
}

//MARK:--头像点击事件  账号设置
- (IBAction)btnClick:(UIButton *)sender {
    
    
    //判断用户是否已经登录
    if(![YLUserInfo isLogIn]){
        [self showLoginViewController];
    }else{
        if (sender.tag == 1) {
            if (self.accountInfo == nil) {
                return;
            }
            //收款方式
            if (!_realVerified) {
                [self.view makeToast:LocalizationKey(@"validateYourID") duration:1.5 position:CSToastPositionCenter];
                return;
            }
            if (!_fundsVerified) {
                [self.view makeToast:LocalizationKey(@"bindingPwd") duration:1.5 position:CSToastPositionCenter];
                return;
            }
            PaymentAccountViewController *payVC = [[PaymentAccountViewController alloc] init];
            [[AppDelegate sharedAppDelegate] pushViewController:payVC];
            
        }else if (sender.tag == 2){
            //账户设置
            SettingCenterViewController *set = [[SettingCenterViewController alloc] init];
            [[AppDelegate sharedAppDelegate] pushViewController:set];
            
        }else if (sender.tag == 3){
            //安全设置
            AccountSettingViewController *accountVC = [[AccountSettingViewController alloc] init];
            [[AppDelegate sharedAppDelegate] pushViewController:accountVC];
        }
    }
}

-(void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath{
    
    if (indexPath.section == 2) {
        if(![YLUserInfo isLogIn]){
            [self showLoginViewController];
        }else{
            EntrustmentRecordViewController *record = [[EntrustmentRecordViewController alloc] init];
            //            record.symbol = [marketManager shareInstance].symbol;
            [[AppDelegate sharedAppDelegate] pushViewController:record];
            
        }
    }
}
-(CGFloat)tableView:(UITableView *)tableView heightForRowAtIndexPath:(NSIndexPath *)indexPath{
    if (indexPath.section == 0) {
        if (kWindowW == 320) {
            return  110;
        }
        return 135 * kWindowWHOne;
        
        
    }else if (indexPath.section == 1){
        return kWindowW * 0.95 * 136 / 355;
    }else{
        return 110 * kWindowWHOne;
    }
}

//MARK:--请求总资产的接口
-(void)getTotalAssets{
    //    [EasyShowLodingView showLodingText:[[ChangeLanguage bundle] localizedStringForKey:@"loading" value:nil table:@"English"]];
    [MineNetManager getMyWalletInfoForCompleteHandle:^(id resPonseObj, int code) {
        //        [EasyShowLodingView hidenLoding];
        if (code) {
            if ([resPonseObj[@"code"] integerValue] == 0) {
                [self.assetTotalArr removeAllObjects];
                NSArray *dataArr = [WalletManageModel mj_objectArrayWithKeyValuesArray:resPonseObj[@"data"]];
                CGFloat ass1 = 0.0;
                CGFloat ass2 = 0.0;
                for (WalletManageModel *walletModel in dataArr) {
                    //计算总资产
                    ass1 = ass1 +[walletModel.balance floatValue]*[walletModel.coin.usdRate floatValue];
                    ass2 = ass2 +[walletModel.balance floatValue]*[walletModel.coin.cnyRate floatValue];
                    
                    [self.assetTotalArr addObject:walletModel];
                }
                
                self.assetUSD = [NSString stringWithFormat:@"%f",ass1];
                self.assetCNY  = [NSString stringWithFormat:@"≈%.2fCNY",ass2];
            }else{
                [self.view makeToast:resPonseObj[MESSAGE] duration:1.5 position:CSToastPositionCenter];
            }
        }else{
            [self.view makeToast:[[ChangeLanguage bundle] localizedStringForKey:@"noNetworkStatus" value:nil table:@"English"] duration:1.5 position:CSToastPositionCenter];
        }
    }];
}
//MARK:--账号设置的状态信息获取
-(void)accountSettingData{
    
    //    [EasyShowLodingView showLodingText:[[ChangeLanguage bundle] localizedStringForKey:@"loading" value:nil table:@"English"]];
    [MineNetManager accountSettingInfoForCompleteHandle:^(id resPonseObj, int code) {
        //        [EasyShowLodingView hidenLoding];
        if (code) {
            if ([resPonseObj[@"code"] integerValue] == 0) {
                
                self.accountInfo = [AccountSettingInfoModel mj_objectWithKeyValues:resPonseObj[@"data"]];
                
                if (![self.accountInfo.avatar isEqualToString:[YLUserInfo shareUserInfo].avatar]) {
                    //保存头像
                    [YLUserInfo shareUserInfo].avatar = self.accountInfo.avatar;
                    [YLUserInfo saveUser:[YLUserInfo shareUserInfo]];
                }
                [self getAccountSettingStatus];
            }else if ([resPonseObj[@"code"] integerValue]==4000){
                [YLUserInfo logout];
                //[ShowLoGinVC showLoginVc:self withTipMessage:resPonseObj[MESSAGE]];
                
            }else{
                [self.view makeToast:resPonseObj[MESSAGE] duration:1.5 position:CSToastPositionCenter];
            }
        }else{
            [self.view makeToast:[[ChangeLanguage bundle] localizedStringForKey:@"noNetworkStatus" value:nil table:@"English"] duration:1.5 position:CSToastPositionCenter];
        }
    }];
}


//验证用户是否为验证商家
-(void)businessstatus{
    
    [MineNetManager userbusinessstatus:^(id resPonseObj, int code) {
        if (code) {
            if ([resPonseObj[@"code"] integerValue] == 0) {
                
                self.memberLevel = [[[resPonseObj objectForKey:@"data"] objectForKey:@"certifiedBusinessStatus"] integerValue];
            }else{
                [self.view makeToast:resPonseObj[MESSAGE] duration:1.5 position:CSToastPositionCenter];
            }
        }else{
            [self.view makeToast:LocalizationKey(@"noNetworkStatus") duration:1.5 position:CSToastPositionCenter];
        }
    }];
}

//MARK:--整理账号设置的信息状态
-(void)getAccountSettingStatus{
    if (self.accountInfo.avatar == nil || [self.accountInfo.avatar isEqualToString:@""]) {
    }else{
        NSURL *headUrl = [NSURL URLWithString:self.accountInfo.avatar];
        [self.headerView.headImage sd_setImageWithURL:headUrl];
    }
    self.headerView.userName.text = self.accountInfo.username;
    self.headerView.account.text = self.accountInfo.mobilePhone;
    
    
    if ([_accountInfo.fundsVerified isEqualToString:@"1"]) {
        _fundsVerified = YES;
    }else{
        _fundsVerified = NO;
    }
    
    if ([_accountInfo.realVerified isEqualToString:@"1"]) {
        //审核成功
        _realVerified = YES;
    }else{
        _realVerified = NO;
    }
    
}

- (void)fuzhibtnclick:(UIButton *)button {
    
    NSString *urlLink=[NSString stringWithFormat:@"%@%@",[YLUserInfo shareUserInfo].promotionPrefix,[YLUserInfo shareUserInfo].promotionCode];
    [BZShareView showWithUrl:urlLink];
}

-(void)viewWillDisappear:(BOOL)animated{
    [super viewWillDisappear:animated];
    _isPush = YES;
    [self.navigationController setNavigationBarHidden:NO animated:YES];
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
