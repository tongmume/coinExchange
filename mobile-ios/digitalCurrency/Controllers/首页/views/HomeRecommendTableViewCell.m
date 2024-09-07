//
//  HomeRecommendTableViewCell.m
//  digitalCurrency
//
//  Created by chu on 2019/7/20.
//  Copyright © 2019年 BIZZAN. All rights reserved.
//

#import "HomeRecommendTableViewCell.h"
#import "KchatViewController.h"
#import "marketManager.h"
@implementation HomeRecommendTableViewCell

- (instancetype)initWithStyle:(UITableViewCellStyle)style reuseIdentifier:(NSString *)reuseIdentifier{
    if (self = [super initWithStyle:style reuseIdentifier:reuseIdentifier]) {
        UICollectionViewFlowLayout *layout = [[UICollectionViewFlowLayout alloc] init];
        layout.scrollDirection = UICollectionViewScrollDirectionHorizontal;
        self.backgroundColor = mainColor;
        self.collectionView = [[UICollectionView alloc] initWithFrame:self.contentView.bounds collectionViewLayout:layout];
        self.collectionView.delegate = self;
        self.collectionView.dataSource = self;
        self.collectionView.pagingEnabled = YES;
        self.collectionView.backgroundColor = mainColor;
        self.collectionView.showsHorizontalScrollIndicator = NO;

        [self.collectionView registerNib:[UINib nibWithNibName:@"HomeRecommendCollectionViewCell" bundle:nil] forCellWithReuseIdentifier:@"recommendCell"];
        [self.collectionView registerClass:[UICollectionViewCell class] forCellWithReuseIdentifier:@"collectionViewcell"];
        [self.contentView addSubview:self.collectionView];

        [self.collectionView mas_makeConstraints:^(MASConstraintMaker *make) {
            make.left.equalTo(self.contentView).with.offset(0);
            make.right.equalTo(self.contentView).with.offset(0);
            make.top.equalTo(self.contentView).with.offset(0);
            make.bottom.equalTo(self.contentView).with.offset(-20);
        }];

    }
    return self;
}


-(UIView *)backview{
    if (!_backview) {
        _backview = [[UIView alloc]initWithFrame:CGRectMake(0, 130, kWindowW, 20)];
        NSInteger num = self.dataSourceArr.count % 3 > 0 ? self.dataSourceArr.count / 3 + 1 : self.dataSourceArr.count / 3;
        for (int i = 0; i < num; i ++) {
            UILabel *linelabel = [[UILabel alloc]initWithFrame:CGRectMake(((kWindowW - (15 * num + 2 *(num - 1))) / 2 + 15 * i + 2 * (i - 1)), 9, 15, 2)];
            linelabel.tag = i;
            if (i == 0) {
                linelabel.backgroundColor = kRGBColor(241, 174, 31);
            }else{
                linelabel.backgroundColor = kRGBColor(230, 230, 230);
            }

            [_backview addSubview:linelabel];
        }
    }
    return _backview;
}

- (void)layoutSubviews{

}

- (void)awakeFromNib {
    [super awakeFromNib];
    self.dataSourceArr = @[];
}


- (NSInteger)numberOfSectionsInCollectionView:(UICollectionView *)collectionView{
    return 1;
}

- (NSInteger)collectionView:(UICollectionView *)collectionView numberOfItemsInSection:(NSInteger)section{

    if (self.dataSourceArr.count % 3 == 0) {
        return self.dataSourceArr.count;

    }else{
        return (self.dataSourceArr.count / 3 + 1) * 3;

    }

}

- (UICollectionViewCell *)collectionView:(UICollectionView *)collectionView cellForItemAtIndexPath:(NSIndexPath *)indexPath{
    if (indexPath.row < self.dataSourceArr.count) {
        HomeRecommendCollectionViewCell *cell = [collectionView dequeueReusableCellWithReuseIdentifier:@"recommendCell" forIndexPath:indexPath];
        if (!cell) {
            cell = [[HomeRecommendCollectionViewCell alloc] init];
        }
        cell.model = self.dataSourceArr[indexPath.item];
        return cell;
    }else{
        UICollectionViewCell *cell = [collectionView dequeueReusableCellWithReuseIdentifier:@"collectionViewcell" forIndexPath:indexPath];

        return cell;
    }



}

- (CGSize)collectionView:(UICollectionView *)collectionView layout:(UICollectionViewLayout*)collectionViewLayout sizeForItemAtIndexPath:(NSIndexPath *)indexPath{
    return CGSizeMake(kWindowW / 3,130);
}
- (UIEdgeInsets)collectionView:(UICollectionView *)collectionView layout:(UICollectionViewLayout*)collectionViewLayout insetForSectionAtIndex:(NSInteger)section{
    return UIEdgeInsetsMake(0, 0, 0, 0);
}
- (CGFloat)collectionView:(UICollectionView *)collectionView layout:(UICollectionViewLayout*)collectionViewLayout minimumLineSpacingForSectionAtIndex:(NSInteger)section{
    return 0;
}
- (CGFloat)collectionView:(UICollectionView *)collectionView layout:(UICollectionViewLayout*)collectionViewLayout minimumInteritemSpacingForSectionAtIndex:(NSInteger)section{
    return 0;
}


- (void)collectionView:(UICollectionView *)collectionView didSelectItemAtIndexPath:(NSIndexPath *)indexPath{
    symbolModel*model=self.dataSourceArr[indexPath.item];
    KchatViewController*klineVC=[[KchatViewController alloc]init];
    klineVC.symbol=model.symbol;
    [[AppDelegate sharedAppDelegate] pushViewController:klineVC withBackTitle:model.symbol];

}

- (void)setDataSourceArr:(NSArray *)dataSourceArr{
    _dataSourceArr = dataSourceArr;
    if (_dataSourceArr.count > 0) {
        symbolModel*model = [_dataSourceArr firstObject];
        if (![marketManager shareInstance].symbol) {
            [marketManager shareInstance].symbol=model.symbol;//默认第一个
        }

    }
    [self.contentView addSubview:self.backview];
    [self.collectionView reloadData];

}


- (void)scrollViewDidEndDecelerating:(UIScrollView *)scrollView{

    NSInteger num =  scrollView.contentOffset.x / kWindowW;

    NSArray *array = self.backview.subviews;
    for (UILabel *label in array) {
        if (label.tag == num) {
            label.backgroundColor = kRGBColor(241, 174, 31);
        }else{
            label.backgroundColor = kRGBColor(230, 230, 230);

        }
    }


}


@end
