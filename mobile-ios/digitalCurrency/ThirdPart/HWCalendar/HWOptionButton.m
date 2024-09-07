//
//  HWOptionButton.m
//  HWCalendar
//
//  Created by wqb on 2019/1/12.
//  Copyright © 2019年 hero_wqb. All rights reserved.
//

#import "HWOptionButton.h"

#define KMainW [UIScreen mainScreen].bounds.size.width
#define KMainH [UIScreen mainScreen].bounds.size.height
#define KMarginYWhenMoving 20.0f
#define KRowHeight 44.0f
#define KMaxShowLine 7
#define KFont [UIFont systemFontOfSize:13.0f]
#define KBackColor [UIColor whiteColor]

@interface HWOptionButton ()<UITableViewDelegate, UITableViewDataSource, UISearchBarDelegate>

@property (nonatomic, strong) NSArray *searchArray;
@property (nonatomic, strong) UIWindow *cover;
@property (nonatomic, strong) UITableView *tableView;
@property (nonatomic, strong) UISearchBar *searchBar;
@property (nonatomic, weak) UIView *view;
@property (nonatomic, weak) UIButton *button;

@end

@implementation HWOptionButton

static NSString *KOptionButtonCell = @"KOptionButtonCell";

- (instancetype)initWithFrame:(CGRect)frame
{
    if (self = [super initWithFrame:frame]) {
        [self setup];
    }

    return self;
}

- (instancetype)initWithCoder:(NSCoder *)coder
{
    if (self = [super initWithCoder:coder]) {
        [self setup];
    }

    return self;
}

- (void)setup {
    //按钮
    UIButton *button = [[UIButton alloc] initWithFrame:self.bounds];
    [button setTitleColor:[UIColor blackColor] forState:UIControlStateNormal];
    button.titleLabel.font = KFont;
    [button setImage:[UIImage imageNamed:@"bottom"] forState:UIControlStateNormal];
    [button setImage:[UIImage imageNamed:@"bottom"] forState:UIControlStateHighlighted];
    [button addTarget:self action:@selector(buttonAction:) forControlEvents:UIControlEventTouchUpInside];
    [self addSubview:button];
    self.button = button;

    //搜索框
    _searchBar = [[UISearchBar alloc] init];
    _searchBar.barTintColor = KBackColor;
    _searchBar.layer.borderWidth = 1.0f;
    _searchBar.layer.borderColor = [[UIColor blackColor] CGColor];
    _searchBar.delegate = self;
    _searchBar.keyboardType = UIKeyboardTypeASCIICapable;

    //选项视图
    _tableView = [[UITableView alloc] init];
    _tableView.rowHeight = KRowHeight;
    _tableView.separatorStyle = UITableViewCellSeparatorStyleNone;
    _tableView.layer.borderWidth = 1.0f;
    _tableView.layer.borderColor = [[UIColor blackColor] CGColor];
    _tableView.dataSource = self;
    _tableView.delegate = self;

    _row = 0;
    self.showSearchBar = NO;
}

- (void)buttonAction:(UIButton *)button
{
    button.hidden = YES;

    [self creatControl];

    [self endEditing];
}

- (void)creatControl
{
    //遮盖window
    _cover = [[UIWindow alloc] initWithFrame:[[UIScreen mainScreen] bounds]];
    _cover.windowLevel = UIWindowLevelAlert;
    _cover.hidden = NO;

    //window视图
    UIView *view = [[UIView alloc] initWithFrame:[[UIScreen mainScreen] bounds]];
    [_cover addSubview:view];
    self.view = view;

    //遮盖视图
    UIView *backview = [[UIView alloc] initWithFrame:[[UIScreen mainScreen] bounds]];
    backview.backgroundColor = [UIColor colorWithRed:(0)/255.0 green:(0)/255.0 blue:(0)/255.0 alpha:0.0f];
    [backview addGestureRecognizer:[[UITapGestureRecognizer alloc] initWithTarget:self action:@selector(Tap:)]];
    [self.view addSubview:backview];

    //坐标转换
    CGRect frame = [self.superview convertRect:self.frame toView:self.view];

    //显示选项按钮
    UIButton *button = [[UIButton alloc] initWithFrame:CGRectMake(frame.origin.x, frame.origin.y, self.frame.size.width, self.frame.size.height)];
    button.titleLabel.font = KFont;
    [button setTitle:_button.titleLabel.text forState:UIControlStateNormal];
    [button setTitleColor:[UIColor blackColor] forState:UIControlStateNormal];
    [button addTarget:self action:@selector(btnOnClick) forControlEvents:UIControlEventTouchUpInside];
    [button setImage:[UIImage imageNamed:@"bottom"] forState:UIControlStateNormal];
    [button setImage:[UIImage imageNamed:@"bottom"] forState:UIControlStateHighlighted];
    CGSize titleSize = [button.titleLabel.text boundingRectWithSize:CGSizeMake(MAXFLOAT, MAXFLOAT) options:NSStringDrawingUsesLineFragmentOrigin attributes:@{NSFontAttributeName:button.titleLabel.font} context:nil].size;
    CGFloat imageLeftWidth = titleSize.width;
    CGFloat titleLeftWidth = CGRectGetWidth(button.imageView.bounds);
    button.imageEdgeInsets = UIEdgeInsetsMake(0, imageLeftWidth, 0, - imageLeftWidth);
    button.titleEdgeInsets = UIEdgeInsetsMake(0, - titleLeftWidth, 0, titleLeftWidth);
    [self.view addSubview:button];

    //搜索框
    if (_showSearchBar) {
        _searchBar.frame = CGRectMake(frame.origin.x, CGRectGetMaxY(frame), frame.size.width, KRowHeight);
        [self.view addSubview:_searchBar];
    }

    //设置初始位置
    [_tableView selectRowAtIndexPath:[NSIndexPath indexPathForRow:_row inSection:0] animated:NO scrollPosition:UITableViewScrollPositionNone];
    [_tableView scrollToRowAtIndexPath:[NSIndexPath indexPathForRow:_row inSection:0] atScrollPosition:UITableViewScrollPositionTop animated:NO];

    //设置frame
    NSInteger rowCount = _showSearchBar ? KMaxShowLine - 1 : KMaxShowLine;
    CGFloat tabelViewY = _showSearchBar ? CGRectGetMaxY(_searchBar.frame) : CGRectGetMaxY(frame);
    if (_array.count <= rowCount) {
        _tableView.frame = CGRectMake(frame.origin.x, tabelViewY, frame.size.width, _array.count * KRowHeight);
    }else {
        _tableView.frame = CGRectMake(frame.origin.x, tabelViewY, frame.size.width, rowCount * KRowHeight);
    }

    [self.view addSubview:_tableView];
}

- (void)setTitle:(NSString *)title
{
    _title = title;

    [_button setTitle:_title forState:UIControlStateNormal];

    CGSize titleSize = [_title boundingRectWithSize:CGSizeMake(MAXFLOAT, MAXFLOAT) options:NSStringDrawingUsesLineFragmentOrigin attributes:@{NSFontAttributeName:_button.titleLabel.font} context:nil].size;
    CGFloat imageLeftWidth = titleSize.width;
    CGFloat titleLeftWidth = CGRectGetWidth(_button.imageView.bounds);
    _button.imageEdgeInsets = UIEdgeInsetsMake(0, imageLeftWidth, 0, - imageLeftWidth);
    _button.titleEdgeInsets = UIEdgeInsetsMake(0, - titleLeftWidth, 0, titleLeftWidth);
}

- (void)setArray:(NSArray *)array
{
    _array = array;

    self.searchArray = [_array copy];

    [_tableView reloadData];
}

- (void)btnOnClick
{
    [self dismissOptionAlert];
}

- (void)Tap:(UITapGestureRecognizer *)recognizer
{
    [self dismissOptionAlert];
}

- (void)dismissOptionAlert
{
    [_searchBar resignFirstResponder];

    if (self.view.frame.origin.y == 0) {
        [self removeCover];
    }else {
        [self searchBarTextDidEndEditing:_searchBar];
    }
}

- (void)removeCover
{
    [_searchBar resignFirstResponder];
    _cover.hidden = YES;
    _cover = nil;
    _button.hidden = NO;
}

- (void)endEditing
{
    [[[self findViewController] view] endEditing:YES];
}

- (UIViewController *)findViewController
{
    id target = self;
    while (target) {
        target = ((UIResponder *)target).nextResponder;
        if ([target isKindOfClass:[UIViewController class]]) {
            break;
        }
    }
    return target;
}

#pragma mark - UISearchBarDelegate
- (void)searchBar:(UISearchBar *)searchBar textDidChange:(NSString *)searchText
{
    if (searchText.length > 0) {
        NSPredicate *predicate = [NSPredicate predicateWithFormat:@"SELF CONTAINS[c] %@", searchText];
        _searchArray = [[_array filteredArrayUsingPredicate:predicate] copy];
    }else {
        _searchArray = [_array copy];
    }

    [_tableView reloadData];
}

- (BOOL)searchBarShouldBeginEditing:(UISearchBar *)searchBar
{
    UIView *view = self.superview;
    while (view.superview) {
        view = view.superview;
    }

    CGFloat Y = KMarginYWhenMoving - [self.superview convertRect:self.frame toView:self.view].origin.y;
    [UIView animateWithDuration:0.22f animations:^{
        view.frame = CGRectMake(0, Y, KMainW, KMainH);
        self.view.frame = CGRectMake(0, Y, KMainW, KMainH);
    }];

    return YES;
}

- (void)searchBarTextDidEndEditing:(UISearchBar *)searchBar
{
    UIView *view = self.superview;
    while (view.superview) {
        view = view.superview;
    }

    [UIView animateWithDuration:0.22f animations:^{
        view.frame = CGRectMake(0, 0, KMainW, KMainH);
        self.view.frame = CGRectMake(0, 0, KMainW, KMainH);
    }completion:^(BOOL finished) {
        [self removeCover];
    }];
}

#pragma mark - tableViewDelegate
- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section
{
    return _searchArray.count;
}

- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath
{
    UITableViewCell *cell = [tableView dequeueReusableCellWithIdentifier:KOptionButtonCell];
    if (!cell) {
        cell = [[UITableViewCell alloc] initWithStyle:UITableViewCellStyleValue1 reuseIdentifier:KOptionButtonCell];
    }
    cell.textLabel.text = _searchArray[indexPath.row];
    cell.backgroundColor = [UIColor whiteColor];
    cell.textLabel.font = KFont;

    return cell;
}

- (void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath
{
    _row = indexPath.row;
    self.title = _searchArray[_row];
    [self dismissOptionAlert];

    if (_delegate && [_delegate respondsToSelector:@selector(didSelectOptionInHWOptionButton:)]) {
        [_delegate didSelectOptionInHWOptionButton:self];
    }
}

@end
