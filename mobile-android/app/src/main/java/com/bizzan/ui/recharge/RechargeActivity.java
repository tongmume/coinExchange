package com.bizzan.ui.recharge;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.gyf.barlibrary.ImmersionBar;

import com.bizzan.R;
import com.bizzan.base.BaseActivity;
import com.bizzan.entity.Coin;
import com.bizzan.app.UrlFactory;
import com.bizzan.utils.SharedPreferenceInstance;
import com.bizzan.utils.WonderfulBitmapUtils;
import com.bizzan.utils.WonderfulCommonUtils;
import com.bizzan.utils.WonderfulLogUtils;
import com.bizzan.utils.WonderfulStringUtils;
import com.bizzan.utils.WonderfulToastUtils;
import com.bizzan.utils.okhttp.StringCallback;
import com.bizzan.utils.okhttp.WonderfulOkhttpUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.Hashtable;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import okhttp3.Request;

public class RechargeActivity extends BaseActivity {

    @BindView(R.id.ibBack)
    ImageButton ibBack;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.tvAddressText)
    TextView tvAddressText;
    @BindView(R.id.ivAddress)
    ImageView ivAddress;
    @BindView(R.id.tvAddress)
    TextView tvAddress;
    @BindView(R.id.llAlbum)
    LinearLayout llAlbum;
    @BindView(R.id.layout_memo)
    LinearLayout layout_memo;
    @BindView(R.id.llCopy)
    LinearLayout llCopy;
    @BindView(R.id.llTitle)
    LinearLayout llTitle;
    @BindView(R.id.text_deposit)
    TextView text_deposit;
    @BindView(R.id.text_memo)
    TextView text_memo;

    private Coin coin;
    private Bitmap saveBitmap;
    @BindView(R.id.view_back)
    View view_back;
    private int biaoshi = 0;

    public static void actionStart(Context context, Coin coin) {
        Intent intent = new Intent(context, RechargeActivity.class);
        intent.putExtra("coin", coin);
        context.startActivity(intent);
    }

    @Override
    protected int getActivityLayoutId() {
        return R.layout.activity_recharge;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        ibBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        view_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        llCopy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                copy();
            }
        });
        llAlbum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                save();
            }
        });
        this.coin = (Coin) getIntent().getSerializableExtra("coin");
        tvAddress.setText(WonderfulToastUtils.getString(this,R.string.now_link) + this.coin.getUnit() + WonderfulToastUtils.getString(this,R.string.node) + "...");
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (biaoshi == 0) {
            biaoshi = 1;
            if (coin.getCoin().getAccountType() == 0) {
                if (coin.getAddress() == null || "".equals(coin.getAddress())) {
                    displayLoadingPopup();
                    Timer timer = new Timer();
                    TimerTask task = new TimerTask() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    try {
                                        layout_memo.setVisibility(View.GONE);
                                        huoqu();
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                            });
                        }
                    };
                    timer.schedule(task, 5000);
                } else {
                    try {
                        layout_memo.setVisibility(View.GONE);
                        text_deposit.setText(String.format("• "+WonderfulToastUtils.getString(this,R.string.rush1)+"：%s %s，"+WonderfulToastUtils.getString(this,R.string.rush2)+"\n• "+WonderfulToastUtils.getString(this,R.string.rush3)+"\n• "+WonderfulToastUtils.getString(this,R.string.rush4)+"\n• "+WonderfulToastUtils.getString(this,R.string.rush5)+"\n• "+WonderfulToastUtils.getString(this,R.string.rush6)+"", coin.getCoin().getMinRechargeAmount(), coin.getCoin().getUnit()));
                        erciLoad();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            } else {
                if (coin.getCoin().getDepositAddress() == null || coin.getCoin().getDepositAddress().equals("")) {
                    WonderfulToastUtils.showToast(WonderfulToastUtils.getString(this,R.string.no_rush));
                } else {
                    layout_memo.setVisibility(View.GONE);
                    text_deposit.setText(String.format("• "+WonderfulToastUtils.getString(this,R.string.rush1)+"：%s %s，"+WonderfulToastUtils.getString(this,R.string.rush2)+"\n• "+WonderfulToastUtils.getString(this,R.string.rush3)+"\n• "+WonderfulToastUtils.getString(this,R.string.rush4)+"\n• "+WonderfulToastUtils.getString(this,R.string.rush5)+"\n• "+WonderfulToastUtils.getString(this,R.string.rush6)+"", coin.getCoin().getMinRechargeAmount(), coin.getCoin().getUnit()));
                    erciLoad();
                }
            }
        }

    }

    private void save() {
        File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/ATC/" + System.currentTimeMillis() + ".jpg");
        if (!file.getParentFile().exists()) {
            file.mkdirs();
        }
        if (!file.exists()) try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        if (saveBitmap != null) try {
            WonderfulBitmapUtils.saveBitmapToFile(saveBitmap, file, 100);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        WonderfulToastUtils.showToast(WonderfulToastUtils.getString(this,R.string.save_ok));
    }

    private void huoqu() {
        final String formatStr = "• "+WonderfulToastUtils.getString(this,R.string.rush1)+"：%s %s，"+WonderfulToastUtils.getString(this,R.string.rush2)+"\n• "+WonderfulToastUtils.getString(this,R.string.rush3)+"\n• "+WonderfulToastUtils.getString(this,R.string.rush4)+"\n• "+WonderfulToastUtils.getString(this,R.string.rush5)+"\n• "+WonderfulToastUtils.getString(this,R.string.rush6)+"";
        WonderfulOkhttpUtils.post().url(UrlFactory.getWalletUrl()).addHeader("x-auth-token", SharedPreferenceInstance.getInstance().getTOKEN()).build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {
                super.onError(request, e);
                WonderfulLogUtils.logi("获取所有钱包出错", "获取所有钱包出错：" + e.getMessage());
                hideLoadingPopup();
            }

            @Override
            public void onResponse(String response) {
                WonderfulLogUtils.logi("获取所有钱包回执：", "获取所有钱包回执：" + response.toString());
                hideLoadingPopup();
                try {
                    JSONObject object = new JSONObject(response);
                    if (object.optInt("code") == 0) {
                        List<Coin> objs = gson.fromJson(object.getJSONArray("data").toString(), new TypeToken<List<Coin>>() {
                        }.getType());
                        for (int i = 0; i < objs.size(); i++) {
                            Coin coin1 = objs.get(i);
                            WonderfulLogUtils.logi("miao", coin1.getId() + "-----" + coin.getId());
                            if (coin.getId() == coin1.getId()) {
                                WonderfulLogUtils.logi("miao", coin1.getAddress() + "-----");
                                if (coin1.getCoin().getAccountType() == 1) {
                                    // EOS类型账户地址
                                    coin.setAddress(coin1.getCoin().getDepositAddress());
                                    layout_memo.setVisibility(View.VISIBLE);
                                    text_memo.setText(String.valueOf(coin.getMemberId() + 345678));
                                    text_deposit.setText(String.format(formatStr, coin1.getCoin().getMinRechargeAmount(), coin1.getCoin().getUnit()));
                                    erciLoad();
                                    break;
                                } else {
                                    coin.setAddress(coin1.getAddress());
                                    layout_memo.setVisibility(View.GONE);
                                    text_deposit.setText(String.format(formatStr, coin1.getCoin().getMinRechargeAmount(), coin1.getCoin().getUnit()));
                                    erciLoad();
                                    break;
                                }
                            }
                        }
                        if (coin.getAddress() == null || "".equals(coin.getAddress())) {
                            WonderfulToastUtils.showToast(WonderfulToastUtils.getString(RechargeActivity.this,R.string.create_rush));
                        }
                    } else {
                        WonderfulToastUtils.showToast("" + object.optString("message"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void copy() {
        WonderfulCommonUtils.copyText(this, tvAddress.getText().toString());
        WonderfulToastUtils.showToast(R.string.copy_success);
    }

    @Override
    protected void obtainData() {

    }

    @Override
    protected void fillWidget() {

    }

    private void erciLoad() {
        if (tvTitle == null) {
            return;
        }

        tvTitle.setText(coin.getCoin().getUnit() + WonderfulToastUtils.getString(this,R.string.chargeMoney));
        tvAddressText.setText(coin.getCoin().getUnit() + WonderfulToastUtils.getString(this,R.string.rush_address));
        if (coin.getCoin().getAccountType() == 0) {
            tvAddress.setText(coin.getAddress());
            layout_memo.setVisibility(View.GONE);
        } else {
            tvAddress.setText(coin.getCoin().getDepositAddress());
            layout_memo.setVisibility(View.VISIBLE);
            text_memo.setText(String.valueOf(coin.getMemberId() + 345678));
        }

        if (coin == null) {
            tvAddress.setText(WonderfulToastUtils.getString(this,R.string.unChargeMoneyTip1));
            return;
        }
        if (coin.getCoin() == null) {
            tvAddress.setText(WonderfulToastUtils.getString(this,R.string.unChargeMoneyTip1));
            return;
        }
        if (coin.getCoin().getAccountType() == 0 && coin.getAddress() == null) {
            tvAddress.setText(WonderfulToastUtils.getString(this,R.string.unChargeMoneyTip1));
            return;
        }

        if (coin.getCoin().getAccountType() == 1 && coin.getCoin().getDepositAddress() == null) {
            tvAddress.setText(WonderfulToastUtils.getString(this,R.string.unChargeMoneyTip1));
            return;
        }

        ivAddress.post(new Runnable() {
            @Override
            public void run() {
                if (coin.getCoin().getAccountType() == 0) {
                    if (WonderfulStringUtils.isEmpty(coin.getAddress())) return;
                    saveBitmap = createQRCode(coin.getAddress(), Math.min(ivAddress.getWidth(), ivAddress.getHeight()));
                }
                if (coin.getCoin().getAccountType() == 1) {
                    if (WonderfulStringUtils.isEmpty(coin.getCoin().getDepositAddress())) return;
                    saveBitmap = createQRCode(coin.getCoin().getDepositAddress(), Math.min(ivAddress.getWidth(), ivAddress.getHeight()));
                }

                ivAddress.setImageBitmap(saveBitmap);
            }
        });
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

    public static Bitmap createQRCode(String text, int size) {
        try {
            Hashtable<EncodeHintType, Object> hints = new Hashtable<>();
            hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
            hints.put(EncodeHintType.MARGIN, 2);   //设置白边大小 取值为 0- 4 越大白边越大
            BitMatrix bitMatrix = new QRCodeWriter().encode(text, BarcodeFormat.QR_CODE, size, size, hints);
            int[] pixels = new int[size * size];
            for (int y = 0; y < size; y++) {
                for (int x = 0; x < size; x++) {
                    if (bitMatrix.get(x, y)) {
                        pixels[y * size + x] = 0xff000000;
                    } else {
                        pixels[y * size + x] = 0xffffffff;
                    }
                }
            }
            Bitmap bitmap = Bitmap.createBitmap(size, size,
                    Bitmap.Config.ARGB_8888);
            bitmap.setPixels(pixels, 0, size, 0, 0, size, size);
            return bitmap;
        } catch (WriterException e) {
            e.printStackTrace();
            return null;
        }
    }
}
