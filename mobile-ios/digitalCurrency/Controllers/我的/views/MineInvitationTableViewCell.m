//
//  MineInvitationTableViewCell.m
//  digitalCurrency
//
//  Created by ios on 2020/9/25.
//  Copyright © 2020 BIZZAN. All rights reserved.
//

#import "MineInvitationTableViewCell.h"

@implementation MineInvitationTableViewCell

-(instancetype)initWithStyle:(UITableViewCellStyle)style reuseIdentifier:(NSString *)reuseIdentifier{
    
    self=[super initWithStyle:style reuseIdentifier:reuseIdentifier];
    if (self) {
        self.backgroundColor = mainColor;
        CGSize size= CGSizeMake(60,20);
        CGFloat interval= (SCREEN_WIDTH_S-20-4*size.width)/5;
        UIFont *txtfont=[UIFont systemFontOfSize:14*kWindowWHOne];
        UIColor *txtcolor=AppTextColor_Level_3;
        UIColor *tiptxtcolor=AppTextColor_Level_3;
        MJWeakSelf;
        
        UIView *line=[[UIView alloc]init];
        line.backgroundColor=baseColor;
        [self.contentView addSubview:line];
        
        [line mas_makeConstraints:^(MASConstraintMaker *make) {
            
            make.left.mas_equalTo(10);
            make.top.mas_equalTo(10);
            make.size.mas_equalTo(CGSizeMake(3,15));
        }];
        
        self.mtitlelabel=[[UILabel alloc]init];
        self.mtitlelabel.textAlignment=NSTextAlignmentLeft;
        self.mtitlelabel.textColor=AppTextColor_999999;
        self.mtitlelabel.font=txtfont;
        [self.contentView addSubview:self.mtitlelabel];
        
        [self.mtitlelabel mas_makeConstraints:^(MASConstraintMaker *make) {
            make.left.equalTo(line.mas_right).offset(5);
            make.centerY.equalTo(line.mas_centerY).offset(0);
            make.size.mas_equalTo(CGSizeMake(100,20));
        }];
        
        
        self.myInvitationBtn=[UIButton buttonWithType:UIButtonTypeCustom];
        [self.myInvitationBtn setTitle:LocalizationKey(@"我的邀请链接") forState:UIControlStateNormal];
        [self.myInvitationBtn setTitleColor:baseColor forState:UIControlStateNormal];
        self.myInvitationBtn.titleLabel.font=txtfont;
        
        [self.contentView addSubview:self.myInvitationBtn];
        
        CGSize mysize=[self.myInvitationBtn.titleLabel sizeThatFits:CGSizeMake(MAXFLOAT,20)];
        
        [self.myInvitationBtn mas_makeConstraints:^(MASConstraintMaker *make) {
            make.right.mas_equalTo(-10);
            make.centerY.equalTo(line.mas_centerY).offset(0);
            make.size.mas_equalTo(CGSizeMake(mysize.width+40,20));
        }];
        
        self.titlelabel1=[[UILabel alloc]init];
        self.titlelabel1.textAlignment=NSTextAlignmentCenter;
        self.titlelabel1.textColor=txtcolor;
        self.titlelabel1.font=txtfont;
        [self addSubview:self.titlelabel1];
        [self.titlelabel1 mas_makeConstraints:^(MASConstraintMaker *make) {
            make.left.mas_equalTo(interval);
            make.top.equalTo(weakSelf.mtitlelabel.mas_bottom).offset(30);
            make.size.mas_equalTo(size);
        }];
        self.tiplabel1=[[UILabel alloc]init];
        self.tiplabel1.textAlignment=NSTextAlignmentCenter;
        self.tiplabel1.textColor=tiptxtcolor;
        self.tiplabel1.text=LocalizationKey(@"一级好友");
        self.tiplabel1.font=txtfont;
        [self.contentView addSubview:self.tiplabel1];
        [self.tiplabel1 mas_makeConstraints:^(MASConstraintMaker *make) {
            
            make.left.equalTo(weakSelf.titlelabel1.mas_left).offset(0);
            make.top.equalTo(weakSelf.titlelabel1.mas_bottom).offset(15);
            make.size.mas_equalTo(CGSizeMake(60,20));
        }];
        
        self.titlelabel2=[[UILabel alloc]init];
        self.titlelabel2.textAlignment=NSTextAlignmentCenter;
        self.titlelabel2.textColor=txtcolor;
        
        self.titlelabel2.font=txtfont;
        [self addSubview:self.titlelabel2];
        [self.titlelabel2 mas_makeConstraints:^(MASConstraintMaker *make) {
            make.left.equalTo(weakSelf.titlelabel1.mas_right).offset(interval);
            make.top.equalTo(weakSelf.titlelabel1.mas_top).offset(0);
            make.size.mas_equalTo(size);
        }];
        self.tiplabel2=[[UILabel alloc]init];
        self.tiplabel2.textAlignment=NSTextAlignmentCenter;
        self.tiplabel2.textColor=tiptxtcolor;
        self.tiplabel2.font=txtfont;
        [self.contentView addSubview:self.tiplabel2];
        [self.tiplabel2 mas_makeConstraints:^(MASConstraintMaker *make) {
            make.top.equalTo(weakSelf.titlelabel2.mas_bottom).offset(15);
            make.left.equalTo(weakSelf.titlelabel2.mas_left).offset(0);
            make.size.mas_equalTo(CGSizeMake(60,20));
        }];
        
        self.titlelabel3 =[[UILabel alloc]init];
        self.titlelabel3.textAlignment=NSTextAlignmentCenter;
        self.titlelabel3.textColor=txtcolor;
        self.titlelabel3.font=txtfont;
        [self addSubview:self.titlelabel3];
        
        [self.titlelabel3 mas_makeConstraints:^(MASConstraintMaker *make) {
            make.top.equalTo(weakSelf.titlelabel2.mas_top).offset(0);
            make.left.equalTo(weakSelf.titlelabel2.mas_right).offset(interval);
            make.size.mas_equalTo(size);
        }];
        self.tiplabel3=[[UILabel alloc]init];
        self.tiplabel3.font=txtfont;
        self.tiplabel3.textColor=tiptxtcolor;
        self.tiplabel3.textAlignment=NSTextAlignmentCenter;
        [self.contentView addSubview:self.tiplabel3];
        [self.tiplabel3 mas_makeConstraints:^(MASConstraintMaker *make) {
            make.left.equalTo(weakSelf.titlelabel3.mas_left).offset(0);
            make.top.equalTo(weakSelf.titlelabel3.mas_bottom).offset(15);
            make.size.mas_equalTo(CGSizeMake(60,20));
        }];
        
        self.titlelbal4=[[UILabel alloc]init];
        self.titlelbal4.textAlignment=NSTextAlignmentCenter;
        self.titlelbal4.textColor=txtcolor;
        self.titlelbal4.font=txtfont;
        [self.contentView addSubview:self.titlelbal4];
        
        [self.titlelbal4 mas_makeConstraints:^(MASConstraintMaker *make) {
            
            make.top.equalTo(weakSelf.titlelabel3.mas_top).offset(0);
            make.left.equalTo(weakSelf.titlelabel3.mas_right).offset(interval);
            make.size.mas_equalTo(size);
        }];
        self.tiplabel4=[[UILabel alloc]init];
        self.tiplabel4.textAlignment=NSTextAlignmentCenter;
        self.tiplabel4.textColor=tiptxtcolor;
        self.tiplabel4.font=txtfont;
        [self.contentView addSubview:self.tiplabel4];
        [self.tiplabel4 mas_makeConstraints:^(MASConstraintMaker *make) {
            make.left.equalTo(weakSelf.titlelbal4.mas_left).offset(0);
            make.top.equalTo(weakSelf.titlelbal4.mas_bottom).offset(15);
            make.size.mas_equalTo(CGSizeMake(60,20));
        }];
        
        self.mtitlelabel.text=LocalizationKey(@"我的邀请");
        self.titlelabel1.text=@"0";
        self.titlelabel2.text=@"0";
        self.titlelabel3.text=@"0.0";
        self.titlelbal4.text=@"LV0";
        self.tiplabel1.text=LocalizationKey(@"一级好友");
        self.tiplabel2.text=LocalizationKey(@"二级好友");
        self.tiplabel3.text=LocalizationKey(@"佣金收入");
        self.tiplabel4.text=LocalizationKey(@"合伙级别");
        
        
        
        
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

- (void)setFrame:(CGRect)frame{
    frame.origin.x += 10;
    frame.size.width -= 20;
    [super setFrame: frame];
}

@end
