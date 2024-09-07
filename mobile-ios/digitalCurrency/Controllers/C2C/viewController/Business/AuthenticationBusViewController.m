//
//  AuthenticationBusViewController.m
//  digitalCurrency
//
//  Created by startlink on 2019/8/10.
//  Copyright © 2019年 BIZZAN. All rights reserved.
//

#import "AuthenticationBusViewController.h"
#import "MineNetManager.h"
#import <AVFoundation/AVFoundation.h>
#import <AssetsLibrary/AssetsLibrary.h>
#import "BusscoinModel.h"
#import "YBPopupMenu.h"

@interface AuthenticationBusViewController ()<UIImagePickerControllerDelegate,UINavigationControllerDelegate,YBPopupMenuDelegate>
@property (weak, nonatomic) IBOutlet UILabel *phonelabel;
@property (weak, nonatomic) IBOutlet UITextField *phoneTF;
@property (weak, nonatomic) IBOutlet UILabel *wechatlabel;
@property (weak, nonatomic) IBOutlet UITextField *wechatTF;
@property (weak, nonatomic) IBOutlet UILabel *QQlabel;
@property (weak, nonatomic) IBOutlet UITextField *QQTF;
@property (weak, nonatomic) IBOutlet UILabel *Bondlabel;//保证金

@property (weak, nonatomic) IBOutlet UILabel *BondNumlabel;//保证金数量
@property (weak, nonatomic) IBOutlet UILabel *personProve;//个人资产证明
@property (weak, nonatomic) IBOutlet UILabel *NumProve;//数字资产证明
@property (weak, nonatomic) IBOutlet UIButton *presonProvebutton;//数字资产
@property (weak, nonatomic) IBOutlet UIButton *NumProvebutton;//交易资产
@property (weak, nonatomic) IBOutlet UIButton *Applynowbutton;//立即申请
@property (weak, nonatomic) IBOutlet UIButton *currencyButton;//币种

@property (weak, nonatomic) IBOutlet UILabel *BoodNum;//保证金个数

@property (nonatomic,copy)NSString *presonProveurl;//个人资产证明地址
@property (nonatomic,copy)NSString *transactionurl;//资产交易证明地址

@property (nonatomic,assign)BOOL ispreson;
@property (nonatomic,strong)NSMutableArray *bussarray;
@property (nonatomic,strong)NSMutableArray *titlearrays;

@property (nonatomic,strong)BusscoinModel *BusModel;
@end

@implementation AuthenticationBusViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    // Do any additional setup after loading the view from its nib.
    self.title = LocalizationKey(@"businessCertification");
    self.phonelabel.text = LocalizationKey(@"phone");
    self.phoneTF.placeholder = LocalizationKey(@"inputMobile");
    self.wechatlabel.text = LocalizationKey(@"WeChat");
    self.wechatTF.placeholder = LocalizationKey(@"inputWeChatNum");
    self.QQTF.placeholder = LocalizationKey(@"inputQQNum");
    self.Bondlabel.text = LocalizationKey(@"depositCurrency");
    self.BondNumlabel.text = LocalizationKey(@"marginAmount");
    self.personProve.text = LocalizationKey(@"assetCertificate");
    self.NumProve.text = LocalizationKey(@"tradingCertificate");
    [self.presonProvebutton setTitle:LocalizationKey(@"Notuploaded") forState:UIControlStateNormal];
    [self.NumProvebutton setTitle:LocalizationKey(@"Notuploaded") forState:UIControlStateNormal];
    [self.Applynowbutton setTitle:LocalizationKey(@"Applynow") forState:UIControlStateNormal];

    self.bussarray = [NSMutableArray array];
    self.titlearrays = [NSMutableArray array];
    self.Applynowbutton.layer.masksToBounds = YES;
    self.Applynowbutton.layer.cornerRadius = 22.5;
    [self approvebusinesslist];

//    [_phoneTF setValue:RGBOF(0x999999) forKeyPath:@"_placeholderLabel.textColor"];
//    [_wechatTF setValue:RGBOF(0x999999) forKeyPath:@"_placeholderLabel.textColor"];
//    [_QQTF setValue:RGBOF(0x999999) forKeyPath:@"_placeholderLabel.textColor"];
}

-(void)viewWillAppear:(BOOL)animated{
    [super viewWillAppear:animated];
//    [self setNavigationControllerStyle];
}


//获取抵押币种列表
-(void)approvebusinesslist{
    [EasyShowLodingView showLodingText:[[ChangeLanguage bundle] localizedStringForKey:@"loading" value:nil table:@"English"]];
    [MineNetManager approvebusinessauthdepositlist:^(id resPonseObj, int code) {
        [EasyShowLodingView hidenLoding];
        if (code) {
            if ([resPonseObj[@"code"] integerValue] == 0) {
                NSArray *arrays = resPonseObj[@"data"];
                for (NSDictionary *dic in arrays) {
                    BusscoinModel *model = [BusscoinModel mj_objectWithKeyValues:dic];
                    [self.bussarray addObject:model];
                    [self.titlearrays addObject:model.coin.unit];
                }
                    self.BusModel = self.bussarray[0];
                    [self.currencyButton setTitle:self.BusModel.coin.unit forState:UIControlStateNormal];
                    self.BoodNum.text = self.BusModel.amount;
            }else{
                [UIApplication.sharedApplication.keyWindow makeToast:resPonseObj[MESSAGE] duration:1.5 position:CSToastPositionCenter];
            }
        }else{
            [UIApplication.sharedApplication.keyWindow makeToast:[[ChangeLanguage bundle] localizedStringForKey:@"noNetworkStatus" value:nil table:@"English"] duration:1.5 position:CSToastPositionCenter];
        }
    }];
}


//必种选择
- (IBAction)currencyaction:(id)sender {


    CGRect absoluteRect = [self.currencyButton convertRect:self.currencyButton.bounds toView:[UIApplication sharedApplication].keyWindow];
    CGPoint relyPoint = CGPointMake(absoluteRect.origin.x + absoluteRect.size.width / 2, absoluteRect.origin.y + absoluteRect.size.height);
    [YBPopupMenu showAtPoint:relyPoint titles:self.titlearrays icons:nil menuWidth:80 otherSettings:^(YBPopupMenu *popupMenu) {
        popupMenu.arrowDirection = YBPopupMenuArrowDirectionNone;
        popupMenu.delegate = self;
        popupMenu.textColor = RGBOF(0xe6e6e6);
        popupMenu.backColor = kRGBColor(18, 22, 28);

    }];
}

#pragma mark - YBPopupMenuDelegate
- (void)ybPopupMenu:(YBPopupMenu *)ybPopupMenu didSelectedAtIndex:(NSInteger)index
{

    self.BusModel = self.bussarray[index];
    [self.currencyButton setTitle:self.BusModel.coin.unit forState:UIControlStateNormal];
    self.BoodNum.text = self.BusModel.amount;
}
//个人资产资产
- (IBAction)personproveaction:(id)sender {
    self.ispreson = YES;
    [self changeHeaderImage];
}


//交易证明
- (IBAction)Numproveaction:(id)sender {
    self.ispreson = NO;
    [self changeHeaderImage];
}



//MARK:--添加证明
-(void)changeHeaderImage{
    __block NSUInteger sourceType = 0;

    [LEEAlert actionsheet].config
    .LeeHeaderColor(mainColor)
    .LeeAddAction(^(LEEAction *action) {
        action.type = LEEActionTypeDestructive;
        action.title = [[ChangeLanguage bundle] localizedStringForKey:@"takingPictures" value:nil table:@"English"];
        action.backgroundColor = mainColor;
        action.titleColor = RGBOF(0xe6e6e6);
        action.borderColor = kRGBColor(18, 22, 28);
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
        action.borderColor = kRGBColor(18, 22, 28);
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
            //[self.view makeToast:@"上传图片成功" duration:1.5 position:CSToastPositionCenter];
            if (self.ispreson) {
                self.presonProveurl = responseObject[@"data"];
                [self.presonProvebutton setTitle:LocalizationKey(@"Uploaded") forState:UIControlStateNormal];
                 [self.presonProvebutton setTitleColor:[UIColor blackColor] forState:UIControlStateNormal];
            }else{
                self.transactionurl = responseObject[@"data"];
                [self.NumProvebutton setTitle:LocalizationKey(@"Uploaded") forState:UIControlStateNormal];
                [self.NumProvebutton setTitleColor:[UIColor blackColor] forState:UIControlStateNormal];

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

//立即申请
- (IBAction)ApplyNowaction:(id)sender {
    if ([NSString stringIsNull:self.phoneTF.text]) {
        [self.view makeToast:LocalizationKey(@"inputMobile") duration:1.5 position:CSToastPositionCenter];
        return;
    }

    if ([NSString stringIsNull:self.wechatTF.text]) {
        [self.view makeToast:LocalizationKey(@"wechatNumber") duration:1.5 position:CSToastPositionCenter];
        return;
    }

    if ([NSString stringIsNull:self.QQTF.text]) {
        [self.view makeToast:LocalizationKey(@"qqNumber") duration:1.5 position:CSToastPositionCenter];
        return;
    }

    if ([NSString stringIsNull:self.presonProveurl]) {
        [self.view makeToast:LocalizationKey(@"uploadAsset") duration:1.5 position:CSToastPositionCenter];
        return;
    }
    if ([NSString stringIsNull:self.presonProveurl]) {
        [self.view makeToast:LocalizationKey(@"uploadTransacrion") duration:1.5 position:CSToastPositionCenter];
        return;
    }

    NSMutableDictionary *bodydic = [NSMutableDictionary dictionary];
    [bodydic setValue:self.phoneTF.text forKey:@"telno"];
    [bodydic setValue:self.wechatTF.text forKey:@"wechat"];
    [bodydic setValue:self.QQTF.text forKey:@"qq"];
    [bodydic setValue:self.BusModel.coin.unit forKey: @"coinSymbol"];
    [bodydic setValue:self.BusModel.amount  forKey: @"amount"];
    [bodydic setValue:self.QQTF.text forKey:@"qq"];
    [bodydic setValue:self.presonProveurl forKey:@"assetData"];
    [bodydic setValue:self.transactionurl forKey:@"tradeData"];

    NSDictionary *jsondic = @{@"json" : [self dictionaryToJson:bodydic],@"businessAuthDepositId" : self.BusModel.ID};
    [EasyShowLodingView showLodingText:[[ChangeLanguage bundle] localizedStringForKey:@"loading" value:nil table:@"English"]];
    [MineNetManager approvecertifiedbusinessParam:jsondic CompleteHandle:^(id resPonseObj, int code) {
        [EasyShowLodingView hidenLoding];
        if (code) {
            if ([resPonseObj[@"code"] integerValue]==0) {
               [self.view makeToast:LocalizationKey(@"submittedSuccessfully") duration:1.5 position:CSToastPositionCenter];
                dispatch_after(dispatch_time(DISPATCH_TIME_NOW, (int64_t)(1.5 * NSEC_PER_SEC)), dispatch_get_main_queue(), ^{
                    [self.navigationController popToRootViewControllerAnimated:YES];

                });

            }else if ([resPonseObj[@"code"] integerValue] == 3000 ||[resPonseObj[@"code"] integerValue] == 4000 ){
                //[ShowLoGinVC showLoginVc:self withTipMessage:resPonseObj[MESSAGE]];
                [YLUserInfo logout];
            }else{
                [self.view makeToast:resPonseObj[MESSAGE] duration:1.5 position:CSToastPositionCenter];

            }
        }else{
            [self.view makeToast:[[ChangeLanguage bundle] localizedStringForKey:@"noNetworkStatus" value:nil table:@"English"] duration:1.5 position:CSToastPositionCenter];
        }
    }];
}

- (NSString*)dictionaryToJson:(NSDictionary *)dic

{

    NSError *parseError = nil;

    NSData *jsonData = [NSJSONSerialization dataWithJSONObject:dic options:NSJSONWritingPrettyPrinted error:&parseError];

    return [[NSString alloc] initWithData:jsonData encoding:NSUTF8StringEncoding];

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
