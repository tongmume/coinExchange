//
//  AddMentionCoinAddressViewController.m
//  digitalCurrency
//
//  Created by iDog on 2019/3/8.
//  Copyright © 2019年 BIZZAN. All rights reserved.
//

#import "AddMentionCoinAddressViewController.h"
#import "MentionCoinInfoModel.h"
#import "AddAdressTableViewCell.h"

@interface AddMentionCoinAddressViewController ()<UITableViewDelegate,UITableViewDataSource>
@property (weak, nonatomic) IBOutlet UITableView *tableView;
@property (weak, nonatomic) IBOutlet NSLayoutConstraint *bottomViewHeight;

@end

@implementation AddMentionCoinAddressViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    self.title = [[ChangeLanguage bundle] localizedStringForKey:@"mentionMoneyAddress" value:nil table:@"English"];
    self.bottomViewHeight.constant = SafeAreaBottomHeight;
    self.tableView.delegate = self;
    self.tableView.dataSource = self;
    self.tableView.estimatedRowHeight = 44;
    self.tableView.tableFooterView = [UIView new];
    [self.tableView registerNib:[UINib nibWithNibName:@"AddAdressTableViewCell" bundle:nil] forCellReuseIdentifier:NSStringFromClass([AddAdressTableViewCell class])];
    LYEmptyView *emptyView = [LYEmptyView emptyViewWithImageStr:@"emptyData" titleStr:[[ChangeLanguage bundle] localizedStringForKey:@"noAddAddressTip" value:nil table:@"English"]];
    self.tableView.ly_emptyView = emptyView;
    // Do any additional setup after loading the view from its nib.
}

-(NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section{
    return _addressInfoArr.count;
}
-(NSInteger)numberOfSectionsInTableView:(UITableView *)tableView{
    return 1;
}
- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath
{
    AddAdressTableViewCell *cell = [tableView dequeueReusableCellWithIdentifier:NSStringFromClass([AddAdressTableViewCell class]) forIndexPath:indexPath];
    cell.selectionStyle = UITableViewCellSelectionStyleNone;
    AddressInfo *addressInfo = _addressInfoArr[indexPath.row];
    if ([addressInfo.remark isEqualToString:@""] || addressInfo.remark == nil) {
        cell.remark.text = [[ChangeLanguage bundle] localizedStringForKey:@"addAddressNoRemark" value:nil table:@"English"];
    }else{
       cell.remark.text = addressInfo.remark;
    }
    cell.addAdress.text = addressInfo.address;
    return cell;
}
-(CGFloat)tableView:(UITableView *)tableView heightForRowAtIndexPath:(NSIndexPath *)indexPath{
    return UITableViewAutomaticDimension;
}
-(void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath{

    AddressInfo *addressInfo = _addressInfoArr[indexPath.row];
    if (self.delegate && [self.delegate respondsToSelector:@selector(AddAdressString:)]) {
        [self.delegate AddAdressString:addressInfo.address];
        [self.navigationController popViewControllerAnimated:YES];
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
