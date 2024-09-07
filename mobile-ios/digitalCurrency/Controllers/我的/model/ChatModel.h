//
//  ChatModel.h
//  digitalCurrency
//
//  Created by iDog on 2019/3/7.
//  Copyright © 2019年 BIZZAN. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface ChatModel : NSObject
@property(nonatomic,copy)NSString *content;
@property(nonatomic,copy)NSString *messageType;
@property(nonatomic,copy)NSString *nameFrom;
@property(nonatomic,copy)NSString *nameTo;
@property(nonatomic,copy)NSString *orderId;
@property(nonatomic,copy)NSString *sendTime;
@property(nonatomic,copy)NSString *sendTimeStr;
@property(nonatomic,copy)NSString *uidFrom;
@property(nonatomic,copy)NSString *uidTo;
@property(nonatomic,copy)NSString *avatar;
@property(nonatomic,copy)NSString *flagIndex;
@end
