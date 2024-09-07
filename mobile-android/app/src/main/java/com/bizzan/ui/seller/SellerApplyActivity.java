package com.bizzan.ui.seller;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gyf.barlibrary.ImmersionBar;
import com.bizzan.R;
import com.bizzan.ui.message_detail.MessageHelpActivity;
import com.bizzan.app.GlobalConstant;
import com.bizzan.base.BaseActivity;
import com.bizzan.ui.dialog.BuyOrSellDialogFragment;
import com.bizzan.utils.WonderfulToastUtils;

import butterknife.BindView;
import butterknife.BindViews;
import com.bizzan.app.Injection;

public class SellerApplyActivity extends BaseActivity implements SellerApplyContract.View, View.OnClickListener {

    @BindView(R.id.text_shangjia)
    TextView text_shangjia;
    @BindView(R.id.llTitle)
    LinearLayout llTitle;
    @BindView(R.id.tv_apply_seller_state_title)
    TextView tv_apply_seller_state_title;
    @BindView(R.id.ll_apply_seller_process)
    LinearLayout ll_apply_seller_process;
    @BindView(R.id.iv_seller_apply_process)
    ImageView iv_seller_apply_process;
    @BindView(R.id.tv_release_advertisement)
    TextView tv_release_advertisement;
    @BindView(R.id.vp_seller_apply)
    ViewPager vp_seller_apply;
    @BindView(R.id.ll_circle_indicator)
    LinearLayout llCircleIndicator;
    @BindView(R.id.ll_apply_seller_bottom)
    LinearLayout ll_apply_seller_bottom;
    @BindView(R.id.iv_check_box)
    ImageView iv_check_box;
    @BindView(R.id.tv_commit)
    TextView tv_commit;
    @BindView(R.id.iv_back)
    ImageView iv_back;
    @BindView(R.id.ll_failed)
    LinearLayout ll_failed;
    @BindView(R.id.tv_retry)
    TextView tv_retry;
    @BindView(R.id.tv_failed_reason)
    TextView tv_failed_reason;
    @BindViews({R.id.tv_indicator0, R.id.tv_indicator1, R.id.tv_indicator2})
    TextView[] indicators;
    private BuyOrSellDialogFragment buyOrSellDialogFragment;
    @BindView(R.id.view_back)
    TextView view_back;


    private SellerApplyContract.Presenter presenter;
    private String status;


    public static void actionStart(Context context, String certifiedBusinessStatus, String reason) {
        Intent intent = new Intent(context, SellerApplyActivity.class);
        intent.putExtra("status", certifiedBusinessStatus);
        intent.putExtra("reason", reason);
        context.startActivity(intent);
    }

    @Override
    protected int getActivityLayoutId() {
        return R.layout.activity_seller_apply;
    }


    @Override
    protected void initViews(Bundle savedInstanceState) {
        new SellerApplyPresent(Injection.provideTasksRepository(getApplicationContext()), this);
        status = getIntent().getStringExtra("status");
        setViewByState(status);
        iv_back.setOnClickListener(this);
        view_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tv_commit.setOnClickListener(this);
        tv_release_advertisement.setOnClickListener(this);
        iv_check_box.setOnClickListener(this);
        tv_retry.setOnClickListener(this);
        initViewPagerAndIndicator();
        setIndicator(0);
        text_shangjia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MessageHelpActivity.actionStart(SellerApplyActivity.this, GlobalConstant.SELLER_AGREEMENT_ID);
            }
        });
    }

    private void setIndicator(int position) {
        for (int i = 0; i < indicators.length; i++) {
            indicators[i].setSelected(i == position);
        }
    }

    private void initViewPagerAndIndicator() {
        vp_seller_apply.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return 3;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {

            }

            @Override
            public boolean isViewFromObject(View view, Object o) {
                return view == o;
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                ImageView imageView = new ImageView(SellerApplyActivity.this);
                ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                imageView.setLayoutParams(params);
                int[] resources = {R.drawable.icon_seller_apply_1, R.drawable.icon_seller_apply_2, R.drawable.icon_seller_apply_3};
                imageView.setImageResource(resources[position]);
                container.addView(imageView);
                return imageView;
            }
        });
        vp_seller_apply.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                setIndicator(i);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }


    /**
     * NOT_CERTIFIED("未认证"),//0
     * AUDITING("认证-待审核"), //1
     * VERIFIED("认证-审核成功"),//2
     * FAILED("认证-审核失败"),  //3
     * DEPOSIT_LESS("保证金不足"), //4
     * CANCEL_AUTH("退保-待审核"), //5
     * RETURN_FAILED("退保-审核失败"), //6
     * RETURN_SUCCESS("退保-审核成功") //7
     */
    private void setViewByState(String state) {
        switch (state) {
            case "0"://未成为商家
                ll_apply_seller_process.setVisibility(View.GONE);
                ll_apply_seller_bottom.setVisibility(View.VISIBLE);
                break;
            case "1"://已申请审核中
                ll_apply_seller_process.setVisibility(View.VISIBLE);
                tv_release_advertisement.setVisibility(View.GONE);
                ll_apply_seller_bottom.setVisibility(View.GONE);
                break;
            case "2"://审核通过
                ll_apply_seller_bottom.setVisibility(View.GONE);
                iv_seller_apply_process.setImageResource(R.drawable.icon_apply_seller_process_success);
                tv_release_advertisement.setVisibility(View.VISIBLE);
                break;
            case "3"://审核拒绝
                ll_apply_seller_bottom.setVisibility(View.GONE);
                iv_seller_apply_process.setImageResource(R.drawable.icon_seller_apply_failed);
                tv_release_advertisement.setVisibility(View.GONE);
                ll_failed.setVisibility(View.VISIBLE);
                tv_failed_reason.setText(WonderfulToastUtils.getString(this,R.string.cause)+":" + getIntent().getStringExtra("reason"));
                break;
            case "4"://保证金不足
                ll_apply_seller_bottom.setVisibility(View.GONE);
                iv_seller_apply_process.setImageResource(R.drawable.icon_seller_apply_failed);
                tv_release_advertisement.setVisibility(View.GONE);
                ll_failed.setVisibility(View.VISIBLE);
                tv_failed_reason.setText(WonderfulToastUtils.getString(this,R.string.cause)+":" + getIntent().getStringExtra("reason"));
                break;
            case "5"://"退保-待审核"
                iv_seller_apply_process.setImageResource(R.drawable.icon_apply_seller_process_success);
                tv_release_advertisement.setVisibility(View.GONE);
                ll_apply_seller_bottom.setVisibility(View.GONE);
                break;
            case "6"://"退保-审核失败"
                iv_seller_apply_process.setImageResource(R.drawable.icon_apply_seller_process_success);
                tv_release_advertisement.setVisibility(View.VISIBLE);
                ll_apply_seller_bottom.setVisibility(View.GONE);
                break;
            case "7"://"退保-审核成功"
                tv_release_advertisement.setVisibility(View.VISIBLE);
                ll_apply_seller_bottom.setVisibility(View.VISIBLE);
                break;
            default:
        }
    }

    @Override
    protected void obtainData() {

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


    @Override
    public void setPresenter(SellerApplyContract.Presenter presenter) {
        this.presenter = presenter;
    }

    private void showBottomDialog() {
        if (buyOrSellDialogFragment == null)
            buyOrSellDialogFragment = new BuyOrSellDialogFragment();
        buyOrSellDialogFragment.show(getSupportFragmentManager(), "bottom");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_commit:
                if (iv_check_box.isSelected()) {
                    SellerApplyCommitActivity.actionStart(this);
                    finish();
                } else {
                    WonderfulToastUtils.showToast(WonderfulToastUtils.getString(this,R.string.read_agreement));
                }
                break;
            case R.id.tv_release_advertisement:
                showBottomDialog();

                break;
            case R.id.iv_check_box:
                if (iv_check_box.isSelected()) {
                    iv_check_box.setSelected(false);
                } else {
                    iv_check_box.setSelected(true);
                }
                break;
            case R.id.tv_retry:
                SellerApplyCommitActivity.actionStart(this);
                finish();
                break;
            default:
        }
    }
}
