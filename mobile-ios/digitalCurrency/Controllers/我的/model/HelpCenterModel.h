//
//  HelpCenterModel.h
//  digitalCurrency
//
//  Created by chu on 2019/8/8.
//  Copyright © 2019年 BIZZAN. All rights reserved.
//

#import "BaseModel.h"
@class HelpCenterContentModel;

@interface HelpCenterModel : BaseModel
@property (nonatomic, strong) NSNumber *cate;
@property (nonatomic, copy) NSString *title;
@property (nonatomic, copy) NSString *titleEN;
@property (nonatomic, copy) NSString *titleCN;
@property (nonatomic, strong) NSArray<HelpCenterContentModel *> *content;
+ (instancetype)modelWithDictionary:(NSDictionary *)dic;
@end

@interface HelpCenterContentModel : BaseModel
@property (nonatomic, copy) NSString *ID;
@property (nonatomic, copy) NSString *title;
@property (nonatomic, copy) NSString *sysHelpClassification;
@property (nonatomic, copy) NSString *imgUrl;
@property (nonatomic, copy) NSString *createTime;
@property (nonatomic, copy) NSString *status;
@property (nonatomic, copy) NSString *content;
@property (nonatomic, copy) NSString *author;
@property (nonatomic, copy) NSString *sort;
@property (nonatomic, copy) NSString *isTop;
+ (instancetype)modelWithDictionary:(NSDictionary *)dic;
@end
