//
//  NoticeTableViewCell.h
//  digitalCurrency
//
//  Created by startlink on 2019/7/31.
//  Copyright © 2019年 BIZZAN. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface NoticeTableViewCell : UITableViewCell

@property (weak, nonatomic) IBOutlet UIView *transactionview;
@property (weak, nonatomic) IBOutlet UIView *helpView;
@property (weak, nonatomic) IBOutlet UILabel *transactionlabel;
@property (weak, nonatomic) IBOutlet UILabel *assestAmountLabel;
@property (weak, nonatomic) IBOutlet UILabel *safelabel;
@property (weak, nonatomic) IBOutlet UILabel *helplebel;
@property (weak, nonatomic) IBOutlet UILabel *problemlabel;

@property (nonatomic, strong) UIButton *logionButton;
@property (weak, nonatomic) IBOutlet UIButton *rechangeButton;

@property (weak, nonatomic) IBOutlet UIView *Noticeview;
@property (weak, nonatomic) IBOutlet UILabel *noticelabel;
@property (weak, nonatomic) IBOutlet UILabel *noticecontentlabel;

@property (nonatomic,copy)void (^CtoCBlock)(void);

@property (nonatomic,copy)void (^loginBlock)(void);

@property (nonatomic,copy)void (^rechangeBlock)(void);
@end
