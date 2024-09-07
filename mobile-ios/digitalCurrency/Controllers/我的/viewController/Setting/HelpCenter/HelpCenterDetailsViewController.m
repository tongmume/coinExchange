//
//  HelpCenterDetailsViewController.m
//  digitalCurrency
//
//  Created by chu on 2019/8/10.
//  Copyright © 2019年 BIZZAN. All rights reserved.
//

#import "HelpCenterDetailsViewController.h"
#import <WebKit/WebKit.h>

@interface HelpCenterDetailsViewController ()<WKNavigationDelegate>

{

    NSString *_title;
    NSString *_source;
    NSString *_content;

    WKWebView *_webView;
}

@property (nonatomic, strong) UIScrollView *scrollView;

@end

@implementation HelpCenterDetailsViewController

- (void)viewWillDisappear:(BOOL)animated {
    [super viewWillDisappear:animated];
}

- (void)viewDidLoad {
    [super viewDidLoad];

    WKWebView *web =[[WKWebView alloc] init];
    web.navigationDelegate = self;
    web.backgroundColor = [UIColor clearColor];
    web.opaque = NO;
    web.scrollView.scrollEnabled = NO;
    _webView = web;
    [self loadScrollView];
    [self initData];
    
    [_webView addObserver:self forKeyPath:@"contentSize" options:NSKeyValueObservingOptionNew context:nil];
}



- (void)loadScrollView{
    self.scrollView = [[UIScrollView alloc] initWithFrame:CGRectMake(0, 0, kWindowW, kWindowH - NEW_NavHeight)];
    self.scrollView.contentSize = CGSizeMake(kWindowW, kWindowH - NEW_NavHeight);
    [self.view addSubview:self.scrollView];
}

- (void)loadSubViews{
    UILabel *label = [[UILabel alloc] initWithFrame:CGRectMake(15, 15, kWindowW - 30, 0)];
    label.numberOfLines = 0;
    label.textAlignment = NSTextAlignmentCenter;
    label.font = [UIFont systemFontOfSize:23];
    label.text = _title;
    CGSize maxSize = CGSizeMake(kWindowW - 30, MAXFLOAT);
    CGSize a = [label sizeThatFits:maxSize];
    label.frame = CGRectMake(15, 15, kWindowW - 30, a.height);
    label.textColor = RGBOF(0xe6e6e6);
    [self.scrollView addSubview:label];

    UILabel *source = [[UILabel alloc] initWithFrame:CGRectMake(15, CGRectGetMaxY(label.frame) + 10, kWindowW - 30, 15)];
    source.text = _source;
    source.textAlignment = NSTextAlignmentCenter;
    source.font = [UIFont systemFontOfSize:13];
    source.textColor = RGBOF(0x999999);
    [self.scrollView addSubview:source];

    UIView *lineView = [[UIView alloc] initWithFrame:CGRectMake(15, CGRectGetMaxY(source.frame) + 8, kWindowW - 30, 1)];
    lineView.backgroundColor = RGBOF(0x333333);
    [self.scrollView addSubview:lineView];

    _webView.frame = CGRectMake(0, CGRectGetMaxY(lineView.frame), kWindowW, kWindowH - NEW_NavHeight - CGRectGetMaxY(lineView.frame));

    [self.scrollView addSubview:_webView];
}

- (void)initData{
    _title = self.contentModel.title;
    NSString *time = self.contentModel.createTime;
    _source = [NSString stringWithFormat:@"%@  BIZZAN%@",time,LocalizationKey(@"exchange")];
    _content = self.contentModel.content;
    [self getData];
}

- (void)webView:(WKWebView *)webView didFinishNavigation:(WKNavigation *)navigation {
    [self loadSubViews];

    NSString *str = [NSString stringWithFormat:@"var script = document.createElement('script');"
                     "script.type = 'text/javascript';"
                     "script.text = \"function ResizeImages() { "
                     "var myimg,oldwidth,oldheight;"
                     "var maxwidth=%f;"// 图片宽度
                     "for(i=0;i <document.images.length;i++){"
                     "myimg = document.images[i];"
                     "if(myimg.width > maxwidth){"
                     "myimg.width = maxwidth;"
                     "}"
                     "}"
                     "}\";"
                     "document.getElementsByTagName('head')[0].appendChild(script);",(kWindowW - 20)];

    [webView evaluateJavaScript:str completionHandler:nil];

    [webView evaluateJavaScript:str completionHandler:nil];
    [webView evaluateJavaScript:@"ResizeImages();" completionHandler:nil];
    [webView evaluateJavaScript:@"document.getElementsByTagName('body')[0].style.webkitTextFillColor= '#E6E6E6'" completionHandler:nil];

    // 禁止放大缩小
    NSString *injectionJSString = @"var script = document.createElement('meta');"
    "script.name = 'viewport';"
    "script.content=\"width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no,viewport-fit\";"
    "document.getElementsByTagName('head')[0].appendChild(script);";
    [webView evaluateJavaScript:injectionJSString completionHandler:nil];
    
    //获取网页的高度
      [webView evaluateJavaScript:@"document.body.scrollHeight"completionHandler:^(id _Nullable result,NSError * _Nullable error){
      CGFloat height  = [result floatValue];
          CGRect frame = _webView.frame;
          _webView.frame = CGRectMake(frame.origin.x, frame.origin.y, frame.size.width, height + 10);
//          self.scrollView.contentSize = CGSizeMake(kWindowW, CGRectGetMaxY(_webView.frame));
      }];
}


- (void)getData{
    [EasyShowLodingView showLodingText:[[ChangeLanguage bundle] localizedStringForKey:@"loading" value:nil table:@"English"]];
    NSString *url = [NSString stringWithFormat:@"%@%@",HOST, @"uc/ancillary/more/help/detail"];
    NSDictionary *param = @{@"id":self.contentModel.ID};
    [[XBRequest sharedInstance] postDataWithUrl:url Parameter:param ResponseObject:^(NSDictionary *responseResult) {
        [EasyShowLodingView hidenLoding];
        NSLog(@"responseResult --- %@",responseResult);
        if (![responseResult objectForKey:@"resError"]) {
            NSDictionary *data = responseResult[@"data"];
            _title = data[@"title"];
            _content = data[@"content"];
            _source = [NSString stringWithFormat:@"%@  BIZZAN %@",data[@"createTime"],LocalizationKey(@"exchange")];
            NSString *htmlStr = [NSString stringWithFormat:@"<header><meta name='viewport' content='width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no'></header><head><style>img{max-width:%dpx !important;}</style></head>%@",(int)(kWindowW - 20), _content];
            [_webView loadHTMLString:htmlStr baseURL:[NSURL URLWithString:HOST]];
        }
    }];
}


#pragma mark  - KVO回调
-(void)observeValueForKeyPath:(NSString *)keyPath ofObject:(id)object change:(NSDictionary<NSKeyValueChangeKey,id> *)change context:(void *)context{
    
    CGFloat newHeight =  _webView.scrollView.contentSize.height;
    CGRect frame = _webView.frame;
    _webView.backgroundColor = UIColor.redColor;
    _webView.frame = CGRectMake(frame.origin.x, frame.origin.y, frame.size.width, newHeight + 10);
    self.scrollView.contentSize = CGSizeMake(kWindowW, CGRectGetMaxY(_webView.frame));
    
}

- (void)dealloc {
    [_webView removeObserver:self forKeyPath:@"contentSize"];
 }

@end
