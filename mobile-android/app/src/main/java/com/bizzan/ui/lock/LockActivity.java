package com.bizzan.ui.lock;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.gyf.barlibrary.ImmersionBar;

import com.bizzan.R;
import com.bizzan.ui.login.LoginActivity;
import com.bizzan.app.MyApplication;
import com.bizzan.base.ActivityManage;
import com.bizzan.base.BaseActivity;
import com.bizzan.entity.User;
import com.bizzan.utils.SharedPreferenceInstance;
import com.bizzan.customview.CircleImageView;
import com.bizzan.customview.lock.LockPatternView;
import com.bizzan.utils.EncryUtils;
import com.bizzan.utils.WonderfulDialogUtils;
import com.bizzan.utils.WonderfulStringUtils;
import com.bizzan.utils.WonderfulToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class LockActivity extends BaseActivity {

    public static final int RETURN_LOCK = 1;

    @BindView(R.id.llTitle)
    LinearLayout llTitle;
    @BindView(R.id.ivHeader)
    CircleImageView ivHeader;
    @BindView(R.id.tvNickName)
    TextView tvNickName;
    @BindView(R.id.tvMessage)
    TextView tvMessage;
    @BindView(R.id.gesture_tip_layout)
    LinearLayout gestureTipLayout;
    @BindView(R.id.lockView)
    LockPatternView lockView;
    @BindView(R.id.tvForgot)
    TextView tvForgot;
    private List<LockPatternView.Cell> cells;

    public static void actionStart(Activity activity) {
        Intent intent = new Intent(activity, LockActivity.class);
        activity.startActivityForResult(intent, RETURN_LOCK);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == LoginActivity.RETURN_LOGIN) {
            if (resultCode == RESULT_OK) {
                setResult(RESULT_OK);
                finish();
            }
        }
    }

    @Override
    protected int getActivityLayoutId() {
        return R.layout.activity_lock;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        isNeedChecke = false;
        tvForgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WonderfulDialogUtils.showDefaultDialog(LockActivity.this, WonderfulToastUtils.getString(LockActivity.this,R.string.hint), WonderfulToastUtils.getString(LockActivity.this,R.string.forget_pwd), WonderfulToastUtils.getString(LockActivity.this,R.string.cancle), WonderfulToastUtils.getString(LockActivity.this,R.string.go_login), null, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SharedPreferenceInstance.getInstance().saveIsNeedShowLock(false);
                        startActivityForResult(new Intent(LockActivity.this, LoginActivity.class), LoginActivity.RETURN_LOGIN);
                    }
                });
            }
        });
        lockView.setOnPatternListener(new LockPatternView.OnPatternListener() {
            @Override
            public void onPatternStart() {
                lockView.removePostClearPatternRunnable();
                lockView.setPattern(LockPatternView.DisplayMode.DEFAULT);
            }

            @Override
            public void onPatternComplete(List<LockPatternView.Cell> cells) {
                patternComplete(cells);
            }
        });
    }

    private void patternComplete(List<LockPatternView.Cell> cells) {
        if (cells.size() < 4) {
            lockView.setPattern(LockPatternView.DisplayMode.DEFAULT);
            tvMessage.setText(WonderfulToastUtils.getString(this,R.string.least_four_dot));
        }
        if (LockActivity.this.cells != null) {
            if (isSame(LockActivity.this.cells, cells)) {
                tvMessage.setText(WonderfulToastUtils.getString(this,R.string.pwd_ok));
                tvMessage.setTextColor(Color.parseColor("#FFFFFF"));
                SharedPreferenceInstance.getInstance().saveIsNeedShowLock(false);
                finish();
            } else {
                lockView.setPattern(LockPatternView.DisplayMode.ERROR);
                lockView.postClearPatternRunnable(500);
                tvMessage.setText(WonderfulToastUtils.getString(this,R.string.Password_error) + "！");
                tvMessage.setTextColor(Color.parseColor("#FC3F3C"));
                Animation animation = AnimationUtils.loadAnimation(this, R.anim.shake);
                tvMessage.startAnimation(animation);
            }
        }
    }

    private boolean isSame(List<LockPatternView.Cell> cells, List<LockPatternView.Cell> cells1) {
        if (cells.size() != cells1.size()) return false;
        for (int i = 0, len = cells.size(); i < len; i++) {
            if (cells.get(i).getIndex() != cells1.get(i).getIndex()) return false;
        }
        return true;
    }

    @Override
    protected void obtainData() {
        String password = EncryUtils.getInstance().decryptString(SharedPreferenceInstance.getInstance().getLockPwd(), "spark");
        cells = new ArrayList<>(password.length());
        for (int i = 0, len = password.length(); i < len; i++) {
            LockPatternView.Cell cell = lockView.new Cell(0, 0, 0, 0, Integer.parseInt(password.charAt(i) + ""));
            cells.add(cell);
        }
    }

    @Override
    protected void fillWidget() {
        User user = MyApplication.getApp().getCurrentUser();
        String url = user.getAvatar();
        String username = user.getUsername();
        if (WonderfulStringUtils.isEmpty(url))
            Glide.with(getApplicationContext()).load(R.mipmap.icon_default_header).into(ivHeader);
        else Glide.with(getApplicationContext()).load(url).into(ivHeader);
        tvNickName.setText(WonderfulStringUtils.isEmpty(username) ? getResources().getString(R.string.app_name) : username);
    }

    @Override
    protected void loadData() {

    }

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        if (!isSetTitle) {
            ImmersionBar.setTitleBar(this, llTitle);
            isSetTitle = true;
        }
    }

    private long lastPressTime = 0;

    @Override
    public void onBackPressed() {
        long now = System.currentTimeMillis();
        if (lastPressTime == 0 || now - lastPressTime > 2 * 1000) {
            WonderfulToastUtils.showToast(WonderfulToastUtils.getString(this,R.string.exit_again));
            lastPressTime = now;
        } else if (now - lastPressTime < 2 * 1000) {
            ActivityManage.finishAll();
        }

    }
}
