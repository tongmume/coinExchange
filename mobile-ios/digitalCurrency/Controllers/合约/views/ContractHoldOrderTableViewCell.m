//
//  ContractHoldOrderTableViewCell.m
//  digitalCurrency
//
//  Created by ios on 2020/9/23.
//  Copyright © 2020 BIZZAN. All rights reserved.
//

#import "ContractHoldOrderTableViewCell.h"

@implementation ContractHoldOrderTableViewCell

-(instancetype)initWithStyle:(UITableViewCellStyle)style reuseIdentifier:(NSString *)reuseIdentifier{
    
    self=[super initWithStyle:style reuseIdentifier:reuseIdentifier];
    self.backgroundColor = mainColor;
    if (self) {
        MJWeakSelf;
        UIFont *smallFont=[UIFont systemFontOfSize:12*kWindowWHOne];
        UIFont *bigFont =[UIFont systemFontOfSize:15*kWindowWHOne];
        UIColor *tipColor=AppTextColor_Level_3;
        UIColor *titleColor=AppTextColor_Level_3;
        CGFloat leftinterval=10;
        CGFloat rightinterval=10;
    
        _shouyilabeltop=[[UILabel alloc]init];
        _shouyilabeltop.text=LocalizationKey(@"text_earnings");
        _shouyilabeltop.font=bigFont;
        _shouyilabeltop.textAlignment=NSTextAlignmentCenter;
        _shouyilabeltop.textColor=tipColor;
        [self addSubview:_shouyilabeltop];
        
        [_shouyilabeltop mas_makeConstraints:^(MASConstraintMaker *make) {
            make.left.mas_equalTo(leftinterval);
            make.top.mas_equalTo(10);
            make.size.mas_equalTo(CGSizeMake(SCREEN_WIDTH_S/2-leftinterval,20));
        }];
        
        _shouyilvlabeltop=[[UILabel alloc]init];
        _shouyilvlabeltop.text=LocalizationKey(@"Rateofreturn");
        _shouyilvlabeltop.font=bigFont;
        _shouyilvlabeltop.textAlignment=NSTextAlignmentCenter;
        _shouyilvlabeltop.textColor=tipColor;
        [self addSubview:_shouyilvlabeltop];
        
        [_shouyilvlabeltop mas_makeConstraints:^(MASConstraintMaker *make) {
            make.right.mas_equalTo(-rightinterval);
            make.top.equalTo(_shouyilabeltop.mas_top).offset(0);
            make.size.mas_equalTo(CGSizeMake(SCREEN_WIDTH_S/2-10,20));
        }];
        
        self.shouyilabel=[[UILabel alloc]init];
        self.shouyilabel.textAlignment=NSTextAlignmentCenter;
        self.shouyilabel.font=bigFont;
        [self addSubview:self.shouyilabel];
       
        
        self.shouyilvlabel=[[UILabel alloc]init];
        self.shouyilvlabel.textAlignment=NSTextAlignmentCenter;
        self.shouyilvlabel.font=bigFont;
        [self addSubview:self.shouyilvlabel];
        
        [self.shouyilabel mas_makeConstraints:^(MASConstraintMaker *make) {
            
            make.left.mas_equalTo(leftinterval);
            make.top.equalTo(_shouyilabeltop.mas_bottom).offset(0);
            make.size.mas_equalTo(CGSizeMake(SCREEN_WIDTH_S/2-10,20));
        }];
        [self.shouyilvlabel mas_makeConstraints:^(MASConstraintMaker *make) {
            make.right.mas_equalTo(-rightinterval);
            make.top.equalTo(_shouyilvlabeltop.mas_bottom).offset(0);
            make.size.mas_equalTo(CGSizeMake(SCREEN_WIDTH_S/2-10,20));
        }];
        
        UIView *line2=[[UIView alloc]init];
        line2.backgroundColor=mainColor;
        [self addSubview:line2];
        
        [line2 mas_updateConstraints:^(MASConstraintMaker *make) {
            make.left.mas_equalTo(leftinterval);
            make.right.mas_equalTo(-rightinterval);
            make.top.equalTo(weakSelf.shouyilabel.mas_bottom).offset(5);
            make.height.mas_equalTo(1);
        }];
        
        _leveragetiplabeltop  =[[UILabel alloc]init];
        _leveragetiplabeltop.textAlignment=NSTextAlignmentLeft;
        _leveragetiplabeltop.textColor=tipColor;
        _leveragetiplabeltop.font=smallFont;
        _leveragetiplabeltop.text=LocalizationKey(@"text_leverage");
        [self addSubview:_leveragetiplabeltop];
        
        _chiNumberlabeltop=[[UILabel alloc]init];
        _chiNumberlabeltop.textAlignment=NSTextAlignmentCenter;
        _chiNumberlabeltop.textColor=tipColor;
        _chiNumberlabeltop.font=smallFont;
        _chiNumberlabeltop.text=LocalizationKey(@"text_inventory");
        [self addSubview:_chiNumberlabeltop];
        
        _kepingNumberlabeltop=[[UILabel alloc]init];
        _kepingNumberlabeltop.textAlignment=NSTextAlignmentCenter;
        _kepingNumberlabeltop.textColor=tipColor;
        _kepingNumberlabeltop.font=smallFont;
        _kepingNumberlabeltop.text=LocalizationKey(@"text_Amount");
        [self addSubview:_kepingNumberlabeltop];
        
        [_leveragetiplabeltop mas_makeConstraints:^(MASConstraintMaker *make) {
            make.left.mas_equalTo(leftinterval);
            make.top.equalTo(weakSelf.shouyilabel.mas_bottom).offset(10);
            make.size.mas_equalTo(CGSizeMake(60,20));
        }];
        
        [_chiNumberlabeltop mas_makeConstraints:^(MASConstraintMaker *make) {
            make.top.equalTo(_leveragetiplabeltop.mas_top).offset(0);
            make.right.mas_equalTo(weakSelf.mas_centerX);
            make.size.mas_equalTo(CGSizeMake((SCREEN_WIDTH_S-20)/4,20));
        }];
        
        [_kepingNumberlabeltop mas_makeConstraints:^(MASConstraintMaker *make) {
            make.left.mas_equalTo(weakSelf.mas_centerX);
            make.top.equalTo(_leveragetiplabeltop.mas_top).offset(0);
            make.size.mas_equalTo(CGSizeMake((SCREEN_WIDTH_S-20)/4,20));
        }];
        
        self.kepingNumberlabel=[[UILabel alloc]init];
        self.kepingNumberlabel.textColor=titleColor;
        self.kepingNumberlabel.font=smallFont;
        self.kepingNumberlabel.textAlignment=NSTextAlignmentCenter;
        [self addSubview:self.kepingNumberlabel];
        
        self.chiNumberlabel=[[UILabel alloc]init];
        self.chiNumberlabel.textColor=titleColor;
        self.chiNumberlabel.font=smallFont;
        self.chiNumberlabel.textAlignment=NSTextAlignmentCenter;
        [self addSubview:self.chiNumberlabel];
        
        self.levergeslabel=[[UILabel alloc]init];
        self.levergeslabel.textColor=mainColor;
        self.levergeslabel.font=smallFont;
        self.levergeslabel.layer.cornerRadius=2;
        self.levergeslabel.layer.masksToBounds=YES;
        self.levergeslabel.textAlignment=NSTextAlignmentCenter;
        [self addSubview:self.levergeslabel];
        
        [self.kepingNumberlabel mas_makeConstraints:^(MASConstraintMaker *make) {
            make.top.equalTo(_kepingNumberlabeltop.mas_bottom).offset(2);
            make.left.right.height.mas_equalTo(self.kepingNumberlabeltop);
        }];
        
        [self.chiNumberlabel mas_makeConstraints:^(MASConstraintMaker *make) {
            make.top.equalTo(_chiNumberlabeltop.mas_bottom).offset(2);
            make.centerX.equalTo(_chiNumberlabeltop.mas_centerX).offset(0);
            make.size.mas_equalTo(CGSizeMake((SCREEN_WIDTH_S-20)/3,20));
        }];
        
        [self.levergeslabel mas_makeConstraints:^(MASConstraintMaker *make) {
            make.left.mas_equalTo(leftinterval);
            make.top.equalTo(_leveragetiplabeltop.mas_bottom).offset(2);
            make.height.mas_equalTo(20);
        }];
        
        _openpricelabeltop=[[UILabel alloc]init];
        _openpricelabeltop.textAlignment=NSTextAlignmentCenter;
        _openpricelabeltop.font=smallFont;
        _openpricelabeltop.textColor=tipColor;
        _openpricelabeltop.text=LocalizationKey(@"text_average_open");
        [self addSubview:_openpricelabeltop];
        
        _currtentPriceDepositlabeltop=[[UILabel alloc]init];
        _currtentPriceDepositlabeltop.textAlignment=NSTextAlignmentRight;
        _currtentPriceDepositlabeltop.font=smallFont;
        _currtentPriceDepositlabeltop.textColor=tipColor;
        _currtentPriceDepositlabeltop.text=LocalizationKey(@"text_Current_margin");
        [self addSubview:_currtentPriceDepositlabeltop];
        
        _priceDepositlabeltop=[[UILabel alloc]init];
        _priceDepositlabeltop.textAlignment=NSTextAlignmentLeft;
        _priceDepositlabeltop.textColor=tipColor;
        _priceDepositlabeltop.font=smallFont;
        _priceDepositlabeltop.text=LocalizationKey(@"text_margin_rate");
        [self addSubview:_priceDepositlabeltop];
        
        [_openpricelabeltop mas_makeConstraints:^(MASConstraintMaker *make) {
            make.centerX.mas_equalTo(self.contentView);
            make.top.equalTo(weakSelf.chiNumberlabel.mas_bottom).offset(10);
            make.size.mas_equalTo(CGSizeMake((SCREEN_WIDTH_S-20)/4,20));
        }];
        
        [_currtentPriceDepositlabeltop mas_makeConstraints:^(MASConstraintMaker *make) {
            make.right.equalTo(self.contentView).offset(-leftinterval);
            make.top.equalTo(_kepingNumberlabeltop.mas_top).offset(0);
            make.size.mas_equalTo(CGSizeMake((SCREEN_WIDTH_S-20)/4,20));
        }];
        
        [_priceDepositlabeltop mas_makeConstraints:^(MASConstraintMaker *make) {
            make.left.mas_equalTo(rightinterval);
            make.top.equalTo(_openpricelabeltop.mas_top).offset(0);
            make.size.mas_equalTo(CGSizeMake((SCREEN_WIDTH_S-20)/4,20));
        }];
        
        self.openpricelabel=[[UILabel alloc]init];
        self.openpricelabel.textColor=titleColor;
        self.openpricelabel.textAlignment=NSTextAlignmentCenter;
        self.openpricelabel.adjustsFontSizeToFitWidth=YES;
        self.openpricelabel.font=smallFont;
        [self addSubview:self.openpricelabel];
        
        self.currtentPriceDepositlabel=[[UILabel alloc]init];
        self.currtentPriceDepositlabel.textColor=titleColor;
        self.currtentPriceDepositlabel.textAlignment=NSTextAlignmentRight;
        self.currtentPriceDepositlabel.font=smallFont;
        self.currtentPriceDepositlabel.adjustsFontSizeToFitWidth=YES;
        [self addSubview:self.currtentPriceDepositlabel];
        
        self.priceDepositlabel=[[UILabel alloc]init];
        self.priceDepositlabel.textColor=titleColor;
        self.priceDepositlabel.textAlignment=NSTextAlignmentLeft;
        self.priceDepositlabel.font=smallFont;
        [self addSubview:self.priceDepositlabel];
        
        [self.openpricelabel mas_makeConstraints:^(MASConstraintMaker *make) {
            
            make.centerX.mas_equalTo(self.contentView);
            make.top.equalTo(_openpricelabeltop.mas_bottom).offset(2);
            make.size.mas_equalTo(CGSizeMake((SCREEN_WIDTH_S-20)/4,20));
        }];
        
        [self.currtentPriceDepositlabel mas_makeConstraints:^(MASConstraintMaker *make) {
            
            make.centerX.equalTo(_currtentPriceDepositlabeltop.mas_centerX).offset(0);
            make.top.equalTo(_currtentPriceDepositlabeltop.mas_bottom).offset(2);
            make.size.mas_equalTo(CGSizeMake((SCREEN_WIDTH_S-20)/4,20));
        }];
        
        [self.priceDepositlabel mas_makeConstraints:^(MASConstraintMaker *make) {
            make.left.mas_equalTo(rightinterval);
            make.top.equalTo(_priceDepositlabeltop.mas_bottom).offset(2);
            make.size.mas_equalTo(CGSizeMake((SCREEN_WIDTH_S-20)/4,20));
        }];
        
        UIView *line=[[UIView alloc]init];
        line.backgroundColor=mainColor;
        [self addSubview:line];
        
        [line mas_makeConstraints:^(MASConstraintMaker *make) {
            make.left.right.bottom.mas_equalTo(0);
            make.height.mas_equalTo(1);
        }];
        
         self.shouyilabel.text=@"+164.7140 USDT";
        self.shouyilabel.textColor=GreenColor;
        self.shouyilvlabel.text=@"+11.11%";
        self.shouyilvlabel.textColor=GreenColor;
        self.levergeslabel.text=@"多头 50x";
        self.levergeslabel.backgroundColor=GreenColor;
        CGSize maxsize = [self.levergeslabel sizeThatFits:CGSizeMake(MAXFLOAT,20)];
        
        [self.levergeslabel mas_updateConstraints:^(MASConstraintMaker *make) {
            
            make.width.mas_equalTo(maxsize.width+5);
        }];
        
        self.chiNumberlabel.text=@"731张";
        self.kepingNumberlabel.text=@"741张";
        self.openpricelabel.text=@"10470.0266USDT";
        self.currtentPriceDepositlabel.text=@"1482.00USDT";
        self.priceDepositlabel.text=@"2.22%";
        
        [self.pingButton mas_makeConstraints:^(MASConstraintMaker *make) {
            make.right.equalTo(self.contentView).offset(-10);
            make.top.equalTo(self.priceDepositlabel).offset(-15);
            make.size.mas_equalTo(CGSizeMake(76,25));
        }];
    }
    
    return self;
}
- (void)pingButtonAction {
    if (self.pingBlock) {
        self.pingBlock();
    }
}

- (UIButton *)pingButton {
    if (!_pingButton) {
        _pingButton = [[UIButton alloc] init];
        _pingButton.titleLabel.font = [UIFont fontWithName:@"Helvetica-Bold" size:14 * kWindowWHOne];
        _pingButton.backgroundColor = RGBOF(0xF0A70A);
        _pingButton.layer.cornerRadius = 2;
        [_pingButton setTitleColor:UIColor.whiteColor forState:UIControlStateNormal];
        _pingButton.layer.masksToBounds = YES;
        [_pingButton addTarget:self action:@selector(pingButtonAction) forControlEvents:UIControlEventTouchUpInside];
        [self.contentView addSubview:_pingButton];
    }
    return  _pingButton;
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
