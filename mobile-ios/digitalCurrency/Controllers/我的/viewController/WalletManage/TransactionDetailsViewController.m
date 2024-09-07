//
//  TransactionDetailsViewController.m
//  digitalCurrency
//
//  Created by chu on 2019/7/4.
//  Copyright © 2019年 BIZZAN. All rights reserved.
//

#import "TransactionDetailsViewController.h"
#import "Masonry.h"
#import "TransactionDetailsTableViewCell.h"
#import "TradeModel.h"
#import "MineNetManager.h"
@interface TransactionDetailsViewController ()<UITableViewDelegate, UITableViewDataSource, UIScrollViewDelegate>

@property (nonatomic, strong) NSMutableArray *dataSourceArr;

@property (nonatomic, strong) UIScrollView *scrollView;

@property (nonatomic, strong) UITableView *tableView;

@property (nonatomic, strong) UIView *sectionView;

@end

static CGFloat scrollViewContentWidth = 700;
static CGFloat sectionViewFirstLabelWidth = 150;

@implementation TransactionDetailsViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    // Do any additional setup after loading the view.
    self.title = @"交易详情";
    self.view.backgroundColor = [UIColor whiteColor];
    [self.view addSubview:self.scrollView];
    [self.scrollView addSubview:self.tableView];
}

- (void)viewDidLayoutSubviews{
    [super viewDidLayoutSubviews];
    self.scrollView.contentSize = CGSizeMake(scrollViewContentWidth, 50 * self.dataSourceArr.count);
}

- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section{
    return self.dataSourceArr.count;
}

- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath{
    static NSString *identifier = @"tradeCell";
    TransactionDetailsTableViewCell *cell = [tableView dequeueReusableCellWithIdentifier:identifier];
    if (!cell) {
        cell = [[NSBundle mainBundle] loadNibNamed:@"TransactionDetailsTableViewCell" owner:nil options:nil][0];
        cell.selectionStyle = UITableViewCellSelectionStyleNone;
    }
    cell.model = self.dataSourceArr[indexPath.row];
    if (indexPath.row % 2) {
        cell.backView.backgroundColor = [UIColor whiteColor];
    }else{
        cell.backView.backgroundColor = kRGBColor(244, 245, 246);
    }
    return cell;
}

- (CGFloat)tableView:(UITableView *)tableView heightForRowAtIndexPath:(NSIndexPath *)indexPath{
    return 50;
}

- (CGFloat)tableView:(UITableView *)tableView heightForHeaderInSection:(NSInteger)section{
    return 50;
}

- (UIView *)tableView:(UITableView *)tableView viewForHeaderInSection:(NSInteger)section{
    UIView *view = [[UIView alloc] init];
    view.backgroundColor = [UIColor whiteColor];
    if (view.subviews.count < 1) {
        [view addSubview:self.sectionView];
    }
    return view;
}

- (CGFloat)tableView:(UITableView *)tableView heightForFooterInSection:(NSInteger)section{
    return 0.01;
}

- (UIView *)tableView:(UITableView *)tableView viewForFooterInSection:(NSInteger)section{
    UIView *view = [[UIView alloc] init];
    return view;
}


- (NSMutableArray *)dataSourceArr{
    if (!_dataSourceArr) {
        _dataSourceArr = [NSMutableArray arrayWithCapacity:0];
        for (int i = 0; i < 30; i++) {
            NSDictionary *dic = @{@"tradeTime":@"2018-07-03 10:46:33", @"tradePair":@"BHB/USTD", @"tradeDirection":@"限价卖出", @"tradePrice":@"0.00001USTD", @"tradeOrderVolume":@"1000BHB", @"tradeDeal":@"1000BHB", @"tradeServiceCharge":@"0.001USTD"};
            TradeModel *model = [TradeModel modelWithDictionary:dic];
            [_dataSourceArr addObject:model];
        }
    }
    return _dataSourceArr;
}

- (UIScrollView *)scrollView{
    if (!_scrollView) {
        _scrollView = [[UIScrollView alloc] initWithFrame:CGRectMake(15, 15, kWindowW - 30, kWindowH - SafeAreaTopHeight - 30)];
        _scrollView.delegate = self;
        _scrollView.layer.cornerRadius = 10;
        _scrollView.layer.borderWidth = 1;
        _scrollView.layer.borderColor = kRGBColor(233, 234, 235).CGColor;
        _scrollView.bounces = NO;
        _scrollView.directionalLockEnabled = YES;
        _scrollView.showsVerticalScrollIndicator = NO;
        _scrollView.showsHorizontalScrollIndicator = NO;
    }
    return _scrollView;
}

- (UITableView *)tableView{
    if (!_tableView) {
        _tableView = [[UITableView alloc] initWithFrame:CGRectMake(0, 0, scrollViewContentWidth, self.scrollView.size.height) style:UITableViewStylePlain];
        _tableView.delegate = self;
        _tableView.dataSource = self;
        _tableView.separatorStyle = UITableViewCellSelectionStyleNone;
    }
    return _tableView;
}

- (UIView *)sectionView{
    if (!_sectionView) {
        _sectionView = [[UIView alloc] initWithFrame:CGRectMake(0, 0, scrollViewContentWidth, 50)];
        NSArray *titles = @[LocalizationKey(@"tradeTime"), LocalizationKey(@"tradePair"), LocalizationKey(@"tradeDirection"), LocalizationKey(@"tradePrice"), LocalizationKey(@"tradeOrderVolume"), LocalizationKey(@"tradeDeal"), LocalizationKey(@"tradeServiceCharge")];
        CGFloat labelHeight = 50.f;
        CGFloat firstLabelWidth = sectionViewFirstLabelWidth;
        CGFloat otherLabelWidth = (scrollViewContentWidth - sectionViewFirstLabelWidth) / 6;
        for (int i = 0; i < titles.count; i++) {
            UILabel *label = [[UILabel alloc] init];
            label.text = titles[i];
            label.font = [UIFont systemFontOfSize:14];
            label.textAlignment = NSTextAlignmentCenter;
            label.textColor = kRGBColor(118, 119, 120);
            if (i == 0) {
                NSMutableParagraphStyle *paraStyle01 = [[NSMutableParagraphStyle alloc] init];
                paraStyle01.alignment = NSTextAlignmentLeft;  //对齐
                paraStyle01.headIndent = 0.0f;//行首缩进
                //参数：（字体大小17号字乘以2，34f即首行空出两个字符）
                CGFloat emptylen = label.font.pointSize * 1;
                paraStyle01.firstLineHeadIndent = emptylen;//首行缩进
                paraStyle01.tailIndent = 0.0f;//行尾缩进
                paraStyle01.lineSpacing = 2.0f;//行间距

                NSAttributedString *attrText = [[NSAttributedString alloc] initWithString:titles[0] attributes:@{NSParagraphStyleAttributeName:paraStyle01}];

                label.attributedText = attrText;
                label.frame = CGRectMake(0, 0, firstLabelWidth, labelHeight);
            }else{
                label.frame = CGRectMake((i - 1) * otherLabelWidth + firstLabelWidth, 0, otherLabelWidth, labelHeight);
            }
            [_sectionView addSubview:label];
        }
    }
    return _sectionView;
}

@end
