//
//  AssetTransferMenuView.m
//  digitalCurrency
//
//  Created by ios on 2020/10/10.
//  Copyright © 2020 BIZZAN. All rights reserved.
//

#import "AssetTransferMenuView.h"

@interface AssetTransferMenuView()

@property (nonatomic, strong) UIImageView *rightimgview;

@end

@implementation AssetTransferMenuView

-(instancetype)init{
    
    self=[super init];
    
    if (self) {
        
        _mtitlelabel=[[UILabel alloc]init];
        _mtitlelabel.font=[UIFont systemFontOfSize:15*kWindowWHOne];
        _mtitlelabel.textColor=AppTextColor_Level_1;
        [self addSubview:_mtitlelabel];
        _mtxtlabel=[[UILabel alloc]init];
        _mtxtlabel.textColor=AppTextColor_Level_3;
        _mtxtlabel.font=[UIFont systemFontOfSize:13*kWindowWHOne];
        _mtxtlabel.adjustsFontSizeToFitWidth=YES;
        [self addSubview:_mtxtlabel];
        
        _rightimgview=[[UIImageView alloc]init];
        _rightimgview.image=[UIImage imageNamed:@"back2"];
        [self addSubview:_rightimgview];
        
    }
    return self;
}

- (void)layoutSubviews{
    
    MJWeakSelf;
    
    [_rightimgview mas_makeConstraints:^(MASConstraintMaker *make) {
        
        make.centerY.equalTo(weakSelf.mas_centerY).offset(0);
        make.right.mas_equalTo(-5);
        make.size.mas_equalTo(CGSizeMake(8,15));
    }];
    
    [_mtitlelabel mas_makeConstraints:^(MASConstraintMaker *make) {
        make.bottom.equalTo(weakSelf.rightimgview.mas_centerY).offset(0);
        make.left.mas_equalTo(0);
        make.right.equalTo(weakSelf.rightimgview.mas_left).offset(-5);
        make.height.mas_equalTo(20);
    }];
    
    [_mtxtlabel mas_makeConstraints:^(MASConstraintMaker *make) {
        make.top.equalTo(weakSelf.rightimgview.mas_centerY).offset(5);
        make.left.mas_equalTo(0);
        make.right.equalTo(weakSelf.rightimgview.mas_left).offset(-5);
        make.height.mas_equalTo(20);
    }];
}

-(void)setIsShowArrow:(BOOL)isShowArrow{
    
    _isShowArrow=isShowArrow;
    _rightimgview.hidden=!isShowArrow;
}

/*
// Only override drawRect: if you perform custom drawing.
// An empty implementation adversely affects performance during animation.
- (void)drawRect:(CGRect)rect {
    // Drawing code
}
*/

@end
