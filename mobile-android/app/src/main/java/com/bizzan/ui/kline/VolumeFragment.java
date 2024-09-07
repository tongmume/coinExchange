package com.bizzan.ui.kline;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.gson.Gson;

import com.google.gson.reflect.TypeToken;

import com.bizzan.R;

import com.bizzan.adapter.VolumeAdapter;
import com.bizzan.base.BaseFragment;

import com.bizzan.entity.VolumeInfo;
import com.bizzan.app.UrlFactory;
import com.bizzan.socket.ISocket;
import com.bizzan.serivce.SocketMessage;
import com.bizzan.serivce.SocketResponse;
import com.bizzan.utils.WonderfulLogUtils;
import com.bizzan.utils.okhttp.StringCallback;
import com.bizzan.utils.okhttp.WonderfulOkhttpUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONObject;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import okhttp3.Request;

/**
 * kline_成交
 * Created by daiyy on 2018/6/4 0004.
 */

public class VolumeFragment extends BaseFragment {
    @BindView(R.id.tvNumberV)
    TextView tvNumberV;
    @BindView(R.id.tvPriceV)
    TextView tvPriceV;
    @BindView(R.id.rvVolume)
    RecyclerView recyclerView;
    @BindView(R.id.volumeBar)
    ProgressBar volumeBar;
    private Unbinder unbinder;
    private String symbol;
    private ArrayList<VolumeInfo> objList;
    private VolumeAdapter adapter;
    private Activity activity;
    private Gson gson;
    private String type_constract;


    public static VolumeFragment getInstance(String symbol, String type_constract) {
        VolumeFragment volumeFragment = new VolumeFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("symbol", symbol);
        bundle.putString("type_constract", type_constract);
        volumeFragment.setArguments(bundle);
        return volumeFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        // 取消订阅
        if (type_constract.equals("1")) {
            EventBus.getDefault().post(new SocketMessage(0, ISocket.CMD.UNSUBSCRIBE_EXCHANGE_TRADE, buildGetBodyJson(symbol).toString().getBytes()));
        } else if (type_constract.equals("2")) {
            EventBus.getDefault().post(new SocketMessage(1, ISocket.CMD.UNSUBSCRIBE_EXCHANGE_TRADE_CONTRACT, buildGetBodyJson(symbol).toString().getBytes()));
        }
        EventBus.getDefault().unregister(this);
    }

    private void startTCP() {
        // 开始订阅
        if (type_constract.equals("1")) {
            EventBus.getDefault().post(new SocketMessage(0, ISocket.CMD.SUBSCRIBE_EXCHANGE_TRADE, buildGetBodyJson(symbol).toString().getBytes()));
        } else if (type_constract.equals("2")) {
            EventBus.getDefault().post(new SocketMessage(1, ISocket.CMD.SUBSCRIBE_EXCHANGE_TRADE_CONTRACT, buildGetBodyJson(symbol).toString().getBytes()));
        }

    }

    private JSONObject buildGetBodyJson(String symbol) {
        JSONObject obj = new JSONObject();
        try {
            obj.put("symbol", symbol);
            return obj;
        } catch (Exception ex) {
            return null;
        }
    }

    /**
     * socket 推送过来的信息
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onSocketMessage(SocketResponse response) {
        if (type_constract.equals("1")) {
            if (response.getCmd() == ISocket.CMD.PUSH_EXCHANGE_TRADE2) {
                try {
                    WonderfulLogUtils.logi("tag", "成交 推送过来的信息==" + response.getResponse());
                    if (gson == null)
                        gson = new Gson();
                    VolumeInfo volumeInfo = gson.fromJson(response.getResponse(), new TypeToken<VolumeInfo>() {
                    }.getType());

                    String strSymbol = volumeInfo.getSymbol();
                    if (strSymbol != null && strSymbol.equals(symbol)) {
                        objList.add(0, volumeInfo);
                        objList = initData(objList);
                        adapter.setObjList(objList);
                        adapter.notifyDataSetChanged();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else if (type_constract.equals("2")) {
            if (response.getCmd() == ISocket.CMD.CONTRACT_PUSH_EXCHANGE_TRADE) {
                try {
                    WonderfulLogUtils.logi("tag", "成交 推送过来的信息==" + response.getResponse());
                    if (gson == null)
                        gson = new Gson();
                    VolumeInfo volumeInfo = gson.fromJson(response.getResponse(), new TypeToken<VolumeInfo>() {
                    }.getType());

                    String strSymbol = volumeInfo.getSymbol();
                    if (strSymbol != null && strSymbol.equals(symbol)) {
                        objList.add(0, volumeInfo);
                        objList = initData(objList);
                        adapter.setObjList(objList);
                        adapter.notifyDataSetChanged();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_volume;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        intLv();
        recyclerView.setNestedScrollingEnabled(false);
    }

    /**
     * 初始化列表数据
     */
    private void intLv() {
        activity = getActivity();
        objList = new ArrayList<>();
        LinearLayoutManager manager = new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false);
        manager.setStackFromEnd(false);
        recyclerView.setLayoutManager(manager);
    }


    @Override
    protected void obtainData() {
        activity = getActivity();
        Bundle bundle = getArguments();
        if (bundle != null) {
            symbol = bundle.getString("symbol");
            type_constract = bundle.getString("type_constract");
            String[] strings = symbol.split("/");
            tvNumberV.setText(getResources().getString(R.string.number) + "(" + strings[0] + ")");
            tvPriceV.setText(getResources().getString(R.string.price) + "(" + strings[1] + ")");
            startTCP();
            if (type_constract.equals("1")) {
                getVolume();
            } else if (type_constract.equals("2")) {
                getVolume_constract();
            }

        }
    }

    @Override
    protected void fillWidget() {

    }

    @Override
    protected void loadData() {

    }

    /**
     * 获取成交数据
     */
    private void getVolume() {
        if (volumeBar != null)
            volumeBar.setVisibility(View.VISIBLE);
        WonderfulOkhttpUtils.post().url(UrlFactory.getVolume()).addParams("symbol", symbol).addParams("size", "20").build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Request request, Exception e) {

                    }

                    @Override
                    public void onResponse(String response) {
                        WonderfulLogUtils.logi("tag123", "成交 返回数据==" + response);
                        doLogicData(response);
                    }
                });
    }

    private void getVolume_constract() {
        if (volumeBar != null)
            volumeBar.setVisibility(View.VISIBLE);
        WonderfulOkhttpUtils.post().url(UrlFactory.getVolume_Constract())
                .addParams("symbol", symbol)
                .addParams("size", "20").build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Request request, Exception e) {

                    }

                    @Override
                    public void onResponse(String response) {
                        WonderfulLogUtils.logi("tag123", "成交 返回数据==" + response);
                        doLogicData(response);
                    }
                });
    }

    /**
     * 解析数据
     *
     * @param response
     */
    private void doLogicData(String response) {
        if (volumeBar != null)
            volumeBar.setVisibility(View.GONE);
        try {
            gson = new Gson();
            ArrayList<VolumeInfo> volumeInfos = new Gson().fromJson(response, new TypeToken<ArrayList<VolumeInfo>>() {
            }.getType());
            objList = initData(volumeInfos);
            if (adapter == null) {
                adapter = new VolumeAdapter(activity, objList);
                recyclerView.setAdapter(adapter);
            }
        } catch (Exception e) {
            e.printStackTrace();
            /*if (isAdded())
                WonderfulToastUtils.showToast(getResources().getString(R.string.parse_error));*/
        }
    }

    /**
     * 填充数据
     */
    private ArrayList<VolumeInfo> initData(ArrayList<VolumeInfo> objList) {
        ArrayList<VolumeInfo> volumeInfos = new ArrayList<>();
        if (objList != null) {
            for (int i = 0; i < 20; i++) {
                if (objList.size() > i) { // list里有数据
                    volumeInfos.add(objList.get(i));
                } else {
                    VolumeInfo volumeInfo = new VolumeInfo();
                    volumeInfo.setTime(-1);
                    volumeInfo.setDirection(null);
                    volumeInfo.setPrice(-1);
                    volumeInfo.setAmount(-1);
                    volumeInfos.add(volumeInfo);
                }
            }
        } else {
            for (int i = 0; i < 20; i++) {
                VolumeInfo volumeInfo = new VolumeInfo();
                volumeInfo.setTime(-1);
                volumeInfo.setDirection(null);
                volumeInfo.setPrice(-1);
                volumeInfo.setAmount(-1);
                volumeInfos.add(volumeInfo);
            }
        }
        return volumeInfos;
    }

}
