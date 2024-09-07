//
//  MyEntrustDetail1TableViewCell.h
//  digitalCurrency
//
//  Created by iDog on 2019/4/23.
//  Copyright © 2019年 BIZZAN. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface MyEntrustDetail1TableViewCell : UITableViewCell
@property (weak, nonatomic) IBOutlet UILabel *payStatus;
@property (weak, nonatomic) IBOutlet UILabel *coinType;
@property (weak, nonatomic) IBOutlet UILabel *dealNumTitle;
@property (weak, nonatomic) IBOutlet UILabel *dealNumData;
@property (weak, nonatomic) IBOutlet UILabel *dealPerPriceTitle;
@property (weak, nonatomic) IBOutlet UILabel *dealPerPriceData;
@property (weak, nonatomic) IBOutlet UILabel *dealTotalNumTitle;
@property (weak, nonatomic) IBOutlet UILabel *dealTotalNumData;
@property (weak, nonatomic) IBOutlet NSLayoutConstraint *leftLabelTitleWidth;

//国际化需要
@property (weak, nonatomic) IBOutlet UILabel *dealDetailLabel;

@end
