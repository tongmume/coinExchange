package com.bizzan.data;


import com.bizzan.R;
import com.bizzan.app.MyApplication;
import com.bizzan.app.UrlFactory;
import com.bizzan.entity.AccountSetting;
import com.bizzan.entity.Ads;
import com.bizzan.entity.AppInfo;
import com.bizzan.entity.BannerEntity;
import com.bizzan.entity.C2C;
import com.bizzan.entity.C2CExchangeInfo;
import com.bizzan.entity.CTCOrder;
import com.bizzan.entity.CTCOrderDetail;
import com.bizzan.entity.CTCPrice;
import com.bizzan.entity.ChatEntity;
import com.bizzan.entity.Coin;
import com.bizzan.entity.CoinInfo;
import com.bizzan.entity.Country;
import com.bizzan.entity.Credit;
import com.bizzan.entity.Currency;
import com.bizzan.entity.DepositInfo;
import com.bizzan.entity.EntrustHistory;
import com.bizzan.entity.ExtractInfo;
import com.bizzan.entity.Favorite;
import com.bizzan.entity.GccMatch;
import com.bizzan.entity.MarketSymbol;
import com.bizzan.entity.Message;
import com.bizzan.entity.Order;
import com.bizzan.entity.OrderDetial;
import com.bizzan.entity.Plate;
import com.bizzan.entity.PromotionRecord;
import com.bizzan.entity.PromotionReward;
import com.bizzan.entity.SafeSetting;
import com.bizzan.entity.User;
import com.bizzan.entity.Vision;
import com.bizzan.entity.WalletConstract;
import com.bizzan.entity.WalletDetailNew;
import com.bizzan.entity.Wallet_Coin;
import com.bizzan.utils.LogUtils;
import com.bizzan.utils.SharedPreferenceInstance;
import com.bizzan.utils.WonderfulLogUtils;
import com.bizzan.utils.WonderfulToastUtils;
import com.bizzan.utils.okhttp.StringCallback;
import com.bizzan.utils.okhttp.WonderfulOkhttpUtils;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.util.List;

import okhttp3.Request;

import static com.bizzan.app.GlobalConstant.JSON_ERROR;
import static com.bizzan.app.GlobalConstant.OKHTTP_ERROR;
import static com.bizzan.utils.okhttp.WonderfulOkhttpUtils.post;

/**
 * Created by Administrator on 2017/9/25.
 */
public class RemoteDataSource implements DataSource {
    private static RemoteDataSource INSTANCE;

    public static RemoteDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new RemoteDataSource();
        }
        return INSTANCE;
    }

    private RemoteDataSource() {

    }

    public void loadDayK(int count, DataCallback loadDataCallback) {

    }

    @Override
    public void phoneCode(String phone, String country, final DataCallback dataCallback) {
        post().url(UrlFactory.getPhoneCodeUrl())
                .addParams("phone", phone).addParams("country", country)
                .build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {
                super.onError(request, e);
                WonderfulLogUtils.logi("获取手机验证码出错", "获取手机验证码出错：" + e.getMessage());
                dataCallback.onDataNotAvailable(OKHTTP_ERROR, null);
            }

            @Override
            public void onResponse(String response) {
                WonderfulLogUtils.logi("获取手机验证码回执：", "获取手机验证码回执：" + response.toString());
                try {
                    JSONObject object = new JSONObject(response);
                    if (object.optInt("code") == 0) {
                        dataCallback.onDataLoaded("获取成功");
                    } else {
                        dataCallback.onDataNotAvailable(object.getInt("code"), object.optString("message"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    dataCallback.onDataNotAvailable(JSON_ERROR, null);
                }
            }
        });
    }

    @Override
    public void emailCode(String email, final DataCallback dataCallback) {
        post().url(UrlFactory.getEmailCodeUrl())
                .addParams("email", email)
                .build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {
                super.onError(request, e);
                WonderfulLogUtils.logi("获取邮箱验证码出错", "获取邮箱验证码出错：" + e.getMessage());
                dataCallback.onDataNotAvailable(OKHTTP_ERROR, null);
            }

            @Override
            public void onResponse(String response) {
                WonderfulLogUtils.logi("获取邮箱验证码回执：", "获取邮箱验证码回执：" + response.toString());
                try {
                    JSONObject object = new JSONObject(response);
                    if (object.optInt("code") == 0) {
                        dataCallback.onDataLoaded("获取成功");
                    } else {
                        dataCallback.onDataNotAvailable(object.getInt("code"), object.optString("message"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    dataCallback.onDataNotAvailable(JSON_ERROR, null);
                }
            }
        });
    }

    @Override
    public void signUpByPhone(String phone, String username, String password, String country, String code, String tuijianma, String challenge, String validate, String seccode, final DataCallback dataCallback) {
        if (country == null || "".equals(country)) {
            WonderfulToastUtils.showToast("请填写完整信息");
            return;
        }
        post().url(UrlFactory.getSignUpByPhone()).addParams("phone", phone).addParams("code", code).addParams("promotion", tuijianma + "")
                .addParams("username", username).addParams("password", password).addParams("country", country).addParams("ticket", challenge)
                .addParams("randStr", validate).build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {
                super.onError(request, e);
                WonderfulLogUtils.logi("手机号码注册出错", "手机号码注册出错：" + e.getMessage());
                dataCallback.onDataNotAvailable(OKHTTP_ERROR, null);
            }

            @Override
            public void onResponse(String response) {
                WonderfulLogUtils.logi("手机号码注册回执：", "手机号码注册回执：" + response.toString());
                try {
                    JSONObject object = new JSONObject(response);
                    if (object.optInt("code") == 0) {
                        dataCallback.onDataLoaded("注册成功");
                    } else {
                        dataCallback.onDataNotAvailable(object.getInt("code"), object.optString("message"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    dataCallback.onDataNotAvailable(JSON_ERROR, null);
                }
            }
        });
    }

    @Override
    public void signUpByEmail(String email, String password, String tuijian2, String country, String code, String validate, String seccode, final DataCallback dataCallback) {
        post().url(UrlFactory.getSignUpByEmail())
                .addParams("email", email)
                .addParams("promotion", tuijian2)
                .addParams("username", email)
                .addParams("password", password)
                .addParams("country", country)
                .addParams("superPartner", "")
                .addParams("code", code).build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {
                super.onError(request, e);
                WonderfulLogUtils.logi("邮箱注册出错", "邮箱注册出错：" + e.getMessage());
                dataCallback.onDataNotAvailable(OKHTTP_ERROR, null);
            }

            @Override
            public void onResponse(String response) {
                WonderfulLogUtils.logi("邮箱注册回执：", "邮箱注册回执：" + response.toString());
                try {
                    JSONObject object = new JSONObject(response);
                    if (object.optInt("code") == 0) {
                        dataCallback.onDataLoaded(object.optString("message"));
                    } else {
                        dataCallback.onDataNotAvailable(object.getInt("code"), object.optString("message"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    dataCallback.onDataNotAvailable(JSON_ERROR, null);
                }
            }
        });
    }

    @Override
    public void login(String username, String password, String challenge, String validate,
                      String seccode, final DataCallback dataCallback) {
        post().url(UrlFactory.getLoginUrl()).addParams("password", password).addParams("username", username).addParams("challenge", challenge)
                .addParams("validate", validate).addParams("seccode", seccode).build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {
                super.onError(request, e);
                WonderfulLogUtils.logi("登录出错", "登录出错：" + e.getMessage());
                dataCallback.onDataNotAvailable(OKHTTP_ERROR, null);
            }

            @Override
            public void onResponse(String response) {
                WonderfulLogUtils.logi("登录回执：", "登录回执：" + response.toString());
                try {
                    JSONObject object = new JSONObject(response);
                    if (object.optInt("code") == 0) {
                        User obj = gson.fromJson(object.getJSONObject("data").toString(), User.class);
                        dataCallback.onDataLoaded(obj);
                    } else {
                        dataCallback.onDataNotAvailable(object.getInt("code"), object.optString("message"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    dataCallback.onDataNotAvailable(JSON_ERROR, null);
                }
            }
        });
    }


    @Override
    public void KData(String symbol, Long from, Long to, String resolution, final DataCallback dataCallback) {
        WonderfulLogUtils.loge("INFO", from + "   " + to);
        post().url(UrlFactory.getKDataUrl())
                .addParams("symbol", symbol).addParams("from", from + "").addParams("to", to + "").addParams("resolution", resolution + "").build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {
                super.onError(request, e);
                WonderfulLogUtils.logi("历史K线数据出错", "历史K线数据出错：" + e.getMessage());
                dataCallback.onDataNotAvailable(OKHTTP_ERROR, null);
            }

            @Override
            public void onResponse(String response) {
                WonderfulLogUtils.logi("历史K线数据回执：", "历史K线数据回执：" + response.toString());
//                LogUtils.e("miao历史",response.toString());
                try {
                    dataCallback.onDataLoaded(new JSONArray(response));
                } catch (JSONException e) {
                    e.printStackTrace();
                    dataCallback.onDataNotAvailable(JSON_ERROR, null);
                }
            }
        });
    }

    @Override
    public void KData_Constract(String symbol, Long from, Long to, String resolution, final DataCallback dataCallback) {
        WonderfulLogUtils.loge("INFO", from + "   " + to + "     " + symbol + "     " + resolution);
        post().url(UrlFactory.getKDataUrl_Constract())
                .addParams("symbol", symbol)
                .addParams("from", from + "")
                .addParams("to", to + "")
                .addParams("resolution", resolution + "")
                .build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {
                super.onError(request, e);
                WonderfulLogUtils.logi("历史K线数据出错", "历史K线数据出错：" + e.getMessage());
                dataCallback.onDataNotAvailable(OKHTTP_ERROR, null);
            }

            @Override
            public void onResponse(String response) {
                WonderfulLogUtils.logi("历史K线数据回执：", "历史K线数据回执：" + response.toString());
//                LogUtils.e("miao历史",response.toString());
                try {
                    dataCallback.onDataLoaded(new JSONArray(response));
                } catch (JSONException e) {
                    e.printStackTrace();
                    dataCallback.onDataNotAvailable(JSON_ERROR, null);
                }
            }
        });
    }

    /**
     * 首页获取所有的币种
     */
    @Override
    public void allHomeCurrency(final DataCallback dataCallback) {
        WonderfulOkhttpUtils.post().url(UrlFactory.getAllCurrencys()).build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {
                super.onError(request, e);
                WonderfulLogUtils.logi("获取所有币种出错", "获取所有币种出错：" + e.getMessage());
                dataCallback.onDataNotAvailable(OKHTTP_ERROR, null);
            }

            @Override
            public void onResponse(String response) {
//                Log.d("jiejie","home " + response);
                WonderfulLogUtils.logi("获取所有币种回执：", "获取所有币种回执：" + response.toString());
                dataCallback.onDataLoaded(response);
            }
        });
    }

    /**
     * 首页获取所有的币种
     */
    @Override
    public void allCurrency(final DataCallback dataCallback) {
        WonderfulOkhttpUtils.post().url(UrlFactory.getAllCurrency()).build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {
                super.onError(request, e);
//                Log.d("jiejie","111111" + e);
                WonderfulLogUtils.logi("获取所有币种出错", "获取所有币种出错：" + e.getMessage());
                dataCallback.onDataNotAvailable(OKHTTP_ERROR, null);
            }

            @Override
            public void onResponse(String response) {
//                Log.d("jiejie","----" + response);
                WonderfulLogUtils.logi("获取所有币种回执：", "获取所有币种回执：" + response.toString());
//                JsonObject object = new JsonParser().parse(response).getAsJsonObject();
//                JsonArray array = object.getAsJsonArray("changeRank").getAsJsonArray();
//                List<Currency> objs = gson.fromJson(array,new TypeToken<List<Currency>>() {
//                    }.getType());
                //              dataCallback.onDataLoaded(response);
                // List<Currency> objs = new
                try {
                    JSONObject object = new JSONObject(response);
                    dataCallback.onDataNotAvailable(object.optInt("code"), object.optString("message"));
                } catch (Exception ex) {
                    List<Currency> objs = gson.fromJson(response, new TypeToken<List<Currency>>() {
                    }.getType());
                    dataCallback.onDataLoaded(objs);
                }
            }
        });
    }

    @Override
    public void find(String token, final DataCallback dataCallback) {
        WonderfulOkhttpUtils.post().url(UrlFactory.getFindUrl()).addHeader("x-auth-token", token).build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {
                super.onError(request, e);
                WonderfulLogUtils.logi("查询自选出错", "查询自选出错：" + e.getMessage());
                dataCallback.onDataNotAvailable(OKHTTP_ERROR, null);
            }

            @Override
            public void onResponse(String response) {
                try {
                    JSONObject obj = new JSONObject(response);
                    dataCallback.onDataNotAvailable(obj.getInt("code"), obj.getString("message"));
                } catch (JSONException e) {
                    WonderfulLogUtils.logi("查询自选回执：", "查询自选回执：" + response.toString());
                    List<Favorite> objs = gson.fromJson(response, new TypeToken<List<Favorite>>() {
                    }.getType());
                    dataCallback.onDataLoaded(objs);
                }
            }
        });
    }

    @Override
    public void delete(String token, String symbol, final DataCallback dataCallback) {
        WonderfulOkhttpUtils.post().url(UrlFactory.getDeleteUrl()).addHeader("x-auth-token", token)
                .addParams("symbol", symbol).build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {
                super.onError(request, e);
                WonderfulLogUtils.logi("删除自选出错", "删除自选出错：" + e.getMessage());
                dataCallback.onDataNotAvailable(OKHTTP_ERROR, null);
            }

            @Override
            public void onResponse(String response) {
                WonderfulLogUtils.logi("删除自选回执：", "删除自选回执：" + response.toString());
                try {
                    JSONObject object = new JSONObject(response);
                    if (object.optInt("code") == 0) {
                        dataCallback.onDataLoaded("删除成功");
                    } else {
                        dataCallback.onDataNotAvailable(object.getInt("code"), object.optString("message"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    dataCallback.onDataNotAvailable(JSON_ERROR, null);
                }
            }
        });
    }

    @Override
    public void add(String token, String symbol, final DataCallback dataCallback) {
        WonderfulOkhttpUtils.post().url(UrlFactory.getAddUrl()).addHeader("x-auth-token", token)
                .addParams("symbol", symbol).build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {
                super.onError(request, e);
                WonderfulLogUtils.logi("添加自选出错", "添加自选出错：" + e.getMessage());
                dataCallback.onDataNotAvailable(OKHTTP_ERROR, null);
            }

            @Override
            public void onResponse(String response) {
                WonderfulLogUtils.logi("添加自选回执：", "添加自选回执：" + response.toString());
                try {
                    JSONObject object = new JSONObject(response);
                    if (object.optInt("code") == 0) {
                        dataCallback.onDataLoaded("添加成功");
                    } else {
                        dataCallback.onDataNotAvailable(object.getInt("code"), object.optString("message"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    dataCallback.onDataNotAvailable(JSON_ERROR, null);
                }
            }
        });
    }

    @Override
    public void exChange(String token, String symbol, String price, String amount, String direction, String type, final DataCallback dataCallback) {
        WonderfulOkhttpUtils.post().url(UrlFactory.getExChangeUrl()).addHeader("x-auth-token", token).addParams("symbol", symbol)
                .addParams("price", price).addParams("amount", amount).addParams("direction", direction).addParams("type", type).build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {
                super.onError(request, e);
                WonderfulLogUtils.logi("提交委托出错", "提交委托出错：" + e.getMessage());
                dataCallback.onDataNotAvailable(OKHTTP_ERROR, null);

            }

            @Override
            public void onResponse(String response) {
                WonderfulLogUtils.logi("提交委托回执：", "提交委托回执：" + response.toString());
                try {
                    JSONObject object = new JSONObject(response);
                    if (object.optInt("code") == 0) {
                        dataCallback.onDataLoaded(object.optString("message"));
                    } else {
                        dataCallback.onDataNotAvailable(object.getInt("code"), object.optString("message"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    dataCallback.onDataNotAvailable(JSON_ERROR, null);
                }
            }
        });
    }

    @Override
    public void wallet(String token, String coinName, final DataCallback dataCallback) {
        WonderfulOkhttpUtils.post().url(UrlFactory.getWalletUrl() + coinName).addHeader("x-auth-token", token).build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {
                super.onError(request, e);
                WonderfulLogUtils.logi("获取单个钱包出错", "获取单个钱包出错：" + e.getMessage());
                dataCallback.onDataNotAvailable(OKHTTP_ERROR, null);
            }

            @Override
            public void onResponse(String response) {
                //WonderfulLogUtils.logi("获取单个钱包回执：", "获取单个钱包回执：" + response.toString());
                try {
                    JSONObject object = new JSONObject(response);
                    if (object.optInt("code") == 0) {
                        Coin obj = gson.fromJson(object.getJSONObject("data").toString(), Coin.class);
                        dataCallback.onDataLoaded(obj);
                    } else {
                        dataCallback.onDataNotAvailable(object.getInt("code"), object.optString("message") + "请重新登录");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    dataCallback.onDataNotAvailable(JSON_ERROR, null);
                }
            }
        });
    }

    @Override
    public void all(final DataCallback dataCallback) {
        WonderfulOkhttpUtils.post().url(UrlFactory.getAllUrl()).build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {
                super.onError(request, e);
                WonderfulLogUtils.logi("获取支持的币种出错", "获取支持的币种出错：" + e.getMessage());
                dataCallback.onDataNotAvailable(OKHTTP_ERROR, null);
            }

            @Override
            public void onResponse(String response) {
                WonderfulLogUtils.logi("获取支持的币种回执：", "获取支持的币种回执：" + response.toString());
                try {
                    JSONObject object = new JSONObject(response);
                    if (object.optInt("code") == 0) {
                        List<CoinInfo> objs = gson.fromJson(object.getJSONArray("data").toString(), new TypeToken<List<CoinInfo>>() {
                        }.getType());
                        dataCallback.onDataLoaded(objs);
                    } else {
                        dataCallback.onDataNotAvailable(object.getInt("code"), object.optString("message"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    dataCallback.onDataNotAvailable(JSON_ERROR, null);
                }
            }
        });
    }

    @Override
    public void advertise(int pageNo, int pageSize, String advertiseType, String id, final DataCallback dataCallback) {
        WonderfulOkhttpUtils.post().url(UrlFactory.getAdvertiseUrl())
                .addParams("pageNo", pageNo + "").addParams("pageSize", pageSize + "").addParams("advertiseType", advertiseType).addParams("id", id).build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {
                super.onError(request, e);
                WonderfulLogUtils.logi("广告查询出错", "广告查询出错：" + e.getMessage());
                dataCallback.onDataNotAvailable(OKHTTP_ERROR, null);
            }

            @Override
            public void onResponse(String response) {
                WonderfulLogUtils.logi("广告查询回执：", "广告查询回执：" + response.toString());
                try {
                    JSONObject object = new JSONObject(response);
                    if (object.optInt("code") == 0) {
                        C2C obj = gson.fromJson(object.getJSONObject("data").toString(), C2C.class);
                        dataCallback.onDataLoaded(obj);
                    } else {
                        dataCallback.onDataNotAvailable(object.getInt("code"), object.optString("message"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    dataCallback.onDataNotAvailable(JSON_ERROR, null);
                }
            }
        });
    }

    @Override
    public void country(final DataCallback dataCallback) {
        WonderfulOkhttpUtils.post().url(UrlFactory.getCountryUrl()).build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {
                super.onError(request, e);
                WonderfulLogUtils.logi("获取所有国家出错", "获取所有国家出错：" + e.getMessage());
                dataCallback.onDataNotAvailable(OKHTTP_ERROR, null);
            }

            @Override
            public void onResponse(String response) {
                WonderfulLogUtils.logi("获取所有国家回执：", "获取所有国家回执：" + response.toString());
                try {
                    JSONObject object = new JSONObject(response);
                    if (object.optInt("code") == 0) {
                        List<Country> objs = gson.fromJson(object.getJSONArray("data").toString(), new TypeToken<List<Country>>() {
                        }.getType());
                        dataCallback.onDataLoaded(objs);
                    } else {
                        dataCallback.onDataNotAvailable(object.getInt("code"), object.optString("message"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    dataCallback.onDataNotAvailable(JSON_ERROR, null);
                }
            }
        });
    }

    @Override
    public void create(String token, final String price, String advertiseType, String coinId, String minLimit, String maxLimit,
                       int timeLimit, String countryZhName, String priceType, String premiseRate, String remark,
                       String number, String pay, String jyPassword, String auto, String autoword, final DataCallback dataCallback) {
        WonderfulOkhttpUtils.post().url(UrlFactory.getReleaseAdUrl()).addHeader("x-auth-token", token)
                .addParams("price", price).addParams("advertiseType", advertiseType).addParams("coin.id", coinId).addParams("minLimit", minLimit).addParams("maxLimit", maxLimit)
                .addParams("timeLimit", timeLimit + "").addParams("country.ZhName", countryZhName).addParams("priceType", priceType).addParams("premiseRate", premiseRate).addParams("remark", remark)
                .addParams("number", number).addParams("pay[]", pay).addParams("jyPassword", jyPassword).addParams("auto", auto).addParams("autoword", autoword)
                .build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {
                super.onError(request, e);
                WonderfulLogUtils.logi("发布广告出错", "发布广告出错：" + e.getMessage());
                dataCallback.onDataNotAvailable(OKHTTP_ERROR, null);
            }

            @Override
            public void onResponse(String response) {
                WonderfulLogUtils.logi("发布广告回执：", "发布广告回执：" + response.toString());
                try {
                    JSONObject object = new JSONObject(response);
                    WonderfulLogUtils.logi("releaseOrEditAd", "price  create  " + price);
                    if (object.optInt("code") == 0) {
                        dataCallback.onDataLoaded("发布成功");
                    } else {
                        dataCallback.onDataNotAvailable(object.getInt("code"), object.optString("message"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    dataCallback.onDataNotAvailable(JSON_ERROR, null);
                }
            }
        });
    }

    @Override
    public void uploadBase64Pic(String token, String base64Data, final DataCallback dataCallback) {
        WonderfulOkhttpUtils.post().url(UrlFactory.getUploadPicUrl()).addHeader("x-auth-token", token).addParams("base64Data", base64Data).build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {
                super.onError(request, e);
                WonderfulLogUtils.logi("上传图片出错", "上传图片出错：" + e.getMessage());
                dataCallback.onDataNotAvailable(OKHTTP_ERROR, null);
            }

            @Override
            public void onResponse(String response) {
                WonderfulLogUtils.logi("上传图片回执：", "上传图片回执：" + response.toString());
                try {
                    JSONObject object = new JSONObject(response);
                    if (object.optInt("code") == 0) {
                        dataCallback.onDataLoaded(object.optString("data"));
                    } else {
                        dataCallback.onDataNotAvailable(object.getInt("code"), object.optString("message"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    dataCallback.onDataNotAvailable(JSON_ERROR, null);
                }
            }
        });
    }

    @Override
    public void name(String token, String realName, String idCard, String idCardFront, String idCardBack, String handHeldIdCard, final DataCallback dataCallback) {
        WonderfulOkhttpUtils.post().url(UrlFactory.getNameUrl()).addHeader("x-auth-token", token)
                .addParams("realName", realName).addParams("idCard", idCard).addParams("idCardFront", idCardFront)
                .addParams("idCardBack", idCardBack).addParams("handHeldIdCard", handHeldIdCard).build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {
                super.onError(request, e);
                WonderfulLogUtils.logi("实名认证出错", "实名认证出错：" + e.getMessage());
                dataCallback.onDataNotAvailable(OKHTTP_ERROR, null);
            }

            @Override
            public void onResponse(String response) {
                WonderfulLogUtils.logi("实名认证回执：", "实名认证回执：" + response.toString());
                try {
                    JSONObject object = new JSONObject(response);
                    if (object.optInt("code") == 0) {
                        String msg = MyApplication.getApp().getString(R.string.submit_ok);
                        dataCallback.onDataLoaded(msg);
                    } else {
                        dataCallback.onDataNotAvailable(object.getInt("code"), object.optString("message"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    dataCallback.onDataNotAvailable(JSON_ERROR, null);
                }
            }
        });
    }

    @Override
    public void accountPwd(String token, String jyPassword, final DataCallback dataCallback) {
        WonderfulOkhttpUtils.post().url(UrlFactory.getAccountPwdUrl()).addHeader("x-auth-token", token)
                .addParams("jyPassword", jyPassword).build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {
                super.onError(request, e);
                WonderfulLogUtils.logi("设置资金密码出错", "设置资金密码出错：" + e.getMessage());
                dataCallback.onDataNotAvailable(OKHTTP_ERROR, null);
            }

            @Override
            public void onResponse(String response) {
                WonderfulLogUtils.logi("设置资金密码回执：", "设置资金密码回执：" + response.toString());
                try {
                    JSONObject object = new JSONObject(response);
                    if (object.optInt("code") == 0) {
                        dataCallback.onDataLoaded("设置成功");
                    } else {
                        dataCallback.onDataNotAvailable(object.getInt("code"), object.optString("message"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    dataCallback.onDataNotAvailable(JSON_ERROR, null);
                }
            }
        });
    }

    @Override
    public void allAds(String token, final DataCallback dataCallback) {
        WonderfulOkhttpUtils.post().url(UrlFactory.getAllAdsUrl()).addHeader("x-auth-token", token).addParams("pageSize", "50").build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {
                super.onError(request, e);
                WonderfulLogUtils.logi("获取我的所有广告出错", "获取我的所有广告出错：" + e.getMessage());
                dataCallback.onDataNotAvailable(OKHTTP_ERROR, null);
            }

            @Override
            public void onResponse(String response) {
                WonderfulLogUtils.logi("获取我的所有广告回执：", "获取我的所有广告回执：" + response.toString());
                try {
                    JSONObject object = new JSONObject(response);
                    if (object.optInt("code") == 0) {
                        List<Ads> objs = gson.fromJson(object.getJSONObject("data").getJSONArray("content").toString(), new TypeToken<List<Ads>>() {
                        }.getType());
                        dataCallback.onDataLoaded(objs);
                    } else {
                        dataCallback.onDataNotAvailable(object.getInt("code"), object.optString("message"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    dataCallback.onDataNotAvailable(JSON_ERROR, null);
                }
            }
        });
    }

    @Override
    public void release(String token, int id, final DataCallback dataCallback) {
        WonderfulOkhttpUtils.post().url(UrlFactory.getReleaseUrl())
                .addHeader("x-auth-token", token).addParams("id", id + "").build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {
                super.onError(request, e);
                WonderfulLogUtils.logi("上架广告出错", "上架广告出错：" + e.getMessage());
                dataCallback.onDataNotAvailable(OKHTTP_ERROR, null);
            }

            @Override
            public void onResponse(String response) {
                WonderfulLogUtils.logi("上架广告回执：", "上架广告回执：" + response.toString());
                try {
                    JSONObject object = new JSONObject(response);
                    if (object.optInt("code") == 0) {
                        dataCallback.onDataLoaded("上架成功");
                    } else {
                        dataCallback.onDataNotAvailable(object.getInt("code"), object.optString("message"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    dataCallback.onDataNotAvailable(JSON_ERROR, null);
                }
            }
        });
    }

    @Override
    public void deleteAds(String token, int id, final DataCallback dataCallback) {
        WonderfulOkhttpUtils.post().url(UrlFactory.getDeleteAdsUrl())
                .addHeader("x-auth-token", token).addParams("id", id + "").build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {
                super.onError(request, e);
                WonderfulLogUtils.logi("删除广告出错", "删除广告出错：" + e.getMessage());
                dataCallback.onDataNotAvailable(OKHTTP_ERROR, null);
            }

            @Override
            public void onResponse(String response) {
                WonderfulLogUtils.logi("删除广告回执：", "删除广告回执：" + response.toString());
                try {
                    JSONObject object = new JSONObject(response);
                    if (object.optInt("code") == 0) {
                        dataCallback.onDataLoaded("删除成功");
                    } else {
                        dataCallback.onDataNotAvailable(object.getInt("code"), object.optString("message"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    dataCallback.onDataNotAvailable(JSON_ERROR, null);
                }
            }
        });
    }

    @Override
    public void offShelf(String token, int id, final DataCallback dataCallback) {
        WonderfulOkhttpUtils.post().url(UrlFactory.getOffShelfUrl())
                .addHeader("x-auth-token", token).addParams("id", id + "").build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {
                super.onError(request, e);
                WonderfulLogUtils.logi("下架广告出错", "下架广告出错：" + e.getMessage());
                dataCallback.onDataNotAvailable(OKHTTP_ERROR, null);
            }

            @Override
            public void onResponse(String response) {
                WonderfulLogUtils.logi("下架广告回执：", "下架广告回执：" + response.toString());
                try {
                    JSONObject object = new JSONObject(response);
                    if (object.optInt("code") == 0) {
                        dataCallback.onDataLoaded("下架成功");
                    } else {
                        dataCallback.onDataNotAvailable(object.getInt("code"), object.optString("message"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    dataCallback.onDataNotAvailable(JSON_ERROR, null);
                }
            }
        });
    }

    @Override
    public void adDetail(String token, int id, final DataCallback dataCallback) {
        WonderfulOkhttpUtils.post().url(UrlFactory.getAdDetailUrl()).addHeader("x-auth-token", token).addParams("id", id + "").build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {
                super.onError(request, e);
                WonderfulLogUtils.logi("广告详情出错", "广告详情出错：" + e.getMessage());
                dataCallback.onDataNotAvailable(OKHTTP_ERROR, null);
            }

            @Override
            public void onResponse(String response) {
                WonderfulLogUtils.logi("广告详情回执：", "广告详情回执：" + response.toString());
                try {
                    JSONObject object = new JSONObject(response);
                    if (object.optInt("code") == 0) {
                        Ads obj = gson.fromJson(object.getJSONObject("data").toString(), Ads.class);
                        dataCallback.onDataLoaded(obj);
                    } else {
                        dataCallback.onDataNotAvailable(object.getInt("code"), object.optString("message"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    dataCallback.onDataNotAvailable(JSON_ERROR, null);
                }
            }
        });
    }

    @Override
    public void updateAd(String token, int id, String price, String advertiseType, String coinId, String minLimit, String maxLimit, int timeLimit, String countryZhName, String priceType, String premiseRate, String remark, String number, String pay, String jyPassword, String auto, String autoword, final DataCallback dataCallback) {
        WonderfulOkhttpUtils.post().url(UrlFactory.getUpdateAdUrl()).addHeader("x-auth-token", token).addParams("id", id + "")
                .addParams("price", price).addParams("advertiseType", advertiseType).addParams("coin.id", coinId).addParams("minLimit", minLimit).addParams("maxLimit", maxLimit)
                .addParams("timeLimit", timeLimit + "").addParams("country.ZhName", countryZhName).addParams("priceType", priceType).addParams("premiseRate", premiseRate).addParams("remark", remark)
                .addParams("number", number).addParams("pay[]", pay).addParams("jyPassword", jyPassword).addParams("auto", auto).addParams("autoword", autoword)
                .build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {
                super.onError(request, e);
                WonderfulLogUtils.logi("修改广告出错", "修改广告出错：" + e.getMessage());
                dataCallback.onDataNotAvailable(OKHTTP_ERROR, null);
            }

            @Override
            public void onResponse(String response) {
                WonderfulLogUtils.logi("修改广告回执：", "修改广告回执：" + response.toString());
                try {
                    JSONObject object = new JSONObject(response);
                    if (object.optInt("code") == 0) {
                        dataCallback.onDataLoaded("修改成功");
                    } else {
                        dataCallback.onDataNotAvailable(object.getInt("code"), object.optString("message"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    dataCallback.onDataNotAvailable(JSON_ERROR, null);
                }
            }
        });
    }

    @Override
    public void c2cInfo(int id, final DataCallback dataCallback) {
        WonderfulOkhttpUtils.post().url(UrlFactory.getC2CInfoUrl()).addParams("id", id + "").build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {
                super.onError(request, e);
                WonderfulLogUtils.logi("获取买入卖出信息出错", "获取买入卖出信息出错：" + e.getMessage());
                dataCallback.onDataNotAvailable(OKHTTP_ERROR, null);
            }

            @Override
            public void onResponse(String response) {
                WonderfulLogUtils.logi("获取买入卖出信息回执：", "获取买入卖出信息回执：" + response.toString());
                try {
                    JSONObject object = new JSONObject(response);
                    if (object.optInt("code") == 0) {
                        C2CExchangeInfo obj = gson.fromJson(object.getJSONObject("data").toString(), C2CExchangeInfo.class);
                        dataCallback.onDataLoaded(obj);
                    } else {
                        dataCallback.onDataNotAvailable(object.getInt("code"), object.optString("message"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    dataCallback.onDataNotAvailable(JSON_ERROR, null);
                }
            }
        });
    }

    @Override
    public void c2cBuy(String token, String id, String coinId, String price, String money, String amount, String remark, String mode, final DataCallback dataCallback) {
        WonderfulOkhttpUtils.post().url(UrlFactory.getC2CBuyUrl()).addHeader("x-auth-token", token)
                .addParams("id", id).addParams("coinId", coinId).addParams("price", price)
                .addParams("money", money).addParams("amount", amount).addParams("remark", remark)
                .addParams("mode", mode)
                .build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {
                super.onError(request, e);
                WonderfulLogUtils.logi("C2C买入出错", "C2C买入出错：" + e.getMessage());
                dataCallback.onDataNotAvailable(OKHTTP_ERROR, null);
            }

            @Override
            public void onResponse(String response) {
                WonderfulLogUtils.logi("C2C买入回执：", "C2C买入回执：" + response.toString());
                try {
                    JSONObject object = new JSONObject(response);
                    if (object.optInt("code") == 0) {
                        dataCallback.onDataLoaded("成功买入");
                    } else {
                        dataCallback.onDataNotAvailable(object.getInt("code"), object.optString("message"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    dataCallback.onDataNotAvailable(JSON_ERROR, null);
                }
            }
        });
    }

    @Override
    public void c2cSell(String token, String id, String coinId, String price, String money, String amount, String remark, String mode, final DataCallback dataCallback) {
        WonderfulOkhttpUtils.post().url(UrlFactory.getC2CSellUrl()).addHeader("x-auth-token", token)
                .addParams("id", id).addParams("coinId", coinId).addParams("price", price)
                .addParams("money", money).addParams("amount", amount).addParams("remark", remark)
                .addParams("mode", mode)
                .build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {
                super.onError(request, e);
                WonderfulLogUtils.logi("C2C卖出出错", "C2C卖出出错：" + e.getMessage());
                dataCallback.onDataNotAvailable(OKHTTP_ERROR, null);
            }

            @Override
            public void onResponse(String response) {
                WonderfulLogUtils.logi("C2C卖出回执：", "C2C卖出回执：" + response.toString());
                try {
                    JSONObject object = new JSONObject(response);
                    if (object.optInt("code") == 0) {
                        dataCallback.onDataLoaded("成功卖出");
                    } else {
                        dataCallback.onDataNotAvailable(object.getInt("code"), object.optString("message"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    dataCallback.onDataNotAvailable(JSON_ERROR, null);
                }
            }
        });
    }

    @Override
    public void myOrder(String token, int status, int pageNo, int pageSize, final DataCallback dataCallback) {
        WonderfulOkhttpUtils.post().url(UrlFactory.getMyOrderUrl()).addHeader("x-auth-token", token)
                .addParams("status", status + "").addParams("pageNo", pageNo + "").addParams("pageSize", pageSize + "").build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {
                super.onError(request, e);
                WonderfulLogUtils.logi("我的订单出错", "我的订单出错：" + e.getMessage());
                dataCallback.onDataNotAvailable(OKHTTP_ERROR, null);
            }

            @Override
            public void onResponse(String response) {
                WonderfulLogUtils.logi("我的订单回执：", "我的订单回执：" + response.toString());
                try {
                    JSONObject object = new JSONObject(response);
                    if (object.optInt("code") == 0) {
                        List<Order> objs = gson.fromJson(object.getJSONObject("data").getJSONArray("content").toString(), new TypeToken<List<Order>>() {
                        }.getType());
                        dataCallback.onDataLoaded(objs);
                    } else {
                        dataCallback.onDataNotAvailable(object.getInt("code"), object.optString("message"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    dataCallback.onDataNotAvailable(JSON_ERROR, null);
                }
            }
        });
    }

    @Override
    public void myWallet(String token, final DataCallback dataCallback) {
//        Log.i("miao",UrlFactory.getWalletUrl());
        WonderfulOkhttpUtils.post().url(UrlFactory.getWalletUrl()).addHeader("x-auth-token", token).build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {
                super.onError(request, e);
                WonderfulLogUtils.logi("获取所有钱包出错", "获取所有钱包出错：" + e.getMessage());
                dataCallback.onDataNotAvailable(OKHTTP_ERROR, null);
            }

            @Override
            public void onResponse(String response) {
                //WonderfulLogUtils.logi("获取所有钱包回执：", "获取所有钱包回执：" + response.toString());
                try {
                    JSONObject object = new JSONObject(response);
                    if (object.optInt("code") == 0) {
                        List<Coin> objs = gson.fromJson(object.getJSONArray("data").toString(), new TypeToken<List<Coin>>() {
                        }.getType());
                        dataCallback.onDataLoaded(objs);
                    } else {
                        dataCallback.onDataNotAvailable(object.getInt("code"), object.optString("message"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    dataCallback.onDataNotAvailable(JSON_ERROR, null);
                }
            }
        });
    }

    @Override
    public void myWallet_Constract(String token, final DataCallback dataCallback) {
        WonderfulOkhttpUtils.post().url(UrlFactory.getWalletUrl_Constract())
                .addHeader("x-auth-token", token)
                .build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {
                super.onError(request, e);
                WonderfulLogUtils.logi("获取所有钱包出错", "获取所有钱包出错：" + e.getMessage());
                dataCallback.onDataNotAvailable(OKHTTP_ERROR, null);
            }

            @Override
            public void onResponse(String response) {
                try {
//                    WonderfulLogUtils.logi("获取所有钱包", "获取所有钱包：" + response.toString());
                    JSONObject object = new JSONObject(response);
                    if (object.optInt("code") == 0) {
                        List<WalletConstract> objs = gson.fromJson(object.getJSONArray("data").toString(), new TypeToken<List<WalletConstract>>() {
                        }.getType());
                        dataCallback.onDataLoaded(objs);
                    } else {
                        dataCallback.onDataNotAvailable(object.getInt("code"), object.optString("message"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    dataCallback.onDataNotAvailable(JSON_ERROR, null);
                }
            }
        });
    }

    @Override
    public void RequesTrans(String token, String unit, String from, String to, String fromWalletId, String toWalletId, String amount, final DataCallback dataCallback) {
        WonderfulOkhttpUtils.post().url(UrlFactory.getTransUrl())
                .addHeader("x-auth-token", token)
                .addParams("unit", unit)
                .addParams("from", from)
                .addParams("to", to)
                .addParams("fromWalletId", fromWalletId)
                .addParams("toWalletId", toWalletId)
                .addParams("amount", amount)
                .build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {
                super.onError(request, e);
                WonderfulLogUtils.logi("资产划转出错", "资产划转出错：" + e.getMessage());
                dataCallback.onDataNotAvailable(OKHTTP_ERROR, null);
            }

            @Override
            public void onResponse(String response) {
                WonderfulLogUtils.logi("资产划转回执：", "资产划转回执：" + response.toString());
                try {
                    JSONObject object = new JSONObject(response);
                    if (object.optInt("code") == 0) {
                        dataCallback.onDataLoaded(object.optString("message"));
                    } else {
                        dataCallback.onDataNotAvailable(object.getInt("code"), object.optString("message"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    dataCallback.onDataNotAvailable(JSON_ERROR, null);
                }
            }
        });
    }

    @Override
    public void extractinfo(String token, final DataCallback dataCallback) {
        WonderfulOkhttpUtils.post().url(UrlFactory.getExtractinfoUrl()).addHeader("x-auth-token", token).build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {
                super.onError(request, e);
                WonderfulLogUtils.logi("获取提币信息出错", "获取提币信息出错：" + e.getMessage());
                dataCallback.onDataNotAvailable(OKHTTP_ERROR, null);
            }

            @Override
            public void onResponse(String response) {
                WonderfulLogUtils.logi("获取提币信息回执：", "获取提币信息回执：" + response.toString());
                try {
                    JSONObject object = new JSONObject(response);
                    if (object.optInt("code") == 0) {
                        List<ExtractInfo> objs = gson.fromJson(object.getJSONArray("data").toString(), new TypeToken<List<ExtractInfo>>() {
                        }.getType());
                        dataCallback.onDataLoaded(objs);
                    } else {
                        dataCallback.onDataNotAvailable(object.getInt("code"), object.optString("message"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    dataCallback.onDataNotAvailable(JSON_ERROR, null);
                }
            }
        });
    }

    @Override
    public void extract(String token, String unit, String amount, String fee, String remark, String jyPassword, String address, String code, final DataCallback dataCallback) {
        WonderfulOkhttpUtils.post().url(UrlFactory.getTIBi()).addHeader("x-auth-token", token).addParams("jyPassword", jyPassword)
                .addParams("unit", unit).addParams("amount", amount).addParams("fee", fee).addParams("address", address).addParams("remark", remark).addParams("code", code).build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {
                super.onError(request, e);
                WonderfulLogUtils.logi("提币出错", "提币出错：" + e.getMessage());
                dataCallback.onDataNotAvailable(OKHTTP_ERROR, null);
            }

            @Override
            public void onResponse(String response) {
                WonderfulLogUtils.logi("提币回执：", "提币回执：" + response.toString());
                try {
                    JSONObject object = new JSONObject(response);
                    if (object.optInt("code") == 0) {
                        dataCallback.onDataLoaded("提币成功");
                    } else {
                        dataCallback.onDataNotAvailable(object.getInt("code"), object.optString("message"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    dataCallback.onDataNotAvailable(JSON_ERROR, null);
                }
            }
        });
    }

    @Override
    public void allTransaction(String token, int page, int limit, int memberId, String startTime, String endTime, String symbol, String type, final DataCallback dataCallback) {
        WonderfulOkhttpUtils.post().url(UrlFactory.getAllTransactionUrl2()).addHeader("x-auth-token", token)
                .addParams("pageNo", page + "")
                .addParams("pageSize", limit + "")
                .addParams("memberId", memberId + "")
                .addParams("startTime", startTime + "")
                .addParams("endTime", endTime + "")
                .addParams("symbol", symbol + "")
                .addParams("type", type + "")
                .build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {
                super.onError(request, e);
                WonderfulLogUtils.logi("获取资产流水出错", "获取资产流水出错：" + e.getMessage());
                dataCallback.onDataNotAvailable(OKHTTP_ERROR, null);
            }

            @Override
            public void onResponse(String response) {
                WonderfulLogUtils.logi("获取资产流水回执：", "获取资产流水回执：" + response.toString());
                JSONObject object = null;
                try {
                    object = new JSONObject(response);
                    if (0 == object.getInt("code")) {
                        WalletDetailNew objs = gson.fromJson(object.get("data").toString(), WalletDetailNew.class);
                        dataCallback.onDataLoaded(objs);
                    } else {
                        dataCallback.onDataNotAvailable(object.getInt("code"), object.getString("message"));
                    }
                } catch (JSONException e) {
                    dataCallback.onDataNotAvailable(JSON_ERROR, null);
                }
            }
        });
    }

    @Override
    public void safeSetting(String token, final DataCallback dataCallback) {
        WonderfulOkhttpUtils.post().url(UrlFactory.getSafeSettingUrl()).addHeader("x-auth-token", token).build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {
                super.onError(request, e);
                WonderfulLogUtils.logi("账户设置出错", "账户设置出错：" + e.getMessage());
                dataCallback.onDataNotAvailable(OKHTTP_ERROR, null);
            }

            @Override
            public void onResponse(String response) {
                //WonderfulLogUtils.logi("账户设置回执：", "账户设置回执：" + response.toString());
                try {
                    JSONObject object = new JSONObject(response);
                    if (object.optInt("code") == 0) {
                        SafeSetting obj = gson.fromJson(object.getJSONObject("data").toString(), SafeSetting.class);
                        dataCallback.onDataLoaded(obj);
                    } else {
                        dataCallback.onDataNotAvailable(object.getInt("code"), object.optString("message") + "请重新登录");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    dataCallback.onDataNotAvailable(JSON_ERROR, null);
                }
            }
        });
    }

    @Override
    public void avatar(String token, String url, final DataCallback dataCallback) {
        WonderfulOkhttpUtils.post().url(UrlFactory.getAvatarUrl()).addHeader("x-auth-token", token).addParams("url", url).build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {
                super.onError(request, e);
                WonderfulLogUtils.logi("设置头像出错", "设置头像出错：" + e.getMessage());
                dataCallback.onDataNotAvailable(OKHTTP_ERROR, null);
            }

            @Override
            public void onResponse(String response) {
                WonderfulLogUtils.logi("设置头像回执：", "设置头像回执：" + response.toString());
                try {
                    JSONObject object = new JSONObject(response);
                    if (object.optInt("code") == 0) {
                        dataCallback.onDataLoaded("设置成功");
                    } else {
                        dataCallback.onDataNotAvailable(object.getInt("code"), object.optString("message"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    dataCallback.onDataNotAvailable(JSON_ERROR, null);
                }
            }
        });
    }

    @Override
    public void bindPhone(String token, String phone, String code, String password, final DataCallback dataCallback) {
        WonderfulOkhttpUtils.post().url(UrlFactory.getBindPhoneUrl()).addHeader("x-auth-token", token)
                .addParams("phone", phone).addParams("code", code).addParams("password", password).build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {
                super.onError(request, e);
                WonderfulLogUtils.logi("绑定手机号出错", "绑定手机号出错：" + e.getMessage());
                dataCallback.onDataNotAvailable(OKHTTP_ERROR, null);
            }

            @Override
            public void onResponse(String response) {
                WonderfulLogUtils.logi("绑定手机号回执：", "绑定手机号回执：" + response.toString());
                try {
                    JSONObject object = new JSONObject(response);
                    if (object.optInt("code") == 0) {
                        dataCallback.onDataLoaded("成功绑定");
                    } else {
                        dataCallback.onDataNotAvailable(object.getInt("code"), object.optString("message"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    dataCallback.onDataNotAvailable(JSON_ERROR, null);
                }
            }
        });
    }

    @Override
    public void sendCode(String token, String phone, final DataCallback dataCallback) {
        WonderfulOkhttpUtils.post().url(UrlFactory.getSendCodeUrl()).addHeader("x-auth-token", token).addParams("phone", phone).build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {
                super.onError(request, e);
                WonderfulLogUtils.logi("发送绑定手机的验证码出错", "发送绑定手机的验证码出错：" + e.getMessage());
                dataCallback.onDataNotAvailable(OKHTTP_ERROR, null);
            }

            @Override
            public void onResponse(String response) {
                WonderfulLogUtils.logi("发送绑定手机的验证码回执：", "发送绑定手机的验证码回执：" + response.toString());
                try {
                    JSONObject object = new JSONObject(response);
                    if (object.optInt("code") == 0) {
                        String msg = MyApplication.getApp().getString(R.string.tv_send_ok);
                        dataCallback.onDataLoaded(msg);
                    } else {
                        dataCallback.onDataNotAvailable(object.getInt("code"), object.optString("message"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    dataCallback.onDataNotAvailable(JSON_ERROR, null);
                }
            }
        });
    }

    @Override
    public void bindEmail(String token, String email, String code, String password, final DataCallback dataCallback) {
        WonderfulOkhttpUtils.post().url(UrlFactory.getBindEmailUrl()).addHeader("x-auth-token", token)
                .addParams("email", email).addParams("code", code).addParams("password", password).build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {
                super.onError(request, e);
                WonderfulLogUtils.logi("绑定邮箱出错", "绑定邮箱出错：" + e.getMessage());
                dataCallback.onDataNotAvailable(OKHTTP_ERROR, null);
            }

            @Override
            public void onResponse(String response) {
                WonderfulLogUtils.logi("绑定邮箱回执：", "绑定邮箱回执：" + response.toString());
                try {
                    JSONObject object = new JSONObject(response);
                    if (object.optInt("code") == 0) {
                        dataCallback.onDataLoaded("绑定成功");
                    } else {
                        dataCallback.onDataNotAvailable(object.getInt("code"), object.optString("message"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    dataCallback.onDataNotAvailable(JSON_ERROR, null);
                }
            }
        });
    }

    @Override
    public void sendEmailCode(String token, String email, final DataCallback dataCallback) {
//        Log.i("miao","x-auth-token : "+token);
//        Log.i("miao","email : "+email);
        WonderfulOkhttpUtils.post().url(UrlFactory.getSendEmailCodeUrl()).addHeader("x-auth-token", token).addParams("email", email).build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {
                super.onError(request, e);
                WonderfulLogUtils.logi("发送绑定邮箱的验证码出错", "发送绑定邮箱的验证码出错：" + e.getMessage());
                dataCallback.onDataNotAvailable(OKHTTP_ERROR, null);
            }

            @Override
            public void onResponse(String response) {
                WonderfulLogUtils.logi("发送绑定邮箱的验证码回执：", "发送绑定邮箱的验证码回执：" + response.toString());
                try {
                    JSONObject object = new JSONObject(response);
                    if (object.optInt("code") == 0) {
                        String msg = MyApplication.getApp().getString(R.string.tv_send_ok);
                        dataCallback.onDataLoaded(msg);
                    } else {
                        dataCallback.onDataNotAvailable(object.getInt("code"), object.optString("message"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    dataCallback.onDataNotAvailable(JSON_ERROR, null);
                }
            }
        });
    }

    @Override
    public void sendEditLoginPwdCode(String token, final DataCallback dataCallback) {
        WonderfulOkhttpUtils.post().url(UrlFactory.getEditLoginPwdUrl()).addHeader("x-auth-token", token).build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {
                super.onError(request, e);
                WonderfulLogUtils.logi("发送修改登录密码出错", "发送修改登录密码出错：" + e.getMessage());
                dataCallback.onDataNotAvailable(OKHTTP_ERROR, null);
            }

            @Override
            public void onResponse(String response) {
                WonderfulLogUtils.logi("发送修改登录密码回执：", "发送修改登录密码回执：" + response.toString());
                try {
                    JSONObject object = new JSONObject(response);
                    if (object.optInt("code") == 0) {
                        String msg = MyApplication.getApp().getString(R.string.tv_send_ok);
                        dataCallback.onDataLoaded(msg);
                    } else {
                        dataCallback.onDataNotAvailable(object.getInt("code"), object.optString("message"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    dataCallback.onDataNotAvailable(JSON_ERROR, null);
                }
            }
        });
    }

    @Override
    public void editPwd(String token, String oldPassword, String newPassword, String code, final DataCallback dataCallback) {
        WonderfulOkhttpUtils.post().url(UrlFactory.getEditPwdUrl()).addHeader("x-auth-token", token)
                .addParams("oldPassword", oldPassword).addParams("newPassword", newPassword).addParams("code", code).build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {
                super.onError(request, e);
                WonderfulLogUtils.logi("修改登录密码出错", "修改登录密码出错：" + e.getMessage());
                dataCallback.onDataNotAvailable(OKHTTP_ERROR, null);
            }

            @Override
            public void onResponse(String response) {
                WonderfulLogUtils.logi("修改登录密码回执：", "修改登录密码回执：" + response.toString());
                try {
                    JSONObject object = new JSONObject(response);
                    if (object.optInt("code") == 0) {
                        dataCallback.onDataLoaded("修改成功");
                    } else {
                        dataCallback.onDataNotAvailable(object.getInt("code"), object.optString("message"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    dataCallback.onDataNotAvailable(JSON_ERROR, null);
                }
            }
        });
    }

    /**
     * 盘口查询
     */
    @Override
    public void plate(String symbol, final DataCallback dataCallback) {
        WonderfulOkhttpUtils.post().url(UrlFactory.getPlateUrl()).addParams("symbol", symbol).build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {
                super.onError(request, e);
                WonderfulLogUtils.logi("盘口信息出错", "盘口信息出错：" + e.getMessage());
                dataCallback.onDataNotAvailable(OKHTTP_ERROR, null);
            }

            @Override
            public void onResponse(String response) {
                WonderfulLogUtils.logi("盘口信息回执：", "盘口信息回执：" + response.toString());
                try {
                    JSONObject object = new JSONObject(response);
                    dataCallback.onDataNotAvailable(object.getInt("code"), object.getString("message"));
                } catch (JSONException e) {
                    Plate obj = gson.fromJson(response.toString(), Plate.class);
                    dataCallback.onDataLoaded(obj);
                }
            }
        });
    }

    @Override
    public void entrust(String token, int pageSize, int pageNo, String symbol, final DataCallback dataCallback) {
        WonderfulOkhttpUtils.post().url(UrlFactory.getEntrustUrl()).addHeader("x-auth-token", token)
                .addParams("pageSize", pageSize + "").addParams("pageNo", pageNo + "").addParams("symbol", symbol + "").build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {
                super.onError(request, e);
                WonderfulLogUtils.logi("委托出错", "委托出错：" + e.getMessage());
                dataCallback.onDataNotAvailable(OKHTTP_ERROR, null);
            }

            @Override
            public void onResponse(String response) {
                WonderfulLogUtils.logi("委托回执：", "委托回执：" + response.toString());
                JSONObject object = null;
                try {
                    object = new JSONObject(response);
                    dataCallback.onDataNotAvailable(object.getInt("code"), object.optString("message"));
                } catch (JSONException e) {
                    try {
                        List<EntrustHistory> objs = gson.fromJson(object.getJSONArray("content").toString(), new TypeToken<List<EntrustHistory>>() {
                        }.getType());
                        dataCallback.onDataLoaded(objs);
                    } catch (JSONException e1) {
                        e1.printStackTrace();
//                        dataCallback.onDataNotAvailable(GlobalConstant.TOKEN_DISABLE1, object.optString("message"));
                    }
                }
            }
        });

    }

    @Override
    public void cancleEntrust(String token, String orderId, final DataCallback dataCallback) {
        WonderfulOkhttpUtils.post().url(UrlFactory.getCancleEntrustUrl() + orderId).addHeader("x-auth-token", token).build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {
                super.onError(request, e);
                WonderfulLogUtils.logi("取消委托出错", "取消委托出错：" + e.getMessage());
                dataCallback.onDataNotAvailable(OKHTTP_ERROR, null);
            }

            @Override
            public void onResponse(String response) {
                WonderfulLogUtils.logi("取消委托回执：", "取消委托回执：" + response.toString());
                try {
                    JSONObject object = new JSONObject(response);
                    if (object.optInt("code") == 0) {
                        dataCallback.onDataLoaded("取消成功");
                    } else {
                        dataCallback.onDataNotAvailable(object.getInt("code"), object.optString("message"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    dataCallback.onDataNotAvailable(JSON_ERROR, null);
                }
            }
        });
    }

    @Override
    public void phoneForgotCode(String account, String challenge, String validate, String seccode, final DataCallback dataCallback) {
        WonderfulOkhttpUtils.post().url(UrlFactory.getPhoneForgotPwdCodeUrl()).addParams("account", account).addParams("challenge", challenge)
                .addParams("validate", validate).addParams("seccode", seccode).build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {
                super.onError(request, e);
                WonderfulLogUtils.logi("忘记密码手机验证码出错", "忘记密码手机验证码出错：" + e.getMessage());
                dataCallback.onDataNotAvailable(OKHTTP_ERROR, null);
            }

            @Override
            public void onResponse(String response) {
                WonderfulLogUtils.logi("忘记密码手机验证码回执：", "忘记密码手机验证码回执：" + response.toString());

                try {
                    JSONObject object = new JSONObject(response);
                    if (object.optInt("code") == 0) {
                        String msg = MyApplication.getApp().getString(R.string.tv_send_ok);
                        dataCallback.onDataLoaded(msg);
                    } else {
                        dataCallback.onDataNotAvailable(object.getInt("code"), object.optString("message"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    dataCallback.onDataNotAvailable(JSON_ERROR, null);
                }
            }
        });
    }

    @Override
    public void forgotPwd(String account, String code, String mode, String password, final DataCallback dataCallback) {
        WonderfulOkhttpUtils.post().url(UrlFactory.getForgotPwdUrl())
                .addParams("account", account).addParams("password", password).addParams("code", code).addParams("mode", mode).build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {
                super.onError(request, e);
                WonderfulLogUtils.logi("忘记密码手机重置出错", "忘记密码手机重置出错：" + e.getMessage());
                dataCallback.onDataNotAvailable(OKHTTP_ERROR, null);
            }

            @Override
            public void onResponse(String response) {
                WonderfulLogUtils.logi("忘记密码手机重置回执：", "忘记密码手机重置回执：" + response.toString());
                try {
                    JSONObject object = new JSONObject(response);
                    if (object.optInt("code") == 0) {
                        String msg = MyApplication.getApp().getString(R.string.set_up_success);
                        dataCallback.onDataLoaded(msg);
                    } else {
                        dataCallback.onDataNotAvailable(object.getInt("code"), object.optString("message"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    dataCallback.onDataNotAvailable(JSON_ERROR, null);
                }
            }
        });
    }

    @Override
    public void emailForgotCode(String account, String challenge, String validate, String seccode, final DataCallback dataCallback) {
        WonderfulOkhttpUtils.post().url(UrlFactory.getEmailForgotPwdCodeUrl()).addParams("account", account).addParams("challenge", challenge)
                .addParams("validate", validate).addParams("seccode", seccode).build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {
                super.onError(request, e);
                WonderfulLogUtils.logi("忘记密码邮箱验证码出错", "忘记密码邮箱验证码出错：" + e.getMessage());
                dataCallback.onDataNotAvailable(OKHTTP_ERROR, null);
            }

            @Override
            public void onResponse(String response) {
                WonderfulLogUtils.logi("忘记密码邮箱验证码回执：", "忘记密码邮箱验证码回执：" + response.toString());
                try {
                    JSONObject object = new JSONObject(response);
                    if (object.optInt("code") == 0) {
                        String msg = MyApplication.getApp().getString(R.string.tv_send_ok);
                        dataCallback.onDataLoaded(msg);
                    } else {
                        dataCallback.onDataNotAvailable(object.getInt("code"), object.optString("message"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    dataCallback.onDataNotAvailable(JSON_ERROR, null);
                }
            }
        });
    }

    @Override
    public void captch(final DataCallback dataCallback) {
        WonderfulOkhttpUtils.post().url(UrlFactory.getCaptchaUrl()).build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {
                super.onError(request, e);
                WonderfulLogUtils.logi("api1出错", "api1出错：" + e.getMessage());
                dataCallback.onDataNotAvailable(OKHTTP_ERROR, null);
            }

            @Override
            public void onResponse(String response) {
                WonderfulLogUtils.logi("api1回执：", "api1回执：" + response.toString());
                try {
                    JSONObject object = new JSONObject(response);
                    dataCallback.onDataLoaded(object);
                } catch (JSONException e) {
                    e.printStackTrace();
                    dataCallback.onDataNotAvailable(JSON_ERROR, null);
                }
            }
        });
    }

    @Override
    public void sendChangePhoneCode(String token, final DataCallback dataCallback) {
        WonderfulOkhttpUtils.post().url(UrlFactory.getSendChangePhoneCodeUrl()).addHeader("x-auth-token", token).build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {
                super.onError(request, e);
                WonderfulLogUtils.logi("更改绑定手机号验证码出错", "更改绑定手机号验证码出错：" + e.getMessage());
                dataCallback.onDataNotAvailable(OKHTTP_ERROR, null);
            }

            @Override
            public void onResponse(String response) {
                WonderfulLogUtils.logi("更改绑定手机号验证码回执：", "更改绑定手机号验证码回执：" + response.toString());
                try {
                    JSONObject object = new JSONObject(response);
                    if (object.optInt("code") == 0) {
                        String msg = MyApplication.getApp().getString(R.string.tv_send_ok);
                        dataCallback.onDataLoaded(msg);
                    } else {
                        dataCallback.onDataNotAvailable(object.getInt("code"), object.optString("message"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    dataCallback.onDataNotAvailable(JSON_ERROR, null);
                }
            }
        });
    }

    @Override
    public void changePhone(String token, String password, String phone, String code, final DataCallback dataCallback) {
        WonderfulOkhttpUtils.post().url(UrlFactory.getChangePhoneUrl())
                .addHeader("x-auth-token", token).addParams("password", password).addParams("phone", phone).addParams("code", code).build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {
                super.onError(request, e);
                WonderfulLogUtils.logi("更改绑定手机号出错", "更改绑定手机号出错：" + e.getMessage());
                dataCallback.onDataNotAvailable(OKHTTP_ERROR, null);
            }

            @Override
            public void onResponse(String response) {
                WonderfulLogUtils.logi("更改绑定手机号回执：", "更改绑定手机号回执：" + response.toString());
                try {
                    JSONObject object = new JSONObject(response);
                    if (object.optInt("code") == 0) {
                        dataCallback.onDataLoaded("修改成功");
                    } else {
                        dataCallback.onDataNotAvailable(object.getInt("code"), object.optString("message"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    dataCallback.onDataNotAvailable(JSON_ERROR, null);
                }
            }
        });
    }

    @Override
    public void message(int pageNo, int pageSize, final DataCallback dataCallback) {
        WonderfulOkhttpUtils.post().url(UrlFactory.getMessageUrl())
                .addParams("pageNo", pageNo + "").addParams("pageSize", pageSize + "").build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {
                super.onError(request, e);
                WonderfulLogUtils.logi("获取公告出错", "获取公告出错：" + e.getMessage());
                dataCallback.onDataNotAvailable(OKHTTP_ERROR, null);
            }

            @Override
            public void onResponse(String response) {
                WonderfulLogUtils.logi("获取公告回执：", "获取公告回执：" + response.toString());
                try {
                    JSONObject object = new JSONObject(response);
                    if (object.optInt("code") == 0) {
                        List<Message> objs = gson.fromJson(object.getJSONObject("data").getJSONArray("content").toString(), new TypeToken<List<Message>>() {
                        }.getType());
                        dataCallback.onDataLoaded(objs);
                    } else {
                        dataCallback.onDataNotAvailable(object.getInt("code"), object.optString("message"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    dataCallback.onDataNotAvailable(JSON_ERROR, null);
                }
            }
        });
    }

    @Override
    public void messageDetail(String id, final DataCallback dataCallback) {
        WonderfulOkhttpUtils.get().url(UrlFactory.getMessageDetailUrl() + id).build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {
                super.onError(request, e);
                WonderfulLogUtils.logi("公告详情出错", "公告详情出错：" + e.getMessage());
                dataCallback.onDataNotAvailable(OKHTTP_ERROR, null);
            }

            @Override
            public void onResponse(String response) {
                WonderfulLogUtils.logi("公告详情回执：", "公告详情回执：" + response.toString());
                try {
                    JSONObject object = new JSONObject(response);
                    if (object.optInt("code") == 0) {
                        Message obj = gson.fromJson(object.getJSONObject("data").toString(), Message.class);
                        dataCallback.onDataLoaded(obj);
                    } else {
                        dataCallback.onDataNotAvailable(object.getInt("code"), object.optString("message"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    dataCallback.onDataNotAvailable(JSON_ERROR, null);
                }
            }
        });
    }

    @Override
    public void remark(String token, String remark, final DataCallback dataCallback) {
        WonderfulOkhttpUtils.post().url(UrlFactory.getRemarkUrl()).addHeader("x-auth-token", token).addParams("remark", remark).build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {
                super.onError(request, e);
                WonderfulLogUtils.logi("意见反馈出错", "意见反馈出错：" + e.getMessage());
                dataCallback.onDataNotAvailable(OKHTTP_ERROR, null);
            }

            @Override
            public void onResponse(String response) {
                WonderfulLogUtils.logi("意见反馈回执：", "意见反馈回执：" + response.toString());
                try {
                    JSONObject object = new JSONObject(response);
                    if (object.optInt("code") == 0) {
                        String msg = MyApplication.getApp().getString(R.string.submit_ok);
                        dataCallback.onDataLoaded(msg);
                    } else {
                        dataCallback.onDataNotAvailable(object.getInt("code"), object.optString("message"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    dataCallback.onDataNotAvailable(JSON_ERROR, null);
                }
            }
        });
    }

    @Override
    public void appInfo(final DataCallback dataCallback) {
        WonderfulOkhttpUtils.post().url(UrlFactory.getAppInfoUrl()).build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {
                super.onError(request, e);
                WonderfulLogUtils.logi("关于我们出错", "关于我们出错：" + e.getMessage());
                dataCallback.onDataNotAvailable(OKHTTP_ERROR, null);
            }

            @Override
            public void onResponse(String response) {
                WonderfulLogUtils.logi("关于我们回执：", "关于我们回执：" + response.toString());
                try {
                    JSONObject object = new JSONObject(response);
                    if (object.optInt("code") == 0) {
                        AppInfo obj = gson.fromJson(object.getJSONObject("data").toString(), AppInfo.class);
                        dataCallback.onDataLoaded(obj);
                    } else {
                        dataCallback.onDataNotAvailable(object.getInt("code"), object.optString("message"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    dataCallback.onDataNotAvailable(JSON_ERROR, null);
                }
            }
        });
    }

    @Override
    public void banners(String sysAdvertiseLocation, final DataCallback dataCallback) {
        String lang = SharedPreferenceInstance.getInstance().getLanguageRegion();
        WonderfulOkhttpUtils.post().url(UrlFactory.getBannersUrl()).addParams("sysAdvertiseLocation", sysAdvertiseLocation).addParams("lang", lang).build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {
                super.onError(request, e);
                LogUtils.e("chenxi", "首页轮播出错：" + e.getMessage());
                System.out.println("首页轮播出错：" + e.getMessage());
                WonderfulLogUtils.logi("首页轮播出错", "首页轮播出错：" + e.getMessage());
                dataCallback.onDataNotAvailable(OKHTTP_ERROR, null);
            }

            @Override
            public void onResponse(String response) {
                //WonderfulLogUtils.logi("首页轮播回执：", "首页轮播回执：" + response.toString());
                LogUtils.e("chenxi", "首页轮播回执：" + response.toString());
                try {
                    JSONObject object = new JSONObject(response);
                    if (object.optInt("code") == 0) {
                        List<BannerEntity> objs = gson.fromJson(object.getJSONArray("data").toString(), new TypeToken<List<BannerEntity>>() {
                        }.getType());
                        dataCallback.onDataLoaded(objs);
                    } else {
                        dataCallback.onDataNotAvailable(object.getInt("code"), object.optString("message"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    dataCallback.onDataNotAvailable(JSON_ERROR, null);
                }
            }
        });
    }

    @Override
    public void orderDetail(String token, String orderSn, final DataCallback dataCallback) {
        WonderfulOkhttpUtils.post().url(UrlFactory.getOrderDetailUrl()).addHeader("x-auth-token", token).addParams("orderSn", orderSn).build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {
                super.onError(request, e);
                WonderfulLogUtils.logi("订单详情出错", "订单详情出错：" + e.getMessage());
                dataCallback.onDataNotAvailable(OKHTTP_ERROR, null);
            }

            @Override
            public void onResponse(String response) {
                WonderfulLogUtils.logi("订单详情回执：", "订单详情回执：" + response.toString());
                try {
                    JSONObject object = new JSONObject(response);
                    if (object.optInt("code") == 0) {
                        OrderDetial obj = gson.fromJson(object.getJSONObject("data").toString(), OrderDetial.class);
                        dataCallback.onDataLoaded(obj);
                    } else {
                        dataCallback.onDataNotAvailable(object.getInt("code"), object.optString("message"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    dataCallback.onDataNotAvailable(JSON_ERROR, null);
                }
            }
        });
    }

    @Override
    public void cancle(String token, String orderSn, final DataCallback dataCallback) {
        WonderfulOkhttpUtils.post().url(UrlFactory.getCancleUrl()).addHeader("x-auth-token", token).addParams("orderSn", orderSn).build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {
                super.onError(request, e);
                WonderfulLogUtils.logi("取消订单出错", "取消订单出错：" + e.getMessage());
                dataCallback.onDataNotAvailable(OKHTTP_ERROR, null);
            }

            @Override
            public void onResponse(String response) {
                WonderfulLogUtils.logi("取消订单回执：", "取消订单回执：" + response.toString());
                try {
                    JSONObject object = new JSONObject(response);
                    if (object.optInt("code") == 0) {
                        dataCallback.onDataLoaded("取消成功");
                    } else {
                        dataCallback.onDataNotAvailable(object.getInt("code"), object.optString("message"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    dataCallback.onDataNotAvailable(JSON_ERROR, null);
                }
            }
        });
    }

    @Override
    public void payDone(String token, String orderSn, final DataCallback dataCallback) {
        WonderfulOkhttpUtils.post().url(UrlFactory.getpayDoneUrl()).addHeader("x-auth-token", token)
                .addParams("orderSn", orderSn).build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {
                super.onError(request, e);
                WonderfulLogUtils.logi("确认付款完成出错", "确认付款完成出错：" + e.getMessage());
                dataCallback.onDataNotAvailable(OKHTTP_ERROR, null);
            }

            @Override
            public void onResponse(String response) {
                WonderfulLogUtils.logi("确认付款完成回执：", "确认付款完成回执：" + response.toString());
                try {
                    JSONObject object = new JSONObject(response);
                    if (object.optInt("code") == 0) {
                        dataCallback.onDataLoaded("成功");
                    } else {
                        dataCallback.onDataNotAvailable(object.getInt("code"), object.optString("message"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    dataCallback.onDataNotAvailable(JSON_ERROR, null);
                }
            }
        });
    }

    @Override
    public void releaseOrder(String token, String orderSn, String jyPassword, final DataCallback dataCallback) {
        WonderfulOkhttpUtils.post().url(UrlFactory.getReleaseOrderUrl())
                .addHeader("x-auth-token", token).addParams("orderSn", orderSn).addParams("jyPassword", jyPassword).build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {
                super.onError(request, e);
                WonderfulLogUtils.logi("放行订单出错", "放行订单出错：" + e.getMessage());
                dataCallback.onDataNotAvailable(OKHTTP_ERROR, null);
            }

            @Override
            public void onResponse(String response) {
                WonderfulLogUtils.logi("放行订单回执：", "放行订单回执：" + response.toString());
                try {
                    JSONObject object = new JSONObject(response);
                    if (object.optInt("code") == 0) {
                        dataCallback.onDataLoaded("成功");
                    } else {
                        dataCallback.onDataNotAvailable(object.getInt("code"), object.optString("message"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    dataCallback.onDataNotAvailable(JSON_ERROR, null);
                }
            }
        });
    }

    @Override
    public void appeal(String token, String remark, String orderSn, final DataCallback dataCallback) {
        WonderfulOkhttpUtils.post().url(UrlFactory.getAppealUrl()).addHeader("x-auth-token", token)
                .addParams("remark", remark).addParams("orderSn", orderSn).build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {
                super.onError(request, e);
                WonderfulLogUtils.logi("订单申诉出错", "订单申诉出错：" + e.getMessage());
                dataCallback.onDataNotAvailable(OKHTTP_ERROR, null);
            }

            @Override
            public void onResponse(String response) {
                WonderfulLogUtils.logi("订单申诉回执：", "订单申诉回执：" + response.toString());
                try {
                    JSONObject object = new JSONObject(response);
                    if (object.optInt("code") == 0) {
                        dataCallback.onDataLoaded(object.optString("message"));
                    } else {
                        dataCallback.onDataNotAvailable(object.getInt("code"), object.optString("message"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    dataCallback.onDataNotAvailable(JSON_ERROR, null);
                }
            }
        });
    }

    @Override
    public void editAccountPed(String token, String newPassword, String oldPassword, final DataCallback dataCallback) {
        WonderfulOkhttpUtils.post().url(UrlFactory.getEditAccountPwdUrl())
                .addHeader("x-auth-token", token).addParams("newPassword", newPassword).addParams("oldPassword", oldPassword).build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {
                super.onError(request, e);
                WonderfulLogUtils.logi("修改账户密码出错", "修改账户密码出错：" + e.getMessage());
                dataCallback.onDataNotAvailable(OKHTTP_ERROR, null);
            }

            @Override
            public void onResponse(String response) {
                WonderfulLogUtils.logi("修改账户密码回执：", "修改账户密码回执：" + response.toString());
                try {
                    JSONObject object = new JSONObject(response);
                    if (object.optInt("code") == 0) {
                        dataCallback.onDataLoaded("修改成功");
                    } else {
                        dataCallback.onDataNotAvailable(object.getInt("code"), object.optString("message"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    dataCallback.onDataNotAvailable(JSON_ERROR, null);
                }
            }
        });
    }

    @Override
    public void resetAccountPwd(String token, String newPassword, String code, final DataCallback dataCallback) {
        WonderfulOkhttpUtils.post().url(UrlFactory.getResetAccountPwdUrl()).addHeader("x-auth-token", token)
                .addParams("newPassword", newPassword).addParams("code", code).build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {
                super.onError(request, e);
                WonderfulLogUtils.logi("重置密码出错", "重置密码出错：" + e.getMessage());
                dataCallback.onDataNotAvailable(OKHTTP_ERROR, null);
            }

            @Override
            public void onResponse(String response) {
                WonderfulLogUtils.logi("重置密码回执：", "重置密码回执：" + response.toString());
                try {
                    JSONObject object = new JSONObject(response);
                    if (object.optInt("code") == 0) {
                        dataCallback.onDataLoaded("重置成功");
                    } else {
                        dataCallback.onDataNotAvailable(object.getInt("code"), object.optString("message"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    dataCallback.onDataNotAvailable(JSON_ERROR, null);
                }
            }
        });
    }

    @Override
    public void resetAccountPwdCode(String token, final DataCallback dataCallback) {
        WonderfulOkhttpUtils.post().url(UrlFactory.getResetAccountPwdCodeUrl()).addHeader("x-auth-token", token).build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {
                super.onError(request, e);
                WonderfulLogUtils.logi("重置密码的验证码出错", "重置密码的验证码出错：" + e.getMessage());
                dataCallback.onDataNotAvailable(OKHTTP_ERROR, null);
            }

            @Override
            public void onResponse(String response) {
                WonderfulLogUtils.logi("重置密码的验证码回执：", "重置密码的验证码回执：" + response.toString());
                try {
                    JSONObject object = new JSONObject(response);
                    if (object.optInt("code") == 0) {
                        String msg = MyApplication.getApp().getString(R.string.tv_send_ok);
                        dataCallback.onDataLoaded(msg);
                    } else {
                        dataCallback.onDataNotAvailable(object.getInt("code"), object.optString("message"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    dataCallback.onDataNotAvailable(JSON_ERROR, null);
                }
            }
        });
    }

    @Override
    public void getHistoryMessage(String token, String orderId, final int pageNo, int pageSize, final DataCallback dataCallback) {
        WonderfulOkhttpUtils.post().url(UrlFactory.getHistoryMessageUrl()).addHeader("x-auth-token", token).
                addParams("orderId", orderId).addParams("page", String.valueOf(pageNo))
                .addParams("limit", String.valueOf(pageSize)).build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {
                super.onError(request, e);
                WonderfulLogUtils.logi("获取历史聊天记录出错", "获取历史聊天记录出错：" + e.getMessage());
                dataCallback.onDataNotAvailable(OKHTTP_ERROR, null);
            }

            @Override
            public void onResponse(String response) {
                WonderfulLogUtils.logi("获取历史聊天记录回执：", "pageNo     " + pageNo + "      获取历史聊天记录回执：" + response.toString());
                try {
                    JSONObject object = new JSONObject(response);
                    /*if (object.optInt("code") == 0) {
                        dataCallback.onDataLoaded(object.getString("message"));
                    } else {
                        dataCallback.onDataNotAvailable(object.getInt("code"), object.optString("message"));
                    }*/
                    List<ChatEntity> objs = gson.fromJson(object.getJSONArray("data").toString(), new TypeToken<List<ChatEntity>>() {
                    }.getType());
                    dataCallback.onDataLoaded(objs);
                } catch (JSONException e) {
                    e.printStackTrace();
                    dataCallback.onDataNotAvailable(JSON_ERROR, null);
                }
            }
        });
    }

    @Override
    public void getEntrustHistory(String token, String symbol, final int pageNo, int pageSize, final DataCallback dataCallback) {
        WonderfulOkhttpUtils.post().url(UrlFactory.getHistoryEntrus()).addHeader("x-auth-token", token)
                .addParams("symbol", symbol)
                .addParams("pageNo", String.valueOf(pageNo))
                .addParams("pageSize", String.valueOf(pageSize)).build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {
                super.onError(request, e);
                WonderfulLogUtils.logi("获取委托记录出错", "获取委托记录出错：" + e.getMessage());
                dataCallback.onDataNotAvailable(OKHTTP_ERROR, null);
            }

            @Override
            public void onResponse(String response) {
                WonderfulLogUtils.logi("获取委托记录回执：", "pageNo     " + pageNo + "      获取委托记录回执：" + response.toString());
                try {
                    JSONObject object = new JSONObject(response);
                    /*if (object.optInt("code") == 0) {
                        dataCallback.onDataLoaded(object.getString("message"));
                    } else {
                        dataCallback.onDataNotAvailable(object.getInt("code"), object.optString("message"));
                    }*/
                    WonderfulLogUtils.logi("获取委托记录回执：", "pageNo     " + pageNo + "      获取委托记录回执：" + object.getJSONArray("content").toString());
                    List<EntrustHistory> objs = gson.fromJson(object.getJSONArray("content").toString(), new TypeToken<List<EntrustHistory>>() {
                    }.getType());
                    dataCallback.onDataLoaded(objs);
                } catch (JSONException e) {
                    e.printStackTrace();
                    dataCallback.onDataNotAvailable(JSON_ERROR, null);
                }
            }
        });
    }

    @Override
    public void getCreditInfo(String token, final DataCallback dataCallback) {
        WonderfulOkhttpUtils.post().url(UrlFactory.getCreditInfo()).addHeader("x-auth-token", token).build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {
                super.onError(request, e);
                WonderfulLogUtils.logi("获取认证信息出错", "获取认证信息出错：" + e.getMessage());
                dataCallback.onDataNotAvailable(OKHTTP_ERROR, null);
            }

            @Override
            public void onResponse(String response) {
                WonderfulLogUtils.logi("获取认证信息回执：", "获取认证信息回执：" + response.toString());
                try {
                    JSONObject object = new JSONObject(response);
                    Credit.DataBean objs = gson.fromJson(object.getJSONObject("data").toString(), new TypeToken<Credit.DataBean>() {
                    }.getType());
                    dataCallback.onDataLoaded(objs);
                } catch (JSONException e) {
                    e.printStackTrace();
                    dataCallback.onDataNotAvailable(JSON_ERROR, null);
                }
            }
        });
    }

    @Override
    public void getNewVision(String token, final DataCallback dataCallback) {
        WonderfulOkhttpUtils.post().url(UrlFactory.getNewVision()).addHeader("x-auth-token", token).build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {
                super.onError(request, e);
                WonderfulLogUtils.logi("获取版本信息出错", "获取版本信息出错：" + e.getMessage());
                dataCallback.onDataNotAvailable(OKHTTP_ERROR, null);
            }

            @Override
            public void onResponse(String response) {
                WonderfulLogUtils.logi("获取版本信息回执：", "获取版本信息回执：" + response.toString());
                try {
                    JSONObject object = new JSONObject(response);
                    if (object.optInt("code") == 0) {
                        Vision objs = gson.fromJson(object.toString(), new TypeToken<Vision>() {
                        }.getType());
                        dataCallback.onDataLoaded(objs);
                    } else {
                        dataCallback.onDataNotAvailable(object.getInt("code"), MyApplication.getApp().getString(R.string.versionUpdateTip));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    dataCallback.onDataNotAvailable(JSON_ERROR, null);
                }
            }
        });
    }

    @Override
    public void getSymbol(final DataCallback dataCallback) {
        WonderfulOkhttpUtils.post().url(UrlFactory.getSymbolUrl()).build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {
                super.onError(request, e);
                WonderfulLogUtils.logi("获取交易对信息出错", "获取交易对信息出错：" + e.getMessage());
                dataCallback.onDataNotAvailable(OKHTTP_ERROR, null);
            }

            @Override
            public void onResponse(String response) {
                WonderfulLogUtils.logi("获取交易对信息回执：", "获取交易对信息回执：" + response.toString());
                try {
                    //JSONObject object = new JSONObject(response);
                    JSONArray array = new JSONArray(response);
                    if (array.length() != 0) {
                        List<MarketSymbol> objs = gson.fromJson(array.toString(), new TypeToken<List<MarketSymbol>>() {
                        }.getType());
                        dataCallback.onDataLoaded(objs);
                    } else {
                        //dataCallback.onDataNotAvailable(object.getInt("code"), object.optString("message"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    dataCallback.onDataNotAvailable(JSON_ERROR, null);
                }
            }
        });
    }

    @Override
    public void getAccountSetting(String token, final DataCallback dataCallback) {
        WonderfulOkhttpUtils.post().url(UrlFactory.getAccountSettingUrl())
                .addHeader("x-auth-token", token).build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {
                super.onError(request, e);
                WonderfulLogUtils.logi("获取收款账户信息出错", "获取收款账户信息出错：" + e.getMessage());
                dataCallback.onDataNotAvailable(OKHTTP_ERROR, null);
            }

            @Override
            public void onResponse(String response) {
                WonderfulLogUtils.logi("获取收款账户信息回执：", "获取收款账户信息回执：" + response.toString());
                try {
                    //JSONObject object = new JSONObject(response);
                    JSONObject object = new JSONObject(response);
                    if (object.optInt("code") == 0) {
                        AccountSetting objs = gson.fromJson(object.getJSONObject("data").toString(), new TypeToken<AccountSetting>() {
                        }.getType());
                        dataCallback.onDataLoaded(objs);
                    } else {
                        dataCallback.onDataNotAvailable(object.getInt("code"), object.optString("message"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    dataCallback.onDataNotAvailable(JSON_ERROR, null);
                }
            }
        });
    }

    @Override
    public void getBindBank(String token, String bank, String branch, String jyPassword, String realName, String cardNo, final DataCallback dataCallback) {
        WonderfulOkhttpUtils.post().url(UrlFactory.getBindBankUrl())
                .addHeader("x-auth-token", token)
                .addParams("bank", bank)
                .addParams("branch", branch)
                .addParams("jyPassword", jyPassword)
                .addParams("realName", realName)
                .addParams("cardNo", cardNo).build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {
                super.onError(request, e);
                WonderfulLogUtils.logi("绑定银行卡出错", "绑定银行卡出错：" + e.getMessage());
                dataCallback.onDataNotAvailable(OKHTTP_ERROR, null);
            }

            @Override
            public void onResponse(String response) {
                WonderfulLogUtils.logi("绑定银行卡回执：", "绑定银行卡回执：" + response.toString());
                try {
                    //JSONObject object = new JSONObject(response);
                    JSONObject object = new JSONObject(response);
                    if (object.optInt("code") == 0) {
                        dataCallback.onDataLoaded("绑定成功");
                    } else {
                        dataCallback.onDataNotAvailable(object.getInt("code"), object.optString("message"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    dataCallback.onDataNotAvailable(JSON_ERROR, null);
                }
            }
        });
    }

    @Override
    public void getUpdateBank(String token, String bank, String branch, String jyPassword, String realName, String cardNo, final DataCallback dataCallback) {
        WonderfulOkhttpUtils.post().url(UrlFactory.getUpdateBankUrl())
                .addHeader("x-auth-token", token)
                .addParams("bank", bank)
                .addParams("branch", branch)
                .addParams("jyPassword", jyPassword)
                .addParams("realName", realName)
                .addParams("cardNo", cardNo).build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {
                super.onError(request, e);
                WonderfulLogUtils.logi("更改银行卡出错", "更改银行卡出错：" + e.getMessage());
                dataCallback.onDataNotAvailable(OKHTTP_ERROR, null);
            }

            @Override
            public void onResponse(String response) {
                WonderfulLogUtils.logi("更改银行卡回执：", "更改银行卡回执：" + response.toString());
                try {
                    //JSONObject object = new JSONObject(response);
                    JSONObject object = new JSONObject(response);
                    if (object.optInt("code") == 0) {
                        dataCallback.onDataLoaded("更改成功");
                    } else {
                        dataCallback.onDataNotAvailable(object.getInt("code"), object.optString("message"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    dataCallback.onDataNotAvailable(JSON_ERROR, null);
                }
            }
        });
    }

    @Override
    public void getBindWeiChat(String token, String wechat, String jyPassword, String realName, String qrCodeUrl, final DataCallback dataCallback) {
        WonderfulOkhttpUtils.post().url(UrlFactory.getBindWechatUrl())
                .addHeader("x-auth-token", token)
                .addParams("wechat", wechat).addParams("jyPassword", jyPassword)
                .addParams("realName", realName)
                .addParams("qrCodeUrl", qrCodeUrl).build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {
                super.onError(request, e);
                WonderfulLogUtils.logi("绑定微信出错", "绑定微信出错：" + e.getMessage());
                dataCallback.onDataNotAvailable(OKHTTP_ERROR, null);
            }

            @Override
            public void onResponse(String response) {
                WonderfulLogUtils.logi("绑定微信回执：", "绑定微信回执：" + response.toString());
                try {
                    //JSONObject object = new JSONObject(response);
                    JSONObject object = new JSONObject(response);
                    if (object.optInt("code") == 0) {
                        dataCallback.onDataLoaded("绑定成功");
                    } else {
                        dataCallback.onDataNotAvailable(object.getInt("code"), object.optString("message"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    dataCallback.onDataNotAvailable(JSON_ERROR, null);
                }
            }
        });
    }

    @Override
    public void getUpdateWeiChat(String token, String wechat, String jyPassword, String realName, String qrCodeUrl, final DataCallback dataCallback) {
        WonderfulOkhttpUtils.post().url(UrlFactory.getUpdateWechatUrl())
                .addHeader("x-auth-token", token)
                .addParams("wechat", wechat).addParams("jyPassword", jyPassword)
                .addParams("realName", realName)
                .addParams("qrCodeUrl", qrCodeUrl).build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {
                super.onError(request, e);
                WonderfulLogUtils.logi("更改微信出错", "更改微信出错：" + e.getMessage());
                dataCallback.onDataNotAvailable(OKHTTP_ERROR, null);
            }

            @Override
            public void onResponse(String response) {
                WonderfulLogUtils.logi("更改微信回执：", "更改微信回执：" + response.toString());
                try {
                    //JSONObject object = new JSONObject(response);
                    JSONObject object = new JSONObject(response);
                    if (object.optInt("code") == 0) {
                        dataCallback.onDataLoaded("更改成功");
                    } else {
                        dataCallback.onDataNotAvailable(object.getInt("code"), object.optString("message"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    dataCallback.onDataNotAvailable(JSON_ERROR, null);
                }
            }
        });
    }

    @Override
    public void getBindAli(String token, String ali, String jyPassword, String realName, String qrCodeUrl, final DataCallback dataCallback) {
        WonderfulOkhttpUtils.post().url(UrlFactory.getBindAliUrl())
                .addHeader("x-auth-token", token)
                .addParams("ali", ali).addParams("jyPassword", jyPassword)
                .addParams("realName", realName)
                .addParams("qrCodeUrl", qrCodeUrl).build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {
                super.onError(request, e);
                WonderfulLogUtils.logi("绑定支付宝出错", "绑定支付宝出错：" + e.getMessage());
                dataCallback.onDataNotAvailable(OKHTTP_ERROR, null);
            }

            @Override
            public void onResponse(String response) {
                WonderfulLogUtils.logi("绑定支付宝回执：", "绑定支付宝回执：" + response.toString());
                try {
                    //JSONObject object = new JSONObject(response);
                    JSONObject object = new JSONObject(response);
                    if (object.optInt("code") == 0) {
                        dataCallback.onDataLoaded("绑定成功");
                    } else {
                        dataCallback.onDataNotAvailable(object.getInt("code"), object.optString("message"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    dataCallback.onDataNotAvailable(JSON_ERROR, null);
                }
            }
        });
    }

    @Override
    public void getUpdateAli(String token, String ali, String jyPassword, String realName, String qrCodeUrl, final DataCallback dataCallback) {
        WonderfulOkhttpUtils.post().url(UrlFactory.getUpdateAliUrl())
                .addHeader("x-auth-token", token)
                .addParams("ali", ali).addParams("jyPassword", jyPassword)
                .addParams("realName", realName)
                .addParams("qrCodeUrl", qrCodeUrl).build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {
                super.onError(request, e);
                WonderfulLogUtils.logi("更改支付宝出错", "更改支付宝出错：" + e.getMessage());
                dataCallback.onDataNotAvailable(OKHTTP_ERROR, null);
            }

            @Override
            public void onResponse(String response) {
                WonderfulLogUtils.logi("更改支付宝回执：", "更改支付宝回执：" + response.toString());
                try {
                    //JSONObject object = new JSONObject(response);
                    JSONObject object = new JSONObject(response);
                    if (object.optInt("code") == 0) {
                        dataCallback.onDataLoaded("更改成功");
                    } else {
                        dataCallback.onDataNotAvailable(object.getInt("code"), object.optString("message"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    dataCallback.onDataNotAvailable(JSON_ERROR, null);
                }
            }
        });
    }

    @Override
    public void getCheckMatch(String token, final DataCallback dataCallback) {
        WonderfulOkhttpUtils.post().url(UrlFactory.getCheckMatchUrl())
                .addHeader("x-auth-token", token)
                .build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {
                super.onError(request, e);
                WonderfulLogUtils.logi("检查配对出错", "检查配对出错：" + e.getMessage());
                dataCallback.onDataNotAvailable(OKHTTP_ERROR, null);
            }

            @Override
            public void onResponse(String response) {
                WonderfulLogUtils.logi("检查配对回执：", "检查配对回执：" + response.toString());
                try {
                    //JSONObject object = new JSONObject(response);
                    JSONObject object = new JSONObject(response);
                    if (object.optInt("code") == 0) {
                        GccMatch objs = gson.fromJson(object.toString(), new TypeToken<GccMatch>() {
                        }.getType());
                        dataCallback.onDataLoaded(objs);
                    } else {
                        dataCallback.onDataNotAvailable(object.getInt("code"), object.optString("message"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    dataCallback.onDataNotAvailable(JSON_ERROR, null);
                }
            }
        });
    }

    @Override
    public void getStartMatch(String token, String amount, final DataCallback dataCallback) {
        WonderfulOkhttpUtils.post().url(UrlFactory.getStartMatchUrl())
                .addHeader("x-auth-token", token)
                .addParams("amount", amount)
                .build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {
                super.onError(request, e);
                WonderfulLogUtils.logi("开始配对出错", "开始配对出错：" + e.getMessage());
                dataCallback.onDataNotAvailable(OKHTTP_ERROR, null);
            }

            @Override
            public void onResponse(String response) {
                WonderfulLogUtils.logi("开始配对回执：", "开始配对回执：" + response.toString());
                try {
                    //JSONObject object = new JSONObject(response);
                    JSONObject object = new JSONObject(response);
                    if (object.optInt("code") == 0) {
                        dataCallback.onDataLoaded(object.optString("message"));
                    } else {
                        dataCallback.onDataNotAvailable(object.getInt("code"), object.optString("message"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    dataCallback.onDataNotAvailable(JSON_ERROR, null);
                }
            }
        });
    }

    @Override
    public void getPromotion(String token, String page, String number, final DataCallback dataCallback) {
        WonderfulOkhttpUtils.post().url(UrlFactory.getPromotionUrl())
                .addHeader("x-auth-token", token)
                .addParams("pageNo", page).addParams("pageSize", number)
                .build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {
                super.onError(request, e);
                WonderfulLogUtils.logi("推广好友记录出错", "推广好友记录出错：" + e.getMessage());
                dataCallback.onDataNotAvailable(OKHTTP_ERROR, null);
            }

            @Override
            public void onResponse(String response) {
                WonderfulLogUtils.logi("推广好友记录回执：", "推广好友记录回执：" + response.toString());
                try {
                    //JSONObject object = new JSONObject(response);
                    JSONObject object = new JSONObject(response);
                    if (object.optInt("code") == 0) {
                        List<PromotionRecord> objs = gson.fromJson(object.getJSONArray("data").toString(), new TypeToken<List<PromotionRecord>>() {
                        }.getType());
                        //WonderfulLogUtils.logi("推广好友记录回执：", "推广好友记录回执：" + objs.size());
                        dataCallback.onDataLoaded(objs);
                    } else {
                        dataCallback.onDataNotAvailable(object.getInt("code"), object.optString("message"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    dataCallback.onDataNotAvailable(JSON_ERROR, null);
                }
            }
        });
    }

    @Override
    public void getPromotionReward(String token, String page, String number, final DataCallback dataCallback) {
        WonderfulOkhttpUtils.post().url(UrlFactory.getPromotionRewardUrl())
                .addHeader("x-auth-token", token)
                .addParams("pageNo", page).addParams("pageSize", number)
                .build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {
                super.onError(request, e);
                WonderfulLogUtils.logi("推广佣金记录出错", "推广佣金记录出错：" + e.getMessage());
                dataCallback.onDataNotAvailable(OKHTTP_ERROR, null);
            }

            @Override
            public void onResponse(String response) {
                WonderfulLogUtils.logi("推广佣金记录回执：", "推广佣金记录回执：" + response.toString());
                try {
                    //JSONObject object = new JSONObject(response);
                    JSONObject object = new JSONObject(response);
                    if (object.optInt("code") == 0) {
                        List<PromotionReward> objs = gson.fromJson(object.getJSONArray("data").toString(), new TypeToken<List<PromotionReward>>() {
                        }.getType());
                        dataCallback.onDataLoaded(objs);
                    } else {
                        dataCallback.onDataNotAvailable(object.getInt("code"), object.optString("message"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    dataCallback.onDataNotAvailable(JSON_ERROR, null);
                }
            }
        });
    }

    @Override
    public void ctcOrderList(String token, int pageNo, int pageSize, final DataCallback dataCallback) {
        WonderfulOkhttpUtils.post().url(UrlFactory.getCtcOrderList()).addHeader("x-auth-token", token)
                .addParams("pageNo", pageNo + "").addParams("pageSize", pageSize + "").build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {
                super.onError(request, e);
                WonderfulLogUtils.logi("获取我的C2C订单出错", "获取我的C2C订单出错：" + e.getMessage());
                dataCallback.onDataNotAvailable(OKHTTP_ERROR, null);
            }

            @Override
            public void onResponse(String response) {
                WonderfulLogUtils.logi("获取我的C2C订单回执：", "获取我的C2C订单回执：" + response.toString());
                try {
                    JSONObject object = new JSONObject(response);
                    if (object.optInt("code") == 0) {
                        CTCOrder obj = gson.fromJson(object.getJSONObject("data").toString(), CTCOrder.class);
                        dataCallback.onDataLoaded(obj);
                    } else {
                        dataCallback.onDataNotAvailable(object.getInt("code"), object.optString("message"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    dataCallback.onDataNotAvailable(JSON_ERROR, null);
                }
            }
        });
    }

    @Override
    public void ctcOrderDetail(String token, Long oid, final DataCallback dataCallback) {
        WonderfulOkhttpUtils.post().url(UrlFactory.getCtcOrderDetail()).addHeader("x-auth-token", token)
                .addParams("oid", oid + "").build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {
                super.onError(request, e);
                WonderfulLogUtils.logi("获取我的C2C订单详情出错", "获取我的C2C订单详情出错：" + e.getMessage());
                dataCallback.onDataNotAvailable(OKHTTP_ERROR, null);
            }

            @Override
            public void onResponse(String response) {
                WonderfulLogUtils.logi("获取我的C2C订单详情回执：", "获取我的C2C订单详情回执：" + response.toString());
                try {
                    JSONObject object = new JSONObject(response);
                    if (object.optInt("code") == 0) {
                        CTCOrderDetail obj = gson.fromJson(object.getJSONObject("data").toString(), CTCOrderDetail.class);
                        dataCallback.onDataLoaded(obj);
                    } else {
                        dataCallback.onDataNotAvailable(object.getInt("code"), object.optString("message"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    dataCallback.onDataNotAvailable(JSON_ERROR, null);
                }
            }
        });
    }

    @Override
    public void ctcOrderCancel(String token, Long oid, final DataCallback dataCallback) {
        WonderfulOkhttpUtils.post().url(UrlFactory.getCtcOrderCancel()).addHeader("x-auth-token", token)
                .addParams("oid", oid + "").build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {
                super.onError(request, e);
                WonderfulLogUtils.logi("取消我的C2C订单出错", "取消我的C2C订单出错：" + e.getMessage());
                dataCallback.onDataNotAvailable(OKHTTP_ERROR, null);
            }

            @Override
            public void onResponse(String response) {
                WonderfulLogUtils.logi("取消我的C2C订单回执：", "取消我的C2C订单回执：" + response.toString());
                try {
                    JSONObject object = new JSONObject(response);
                    if (object.optInt("code") == 0) {
                        CTCOrderDetail obj = gson.fromJson(object.getJSONObject("data").toString(), CTCOrderDetail.class);
                        dataCallback.onDataLoaded(obj);
                    } else {
                        dataCallback.onDataNotAvailable(object.getInt("code"), object.optString("message"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    dataCallback.onDataNotAvailable(JSON_ERROR, null);
                }
            }
        });
    }

    @Override
    public void ctcOrderPay(String token, Long oid, final DataCallback dataCallback) {
        WonderfulOkhttpUtils.post().url(UrlFactory.getCtcOrderPay()).addHeader("x-auth-token", token)
                .addParams("oid", oid + "").build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {
                super.onError(request, e);
                WonderfulLogUtils.logi("标记付款我的C2C订单出错", "标记付款我的C2C订单出错：" + e.getMessage());
                dataCallback.onDataNotAvailable(OKHTTP_ERROR, null);
            }

            @Override
            public void onResponse(String response) {
                WonderfulLogUtils.logi("标记付款我的C2C订单回执：", "标记付款我的C2C订单回执：" + response.toString());
                try {
                    JSONObject object = new JSONObject(response);
                    if (object.optInt("code") == 0) {
                        CTCOrderDetail obj = gson.fromJson(object.getJSONObject("data").toString(), CTCOrderDetail.class);
                        dataCallback.onDataLoaded(obj);
                    } else {
                        dataCallback.onDataNotAvailable(object.getInt("code"), object.optString("message"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    dataCallback.onDataNotAvailable(JSON_ERROR, null);
                }
            }
        });
    }

    @Override
    public void ctcPrice(final DataCallback dataCallback) {
        WonderfulLogUtils.logi("ctcPrice remote call", "ctcPrice remote call");
        WonderfulOkhttpUtils.post().url(UrlFactory.getCtcPrice()).build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {
                super.onError(request, e);
                WonderfulLogUtils.logi("获取C2C价格失败", "获取C2C价格失败：" + e.getMessage());
                dataCallback.onDataNotAvailable(OKHTTP_ERROR, null);
            }

            @Override
            public void onResponse(String response) {
                WonderfulLogUtils.logi("获取C2C价格回执：", "获取C2C价格回执：" + response.toString());
                try {
                    JSONObject object = new JSONObject(response);
                    if (object.optInt("code") == 0) {
                        CTCPrice obj = gson.fromJson(object.getJSONObject("data").toString(), CTCPrice.class);
                        dataCallback.onDataLoaded(obj);
                    } else {
                        dataCallback.onDataNotAvailable(object.getInt("code"), object.optString("message"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    dataCallback.onDataNotAvailable(JSON_ERROR, null);
                }
            }
        });
    }

    @Override
    public void ctcNewOrder(String token, BigDecimal price, BigDecimal amount, String payType, int direction, String unit, String fundpwd, String code, final DataCallback dataCallback) {
        WonderfulOkhttpUtils.post().url(UrlFactory.getCtcNewOrder()).addHeader("x-auth-token", token)
                .addParams("price", price + "")
                .addParams("amount", amount + "")
                .addParams("payType", payType)
                .addParams("direction", direction + "")
                .addParams("unit", unit)
                .addParams("fundpwd", fundpwd + "")
                .addParams("code", code).build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {
                super.onError(request, e);
                WonderfulLogUtils.logi("新建C2C订单出错", "新建C2C订单出错：" + e.getMessage());
                dataCallback.onDataNotAvailable(OKHTTP_ERROR, null);
            }

            @Override
            public void onResponse(String response) {
                WonderfulLogUtils.logi("新建C2C订单回执：", "新建C2C订单回执：" + response.toString());
                try {
                    JSONObject object = new JSONObject(response);
                    if (object.optInt("code") == 0) {
                        CTCOrderDetail obj = gson.fromJson(object.getJSONObject("data").toString(), CTCOrderDetail.class);
                        dataCallback.onDataLoaded(obj);
                    } else {
                        dataCallback.onDataNotAvailable(object.getInt("code"), object.optString("message"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    dataCallback.onDataNotAvailable(JSON_ERROR, null);
                }
            }
        });
    }

    @Override
    public void ctcSendNewOrderPhoneCode(String token, final DataCallback dataCallback) {
        post().url(UrlFactory.getCtcSendNewOrderPhoneCode()).addHeader("x-auth-token", token)
                .build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {
                super.onError(request, e);
                WonderfulLogUtils.logi("获取手机验证码出错", "获取手机验证码出错：" + e.getMessage());
                dataCallback.onDataNotAvailable(OKHTTP_ERROR, null);
            }

            @Override
            public void onResponse(String response) {
                WonderfulLogUtils.logi("获取手机验证码回执：", "获取手机验证码回执：" + response.toString());
                try {
                    JSONObject object = new JSONObject(response);
                    if (object.optInt("code") == 0) {
                        dataCallback.onDataLoaded("获取成功");
                    } else {
                        dataCallback.onDataNotAvailable(object.getInt("code"), object.optString("message"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    dataCallback.onDataNotAvailable(JSON_ERROR, null);
                }
            }
        });
    }

    @Override
    public void getDepositList(String token, final DataCallback dataCallback) {
        WonderfulOkhttpUtils.post().url(UrlFactory.getDepositList())
                .addHeader("x-auth-token", token)
                .build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {
                super.onError(request, e);
                WonderfulLogUtils.logi("获取保证金币种出错", e.getMessage());
                dataCallback.onDataNotAvailable(OKHTTP_ERROR, null);
            }

            @Override
            public void onResponse(String response) {
                WonderfulLogUtils.logi("保证金币种：", response);
                try {
                    JSONObject object = new JSONObject(response);
                    if (object.optInt("code") == 0) {
                        List<DepositInfo> objs = gson.fromJson(object.getJSONArray("data").toString(), new TypeToken<List<DepositInfo>>() {
                        }.getType());
                        dataCallback.onDataLoaded(objs);
                    } else {
                        dataCallback.onDataNotAvailable(object.getInt("code"), object.optString("message"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    dataCallback.onDataNotAvailable(JSON_ERROR, null);
                }
            }
        });
    }

    @Override
    public void commitSellerApply(String token, String coinId, String json, final DataCallback dataCallback) {

        WonderfulOkhttpUtils.post().url(UrlFactory.getSellerApply())
                .addHeader("x-auth-token", token)
                .addParams("json", json)
                .addParams("businessAuthDepositId", coinId)
                .build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {
                super.onError(request, e);
                WonderfulLogUtils.logi("申请成为商家出错", e.getMessage());
                dataCallback.onDataNotAvailable(OKHTTP_ERROR, null);
            }

            @Override
            public void onResponse(String response) {
                WonderfulLogUtils.logi("申请成为商家：", response);
                try {
                    JSONObject object = new JSONObject(response);
                    if (object.optInt("code") == 0) {
                        dataCallback.onDataLoaded("申请成功");
                    } else {
                        dataCallback.onDataNotAvailable(object.getInt("code"), object.optString("message"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    dataCallback.onDataNotAvailable(JSON_ERROR, null);
                }
            }
        });
    }

    @Override
    public void myWalletUsdt(String token, final DataCallback dataCallback) {
        WonderfulOkhttpUtils.post().url(UrlFactory.getWalleUsdttUrl())
                .addHeader("x-auth-token", token).build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {
                super.onError(request, e);
                WonderfulLogUtils.logi("资产划转获取币币余额", "出错：" + e.getMessage());
                dataCallback.onDataNotAvailable(OKHTTP_ERROR, null);
            }

            @Override
            public void onResponse(String response) {
                try {
                    JSONObject object = new JSONObject(response);
                    if (object.optInt("code") == 0) {
                        Wallet_Coin objs = gson.fromJson(object.getJSONObject("data").toString(), new TypeToken<Wallet_Coin>() {
                        }.getType());
                        dataCallback.onDataLoaded(objs);
                    } else {
                        dataCallback.onDataNotAvailable(object.getInt("code"), object.optString("message"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    dataCallback.onDataNotAvailable(JSON_ERROR, null);
                }
            }
        });
    }

    @Override
    public void myPromotion(String token, final DataCallback dataCallback) {
        WonderfulOkhttpUtils.post().url(UrlFactory.getMyPromotion())
                .addHeader("x-auth-token", token)
                .build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {
                super.onError(request, e);
                WonderfulLogUtils.logi("获取我的推广佣金出错", e.getMessage());
                dataCallback.onDataNotAvailable(OKHTTP_ERROR, null);
            }

            @Override
            public void onResponse(String response) {
                WonderfulLogUtils.logi("获取推广佣金：", response);
                try {
                    JSONObject object = new JSONObject(response);
                    if (object.optInt("code") == 0) {
                        dataCallback.onDataLoaded(object.getJSONObject("data"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    dataCallback.onDataNotAvailable(JSON_ERROR, null);
                }
            }
        });
    }


}
