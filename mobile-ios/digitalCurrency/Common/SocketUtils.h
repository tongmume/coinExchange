//
//  SocketUtils.h
//  digitalCurrency
//
//  Created by sunliang on 2019/4/9.
//  Copyright © 2019年 BIZZAN. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface SocketUtils : NSObject
/**
 *  反转字节序列
 *
 *  @param srcData 原始字节NSData
 *
 *  @return 反转序列后字节NSData
 */
+ (NSData *)dataWithReverse:(NSData *)srcData;

/** 将数值转成字节。编码方式：低位在前，高位在后 */
+ (NSData *)byteFromUInt8:(uint8_t)val;
+ (NSData *)bytesFromUInt16:(uint16_t)val;
+ (NSData *)bytesFromUInt32:(uint32_t)val;
+ (NSData *)bytesFromUInt64:(uint64_t)val;
+ (NSData *)bytesFromValue:(NSInteger)value byteCount:(int)byteCount;
+ (NSData *)bytesFromValue:(NSInteger)value byteCount:(int)byteCount reverse:(BOOL)reverse;

/** 将字节转成数值。解码方式：前序字节为低位，后续字节为高位 */
+ (uint8_t)uint8FromBytes:(NSData *)data;
+ (uint16_t)uint16FromBytes:(NSData *)data;
+ (uint32_t)uint32FromBytes:(NSData *)data;
+ (NSInteger)valueFromBytes:(NSData *)data;
+ (NSInteger)valueFromBytes:(NSData *)data reverse:(BOOL)reverse;
+ (NSData *)dataFromString:(NSString *)String;
+ (NSDictionary *)dictionaryWithJsonString:(NSString *)jsonString;
@end
