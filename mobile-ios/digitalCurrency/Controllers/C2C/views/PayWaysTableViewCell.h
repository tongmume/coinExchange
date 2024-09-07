//
//  PayWaysTableViewCell.h
//  digitalCurrency
//
//  Created by iDog on 2019/2/23.
//  Copyright © 2019年 BIZZAN. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface PayWaysTableViewCell : UITableViewCell
@property (weak, nonatomic) IBOutlet UILabel *leftLabel;
@property (weak, nonatomic) IBOutlet UIImageView *rightImage;
@property (weak, nonatomic) IBOutlet UIButton *rightButton;

@end
