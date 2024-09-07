//
//  AdvertisingBuyViewController.m
//  digitalCurrency
//
//  Created by iDog on 2019/1/31.
//  Copyright © 2019年 BIZZAN. All rights reserved.
//

#import "AdvertisingBuyViewController.h"
#import "AccountSettingTableViewCell.h"
#import "Advertising1TableViewCell.h"
#import "Adversiting2TableViewCell.h"
#import "C2CNetManager.h"
#import "SelectCoinTypeModel.h"
#import "SelectCoinTypeView.h"
#import "countryViewController.h"
#import "Adversiting3TableViewCell.h"
#import "Adversiting4TableViewCell.h"
#import "MyAdvertisingViewController.h"

@interface AdvertisingBuyViewController ()<UITableViewDelegate,UITableViewDataSource,Advertising1TableViewCellDelegate,Adversiting2TableViewCellDelegate,SelectCoinTypeViewDelegate,Adversiting4TableViewCellDelegate,UITextFieldDelegate>{
    SelectCoinTypeView *_selectTypeView;
    NSString*_actualPrice;//实时价格
}
@property (weak, nonatomic) IBOutlet UITableView *mainTableView;
@property (weak, nonatomic) IBOutlet NSLayoutConstraint *bottomViewHeight;
@property (weak, nonatomic) IBOutlet UIButton *certainButton;
@property(nonatomic,strong)NSMutableArray *contentArr;

@property (nonatomic,strong)NSMutableArray *selectCoinTypeArr;
@property(nonatomic,copy)NSString *coinId;//选择货币的id
@property(nonatomic,strong)NSMutableArray *payWays; //支付方式ID
@property(nonatomic,assign)NSInteger enterWays;//进入方式 1 选择币种 2付款方式
@end

@implementation AdvertisingBuyViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    self.title = [[ChangeLanguage bundle] localizedStringForKey:@"postPurchaseAdvertising" value:nil table:@"English"];
    [self.certainButton setTitle:[[ChangeLanguage bundle] localizedStringForKey:@"release" value:nil table:@"English"] forState:UIControlStateNormal];
    self.edgesForExtendedLayout = UIRectEdgeNone;
    [self backBtnNoNavBar:NO normalBack:YES];
//  [self RightsetupNavgationItemWithpictureName:@"helpImage"];
    self.bottomViewHeight.constant = SafeAreaBottomHeight+10;
    self.mainTableView.delegate = self;
    self.mainTableView.dataSource = self;
     [self.mainTableView registerNib:[UINib nibWithNibName:@"AccountSettingTableViewCell" bundle:nil] forCellReuseIdentifier:NSStringFromClass([AccountSettingTableViewCell class])];
    [self.mainTableView registerNib:[UINib nibWithNibName:@"Advertising1TableViewCell" bundle:nil] forCellReuseIdentifier:NSStringFromClass([Advertising1TableViewCell class])];
    [self.mainTableView registerNib:[UINib nibWithNibName:@"Adversiting2TableViewCell" bundle:nil] forCellReuseIdentifier:NSStringFromClass([Adversiting2TableViewCell class])];
    [self.mainTableView registerNib:[UINib nibWithNibName:@"Adversiting3TableViewCell" bundle:nil] forCellReuseIdentifier:NSStringFromClass([Adversiting3TableViewCell class])];
    [self.mainTableView registerNib:[UINib nibWithNibName:@"Adversiting4TableViewCell" bundle:nil] forCellReuseIdentifier:NSStringFromClass([Adversiting4TableViewCell class])];

    _actualPrice=@"";

    if (_index == 1) {
        [self.certainButton setTitle:[[ChangeLanguage bundle] localizedStringForKey:@"modifiedRelease" value:nil table:@"English"] forState:UIControlStateNormal];
        if (!_contentArr) {
            _contentArr = [NSMutableArray arrayWithArray:@[[[ChangeLanguage bundle] localizedStringForKey:@"select" value:nil table:@"English"],@"中国",@"RMB",@"",@"MUTATIVE",@"",@"",@"",@"",@"",[[ChangeLanguage bundle] localizedStringForKey:@"select" value:nil table:@"English"],@"",@"",@"0",@"",@""]];
             [self editorInfo];
        }
    }

}
//MARK:--编辑界面过来进行处理
-(void)editorInfo{
    _actualPrice = _detailModel.marketPrice;
    [_contentArr replaceObjectAtIndex:0 withObject:_detailModel.coinUnit];
    [_contentArr replaceObjectAtIndex:3 withObject:_detailModel.marketPrice];
    self.coinId = _detailModel.coinId;
    [_contentArr replaceObjectAtIndex:1 withObject:_detailModel.country.zhName];
    [_contentArr replaceObjectAtIndex:2 withObject:_detailModel.country.localCurrency];
    if ([_detailModel.priceType isEqualToString:@"0"]){
        [_contentArr replaceObjectAtIndex:4 withObject:@"REGULAR"];
       [_contentArr replaceObjectAtIndex:6 withObject:_detailModel.price];
    }else{

       [_contentArr replaceObjectAtIndex:4 withObject:@"MUTATIVE"];

        if (_detailModel.premiseRate == nil || [_detailModel.premiseRate floatValue] <= 0) {
            [_contentArr replaceObjectAtIndex:6 withObject:_actualPrice];
        }else{
        [_contentArr replaceObjectAtIndex:6 withObject:[NSString stringWithFormat:@"%.2f",[_actualPrice floatValue]*[_detailModel.premiseRate floatValue]/100+[_actualPrice floatValue]]];
        }
    }

    if (_detailModel.premiseRate == nil) {
    }else{
        [_contentArr replaceObjectAtIndex:5 withObject:_detailModel.premiseRate];
    }
    [_contentArr replaceObjectAtIndex:7 withObject:_detailModel.minLimit];
    [_contentArr replaceObjectAtIndex:8 withObject:_detailModel.maxLimit];
    [_contentArr replaceObjectAtIndex:9 withObject:_detailModel.number];

    NSArray* array = [_detailModel.payMode componentsSeparatedByString:@","];
    self.payWays = [NSMutableArray arrayWithArray:array];
    NSMutableArray *payWaysArr = [[NSMutableArray alloc] init];
    if (array.count > 0) {
        for (NSString *payStr in array) {
            if ([payStr isEqualToString:@"支付宝"]) {
                [payWaysArr addObject:LocalizationKey(@"alipay")];
            }else if ([payStr isEqualToString:@"微信"]){
               [payWaysArr addObject:LocalizationKey(@"WeChat")];
            }else{
                [payWaysArr addObject:LocalizationKey(@"bankCard")];
            }
        }
    }
    [_contentArr replaceObjectAtIndex:10 withObject:[payWaysArr componentsJoinedByString:@" "]];
//    [_contentArr replaceObjectAtIndex:10 withObject:_detailModel.payMode];
    [_contentArr replaceObjectAtIndex:11 withObject:_detailModel.timeLimit];
    [_contentArr replaceObjectAtIndex:13 withObject:_detailModel.isAuto];
    [_contentArr replaceObjectAtIndex:14 withObject:_detailModel.autoword];
    [_contentArr replaceObjectAtIndex:15 withObject:_detailModel.remark];
    [self.mainTableView reloadData];
}
-(NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section{
    return 16;
}
-(NSInteger)numberOfSectionsInTableView:(UITableView *)tableView{
    return 1;
}
-(CGFloat)tableView:(UITableView *)tableView heightForRowAtIndexPath:(NSIndexPath *)indexPath{
    if (indexPath.row == 14 || indexPath.row == 15) {
        return 100;
    }else{
        return 50;
    }
}
- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath
{
    if (indexPath.row == 0 || indexPath.row == 1 || indexPath.row == 2 || indexPath.row == 10){
        AccountSettingTableViewCell *cell = [tableView dequeueReusableCellWithIdentifier:NSStringFromClass([AccountSettingTableViewCell class])];
        cell.selectionStyle = UITableViewCellSelectionStyleNone;
        cell.leftLabel.text = [self getNameArr][indexPath.row];
        cell.rightLabel.text = [self getContentArr2][indexPath.row];
        return cell;
    }else if (indexPath.row == 3){
        Adversiting3TableViewCell *cell = [tableView dequeueReusableCellWithIdentifier:NSStringFromClass([Adversiting3TableViewCell class])];
        cell.selectionStyle = UITableViewCellSelectionStyleNone;
        cell.leftLabel.text = [self getNameArr][indexPath.row];
        cell.centerLabel.text = [self getContentArr2][indexPath.row];

        return cell;
    }else if (indexPath.row == 14 || indexPath.row == 15){
        Adversiting2TableViewCell *cell = [tableView dequeueReusableCellWithIdentifier:NSStringFromClass([Adversiting2TableViewCell class])];
        cell.selectionStyle = UITableViewCellSelectionStyleNone;
        cell.index = indexPath;
        if(indexPath.row == 14){
            //是否自动回复
            cell.leftLabel.text = [[ChangeLanguage bundle] localizedStringForKey:@"replyContent" value:nil table:@"English"];
            if ([_contentArr[13] isEqualToString:@"0"]) {
                //否，不需要回复
                cell.textView.editable = NO;
            }else{
                cell.textView.editable = YES;
            }
        }else{
           cell.leftLabel.text = [[ChangeLanguage bundle] localizedStringForKey:@"messageContent" value:nil table:@"English"];
            cell.textView.editable = YES;
        }

        cell.textView.text = [self getContentArr2][indexPath.row];
        cell.delegate = self;
        return cell;
    }else if (indexPath.row == 4 || indexPath.row == 13){

        Adversiting4TableViewCell *cell = [tableView dequeueReusableCellWithIdentifier:NSStringFromClass([Adversiting4TableViewCell class])];
        cell.selectionStyle = UITableViewCellSelectionStyleNone;
        cell.index = indexPath;
        cell.leftLabel.text = [self getNameArr][indexPath.row];

        if ([_contentArr[indexPath.row] isEqualToString:@"MUTATIVE"] ||[_contentArr[indexPath.row] isEqualToString:@"0"]) {
            [cell.leftButton setImage:[UIImage imageNamed:@"selectedImage"] forState:UIControlStateNormal];
            [cell.rightButton setImage:[UIImage imageNamed:@"noSelectImage"] forState:UIControlStateNormal];
        }else{
            [cell.leftButton setImage:[UIImage imageNamed:@"noSelectImage"] forState:UIControlStateNormal];
            [cell.rightButton setImage:[UIImage imageNamed:@"selectedImage"] forState:UIControlStateNormal];
        }
        if (indexPath.row == 4) {
            [cell.leftButton setTitle:[NSString stringWithFormat:@"  %@",[[ChangeLanguage bundle] localizedStringForKey:@"Real-timePrice" value:nil table:@"English"]] forState:UIControlStateNormal];
            [cell.rightButton setTitle:[NSString stringWithFormat:@"  %@",[[ChangeLanguage bundle] localizedStringForKey:@"fixedPrice" value:nil table:@"English"]] forState:UIControlStateNormal];

        }else{
            [cell.leftButton setTitle:[NSString stringWithFormat:@"  %@",[[ChangeLanguage bundle] localizedStringForKey:@"no" value:nil table:@"English"]] forState:UIControlStateNormal];
            [cell.rightButton setTitle:[NSString stringWithFormat:@"  %@",[[ChangeLanguage bundle] localizedStringForKey:@"yes" value:nil table:@"English"]] forState:UIControlStateNormal];
        }
        cell.delegate = self;
        return cell;
    }else{
        Advertising1TableViewCell *cell = [tableView dequeueReusableCellWithIdentifier:NSStringFromClass([Advertising1TableViewCell class])];
        cell.selectionStyle = UITableViewCellSelectionStyleNone;
        cell.centerTextFileld.secureTextEntry = NO;
        cell.centerTextFileld.enabled = YES;
        cell.index = indexPath;
        cell.leftLabel.text = [self getNameArr][indexPath.row];
        cell.centerTextFileld.placeholder = [self getPlaceHoldArr2][indexPath.row];
        cell.rightLabel.text = [self getAttributeArr2][indexPath.row];
         cell.centerTextFileld.text = [self getContentArr2][indexPath.row];
        if (indexPath.row == 5 || indexPath.row == 6 || indexPath.row == 9 || indexPath.row == 7 || indexPath.row == 8) {
            cell.centerTextFileld.keyboardType = UIKeyboardTypeDecimalPad;
            if(indexPath.row == 5){
                //溢价处理
                if ([_contentArr[4] isEqualToString:@"REGULAR"] ||[_contentArr[4] isEqualToString:@"0"]) {
                    cell.centerTextFileld.enabled = NO;
                     cell.centerTextFileld.placeholder = [[ChangeLanguage bundle] localizedStringForKey:@"inputFixedPriceNoPremium" value:nil table:@"English"];

                }else{
                    cell.centerTextFileld.enabled = YES;
                    cell.centerTextFileld.placeholder = [[ChangeLanguage bundle] localizedStringForKey:@"inputMarketPricePremium" value:nil table:@"English"];
                }
            }else if (indexPath.row == 6){
                //溢价处理
                cell.centerTextFileld.keyboardType = UIKeyboardTypeDecimalPad;
                if ([_contentArr[4] isEqualToString:@"REGULAR"] ||[_contentArr[4] isEqualToString:@"0"]) {
                    cell.centerTextFileld.enabled = YES;
                    cell.centerTextFileld.placeholder = [[ChangeLanguage bundle] localizedStringForKey:@"inputTradPrice" value:nil table:@"English"];
                }else{
                    cell.centerTextFileld.enabled = NO;
                    cell.centerTextFileld.placeholder = [[ChangeLanguage bundle] localizedStringForKey:@"inputAutomaticCalculation" value:nil table:@"English"];
                }
            }
        }else if (indexPath.row == 11){
            cell.centerTextFileld.keyboardType = UIKeyboardTypeNumberPad;

        }else if (indexPath.row == 12){

             cell.centerTextFileld.keyboardType = UIKeyboardTypeDefault;cell.centerTextFileld.secureTextEntry = YES;
        }
        else{
            cell.centerTextFileld.keyboardType = UIKeyboardTypeDefault;

        }
        cell.delegate = self;
        return cell;
    }
}
//MARK:--获取货币类型和是否自动回复的代理回调
-(void)tableViewIndex:(NSIndexPath *)index buttonTag:(NSInteger)buttonTag{
    if (index.row == 4) {
        //价格类型
        if (buttonTag == 1) {
            //实时价格
             [_contentArr replaceObjectAtIndex:4 withObject:@"MUTATIVE"];
             [_contentArr replaceObjectAtIndex:3 withObject:_actualPrice];
            [_contentArr replaceObjectAtIndex:5 withObject:@""];
            if (_detailModel.premiseRate==nil || [_detailModel.premiseRate isEqualToString:@""]) {
                [_contentArr replaceObjectAtIndex:6 withObject:_actualPrice];
            }else{
                [_contentArr replaceObjectAtIndex:6 withObject:[NSString stringWithFormat:@"%.2f",[_actualPrice floatValue]*[_detailModel.premiseRate floatValue]/100+[_actualPrice floatValue]]];
            }
        }else{
            //固定价格
            [_contentArr replaceObjectAtIndex:4 withObject:@"REGULAR"];
            [_contentArr replaceObjectAtIndex:3 withObject:_actualPrice];
            [_contentArr replaceObjectAtIndex:6 withObject:@""];
            [_contentArr replaceObjectAtIndex:5 withObject:@""];

        }
//        NSIndexPath *indexPath=[NSIndexPath indexPathForRow:4 inSection:0];
//        NSIndexPath *indexPath1=[NSIndexPath indexPathForRow:3 inSection:0];
//         NSIndexPath *indexPath2=[NSIndexPath indexPathForRow:6 inSection:0];
//        NSIndexPath *indexPath3=[NSIndexPath indexPathForRow:5 inSection:0];
//        [_mainTableView reloadRowsAtIndexPaths:[NSArray arrayWithObjects:indexPath,indexPath1,indexPath2,indexPath3,nil] withRowAnimation:UITableViewRowAnimationNone];
        [self.mainTableView reloadData];
    }else if (index.row == 13){
        if (buttonTag == 1) {
            //是否自动回复
            [_contentArr replaceObjectAtIndex:13 withObject:@"0"];
            [_contentArr replaceObjectAtIndex:14 withObject:@""];
        }else{
            [_contentArr replaceObjectAtIndex:13 withObject:@"1"];
        }
//        NSIndexPath *indexPath=[NSIndexPath indexPathForRow:13 inSection:0];
//        NSIndexPath *indexPath1=[NSIndexPath indexPathForRow:14 inSection:0];
//        [_mainTableView reloadRowsAtIndexPaths:[NSArray arrayWithObjects:indexPath,indexPath1,nil] withRowAnimation:UITableViewRowAnimationNone];
        [self.mainTableView reloadData];
    }

}
-(void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath{
    [tableView deselectRowAtIndexPath:indexPath animated:YES];
    if (indexPath.row == 0) {
        //选择币种
        if (_index == 1) {
            //修改不让点击
            [self.view makeToast:[[ChangeLanguage bundle] localizedStringForKey:@"inputModifyAdsNoCoinType" value:nil table:@"English"] duration:1.5 position:CSToastPositionCenter];
        }else{
            self.enterWays = 1;
            [self selectCoinTypeData];
        }
    }else if (indexPath.row == 1){
      //选择国家
        __weak AdvertisingBuyViewController *weakSelf=self;
        countryViewController *countryVC = [[countryViewController alloc]init];
        countryVC.hidesBottomBarWhenPushed=YES;
        countryVC.returnValueBlock = ^(countryModel *model){
            [weakSelf.contentArr replaceObjectAtIndex:1 withObject:model.zhName];
            [weakSelf.contentArr replaceObjectAtIndex:2 withObject:model.localCurrency];
            [weakSelf.mainTableView reloadData];
        };
        [self.navigationController pushViewController:countryVC animated:YES];
    }else if (indexPath.row == 10){
        //选择支付方式
        self.enterWays = 2;
        [self selectCoinTypeView];
    }
}
//MARK:--选择币种的请求数据
-(void)selectCoinTypeData{
    [EasyShowLodingView showLodingText:[[ChangeLanguage bundle] localizedStringForKey:@"loading" value:nil table:@"English"]];
    [C2CNetManager selectCoinTypeForCompleteHandle:^(id resPonseObj, int code) {
        NSLog(@"选择币种的请求数据 --- %@",resPonseObj);
        [EasyShowLodingView hidenLoding];
        if (code){
            if ([resPonseObj[@"code"] integerValue]==0) {
                //获取数据成功
                [_selectCoinTypeArr removeAllObjects];
//                NSLog(@"--%@",resPonseObj);
                NSArray *dataArr = [SelectCoinTypeModel mj_objectArrayWithKeyValuesArray:resPonseObj[@"data"]];
                [self.selectCoinTypeArr addObjectsFromArray:dataArr];
                [self selectCoinTypeView];
//                NSLog(@"--%ld",_selectCoinTypeArr.count);
            }else{
                [self.view makeToast:resPonseObj[MESSAGE] duration:1.5 position:CSToastPositionCenter];
            }
        }else{
            [self.view makeToast:[[ChangeLanguage bundle] localizedStringForKey:@"noNetworkStatus" value:nil table:@"English"] duration:1.5 position:CSToastPositionCenter];
        }
    }];
}
//MARK:--点击购买弹出的提示框
-(void)selectCoinTypeView{
    if (!_selectTypeView) {
        _selectTypeView = [[NSBundle mainBundle] loadNibNamed:@"SelectCoinTypeView" owner:nil options:nil].firstObject;
        _selectTypeView.frame=[UIScreen mainScreen].bounds;
        _selectTypeView.modelArr = self.selectCoinTypeArr;
        _selectTypeView.delegate = self;
    }
    _selectTypeView.enterIndex = self.enterWays;
    [_selectTypeView.tableView reloadData];
    [self.view addSubview:_selectTypeView];
    CGAffineTransform transform = CGAffineTransformScale(CGAffineTransformIdentity,1.0,1.0);
    _selectTypeView.boardView.transform = CGAffineTransformScale(CGAffineTransformIdentity,0.2,0.2);
    _selectTypeView.boardView.alpha = 0;
    [UIView animateWithDuration:0.3 delay:0.1 usingSpringWithDamping:0.5 initialSpringVelocity:10 options:UIViewAnimationOptionCurveLinear animations:^{
        _selectTypeView.boardView.transform = transform;
        _selectTypeView.boardView.alpha = 1;
    } completion:^(BOOL finished) {

    }];
}
- (NSMutableArray *)selectCoinTypeArr {
    if (!_selectCoinTypeArr) {
        _selectCoinTypeArr = [NSMutableArray array];
    }
    return _selectCoinTypeArr;
}
-(void)selectCoinTypeModel:(SelectCoinTypeModel *)model enterIndex:(NSInteger)index payWaysArr:(NSMutableArray *)payWaysArr{
    if (index == 1) {
        //选择币种
        _actualPrice=model.marketPrice;
        [_contentArr replaceObjectAtIndex:0 withObject:model.unit];
        [_contentArr replaceObjectAtIndex:3 withObject:model.marketPrice];
        NSString *rateString =[_contentArr objectAtIndex:5];
        if (![rateString isEqualToString:@""]) {

            [_contentArr replaceObjectAtIndex:6 withObject:[NSString stringWithFormat:@"%.2f",[model.marketPrice floatValue]*[rateString floatValue]/100+[model.marketPrice floatValue]]];
        }else{
            [_contentArr replaceObjectAtIndex:6 withObject:model.marketPrice];
        }
        self.coinId = model.ID;
        [_mainTableView reloadData];
    }else if (index == 2){
      //选择支付方式
        NSString *string = @"";
        for (NSString *str  in payWaysArr) {
//            NSLog(@"---%@",str);
            string = [string stringByAppendingString:[NSString stringWithFormat:@" %@",str]];
        }
        NSMutableArray *payWaysArrary = [[NSMutableArray alloc] init];
        if (payWaysArr.count > 0) {
            for (NSString *payStr in payWaysArr) {
                if ([payStr isEqualToString:@"支付宝"]) {
                    [payWaysArrary addObject:LocalizationKey(@"alipay")];
                }else if ([payStr isEqualToString:@"微信"]){
                    [payWaysArrary addObject:LocalizationKey(@"WeChat")];
                }else{
                    [payWaysArrary addObject:LocalizationKey(@"bankCard")];
                }
            }
        }
        [_contentArr replaceObjectAtIndex:10 withObject:[payWaysArrary componentsJoinedByString:@" "]];
//         [_contentArr replaceObjectAtIndex:10 withObject:string];
        self.payWays = payWaysArr;
//        NSLog(@"%@",payWaysArr);
        [_mainTableView reloadData];
    }
}
//MARK:--输入框的代理方法
-(void)textFieldIndex:(NSIndexPath *)index TextFieldString:(NSString *)textString{

    [_contentArr replaceObjectAtIndex:index.row withObject:textString];
    if (index.row == 5) {
        //溢价
        if ([textString floatValue] <= 0) {
            [_contentArr replaceObjectAtIndex:6 withObject:_actualPrice];
        }else{
            NSString *regularStr = [NSString stringWithFormat:@"%.2f",[_actualPrice floatValue]* [textString floatValue]/100+[_actualPrice floatValue]];
            [_contentArr replaceObjectAtIndex:6 withObject:regularStr];
        }
        [self.mainTableView reloadData];
    }

}
//MARK:--文本框的代理方法
-(void)textViewIndex:(NSIndexPath *)index TextViewString:(NSString *)textString{
    [_contentArr replaceObjectAtIndex:index.row withObject:textString];
}
-(NSArray *)getNameArr{
    NSArray * nameArr = @[[[ChangeLanguage bundle] localizedStringForKey:@"selectCoinType" value:nil table:@"English"],[[ChangeLanguage bundle] localizedStringForKey:@"home" value:nil table:@"English"],[[ChangeLanguage bundle] localizedStringForKey:@"currencyType" value:nil table:@"English"],[[ChangeLanguage bundle] localizedStringForKey:@"Real-timePrice" value:nil table:@"English"],[[ChangeLanguage bundle] localizedStringForKey:@"priceType" value:nil table:@"English"],[[ChangeLanguage bundle] localizedStringForKey:@"premium" value:nil table:@"English"],[[ChangeLanguage bundle] localizedStringForKey:@"tradingPrice" value:nil table:@"English"],[[ChangeLanguage bundle] localizedStringForKey:@"minimum" value:nil table:@"English"],[[ChangeLanguage bundle] localizedStringForKey:@"maximum" value:nil table:@"English"],[[ChangeLanguage bundle] localizedStringForKey:@"buyNumber" value:nil table:@"English"],[[ChangeLanguage bundle] localizedStringForKey:@"payMethods" value:nil table:@"English"],[[ChangeLanguage bundle] localizedStringForKey:@"payTerm" value:nil table:@"English"],[[ChangeLanguage bundle] localizedStringForKey:@"moneyPassword" value:nil table:@"English"],[[ChangeLanguage bundle] localizedStringForKey:@"isAutoReply" value:nil table:@"English"],@"",@""];
    return nameArr;
}
-(NSArray *)getPlaceHoldArr2{
    NSArray * nameArr = @[@"",@"",@"",[[ChangeLanguage bundle] localizedStringForKey:@"inputReal-timePrice" value:nil table:@"English"],@"",[[ChangeLanguage bundle] localizedStringForKey:@"inputMarketPricePremium" value:nil table:@"English"],[[ChangeLanguage bundle] localizedStringForKey:@"inputTradPrice" value:nil table:@"English"],[[ChangeLanguage bundle] localizedStringForKey:@"inputTradMin" value:nil table:@"English"],[[ChangeLanguage bundle] localizedStringForKey:@"inputTradMax" value:nil table:@"English"],[[ChangeLanguage bundle] localizedStringForKey:@"inputBuyAmount" value:nil table:@"English"],@"",[[ChangeLanguage bundle] localizedStringForKey:@"inputPayTerm" value:nil table:@"English"],[[ChangeLanguage bundle] localizedStringForKey:@"inputMoneyPassword" value:nil table:@"English"],@"",@"",@""];
    return nameArr;
}
-(NSArray *)getAttributeArr2{
    NSArray * nameArr = @[@"",@"",@"",@"CNY",@"",@"  %",@"  CNY",@"  CNY",@"  CNY",@"",@"",[[ChangeLanguage bundle] localizedStringForKey:@"minutes" value:nil table:@"English"],@"",@"",@"",@""];
    return nameArr;
}
-(NSMutableArray *)getContentArr2{
    if (!_contentArr) {
         _contentArr = [NSMutableArray arrayWithArray:@[[[ChangeLanguage bundle] localizedStringForKey:@"select" value:nil table:@"English"],@"中国",@"RMB",@"",@"MUTATIVE",@"",@"",@"",@"",@"",[[ChangeLanguage bundle] localizedStringForKey:@"select" value:nil table:@"English"],@"",@"",@"0",@"",@""]];
    }
    return _contentArr;
}
//MARK:--导航栏右边按钮点击事件
-(void)RighttouchEvent{

}
-(NSArray *)judgePlaceHoldArr{
    NSArray * nameArr = @[[[ChangeLanguage bundle] localizedStringForKey:@"inputSelectCoinType" value:nil table:@"English"],[[ChangeLanguage bundle] localizedStringForKey:@"inputSelectHome" value:nil table:@"English"],[[ChangeLanguage bundle] localizedStringForKey:@"inputSelectCurrencyType" value:nil table:@"English"],[[ChangeLanguage bundle] localizedStringForKey:@"inputReal-timePrice" value:nil table:@"English"],@"",[[ChangeLanguage bundle] localizedStringForKey:@"inputMarketPricePremium" value:nil table:@"English"],[[ChangeLanguage bundle] localizedStringForKey:@"inputTradPrice" value:nil table:@"English"],[[ChangeLanguage bundle] localizedStringForKey:@"inputTradMin" value:nil table:@"English"],[[ChangeLanguage bundle] localizedStringForKey:@"inputTradMax" value:nil table:@"English"],[[ChangeLanguage bundle] localizedStringForKey:@"inputBuyAmount" value:nil table:@"English"],[[ChangeLanguage bundle] localizedStringForKey:@"inputPayWays" value:nil table:@"English"],[[ChangeLanguage bundle] localizedStringForKey:@"inputPayTerm" value:nil table:@"English"],[[ChangeLanguage bundle] localizedStringForKey:@"inputMoneyPassword" value:nil table:@"English"],@"",[[ChangeLanguage bundle] localizedStringForKey:@"inputAutoReply" value:nil table:@"English"],@""];
    return nameArr;
}
//MARK:--发布购买广告点击事件
- (IBAction)submitBtnClick:(UIButton *)sender {

    for (int i=0; i<_contentArr.count; i++) {
        NSString *contentStr = _contentArr[i];
//        NSLog(@"--%@",contentStr);
        if (i ==15 || i==5) {
            //可以不填，不处理
            if (i == 5) {
                NSString *regular = _contentArr[4];
                if ([regular isEqualToString:@"MUTATIVE"]) {
                    if ([_contentArr[5] isEqualToString:@""]) {
                        [self.view makeToast:[self judgePlaceHoldArr][i] duration:1.5 position:CSToastPositionCenter];
                        return;
                    }
                }
            }
        }else if (i == 7){
            if ([contentStr isEqualToString:@""]) {
                [self.view makeToast:[self judgePlaceHoldArr][i] duration:1.5 position:CSToastPositionCenter];
                return;
            }else{
                if ([contentStr floatValue] < 100) {
                    [self.view makeToast:LocalizationKey(@"minimum100") duration:1.5 position:CSToastPositionCenter];
                    return;
                }
            }

        }else if (i == 14){
            if ([contentStr isEqualToString:@""]) {
//                NSLog(@"--%@",_contentArr[13]);
                if ([_contentArr[13] isEqualToString:@"1"]) {
                    [self.view makeToast:[self judgePlaceHoldArr][i] duration:1.5 position:CSToastPositionCenter];
                    return;
                }
            }
        }else{
            if ([contentStr isEqualToString:@""] || [contentStr isEqualToString:[[ChangeLanguage bundle] localizedStringForKey:@"select" value:nil table:@"English"]]) {
                [self.view makeToast:[self judgePlaceHoldArr][i] duration:1.5 position:CSToastPositionCenter];
                return;
            }
        }
    }

    [EasyShowLodingView showLodingText:[[ChangeLanguage bundle] localizedStringForKey:@"loading" value:nil table:@"English"]];
    if (_index == 1) {
        //修改发布
        [C2CNetManager changeAdvertisingForBuyOrSellWithAdvertisingId:_detailModel.ID WithPrice:_contentArr[6] withAdvertiseType:@"BUY" withCoinId:self.coinId withMinLimit:_contentArr[7] withMaxLimit:_contentArr[8] withTimeLimit:_contentArr[11] withCountry:_contentArr[1] withPriceType:_contentArr[4] withPremiseRate:_contentArr[5] withRemark:_contentArr[15] withNumber:_contentArr[9] withPayways:self.payWays withJyPassword:_contentArr[12] withAuto:_contentArr[13] withAutoWord:_contentArr[14] CompleteHandle:^(id resPonseObj, int code) {
            [EasyShowLodingView hidenLoding];
//            NSLog(@"--%@",resPonseObj);
            if (code) {
                if ([resPonseObj[@"code"] integerValue]==0) {
                    //获取数据成功
                    [self.view makeToast:resPonseObj[MESSAGE] duration:1.0 position:CSToastPositionCenter];
                    dispatch_after(dispatch_time(DISPATCH_TIME_NOW, (int64_t)(1.5 * NSEC_PER_SEC)), dispatch_get_main_queue(), ^{
                        [self.navigationController popViewControllerAnimated:YES];
                    });
//                    NSLog(@"--%@",resPonseObj);
                }else if ([resPonseObj[@"code"] integerValue] == 3000 ||[resPonseObj[@"code"] integerValue] == 4000 ){
                    //[ShowLoGinVC showLoginVc:self withTipMessage:resPonseObj[MESSAGE]];
                      [YLUserInfo logout];
                }else if ([resPonseObj[@"code"] integerValue] == 500){
                    //请先实名认证
                    [self.view makeToast:resPonseObj[MESSAGE] duration:1.5 position:CSToastPositionCenter];
                }else{
                    [self.view makeToast:resPonseObj[MESSAGE] duration:1.5 position:CSToastPositionCenter];
                }
            }else{
                [self.view makeToast:[[ChangeLanguage bundle] localizedStringForKey:@"noNetworkStatus" value:nil table:@"English"] duration:1.5 position:CSToastPositionCenter];
            }
        }];

    }else{
        //上传发布广告数据
        [C2CNetManager advertisingForBuyOrSellWithPrice:_contentArr[6] withAdvertiseType:@"BUY" withCoinId:self.coinId withMinLimit:_contentArr[7] withMaxLimit:_contentArr[8] withTimeLimit:_contentArr[11] withCountry:_contentArr[1] withPriceType:_contentArr[4] withPremiseRate:_contentArr[5] withRemark:_contentArr[15] withNumber:_contentArr[9] withPayways:self.payWays withJyPassword:_contentArr[12] withAuto:_contentArr[13] withAutoWord:_contentArr[14] CompleteHandle:^(id resPonseObj, int code) {
            [EasyShowLodingView hidenLoding];
//            NSLog(@"--%@",resPonseObj);
            if (code) {
                if ([resPonseObj[@"code"] integerValue]==0) {
                    //获取数据成功
                    [self.view makeToast:resPonseObj[MESSAGE] duration:1.0 position:CSToastPositionCenter];
                    dispatch_after(dispatch_time(DISPATCH_TIME_NOW, (int64_t)(1.5 * NSEC_PER_SEC)), dispatch_get_main_queue(), ^{

                        __weak AdvertisingBuyViewController *weakSelf=self;
                        [self addAlterViewWithTitle:LocalizationKey(@"warmPrompt") withMessage:LocalizationKey(@"IsShelvesTip") withCertainBtnTitle:LocalizationKey(@"goShelves") withCancelBtnTitle:LocalizationKey(@"BuYongLe") withActionBlock:^{

                            MyAdvertisingViewController *advertisingVC = [[MyAdvertisingViewController alloc] init];
                            advertisingVC.hidesBottomBarWhenPushed = YES;
                            [weakSelf.navigationController popViewControllerAnimated:YES];
                            [weakSelf.navigationController pushViewController:advertisingVC animated:YES];
                        } andCancel:^{
//                            NSLog(@"取消");
                            [weakSelf.navigationController popViewControllerAnimated:YES];
                        }];
                    });
//                    NSLog(@"--%@",resPonseObj);
                }else if ([resPonseObj[@"code"] integerValue] == 3000 ||[resPonseObj[@"code"] integerValue] == 4000 ){
                   // [ShowLoGinVC showLoginVc:self withTipMessage:resPonseObj[MESSAGE]];
                     [YLUserInfo logout];
                }else if ([resPonseObj[@"code"] integerValue] == 500){
                    //请先实名认证
                    [self.view makeToast:resPonseObj[MESSAGE] duration:1.5 position:CSToastPositionCenter];
                }else{
                    [self.view makeToast:resPonseObj[MESSAGE] duration:1.5 position:CSToastPositionCenter];
                }
            }else{
                [self.view makeToast:[[ChangeLanguage bundle] localizedStringForKey:@"noNetworkStatus" value:nil table:@"English"] duration:1.5 position:CSToastPositionCenter];
            }
        }];
    }

}
- (void)textFieldDidEndEditing:(UITextField *)textField{
    if ([_contentArr[4] isEqualToString:@"REGULAR"] ||[_contentArr[4] isEqualToString:@"0"]) {
      [_contentArr replaceObjectAtIndex:3 withObject:textField.text];
    }
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
