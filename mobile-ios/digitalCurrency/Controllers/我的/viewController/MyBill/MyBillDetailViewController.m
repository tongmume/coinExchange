//
//  MyBillDetailViewController.m
//  digitalCurrency
//
//  Created by iDog on 2019/3/7.
//  Copyright © 2019年 BIZZAN. All rights reserved.
//

#import "MyBillDetailViewController.h"
#import "ChatTableViewCell.h"
#import "MineNetManager.h"
#import "ZYInputAlertView.h"
#import "UITextView+Placeholder.h"

@interface MyBillDetailViewController ()<UITableViewDelegate,UITableViewDataSource>
@property (weak, nonatomic) IBOutlet UILabel *payStatus;//状态，出售或购买
@property (weak, nonatomic) IBOutlet UILabel *payNum;//订单的比特币
@property (weak, nonatomic) IBOutlet UILabel *priceLabel;//比特币价格
@property (weak, nonatomic) IBOutlet UIButton *tipButton;//提醒按钮，付款，投诉
@property (weak, nonatomic) IBOutlet UIButton *cancelButton;//取消按钮
@property (weak, nonatomic) IBOutlet NSLayoutConstraint *cancelBtnWidth;//取消按钮宽度
@property (weak, nonatomic) IBOutlet NSLayoutConstraint *cancelBtnLeftLength;//取消按钮左边宽度
@property (weak, nonatomic) IBOutlet UILabel *tipLabel;//提醒标注
@property (weak, nonatomic) IBOutlet UITableView *tableView;
@property (weak, nonatomic) IBOutlet NSLayoutConstraint *bottomViewHeight;
@property (weak, nonatomic) IBOutlet UITextView *sendTextView;//发送输入框
@property (weak, nonatomic) IBOutlet UIButton *sendButton;//发送按钮
@property(nonatomic,assign)NSInteger btnIndex; //1付款 2取消 3提醒 4放款 5投诉
@property(nonatomic,strong)NSMutableArray *chatArr;
@end

@implementation MyBillDetailViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    self.title = @"订单详情";
    self.tableView.delegate = self;
    self.tableView.dataSource =self;
    [self.tableView registerClass:[ChatTableViewCell class] forCellReuseIdentifier:NSStringFromClass([ChatTableViewCell class])];
    self.sendTextView.clipsToBounds = YES;
    self.sendTextView.layer.borderWidth = 1;
    self.sendTextView.layer.borderColor = [UIColor lightGrayColor].CGColor;
    self.sendTextView.layer.cornerRadius = 10;
    self.sendTextView.placeholder = @"请输入发送内容";
    _chatArr = [[NSMutableArray alloc] init];

    for (int i= 0; i<3; i++) {
        ChatModel *model = [ChatModel new];
        if (i== 0) {
            model.content = @"您好，在吗？";
            model.isRight = @"1";
            [_chatArr addObject:model];
        }else if (i==1){
            model.content = @"在的，怎么了";
            model.isRight = @"0";
            [_chatArr addObject:model];
        }else{
            model.content = @"想和你了解一下货币";
            model.isRight = @"1";
            [_chatArr addObject:model];
        }
    }
    [self arrageData];
}
//提醒，付款等的点击事件
- (IBAction)tipBtnClick:(UIButton *)sender {
    //1付款 2取消 3提醒 4放款 5投诉
    if (_btnIndex == 1 || _btnIndex == 2 || _btnIndex == 4) {
        //1付款 2取消 4放款
        NSString *urlStr = @"";
        if (_btnIndex == 1) {
            urlStr = @"otc/order/pay";
        }else if (_btnIndex == 2){
            urlStr = @"otc/order/cancel";
        }else{
           urlStr = @"otc/order/release";
        }
        [EasyShowLodingView showLodingText:@"加载中..."];
        [MineNetManager myBillDetailTipForUrlString:urlStr withOrderSn:self.infoModel.orderSn CompleteHandle:^(id resPonseObj, int code) {
             [EasyShowLodingView hidenLoding];
            if (code) {
                if ([resPonseObj[@"code"] integerValue]==0) {
                    //获取数据成功
                    [self.view makeToast:resPonseObj[MESSAGE] duration:1.5 position:CSToastPositionCenter];
                }else if ([resPonseObj[@"code"] integerValue] == 3000 ||[resPonseObj[@"code"] integerValue] == 4000 ){
                    [ShowLoGinVC showLoginVc:self withTipMessage:resPonseObj[MESSAGE]];
                }else{
                    [self.view makeToast:resPonseObj[MESSAGE] duration:1.5 position:CSToastPositionCenter];
                }
            }else{
                [self.view makeToast:@"网络异常,请检查网络!" duration:1.5 position:CSToastPositionCenter];
            }
        }];
    }else if (_btnIndex == 5){
        //投诉
        [self complaintsTextField];
    }
}
//MARK:--投诉输入框
-(void)complaintsTextField{
    //提示框添加文本输入框
     __weak typeof(self) weakSelf = self;
    ZYInputAlertView *alertView = [ZYInputAlertView alertView];
    [alertView.confirmBtn setTitle:@"投诉" forState:UIControlStateNormal];
    alertView.placeholder = @"点击输入投诉原因···";
    [alertView confirmBtnClickBlock:^(NSString *inputString) {
        [weakSelf orderAppeal:inputString];
    }];
    [alertView show];
}
//MARK--订单投诉事件
-(void)orderAppeal:(NSString *)appealStr{
    if ([appealStr isEqualToString:@""]) {
        [self.view makeToast:@"投诉原因不能为空" duration:1.5 position:CSToastPositionCenter];
        return;
    }
    [EasyShowLodingView showLodingText:@"加载中..."];
    [MineNetManager myBillDetailComplaintsForRemark:appealStr withOrderSn:self.infoModel.orderSn CompleteHandle:^(id resPonseObj, int code) {
        [EasyShowLodingView hidenLoding];
        if (code) {
            if ([resPonseObj[@"code"] integerValue]==0) {
                //获取数据成功
                [self.view makeToast:resPonseObj[MESSAGE] duration:1.5 position:CSToastPositionCenter];
                self.tipButton.userInteractionEnabled = NO;
                self.tipButton.alpha = 0.4;
            }else if ([resPonseObj[@"code"] integerValue] == 3000 ||[resPonseObj[@"code"] integerValue] == 4000 ){
                [ShowLoGinVC showLoginVc:self withTipMessage:resPonseObj[MESSAGE]];
            }else{
                [self.view makeToast:resPonseObj[MESSAGE] duration:1.5 position:CSToastPositionCenter];
            }
        }else{
            [self.view makeToast:@"网络异常,请检查网络!" duration:1.5 position:CSToastPositionCenter];
        }
    }];
}
-(NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section{
    return _chatArr.count;
}
-(NSInteger)numberOfSectionsInTableView:(UITableView *)tableView{
    return 1;
}
- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath
{
    ChatTableViewCell *cell = [tableView dequeueReusableCellWithIdentifier:NSStringFromClass([ChatTableViewCell class]) forIndexPath:indexPath];
    ChatModel *model = _chatArr[indexPath.row];
    cell.selectionStyle = UITableViewCellSelectionStyleNone;
    [cell refreshCell:model];

    return cell;
}
-(CGFloat)tableView:(UITableView *)tableView heightForRowAtIndexPath:(NSIndexPath *)indexPath{
    if ([self cellForHeight:_chatArr[indexPath.row]] <= 30) {
        return 60;
    }else if ([self cellForHeight:_chatArr[indexPath.row]] <= 50){
        return 70;
    }else{
        NSLog(@"%f",[self cellForHeight:_chatArr[indexPath.row]]);
       return [self cellForHeight:_chatArr[indexPath.row]]+20;

    }
}
-(CGFloat)cellForHeight:(ChatModel*)model{
    // 首先计算文本宽度和高度
    CGRect rec = [model.content boundingRectWithSize:CGSizeMake(200, CGFLOAT_MAX) options:NSStringDrawingUsesLineFragmentOrigin attributes:@{NSFontAttributeName : [UIFont systemFontOfSize:17]} context:nil];
    return rec.size.height;
}
//MARK:--整理信息
-(void)arrageData{
    NSString *status = @"";
    if ([self.infoModel.type isEqualToString:@"0"]) {
        //买入
        status = @"购买";
    }else{
        status = @"出售";
    }
    self.payStatus.text = status;
    self.payNum.text = [NSString stringWithFormat:@"%@%@",self.infoModel.amount,self.infoModel.unit];
    self.priceLabel.text = [NSString stringWithFormat:@"%@%@",self.infoModel.money,@"CNY"];
    //1付款 2取消 3提醒 4放款 5投诉
    if (_index == 0) {
        //未付款
        if ([self.infoModel.type isEqualToString:@"0"]) {
            //买入
            [self.tipButton setTitle:@"付款完成" forState:UIControlStateNormal];
            self.btnIndex = 1;
            self.tipLabel.text = @"注：支付完成之后可提醒对方放行";
            self.cancelButton.hidden = NO;
            self.cancelBtnWidth.constant = 80;
            self.cancelBtnLeftLength.constant = 6;
        }else{
            [self.tipButton setTitle:@"取消" forState:UIControlStateNormal];
            self.tipButton.hidden = YES;
            self.tipLabel.text = @"注：卖家可以取消此订单";
            self.cancelButton.hidden = YES;
            self.cancelBtnWidth.constant = 0;
            self.cancelBtnLeftLength.constant = 0;
        }
        self.tipButton.userInteractionEnabled = YES;
    }else if (_index == 1){
        //已付款
        if ([self.infoModel.type isEqualToString:@"0"]) {
            //买入
            [self.tipButton setTitle:@"订单申诉" forState:UIControlStateNormal];
            self.tipButton.userInteractionEnabled = YES;
             self.btnIndex = 5;
            self.tipLabel.text = @"注：买家已付款，等待卖家放行！";
            self.cancelButton.hidden = NO;
            self.cancelBtnWidth.constant = 80;
            self.cancelBtnLeftLength.constant = 6;
        }else{
            [self.tipButton setTitle:@"放款" forState:UIControlStateNormal];
            self.btnIndex = 4;
            self.tipLabel.text = @"注：确定无误后可以放款";
            self.cancelButton.hidden = YES;
            self.cancelBtnWidth.constant = 0;
            self.cancelBtnLeftLength.constant = 0;
        }
        self.tipButton.userInteractionEnabled = YES;
    }else if (_index == 2){
        //已完成
        [self.tipButton setTitle:@"投诉" forState:UIControlStateNormal];
        self.btnIndex = 5;
        self.tipButton.userInteractionEnabled = YES;
        self.tipLabel.text = @"注：如果不满意可以进行投诉";
        self.cancelButton.hidden = YES;
        self.cancelBtnWidth.constant = 0;
        self.cancelBtnLeftLength.constant = 0;
    }else if (_index == 3){
        //已取消
        [self.tipButton setTitle:@"已取消" forState:UIControlStateNormal];
        self.tipButton.hidden = YES;
        self.tipLabel.text = @"注：订单处于取消状态，可以聊天沟通";
        self.cancelButton.hidden = YES;
        self.cancelBtnWidth.constant = 0;
        self.cancelBtnLeftLength.constant = 0;
    }else{
        //申诉中
        [self.tipButton setTitle:@"申诉中" forState:UIControlStateNormal];
        self.tipButton.hidden = YES;
        self.tipLabel.text = @"注：订单正在申诉中，需要工作人员进行处理";
        self.cancelButton.hidden = YES;
        self.cancelBtnWidth.constant = 0;
        self.cancelBtnLeftLength.constant = 0;
    }
}
//MARK:--取消按钮的点击事件
- (IBAction)cancelBtnClick:(UIButton *)sender {
    [EasyShowLodingView showLodingText:@"加载中..."];
    [MineNetManager myBillDetailTipForUrlString:@"otc/order/pay" withOrderSn:self.infoModel.orderSn CompleteHandle:^(id resPonseObj, int code) {
        [EasyShowLodingView hidenLoding];
        if (code) {
            if ([resPonseObj[@"code"] integerValue]==0) {
                //获取数据成功
                [self.view makeToast:resPonseObj[MESSAGE] duration:1.5 position:CSToastPositionCenter];
                self.cancelButton.userInteractionEnabled = NO;
                self.cancelButton.alpha = 0.4;
            }else if ([resPonseObj[@"code"] integerValue] == 3000 ||[resPonseObj[@"code"] integerValue] == 4000 ){
                [ShowLoGinVC showLoginVc:self withTipMessage:resPonseObj[MESSAGE]];
            }else{
                [self.view makeToast:resPonseObj[MESSAGE] duration:1.5 position:CSToastPositionCenter];
            }
        }else{
            [self.view makeToast:@"网络异常,请检查网络!" duration:1.5 position:CSToastPositionCenter];
        }
    }];
}
//MARK:--发送按钮的点击事件
- (IBAction)sendBtnClick:(UIButton *)sender {
    NSLog(@"发送消息");

    if ([self.sendTextView.text isEqualToString:@""]) {
        [self.view makeToast:@"不得发送空的内容" duration:1.5 position:CSToastPositionCenter];
        return;
    }
    //发送消息内容
    ChatModel *model = [[ChatModel alloc] init];
    model.content = self.sendTextView.text;
    model.isRight = @"1";
    [_chatArr addObject:model];
    [self.tableView reloadData];
    if (self.chatArr.count != 0) {
        [self.tableView scrollToRowAtIndexPath:[NSIndexPath indexPathForRow:self.self.chatArr.count - 1 inSection:0] atScrollPosition:UITableViewScrollPositionBottom animated:YES];
    }
    self.sendTextView.text = @"";
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
