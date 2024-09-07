//
//  SocketUtils.m
//  digitalCurrency
//
//  Created by sunliang on 2019/4/9.
//  Copyright © 2019年 BIZZAN. All rights reserved.
//

#import "SocketUtils.h"

@implementation SocketUtils

/**
 *  反转字节序列
 *
 *  @param srcData 原始字节NSData
 *
 *  @return 反转序列后字节NSData
 */
+ (NSData *)dataWithReverse:(NSData *)srcData
{
    //    NSMutableData *dstData = [[NSMutableData alloc] init];
    //    for (NSUInteger i=0; i<srcData.length; i++) {
    //        [dstData appendData:[srcData subdataWithRange:NSMakeRange(srcData.length-1-i, 1)]];
    //    }//for

    NSUInteger byteCount = srcData.length;
    NSMutableData *dstData = [[NSMutableData alloc] initWithData:srcData];
    NSUInteger halfLength = byteCount / 2;
    for (NSUInteger i=0; i<halfLength; i++) {
        NSRange begin = NSMakeRange(i, 1);
        NSRange end = NSMakeRange(byteCount - i - 1, 1);
        NSData *beginData = [srcData subdataWithRange:begin];
        NSData *endData = [srcData subdataWithRange:end];
        [dstData replaceBytesInRange:begin withBytes:endData.bytes];
        [dstData replaceBytesInRange:end withBytes:beginData.bytes];
    }//for

    return dstData;
}

+ (NSData *)byteFromUInt8:(uint8_t)val
{
    NSMutableData *valData = [[NSMutableData alloc] init];

    unsigned char valChar[1];
    valChar[0] = 0xff & val;
    [valData appendBytes:valChar length:1];

    return [self dataWithReverse:valData];
}

+ (NSData *)bytesFromUInt16:(uint16_t)val
{
    NSMutableData *valData = [[NSMutableData alloc] init];

    unsigned char valChar[2];
    valChar[0] = 0xff & val;
    valChar[1] = (0xff00 & val) >> 8;
    [valData appendBytes:valChar length:2];

    return [self dataWithReverse:valData];
}

+ (NSData *)bytesFromUInt32:(uint32_t)val
{
    NSMutableData *valData = [[NSMutableData alloc] init];

    unsigned char valChar[4];
    valChar[0] = 0xff & val;
    valChar[1] = (0xff00 & val) >> 8;
    valChar[2] = (0xff0000 & val) >> 16;
    valChar[3] = (0xff000000 & val) >> 24;
    [valData appendBytes:valChar length:4];

    return [self dataWithReverse:valData];
}

+ (NSData *)bytesFromUInt64:(uint64_t)val
{
    NSMutableData *valData = [[NSMutableData alloc] init];

    unsigned char valChar[8];
    valChar[0] = 0xff & val;
    valChar[1] = (0xff00 & val) >> 8;
    valChar[2] = (0xff0000 & val) >> 16;
    valChar[3] = (0xff000000 & val) >> 24;
    valChar[4] = (0xff00000000 & val) >> 32;
    valChar[5] = (0xff0000000000 & val) >> 40;
    valChar[6] = (0xff000000000000 & val) >> 48;
    valChar[7] = (0xff00000000000000 & val) >> 56;
    [valData appendBytes:valChar length:8];

    return [self dataWithReverse:valData];
}

+ (NSData *)bytesFromValue:(NSInteger)value byteCount:(int)byteCount
{
    NSAssert(value <= 4294967295, @"bytesFromValue: (max value is 4294967295)");
    NSAssert(byteCount <= 4, @"bytesFromValue: (byte count is too long)");

    NSMutableData *valData = [[NSMutableData alloc] init];
    NSUInteger tempVal = value;
    int offset = 0;

    while (offset < byteCount) {
        unsigned char valChar = 0xff & tempVal;
        [valData appendBytes:&valChar length:1];
        tempVal = tempVal >> 8;
        offset++;
    }//while

    return valData;
}

+ (NSData *)bytesFromValue:(NSInteger)value byteCount:(int)byteCount reverse:(BOOL)reverse
{
    NSData *tempData = [self bytesFromValue:value byteCount:byteCount];
    if (reverse) {
        return tempData;
    }

    return [self dataWithReverse:tempData];
}

+ (uint8_t)uint8FromBytes:(NSData *)fData
{
    NSAssert(fData.length == 1, @"uint8FromBytes: (data length != 1)");
    NSData *data = fData;
    uint8_t val = 0;
    [data getBytes:&val length:1];
    return val;
}

+ (uint16_t)uint16FromBytes:(NSData *)fData
{
    NSAssert(fData.length == 2, @"uint16FromBytes: (data length != 2)");
    NSData *data = [self dataWithReverse:fData];;
    uint16_t val0 = 0;
    uint16_t val1 = 0;
    [data getBytes:&val0 range:NSMakeRange(0, 1)];
    [data getBytes:&val1 range:NSMakeRange(1, 1)];

    uint16_t dstVal = (val0 & 0xff) + ((val1 << 8) & 0xff00);
    return dstVal;
}

+ (uint32_t)uint32FromBytes:(NSData *)fData
{
    NSAssert(fData.length == 4, @"uint16FromBytes: (data length != 4)");
    NSData *data = [self dataWithReverse:fData];

    uint32_t val0 = 0;
    uint32_t val1 = 0;
    uint32_t val2 = 0;
    uint32_t val3 = 0;
    [data getBytes:&val0 range:NSMakeRange(0, 1)];
    [data getBytes:&val1 range:NSMakeRange(1, 1)];
    [data getBytes:&val2 range:NSMakeRange(2, 1)];
    [data getBytes:&val3 range:NSMakeRange(3, 1)];

    uint32_t dstVal = (val0 & 0xff) + ((val1 << 8) & 0xff00) + ((val1 << 16) & 0xff0000) + ((val1 << 24) & 0xff000000);
    return dstVal;
}

+ (NSInteger)valueFromBytes:(NSData *)data
{
    NSAssert(data.length <= 4, @"valueFromBytes: (data is too long)");

    NSUInteger dataLen = data.length;
    NSUInteger value = 0;
    int offset = 0;

    while (offset < dataLen) {
        uint32_t tempVal = 0;
        [data getBytes:&tempVal range:NSMakeRange(offset, 1)];
        value += (tempVal << (8 * offset));
        offset++;
    }//while

    return value;
}
+ (NSInteger)valueFromBytes:(NSData *)data reverse:(BOOL)reverse
{
    NSData *tempData = data;
    if (reverse) {
        tempData = [self dataWithReverse:tempData];
    }
    return [self valueFromBytes:tempData];
}
+ (NSData *)dataFromString:(NSString *)String{
    return [String dataUsingEncoding:NSUTF8StringEncoding];
}
+ (NSDictionary *)dictionaryWithJsonString:(NSString *)jsonString
{
    if (jsonString == nil) {
        return nil;
    }

    NSData *jsonData = [jsonString dataUsingEncoding:NSUTF8StringEncoding];
    NSError *err;
    NSDictionary *dic = [NSJSONSerialization JSONObjectWithData:jsonData
                                                        options:NSJSONReadingMutableContainers
                                                          error:&err];
    if(err)
    {
        NSLog(@"json解析失败：%@",err);
        return nil;
    }
    return dic;
}
@end
