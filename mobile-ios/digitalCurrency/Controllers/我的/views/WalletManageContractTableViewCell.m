//
//  WalletManageContractTableViewCell.m
//  digitalCurrency
//
//  Created by ios on 2020/10/9.
//  Copyright © 2020 BIZZAN. All rights reserved.
//

#import "WalletManageContractTableViewCell.h"

@implementation WalletManageContractTableViewCell


-(instancetype)initWithStyle:(UITableViewCellStyle)style reuseIdentifier:(NSString *)reuseIdentifier{
    
    self=[super initWithStyle:style reuseIdentifier:reuseIdentifier];
    self.backgroundColor = mainColor;
    if (self) {
        MJWeakSelf;
        CGFloat interval=15;
        
        UIColor *tipcolor =kRGBColor(107,107,107);
        UIColor *txtcolor=kRGBColor(153,153,153);
        UIColor *titlecolor=VCBackgroundColor;
        UIFont *mtitleFont=[UIFont boldSystemFontOfSize:17];
        UIFont *txtfont=[UIFont systemFontOfSize:13];
        
        _mtitlelabel=[[UILabel alloc]init];
        _mtitlelabel.font=mtitleFont;
        _mtitlelabel.textColor=titlecolor;
        _mtitlelabel.textAlignment=NSTextAlignmentLeft;
        [self addSubview:_mtitlelabel];
        
        [_mtitlelabel mas_makeConstraints:^(MASConstraintMaker *make) {
            make.left.mas_equalTo(interval);
            make.top.mas_equalTo(10);
            make.right.mas_equalTo(-interval);
            make.height.mas_equalTo(30);
        }];
        
        CGSize labelsize= CGSizeMake((SCREEN_WIDTH_S-2*interval)/4,20);
        UILabel *onetiplabel=[[UILabel alloc]init];
        onetiplabel.textAlignment=NSTextAlignmentLeft;
        onetiplabel.textColor=tipcolor;
        onetiplabel.font=txtfont;
        onetiplabel.text=LocalizationKey(@"Account_rights");
        [self addSubview:onetiplabel];
        UILabel *twotiplabel=[[UILabel alloc]init];
        twotiplabel.textAlignment=NSTextAlignmentCenter;
        twotiplabel.font=txtfont;
        twotiplabel.textColor=tipcolor;
        twotiplabel.text=LocalizationKey(@"may_money");
        [self addSubview:twotiplabel];
        UILabel *threetiplabel=[[UILabel alloc]init];
        threetiplabel.textAlignment=NSTextAlignmentCenter;
        threetiplabel.textColor=tipcolor;
        threetiplabel.font=txtfont;
        threetiplabel.text=LocalizationKey(@"use_money");
        [self addSubview:threetiplabel];
        UILabel *fourtiplabel=[[UILabel alloc]init];
        fourtiplabel.textAlignment=NSTextAlignmentRight;
        fourtiplabel.textColor=tipcolor;
        fourtiplabel.font=txtfont;
        fourtiplabel.text=LocalizationKey(@"freeze_money");
        [self addSubview:fourtiplabel];
        
        [onetiplabel mas_makeConstraints:^(MASConstraintMaker *make) {
            make.left.mas_equalTo(interval);
            make.top.equalTo(weakSelf.mtitlelabel.mas_bottom).offset(10);
            make.size.mas_equalTo(labelsize);
        }];
        
        [twotiplabel mas_makeConstraints:^(MASConstraintMaker *make) {
            make.left.equalTo(onetiplabel.mas_right).offset(0);
            make.top.equalTo(onetiplabel.mas_top).offset(0);
            make.size.mas_equalTo(labelsize);
        }];
        
        [threetiplabel mas_makeConstraints:^(MASConstraintMaker *make) {
            make.left.equalTo(twotiplabel.mas_right).offset(0);
            make.top.equalTo(onetiplabel.mas_top).offset(0);
            make.size.mas_equalTo(labelsize);
        }];
        [fourtiplabel mas_makeConstraints:^(MASConstraintMaker *make) {
            make.left.equalTo(threetiplabel.mas_right).offset(0);
            make.top.equalTo(onetiplabel.mas_top).offset(0);
            make.size.mas_equalTo(labelsize);
        }];
        
        _onelabel=[[UILabel alloc]init];
        _onelabel.textAlignment=NSTextAlignmentLeft;
        _onelabel.textColor=txtcolor;
        _onelabel.font=txtfont;
        _onelabel.adjustsFontSizeToFitWidth=YES;
        [self addSubview:_onelabel];
        
        _twolabel=[[UILabel alloc]init];
        _twolabel.textAlignment=NSTextAlignmentCenter;
        _twolabel.textColor=txtcolor;
        _twolabel.font=txtfont;
        _twolabel.adjustsFontSizeToFitWidth=YES;

        [self addSubview:_twolabel];
        
        _threelabel=[[UILabel alloc]init];
        _threelabel.textAlignment=NSTextAlignmentCenter;
        _threelabel.textColor=txtcolor;
        _threelabel.font=txtfont;
        _threelabel.adjustsFontSizeToFitWidth=YES;

        [self addSubview:_threelabel];
        
        _fourlabel=[[UILabel alloc]init];
        _fourlabel.textAlignment=NSTextAlignmentRight;
        _fourlabel.textColor=txtcolor;
        _fourlabel.font=txtfont;
        _fourlabel.adjustsFontSizeToFitWidth=YES;

        [self addSubview:_fourlabel];
        
        [_onelabel mas_makeConstraints:^(MASConstraintMaker *make) {
            make.left.equalTo(onetiplabel.mas_left).offset(1);
            make.top.equalTo(onetiplabel.mas_bottom).offset(5);
            make.size.mas_equalTo(labelsize);
        }];
        
        [_twolabel mas_makeConstraints:^(MASConstraintMaker *make) {
            make.left.equalTo(twotiplabel.mas_left).offset(1);
            make.top.equalTo(twotiplabel.mas_bottom).offset(5);
            make.size.mas_equalTo(labelsize);
        }];
        
        [_threelabel mas_makeConstraints:^(MASConstraintMaker *make) {
            make.left.equalTo(threetiplabel.mas_left).offset(1);
            make.top.equalTo(threetiplabel.mas_bottom).offset(5);
            make.size.mas_equalTo(labelsize);
        }];
        
        [_fourlabel mas_makeConstraints:^(MASConstraintMaker *make) {
            make.left.equalTo(fourtiplabel.mas_left).offset(1);
            make.top.equalTo(fourtiplabel.mas_bottom).offset(5);
            make.size.mas_equalTo(labelsize);
        }];
        
        UIView *line=[[UIView alloc]init];
        line.backgroundColor=mainColor;
        [self addSubview:line];
        
        [line mas_makeConstraints:^(MASConstraintMaker *make) {
            make.bottom.mas_equalTo(0);
            make.height.mas_equalTo(1);
            make.left.mas_equalTo(interval);
            make.right.mas_equalTo(-interval);
        }];
        
        _mtitlelabel.text=@"BTC/USDT  永续账户";
        _onelabel.text=@"0.0000000";
        _twolabel.text=@"0.0000000";
        _threelabel.text=@"0.0000000";
        _fourlabel.text=@"0.0000000";
        
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
