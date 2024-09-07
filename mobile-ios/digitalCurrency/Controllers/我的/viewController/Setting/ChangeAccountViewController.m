//
//  ChangeAccountViewController.m
//  digitalCurrency
//
//  Created by iDog on 2019/2/28.
//  Copyright © 2019年 BIZZAN. All rights reserved.
//

#import "ChangeAccountViewController.h"
#import "FMDatabase.h"
#import "FMDatabaseQueue.h"
#import "ChangeAccountTableViewCell.h"
#import "LoginViewController.h"
#import "YLNavigationController.h"
#import "LoginNetManager.h"

@interface ChangeAccountViewController ()<UITableViewDelegate,UITableViewDataSource>
@property (weak, nonatomic) IBOutlet UITableView *tableView;
@property (weak, nonatomic) IBOutlet NSLayoutConstraint *bottomViewHeight;
@property(nonatomic,strong)NSMutableArray *nameArr;
@property(nonatomic,strong)NSMutableArray *passArr;
@property(nonatomic,copy)NSString *loginName; //登陆的名字
@property(nonatomic,assign)NSInteger selectIndex;//当前登录的账号index
@end

@implementation ChangeAccountViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    self.title = @"切换账号";
    self.bottomViewHeight.constant = SafeAreaBottomHeight;
    self.nameArr = [[NSMutableArray alloc] init];
    self.passArr = [[NSMutableArray alloc] init];
    self.tableView.delegate = self;
    self.tableView.dataSource = self;
    [self.tableView registerNib:[UINib nibWithNibName:@"ChangeAccountTableViewCell" bundle:nil] forCellReuseIdentifier:NSStringFromClass([ChangeAccountTableViewCell class])];
    [self.tableView registerClass:[UITableViewCell class] forCellReuseIdentifier:NSStringFromClass([UITableViewCell class])];
    // Do any additional setup after loading the view from its nib.

}
-(void)viewWillAppear:(BOOL)animated{
    [super viewWillAppear:animated];
    [self.nameArr removeAllObjects];
    [self.passArr removeAllObjects];
    self.loginName = [[NSUserDefaults standardUserDefaults] objectForKey:@"userNameTF"];
    [self getLoginInfo];
}
-(NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section{
    if (section == 0) {
        return self.nameArr.count;
    }else{
        return 1;
    }
}
-(NSInteger)numberOfSectionsInTableView:(UITableView *)tableView{
    return 2;
}
- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath
{
    if (indexPath.section == 0) {
        ChangeAccountTableViewCell *cell = [tableView dequeueReusableCellWithIdentifier:NSStringFromClass([ChangeAccountTableViewCell class]) forIndexPath:indexPath];
        cell.selectionStyle = UITableViewCellSelectionStyleNone;
        cell.accountNun.text = [NSString stringWithFormat:@"账号：%@",self.nameArr[indexPath.row]];
        //hookImage
        if (indexPath.row == self.selectIndex) {
            cell.selectStatus.image = [UIImage imageNamed:@"hookImage"];
        }else{
           cell.selectStatus.image = [UIImage imageNamed:@""];
        }
        return cell;
    }else{
        UITableViewCell *cell
        = [tableView dequeueReusableCellWithIdentifier:NSStringFromClass([UITableViewCell class])forIndexPath:indexPath];
        cell.textLabel.text = @"添加新账号登录";
        cell.selectionStyle = UITableViewCellSelectionStyleNone;
        cell.accessoryType = UITableViewCellAccessoryDisclosureIndicator;
        return cell;
    }
}
-(void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath{
    if (indexPath.section == 0) {
        //
        if (self.selectIndex == indexPath.row) {
            //不走接口
        }else{
            [self login:self.nameArr[indexPath.row] withPass:self.passArr[indexPath.row] withIndex:indexPath.row];
        }
    }else{
        //添加账号
        LoginViewController *loginVC = [[LoginViewController alloc] init];
        YLNavigationController *logNavi = [[YLNavigationController alloc] initWithRootViewController:loginVC];
        loginVC.hidesBottomBarWhenPushed = YES;
        [self presentViewController:logNavi animated:YES completion:nil];
    }
}
//MARK:--登录接口
-(void)login:(NSString *)userName withPass:(NSString *)password withIndex:(NSInteger)index{
    [EasyShowLodingView showLodingText:@"登录中..."];
    [ LoginNetManager LogInForUsername:userName andpassword:password CompleteHandle:^(id resPonseObj, int code) {
        [EasyShowLodingView hidenLoding];
        if (code) {
            if ([resPonseObj[@"code"] integerValue] == 0) {
                [YLUserInfo getuserInfoWithDic:resPonseObj[@"data"]];//存储登录信息
                [[NSUserDefaults standardUserDefaults] setObject:userName forKey:@"userNameTF"];
                [[NSUserDefaults standardUserDefaults] synchronize];
                [self.view makeToast:resPonseObj[MESSAGE] duration:1.0 position:CSToastPositionCenter];
                self.selectIndex = index;
                [self.tableView reloadData];

            }else{
                [self.view makeToast:resPonseObj[MESSAGE] duration:1.5 position:CSToastPositionCenter];
            }
        }else{
            [self.view makeToast:LocalizationKey(@"networkAbnormal") duration:1.5 position:CSToastPositionCenter];
        }
    }];
}
-(CGFloat)tableView:(UITableView *)tableView heightForRowAtIndexPath:(NSIndexPath *)indexPath{
    if (indexPath.section == 0) {
        return 60;
    }else{
        return 50;
    }
}
-(CGFloat)tableView:(UITableView *)tableView heightForFooterInSection:(NSInteger)section{
    return 0.01;
}
-(CGFloat)tableView:(UITableView *)tableView heightForHeaderInSection:(NSInteger)section{
    if (section == 0) {
        return 10;
    }else{
        return 20;
    }
}
//MARK:--查询数据
-(void)getLoginInfo{
    NSString *doc = [NSSearchPathForDirectoriesInDomains(NSDocumentDirectory, NSUserDomainMask, YES) objectAtIndex:0];
    NSString *path = [doc stringByAppendingPathComponent:@"user.sqlite"];

    FMDatabase *db = [FMDatabase databaseWithPath:path];
    if ([db open]) {
        NSString *sql = @"select *from user";
        FMResultSet *rs = [db executeQuery:sql];
        while ([rs next]) {
            int userId = [rs intForColumn:@"id"];
            NSLog(@"userId --- %d",userId);
            NSString *name = [rs stringForColumn:@"name"];
            NSString *pass = [rs stringForColumn:@"password"];
            [self.nameArr addObject:name];
            [self.passArr addObject:pass];

            for (int i= 0; i<self.nameArr.count; i++) {
                NSString *name = self.nameArr[i];
                if ([self.loginName isEqualToString:name]) {
                    self.selectIndex = i;
                }
            }
            [self.tableView reloadData];
        }
        [db close];
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
