package com.bizzan.ui.aboutus;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gyf.barlibrary.ImmersionBar;
import com.bizzan.R;

import butterknife.BindView;
import butterknife.OnClick;
import com.bizzan.base.BaseActivity;

public class AddUrlActivity extends BaseActivity {

    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.llTitle)
    LinearLayout llTitle;
    @BindView(R.id.wb)
    WebView wb;
    private String url;
    public static void show(Activity activity,String title,String url){
        Intent intent = new Intent(activity,AddUrlActivity.class);

            intent.putExtra("title",title);


        intent.putExtra("url",url);
//        Log.i("miao","url"+url);
        activity.startActivity(intent);
    }

    @Override
    protected int getActivityLayoutId() {
        return R.layout.activity_add_url;
    }
    private void initWb() {
        wb.getSettings().setJavaScriptEnabled(true);
        wb.getSettings().setSupportZoom(false);
        wb.getSettings().setBuiltInZoomControls(false);
//        wb.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        wb.getSettings().setDefaultFontSize(14);
        wb.setWebViewClient(new WebViewClient());
    }
    @Override
    protected void initViews(Bundle savedInstanceState) {
        title.setText(getIntent().getStringExtra("title"));
        url = getIntent().getStringExtra("url");
        initWb();
//        Log.d("jiejie",url + "--" + title);
        if(!TextUtils.isEmpty(url)){
            if(url.contains("http")){
                wb.loadUrl(url);
            }else {
                wb.loadDataWithBaseURL(null,url, "text/html", "utf-8", null);
            }
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

    @OnClick(R.id.ibBack)
    public void onViewClicked() {
        finish();
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
