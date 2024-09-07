//
//  HelpCenterTableViewCell.h
//  digitalCurrency
//
//  Created by chu on 2019/8/6.
//  Copyright © 2019年 BIZZAN. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "HelpCenterModel.h"

@interface HelpCenterTableViewCell : UITableViewCell

@property (weak, nonatomic) IBOutlet UILabel *topLabel;
@property (weak, nonatomic) IBOutlet UILabel *bottomLabel;
@property (weak, nonatomic) IBOutlet UIView *backView;
@property (nonatomic, strong) HelpCenterContentModel *model;
@end
