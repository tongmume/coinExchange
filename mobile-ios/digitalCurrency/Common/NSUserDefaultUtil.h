//
//  NSUserDefaultUtil.h
//  siLuBi
//
//  Created by sunliang on 2019/1/15.
//  Copyright © 2019年 BIZZAN. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface NSUserDefaultUtil : NSObject
+(void)PutBoolDefaults:(NSString *)key Value:(BOOL)value;
+(void)PutDefaults:(NSString *)key Value:(id)value;
+(id)GetDefaults:(NSString *)key;
@end
