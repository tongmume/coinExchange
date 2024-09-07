//
//  MyAdvertisingViewController.m
//  digitalCurrency
//
//  Created by iDog on 2019/3/5.
//  Copyright © 2019年 BIZZAN. All rights reserved.
//

#import "MyAdvertisingViewController.h"
#import "MyAdvertisingTableViewCell.h"
#import "MineNetManager.h"
#import "MyAdvertisingModel.h"
#import "MyAdvertisingAlterView.h"
#import "MyAdvertisingDetailModel.h"
#import "AdvertisingBuyViewController.h"
#import "AdvertisingSellViewController.h"
#import "YBPopupMenu.h"

@interface MyAdvertisingViewController ()<UITableViewDelegate,UITableViewDataSource,YBPopupMenuDelegate>{
    MyAdvertisingAlterView *_myAdvertisingAlterView;
}
@property (weak, nonatomic) IBOutlet UITableView *tableView;
@property (weak, nonatomic) IBOutlet NSLayoutConstraint *bottomViewHeight;
@property(nonatomic,strong)NSMutableArray *myAdvertisingArr;
@property(nonatomic,copy)NSString *userName;
@property(nonatomic,strong)MyAdvertisingModel *infoModel;
@property(nonatomic,strong)MyAdvertisingDetailModel *detailModel;
@property(nonatomic,strong)NSIndexPath *deleteIndex;//删除广告的index
@end

@implementation MyAdvertisingViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    self.title = [[ChangeLanguage bundle] localizedStringForKey:@"myAdvertising" value:nil table:@"English"];
    [self backBtnNoNavBar:NO normalBack:YES];
    self.bottomViewHeight.constant = SafeAreaBottomHeight;
    self.tableView.delegate = self;
    self.tableView.dataSource = self;
    [self.tableView registerNib:[UINib nibWithNibName:@"MyAdvertisingTableViewCell" bundle:nil] forCellReuseIdentifier:NSStringFromClass([MyAdvertisingTableViewCell class])];
    self.userName = [YLUserInfo shareUserInfo].username;
    LYEmptyView *emptyView = [LYEmptyView emptyViewWithImageStr:@"emptyData" titleStr:[[ChangeLanguage bundle] localizedStringForKey:@"noAdvertisingTip" value:nil table:@"English"]];
    self.tableView.ly_emptyView = emptyView;

    [self RightsetupNavgationItemWithpictureName:@"zicanliushui"];

}
-(void)viewWillAppear:(BOOL)animated{
    [super viewWillAppear:animated];
//    [self setNavigationControllerStyle];
    [self getData];
}

//发布广告
-(void)RighttouchEvent{

    NSArray *namearray = @[LocalizationKey(@"postPurchaseAdvertising"),LocalizationKey(@"sellAdvertising")];

    [YBPopupMenu showAtPoint:CGPointMake(kWindowW - 32, NEW_NavHeight - 15) titles:namearray icons:nil menuWidth:130 otherSettings:^(YBPopupMenu *popupMenu) {
        popupMenu.arrowDirection = YBPopupMenuArrowDirectionNone;
        popupMenu.delegate = self;
        popupMenu.textColor = RGBOF(0xe6e6e6);
        popupMenu.backColor = mainColor;

    }];

}

#pragma mark - YBPopupMenuDelegate
- (void)ybPopupMenu:(YBPopupMenu *)ybPopupMenu didSelectedAtIndex:(NSInteger)index
{
    if (index == 0) {
        //购买
        AdvertisingBuyViewController *buyVC = [[AdvertisingBuyViewController alloc] init];
        [self.navigationController pushViewController:buyVC animated:YES];

    }

    if (index == 1) {

        //出售
        AdvertisingSellViewController *sellVC = [[AdvertisingSellViewController alloc] init];
        [self.navigationController pushViewController:sellVC animated:YES];

    }

}
//MARK:--获取广告数据信息
-(void)getData{
    [EasyShowLodingView showLodingText:[[ChangeLanguage bundle] localizedStringForKey:@"loading" value:nil table:@"English"]];
    [MineNetManager getMyAdvertisingForCompleteHandle:^(id resPonseObj, int code) {
        [EasyShowLodingView hidenLoding];
        [_myAdvertisingArr removeAllObjects];
        if (code) {
            if ([resPonseObj[@"code"] integerValue] == 0) {
                NSArray *dataArr = [MyAdvertisingModel mj_objectArrayWithKeyValuesArray:resPonseObj[@"data"][@"content"]];
                [self.myAdvertisingArr addObjectsFromArray:dataArr];
                [self.tableView reloadData];
            }else{
                [self.view makeToast:resPonseObj[MESSAGE] duration:1.5 position:CSToastPositionCenter];
            }
        }else{
            [self.view makeToast:[[ChangeLanguage bundle] localizedStringForKey:@"noNetworkStatus" value:nil table:@"English"] duration:1.5 position:CSToastPositionCenter];
        }
    }];
}
- (NSMutableArray *)myAdvertisingArr {
    if (!_myAdvertisingArr) {
        _myAdvertisingArr = [NSMutableArray array];
    }
    return _myAdvertisingArr;
}
-(NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section{
    return 1;
}
-(NSInteger)numberOfSectionsInTableView:(UITableView *)tableView{
    return _myAdvertisingArr.count;

}

-(CGFloat)tableView:(UITableView *)tableView heightForHeaderInSection:(NSInteger)section{
    return 10;
}

-(UIView *)tableView:(UITableView *)tableView viewForHeaderInSection:(NSInteger)section{
    UIView * headview = [[UIView alloc]init];
    headview.backgroundColor = mainColor;
    return headview;

}

- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath
{
    MyAdvertisingTableViewCell *cell = [tableView dequeueReusableCellWithIdentifier:NSStringFromClass([MyAdvertisingTableViewCell class]) forIndexPath:indexPath];
    [cell.headImage sd_setImageWithURL:[NSURL URLWithString:self.avatar] placeholderImage:[UIImage imageNamed:@"defaultImage"]];
    cell.selectionStyle = UITableViewCellSelectionStyleNone;
    MyAdvertisingModel *model = _myAdvertisingArr[indexPath.section];
    cell.userName.text = self.userName;
    if ([model.advertiseType isEqualToString:@"0"]) {
        //购买
        cell.advertisingType.text = [[ChangeLanguage bundle] localizedStringForKey:@"buy" value:nil table:@"English"];
    }else{
        cell.advertisingType.text = [[ChangeLanguage bundle] localizedStringForKey:@"sell" value:nil table:@"English"];
    }
    if ([model.status isEqualToString:@"0"]) {
        cell.statusLabel.text=[[ChangeLanguage bundle] localizedStringForKey:@"grounding" value:nil table:@"English"];
    }else{
        cell.statusLabel.text=[[ChangeLanguage bundle] localizedStringForKey:@"shelved" value:nil table:@"English"];
    }
    cell.limitNum.text = [NSString stringWithFormat:@"%@ %@-%@CNY",[[ChangeLanguage bundle] localizedStringForKey:@"limit" value:nil table:@"English"],model.minLimit,model.maxLimit];
    cell.coinNum.text = [NSString stringWithFormat:@"%@ %@%@",LocalizationKey(@"amonut"),[ToolUtil judgeStringForDecimalPlaces:model.remainAmount],model.coin.unit];
    NSString *numstr = [NSString stringWithFormat:@"%f",([model.number doubleValue]-[model.remainAmount doubleValue])];
    cell.Surplusnum.text = [NSString stringWithFormat:@"%@%@", [ToolUtil judgeStringForDecimalPlaces:numstr],model.coin.unit];
    return cell;
}
-(void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath{
     MyAdvertisingModel *model = _myAdvertisingArr[indexPath.section];
    self.infoModel = model;
    [self myAdvertisingAlterView];
}
// UITableViewDataSource协议中定义的方法。该方法的返回值决定某行是否可编辑
- (BOOL) tableView:(UITableView *)tableView canEditRowAtIndexPath:(NSIndexPath *)indexPath{
    return YES;
}
// 自定义左滑显示编辑按钮
- (NSArray<UITableViewRowAction*>*)tableView:(UITableView *)tableView editActionsForRowAtIndexPath:(NSIndexPath *)indexPath {

    UITableViewRowAction *rowAction = [UITableViewRowAction rowActionWithStyle:UITableViewRowActionStyleDefault title:LocalizationKey(@"delete") handler:^(UITableViewRowAction * _Nonnull action, NSIndexPath * _Nonnull indexPath) {

//        NSLog(@"删除");
        self.deleteIndex = indexPath;

        MyAdvertisingModel *model = _myAdvertisingArr[indexPath.section];
        if ([model.status isEqualToString:@"0"]) {
            //上架广告，可以下架
            [self.view makeToast:LocalizationKey(@"deleteShelvesAdvertiseTip") duration:1.5 position:CSToastPositionCenter];
        }else{
            //删除广告
            [self deleteAdvertiseInfo:model.ID];
        }
        //取消编辑状态
        [tableView setEditing:NO animated:YES];
    }];
    NSArray *arr = @[rowAction];
    return arr;
}

//MARK:--删除广告接口
-(void)deleteAdvertiseInfo:(NSString *)advertiseId{
    [EasyShowLodingView showLodingText:[[ChangeLanguage bundle] localizedStringForKey:@"loading" value:nil table:@"English"]];
    [MineNetManager deleteAdvertiseForAdvertiseId:advertiseId CompleteHandle:^(id resPonseObj, int code) {
        [EasyShowLodingView hidenLoding];
        if (code) {
            if ([resPonseObj[@"code"] integerValue] == 0) {
                [self.view makeToast:resPonseObj[MESSAGE] duration:1.5 position:CSToastPositionCenter];
                dispatch_after(dispatch_time(DISPATCH_TIME_NOW, (int64_t)(1.5 * NSEC_PER_SEC)), dispatch_get_main_queue(), ^{
                    dispatch_async(dispatch_get_main_queue(), ^{
                        [_myAdvertisingArr removeObjectAtIndex:self.deleteIndex.section];
                        [_tableView reloadData];
                    });
                });
            }else{
                [self.view makeToast:resPonseObj[MESSAGE] duration:1.5 position:CSToastPositionCenter];
            }
        }else{
            [self.view makeToast:[[ChangeLanguage bundle] localizedStringForKey:@"noNetworkStatus" value:nil table:@"English"] duration:1.5 position:CSToastPositionCenter];
        }
    }];
}

//MARK:--点击广告弹出的提示框
-(void)myAdvertisingAlterView{
    if (!_myAdvertisingAlterView) {
        _myAdvertisingAlterView = [[NSBundle mainBundle] loadNibNamed:@"MyAdvertisingAlterView" owner:nil options:nil].firstObject;
        _myAdvertisingAlterView.frame=[UIScreen mainScreen].bounds;
        [_myAdvertisingAlterView.cancelButton addTarget:self action:@selector(push:) forControlEvents:UIControlEventTouchUpInside];
        [_myAdvertisingAlterView.changeButton addTarget:self action:@selector(push:) forControlEvents:UIControlEventTouchUpInside];
        [_myAdvertisingAlterView.backOnButton addTarget:self action:@selector(push:) forControlEvents:UIControlEventTouchUpInside];

        UITapGestureRecognizer *tap = [[UITapGestureRecognizer alloc]initWithTarget:self action:@selector(removeformeview)];
        _myAdvertisingAlterView.removeview.userInteractionEnabled = YES;
        [_myAdvertisingAlterView.removeview addGestureRecognizer:tap];
    }
    CGAffineTransform translates = CGAffineTransformTranslate(CGAffineTransformIdentity, 0, 0);
    _myAdvertisingAlterView.backView.transform = CGAffineTransformTranslate(CGAffineTransformIdentity,0,_myAdvertisingAlterView.backView.height);
    [UIView animateWithDuration:0.3 delay:0.1 usingSpringWithDamping:1 initialSpringVelocity:10 options:UIViewAnimationOptionCurveLinear animations:^{
        _myAdvertisingAlterView.backView.transform = translates;
    } completion:^(BOOL finished) {

    }];
    if ([self.infoModel.status isEqualToString:@"0"]) {
        //上架广告，可以下架
        _myAdvertisingAlterView.backOnlabel.text =[[ChangeLanguage bundle] localizedStringForKey:@"shelves" value:nil table:@"English"]  ;
         [_myAdvertisingAlterView.backOnButton setImage:[UIImage imageNamed:@"gg_xiaji"] forState:UIControlStateNormal];
    }else{

        _myAdvertisingAlterView.backOnlabel.text =[[ChangeLanguage bundle] localizedStringForKey:@"Added" value:nil table:@"English"]  ;

        [_myAdvertisingAlterView.backOnButton setImage:[UIImage imageNamed:@"gg_shangjia"] forState:UIControlStateNormal];

    }

    [UIApplication.sharedApplication.keyWindow addSubview:_myAdvertisingAlterView];
}

-(void)removeformeview{
    [_myAdvertisingAlterView removeFromSuperview];

}
-(void)push:(UIButton*)button{
    [_myAdvertisingAlterView removeFromSuperview];
    if (button.tag == 1) {
        //修改
        if ([self.infoModel.status isEqualToString:@"0"]) {
            //上架
            [self.view makeToast:[[ChangeLanguage bundle] localizedStringForKey:@"shelvesTip" value:nil table:@"English"] duration:1.5 position:CSToastPositionCenter];
            return ;
        }else{
            //修改
            [self changeAdvertising];
        }
    }else if (button.tag == 2){
        if ([self.infoModel.status isEqualToString:@"0"]) {
            //下架广告
           [self downAdvertising];
        }else{
            //上架广告
            [self upAdvertising];
        }
    }else if (button.tag == 3){

        if ([self.infoModel.status isEqualToString:@"0"]) {
            //上架广告，可以下架
            [self.view makeToast:LocalizationKey(@"deleteShelvesAdvertiseTip") duration:1.5 position:CSToastPositionCenter];
        }else{
            //删除广告
            [self deleteAdvertiseInfo:self.infoModel.ID];
        }
    }
}
//MARK:--修改广告
-(void)changeAdvertising{
    [EasyShowLodingView showLodingText:[[ChangeLanguage bundle] localizedStringForKey:@"loading" value:nil table:@"English"]];
    [MineNetManager getMyAdvertisingDetailInfoForAdvertisingId:self.infoModel.ID CompleteHandle:^(id resPonseObj, int code) {
        [EasyShowLodingView hidenLoding];
        if (code) {
            if ([resPonseObj[@"code"] integerValue] == 0) {
                self.detailModel = [MyAdvertisingDetailModel mj_objectWithKeyValues:resPonseObj[@"data"]];
                [_myAdvertisingAlterView removeFromSuperview];
                if ([self.detailModel.advertiseType isEqualToString:@"0" ]) {
                    //购买广告  进入购买广告编辑界面
                    AdvertisingBuyViewController *buyVC = [[AdvertisingBuyViewController alloc] init];
                    buyVC.hidesBottomBarWhenPushed = YES;
                    buyVC.index = 1;
                    buyVC.detailModel = self.detailModel;
                    [self.navigationController pushViewController:buyVC animated:YES];
                }else{
                  //出售广告  进入出售广告编辑界面
                    AdvertisingSellViewController *sellVC = [[AdvertisingSellViewController alloc] init];
                    sellVC.hidesBottomBarWhenPushed = YES;
                    sellVC.index = 1;
                    sellVC.detailModel = self.detailModel;
                    [self.navigationController pushViewController:sellVC animated:YES];
                }
            }else{
                [UIApplication.sharedApplication.keyWindow makeToast:resPonseObj[MESSAGE] duration:1.5 position:CSToastPositionCenter];
            }
        }else{
            [UIApplication.sharedApplication.keyWindow makeToast:[[ChangeLanguage bundle] localizedStringForKey:@"noNetworkStatus" value:nil table:@"English"] duration:1.5 position:CSToastPositionCenter];
        }
    }];
}
//MARK:--上架广告
-(void)upAdvertising{
    [EasyShowLodingView showLodingText:[[ChangeLanguage bundle] localizedStringForKey:@"AddingAdvertiseTip" value:nil table:@"English"]];
    [MineNetManager upMyAdvertisingForAdvertisingId:self.infoModel.ID CompleteHandle:^(id resPonseObj, int code) {
        [EasyShowLodingView hidenLoding];
        if (code) {
            if ([resPonseObj[@"code"] integerValue] == 0) {
                [self.view makeToast:[[ChangeLanguage bundle] localizedStringForKey:@"addAdvertiseSuccessTip" value:nil table:@"English"] duration:1.0 position:CSToastPositionCenter];
                [_myAdvertisingAlterView removeFromSuperview];
                dispatch_after(dispatch_time(DISPATCH_TIME_NOW, (int64_t)(1.5 * NSEC_PER_SEC)), dispatch_get_main_queue(), ^{
                    dispatch_async(dispatch_get_main_queue(), ^{
                         [self getData];
                    });
                });

            }else{
                [UIApplication.sharedApplication.keyWindow makeToast:resPonseObj[MESSAGE] duration:1.5 position:CSToastPositionCenter];
            }
        }else{
            [UIApplication.sharedApplication.keyWindow makeToast:[[ChangeLanguage bundle] localizedStringForKey:@"noNetworkStatus" value:nil table:@"English"] duration:1.5 position:CSToastPositionCenter];
        }
    }];
}
//MARK:--下架广告
-(void)downAdvertising{
    [EasyShowLodingView showLodingText:[[ChangeLanguage bundle] localizedStringForKey:@"shelvesAdvertiseTip" value:nil table:@"English"]];
    [MineNetManager downMyAdvertisingForAdvertisingId:self.infoModel.ID CompleteHandle:^(id resPonseObj, int code) {
        [EasyShowLodingView hidenLoding];
        if (code) {
            if ([resPonseObj[@"code"] integerValue] == 0) {
                [self.view makeToast:[[ChangeLanguage bundle] localizedStringForKey:@"shelvesAdvertiseSuccessTip" value:nil table:@"English"] duration:1.0 position:CSToastPositionCenter];
                [_myAdvertisingAlterView removeFromSuperview];
                dispatch_after(dispatch_time(DISPATCH_TIME_NOW, (int64_t)(1.5 * NSEC_PER_SEC)), dispatch_get_main_queue(), ^{
                    dispatch_async(dispatch_get_main_queue(), ^{
                        [self getData];
                    });
                });
            }else{
                [UIApplication.sharedApplication.keyWindow makeToast:resPonseObj[MESSAGE] duration:1.5 position:CSToastPositionCenter];
            }
        }else{
            [UIApplication.sharedApplication.keyWindow makeToast:[[ChangeLanguage bundle] localizedStringForKey:@"noNetworkStatus" value:nil table:@"English"] duration:1.5 position:CSToastPositionCenter];
        }
    }];
}
-(CGFloat)tableView:(UITableView *)tableView heightForRowAtIndexPath:(NSIndexPath *)indexPath{
    return 116;
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
