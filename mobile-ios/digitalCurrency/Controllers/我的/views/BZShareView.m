//
//  BZShareView.m
//  digitalCurrency
//
//  Created by Lanzz on 2021/1/14.
//  Copyright © 2021 BIZZAN. All rights reserved.
//

#import "BZShareView.h"

static const CGFloat sheight = 330;
@interface BZShareView()

@property (nonatomic, strong) UIView *coverView;
@property (nonatomic, strong) UIView *contentView;
@property (nonatomic, strong) UILabel *titleLabel;
@property (nonatomic, strong) UIButton *cancelButton;
@property (nonatomic, strong) UIScrollView *scrollView;
@property (nonatomic, strong) UIButton *copyButton;
@property (nonatomic, strong) UIButton *saveButton;
@property (nonatomic, copy  ) NSString *url;
@property (nonatomic, strong) NSMutableArray *viewsArray;
@property (nonatomic, assign) NSInteger selectedIndex;
@property (nonatomic, strong) NSMutableArray *imgesArray;

@end

@implementation BZShareView

+ (void)showWithUrl:(NSString *)url {
    UIWindow *window = [UIApplication sharedApplication].delegate.window;
    BZShareView *popView = [[BZShareView alloc] initWithURL:url];
    [window addSubview:popView];
    [popView mas_makeConstraints:^(MASConstraintMaker *make) {
        make.edges.equalTo(window);
    }];
}

- (instancetype)initWithURL:(NSString *)url {
    if (self = [super init]) {
        self.selectedIndex = 1;
        self.url = url;
        [self.coverView mas_makeConstraints:^(MASConstraintMaker *make) {
            make.edges.equalTo(self);
        }];
        
        [self.contentView mas_makeConstraints:^(MASConstraintMaker *make) {
            make.bottom.equalTo(self);
            make.left.right.equalTo(self);
            make.height.equalTo(@(sheight + 40 + 46 + 20 + SafeAreaBottomHeight));
        }];
        
        [self.titleLabel mas_makeConstraints:^(MASConstraintMaker *make) {
            make.left.equalTo(self.contentView).offset(15);
            make.top.equalTo(self.contentView);
            make.height.equalTo(@40);
        }];
        
        [self.cancelButton mas_makeConstraints:^(MASConstraintMaker *make) {
            make.top.equalTo(self.contentView);
            make.right.equalTo(self.contentView).offset(-15);
            make.height.equalTo(self.titleLabel);
        }];
        
        UIView *lineView = [[UIView alloc] init];
        lineView.backgroundColor = UIColor.blackColor;
        [self.contentView addSubview:lineView];
        [lineView mas_makeConstraints:^(MASConstraintMaker *make) {
            make.top.equalTo(self.cancelButton.mas_bottom);
            make.left.right.equalTo(self.contentView);
            make.height.equalTo(@2);
        }];
        
        [self.scrollView mas_makeConstraints:^(MASConstraintMaker *make) {
            make.left.right.equalTo(self);
            make.top.equalTo(self.cancelButton.mas_bottom).offset(2);
            make.height.equalTo(@(sheight));
        }];
        
        UIView *lineView1 = [[UIView alloc] init];
        lineView1.backgroundColor = UIColor.blackColor;
        [self.contentView addSubview:lineView1];
        [lineView1 mas_makeConstraints:^(MASConstraintMaker *make) {
            make.top.equalTo(self.scrollView.mas_bottom);
            make.left.right.equalTo(self.contentView);
            make.height.equalTo(@2);
        }];
        
        [self.copyButton mas_makeConstraints:^(MASConstraintMaker *make) {
            make.left.equalTo(self.contentView).offset(10);
            make.top.equalTo(self.scrollView.mas_bottom).offset(10);
            make.height.equalTo(@46);
            make.right.equalTo(self.contentView.mas_centerX).offset(-5);
        }];
        
        [self.saveButton mas_makeConstraints:^(MASConstraintMaker *make) {
            make.right.equalTo(self.contentView).offset(-10);
            make.top.equalTo(self.scrollView.mas_bottom).offset(10);
            make.height.equalTo(@46);
            make.left.equalTo(self.contentView.mas_centerX).offset(5);
        }];
        dispatch_async(dispatch_get_global_queue(0, 0), ^{
            CIImage *codeCIImage = [self createQRForString:self.url];
            UIImage *codeImage = [self createNonInterpolatedUIImageFormCIImage:codeCIImage withSize:80];
            UIImage *new1 = [self addTwoImageToOne:[UIImage imageNamed:@"invite1"] twoImage:codeImage];
            UIImage *new2 = [self addTwoImageToOne:[UIImage imageNamed:@"invite2"] twoImage:codeImage];
            UIImage *new3 = [self addTwoImageToOne:[UIImage imageNamed:@"invite3"] twoImage:codeImage];
            [self.imgesArray addObject:new1];
            [self.imgesArray addObject:new2];
            [self.imgesArray addObject:new3];
            CGFloat w = 375 * 0.5 *0.84;
            CGFloat margin = 10;
            dispatch_async(dispatch_get_main_queue(), ^{
                for (int i = 0 ; i < self.imgesArray.count; i ++) {
                    UIImageView*imgView = [[UIImageView alloc] initWithFrame:CGRectMake(margin + (w+margin) *i , 20, w, sheight -40)];
                    imgView.userInteractionEnabled = YES;
                    imgView.tag = i + 1000;
                    imgView.image = self.imgesArray[i];
                    [_scrollView addSubview:imgView];
                    UITapGestureRecognizer *tap = [[UITapGestureRecognizer alloc] initWithTarget:self action:@selector(imageTapAction:)];
                    [imgView addGestureRecognizer:tap];
                    UIView *coverView = [[UIView alloc] initWithFrame:imgView.bounds];
                    coverView.backgroundColor = [[UIColor blackColor] colorWithAlphaComponent:0.4];
                    coverView.tag = i +2000;
                    [imgView addSubview:coverView];
                    [self.viewsArray addObject:imgView];
                    if (i == 1 ) {
                        imgView.top = 10;
                        coverView.hidden = YES;
                    }
                }
            });
        });
    }
    return  self;
}

- (void)copyButtonAction {
    UIPasteboard *pab = [UIPasteboard generalPasteboard];
    
    if (self.url.length!=0) {
        pab.string = self.url;
        [self makeToast:LocalizationKey(@"Copied") duration:1 position:CSToastPositionCenter];
    }
    if (pab == nil||self.url.length==0) {
        [self makeToast:LocalizationKey(@"copyFailed") duration:1 position:CSToastPositionCenter];
    }
}

- (void)saveButtonAction {
    [self saveImage: self.imgesArray[self.selectedIndex]];
}

- (void)cancelAction {
    [self removeFromSuperview];
}

- (void)imageTapAction:(UIGestureRecognizer *)ges {
    UIView *gesview = ges.view;
    self.selectedIndex = gesview.tag - 1000;
    for (UIView * imgView in self.viewsArray) {
         UIView *cover = [imgView viewWithTag:imgView.tag +1000];
        if (gesview.tag == imgView.tag) {
            imgView.top = 10;
            cover.hidden = YES;
        }else {
            imgView.top = 20;
            cover.hidden = NO;
        }
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

- (UIImage *)addTwoImageToOne:(UIImage *)oneImg twoImage:(UIImage *)twoImg {

    CGImageRef imgRef = oneImg.CGImage;
    CGFloat w = CGImageGetWidth(imgRef);
    CGFloat h = CGImageGetHeight(imgRef);
    UIGraphicsBeginImageContext(CGSizeMake(w, h));
    
    CGImageRef mgRef1 = twoImg.CGImage;
    CGFloat w1 = CGImageGetWidth(mgRef1);
    CGFloat h1 = CGImageGetHeight(mgRef1);
    
    [oneImg drawInRect:CGRectMake(0, 0, w, h)];
    [twoImg drawInRect:CGRectMake(w - w1 - 20, h - h1 - 20, w1, h1)];
    
    UIImage *resultImg = UIGraphicsGetImageFromCurrentImageContext();
    
    UIGraphicsEndImageContext();
    
    return resultImg;
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
        [self makeToast:LocalizationKey(@"savePhotoFailure") duration:1.5 position:CSToastPositionCenter];
    }
    else {
//        NSLog(@"保存图片成功");
        [self makeToast:LocalizationKey(@"savePhotoSuccess") duration:1.5 position:CSToastPositionCenter];
    }
}

- (UIView *)coverView {
    if (!_coverView) {
        _coverView = [[UIView alloc] init];
        _coverView.backgroundColor = [[UIColor blackColor] colorWithAlphaComponent:0.4];
        UITapGestureRecognizer *tap = [[UITapGestureRecognizer alloc] initWithTarget:self action:@selector(cancelAction)];
        [_coverView addGestureRecognizer:tap];
        [self addSubview:_coverView];
    }
    return _coverView;;
}

- (UIView *)contentView {
    if (!_contentView) {
        _contentView = [[UIView alloc] init];
        _contentView.backgroundColor = mainColor;
        [self addSubview:_contentView];
    }
    return _contentView;;
}

- (UILabel *)titleLabel {
    if (!_titleLabel) {
        _titleLabel = [[UILabel alloc] init];
        _titleLabel.text = LocalizationKey(@"shareBoard");
        _titleLabel.textColor = UIColor.whiteColor;
        _titleLabel.font = [UIFont systemFontOfSize:16];
        _titleLabel.textAlignment = NSTextAlignmentLeft;
        [self.contentView addSubview:_titleLabel];
    }
    return _titleLabel;
}

- (UIButton *)cancelButton {
    if (!_cancelButton) {
        _cancelButton = [[UIButton alloc] init];
        [_cancelButton setTitle:LocalizationKey(@"cancel") forState:UIControlStateNormal];
        [_cancelButton setTitleColor:baseColor forState:UIControlStateNormal];
        _cancelButton.titleLabel.font = [UIFont systemFontOfSize:16];
        [_cancelButton addTarget:self action:@selector(cancelAction) forControlEvents:UIControlEventTouchUpInside];
        [self.contentView addSubview:_cancelButton];
    }
    return _cancelButton;
}

- (UIScrollView *)scrollView {
    if (!_scrollView) {
        _scrollView = [[UIScrollView alloc] init];
        CGFloat w = 375 * 0.5 *0.84;
        CGFloat margin = 10;
        _scrollView.contentSize = CGSizeMake(w * 3 + margin * 4 , sheight);
        _scrollView.showsVerticalScrollIndicator = NO;
        _scrollView.showsHorizontalScrollIndicator = NO;
        [self.contentView addSubview:_scrollView];
    }
    return _scrollView;
}

- (UIButton *)copyButton {
    if (!_copyButton) {
        _copyButton = [[UIButton alloc] init];
        [_copyButton setTitle:LocalizationKey(@"Copylink") forState:UIControlStateNormal];
        [_copyButton setBackgroundColor:baseColor];
        [_copyButton setTitleColor:UIColor.whiteColor forState:UIControlStateNormal];
        _copyButton.titleLabel.font = [UIFont systemFontOfSize:16];
        [_copyButton addTarget:self action:@selector(copyButtonAction) forControlEvents:UIControlEventTouchUpInside];
        [self.contentView addSubview:_copyButton];
    }
    return _copyButton;
}

- (UIButton *)saveButton {
    if (!_saveButton) {
        _saveButton = [[UIButton alloc] init];
        [_saveButton setTitle:LocalizationKey(@"Save photo") forState:UIControlStateNormal];
        [_saveButton setBackgroundColor:baseColor];
        [_saveButton setTitleColor:UIColor.whiteColor forState:UIControlStateNormal];
        _saveButton.titleLabel.font = [UIFont systemFontOfSize:16];
        [_saveButton addTarget:self action:@selector(saveButtonAction) forControlEvents:UIControlEventTouchUpInside];
        [self.contentView addSubview:_saveButton];
    }
    return _saveButton;
}

- (NSMutableArray *)viewsArray {
    if (!_viewsArray) {
        _viewsArray = [NSMutableArray array];
    }
    return _viewsArray;
}

- (NSMutableArray *)imgesArray {
    if (!_imgesArray) {
        _imgesArray = [NSMutableArray array];
    }
    return _imgesArray;
}

@end
