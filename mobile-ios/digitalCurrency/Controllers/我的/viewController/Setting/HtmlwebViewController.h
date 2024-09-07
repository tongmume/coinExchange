//
//  HtmlwebViewController.h
//  digitalCurrency
//
//  Created by startlink on 2019/7/7.
//  Copyright © 2019年 BIZZAN. All rights reserved.
//

#import <UIKit/UIKit.h>
#import <WebKit/WebKit.h>

@interface HtmlwebViewController : BaseViewController
@property (nonatomic,strong)WKWebView *webview;
@property (nonatomic,copy)NSString *urlstr;
@end
