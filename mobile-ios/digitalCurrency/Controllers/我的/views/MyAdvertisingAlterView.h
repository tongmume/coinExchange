//
//  MyAdvertisingAlterView.h
//  digitalCurrency
//
//  Created by iDog on 2019/2/2.
//  Copyright © 2019年 BIZZAN. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface MyAdvertisingAlterView : UIView

@property (weak, nonatomic) IBOutlet UIView *backView;

@property (weak, nonatomic) IBOutlet UIButton *changeButton;//修改
@property (weak, nonatomic) IBOutlet UIButton *backOnButton;//重新上架
@property (weak, nonatomic) IBOutlet UIButton *cancelButton;//取消


@property (weak, nonatomic) IBOutlet UILabel *changelabel;
@property (weak, nonatomic) IBOutlet UILabel *backOnlabel;
@property (weak, nonatomic) IBOutlet UILabel *delegatelabel;
@property (weak, nonatomic) IBOutlet UIView *removeview;
@end
