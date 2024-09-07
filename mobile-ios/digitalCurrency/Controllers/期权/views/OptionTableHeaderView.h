//
//  OptionTableHeaderView.h
//  digitalCurrency
//
//  Created by chan on 2020/12/30.
//  Copyright © 2020 BIZZAN. All rights reserved.
//

#import <UIKit/UIKit.h>
@class OptionModel;

typedef NS_ENUM(NSUInteger,OptionTableCellType ) {
    OptionTableCellTypeTrade,   //交易
    OptionTableCellTypeCurrent, //当前交割
    OptionTableCellTypeHistory  //历史交割
};

NS_ASSUME_NONNULL_BEGIN

@interface OptionTableHeaderView : UIView

@property (nonatomic, strong) NSArray *historyList;
@property (nonatomic, copy  ) void(^bottomDidClick)(OptionTableCellType);
@property (nonatomic, copy  ) NSString *symbol;
@property (nonatomic, copy  ) void(^didClick)(OptionModel *);

@end

NS_ASSUME_NONNULL_END
