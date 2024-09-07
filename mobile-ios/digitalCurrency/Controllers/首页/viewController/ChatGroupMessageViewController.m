//
//  ChatGroupMessageViewController.m
//  digitalCurrency
//
//  Created by iDog on 2019/4/16.
//  Copyright © 2019年 BIZZAN. All rights reserved.
//

#import "ChatGroupMessageViewController.h"
#import "ChatGroupMessageTableViewCell.h"
#import "FMDatabase.h"
#import "FMDatabaseQueue.h"
#import "ChatGroupInfoModel.h"
#import "MyBillChatViewController.h"
#import "ChatGroupFMDBTool.h"
#import <AVFoundation/AVFoundation.h>

@interface ChatGroupMessageViewController ()<UITableViewDelegate,UITableViewDataSource,chatSocketDelegate>
@property (weak, nonatomic) IBOutlet UITableView *tableView;
@property (weak, nonatomic) IBOutlet NSLayoutConstraint *bottomViewHeight;
@property(nonatomic,strong)NSMutableArray *groupInfoArr;
@property(nonatomic,strong)ChatGroupInfoModel *groupInfoModel;
@end

@implementation ChatGroupMessageViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    self.view.backgroundColor = mainColor;
    self.title = LocalizationKey(@"message");
    self.bottomViewHeight.constant = SafeAreaBottomHeight;
    self.groupInfoArr = [[NSMutableArray alloc] init];
    self.tableView.delegate = self;
    self.tableView.dataSource = self;
    self.tableView.rowHeight = 70;
    [self.tableView registerNib:[UINib nibWithNibName:@"ChatGroupMessageTableViewCell" bundle:nil] forCellReuseIdentifier:NSStringFromClass([ChatGroupMessageTableViewCell class])];
    self.bottomViewHeight.constant = SafeAreaBottomHeight;
    LYEmptyView *emptyView=[LYEmptyView emptyViewWithImageStr:@"emptyData" titleStr:LocalizationKey(@"noChatGroupInfo")];
    self.tableView.ly_emptyView = emptyView;

    // Do any additional setup after loading the view from its nib.
}
-(void)viewWillAppear:(BOOL)animated{
    [super viewWillAppear:YES];
    self.groupInfoArr = [ChatGroupFMDBTool getChatGroupDataArr];
    [self.tableView reloadData];

    if ([YLUserInfo isLogIn]) {
        NSDictionary*dic=[NSDictionary dictionaryWithObjectsAndKeys:[YLUserInfo shareUserInfo].ID, @"uid",nil];
        [[ChatSocketManager share] ChatsendMsgWithLength:SOCKETREQUEST_LENGTH withsequenceId:0 withcmd:SUBSCRIBE_GROUP_CHAT withVersion:COMMANDS_VERSION withRequestId: 0 withbody:dic];
        [ChatSocketManager share].delegate = self;
    }
}

#pragma mark - SocketDelegate Delegate
- (void)ChatdelegateSocket:(GCDAsyncSocket *)sock didReadData:(NSData *)data withTag:(long)tag{

    NSData *endData = [data subdataWithRange:NSMakeRange(SOCKETRESPONSE_LENGTH, data.length -SOCKETRESPONSE_LENGTH)];
    NSString *endStr= [[NSString alloc] initWithData:endData encoding:NSUTF8StringEncoding];
    NSData *cmdData = [data subdataWithRange:NSMakeRange(12,2)];
    uint16_t cmd=[SocketUtils uint16FromBytes:cmdData];

    if (cmd==SUBSCRIBE_GROUP_CHAT) {
        NSLog(@"订阅聊天成功");
    }
    else if (cmd==UNSUBSCRIBE_GROUP_CHAT) {
        NSLog(@"取消订阅成功");
    }
    else if (cmd==SEND_CHAT) {//发送消息
        if (endStr) {
//            NSLog(@"发送消息--%@-收到的回复命令--%d",endStr,cmd);
        }
    }
    else if (cmd==PUSH_GROUP_CHAT)//收到消息
    {
        if (endStr) {
            NSDictionary *dic =[SocketUtils dictionaryWithJsonString:endStr];
            NSLog(@"接受消息--收到的回复-%@--%d--",dic,cmd);
            _groupInfoModel = [ChatGroupInfoModel mj_objectWithKeyValues:dic];
            //存入数据库
            [ChatGroupFMDBTool createTable:_groupInfoModel withIndex:1];
            [self setSound];
             self.groupInfoArr = [ChatGroupFMDBTool getChatGroupDataArr];
            [self.tableView reloadData];
        }

    }else{
//        NSLog(@"消息-%@--%d",endStr,cmd);
    }
}
//MARK:--设置音效
-(void)setSound{
    NSURL *url = [[NSBundle mainBundle]URLForResource:@"m_click" withExtension:@"wav"];
    //对该音效标记SoundID
    SystemSoundID soundID1 = 0;
    //加载该音效
    AudioServicesCreateSystemSoundID((__bridge CFURLRef)(url), &soundID1);
    //播放该音效
    AudioServicesPlaySystemSound(soundID1);
}
-(NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section{
    return self.groupInfoArr.count;
}
-(NSInteger)numberOfSectionsInTableView:(UITableView *)tableView{
    return 1;
}

- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath
{
    ChatGroupMessageTableViewCell *cell = [tableView dequeueReusableCellWithIdentifier:NSStringFromClass([ChatGroupMessageTableViewCell class]) forIndexPath:indexPath];
    cell.selectionStyle = UITableViewCellSelectionStyleNone;
    ChatGroupInfoModel *model = self.groupInfoArr[indexPath.row];
    [cell.headImage sd_setImageWithURL:[NSURL URLWithString:model.avatar] placeholderImage:[UIImage imageNamed:@"defaultImage"]];
    cell.userName.text = model.nameFrom;
    if ([model.flagIndex isEqualToString:@"1"]) {
        cell.tipImage.hidden = NO;
    }else{
      cell.tipImage.hidden = YES;
    }
    cell.content.text = model.content;
    cell.timeData.text = [self dateTimeDifferenceWithStartTime:model.sendTimeStr endTime:[ToolUtil getCurrentDateString]];

    return cell;
}
//MARK:--发送时间的计算，5分钟以内标记刚刚，今天之内显示时间，今天之后显示日期
-(NSString *)dateTimeDifferenceWithStartTime:(NSString *)startTime endTime:(NSString *)endTime{
    NSArray * startArr = [startTime componentsSeparatedByString:@" "];
    NSArray * endArr = [endTime componentsSeparatedByString:@" "];
    NSDateFormatter *date = [[NSDateFormatter alloc]init];
    [date setDateFormat:@"yyyy-MM-dd HH:mm:ss"];
    [date setTimeZone:ChangeLanguage.timeZone];
    NSDate *startD =[date dateFromString:startTime];
    NSDate *endD = [date dateFromString:endTime];
    NSTimeInterval start = [startD timeIntervalSince1970]*1;
    NSTimeInterval end = [endD timeIntervalSince1970]*1;
    NSTimeInterval value = end - start;
//    NSLog(@"--%f",value);
    NSString  *str;
    if (value <= 300) {
        //五分钟以内
        str = LocalizationKey(@"justTime");
    }else {
        if ([startArr[0] isEqualToString:endArr[0]]) {
            //今天时间
            str = startArr[1];
        }else{
            str = startArr[0];
        }
    }
    return str;
}
// UITableViewDataSource协议中定义的方法。该方法的返回值决定某行是否可编辑
- (BOOL) tableView:(UITableView *)tableView canEditRowAtIndexPath:(NSIndexPath *)indexPath{
    return YES;
}
-(void)tableView:(UITableView *)tableView commitEditingStyle:(UITableViewCellEditingStyle)editingStyle forRowAtIndexPath:(NSIndexPath *)indexPath{

    ChatGroupInfoModel *model = self.groupInfoArr[indexPath.row];
    [ChatGroupFMDBTool deleteSqliteData:model];
    //tableView刷新方式   设置tableView带动画效果 删除数据
    [self.groupInfoArr removeObjectAtIndex:indexPath.row];
    [tableView deleteRowsAtIndexPaths:[NSArray arrayWithObject:indexPath]  withRowAnimation:UITableViewRowAnimationFade];
    //取消编辑状态
    [tableView setEditing:NO animated:YES];
}

-(void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath{
    if ([YLUserInfo isLogIn]) {
        ChatGroupInfoModel *model = self.groupInfoArr[indexPath.row];
        [ChatGroupFMDBTool changeData:model];
        MyBillChatViewController *chatVC = [[MyBillChatViewController alloc] init];
        chatVC.hidesBottomBarWhenPushed = YES;
        chatVC.clickIndex = 1;
        chatVC.groupModel= model;
        [self.navigationController pushViewController:chatVC animated:YES];
    }else{
        [self showLoginViewController];
    }
}
-(void)viewWillDisappear:(BOOL)animated{
    [super viewWillDisappear:YES];
//    NSDictionary*dic=[NSDictionary dictionaryWithObjectsAndKeys:[YLUserInfo shareUserInfo].ID, @"uid",nil];
//    [[ChatSocketManager share] ChatsendMsgWithLength:SOCKETREQUEST_LENGTH withsequenceId:0 withcmd:UNSUBSCRIBE_GROUP_CHAT withVersion:COMMANDS_VERSION withRequestId: 0 withbody:dic];
    [ChatSocketManager share].delegate = nil;

}
- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

/*
#pragma mark - Navigation

// In a storyboard-based application, you will often want to do a little preparation before navigation
- (void)prepareForSegue:(UIStoryboardSegue *)segue sender:(id)sender {
    // Get the new view controller using [segue destinationViewController].
    // Pass the selected object to the new view controller.
}
*/

@end
