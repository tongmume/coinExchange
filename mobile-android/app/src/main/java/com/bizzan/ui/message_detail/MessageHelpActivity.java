package com.bizzan.ui.message_detail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gyf.barlibrary.ImmersionBar;
import com.bizzan.R;
import com.bizzan.base.BaseActivity;
import com.bizzan.entity.Message;
import com.bizzan.app.UrlFactory;
import com.bizzan.utils.WonderfulLogUtils;
import com.bizzan.utils.WonderfulToastUtils;
import com.bizzan.utils.okhttp.StringCallback;
import com.bizzan.utils.okhttp.WonderfulOkhttpUtils;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import okhttp3.Request;

public class MessageHelpActivity extends BaseActivity {

    @BindView(R.id.ibBack)
    ImageButton ibBack;
    @BindView(R.id.ibDetail)
    TextView ibDetail;
    @BindView(R.id.llTitle)
    LinearLayout llTitle;
    @BindView(R.id.wb)
    WebView wb;
    @BindView(R.id.text)
    TextView text;
    @BindView(R.id.text_time)
    TextView text_time;
    public final static String CSS_STYLE = "<style>* {font-size:16px;line-height:20px;}p {color:#FFFFFF;}</style>";
    private String id;
    @BindView(R.id.view_back)
    View view_back;

    @BindView(R.id.ibSahre)
    ImageButton ibSahre;
    private String title;
    public static void actionStart(Context context, String id) {
        Intent intent = new Intent(context, MessageHelpActivity.class);
        intent.putExtra("id", id);
        context.startActivity(intent);
    }

    @Override
    protected int getActivityLayoutId() {
        return R.layout.activity_message_detail;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        initWb();
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
        title = "【"+WonderfulToastUtils.getString(this,R.string.help)+"】- ";
        ibSahre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent textIntent = new Intent(Intent.ACTION_SEND);
                textIntent.setType("text/plain");
                textIntent.putExtra(Intent.EXTRA_TEXT, title +
                        "(" + WonderfulToastUtils.getString(MessageHelpActivity.this,R.string.details) +
                        "：https://www.bizzan.com/notice/index?id=" + id + ") -" +
                        WonderfulToastUtils.getString(MessageHelpActivity.this,R.string.web_url) + " | " +
                        WonderfulToastUtils.getString(MessageHelpActivity.this,R.string.web_tv1) +
                        " | " + WonderfulToastUtils.getString(MessageHelpActivity.this,R.string.web_tv2) + "");
                startActivity(Intent.createChooser(textIntent, WonderfulToastUtils.getString(MessageHelpActivity.this,R.string.web_share)));
            }
        });
    }

    private void initWb() {
        wb.getSettings().setJavaScriptEnabled(true);
        wb.getSettings().setSupportZoom(false);
        wb.getSettings().setBuiltInZoomControls(false);
        wb.getSettings().setUseWideViewPort(true);
        wb.getSettings().setLoadWithOverviewMode(true);
        wb.getSettings().setDefaultFontSize(20);
        wb.setWebViewClient(new WebViewClient());
        wb.setBackgroundColor(getResources().getColor(R.color.transparent));
    }

    @Override
    protected void obtainData() {
        id = getIntent().getStringExtra("id");
        WonderfulLogUtils.logi("miao", id);
    }

    @Override
    protected void fillWidget() {

    }

    @Override
    protected void loadData() {
        displayLoadingPopup();
        qingQiu();
    }

    private void qingQiu() {
        WonderfulOkhttpUtils.post().url(UrlFactory.getMessageHelpUrl()).addParams("id", id).build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {
                super.onError(request, e);
                WonderfulLogUtils.logi("帮助详情出错", "帮助详情出错：" + e.getMessage());
                hideLoadingPopup();
            }

            @Override
            public void onResponse(String response) {
                WonderfulLogUtils.logi("帮助详情回执：", "帮助详情回执：" + response.toString());
                hideLoadingPopup();
                try {
                    JSONObject object = new JSONObject(response);
                    if (object.optInt("code") == 0) {
                        Message obj = gson.fromJson(object.getJSONObject("data").toString(), Message.class);
                        if (obj == null) return;
                        if (text == null) {
                            return;
                        }
                        title = title + obj.getTitle();
                        text.setText(obj.getTitle());
                        text_time.setText(obj.getCreateTime()  );
                        wb.getSettings().setTextSize(WebSettings.TextSize.LARGEST);
                        wb.setWebViewClient(new WebViewClient(){
                            @Override
                            public void onPageFinished(WebView view, String url) {
                                super.onPageFinished(view, url);
                                view.loadUrl("javascript:function modifyTextColor(){" +
                                        "document.getElementsByTagName('body')[0].style.webkitTextFillColor='#74777a';" +
                                        "var objs = document.getElementsByTagName('img'); " +
                                        "for(var i=0;i<objs.length;i++)  " +
                                        "{"
                                        + "var img = objs[i];   " +
                                        "    img.style.maxWidth = '100%'; img.style.height = 'auto';  " +
                                        "}"+ "};modifyTextColor();");
                            }
                        });
                        wb.loadDataWithBaseURL(null,  obj.getContent(), "text/html", "utf-8", null);
                    } else {
                        WonderfulToastUtils.showToast(object.optString("message"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();

                }
            }
        });
    }


    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        if (!isSetTitle) {
            isSetTitle = true;
            ImmersionBar.setTitleBar(this, llTitle);
        }
    }


}
