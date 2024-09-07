//
//  HelpCenterTableViewCell.m
//  digitalCurrency
//
//  Created by chu on 2019/8/6.
//  Copyright © 2019年 BIZZAN. All rights reserved.
//

#import "HelpCenterTableViewCell.h"

@implementation HelpCenterTableViewCell

- (void)awakeFromNib {
    [super awakeFromNib];
    // Initialization code
    self.backgroundColor = [UIColor clearColor];
    self.contentView.backgroundColor = [UIColor clearColor];
    self.backView.layer.cornerRadius=6;
    self.backView.layer.shadowColor=[UIColor blackColor].CGColor;
    self.backView.layer.shadowOffset=CGSizeMake(0, 0);
    self.backView.layer.shadowOpacity=0.1;
    self.backView.layer.shadowRadius=2;
}

- (void)setModel:(HelpCenterContentModel *)model{
    _model = model;
    // 创建一个富文本
    NSMutableAttributedString *attri = [[NSMutableAttributedString alloc] initWithString:_model.title];
    //     修改富文本中的不同文字的样式
    [attri addAttribute:NSForegroundColorAttributeName value:RGBOF(0xe6e6e6) range:NSMakeRange(0, _model.title.length)];
    [attri addAttribute:NSFontAttributeName value:[UIFont systemFontOfSize:15] range:NSMakeRange(0, _model.title.length)];
    if ([_model.isTop isEqualToString:@"0"]) {
        // 添加表情
        NSTextAttachment *attch = [[NSTextAttachment alloc] init];
        // 表情图片
        attch.image = [UIImage imageNamed:@"顶"];
        // 设置图片大小
        attch.bounds = CGRectMake(0, -2, 12, 14);
        // 创建带有图片的富文本
        NSAttributedString *string = [NSAttributedString attributedStringWithAttachment:attch];
        [attri insertAttributedString:string atIndex:_model.title.length];
    }

    self.topLabel.attributedText = attri;
    self.bottomLabel.text = _model.createTime;

}


- (void)setSelected:(BOOL)selected animated:(BOOL)animated {
    [super setSelected:selected animated:animated];

    // Configure the view for the selected state
}

@end
