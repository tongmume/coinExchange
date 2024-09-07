//
//  LeveragesView.m
//  digitalCurrency
//
//  Created by ios on 2020/9/18.
//  Copyright © 2020 BIZZAN. All rights reserved.
//

#import "LeveragesView.h"
#import "StepSlider.h"

@interface LeveragesView (){
    
    NSInteger _selectindex;
    
    
}

@property (nonatomic, strong) UIView *touchBgView;

@property (nonatomic, strong) UIScrollView *myScrollView;

@property (nonatomic, strong) UIView *mySliderView;

//@property (nonatomic, strong) UIScrollView *mySliderView;
@property (nonatomic,strong) UILabel *titlelabel;

@property (nonatomic, strong) UIView *bottomView;

@property (nonatomic, strong) UIView *moveView;

@property (strong, nonatomic) StepSlider *slider;

@end

@implementation LeveragesView

-(instancetype)initTitles:(NSArray *)titles{
    
    _titles=titles;
    return [self init];
}

-(instancetype)init{
    
    self=[super init];
      
      if (self) {
          self.backgroundColor=[UIColor clearColor];
           _touchBgView=[self touchBgView];
          
           _bottomView=[[UIView alloc]initWithFrame:CGRectMake(0,SCREEN_HEIGHT_S,SCREEN_WIDTH_S,130)];
           _bottomView.backgroundColor=mainColor;
           [self addSubview:_bottomView];
                   
           _myScrollView =[[UIScrollView alloc]initWithFrame:CGRectMake(0,40,SCREEN_WIDTH_S,_bottomView.frame.size.height-80)];
          _myScrollView.showsHorizontalScrollIndicator=NO;
           [_bottomView addSubview:_myScrollView];
          
          _mySliderView =[[UIView alloc]initWithFrame:CGRectMake(0,40,SCREEN_WIDTH_S,_bottomView.frame.size.height-80)];
//          _mySliderView.showsHorizontalScrollIndicator=NO;
           [_bottomView addSubview:_mySliderView];
          
          
          
           UITapGestureRecognizer *tap = [[UITapGestureRecognizer alloc] initWithTarget:self action:@selector(touchRemoveView)];
           [_touchBgView addGestureRecognizer:tap];
                 
           UIView *line=[[UIView alloc]initWithFrame:CGRectMake(0,_bottomView.frame.size.height-41,SCREEN_WIDTH_S,1)];
           line.backgroundColor=AppTextColor_E6E6E6;
           [_bottomView addSubview:line];
         
           
           UIButton *btn=[UIButton buttonWithType:UIButtonTypeCustom];
           [btn setTitle:LocalizationKey(@"cancel") forState:UIControlStateNormal];
           [btn setTitleColor:AppTextColor_Level_3 forState:UIControlStateNormal];
           [btn addTarget:self action:@selector(touchRemoveView) forControlEvents:UIControlEventTouchUpInside];
           [_bottomView addSubview:btn];
           
           UIButton *btn2=[UIButton buttonWithType:UIButtonTypeCustom];
           [btn2 setTitle:LocalizationKey(@"ok") forState:UIControlStateNormal];
           [btn2 setTitleColor:AppTextColor_Level_3 forState:UIControlStateNormal];
           [btn2 addTarget:self action:@selector(quedingbtnclick:) forControlEvents:UIControlEventTouchUpInside];
           [_bottomView addSubview:btn2];
           UIView *line2=[[UIView alloc]initWithFrame:CGRectMake(SCREEN_WIDTH_S/2,_bottomView.frame.size.height-41,1,40)];
          line2.backgroundColor=AppTextColor_E6E6E6;

            [_bottomView addSubview:line2];
           btn.frame=CGRectMake(0,_bottomView.frame.size.height-40,SCREEN_WIDTH_S/2,40);
           btn2.frame=CGRectMake(SCREEN_WIDTH_S/2,_bottomView.frame.size.height-40,SCREEN_WIDTH_S/2,40);
           
           _titlelabel=[[UILabel alloc]initWithFrame:CGRectMake(0,10,SCREEN_WIDTH_S,20)];
           _titlelabel.text=LocalizationKey(@"text_leverage");
           _titlelabel.textColor=AppTextColor_666666;
           _titlelabel.textAlignment=NSTextAlignmentCenter;
           [_bottomView addSubview:_titlelabel];
           
           [self loadData];
      }
    return self;
}

-(void)setTitles:(NSArray *)titles{
    
    if ([_titles isEqualToArray:titles]) {
        return;
    }
    if (titles.count!=_titles.count) {
        for (UIView *view in _myScrollView.subviews) {
            if ((view.tag-100)>(titles.count-1)) {
                 [view removeFromSuperview];
             }
         }
    }
    _titles=titles;
    
    [self loadData];
}

-(void)showsLevergesView{
    
    if (self.frame.size.width!=SCREEN_WIDTH_S||self.frame.size.height!=SCREEN_HEIGHT_S) {
        self.frame=CGRectMake(0,0,SCREEN_WIDTH_S,SCREEN_HEIGHT_S);
    }
    
    [[UIApplication sharedApplication].keyWindow addSubview:self];
    
    CGRect rect=_bottomView.frame;
    
    rect.origin.y=SCREEN_HEIGHT_S-_bottomView.frame.size.height-kEasyShowSafeBottomMargin_S;
    
    [UIView animateWithDuration:0.4 animations:^{
        self.bottomView.frame=rect;
    }];
    
}

-(UIView *)touchBgView{
    
    if (!_touchBgView) {
        
        _touchBgView=[[UIView alloc]initWithFrame:CGRectMake(0,0,SCREEN_WIDTH_S,SCREEN_HEIGHT_S)];
        _touchBgView.alpha=0.2;
        _touchBgView.backgroundColor=[UIColor blackColor];
        [self addSubview:_touchBgView];
    }
    return _touchBgView;;
}

-(UIView *)moveView{
    
    if (!_moveView) {
            _moveView=[[UIView alloc]initWithFrame:CGRectZero];
             _moveView.layer.borderWidth=1;
             _moveView.layer.borderColor=baseColor.CGColor;
             _moveView.layer.masksToBounds=YES;
            _moveView.layer.cornerRadius=5;
             [_myScrollView addSubview:_moveView];
           
         }
    return _moveView;
}

-(void)setSelectLeverString:(NSString *)selectLeverString{
    
    _selectLeverString=selectLeverString;
    
    if (_titles&&_titles.count!=0) {
        
        NSString *strtitle=selectLeverString;
        
        if ([selectLeverString containsString:@"x"]) {
            strtitle=[selectLeverString substringWithRange:NSMakeRange(0,selectLeverString.length-1)];
        }
        
        if ([_titles containsObject:strtitle]) {
            
            NSInteger index= [_titles indexOfObject:strtitle]+100;
           
            [self moveSelectViewAtIndex:index];
        }
    }
}


- (void)loadData {
    
    CGSize btnsize=CGSizeMake(50,20);
    CGFloat interval=10;
    NSInteger selectIndex=0;
   
  
    
    if (_titles&&_titles.count!=0) {
        if (self.leverageType.longValue == 1) {
            for (NSInteger i=0; i<_titles.count; i++) {
                
                NSString *string=_titles[i];
                UIButton *btn=[_myScrollView viewWithTag:100+i];
                if (!btn) {
                    btn=[UIButton buttonWithType:UIButtonTypeCustom];
                }
                btn.tag=100+i;
                [btn setTitle:[NSString stringWithFormat:@"%@x",string] forState:UIControlStateNormal];
                btn.titleLabel.font=[UIFont systemFontOfSize:14*kWindowWHOne];
                [btn setTitleColor:AppTextColor_Level_2 forState:UIControlStateNormal];
                [btn setTitleColor:baseColor forState:UIControlStateSelected];
                [btn addTarget:self action:@selector(selectbtnclick:) forControlEvents:UIControlEventTouchUpInside];
                [_myScrollView addSubview:btn];
                btn.frame=CGRectMake(interval+btnsize.width*i,_myScrollView.frame.size.height/2-btnsize.height/2,btnsize.width, btnsize.height);
                if ([self.selectLeverString containsString:string]) {
                    btn.selected=YES;
                    selectIndex=btn.tag;
                }
            }
            
            CGFloat totalwidth=2*interval+_titles.count*btnsize.width;
            
            _myScrollView.contentSize=CGSizeMake(totalwidth,_myScrollView.frame.size.height);
            
            if (selectIndex>=100) {
                
                [self moveSelectViewAtIndex:selectIndex];
            }
        }else if(self.leverageType.longValue == 2){
            self.slider=[[StepSlider alloc]init];
            
            [_mySliderView addSubview:self.slider];
            
            self.slider.labelOrientation=StepSliderTextOrientationDown;
            self.slider.labelOffset=5;
            self.slider.delegate = self;
            self.slider.trackHeight = 3;
            self.slider.trackCircleRadius = 6;
            self.slider.sliderCircleRadius = 6;
            self.slider.trackColor= AppTextColor_E6E6E6;
            self.slider.tintColor = GreenColor;
            self.slider.sliderCircleImage = [UIImage imageNamed:@"circularGreen"];
            [self.slider setMaxCount:5];
            [self.slider setIndex:0 animated:YES];
            self.slider.backgroundColor = mainColor;
//            self.slider.labelColor=AppTextColor_Level_1;
            self.slider.labelFont=[UIFont systemFontOfSize:10.0 * kWindowWHOne];
            self.slider.labels=@[@"0x",@"25x",@"50x",@"75x",@"100x"];
            
            
            [self.slider mas_makeConstraints:^(MASConstraintMaker *make) {
                make.left.mas_equalTo(5);
                make.right.mas_equalTo(-5);
//                make.top.equalTo(weakSelf.huilvlabel.mas_bottom).offset(10);
                make.top.mas_equalTo(10);
                make.height.mas_equalTo(40);
            }];
            
        }
        
    }
}

-(void)selectbtnclick:(UIButton *)button {
    
    if (_selectindex>=100) {
        
        UIButton *btn= [_myScrollView viewWithTag:_selectindex];
        btn.selected=NO;
    }
  //  button.selected=YES;
    
    [self moveSelectViewAtIndex:button.tag];
}

-(void)moveSelectViewAtIndex:(NSInteger)selectIndex {
  
    _selectindex=selectIndex;
    UIButton *btn= [_myScrollView viewWithTag:selectIndex];
    
    
    CGFloat pointX=btn.frame.origin.x;
    CGRect rect =self.moveView.frame;
    rect.origin.x=pointX;
    rect.origin.y=btn.frame.origin.y;
    rect.size=btn.frame.size;
    CGFloat sCenterwidth=_myScrollView.frame.size.width/2;
    
    CGFloat offsetX = pointX+btn.frame.size.width/2-sCenterwidth;
    
    if (offsetX<0) {
        offsetX=0;
    }else{
        
        if (offsetX>=(_myScrollView.contentSize.width-_myScrollView.frame.size.width)) {
            offsetX=_myScrollView.contentSize.width-_myScrollView.frame.size.width;
        }
    }
  
    [UIView animateWithDuration:0.3 animations:^{
        self.moveView.frame=rect;
        btn.selected=YES;
    } completion:^(BOOL finished) {
        
        [UIView animateWithDuration:0.2 animations:^{
            _myScrollView.contentOffset=CGPointMake(offsetX,0);
        }];
    }];
}


-(void)touchRemoveView {
    
    UIButton *btn= [_myScrollView viewWithTag:_selectindex];
    btn.selected=NO;
    CGRect rect=_bottomView.frame;
       
    rect.origin.y=SCREEN_HEIGHT_S;
       
    [UIView animateWithDuration:0.4 animations:^{
         self.bottomView.frame=rect;
    } completion:^(BOOL finished) {
         [self removeFromSuperview];
    }];
    
   
}

-(void)quedingbtnclick:(UIButton *)button {
    if (_selectindex/100!=1) {
        [self makeToast:[NSString stringWithFormat:@"%@ %@",LocalizationKey(@"select"),LocalizationKey(@"text_leverage")]  duration:1.5 position:CSToastPositionCenter];
        return;
    }
    
    NSString *string= _titles[_selectindex-100];
    
    if (_selcetLeverageblock) {
        
        _selcetLeverageblock(_selectindex-100,string);
    }
    
    [self touchRemoveView];
}

//setpSlider 代理
-(void)getSliderValue:(CGFloat)sliderValue{
    NSLog(@"%f",sliderValue/4 );
    NSInteger number = sliderValue/4 * 100;
    self.titlelabel.text= [NSString stringWithFormat: @"%ldx",(long)number];
//     NSArray *array = [self.znumberslabel.text componentsSeparatedByString:@" "];
//    NSString *valueStr=self.znumberslabel.text;
//    NSString *newstr=[valueStr stringByReplacingOccurrencesOfString:LocalizationKey(@"zhang") withString:@""];
//    if (newstr.doubleValue!=0) {
//        NSInteger number=sliderValue/4 * newstr.doubleValue;
//        if (number==0) {
//            self.inputNumberTextField.text=@"";
//        }else{
//            self.inputNumberTextField.text= [NSString stringWithFormat:@"%ld",number];
//
//        }
//    }
}

-(void)showMyScrollView{
    self.myScrollView.hidden = false;
    self.mySliderView.hidden = true;
    self.titlelabel.text=LocalizationKey(@"text_leverage");
}

-(void)showSliderView{
    self.myScrollView.hidden = true;
    self.mySliderView.hidden = false;
    self.titlelabel.text=LocalizationKey(@"1x");
}

/*
// Only override drawRect: if you perform custom drawing.
// An empty implementation adversely affects performance during animation.
- (void)drawRect:(CGRect)rect {
    // Drawing code
}
*/

@end
