//
//  ContractExchangeSocketManager.m
//  digitalCurrency
//
//  Created by ios on 2020/9/18.
//  Copyright © 2020 BIZZAN. All rights reserved.
//

#import "ContractExchangeSocketManager.h"
#import "SocketUtils.h"


static  NSString * s_host = @"apis.bizzan.vip";

static const uint16_t s_port =38901;
#define kMaxReconnection_time 5 //异常中断时，重连次数

@interface ContractExchangeSocketManager()<GCDAsyncSocketDelegate>
{
    GCDAsyncSocket *gcdSocket;
    int _reconnection_time;//重连次数
    NSTimer*_timer;
    BOOL _isSendconnection; //判断是否要重连
}
@property (nonatomic, retain) NSTimer        *connectTimer; // 心跳包计时器
@property (nonatomic, strong) NSMutableData        *readBuf; // 缓存区存储数据
@property (nonatomic, strong) NSMutableArray<NSMutableData*> *sendDatas; //存储断开链接需要发送的数据

@end

@implementation ContractExchangeSocketManager 

-(NSMutableArray *)sendDatas{
    if (!_sendDatas) {
        _sendDatas=[NSMutableArray array];
    }
    return nil;
}

+ (instancetype)share
{
    static dispatch_once_t onceToken;
    static ContractExchangeSocketManager *instance = nil;
    dispatch_once(&onceToken, ^{
        instance = [[self alloc]init];
        [instance initSocket];
    });
    return instance;
}
- (void)initSocket
{
    gcdSocket = [[GCDAsyncSocket alloc] initWithDelegate:self delegateQueue:dispatch_get_main_queue()];

}
//建立连接
- (BOOL)connect
{
    return  [gcdSocket connectToHost:s_host onPort:s_port error:nil];
}

//主动断开连接
- (void)disConnect
{
    [gcdSocket disconnect];
     gcdSocket=nil;
    [_timer invalidate];
    _timer=nil;
    [self.connectTimer invalidate];
    self.connectTimer=nil;
}
//发送消息
- (void)sendMsgWithLength:(int)length withsequenceId:(long)sequenceId withcmd:(short)cmd withVersion:(int)Version withRequestId:(int)RequestId withbody:(NSDictionary*)jsonDict{
    NSString* Terminal=SOCKETTERMINAL;//4字节
    NSMutableData*Data = [[NSMutableData alloc]init];
    NSError *error = nil;
    if ([jsonDict isKindOfClass:[NSDictionary class]]&&jsonDict) {

        NSData *jsonData = [NSJSONSerialization dataWithJSONObject:jsonDict options:NSJSONWritingPrettyPrinted error:&error];
        if ([jsonData length] > 0 &&error == nil)
        {
            [Data appendData:[SocketUtils bytesFromUInt32:(length+(int)jsonData.length)]];
        }else{
            [Data appendData:[SocketUtils bytesFromUInt32:length]];//4个字节
        }
        [Data appendData:[SocketUtils bytesFromUInt64:sequenceId]];//8个字节.token
        [Data appendData:[SocketUtils bytesFromUInt16:cmd]];//2字节
        [Data appendData:[SocketUtils bytesFromUInt32:Version]]; //4字节
        [Data appendData:[SocketUtils dataFromString:Terminal]];//4字节
        [Data appendData:[SocketUtils bytesFromUInt32:RequestId]];//4字节，标签
        [Data appendData:jsonData];
    }else{
        [Data appendData:[SocketUtils bytesFromUInt32:length]];//4个字节
        [Data appendData:[SocketUtils bytesFromUInt64:sequenceId]];//8个字节.token
        [Data appendData:[SocketUtils bytesFromUInt16:cmd]];//2字节
        [Data appendData:[SocketUtils bytesFromUInt32:Version]]; //4字节
        [Data appendData:[SocketUtils dataFromString:Terminal]];//4字节
        [Data appendData:[SocketUtils bytesFromUInt32:RequestId]];//4字节，标签
    }

     if (!_isSendconnection) {
                  [gcdSocket writeData:Data withTimeout:-1 tag:0];
       }else{
         [self reconnection];
                      [_sendDatas addObject:Data];

       }
}
#pragma mark - GCDAsynSocket Delegate
- (void)socket:(GCDAsyncSocket *)sock didConnectToHost:(NSString *)host port:(uint16_t)port{
    NSLog(@"行情socket连接成功");
    self.readBuf = [[NSMutableData alloc] init];
    [gcdSocket readDataWithTimeout:-1 tag:0];
    self.connectTimer = [NSTimer scheduledTimerWithTimeInterval:60 target:self selector:@selector(longConnectToSocket) userInfo:nil repeats:YES];
    [self.connectTimer fire];
    if (_isSendconnection) {
         if (self.sendDatas.count!=0) {
                   for (NSMutableData *data in self.sendDatas) {
                       
                       [gcdSocket writeData:data withTimeout:-1 tag:0];
                   }
                   [self.sendDatas removeAllObjects];
           }
           _isSendconnection=false;
       }
}
// 心跳连接
-(void)longConnectToSocket{
    [[SocketManager share] sendMsgWithLength:SOCKETREQUEST_LENGTH withsequenceId:0 withcmd:HEARTBEAT withVersion:COMMANDS_VERSION withRequestId: 0 withbody:nil];
}
- (void)socket:(GCDAsyncSocket *)sock didWriteDataWithTag:(long)tag{
//    NSLog(@"发送指令");
}
//收到消息
- (void)socket:(GCDAsyncSocket *)sock didReadData:(NSData *)data withTag:(long)tag{
    //将数据存入缓存区
    [self.readBuf appendData:data];
    while (self.readBuf.length > SOCKETRESPONSE_LENGTH) {

        NSData *lengthdata = [self.readBuf subdataWithRange:NSMakeRange(0,4)];
        int dataLenInt = CFSwapInt32BigToHost(*(int*)([lengthdata bytes]));
        NSInteger lengthInteger = 0;
        lengthInteger = (NSInteger)dataLenInt;

        if (_readBuf.length < lengthInteger) { //如果缓存中的数据长度小于包头长度 则继续拼接
            break;
        }else {
            //截取完整数据包
            NSData *dataOne = [_readBuf subdataWithRange:NSMakeRange(0, lengthInteger)];
            if (_delegate && [_delegate respondsToSelector:@selector(contractExchangeetdelegateSocket:didReadData:withTag:)]) {
                
                [_delegate contractExchangeetdelegateSocket:sock didReadData:dataOne withTag:tag];
                
            }
            [_readBuf replaceBytesInRange:NSMakeRange(0, lengthInteger) withBytes:nil length:0];
        }
    }
    [gcdSocket readDataWithTimeout:-1 tag:0];
}


- (void)socketDidDisconnect:(GCDAsyncSocket *)sock withError:(NSError *)err{
     NSLog(@"交易类socket断开连接%@",err);
     //[APPLICATION.window makeToast:@"交易类socket断开连接" duration:5.5 position:CSToastPositionCenter];
    if(_reconnection_time>=0&&_reconnection_time<=kMaxReconnection_time)
    {
        [_timer invalidate];
        _timer=nil;
         int time = 2;//设置重连时间
        _timer= [NSTimer scheduledTimerWithTimeInterval:time target:self selector:@selector(reconnection) userInfo:nil repeats:NO];
        _reconnection_time++;
    }else{
        _reconnection_time=0;
        _isSendconnection=true;
    }
}
//重连
-(void)reconnection{
     [gcdSocket connectToHost:s_host onPort:s_port error:nil];
}
@end
