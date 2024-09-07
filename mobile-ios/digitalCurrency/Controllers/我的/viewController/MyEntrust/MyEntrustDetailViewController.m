//
//  MyEntrustDetailViewController.m
//  digitalCurrency
//
//  Created by iDog on 2019/4/11.
//  Copyright © 2019年 BIZZAN. All rights reserved.
//

#import "MyEntrustDetailViewController.h"
#import "MineNetManager.h"
#import "YLTabBarController.h"
#import "MyEntrustDetail1TableViewCell.h"
#import "MyEntrustDetail2TableViewCell.h"
@interface MyEntrustDetailViewController ()<UITableViewDelegate,UITableViewDataSource>
@property (weak, nonatomic) IBOutlet UITableView *tableView;
@property (weak, nonatomic) IBOutlet NSLayoutConstraint *bottomViewHeight;

@end

@implementation MyEntrustDetailViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    self.title = self.model.symbol;
    self.bottomViewHeight.constant = SafeAreaBottomHeight;
    self.tableView.delegate = self;
    self.tableView.dataSource = self;
    [self.tableView registerNib:[UINib nibWithNibName:@"MyEntrustDetail1TableViewCell" bundle:nil] forCellReuseIdentifier:NSStringFromClass([MyEntrustDetail1TableViewCell class])];
    [self.tableView registerNib:[UINib nibWithNibName:@"MyEntrustDetail2TableViewCell" bundle:nil] forCellReuseIdentifier:NSStringFromClass([MyEntrustDetail2TableViewCell class])];

}
-(NSInteger)numberOfSectionsInTableView:(UITableView *)tableView{
    return 2;
}
-(NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section{
    if (section == 0) {
        return 1;
    }else{
        return _model.detail.count;
    }
}
-(UITableViewCell*)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath{
    if (indexPath.section == 0) {
        MyEntrustDetail1TableViewCell *cell = [tableView dequeueReusableCellWithIdentifier:NSStringFromClass([MyEntrustDetail1TableViewCell class]) forIndexPath:indexPath];
        cell.selectionStyle = UITableViewCellSelectionStyleNone;
        if ([_model.direction isEqualToString:@"BUY"]) {
            cell.payStatus.text = [[ChangeLanguage bundle] localizedStringForKey:@"Buy" value:nil table:@"English"];
            cell.payStatus.textColor = GreenColor;
        }else{
            cell.payStatus.text = [[ChangeLanguage bundle] localizedStringForKey:@"Sell" value:nil table:@"English"];
            cell.payStatus.textColor = RedColor;
        }
        cell.coinType.text = _model.symbol;
        cell.dealNumTitle.text = [NSString stringWithFormat:@"%@(%@)",[[ChangeLanguage bundle] localizedStringForKey:@"dealTotal" value:nil table:@"English"],_model.baseSymbol];
        cell.dealNumData.text = _model.turnover;
        cell.dealPerPriceTitle.text = [NSString stringWithFormat:@"%@(%@)",[[ChangeLanguage bundle] localizedStringForKey:@"dealPerPrice" value:nil table:@"English"],_model.baseSymbol];
        if ([_model.tradedAmount floatValue] <= 0) {
            cell.dealPerPriceData.text = [NSString stringWithFormat:@"0.00"];
        }else{
            NSDecimalNumber *turnover = [[NSDecimalNumber alloc] initWithString:_model.turnover];
            NSDecimalNumber *tradedAmount = [[NSDecimalNumber alloc] initWithString:_model.tradedAmount];
            cell.dealPerPriceData.text= [NSString stringWithFormat:@"%.8f",[[turnover decimalNumberByDividingBy:tradedAmount] floatValue]];
        }

        cell.dealTotalNumTitle.text = [NSString stringWithFormat:@"%@(%@)",[[ChangeLanguage bundle] localizedStringForKey:@"dealNum" value:nil table:@"English"],_model.coinSymbol];
        cell.dealTotalNumData.text = _model.tradedAmount;
        return cell;
    }else{
        MyEntrustDetail2TableViewCell *cell = [tableView dequeueReusableCellWithIdentifier:NSStringFromClass([MyEntrustDetail2TableViewCell class]) forIndexPath:indexPath];
        cell.selectionStyle = UITableViewCellSelectionStyleNone;
        DetailInfo *detailInfo = self.model.detail[indexPath.row];
        cell.timeData.text = [ToolUtil convertStrToTime:detailInfo.time];
        cell.dealPriceData.text = detailInfo.price;
        cell.dealNumData.text = [NSString stringWithFormat:@"%.2f %@",[[ToolUtil judgeStringForDecimalPlaces:detailInfo.amount] floatValue],_model.coinSymbol];
        cell.feeData.text = [ToolUtil formartScientificNotationWithString:detailInfo.fee];
        return cell;
    }
}
-(CGFloat)tableView:(UITableView *)tableView heightForRowAtIndexPath:(NSIndexPath *)indexPath{
    if (indexPath.section == 0) {
        return 175;
    }else{
        return 73;
    }
}
-(CGFloat)tableView:(UITableView *)tableView heightForFooterInSection:(NSInteger)section{
    return 0.01;
}
-(CGFloat)tableView:(UITableView *)tableView heightForHeaderInSection:(NSInteger)section{
    if (section == 0) {
        return 10;
    }else{
        if (_model.detail.count > 0) {
            return 10;
        }else{
            return 0.01;
        }
    }
}
-(UIView *)tableView:(UITableView *)tableView viewForHeaderInSection:(NSInteger)section{
    UIView *headView = [[UIView alloc] initWithFrame:CGRectMake(0, 0, kWindowW, 10)];
    headView.backgroundColor = mainColor;
    return headView;
}

- (UIView *)tableView:(UITableView *)tableView viewForFooterInSection:(NSInteger)section{
    UIView *headView = [[UIView alloc] init];
    headView.backgroundColor = mainColor;
    return headView;
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
