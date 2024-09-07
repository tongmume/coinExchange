//
//  AdministrationTableViewCell.h
//  digitalCurrency
//
//  Created by startlink on 2019/8/6.
//  Copyright © 2019年 BIZZAN. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface AdministrationTableViewCell : UITableViewCell

@property (weak, nonatomic) IBOutlet UIView *backview;
@property (weak, nonatomic) IBOutlet UILabel *titlelabel;
@property (weak, nonatomic) IBOutlet UIView *butview1;
@property (weak, nonatomic) IBOutlet UIView *butview2;
@property (weak, nonatomic) IBOutlet UIButton *Advertisementbut;
@property (weak, nonatomic) IBOutlet UIButton *orderbut;
@property (weak, nonatomic) IBOutlet NSLayoutConstraint *bottomspec;

@property (nonatomic,copy) void (^butBlock)(NSInteger num);
@end
