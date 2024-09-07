//
//  BuyCoinsDetailViewController.m
//  digitalCurrency
//
//  Created by iDog on 2019/1/31.
//  Copyright © 2019年 BIZZAN. All rights reserved.
//

#import "BuyCoinsDetailViewController.h"
#import "SearchViewController.h"
#import "BuyCoinsDetail1TableViewCell.h"
#import "BuyCoinsDetail2TableViewCell.h"
#import "OrderConfirmAlterView.h"
#import "C2CNetManager.h"
#import "BuyOrSellCoinInfo.h"
#import "MineViewController.h"
#import "MyBillViewController.h"
#import "YLTabBarController.h"
#import "AppDelegate.h"
@interface BuyCoinsDetailViewController ()<UITableViewDelegate,UITableViewDataSource,BuyCoinsDetail2TableViewCellDelegate>{
    OrderConfirmAlterView *_orderComfirmAlterView;
}
@property (weak, nonatomic) IBOutlet UIButton *buyButton;//购买按钮
@property (weak, nonatomic) IBOutlet NSLayoutConstraint *bottomViewheight;
@property (nonatomic,strong)BuyOrSellCoinInfo *model;
@property(nonatomic,strong)UITableView *tableView;

@property(nonatomic,copy)NSString *coinType1Num;//货币1数量
@property(nonatomic,copy)NSString *coinType2Num;//货币2数量
@property(nonatomic,copy)NSString *mode;//计算方式，金额/价格=数量为0，数量*价格=金额为1

@property(nonatomic,strong)UITextField*coinType1NumTF;
@property(nonatomic,strong)UITextField*coinType2NumTF;
@property(nonatomic,strong)NSArray*payArray;//付款方式
@end

@implementation BuyCoinsDetailViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    self.view.backgroundColor =RGBCOLOR(18, 22, 28);
    self.edgesForExtendedLayout = UIRectEdgeNone;
    self.buyButton.layer.cornerRadius = 25;

    if (self.flagindex == 1) {
        //对我而言出售
        self.title = [NSString stringWithFormat:@"%@%@",[[ChangeLanguage bundle] localizedStringForKey:@"sell" value:nil table:@"English"],self.unit];
        [self.buyButton setTitle:[[ChangeLanguage bundle] localizedStringForKey:@"sell" value:nil table:@"English"] forState:UIControlStateNormal];
    }else{
        //对我而言购买
        self.title = [NSString stringWithFormat:@"%@%@",[[ChangeLanguage bundle] localizedStringForKey:@"buy" value:nil table:@"English"],self.unit];
        [self.buyButton setTitle:[[ChangeLanguage bundle] localizedStringForKey:@"buy" value:nil table:@"English"] forState:UIControlStateNormal];
    }
    //[self setNavBarUI];
    [self.view addSubview:self.tableView];
    self.bottomViewheight.constant = SafeAreaBottomHeight + 10;
    // Do any additional setup after loading the view from its nib.
    self.coinType2Num = @"";
    self.coinType1Num = @"";
    [self getBuyAdvertiseInfo];
}
//MARK:--获取购买的广告信息
-(void)getBuyAdvertiseInfo{
    [EasyShowLodingView showLodingText:[[ChangeLanguage bundle] localizedStringForKey:@"loading" value:nil table:@"English"]];
    [C2CNetManager buyOrSelladvertiseInfoForid:_advertisingId CompleteHandle:^(id resPonseObj, int code) {
        NSLog(@"获取购买的广告信息 --- %@",resPonseObj);
        [EasyShowLodingView hidenLoding];
        if (code){
            if ([resPonseObj[@"code"] integerValue]==0) {
                //获取数据成功
                BuyOrSellCoinInfo *model = [BuyOrSellCoinInfo mj_objectWithKeyValues:resPonseObj[@"data"]];
                self.model = model;
                self.payArray=[model.payMode componentsSeparatedByString:@","];
                [self.tableView reloadData];
            }else if ([resPonseObj[@"code"] integerValue]==4000){
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

//MARK:--购买的点击事件
- (IBAction)buyBtnClick:(UIButton *)sender {

    if ([self.coinType1Num isEqualToString:@""] ||[self.coinType2Num isEqualToString:@""]) {
        if (self.flagindex == 1) {
            //对我而言出售
            [self.view makeToast:[[ChangeLanguage bundle] localizedStringForKey:@"inputSellNum" value:nil table:@"English"] duration:1.5 position:CSToastPositionCenter];
        }else{
          [self.view makeToast:[[ChangeLanguage bundle] localizedStringForKey:@"inputBuyNum" value:nil table:@"English"] duration:1.5 position:CSToastPositionCenter];
        }
        return;
    }
    [self orderConfirmAlterView];
}
//MARK:--点击购买弹出的提示框
-(void)orderConfirmAlterView{

    if (!_orderComfirmAlterView) {
        _orderComfirmAlterView = [[NSBundle mainBundle] loadNibNamed:@"OrderConfirmAlterView" owner:nil options:nil].firstObject;
        _orderComfirmAlterView.frame=[UIScreen mainScreen].bounds;
        [_orderComfirmAlterView.cancelButton addTarget:self action:@selector(push:) forControlEvents:UIControlEventTouchUpInside];
        [_orderComfirmAlterView.certainButton addTarget:self action:@selector(push:) forControlEvents:UIControlEventTouchUpInside];

    }

    if (self.flagindex == 1) {
        //对我而言出售
        _orderComfirmAlterView.titleLabel1.text=[[ChangeLanguage bundle] localizedStringForKey:@"sellPrice" value:nil table:@"English"];
        _orderComfirmAlterView.titleLabel2.text=[[ChangeLanguage bundle] localizedStringForKey:@"sellNum" value:nil table:@"English"];
        _orderComfirmAlterView.titleLabel3.text=[[ChangeLanguage bundle] localizedStringForKey:@"sellTotalPrice" value:nil table:@"English"];
    }else{
        _orderComfirmAlterView.titleLabel1.text=[[ChangeLanguage bundle] localizedStringForKey:@"buyPrice" value:nil table:@"English"];
        _orderComfirmAlterView.titleLabel2.text=[[ChangeLanguage bundle] localizedStringForKey:@"buyNum" value:nil table:@"English"];
        _orderComfirmAlterView.titleLabel3.text=[[ChangeLanguage bundle] localizedStringForKey:@"buyTotalPrice" value:nil table:@"English"];
    }
    _orderComfirmAlterView.buyPrice.text = [NSString stringWithFormat:@"%@CNY",self.model.price];
    _orderComfirmAlterView.buyNum.text = [NSString stringWithFormat:@"%@%@",self.coinType2Num,self.model.unit];
    _orderComfirmAlterView.buyTotal.text = [NSString stringWithFormat:@"%@CNY",self.coinType1Num];
    _orderComfirmAlterView.remindContent.text = [NSString stringWithFormat:@"%@%@%@",[[ChangeLanguage bundle] localizedStringForKey:@"placeOrderTip1" value:nil table:@"English"],self.model.unit,[[ChangeLanguage bundle] localizedStringForKey:@"placeOrderTip2" value:nil table:@"English"]];
    CGAffineTransform translates = CGAffineTransformTranslate(CGAffineTransformIdentity, 0, 0);
    _orderComfirmAlterView.boardView.transform = CGAffineTransformTranslate(CGAffineTransformIdentity,0,_orderComfirmAlterView.boardView.height);
    [UIView animateWithDuration:0.3 delay:0.1 usingSpringWithDamping:1 initialSpringVelocity:10 options:UIViewAnimationOptionCurveLinear animations:^{
        _orderComfirmAlterView.boardView.transform = translates;

    } completion:^(BOOL finished) {

    }];

    [UIApplication.sharedApplication.keyWindow addSubview:_orderComfirmAlterView];
}
-(void)push:(UIButton*)sender{
    [_orderComfirmAlterView removeFromSuperview];
    if (sender.tag == 1) {
        //取消
    }else if (sender.tag == 2){
        //确定
        [self certainBillAndSubmit];
    }else{
        //其他
    }
}
//MARK:--下单确认提交
-(void)certainBillAndSubmit{
   [EasyShowLodingView showLodingText:[[ChangeLanguage bundle] localizedStringForKey:@"loading" value:nil table:@"English"]];
    NSString *urlStr = @"";
    if (self.flagindex == 1) {
        //对我来说出售
        urlStr = @"otc/order/sell";
    }else{
        //对我来说购买
        urlStr = @"otc/order/buy";
    }
    [C2CNetManager coinSellOrBuyForUrlString:urlStr withAdvertisingId:self.advertisingId withCoinId:self.model.otcCoinId withPrice:self.model.price withMoney:self.coinType1Num withAmount:self.coinType2Num withRemark:self.model.remark withMode:self.mode CompleteHandle:^(id resPonseObj, int code) {
        [EasyShowLodingView hidenLoding];
        if (code){
            if ([resPonseObj[@"code"] integerValue]==0) {
                //获取数据成功
                [self.view makeToast:resPonseObj[MESSAGE] duration:1.0 position:CSToastPositionCenter];
                dispatch_after(dispatch_time(DISPATCH_TIME_NOW, (int64_t)(1.5 * NSEC_PER_SEC)), dispatch_get_main_queue(), ^{

                    [self.navigationController popViewControllerAnimated:YES];
                    AppDelegate *appDelegate = (AppDelegate *)[[UIApplication sharedApplication] delegate];

                    YLTabBarController *tabViewController = (YLTabBarController *) appDelegate.window.rootViewController;

                    [tabViewController setSelectedIndex:3];

                    MyBillViewController *billVC = [[MyBillViewController alloc] init];
                    billVC.hidesBottomBarWhenPushed = YES;
                    [[tabViewController selectedViewController] pushViewController:billVC animated:YES];


                });
            }else{
                [self.view makeToast:resPonseObj[MESSAGE] duration:1.5 position:CSToastPositionCenter];
            }
        }else{
            [self.view makeToast:[[ChangeLanguage bundle] localizedStringForKey:@"noNetworkStatus" value:nil table:@"English"] duration:1.5 position:CSToastPositionCenter];
        }
    }];
}
-(UITableView *)tableView{
    if (!_tableView) {
        _tableView = [[UITableView alloc] initWithFrame:CGRectMake(0, 0, kWindowW, kWindowH-SafeAreaBottomHeight-50-SafeAreaTopHeight - 10) style:UITableViewStyleGrouped];
        _tableView.backgroundColor=RGBCOLOR(18, 22, 28);
        _tableView.separatorStyle = UITableViewCellSelectionStyleNone;
        _tableView.estimatedSectionHeaderHeight = 0;
        _tableView.estimatedSectionFooterHeight = 0;
        _tableView.estimatedRowHeight = 68;
        _tableView.delegate = self;
        _tableView.dataSource = self;
        [_tableView registerNib:[UINib nibWithNibName:@"BuyCoinsDetail1TableViewCell" bundle:nil] forCellReuseIdentifier:NSStringFromClass([BuyCoinsDetail1TableViewCell class])];
        [_tableView registerNib:[UINib nibWithNibName:@"BuyCoinsDetail2TableViewCell" bundle:nil] forCellReuseIdentifier:NSStringFromClass([BuyCoinsDetail2TableViewCell class])];

    }
    return _tableView;
}
-(NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section{
    return 1;
}
-(NSInteger)numberOfSectionsInTableView:(UITableView *)tableView{
    return 2;
}
-(CGFloat)tableView:(UITableView *)tableView heightForRowAtIndexPath:(NSIndexPath *)indexPath{

    return UITableViewAutomaticDimension;

}
-(CGFloat)tableView:(UITableView *)tableView heightForFooterInSection:(NSInteger)section{

    return 0.01;
}
-(CGFloat)tableView:(UITableView *)tableView heightForHeaderInSection:(NSInteger)section{

    if (section == 0) {
        return 0.01;
    }else{
        return 10;
    }
}
- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath
{
    if (indexPath.section == 0) {
        BuyCoinsDetail1TableViewCell *cell = [tableView dequeueReusableCellWithIdentifier:NSStringFromClass([BuyCoinsDetail1TableViewCell class]) forIndexPath:indexPath];
        cell.selectionStyle = UITableViewCellSelectionStyleNone;
        if (self.headImage == nil || [self.headImage isEqualToString:@""]) {
            cell.headImage.image = [UIImage imageNamed:@"defaultImage"];
        }else{
            NSURL *headUrl = [NSURL URLWithString:self.headImage];
            [cell.headImage sd_setImageWithURL:headUrl];
        }
        if ([self.model.advertiseType isEqualToString:@"1"]) {
            cell.zuanshiWidthConstraints.constant = 14;
        }else{
            cell.zuanshiWidthConstraints.constant = 0;
        }

        cell.userName.text = [NSString stringWithFormat:@"%@**",[self.model.username substringToIndex:1]];
        //交易次数
        cell.tradingNum.text = self.model.transactions;
        //限额
        cell.limitNum.text = [NSString stringWithFormat:@"%@ %@-%@ CNY   ",[[ChangeLanguage bundle] localizedStringForKey:@"limit" value:nil table:@"English"],self.model.minLimit,self.model.maxLimit];
        //单价
        cell.coinNum.text = [NSString stringWithFormat:@"%@CNY",self.model.price];

        cell.message.text = [NSString stringWithFormat:@"%@：%@",[[ChangeLanguage bundle] localizedStringForKey:@"advertisingMessage" value:nil table:@"English"],self.model.remark];
        //剩余数量
        cell.remainAmountLabel.text=[NSString stringWithFormat:@"%@:%@   ",[[ChangeLanguage bundle] localizedStringForKey:@"amonut" value:nil table:@"English"],[ToolUtil judgeStringForDecimalPlaces:self.model.maxTradableAmount]];
        [cell configLabelWithArray:self.payArray];
        return cell;
    }else {
        BuyCoinsDetail2TableViewCell *cell = [tableView dequeueReusableCellWithIdentifier:NSStringFromClass([BuyCoinsDetail2TableViewCell class]) forIndexPath:indexPath];
        cell.selectionStyle = UITableViewCellSelectionStyleNone;
        cell.contentLabel.text = [[ChangeLanguage bundle] localizedStringForKey:@"tradingRemindTip" value:nil table:@"English"];
        cell.delegate = self;
        if (self.flagindex == 1) {
            //买币 对我而言就是出售
            cell.tipstring.text = [[ChangeLanguage bundle] localizedStringForKey:@"sellNumTip" value:nil table:@"English"];
        }else {
             //卖币 对我而言就是购买
            cell.tipstring.text = [[ChangeLanguage bundle] localizedStringForKey:@"buyNumTip" value:nil table:@"English"];
        }
        cell.tradTipLabel.text = LocalizationKey(@"tradingRemind");
        cell.coinType1.text = @"CNY";
        cell.coinType2.text = self.model.unit;
        cell.coinType1Num.text = self.coinType1Num;
        cell.coinType2Num.text = self.coinType2Num;
        self.coinType1NumTF= cell.coinType1Num;
        self.coinType2NumTF= cell.coinType2Num;
        return cell;
    }
}

-(void)textFieldTag:(NSInteger)index TextFieldString:(NSString *)textString{
    if (index == 1) {
//        NSLog(@"货币种类1数量");
        self.coinType1Num = textString;
        self.coinType2Num = [NSString stringWithFormat:@"%.8f",[textString doubleValue]/[self.model.price doubleValue]];
        self.mode = @"0";
        self.coinType1NumTF.text= textString;
        self.coinType2NumTF.text= self.coinType2Num;
    }else{
//        NSLog(@"货币种类2数量");
        self.coinType2Num = textString;
        self.coinType1Num = [NSString stringWithFormat:@"%.2f",[textString doubleValue]*[self.model.price doubleValue]];
        self.mode = @"1";
        self.coinType1NumTF.text= self.coinType1Num ;
        self.coinType2NumTF.text= textString;
    }
   // [self.tableView reloadData];
}
//MARK:--导航栏的设置
-(void)setNavBarUI{

    UIButton *rightButton1 = [[UIButton alloc]initWithFrame:CGRectMake(20, 10, 20, 20)];
    [rightButton1 addTarget:self action:@selector(BtnClick:) forControlEvents:UIControlEventTouchUpInside];
    rightButton1.tag = 1;
    [rightButton1 setImage:[UIImage imageNamed:@"searchImage"] forState:UIControlStateNormal];

    UIBarButtonItem *rightBarButton1 = [[UIBarButtonItem alloc]initWithCustomView:rightButton1];
    UIButton *rightButton2 = [[UIButton alloc]initWithFrame:CGRectMake(20, 10, 60, 15)];
    [rightButton2 addTarget:self action:@selector(BtnClick:) forControlEvents:UIControlEventTouchUpInside];
    rightButton2.tag = 2;
    [rightButton2 setImage:[UIImage imageNamed:@"pullImage.png"] forState:UIControlStateNormal];
    [rightButton2 setTitle:@"中国" forState:UIControlStateNormal];
    UIBarButtonItem *rightBarButton2 = [[UIBarButtonItem alloc]initWithCustomView:rightButton2];
//    self.navigationItem.leftBarButtonItem = rightBarButton;
    self.navigationItem.rightBarButtonItems = @[rightBarButton2,rightBarButton1];
}
//MARK:--选择国家的点击事件
-(void)BtnClick:(UIButton *)button{

    if (button.tag == 1) {
        //搜索点击事件
        SearchViewController *searchVC = [[SearchViewController alloc] init];
        searchVC.hidesBottomBarWhenPushed = YES;
        [self.navigationController pushViewController:searchVC animated:YES];
    }else if (button.tag == 2){
        //国家点击事件

    }
}


@end
