//
//  ContractPingView.m
//  digitalCurrency
//
//  Created by chan on 2021/1/6.
//  Copyright © 2021 BIZZAN. All rights reserved.
//

#import "ContractPingView.h"
#import "OptionChartSegement.h"
#import "StepSlider.h"
#import "ContractExchangeManager.h"

static CGFloat const height = 310;

@interface ContractPingView() <StepSliderDelegate>

@property (nonatomic, strong) UIView *coverView;

@property (nonatomic, strong) UIView *contentView;

@property (nonatomic, strong) UILabel *titleLabel;

@property (nonatomic, strong) ContractRefreshModel *model;

@property (nonatomic, strong) UIButton *doneButton;

@property (nonatomic, strong) OptionChartSegement *segmentView;

@property (nonatomic, strong) UITextField *priceInput; //价格

@property (nonatomic, strong) UITextField *amountInput; //张数

@property (nonatomic, strong) StepSlider *slider;

@property (nonatomic, assign) BOOL isBuy;

@property (nonatomic, assign) NSInteger type;

@property (nonatomic,copy  ) NSString *contractId;

@property (nonatomic, strong) UITextField *price1Input; //价格

@property (nonatomic, strong) UIView *line;
@end

@implementation ContractPingView

- (void)dealloc {
    [[NSNotificationCenter defaultCenter] removeObserver:self];
}

+ (ContractPingView *)showWithModel:(ContractRefreshModel *)model  {
    UIWindow *window = [UIApplication sharedApplication].delegate.window;
    ContractPingView *popView = [[ContractPingView alloc] initWithFrame:window.bounds model:model];
    [window addSubview:popView];
    return popView;
}

- (void)setModel:(ContractRefreshModel *)model {
    _model = model;
    self.titleLabel.text = LocalizationKey(@"text_flat");
}

- (instancetype)initWithFrame:(CGRect)frame  model:(ContractRefreshModel *)model {
    if (self = [super initWithFrame:frame]) {
        self.model = model;
        self.type = 0;
        self.isBuy = [self.model.modelstr isEqualToString:@"buyid"];
        [self setUI];
        [[NSNotificationCenter defaultCenter]addObserver:self selector:@selector(keyboardWillShow:) name:UIKeyboardWillShowNotification object:nil];
        [[NSNotificationCenter defaultCenter]addObserver:self selector:@selector(keyboardWillHide:) name:UIKeyboardWillHideNotification object:nil];
        [self.coverView mas_makeConstraints:^(MASConstraintMaker *make) {
            make.edges.equalTo(self);
        }];
        [self.contentView mas_makeConstraints:^(MASConstraintMaker *make) {
            make.bottom.equalTo(self);
            make.left.right.equalTo(self);
            make.height.equalTo(@(height + SafeAreaBottomHeight));
        }];
        [self.titleLabel mas_makeConstraints:^(MASConstraintMaker *make) {
            make.left.right.equalTo(self.contentView);
            make.top.equalTo(self.contentView).offset(15);
        }];
    
        [self.contentView addSubview:self.segmentView];
        [self.priceInput mas_makeConstraints:^(MASConstraintMaker *make) {
            make.top.equalTo(self.segmentView.mas_bottom).offset(10);
            make.left.right.equalTo(self.segmentView);
            make.height.equalTo(@40);
        }];
        
        [self.amountInput mas_makeConstraints:^(MASConstraintMaker *make) {
            make.top.equalTo(self.priceInput.mas_bottom).offset(10);
            make.left.right.equalTo(self.segmentView);
            make.height.equalTo(@40);
        }];
        
        [self.slider mas_makeConstraints:^(MASConstraintMaker *make) {
            make.left.mas_equalTo(10);
            make.right.mas_equalTo(-10);
            make.top.equalTo(self.amountInput.mas_bottom).offset(10);
            make.height.mas_equalTo(40);
        }];
        
        [self.doneButton mas_makeConstraints:^(MASConstraintMaker *make) {
            make.top.equalTo(self.priceInput.mas_bottom).offset(110);
            make.left.equalTo(self).offset(10);
            make.right.equalTo(self).offset(-10);
            make.height.equalTo(@48);
        }];
        [self bringSubviewToFront:self.contentView];
        
        [self.price1Input mas_makeConstraints:^(MASConstraintMaker *make) {
            make.top.equalTo(self.priceInput.mas_bottom).offset(10);
            make.left.right.equalTo(self.segmentView);
            make.height.equalTo(@40);
        }];
        
        [self.line mas_makeConstraints:^(MASConstraintMaker *make) {
            make.top.equalTo(self.segmentView);
            make.left.right.equalTo(self.contentView);
            make.height.equalTo(@1);
        }];
    }
    return  self;
}

- (void)setUI {
    
    
}

-(void)keyboardWillShow:(NSNotification *)note {
    CGRect keyBoardRect=[note.userInfo[UIKeyboardFrameEndUserInfoKey] CGRectValue];
    [self.contentView mas_updateConstraints:^(MASConstraintMaker *make) {
        make.height.equalTo( @(height + SafeAreaBottomHeight + keyBoardRect.size.height));
    } ];
}
#pragma mark 键盘消失
-(void)keyboardWillHide:(NSNotification *)note {
    [self.contentView mas_updateConstraints:^(MASConstraintMaker *make) {
        make.height.equalTo( @(height + SafeAreaBottomHeight));
    } ];
}


- (void)cancelAction {
    [self removeFromSuperview];
}

- (void)doneAction {

    NSMutableDictionary *mdict = [NSMutableDictionary dictionary];
    if (self.type == 0) {
        if (self.priceInput.text.length == 0) {
            [self makeToast:LocalizationKey(@"input_price") duration:1.5 position:CSToastPositionCenter];
            return;
        }
    }

    if (self.amountInput.text.length == 0) {
        [self makeToast:LocalizationKey(@"input_number") duration:1.5 position:CSToastPositionCenter];
        return;
    }
    if (self.type == 0) {
        [mdict setObject:@(1) forKey:@"type"];
    }else if (self.type == 1)  {
        [mdict setObject:@(0) forKey:@"type"];
    }else {
        [mdict setObject:@(self.type) forKey:@"type"];
    }
  

//     //平仓时 多空应传相反
    [mdict setObject:@(_isBuy) forKey:@"direction"];


    if (self.type == 2) {
        [mdict setObject:self.priceInput.text forKey:@"triggerPrice"];
        [mdict setObject:self.price1Input.text.length == 0 ? @(0) : self.price1Input.text forKey:@"entrustPrice"];
    }else{
        [mdict setObject:@(0) forKey:@"triggerPrice"];
        [mdict setObject:self.type == 0? self.priceInput.text : @(0) forKey:@"entrustPrice"];
    }
    [mdict setObject:[NSString stringWithFormat:@"%ld",self.model.resfreshOne] forKey:@"leverage"];
    [mdict setObject:self.amountInput.text forKey:@"volume"];
    if (self.doneBlock) {
        self.doneBlock(mdict);
    }
    [self removeFromSuperview];
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
        _titleLabel.text = @" ";
        _titleLabel.textColor = UIColor.grayColor;
        _titleLabel.font = [UIFont systemFontOfSize:16];
        _titleLabel.textAlignment = NSTextAlignmentCenter;
        [self.contentView addSubview:_titleLabel];
    }
    return _titleLabel;
}

- (UIButton *)doneButton {
    if (!_doneButton) {
        _doneButton = [[UIButton alloc] init];
        NSString *title = self.isBuy ?  LocalizationKey(@"sell_ping"): LocalizationKey(@"buy_ping");
        [_doneButton setTitle:title forState:UIControlStateNormal];
        [_doneButton setTitleColor:UIColor.whiteColor forState:UIControlStateNormal];
        _doneButton.titleLabel.font = [UIFont systemFontOfSize:16];
        UIColor *bgColor = self.isBuy?  RedColor : GreenColor;
        [_doneButton setBackgroundColor:bgColor];
        [_doneButton addTarget:self action:@selector(doneAction) forControlEvents:UIControlEventTouchUpInside];
        [self.contentView addSubview:_doneButton];
    }
    return _doneButton;
}

- (OptionChartSegement *)segmentView {
    if (!_segmentView) {
        _segmentView = [[OptionChartSegement alloc] initWithFrame:CGRectMake(10, 50,self.frame.size.width-20, 40)
                                                            array:@[
                                                                LocalizationKey(@"text_limit_entrust"),
                                                                LocalizationKey(@"text_Market_entrust"),
                                                                LocalizationKey(@"text_plan_entrust")
                                                            ]];
        __weak typeof(self) weakself = self;
        _segmentView.didClick = ^(NSInteger index) {
            __weak typeof(weakself) strongself = weakself;
            strongself.type = index;
            [strongself reloadUIWithIndex:index];
        };
    }
    return _segmentView;
}
    
    
- (void)reloadUIWithIndex:(NSInteger)index {
    switch (index) {
        case 0:{
            self.priceInput.userInteractionEnabled = YES;
            self.priceInput.text = @"";
            self.priceInput.placeholder = LocalizationKey(@"entrustPrice");
            self.priceInput.textAlignment = NSTextAlignmentLeft;
            self.price1Input.hidden = YES;
            self.slider.hidden = NO;
            [self.amountInput mas_remakeConstraints:^(MASConstraintMaker *make) {
                make.top.equalTo(self.priceInput.mas_bottom).offset(10);
                make.left.right.equalTo(self.price1Input);
                make.height.equalTo(@40);
            }];
//            self.amountInput.text = @"";
//            [self.slider setIndex:0 animated:YES];
        }
            break;
        case 1:{
            self.priceInput.userInteractionEnabled = false;
            self.priceInput.text = LocalizationKey(@"you_pice");
            self.priceInput.textAlignment = NSTextAlignmentCenter;
            self.price1Input.hidden = YES;
            self.slider.hidden = NO;
            [self.amountInput mas_remakeConstraints:^(MASConstraintMaker *make) {
                make.top.equalTo(self.priceInput.mas_bottom).offset(10);
                make.left.right.equalTo(self.price1Input);
                make.height.equalTo(@40);
            }];
            
//            self.amountInput.text = @"";
//            [self.slider setIndex:0 animated:YES];
           
        }
            break;
        case 2:{
            
            self.priceInput.userInteractionEnabled = false;
            self.priceInput.text = @"";
            self.priceInput.placeholder = LocalizationKey(@"text_trigger_price_constract");
            self.priceInput.textAlignment = NSTextAlignmentLeft;
            self.price1Input.hidden = NO;
            self.price1Input.placeholder = LocalizationKey(@"default_pice");
            [self.amountInput mas_remakeConstraints:^(MASConstraintMaker *make) {
                make.top.equalTo(self.price1Input.mas_bottom).offset(10);
                make.left.right.equalTo(self.price1Input);
                make.height.equalTo(@40);
            }];
            self.slider.hidden = YES;
//            self.amountInput.text = @"";
//            [self.slider setIndex:0 animated:YES];
        }
            break;
            
        default:
            break;
    }
}

- (UITextField *)priceInput {
    if (!_priceInput) {
        _priceInput = [[UITextField alloc]init];
        _priceInput.textColor = UIColor.whiteColor;
        _priceInput.font = [UIFont systemFontOfSize:12.0 * kWindowWHOne];
        _priceInput.keyboardType = UIKeyboardTypeDecimalPad;
        _priceInput.textAlignment = NSTextAlignmentLeft;
        _priceInput.layer.borderWidth = 1;
        _priceInput.layer.borderColor = UIColor.grayColor.CGColor;
        _priceInput.layer.cornerRadius = 2;
        _priceInput.leftView = [[UIView alloc] initWithFrame:CGRectMake(0, 0, 10, 10)];
        _priceInput.leftViewMode = UITextFieldViewModeAlways;
        _priceInput.layer.masksToBounds = YES;
        _priceInput.placeholder = LocalizationKey(@"entrustPrice");
        [_priceInput setValue:[UIColor lightGrayColor] forKeyPath:@"placeholderLabel.textColor"];
        [self.contentView addSubview:_priceInput];
    }
    return _priceInput;
}

- (UITextField *)price1Input {
    if (!_price1Input) {
        _price1Input=[[UITextField alloc]init];
        _price1Input.textColor = UIColor.whiteColor;
        _price1Input.font = [UIFont systemFontOfSize:12.0 * kWindowWHOne];
        _price1Input.keyboardType = UIKeyboardTypeDecimalPad;
        _price1Input.textAlignment = NSTextAlignmentLeft;
        _price1Input.layer.borderWidth = 1;
        _price1Input.layer.borderColor = UIColor.grayColor.CGColor;
        _price1Input.layer.cornerRadius = 2;
        _price1Input.layer.masksToBounds = YES;
        _price1Input.hidden = YES;
        _price1Input.leftView = [[UIView alloc] initWithFrame:CGRectMake(0, 0, 10, 10)];
        _price1Input.leftViewMode = UITextFieldViewModeAlways;
        _price1Input.placeholder = LocalizationKey(@"entrustPrice");
        [_price1Input setValue:[UIColor lightGrayColor] forKeyPath:@"placeholderLabel.textColor"];
        [self.contentView addSubview:_price1Input];
    }
    return _price1Input;
}

- (UIView *)line {
    if (!_line) {
        _line = [[UIView alloc] init];
        _line.backgroundColor = UIColor.grayColor;
        [self.contentView addSubview:_line];
    }
    return _line;
}

- (UITextField *)amountInput {
    if (!_amountInput) {
        _amountInput=[[UITextField alloc]init];
        _amountInput.textColor = UIColor.whiteColor;
        _amountInput.font = [UIFont systemFontOfSize:12.0 * kWindowWHOne];
        _amountInput.keyboardType = UIKeyboardTypeNumberPad;
        _amountInput.textAlignment = NSTextAlignmentLeft;
        _amountInput.layer.borderWidth = 1;
        _amountInput.layer.borderColor = UIColor.grayColor.CGColor;
        _amountInput.layer.cornerRadius = 2;
        _amountInput.layer.masksToBounds = YES;
        _amountInput.leftView = [[UIView alloc] initWithFrame:CGRectMake(0, 0, 10, 10)];
        _amountInput.leftViewMode = UITextFieldViewModeAlways;
        UIView * rightView = [[UIView alloc] initWithFrame:CGRectMake(0, 0, 30, 30)];
        
        UILabel *label = [[UILabel alloc] initWithFrame:CGRectMake(0, 0, 30, 30)];
        label.text = LocalizationKey(@"zhang");
        label.font = [UIFont systemFontOfSize:13];
        label.textColor = UIColor.grayColor;
        [rightView addSubview:label];
        _amountInput.rightView = rightView;
        _amountInput.rightViewMode = UITextFieldViewModeAlways;
        [self.contentView addSubview:_amountInput];
    }
    return _amountInput;
}

- (StepSlider *)slider {
    if (!_slider) {
        _slider = [[StepSlider alloc]init];
        
        _slider.labelOrientation=StepSliderTextOrientationDown;
        _slider.labelOffset=5;
        _slider.delegate = self;
        _slider.trackHeight = 3;
        _slider.trackCircleRadius = 6;
        _slider.sliderCircleRadius = 6;
        _slider.trackColor= AppTextColor_E6E6E6;
        _slider.tintColor = self.isBuy? RedColor:GreenColor;
        _slider.sliderCircleImage = self.isBuy? [UIImage imageNamed:@"circularRed"] :[UIImage imageNamed:@"circularGreen"];
        [_slider setMaxCount:5];
        [_slider setIndex:0 animated:YES];
        _slider.backgroundColor = mainColor;
        _slider.labelFont = [UIFont systemFontOfSize:10.0 * kWindowWHOne];
        _slider.labels=@[@"0%",@"25%",@"50%",@"75%",@"100%"];
        [self.contentView addSubview:_slider];
    }
    return _slider;
}

- (void)getSliderValue:(CGFloat)sliderValue {
    NSLog(@"-------- %f",sliderValue/4 );
}

@end

