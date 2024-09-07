//
//  IdentityAuthenticationViewController.m
//  digitalCurrency
//
//  Created by iDog on 2019/2/7.
//  Copyright © 2019年 BIZZAN. All rights reserved.
//
#import "IdentityAuthenticationViewController.h"
#import "MineNetManager.h"

#import "IdentifyPersionInfoModel.h"
#import "UpdateIDCardViewController.h"
#import "UpVideoViewController.h"

@interface IdentityAuthenticationViewController ()
{
    UIButton *_rightBtn;
    NSString *_random;
    NSString *_videoUrl;
}
@property (weak, nonatomic) IBOutlet UITextField *nameTextField;
@property (weak, nonatomic) IBOutlet UITextField *IDNumTextField;

@property (nonatomic) NSInteger btnTag;

//返回的图片路径数组
@property (nonatomic,strong)NSMutableDictionary *imageDic;
@property(nonatomic,strong)IdentifyPersionInfoModel *personInfoModel;
@property (weak, nonatomic) IBOutlet UILabel *reasonTitle;
@property (weak, nonatomic) IBOutlet UIView *reasonView;
@property (weak, nonatomic) IBOutlet UIImageView *reasonImage;
@property (weak, nonatomic) IBOutlet NSLayoutConstraint *resonViewHeight;

//国际化需要
@property (weak, nonatomic) IBOutlet UILabel *nameLabel;
@property (weak, nonatomic) IBOutlet UILabel *IDNumLabel;

@property (weak, nonatomic) IBOutlet UIView *idcardView;
@property (weak, nonatomic) IBOutlet UILabel *idcardLabel;
@property (weak, nonatomic) IBOutlet UILabel *idcardUpdateLabel;
@property (weak, nonatomic) IBOutlet UILabel *bottomLabel;

@end

@implementation IdentityAuthenticationViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    self.view.backgroundColor = mainColor;
    self.title = [[ChangeLanguage bundle] localizedStringForKey:@"identityAuthentication" value:nil table:@"English"];
    [self setRightItem];
    [self.idcardView setUserInteractionEnabled:YES];
    UITapGestureRecognizer *tap = [[UITapGestureRecognizer alloc] initWithTarget:self action:@selector(pushUpdate:)];
    [self.idcardView addGestureRecognizer:tap];
    self.bottomLabel.text = LocalizationKey(@"realNamethreeConditions");
    self.bottomLabel.adjustsFontSizeToFitWidth = YES;
    self.imageDic = [[NSMutableDictionary alloc]init];
    self.IDNumTextField.keyboardType = UIKeyboardTypeASCIICapable;
    if ([self.identifyStatus isEqualToString:@"1"]) {
        //审核成功
        [self getIdentifyData];
        [self makeUI];

    }else{
        if ([self.realAuditing isEqualToString:@"1"]) {
            //审核中
            [self getIdentifyData];
            [self makeUI];
        }else{
            if (self.realNameRejectReason== nil) {
                //未提交认证
                [self makeUI];
            }else{
                //审核失败
                [self getIdentifyData];
                [self makeUI];
            }
        }
    }
    self.nameLabel.text = [[ChangeLanguage bundle] localizedStringForKey:@"name" value:nil table:@"English"];
    self.IDNumLabel.text = [[ChangeLanguage bundle] localizedStringForKey:@"IdCardNumber" value:nil table:@"English"];
    self.nameTextField.placeholder = [[ChangeLanguage bundle] localizedStringForKey:@"inputcertifyName" value:nil table:@"English"];
    self.IDNumTextField.placeholder = [[ChangeLanguage bundle] localizedStringForKey:@"inputcertifyIDNum" value:nil table:@"English"];
    self.idcardLabel.text = LocalizationKey(@"IdCardImage");

//    [_nameTextField setValue:RGBOF(0x999999) forKeyPath:@"_placeholderLabel.textColor"];
//    [_IDNumTextField setValue:RGBOF(0x999999) forKeyPath:@"_placeholderLabel.textColor"];

}

- (void)pushUpdate:(UITapGestureRecognizer *)tap{
    UpdateIDCardViewController *update = [[UpdateIDCardViewController alloc] init];

    update.block = ^(NSDictionary *dic) {
        [self.imageDic setDictionary:dic];
        if (dic.count > 2) {
            self.idcardUpdateLabel.text = LocalizationKey(@"Uploaded");
        }
    };
    [[AppDelegate sharedAppDelegate] pushViewController:update];
}


//-(void)videopush:(UITapGestureRecognizer *)tap{
//    UpVideoViewController *UpVideoVC = [UpVideoViewController new];
//    UpVideoVC.saveBlock = ^(NSString *videourl, NSString *randomnum) {
//        self.videouplabel.text = LocalizationKey(@"Uploaded");
//        _videoUrl = videourl;
//        _random = randomnum;
//    };
//    [self.navigationController pushViewController:UpVideoVC animated:YES];
//
//}

- (void)setRightItem{
    UIButton *btn = [UIButton buttonWithType:UIButtonTypeCustom];
    _rightBtn = btn;
    btn.frame = CGRectMake(0, 0, 30, 44);
    [btn setTitle:LocalizationKey(@"save") forState:UIControlStateNormal];
    [btn setTitleColor:RGBOF(0xF0A70A) forState:UIControlStateNormal];
    btn.titleLabel.font = [UIFont systemFontOfSize:14];
    [btn addTarget:self action:@selector(RighttouchEvent) forControlEvents:UIControlEventTouchUpInside];

    UIBarButtonItem*item=[[UIBarButtonItem alloc] initWithCustomView:btn];
    self.navigationItem.rightBarButtonItem = item;
}

-(void)RighttouchEvent{
    //开始认证按钮
    if ([self.nameTextField.text isEqualToString:@""]) {
        [self.view makeToast:[[ChangeLanguage bundle] localizedStringForKey:@"inputcertifyName" value:nil table:@"English"] duration:1.5 position:CSToastPositionCenter];
        return;
    }
    if ([self.IDNumTextField.text isEqualToString:@""]) {
        [self.view makeToast:[[ChangeLanguage bundle] localizedStringForKey:@"inputcertifyIDNum" value:nil table:@"English"] duration:1.5 position:CSToastPositionCenter];
        return;
    }
    if ([self.imageDic allKeys].count < 3) {
        [self.view makeToast:[[ChangeLanguage bundle] localizedStringForKey:@"upLoadPictureFailure" value:nil table:@"English"] duration:1.5 position:CSToastPositionCenter];
        return;
    }
    if ([_videoUrl isEqualToString:@""]) {
        [self.view makeToast:@"请上传视频" duration:1.5 position:CSToastPositionCenter];
        return;
    }
    [EasyShowLodingView showLodingText:[[ChangeLanguage bundle] localizedStringForKey:@"hardUpLoading" value:nil table:@"English"]];
    [MineNetManager identityAuthenticationRealName:self.nameTextField.text andIdCard:self.IDNumTextField.text andVideoUrl:_videoUrl andRandom:_random andCardDic:self.imageDic CompleteHandle:^(id resPonseObj, int code) {
        [EasyShowLodingView hidenLoding];
        if (code) {
            if ([resPonseObj[@"code"] integerValue]==0) {
                //获取数据成功
                [self.view makeToast:resPonseObj[MESSAGE] duration:1.0 position:CSToastPositionCenter];
                dispatch_after(dispatch_time(DISPATCH_TIME_NOW, (int64_t)(1.5 * NSEC_PER_SEC)), dispatch_get_main_queue(), ^{
                    [self.navigationController popViewControllerAnimated:YES];
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

//MARK:--获取身份认证的消息
-(void)getIdentifyData{
    [EasyShowLodingView showLodingText:[[ChangeLanguage bundle] localizedStringForKey:@"loading" value:nil table:@"English"]];
    [MineNetManager getIdentifyInfo:^(id resPonseObj, int code){
        [EasyShowLodingView hidenLoding];
        if (code) {
            if ([resPonseObj[@"code"] integerValue] == 0) {
                //获取数据成功
                self.personInfoModel = [IdentifyPersionInfoModel mj_objectWithKeyValues:resPonseObj[@"data"]];
                [self arrageData];
            }else{
                [self.view makeToast:resPonseObj[MESSAGE] duration:1.5 position:CSToastPositionCenter];
            }
        }else{
            [self.view makeToast:[[ChangeLanguage bundle] localizedStringForKey:@"noNetworkStatus" value:nil table:@"English"] duration:1.5 position:CSToastPositionCenter];
        }
    }];
}
//整理数据
-(void)arrageData{

    self.personInfoModel.identityCardImgInHand  = [self.personInfoModel.identityCardImgInHand  stringByAddingPercentEscapesUsingEncoding:NSUTF8StringEncoding];
    self.personInfoModel.identityCardImgReverse  = [self.personInfoModel.identityCardImgReverse  stringByAddingPercentEscapesUsingEncoding:NSUTF8StringEncoding];
    if ([self.identifyStatus isEqualToString:@"1"]) {
        //审核成功
        self.nameTextField.enabled = NO;
        self.IDNumTextField.enabled = NO;
        if (self.personInfoModel.realName.length == 2) {
            NSString *name = [self.personInfoModel.realName substringFromIndex:self.personInfoModel.realName.length-1];
             self.nameTextField.text = [NSString stringWithFormat:@"*%@",name];
        }else if (self.personInfoModel.realName.length == 4){
            NSString *name = [self.personInfoModel.realName substringFromIndex:self.personInfoModel.realName.length-1];
            self.nameTextField.text = [NSString stringWithFormat:@"***%@",name];
        }else{
            NSString *name = [self.personInfoModel.realName substringFromIndex:self.personInfoModel.realName.length-1];
            self.nameTextField.text = [NSString stringWithFormat:@"**%@",name];
        }
        NSString *backFourId = [self.personInfoModel.idCard substringFromIndex:self.personInfoModel.idCard.length-4];
        NSString *fontFourId = [self.personInfoModel.idCard substringToIndex:4];
         self.IDNumTextField.text = [NSString stringWithFormat:@"%@*********%@",fontFourId,backFourId];
    }else{
        if ([self.realAuditing isEqualToString:@"1"]) {
            //审核中
            self.nameTextField.enabled = NO;
            self.IDNumTextField.enabled = NO;
            self.nameTextField.text = self.personInfoModel.realName;
            self.IDNumTextField.text = self.personInfoModel.idCard;

        }else{
            if (self.realNameRejectReason== nil) {
                //未提交认证

            }else{
                //审核失败
                self.nameTextField.text = self.personInfoModel.realName;
                self.IDNumTextField.text = self.personInfoModel.idCard;


                [self.imageDic setObject:self.personInfoModel.identityCardImgFront forKey:@"idCardFront"];
                [self.imageDic setObject:self.personInfoModel.identityCardImgReverse forKey:@"idCardBack"];
                [self.imageDic setObject:self.personInfoModel.identityCardImgInHand forKey:@"handHeldIdCard"];

            }
        }
    }
}
//MARK:---修饰界面
-(void)makeUI{

    if ([self.identifyStatus isEqualToString:@"1"]) {
        //审核成功
         self.reasonTitle.text =[[ChangeLanguage bundle] localizedStringForKey:@"certifySuccessful" value:nil table:@"English"];
        self.resonViewHeight.constant = [self calculateRowHeight:self.reasonTitle.text fontSize:17]+40;
        self.reasonImage.image = [UIImage imageNamed:@""];
        self.reasonView.backgroundColor = kRGBColor(18, 22, 28);
        self.idcardUpdateLabel.text = LocalizationKey(@"Uploaded");
        self.idcardView.hidden = YES;
        self.bottomLabel.hidden = YES;
        _rightBtn.hidden = YES;
    }else{
        if ([self.realAuditing isEqualToString:@"1"]) {
            //审核中
            _rightBtn.hidden = YES;
            self.idcardView.hidden = YES;
            self.reasonTitle.text = [[ChangeLanguage bundle] localizedStringForKey:@"certifyAuditing" value:nil table:@"English"];
            self.resonViewHeight.constant = [self calculateRowHeight:self.reasonTitle.text fontSize:17]+40;
            self.reasonImage.image = [UIImage imageNamed:@"identityWaitImage"];
            self.reasonView.backgroundColor = kRGBColor(18, 22, 28);
            self.idcardUpdateLabel.text = LocalizationKey(@"Uploaded");
            self.bottomLabel.hidden = YES;
        }else{
            if (self.realNameRejectReason== nil) {
                //未提交认证
                self.resonViewHeight.constant = 0;
                self.reasonView.hidden = YES;
                self.idcardUpdateLabel.text = LocalizationKey(@"Notuploaded");

            }else{
                //审核失败
                self.reasonTitle.text = [NSString stringWithFormat:@"%@： \n%@",[[ChangeLanguage bundle] localizedStringForKey:@"certifyFailure" value:nil table:@"English"],self.realNameRejectReason];
                self.resonViewHeight.constant = [self calculateRowHeight:self.reasonTitle.text fontSize:17]+40;
                self.reasonImage.image = [UIImage imageNamed:@"careFullyImage"];
                self.reasonView.backgroundColor = kRGBColor(18, 22, 28);
                self.idcardUpdateLabel.text = LocalizationKey(@"Uploaded");
                self.bottomLabel.hidden = YES;

            }
        }
    }
}
//MARK--计算文字的高度
- (CGFloat)calculateRowHeight:(NSString *)string fontSize:(NSInteger)fontSize{
    NSDictionary *dic = @{NSFontAttributeName:[UIFont systemFontOfSize:fontSize]};//指定字号
    CGRect rect = [string boundingRectWithSize:CGSizeMake(self.reasonTitle.frame.size.width, 0)/*计算高度要先指定宽度*/ options:NSStringDrawingUsesLineFragmentOrigin |
                   NSStringDrawingUsesFontLeading attributes:dic context:nil];
    return rect.size.height;
}

-(void)viewWillAppear:(BOOL)animated{
    [super viewWillAppear:animated];

}


@end
