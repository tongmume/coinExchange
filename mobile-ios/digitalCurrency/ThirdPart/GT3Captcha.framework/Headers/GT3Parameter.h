//
//  GT3Parameter.h
//  GT3Captcha
//
//  Created by NikoXu on 2019/12/10.
//  Copyright © 2019 Geetest. All rights reserved.
//

#import <Foundation/Foundation.h>

NS_ASSUME_NONNULL_BEGIN

/** 验证注册参数 */
@interface GT3RegisterParameter : NSObject

/** 验证ID(gt) */
@property (nonatomic, strong) NSString *gt;

/** 验证流水号 */
@property (nonatomic, strong) NSString *challenge;

/** 验证当机状态。@(1) 为正常, @(0) 为宕机。*/
@property (nonatomic, strong) NSNumber *success;

@end

/** 验证校验参数 */
@interface GT3ValidationParam : NSObject

/** 验证初步判定结果。@"1" 通过, @"0" 未通过。*/
@property (nonatomic, strong) NSString *code;

/** 验证结果校验数据。使用该数据，通过 validate 接口进行结果校验，以获得最终验证结果。*/
@property (nullable, nonatomic, strong) NSDictionary *result;

/** 附带的消息。*/
@property (nullable, nonatomic, strong) NSString *message;

@end

NS_ASSUME_NONNULL_END
