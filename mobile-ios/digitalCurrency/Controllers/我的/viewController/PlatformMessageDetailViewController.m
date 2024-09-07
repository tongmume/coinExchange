//
//  PlatformMessageDetailViewController.m
//  digitalCurrency
//
//  Created by iDog on 2019/3/21.
//  Copyright © 2019年 BIZZAN. All rights reserved.
//

#import "PlatformMessageDetailViewController.h"
#import <WebKit/WebKit.h>

@interface PlatformMessageDetailViewController ()<WKNavigationDelegate>
@property(nonatomic,strong) WKWebView *webView;
@end

@implementation PlatformMessageDetailViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    self.navigationItem.title = self.navtitle;
//    self.title = LocalizationKey(@"notice");
    [self getData];
    
    // Do any additional setup after loading the view.
}

-(WKWebView *)webView{
    if (!_webView) {
        _webView = [[WKWebView alloc] initWithFrame:CGRectMake(0, 0, kWindowW, kWindowH-NEW_NavHeight)];
        _webView.navigationDelegate = self;
        [_webView loadHTMLString:self.content baseURL:nil];
        _webView.opaque = NO;
        [_webView sizeToFit];
        _webView.backgroundColor = mainColor;
    }
    return _webView;
}

- (void)webView:(WKWebView *)webView didStartProvisionalNavigation:(WKNavigation *)navigation {
    [EasyShowLodingView showLodingText:LocalizationKey(@"loading")];
}

- (void)webView:(WKWebView *)webView didFinishNavigation:(WKNavigation *)navigation {
        [EasyShowLodingView hidenLoding];
        NSString *js=@"var script = document.createElement('script');"
        "script.type = 'text/javascript';"
        "script.text = \"function ResizeImages() { "
        "var myimg,oldwidth;"
        "var maxwidth = %f;"
        "for(i=0;i <document.images.length;i++){"
        "myimg = document.images[i];"
        "if(myimg.width > maxwidth){"
        "oldwidth = myimg.width;"
        "myimg.width = %f;"
        "}"
        "}"
        "}\";"
        "document.getElementsByTagName('head')[0].appendChild(script);";
        js=[NSString stringWithFormat:js,[UIScreen mainScreen].bounds.size.width,[UIScreen mainScreen].bounds.size.width-20];
        [webView evaluateJavaScript:js completionHandler:nil];
        [webView evaluateJavaScript:@"ResizeImages();" completionHandler:nil];
        [webView evaluateJavaScript:@"document.getElementsByTagName('body')[0].style.webkitTextFillColor= '#E6E6E6'" completionHandler:nil];
    
    // 禁止放大缩小
    NSString *injectionJSString = @"var script = document.createElement('meta');"
    "script.name = 'viewport';"
    "script.content=\"width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no,viewport-fit\";"
    "document.getElementsByTagName('head')[0].appendChild(script);";
    [webView evaluateJavaScript:injectionJSString completionHandler:nil];
}

- (void)webView:(WKWebView *)webView didFailNavigation:(WKNavigation *)navigation withError:(NSError *)error {
    [EasyShowLodingView hidenLoding];
}


- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}


- (void)getData{
    [EasyShowLodingView showLodingText:[[ChangeLanguage bundle] localizedStringForKey:@"loading" value:nil table:@"English"]];
    NSString *url = [NSString stringWithFormat:@"%@%@%@",HOST, @"uc/announcement/",self.ID];
    [[XBRequest sharedInstance] getDataWithUrl:url Parameter:nil ResponseObject:^(NSDictionary *responseResult) {
        [EasyShowLodingView hidenLoding];
        NSLog(@"responseResult --- %@",responseResult);
        if (![responseResult objectForKey:@"resError"]) {
            NSDictionary *data = responseResult[@"data"];
            self.navigationItem.title = data[@"title"];
            self.content = data[@"content"];
            [self.view addSubview:[self webView]];
        }
    }];
}


@end
