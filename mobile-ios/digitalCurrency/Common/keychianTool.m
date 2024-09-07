//
//  keychianTool.m
//  digitalCurrency
//
//  Created by sunliang on 2019/4/4.
//  Copyright © 2019年 BIZZAN. All rights reserved.
//

#import "keychianTool.h"

@implementation keychianTool
/** 获取设备唯一标识符
 */
+ (NSString*)readUserUUID{
    NSMutableDictionary *userPwd = (NSMutableDictionary *)[ToolUtil load:UUIDKey];
    NSString *uuid = [userPwd objectForKey:UUIDKey];
    if (uuid == nil) {
        NSMutableDictionary *keyChain = [NSMutableDictionary dictionary];
        [keyChain setObject:UUID forKey:UUIDKey];
        [ToolUtil save:UUIDKey data:keyChain];
        uuid = [keyChain objectForKey:UUIDKey];
    }
    return uuid;
}
/** 保存用户账号和密码到钥匙串中
 */
+(void)saveToKeychainWithUserName:(NSString*)UserName withPassword:(NSString*)password{
    NSMutableDictionary *keyChain = [NSMutableDictionary dictionary];
    [keyChain setObject:[NSString stringWithFormat:@"%@%@",UserName,password] forKey:USENAMEPASSWORD];
    [ToolUtil save:USENAMEPASSWORD data:keyChain];

}
/** 获取钥匙串中保存的账号密码
 */
+(NSString*)getUserNameAndPasswordFromKeychain{
    NSMutableDictionary *userPwd = (NSMutableDictionary *)[ToolUtil load:USENAMEPASSWORD];
    NSString *UserNameAndPassword = [userPwd objectForKey:USENAMEPASSWORD];

    return UserNameAndPassword;
}

@end
