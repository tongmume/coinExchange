//
//  ContractHoldOrderTableViewCell.h
//  digitalCurrency
//
//  Created by ios on 2020/9/23.
//  Copyright © 2020 BIZZAN. All rights reserved.
//

#import <UIKit/UIKit.h>

NS_ASSUME_NONNULL_BEGIN

@interface ContractHoldOrderTableViewCell : UITableViewCell
@property (nonatomic, strong) UILabel *shouyilabeltop;

@property (nonatomic, strong) UILabel *shouyilvlabeltop;

@property (nonatomic, strong) UILabel *leveragetiplabeltop;

@property (nonatomic, strong) UILabel *chiNumberlabeltop;

@property (nonatomic, strong) UILabel *kepingNumberlabeltop;

@property (nonatomic, strong) UILabel *openpricelabeltop;

@property (nonatomic, strong) UILabel *currtentPriceDepositlabeltop;

@property (nonatomic, strong) UILabel *priceDepositlabeltop;

@property (nonatomic, strong) UILabel *shouyilabel;

@property (nonatomic, strong) UILabel *shouyilvlabel;

@property (nonatomic, strong) UILabel *levergeslabel;

@property (nonatomic, strong) UILabel *chiNumberlabel;

@property (nonatomic, strong) UILabel *kepingNumberlabel;

@property (nonatomic, strong) UILabel *openpricelabel;

@property (nonatomic, strong) UILabel *currtentPriceDepositlabel;

@property (nonatomic, strong) UILabel *priceDepositlabel;

@property (nonatomic, strong) UIButton *pingButton;

@property (nonatomic, copy  ) void(^pingBlock)();

@end

NS_ASSUME_NONNULL_END
