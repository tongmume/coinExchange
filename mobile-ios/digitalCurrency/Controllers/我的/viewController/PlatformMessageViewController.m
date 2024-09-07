//
//  PlatformMessageViewController.m
//  digitalCurrency
//
//  Created by iDog on 2019/1/29.
//  Copyright © 2019年 BIZZAN. All rights reserved.
//

#import "PlatformMessageViewController.h"
#import "PlatformMessageCell.h"
#import "MineNetManager.h"
#import "PlatformMessageModel.h"
#import "PlatformMessageDetailViewController.h"

@interface PlatformMessageViewController ()<UITableViewDelegate,UITableViewDataSource>
@property (weak, nonatomic) IBOutlet UITableView *tableView;
@property (weak, nonatomic) IBOutlet NSLayoutConstraint *bottomViewHeight;
@property(nonatomic,assign)NSInteger pageNo;
@property(nonatomic,copy)NSMutableArray *platformMessageArr;
@end

@implementation PlatformMessageViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    self.navigationItem.title= LocalizationKey(@"platformMessage");
    self.tableView.delegate = self;
    self.tableView.dataSource = self;
    self.tableView.rowHeight = 90;
    [self.tableView registerNib:[UINib nibWithNibName:@"PlatformMessageCell" bundle:nil] forCellReuseIdentifier:NSStringFromClass([PlatformMessageCell class])];
    self.bottomViewHeight.constant = SafeAreaBottomHeight;
    [self headRefreshWithScrollerView:self.tableView];
    [self footRefreshWithScrollerView:self.tableView];
    LYEmptyView *emptyView = [LYEmptyView emptyViewWithImageStr:@"emptyData" titleStr:LocalizationKey(@"noPlatformMessageData")];
    self.tableView.ly_emptyView = emptyView;
    self.pageNo = 1;
    [self getData];
}
//MARK:--上拉加载
- (void)refreshFooterAction{
    self.pageNo++;
    [self getData];
}
//MARK:--下拉刷新
- (void)refreshHeaderAction{
    self.pageNo = 1;
    [self getData];
}
//MARK:--获取消息
-(void)getData{
   [EasyShowLodingView showLodingText:LocalizationKey(@"loading")];
     NSString *pageNoStr = [[NSString alloc] initWithFormat:@"%ld",(long)_pageNo];
    [MineNetManager getPlatformMessageForCompleteHandleWithPageNo:pageNoStr withPageSize:@"20" CompleteHandle:^(id resPonseObj, int code) {
        [EasyShowLodingView hidenLoding];
        if (code) {
            if ([resPonseObj[@"code"] integerValue] == 0) {
                //获取数据成功
                if (_pageNo == 1) {
                    [_platformMessageArr removeAllObjects];
                }
                NSArray *dataArr = [PlatformMessageModel mj_objectArrayWithKeyValuesArray:resPonseObj[@"data"][@"content"]];
                [self.platformMessageArr addObjectsFromArray:dataArr];
                [self.tableView reloadData];
            }else{
                [self.view makeToast:resPonseObj[MESSAGE] duration:1.5 position:CSToastPositionCenter];
            }
        }else{
            [self.view makeToast:LocalizationKey(@"networkAbnormal") duration:1.5 position:CSToastPositionCenter];
        }
    }];
}
- (NSMutableArray *)platformMessageArr {
    if (!_platformMessageArr) {
        _platformMessageArr = [NSMutableArray array];
    }
    return _platformMessageArr;
}
-(NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section{
    return _platformMessageArr.count;
}
-(NSInteger)numberOfSectionsInTableView:(UITableView *)tableView{
    return 1;
}

- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath
{
    PlatformMessageCell *cell = [tableView dequeueReusableCellWithIdentifier:NSStringFromClass([PlatformMessageCell class]) forIndexPath:indexPath];
    cell.selectionStyle = UITableViewCellSelectionStyleNone;
    PlatformMessageModel *model = _platformMessageArr[indexPath.row];
    cell.messageTitle.text = model.title;
    cell.messageCreateTime.text = model.createTime;
    [cell.messageImage sd_setImageWithURL:[NSURL URLWithString:model.imgUrl] placeholderImage:[UIImage imageNamed:@"placeholderImage"]];

    return cell;
}
-(void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath{
    PlatformMessageModel *model = _platformMessageArr[indexPath.row];
    PlatformMessageDetailViewController *detailVC = [[PlatformMessageDetailViewController alloc] init];
    detailVC.hidesBottomBarWhenPushed = YES;
    detailVC.content = model.content;
    detailVC.navtitle = model.title;
    detailVC.ID = model.ID;
    [self.navigationController pushViewController:detailVC animated:YES];
}

- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}
-(void)dealloc{

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
