//
//  countryViewController.m
//  digitalCurrency
//
//  Created by sunliang on 2019/2/23.
//  Copyright © 2019年 BIZZAN. All rights reserved.
//

#import "countryViewController.h"
#import "LoginNetManager.h"
#import "countryModel.h"
#import "SelectCountryTableViewCell.h"
@interface countryViewController ()<UITableViewDelegate,UITableViewDataSource>
@property (nonatomic,strong)NSMutableArray *contentArr;
@end

@implementation countryViewController
- (NSMutableArray *)contentArr
{
    if (!_contentArr) {
        _contentArr = [NSMutableArray array];
    }
    return _contentArr;
}
- (void)viewDidLoad {
    [super viewDidLoad];
    self.navigationItem.title=LocalizationKey(@"selectCountry");
    [self getData];
    self.tableView.delegate = self;
    self.tableView.dataSource = self;
    [self.tableView registerNib:[UINib nibWithNibName:@"SelectCountryTableViewCell" bundle:nil] forCellReuseIdentifier:NSStringFromClass([SelectCountryTableViewCell class])];
    // Do any additional setup after loading the view from its nib.
}
#pragma mark-获取国家列表
-(void)getData{
    [EasyShowLodingView showLodingText:LocalizationKey(@"loading")];
    [LoginNetManager getAllCountryCompleteHandle:^(id resPonseObj, int code) {
    [EasyShowLodingView hidenLoding];
        if (code) {
            if ([resPonseObj[@"code"] integerValue] == 0) {
//                NSLog(@"--%@",resPonseObj);
                NSArray*countryArray=resPonseObj[@"data"];
                for (int i=0; i<countryArray.count; i++) {
                    countryModel*model = [countryModel mj_objectWithKeyValues:countryArray[i]];
                    [self.contentArr addObject:model];
                }
                [self.tableView reloadData];
            }else{
                [self.view makeToast:resPonseObj[MESSAGE] duration:1.5 position:CSToastPositionCenter];
            }
        }else{
            [self.view makeToast:LocalizationKey(@"noNetworkStatus") duration:1.5 position:CSToastPositionCenter];
        }
    }];

}
-(NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section{
    return self.contentArr.count;
}

- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath
{
    SelectCountryTableViewCell *cell = [tableView dequeueReusableCellWithIdentifier:NSStringFromClass([SelectCountryTableViewCell class]) forIndexPath:indexPath];
    cell.selectionStyle = UITableViewCellSelectionStyleNone;
     countryModel*model=self.contentArr[indexPath.row];
    cell.enName.text = model.enName;
    cell.zhName.text = model.translation;
    return cell;

}
-(CGFloat)tableView:(UITableView *)tableView heightForRowAtIndexPath:(NSIndexPath *)indexPath{
    return 66;
}
-(void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath{
    countryModel*model=self.contentArr[indexPath.row];
    self.returnValueBlock(model);
    [self.navigationController popViewControllerAnimated:YES];
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
