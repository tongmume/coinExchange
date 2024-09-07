//
//  GestureTableViewCell.m
//  digitalCurrency
//
//  Created by chu on 2019/8/9.
//  Copyright © 2019年 BIZZAN. All rights reserved.
//

#import "GestureTableViewCell.h"
#import "ZLGestureLockViewController.h"

@implementation GestureTableViewCell

- (void)awakeFromNib {
    [super awakeFromNib];
    // Initialization code
    self.leftLabel.text = LocalizationKey(@"gesturesPassword");

}

- (IBAction)changeAction:(UISwitch *)sender {
    if ([ZLGestureLockViewController gesturesPassword].length > 0) {
        self.gestureSwitch.on=YES;
        //校验手势密码并关闭
        ZLGestureLockViewController *vc = [[ZLGestureLockViewController alloc] initWithUnlockType:ZLUnlockTypeDeletePsw];
        [[AppDelegate sharedAppDelegate] presentViewController:vc animated:YES completion:nil];
    }else{
        self.gestureSwitch.on=NO;
        //创建手势密码
        ZLGestureLockViewController *vc = [[ZLGestureLockViewController alloc] initWithUnlockType:ZLUnlockTypeCreatePsw];
        [[AppDelegate sharedAppDelegate] presentViewController:vc animated:YES completion:nil];
    }
}

- (void)setSelected:(BOOL)selected animated:(BOOL)animated {
    [super setSelected:selected animated:animated];

    // Configure the view for the selected state
}

@end
