//
//  NSString+LCJudgeNumber.h
//  ios_demo
//
//  Created by 刘翀 on 16/6/6.
//  Copyright © 2016年 xinhuo. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface NSString (LCJudgeNumber)

/*
 验证字符串是否为空
 */
+(BOOL)stringIsNull:(NSString *)string;
/*
 验证密码是否合法
 */
+ (BOOL)checkPassword:(NSString *) password;
/**
 *  判断中英混合字符串长度
 *
 *  @param strtemp 字符串
 *
 *  @return 长度int
 */
+ (int)convertToInt:(NSString*)strtemp;

/**
 *  验证首字母是否是中文
 *
 *
 *  @return yes or no
 */
+(BOOL)isChineseFirst:(NSString *)string;
/**
 *  判断首字符是否是英文字母
 *
 *  @param string 字符串
 *
 *  @return yes or no
 */
+(BOOL)pipeizimu:(NSString *)string;


@end
