//
//  ZLAdvertViewController.m
//  ZLAdvertDemo
//
//  Created by zhangli on 2019/2/28.
//  Copyright © 2019年 YSMX. All rights reserved.
//

#import "ZLAdvertViewController.h"
#import <WebKit/WebKit.h>

@interface ZLAdvertViewController ()

@property (nonatomic, strong) WKWebView *webView;

@end

@implementation ZLAdvertViewController

- (void)viewDidLoad {
    [super viewDidLoad];

    self.title = @"点击进入广告链接";
    _webView = [[WKWebView alloc] initWithFrame:self.view.bounds];
    _webView.backgroundColor = [UIColor whiteColor];

    if (!self.adUrl) {
        self.adUrl = @"http://www.baidu.com";
    }

    NSURLRequest *request = [NSURLRequest requestWithURL:[NSURL URLWithString:self.adUrl]];
    [_webView loadRequest:request];
    [self.view addSubview:_webView];
}

- (void)setAdUrl:(NSString *)adUrl {
    _adUrl = adUrl;
}

- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

@end
