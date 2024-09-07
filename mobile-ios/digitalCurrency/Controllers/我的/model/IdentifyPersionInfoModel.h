//
//  IdentifyPersionInfoModel.h
//  digitalCurrency
//
//  Created by iDog on 2019/4/13.
//  Copyright © 2019年 BIZZAN. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface IdentifyPersionInfoModel : NSObject
@property(nonatomic,copy)NSString *idCard;
@property(nonatomic,copy)NSString *identityCardImgFront;
@property(nonatomic,copy)NSString *identityCardImgInHand;
@property(nonatomic,copy)NSString *identityCardImgReverse;
@property(nonatomic,copy)NSString *realName;
@end
