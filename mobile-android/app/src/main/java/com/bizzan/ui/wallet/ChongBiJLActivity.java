package com.bizzan.ui.wallet;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.google.gson.Gson;
import com.gyf.barlibrary.ImmersionBar;
import com.bizzan.R;
import com.bizzan.ui.entrust.DropdownLayout;
import com.bizzan.adapter.ChongBiAdapter;
import com.bizzan.adapter.ShaiXuanAdapter;
import com.bizzan.base.BaseActivity;
import com.bizzan.base.LinListView;
import com.bizzan.entity.ChongBiBean;
import com.bizzan.app.UrlFactory;
import com.bizzan.utils.SharedPreferenceInstance;
import com.bizzan.utils.WonderfulLogUtils;
import com.bizzan.utils.okhttp.StringCallback;
import com.bizzan.utils.okhttp.WonderfulOkhttpUtils;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import okhttp3.Request;

/**
 * Created by Administrator on 2018/8/8.
 */
public class ChongBiJLActivity extends BaseActivity {
    @BindView(R.id.llTitle)
    LinearLayout llTitle;
    @BindView(R.id.ibBack)
    ImageButton ibBack;
    @BindView(R.id.ibCalendar)
    ImageButton ibCalendar;
    @BindView(R.id.dropdownLayout)
    DropdownLayout dropdownLayout;
    @BindView(R.id.listview)
    ListView listview;
    @BindView(R.id.listview_1)
    LinListView listview_1;
    @BindView(R.id.tvMessage)
    LinearLayout tvMessage;
    @BindView(R.id.view_xianshi)
    View view_xianshi;

    private List<String> lists=new ArrayList<>();
    private List<ChongBiBean.ContentBean> beans=new ArrayList<>();
    private int page=1;
    private String bizhong="";

    private ChongBiAdapter adapter;

    @Override
    protected int getActivityLayoutId() {
        return R.layout.activity_chongbijilu;
    }


    public static void actionStart(Context context,List<String> list) {
        Intent intent = new Intent(context, ChongBiJLActivity.class);
        intent.putStringArrayListExtra("list", (ArrayList<String>) list);
        context.startActivity(intent);
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
    protected void initViews(Bundle savedInstanceState) {
        view_xianshi.setVisibility(View.GONE);
        ArrayList<String> list = getIntent().getStringArrayListExtra("list");
        lists=list;
        for (int i=0;i<lists.size();i++){
            WonderfulLogUtils.logi("miao",lists.get(i));
        }
        ibBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        view_xianshi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dropdownLayout.hide();
                Drawable drawable = getResources().getDrawable(R.drawable.icon_filter_no);
                ibCalendar.setBackgroundDrawable(drawable);
                listview.setVisibility(View.GONE);
                view_xianshi.setVisibility(View.GONE);
            }
        });
        ibCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ( dropdownLayout.isOpen()) {
                    dropdownLayout.hide();
                    Drawable drawable = getResources().getDrawable(R.drawable.icon_filter_no);
                    ibCalendar.setBackgroundDrawable(drawable);
                    listview.setVisibility(View.GONE);
                    view_xianshi.setVisibility(View.GONE);
                }else {
                    dropdownLayout.show();
                    Drawable drawable = getResources().getDrawable(R.drawable.icon_filter_orange);
                    ibCalendar.setBackgroundDrawable(drawable);
                    listview.setVisibility(View.VISIBLE);
                    view_xianshi.setVisibility(View.VISIBLE);
                }

            }
        });
        listview.setAdapter(new ShaiXuanAdapter(ChongBiJLActivity.this,lists));
      listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
          @Override
          public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
              String s = lists.get(position);
              bizhong=s;
              dropdownLayout.hide();
              Drawable drawable = getResources().getDrawable(R.drawable.icon_filter_no);
              ibCalendar.setBackgroundDrawable(drawable);
              listview.setVisibility(View.GONE);
              view_xianshi.setVisibility(View.GONE);
              page=1;
              qingQiu(s);
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
        qingQiu("");
    }

    private void qingQiu(String symbol){
        int id1 = SharedPreferenceInstance.getInstance().getID();
        WonderfulLogUtils.logi("miao",id1+"id");
        WonderfulOkhttpUtils.post().url(UrlFactory.getCha())
                .addHeader("x-auth-token", SharedPreferenceInstance.getInstance().getTOKEN())
                .addParams("memberId",id1+"")
                .addParams("pageNo",page+"")
                .addParams("pageSize","40")
                .addParams("type","0")
                .addParams("symbol",""+symbol)
                .build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {
                super.onError(request,e);
                hideLoadingPopup();
                listview_1.stopFreshing();
                listview_1.setVisibility(View.GONE);
                tvMessage.setVisibility(View.VISIBLE);
            }
            @Override
            public void onResponse(String response) {
                WonderfulLogUtils.logi("miao","充币记录："+response);
                if (page==1){
                    beans.clear();
                }
                if (listview_1==null){
                    return;
                }
                listview_1.stopFreshing();
                try {
                    JSONObject jsonObject=new JSONObject(response);
                    Gson gson=new Gson();
                    ChongBiBean chongBiBean = gson.fromJson(jsonObject.optString("data"),ChongBiBean.class);
                    List<ChongBiBean.ContentBean> contentBeanList = chongBiBean.getContent();
                    if (contentBeanList.size()==0&&page==1){
                        listview_1.setVisibility(View.GONE);
                        tvMessage.setVisibility(View.VISIBLE);
                        return;
                    }
                    beans.addAll(contentBeanList);
                    tvMessage.setVisibility(View.GONE);
                    listview_1.setVisibility(View.VISIBLE);
                    adapter=new ChongBiAdapter(ChongBiJLActivity.this,beans);
                    listview_1.setEveryPageItemCount(40);
                    listview_1.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                    listview_1.setOnRefreshListener(new LinListView.OnRefreshListener() {
                        @Override
                        public void onLoadMore() {
                            page=page+1;
                            qingQiu(bizhong);
                        }

                        @Override
                        public void onRefresh() {
                            page=1;
                            beans.clear();
                            qingQiu(bizhong);
                        }
                    });

                } catch (Exception e) {
                    listview_1.setVisibility(View.GONE);
                    tvMessage.setVisibility(View.VISIBLE);
                    e.printStackTrace();
                }

            }
        });
    }
}
