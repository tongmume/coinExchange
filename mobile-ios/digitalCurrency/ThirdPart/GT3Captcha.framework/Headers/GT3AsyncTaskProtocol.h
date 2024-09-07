//
//  GT3AsyncTaskProtocol.h
//  GT3Captcha
//
//  Created by NikoXu on 2019/12/10.
//  Copyright © 2019 Geetest. All rights reserved.
//

#import "GT3Parameter.h"
#import "GT3Error.h"

NS_ASSUME_NONNULL_BEGIN

@protocol GT3AsyncTaskProtocol <NSObject>

/** 用于自定义验证注册的任务
 * @param completion    返回验证注册参数，以用于启动验证。`params`为用于注册
 *                      的数据。`error` 为获取验证注册参数失败的错误描述对象。
 *                      `GT3Error`的构造可以使用`+[GT3Error errorWithDomainType:GT3ErrorTypeExtern originalError:originalError withGTDesciption:nil]`完成。
 */
- (void)executeRegisterTaskWithCompletion:(void(^)(GT3RegisterParameter * _Nullable params, GT3Error * _Nullable error))completion;

/** 用于自定义验证结果校验的任务
 *  @param param        验证用于业务方进行校验的参数
 *  @param completion   返回验证二次校验结果给管理器，用于返回给管理器更新状态。
 *                      `validationResult` 为校验结果，赋值 YES 为成功，赋值 NO
 *                      为失败。`error` 为二次校验失败描述对象，其中 `error.code`
 *                      将被用于界面展示。
 *                      `GT3Error`的构造可以使用`+[GT3Error errorWithDomainType:GT3ErrorTypeExtern originalError:originalError withGTDesciption:nil]`完成。
 */
- (void)executeValidationTaskWithValidateParam:(GT3ValidationParam *)param completion:(void(^)(BOOL validationResult, GT3Error * _Nullable error))completion;

/** 用于取消所有自定义的任务 */
- (void)cancel;

@end

NS_ASSUME_NONNULL_END
