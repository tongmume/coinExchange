//
//  ContractCurrtentEntrustTableViewCell.m
//  digitalCurrency
//
//  Created by ios on 2020/9/23.
//  Copyright © 2020 BIZZAN. All rights reserved.
//

#import "ContractCurrtentEntrustTableViewCell.h"

@implementation ContractCurrtentEntrustTableViewCell

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
        
        self.mtitilelabel=[[UILabel alloc]init];
        self.mtitilelabel.textAlignment=NSTextAlignmentLeft;
        self.mtitilelabel.font=bigFont;
        [self addSubview:self.mtitilelabel];
        
        self.timelabel=[[UILabel alloc]init];
        self.timelabel.textAlignment=NSTextAlignmentLeft;
        self.timelabel.font=smallFont;
        self.timelabel.textColor=tipColor;
        [self addSubview:self.timelabel];
        
        self.revokebtn=[UIButton buttonWithType:UIButtonTypeCustom];
        self.revokebtn.titleLabel.font=[UIFont systemFontOfSize:14*kWindowWHOne];
        [self.revokebtn setTitle:LocalizationKey(@"undo") forState:UIControlStateNormal];
        self.revokebtn.layer.cornerRadius=3;
        self.revokebtn.layer.masksToBounds=YES;
        self.revokebtn.backgroundColor=baseColor;
        [self.revokebtn setTitleColor:mainColor forState:UIControlStateNormal];
        [self addSubview:self.revokebtn];
        
     
        [self.mtitilelabel mas_makeConstraints:^(MASConstraintMaker *make) {
                   make.left.mas_equalTo(leftinterval);
                   make.bottom.equalTo(weakSelf.timelabel.mas_bottom).offset(0);
                   make.height.mas_equalTo(20);
        }];
        
        [self.timelabel mas_makeConstraints:^(MASConstraintMaker *make) {
            make.left.equalTo(weakSelf.mtitilelabel.mas_right).offset(5);
            make.bottom.equalTo(weakSelf.revokebtn.mas_bottom).offset(0);
            make.right.equalTo(weakSelf.revokebtn.mas_left).offset(-5);
            make.height.mas_equalTo(15);
        }];
        
       [self.revokebtn mas_makeConstraints:^(MASConstraintMaker *make) {
                make.right.mas_equalTo(-rightinterval);
                make.top.mas_equalTo(10);
                make.size.mas_equalTo(CGSizeMake(50,20));
            }];
        
        _entrustTypetiplabel=[[UILabel alloc]init];
        _entrustTypetiplabel.textAlignment=NSTextAlignmentLeft;
        _entrustTypetiplabel.textColor=tipColor;
        _entrustTypetiplabel.font=smallFont;
        _entrustTypetiplabel.text=LocalizationKey(@"text_entrust_type");
        [self addSubview:_entrustTypetiplabel];
        
        _triggertiplabel=[[UILabel alloc]init];
        _triggertiplabel.textAlignment=NSTextAlignmentCenter;
        _triggertiplabel.font=smallFont;
        _triggertiplabel.textColor=tipColor;
        _triggertiplabel.text=LocalizationKey(@"text_trigger_price_constract");
        [self addSubview:_triggertiplabel];
        
        _entrusttiplabel = [[UILabel alloc]init];
        _entrusttiplabel.textAlignment=NSTextAlignmentRight;
        _entrusttiplabel.textColor=tipColor;
        _entrusttiplabel.text=LocalizationKey(@"entrustPrice");
        _entrusttiplabel.font=smallFont;
        [self addSubview:_entrusttiplabel];
        
        [_entrusttiplabel mas_makeConstraints:^(MASConstraintMaker *make) {
            make.right.mas_equalTo(-rightinterval);
            make.top.equalTo(weakSelf.mtitilelabel.mas_bottom).offset(15);
            make.size.mas_equalTo(CGSizeMake((SCREEN_WIDTH_S-20)/3,20));
        }];
        
        [_triggertiplabel mas_makeConstraints:^(MASConstraintMaker *make) {
            make.centerX.mas_equalTo(weakSelf);
            make.top.equalTo(_entrusttiplabel.mas_top).offset(0);
            make.size.mas_equalTo(CGSizeMake((SCREEN_WIDTH_S-20)/3,20));
        }];
        
        [_entrustTypetiplabel mas_makeConstraints:^(MASConstraintMaker *make) {
            make.left.mas_equalTo(leftinterval);
            make.top.equalTo(_entrusttiplabel.mas_top).offset(0);
            make.size.mas_equalTo(CGSizeMake((SCREEN_WIDTH_S-20)/3,20));
        }];
        
        self.entrustTypeStrlabel=[[UILabel alloc]init];
        self.entrustTypeStrlabel.textAlignment=NSTextAlignmentLeft;
        self.entrustTypeStrlabel.textColor=titleColor;
        self.entrustTypeStrlabel.font=smallFont;
        [self addSubview:self.entrustTypeStrlabel];
        
        self.triggerPricelabel=[[UILabel alloc]init];
        self.triggerPricelabel.textAlignment=NSTextAlignmentCenter;
        self.triggerPricelabel.textColor=titleColor;
        self.triggerPricelabel.font=smallFont;
        [self addSubview:self.triggerPricelabel];
        
        self.entrustPricelabel=[[UILabel alloc]init];
        self.entrustPricelabel.textAlignment=NSTextAlignmentRight;
        self.entrustPricelabel.textColor=titleColor;
        self.entrustPricelabel.font=smallFont;
        [self addSubview:self.entrustPricelabel];
        
        [self.entrustTypeStrlabel mas_makeConstraints:^(MASConstraintMaker *make) {
            
            make.left.mas_equalTo(leftinterval);
            make.top.equalTo(_entrustTypetiplabel.mas_bottom).offset(5);
            make.size.mas_equalTo(CGSizeMake((SCREEN_WIDTH_S-20)/3,20));
        }];
        [self.triggerPricelabel mas_makeConstraints:^(MASConstraintMaker *make) {
            
            make.centerX.equalTo(_triggertiplabel.mas_centerX).offset(0);
            make.top.equalTo(_triggertiplabel.mas_bottom).offset(5);
            make.size.mas_equalTo(CGSizeMake((SCREEN_WIDTH_S-20)/3,20));
        }];
        
        [self.entrustPricelabel mas_makeConstraints:^(MASConstraintMaker *make) {
            
            make.right.mas_equalTo(-rightinterval);
            make.top.equalTo(weakSelf.triggerPricelabel.mas_top).offset(0);
            make.size.mas_equalTo(CGSizeMake((SCREEN_WIDTH_S-20)/3,20));

        }];
        
        _dealtiplabel=[[UILabel alloc]init];
        _dealtiplabel.textColor=tipColor;
        _dealtiplabel.textAlignment=NSTextAlignmentLeft;
        _dealtiplabel.font=smallFont;
        _dealtiplabel.text=LocalizationKey(@"dealPrice");
        [self addSubview:_dealtiplabel];
        
        _deposittiplabel=[[UILabel alloc]init];
        _deposittiplabel.textColor=tipColor;
        _deposittiplabel.textAlignment=NSTextAlignmentCenter;
        _deposittiplabel.font=smallFont;
        _deposittiplabel.text=LocalizationKey(@"text_guarantee_money");

        [self addSubview:_deposittiplabel];
        
        _entrustnumbertiplabel=[[UILabel alloc]init];
        _entrustnumbertiplabel.textColor=tipColor;
        _entrustnumbertiplabel.textAlignment=NSTextAlignmentRight;
        _entrustnumbertiplabel.font=smallFont;
        _entrustnumbertiplabel.text=LocalizationKey(@"commissionamount");

        [self addSubview:_entrustnumbertiplabel];
        
        [_dealtiplabel mas_makeConstraints:^(MASConstraintMaker *make) {
            make.left.mas_equalTo(leftinterval);
            make.top.equalTo(weakSelf.entrustTypeStrlabel.mas_bottom).offset(10);
            make.size.mas_equalTo(CGSizeMake((SCREEN_WIDTH_S-20)/3,20));
        }];
        
        [_deposittiplabel mas_makeConstraints:^(MASConstraintMaker *make) {
            make.centerX.equalTo(_triggertiplabel.mas_centerX).offset(0);
            make.top.equalTo(_dealtiplabel.mas_top).offset(0);
            make.size.mas_equalTo(CGSizeMake((SCREEN_WIDTH_S-20)/3,20));
        }];
        
        [_entrustnumbertiplabel mas_makeConstraints:^(MASConstraintMaker *make) {
            make.right.mas_equalTo(-rightinterval);
            make.top.equalTo(_dealtiplabel.mas_top).offset(0);
             make.size.mas_equalTo(CGSizeMake((SCREEN_WIDTH_S-20)/3,20));
        }];
        
        self.dealPricelabel=[[UILabel alloc]init];
        self.dealPricelabel.textAlignment=NSTextAlignmentLeft;
        self.dealPricelabel.font=smallFont;
        self.dealPricelabel.textColor=titleColor;
        self.dealPricelabel.adjustsFontSizeToFitWidth=YES;
        [self addSubview:self.dealPricelabel];
        
        self.depositPricelabel=[[UILabel alloc]init];
        self.depositPricelabel.textAlignment=NSTextAlignmentCenter;
        self.depositPricelabel.font=smallFont;
        self.depositPricelabel.textColor=titleColor;
        self.depositPricelabel.adjustsFontSizeToFitWidth=YES;
        [self addSubview:self.depositPricelabel];
        
        self.entrustNumberlabel=[[UILabel alloc]init];
        self.entrustNumberlabel.textAlignment=NSTextAlignmentRight;
        self.entrustNumberlabel.font=smallFont;
        self.entrustNumberlabel.textColor=titleColor;
        [self addSubview:self.entrustNumberlabel];
        
        [self.dealPricelabel mas_makeConstraints:^(MASConstraintMaker *make) {
            make.left.mas_equalTo(leftinterval);
            make.top.equalTo(_dealtiplabel.mas_bottom).offset(5);
            make.size.mas_equalTo(CGSizeMake((SCREEN_WIDTH_S-20)/3,20));
        }];
        
        [self.depositPricelabel mas_makeConstraints:^(MASConstraintMaker *make) {
            make.size.mas_equalTo(CGSizeMake((SCREEN_WIDTH_S-20)/3,20));
            make.centerX.equalTo(_triggertiplabel.mas_centerX).offset(0);
            make.top.equalTo(weakSelf.dealPricelabel.mas_top).offset(0);
        }];
        
        [self.entrustNumberlabel mas_makeConstraints:^(MASConstraintMaker *make) {
            make.size.mas_equalTo(CGSizeMake((SCREEN_WIDTH_S-20)/3,20));
            make.top.equalTo(weakSelf.dealPricelabel.mas_top).offset(0);
            make.right.mas_equalTo(-rightinterval);
        }];
    
        UIView *line=[[UIView alloc]init];
               line.backgroundColor=mainColor;
               [self addSubview:line];
               
               [line mas_makeConstraints:^(MASConstraintMaker *make) {
                   make.left.right.bottom.mas_equalTo(0);
                   make.height.mas_equalTo(1);
               }];
        
        self.mtitilelabel.text=LocalizationKey(@"buyOpenmore");
        CGSize maxsize = [self.mtitilelabel sizeThatFits:CGSizeMake(MAXFLOAT,20)];
               
               [self.mtitilelabel mas_updateConstraints:^(MASConstraintMaker *make) {
                   
                   make.width.mas_equalTo(maxsize.width+5);
               }];
        self.mtitilelabel.textColor=GreenColor;
        self.timelabel.text=@"09-23 11:28";
        self.entrustTypeStrlabel.text=@"限价委托";
        self.triggerPricelabel.text=@"--";
        self.entrustPricelabel.text=@"10489.49";
        self.dealPricelabel.text=@"0.00";
        self.depositPricelabel.text=@"0.00USDT";
        self.entrustNumberlabel.text=@"1张";
        
        
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
