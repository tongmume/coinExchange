//
//  HomeRecommendCollectionViewCell.h
//  digitalCurrency
//
//  Created by chu on 2019/7/20.
//  Copyright © 2019年 BIZZAN. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "symbolModel.h"

@interface HomeRecommendCollectionViewCell : UICollectionViewCell
@property (weak, nonatomic) IBOutlet UILabel *symbolLabel;
@property (weak, nonatomic) IBOutlet UILabel *priceLabel;
@property (weak, nonatomic) IBOutlet UILabel *chgLabel;
@property (weak, nonatomic) IBOutlet UILabel *cnyLabel;
@property (nonatomic, strong) symbolModel *model;
@end
