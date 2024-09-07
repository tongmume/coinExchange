package com.bizzan.ui.order_detail;

import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.gyf.barlibrary.ImmersionBar;
import com.bizzan.R;
import com.bizzan.ui.appeal.AppealActivity;
import com.bizzan.ui.chat.ChatActivity;
import com.bizzan.ui.my_order.MyOrderActivity;
import com.bizzan.ui.my_order.OrderFragment;
import com.bizzan.app.GlobalConstant;
import com.bizzan.app.MyApplication;
import com.bizzan.base.BaseActivity;
import com.bizzan.entity.OrderDetial;
import com.bizzan.socket.ISocket;
import com.bizzan.socket.SocketFactory;
import com.bizzan.utils.WonderfulCodeUtils;
import com.bizzan.utils.WonderfulDateUtils;
import com.bizzan.utils.WonderfulDialogUtils;
import com.bizzan.utils.WonderfulLogUtils;
import com.bizzan.utils.WonderfulPermissionUtils;
import com.bizzan.utils.WonderfulStringUtils;
import com.bizzan.utils.WonderfulToastUtils;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.Permission;
import com.yanzhenjie.permission.PermissionListener;

import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import butterknife.BindView;
import com.bizzan.app.Injection;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OrderDetailActivity extends BaseActivity implements OrderDetailContract.View, ISocket.TCPCallback {
    @BindView(R.id.ibBack)
    ImageButton ibBack;
    @BindView(R.id.ibDetail)
    TextView ibDetail;
    @BindView(R.id.llTitle)
    LinearLayout llTitle;
    /* @BindView(R.id.tvToChat)
     TextView tvToChat;*/
    @BindView(R.id.tvOrderSn)
    TextView tvOrderSn;
    @BindView(R.id.tvPrice)
    TextView tvPrice;
    @BindView(R.id.tvCount)
    TextView tvCount;
    @BindView(R.id.tvTotal)
    TextView tvTotal;
    @BindView(R.id.tvZhifubao)
    TextView tvZhifubao;
    @BindView(R.id.tvWeChat)
    TextView tvWeChat;
    @BindView(R.id.tvBankNo)
    TextView tvBankNo;
    @BindView(R.id.tvStatus)
    TextView tvStatus;
    @BindView(R.id.tvPayDone)
    TextView tvPayDone;
    @BindView(R.id.tvCancle)
    TextView tvCancle;
    @BindView(R.id.tvAppeal)
    TextView tvAppeal;
    @BindView(R.id.llPayInfo)
    LinearLayout llPayInfo;
    @BindView(R.id.llAppeal)
    LinearLayout llAppeal;
    @BindView(R.id.tvRelease)
    TextView tvRelease;
    @BindView(R.id.llOperate)
    LinearLayout llOperate;
    @BindView(R.id.ivZhifubaoQR)
    ImageView ivZhifubaoQR;
    @BindView(R.id.ivWeChatQR)
    ImageView ivWeChatQR;
    @BindView(R.id.tvOtherSide)
    TextView tvOtherSide;
    @BindView(R.id.tvTime)
    TextView tvTime;
    @BindView(R.id.tvLastTime)
    TextView tvLastTime;
    @BindView(R.id.llLastTime)
    LinearLayout llLastTime;
    @BindView(R.id.tvBranch)
    TextView tvBranch;
    private String orderSn;
    private OrderDetailContract.Presenter presenter;
    private OrderDetial orderDetial;
    private Intent intent;
    private String downloadUrl;
    private CountDownTimer timer;
    @BindView(R.id.view_back)
    View view_back;
    @BindView(R.id.tvBank)
    TextView tvBank;
    @BindView(R.id.line_zhankai)
    LinearLayout line_zhankai;
    @BindView(R.id.img_zhankai)
    ImageView img_zhankai;
    @BindView(R.id.line_xingming)
    LinearLayout line_xingming;
    private boolean isZham = false;

    @BindView(R.id.tvrealName)
    TextView tvrealName;

    //private String urlTest = "http://xinhuo-xindai.oss-cn-hangzhou.aliyuncs.com/2018/04/21/690c64c1-0767-416f-95a8-af1aadb724a5.jpg";
    private String dir = Environment.getExternalStorageDirectory().getAbsolutePath() + "/ATC/";//图片/
    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    Toast.makeText(OrderDetailActivity.this, WonderfulToastUtils.getString(OrderDetailActivity.this,R.string.Download_failed), Toast.LENGTH_SHORT).show();
                    break;
                case 2:
                    popWnd.dismiss();
                    Toast.makeText(OrderDetailActivity.this, WonderfulToastUtils.getString(OrderDetailActivity.this,R.string.success), Toast.LENGTH_SHORT).show();
                    break;
            }
            return true;
        }
    });
    private PopupWindow popWnd;
    private PopupWindow popWnd2;

    public static void actionStart(Context context, String orderSn) {
        Intent intent = new Intent(context, OrderDetailActivity.class);
        intent.putExtra("orderSn", orderSn);
        context.startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) presenter.orderDetail(getToken(), orderSn);
    }

    @Override
    protected int getActivityLayoutId() {
        return R.layout.activity_order_detial;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        new OrderDetailPresenter(Injection.provideTasksRepository(getApplicationContext()), this);
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
        tvPayDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                payDone();
            }
        });
        tvCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancle();
            }
        });
        tvRelease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                release();
            }
        });
        tvAppeal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appeal();
            }
        });
        /*tvToChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (orderDetial == null) {
                    WonderfulToastUtils.showToast(WonderfulToastUtils.getString(this,R.string.order_details_failed));
                    presenter.orderDetail(getToken(), orderSn);
                    return;
                }
                ChatActivity.actionStart(OrderDetailActivity.this, orderDetial);
            }
        });*/
        ibDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (orderDetial == null) {
                    WonderfulToastUtils.showToast(WonderfulToastUtils.getString(OrderDetailActivity.this,R.string.order_details_failed));
                    presenter.orderDetail(getToken(), orderSn);
                    return;
                }
                ChatActivity.actionStart(OrderDetailActivity.this, orderDetial);
            }
        });
        line_zhankai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(tvBankNo.getText())) {
                    return;
                }
                isZham = !isZham;
                Drawable no = getResources().getDrawable(R.drawable.yinhang_shouqi);
                Drawable yes = getResources().getDrawable(R.drawable.yinhang_zhankai);
                if (isZham) {
                    //点击显示
                    line_xingming.setVisibility(View.VISIBLE);
                    img_zhankai.setBackground(no);

                } else {
                    line_xingming.setVisibility(View.GONE);
                    img_zhankai.setBackground(yes);
                }


            }
        });
    }

    private void appeal() {
        String payTime = orderDetial.getPayTime();
        long payTimeMillis = WonderfulDateUtils.getTimeMillis("", payTime);
        long currentTime = System.currentTimeMillis();
        if (currentTime - payTimeMillis > 30 * 60 * 1000) {
            AppealActivity.actionStart(this, orderSn);
        } else {
            WonderfulToastUtils.showToast(WonderfulToastUtils.getString(this,R.string.up_appeal));
        }
    }

    private void release() {
        if (orderDetial != null) {
            showReleaseDialog();
        } else presenter.orderDetail(getToken(), orderSn);
    }

    private void showReleaseDialog() {
        View view = getLayoutInflater().inflate(R.layout.dialog_jypassword, null);
        TextView tvRelease = view.findViewById(R.id.tvRelease);
        final EditText etPassword = view.findViewById(R.id.etPassword);
        final AlertDialog dialog = new AlertDialog.Builder(this).setView(view).create();
        dialog.show();
        tvRelease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String jyPassword = etPassword.getText().toString();
                if (WonderfulStringUtils.isEmpty(jyPassword)) {
                    WonderfulToastUtils.showToast(WonderfulToastUtils.getString(OrderDetailActivity.this,R.string.paymentTip6));
                    return;
                }
                presenter.release(getToken(), orderSn, jyPassword);
                dialog.dismiss();
            }
        });
    }

    private void cancle() {
        if (orderDetial != null) {
            WonderfulDialogUtils.showDefaultDialog(this, WonderfulToastUtils.getString(this,R.string.Warm_prompt), WonderfulToastUtils.getString(this,R.string.paymentTip7), WonderfulToastUtils.getString(this,R.string.cancle), WonderfulToastUtils.getString(this,R.string.confirm), null, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    presenter.cancle(getToken(), orderSn);
                }
            });
        } else presenter.orderDetail(getToken(), orderSn);
    }

    private void payDone() {
        if (orderDetial != null) {
            WonderfulDialogUtils.showDefaultDialog(this, WonderfulToastUtils.getString(this,R.string.Warm_prompt), WonderfulToastUtils.getString(this,R.string.paymentTip8), WonderfulToastUtils.getString(this,R.string.cancle), WonderfulToastUtils.getString(this,R.string.confirm), null, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    presenter.payDone(getToken(), orderSn);
                }
            });
        } else presenter.orderDetail(getToken(), orderSn);

    }

    private JSONObject buildBodyJson() {
        JSONObject obj = new JSONObject();
        try {
            obj.put("orderId", orderDetial.getOrderSn());
            //obj.put("uid", orderDetial.getMyId());
            obj.put("uidFrom", MyApplication.getApp().getCurrentUser().getId());
            obj.put("uidTo", orderDetial.getHisId());
            obj.put("nameTo", orderDetial.getOtherSide());
            obj.put("nameFrom", MyApplication.getApp().getCurrentUser().getUsername());
            obj.put("messageType", 1);
            obj.put("content", "");
            obj.put("avatar", MyApplication.getApp().getCurrentUser().getAvatar());
            return obj;
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    protected void obtainData() {
        orderSn = getIntent().getStringExtra("orderSn");
    }

    @Override
    protected void fillWidget() {

    }

    @Override
    protected void loadData() {
        presenter.orderDetail(getToken(), orderSn);
    }

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        if (!isSetTitle) {
            isSetTitle = true;
            ImmersionBar.setTitleBar(this, llTitle);
        }
    }

    @Override
    public void setPresenter(OrderDetailContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void orderDetailSuccess(OrderDetial obj) {
        if (obj == null) return;
        this.orderDetial = obj;

        try {
            setViews();
        } catch (Exception e) {

        }

        /*intent = new Intent(OrderDetailActivity.this, MyBindService.class);
        intent.putExtra("type", MyBindService.CHAT);
        intent.putExtra("orderId", orderDetial.getOrderSn());
        intent.putExtra("uid", orderDetial.getMyId());
        startService(intent);*/
    }

    private void setViews() {
        tvOtherSide.setText(orderDetial.getOtherSide());
        tvOrderSn.setText(orderDetial.getOrderSn());
        tvPrice.setText(orderDetial.getPrice() + " CNY");
        tvCount.setText(orderDetial.getAmount() + " " + orderDetial.getUnit());
        tvTotal.setText(orderDetial.getMoney() + " CNY");
        tvTime.setText(orderDetial.getCreateTime());
        tvrealName.setText(orderDetial.getPayInfo().getRealName() + "");
        OrderDetial.PayInfoBean payInfoBean1 = orderDetial.getPayInfo();
        if (payInfoBean1.getBankInfo() != null) {
            tvBank.setText(orderDetial.getPayInfo().getBankInfo().getBank() + "");

        } else {


        }


        tvrealName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(tvrealName.getText())) {
                    return;
                }
                ClipboardManager cm = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                // 将文本内容放到系统剪贴板里。
                cm.setText(tvrealName.getText());
                WonderfulToastUtils.showToast(WonderfulToastUtils.getString(OrderDetailActivity.this,R.string.replicated));
            }
        });
        tvBranch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(tvBranch.getText())) {
                    return;
                }
                ClipboardManager cm = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                // 将文本内容放到系统剪贴板里。
                cm.setText(tvBranch.getText());
                WonderfulToastUtils.showToast(WonderfulToastUtils.getString(OrderDetailActivity.this,R.string.replicated));
            }
        });

        tvBankNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(tvBankNo.getText())) {
                    return;
                }
                ClipboardManager cm = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                // 将文本内容放到系统剪贴板里。
                cm.setText(tvBankNo.getText());
                WonderfulToastUtils.showToast(WonderfulToastUtils.getString(OrderDetailActivity.this,R.string.replicated));
            }
        });
        if (orderDetial.getType() == 0) {
            final OrderDetial.PayInfoBean payInfoBean = orderDetial.getPayInfo();
            if (payInfoBean != null) {
                if (payInfoBean.getAlipay() == null) {
                    tvZhifubao.setText("");
                    ivZhifubaoQR.setVisibility(View.GONE);
                } else {
                    tvZhifubao.setText(payInfoBean.getAlipay().getAliNo());
                    tvZhifubao.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (TextUtils.isEmpty(tvZhifubao.getText())) {
                                return;
                            }
                            ClipboardManager cm = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                            // 将文本内容放到系统剪贴板里。
                            cm.setText(tvZhifubao.getText());
                            WonderfulToastUtils.showToast(WonderfulToastUtils.getString(OrderDetailActivity.this,R.string.replicated));
                        }
                    });
                    ivZhifubaoQR.setVisibility(View.VISIBLE);
                    ivZhifubaoQR.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            if (payInfoBean.getAlipay().getQrCodeUrl() != null && !("".equals(payInfoBean.getAlipay().getQrCodeUrl()))) {
                                showPopWindow(payInfoBean.getAlipay().getQrCodeUrl());
                            } else {
                                WonderfulToastUtils.showToast(WonderfulToastUtils.getString(OrderDetailActivity.this,R.string.No_qrcode));
                            }
                            //showPopWindow("");
                        }
                    });
                }
                if (payInfoBean.getWechatPay() == null) {
                    tvWeChat.setText("");
                    ivWeChatQR.setVisibility(View.GONE);
                } else {
                    tvWeChat.setText(payInfoBean.getWechatPay().getWechatNo());
                    tvWeChat.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (TextUtils.isEmpty(tvWeChat.getText())) {
                                return;
                            }
                            ClipboardManager cm = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                            // 将文本内容放到系统剪贴板里。
                            cm.setText(tvWeChat.getText());
                            WonderfulToastUtils.showToast(WonderfulToastUtils.getString(OrderDetailActivity.this,R.string.replicated));
                        }
                    });
                    ivWeChatQR.setVisibility(View.VISIBLE);
                    ivWeChatQR.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (payInfoBean.getWechatPay().getQrCodeUrl() != null) {

                                showPopWindow2(payInfoBean.getWechatPay().getQrCodeUrl());
                            } else
                                WonderfulToastUtils.showToast(WonderfulToastUtils.getString(OrderDetailActivity.this,R.string.No_qrcode));
                        }
                    });
                }
                if (payInfoBean.getBankInfo() != null) {
                    tvBankNo.setText(payInfoBean.getBankInfo().getCardNo() + "");
                    tvBranch.setText(payInfoBean.getBankInfo().getBranch() + "");
                } else {
                    tvBranch.setVisibility(View.GONE);
                    tvBankNo.setText("");
                }
            }
        } else llPayInfo.setVisibility(View.GONE);
        OrderFragment.Status status = OrderFragment.Status.values()[orderDetial.getStatus()];
        tvStatus.setText(status.getStatusStr());
        int type = orderDetial.getType();
        showWhichViews(type, status);
    }


    public void showPopWindow(String url) {

        downloadUrl = url;
        View contentView = LayoutInflater.from(OrderDetailActivity.this).inflate(R.layout.pop_order_detail, null);

        if (popWnd != null) {

        } else {
            popWnd = new PopupWindow(OrderDetailActivity.this);
            popWnd.setContentView(contentView);
            popWnd.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
            popWnd.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
            popWnd.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            // 设置PopupWindow是否能响应外部点击事件
            popWnd.setOutsideTouchable(true);
            // 设置PopupWindow是否能响应点击事件
            popWnd.setTouchable(true);

        }
        darkenBackground(0.4f);
        ImageView ivQR = contentView.findViewById(R.id.ivQR);

        Glide.with(this).load(url).into(ivQR);
        ivQR.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (!WonderfulPermissionUtils.isCanUseStorage(OrderDetailActivity.this))
                    checkPermission(GlobalConstant.PERMISSION_STORAGE, Permission.STORAGE);
                else {
                    WonderfulToastUtils.showToast(WonderfulToastUtils.getString(OrderDetailActivity.this,R.string.Start_downloading));
                    download(downloadUrl);
                }
                return true;
            }
        });
        View rootview = LayoutInflater.from(OrderDetailActivity.this).inflate(R.layout.activity_order_detial, null);
        popWnd.showAtLocation(rootview, Gravity.CENTER, 0, 0);
        popWnd.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                darkenBackground(1f);
            }
        });
    }

    public void showPopWindow2(String url) {

        downloadUrl = url;
        View contentView = LayoutInflater.from(OrderDetailActivity.this).inflate(R.layout.pop_order_detail, null);

        if (popWnd2 != null) {

        } else {
            popWnd2 = new PopupWindow(OrderDetailActivity.this);
            popWnd2.setContentView(contentView);
            popWnd2.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
            popWnd2.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
            popWnd2.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            // 设置PopupWindow是否能响应外部点击事件
            popWnd2.setOutsideTouchable(true);
            // 设置PopupWindow是否能响应点击事件
            popWnd2.setTouchable(true);

        }
        darkenBackground(0.4f);
        ImageView ivQR = contentView.findViewById(R.id.ivQR);

        Glide.with(this).load(url).into(ivQR);
        ivQR.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (!WonderfulPermissionUtils.isCanUseStorage(OrderDetailActivity.this))
                    checkPermission(GlobalConstant.PERMISSION_STORAGE, Permission.STORAGE);
                else {
                    WonderfulToastUtils.showToast(WonderfulToastUtils.getString(OrderDetailActivity.this,R.string.Start_downloading));
                    download(downloadUrl);
                }
                return true;
            }
        });
        View rootview = LayoutInflater.from(OrderDetailActivity.this).inflate(R.layout.activity_order_detial, null);
        popWnd2.showAtLocation(rootview, Gravity.CENTER, 0, 0);
        popWnd2.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                darkenBackground(1f);
            }
        });
    }

    /**
     * 改变背景颜色
     */
    private void darkenBackground(Float bgcolor) {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = bgcolor;

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        getWindow().setAttributes(lp);

    }

    private void checkPermission(int requestCode, String[] permissions) {
        AndPermission.with(OrderDetailActivity.this).requestCode(requestCode).permission(permissions).callback(permissionListener).start();
    }

    private PermissionListener permissionListener = new PermissionListener() {
        @Override
        public void onSucceed(int requestCode, @NonNull List<String> grantPermissions) {
            switch (requestCode) {
                case GlobalConstant.PERMISSION_STORAGE:
                    download(downloadUrl);
                    break;
            }
        }

        @Override
        public void onFailed(int requestCode, @NonNull List<String> deniedPermissions) {
            switch (requestCode) {
                case GlobalConstant.PERMISSION_STORAGE:
                    WonderfulToastUtils.showToast(WonderfulToastUtils.getString(OrderDetailActivity.this,R.string.storage_permission));
                    break;
            }
        }
    };


    private void download(String url) {
        if (url == null || url.equals("")) {
            return;
        }
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .get()
                .url(url)
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Message message = Message.obtain();
                message.what = 1;
                handler.sendMessage(message);
                //Toast.makeText(getActivity(),"下载失败",Toast.LENGTH_SHORT).show();
                WonderfulLogUtils.logi("OrderDetailDialogFragment   ", "下载失败" + e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                //将响应数据转化为输入流数据
                InputStream inputStream = response.body().byteStream();
                //将输入流数据转化为Bitmap位图数据
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                Calendar now = new GregorianCalendar();
                SimpleDateFormat simpleDate = new SimpleDateFormat("yyyyMMddHHmmss", Locale.getDefault());
                String fileName = simpleDate.format(now.getTime());
                File folderFile = new File(dir);
                if (!folderFile.exists()) {
                    // mkdir() 不会创建多级目录，所以导致后面的代码报错 提示文件或目录不存在
                    // folderFile.mkdir();
                    // mkdirs() 则会创建多级目录
                    folderFile.mkdirs();
                }
                File file = new File(dir + fileName + ".jpg");
                try {
                    if (file.exists()) {
                        file.delete();
                    }
                    file.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                //创建文件输出流对象用来向文件中写入数据
                FileOutputStream out = new FileOutputStream(file);
                //将bitmap存储为jpg格式的图片
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
                //刷新文件流
                out.flush();
                out.close();
                Uri uri = Uri.fromFile(file);
                sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, uri));
                Message message = Message.obtain();
                message.what = 2;
                handler.sendMessage(message);

            }
        });
    }

    private void showWhichViews(int type, OrderFragment.Status status) {
        switch (status) {
            case CANC:
                llOperate.setVisibility(View.GONE);
                llAppeal.setVisibility(View.GONE);
                break;
            case UNPAID:
                llAppeal.setVisibility(View.GONE);
                if (type == 0) {
                    llOperate.setVisibility(View.VISIBLE);
                    tvPayDone.setVisibility(View.VISIBLE);
                    tvCancle.setVisibility(View.VISIBLE);
                    llLastTime.setVisibility(View.VISIBLE);
                    getTime();
                    tvRelease.setVisibility(View.GONE);
                } else if (type == 1) {
                    llOperate.setVisibility(View.GONE);
                }
                break;
            case PAID:
                llOperate.setVisibility(View.VISIBLE);
                if (type == 0) {
                    llAppeal.setVisibility(View.VISIBLE);
                    llOperate.setVisibility(View.GONE);
                } else if (type == 1) {
                    tvPayDone.setVisibility(View.GONE);
                    tvCancle.setVisibility(View.GONE);
                    llAppeal.setVisibility(View.VISIBLE);
                    tvRelease.setVisibility(View.VISIBLE);
                }
                break;
            case DONE:
                llOperate.setVisibility(View.GONE);
                llAppeal.setVisibility(View.GONE);
                break;
            case COMPLAINING:
                llOperate.setVisibility(View.GONE);
                llAppeal.setVisibility(View.GONE);
                break;
        }
    }

    private void getTime() {
        long createTime = WonderfulDateUtils.getTimeMillis("", orderDetial.getCreateTime());
        long timeLimit = (long) (orderDetial.getTimeLimit() * 60 * 1000);
        long currentTime = System.currentTimeMillis();
        if (createTime + timeLimit - currentTime > 0) {
            fillCodeView(createTime + timeLimit - currentTime);
        } else tvLastTime.setText("00:00:00");
        WonderfulLogUtils.logi("OrderDetailActivity", "减减  " + (createTime + timeLimit - currentTime));
    }

    private void fillCodeView(long time) {
        WonderfulLogUtils.logi("OrderDetailActivity", "time  " + time);
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
        timer = new CountDownTimer(time, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
                formatter.setTimeZone(TimeZone.getTimeZone("GMT+00:00"));
                String hms = formatter.format(millisUntilFinished);
                if (!WonderfulStringUtils.isEmpty(hms)) {
                    tvLastTime.setText(formatter.format(millisUntilFinished));
                }
            }

            @Override
            public void onFinish() {
                tvLastTime.setText("00:00:00");
                timer.cancel();
                timer = null;
            }
        };
        timer.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }

    @Override
    public void orderDetaileFail(Integer code, String toastMessage) {
        WonderfulCodeUtils.checkedErrorCode(this, code, toastMessage);
    }

    @Override
    public void payDoneSuccess(String obj) {
        WonderfulToastUtils.showToast(obj);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                presenter.orderDetail(getToken(), orderSn);
            }
        }, 500);
        MyOrderActivity.actionStart(OrderDetailActivity.this, 1);
        JSONObject json = buildBodyJson();
        SocketFactory.produceSocket(ISocket.C2C).sendRequest(ISocket.CMD.SEND_CHAT, json.toString().getBytes(), OrderDetailActivity.this);
    }

    @Override
    public void payDoneFail(Integer code, String toastMessage) {
        WonderfulCodeUtils.checkedErrorCode(this, code, toastMessage);
    }

    @Override
    public void cancleSuccess(String obj) {
        WonderfulToastUtils.showToast(obj);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                presenter.orderDetail(getToken(), orderSn);
            }
        }, 500);
    }

    @Override
    public void cancleFail(Integer code, String toastMessage) {
        WonderfulCodeUtils.checkedErrorCode(this, code, toastMessage);
    }

    @Override
    public void releaseSuccess(String obj) {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                presenter.orderDetail(getToken(), orderSn);
            }
        }, 500);
    }

    @Override
    public void releaseFail(Integer code, String toastMessage) {
        WonderfulCodeUtils.checkedErrorCode(this, code, toastMessage);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void dataSuccess(ISocket.CMD cmd, String response) {
        WonderfulLogUtils.logi(cmd + "  发送订单成功的回执：", response);
    }

    @Override
    public void dataFail(int code, ISocket.CMD cmd, String errorInfo) {
        WonderfulLogUtils.logi(cmd + "  发送订单出错的回执：", errorInfo);
    }
}
