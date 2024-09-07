//
//  ContractExchangeSocketManager.h
//  digitalCurrency
//
//  Created by ios on 2020/9/18.
//  Copyright © 2020 BIZZAN. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "GCDAsyncSocket.h"

NS_ASSUME_NONNULL_BEGIN

@protocol ContractExchangeSocketManagerDelegate;

@interface ContractExchangeSocketManager : NSObject
@property (nonatomic, weak) id<ContractExchangeSocketManagerDelegate> delegate;
+ (instancetype)share;
- (BOOL)connect;
- (void)disConnect;

- (void)sendMsgWithLength:(int)length withsequenceId:(long)sequenceId withcmd:(short)cmd withVersion:(int)Version withRequestId:(int)RequestId withbody:(NSDictionary*)jsonDict;//发送消息

@end

@protocol ContractExchangeSocketManagerDelegate <NSObject>

@optional

- (void)contractExchangeetdelegateSocket:(GCDAsyncSocket *)sock didReadData:(NSData *)data withTag:(long)tag;

@end

NS_ASSUME_NONNULL_END
