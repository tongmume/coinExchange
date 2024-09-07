//
//  SelectCoinTypeView.m
//  digitalCurrency
//
//  Created by iDog on 2019/2/9.
//  Copyright © 2019年 BIZZAN. All rights reserved.
//

#import "SelectCoinTypeView.h"
#import "SelectCoinTypeTableViewCell.h"
#import "PayWaysTableViewCell.h"

@implementation SelectCoinTypeView
-(void)awakeFromNib{
    [super awakeFromNib];
    self.backgroundColor=[[UIColor blackColor] colorWithAlphaComponent:0.4];
    [self.certainButton setTitle:[[ChangeLanguage bundle] localizedStringForKey:@"ok" value:nil table:@"English"] forState:UIControlStateNormal];
    self.tableView.delegate = self;
    self.tableView.dataSource = self;
    [self.tableView registerNib:[UINib nibWithNibName:@"SelectCoinTypeTableViewCell" bundle:nil] forCellReuseIdentifier:NSStringFromClass([SelectCoinTypeTableViewCell class])];
    [self.tableView registerNib:[UINib nibWithNibName:@"PayWaysTableViewCell" bundle:nil] forCellReuseIdentifier:NSStringFromClass([PayWaysTableViewCell class])];
    self.boardView.layer.cornerRadius=10;
    self.boardView.clipsToBounds=YES;



}

- (void)setEnterIndex:(NSInteger)enterIndex{
    _enterIndex = enterIndex;
    if (_enterIndex == 1) {
        self.boardViewHeightConstraint.constant = self.modelArr.count * 50;
        self.doneBtnHeightConstraint.constant = 0;
    }else{
        self.boardViewHeightConstraint.constant = 300;
        self.doneBtnHeightConstraint.constant = 50;
    }
}

-(NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section{
    if (_enterIndex == 1){
        //选择币种
         return _modelArr.count;
    }else{
        return 3;
    }
}
-(NSInteger)numberOfSectionsInTableView:(UITableView *)tableView{
    return 1;
}
-(CGFloat)tableView:(UITableView *)tableView heightForRowAtIndexPath:(NSIndexPath *)indexPath{
    return 50;
}
- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath
{

    if (_enterIndex == 1){
        //选择币种
         SelectCoinTypeTableViewCell *cell = [tableView dequeueReusableCellWithIdentifier:NSStringFromClass([SelectCoinTypeTableViewCell class])];
         cell.coinTypeLabel.text = self.modelArr[indexPath.row].unit;
        self.certainButton.hidden = YES;
        return cell;
    }else{
         PayWaysTableViewCell *cell = [tableView dequeueReusableCellWithIdentifier:NSStringFromClass([PayWaysTableViewCell class])];

        cell.leftLabel.text = @[LocalizationKey(@"alipay"),LocalizationKey(@"WeChat"),LocalizationKey(@"bankCard")][indexPath.row];
        self.certainButton.hidden = NO;
        if (cell.rightButton.selected) {
            cell.rightImage.image = [UIImage imageNamed:@"hookImage"];
        }else{
            cell.rightImage.image = [UIImage imageNamed:@""];
        }
        cell.rightButton.tag = indexPath.row;
        [cell.rightButton addTarget:self action:@selector(payWaysBtnClick:) forControlEvents:UIControlEventTouchUpInside];
        return cell;
    }
}
-(void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath{
    [tableView deselectRowAtIndexPath:indexPath animated:YES];
    if (_enterIndex == 1) {
         if (self.delegate && [self.delegate respondsToSelector:@selector(selectCoinTypeModel:enterIndex:payWaysArr:)]) {
             [self  removeFromSuperview];
             [self.delegate selectCoinTypeModel:self.modelArr[indexPath.row] enterIndex:_enterIndex payWaysArr:nil];
         }
    }else{
    }
}
//MARK:--付款方式的点击事件
-(void)payWaysBtnClick:(UIButton*)button{
    button.selected =!button.selected;
    NSIndexPath *indexPath = [NSIndexPath indexPathForRow:button.tag inSection:0];
    PayWaysTableViewCell *cell = [self.tableView cellForRowAtIndexPath:indexPath];
    if (button.selected){
        cell.rightImage.image = [UIImage imageNamed:@"hookImage"];
        [[self payWaysSelectArr] replaceObjectAtIndex:button.tag withObject:@[@"支付宝",@"微信",@"银联"][button.tag]];
    }else{
      cell.rightImage.image = [UIImage imageNamed:@""];
      [[self payWaysSelectArr] replaceObjectAtIndex:button.tag withObject:@""];
    }
}
-(NSMutableArray *)payWaysSelectArr{
    if (!_payWaysSelectArr) {
        _payWaysSelectArr = [NSMutableArray arrayWithArray:@[@"",@"",@""]];
    }
    return _payWaysSelectArr;
}
//MARK:--确定按钮的点击事件
- (IBAction)certainBtnClick:(UIButton *)sender {
    NSMutableArray *arr = [[NSMutableArray alloc] init];
    for (NSString *string in self.payWaysSelectArr) {
        if (![string isEqualToString:@""]) {
            [arr addObject:string];
        }
    }
    //代理传值
    if (_enterIndex == 2) {
        if (self.delegate && [self.delegate respondsToSelector:@selector(selectCoinTypeModel:enterIndex:payWaysArr:)]) {
            [self  removeFromSuperview];
            [self.delegate selectCoinTypeModel:nil enterIndex:_enterIndex payWaysArr:arr];
        }
    }
}

-(void)touchesBegan:(NSSet<UITouch *> *)touches withEvent:(UIEvent *)event{
    [self  removeFromSuperview ];
}



/*
// Only override drawRect: if you perform custom drawing.
// An empty implementation adversely affects performance during animation.
- (void)drawRect:(CGRect)rect {
    // Drawing code
}
*/

@end
