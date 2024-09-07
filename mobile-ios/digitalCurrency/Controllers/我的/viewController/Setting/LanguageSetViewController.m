//
//  LanguageSetViewController.m
//  digitalCurrency
//
//  Created by chan on 2021/1/5.
//  Copyright © 2021 BIZZAN. All rights reserved.
//

#import "LanguageSetViewController.h"
#import "NewTabBarViewController.h"

@interface LanguageCell : UITableViewCell

@property(nonatomic, strong) UIImageView *icon;

@property(nonatomic, strong) UILabel  *titleLabel;

@end

@implementation LanguageCell

- (instancetype)initWithStyle:(UITableViewCellStyle)style reuseIdentifier:(NSString *)reuseIdentifier {
    if (self = [super initWithStyle:style reuseIdentifier:reuseIdentifier]) {
        [self initUI];
        self.selectionStyle = UITableViewCellSelectionStyleNone;
    }
    return self;
}

- (void)initUI {
    [self.icon mas_makeConstraints:^(MASConstraintMaker *make) {
        make.right.equalTo(self.contentView).offset(-15);
        make.centerY.equalTo(self.contentView);
        make.width.height.equalTo(@15);
    }];
    [self.titleLabel mas_makeConstraints:^(MASConstraintMaker *make) {
        make.left.equalTo(self.contentView).offset(15);
        make.centerY.equalTo(self.contentView);
    }];
}

- (UIImageView *)icon {
    if (!_icon) {
        _icon = [[UIImageView alloc] initWithImage:[UIImage imageNamed:@"hookImage"]];
        [self.contentView addSubview:_icon];
    }
    return  _icon;
}
- (UILabel *)titleLabel {
    if (!_titleLabel) {
        _titleLabel = [[UILabel alloc] init];
        _titleLabel.textColor = UIColor.whiteColor;
        _titleLabel.font = [UIFont systemFontOfSize:15];
        [self.contentView addSubview:_titleLabel];
    }
    return  _titleLabel;
}

@end

@interface LanguageSetViewController ()<UITableViewDelegate, UITableViewDataSource>

@property (nonatomic, strong) UITableView *tableView;

@end

@implementation LanguageSetViewController


- (void)viewDidLoad {
    [super viewDidLoad];
    self.view.backgroundColor = mainColor;
    self.title = [[ChangeLanguage bundle] localizedStringForKey:@"languageSettings" value:nil table:@"English"];
    [self.tableView mas_makeConstraints:^(MASConstraintMaker *make) {
        make.edges.equalTo(self.view);
    }];
}

- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section {
    return [ChangeLanguage languageList].count;
}

- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath {
    LanguageCell * cell = [tableView dequeueReusableCellWithIdentifier:@"LanguageCellID" forIndexPath:indexPath];
        cell.backgroundColor = mainColor;
        LYLanguage *model = [ChangeLanguage languageList][indexPath.row];
        cell.titleLabel.text = model.name;
    if ([[ChangeLanguage userLanguage] isEqualToString:model.bundleKey]) {
        cell.icon.hidden = NO;
    }else {
        cell.icon.hidden = YES;
    }

    return cell;
}

- (void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath {
    LYLanguage *model = [ChangeLanguage languageList][indexPath.row];
    if ([[ChangeLanguage userLanguage] isEqualToString:model.bundleKey]) {
        return;
    }
    [ChangeLanguage setUserlanguage:model.bundleKey];
    //创建通知
    NSNotification *notification =[NSNotification notificationWithName:LanguageChange object:nil userInfo:nil];
    //通过通知中心发送通知
    [[NSNotificationCenter defaultCenter] postNotification:notification];
    self.title = [[ChangeLanguage bundle] localizedStringForKey:@"languageSettings" value:nil table:@"English"];
    [self.tableView reloadData];
    NewTabBarViewController*tabBarController=(NewTabBarViewController*)APPLICATION.window.rootViewController;
    [tabBarController resettabarItemsTitle];
}


- (CGFloat)tableView:(UITableView *)tableView heightForRowAtIndexPath:(NSIndexPath *)indexPath {
    return  44;
}

- (UITableView *)tableView {
    if (!_tableView) {
        _tableView = [[UITableView alloc] initWithFrame:CGRectZero style:UITableViewStylePlain];
        _tableView.dataSource = self;
        _tableView.backgroundColor = mainColor;
        _tableView.delegate = self;
        _tableView.tableHeaderView = nil;
        _tableView.tableFooterView = nil;
        [_tableView registerClass:[LanguageCell class] forCellReuseIdentifier:@"LanguageCellID"];
        [self.view addSubview:_tableView];
    }
    return  _tableView;
}

@end
