//
//  keychianTool.h
//  digitalCurrency
//
//  Created by sunliang on 2019/4/4.
//  Copyright © 2019年 BIZZAN. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface keychianTool : NSObject
/** 获取设备唯一标识符
 */
+ (NSString*)readUserUUID;
//保存用户账号和密码到钥匙串中
+(void)saveToKeychainWithUserName:(NSString*)UserName withPassword:(NSString*)password;
/** 获取钥匙串中保存的账号密码
 */
+(NSString*)getUserNameAndPasswordFromKeychain;
@end
