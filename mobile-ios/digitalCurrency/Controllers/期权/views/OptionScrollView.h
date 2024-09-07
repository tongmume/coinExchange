//
//  OptionScrollView.h
//  digitalCurrency
//
//  Created by chan on 2021/1/4.
//  Copyright © 2021 BIZZAN. All rights reserved.
//

#import <UIKit/UIKit.h>


NS_ASSUME_NONNULL_BEGIN

@interface OptionScrollView : UIView

@property (nonatomic, strong) NSArray *data;

@property (nonatomic, copy  ) void(^didClickBlock)(NSString *);

@end

NS_ASSUME_NONNULL_END
