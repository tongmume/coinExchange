//
//  AddMentionCoinAddressViewController.h
//  digitalCurrency
//
//  Created by iDog on 2019/3/8.
//  Copyright © 2019年 BIZZAN. All rights reserved.
//

#import <UIKit/UIKit.h>
@class AddMentionCoinAddressViewController;

@protocol AddMentionCoinAddressViewControllerDelegate <NSObject>
- (void)AddAdressString: (NSString *)addAdressString;
@end

@interface AddMentionCoinAddressViewController : BaseViewController
@property(nonatomic,copy)NSArray *addressInfoArr; //地址数组
@property (nonatomic,weak) id<AddMentionCoinAddressViewControllerDelegate> delegate;
@end
