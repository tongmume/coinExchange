//
//  ChatGroupFMDBTool.h
//  digitalCurrency
//
//  Created by iDog on 2019/4/16.
//  Copyright © 2019年 BIZZAN. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "ChatGroupInfoModel.h"
#import "ChatModel.h"
@interface ChatGroupFMDBTool : NSObject

//创建表格
+(void)createTable:(ChatGroupInfoModel *)model withIndex:(NSInteger)index;
//存值查询方法
+(void)saveDataForQuery:(ChatGroupInfoModel*)model withSqliteFlag:(BOOL)sqliteFlag withIndex:(NSInteger)index;
//存值时插入数据
+(void)saveDataForinsert:(ChatGroupInfoModel*)model withIndex:(NSInteger)index;
//数据库取值时得到的对象数组
+(NSMutableArray *)getChatGroupDataArr;
//修改数据
+(void)changeData:(ChatGroupInfoModel *)model;
//删除数据
+(void)deleteSqliteData:(ChatGroupInfoModel *)model;

@end
