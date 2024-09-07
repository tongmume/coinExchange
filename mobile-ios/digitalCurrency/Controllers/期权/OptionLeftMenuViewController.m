//
//  OptionLeftMenuViewController.m
//  digitalCurrency
//
//  Created by chan on 2020/12/30.
//  Copyright © 2020 BIZZAN. All rights reserved.
//

#import "OptionLeftMenuViewController.h"
#import "LeftTableViewCell.h"
#import "UIViewController+LeftSlide.h"
#import "OptionNetManager.h"
#import "OptionCoinModel.h"

@interface OptionLeftMenuViewController ()<UITableViewDelegate,UITableViewDataSource>

@property (nonatomic, strong)UIView *dcbackgroundView;

@property (nonatomic,strong) UITableView *myTableView;

@property (nonatomic,strong) UIView *topview;

@property (nonatomic,strong) NSMutableArray *dataArray;

@end

@implementation OptionLeftMenuViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    // Do any additional setup after loading the view.
    self.view.backgroundColor=[UIColor clearColor];
    _dcbackgroundView = [[UIView alloc]initWithFrame:CGRectMake(0,0,SCREEN_WIDTH_S*0.8,SCREEN_HEIGHT_S)];
    _dcbackgroundView.backgroundColor = mainColor;
    [self.view addSubview:_dcbackgroundView];
    
    UIButton *bcrightbtn = [UIButton buttonWithType:UIButtonTypeCustom];
    bcrightbtn.frame=CGRectMake(_dcbackgroundView.frame.size.width,0, SCREEN_WIDTH_S-_dcbackgroundView.frame.size.width, SCREEN_HEIGHT_S);
    [bcrightbtn addTarget:self action:@selector(backLeftContractMenu) forControlEvents:UIControlEventTouchUpInside];
    [self.view addSubview:bcrightbtn];
    
    _topview = [[UIView alloc]init];
          [_dcbackgroundView addSubview:_topview];
          [_topview mas_makeConstraints:^(MASConstraintMaker *make) {
              
              make.top.left.right.mas_equalTo(0);
              make.height.mas_equalTo(SafeAreaTopHeight);
          }];
          
//          UIView *line=[[UIView alloc]init];
//          line.backgroundColor=AppTextColor_E6E6E6;
//          [_topview addSubview:line];
          
//          [line mas_makeConstraints:^(MASConstraintMaker *make) {
//              make.left.right.bottom.mas_equalTo(0);
//              make.height.mas_equalTo(1);
//          }];
    
    UILabel *lefttitlelabel=[[UILabel alloc]init];
    lefttitlelabel.textColor=VCBackgroundColor;
    lefttitlelabel.font=[UIFont fontWithName:@"PingFangSC-Semibold" size:18.0];
    lefttitlelabel.textAlignment=NSTextAlignmentLeft;
    lefttitlelabel.text = LocalizationKey(@"Option_contract");
    [_topview addSubview:lefttitlelabel];
    [lefttitlelabel mas_makeConstraints:^(MASConstraintMaker *make) {
        make.left.mas_equalTo(10);
        make.bottom.mas_equalTo(-10);
        make.size.mas_equalTo(CGSizeMake(190,30));
    }];
    
    UIButton *rightbackbtn=[UIButton buttonWithType:UIButtonTypeCustom];
    [rightbackbtn setImage:[UIImage imageNamed:@"tradeLeft"] forState:UIControlStateNormal];
    [rightbackbtn addTarget:self action:@selector(backLeftContractMenu) forControlEvents:UIControlEventTouchUpInside];
    [_topview addSubview:rightbackbtn];
    
    [rightbackbtn mas_makeConstraints:^(MASConstraintMaker *make) {
        make.right.mas_equalTo(-10);
        make.centerY.equalTo(lefttitlelabel.mas_centerY).offset(0);
        make.size.mas_equalTo(CGSizeMake(22,(7/6)*22));
    }];
    
    _myTableView=[[UITableView alloc]init];
    [_myTableView  setSeparatorStyle:UITableViewCellSeparatorStyleNone];
    _myTableView.rowHeight=55;
    _myTableView.backgroundColor = ViewBackgroundColor;
    _myTableView.tableFooterView=[UIView new];
       _myTableView.delegate=self;
       _myTableView.dataSource=self;
       if (@available(iOS 11.0, *)) {

        _myTableView.estimatedRowHeight = 0;

        _myTableView.estimatedSectionFooterHeight = 0;

        _myTableView.estimatedSectionHeaderHeight=0;
        _myTableView.contentInsetAdjustmentBehavior= UIScrollViewContentInsetAdjustmentNever;
    }
    [_myTableView registerNib:[UINib nibWithNibName:@"LeftTableViewCell" bundle:nil] forCellReuseIdentifier:@"LeftTableViewCell"];
    [_dcbackgroundView addSubview:_myTableView];
    MJWeakSelf
    [_myTableView mas_makeConstraints:^(MASConstraintMaker *make) {
        
        make.left.right.bottom.mas_equalTo(0);
        make.top.equalTo(weakSelf.topview.mas_bottom).offset(0);
    }];
    [self initSlideFoundation];
    [self getOptiontSymbolThumb];
    [[NSNotificationCenter defaultCenter] addObserver:self selector:@selector(languageReload) name:LanguageChange object:nil];
}

- (void)languageReload {
    [self.myTableView reloadData];
}


- (void)showLeftContractMenu{
    
    [[NSNotificationCenter defaultCenter] addObserver:self selector:@selector(reloadShowDataItem:)name:NSNotification_CONTRACTMENULIST object:nil];
    [self show];
   
}

-(void)backLeftContractMenu{
    _isObserverNotificantion=NO;
    [[NSNotificationCenter defaultCenter] removeObserver:self];
    [self hide];
    
}

-(void)reloadShowDataItem:(NSNotification *)notif{
    
    if (notif.userInfo) {
        
         NSDictionary *iteminfodict=notif.userInfo;
         symbolModel *model= [symbolModel mj_objectWithKeyValues:iteminfodict];
        if (self.dataArray&&self.dataArray.count!=0) {
            BOOL _isyes=NO;
            for (NSInteger i=0;i <self.dataArray.count; i++) {
                
                symbolModel *modeldata=self.dataArray[i];
                if ([modeldata.symbol isEqualToString:model.symbol]) {
                    _isyes=YES;
                    [self.dataArray replaceObjectAtIndex:i withObject:model];
                    NSIndexPath *indxPath=[NSIndexPath indexPathForRow:i inSection:0];
                    
                    [_myTableView reloadRowsAtIndexPaths:@[indxPath] withRowAnimation:UITableViewRowAnimationNone];
                }
            }
            
            if (!_isyes) {
                  [self.dataArray addObject:model];
                    NSIndexPath *indxPath=[NSIndexPath indexPathForRow:self.dataArray.count-1 inSection:0];

                 [_myTableView insertRowsAtIndexPaths:@[indxPath] withRowAnimation:UITableViewRowAnimationNone];
            }
        }
    }
}

-(NSMutableArray *)dataArray{
    
    if (!_dataArray) {
        _dataArray=[NSMutableArray array];
    }
    return _dataArray;
}

#pragma mark - 数据源方法
- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section {
 
    return _dataArray.count;
}
-(UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath{
    
    LeftTableViewCell *cell = [tableView dequeueReusableCellWithIdentifier:@"LeftTableViewCell"];
       
       if (!cell) {
           cell = [tableView dequeueReusableCellWithIdentifier:@"LeftTableViewCell" forIndexPath:indexPath];
       }
       cell.selectionStyle = UITableViewCellSelectionStyleNone;
            
            
    if (self.dataArray&&self.dataArray.count!=0) {
        OptionCoinModel *model = self.dataArray[indexPath.row];
       NSArray *arr= [model.symbol componentsSeparatedByString:@"/"];
        cell.pricelabel.hidden = YES;
        cell.changelabel.hidden = YES;
        cell.namelabel.font=[UIFont fontWithName:@"PingFangSC-Semibold" size:17];
        cell.namelabel.attributedText=[cell optionfondstr:arr.firstObject fondstr:arr.lastObject];
    }
    return cell;
}

- (void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath{
    
    if (indexPath.row<self.dataArray.count) {
        
        OptionCoinModel *model=self.dataArray[indexPath.row];
        if (self.selcetContractcoinSymbolModelBlock) {
            
            self.selcetContractcoinSymbolModelBlock(model);
            [self backLeftContractMenu];
        }
        
    }
    
}

- (void)getOptiontSymbolThumb {
    [EasyShowLodingView showLodingText:LocalizationKey(@"loading")];
    [OptionNetManager getOptionSymbolListCompleteHandle:^(id  _Nonnull resPonseObj, int code) {
        [EasyShowLodingView hidenLoding];

         if (code) {
                   if (code == 1) {
                       NSArray*arr=resPonseObj[@"data"];
                       
                       if ([arr isKindOfClass:[NSArray class]]) {
                           [self.dataArray removeAllObjects];
                           NSArray *array=(NSArray*)arr;
                           
                           for (int i=0; i<array.count; i++) {
                               
                               OptionCoinModel *model= [OptionCoinModel mj_objectWithKeyValues:array[i]];
                               [self.dataArray addObject:model];
                           }
                       }
                       [self.myTableView reloadData];
                       //初始化数据后开启通知
                       _isObserverNotificantion=YES;

                   }
                   else if ([resPonseObj[@"code"] integerValue] ==4000){

                       [YLUserInfo logout];
                   }
                   else{
                       [self.view makeToast:resPonseObj[MESSAGE] duration:1.5 position:CSToastPositionCenter];
                   }
               }
               else{
                   [self.view makeToast:LocalizationKey(@"noNetworkStatus") duration:1.5 position:CSToastPositionCenter];
               }
        
    }];
}

@end
