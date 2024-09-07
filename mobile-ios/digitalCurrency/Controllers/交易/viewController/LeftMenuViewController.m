//
//  LeftMenuViewController.m
//  digitalCurrency
//
//  Created by sunliang on 2019/1/31.
//  Copyright © 2019年 BIZZAN. All rights reserved.
//

#import "LeftMenuViewController.h"
#import "UIViewController+LeftSlide.h"
//#import "menuCell.h"
#import "marketManager.h"
#import "HomeNetManager.h"
#import "symbolModel.h"
#import "MarketNetManager.h"
#import "marketCell.h"
#import "LeftTableViewCell.h"

@interface LeftMenuViewController ()<SocketDelegate>
{
    UIButton*_currentBtn;//当前选中的按钮
    BOOL  _isDragging;
}
@property (weak, nonatomic) IBOutlet NSLayoutConstraint *navHeight;
@property (weak, nonatomic) IBOutlet UIButton *defalutBtn;
@property (weak, nonatomic) IBOutlet UIView *lineView;
@property (weak, nonatomic) IBOutlet UIActivityIndicatorView *indicatorView;
@property (nonatomic,strong)NSMutableArray *contentArr;
@property (weak, nonatomic) IBOutlet UIButton *collectionBtn;
@property (weak, nonatomic) IBOutlet UILabel *marketsLabel;

@end

@implementation LeftMenuViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    self.navHeight.constant=SafeAreaTopHeight;
//    [self.tableView registerNib:[UINib nibWithNibName:@"menuCell" bundle:nil] forCellReuseIdentifier:@"menuCell"];
    [self.tableView registerNib:[UINib nibWithNibName:@"LeftTableViewCell" bundle:nil] forCellReuseIdentifier:@"LeftTableViewCell"];
    self.tableView.separatorStyle = UITableViewCellSelectionStyleNone;
    self.tableView.rowHeight=55;
    self.tableView.tableFooterView=[UIView new];
     [_defalutBtn setTitleColor:kRGBColor(240, 167, 11) forState:UIControlStateNormal];
    LYEmptyView*emptyView=[LYEmptyView emptyViewWithImageStr:@"no" titleStr:LocalizationKey(@"noDada")];
    self.tableView.ly_emptyView = emptyView;
    // 添加从左划入的功能
    [self initSlideFoundation];
    _currentBtn=self.defalutBtn;
    self.indicatorView.hidesWhenStopped=YES;
       [[NSNotificationCenter defaultCenter] addObserver:self selector:@selector(languageSetting)name:LanguageChange object:nil];
    // Do any additional setup after loading the view from its nib.
}
//MARK:--国际化通知处理事件
- (void)languageSetting{
    LYEmptyView*emptyView=[LYEmptyView emptyViewWithImageStr:@"no" titleStr:LocalizationKey(@"noDada")];
    self.tableView.ly_emptyView = emptyView;
}
-(void)viewWillAppear:(BOOL)animated{
    [super viewWillAppear:YES];

}
#pragma mark - 数据源方法
- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section {
    if (self.viewType==ChildViewType_USDT) {
        return [marketManager shareInstance].USDTArray.count;
    }else if (self.viewType==ChildViewType_BTC){
        return [marketManager shareInstance].BTCArray.count;
    }else if (self.viewType==ChildViewType_ETH){
        return [marketManager shareInstance].ETHArray.count;
    }else{
        return [marketManager shareInstance].CollectionArray.count;
    }
}

- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath {
    LeftTableViewCell * cell = [tableView dequeueReusableCellWithIdentifier:@"LeftTableViewCell" forIndexPath:indexPath];
    cell.selectionStyle=UITableViewCellSelectionStyleNone;
    if (self.viewType==ChildViewType_USDT) {
        symbolModel*model=[marketManager shareInstance].USDTArray[indexPath.row];
        [cell configDataWithModel:model withtype:0 withIndex:(int)indexPath.row];

    }else if (self.viewType==ChildViewType_BTC){
        symbolModel*model=[marketManager shareInstance].BTCArray[indexPath.row];
        [cell configDataWithModel:model withtype:0 withIndex:(int)indexPath.row];
    }else if (self.viewType==ChildViewType_ETH){
        symbolModel*model=[marketManager shareInstance].ETHArray[indexPath.row];
        [cell configDataWithModel:model withtype:0 withIndex:(int)indexPath.row];
    }else{
        symbolModel*model=[marketManager shareInstance].CollectionArray[indexPath.row];
        [cell configDataWithModel:model withtype:1 withIndex:(int)indexPath.row];
    }
     return cell;
}

- (void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath{
    NSDictionary *dict;
    LeftTableViewCell *currentCell = [tableView cellForRowAtIndexPath:indexPath];
    if (self.viewType==ChildViewType_Collection) {
        symbolModel*model=[marketManager shareInstance].CollectionArray[indexPath.row];
        NSArray *array = [model.symbol componentsSeparatedByString:@"/"];
       dict =[[NSDictionary alloc]initWithObjectsAndKeys:[array firstObject],@"object",[array lastObject],@"base",nil];
    }
    else{
       dict =[[NSDictionary alloc]initWithObjectsAndKeys:currentCell.namelabel.text,@"object",_currentBtn.titleLabel.text,@"base",nil];
    }
    NSDictionary*dic;
    if ([YLUserInfo isLogIn]) {
        dic=[NSDictionary dictionaryWithObjectsAndKeys:[marketManager shareInstance].symbol,@"symbol",[YLUserInfo shareUserInfo].ID,@"uid",nil];
    }else{
        dic=[NSDictionary dictionaryWithObjectsAndKeys:[marketManager shareInstance].symbol,@"symbol",nil];
    }
    [[SocketManager share] sendMsgWithLength:SOCKETREQUEST_LENGTH withsequenceId:0 withcmd:UNSUBSCRIBE_EXCHANGE_TRADE withVersion:COMMANDS_VERSION withRequestId: 0 withbody:dic];

    [marketManager shareInstance].symbol=[NSString stringWithFormat:@"%@/%@",dict[@"object"],dict[@"base"]];
    //创建通知
    NSNotification *notification =[NSNotification notificationWithName:CURRENTSELECTED_SYMBOL object:nil userInfo:dict];
    //通过通知中心发送通知
    [[NSNotificationCenter defaultCenter] postNotification:notification];
    [self hide];
}
//开始拖拽
- (void)scrollViewWillBeginDragging:(UIScrollView *)scrollView{

    _isDragging=YES;
}
//结束拖拽
- (void)scrollViewWillEndDragging:(UIScrollView *)scrollView withVelocity:(CGPoint)velocity targetContentOffset:(inout CGPoint *)targetContentOffset{

    _isDragging=NO;
}
//点击切换数据
- (IBAction)changeData:(UIButton *)sender {

    if (sender!=_currentBtn) {
        [_currentBtn setTitleColor:RGBOF(0xe6e6e6) forState:UIControlStateNormal];
        [sender setTitleColor:kRGBColor(240, 167, 11) forState:UIControlStateNormal];
        _currentBtn=sender;
        self.viewType=sender.tag;
        [self.tableView reloadData];
        [UIView animateWithDuration:0.3 animations:^{
            self.lineView.mj_x=sender.mj_x;

        } completion:^(BOOL finished) {
            //            [self getData];

        }];
    }
}

#pragma mark -- show or hide
- (void)showFromLeft
{
    [self.collectionBtn setTitle:LocalizationKey(@"Collection") forState:UIControlStateNormal];
    self.marketsLabel.text=LocalizationKey(@"tabbar3");
    self.collectionBtn.titleLabel.adjustsFontSizeToFitWidth = YES;
    [self show];
    [self getData];
    [[SocketManager share] sendMsgWithLength:SOCKETREQUEST_LENGTH withsequenceId:0 withcmd:SUBSCRIBE_SYMBOL_THUMB withVersion:COMMANDS_VERSION withRequestId: 0 withbody:nil];
    [SocketManager share].delegate=self;
}
- (IBAction)hideToLeft:(id)sender {
    [self hide];
    [[SocketManager share] sendMsgWithLength:SOCKETREQUEST_LENGTH withsequenceId:0 withcmd:UNSUBSCRIBE_SYMBOL_THUMB withVersion:COMMANDS_VERSION withRequestId: 0 withbody:nil];
}
kRemoveCellSeparator

//获取所有交易币种缩略行情
-(void)getData{
    [self removeAllArray];
    [self.indicatorView startAnimating];
    [HomeNetManager getsymbolthumbCompleteHandle:^(id resPonseObj, int code) {
        if (code) {
            if ([resPonseObj isKindOfClass:[NSArray class]]) {
                [self.contentArr removeAllObjects];
                NSArray*symbolArray=(NSArray*)resPonseObj;
                for (int i=0; i<symbolArray.count; i++) {
                    symbolModel*model = [symbolModel mj_objectWithKeyValues:symbolArray[i]];
                    [self.contentArr addObject:model];
                    NSArray *array = [model.symbol componentsSeparatedByString:@"/"];
                    NSString*baseSymbol=[array lastObject];
                    if ([baseSymbol isEqualToString:@"USDT"]) {
                        [[marketManager shareInstance].USDTArray addObject:model];
                    }else if ([baseSymbol isEqualToString:@"BTC"])
                    {
                        [[marketManager shareInstance].BTCArray addObject:model];
                    }
                    else if ([baseSymbol isEqualToString:@"ETH"])
                    {
                        [[marketManager shareInstance].ETHArray addObject:model];
                    }
                }
                [marketManager shareInstance].AllCoinArray=self.contentArr;
                if ([YLUserInfo isLogIn]) {
//                    if (_currentBtn.tag==3) {
//                        [self getPersonAllCollection];//获取全部自选
//                    }else{
//                        [self.tableView reloadData];
//                        [self.indicatorView stopAnimating];
//                        }
                [self getPersonAllCollection];//获取全部自选
                [self.indicatorView stopAnimating];
                }else{
                    [self.tableView reloadData];
                    [self.indicatorView stopAnimating];
                }
            }else{
                [self.indicatorView stopAnimating];
                [self.view makeToast:resPonseObj[MESSAGE] duration:1.5 position:CSToastPositionCenter];
            }
        }else{
            [self.indicatorView stopAnimating];
            [self.view makeToast:LocalizationKey(@"noNetworkStatus") duration:1.5 position:CSToastPositionCenter];
        }
    }];
}
//获取个人全部自选
-(void)getPersonAllCollection{
    [MarketNetManager queryAboutMyCollectionCompleteHandle:^(id resPonseObj, int code) {
        if (code) {
            if ([resPonseObj isKindOfClass:[NSArray class]]) {
                [self.indicatorView stopAnimating];
                NSArray*symbolArray=(NSArray*)resPonseObj;
                for (int i=0; i<symbolArray.count; i++) {
                    NSDictionary*dict=symbolArray[i];
                    [[marketManager shareInstance].AllCoinArray enumerateObjectsUsingBlock:^(symbolModel* obj, NSUInteger idx, BOOL * _Nonnull stop) {
                        if ([obj.symbol isEqualToString:dict[@"symbol"]]) {
                            [[marketManager shareInstance].CollectionArray addObject:obj];
                        }
                    }];
                }
                [self.tableView reloadData];

            }else{
                [self.view makeToast:resPonseObj[MESSAGE] duration:1.5 position:CSToastPositionCenter];
            }
        }else{
            [self.view makeToast:LocalizationKey(@"noNetworkStatus") duration:1.5 position:CSToastPositionCenter];
        }
    } ];
}

- (NSMutableArray *)contentArr
{
    if (!_contentArr) {
        _contentArr = [NSMutableArray array];
    }
    return _contentArr;
}
-(void)removeAllArray{
    [[marketManager shareInstance].USDTArray removeAllObjects];
    [[marketManager shareInstance].BTCArray removeAllObjects];
    [[marketManager shareInstance].ETHArray removeAllObjects];
    [[marketManager shareInstance].CollectionArray removeAllObjects];
}
#pragma mark - SocketDelegate Delegate
- (void)delegateSocket:(GCDAsyncSocket *)sock didReadData:(NSData *)data withTag:(long)tag{
    NSData *endData = [data subdataWithRange:NSMakeRange(SOCKETRESPONSE_LENGTH, data.length -SOCKETRESPONSE_LENGTH)];
    NSString *endStr= [[NSString alloc] initWithData:endData encoding:NSUTF8StringEncoding];
    NSData *cmdData = [data subdataWithRange:NSMakeRange(12,2)];
    uint16_t cmd=[SocketUtils uint16FromBytes:cmdData];
    //缩略行情
    if (cmd==PUSH_SYMBOL_THUMB) {
        if (endStr) {
            NSDictionary*dic=[SocketUtils dictionaryWithJsonString:endStr];
//            NSLog(@"收到消息-%@--%@",endStr,dic);
            symbolModel*model = [symbolModel mj_objectWithKeyValues:dic];
            if (_isDragging) {
                return;
            }
            if (self.viewType==ChildViewType_USDT) {
                [[marketManager shareInstance].USDTArray enumerateObjectsUsingBlock:^(symbolModel*  _Nonnull obj, NSUInteger idx, BOOL * _Nonnull stop) {
                    if ([obj.symbol isEqualToString:model.symbol]) {
                        [[marketManager shareInstance].USDTArray  replaceObjectAtIndex:idx withObject:model];
                        *stop = YES;

                        [self.tableView reloadData];
                    }
                }];
            }
            else if (self.viewType==ChildViewType_BTC)
            {
                [[marketManager shareInstance].BTCArray enumerateObjectsUsingBlock:^(symbolModel*  _Nonnull obj, NSUInteger idx, BOOL * _Nonnull stop) {
                    if ([obj.symbol isEqualToString:model.symbol]) {
                        [[marketManager shareInstance].BTCArray  replaceObjectAtIndex:idx withObject:model];
                        *stop = YES;
                        [self.tableView reloadData];
                    }
                }];
            }else if (self.viewType==ChildViewType_ETH){
                [[marketManager shareInstance].ETHArray enumerateObjectsUsingBlock:^(symbolModel*  _Nonnull obj, NSUInteger idx, BOOL * _Nonnull stop) {
                    if ([obj.symbol isEqualToString:model.symbol]) {
                        [[marketManager shareInstance].ETHArray  replaceObjectAtIndex:idx withObject:model];
                        *stop = YES;
                        [self.tableView reloadData];
                    }
                }];
            }
            else{
                [[marketManager shareInstance].CollectionArray enumerateObjectsUsingBlock:^(symbolModel*  _Nonnull obj, NSUInteger idx, BOOL * _Nonnull stop) {
                    if ([obj.symbol isEqualToString:model.symbol]) {
                        [[marketManager shareInstance].CollectionArray  replaceObjectAtIndex:idx withObject:model];
                        *stop = YES;
                        [self.tableView reloadData];
                    }
                }];
            }

        }
    }
}
-(void)dealloc{
    [[NSNotificationCenter defaultCenter] removeObserver:self];
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
