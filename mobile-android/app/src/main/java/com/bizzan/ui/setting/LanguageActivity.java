package com.bizzan.ui.setting;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bizzan.R;
import com.bizzan.base.BaseActivity;
import com.bizzan.ui.home.MainActivity;
import com.bizzan.utils.SharedPreferenceInstance;
import com.gyf.barlibrary.ImmersionBar;

import butterknife.BindView;

public class LanguageActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.llTitle)
    LinearLayout llTitle;
    @BindView(R.id.imageZh)
    ImageView imageZh;
    @BindView(R.id.imageEh)
    ImageView imageEh;
    @BindView(R.id.imageJp)
    ImageView imageJp;
    @BindView(R.id.imageDe)
    ImageView imageDe;
    @BindView(R.id.imageKr)
    ImageView imageKr;
    @BindView(R.id.imageFr)
    ImageView imageFr;
    @BindView(R.id.imageHk)
    ImageView imageHk;
    @BindView(R.id.imageIt)
    ImageView imageIt;
    @BindView(R.id.imageEs)
    ImageView imageEs;
    @BindView(R.id.imagePt)
    ImageView imagePt;
    @BindView(R.id.imageTk)
    ImageView imageTk;
    @BindView(R.id.imageId)
    ImageView imageId;

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, LanguageActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected int getActivityLayoutId() {
        return R.layout.activity_language;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        findViewById(R.id.btn_en).setOnClickListener(this);
        findViewById(R.id.btn_zh).setOnClickListener(this);
        findViewById(R.id.btn_jp).setOnClickListener(this);
        findViewById(R.id.btn_kr).setOnClickListener(this);
        findViewById(R.id.btn_de).setOnClickListener(this);
        findViewById(R.id.btn_fr).setOnClickListener(this);
        findViewById(R.id.btn_hk).setOnClickListener(this);
        findViewById(R.id.btn_It).setOnClickListener(this);
        findViewById(R.id.btn_es).setOnClickListener(this);
        findViewById(R.id.btn_Id).setOnClickListener(this);
        findViewById(R.id.btn_pt).setOnClickListener(this);
        findViewById(R.id.btn_tk).setOnClickListener(this);
        findViewById(R.id.ibBack).setOnClickListener(view -> finish());
    }

    @Override
    protected void obtainData() {

    }

    @Override
    protected void fillWidget() {
        String language = BaseActivity.PreferenceUtil.getString("language", "en");
        reset(language);
    }

    @Override
    protected void loadData() {

    }

    protected void switchLanguage(String language) {
        BaseActivity.switchLanguage(this, language);
        reset(language);
    }

    void reset(String language) {
        imageZh.setVisibility(View.GONE);
        imageEh.setVisibility(View.GONE);
        imageJp.setVisibility(View.GONE);
        imageKr.setVisibility(View.GONE);
        imageDe.setVisibility(View.GONE);
        imageFr.setVisibility(View.GONE);
        imageHk.setVisibility(View.GONE);
        imageIt.setVisibility(View.GONE);
        imageEs.setVisibility(View.GONE);
        imagePt.setVisibility(View.GONE);
        imageTk.setVisibility(View.GONE);
        imageId.setVisibility(View.GONE);
        switch (language) {
            case "en":
                imageEh.setVisibility(View.VISIBLE);
                break;
            case "ch":
                imageZh.setVisibility(View.VISIBLE);
                break;
            case "jp":
                imageJp.setVisibility(View.VISIBLE);
                break;
            case "kr":
                imageKr.setVisibility(View.VISIBLE);
                break;
            case "de":
                imageDe.setVisibility(View.VISIBLE);
                break;
            case "fr":
                imageFr.setVisibility(View.VISIBLE);
                break;
            case "hk":
                imageHk.setVisibility(View.VISIBLE);
                break;
            case "it":
                imageIt.setVisibility(View.VISIBLE);
                break;
            case "es":
                imageEs.setVisibility(View.VISIBLE);
                break;
            case "pt":
                imagePt.setVisibility(View.VISIBLE);
                break;
            case "tk":
                imageTk.setVisibility(View.VISIBLE);
                break;
            case "id":
                imageId.setVisibility(View.VISIBLE);
                break;
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_zh:
                switchLanguage("ch");
                SharedPreferenceInstance.getInstance().saveLanguageCode(1);
                break;
            case R.id.btn_en:
                switchLanguage("en");
                SharedPreferenceInstance.getInstance().saveLanguageCode(2);
                break;
            case R.id.btn_jp:
                switchLanguage("jp");
                SharedPreferenceInstance.getInstance().saveLanguageCode(3);
                break;
            case R.id.btn_kr:
                switchLanguage("kr");
                SharedPreferenceInstance.getInstance().saveLanguageCode(4);
                break;
            case R.id.btn_de:
                switchLanguage("de");
                SharedPreferenceInstance.getInstance().saveLanguageCode(5);
                break;
            case R.id.btn_fr:
                switchLanguage("fr");
                SharedPreferenceInstance.getInstance().saveLanguageCode(6);
                break;
            case R.id.btn_hk:
                switchLanguage("hk");
                SharedPreferenceInstance.getInstance().saveLanguageCode(-1);
                break;
            case R.id.btn_It:
                switchLanguage("it");
                SharedPreferenceInstance.getInstance().saveLanguageCode(7);
                break;
            case R.id.btn_es:
                switchLanguage("es");
                SharedPreferenceInstance.getInstance().saveLanguageCode(8);
                break;
            case R.id.btn_pt:
                switchLanguage("pt");
                SharedPreferenceInstance.getInstance().saveLanguageCode(9);
                break;
            case R.id.btn_tk:
                switchLanguage("tk");
                SharedPreferenceInstance.getInstance().saveLanguageCode(10);
                break;
            case R.id.btn_Id:
                switchLanguage("id");
                SharedPreferenceInstance.getInstance().saveLanguageCode(11);
                break;
        }
        //更新语言后，destroy当前页面，重新绘制
        finish();
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        this.startActivity(intent);
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
