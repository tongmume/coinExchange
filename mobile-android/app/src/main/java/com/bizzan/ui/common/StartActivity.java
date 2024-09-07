package com.bizzan.ui.common;

import android.Manifest;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.LinearLayout;

import com.gyf.barlibrary.ImmersionBar;
import com.bizzan.R;
import com.bizzan.serivce.MyTextService_contract;
import com.bizzan.ui.home.MainActivity;
import com.bizzan.ui.lock.LockActivity;
import com.bizzan.base.BaseActivity;
import com.bizzan.utils.SharedPreferenceInstance;
import com.bizzan.serivce.MyTextService;
import com.bizzan.utils.WonderfulLogUtils;

import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.PermissionListener;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;

public class StartActivity extends BaseActivity {
//    @BindView(R.id.img)
//    GifImageView img;
//    @BindView(R.id.web)
//    WebView web;

//    @BindView(R.id.rl_img)
//    LinearLayout rl_img;
//    @BindView(R.id.btn_tiaoguo)
//    Button btn_tiaoguo;
//    private Timer timer;

//    private CountDownTimer timer3 = new CountDownTimer(3151, 1000) {
//
//        @Override
//        public void onTick(long millisUntilFinished) {
////            Log.i("miao",":::"+millisUntilFinished);
////            btn_tiaoguo.setText("跳过"+(millisUntilFinished / 1000)+"");
//
//        }
//
//        @Override
//        public void onFinish() {
////            btn_tiaoguo.setText("跳过0");
//            if (SharedPreferenceInstance.getInstance().getIsFirstUse()) LeadActivity.actionStart(StartActivity.this);
//            else MainActivity.actionStart(StartActivity.this);
//
//            finish();
//        }
//    };

//    int n = 3;
    private PermissionListener listener =new PermissionListener() {
        @Override
        public void onSucceed(int requestCode, @NonNull List<String> grantPermissions) {

        }

        @Override
        public void onFailed(int requestCode, @NonNull List<String> deniedPermissions) {


        }
    };
     int duration=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ImmersionBar.with(this).fullScreen(true).init();
        startService(new Intent(StartActivity.this, MyTextService.class));
        startService(new Intent(StartActivity.this, MyTextService_contract.class));
        checkPermission(100);
        ActivityManager mActivityManager =  (ActivityManager)getSystemService(Context.ACTIVITY_SERVICE);
        int memoryClass = mActivityManager.getMemoryClass();
        int largeMemoryClass = mActivityManager.getLargeMemoryClass();
        WonderfulLogUtils.logi("miao","多大"+memoryClass+"--"+largeMemoryClass);
//        WebSettings webSettings = web.getSettings();
//        webSettings.setUseWideViewPort(true);
//        webSettings.setLoadWithOverviewMode(true);
//        webSettings.setJavaScriptEnabled(true); // 设置支持javascript脚本
//        webSettings.setAllowFileAccess(true); // 允许访问文件
//        web.loadUrl("file:///android_asset/web/demo.html");
    }
    private void checkPermission(int requestCode) {
        AndPermission.with(this).requestCode(requestCode).permission(Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.REQUEST_INSTALL_PACKAGES).callback(listener).start();
    }


    @Override
    protected int getActivityLayoutId() {
        return R.layout.activity_start2;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {

    }

    @Override
    protected void obtainData() {

    }

    @Override
    protected void fillWidget() {
        if (!isNeedShowLockActivity()) {
//            timerStart();
            //timer = new Timer();
            //timer.schedule(new TimerTask() {
               // @Override
                //public void run() {
                    //timer.cancel();
                    //timer = null;
                    /*
                    if (SharedPreferenceInstance.getInstance().getIsFirstUse()) LeadActivity.actionStart(StartActivity.this);
                    else MainActivity.actionStart(StartActivity.this);
                    */
                    MainActivity.actionStart(StartActivity.this);
                    finish();

                //}
            //}, 4300);

        }
    }

    @Override
    protected void loadData() {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == LockActivity.RETURN_LOCK){
            /*
            timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    timer.cancel();
                    timer = null;

                    if (SharedPreferenceInstance.getInstance().getIsFirstUse()) LeadActivity.actionStart(StartActivity.this);
                    else MainActivity.actionStart(StartActivity.this);
                    finish();

                }
            }, 4000);
            */
            MainActivity.actionStart(StartActivity.this);
        }
    }

//    private void timerStart() {
////        Glide.with(StartActivity.this).load(R.drawable.sp1).asGif().diskCacheStrategy(DiskCacheStrategy.SOURCE).into(img1);
////        Glide.with(StartActivity.this).load(R.drawable.donghua).diskCacheStrategy(DiskCacheStrategy.SOURCE).into(new GlideDrawableImageViewTarget(img, 1));
//        img.setVisibility(View.VISIBLE);
////        btn_tiaoguo.setVisibility(View.GONE);
////        btn_tiaoguo.setText("跳过：3");
////        btn_tiaoguo.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                if (SharedPreferenceInstance.getInstance().getIsFirstUse()) LeadActivity.actionStart(StartActivity.this);
////                else MainActivity.actionStart(StartActivity.this);
////                finish();
////            }
////        });
//        AnimationSet animationSet = new AnimationSet(true);
//        AnimationDrawable frameAnim=new AnimationDrawable();
//        frameAnim.addFrame(getResources().getDrawable(R.drawable.login_2), 2600);
//        frameAnim.addFrame(getResources().getDrawable(R.drawable.logo_1), 3000);
//        frameAnim.setOneShot(true);
//        rl_img.setBackgroundDrawable(frameAnim);
//        frameAnim.start();
//        AlphaAnimation alphaAnimation = new AlphaAnimation(1f, 1f);
//
//        animationSet.addAnimation(alphaAnimation);
//        //设置动画持续时长
//        alphaAnimation.setDuration(3000);
//        //设置动画结束之后的状态是否是动画的最终状态，true，表示是保持动画结束时的最终状态
//        alphaAnimation.setFillAfter(true);
//        //设置动画播放次数
//        alphaAnimation.setRepeatCount(0);
//        alphaAnimation.setAnimationListener(new Animation.AnimationListener() {
//            @Override
//            public void onAnimationStart(Animation animation) {
//            }
//
//            @Override
//            public void onAnimationEnd(Animation animation) {
//                timer = new Timer();
//                timer.schedule(new TimerTask() {
//                    @Override
//                    public void run() {
////                        if (n == 0) {
//                        timer.cancel();
//                        timer = null;
////                        runOnUiThread(new Runnable(){
////                            @Override
////                            public void run() {
//////                                img.setVisibility(View.GONE);
////                                if (btn_tiaoguo==null){
////
////                                }else {
////                                    btn_tiaoguo.setVisibility(View.VISIBLE);
////                                }
////                                Drawable btnDrawable1 = getResources().getDrawable(R.mipmap.guanggao1);
////                                img.setImageDrawable(null);
////                                img.setBackground(btnDrawable1);
////                            }});
////                       timer3.start();
//
//
////
////                    if (SharedPreferenceInstance.getInstance().getIsFirstUse())
////                        LeadActivity.actionStart(StartActivity.this);
////                    else if (!WonderfulStringUtils.isEmpty(MyApplication.getApp().getCurrentUser().getToken())) //没有过期
////                        MainActivity.actionStart(StartActivity.this);
////                    else
////                        LoginActivity.actionStart(StartActivity.this);
//
//
//                        if (SharedPreferenceInstance.getInstance().getIsFirstUse()) LeadActivity.actionStart(StartActivity.this);
//                        else MainActivity.actionStart(StartActivity.this);
//                        finish();
//
//                    }
//                }, 500);
//            }
//
//            @Override
//            public void onAnimationRepeat(Animation animation) {
//
//            }
//        });
//        //开始动画
//        rl_img.startAnimation(animationSet);
//
//
//
//
//
//    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
//        if (timer3!=null){
//            timer3.cancel();
//        }

    }

    @Override
    public void onBackPressed() {
        //if (timer != null) {
        //    timer.cancel();
        //    timer = null;
        //}
        //super.onBackPressed();

    }
}
