//
//  MyEntrustDetail2TableViewCell.h
//  digitalCurrency
//
//  Created by iDog on 2019/4/23.
//  Copyright © 2019年 BIZZAN. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface MyEntrustDetail2TableViewCell : UITableViewCell
@property (weak, nonatomic) IBOutlet UILabel *timeData;
@property (weak, nonatomic) IBOutlet UILabel *dealPriceData;
@property (weak, nonatomic) IBOutlet UILabel *dealNumData;
@property (weak, nonatomic) IBOutlet UILabel *feeData;
@property (weak, nonatomic) IBOutlet NSLayoutConstraint *leftLabelTitleWidth;

//国际化需要
@property (weak, nonatomic) IBOutlet UILabel *timeLabel;
@property (weak, nonatomic) IBOutlet UILabel *dealPriceLabel;
@property (weak, nonatomic) IBOutlet UILabel *dealNum;
@property (weak, nonatomic) IBOutlet UILabel *feeLabel;

@end
