package com.bizzan.ui.set_lock;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gyf.barlibrary.ImmersionBar;
import com.bizzan.R;
import com.bizzan.base.BaseActivity;
import com.bizzan.utils.SharedPreferenceInstance;
import com.bizzan.customview.lock.LockPatternIndicator;
import com.bizzan.customview.lock.LockPatternView;
import com.bizzan.utils.EncryUtils;
import com.bizzan.utils.WonderfulToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class SetLockActivity extends BaseActivity {

    public static final int RETURN_SET_LOCK = 0;
    @BindView(R.id.tvCancle)
    TextView tvCancle;
    @BindView(R.id.llTitle)
    LinearLayout llTitle;
    @BindView(R.id.indicator)
    LockPatternIndicator indicator;
    @BindView(R.id.lockView)
    LockPatternView lockView;
    @BindView(R.id.tvMessage)
    TextView tvMessage;
    private String password = "";
    private List<LockPatternView.Cell> cells = null;
    private int type; //0  设置  1  关闭

    public static void actionStart(Activity activity, int type) {
        Intent intent = new Intent(activity, SetLockActivity.class);
        intent.putExtra("type", type);
        activity.startActivityForResult(intent, RETURN_SET_LOCK);
    }

    @Override
    protected int getActivityLayoutId() {
        return R.layout.activity_set_lock;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        isNeedChecke = false;
        password = EncryUtils.getInstance().decryptString(SharedPreferenceInstance.getInstance().getLockPwd(), "spark");
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
        tvCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void patternComplete(List<LockPatternView.Cell> cells) {
        if (cells.size() < 4) {
            lockView.setPattern(LockPatternView.DisplayMode.DEFAULT);
            tvMessage.setText(WonderfulToastUtils.getString(this,R.string.gesturesPassThirdPartTip6));
        }
        if (type == 0) {//设置
            if (SetLockActivity.this.cells == null && cells.size() >= 4) {
                SetLockActivity.this.cells = new ArrayList<>(cells);
                tvMessage.setText(WonderfulToastUtils.getString(this,R.string.gesturesPassThirdPartTip7));
                lockView.setPattern(LockPatternView.DisplayMode.DEFAULT);
                updateLockPatternIndicator();
                return;
            }
        }
        if (SetLockActivity.this.cells != null) {
            if (isSame(SetLockActivity.this.cells, cells)) {
                if (type == 0) {
                    tvMessage.setText(WonderfulToastUtils.getString(this,R.string.set_up_success));
                    saveCells(cells);
                    lockView.setPattern(LockPatternView.DisplayMode.DEFAULT);
                } else if (type == 1) {
                    tvMessage.setText(WonderfulToastUtils.getString(this,R.string.Abolish_success));
                    clearCells();
                }
                finish();

            } else {
                lockView.setPattern(LockPatternView.DisplayMode.ERROR);
                lockView.postClearPatternRunnable(500);
                if (type == 0) {
                    tvMessage.setText(WonderfulToastUtils.getString(this,R.string.pwd_diff));
                } else if (type == 1) {
                    tvMessage.setText(WonderfulToastUtils.getString(this,R.string.Password_error));
                }
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

    private void updateLockPatternIndicator() {
        if (cells != null) indicator.setIndicator(cells);
    }

    private void clearCells() {
        SharedPreferenceInstance.getInstance().saveLockPwd("");
    }

    private void saveCells(List<LockPatternView.Cell> cells) {
        String str = "";
        for (LockPatternView.Cell cell : cells) {
            str += cell.getIndex();
        }
        SharedPreferenceInstance.getInstance().saveLockPwd(EncryUtils.getInstance().encryptString(str, "spark"));
    }

    @Override
    protected void obtainData() {
        type = getIntent().getIntExtra("type", -1);
        if (type == 1) {
            password = EncryUtils.getInstance().decryptString(SharedPreferenceInstance.getInstance().getLockPwd(), "spark");
            cells = new ArrayList<>(password.length());
            for (int i = 0, len = password.length(); i < len; i++) {
                LockPatternView.Cell cell = lockView.new Cell(0, 0, 0, 0, Integer.parseInt(password.charAt(i) + ""));
                cells.add(cell);
            }
        }
    }

    @Override
    protected void fillWidget() {

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
}
