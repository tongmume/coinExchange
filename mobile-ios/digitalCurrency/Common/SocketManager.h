//
//  SocketManager.h
//  digitalCurrency
//
//  Created by sunliang on 2019/4/9.
//  Copyright © 2019年 BIZZAN. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "GCDAsyncSocket.h"
@protocol SocketDelegate;
@interface SocketManager : NSObject
@property (nonatomic, weak) id<SocketDelegate> delegate;
+ (instancetype)share;
- (BOOL)connect;
- (void)disConnect;
- (void)sendMsgWithLength:(int)length withsequenceId:(long)sequenceId withcmd:(short)cmd withVersion:(int)Version withRequestId:(int)RequestId withbody:(NSDictionary*)jsonDict;//发送消息
@end
@protocol SocketDelegate <NSObject>

@optional

- (void)delegateSocket:(GCDAsyncSocket *)sock didReadData:(NSData *)data withTag:(long)tag;

@end
