//
//  CurrencyrecordTableViewCell.h
//  digitalCurrency
//
//  Created by startlink on 2019/8/7.
//  Copyright © 2019年 BIZZAN. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface CurrencyrecordTableViewCell : UITableViewCell

@property (weak, nonatomic) IBOutlet UILabel *typelabel;
@property (weak, nonatomic) IBOutlet UILabel *statuelabel;
@property (weak, nonatomic) IBOutlet UILabel *timelabel;
@property (weak, nonatomic) IBOutlet UILabel *addresslabel;
@property (weak, nonatomic) IBOutlet UILabel *numlabel;
@property (weak, nonatomic) IBOutlet UILabel *freelabel;

//国际化
@property (weak, nonatomic) IBOutlet UILabel *Presenttime;
@property (weak, nonatomic) IBOutlet UILabel *Presentaddress;
@property (weak, nonatomic) IBOutlet UILabel *PresentNum;

@property (weak, nonatomic) IBOutlet UILabel *Presentmoney;
@end
