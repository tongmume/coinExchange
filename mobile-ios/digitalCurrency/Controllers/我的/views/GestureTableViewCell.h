//
//  GestureTableViewCell.h
//  digitalCurrency
//
//  Created by chu on 2019/8/9.
//  Copyright © 2019年 BIZZAN. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface GestureTableViewCell : UITableViewCell
@property (weak, nonatomic) IBOutlet UILabel *leftLabel;
@property (weak, nonatomic) IBOutlet UISwitch *gestureSwitch;

@end
