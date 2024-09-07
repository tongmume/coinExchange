//
//  ChargerecordTableViewCell.h
//  digitalCurrency
//
//  Created by startlink on 2019/8/7.
//  Copyright © 2019年 BIZZAN. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface ChargerecordTableViewCell : UITableViewCell
@property (weak, nonatomic) IBOutlet UILabel *typelabel;
@property (weak, nonatomic) IBOutlet UILabel *timelabel;
@property (weak, nonatomic) IBOutlet UILabel *addresslabel;
@property (weak, nonatomic) IBOutlet UILabel *numlabel;




//国际化需要
@property (weak, nonatomic) IBOutlet UILabel *Paymentdate;
@property (weak, nonatomic) IBOutlet UILabel *Chargeaddress;
@property (weak, nonatomic) IBOutlet UILabel *Amountrecharge;


@end
