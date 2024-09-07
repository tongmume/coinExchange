package com.bizzan.ui.setting;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bizzan.utils.LogUtils;
import com.bizzan.utils.SharedPreferenceInstance;
import com.gyf.barlibrary.ImmersionBar;
import com.bizzan.R;
import com.bizzan.ui.message_detail.MessageHelpActivity;
import com.bizzan.adapter.GongGaoAdapter;
import com.bizzan.base.BaseActivity;
import com.bizzan.base.MyListView;
import com.bizzan.entity.Message;
import com.bizzan.app.UrlFactory;
import com.bizzan.utils.WonderfulLogUtils;
import com.bizzan.utils.okhttp.StringCallback;
import com.bizzan.utils.okhttp.WonderfulOkhttpUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import okhttp3.Request;

/**
 * Created by Administrator on 2018/8/9.
 */
public class HelpActivity extends BaseActivity {
    @BindView(R.id.llTitle)
    LinearLayout llTitle;
    private GongGaoAdapter adapter;
    private GongGaoAdapter adapter2;
    private GongGaoAdapter adapter3;
    private GongGaoAdapter adapter4;
    private GongGaoAdapter adapter5;
    private GongGaoAdapter adapter6;
    private GongGaoAdapter adapter7;
    @BindView(R.id.listview_xinshou)
    MyListView listview_xinshou;
    @BindView(R.id.ibBack)
    ImageButton ibBack;
    @BindView(R.id.listview_changjian)
    MyListView listview_changjian;
    @BindView(R.id.listview_jiaoyi)
    MyListView listview_jiaoyi;
    @BindView(R.id.listview_bizhong)
    MyListView listview_bizhong;
    @BindView(R.id.listview_hangqing)
    MyListView listview_hangqing;

    @BindView(R.id.listview_tiaokuan)
    MyListView listview_tiaokuan;
    @BindView(R.id.listview_qita)
    MyListView listview_qita;

    @BindView(R.id.text_gengduo1)
    TextView text_gengduo1;

    @BindView(R.id.text_gengduo2)
    TextView text_gengduo2;

    @BindView(R.id.text_gengduo3)
    TextView text_gengduo3;

    @BindView(R.id.text_gengduo4)
    TextView text_gengduo4;

    @BindView(R.id.text_gengduo5)
    TextView text_gengduo5;

    @BindView(R.id.text_gengduo6)
    TextView text_gengduo6;

    @BindView(R.id.text_gengduo7)
    TextView text_gengduo7;

    @BindView(R.id.view_back)
    View view_back;

    @Override
    protected int getActivityLayoutId() {
        return R.layout.activity_help;
    }

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, HelpActivity.class);
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
        view_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        listview_xinshou.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MessageHelpActivity.actionStart(HelpActivity.this, messageList.get(position).getId());
            }
        });
        listview_changjian.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MessageHelpActivity.actionStart(HelpActivity.this, messageList_chang.get(position).getId());
            }
        });
        listview_jiaoyi.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MessageHelpActivity.actionStart(HelpActivity.this, messageList_jiaoyi.get(position).getId());
            }
        });
        listview_bizhong.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MessageHelpActivity.actionStart(HelpActivity.this, messageList_bizhong.get(position).getId());
            }
        });
        listview_hangqing.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MessageHelpActivity.actionStart(HelpActivity.this, messageList_hangqing.get(position).getId());
            }
        });
        listview_tiaokuan.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MessageHelpActivity.actionStart(HelpActivity.this, messageList_tiaokuan.get(position).getId());
            }
        });
        listview_qita.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MessageHelpActivity.actionStart(HelpActivity.this, messageList_qita.get(position).getId());
            }
        });

        text_gengduo1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (messageList.size() == 0) {
                    return;
                }
                HelpXinShouActivity.actionStart(HelpActivity.this);
            }
        });
        text_gengduo2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (messageList_chang.size() == 0) {
                    return;
                }
                HelpChangjianActivity.actionStart(HelpActivity.this);
            }
        });
        text_gengduo3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (messageList_jiaoyi.size() == 0) {
                    return;
                }
                HelpJiaoyiActivity.actionStart(HelpActivity.this);
            }
        });
        text_gengduo4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (messageList_bizhong.size() == 0) {
                    return;
                }
                HelpBizhongActivity.actionStart(HelpActivity.this);
            }
        });
        text_gengduo5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (messageList_hangqing.size() == 0) {
                    return;
                }
                HelpHangqingActivity.actionStart(HelpActivity.this);
            }
        });
        text_gengduo6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (messageList_tiaokuan.size() == 0) {
                    return;
                }
                HelpTiaokuanActivity.actionStart(HelpActivity.this);
            }
        });
        text_gengduo7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (messageList_qita.size() == 0) {
                    return;
                }
                HelpQitaActivity.actionStart(HelpActivity.this);
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
        String lang = SharedPreferenceInstance.getInstance().getLanguageRegion();
        WonderfulOkhttpUtils.post().url(UrlFactory.getHelp())
                .addParams("total", "6")
                .addParams("lang", lang)
                .build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {
                hideLoadingPopup();
            }

            @Override
            public void onResponse(String response) {
                Log.e("chenxi", "json:" + response);
                WonderfulLogUtils.logi("miao", response);
                try {
                    JSONObject object = new JSONObject(response);
                    JSONArray jsonArray = object.optJSONArray("data");
                    if (jsonArray != null) {
                        if (jsonArray.length() > 0) {
                            // 新手指南
                            JSONObject jsonObject = jsonArray.optJSONObject(0);
                            JSONArray jsonArray1 = jsonObject.optJSONArray("content");
                            if (jsonArray1.length() > 0) {
                                messageList.clear();
                                for (int i = 0; i < jsonArray1.length(); i++) {
                                    if (i == 3) {
                                        break;
                                    }
                                    JSONObject jsonObject1 = jsonArray1.optJSONObject(i);
                                    Message bean = new Message();
                                    bean.setContent(jsonObject1.optString("content"));
                                    bean.setCreateTime(jsonObject1.optString("createTime"));
                                    bean.setIsTop(jsonObject1.optString("isTop"));
                                    bean.setId(jsonObject1.optString("id"));
                                    bean.setTitle(jsonObject1.optString("title"));
                                    messageList.add(bean);
                                }

                                adapter = new GongGaoAdapter(HelpActivity.this, messageList);
                                listview_xinshou.setAdapter(adapter);
                                adapter.notifyDataSetChanged();
                            }

                            // 常见问题
                            JSONObject jsonObject2 = jsonArray.optJSONObject(1);
                            JSONArray jsonArray2 = jsonObject2.optJSONArray("content");
                            if (jsonArray2.length() > 0) {
                                messageList_chang.clear();
                                for (int i = 0; i < jsonArray2.length(); i++) {
                                    if (i == 3) {
                                        break;
                                    }
                                    JSONObject jsonObject1 = jsonArray2.optJSONObject(i);
                                    Message bean = new Message();
                                    bean.setContent(jsonObject1.optString("content"));
                                    bean.setCreateTime(jsonObject1.optString("createTime"));
                                    bean.setIsTop(jsonObject1.optString("isTop"));
                                    bean.setId(jsonObject1.optString("id"));
                                    bean.setTitle(jsonObject1.optString("title"));
                                    messageList_chang.add(bean);
                                }
                                adapter2 = new GongGaoAdapter(HelpActivity.this, messageList_chang);
                                listview_changjian.setAdapter(adapter2);
                                adapter2.notifyDataSetChanged();
                            }

                            // 交易指南
                            JSONObject jsonObject3 = jsonArray.optJSONObject(2);
                            JSONArray jsonArray3 = jsonObject3.optJSONArray("content");
                            if (jsonArray3.length() > 0) {
                                messageList_jiaoyi.clear();
                                for (int i = 0; i < jsonArray3.length(); i++) {
                                    if (i == 3) {
                                        break;
                                    }
                                    JSONObject jsonObject1 = jsonArray3.optJSONObject(i);
                                    Message bean = new Message();
                                    bean.setContent(jsonObject1.optString("content"));
                                    bean.setCreateTime(jsonObject1.optString("createTime"));
                                    bean.setIsTop(jsonObject1.optString("isTop"));
                                    bean.setId(jsonObject1.optString("id"));
                                    bean.setTitle(jsonObject1.optString("title"));
                                    messageList_jiaoyi.add(bean);
                                }
                                adapter3 = new GongGaoAdapter(HelpActivity.this, messageList_jiaoyi);
                                listview_jiaoyi.setAdapter(adapter3);
                                adapter3.notifyDataSetChanged();
                            }

                            // 币种资料
                            JSONObject jsonObject4 = jsonArray.optJSONObject(3);
                            JSONArray jsonArray4 = jsonObject4.optJSONArray("content");
                            if (jsonArray4.length() > 0) {
                                messageList_bizhong.clear();
                                for (int i = 0; i < jsonArray4.length(); i++) {
                                    if (i == 3) {
                                        break;
                                    }
                                    JSONObject jsonObject1 = jsonArray4.optJSONObject(i);
                                    Message bean = new Message();
                                    bean.setContent(jsonObject1.optString("content"));
                                    bean.setCreateTime(jsonObject1.optString("createTime"));
                                    bean.setIsTop(jsonObject1.optString("isTop"));
                                    bean.setId(jsonObject1.optString("id"));
                                    bean.setTitle(jsonObject1.optString("title"));
                                    messageList_bizhong.add(bean);
                                }
                                adapter4 = new GongGaoAdapter(HelpActivity.this, messageList_bizhong);
                                listview_bizhong.setAdapter(adapter4);
                                adapter4.notifyDataSetChanged();
                            }

                            // 行情技术
                            JSONObject jsonObject5 = jsonArray.optJSONObject(4);
                            JSONArray jsonArray5 = jsonObject5.optJSONArray("content");
                            if (jsonArray5.length() > 0) {
                                messageList_hangqing.clear();
                                for (int i = 0; i < jsonArray5.length(); i++) {
                                    if (i == 3) {
                                        break;
                                    }
                                    JSONObject jsonObject1 = jsonArray5.optJSONObject(i);
                                    Message bean = new Message();
                                    bean.setContent(jsonObject1.optString("content"));
                                    bean.setCreateTime(jsonObject1.optString("createTime"));
                                    bean.setIsTop(jsonObject1.optString("isTop"));
                                    bean.setId(jsonObject1.optString("id"));
                                    bean.setTitle(jsonObject1.optString("title"));
                                    messageList_hangqing.add(bean);
                                }
                                adapter5 = new GongGaoAdapter(HelpActivity.this, messageList_hangqing);
                                listview_hangqing.setAdapter(adapter5);
                                adapter5.notifyDataSetChanged();
                            }

                            // 条款协议
                            JSONObject jsonObject6 = jsonArray.optJSONObject(5);
                            JSONArray jsonArray6 = jsonObject6.optJSONArray("content");
                            if (jsonArray6.length() > 0) {
                                messageList_tiaokuan.clear();
                                for (int i = 0; i < jsonArray6.length(); i++) {
                                    if (i == 3) {
                                        break;
                                    }
                                    JSONObject jsonObject1 = jsonArray6.optJSONObject(i);
                                    Message bean = new Message();
                                    bean.setContent(jsonObject1.optString("content"));
                                    bean.setCreateTime(jsonObject1.optString("createTime"));
                                    bean.setIsTop(jsonObject1.optString("isTop"));
                                    bean.setId(jsonObject1.optString("id"));
                                    bean.setTitle(jsonObject1.optString("title"));
                                    messageList_tiaokuan.add(bean);
                                }
                                adapter6 = new GongGaoAdapter(HelpActivity.this, messageList_tiaokuan);
                                listview_tiaokuan.setAdapter(adapter6);
                                adapter6.notifyDataSetChanged();
                            }

                            // 其他
                            JSONObject jsonObject7 = jsonArray.optJSONObject(6);
                            JSONArray jsonArray7 = jsonObject7.optJSONArray("content");
                            if (jsonArray7.length() > 0) {
                                messageList_qita.clear();
                                for (int i = 0; i < jsonArray7.length(); i++) {
                                    if (i == 3) {
                                        break;
                                    }
                                    JSONObject jsonObject1 = jsonArray7.optJSONObject(i);
                                    Message bean = new Message();
                                    bean.setContent(jsonObject1.optString("content"));
                                    bean.setCreateTime(jsonObject1.optString("createTime"));
                                    bean.setIsTop(jsonObject1.optString("isTop"));
                                    bean.setId(jsonObject1.optString("id"));
                                    bean.setTitle(jsonObject1.optString("title"));
                                    messageList_qita.add(bean);
                                }
                                adapter7 = new GongGaoAdapter(HelpActivity.this, messageList_qita);
                                listview_qita.setAdapter(adapter7);
                                adapter7.notifyDataSetChanged();
                            }

                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                } finally {
                    hideLoadingPopup();
                }
            }
        });
//        marqueeView.setOnItemClickListener(new MarqueeView.OnItemClickListener() {
//            @Override
//            public void onItemClick(int position, TextView textView) {
//                MessageDetailActivity.actionStart(getActivity(), messageList.get(infoss.get(position)).getId());
//            }
//        });
    }

    private List<Message> messageList = new ArrayList<>();
    private List<Message> messageList_chang = new ArrayList<>();
    private List<Message> messageList_jiaoyi = new ArrayList<>();
    private List<Message> messageList_bizhong = new ArrayList<>();
    private List<Message> messageList_hangqing = new ArrayList<>();
    private List<Message> messageList_tiaokuan = new ArrayList<>();
    private List<Message> messageList_qita = new ArrayList<>();

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        if (!isSetTitle) {
            ImmersionBar.setTitleBar(HelpActivity.this, llTitle);
            isSetTitle = true;
        }
    }
}
