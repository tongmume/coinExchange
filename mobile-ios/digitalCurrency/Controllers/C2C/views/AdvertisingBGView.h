//
//  AdvertisingBGView.h
//  digitalCurrency
//
//  Created by iDog on 2019/1/31.
//  Copyright © 2019年 BIZZAN. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface AdvertisingBGView : UIView
//  tag值  购买1 出售2 取消3
@property (weak, nonatomic) IBOutlet NSLayoutConstraint *buyButtonLeftWidth;
@property (strong, nonatomic) IBOutlet UIButton *buyButton;
@property (strong, nonatomic) IBOutlet UIButton *sellButton;
@property (weak, nonatomic) IBOutlet UIButton *cancelButton;
@property (weak, nonatomic) IBOutlet NSLayoutConstraint *bottomViewHeight;
@property(strong,nonatomic)NSArray *ary;
@property (nonatomic, strong) NSMutableArray *itemButtons;
@property (nonatomic, strong) NSTimer *timer;
@property(assign,nonatomic)int upIndex;
@property(assign,nonatomic)int downIndex;
@property(strong,nonatomic)UIImageView *closeImgView;
- (void)setMenu;
-(void)dismissMenu;



@end
