//
//  ImageBtn.m
//  digitalCurrency
//
//  Created by sunliang on 2019/2/7.
//  Copyright © 2019年 BIZZAN. All rights reserved.
//

#import "ImageBtn.h"

@implementation ImageBtn
@synthesize lb_title,image;
- (id)initWithFrame:(CGRect)frame :(NSString *)title :(UIImage *)Image
{
    self = [super initWithFrame:frame];
    if (self) {

        lb_title = [[UILabel alloc] initWithFrame:CGRectZero];
        lb_title.numberOfLines = 0;
        lb_title.font = [UIFont systemFontOfSize:14.f];
        lb_title.backgroundColor = [UIColor clearColor];
        lb_title.textColor=[UIColor whiteColor];
        lb_title.text = title;
        CGSize size = [lb_title.text sizeWithAttributes:@{NSFontAttributeName:[UIFont boldSystemFontOfSize:14.f]}];
        //假设lb_title与图片、按钮边缘间隔都是5,图片宽度8
        if (size.width>self.frame.size.width-8) {
            size.width =self.frame.size.width-8;
        }
        lb_title.frame = CGRectMake(0, 0, size.width, self.frame.size.height);
        [self addSubview:lb_title];
        if (lb_title.text.length<=4) {
            image = [[UIImageView alloc] initWithFrame:CGRectMake(lb_title.frame.size.width+lb_title.frame.origin.x+5, (self.frame.size.height-8*12/13)/2, 8, 8*12/13)];
        }else{
             image = [[UIImageView alloc] initWithFrame:CGRectMake(lb_title.frame.size.width+lb_title.frame.origin.x, (self.frame.size.height-8*12/13)/2, 8, 8*12/13)];
        }
        image.image = Image;
        image.backgroundColor = [UIColor clearColor];
        [self addSubview:image];
    }
    return self;
}
-(void)resetFrame{

    CGSize size = [lb_title.text sizeWithAttributes:@{NSFontAttributeName:[UIFont boldSystemFontOfSize:14.f]}];
    //假设lb_title与图片、按钮边缘间隔都是5,图片宽度8
    if (size.width>self.frame.size.width-8) {
        size.width =self.frame.size.width-8;
    }
    lb_title.frame = CGRectMake(0, 0, size.width, self.frame.size.height);
    if (lb_title.text.length<=4) {
        image.frame = CGRectMake(lb_title.frame.size.width+lb_title.frame.origin.x+5, (self.frame.size.height-8*12/13)/2, 8, 8*12/13);
    }else{
        image.frame = CGRectMake(lb_title.frame.size.width+lb_title.frame.origin.x, (self.frame.size.height-8*12/13)/2, 8, 8*12/13);
    }

}


/*
// Only override drawRect: if you perform custom drawing.
// An empty implementation adversely affects performance during animation.
- (void)drawRect:(CGRect)rect {
    // Drawing code
}
*/

@end
