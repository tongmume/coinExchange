//
//  WalletSelectCurrencyTableViewCell.m
//  digitalCurrency
//
//  Created by ios on 2020/10/9.
//  Copyright © 2020 BIZZAN. All rights reserved.
//

#import "WalletSelectCurrencyTableViewCell.h"

@implementation WalletSelectCurrencyTableViewCell

-(instancetype)initWithStyle:(UITableViewCellStyle)style reuseIdentifier:(NSString *)reuseIdentifier{
    
    self=[super initWithStyle:style reuseIdentifier:reuseIdentifier];
    self.backgroundColor = mainColor;
    if (self) {
        MJWeakSelf;
        _mtitlelabel=[[UILabel alloc]init];
        _mtitlelabel.font=[UIFont boldSystemFontOfSize:17];
        _mtitlelabel.textColor=VCBackgroundColor;
        [self addSubview:_mtitlelabel];
        
        UIImageView *arrowimgview=[[UIImageView alloc]init];
        arrowimgview.image=[UIImage imageNamed:@"back2"];
        [self addSubview:arrowimgview];
        
        [_mtitlelabel mas_makeConstraints:^(MASConstraintMaker *make) {
            make.left.mas_equalTo(15);
            make.top.mas_equalTo(10);
            make.size.mas_equalTo(CGSizeMake(200,30));
        }];
        
        [arrowimgview mas_makeConstraints:^(MASConstraintMaker *make) {
            
            make.right.mas_equalTo(-15);
            make.centerY.equalTo(weakSelf.mtitlelabel.mas_centerY).offset(0);
            make.size.mas_equalTo(CGSizeMake(5,10));
        }];
        
        UIView *line=[[UIView alloc]init];
        line.backgroundColor=ViewBackgroundColor;
        [self addSubview:line];
        
        [line mas_makeConstraints:^(MASConstraintMaker *make) {
            make.left.right.bottom.mas_equalTo(0);
            make.height.mas_equalTo(1);
        }];
        
        
    }
    
    return self;
}

- (void)awakeFromNib {
    [super awakeFromNib];
    // Initialization code
}

- (void)setSelected:(BOOL)selected animated:(BOOL)animated {
    [super setSelected:selected animated:animated];

    // Configure the view for the selected state
}

@end
