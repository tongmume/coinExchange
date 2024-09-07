//
//  BankcardTableViewCell.h
//  digitalCurrency
//
//  Created by startlink on 2019/8/23.
//  Copyright © 2019年 BIZZAN. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface BankcardTableViewCell : UITableViewCell

@property (weak, nonatomic) IBOutlet UIImageView *bankimageV;
@property (weak, nonatomic) IBOutlet UILabel *bankNum;
@property (weak, nonatomic) IBOutlet UILabel *banknumlabel;
@property (weak, nonatomic) IBOutlet UILabel *bankname;
@property (weak, nonatomic) IBOutlet UILabel *banknamelabel;
@property (weak, nonatomic) IBOutlet UILabel *bankaddress;
@property (weak, nonatomic) IBOutlet UILabel *bankaddresslabel;
@property (weak, nonatomic) IBOutlet UIButton *showbut;

@property (nonatomic,copy)void (^showBankBlock)(BOOL showBank);
@end
