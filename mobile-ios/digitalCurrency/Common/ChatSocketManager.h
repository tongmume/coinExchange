//
//  ChatSocketManager.h
//  digitalCurrency
//
//  Created by sunliang on 2019/4/13.
//  Copyright © 2019年 BIZZAN. All rights reserved.
//
#import <Foundation/Foundation.h>
#import "GCDAsyncSocket.h"
@protocol chatSocketDelegate <NSObject>
@optional
- (void)ChatdelegateSocket:(GCDAsyncSocket *)sock didReadData:(NSData *)data withTag:(long)tag;

@end
@interface ChatSocketManager : NSObject
@property (nonatomic, assign) id<chatSocketDelegate>delegate;
+ (instancetype)share;
- (BOOL)connect;
- (void)disConnect;
- (void)ChatsendMsgWithLength:(int)length withsequenceId:(long)sequenceId withcmd:(short)cmd withVersion:(int)Version withRequestId:(int)RequestId withbody:(NSDictionary*)jsonDict;//发送消息
@end

