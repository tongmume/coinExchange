//
//  ChargeMoneyViewController.m
//  digitalCurrency
//
//  Created by iDog on 2019/2/7.
//  Copyright © 2019年 BIZZAN. All rights reserved.
//

#import "ChargeMoneyViewController.h"
#import "MineNetManager.h"
#import "WalletManageModel.h"

@interface ChargeMoneyViewController ()

@property (weak, nonatomic) IBOutlet UILabel *coinAddressType;//货币地址类型
@property (weak, nonatomic) IBOutlet UIImageView *QRCodeImage;//二维码图片
@property (weak, nonatomic) IBOutlet UILabel *coinAddress;//地址
@property (weak, nonatomic) IBOutlet UIButton *photoAlbumButton;//相册按钮
@property (weak, nonatomic) IBOutlet UIButton *addressButton;//复制地址按钮

@property(nonatomic,strong)UIImage *codeImage;
@property (weak, nonatomic) IBOutlet UILabel *contentlabel;
@property (weak, nonatomic) IBOutlet NSLayoutConstraint *topheight;
@property(nonatomic,strong)WalletManageModel *clickModel;
@property (weak, nonatomic) IBOutlet UILabel *memolable;
@property (weak, nonatomic) IBOutlet UIButton *memonumberlable;

@property (weak, nonatomic) IBOutlet NSLayoutConstraint *memonumber;
@property (weak, nonatomic) IBOutlet NSLayoutConstraint *memo;


@end

@implementation ChargeMoneyViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    [self.photoAlbumButton setTitle:LocalizationKey(@"savePhoto") forState:UIControlStateNormal];
    [self.addressButton setTitle:LocalizationKey(@"copyAddress") forState:UIControlStateNormal];
    self.memolable.text = LocalizationKey(@"charge_money_attention");
    self.unit = [self.unit uppercaseString];
    self.title = [NSString stringWithFormat:@"%@",[[ChangeLanguage bundle] localizedStringForKey:@"chargeMoney" value:nil table:@"English"]];
    if ([self.accountType isEqualToString:@"0"]) {
        if ([self.address isEqualToString:@""] || self.address == nil) {
            _memo.constant = 0;
            _memonumber.constant = 0;
            _memonumberlable.hidden = YES;
            _memolable.hidden = YES;
            _contentlabel.text = [NSString stringWithFormat:@"• %@：%@ %@，%@\n• %@\n• %@\n• %@\n•  %@",LocalizationKey(@"rush1"),self.model.coin.minRechargeAmount,self.model.coin.unit,LocalizationKey(@"rush2"),LocalizationKey(@"rush3"),LocalizationKey(@"rush4"),LocalizationKey(@"rush5"),LocalizationKey(@"rush6")];
            [self getassetwalletresetaddress];
            
        }else{
            self.address = self.model.address;
            _memo.constant = 0;
            _memonumber.constant = 0;
            _memonumberlable.hidden = YES;
            _memolable.hidden = YES;
            _contentlabel.text = [NSString stringWithFormat:@"• %@：%@ %@，%@\n• %@\n• %@\n• %@\n•  %@",LocalizationKey(@"rush1"),self.model.coin.minRechargeAmount,self.model.coin.unit,LocalizationKey(@"rush2"),LocalizationKey(@"rush3"),LocalizationKey(@"rush4"),LocalizationKey(@"rush5"),LocalizationKey(@"rush6")];
            [self erciload];
            //            CIImage *codeCIImage = [self createQRForString:self.address];
            //            self.codeImage = [self createNonInterpolatedUIImageFormCIImage:codeCIImage withSize:200];
            //            self.QRCodeImage.image = self.codeImage;
            //            self.coinAddress.text = self.address;
        }
    }else{
        if (self.model.coin.depositAddress == nil || [self.model.coin.depositAddress isEqualToString:@""]) {
            [self.view makeToast:LocalizationKey(@"no_rush")];
        }else{
            self.address = self.model.coin.depositAddress;
            _memo.constant = 0;
            _memonumber.constant = 0;
            _memonumberlable.hidden = YES;
            _memolable.hidden = YES;
            _contentlabel.text = [NSString stringWithFormat:@"• %@：%@ %@，%@\n• %@\n• %@\n• %@\n•  %@",LocalizationKey(@"rush1"),self.model.coin.minRechargeAmount,self.model.coin.unit,LocalizationKey(@"rush2"),LocalizationKey(@"rush3"),LocalizationKey(@"rush4"),LocalizationKey(@"rush5"),LocalizationKey(@"rush6")];
            [self erciload];
        }
    }
    
    if (kWindowW == 320 ) {
        self.topheight.constant =20;
        
    }
    
}
-(void) erciload{
    self.title = [NSString stringWithFormat:@"%@%@",self.model.coin.unit,LocalizationKey(@"chargeMoney")];
    self.coinAddressType.text =[NSString stringWithFormat:@"%@%@",self.model.coin.unit,LocalizationKey(@"Address")];
    if ([self.model.coin.accountType isEqualToString:@"0"]) {
        self.coinAddress.text = self.model.address;
        _memo.constant = 0;
        _memonumber.constant = 0;
        _memonumberlable.hidden = YES;
        _memolable.hidden = YES;
    }else{
        _memo.constant = 29;
        _memonumber.constant = 17;
        _memonumberlable.hidden = NO;
        _memolable.hidden = NO;
        self.
        self.coinAddress.text = self.model.coin.depositAddress;
        [self.memonumberlable setTitle:self.model.memo forState:UIControlStateNormal];
    }
    if (self.model == nil) {
        self.coinAddress.text = LocalizationKey(@"unChargeMoneyTip1");
        return;
    }
    if (self.model.coin == nil) {
        self.coinAddress.text = LocalizationKey(@"unChargeMoneyTip1");
        return;
    }
    if ([self.model.coin.accountType isEqualToString:@"0"] && self.model.address == nil) {
        self.coinAddress.text = LocalizationKey(@"unChargeMoneyTip1");
        return;
    }
    if ([self.model.coin.accountType isEqualToString:@"1"] && self.model.coin.depositAddress == nil) {
        self.coinAddress.text = LocalizationKey(@"unChargeMoneyTip1");
        return;
    }
    if ([self.model.coin.accountType isEqualToString:@"0"]) {
        if (self.model.address == nil || [self.model.address isEqualToString:@""]) return;
        CIImage *codeCIImage = [self createQRForString:self.model.address];
        self.codeImage = [self createNonInterpolatedUIImageFormCIImage:codeCIImage withSize:200];
    }
    if ([self.model.coin.accountType isEqualToString:@"1"]) {
        if (self.model.coin.depositAddress == nil || [self.model.coin.depositAddress isEqualToString:@""]) return;
        CIImage *codeCIImage = [self createQRForString:self.model.address];
        self.codeImage = [self createNonInterpolatedUIImageFormCIImage:codeCIImage withSize:200];
    }
    self.QRCodeImage.image = self.codeImage;
    self.coinAddress.text = self.address;
}

//获取提币地址
-(void)getassetwalletresetaddress{
    
    [EasyShowLodingView showLodingText:LocalizationKey(@"loading")];
    [MineNetManager getassetwalletresetaddress:@{@"unit":self.unit} CompleteHandle:^(id resPonseObj, int code) {
        [EasyShowLodingView hidenLoding];
        if (code) {
            if ([resPonseObj[@"code"] integerValue] == 0) {
                [self getData];
            }else{
                [self.view makeToast:resPonseObj[MESSAGE] duration:1.5 position:CSToastPositionCenter];
            }
        }else{
            [self.view makeToast:LocalizationKey(@"noNetworkStatus") duration:1.5 position:CSToastPositionCenter];
        }
    }];
    
}

//MARK:---获取我的钱包所有数据
-(void)getData{
    [MineNetManager getMyWalletInfoForCompleteHandle:^(id resPonseObj, int code) {
        
        if (code) {
            if ([resPonseObj[@"code"] integerValue] == 0) {
                NSArray *dataArr = [WalletManageModel mj_objectArrayWithKeyValuesArray:resPonseObj[@"data"]];
                
                for (WalletManageModel *model in dataArr) {
                    if ([model.coin.unit isEqualToString:self.unit]) {
                        self.address = model.address;
                        [self erciload];
                    }
                }
                
                if ([self.address isEqualToString:@""] || self.address == nil) {
                    
                    self.QRCodeImage.image = [UIImage imageNamed:@"emptyData"];
                    self.coinAddress.text = [[ChangeLanguage bundle] localizedStringForKey:@"unChargeMoneyTip1" value:nil table:@"English"];
                    self.photoAlbumButton.hidden = YES;
                    self.addressButton.hidden = YES;
                    
                }
                
            }else{
                [self.view makeToast:resPonseObj[MESSAGE] duration:1.5 position:CSToastPositionCenter];
            }
        }else{
            [self.view makeToast:LocalizationKey(@"noNetworkStatus") duration:1.5 position:CSToastPositionCenter];
        }
    }];
}



-(void)viewWillAppear:(BOOL)animated{
    [super viewWillAppear:animated];
    //    [self setNavigationControllerStyle];
    
}
//MARK:--按钮的点击事件
- (IBAction)btnClick:(UIButton *)sender {
    if (sender.tag == 1) {
        //保存相册按钮点击事件
        if ([self.address isEqualToString:@""] || self.address == nil) {
            [self.view makeToast:[[ChangeLanguage bundle] localizedStringForKey:@"unChargeMoneyTip2" value:nil table:@"English"] duration:1.5 position:CSToastPositionCenter];
            return;
        }
        [self saveImage:self.codeImage];
    }else if (sender.tag == 2){
        //复制地址按钮的点击事件
        if ([self.address isEqualToString:@""] || self.address == nil) {
            [self.view makeToast:[[ChangeLanguage bundle] localizedStringForKey:@"unChargeMoneyTip3" value:nil table:@"English"] duration:1.5 position:CSToastPositionCenter];
            return;
        }
        UIPasteboard *pasteboard = [UIPasteboard generalPasteboard];
        pasteboard.string = self.address;
        [self.view makeToast:[[ChangeLanguage bundle] localizedStringForKey:@"copyscuuses" value:nil table:@"English"] duration:1.5 position:CSToastPositionCenter];
    }else if (sender.tag == 3){
        //复制memo按钮的点击事件
        if ([self.address isEqualToString:@""] || self.address == nil) {
            [self.view makeToast:[[ChangeLanguage bundle] localizedStringForKey:@"unChargeMoneyTip3" value:nil table:@"English"] duration:1.5 position:CSToastPositionCenter];
            return;
        }
        UIPasteboard *pasteboard = [UIPasteboard generalPasteboard];
        pasteboard.string = self.memonumberlable.titleLabel.text;
        [self.view makeToast:[[ChangeLanguage bundle] localizedStringForKey:@"copyscuuses" value:nil table:@"English"] duration:1.5 position:CSToastPositionCenter];
    }
}
//image是要保存的图片
- (void) saveImage:(UIImage *)image{
    if (image) {
        UIImageWriteToSavedPhotosAlbum(image, self, @selector(savedPhotoImage:didFinishSavingWithError:contextInfo:), nil);
    };
}
//保存完成后调用的方法
- (void)savedPhotoImage:(UIImage*)image didFinishSavingWithError: (NSError *)error contextInfo: (void *)contextInfo {
    if (error) {
        [self.view makeToast:[[ChangeLanguage bundle] localizedStringForKey:@"savePhotoFailure" value:nil table:@"English"] duration:1.5 position:CSToastPositionCenter];
    }
    else {
        [self.view makeToast:[[ChangeLanguage bundle] localizedStringForKey:@"savePhotoSuccess" value:nil table:@"English"] duration:1.5 position:CSToastPositionCenter];
    }
}
//MARK:--字符串生成二维码
- (CIImage *)createQRForString:(NSString *)qrString {
    NSData *stringData = [qrString dataUsingEncoding:NSUTF8StringEncoding];
    // 创建filter
    CIFilter *qrFilter = [CIFilter filterWithName:@"CIQRCodeGenerator"];
    // 设置内容和纠错级别
    [qrFilter setValue:stringData forKey:@"inputMessage"];
    [qrFilter setValue:@"M" forKey:@"inputCorrectionLevel"];
    // 返回CIImage
    return qrFilter.outputImage;
}
- (UIImage *)createNonInterpolatedUIImageFormCIImage:(CIImage *)image withSize:(CGFloat) size {
    CGRect extent = CGRectIntegral(image.extent);
    CGFloat scale = MIN(size/CGRectGetWidth(extent), size/CGRectGetHeight(extent));
    // 1.创建bitmap;
    size_t width = CGRectGetWidth(extent) * scale;
    size_t height = CGRectGetHeight(extent) * scale;
    CGColorSpaceRef cs = CGColorSpaceCreateDeviceGray();
    CGContextRef bitmapRef = CGBitmapContextCreate(nil, width, height, 8, 0, cs, (CGBitmapInfo)kCGImageAlphaNone);
    CIContext *context = [CIContext contextWithOptions:nil];
    CGImageRef bitmapImage = [context createCGImage:image fromRect:extent];
    CGContextSetInterpolationQuality(bitmapRef, kCGInterpolationNone);
    CGContextScaleCTM(bitmapRef, scale, scale);
    CGContextDrawImage(bitmapRef, extent, bitmapImage);
    // 2.保存bitmap到图片
    CGImageRef scaledImage = CGBitmapContextCreateImage(bitmapRef);
    CGContextRelease(bitmapRef);
    CGImageRelease(bitmapImage);
    //原图
    UIImage *outputImage = [UIImage imageWithCGImage:scaledImage];
    UIGraphicsBeginImageContextWithOptions(outputImage.size, NO, [[UIScreen mainScreen] scale]);
    [outputImage drawInRect:CGRectMake(0,0 , size, size)];
    //水印图
    UIImage *waterimage = [UIImage imageNamed:@""];
    [waterimage drawInRect:CGRectMake((size-waterimage.size.width)/2.0, (size-waterimage.size.height)/2.0, waterimage.size.width, waterimage.size.height)];
    UIImage *newPic = UIGraphicsGetImageFromCurrentImageContext();
    UIGraphicsEndImageContext();
    return newPic;
}
- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}
/*
 #pragma mark - Navigation
 
 // In a storyboard-based application, you will often want to do a little preparation before navigation
 - (void)prepareForSegue:(UIStoryboardSegue *)segue sender:(id)sender {
 // Get the new view controller using [segue destinationViewController].
 // Pass the selected object to the new view controller.
 }
 */

@end
