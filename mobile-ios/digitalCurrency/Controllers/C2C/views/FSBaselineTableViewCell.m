//
//  FSBaselineTableViewCell.m
//  FSScrollViewNestTableViewDemo
//
//  Created by huim on 2019/5/23.
//  Copyright © 2019年 fengshun. All rights reserved.
//

#import "FSBaselineTableViewCell.h"

@implementation FSBaselineTableViewCell

- (instancetype)initWithStyle:(UITableViewCellStyle)style reuseIdentifier:(NSString *)reuseIdentifier
{
    self = [super initWithStyle:style reuseIdentifier:reuseIdentifier];
    if (self) {
        UIImageView *imageView = [[UIImageView alloc] init];
        imageView.image = [UIImage imageNamed:@"成为商家"];
        [self.contentView addSubview:imageView];

        [imageView mas_makeConstraints:^(MASConstraintMaker *make) {
            make.left.equalTo(self.contentView).with.offset(0);
            make.top.equalTo(self.contentView).with.offset(0);
            make.bottom.equalTo(self.contentView).with.offset(0);
            make.right.equalTo(self.contentView).with.offset(0);
        }];

        UITapGestureRecognizer *tap1 = [[UITapGestureRecognizer alloc]initWithTarget:self action:@selector(actionimage)];
        imageView.userInteractionEnabled = YES;
        [imageView addGestureRecognizer:tap1];
    }
    return self;
}


-(void)actionimage{
    self.BusinessBlock();
}
@end
