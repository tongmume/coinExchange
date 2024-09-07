//
//  MyBillDetail1ViewController.m
//  digitalCurrency
//
//  Created by iDog on 2019/4/3.
//  Copyright © 2019年 BIZZAN. All rights reserved.
//

#import "MyBillDetail1ViewController.h"
#import "MineNetManager.h"
#import "MyBillDetailInfoModel.h"
#import "Section1TableViewCell.h"
#import "Section2TableViewCell.h"
#import "Section3TableViewCell.h"
#import "ZYInputAlertView.h"
#import "MyBillChatViewController.h"
#import "QRCodeView.h"
#import "BankcardTableViewCell.h"


@interface MyBillDetail1ViewController ()<UITableViewDelegate,UITableViewDataSource>

{
    QRCodeView *_QRView;
}

@property (weak, nonatomic) IBOutlet NSLayoutConstraint *bottomViewHeight;
@property(nonatomic,strong)UITableView *tableView;
@property(nonatomic,strong)MyBillDetailInfoModel *detailModel;
@property(nonatomic,strong)NSMutableArray *section1Arr;
@property(nonatomic,strong)NSMutableArray *section2Arr;
//
@property(nonatomic,strong)NSMutableArray *section2LeftArr;
@property(nonatomic,strong)NSMutableArray *section2ImageArr;

@property(nonatomic,assign)NSInteger btnIndex; //1付款 2取消 3提醒 4放款 5投诉
@property (weak, nonatomic) IBOutlet NSLayoutConstraint *leftButtonWidth;//第一个Button的宽度
@property (weak, nonatomic) IBOutlet NSLayoutConstraint *leftButtonHeight;//第一个Button的高度
@property (weak, nonatomic) IBOutlet UIButton *certainButton;
@property (weak, nonatomic) IBOutlet UIButton *cancelButton;
@property(nonatomic,copy)NSString *timeStr;
@property(nonatomic,assign)int secondTime;
@property(nonatomic,assign)CGFloat tableBottomHeight;//tableView距离底部的高度

@property (nonatomic,assign)BOOL isshowBank;
@end

@implementation MyBillDetail1ViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    self.view.backgroundColor = mainColor;
    self.title = [[ChangeLanguage bundle] localizedStringForKey:@"orderDetail" value:nil table:@"English"];
    self.edgesForExtendedLayout = UIRectEdgeNone;
    self.section1Arr = [[NSMutableArray alloc] init];
    self.section2Arr = [[NSMutableArray alloc]init];
    self.certainButton.hidden = YES;
    self.cancelButton.hidden = YES;
//    self.bottomViewHeight.constant = SafeAreaBottomHeight;
    [self getData];
}
//MARK:---和我联系
-(void)RighttouchEvent{
    MyBillChatViewController *chatVC = [[MyBillChatViewController alloc] init];
    chatVC.hidesBottomBarWhenPushed = YES;
    chatVC.model=_detailModel;
    chatVC.avatar = self.avatar;
    [self.navigationController pushViewController:chatVC animated:YES];
}
-(UITableView *)tableView{
    if (!_tableView) {
        _tableView = [[UITableView alloc] initWithFrame:CGRectMake(0, 0, kWindowW, kWindowH-SafeAreaBottomHeight-NEW_NavHeight-self.tableBottomHeight) style:UITableViewStyleGrouped];
        _tableView.backgroundColor = mainColor;
        _tableView.separatorStyle = UITableViewCellSelectionStyleNone;
        _tableView.delegate = self;
        _tableView.dataSource = self;
        _tableView.estimatedSectionHeaderHeight = 0;
        _tableView.estimatedSectionFooterHeight = 0;
        _tableView.estimatedRowHeight = 68;
        [self.tableView registerNib:[UINib nibWithNibName:@"Section1TableViewCell" bundle:nil] forCellReuseIdentifier:NSStringFromClass([Section1TableViewCell class])];
        [self.tableView registerNib:[UINib nibWithNibName:@"Section2TableViewCell" bundle:nil] forCellReuseIdentifier:NSStringFromClass([Section2TableViewCell class])];
        [self.tableView registerNib:[UINib nibWithNibName:@"Section3TableViewCell" bundle:nil] forCellReuseIdentifier:NSStringFromClass([Section3TableViewCell class])];
        [self.tableView registerNib:[UINib nibWithNibName:@"BankcardTableViewCell" bundle:nil] forCellReuseIdentifier:NSStringFromClass([BankcardTableViewCell class])];

    }
    return _tableView;
}
//MARK:--获取数据
-(void)getData{
    [EasyShowLodingView showLodingText:[[ChangeLanguage bundle] localizedStringForKey:@"loading" value:nil table:@"English"]];
    [MineNetManager myBillDetailForId:self.orderId CompleteHandle:^(id resPonseObj, int code) {
        NSLog(@"resPonseObj --- %@",resPonseObj);
        [EasyShowLodingView hidenLoding];
        if (code) {
            if ([resPonseObj[@"code"] integerValue]==0) {
                //获取数据成功
                self.detailModel = [MyBillDetailInfoModel mj_objectWithKeyValues:resPonseObj[@"data"]];
                [self arrangeData];

            }else if ([resPonseObj[@"code"] integerValue] == 3000 ||[resPonseObj[@"code"] integerValue] == 4000 ){
                //[ShowLoGinVC showLoginVc:self withTipMessage:resPonseObj[MESSAGE]];
                [YLUserInfo logout];
            }else{
                [self.view makeToast:resPonseObj[MESSAGE] duration:1.5 position:CSToastPositionCenter];
            }
        }else{
            [self.view makeToast:[[ChangeLanguage bundle] localizedStringForKey:@"noNetworkStatus" value:nil table:@"English"] duration:1.5 position:CSToastPositionCenter];
        }
    }];
}
//MARK:--整理数据
-(void)arrangeData{
    [self.section1Arr addObject:[NSString stringWithFormat:@"%@ %@ %@",[[ChangeLanguage bundle] localizedStringForKey:@"orderDetailTip1" value:nil table:@"English"],_detailModel.otherSide,[[ChangeLanguage bundle] localizedStringForKey:@"orderDetailTip2" value:nil table:@"English"]]];
    [self.section1Arr addObject:_detailModel.orderSn];
    [self.section1Arr addObject:[NSString stringWithFormat:@"%@CNY",_detailModel.price]];
    [self.section1Arr addObject:[NSString stringWithFormat:@"%@%@",_detailModel.amount,_detailModel.unit]];
    [self.section1Arr addObject:[NSString stringWithFormat:@"%@CNY",_detailModel.money]];
     [self.section1Arr addObject:_detailModel.createTime];
    if ([_detailModel.type isEqualToString:@"0"]) {
        //买入 填写支付信息
        self.section2LeftArr = [[NSMutableArray alloc] init];
        self.section2ImageArr = [[NSMutableArray alloc] init];
        if (_detailModel.payInfo.alipay == nil) {

        }else{
            [self.section2Arr addObject:_detailModel.payInfo.alipay.aliNo];
            [self.section2LeftArr addObject:[[ChangeLanguage bundle] localizedStringForKey:@"alipayAccount" value:nil table:@"English"]];
            [self.section2ImageArr addObject:@"zhifubaoImage"];
        }
        if (_detailModel.payInfo.wechatPay == nil) {

        }else{
            [self.section2Arr addObject:_detailModel.payInfo.wechatPay.wechat];
            [self.section2LeftArr addObject:[[ChangeLanguage bundle] localizedStringForKey:@"WeChatAccount" value:nil table:@"English"]];
             [self.section2ImageArr addObject:@"wechatImage"];
        }
        if (_detailModel.payInfo.bankInfo == nil) {

        }else{
            [self.section2Arr addObject:_detailModel.payInfo.bankInfo.cardNo];
            [self.section2LeftArr addObject:[[ChangeLanguage bundle] localizedStringForKey:@"bankCardAccount" value:nil table:@"English"]];
            [self.section2ImageArr addObject:@"yinlianImage"];
        }
    }else{
        //卖家不需要展示账户信息
    }
    //状态码
    if ([_detailModel.status isEqualToString:@"1"]) {
        //未付款
        if ([_detailModel.type isEqualToString:@"0"]) {
            //买入
            [self.certainButton setTitle:[[ChangeLanguage bundle] localizedStringForKey:@"payment" value:nil table:@"English"] forState:UIControlStateNormal];
            [self.cancelButton setTitle:[[ChangeLanguage bundle] localizedStringForKey:@"cancelOrder" value:nil table:@"English"] forState:UIControlStateNormal];
            self.leftButtonWidth.constant = kWindowW/2;
            self.leftButtonHeight.constant = 50;
            self.tableBottomHeight = 50;
            self.certainButton.hidden = NO;
            self.cancelButton.hidden = NO;
            self.btnIndex = 1;
        }else{
            self.leftButtonHeight.constant = 0;
            self.tableBottomHeight = 0;
        }
    }else if ([_detailModel.status isEqualToString:@"2"]){
        if ([_detailModel.type isEqualToString:@"0"]) {
            //买入
            [self.certainButton setTitle:[[ChangeLanguage bundle] localizedStringForKey:@"orderComplaint" value:nil table:@"English"] forState:UIControlStateNormal];
            self.leftButtonWidth.constant = kWindowW;
            self.leftButtonHeight.constant = 50;
            self.tableBottomHeight = 50;
            self.certainButton.hidden = NO;
            self.cancelButton.hidden = NO;
            self.btnIndex = 5;
        }else{
            [self.certainButton setTitle:[[ChangeLanguage bundle] localizedStringForKey:@"orderComplaint" value:nil table:@"English"] forState:UIControlStateNormal];
            [self.cancelButton setTitle:[[ChangeLanguage bundle] localizedStringForKey:@"putCoin" value:nil table:@"English"] forState:UIControlStateNormal];
            self.leftButtonWidth.constant = kWindowW/2;
            self.leftButtonHeight.constant = 50;
            self.tableBottomHeight = 50;
            self.certainButton.hidden = NO;
            self.cancelButton.hidden = NO;
            self.btnIndex = 4;
        }
    }else if ([_detailModel.status isEqualToString:@"3"]){
        self.leftButtonHeight.constant = 0;
        self.tableBottomHeight = 0;
    }else if ([_detailModel.status isEqualToString:@"0"]){
        self.leftButtonHeight.constant = 0;
        self.tableBottomHeight = 0;
    }else if ([_detailModel.status isEqualToString:@"4"]){
        self.leftButtonHeight.constant = 0;
        self.tableBottomHeight = 0;
    }

    if (self.flagIndex == 1) {
    }else{
        [self rightBarItemWithTitle:[[ChangeLanguage bundle] localizedStringForKey:@"contactMe" value:nil table:@"English"]];
    }

    if ([_detailModel.status isEqualToString:@"1"]) {
        //未付款
        if ([_detailModel.type isEqualToString:@"0"]) {
            //买入 需要加上倒计时
            //获得当前时间：
            NSDate *now=[NSDate date];
            //实例化一个NSDateFormatter对象
            NSDateFormatter *dateFormatter = [[NSDateFormatter alloc] init];
            //设定时间格式,这里可以设置成自己需要的格式
            [dateFormatter setDateFormat:@"yyyy-MM-dd HH:mm:ss"];
            NSString *nowTime = [dateFormatter stringFromDate:now];
            self.timeStr = [self dateTimeDifferenceWithStartTime:_detailModel.createTime endTime:nowTime];
            [self dateTime];
        }
    }
    [self.view addSubview:[self tableView]];
}
//MARK:--
-(void)dateTime{
    __block int timeout= [_detailModel.timeLimit intValue]*60 - self.secondTime; //倒计时时间
    dispatch_queue_t queue = dispatch_get_global_queue(DISPATCH_QUEUE_PRIORITY_DEFAULT, 0);
    dispatch_source_t _timer = dispatch_source_create(DISPATCH_SOURCE_TYPE_TIMER, 0, 0,queue);
    dispatch_source_set_timer(_timer,dispatch_walltime(NULL, 0),1.0*NSEC_PER_SEC, 0); //每秒执行
    dispatch_source_set_event_handler(_timer, ^{
        if(timeout<=0){ //倒计时结束，关闭
            dispatch_source_cancel(_timer);
            dispatch_async(dispatch_get_main_queue(), ^{
                //设置界面的按钮显示 根据自己需求
                self.timeStr = LocalizationKey(@"payOutTime");
                [self.tableView reloadData];
            });
        }else{
            int minutes = timeout/60%60;
            int seconds = timeout%60;
            int house = timeout/(3600)%3600;
            self.timeStr = [NSString stringWithFormat:@"%.2d:%.2d:%.2d",house,minutes, seconds];
            dispatch_async(dispatch_get_main_queue(), ^{
                [self.tableView reloadData];
            });
            timeout--;
        }
    });
    dispatch_resume(_timer);

}
//MARK:--付款倒计时
-(NSString *)dateTimeDifferenceWithStartTime:(NSString *)startTime endTime:(NSString *)endTime{
    NSDateFormatter *date = [[NSDateFormatter alloc]init];
    [date setDateFormat:@"yyyy-MM-dd HH:mm:ss"];
    [date setTimeZone:ChangeLanguage.timeZone];
    NSDate *startD =[date dateFromString:startTime];
    NSDate *endD = [date dateFromString:endTime];
    NSTimeInterval start = [startD timeIntervalSince1970]*1;
    NSTimeInterval end = [endD timeIntervalSince1970]*1;
    NSTimeInterval value = end - start;
    int second = (int)value %60;//秒
    int minute = (int)value /60%60;
    int house = (int)value / (3600)%3600;
    self.secondTime = (int)value;
    NSString *str = str = [NSString stringWithFormat:@"%.2d:%.2d:.2%d ",house,minute,second];
    return str;
}

- (IBAction)certainBtnClick:(id)sender {

    if (_btnIndex == 1) {
        //付款
        [self certainOrCancelCoinAlterView:[[ChangeLanguage bundle] localizedStringForKey:@"certainPayTip" value:nil table:@"English"] withIndex:_btnIndex];
    }else if(_btnIndex == 4 || _btnIndex == 5){
        //申诉
        [self complaintsTextField];
    }
}
//MARK:--放币和取消前提示
-(void)certainOrCancelCoinAlterView:(NSString *)titleString withIndex:(NSInteger)index{
    [LEEAlert alert].config
    .LeeHeaderColor(mainColor)
    .LeeAddTitle(^(UILabel *label) {
        label.textColor = RGBOF(0xe6e6e6);
        label.text = [[ChangeLanguage bundle] localizedStringForKey:@"warmPrompt" value:nil table:@"English"];
    })
    .LeeAddContent(^(UILabel *label) {
        label.textColor = RGBOF(0xe6e6e6);
        label.text = titleString;
    })
    .LeeAddAction(^(LEEAction *action) {
        action.title = [[ChangeLanguage bundle] localizedStringForKey:@"ok" value:nil table:@"English"];
        action.titleColor = RGBOF(0xe6e6e6);
        action.borderColor = mainColor;
        action.clickBlock = ^{
            //响应事件
            NSString *urlStr = @"";
            if (index == 1) {
                urlStr = @"otc/order/pay";
            }else if (index == 2){
                urlStr = @"otc/order/cancel";
            }
            [EasyShowLodingView showLodingText:[[ChangeLanguage bundle] localizedStringForKey:@"loading" value:nil table:@"English"]];
            [MineNetManager myBillDetailTipForUrlString:urlStr withOrderSn:self.detailModel.orderSn CompleteHandle:^(id resPonseObj, int code) {
                [EasyShowLodingView hidenLoding];
                if (code) {
                    if ([resPonseObj[@"code"] integerValue]==0) {
                        //获取数据成功
                        if (index == 1) {
                            //付款完成
                            [self payCompletedSendMessage];

                            NSNotification *notification =[NSNotification notificationWithName:@"PayBill" object:nil userInfo:nil];
                            [[NSNotificationCenter defaultCenter] postNotification:notification];
                        }                                                                       [self.view makeToast:resPonseObj[MESSAGE] duration:1.5 position:CSToastPositionCenter];
                        [self.navigationController popViewControllerAnimated:YES];
                    }else if ([resPonseObj[@"code"] integerValue] == 3000 ||[resPonseObj[@"code"] integerValue] == 4000 ){
                        [ShowLoGinVC showLoginVc:self withTipMessage:resPonseObj[MESSAGE]];
                    }else{
                        [self.view makeToast:resPonseObj[MESSAGE] duration:1.5 position:CSToastPositionCenter];
                    }
                }else{
                    [self.view makeToast:[[ChangeLanguage bundle] localizedStringForKey:@"noNetworkStatus" value:nil table:@"English"] duration:1.5 position:CSToastPositionCenter];
                }
            }];
        };

    })
    .LeeAddAction(^(LEEAction *action) {
        action.titleColor = RGBOF(0xe6e6e6);
        action.borderColor = mainColor;
        action.title = [[ChangeLanguage bundle] localizedStringForKey:@"cancel" value:nil table:@"English"];
    })
    .LeeShow();

}
//MARK:--socket发送消息
-(void)payCompletedSendMessage{
    NSDictionary *dic = [NSDictionary dictionaryWithObjectsAndKeys:self.detailModel.orderSn,@"orderId",self.detailModel.hisId,@"uidFrom",self.detailModel.myId,@"uidTo",self.detailModel.otherSide,@"nameTo",[YLUserInfo shareUserInfo].username,@"nameFrom",[NSNumber numberWithInt:0],@"messageType",@"",@"content",[YLUserInfo shareUserInfo].avatar,@"avatar", nil];
    [[ChatSocketManager share] ChatsendMsgWithLength:SOCKETREQUEST_LENGTH withsequenceId:0 withcmd:SEND_CHAT withVersion:COMMANDS_VERSION withRequestId: 0 withbody:dic];
}
- (IBAction)cancelBtnClick:(id)sender {
    if (_btnIndex == 1) {
        //取消订单
        [self certainOrCancelCoinAlterView:[[ChangeLanguage bundle] localizedStringForKey:@"cancelOrderTip" value:nil table:@"English"] withIndex:2];
    }else if (_btnIndex == 4 ){
        //放币
        [self fangKuanAlterView];
    }
}
-(NSInteger)numberOfSectionsInTableView:(UITableView *)tableView{
    return 3;
}
-(NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section{
    if (section == 0) {
        if ([_detailModel.status isEqualToString:@"1"]) {
            //未付款
            if ([_detailModel.type isEqualToString:@"0"]) {
                //买入 需要加上倒计时
                return 2;
            }else{
                return 1;
            }
        }else{
             return 1;
        }
    }else if (section ==1){
        return self.section1Arr.count;
    }else{
        return self.section2Arr.count;
    }
}
-(UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath{
    if (indexPath.section == 0) {
        Section1TableViewCell *cell = [tableView dequeueReusableCellWithIdentifier:NSStringFromClass([Section1TableViewCell class])];
        cell.selectionStyle = UITableViewCellSelectionStyleNone;
        cell.leftLabel.font = [UIFont systemFontOfSize:16];
        cell.rightLabel.font = [UIFont systemFontOfSize:16];
        if (indexPath.row == 0) {
            //订单状态
            cell.rightLabel.textColor = kRGBColor(42, 124, 246);
            cell.leftLabel.text = [[ChangeLanguage bundle] localizedStringForKey:@"orderStatus" value:nil table:@"English"];
            if ([_detailModel.status isEqualToString:@"0"]) {
                cell.rightLabel.text = [[ChangeLanguage bundle] localizedStringForKey:@"cancelled" value:nil table:@"English"];
            }else if ([_detailModel.status isEqualToString:@"1"]){
                cell.rightLabel.text = [[ChangeLanguage bundle] localizedStringForKey:@"unPaying" value:nil table:@"English"];
            }else if ([_detailModel.status isEqualToString:@"2"]){
                cell.rightLabel.text = [[ChangeLanguage bundle] localizedStringForKey:@"paid" value:nil table:@"English"];
            }else if ([_detailModel.status isEqualToString:@"3"]){
                cell.rightLabel.text = [[ChangeLanguage bundle] localizedStringForKey:@"completed" value:nil table:@"English"];
            }else if ([_detailModel.status isEqualToString:@"4"]){
                cell.rightLabel.text = [[ChangeLanguage bundle] localizedStringForKey:@"complaint" value:nil table:@"English"];
            }
        }

        if ([_detailModel.status isEqualToString:@"1"]) {
            //未付款
            if ([_detailModel.type isEqualToString:@"0"]) {
                //买入 需要加上倒计时
                if (indexPath.row == 1) {
                    //订单状态
                    cell.leftLabel.text = LocalizationKey(@"payCountdown");
                    cell.rightLabel.text = self.timeStr;
                    cell.rightLabel.textColor = RedColor;
                }
            }
        }

        return cell;
    }else if (indexPath.section == 1){
        Section1TableViewCell *cell = [tableView dequeueReusableCellWithIdentifier:NSStringFromClass([Section1TableViewCell class])];
        cell.selectionStyle = UITableViewCellSelectionStyleNone;
        cell.leftLabel.font = [UIFont systemFontOfSize:15];
        cell.rightLabel.font = [UIFont systemFontOfSize:15];
        cell.rightLabel.textColor = RGBOF(0x999999);
        cell.leftLabel.text = [self getSection1LeftArr][indexPath.row];
        cell.rightLabel.text = self.section1Arr[indexPath.row];
        return cell;
    }else{
        if ([self.section2ImageArr[indexPath.row] isEqualToString:@"yinlianImage"]) {
            BankcardTableViewCell *cell = [tableView dequeueReusableCellWithIdentifier:NSStringFromClass([BankcardTableViewCell class])];
            cell.banknumlabel.text = self.detailModel.payInfo.bankInfo.cardNo;
            cell.banknamelabel.text = self.detailModel.payInfo.realName;
            cell.bankaddresslabel.text = self.detailModel.payInfo.bankInfo.bank;
            cell.showbut.selected = self.isshowBank;
            cell.bankname.hidden = cell.banknamelabel.hidden = cell.bankaddress.hidden = cell.bankaddresslabel.hidden = !self.isshowBank;
            cell.showBankBlock = ^(BOOL showBank) {
                self.isshowBank = showBank;

                [self.tableView reloadRowsAtIndexPaths:@[indexPath] withRowAnimation:UITableViewRowAnimationNone];
            };

            return cell;

        }else{
            Section2TableViewCell *cell = [tableView dequeueReusableCellWithIdentifier:NSStringFromClass([Section2TableViewCell class])];
            cell.selectionStyle = UITableViewCellSelectionStyleNone;
            cell.centerLabel.text = self.section2LeftArr[indexPath.row];
            cell.leftImage.image = [UIImage imageNamed:self.section2ImageArr[indexPath.row]];
            cell.rightLabel.text = self.section2Arr[indexPath.row];


            if ([self.section2ImageArr[indexPath.row] isEqualToString:@"zhifubaoImage"]) {
                cell.QRCodeBtn.hidden = NO;
                cell.QRCodeBtn.tag = 0;
            }else{
                cell.QRCodeBtn.hidden = NO;
                cell.QRCodeBtn.tag = 1;
            }
            [cell.QRCodeBtn addTarget:self action:@selector(QRCodeBtnClick:) forControlEvents:UIControlEventTouchUpInside];
            return cell;
        }


    }
}
//MARK:--二维码Button的点击事件
-(void)QRCodeBtnClick:(UIButton *)button{
    if (button.tag == 0) {
        //支付宝
        if (_detailModel.payInfo.alipay.qrCodeUrl.length > 0 ) {
            [self qRView:1];
        }else{
           [self.view makeToast:[[ChangeLanguage bundle] localizedStringForKey:@"noAlipayQRImage" value:nil table:@"English"] duration:1.5 position:CSToastPositionCenter];
        }
    }else if (button.tag == 1){
        //微信
        if (_detailModel.payInfo.wechatPay.qrWeCodeUrl.length > 0) {
            [self qRView:2];
        }else{
           [self.view makeToast:[[ChangeLanguage bundle] localizedStringForKey:@"noWeChatQRImage" value:nil table:@"English"] duration:1.5 position:CSToastPositionCenter];
        }
    }
}
//MARK:--点击二维码的提示框
-(void)qRView:(NSInteger)index{
    if (!_QRView) {
        _QRView = [[NSBundle mainBundle] loadNibNamed:@"QRCodeView" owner:nil options:nil].firstObject;
        [_QRView.cancelButton addTarget:self action:@selector(cancelQRCodeBtnClick) forControlEvents:UIControlEventTouchUpInside];
        _QRView.frame=[UIScreen mainScreen].bounds;
    }

    CGAffineTransform translates = CGAffineTransformTranslate(CGAffineTransformIdentity, 0, 0);
    _QRView.bgView.transform = CGAffineTransformTranslate(CGAffineTransformIdentity,0,_QRView.bgView.height);
    [UIView animateWithDuration:0.3 delay:0.1 usingSpringWithDamping:1 initialSpringVelocity:10 options:UIViewAnimationOptionCurveLinear animations:^{
        _QRView.bgView.transform = translates;

    } completion:^(BOOL finished) {

    }];
    NSURL *QRCodeUrl;
    if (index == 1) {
        //支付宝
        QRCodeUrl = [NSURL URLWithString:_detailModel.payInfo.alipay.qrCodeUrl];
        _QRView.titleLabel.text = [[ChangeLanguage bundle] localizedStringForKey:@"alipayQR" value:nil table:@"English"];
    }else{
        //微信
        QRCodeUrl = [NSURL URLWithString:_detailModel.payInfo.wechatPay.qrWeCodeUrl];
        _QRView.titleLabel.text = [[ChangeLanguage bundle] localizedStringForKey:@"weChatQR" value:nil table:@"English"];
    }
    [_QRView.QRCodeImage sd_setImageWithURL:QRCodeUrl];
    if ([[SDImageCache sharedImageCache] imageFromDiskCacheForKey:_detailModel.payInfo.wechatPay.qrWeCodeUrl]) {
        UIImage *image = [[SDImageCache sharedImageCache] imageFromDiskCacheForKey:_detailModel.payInfo.wechatPay.qrWeCodeUrl];

        NSLog(@"width - %f", image.size.width);
        NSLog(@"height - %f", image.size.height);
    }
    [UIApplication.sharedApplication.keyWindow addSubview:_QRView];
}
-(void)cancelQRCodeBtnClick{
    [_QRView removeFromSuperview];
}
-(void)fangKuanAlterView{
    __block UITextField *tf = nil;

    [LEEAlert alert].config
    .LeeHeaderColor(mainColor)
    .LeeAddContent(^(UILabel *label) {
        label.textColor = RGBOF(0xe6e6e6);
        label.text = [[ChangeLanguage bundle] localizedStringForKey:@"moneyPassword" value:nil table:@"English"];

    })
    .LeeAddTextField(^(UITextField *textField){
        textField.placeholder = [[ChangeLanguage bundle] localizedStringForKey:@"inputMoneyPassword" value:nil table:@"English"];
        textField.secureTextEntry = YES;
        textField.backgroundColor = mainColor;
        textField.layer.borderColor = mainColor.CGColor;
        textField.layer.borderWidth = 1;
//        [textField setValue:RGBOF(0x999999) forKeyPath:@"_placeholderLabel.textColor"];
        textField.textColor = RGBOF(0x999999);
        tf = textField;
    })
    .LeeAddAction(^(LEEAction *action) {
        action.titleColor = RGBOF(0xe6e6e6);
        action.borderColor = kRGBColor(18, 22, 28);
        action.title = @"确定";
        action.clickBlock = ^{
            //响应事件
            //得到文本信息
            [self fangKuan:tf.text];
        };
    })
    .LeeAddAction(^(LEEAction *action) {
        action.titleColor = RGBOF(0xe6e6e6);
        action.borderColor = kRGBColor(18, 22, 28);
        action.title = @"取消";
    })
    .LeeShow();

}
//MARK:--放款提交信息
-(void)fangKuan:(NSString *)fangKuan{
    [EasyShowLodingView showLodingText:[[ChangeLanguage bundle] localizedStringForKey:@"loading" value:nil table:@"English"]];
    [MineNetManager myBillDetailTipForOrderSn:self.detailModel.orderSn withJyPassword:fangKuan CompleteHandle:^(id resPonseObj, int code) {
        [EasyShowLodingView hidenLoding];
        if (code) {
            if ([resPonseObj[@"code"] integerValue]==0) {
                //获取数据成功
                [self.view makeToast:resPonseObj[MESSAGE] duration:1.5 position:CSToastPositionCenter];
                [self.navigationController popViewControllerAnimated:YES];
            }else if ([resPonseObj[@"code"] integerValue] == 3000 ||[resPonseObj[@"code"] integerValue] == 4000 ){
               // [ShowLoGinVC showLoginVc:self withTipMessage:resPonseObj[MESSAGE]];
                [YLUserInfo logout];
            }else{
                [self.view makeToast:resPonseObj[MESSAGE] duration:1.5 position:CSToastPositionCenter];
            }
        }else{
            [self.view makeToast:[[ChangeLanguage bundle] localizedStringForKey:@"noNetworkStatus" value:nil table:@"English"] duration:1.5 position:CSToastPositionCenter];
        }
    }];
}
//MARK:--投诉输入框
-(void)complaintsTextField{
    //判断订单时间 30分钟后才能申诉
    NSString *currentTime = [ToolUtil timeToTimeIntervalString:[ToolUtil getCurrentDateString] WithDateFormat:@"yyyy-MM-dd HH:mm:ss"];
    NSString *orderTime = [ToolUtil timeToTimeIntervalString:self.detailModel.payTime WithDateFormat:@"yyyy-MM-dd HH:mm:ss"];
    if (([currentTime integerValue] - [orderTime integerValue]) < 1800000) {
        [LEEAlert alert].config
        .LeeHeaderColor(mainColor)
        .LeeAddTitle(^(UILabel *label) {
            label.textColor = RGBOF(0xe6e6e6);
            label.text = [[ChangeLanguage bundle] localizedStringForKey:@"warmPrompt" value:nil table:@"English"];
        })
        .LeeAddContent(^(UILabel *label) {
            label.textColor = RGBOF(0xe6e6e6);
            label.text = @"付款后半个小时，才可以申诉";
        })
        .LeeAddAction(^(LEEAction *action) {
            action.titleColor = RGBOF(0xe6e6e6);
            action.borderColor = mainColor;
            action.title = @"确定";
        })

        .LeeShow();
    }else{
        //提示框添加文本输入框
        __weak typeof(self) weakSelf = self;
        ZYInputAlertView *alertView = [ZYInputAlertView alertView];
        [alertView.confirmBtn setTitle:[[ChangeLanguage bundle] localizedStringForKey:@"complaints" value:nil table:@"English"] forState:UIControlStateNormal];
        alertView.placeholder = [[ChangeLanguage bundle] localizedStringForKey:@"orderComplaintReason" value:nil table:@"English"];
        [alertView confirmBtnClickBlock:^(NSString *inputString) {
            [weakSelf orderAppeal:inputString];
        }];
        [alertView show];
    }

}
//MARK--订单投诉事件
-(void)orderAppeal:(NSString *)appealStr{
    if ([appealStr isEqualToString:@""]) {
        [self.view makeToast:[[ChangeLanguage bundle] localizedStringForKey:@"noOrderComplaintTip" value:nil table:@"English"] duration:1.5 position:CSToastPositionCenter];
        return;
    }
    [EasyShowLodingView showLodingText:[[ChangeLanguage bundle] localizedStringForKey:@"loading" value:nil table:@"English"]];
    [MineNetManager myBillDetailComplaintsForRemark:appealStr withOrderSn:self.detailModel.orderSn CompleteHandle:^(id resPonseObj, int code) {
        [EasyShowLodingView hidenLoding];
        if (code) {
            if ([resPonseObj[@"code"] integerValue]==0) {
                //获取数据成功
                [self.view makeToast:resPonseObj[MESSAGE] duration:1.5 position:CSToastPositionCenter];
                [self.navigationController popViewControllerAnimated:YES];
            }else if ([resPonseObj[@"code"] integerValue] == 3000 ||[resPonseObj[@"code"] integerValue] == 4000 ){
                //[ShowLoGinVC showLoginVc:self withTipMessage:resPonseObj[MESSAGE]];
                [YLUserInfo logout];
            }else{
                [self.view makeToast:resPonseObj[MESSAGE] duration:1.5 position:CSToastPositionCenter];
            }
        }else{
            [self.view makeToast:[[ChangeLanguage bundle] localizedStringForKey:@"noNetworkStatus" value:nil table:@"English"] duration:1.5 position:CSToastPositionCenter];
        }
    }];
}
//MARK:--取消订单点击事件
-(void)cancelBtnClick{
 [self certainOrCancelCoinAlterView:[[ChangeLanguage bundle] localizedStringForKey:@"cancelOrderTip" value:nil table:@"English"] withIndex:2];
}
-(CGFloat)tableView:(UITableView *)tableView heightForRowAtIndexPath:(NSIndexPath *)indexPath{
    if (indexPath.section == 0){
        return 60;
    }else{
        if (indexPath.section == 2) {
            if ([self.section2ImageArr[indexPath.row] isEqualToString:@"yinlianImage"]){

                if (self.isshowBank) {
                    return  150;
                }else{
                    return 50;
                }

            }
        }
      return 50;
    }
}
-(CGFloat)tableView:(UITableView *)tableView heightForFooterInSection:(NSInteger)section{
    return 0.01;
}
-(CGFloat)tableView:(UITableView *)tableView heightForHeaderInSection:(NSInteger)section{
    if (section == 0) {
        return 0.01;
    }else if (section == 1){
        return 40;
    }else if (section == 2){
        if (self.section2Arr.count < 1) {
            return 0.01;
        }else{
            return 40;
        }
    }else{
        return 0.01;
    }
}
-(UIView *)tableView:(UITableView *)tableView viewForHeaderInSection:(NSInteger)section{
    UIView *headView = [[UIView alloc] initWithFrame:CGRectMake(0, 0, kWindowW, 40)];
    headView.backgroundColor = mainColor;
    UILabel *leftLabel = [[UILabel alloc] initWithFrame:CGRectMake(10, 10,kWindowW-20, 20)];
    leftLabel.textColor = RGBOF(0xe6e6e6);
    [headView addSubview:leftLabel];
    if (section == 1) {
        leftLabel.text = [[ChangeLanguage bundle] localizedStringForKey:@"orderInformation" value:nil table:@"English"];
    }else if (section == 2){
        if (self.section2Arr.count < 1) {
        }else{
            leftLabel.text = [[ChangeLanguage bundle] localizedStringForKey:@"payInfo" value:nil table:@"English"];
        }
    }
    return  headView;
}
-(NSArray *)getSection1LeftArr{
    NSArray * section1LeftArr = @[[[ChangeLanguage bundle] localizedStringForKey:@"tradingObject" value:nil table:@"English"],[[ChangeLanguage bundle] localizedStringForKey:@"orderNo" value:nil table:@"English"],[[ChangeLanguage bundle] localizedStringForKey:@"tradingPrice" value:nil table:@"English"],[[ChangeLanguage bundle] localizedStringForKey:@"tradingNumber" value:nil table:@"English"],[[ChangeLanguage bundle] localizedStringForKey:@"tradingCash" value:nil table:@"English"],[[ChangeLanguage bundle] localizedStringForKey:@"createTime" value:nil table:@"English"]];
    return section1LeftArr;
}
- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}
-(void)viewWillDisappear:(BOOL)animated{
    [super viewWillDisappear:YES];


}
-(void)dealloc{

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

