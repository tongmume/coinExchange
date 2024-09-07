//
//  SearchAdvertisingViewController.m
//  digitalCurrency
//
//  Created by iDog on 2019/1/31.
//  Copyright © 2019年 BIZZAN. All rights reserved.
//

#import "SearchAdvertisingViewController.h"

@interface SearchAdvertisingViewController ()
@property (weak, nonatomic) IBOutlet UILabel *selectCoins; //选择币种
@property (weak, nonatomic) IBOutlet UILabel *country; //所在地
@property (weak, nonatomic) IBOutlet UITextField *lowPrice; //最低价
@property (weak, nonatomic) IBOutlet UITextField *heighPrice; //最高价
@property (weak, nonatomic) IBOutlet UILabel *payWays; //付款方式
@property (weak, nonatomic) IBOutlet UIButton *Cointype; //货币类型

@end

@implementation SearchAdvertisingViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    // Do any additional setup after loading the view from its nib.
}
//MARK:--按钮的点击事件
- (IBAction)buttonClick:(UIButton *)sender {
    if (sender.tag == 1){
        //选择币种
        NSLog(@"选择币种");
    }else if (sender.tag == 2){
        //所在地
        NSLog(@"所在地");
    }else if (sender.tag == 3){
        //付款方式
        NSLog(@"付款方式");
    }else if (sender.tag == 4){
        //货币类型
        NSLog(@"货币类型");
    }else if (sender.tag == 5){
        //搜索
        NSLog(@"搜索");
    }else{
      //其他
        NSLog(@"其他");
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
