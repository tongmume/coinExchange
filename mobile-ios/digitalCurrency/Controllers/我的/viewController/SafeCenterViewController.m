//
//  SafeCenterViewController.m
//  digitalCurrency
//
//  Created by iDog on 2019/1/30.
//  Copyright © 2019年 BIZZAN. All rights reserved.
//

#import "SafeCenterViewController.h"
#import "AccountSettingTableViewCell.h"
#import "GestureViewController.h"
#import "ZLGestureLockViewController.h"
@interface SafeCenterViewController ()<UITableViewDataSource,UITableViewDelegate>
@property (weak, nonatomic) IBOutlet UITableView *tableView;
@property (weak, nonatomic) IBOutlet NSLayoutConstraint *bottomViewHeight;
@property(nonatomic,strong)NSArray * rightArr;
@end

@implementation SafeCenterViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    self.title = [[ChangeLanguage bundle] localizedStringForKey:@"safeCenter" value:nil table:@"English"];
    self.tableView.delegate = self;
    self.tableView.dataSource = self;
    [self.tableView registerNib:[UINib nibWithNibName:@"AccountSettingTableViewCell" bundle:nil] forCellReuseIdentifier:NSStringFromClass([AccountSettingTableViewCell class])];
    self.bottomViewHeight.constant = SafeAreaBottomHeight;
    // Do any additional setup after loading the view from its nib.
}
-(void)viewWillAppear:(BOOL)animated{
    [super viewWillAppear:YES];
    NSString*Gesturepassword=[ZLGestureLockViewController gesturesPassword].length >0?[[ChangeLanguage bundle] localizedStringForKey:@"opened" value:nil table:@"English"]:[[ChangeLanguage bundle] localizedStringForKey:@"unOpened" value:nil table:@"English"];
    self.rightArr = @[Gesturepassword];
    [self.tableView reloadData];
}
-(NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section{
    return [[self getNameArr] count];
}

-(CGFloat)tableView:(UITableView *)tableView heightForRowAtIndexPath:(NSIndexPath *)indexPath{
    return 50;
}
-(NSArray *)getNameArr{
    NSArray * nameArr = @[[[ChangeLanguage bundle] localizedStringForKey:@"gesturesPassword" value:nil table:@"English"]];
    return nameArr;
}
- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath
{
    AccountSettingTableViewCell *cell = [tableView dequeueReusableCellWithIdentifier:NSStringFromClass([AccountSettingTableViewCell class])];
    cell.leftLabel.text = [self getNameArr][indexPath.row];
    cell.rightLabel.text=self.rightArr[indexPath.row];
    if ([cell.rightLabel.text isEqualToString:[[ChangeLanguage bundle] localizedStringForKey:@"unOpened" value:nil table:@"English"]]) {
        cell.rightLabel.textColor=baseColor;
    }else{
        cell.rightLabel.textColor=[UIColor blackColor];
    }

    return cell;
}
-(void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath{
    [tableView deselectRowAtIndexPath:indexPath animated:YES];
    //手势密码
    [self.navigationController pushViewController:[[GestureViewController alloc]init] animated:YES];
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
