//
//  HtmlwebViewController.m
//  digitalCurrency
//
//  Created by startlink on 2019/7/7.
//  Copyright © 2019年 BIZZAN. All rights reserved.
//

#import "HtmlwebViewController.h"
#import "Masonry.h"
@interface HtmlwebViewController ()<WKUIDelegate,WKNavigationDelegate,WKScriptMessageHandler>

@end

@implementation HtmlwebViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    // Do any additional setup after loading the view.
//    self.title = LocalizationKey(@"BiYong");
    WKWebViewConfiguration *config = [[WKWebViewConfiguration alloc] init];
    // 设置偏好设置
    config.preferences = [[WKPreferences alloc] init];
    // 默认为0
    config.preferences.minimumFontSize = 10;
    // 默认认为YES
    config.preferences.javaScriptEnabled = YES;
    // 在iOS上默认为NO，表示不能自动通过窗口打开
    config.preferences.javaScriptCanOpenWindowsAutomatically = NO;
    config.processPool = [[WKProcessPool alloc] init];
    config.userContentController = [[WKUserContentController alloc] init];
    //注意在这里注入JS对象名称 "JSObjec"
    [config.userContentController addScriptMessageHandler:self name:@"tel"];



    self.webview = [[WKWebView alloc]initWithFrame:CGRectMake(0, 0, 0, 0) configuration:config];
    self.webview.UIDelegate = self;
    self.webview.navigationDelegate = self;
    [self.view addSubview:self.webview];
    [self.webview mas_makeConstraints:^(MASConstraintMaker *make) {
        make.left.equalTo(self.view.mas_left);
        make.right.equalTo(self.view.mas_right);
        make.top.equalTo(self.view.mas_top);
        if (@available(iOS 11.0, *)) {
            make.bottom.equalTo(self.view.mas_safeAreaLayoutGuideBottom);
        } else {
            make.bottom.equalTo(self.view.mas_bottom);
        }
    }];

    [self.webview loadRequest:[NSURLRequest requestWithURL:[NSURL URLWithString:self.urlstr]]];

}

//// 页面开始加载时调用
//- (void)webView:(WKWebView *)webView didStartProvisionalNavigation:(WKNavigation *)navigation{
//     [EasyShowLodingView showLodingText:LocalizationKey(@"loading")];
//}

-(void)viewWillAppear:(BOOL)animated{
    [EasyShowLodingView showLodingText:LocalizationKey(@"loading")];

}

// 页面加载完成之后调用
- (void)webView:(WKWebView *)webView didFinishNavigation:(WKNavigation *)navigation{
    [EasyShowLodingView hidenLoding];
    // 禁止放大缩小
    NSString *injectionJSString = @"var script = document.createElement('meta');"
    "script.name = 'viewport';"
    "script.content=\"width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no,viewport-fit\";"
    "document.getElementsByTagName('head')[0].appendChild(script);";
    [webView evaluateJavaScript:injectionJSString completionHandler:nil];

}
// 页面加载失败时调用
- (void)webView:(WKWebView *)webView didFailProvisionalNavigation:(WKNavigation *)navigation{
    [EasyShowLodingView hidenLoding];

}


#pragma mark - WKNavigationDelegate
- (void)webView:(WKWebView *)webView decidePolicyForNavigationAction:(WKNavigationAction *)navigationAction decisionHandler:(void (^)(WKNavigationActionPolicy))decisionHandler//如果实现了这个代理方法，就必须得调用decisionHandler这个block
{
    NSURL *URL = navigationAction.request.URL;
    NSString *scheme = [URL scheme];
    //uiwebview和wk都需要统一的scheme;
    if ([scheme isEqualToString:@"tel"]) {

        //WKNavigationActionPolicyCancel代表取消加载等于是return NO；
        decisionHandler(WKNavigationActionPolicyCancel);
        return;
    }
    //WKNavigationActionPolicyAllow代表允许加载；
    decisionHandler(WKNavigationActionPolicyAllow);
}
- (void)userContentController:(WKUserContentController *)userContentController didReceiveScriptMessage:(WKScriptMessage *)message{

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
