//
//  ChangeLanguage.h
//  siLuBi
//
//  Created by sunliang on 2019/1/7.
//  Copyright © 2019年 BIZZAN. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface LYLanguage : NSObject

@property (nonatomic, copy) NSString *name;   //显示
@property (nonatomic, copy) NSString *key;    //传递给接口
@property (nonatomic, copy) NSString *bundleKey; //本地读取

+ (LYLanguage *)creatName:(NSString *)name
              key:(NSString *)key
        bundleKey:(NSString *)bundleKey;
@end


@interface ChangeLanguage : NSObject
+(NSBundle *)bundle;//获取当前资源文件
+(void)initUserLanguage;//初始化语言文件
+(NSString *)userLanguage;//获取应用当前语言
+(void)setUserlanguage:(NSString *)language;//设置当前语言
+(NSString *)networkLanguage;
+(NSArray *)languageList;
+ (NSTimeZone *)timeZone;

@end
