//
//  MineTableHeadView.h
//  ATC
//
//  Created by iDog on 2019/6/1.
//  Copyright © 2019年 BIZZAN. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface MineTableHeadView : UIView
@property (weak, nonatomic) IBOutlet UIButton *headButton;//头像的点击按钮
@property (weak, nonatomic) IBOutlet UIImageView *headImage;//头像
@property (weak, nonatomic) IBOutlet UILabel *userName;//昵称
@property (weak, nonatomic) IBOutlet UILabel *account; //账号
@property (weak, nonatomic) IBOutlet UIButton *safebutton;

@property (weak, nonatomic) IBOutlet UIButton *setbutton;
@property (weak, nonatomic) IBOutlet NSLayoutConstraint *imageheight;
@property (weak, nonatomic) IBOutlet NSLayoutConstraint *safetop;
-(MineTableHeadView *)instancetableHeardViewWithFrame:(CGRect)Rect;
@end
