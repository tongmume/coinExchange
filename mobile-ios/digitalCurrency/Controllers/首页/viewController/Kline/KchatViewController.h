//
//  KchatViewController.h
//  digitalCurrency
//
//  Created by sunliang on 2019/5/18.
//  Copyright © 2019年 BIZZAN. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface KchatViewController : BaseViewController
@property (weak, nonatomic) IBOutlet UITableView *tableView;
@property (nonatomic,copy) NSString*symbol;//交易对
@property (weak, nonatomic) IBOutlet UIView *backView;
@property (weak, nonatomic) IBOutlet UIImageView *collectIamgeV;
@property (weak, nonatomic) IBOutlet UILabel *collectLabel;

@property (nonatomic, assign) BOOL isShowContract;

@property (nonatomic, assign) NSString *istype;

@end
