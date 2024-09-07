//
//  MyEntrustInfoModel.m
//  digitalCurrency
//
//  Created by iDog on 2019/4/10.
//  Copyright © 2019年 BIZZAN. All rights reserved.
//

#import "MyEntrustInfoModel.h"

@implementation MyEntrustInfoModel
+ (NSDictionary *)mj_objectClassInArray
{
    return @{@"detail":[DetailInfo class]};
}



@end
@implementation DetailInfo

- (void)setValue:(id)value forKey:(NSString *)key{
    [super setValue:value forKey:key];
    if ([key isEqualToString:@"price"]) {
        if ([value isKindOfClass:[NSString class]]) {

            NSString *balance = (NSString *)value;
            NSDecimalNumber *number = [[NSDecimalNumber alloc] initWithString:balance];

            self.price = [number stringValue];
        }
    }

}

@end
