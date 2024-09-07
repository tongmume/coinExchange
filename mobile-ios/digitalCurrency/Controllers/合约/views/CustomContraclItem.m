//
//  CustomContraclItem.m
//  digitalCurrency
//
//  Created by ios on 2020/9/16.
//  Copyright © 2020 BIZZAN. All rights reserved.
//

#import "CustomContraclItem.h"

@interface CustomContraclItem ()

@end

@implementation CustomContraclItem


-(instancetype)init{
    
    self=[super init];
    
    if (self) {
        
        _leftlabel=[[UILabel alloc]init];
        _leftlabel.textColor=AppTextColor_Level_3;
        _leftlabel.font=[UIFont systemFontOfSize:10];
        _leftlabel.textAlignment=NSTextAlignmentLeft;
        [self addSubview:_leftlabel];
        
    }
    return self;
}


-(void)layoutSubviews {
    
    MJWeakSelf
        
    [self.leftlabel mas_makeConstraints:^(MASConstraintMaker *make) {
        
        make.left.mas_equalTo(5);
        make.top.bottom.mas_equalTo(0);
        make.right.mas_equalTo(10);
    }];
    
    UIImageView *imgview=[[UIImageView alloc]init];
    imgview.image=[UIImage imageNamed:@"pullBlackImage"];
    [self addSubview:imgview];
    
    [imgview mas_makeConstraints:^(MASConstraintMaker *make) {
        make.right.mas_equalTo(-2);
        make.size.mas_equalTo(CGSizeMake(8,5));
        make.centerY.equalTo(weakSelf.leftlabel.mas_centerY).offset(0);
    }];
}

/*
// Only override drawRect: if you perform custom drawing.
// An empty implementation adversely affects performance during animation.
- (void)drawRect:(CGRect)rect {
    // Drawing code
}
*/

@end
