//
//  UpdateIDCardViewController.m
//  digitalCurrency
//
//  Created by chu on 2019/8/9.
//  Copyright © 2019年 BIZZAN. All rights reserved.
//

#import "UpdateIDCardViewController.h"
#import <AVFoundation/AVFoundation.h>
#import <AssetsLibrary/AssetsLibrary.h>
@interface UpdateIDCardViewController ()<UIImagePickerControllerDelegate,UINavigationControllerDelegate>

@property (nonatomic, strong) UIScrollView *scrollView;

@property (nonatomic, assign) NSInteger btnTag;

//相机控制器
@property(nonatomic,strong)UIImagePickerController *imagePickerVC;
//返回的图片路径数组
@property (nonatomic,strong) NSMutableDictionary *imageDic;

@property (nonatomic, strong) NSMutableArray *btnsArr;

@end

@implementation UpdateIDCardViewController

- (NSMutableArray *)btnsArr{
    if (!_btnsArr) {
        _btnsArr = [NSMutableArray arrayWithCapacity:0];
    }
    return _btnsArr;
}

- (NSMutableDictionary *)imageDic{
    if (!_imageDic) {
        _imageDic = [NSMutableDictionary dictionaryWithCapacity:0];
    }
    return _imageDic;
}

- (void)viewDidLoad {
    [super viewDidLoad];
    // Do any additional setup after loading the view.
    self.view.backgroundColor = mainColor;
    self.title = LocalizationKey(@"Realnameauthentication");

    UIButton *btn = [UIButton buttonWithType:UIButtonTypeCustom];
    btn.frame = CGRectMake(0, 0, 30, 44);
    [btn setTitle:LocalizationKey(@"save") forState:UIControlStateNormal];
    [btn setTitleColor:RGBOF(0xF0A70A) forState:UIControlStateNormal];
    btn.titleLabel.font = [UIFont systemFontOfSize:14];
    [btn addTarget:self action:@selector(RighttouchEvent) forControlEvents:UIControlEventTouchUpInside];

    UIBarButtonItem*item=[[UIBarButtonItem alloc] initWithCustomView:btn];

    self.navigationItem.rightBarButtonItem = item;

    [self.view addSubview:self.scrollView];
    [self creatUI];
}

-(void)RighttouchEvent{
    if (self.imageDic.count < 3) {
        __weak typeof(self)weakself = self;
        [self addUIAlertControlWithString:@"您的照片没上传完，确定保存吗？" withActionBlock:^{
            [[AppDelegate sharedAppDelegate] popViewController];
            weakself.block(weakself.imageDic);
        } andCancel:^{

        }];
    }else{
        [[AppDelegate sharedAppDelegate] popViewController];
        if (self.block) {
            self.block(self.imageDic);
        }
    }
}

//MARK:--添加头像
-(void)changeHeaderImage{
    __block NSUInteger sourceType = 0;
    [LEEAlert actionsheet].config
    .LeeAddTitle(^(UILabel *label) {
        label.text = [[ChangeLanguage bundle] localizedStringForKey:@"projectNameTip" value:nil table:@"English"];
        label.textColor = RGBOF(0xe6e6e6);
        label.backgroundColor = mainColor;
    })
    .LeeAddContent(^(UILabel *label){
        label.text = LocalizationKey(@"certifyTakePhotoTip");
        label.textColor = RGBOF(0xe6e6e6);
        label.backgroundColor = mainColor;
    })
    .LeeHeaderColor(mainColor)
    .LeeAddAction(^(LEEAction *action) {
        action.type = LEEActionTypeDestructive;
        action.title = [[ChangeLanguage bundle] localizedStringForKey:@"takingPictures" value:nil table:@"English"];
        action.backgroundColor = mainColor;
        action.titleColor = RGBOF(0xe6e6e6);
        action.borderColor = mainColor;
        action.clickBlock = ^{
            //判断是否已授权
            if ([[[UIDevice currentDevice] systemVersion] floatValue] >= 7.0) {
                AVAuthorizationStatus authStatus = [AVCaptureDevice authorizationStatusForMediaType:AVMediaTypeVideo];
                if (authStatus == ALAuthorizationStatusDenied||authStatus == ALAuthorizationStatusRestricted) {
                    [self.view makeToast:[[ChangeLanguage bundle] localizedStringForKey:@"cameraPermissionsTips" value:nil table:@"English"] duration:1.5 position:CSToastPositionCenter];
                    return ;
                }
            }
            sourceType = UIImagePickerControllerSourceTypeCamera;
            if ([UIImagePickerController isSourceTypeAvailable:UIImagePickerControllerSourceTypeCamera]) {
                [self chooseImage:sourceType];
            }else{
                UIAlertController *alert = [UIAlertController alertControllerWithTitle:[[ChangeLanguage bundle] localizedStringForKey:@"tips" value:nil table:@"English"] message:[[ChangeLanguage bundle] localizedStringForKey:@"unSupportTakePhoto" value:nil table:@"English"] preferredStyle:UIAlertControllerStyleAlert];
                [alert addAction:[UIAlertAction actionWithTitle:[[ChangeLanguage bundle] localizedStringForKey:@"ok" value:nil table:@"English"] style:UIAlertActionStyleDefault handler:^(UIAlertAction * _Nonnull action) {
                }]];
                [self presentViewController:alert animated:YES completion:nil];
            }
        };
    }).LeeAddAction(^(LEEAction *action){
        action.type = LEEActionTypeDestructive;
        action.title = [[ChangeLanguage bundle] localizedStringForKey:@"photoAlbumSelect" value:nil table:@"English"];
        action.backgroundColor = mainColor;
        action.titleColor = RGBOF(0xe6e6e6);
        action.borderColor = mainColor;
        action.clickBlock = ^{
            //判断是否已授权
            if ([[[UIDevice currentDevice] systemVersion] floatValue] >= 7.0) {

                ALAuthorizationStatus authStatus = [ALAssetsLibrary authorizationStatus];
                if (authStatus == ALAuthorizationStatusDenied) {
                    [self.view makeToast:[[ChangeLanguage bundle] localizedStringForKey:@"cameraPermissionsTips" value:nil table:@"English"] duration:1.5 position:CSToastPositionCenter];
                    return;
                }
            }
            sourceType = UIImagePickerControllerSourceTypePhotoLibrary;
            [self chooseImage:sourceType];
        };
    }).LeeAddAction(^(LEEAction *action){
        action.type = LEEActionTypeCancel;
        action.title = [[ChangeLanguage bundle] localizedStringForKey:@"cancel" value:nil table:@"English"];
        action.titleColor = RGBOF(0xe6e6e6);
        action.backgroundColor = mainColor;
    })
    .LeeShow();


}
//从本地选择照片（拍照或从相册选择）
-(void)chooseImage:(NSInteger)sourceType
{
    //创建对象
    UIImagePickerController *imagePickerController = [[UIImagePickerController alloc]init];
    self.imagePickerVC = imagePickerController;
    //设置代理
    imagePickerController.delegate = self;
    //是否允许图片进行编辑
    imagePickerController.allowsEditing = YES;
    //选择图片还是开启相机
    imagePickerController.sourceType = sourceType;
    [self presentViewController:imagePickerController animated:YES completion:nil];
}

#pragma mark UIImagePickerController代理 已经选择了图片,上传到服务器中,返回上传结果
- (void)imagePickerController:(UIImagePickerController *)picker didFinishPickingMediaWithInfo:(NSDictionary<NSString *,id> *)info
{
    //选择图片
    UIImage *headImage = [info objectForKey:@"UIImagePickerControllerEditedImage"];
    NSData *imageData = UIImageJPEGRepresentation(headImage, 0.5);
    //将图片上传到服务器
    [EasyShowLodingView showLodingText:[[ChangeLanguage bundle] localizedStringForKey:@"upLoadingHeadPhoto" value:nil table:@"English"]];
    NSString *str=@"uc/upload/oss/image";
    NSString *urlString=[HOST stringByAppendingString:str];
    AFHTTPSessionManager *manager = [AFHTTPSessionManager manager];
    NSMutableDictionary *dic = [NSMutableDictionary new];
    dic[@"x-auth-token"] = [YLUserInfo shareUserInfo].token;
    dic[@"Content-Type"] = @"application/x-www-form-urlencoded";
    //接收类型不一致请替换一致text/html或别的
    manager.responseSerializer.acceptableContentTypes = [NSSet setWithObjects:@"application/json",
                                                         @"text/html",
                                                         @"image/jpeg",
                                                         @"image/png",
                                                         @"application/octet-stream",
                                                         @"text/json",
                                                         nil];
    [manager POST:urlString parameters:dic constructingBodyWithBlock:^(id<AFMultipartFormData>  _Nonnull formData) {
        NSData *imageDatas = imageData;
        NSDateFormatter *formatter = [[NSDateFormatter alloc] init];
        formatter.dateFormat = @"yyyyMMddHHmmss";
        NSString *str = [formatter stringFromDate:[NSDate date]];
        NSString *fileName = [NSString stringWithFormat:@"%@.jpg", str];
        //上传的参数(上传图片，以文件流的格式)
        [formData appendPartWithFileData:imageDatas
                                    name:@"file"
                                fileName:fileName
                                mimeType:@"image/jpeg"];
    } progress:^(NSProgress * _Nonnull uploadProgress) {
        //打印下上传进度
    } success:^(NSURLSessionDataTask * _Nonnull task, id  _Nullable responseObject) {
        [EasyShowLodingView hidenLoding];
        //上传成功
        if ([responseObject[@"code"] integerValue] == 0) {
            [self.view makeToast:[[ChangeLanguage bundle] localizedStringForKey:@"upLoadPictureSuccess" value:nil table:@"English"] duration:1.5 position:CSToastPositionCenter];
            UIButton *btn = [self.view viewWithTag:self.btnTag];
            btn.userInteractionEnabled = YES;
            if (self.btnTag == 11) {
                [self.imageDic setObject:responseObject[@"data"] forKey:@"idCardFront"];
                UIButton *btn = self.btnsArr[0];
                [btn setImage:headImage forState:UIControlStateNormal];
            }
            if (self.btnTag == 22) {
                [self.imageDic setObject:responseObject[@"data"] forKey:@"idCardBack"];
                UIButton *btn = self.btnsArr[1];
                [btn setImage:headImage forState:UIControlStateNormal];
            }
            if (self.btnTag == 33) {
                [self.imageDic setObject:responseObject[@"data"] forKey:@"handHeldIdCard"];
                UIButton *btn = self.btnsArr[2];
                [btn setImage:headImage forState:UIControlStateNormal];
            }
        }else if([responseObject[@"code"] integerValue] == 4000) {
            [ShowLoGinVC showLoginVc:self withTipMessage:responseObject[MESSAGE]];
        }else{
            [self.view makeToast:responseObject[MESSAGE] duration:1.5 position:CSToastPositionCenter];
        }
    } failure:^(NSURLSessionDataTask * _Nullable task, NSError * _Nonnull error) {
        [EasyShowLodingView hidenLoding];
        //上传失败
        [self.view makeToast:[[ChangeLanguage bundle] localizedStringForKey:@"upLoadPictureFailure" value:nil table:@"English"] duration:1.5 position:CSToastPositionCenter];
    }];
    [self dismissViewControllerAnimated:YES completion:nil];
}

-(void)viewWillAppear:(BOOL)animated{
    [super viewWillAppear:animated];

}

- (void)creatUI{
    UILabel *label = [[UILabel alloc] initWithFrame:CGRectMake(10, 20, kWindowW-20, 40)];
    label.textColor = RGBOF(0xe6e6e6);
    label.text = LocalizationKey(@"Take and upload photos");
    label.font = [UIFont systemFontOfSize:15];
    label.numberOfLines = 0;
    label.textAlignment = NSTextAlignmentCenter;
    [self.scrollView addSubview:label];

    NSArray *images = @[@"smrz_zhengmian", @"smrz_fanmian", @"smrz_shouchi"];
    for (int i = 0; i < 3; i++) {
        UIButton *btn = [UIButton buttonWithType:UIButtonTypeCustom];
        [btn setBackgroundImage:[UIImage imageNamed:images[i]] forState:UIControlStateNormal];
        btn.frame = CGRectMake((kWindowW - kWindowWHOne * 275) / 2, CGRectGetMaxY(label.frame) + 20 + i * kWindowWHOne * 180 + i * 25, kWindowWHOne * 275, kWindowWHOne * 180);
        [btn addTarget:self action:@selector(selActoin:) forControlEvents:UIControlEventTouchUpInside];
        btn.tag = i;
        if (i == 0) {
            btn.tag = 11;
        }else if (i == 1){
            btn.tag = 22;
        }else{
            btn.tag = 33;
        }
        [self.btnsArr addObject:btn];
        [self.scrollView addSubview:btn];
    }
    UIButton *btn = [self.btnsArr lastObject];

    UIButton *tipBtn = [UIButton buttonWithType:UIButtonTypeCustom];
    [tipBtn setTitle:LocalizationKey(@"Sample picture") forState:UIControlStateNormal];
    [tipBtn setTitleColor:RGBOF(0xF0A70A) forState:UIControlStateNormal];
    tipBtn.titleLabel.font = [UIFont systemFontOfSize:15];
    tipBtn.frame = CGRectMake(0, CGRectGetMaxY(btn.frame) + 20, kWindowW, 20);
    [tipBtn addTarget:self action:@selector(tipPicture) forControlEvents:UIControlEventTouchUpInside];
    [self.scrollView addSubview:tipBtn];

    self.scrollView.contentSize = CGSizeMake(kWindowW, CGRectGetMaxY(tipBtn.frame) + 20);
}

- (void)tipPicture{
    //创建数据源
    YBImageBrowserModel *model0 = [YBImageBrowserModel new];
    [model0 setImageWithFileName:@"WechatIMG2102" fileType:@"jpeg"];

    //创建图片浏览器
    YBImageBrowser *browser = [YBImageBrowser new];
    browser.dataArray = @[model0];
    browser.currentIndex = 0;
    [browser show];
}

- (void)selActoin:(UIButton *)sender{
    self.btnTag = sender.tag;
    [self changeHeaderImage];
}

- (UIScrollView *)scrollView{
    if (!_scrollView) {
        _scrollView = [[UIScrollView alloc] initWithFrame:CGRectMake(0, 10, kWindowW, kWindowH - NEW_NavHeight - 10)];
        _scrollView.backgroundColor = mainColor;
    }
    return _scrollView;
}


@end
