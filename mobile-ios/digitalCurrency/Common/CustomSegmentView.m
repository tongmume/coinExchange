//
//  CustomSegmentView.m
//  digitalCurrency
//
//  Created by ios on 2020/9/16.
//  Copyright © 2020 BIZZAN. All rights reserved.
//

#import "CustomSegmentView.h"

@interface CustomSegmentView ()

@property (nonatomic, strong)UIView *backGroundView;

@property (nonatomic, strong)NSArray *titles;

@property (nonatomic, strong)UIView *moveView;


@end

@implementation CustomSegmentView

- (instancetype)initWithFrame:(CGRect)frame   Items:(NSArray *)array {
    
    self=[super initWithFrame:frame];
    
    if (self) {
        
        _titles=array;
        _backGroundView=[[UIView alloc]init];
        _backGroundView.layer.borderWidth=1;
        _backGroundView.layer.cornerRadius=5;
        _backGroundView.layer.masksToBounds=YES;
        _backGroundView.layer.borderColor=[UIColor whiteColor].CGColor;
        _backGroundView.frame=self.bounds;
        [self addSubview:_backGroundView];
        
        _moveView=[[UIView alloc]initWithFrame:CGRectMake(0,0,frame.size.width/array.count,frame.size.height)];
        _moveView.backgroundColor=[UIColor clearColor];
        _moveView.layer.borderWidth=1;
               _moveView.layer.cornerRadius=5;
               _moveView.layer.masksToBounds=YES;
               _moveView.layer.borderColor=[UIColor whiteColor].CGColor;
        [self addSubview:_moveView];
        
        [self intItems];
        
    }
    
    return self;
}


-(void)setBackgroundColor:(UIColor *)backgroundColor{
    [super setBackgroundColor:backgroundColor];
    _backGroundView.backgroundColor=backgroundColor;
}

-(void)setUnselectColor:(UIColor *)unselectColor{
    
    _unselectColor=unselectColor;
    
    _backGroundView.layer.borderColor=unselectColor.CGColor;
    [self setbtnState:UIControlStateNormal];
}


-(void)setSelectColor:(UIColor *)selectColor {
    
    _selectColor=selectColor;
    _moveView.layer.borderColor=selectColor.CGColor;
    [self setbtnState:UIControlStateSelected];

}

- (void)setbtnState:(UIControlState)state {
    
    if (_titles&&_titles.count!=0) {
     
        for (int i=0; i<_titles.count; i++) {
            
            UIButton *btn = [self viewWithTag:100+i];
            
            if (btn) {
                    if (state==UIControlStateSelected) {
                    [btn setTitleColor:self.selectColor forState:state];
                }else
                    [btn setTitleColor:self.unselectColor forState:state];
            }
        }
    }
}

-(void)setSelectIndex:(NSInteger)selectIndex {
    
    _selectIndex=selectIndex;
    
    for (int i=0; i<_titles.count; i++) {
         UIButton *btn = [self viewWithTag:100+i];
        if (i!=selectIndex) {
            btn.selected=NO;
        }
    }
    
    UIButton *btn = [self viewWithTag:100+selectIndex];
    btn.selected=YES;
    
    if (btn) {
      
        MJWeakSelf
        CGRect rect = _moveView.frame;
        _unselectIndex=rect.origin.x/rect.size.width;
        rect.origin.x=btn.frame.origin.x;
        [UIView animateWithDuration:0.3 animations:^{
            weakSelf.moveView.frame=rect;
        }];
    }
}


-(void)intItems {
    
    if (_titles&&_titles.count!=0) {
        
        CGFloat btnWidth= self.frame.size.width/_titles.count;
        CGFloat btnheight= self.frame.size.height;
        
        for (int i=0; i<_titles.count; i++) {
            UIButton *button =[UIButton buttonWithType:UIButtonTypeCustom];
            [button setTitle:_titles[i] forState:UIControlStateNormal];
            button.titleLabel.font=[UIFont fontWithName:@"PingFangSC-Semibold" size:18];
            button.tag=100+i;
            button.frame=CGRectMake(i*btnWidth,0,btnWidth,btnheight);
            [button addTarget:self action:@selector(btnclick:) forControlEvents:UIControlEventTouchUpInside];
            [self addSubview:button];
        }
    }
}


-(void)btnclick:(UIButton *)btn {
    
    self.selectIndex=btn.tag-100;

    if (self.selectitemblock) {
        self.selectitemblock(btn.tag-100);
    }    
}

/*
// Only override drawRect: if you perform custom drawing.
// An empty implementation adversely affects performance during animation.
- (void)drawRect:(CGRect)rect {
    // Drawing code
}
*/

@end
