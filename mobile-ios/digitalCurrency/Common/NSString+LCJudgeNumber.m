//
//  NSString+LCJudgeNumber.m
//  ios_demo
//
//  Created by 刘翀 on 16/6/6.
//  Copyright © 2016年 xinhuo. All rights reserved.
//

#import "NSString+LCJudgeNumber.h"

@implementation NSString (LCJudgeNumber)

+ (BOOL)stringIsNull:(NSString *)string{
    //判断用户名是否为空
    if(string == nil){
        return YES;
    }
    NSString *str = [string stringByReplacingOccurrencesOfString:@" " withString:@""];
    if ([str isEqualToString:@""]) {
        return YES;
    }
    return NO;//不为空
}

+ (BOOL)checkPassword:(NSString *)password{
    //判断是否是6-16位
    if (password.length >= 6 && password.length <=16) {
        return YES;
    }
    return NO;
}
//判断中英混合的的字符串长度
+ (int)convertToInt:(NSString*)strtemp
{
    int strlength = 0;
    char* p = (char*)[strtemp cStringUsingEncoding:NSUnicodeStringEncoding];
    for (int i=0 ; i<[strtemp lengthOfBytesUsingEncoding:NSUnicodeStringEncoding] ;i++) {
        if (*p) {
            p++;
            strlength++;
        }
        else {
            p++;
        }
        
    }
    return strlength;
}

#pragma mark 正则表达式／判断第一个是否以中文开头的方法
+(BOOL)pipeizimu:(NSString *)string
{
    NSString *firstStr = [string substringToIndex:1];
    //判断是否以字母开头
    NSString *ZIMU = @"/^[a-zA-z]/";
    NSPredicate *regextestA = [NSPredicate predicateWithFormat:@"SELF MATCHES %@", ZIMU];
    
    if ([regextestA evaluateWithObject:firstStr] == YES)
    {
        return YES;
    }
    else
    {
        return NO;
    }
}

+(BOOL)isChineseFirst:(NSString *)string
{
    NSString *firstStr = [string substringToIndex:1];
    //是否以中文开头(unicode中文编码范围是0x4e00~0x9fa5)
    int utfCode = 0;
    void *buffer = &utfCode;
    NSRange range = NSMakeRange(0, 1);
    //判断是不是中文开头的,buffer->获取字符的字节数据 maxLength->buffer的最大长度 usedLength->实际写入的长度，不需要的话可以传递NULL encoding->字符编码常数，不同编码方式转换后的字节长是不一样的，这里我用了UTF16 Little-Endian，maxLength为2字节，如果使用Unicode，则需要4字节 options->编码转换的选项，有两个值，分别是NSStringEncodingConversionAllowLossy和NSStringEncodingConversionExternalRepresentation range->获取的字符串中的字符范围,这里设置的第一个字符 remainingRange->建议获取的范围，可以传递NULL
    BOOL b = [firstStr getBytes:buffer maxLength:2 usedLength:NULL encoding:NSUTF16LittleEndianStringEncoding options:NSStringEncodingConversionExternalRepresentation range:range remainingRange:NULL];
    if (b && (utfCode >= 0x4e00 && utfCode <= 0x9fa5))
        return YES;
    else
        return NO;
}

@end
