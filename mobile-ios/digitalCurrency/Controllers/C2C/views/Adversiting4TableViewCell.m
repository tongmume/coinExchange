//
//  Adversiting4TableViewCell.m
//  digitalCurrency
//
//  Created by iDog on 2019/3/5.
//  Copyright © 2019年 BIZZAN. All rights reserved.
//

#import "Adversiting4TableViewCell.h"

@implementation Adversiting4TableViewCell

- (void)awakeFromNib {
    [super awakeFromNib];
    self.buttonWidth.constant = (kWindowW-180 * kWindowWHOne)/2;
    self.leftLabel.font = [UIFont systemFontOfSize:16 * kWindowWHOne];
    self.labelwidth.constant = 120 * kWindowWHOne;
    // Initialization code
}
- (IBAction)buttonClick:(UIButton *)sender {
    if (sender.tag == 1){
        [self.leftButton setImage:[UIImage imageNamed:@"selectedImage"] forState:UIControlStateNormal];
         [self.rightButton setImage:[UIImage imageNamed:@"noSelectImage"] forState:UIControlStateNormal];
    }else{
        [self.leftButton setImage:[UIImage imageNamed:@"noSelectImage"] forState:UIControlStateNormal];
       [self.rightButton setImage:[UIImage imageNamed:@"selectedImage"] forState:UIControlStateNormal];
    }
    if (self.delegate && [self.delegate respondsToSelector:@selector(tableViewIndex:buttonTag:)]) {
        [self.delegate tableViewIndex:_index buttonTag:sender.tag];
    }
}

- (void)setSelected:(BOOL)selected animated:(BOOL)animated {
    [super setSelected:selected animated:animated];

    // Configure the view for the selected state
}

@end
