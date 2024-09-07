package com.bizzan.ui.home.presenter;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import com.bizzan.app.UrlFactory;
import com.bizzan.entity.CurrentEntrust;
import com.bizzan.entity.Detail;
import com.bizzan.entity.Exchange;
import com.bizzan.entity.SixInfo;
import com.bizzan.entity.modifyleverage;
import com.bizzan.entity.switchpattern;
import com.bizzan.ui.home.ISixContract;
import com.bizzan.utils.WonderfulLogUtils;
import com.bizzan.utils.okhttp.StringCallback;
import com.bizzan.utils.okhttp.WonderfulOkhttpUtils;
import okhttp3.Request;

import static com.bizzan.app.GlobalConstant.JSON_ERROR;
import static com.bizzan.app.GlobalConstant.OKHTTP_ERROR;

/**
 * author: wuzongjie
 * time  : 2018/4/17 0017 19:14
 * desc  :
 */

public class SixImpl implements ISixContract.Presenter {
    ISixContract.View view;

    public SixImpl(ISixContract.View view) {
        this.view = view;
    }


    /**
     * 买卖  开仓
     */
    @Override
    public void getAddOrder(String token, String type, String direction, String contractCoinId,
                            String triggerPrice, String entrustPrice, String leverage, String volume) {
        if (view != null) view.showDialog();
        WonderfulOkhttpUtils.post().url(UrlFactory.getOpen())
                .addHeader("x-auth-token", token)
                .addParams("type", type)
                .addParams("direction", direction)
                .addParams("contractCoinId", contractCoinId)
                .addParams("triggerPrice", triggerPrice)
                .addParams("entrustPrice", entrustPrice)
                .addParams("leverage", leverage)
                .addParams("volume", volume)
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
     * 买卖  平仓
     */
    @Override
    public void getcloseOrder(String token, String type, String direction, String contractCoinId,
                            String triggerPrice, String entrustPrice, String leverage, String volume) {
        System.out.println("1111111111222222:");
        if (view != null) view.showDialog();
        WonderfulOkhttpUtils.post().url(UrlFactory.getClone())
                .addHeader("x-auth-token", token)
                .addParams("type", type)
                .addParams("direction", direction)
                .addParams("contractCoinId", contractCoinId)
                .addParams("triggerPrice", triggerPrice)
                .addParams("entrustPrice", entrustPrice)
                .addParams("leverage", leverage)
                .addParams("volume", volume)
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
        WonderfulOkhttpUtils.post().url(UrlFactory.getPlate_sixUrl())
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
                                Log.d("miao", UrlFactory.getPlateUrl()+"查询盘信息 " + e.getMessage());
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
     */
    @Override
    public void getCurrentOrder(String token, int contractCoinId,int pageNo, int pageSize) {
        WonderfulOkhttpUtils.post().url(UrlFactory.getEntrust())
                .addHeader("x-auth-token", token)
                .addParams("contractCoinId",contractCoinId+"")
                .addParams("pageNo", pageNo + "")
                .addParams("pageSize", pageSize + "")
                .build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {
                super.onError(request, e);
                if (view != null)
                    view.errorMes(OKHTTP_ERROR, "网络错误");
            }

            @Override
            public void onResponse(String response) {
                Log.d("trust", "当前委托add" + response);
                int code = 4000;
                if (view != null) {
                    try {
                        JSONObject object = new JSONObject(response);
                        code = object.optInt("code");
//                        List<CurrentEntrust> objs = gson.fromJson(object.getJSONArray("content").toString(), new TypeToken<List<CurrentEntrust>>() {
//                        }.getType());
                        CurrentEntrust CurrentEntrust = new Gson().fromJson(response, CurrentEntrust.class);
                        view.getDataLoaded(CurrentEntrust);
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
     * 取消某个委托
     */
    @Override
    public void getCancelEntrust(String token, String entrustId) {
        WonderfulOkhttpUtils.post().url(UrlFactory.getCancleEntrusConstracttUrl())
                .addHeader("x-auth-token", token)
                .addParams("entrustId",entrustId).build().execute(new StringCallback() {
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

    @Override
    public void getSymbolInfo(String symbol) {
        WonderfulOkhttpUtils.post().url(UrlFactory.getSymbolInfo_Constract())
                .addParams("symbol", symbol).build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {
                super.onError(request, e);
                if (view == null) return;
                SixInfo info = null;
                view.getAccuracy(4, 4, info);
            }

            @Override
            public void onResponse(String response) {
                if (view == null) return;
                SixInfo info = gson.fromJson(response, SixInfo.class);
                if (info == null) {
//                    WonderfulToastUtils.showToast(WonderfulToastUtils.getString(R.string.parse_error));
                    return;
                }
                view.getAccuracy(info.getCoinScale(), info.getBaseCoinScale(), info);
            }
        });
    }

    @Override
    public void getSwitchPattern(String contractCoinId, String targetPattern) {
        WonderfulOkhttpUtils.post().url(UrlFactory.getSwitchPattern())
                .addParams("contractCoinId", contractCoinId)
                .addParams("targetPattern", targetPattern)
                .build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {
                super.onError(request, e);
                if (view != null) view.errorMes(OKHTTP_ERROR, "修改失败");
            }

            @Override
            public void onResponse(String response) {
                if (view == null) return;
                switchpattern switchpattern = gson.fromJson(response, switchpattern.class);
                if (switchpattern == null) {
                    return;
                }
                view.getswitch(switchpattern);
            }
        });
    }

    @Override
    public void getDetail(String contractCoinId, String token) {
        WonderfulOkhttpUtils.post().url(UrlFactory.getDetail())
                .addHeader("x-auth-token", token)
                .addParams("contractCoinId", contractCoinId)
                .build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {
                super.onError(request, e);
                if (view != null) view.errorMes(OKHTTP_ERROR, e.getMessage());
            }

            @Override
            public void onResponse(String response) {
                if (view == null) return;
                Detail detail = gson.fromJson(response, Detail.class);
                if (detail == null) {
                    return;
                }
                view.getdetail(detail);
            }
        });
    }

    @Override
    public void getModifyLeverage(String contractCoinId, String leverage, String direction) {
        WonderfulOkhttpUtils.post().url(UrlFactory.getModifyLeverage())
                .addParams("contractCoinId", contractCoinId)
                .addParams("leverage", leverage)
                .addParams("direction", direction)
                .build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {
                super.onError(request, e);
                if (view != null) view.errorMes(OKHTTP_ERROR, e.getMessage());
            }

            @Override
            public void onResponse(String response) {
                if (view == null) return;
                modifyleverage modifyleverage = gson.fromJson(response, modifyleverage.class);
                if (modifyleverage == null) {
                    return;
                }
                view.GetModifyLeverage(modifyleverage);
            }
        });
    }

}
