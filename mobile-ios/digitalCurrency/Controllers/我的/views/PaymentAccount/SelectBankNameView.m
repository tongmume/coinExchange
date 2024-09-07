//
//  SelectBankNameView.m
//  digitalCurrency
//
//  Created by iDog on 2019/5/2.
//  Copyright © 2019年 BIZZAN. All rights reserved.
//

#import "SelectBankNameView.h"
#import "SelectCountryTableViewCell.h"

@implementation SelectBankNameView

-(void)awakeFromNib{
    [super awakeFromNib];
    self.backgroundColor=[[UIColor blackColor] colorWithAlphaComponent:0.4];

    self.tableView.delegate = self;
    self.tableView.dataSource = self;
    [self.tableView registerNib:[UINib nibWithNibName:@"SelectCountryTableViewCell" bundle:nil] forCellReuseIdentifier:NSStringFromClass([SelectCountryTableViewCell class])];
}
-(NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section{
    return _modelArr.count;
}
-(NSInteger)numberOfSectionsInTableView:(UITableView *)tableView{
    return 1;
}
-(CGFloat)tableView:(UITableView *)tableView heightForRowAtIndexPath:(NSIndexPath *)indexPath{
    return 66;
}
- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath
{
    SelectCountryTableViewCell *cell = [tableView dequeueReusableCellWithIdentifier:NSStringFromClass([SelectCountryTableViewCell class])];
    SelectBankNameModel *model = self.modelArr[indexPath.row];
    cell.enName.text = model.enBankName;
    cell.zhName.text = model.zhBankName;
    return cell;
}
-(void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath{
    [tableView deselectRowAtIndexPath:indexPath animated:YES];
     SelectBankNameModel *model = self.modelArr[indexPath.row];
    if (self.delegate && [self.delegate respondsToSelector:@selector(selectBankNameModel:)]) {
        [self  removeFromSuperview];
        [self.delegate selectBankNameModel:model];
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
