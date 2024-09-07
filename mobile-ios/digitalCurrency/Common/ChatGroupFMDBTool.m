//
//  ChatGroupFMDBTool.m
//  digitalCurrency
//
//  Created by iDog on 2019/4/16.
//  Copyright © 2019年 BIZZAN. All rights reserved.
//

#import "ChatGroupFMDBTool.h"
#import "FMDatabase.h"
#import "FMDatabaseQueue.h"


@implementation ChatGroupFMDBTool

//创建表格
+(void)createTable:(ChatGroupInfoModel *)model withIndex:(NSInteger)index{
    NSString *doc = [NSSearchPathForDirectoriesInDomains(NSDocumentDirectory, NSUserDomainMask, YES) objectAtIndex:0];
//    NSLog(@"--%@",doc);
    NSString *pathStr = [NSString stringWithFormat:@"%@GroupInfo.sqlite",[YLUserInfo shareUserInfo].ID];
    NSString *path = [doc stringByAppendingPathComponent:pathStr];
    NSFileManager *fileManager = [NSFileManager defaultManager];
    BOOL result = [fileManager fileExistsAtPath:path];

//    NSLog(@"--%@",path);
    if (!result)
    {
        FMDatabase *db = [FMDatabase databaseWithPath:path];
        if ([db open]) {
            NSString *sql = @"CREATE TABLE 'GroupInfo' ('id' INTEGER PRIMARY KEY AUTOINCREMENT  NOT NULL , 'nameFrom' VARCHAR(30), 'uidFrom' VARCHAR(30), 'avatar' VARCHAR(100), 'orderId' VARCHAR(30),  'content' VARCHAR(100), 'flagIndex' VARCHAR(30), 'nameTo' VARCHAR(30), 'uidTo' VARCHAR(30), 'sendTimeStr' VARCHAR(30))";
            BOOL res = [db executeUpdate:sql];
            if (!res) {
                NSLog(@"error when creating db table");
            } else {
                NSLog(@"success to creating db table");
                [self saveDataForinsert:model withIndex:index];
            }
            [db close];
    }else{
        NSLog(@"error when open db table");
        }
    }else{
      [self saveDataForQuery:model withSqliteFlag:NO withIndex:index];
    }
}
//存值查询方法
+(void)saveDataForQuery:(ChatGroupInfoModel*)model withSqliteFlag:(BOOL)sqliteFlag withIndex:(NSInteger)index{
    NSString *doc = [NSSearchPathForDirectoriesInDomains(NSDocumentDirectory, NSUserDomainMask, YES) objectAtIndex:0];
    NSString *pathStr = [NSString stringWithFormat:@"%@GroupInfo.sqlite",[YLUserInfo shareUserInfo].ID];
    NSString *path = [doc stringByAppendingPathComponent:pathStr];
    FMDatabase *db = [FMDatabase databaseWithPath:path];
    if ([db open]) {
        NSString *sql = @"select *from GroupInfo";
        FMResultSet *rs = [db executeQuery:sql];
        while ([rs next]) {
            NSString *orderId = [rs stringForColumn:@"orderId"];
            if ([orderId isEqualToString:model.orderId]) {
                sqliteFlag = YES;

                NSString *sql1 = @"UPDATE GroupInfo SET flagIndex = ?  WHERE  orderId = ?";
                NSString *sql2 = @"UPDATE GroupInfo SET content = ?  WHERE  orderId = ?";
                NSString *sql3 = @"UPDATE GroupInfo SET avatar = ?  WHERE  orderId = ?";
                NSString *sql4 = @"UPDATE GroupInfo SET sendTimeStr = ?  WHERE  orderId = ?";
                NSString *flag;
                if (index == 1) {
                    //需要标记
                    flag = @"1";
                }else{
                    flag = @"0";
                }
                BOOL res1 = [db executeUpdate:sql1, flag, orderId];
                BOOL res2 = [db executeUpdate:sql2,model.content, orderId];
                BOOL res3 = [db executeUpdate:sql3,model.avatar, orderId];
                BOOL res4 = [db executeUpdate:sql4,model.sendTimeStr, orderId];

                if (res1 && res2 && res3 && res4) {
                    NSLog(@"数据修改成功");
                } else {
                    NSLog(@"数据修改失败");

                }
            }
        }
        if (sqliteFlag == NO) {
            [self saveDataForinsert:model withIndex:index];
        }
        [db close];
    }
}
//存值时插入数据
+(void)saveDataForinsert:(ChatGroupInfoModel*)model withIndex:(NSInteger)index{
    NSString *doc = [NSSearchPathForDirectoriesInDomains(NSDocumentDirectory, NSUserDomainMask, YES) objectAtIndex:0];
    NSString *pathStr = [NSString stringWithFormat:@"%@GroupInfo.sqlite",[YLUserInfo shareUserInfo].ID];
    NSString *path = [doc stringByAppendingPathComponent:pathStr];
    FMDatabase *db = [FMDatabase databaseWithPath:path];
    if ([db open]) {
        NSString *sql = @"insert into GroupInfo (nameFrom,uidFrom, avatar,orderId,content,flagIndex,uidTo,nameTo,sendTimeStr) values(?, ?, ?, ?, ?, ?, ?, ?, ?) ";
        NSString *flag;
        if (index == 1) {
            //需要标记
            flag = @"1";
        }else{
            flag = @"0";
        }
        BOOL res = [db executeUpdate:sql, model.nameFrom,model.uidFrom, model.avatar,model.orderId,model.content,flag,model.uidTo,model.nameTo,model.sendTimeStr];
        if (!res) {
            NSLog(@"error to insert data");
        } else {
            NSLog(@"success to insert data");
        }
        [db close];
    }
}
//数据库取值时得到的对象数组
+(NSMutableArray *)getChatGroupDataArr{
    NSMutableArray *chatGroupArr = [[NSMutableArray alloc] init];
    NSMutableArray *tempArr = [[NSMutableArray alloc] init];
    NSString *doc = [NSSearchPathForDirectoriesInDomains(NSDocumentDirectory, NSUserDomainMask, YES) objectAtIndex:0];
//    NSLog(@"--%@",doc);
    NSString *pathStr = [NSString stringWithFormat:@"%@GroupInfo.sqlite",[YLUserInfo shareUserInfo].ID];
    NSString *path = [doc stringByAppendingPathComponent:pathStr];
    NSFileManager *fileManager = [NSFileManager defaultManager];
    BOOL result = [fileManager fileExistsAtPath:path];
    FMDatabase *db = [FMDatabase databaseWithPath:path];
    if (result) {
        //
        if ([db open]) {
            NSString *sql = @"select *from GroupInfo";
            FMResultSet *rs = [db executeQuery:sql];
            while ([rs next]) {
                int userId = [rs intForColumn:@"id"];
                ChatGroupInfoModel *groupModel = [[ChatGroupInfoModel alloc] init];
                groupModel.nameFrom = [rs stringForColumn:@"nameFrom"];
                groupModel.orderId = [rs stringForColumn:@"orderId"];
                groupModel.flagIndex = [rs stringForColumn:@"flagIndex"];
                groupModel.uidFrom = [rs stringForColumn:@"uidFrom"];
                groupModel.uidTo = [rs stringForColumn:@"uidTo"];
                groupModel.nameTo = [rs stringForColumn:@"nameTo"];
                groupModel.avatar = [rs stringForColumn:@"avatar"];
                groupModel.content = [rs stringForColumn:@"content"];
                groupModel.sendTimeStr = [rs stringForColumn:@"sendTimeStr"];
                NSLog(@"user id = %d, nameFrom = %@, orderId = %@, flagIndex = %@,uidFrom = %@,avatar = %@,content= %@,nameTo = %@,sendTimeStr = %@", userId, groupModel.nameFrom,groupModel.orderId,groupModel.flagIndex,groupModel.uidFrom,groupModel.avatar,groupModel.content,groupModel.nameTo ,groupModel.sendTimeStr);
                [tempArr addObject:groupModel];
            }
            for (ChatGroupInfoModel *model in tempArr) {
                if ([model.flagIndex isEqualToString:@"1"]) {
                    [chatGroupArr addObject:model];
                }
            }
            for (ChatGroupInfoModel *model in tempArr) {
                if (![model.flagIndex isEqualToString:@"1"]) {
                    [chatGroupArr addObject:model];
                }
            }
            [db close];
        }else{

        }
    }
    return chatGroupArr;
}
//修改数据
+(void)changeData:(ChatGroupInfoModel *)model{
    NSString *doc = [NSSearchPathForDirectoriesInDomains(NSDocumentDirectory, NSUserDomainMask, YES) objectAtIndex:0];
//    NSLog(@"--%@",doc);
    NSString *pathStr = [NSString stringWithFormat:@"%@GroupInfo.sqlite",[YLUserInfo shareUserInfo].ID];
    NSString *path = [doc stringByAppendingPathComponent:pathStr];
    FMDatabase *db = [FMDatabase databaseWithPath:path];
    if ([db open]) {
        NSString *sql = @"select *from GroupInfo";
        FMResultSet *rs = [db executeQuery:sql];
        while ([rs next]) {
            NSString *orderId = [rs stringForColumn:@"orderId"];
            if ([orderId isEqualToString:model.orderId]) {
                NSString *sql = @"UPDATE GroupInfo SET flagIndex = ? WHERE  orderId = ?";
                BOOL res = [db executeUpdate:sql, @"0", orderId];
                if (!res) {
                    NSLog(@"数据修改失败");
                } else {
//                    NSLog(@"数据修改成功");
                    model.flagIndex = 0;
                }
            }
        }
        [db close];
    }
}
//删除数据
+(void)deleteSqliteData:(ChatGroupInfoModel *)model{
    NSString *doc = [NSSearchPathForDirectoriesInDomains(NSDocumentDirectory, NSUserDomainMask, YES) objectAtIndex:0];
//    NSLog(@"--%@",doc);
    NSString *pathStr = [NSString stringWithFormat:@"%@GroupInfo.sqlite",[YLUserInfo shareUserInfo].ID];
    NSString *path = [doc stringByAppendingPathComponent:pathStr];
    FMDatabase *db = [FMDatabase databaseWithPath:path];
    if ([db open]) {
        NSString *deleteSql = [NSString stringWithFormat:
                               @"DELETE FROM GroupInfo WHERE orderId = ? "];
        BOOL res = [db executeUpdate:deleteSql,model.orderId];
        if (!res) {
            NSLog(@"error when insert db table");
        } else {
//            NSLog(@"success to insert db table");
        }
        [db close];
    }
}
@end
