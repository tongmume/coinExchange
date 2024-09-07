//
//  Advertising1TableViewCell.h
//  digitalCurrency
//
//  Created by iDog on 2019/1/31.
//  Copyright © 2019年 BIZZAN. All rights reserved.
//

#import <UIKit/UIKit.h>

@class Advertising1TableViewCell;

@protocol Advertising1TableViewCellDelegate <NSObject>
- (void)textFieldIndex:(NSIndexPath *)index TextFieldString: (NSString *)textString;
@end
@interface Advertising1TableViewCell : UITableViewCell
@property (weak, nonatomic) IBOutlet UILabel *leftLabel;
@property (weak, nonatomic) IBOutlet UITextField *centerTextFileld;
@property (weak, nonatomic) IBOutlet UILabel *rightLabel;
@property (nonatomic,weak) id<Advertising1TableViewCellDelegate> delegate;
@property(nonatomic,strong)NSIndexPath *index;
@property (weak, nonatomic) IBOutlet NSLayoutConstraint *rightwidth;
@end
