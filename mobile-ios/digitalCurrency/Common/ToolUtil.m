//
//  ToolUtil.m
//  DynamicTraffic 3.0
//
//  Created by pt on 16/6/24.
//  Copyright © 2016年 yundi. All rights reserved.
//

#import "ToolUtil.h"


@implementation ToolUtil

//根据内容计算文本高度
+ (CGFloat) heightForString:(NSString *)value andWidth:(CGFloat)width fontSize:(CGFloat)size{
    UILabel *label = [[UILabel alloc] init];
    label.numberOfLines = 0;
    label.text = value;
    label.font = [UIFont systemFontOfSize:size];
    CGSize maxsize = [label sizeThatFits:CGSizeMake(width, MAXFLOAT)];
    return maxsize.height;
}

//加载16进制颜色
+ (UIColor *)colorWithHexString:(NSString *)color
{
    NSString *cString = [[color stringByTrimmingCharactersInSet:[NSCharacterSet whitespaceAndNewlineCharacterSet]] uppercaseString];
    
    // String should be 6 or 8 characters
    if ([cString length] < 6) {
        return [UIColor clearColor];
    }
    
    // strip 0X if it appears
    if ([cString hasPrefix:@"0X"])
        cString = [cString substringFromIndex:2];
    if ([cString hasPrefix:@"#"])
        cString = [cString substringFromIndex:1];
    if ([cString length] != 6)
        return [UIColor clearColor];
    
    // Separate into r, g, b substrings
    NSRange range;
    range.location = 0;
    range.length = 2;
    
    //r
    NSString *rString = [cString substringWithRange:range];
    
    //g
    range.location = 2;
    NSString *gString = [cString substringWithRange:range];
    
    //b
    range.location = 4;
    NSString *bString = [cString substringWithRange:range];
    
    // Scan values
    unsigned int r, g, b;
    [[NSScanner scannerWithString:rString] scanHexInt:&r];
    [[NSScanner scannerWithString:gString] scanHexInt:&g];
    [[NSScanner scannerWithString:bString] scanHexInt:&b];
    
    return [UIColor colorWithRed:((float) r / 255.0f) green:((float) g / 255.0f) blue:((float) b / 255.0f) alpha:1.0f];
}
//
+ (NSString*)checkNullOrEmpty:(id)obj
{
    if([obj isKindOfClass:[NSNull class]]){
        return @"";
    }
    if(!obj){
        return @"";
    }
    if([obj isKindOfClass:[NSNumber class]])
    {
        NSNumber*number = obj;
        if([number integerValue] == 0)return @"";
        return INT2STRING([number intValue]);
    }
    if([obj isKindOfClass:[NSString class]]){
        return obj;
    }
    if ([obj isEqualToString:@"null"] || [obj isEqualToString:@"NULL"] || [obj isEqualToString:@""]) {
        return @"";
    }
    return @"";
}
//电话号码匹配
+ (BOOL)checkPhoneNumInput:(NSString *)phoneStr
{
    NSString *photoRange = @"^1(3[0-9]|4[0-9]|5[0-9]|7[0-9]|8[0-9])\\d{8}$";//正则表达式
    NSPredicate *regexMobile = [NSPredicate predicateWithFormat:@"SELF MATCHES %@",photoRange];
    BOOL result = [regexMobile evaluateWithObject:phoneStr];
    if (result)
    {
        return YES;
    } else
    {
        return NO;
    }
    
}

//存
+ (void)save:(NSString *)service data:(id)data {
    //Get search dictionary
    NSMutableDictionary *keychainQuery = [self getKeychainQuery:service];
    //Delete old item before add new item
    SecItemDelete((__bridge CFDictionaryRef)keychainQuery);
    //Add new object to search dictionary(Attention:the data format)
    [keychainQuery setObject:[NSKeyedArchiver archivedDataWithRootObject:data] forKey:(__bridge id)kSecValueData];
    //Add item to keychain with the search dictionary
    SecItemAdd((__bridge CFDictionaryRef)keychainQuery, NULL);
}

+ (NSMutableDictionary *)getKeychainQuery:(NSString *)service {
    return [NSMutableDictionary dictionaryWithObjectsAndKeys:
            (__bridge id)kSecClassGenericPassword,(__bridge id)kSecClass,
            service, (__bridge id)kSecAttrService,
            service, (__bridge id)kSecAttrAccount,
            (__bridge id)kSecAttrAccessibleAfterFirstUnlock,(__bridge id)kSecAttrAccessible,
            nil];
}

//取
+ (id)load:(NSString *)service {
    id ret = nil;
    NSMutableDictionary *keychainQuery = [self getKeychainQuery:service];
    //Configure the search setting
    //Since in our simple case we are expecting only a single attribute to be returned (the password) we can set the attribute kSecReturnData to kCFBooleanTrue
    [keychainQuery setObject:(__bridge id)kCFBooleanTrue forKey:(__bridge id)kSecReturnData];
    [keychainQuery setObject:(__bridge id)kSecMatchLimitOne forKey:(__bridge id)kSecMatchLimit];
    CFDataRef keyData = NULL;
    if (SecItemCopyMatching((__bridge CFDictionaryRef)keychainQuery, (CFTypeRef *)&keyData) == noErr) {
        @try {
            ret = [NSKeyedUnarchiver unarchiveObjectWithData:(__bridge NSData *)keyData];
        } @catch (NSException *e) {
            NSLog(@"Unarchive of %@ failed: %@", service, e);
        } @finally {
        }
    }
    if (keyData)
        CFRelease(keyData);
    return ret;
}

+ (void)delete:(NSString *)service {
    NSMutableDictionary *keychainQuery = [self getKeychainQuery:service];
    SecItemDelete((__bridge CFDictionaryRef)keychainQuery);
}

//根据时间戳取得时间 xx年xx月xx日 
+ (NSString *)birthdayFormatWithStamp:(NSInteger)stamp withFormat:(NSString *)format{
//    return [self formatTimeStamp:stamp withFormat:@"yyyy年MM月dd日"];
    return [self formatTimeStamp:stamp withFormat:format];
}
+ (NSString *)formatTimeStamp:(NSInteger)stamp withFormat:(NSString *)format
{
    NSDate *date = [NSDate dateWithTimeIntervalSince1970:stamp];
    NSDateFormatter *dateFormatter = [[NSDateFormatter alloc] init];
    [dateFormatter setDateFormat:format];
    NSTimeZone *zone = [NSTimeZone defaultTimeZone];
    [dateFormatter setTimeZone:zone];
    NSString *dateStr = [dateFormatter stringFromDate:date];
    return dateStr;
}
//str -> code
+ (NSString *)encodeToPercentEscapeString: (NSString *) input
{
    NSString*
    outputStr = (__bridge NSString *)CFURLCreateStringByAddingPercentEscapes(
                                                                             
                                                                             NULL, /* allocator */
                                                                             
                                                                             (__bridge CFStringRef)input,
                                                                             
                                                                             NULL, /* charactersToLeaveUnescaped */
                                                                             
                                                                             (CFStringRef)@"!*'();:@&=+$,/?%#[]",
                                                                             
                                                                             kCFStringEncodingUTF8);
    
    
    return outputStr;
}
//根据当前时间 输出
+ (NSString *)printDateStringWithNowDate:(NSString *)format{
//    @"YYYYMMDDhhmm"年月日时分 YYYYMMDD年月日
    NSDate *date = [NSDate date];
    NSDateFormatter *dateFormatter = [[NSDateFormatter alloc] init];
    [dateFormatter setDateFormat:format];
    NSTimeZone *zone = [NSTimeZone defaultTimeZone];
    [dateFormatter setTimeZone:zone];
    NSString *dateStr = [dateFormatter stringFromDate:date];
    return dateStr;
}

//截屏
+ (UIImage *)screenShot{
    UIGraphicsBeginImageContextWithOptions(CGSizeMake(kWindowW, kWindowH), YES, 0);
    
    //设置截屏大小
    
    [[APPLICATION.window layer] renderInContext:UIGraphicsGetCurrentContext()];
    
    UIImage *viewImage = UIGraphicsGetImageFromCurrentImageContext();
    
    UIGraphicsEndImageContext();
    
    
    return viewImage;
}

+ (BOOL)textfiledOnlyCanInputWithText:(NSString *)text{
    NSString * regex = @"^[A-Za-z0-9]{9,15}$";
    NSPredicate *pred = [NSPredicate predicateWithFormat:@"SELF MATCHES %@", regex];
    BOOL isMatch = [pred evaluateWithObject:text];
    return isMatch;
}
//UTC
+ (NSString *)presentDateTransformUTCdateWithFormat:(NSString *)format{
    NSDate *date = [NSDate date];
    NSDateFormatter *dateFormatter = [[NSDateFormatter alloc] init];
    [dateFormatter setDateFormat:format];
    
    NSTimeZone *zone = [NSTimeZone timeZoneWithName:@"UTC"];
     NSTimeZone* destinationTimeZone = [NSTimeZone localTimeZone];     //得到源日期与世界标准时间的偏移量
     NSInteger sourceGMTOffset = [zone secondsFromGMTForDate:date];     //目标日期与本地时区的偏移量
      NSInteger destinationGMTOffset = [destinationTimeZone secondsFromGMTForDate:date];     //得到时间偏移量的差值
     NSTimeInterval interval = destinationGMTOffset - sourceGMTOffset;     //转为现在时间
    NSDate* destinationDateNow = [[NSDate alloc] initWithTimeInterval:interval sinceDate:date];
//    [dateFormatter setTimeZone:zone];
    NSString *dateStr = [dateFormatter stringFromDate:destinationDateNow];
    return dateStr;
}


+ (long long)returnHowManyMintesswithstr:(NSString *)timeStr{
    NSDateFormatter *formatter = [[NSDateFormatter alloc] init];
    [formatter setDateStyle:NSDateFormatterMediumStyle];
    [formatter setTimeStyle:NSDateFormatterShortStyle];
    [formatter setDateFormat:@"yyy-MM-dd HH:mm:ss"]; //设置格式,hh与HH的区别:分别表示12小时制,24小时制
    [formatter setTimeZone:ChangeLanguage.timeZone];
//    NSTimeZone* timeZone = [NSTimeZone systemTimeZone]; //设置时区
//    [formatter setTimeZone:timeZone];
    
    NSDate* date = [formatter dateFromString:timeStr]; //将字符串按formatter转成nsdate
    
    //时间转时间戳的方法:
    NSString *timeSp = [NSString stringWithFormat:@"%ld", (long)[date timeIntervalSince1970]]; //时间戳的值
    return [timeSp longLongValue];
}
#pragma mark - 匹配6-20位的密码
+ (BOOL)matchPassword:(NSString *)password {
    NSString *pattern = @"^[^s]{6,20}$";
    NSPredicate *regexMobile = [NSPredicate predicateWithFormat:@"SELF MATCHES %@",pattern];
    BOOL result = [regexMobile evaluateWithObject:password];
    if (result)
    {
        return YES;
    } else
    {
        return NO;
    }
}
#pragma mark - 匹配邮箱帐号
+ (BOOL)matchEmail:(NSString *)email {
    NSString *pattern =
    @"^[a-z0-9]+([\\._\\-]*[a-z0-9])*@([a-z0-9]+[-a-z0-9]*[a-z0-9]+\\.){1,63}[a-z0-9]+$";
    NSPredicate *regexMobile = [NSPredicate predicateWithFormat:@"SELF MATCHES %@",pattern];
    BOOL result = [regexMobile evaluateWithObject:email];
    if (result)
    {
        return YES;
    } else
    {
        return NO;
    }
}
+(NSString *)stampFormatterWithStamp:(NSInteger)stamp{
    
    NSDateFormatter *stampFormatter = [[NSDateFormatter alloc] init];
    [stampFormatter setDateFormat:@"YYYY-MM-dd HH:mm:ss"];
    [stampFormatter setTimeZone:ChangeLanguage.timeZone];
    //以 1970/01/01 GMT为基准，然后过了secs秒的时间
    NSDate *stampDate2 = [NSDate dateWithTimeIntervalSince1970:stamp/1000];
    return [stampFormatter stringFromDate:stampDate2];
}
//获取当前日期
+ (NSString *)getCurrentTime {
    NSDateFormatter *formatter = [[NSDateFormatter alloc] init];
    [formatter setDateFormat:@"yyyyMMdd"];
    [formatter setTimeZone:ChangeLanguage.timeZone];
    NSString *dateTime = [formatter stringFromDate:[NSDate date]];
    return dateTime;
}
+ (NSDate *)zeroOfDate
 {
        NSCalendar *calendar = [NSCalendar currentCalendar];
         NSDateComponents *components = [calendar components:NSUIntegerMax fromDate:[NSDate date]];
       components.hour = 0;
        components.minute = 0;
        components.second = 0;
        NSTimeInterval ts = (double)(int)[[calendar dateFromComponents:components] timeIntervalSince1970];
         return [NSDate dateWithTimeIntervalSince1970:ts];
     }
//MARK:--判断字符串后有几位小数，超过八位就省略
+(NSString *)judgeStringForDecimalPlaces:(NSString *)string{
    NSString *numStr = @"";
    NSArray *array = [string componentsSeparatedByString:@"."];
    if (array.count == 2) {
        NSString *str = array[1];
        if (str.length > 8) {
            numStr = [NSString stringWithFormat:@"%@.%@",array[0],[str substringWithRange:NSMakeRange(0, 8)]];
//            numStr = [NSString stringWithFormat:@"%.8f",[string doubleValue]];

        }else{
            numStr = string;
        }
    }else{
        numStr = string;
    }
    return numStr;
}
//MARK:--科学计数法转具体数字 ,如果传输的就是具体数据，可控制其小数点后面最多保留八位
+(NSString *)formartScientificNotationWithString:(NSString *)str{
    NSRange eSite;
    BOOL flag;
    NSString *feeStr;
    if ([str containsString:@"E"]) {
        eSite = [str rangeOfString:@"E"];
        flag = YES;
    }else if ([str containsString:@"e"]){
        eSite = [str rangeOfString:@"e"];
        flag = YES;
    }else{
        flag = NO;
    }
    if (flag) {
        double fund = [[str substringWithRange:NSMakeRange(0, eSite.location)] doubleValue];  //把E前面的数截取下来当底数
        double top = [[str substringFromIndex:eSite.location + 1] doubleValue];   //把E后面的数截取下来当指数
        double result = fund * pow(10.0, top);
        feeStr = [NSString stringWithFormat:@"%.8f",result];
        feeStr = [self stringFromNumber:result withlimit:8];
    }else{
        feeStr = [self judgeStringForDecimalPlaces:str];
    }
    return feeStr;
}

//截取小数点后两位 如果没有小数点 自动补0
+ (NSString *)interceptTheTwoBitsAfterDecimalPoint:(NSString *)string{
    NSString *returnStr = string;
    //判断是否包含小数点
    if ([string containsString:@"."]) {
        //截取字符串
        NSArray *bits = [string componentsSeparatedByString:@"."];
        //如果bits数量大于2说明小数点不止一个
        if (bits.count == 2) {
            NSString *integerStr = [bits firstObject];

            NSString *bit = [bits lastObject];
            NSString *newBit = @"";
            if (bit.length >= 2) {
                newBit = [bit substringToIndex:2];
            }else{
                newBit = [bit stringByAppendingString:@"0"];
            }
            string = [NSString stringWithFormat:@"%@.%@",integerStr,newBit];
        }
    }else{
        string = [string stringByAppendingString:@".00"];
    }
    returnStr = string;
    return returnStr;
}
//MARK:--毫秒时间戳转 HH:mm MM/dd格式
+ (NSString *)convertStrToTime:(NSString *)timeStr
{
    long long time=[timeStr longLongValue];
    NSDate *d = [[NSDate alloc]initWithTimeIntervalSince1970:time/1000.0];
    NSDateFormatter *formatter = [[NSDateFormatter alloc]init];
    [formatter setDateFormat:@"HH:mm MM/dd"];
    [formatter setTimeZone:ChangeLanguage.timeZone];
    NSString*timeString=[formatter stringFromDate:d];
    return timeString;
    
}
//MARK:--时间字符串格式化为 HH:mm MM/dd
+(NSString *)transformForTimeString:(NSString *)timeStr{
    NSDateFormatter *formatter = [[NSDateFormatter alloc] init];
    [formatter setDateFormat:@"yyyy-MM-dd HH:mm:ss"];
    [formatter setTimeZone:ChangeLanguage.timeZone];
    NSDate *someDay = [formatter dateFromString:timeStr];
    NSDateFormatter *formatter1 = [[NSDateFormatter alloc] init];
    [formatter1 setDateFormat:@"HH:mm MM/dd"];
    [formatter1 setTimeZone:ChangeLanguage.timeZone];
    NSString *currentDateString = [formatter1 stringFromDate:someDay];
    return currentDateString;
}
//doubleNumber,原数据，decimalNum,保留的小数位数
+(NSString*)stringFromNumber:(double)doubleNumber withlimit:(int)decimalNum{
    NSNumberFormatter *nFormat = [[NSNumberFormatter alloc] init];
    [nFormat setNumberStyle:NSNumberFormatterNoStyle];
    nFormat.roundingMode = NSNumberFormatterRoundFloor;//不四舍五入
    [nFormat setPositiveFormat:@"#####0.000;"];
    [nFormat setMinimumFractionDigits:decimalNum];
    [nFormat setMaximumFractionDigits:decimalNum];
    return [nFormat stringFromNumber:[NSNumber numberWithDouble:doubleNumber]];
}
//获取当前的时间  YYYY-MM-dd HH:mm:ss
+(NSString*)getCurrentDateString{
    NSDateFormatter *formatter = [[NSDateFormatter alloc] init];
    // ----------设置你想要的格式,hh与HH的区别:分别表示12小时制,24小时制
    [formatter setDateFormat:@"YYYY-MM-dd HH:mm:ss"];
    [formatter setTimeZone:ChangeLanguage.timeZone];
    //现在时间,你可以输出来看下是什么格式
    NSDate *datenow = [NSDate date];
    //----------将nsdate按formatter格式转成nsstring
    NSString *currentTimeString = [formatter stringFromDate:datenow];
//    NSLog(@"currentTimeString =  %@",currentTimeString);
    return currentTimeString;
}

//时间转时间戳
+ (NSString *)timeToTimeIntervalString:(NSString *)timeString WithDateFormat:(NSString *)format
{
    NSDateFormatter *dateFormatter = [[NSDateFormatter alloc] init];
    dateFormatter.locale = [NSLocale currentLocale];
    dateFormatter.timeZone = [NSTimeZone localTimeZone];
    [dateFormatter setDateFormat:@"yyyy-MM-dd HH:mm:ss"];
    [dateFormatter setTimeZone:ChangeLanguage.timeZone];
    NSDate *destDate = [dateFormatter dateFromString:timeString];
    NSTimeInterval interval = [destDate timeIntervalSince1970] * 1000;// *1000 是精确到毫秒，不乘就是精确到秒
    NSString *dateString = [NSString stringWithFormat:@"%0.f", interval];
    
    return dateString;
}

//时间戳转时间
+ (NSString *)timeIntervalToTimeString:(NSString *)timeString WithDateFormat:(NSString *)format
{
    NSTimeInterval time = [timeString doubleValue] / 1000;// *1000 是精确到毫秒，不乘就是精确到秒
    
    NSDate *date=[NSDate dateWithTimeIntervalSince1970:time];
    
    NSDateFormatter *dateformatter=[[NSDateFormatter alloc] init];
    
    [dateformatter setDateFormat:format];
    [dateformatter setTimeZone:ChangeLanguage.timeZone];
    
    NSString *staartstr=[dateformatter stringFromDate:date];
    
    return staartstr;
    
}
@end
