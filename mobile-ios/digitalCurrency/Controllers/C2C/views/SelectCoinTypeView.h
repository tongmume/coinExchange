//
//  SelectCoinTypeView.h
//  digitalCurrency
//
//  Created by iDog on 2019/2/9.
//  Copyright © 2019年 BIZZAN. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "SelectCoinTypeModel.h"
@class SelectCoinTypeView;
@protocol SelectCoinTypeViewDelegate <NSObject>
- (void)selectCoinTypeModel:(SelectCoinTypeModel *)model  enterIndex:(NSInteger)index payWaysArr:(NSMutableArray *)payWaysArr;
@end

@interface SelectCoinTypeView : UIView<UITableViewDelegate,UITableViewDataSource>

@property (weak, nonatomic) IBOutlet UITableView *tableView;
@property (weak, nonatomic) IBOutlet UIButton *certainButton;
@property(nonatomic,strong)NSArray<SelectCoinTypeModel *> *modelArr;
@property (nonatomic,weak) id<SelectCoinTypeViewDelegate> delegate;
@property(nonatomic,assign)NSInteger enterIndex;//进入的类型 1:选择币种进入 2：选择付款方式
@property(nonatomic,strong)NSMutableArray *payWaysSelectArr;
@property (weak, nonatomic) IBOutlet UIView *boardView;
@property (weak, nonatomic) IBOutlet NSLayoutConstraint *boardViewHeightConstraint;
@property (weak, nonatomic) IBOutlet NSLayoutConstraint *doneBtnHeightConstraint;


@end
