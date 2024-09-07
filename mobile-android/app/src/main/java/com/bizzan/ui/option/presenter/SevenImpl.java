package com.bizzan.ui.option.presenter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import com.bizzan.app.UrlFactory;
import com.bizzan.entity.CurrentBean;
import com.bizzan.entity.ForecaseBean;
import com.bizzan.entity.Money;
import com.bizzan.entity.OptionAddBean;
import com.bizzan.entity.OptionIconBean;
import com.bizzan.entity.OptionOrderHistoryBean;
import com.bizzan.ui.option.ISevenContract;
import com.bizzan.utils.WonderfulLogUtils;
import com.bizzan.utils.okhttp.StringCallback;
import com.bizzan.utils.okhttp.WonderfulOkhttpUtils;
import okhttp3.Request;

import static com.bizzan.app.GlobalConstant.JSON_ERROR;
import static com.bizzan.app.GlobalConstant.OKHTTP_ERROR;
import static com.bizzan.utils.okhttp.WonderfulOkhttpUtils.post;

/**
 * author: wuzongjie
 * time  : 2018/4/17 0017 19:14
 * desc  :
 */

public class SevenImpl implements ISevenContract.Presenter {
    ISevenContract.View view;

    public SevenImpl(ISevenContract.View view) {
        this.view = view;
    }

    //获取往期结果的45条数据
    @Override
    public void OptionHistory(String symbol, String token) {
        System.out.println("token:" + token);
        WonderfulOkhttpUtils.post().url(UrlFactory.getOption_History())
                .addHeader("x-auth-token", token)
                .addParams("symbol", symbol)
                .addParams("count", "45")
                .build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {
                super.onError(request, e);
                if (view != null) {
                    view.errorMes(OKHTTP_ERROR, "");
                }
            }

            @Override
            public void onResponse(String response) {
                WonderfulLogUtils.logi("SevenImpl:", "getoptionhistory:" + response.toString());
                if (view == null) return;
                try {
                    JSONObject object = new JSONObject(response);
                    if (object.optInt("code") == 0) {
                        OptionIconBean.DataBean bean = gson.fromJson(object.getJSONObject("data").toString(), new TypeToken<OptionIconBean.DataBean>() {
                        }.getType());

                        view.option_History(bean);
                    } else {
                        view.errorMes(object.getInt("code"), object.optString("message"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    view.errorMes(JSON_ERROR, "");
                }
            }
        });
    }

    //获取历史交割数据
    @Override
    public void OrderHistory(String symbol, String pageNo, String pageSize, String token) {
        WonderfulOkhttpUtils.post().url(UrlFactory.getOrder_History())
                .addHeader("x-auth-token", token)
                .addParams("symbol", symbol)
                .addParams("pageNo", pageNo)
                .addParams("pageSize", pageSize)
                .build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {
                super.onError(request, e);
                if (view != null) {
                    view.errorMes(OKHTTP_ERROR, "");
                }
            }

            @Override
            public void onResponse(String response) {
                WonderfulLogUtils.logi("SevenImpl:", "OrderHistory:" + response.toString());
                if (view == null) return;
                try {
                    JSONObject object = new JSONObject(response);
                    if (object.optInt("code") == 0) {
                        OptionOrderHistoryBean.DataBean bean = gson.fromJson(object.getJSONObject("data").toString(), new TypeToken<OptionOrderHistoryBean.DataBean>() {
                        }.getType());

                        view.option_Order_History(bean);
                    } else {
                        view.errorMes(object.getInt("code"), object.optString("message"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    view.errorMes(JSON_ERROR, "");
                }
            }
        });
    }

    //获取要开始的预测
    @Override
    public void OptionStarting(String symbol, String token) {
        WonderfulOkhttpUtils.post().url(UrlFactory.getOption_Starting())
                .addHeader("x-auth-token", token)
                .addParams("symbol", symbol)
                .build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {
                super.onError(request, e);
                if (view != null) {
                    view.errorMes(OKHTTP_ERROR, "");
                }
            }

            @Override
            public void onResponse(String response) {
                WonderfulLogUtils.logi("SevenImpl:", "getoptionstarting:" + response.toString());
                if (view == null) return;
                try {
                    JSONObject object = new JSONObject(response);
                    if (object.optInt("code") == 0) {
                        List<ForecaseBean.DataBean> bean = gson.fromJson(object.getJSONArray("data").toString(), new TypeToken<List<ForecaseBean.DataBean>>() {
                        }.getType());

                        view.option_Starting(bean);
                    } else {
                        view.errorMes(object.getInt("code"), object.optString("message"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    view.errorMes(JSON_ERROR, "");
                }
            }
        });
    }

    //获取正在进行中的预测
    @Override
    public void OptionOpening(String symbol, String token) {
        WonderfulOkhttpUtils.post().url(UrlFactory.getOption_Opening())
                .addHeader("x-auth-token", token)
                .addParams("symbol", symbol)
                .build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {
                super.onError(request, e);
                if (view != null) {
                    view.errorMes(OKHTTP_ERROR, "");
                }
            }

            @Override
            public void onResponse(String response) {
                WonderfulLogUtils.logi("SevenImpl:", "getoptionopening:" + response.toString());
                if (view == null) return;
                try {
                    JSONObject object = new JSONObject(response);
                    if (object.optInt("code") == 0) {
                        List<ForecaseBean.DataBean> bean = gson.fromJson(object.getJSONArray("data").toString(), new TypeToken<List<ForecaseBean.DataBean>>() {
                        }.getType());

                        view.option_Opening(bean);
                    } else {
                        view.errorMes(object.getInt("code"), object.optString("message"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    view.errorMes(JSON_ERROR, "");
                }
            }
        });
    }

    //获取我的开仓数据
    @Override
    public void OptionCurrent(String symbol, String optionId, String token, final String type) {
        WonderfulOkhttpUtils.post().url(UrlFactory.getOption_Current())
                .addHeader("x-auth-token", token)
                .addParams("symbol", symbol)
                .addParams("optionId", optionId)
                .build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {
                super.onError(request, e);
                if (view != null) {
                    view.errorMes(OKHTTP_ERROR, "");
                }
            }

            @Override
            public void onResponse(String response) {
                WonderfulLogUtils.logi("SevenImpl:", "getoptioncurrent:" + response.toString());
                if (view == null) return;
                try {
                    JSONObject object = new JSONObject(response);
                    if (object.optInt("code") == 0) {
                        List<CurrentBean.DataBean> bean = gson.fromJson(object.getJSONArray("data").toString(), new TypeToken<List<CurrentBean.DataBean>>() {
                        }.getType());
                        if (type.equals("1")) {
                            view.option_Current(bean);
                        }else if (type.equals("2")) {
                            view.option_Current2(bean);
                        }
                    } else {
                        view.errorMes(object.getInt("code"), object.optString("message"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    view.errorMes(JSON_ERROR, "");
                }
            }
        });
    }

    /**
     * 获取单个钱包
     */
    @Override
    public void getWallet(String token, String coinName) {
        WonderfulOkhttpUtils.post().url(UrlFactory.getWalletUrl() + coinName)
                .addParams("x-auth-token", token)
                .build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {
                super.onError(request, e);
            }

            @Override
            public void onResponse(String response) {
                WonderfulLogUtils.logi("SevenImpl:", "getWallet:" + response.toString());

                if (view == null) return;
                try {
                    Money money = new Gson().fromJson(response, Money.class);
                    view.option_WalletUrl(money);
                } catch (Exception e) {
                    e.printStackTrace();
                    view.errorMes(JSON_ERROR, "");
                }

            }
        });
    }

    //type  1 表示第一次请求  2表示加载数据
    //获取k线数据
    @Override
    public void KData(String symbol, Long from, Long to, String resolution, final String type) {
        post().url(UrlFactory.getKDataUrl_Constract())
                .addParams("symbol", symbol)
                .addParams("from", from + "")
                .addParams("to", to + "")
                .addParams("resolution", resolution + "")
                .build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {
                super.onError(request, e);
                WonderfulLogUtils.logi("期权历史K线数据出错", "期权历史K线数据出错：" + e.getMessage());
                if (view != null) {
                    view.hideDialog();
                    view.errorMes(OKHTTP_ERROR, null);
                }
            }

            @Override
            public void onResponse(String response) {
                WonderfulLogUtils.logi("期权历史K线数据回执：", "期权历史K线数据回执：" + response.toString());
                if (view == null) return;
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    if (type.equals("1")) {
                        view.KDataSuccess((JSONArray) jsonArray);
                    } else if (type.equals("2")) {
                        view.KDataSuccess2((JSONArray) jsonArray);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    view.errorMes(JSON_ERROR, null);
                }
            }
        });

    }
//看涨或者看跌

    /**
     * @param symbol    币种
     * @param direction 0 看涨 1看跌
     * @param optionId  id
     * @param amount    数量
     * @param token
     */
    @Override
    public void Add(String symbol, String direction, String optionId, String amount, String token) {
        WonderfulOkhttpUtils.post().url(UrlFactory.getAdd())
                .addHeader("x-auth-token", token)
                .addParams("symbol", symbol)
                .addParams("direction", direction)
                .addParams("optionId", optionId)
                .addParams("amount", amount)
                .build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {
                super.onError(request, e);
                if (view != null) {
                    view.errorMes(OKHTTP_ERROR, "");
                }
            }

            @Override
            public void onResponse(String response) {
                WonderfulLogUtils.logi("SevenImpl:", "getoptionstarting:" + response.toString());
                if (view == null) return;
                OptionAddBean bean = gson.fromJson(response, OptionAddBean.class);
                view.option_Add(bean.getMessage());
            }
        });
    }

}
