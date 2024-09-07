package com.bizzan.ui.home.presenter;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import com.bizzan.ui.home.IThreeTextContract;
import com.bizzan.entity.EntrustHistory;
import com.bizzan.entity.Exchange;
import com.bizzan.entity.Money;
import com.bizzan.entity.ThreeTextInfo;
import com.bizzan.app.UrlFactory;
import com.bizzan.utils.WonderfulLogUtils;
import com.bizzan.utils.okhttp.StringCallback;
import com.bizzan.utils.okhttp.WonderfulOkhttpUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import okhttp3.Request;

import static com.bizzan.app.GlobalConstant.JSON_ERROR;
import static com.bizzan.app.GlobalConstant.OKHTTP_ERROR;

/**
 * author: wuzongjie
 * time  : 2018/4/17 0017 19:14
 * desc  :
 */

public class ThreeTextImpl implements IThreeTextContract.Presenter {
    IThreeTextContract.View view;

    public ThreeTextImpl(IThreeTextContract.View view) {
        this.view = view;
    }

    /**
     * 提交委托 卖出或是买入
     * 这里加个Dialog
     */
    @Override
    public void getAddOrder(String token, String symbol, String price, String amount,
                            String direction, String type,String useDiscount) {
        if (view != null) view.showDialog();
        WonderfulOkhttpUtils.post().url(UrlFactory.getExChangeUrl()).addHeader("x-auth-token", token).addParams("symbol", symbol)
                .addParams("price", price).addParams("amount", amount).addParams("direction", direction).addParams("type", type)
                .build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {
                super.onError(request, e);
                if (view != null) {
                    view.hideDialog();
                    view.errorMes(OKHTTP_ERROR, "提交失败");
                }
            }

            @Override
            public void onResponse(String response) {
                if (view == null) return;
                view.hideDialog();
                WonderfulLogUtils.logd("jiejie", "提交委托的" + response);
                try {
                    JSONObject object = new JSONObject(response);
                    if (object.optInt("code") == 0) {
                        view.getDataLoad(0, object.optString("message"));
                    } else {
                        view.errorMes(object.getInt("code"), object.optString("message"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    view.errorMes(JSON_ERROR, "提交失败");
                }
            }
        });
    }

    /**
     * 查询盘口的信息
     */
    @Override
    public void getExchange(final String symbol) {
        WonderfulOkhttpUtils.post().url(UrlFactory.getPlateUrl())
                .addParams("symbol", symbol).build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Request request, Exception e) {
                        super.onError(request, e);
                        //if (view != null) view.errorMes(OKHTTP_ERROR);
                    }

                    @Override
                    public void onResponse(String response) {
                        Log.d("miao", UrlFactory.getPlateUrl()+"查询盘信息 " + symbol + " --" + response);
                        if (view != null) {
                            try {
                                JsonObject object = new JsonParser().parse(response).getAsJsonObject();
                                JsonArray ask = object.getAsJsonArray("ask").getAsJsonArray();
                                List<Exchange> askList = new Gson().fromJson(ask, new TypeToken<List<Exchange>>() {
                                }.getType());
                                JsonArray bid = object.getAsJsonArray("bid").getAsJsonArray();
                                List<Exchange> bidList = new Gson().fromJson(bid, new TypeToken<List<Exchange>>() {
                                }.getType());
                                view.getSuccess(askList, bidList);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                        }
                    }
                });
    }

    /**
     * 获取当前委托
     *
     * @param token    token
     * @param pageNo   页码
     * @param pageSize 多少条
     * @param symbol   类型
     */
    @Override
    public void getCurrentOrder(String token, int pageNo, int pageSize, String symbol,String type,String direction,String startTime,String endTime) {
        WonderfulOkhttpUtils.post().url(UrlFactory.getEntrustUrl())
                .addHeader("x-auth-token", token)
                .addParams("pageNo", pageNo + "")
                .addParams("pageSize", pageSize + "")
                .addParams("type", type )
                .addParams("direction", direction )
                .addParams("startTime", startTime)
                .addParams("endTime", endTime)
                .addParams("symbol", symbol).build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {
                super.onError(request, e);
                if (view != null) view.errorMes(OKHTTP_ERROR, null);
            }

            @Override
            public void onResponse(String response) {
                Log.d("trust", "当前委托add" + response);
                int code=4000;
                if (view != null) {
                    try {
                        JSONObject object = new JSONObject(response);
                        code=object.optInt("code");
                        List<EntrustHistory> objs = gson.fromJson(object.getJSONArray("content").toString(), new TypeToken<List<EntrustHistory>>() {
                        }.getType());
                        view.getDataLoaded(objs);
                    } catch (JSONException e1) {

                        view.errorMes(code, null);
                    }
                    /*try {
                        object = new JSONObject(response);
                        int o = object.has("code");
                        if (o==500){
                            SharedPreferenceInstance.getInstance().saveIsNeedShowLock(false);
                            SharedPreferenceInstance.getInstance().saveLockPwd("");
                        }
                        //view.errorMes(object.getInt("code"), object.optString("message"));
                    } catch (JSONException e) {

                    }*/
                }
            }
        });
    }

    /**
     * 获取历史的订单
     */
    @Override
    public void getOrderHistory(String token, int pageNo, int pageSize, String symbol,String type,String direction,String startTime,String endTime) {
        WonderfulOkhttpUtils.post().url(UrlFactory.getHistoryEntrus())
                .addHeader("x-auth-token", token)
                .addParams("pageNo", pageNo + "")
                .addParams("pageSize", pageSize + "")
                .addParams("type", type )
                .addParams("direction", direction )
                .addParams("startTime", startTime)
                .addParams("endTime", endTime)
                .addParams("symbol", symbol).build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {
                if (view != null) view.errorMes(OKHTTP_ERROR, null);
            }

            @Override
            public void onResponse(String response) {
                if (view == null) return;
                Log.d("trust", "-历史的订单--" + response);
                try {
                    JsonObject object1 = new JsonParser().parse(response).getAsJsonObject();
                    List<EntrustHistory> orders = gson.fromJson(object1.getAsJsonArray("content")
                            .getAsJsonArray(), new TypeToken<List<EntrustHistory>>() {
                    }.getType());
                    view.getHistorySuccess(orders);
                } catch (Exception e1) {
                    // view.onDataNotAvailable(JSON_ERROR, null);
                }
            }
        });
    }


    /**
     * 取消某个委托
     */
    @Override
    public void getCancelEntrust(String token, String orderId) {
        WonderfulOkhttpUtils.post().url(UrlFactory.getCancleEntrustUrl() + orderId)
                .addHeader("x-auth-token", token).build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {
                view.getCancelFail();
                if (view != null) view.errorMes(OKHTTP_ERROR, "取消失败");
            }

            @Override
            public void onResponse(String response) {
                try {
                    JSONObject object = new JSONObject(response);
                    if (object.optInt("code") == 0) {
                        view.getCancelSuccess(object.getString("message"));
                    } else {
                        view.getCancelFail();
                        view.errorMes(object.getInt("code"), object.optString("message"));
                    }
                } catch (JSONException e) {
                    view.getCancelFail();
                    e.printStackTrace();
                    view.errorMes(JSON_ERROR, "取消失败");
                }
            }
        });
    }

    /**
     * 获取单个钱包
     */
    @Override
    public void getWallet(final int type, String token, String coinName) {
        WonderfulOkhttpUtils.post().url(UrlFactory.getWalletUrl() + coinName)
                .addParams("x-auth-token", token).build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {
                super.onError(request, e);
                view.getWalletSuccess(null, 3);
            }

            @Override
            public void onResponse(String response) {
                WonderfulLogUtils.logi("jiejie", "获取单个钱包回调" + response);

                if (view == null) return;
                try {
                    Money money = new Gson().fromJson(response, Money.class);
                    view.getWalletSuccess(money, type);
                } catch (Exception e) {
                    view.getWalletSuccess(null, 3);
                }

            }
        });
    }

    @Override
    public void getSymbolInfo(String symbol) {
        WonderfulOkhttpUtils.post().url(UrlFactory.getSymbolInfo())
                .addParams("symbol", symbol).build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {
                super.onError(request, e);
                if (view == null) return;
                view.getAccuracy(4, 4);
            }

            @Override
            public void onResponse(String response) {
                if (view == null) return;
                ThreeTextInfo info = gson.fromJson(response, ThreeTextInfo.class);
                if (info == null) {
//                    WonderfulToastUtils.showToast(WonderfulToastUtils.getString(R.string.parse_error));
                    return;
                }
                view.getAccuracy(info.getCoinScale(), info.getBaseCoinScale());
            }
        });
    }
    
}
