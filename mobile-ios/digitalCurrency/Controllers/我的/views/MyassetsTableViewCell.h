//
//  MyassetsTableViewCell.h
//  digitalCurrency
//
//  Created by startlink on 2019/8/6.
//  Copyright © 2019年 BIZZAN. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface MyassetsTableViewCell : UITableViewCell<UICollectionViewDelegate, UICollectionViewDataSource, UICollectionViewDelegateFlowLayout>

@property (weak, nonatomic) IBOutlet UIView *backview;
@property (weak, nonatomic) IBOutlet UILabel *titleview;
@property (weak, nonatomic) IBOutlet UICollectionView *collectionview;
@property (nonatomic,copy)void (^selectBlock)(NSInteger num);
@end
