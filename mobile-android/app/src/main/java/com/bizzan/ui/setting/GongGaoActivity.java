package com.bizzan.ui.setting;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.gyf.barlibrary.ImmersionBar;
import com.bizzan.R;
import com.bizzan.ui.message_detail.MessageDetailActivity;
import com.bizzan.adapter.GongGaoAdapter;
import com.bizzan.base.BaseActivity;
import com.bizzan.base.LinListView;
import com.bizzan.entity.Message;
import com.bizzan.app.UrlFactory;
import com.bizzan.utils.WonderfulLogUtils;
import com.bizzan.utils.okhttp.StringCallback;
import com.bizzan.utils.okhttp.WonderfulOkhttpUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import okhttp3.Request;

/**
 * Created by Administrator on 2018/8/9.
 */
public class GongGaoActivity extends BaseActivity {
    @BindView(R.id.llTitle)
    LinearLayout llTitle;
    @BindView(R.id.ibBack)
    ImageButton ibBack;
    @BindView(R.id.listview_1)
    LinListView listview_1;
    @BindView(R.id.view_back)
    View view_back;

    private GongGaoAdapter adapter;
    @Override
    protected int getActivityLayoutId() {
        return R.layout.activity_gonggao;
    }
    public static void actionStart(Context context) {
        Intent intent = new Intent(context, GongGaoActivity.class);
        context.startActivity(intent);
    }
    @Override
    protected void initViews(Bundle savedInstanceState) {
        ibBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        listview_1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MessageDetailActivity.actionStart(GongGaoActivity.this, messageList.get(position).getId());
            }
        });
        view_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    @Override
    protected void obtainData() {

    }

    @Override
    protected void fillWidget() {

    }

    @Override
    protected void loadData() {
        displayLoadingPopup();
        getMessage();
    }

    private void getMessage() {
        WonderfulOkhttpUtils.post().url(UrlFactory.getMessageUrl())
                .addParams("pageNo", 1 + "").addParams("pageSize", "100")
                .build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {
                hideLoadingPopup();
            }

            @Override
            public void onResponse(String response) {
                WonderfulLogUtils.logi("miao",response);
                hideLoadingPopup();
                try {
                    JSONObject object = new JSONObject(response);
                    if (object.optInt("code") == 0) {
                        List<Message> messages = new Gson().fromJson(object.getJSONObject("data").getJSONArray("content").toString(), new TypeToken<List<Message>>() {
                        }.getType());
                        messageList.clear();
                        messageList.addAll(messages);
                        adapter=new GongGaoAdapter(GongGaoActivity.this,messageList);
                        listview_1.setEveryPageItemCount(100);
                        listview_1.setAdapter(adapter);
                        adapter.notifyDataSetChanged();
                        listview_1.setOnRefreshListener(new LinListView.OnRefreshListener() {
                            @Override
                            public void onLoadMore() {
                                listview_1.setNotMore();
                            }

                            @Override
                            public void onRefresh() {
                                listview_1.stopFreshing();
                                getMessage();
                            }
                        });
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private List<Message> messageList = new ArrayList<>();
    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        if (!isSetTitle) {
            ImmersionBar.setTitleBar(this, llTitle);
            isSetTitle = true;
        }
    }
}
