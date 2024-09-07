//
//  Adversiting2TableViewCell.m
//  digitalCurrency
//
//  Created by iDog on 2019/1/31.
//  Copyright © 2019年 BIZZAN. All rights reserved.
//

#import "Adversiting2TableViewCell.h"

@implementation Adversiting2TableViewCell

- (void)awakeFromNib {
    [super awakeFromNib];
    self.textView.delegate = self;
    // Initialization code
}
-(void)textViewDidEndEditing:(UITextView *)textView{
    if (self.delegate && [self.delegate respondsToSelector:@selector(textViewIndex:TextViewString:)]) {
        [self.delegate textViewIndex:self.index TextViewString:textView.text];
    }
}
- (void)setSelected:(BOOL)selected animated:(BOOL)animated {
    [super setSelected:selected animated:animated];

    // Configure the view for the selected state
}

@end
