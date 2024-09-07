//
//  ChatTableViewCell.m
//  digitalCurrency
//
//  Created by iDog on 2019/3/7.
//  Copyright © 2019年 BIZZAN. All rights reserved.
//

#import "ChatTableViewCell.h"

@implementation ChatTableViewCell

- (void)awakeFromNib {
    [super awakeFromNib];
    // Initialization code

}
-(void)refreshCell:(ChatModel *)model
{
    //移除所有子View，防止重用
    [self.subviews makeObjectsPerformSelector:@selector(removeFromSuperview)];
    self.headImageView = [[UIImageView alloc] init];
    self.backView = [[UIImageView alloc] init];
    self.contentLabel = [[UILabel alloc] init];
    // 首先计算文本宽度和高度
    CGRect rec = [model.content boundingRectWithSize:CGSizeMake(200, CGFLOAT_MAX) options:NSStringDrawingUsesLineFragmentOrigin attributes:@{NSFontAttributeName : [UIFont systemFontOfSize:17]} context:nil];
    //NSLog(@"%f",rec.size.height);
    // 气泡
    UIImage *image = nil;

    // 模拟左边
    if (![model.uidFrom isEqualToString:[YLUserInfo shareUserInfo].ID])
    {
        // 当输入只有一个行的时候高度就是20多一点
        self.headImageView.frame = CGRectMake(10, 10, 50, 50);
        self.backView.frame = CGRectMake(70, 10, rec.size.width + 30, rec.size.height + 20);
        image = [UIImage imageNamed:@"bubble"];
        [self.headImageView sd_setImageWithURL:[NSURL URLWithString:model.avatar] placeholderImage:[UIImage imageNamed:@"defaultImage"]];
    }
    else // 模拟右边
    {
        self.headImageView.frame = CGRectMake(kWindowW - 60, 10, 50, 50);
        self.backView.frame = CGRectMake(kWindowW - 70 - rec.size.width - 20, 10, rec.size.width + 30, rec.size.height + 20);
        image = [UIImage imageNamed:@"bubbleSelf"];

//        headImage = [UIImage imageNamed:@"defaultImage"];
        [self.headImageView sd_setImageWithURL:[NSURL URLWithString:[YLUserInfo shareUserInfo].avatar] placeholderImage:[UIImage imageNamed:@"defaultImage"]];
    }
    self.headImageView.clipsToBounds = YES;
    self.headImageView.layer.cornerRadius = 25;
    [self addSubview:self.headImageView];
    [self addSubview:self.backView];
    // 拉伸图片 参数1 代表从左侧到指定像素禁止拉伸，该像素之后拉伸，参数2 代表从上面到指定像素禁止拉伸，该像素以下就拉伸
    image = [image stretchableImageWithLeftCapWidth:image.size.width/2 topCapHeight:image.size.height/2];
    self.backView.image = image;
    self.backView.backgroundColor = [UIColor clearColor];
//    self.headImageView.image = headImage;
    // 文本内容的frame
    self.contentLabel.frame = CGRectMake([model.uidFrom isEqualToString:[YLUserInfo shareUserInfo].ID] ? 10 : 18, 8, rec.size.width, rec.size.height);
    self.contentLabel.numberOfLines = 0;
   // NSLog(@"---%@",model.msg);
    self.contentLabel.text = model.content;
    [self.backView addSubview:self.contentLabel];
    self.backgroundColor=mainColor;
}

- (void)setSelected:(BOOL)selected animated:(BOOL)animated {
    [super setSelected:selected animated:animated];

    // Configure the view for the selected state
}

@end
