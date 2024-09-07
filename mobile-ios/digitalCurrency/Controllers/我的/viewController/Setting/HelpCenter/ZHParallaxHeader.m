//
//  ZHParallaxHeader.m
//  ParallaxTableView
//
//  Created by aleck on 2019/7/28.
//  Copyright © 2019年 Xmly. All rights reserved.
//

#import "ZHParallaxHeader.h"
#import <objc/runtime.h>

@interface ZHParallaxHeader ()
@property (nonatomic,weak) UIScrollView *scrollView;
@property (nonatomic,readwrite) CGFloat progress;
@end

@interface ZHParallaxHeaderView: UIView

@property (nonatomic, weak) ZHParallaxHeader *parent;

- (void)resetContentOffset;

@end

@implementation ZHParallaxHeaderView

static void * const kZHParallaxHeaderViewKVOContext = (void*)&kZHParallaxHeaderViewKVOContext;

- (void)willMoveToSuperview:(UIView *)newSuperview {
    if ([self.superview isKindOfClass:[UIScrollView class]]) {
        [self.superview removeObserver:self forKeyPath:NSStringFromSelector(@selector(contentOffset)) context:kZHParallaxHeaderViewKVOContext];
    }
}

- (void)didMoveToSuperview {
    if ([self.superview isKindOfClass:[UIScrollView class]]) {
        [self.superview addObserver:self
               forKeyPath:NSStringFromSelector(@selector(contentOffset))
                  options:NSKeyValueObservingOptionNew
                  context:kZHParallaxHeaderViewKVOContext];
    }
}

- (void)observeValueForKeyPath:(NSString *)keyPath
                      ofObject:(id)object
                        change:(NSDictionary<NSKeyValueChangeKey,id> *)change
                       context:(void *)context {
    if (object == self.parent.scrollView) {
        if ([keyPath isEqualToString:@"contentOffset"]) {
            [self scrollViewContentOffsetDidChange];
        }
    }
}

- (void)resetMJRefreshContentInsetTop {
    Class cls = NSClassFromString(@"MJRefreshHeader");
#pragma clang diagnostic push
#pragma clang diagnostic ignored "-Wundeclared-selector"
    if ([self.parent.scrollView respondsToSelector:@selector(mj_header)] && cls) {
        UIView *mj_header = [self.parent.scrollView performSelector:@selector(mj_header)];
        if ([mj_header isKindOfClass:cls] && [mj_header respondsToSelector:@selector(setIgnoredScrollViewContentInsetTop:)]) {
            [mj_header performSelector:@selector(setIgnoredScrollViewContentInsetTop:) withObject:@(self.parent.height)];
        }
#pragma clang diagnostic pop
    }
}

- (void)scrollViewContentOffsetDidChange {
    [self.parent.scrollView insertSubview:self atIndex:0];

    if (!self.parent.scrollView.frame.size.width) {
        return;
    }

    [self resetMJRefreshContentInsetTop];

    UIWindow *window = UIApplication.sharedApplication.keyWindow;
    UIScrollView *scrollView = self.parent.scrollView;

    CGPoint targetPoint = [window convertPoint:window.frame.origin toView:scrollView];
    CGSize size = CGSizeMake(scrollView.frame.size.width, self.parent.height);

    CGPoint offset = scrollView.contentOffset;

    CGFloat progress = -self.parent.scrollView.contentOffset.y / self.parent.height;
    if (offset.y <= -self.parent.height) {
        self.frame = CGRectMake(targetPoint.x, targetPoint.y, size.width, size.height);
        CGRect frame = self.frame;
        frame.size.height = fabs(scrollView.contentOffset.y);
        self.frame = frame;
//        progress = MIN(fabs(progress), 1);
    } else {
        CGFloat deltaY = offset.y + self.parent.height;
        CGRect frame = CGRectMake(targetPoint.x, targetPoint.y - MIN(deltaY * self.parent.ratio, self.parent.height), size.width, size.height);
        self.frame = frame;
//        progress = 1 - () / self.parent.height;
        progress = progress < 0 ? 0 : progress;
    }
    self.parent.progress = progress;
}

- (void)resetContentOffset {
    [self scrollViewContentOffsetDidChange];
}

@end

@implementation ZHParallaxHeader

- (UIView *)headerView {
    if (!_headerView) {
        ZHParallaxHeaderView *headerView = [ZHParallaxHeaderView new];
        headerView.parent = self;
        _headerView = headerView;
    }
    return _headerView;
}

- (void)setContentView:(UIView *)contentView {

    if (_contentView != contentView) {
        [_contentView removeFromSuperview];
        _contentView = contentView;
        [self.headerView addSubview:contentView];
        [self updateContentOffset];
    }
}

- (void)setRatio:(CGFloat)ratio {
    _ratio = ratio;
    [self updateContentOffset];
}

- (void)setScrollView:(UIScrollView *)scrollView {
    if (_scrollView != scrollView) {
        _scrollView = scrollView;
        [_scrollView addSubview:self.headerView];
    }
}

- (void)setHeight:(CGFloat)height {
    _height = height;
    [self updateContentOffset];
}

- (void)setProgress:(CGFloat)progress {
    _progress = progress;
    if (self.delegate && [self.delegate respondsToSelector:@selector(parallaxHeaderDidScroll:)]) {
        [self.delegate parallaxHeaderDidScroll:self];
    }
}

- (void)updateContentOffset {
    _scrollView.contentInset = UIEdgeInsetsMake(self.height - 50, 0, 0, 0);
    [(ZHParallaxHeaderView *)self.headerView resetContentOffset];
    self.contentView.frame = self.headerView.bounds;
    self.contentView.autoresizingMask = UIViewAutoresizingFlexibleWidth | UIViewAutoresizingFlexibleHeight;
}


@end

@implementation UIScrollView (ZHParallaxHeader)

static void * const kZHParallaxHeaderKey = (void *)&kZHParallaxHeaderKey;

- (ZHParallaxHeader *)parallaxHeader {
    ZHParallaxHeader *header = objc_getAssociatedObject(self, kZHParallaxHeaderKey);
    if (!header) {
        self.parallaxHeader = [ZHParallaxHeader new];
        header = self.parallaxHeader;
    }
    return header;
}

- (void)setParallaxHeader:(ZHParallaxHeader *)parallaxHeader {
    parallaxHeader.scrollView = self;
    objc_setAssociatedObject(self, kZHParallaxHeaderKey, parallaxHeader, OBJC_ASSOCIATION_RETAIN_NONATOMIC);
}

@end
