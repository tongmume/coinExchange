//
//  ChangeLanguage.m
//  siLuBi
//
//  Created by sunliang on 2019/1/7.
//  Copyright © 2019年 BZEEX. All rights reserved.
//

#import "ChangeLanguage.h"
//@import GT3Captcha;

@implementation LYLanguage

+ (LYLanguage*)creatName:(NSString *)name
              key:(NSString *)key
        bundleKey:(NSString *)bundleKey {
    LYLanguage *model = [LYLanguage new];
    model.name = name;
    model.key = key;
    model.bundleKey = bundleKey;
    return model;
}

@end

@implementation ChangeLanguage
static NSBundle *bundle = nil;
+ ( NSBundle * )bundle{

    return bundle;
}
//首次加载的时候先检测语言是否存在
+(void)initUserLanguage{

    NSUserDefaults *def = [NSUserDefaults standardUserDefaults];

    NSString *currLanguage = [def valueForKey:LocalLanguageKey];

    if(!currLanguage){
        currLanguage = @"en";
//        NSArray *preferredLanguages = [NSLocale preferredLanguages];
//        currLanguage = preferredLanguages[0];
//        if ([currLanguage hasPrefix:@"en"]) {
//            currLanguage = @"en";
////            currLanguage = @"zh-Hans";
//        }else if ([currLanguage hasPrefix:@"zh"]) {
//            currLanguage = @"zh-Hans";
//        }else currLanguage = @"en";
        [def setValue:currLanguage forKey:LocalLanguageKey];
        [def synchronize];
    }

    //获取文件路径
    NSString *path = [[NSBundle mainBundle] pathForResource:currLanguage ofType:@"lproj"];
    bundle = [NSBundle bundleWithPath:path];//生成bundle
}

//获取当前语言
+(NSString *)userLanguage{

    NSUserDefaults *def = [NSUserDefaults standardUserDefaults];

    NSString *language = [def valueForKey:LocalLanguageKey];

    return language;
}
// 设置语言
+(void)setUserlanguage:(NSString *)language{

    NSUserDefaults *userDefaults = [NSUserDefaults standardUserDefaults];
    NSString *currLanguage = [userDefaults valueForKey:LocalLanguageKey];
    if ([currLanguage isEqualToString:language]) {
        return;
    }
    [userDefaults setValue:language forKey:LocalLanguageKey];
    [userDefaults synchronize];

    NSString *path = [[NSBundle mainBundle] pathForResource:language ofType:@"lproj" ];
    bundle = [NSBundle bundleWithPath:path];
}

+ (NSString *)networkLanguage {
  NSString *key =  [self userLanguage];
    for (LYLanguage*language in  self.languageList) {
        if ([language.bundleKey isEqualToString:key]) {
            return  language.key;
        }
    }
    return @"zh-Hans";
}

+ (NSArray *)languageList {
    return @[
        [LYLanguage creatName:LocalizationKey(@"simplifiedChinese") key:@"zh_CN" bundleKey:@"zh-Hans"],
        [LYLanguage creatName:LocalizationKey(@"English") key:@"en_US" bundleKey:@"en"],
        [LYLanguage creatName:LocalizationKey(@"traditionalChinese") key:@"zh_HK" bundleKey:@"zh-HK"],
        [LYLanguage creatName:LocalizationKey(@"Japanese") key:@"ja_JP" bundleKey:@"ja"],
        [LYLanguage creatName:LocalizationKey(@"Korean") key:@"ko_KR" bundleKey:@"ko"],
        [LYLanguage creatName:LocalizationKey(@"German") key:@"de_DE" bundleKey:@"de"],
        [LYLanguage creatName:LocalizationKey(@"French") key:@"fr_FR" bundleKey:@"fr"],
        [LYLanguage creatName:LocalizationKey(@"Italian") key:@"it_IT" bundleKey:@"it"],
        [LYLanguage creatName:LocalizationKey(@"Spanish") key:@"es_ES" bundleKey:@"es"],
    ];
}

+ (NSTimeZone *)timeZone {
    NSString *current = [self userLanguage];
    NSString *name = @"America/Los_Angeles";
    if ([current isEqualToString: @"zh-Hans"]) {
        name = @"Asia/Shanghai";
    } else if ([current isEqualToString: @"en"]) {
        name = @"America/Los_Angeles";
    }else if ([current isEqualToString: @"zh-HK"]) {
        name = @"Asia/Hong_Kong";
    }else if ([current isEqualToString: @"ja"]) {
        name = @"Asia/Tokyo";
    }else if ([current isEqualToString: @"ko"]) {
        name = @"Asia/Seoul";
    }else if ([current isEqualToString: @"de"]) {
        name =  @"Europe/Berlin";
    }else if ([current isEqualToString: @"fr"]) {
        name = @"Europe/Paris";
    }else if ([current isEqualToString: @"it"]) {
        name = @"Europe/Rome";
    }else if ([current isEqualToString: @"es"]) {
        name = @"Europe/Madrid";
    }
    return  [[NSTimeZone alloc] initWithName:name];
}


@end
