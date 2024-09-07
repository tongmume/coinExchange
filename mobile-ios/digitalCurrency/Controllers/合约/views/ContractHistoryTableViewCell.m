//
//  ContractHistoryTableViewCell.m
//  digitalCurrency
//
//  Created by ios on 2020/9/24.
//  Copyright © 2020 BIZZAN. All rights reserved.
//

#import "ContractHistoryTableViewCell.h"

@implementation ContractHistoryTableViewCell


-(instancetype)initWithStyle:(UITableViewCellStyle)style reuseIdentifier:(NSString *)reuseIdentifier{
    self=[super initWithStyle:style reuseIdentifier:reuseIdentifier];
    self.backgroundColor = ViewBackgroundColor;
    if (self) {
        
        UIFont *smallFont=[UIFont systemFontOfSize:12*kWindowWHOne];
        UIFont *bigFont =[UIFont systemFontOfSize:15*kWindowWHOne];
        UIColor *txtColor=AppTextColor_Level_2;
        CGFloat leftinterval=10;
        CGFloat rightinterval=10;
        
        CGFloat titileWidth= (SCREEN_WIDTH_S-leftinterval-rightinterval-15)/4;
        UIImageView *arrowimgview=[[UIImageView alloc]initWithImage:[UIImage imageNamed:@"back2"]];
        
        [self addSubview:arrowimgview];
        MJWeakSelf;
        _mtitlelabel=[[UILabel alloc]init];
        _mtitlelabel.font=bigFont;
        _mtitlelabel.textAlignment=NSTextAlignmentLeft;
        [self addSubview:_mtitlelabel];
        
        _timelabel=[[UILabel alloc]init];
        _timelabel.font=smallFont;
        _timelabel.textAlignment=NSTextAlignmentLeft;
        _timelabel.textColor=AppTextColor_Level_3;
        [self addSubview:_timelabel];
        
        [_mtitlelabel mas_makeConstraints:^(MASConstraintMaker *make) {
            
            make.left.mas_equalTo(leftinterval);
            make.top.mas_equalTo(10);
            make.size.mas_equalTo(CGSizeMake(titileWidth,20));
        }];
        
        [arrowimgview mas_makeConstraints:^(MASConstraintMaker *make) {
            make.right.mas_equalTo(-rightinterval);
            make.centerY.equalTo(weakSelf.mtitlelabel.mas_centerY).offset(0);
            make.size.mas_equalTo(CGSizeMake(7,10));
        }];
        
        [_timelabel mas_makeConstraints:^(MASConstraintMaker *make) {
            make.left.equalTo(weakSelf.mtitlelabel.mas_right).offset(5);
            make.right.equalTo(arrowimgview.mas_left).offset(-10);
            make.height.mas_equalTo(15);
            make.bottom.mas_equalTo(weakSelf.mtitlelabel);
        }];
        
        UILabel *entrustnumbertiplabel=[[UILabel alloc]init];
        entrustnumbertiplabel.textAlignment=NSTextAlignmentLeft;
        entrustnumbertiplabel.textColor=txtColor;
        entrustnumbertiplabel.font=smallFont;
        entrustnumbertiplabel.text=LocalizationKey(@"commissionamount");
        [self addSubview:entrustnumbertiplabel];
        
        UILabel *entrustPricetiplabel=[[UILabel alloc]init];
        entrustPricetiplabel.textAlignment=NSTextAlignmentCenter;
        entrustPricetiplabel.textColor=txtColor;
        entrustPricetiplabel.font=smallFont;
        entrustPricetiplabel.text=LocalizationKey(@"entrustPrice");
        [self addSubview:entrustPricetiplabel];
        
        UILabel *dealtiplabel=[[UILabel alloc]init];
        dealtiplabel.textAlignment=NSTextAlignmentCenter;
        dealtiplabel.textColor=txtColor;
        dealtiplabel.font=smallFont;
        dealtiplabel.text=LocalizationKey(@"dealPrice");
        [self addSubview:dealtiplabel];
        
        UILabel *profittiplabel=[[UILabel alloc]init];
        profittiplabel.textAlignment=NSTextAlignmentRight;
        profittiplabel.textColor=txtColor;
        profittiplabel.font=smallFont;
        profittiplabel.text=LocalizationKey(@"text_compute");
        [self addSubview:profittiplabel];
        
        [entrustnumbertiplabel mas_makeConstraints:^(MASConstraintMaker *make) {
            make.left.mas_equalTo(leftinterval);
            make.top.equalTo(weakSelf.mtitlelabel.mas_bottom).offset(10);
            make.size.mas_equalTo(CGSizeMake(titileWidth,20));
        }];
        
        [entrustPricetiplabel mas_makeConstraints:^(MASConstraintMaker *make) {
            make.left.equalTo(entrustnumbertiplabel.mas_right).offset(5);
            make.top.equalTo(entrustnumbertiplabel.mas_top).offset(0);
            make.size.mas_equalTo(CGSizeMake(titileWidth,20));
        }];
        
        [dealtiplabel mas_makeConstraints:^(MASConstraintMaker *make) {
            make.left.equalTo(entrustPricetiplabel.mas_right).offset(5);
            make.top.equalTo(entrustnumbertiplabel.mas_top).offset(0);
            make.size.mas_equalTo(CGSizeMake(titileWidth,20));
        }];
        
        [profittiplabel mas_makeConstraints:^(MASConstraintMaker *make) {
            make.right.mas_equalTo(-rightinterval);
            make.top.equalTo(entrustnumbertiplabel.mas_top).offset(0);
            make.size.mas_equalTo(CGSizeMake(titileWidth,20));

        }];
        
        _entrustNumberlabel=[[UILabel alloc]init];
        _entrustNumberlabel.textAlignment=NSTextAlignmentLeft;
        _entrustNumberlabel.font=smallFont;
        _entrustNumberlabel.textColor=txtColor;
        _entrustNumberlabel.adjustsFontSizeToFitWidth=YES;
        [self addSubview:_entrustNumberlabel];
        
        _entrustPricelabel=[[UILabel alloc]init];
        _entrustPricelabel.textAlignment=NSTextAlignmentCenter;
        _entrustPricelabel.textColor=txtColor;
        _entrustPricelabel.font=smallFont;
        _entrustPricelabel.adjustsFontSizeToFitWidth=YES;
        [self addSubview:_entrustPricelabel];
        
        _dealPricelabel=[[UILabel alloc]init];
        _dealPricelabel.textAlignment=NSTextAlignmentCenter;
        _dealPricelabel.textColor=txtColor;
        _dealPricelabel.font=smallFont;
        _dealPricelabel.adjustsFontSizeToFitWidth=YES;
        [self addSubview:_dealPricelabel];
        
        _profitlabel=[[UILabel alloc]init];
        _profitlabel.textAlignment=NSTextAlignmentRight;
        _profitlabel.textColor=txtColor;
        _profitlabel.font=smallFont;
        _profitlabel.adjustsFontSizeToFitWidth=YES;
        [self addSubview:_profitlabel];
        
        [_entrustNumberlabel mas_makeConstraints:^(MASConstraintMaker *make) {
            
            make.left.mas_equalTo(leftinterval);
            make.top.equalTo(entrustnumbertiplabel.mas_bottom).offset(0);
            make.size.mas_equalTo(CGSizeMake(titileWidth,20));

        }];
        
        [_entrustPricelabel mas_makeConstraints:^(MASConstraintMaker *make) {
            make.left.equalTo(entrustPricetiplabel.mas_left).offset(0);
            make.top.equalTo(entrustPricetiplabel.mas_bottom).offset(0);
            make.size.mas_equalTo(CGSizeMake(titileWidth,20));
        }];
        
        [_dealPricelabel mas_makeConstraints:^(MASConstraintMaker *make) {
             make.left.equalTo(dealtiplabel.mas_left).offset(0);
                       make.top.equalTo(dealtiplabel.mas_bottom).offset(0);
                       make.size.mas_equalTo(CGSizeMake(titileWidth,20));
        }];
        
        [_profitlabel mas_makeConstraints:^(MASConstraintMaker *make) {
            make.left.equalTo(profittiplabel.mas_left).offset(0);
            make.top.equalTo(profittiplabel.mas_bottom).offset(0);
            make.size.mas_equalTo(CGSizeMake(titileWidth,20));
        }];
        UIView *line=[[UIView alloc]init];
                      line.backgroundColor=mainColor;
                      [self addSubview:line];
                      
                      [line mas_makeConstraints:^(MASConstraintMaker *make) {
                          make.left.right.bottom.mas_equalTo(0);
                          make.height.mas_equalTo(1);
                      }];
        
        self.mtitlelabel.text=LocalizationKey(@"buyOpenmore");
        self.mtitlelabel.textColor=GreenColor;
        CGSize maxsize = [self.mtitlelabel sizeThatFits:CGSizeMake(MAXFLOAT,20)];
                      
        [self.mtitlelabel mas_updateConstraints:^(MASConstraintMaker *make) {
            make.width.mas_equalTo(maxsize.width+5);
        }];
        
        self.timelabel.text=@"09-22 21:56";
        self.entrustNumberlabel.text=@"242张";
        self.entrustPricelabel.text=@"10471.01";
        self.dealPricelabel.text=@"10459.97";
        self.profitlabel.text=@"--";
        
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
