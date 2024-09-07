//
//  WalletManageTableHeadView.m
//  digitalCurrency
//
//  Created by iDog on 2019/4/12.
//  Copyright © 2019年 BIZZAN. All rights reserved.
//

#import "WalletManageTableHeadView.h"

@implementation WalletManageTableHeadView

-(void)awakeFromNib{
    [super awakeFromNib];
    [[[[ self.searchBar . subviews objectAtIndex : 0 ] subviews ] objectAtIndex : 0 ] removeFromSuperview ];

    self.searchBar.backgroundColor = mainColor;

    self.searchBar.placeholder = [[ChangeLanguage bundle] localizedStringForKey:@"searchAssets" value:nil table:@"English"];
    self.selectBtnLabel.text = [[ChangeLanguage bundle] localizedStringForKey:@"hidden0Currency" value:nil table:@"English"];
    self.searchBar.backgroundImage = [[UIImage alloc] init];
    self.searchBar.barStyle = UIBarStyleBlack;
    UITextField *searchField = [self.searchBar valueForKey:@"searchField"];
    if (searchField) {
        [searchField setBackgroundColor:mainColor];
        searchField.layer.cornerRadius = 5.0f;
        searchField.layer.borderColor = [UIColor clearColor].CGColor;
        searchField.layer.borderWidth = 1;
        searchField.layer.masksToBounds = YES;
    }

}
-(WalletManageTableHeadView *)instancetableHeardViewWithFrame:(CGRect)Rect
{
    NSArray* nibView =  [[NSBundle mainBundle] loadNibNamed:@"WalletManageTableHeadView" owner:nil options:nil];
    WalletManageTableHeadView *tableView=[nibView objectAtIndex:0];
    tableView.frame=Rect;
    return tableView;
}
/*
// Only override drawRect: if you perform custom drawing.
// An empty implementation adversely affects performance during animation.
- (void)drawRect:(CGRect)rect {
    // Drawing code
}
*/

@end
