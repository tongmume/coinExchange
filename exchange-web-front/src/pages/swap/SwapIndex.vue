<template>
  <div class="container swap" :class="skin">

    <div class="main">
      <div style="display: flex;flex: 0 0 70%;flex-wrap:wrap;justify-content: space-between;">
        <div class="right">
          <div class="coin-menu">
            <div style="padding: 8px 10px;height:48px;">
              <Input search :placeholder="$t('common.searchplaceholderswap')" @on-change="seachInputChange" v-model="searchKey"/>
            </div>
            <div class="sc_filter" style="display: none;">
              <span @click="changeBaseCion('usdt')" :class="{active:basecion==='usdt'}">USDT</span>
            </div>
            <Table height="463" @on-current-change="gohref" highlight-row id="USDT" v-show="basecion==='usdt'" :columns="coins.columns" :data="dataIndex"></Table></Table>
          </div>
        </div>
        <div class="center">
          <div class="symbol">
            <div class="item" style="margin-left: 10px;">
                <span class="coin">{{currentCoin.symbol}} {{$t("swap.swapindex")}}
                </span>
            </div>
            <div class="item">
              <span class="text">{{$t('service.NewPrice')}}</span>
              <span class="num" :class="{buy:currentCoin.change>0,sell:currentCoin.change<0}">{{currentCoin.close | toFixed(4)}}</span>
              <span class="price-cny">≈ ￥{{currentCoin.usdRate*CNYRate | toFixed(2)}}</span>
            </div>
            <div class="item">
              <span class="text">{{$t('service.Change')}}</span>
              <span class="num" :class="{buy:currentCoin.change>0,sell:currentCoin.change<0}">{{currentCoin.rose}}</span>
            </div>
            <div class="item">
              <span class="text">{{$t('service.high')}}</span>
              <span class="num ">{{currentCoin.high | toFixed(4)}}</span>
            </div>
            <div class="item">
              <span class="text">{{$t('service.low')}}</span>
              <span class="num ">{{currentCoin.low | toFixed(4)}}</span>
            </div>
            <div class="item">
              <span class="text">{{$t('service.ExchangeNum')}}</span>
              <span class="num ">{{currentCoin.volume |  toFixed(4)}} {{currentCoin.coin}}</span>
            </div>
            <div class="item">
              <!-- <img src="../../assets/images/exchange/light-switch.png" alt=""> -->
            </div>
          </div>
          <div class="imgtable">
            <div class="handler">
              <span @click="changeImgTable('k')" :class="{active:currentImgTable==='k'}">{{$t("swap.kline")}}</span>
              <span @click="changeImgTable('s')" :class="{active:currentImgTable==='s'}">{{$t("swap.depth")}}</span>
            </div>
            <div id="kline_container" :class="{hidden:currentImgTable==='s'}">
            </div>
            <DepthGraph :class="{hidden:currentImgTable==='k'}" ref="depthGraph"></DepthGraph>
          </div>
        </div>
        <div style="width:100%;margin-top: 5px;flex: 0 0 100%;">
          <div class="order" style="background: #999;margin-right: 5px;">
            <div class="order-handler">
              <span @click="changeOrder('currentPositions')" :class="{active:selectedOrder==='currentPositions'}">{{$t('swap.currentposition')}}</span>
              <span @click="changeOrder('currentEntrustOrders')" :class="{active:selectedOrder==='currentEntrustOrders'}">{{$t('swap.curdelegation')}}</span>
              <span @click="changeOrder('historyEntrustOrders')" :class="{active:selectedOrder==='historyEntrustOrders'}">{{$t('swap.hisdelegation')}}</span>
            </div>
            <div class="table">
              <Table height="285" v-if="selectedOrder==='currentPositions'" :columns="currentPosition.columns" :data="positionList" :no-data-text="$t('common.nodata')"></Table>
              <Table height="285" v-if="selectedOrder==='currentEntrustOrders'" :columns="currentOrder.columns" :no-data-text="$t('common.nodata')" :data="currentEntrustOrderList"></Table>
              <Table height="285" v-if="selectedOrder==='historyEntrustOrders'" :columns="historyOrder.columns" :no-data-text="$t('common.nodata')" :data="historyEntrustOrderList"></Table>
            </div>
          </div>
        </div>
      </div>
      <!-- 盘口 -->
      <div class="left plate-wrap" style="position:relative; flex: 0 0 17%;">
        <div class="handlers">
          <span @click="changePlate('all')" class="handler handler-all" :class="{active:selectedPlate=='all'}"></span>
          <span @click="changePlate('buy')" class="handler handler-green" :class="{active:selectedPlate=='buy'}"></span>
          <span @click="changePlate('sell')" class="handler handler-red" :class="{active:selectedPlate=='sell'}"></span>
        </div>
        <!-- 盘口：卖 -->
        <Table v-show="selectedPlate!='buy'" @on-current-change="buyPlate" highlight-row ref="currentRowTable" class="sell_table" :columns="plate.columns" :data="plate.askRows" :no-data-text="$t('common.nodata')"></Table>
        <div class="plate-nowprice">
          <span class="price" :class="{buy:currentCoin.change>0,sell:currentCoin.change<0}">{{currentCoin.price | toFixed(baseCoinScale)}}</span>
          <span v-if="currentCoin.change>0" class="buy">↑</span>
          <span v-else-if="currentCoin.change<0" class="sell">↓</span>
          <span class="price-cny"> ≈ {{currentCoin.usdRate*CNYRate | toFixed(2)}} CNY</span>
        </div>
        <!-- 盘口：买 -->
        <Table v-show="selectedPlate!='sell'" @on-current-change="sellPlate" highlight-row class="buy_table" :class="{hidden:selectedPlate==='all'}" :columns="plate.columns" :data="plate.bidRows" :no-data-text="$t('common.nodata')"></Table>

        <!-- 开仓/平仓 -->
        <div class="order" style="margin-top: 5px;background-color: #192330;height: 320px;">
          <div class="order-handler" style="border-bottom: 1px solid rgb(39, 49, 62);">
            <span style="width: 50%;text-align: center;" @click="entrustChange(1)" :class="{active:entrustType===1}">{{$t('swap.open')}}</span>
            <span style="width: 50%;text-align: center;" @click="entrustChange(2)" :class="{active:entrustType===2}">{{$t('swap.close')}}</span>
          </div>
          <div class="table open-close">
            <div v-if="entrustType===1" class="open">
              <RadioGroup v-model="entrustOrderType" type="button" size="default" @on-change="changeEntrustOrderType">
                  <Radio label="1">{{$t('swap.limited_price')}}</Radio>
                  <Radio label="0">{{$t('swap.market_price')}}</Radio>
                  <Radio label="2">{{$t('swap.spot_price')}}</Radio>
              </RadioGroup>
              <Form style="width: 94%;margin-left: 3%;margin-top:10px;">
                  <FormItem style="margin-bottom: 10px;" v-if="entrustOrderType=='1'">
                    <label class="before">{{$t('swap.entrustprice')}}</label>
                    <Input v-model="form.entrustPrice"  @on-keyup="form.entrustPrice=form.entrustPrice.replace(/[^\d^\.]+/g,'')"></Input>
                    <label class="after">{{currentCoin.base}}</label>
                  </FormItem>
                  <FormItem style="margin-bottom: 10px;" v-if="entrustOrderType=='0'">
                    <label class="before">{{$t('swap.entrustprice')}}</label>
                    <Input :value="$t('swap.marketpriceplaceholder')" disabled class="market-price" ></Input>
                  </FormItem>

                  <FormItem style="margin-bottom: 10px;" v-if="entrustOrderType=='2'">
                    <label class="before">{{$t('swap.triggleprice')}}</label>
                    <Input v-model="form.triggerPrice" @on-keyup="form.triggerPrice=form.triggerPrice.replace(/[^\d^\.]+/g,'')"></Input>
                    <label class="after">{{currentCoin.base}}</label>
                  </FormItem>

                  <FormItem style="margin-bottom: 10px;" v-if="entrustOrderType=='2'">
                    <label class="before">{{$t('swap.entrustprice')}}</label>
                    <Input v-model="form.entrustPrice" :placeholder="$t('swap.triggleplaceholder')" @on-keyup="form.entrustPrice=form.entrustPrice.replace(/[^\d^\.]+/g,'')"></Input>
                    <label class="after">{{currentCoin.base}}</label>
                  </FormItem>

                  <FormItem style="margin-bottom: 10px;" >
                    <label class="before">{{$t('swap.openvolume')}}</label>
                    <Input v-model="form.volume" v-if="sliderOpenPercent == 0"  @on-keyup="form.volume=form.volume.replace(/\D/g,'')"></Input>
                    <Input class="market-price" :readonly="true" v-if="sliderOpenPercent != 0" @on-focus="onOpenAmountFocus" :value="sliderOpenPercent + ' %'"></Input>
                    <label class="after" v-if="sliderOpenPercent == 0">{{$t('swap.shareNumber')}}</label>
                    <span style="position:absolute;top:32px;right:0px;color: #7c7f82;">1{{$t('swap.shareNumber')}} = {{contractCoinInfo.shareNumber}}USDT</span>
                  </FormItem>
                  <div class="split-panel" v-if="entrustOrderType!='2'">
                    <div :class="{selected:Number(sliderOpenPercent)===25}" @click="onChangeOpenPercent(25)">25%</div>
                    <div :class="{selected:Number(sliderOpenPercent)===50}" @click="onChangeOpenPercent(50)">50%</div>
                    <div :class="{selected:Number(sliderOpenPercent)===75}" @click="onChangeOpenPercent(75)">75%</div>
                    <div :class="{selected:Number(sliderOpenPercent)===100}" @click="onChangeOpenPercent(100)">100%</div>
                  </div>

              </Form>
              <div class="open-tips">
                <div><span class="green">{{$t('swap.canup')}}:</span><span class="num">{{avaOpenBuy}} {{$t('swap.shareNumber')}}</span></div>
                <div><span class="red">{{$t('swap.candown')}}:</span><span class="num">{{avaOpenSell}} {{$t('swap.shareNumber')}}</span></div>
              </div>
              <div class="operate" v-if="isLogin">
                <Button type="primary" shape="circle" class="open-up" @click="onOpen(0)">{{$t('swap.openup')}}</Button>
                <Button type="primary" shape="circle" class="open-down" @click="onOpen(1)">{{$t('swap.opendown')}}</Button>
              </div>
              <div class="operate-login" v-if="!isLogin" style="width: 94%; margin-left:3%;">
                <span style="display: inline-block;width: 100%; text-align: center;border:1px solid #232d3a;padding: 5px 0;border-radius: 5px;">
                  {{$t("common.please")}}
                  <router-link to="/login">
                    <span style="color:#f0a70a;">{{$t("common.login")}}</span>
                  </router-link> /
                  <router-link to="/register">
                    <span style="color:#00dcff;">{{$t("common.register")}}</span>
                  </router-link>
                </span>
              </div>
            </div>
            <div v-else class="open">
              <RadioGroup v-model="entrustOrderType" type="button" size="default" @on-change="changeEntrustOrderType">
                  <Radio label="1">{{$t('swap.limited_price')}}</Radio>
                  <Radio label="0">{{$t('swap.market_price')}}</Radio>
                  <Radio label="2">{{$t('swap.spot_price')}}</Radio>
              </RadioGroup>
              <Form style="width: 94%;margin-left: 3%;margin-top:10px;">
                  <FormItem style="margin-bottom: 10px;" v-if="entrustOrderType=='1'">
                    <label class="before">{{$t('swap.entrustprice')}}</label>
                    <Input v-model="form.entrustPrice" @on-keyup="form.entrustPrice=form.entrustPrice.replace(/[^\d^\.]+/g,'')"></Input>
                    <label class="after">{{currentCoin.base}}</label>
                  </FormItem>
                  <FormItem style="margin-bottom: 10px;" v-if="entrustOrderType=='0'">
                    <label class="before">{{$t('swap.entrustprice')}}</label>
                    <Input :value="$t('swap.marketpriceplaceholder')" disabled class="market-price"></Input>
                  </FormItem>

                  <FormItem style="margin-bottom: 10px;" v-if="entrustOrderType=='2'">
                    <label class="before">{{$t('swap.triggleprice')}}</label>
                    <Input v-model="form.triggerPrice" @on-keyup="form.trigglePrice=form.triggerPrice.replace(/[^\d^\.]+/g,'')"></Input>
                    <label class="after">{{currentCoin.base}}</label>
                  </FormItem>

                  <FormItem style="margin-bottom: 10px;" v-if="entrustOrderType=='2'">
                    <label class="before">{{$t('swap.entrustprice')}}</label>
                    <Input v-model="form.entrustPrice" :placeholder="$t('swap.triggleplaceholder')"  @on-keyup="form.entrustPrice=form.entrustPrice.replace(/[^\d^\.]+/g,'')"></Input>
                    <label class="after">{{currentCoin.base}}</label>
                  </FormItem>

                  <FormItem style="margin-bottom: 10px;">
                    <label class="before">{{$t('swap.closevolume')}}</label>
                    <Input v-model="form.volume" v-if="sliderClosePercent == 0" @on-keyup="form.volume=form.volume.replace(/\D/g,'')"></Input>
                    <Input class="market-price" :readonly="true" v-if="sliderClosePercent != 0" @on-focus="onCloseAmountFocus"></Input>
                    <label class="after">{{$t('swap.shareNumber')}}</label>
                    <span style="position:absolute;top:32px;right:0px;color: #7c7f82;">1{{$t('swap.shareNumber')}} = {{contractCoinInfo.shareNumber}}USDT</span>
                  </FormItem>


                  <div class="split-panel" v-if="entrustOrderType!='2'">
                    <div :class="{selected:Number(sliderClosePercent)===25}" @click="onChangeClosePercent(25)">25%</div>
                    <div :class="{selected:Number(sliderClosePercent)===50}" @click="onChangeClosePercent(50)">50%</div>
                    <div :class="{selected:Number(sliderClosePercent)===75}" @click="onChangeClosePercent(75)">75%</div>
                    <div :class="{selected:Number(sliderClosePercent)===100}" @click="onChangeClosePercent(100)">100%</div>
                  </div>
              </Form>
              <div class="open-tips">
                <div><span class="red">{{$t('swap.canclosedown')}}:</span><span class="num">{{contractWalletInfo.usdtBuyPosition}} {{$t('swap.shareNumber')}}</span></div>
                <div><span class="green">{{$t('swap.cancloseup')}}:</span><span class="num">{{contractWalletInfo.usdtSellPosition}} {{$t('swap.shareNumber')}}</span></div>
              </div>
              <div class="operate" v-if="isLogin">
                <Button type="primary" shape="circle" class="open-down" @click="onClose(1)">{{$t('swap.closedown')}}</Button>
                <Button type="primary" shape="circle" class="open-up" @click="onClose(0)">{{$t('swap.closeup')}}</Button>
              </div>
              <div class="operate-login" v-if="!isLogin" style="width: 94%; margin-left:3%;">
                <span style="display: inline-block;width: 100%; text-align: center;border:1px solid #232d3a;padding: 5px 0;border-radius: 5px;">
                  {{$t("common.please")}}
                  <router-link to="/login">
                    <span style="color:#f0a70a;">{{$t("common.login")}}</span>
                  </router-link> /
                  <router-link to="/register">
                    <span style="color:#00dcff;">{{$t("common.register")}}</span>
                  </router-link>
                </span>
              </div>
            </div>
          </div>
        </div>
      </div>
      <!-- 成交记录 -->
      <div class="left plate-wrap" style="position:relative; flex: 0 0 13%;">
        <div style="background-color: #192330;height:40px;line-height:40px;padding-left:5px;color:#61688A;font-size: 13px;">
          <span>{{$t("swap.latestdeal")}}</span>
        </div>
        <div class="trade-wrap">
          <Table height="472" :columns="trade.columns" :data="trade.rows" :no-data-text="$t('common.nodata')"></Table>
        </div>
        <!-- 我的合约账户 -->
        <div class="order" style="margin-top: 5px;min-height: 320px;background-color: #192330;color:#61688A;">
          <div style="height:32px;line-height:32px;padding-left:10px;border-bottom: 1px solid #27313e;font-size: 13px;">
            <span>{{$t("swap.myswapaccount")}}</span>
              <router-link class="linkmore" to="/uc/swapAssets/">{{$t("swap.zijinhuazhuan")}}</router-link>
          </div>
          <div class="table swap-my-account">
            <div class="account-item" style="margin-top: 20px;">
              <div style="width:35%;" >{{$t("swap.accountmode")}}</div>     
              <div class="mode" style="width: 65%;">
                <Button @click="showMarginModeModal()" v-if="marginMode === '1'" size="small">{{$t("swap.marginMode1")}}</Button>
                <Button @click="showMarginModeModal()" v-if="marginMode === '0'" size="small">{{$t("swap.marginMode2")}}</Button>
              </div>
            </div>
            <div class="account-item" style="margin-top: 20px;height:42px;padding-bottom:5px;border-bottom: 1px dashed #27313e;">
              <div style="width:35%;">{{$t("swap.accountmargin")}}</div>
              <div class="margin" style="width: 65%;display: flex;justify-content: space-between;">
                <Button @click="showAdjustLeverage(1)" size="small" style="flex: 0 0 47%;border:none;background: #4db872;">{{buyLeverage}}X{{$t('swap.up')}}</Button>
                <Button @click="showAdjustLeverage(2)" size="small" style="flex: 0 0 47%;border:none;background: #ee6560;">{{sellLeverage}}X{{$t('swap.down')}}</Button>
              </div>
            </div>

            <div class="account-item" style="margin-top:10px;"> <div>{{$t("swap.accountquanyi")}}</div>   <div><span>{{totalQuanyi | fixed4}}</span></div></div>
            <div class="account-item"> <div>{{$t("swap.profitandloss")}}</div>   <div><span>{{profitAndLoss | fixed4}}</span></div></div>
            <div class="account-item"> <div>{{$t("swap.principalAmount")}}</div> <div><span>{{contractWalletInfo.usdtBalance | fixed4}}</span></div></div>
            <div class="account-item"> <div>{{$t("swap.positionAmount")}}</div>  <div><span>{{contractWalletInfo.usdtBuyPrincipalAmount + contractWalletInfo.usdtSellPrincipalAmount | fixed4}}</span></div></div>
            <div class="account-item"> <div>{{$t("swap.frozenAmount")}}</div>    <div><span>{{contractWalletInfo.usdtFrozenBalance | fixed4}}</span></div></div>
            <div class="account-item"> <div>{{$t("swap.principalRate")}}</div>   <div><span>{{assetRate | percent}} %</span></div></div>
          </div>
        </div>
      </div>
    </div>

    <!-- 弹出框: 变更仓位模式 -->
    <Modal
        v-model="marginModeModal"
        :title="$t('swap.modifyMarginModeTitle')"
        width="20"
        class-name="vertical-center-modal">
        <div style="text-align: center;width:100%;">
            <ButtonGroup style="width: 80%;text-align: center;">
              <Button style="width: 50%;" v-if="marginMode==='1'" type="primary">{{$t("swap.marginMode1")}}</Button>
              <Button style="width: 50%;" v-else  @click="changeMarginMode('1')">{{$t("swap.marginMode1")}}</Button>

              <Button style="width: 50%;" v-if="marginMode==='0'"  type="primary">{{$t("swap.marginMode2")}}</Button>
              <Button style="width: 50%;" v-else  @click="changeMarginMode('0')">{{$t("swap.marginMode2")}}</Button>
            </ButtonGroup>
        </div>
        <div slot="footer">
            <Button type="default" size="large" @click="marginModeModal=false">{{$t("common.close")}}</Button>
        </div>
    </Modal>

    <!-- 弹出框: 调整杠杆 -->
    <Modal
        v-model="leverageModal"
        :title="$t('swap.modifyLeverage')"
        width="25"
        class-name="vertical-center-modal">
        <div class="leverage">
          <!-- 连续杠杆 -->
          <InputNumber size="large" style="width: 100%;" :value.sync="leverageAdjustVal" v-if="leverageType==2" @on-change="onLeverageChange">
          </InputNumber>
          <!-- 离散杠杆 -->
          <div class="leverage-panel" v-if="leverageType==1 && changeLeverageType==1">
            <Button v-for="item in leverageList" :key="item" style="margin-left:5px;margin-right:5px;margin-bottom: 10px;" @click="changeLeverage(Number(item))" :class="{leverageSelect:Number(item)===leverageAdjustVal}">{{item}}X</Button>
          </div>
          <div class="leverage-panel" v-if="leverageType==1 && changeLeverageType==2">
            <Button v-for="item in leverageList" :key="item" style="margin-left:5px;margin-right:5px;margin-bottom: 10px;" @click="changeLeverage(Number(item))" :class="{leverageSelect:Number(item)===leverageAdjustVal}">{{item}}X</Button>
          </div>
        </div>
        <div slot="footer">
            <Button type="default" size="large" @click="leverageModal=false">{{$t("common.close")}}</Button>
            <Button type="primary" size="large" @click="adjustLeverage()">{{$t("common.ok")}}</Button>
        </div>
    </Modal>
  </div>
</template>

<script>
import expandRow from "@components/exchange/expand.vue";
import Datafeeds from "@js/charting_library/datafeed/swaptrade.js";
var Stomp = require("stompjs");
var SockJS = require("sockjs-client");
var moment = require("moment");
import DepthGraph from "@components/exchange/DepthGraph.vue";
import $ from "@js/jquery.min.js";

export default {
  components: { expandRow, DepthGraph},
  data() {
    let self = this;
    return {
      buyLeverage: "10",
      sellLeverage: "10",
      changeLeverageType: 1, // 多仓：1，空仓：2
      leverageList: [],
      leverageModal: false,
      leverageLong: 10,
      leverageShort: 10,
      leverageAdjustVal: 10,
      checkSellCount: 0,
      checkBuyCount: 0,
      checkAllCount: 0,
      marginModeModal: false,
      marginMode: '1',
      accountMode: 0,
      entrustType: 1,
      entrustOrderType: "1",
      sliderStep: [25, 50, 75, 100],
      sliderOpenPercent: 0,
      sliderClosePercent: 0,
      memberRate: 0,
      // userRealVerified: false, //是否实名认证
      collecRequesting: false,
      currentCoinIsFavor: false,
      isUseBHB: false,
      skin: "night", //皮肤样式day&night
      currentImgTable: "k",
      selectedOrder: "currentPositions", //当前显示的委托记录
      selectedPlate: "all", //当前显示的买卖盘
      CNYRate: null,
      datafeed: null,
      defaultPath: "btc_usdt",
      basecion: "usdt",
      coinScale: 6,
      baseCoinScale: 6,
      symbolFee: 0.001,
      dataIndex: [],
      searchKey: "",
      coinInfo:{

      },
      leverageType: 1,
      contractCoinInfo: {
        id: 0,
        baseSymbol: "",
        coinSymbol: "",
        symbol: "",
        openFee: 0,
        closeFee: 0,
        enableTriggerEntrust: 1,
        feePercent: 0,
        leverageType: 1,
        leverage: "10,20,30,40,50,60,70,80,90,100",
        maxShare: 0,
        minShare: 0,
        shareNumber: 0
      },
      contractWalletInfo: {
        usdtBalance: 0.0,
        usdtBuyPosition: 0.0,
        usdtSellPosition: 0.0,
        usdtBuyLeverage: 10,
        usdtSellLeverage: 10,
        usdtFrozenBalance: 0.0,
        usdtBuyPrincipalAmount: 0.0,
        usdtSellPrincipalAmount: 0.0
      },
      currentCoin: {
        base: "",
        coin: "",
        symbol: "",
        price: 0.0
      },
      enableMarketBuy: 1, // 0:禁用  1:启用
      enableMarketSell: 1,
      exchangeable: 1, // 0:可交易   1:不可交易
      currentEntrustOrderList: [],
      historyEntrustOrderList: [],
      currentPositionList: [],
      //当前市场上的交易币种，按交易对分
      coins: {
        _map: [],
        USDT: [],
        columns: [
          {
            title: this.$t("swap.contract") + "/" + this.$t("swap.vol"),
            key: "coin",
            minWidth: 70,
            sortable: false,
            className: "coin-menu-symbol swap-coin-menu-symbol",
            render: (h, params) => {
              return h("div", {
                  style: {
                    padding: "5px 0"
                  }
                },[
                h("span", {
                  style: {
                    fontSize: "14px",
                    fontWeight: "bold"
                  }
                }, params.row.coin + "/USDT " + this.$t("swap.swapindex")),
                h("br"),
                h("span", {
                  style: {
                    color: "#61688A",
                    fontSize: "14px"
                  }
                }, params.row.volume.toFixed(4))
              ]);
            }
          },
          {
            title: this.$t("swap.lastprice") + "/" + this.$t("swap.daychange"),
            key: "rose",
            className: "swap-coin-menu-lastprice",
            sortMethod: function(a, b, type) {
              let a1 = a.replace(/[^\d|.|-]/g, "") - 0;
              let b1 = b.replace(/[^\d|.|-]/g, "") - 0;
              if (type == "asc") {
                return a1 - b1;
              } else {
                return b1 - a1;
              }
            },
            render: (h, params) => {
              const row = params.row;
              const className = parseFloat(row.rose) < 0 ? "sell" : "buy";
              return h("div", {
                  style: {
                    padding: "5px 10px",
                    textAlign: "right",
                    fontSize: "14px"
                  }
                },[
                  h("span", params.row.close),
                  h("br"),
                  h(
                  "span",
                  {
                    attrs: {
                      class: className
                    }
                  },
                  row.rose
                )]);
            }
          }
        ]
      },
      wallet: {
        base: 0.0,
        coin: 0.0
      },
      showMarket: false,
      fixHistoryHeight: 295,
      // rechargeUrl:'#/finance/recharge',
      // rechargeUSDTUrl:'#/finance/recharge?name=USDT',
      form: {
          trigglePrice: "",
          entrustPrice: "",
          volume: ""
      },
      trade: {
        rows: [],
        columns: [
          {
            title: self.$t("swap.price"),
            key: "price",
            render: (h, params) => {
              const row = params.row;
              const className = row.direction == "BUY" ? "buy" : "sell";

              return h(
                "span",
                {
                  attrs: {
                    class: className
                  }
                },
                params.row.price.toFixed(4)
              );
            },
            renderHeader: (h, params) => {
              const title =
                self.$t("swap.price") + "(" + self.currentCoin.base + ")";
              return h("span", {}, title);
            }
          },
          {
            title: self.$t("swap.num"),
            key: "amount",
            render: (h, params) => {
              return h("span", {}, params.row.amount.toFixed(4));
            },
            renderHeader: (h, params) => {
              const title =
                self.$t("swap.num") + "(" + self.currentCoin.coin + ")";
              return h("span", {}, title);
            }
          },
          {
            title: self.$t("swap.time"),
            key: "time",
            render: (h, params) => {
              return h("span", {}, this.timeFormat(params.row.time));
            }
          }
        ]
      },
      //   最新价的 table 数据;
      plate: {
        maxPostion: 10,
        columns: [
          {
            title: self.$t("swap.price"),
            key: "price",
            render: (h, params) => {
              let str = "";
              let price = "";
              const className = params.row.direction.toLowerCase();
              params.row.price == 0 && (str = h("span", {}, "--"));
              params.row.price != 0 &&
                (price = params.row.price.toFixed(4)) &&
                (str = h(
                  "span",
                  {
                    attrs: {
                      class: className
                    }
                  },
                  price
                ));
              return str;
            },
            renderHeader: (h, params) => {
              const title =
                self.$t("swap.price") + "(" + self.currentCoin.base + ")";
              return h("span", {}, title);
            }
          },
          {
            title: self.$t("swap.num"),
            key: "amount",
            render: (h, params) => {
              let str = "";
              params.row.amount == 0 && (str = h("span", {}, "--"));
              params.row.amount != 0 &&
                (str = h(
                  "span",
                  {},
                  params.row.amount.toFixed(4)
                ));
              return str;
            },
            renderHeader: (h, params) => {
              const title =
                self.$t("swap.num") + "(" + self.currentCoin.coin + ")";
              return h("span", {}, title);
            }
          },
          {
            title: self.$t("swap.total"),
            key: "totalAmount",
            render: (h, params) => {
              if (params.row.price == 0 || params.row.totalAmount == 0) {
                return h("span", {}, "--");
              } else {
                return h(
                  "span",
                  {},
                  params.row.totalAmount.toFixed(4)
                );
              }
            },
            renderHeader: (h, params) => {
              const title =
                self.$t("swap.total") + "(" + self.currentCoin.coin + ")";
              return h("span", {}, title);
            }
          },
          {
            className: "percenttd",
            width: 1,
            render: (h, params) => {
              let width = "0",
                backgroundColor =
                  params.row.direction === "BUY" ? "#00b275" : "#f15057",
                totle =
                  params.row.direction === "BUY"
                    ? this.plate.bidTotle
                    : this.plate.askTotle;
              if (params.row.totalAmount) {
                width = (params.row.totalAmount / totle).toFixed(4) * 100 + "%";
              }
              return h(
                "div",
                {
                  style: {
                    width,
                    backgroundColor
                  },
                  attrs: {
                    class: "percentdiv"
                  }
                },
                " "
              );
            }
          }
        ],
        askRows: [],
        bidRows: []
      },
      currentPosition: {
        columns: [
          {
            title: self.$t("swap.pos_leverage"),
            key: "leverage",
            render: (h, params) => {
              if(params.row.direction == "Buy") {
                return h("span", {style:{
                  color:'#FFF',
                  backgroundColor: "#42b983",
                  padding: "3px 5px",
                  borderRadius: "3px",
                  display: "inline-block",
                  width: "80px"
                }}, this.$t("swap.pos_long") + " " + params.row.leverage + "X");
              }else{
                return h("span", {style:{
                  color:'#FFF',
                  backgroundColor: "#FF0000",
                  padding: "3px 5px",
                  borderRadius: "3px",
                  display: "inline-block",
                  width: "80px"
                }}, this.$t("swap.pos_short") + " " + params.row.leverage + "X");
              }
            }
          },
          {
            title: self.$t("swap.pos_Pl"),
            key: "pl",
            render: (h, params) => {
              if(params.row.pl > 0) {
                return h("span", {
                  style:{
                    color:'#42b983'
                  }
                }, "+" + params.row.pl.toFixed(4) + " USDT");
              }
              return h("span", {
                style:{
                    color:'#FF0000'
                  }
              }, params.row.pl.toFixed(4) + " USDT");
            }
          },
          {
            title: self.$t("swap.pos_Ratio"),
            key: "plRatio",
            render: (h, params) => {
              if(params.row.plRatio > 0) {
                return h("span", {
                  style:{
                    color:'#42b983'
                  }
                }, (params.row.plRatio * 100).toFixed(2) + " %");
              }
              return h("span", {
                style:{
                    color:'#FF0000'
                  }
              }, (params.row.plRatio * 100).toFixed(2) + " %");
            }
          },
          {
            title: self.$t("swap.pos_pos"),
            key: "position",
            render: (h, params) => {
              return h("span", {}, params.row.position + this.$t("swap.shareNumber"));
            }
          },
          {
            title: self.$t("swap.pos_canClose"),
            key: "avaPosition",
            render: (h, params) => {
              return h("span", {}, params.row.avaPosition + this.$t("swap.shareNumber"));
            }
          },
          {
            title: self.$t("swap.pos_openPrice"),
            key: "openPrice",
            render: (h, params) => {
              return h("span", {}, params.row.openPrice.toFixed(4) + " USDT");
            }
          },
          {
            title: self.$t("swap.pos_margin"),
            key: "margin",
            render: (h, params) => {
              return h("span", {}, params.row.margin.toFixed(2) + " USDT");
            }
          },
          {
            title: self.$t("swap.pos_mRatio"),
            key: "mRatio",
            render: (h, params) => {
              return h("span", {}, (params.row.mRatio * 100).toFixed(2) + " %");
            }
          }
        ]
      },
      currentOrder: {
        columns: [
          {
            title: self.$t("swap.time"),
            key: "createTime",
            render: (h, params) => {
              return h("span", {}, this.dateFormat(params.row.createTime));
            }
          },
          {
            title: self.$t("swap.ent_entrustType"),
            key: "entrustType",
            render: (h, params) => {
              if(params.row.entrustType == "OPEN") {
                return h("span", {}, this.$t("swap.open"));
              }else{
                return h("span", {}, this.$t("swap.close"));
              }
            }
          },
          {
            title: self.$t("swap.ent_direction"),
            key: "direction",
            render: (h, params) => {
              if(params.row.entrustType == "OPEN") {
                if(params.row.direction == "BUY") {
                  return h("span", {style:{color:"#42b983"}}, this.$t("swap.openup"));
                }else{
                  return h("span", {style:{color:"#FF0000"}}, this.$t("swap.opendown"));
                }
              }else{
                if(params.row.direction == "BUY") {
                  return h("span", {style:{color:"#42b983"}}, this.$t("swap.closeup"));
                }else{
                  return h("span", {style:{color:"#FF0000"}}, this.$t("swap.closedown"));
                }
              }
            }
          },
          {
            title: self.$t("swap.ent_type"),
            key: "type",
            render: (h, params) => {
              if(params.row.type == "LIMIT_PRICE") {
                return h("span", {}, this.$t("swap.limited_price"));
              }else if(params.row.type == "MARKET_PRICE"){
                return h("span", {}, this.$t("swap.market_price"));
              }else{
                return h("span", {}, this.$t("swap.spot_price"));
              }
            }
          },
          {
            title: self.$t("swap.ent_trigglePrice"),
            key: "trigglePrice",
            render: (h, params) => {
              if(params.row.type == "SPOT_LIMIT") {
                return h("span", {}, params.row.triggerPrice.toFixed(2));
              }else{
                return h("span", {}, "--");
              }
            }
          },
          {
            title: self.$t("swap.ent_entrustPrice"),
            key: "entrustPrice",
            render: (h, params) => {
              return h("span", {}, params.row.entrustPrice.toFixed(2));
            }
          },
          {
            title: self.$t("swap.ent_tradedPrice"),
            key: "tradedPrice",
            render: (h, params) => {
              return h("span", {}, params.row.tradedPrice.toFixed(2));
            }
          },
          {
            title: self.$t("swap.ent_margin"),
            key: "principalAmount",
            render: (h, params) => {
              return h("span", {}, params.row.principalAmount.toFixed(2) + "USDT");
            }
          },
          {
            title: self.$t("swap.ent_volume"),
            key: "volume",
            render: (h, params) => {
              return h("span", {}, params.row.volume + this.$t("swap.shareNumber"));
            }
          },
          {
            title: self.$t("swap.ent_status"),
            key: "status",
            render: (h, params) => {
              if(params.row.status == "ENTRUST_ING"){
                return h("span", {}, this.$t("swap.ent_status1"));
              }else if(params.row.status == "ENTRUST_CANCEL"){
                return h("span", {}, this.$t("swap.ent_status2"));
              }else if(params.row.status == "ENTRUST_FAILURE"){
                return h("span", {}, this.$t("swap.ent_status3"));
              }else if(params.row.status == "ENTRUST_SUCCESS"){
                return h("span", {}, this.$t("swap.ent_status4"));
              }else{}
            }
          },
          {
            title: self.$t("swap.action"),
            key: "operate",
            width: 110,
            render: (h, params) => {
              return h(
                "Button",
                {
                  props: {
                    size: "small"
                  },
                  style: {
                    color: "#f1ac19",
                    "line-height": "1.2",
                    backgroundColor: "transparent"
                  },
                  on: {
                    click: () => {
                      this.cancel(params.row.id);
                    }
                  }
                },
                self.$t("swap.undo")
              );
            }
          }
        ],
        rows: []
      },
      historyOrder: {
        pageSize: 10,
        total: 10,
        page: 0,
        columns: [
          {
            title: self.$t("swap.time"),
            key: "createTime",
            render: (h, params) => {
              return h("span", {}, this.dateFormat(params.row.createTime));
            }
          },
          {
            title: self.$t("swap.ent_entrustType"),
            key: "entrustType",
            render: (h, params) => {
              if(params.row.entrustType == "OPEN") {
                return h("span", {}, this.$t("swap.open"));
              }else{
                return h("span", {}, this.$t("swap.close"));
              }
            }
          },
          {
            title: self.$t("swap.ent_direction"),
            key: "direction",
            render: (h, params) => {
              if(params.row.entrustType == "OPEN") {
                if(params.row.direction == "BUY") {
                  return h("span", {style:{color:"#42b983"}}, this.$t("swap.openup"));
                }else{
                  return h("span", {style:{color:"#FF0000"}}, this.$t("swap.opendown"));
                }
              }else{
                if(params.row.direction == "BUY") {
                  return h("span", {style:{color:"#42b983"}}, this.$t("swap.closeup"));
                }else{
                  return h("span", {style:{color:"#FF0000"}}, this.$t("swap.closedown"));
                }
              }
            }
          },
          {
            title: self.$t("swap.ent_type"),
            key: "type",
            render: (h, params) => {
              if(params.row.isFromSpot == 1) {
                return h("span", {}, this.$t("swap.spot_price"));
              }
              if(params.row.type == "LIMIT_PRICE") {
                return h("span", {}, this.$t("swap.limited_price"));
              }else if(params.row.type == "MARKET_PRICE"){
                return h("span", {}, this.$t("swap.market_price"));
              }else{
                return h("span", {}, this.$t("swap.spot_price"));
              }
            }
          },
          {
            title: self.$t("swap.ent_trigglePrice"),
            key: "trigglePrice",
            render: (h, params) => {
              if(params.row.isFromSpot == 1) {
                return h("span", {}, params.row.triggerPrice.toFixed(2));
              }
              if(params.row.type == "SPOT_PRICE") {
                return h("span", {}, params.row.triggerPrice.toFixed(2));
              }else{
                return h("span", {}, "--");
              }
            }
          },
          {
            title: self.$t("swap.ent_entrustPrice"),
            key: "entrustPrice",
            render: (h, params) => {
              return h("span", {}, params.row.entrustPrice.toFixed(2));
            }
          },
          {
            title: self.$t("swap.ent_tradedPrice"),
            key: "tradedPrice",
            render: (h, params) => {
              return h("span", {}, params.row.tradedPrice.toFixed(2));
            }
          },
          {
            title: self.$t("swap.ent_margin"),
            key: "principalAmount",
            render: (h, params) => {
              return h("span", {}, params.row.principalAmount.toFixed(2) + "USDT");
            }
          },
          {
            title: self.$t("swap.ent_volume"),
            key: "volume",
            render: (h, params) => {
              return h("span", {}, params.row.volume + this.$t("swap.shareNumber"));
            }
          },
          {
            title: self.$t("swap.ent_fee"),
            key: "volume",
            render: (h, params) => {
              if(params.row.status != "ENTRUST_CANCEL") {
                if(params.row.entrustType == "OPEN"){
                  return h("span", {}, params.row.openFee);  
                }
                if(params.row.entrustType == "CLOSE"){
                  return h("span", {}, params.row.closeFee);  
                }
              }else{
                return h("span", {}, "--");
              }
            }
          },
          {
            title: self.$t("swap.ent_pl"),
            key: "profitAndLoss",
            render: (h, params) => {
              if(params.row.entrustType == "CLOSE"){
                if(params.row.profitAndLoss < 0){
                  return h("span", {style:{color:"#FF0000"}}, params.row.profitAndLoss.toFixed(2));
                }else{
                  return h("span", {style:{color:"#42b983"}}, params.row.profitAndLoss.toFixed(2));
                }
              }else{
                return h("span", {}, "--");
              }
            }
          },
          {
            title: self.$t("swap.ent_status"),
            key: "volume",
            render: (h, params) => {
              if(params.row.isBlast == 1){
                return h("span", {}, this.$t("swap.ent_statusblast"));
              }
              if(params.row.status == "ENTRUST_CANCEL") {
                return h("span", {}, this.$t("swap.ent_status2"));
              } else if(params.row.status == "ENTRUST_FAILURE") {
                return h("span", {}, this.$t("swap.ent_status3"));
              } else if(params.row.status == "ENTRUST_SUCCESS") {
                return h("span", {}, this.$t("swap.ent_status4"));
              }
            }
          }
        ],
        rows: []
      },
      fullTrade: {}
    };
  },
  filters: {

  },
  isLogin: function() {
      return this.$store.getters.isLogin;
    },
    member: function() {
      return this.$store.getters.member;
    },
    lang: function() {
      return this.$store.state.lang;
    },
    profitAndLoss: function() {
      if(!this.isLogin || this.contractWalletInfo == null) {
        return 0;
      }
      var buyPL = 0;
      if(this.contractWalletInfo.usdtBuyPrice > 0){
        buyPL = (this.currentCoin.price / this.contractWalletInfo.usdtBuyPrice -1) * (this.contractWalletInfo.usdtBuyPosition + this.contractWalletInfo.usdtFrozenBuyPosition) * this.contractWalletInfo.usdtShareNumber;
      }
      var sellPL = 0;
      if(this.contractWalletInfo.usdtSellPrice > 0){
        sellPL = (1 - this.currentCoin.price / this.contractWalletInfo.usdtSellPrice) * (this.contractWalletInfo.usdtSellPosition + this.contractWalletInfo.usdtFrozenSellPosition) * this.contractWalletInfo.usdtShareNumber;
      }

      return (buyPL + sellPL);
    },
    totalQuanyi: function() {
      if(!this.isLogin || this.contractWalletInfo == null) {
        return 0;
      }
      var buyPL = 0;
      if(this.contractWalletInfo.usdtBuyPrice > 0){
        buyPL = (this.currentCoin.price / this.contractWalletInfo.usdtBuyPrice -1) * (this.contractWalletInfo.usdtBuyPosition + this.contractWalletInfo.usdtFrozenBuyPosition) * this.contractWalletInfo.usdtShareNumber;
      }
      var sellPL = 0;
      if(this.contractWalletInfo.usdtSellPrice > 0){
        sellPL = (1 - this.currentCoin.price / this.contractWalletInfo.usdtSellPrice) * (this.contractWalletInfo.usdtSellPosition + this.contractWalletInfo.usdtFrozenSellPosition) * this.contractWalletInfo.usdtShareNumber;
      }

      return (buyPL + sellPL + this.contractWalletInfo.usdtBalance + this.contractWalletInfo.usdtFrozenBalance + this.contractWalletInfo.usdtBuyPrincipalAmount + this.contractWalletInfo.usdtSellPrincipalAmount);
    },
    assetRate: function(){
      if(!this.isLogin || this.contractWalletInfo == null) return 0;
      var zhanyong =  this.contractWalletInfo.usdtFrozenBalance + this.contractWalletInfo.usdtBuyPrincipalAmount + this.contractWalletInfo.usdtSellPrincipalAmount;
      if(this.totalQuanyi > 0) {
        return zhanyong / this.totalQuanyi;
      }else{
        return 0;
      }
    },
    avaOpenBuy: function(){ // 可开多
      if(!this.isLogin || this.contractWalletInfo == null) return 0;
      // 计算可开多/空
      if(this.contractWalletInfo.usdtPattern == "FIXED") { // 逐仓，直接usdt余额即可
        return parseInt(this.buyLeverage * this.contractWalletInfo.usdtBalance / this.contractCoinInfo.shareNumber);
      }else{ // 全仓模式
        // 计算多仓盈亏
        var buyPL = 0;
        if(this.contractWalletInfo.usdtBuyPrice > 0){
          // 多仓盈亏（当前价格 / 开仓均价 - 1）* （可用多单仓位 + 冻结多单仓位） * 合约面值
          buyPL = (this.currentCoin.price / this.contractWalletInfo.usdtBuyPrice -1) * (this.contractWalletInfo.usdtBuyPosition + this.contractWalletInfo.usdtFrozenBuyPosition) * this.contractWalletInfo.usdtShareNumber;
        }
        // 计算空仓盈亏
        var sellPL = 0;
        if(this.contractWalletInfo.usdtSellPrice > 0){
          // 空仓盈亏（1 - 当前价格 / 开仓均价）* （可用空单仓位 + 冻结空单仓位） * 合约面值
          sellPL = (1 - this.currentCoin.price / this.contractWalletInfo.usdtSellPrice) * (this.contractWalletInfo.usdtSellPosition + this.contractWalletInfo.usdtFrozenSellPosition) * this.contractWalletInfo.usdtShareNumber;
        }
        // 计算盈亏（多仓盈亏 + 空仓盈亏 + 多仓保证金 + 空仓保证金）
        var lossandp = buyPL + sellPL + this.contractWalletInfo.usdtBuyPrincipalAmount + this.contractWalletInfo.usdtSellPrincipalAmount;
        // 大于0，说明多单和空单还没亏完，直接用可用余额计算能开多少单（计算方法 = 多仓杠杆 * 可用余额 / 合约面值）
        if(lossandp >= 0) {
          return parseInt(this.buyLeverage * this.contractWalletInfo.usdtBalance / this.contractCoinInfo.shareNumber);
        }else{
          // 小于0，因为是全仓，所以需要考虑盈亏问题，计算方法（多仓杠杆 *（可用余额 - 亏损金额）/ 合约面值）
          return parseInt(this.buyLeverage * (this.contractWalletInfo.usdtBalance + lossandp) / this.contractCoinInfo.shareNumber);
        }
      }
    },
    avaOpenSell: function(){ // 可开空
      if(!this.isLogin || this.contractWalletInfo == null) return 0;
      // 计算可开多/空
      if(this.contractWalletInfo.usdtPattern == "FIXED") { // 逐仓，直接usdt余额即可
        return parseInt(this.sellLeverage * this.contractWalletInfo.usdtBalance / this.contractCoinInfo.shareNumber);
      }else{
        // 计算可用
        var buyPL = 0;
        if(this.contractWalletInfo.usdtBuyPrice > 0){
          buyPL = (this.currentCoin.price / this.contractWalletInfo.usdtBuyPrice -1) * (this.contractWalletInfo.usdtBuyPosition + this.contractWalletInfo.usdtFrozenBuyPosition) * this.contractWalletInfo.usdtShareNumber;
        }
        var sellPL = 0;
        if(this.contractWalletInfo.usdtSellPrice > 0){
          sellPL = (1 - this.currentCoin.price / this.contractWalletInfo.usdtSellPrice) * (this.contractWalletInfo.usdtSellPosition + this.contractWalletInfo.usdtFrozenSellPosition) * this.contractWalletInfo.usdtShareNumber;
        }
        var lossandp = buyPL + sellPL + this.contractWalletInfo.usdtBuyPrincipalAmount + this.contractWalletInfo.usdtSellPrincipalAmount;
        if(lossandp >= 0) {
          return parseInt(this.sellLeverage * this.contractWalletInfo.usdtBalance / this.contractCoinInfo.shareNumber);
        }else{
          return parseInt(this.sellLeverage * (this.contractWalletInfo.usdtBalance - lossandp) / this.contractCoinInfo.shareNumber);
        }
      }
    },
    positionList: function(){
      if(this.contractWalletInfo.id == 0 || this.currentPositionList.length == 0) {
        return;
      }
      var buyPl = 0;
      // 构建多仓数据
      if(this.contractWalletInfo.usdtBuyPosition > 0 || this.contractWalletInfo.usdtFrozenBuyPosition > 0) {
        buyPl = (this.currentCoin.price / this.contractWalletInfo.usdtBuyPrice - 1) * (this.contractWalletInfo.usdtBuyPosition + this.contractWalletInfo.usdtFrozenBuyPosition) * this.contractWalletInfo.usdtShareNumber;
        this.$set(this.currentPositionList[0], "direction", "Buy");
        this.$set(this.currentPositionList[0], "openPrice", this.contractWalletInfo.usdtBuyPrice);
        this.$set(this.currentPositionList[0], "pl", buyPl);
        this.$set(this.currentPositionList[0], "position", this.contractWalletInfo.usdtBuyPosition + this.contractWalletInfo.usdtFrozenBuyPosition);
        this.$set(this.currentPositionList[0], "avaPosition", this.contractWalletInfo.usdtBuyPosition);
        this.$set(this.currentPositionList[0], "margin", this.contractWalletInfo.usdtBuyPrincipalAmount);
        this.$set(this.currentPositionList[0], "leverage", this.contractWalletInfo.usdtBuyLeverage);
        this.$set(this.currentPositionList[0], "plRatio", buyPl / this.contractWalletInfo.usdtBuyPrincipalAmount);
        this.$set(this.currentPositionList[0], "mRatio", (buyPl + this.contractWalletInfo.usdtBuyPrincipalAmount) / this.contractWalletInfo.usdtBuyPrincipalAmount / this.contractWalletInfo.usdtBuyLeverage);
        this.$set(this.currentPositionList[0], "cmRatio", this.contractCoinInfo.maintenanceMarginRate);

        if(this.contractWalletInfo.usdtPattern == "FIXED") {
          if(buyPl + this.contractWalletInfo.usdtBuyPrincipalAmount < 0 && this.checkBuyCount < 5) {
            this.getMemberContractWallet();
            this.checkBuyCount++;
          }
        }
      }
      // 构建空仓数据
      var sellPl = 0;
      if(this.contractWalletInfo.usdtSellPosition > 0 || this.contractWalletInfo.usdtFrozenSellPosition > 0) {
        sellPl = (1 - this.currentCoin.price / this.contractWalletInfo.usdtSellPrice) * (this.contractWalletInfo.usdtSellPosition + this.contractWalletInfo.usdtFrozenSellPosition) * this.contractWalletInfo.usdtShareNumber;
        var temIndex = 0;
        if(this.currentPositionList.length > 1) {
          temIndex = 1;
        }
        this.$set(this.currentPositionList[temIndex], "direction", "Sell");
        this.$set(this.currentPositionList[temIndex], "openPrice", this.contractWalletInfo.usdtSellPrice);
        this.$set(this.currentPositionList[temIndex], "pl", sellPl);
        this.$set(this.currentPositionList[temIndex], "position", this.contractWalletInfo.usdtSellPosition + this.contractWalletInfo.usdtFrozenSellPosition);
        this.$set(this.currentPositionList[temIndex], "avaPosition", this.contractWalletInfo.usdtSellPosition);
        this.$set(this.currentPositionList[temIndex], "margin", this.contractWalletInfo.usdtSellPrincipalAmount);
        this.$set(this.currentPositionList[temIndex], "leverage", this.contractWalletInfo.usdtSellLeverage);
        this.$set(this.currentPositionList[temIndex], "plRatio", sellPl / this.contractWalletInfo.usdtSellPrincipalAmount);
        this.$set(this.currentPositionList[temIndex], "mRatio", (sellPl + this.contractWalletInfo.usdtSellPrincipalAmount) / this.contractWalletInfo.usdtSellPrincipalAmount / this.contractWalletInfo.usdtSellLeverage);
        this.$set(this.currentPositionList[temIndex], "cmRatio", this.contractCoinInfo.maintenanceMarginRate);

        if(this.contractWalletInfo.usdtPattern == "FIXED") {
          if(sellPl + this.contractWalletInfo.usdtSellPrincipalAmount <= 0 && this.checkSellCount < 5) {
            this.getMemberContractWallet();
            this.checkSellCount++;
          }
        }
      }
      // 可能全仓模式已爆仓
      if(this.contractWalletInfo.usdtPattern == "CROSSED") {
        if(buyPl + sellPl + this.contractWalletInfo.usdtBalance + this.contractWalletInfo.usdtFrozenBalance + this.contractWalletInfo.usdtBuyPrincipalAmount + this.contractWalletInfo.usdtSellPrincipalAmount < 0 && this.checkAllCount < 5) {
          this.getMemberContractWallet();
          this.checkAllCount++;
        }
      }
      return this.currentPositionList;
  },
  watch: {
    lang: function() {
      this.updateLangData();
    },
    $route(to, from) {
      this.init();
    }
  },
  created: function() {
    this.init();
  },
  filters:{
    dateFormat: function(tick) {
      return moment(tick).format("MM-DD HH:mm");
    },
    fixed2: function(value){
      return value.toFixed(2);
    },
    fixed4: function(value){
      return value.toFixed(4);
    },
    percent: function(value){
      return (value * 100).toFixed(2);
    }
  },
  methods: {
    seachInputChange(){
      this.searchKey = this.searchKey.toUpperCase();
      this.dataIndex = this.coins.USDT.filter(item => item["coin"].indexOf(this.searchKey) == 0);
    },
    silderGo(silder, val) {
      this[silder] = val;
    },
    init() {
      var params = this.$route.params.pair;
      if (params == undefined) {
        this.$router.push("/swap/" + this.defaultPath);
        params = this.defaultPath;
      }
      const basecion = params.split("_")[1];
      if (basecion) {
        this.basecion = basecion;
      }
      var coin = params.toUpperCase().split("_")[0];
      var base = params.toUpperCase().split("_")[1];
      this.currentCoin.symbol = coin + "/" + base;
      this.currentCoin.coin = coin;
      this.currentCoin.base = base;
      this.$store.commit("navigate", "nav-swap");
      this.$store.commit("setSkin", this.skin);
      this.currentPositionList = new Array();
      this.getCNYRate();
      this.getSymbolInfo();
      this.getSymbolThumb(); //包含 K线图、getFavor、startWebsock等
      this.getPlate(); //买卖盘
      this.getPlateFull();
      this.sliderOpenPercent = 0;
      this.sliderClosePercent = 0;

    },
    tipFormat(val) {
      return val + "%";
    },
    changeBaseCion(str) {
      this.basecion = str;
      this.dataIndex = this.coins.USDT;
    },
    changePlate(str) {
      if (str != "all") {
        this.plate.maxPostion = 20;
      } else {
        this.plate.maxPostion = 10;
      }
      this.getPlate(str);
    },
    changeImgTable(str) {
      this.currentImgTable = str;
    },
    changeOrder(str) {
      this.selectedOrder = str;
    },
    entrustChange(val){
      this.entrustType = val;
    },
    setback() {
      var obk = document.getElementsByClassName("container")[0];
      var height = 0;
      var doc = document;
      window.innerHeight && (height = window.innerHeight);
      !window.innerHeight &&
        doc.body.clientHeight &&
        (height = doc.body.clientHeight);
      !window.innerHeight &&
        !doc.body.clientHeight &&
        doc.documentElement.clientHeight &&
        (height = doc.documentElement.clientHeight);
      obk.style.minHeight = height - 100 + "px";
    },
    updateLangData() {

      this.trade.columns[0].title = this.$t("swap.num");
      this.trade.columns[1].title = this.$t("swap.price");
      this.trade.columns[2].title = this.$t("swap.time");

      this.plate.columns[0].title = this.$t("swap.stall");
      this.plate.columns[1].title = this.$t("swap.price");
      this.plate.columns[2].title = this.$t("swap.num");
      this.plate.columns[3].title = this.$t("swap.total");

      this.currentOrder.columns[1].title = this.$t("swap.time");
      this.currentOrder.columns[2].title = this.$t("swap.symbol");
      this.currentOrder.columns[3].title = this.$t("swap.type");
      this.currentOrder.columns[4].title = this.$t("swap.direction");
      this.currentOrder.columns[5].title = this.$t("swap.price");
      this.currentOrder.columns[6].title = this.$t("swap.num");
      this.currentOrder.columns[7].title = this.$t("swap.traded");
      this.currentOrder.columns[8].title = this.$t("swap.dealamount");
      this.currentOrder.columns[9].title = this.$t("swap.action");

      this.historyOrder.columns[1].title = this.$t("swap.time");
      this.historyOrder.columns[2].title = this.$t("swap.symbol");
      this.historyOrder.columns[3].title = this.$t("swap.type");
      this.historyOrder.columns[4].title = this.$t("swap.direction");
      this.historyOrder.columns[5].title = this.$t("swap.price");
      this.historyOrder.columns[6].title = this.$t("swap.num");
      this.historyOrder.columns[7].title = this.$t("swap.done");
      this.historyOrder.columns[8].title = this.$t("swap.dealamount");
      this.historyOrder.columns[9].title = this.$t("swap.status");

      // window.tvWidget.options.time_frames[0].title = this.$t("swap.realtime");
    },
    getCNYRate() {
      this.$http
        .post(this.host + "/market/exchange-rate/usd-cny")
        .then(response => {
          var resp = response.body;
          this.CNYRate = resp.data;
        });
    },
    getCoin(symbol) {
      return this.coins._map[symbol];
    },
    getKline() {
      var that = this;
      let config = {
        autosize: true,
        height: 400,
        fullscreen: true,
        symbol: that.symbol,
        interval: "5", // 默认K线周期
        timezone: this.getTimezone4K(),
        toolbar_bg: "#192330",
        container_id: "kline_container",
        datafeed: that.datafeed,
        library_path:
          process.env.NODE_ENV === "production"
            ? "/assets/charting_library/"
            : "/src/assets/js/charting_library/",
        locale: this.getLang4K(),
        debug: false,
        drawings_access: {
          type: "black",
          tools: [{ name: "Regression Trend" }]
        },
        disabled_features: [
          "header_resolutions",
          "timeframes_toolbar",
          "header_symbol_search",
          "header_chart_type",
          "header_compare",
          "header_undo_redo",
          "header_screenshot",
          "header_saveload",
          //"use_localstorage_for_settings",
          //"left_toolbar",
          "volume_force_overlay",
		  "widget_logo",
		  "compare_symbol", 
		  "display_market_status",
		  "go_to_date", 
		  "header_interval_dialog_button", 
		  "legend_context_menu", 
		  "show_hide_button_in_legend",
		  "show_interval_dialog_on_key_press", 
		  "snapshot_trading_drawings", 
		  "symbol_info", 
		  //"header_widget", 
		  "edit_buttons_in_legend",
		  "context_menus", 
		  "control_bar", 
		  "border_around_the_chart"
        ],
        enabled_features: [
		  "disable_resolution_rebuild",
		  "keep_left_toolbar_visible_on_small_screens", //防止左侧工具栏在小屏幕上消失
		  "hide_last_na_study_output",
		  "move_logo_to_main_pane",
		  "dont_show_boolean_study_arguments",
		  "use_localstorage_for_settings",
		  "remove_library_container_border",
		  "save_chart_properties_to_local_storage",
		  "side_toolbar_in_fullscreen_mode",
		  "constraint_dialogs_movement",
		  "hide_left_toolbar_by_default",
		  "left_toolbar",
		  "same_data_requery",
		  "header_in_fullscreen_mode"
        ],
		//成交量样式
         studies_overrides: {
             "volume.volume.color.0": "#fa5252",
             "volume.volume.color.1": "#12b886",
             "volume.volume.transparency": 25,
        },
        custom_css_url: "bundles/common.css",
        supported_resolutions: ["1", "5", "15", "30", "60", "4H", "1D", "1W", "1M"],
        charts_storage_url: "http://saveload.tradingview.com",
        charts_storage_api_version: "1.1",
        client_id: "tradingview.com",
        user_id: "public_user_id",
        overrides: {
          "paneProperties.background": "#192330",
          "paneProperties.vertGridProperties.color": "rgba(0,0,0,.1)",
          "paneProperties.horzGridProperties.color": "rgba(0,0,0,.1)",
          //"scalesProperties.textColor" : "#AAA",
          "scalesProperties.textColor": "#61688A",
          "mainSeriesProperties.candleStyle.upColor": "#00b275",
          "mainSeriesProperties.candleStyle.downColor": "#f15057",
          "mainSeriesProperties.candleStyle.drawBorder": false,
          "mainSeriesProperties.candleStyle.wickUpColor": "#589065",
          "mainSeriesProperties.candleStyle.wickDownColor": "#AE4E54",
          "paneProperties.legendProperties.showLegend": false,

          "mainSeriesProperties.areaStyle.color1": "rgba(71, 78, 112, 0.5)",
          "mainSeriesProperties.areaStyle.color2": "rgba(71, 78, 112, 0.5)",
          "mainSeriesProperties.areaStyle.linecolor": "#9194a4",
          "volumePaneSize": "small"
        },
        time_frames: [
          {
            text: "1min",
            resolution: "1",
            description: "realtime",
            title: that.$t("swap.realtime")
          },
          { text: "1min", resolution: "1", description: "1min" },
          { text: "5min", resolution: "5", description: "5min" },
          { text: "15min", resolution: "15", description: "15min" },
          { text: "30min", resolution: "30", description: "30min" },
          { text: "1hour", resolution: "60", description: "1hour", title: "1hour" },
          { text: "4hour", resolution: "240", description: "4hour",title: "4hour" },
          { text: "1day", resolution: "1D",	description: "1day",title: "1day"},
          { text: "1week",resolution: "1W",description: "1week",title: "1week"},
          { text: "1mon", resolution: "1M", description: "1mon" }
        ]
      };
      if (that.skin === "day") {
        config.toolbar_bg = "#fff";
        config.custom_css_url = "bundles/common_day.css";
        config.overrides["paneProperties.background"] = "#fff";
        config.overrides["mainSeriesProperties.candleStyle.upColor"] =
          "#a6d3a5";
        config.overrides["mainSeriesProperties.candleStyle.downColor"] =
          "#ffa5a6";
      }
      require(["@js/charting_library/charting_library.min.js"], function(tv) {
        var widget = (window.tvWidget = new TradingView.widget(config));
        widget.onChartReady(function() {
          widget.chart().executeActionById("drawingToolbarAction");
          widget
            .chart()
            .createStudy("Moving Average", false, false, [5], null, {
              "plot.color": "#EDEDED"
            });
          widget
            .chart()
            .createStudy("Moving Average", false, false, [10], null, {
              "plot.color": "#ffe000"
            });
          widget
            .chart()
            .createStudy("Moving Average", false, false, [30], null, {
              "plot.color": "#ce00ff"
            });
          widget
            .chart()
            .createStudy("Moving Average", false, false, [60], null, {
              "plot.color": "#00adff"
            });
          widget
            .createButton()
            .attr("title", "realtime")
            .on("click", function() {
              if ($(this).hasClass("selected")) return;
              $(this)
                .addClass("selected")
                .parent(".group")
                .siblings(".group")
                .find(".button.selected")
                .removeClass("selected");
              widget.chart().setChartType(3);
              widget.setSymbol("", "1");
            })
            .append("<span>Time</span>");

          widget
            .createButton()
            .attr("title", "M1")
            .on("click", function() {
              if ($(this).hasClass("selected")) return;
              $(this)
                .addClass("selected")
                .parent(".group")
                .siblings(".group")
                .find(".button.selected")
                .removeClass("selected");
              widget.chart().setChartType(1);
              widget.setSymbol("", "1");
            })
            .append("<span>M1</span>");

          widget
            .createButton()
            .attr("title", "M5")
            .on("click", function() {
              if ($(this).hasClass("selected")) return;
              $(this)
                .addClass("selected")
                .parent(".group")
                .siblings(".group")
                .find(".button.selected")
                .removeClass("selected");
              widget.chart().setChartType(1);
              widget.setSymbol("", "5");
            })
            .append("<span>M5</span>")
			.addClass("selected");
			
          widget
            .createButton()
            .attr("title", "M15")
            .on("click", function() {
              if ($(this).hasClass("selected")) return;
              $(this)
                .addClass("selected")
                .parent(".group")
                .siblings(".group")
                .find(".button.selected")
                .removeClass("selected");
              widget.chart().setChartType(1);
              widget.setSymbol("", "15");
            })
            .append("<span>M15</span>");

          widget
            .createButton()
            .attr("title", "M30")
            .on("click", function() {
              if ($(this).hasClass("selected")) return;
              $(this)
                .addClass("selected")
                .parent(".group")
                .siblings(".group")
                .find(".button.selected")
                .removeClass("selected");
              widget.chart().setChartType(1);
              widget.setSymbol("", "30");
            })
            .append("<span>M30</span>");

          widget
            .createButton()
            .attr("title", "H1")
            .on("click", function() {
              if ($(this).hasClass("selected")) return;
              $(this)
                .addClass("selected")
                .parent(".group")
                .siblings(".group")
                .find(".button.selected")
                .removeClass("selected");
              widget.chart().setChartType(1);
              widget.setSymbol("", "60");
            })
            .append("<span>H1</span>");
            

          widget
            .createButton()
            .attr("title", "H4")
            .on("click", function() {
              if ($(this).hasClass("selected")) return;
              $(this)
                .addClass("selected")
                .parent(".group")
                .siblings(".group")
                .find(".button.selected")
                .removeClass("selected");
              widget.chart().setChartType(1);
              widget.setSymbol("", "240");
            })
            .append("<span>H4</span>");

          widget
            .createButton()
            .attr("title", "D1")
            .on("click", function() {
              if ($(this).hasClass("selected")) return;
              $(this)
                .addClass("selected")
                .parent(".group")
                .siblings(".group")
                .find(".button.selected")
                .removeClass("selected");
              widget.chart().setChartType(1);
              widget.setSymbol("", "1D");
            })
            .append("<span>D1</span>");

          widget
            .createButton()
            .attr("title", "W1")
            .on("click", function() {
              if ($(this).hasClass("selected")) return;
              $(this)
                .addClass("selected")
                .parent(".group")
                .siblings(".group")
                .find(".button.selected")
                .removeClass("selected");
              widget.chart().setChartType(1);
              widget.setSymbol("", "1W");
            })
            .append("<span>W1</span>");

          widget
            .createButton()
            .attr("title", "M1")
            .on("click", function() {
              if ($(this).hasClass("selected")) return;
              $(this)
                .addClass("selected")
                .parent(".group")
                .siblings(".group")
                .find(".button.selected")
                .removeClass("selected");
              widget.chart().setChartType(1);
              widget.setSymbol("", "1M");
            })
            .append("<span>M1</span>");
        });
      });
    },
    getSymbolThumb() {
      this.$http.post(this.host + this.api.swap.thumb, {}).then(response => {
        var resp = response.body;
        //先清空已有数据
        for (var i = 0; i < resp.length; i++) {
          var coin = resp[i];
          coin.base = resp[i].symbol.split("/")[1];
          this.coins[coin.base] = [];
          this.coins[coin.base + "2"] = [];
          this.coins._map = [];
        }
        for (var i = 0; i < resp.length; i++) {
          var coin = resp[i];
          coin.price = resp[i].close = resp[i].close.toFixed(
            this.baseCoinScale
          );
          coin.rose =
            resp[i].chg > 0
              ? "+" + (resp[i].chg * 100).toFixed(2) + "%"
              : (resp[i].chg * 100).toFixed(2) + "%";
          coin.coin = resp[i].symbol.split("/")[0];
          coin.base = resp[i].symbol.split("/")[1];
          coin.href = (coin.coin + "_" + coin.base).toLowerCase();
          coin.isFavor = false;
          this.coins._map[coin.symbol] = coin;
          if(coin.zone == 0){
            this.coins[coin.base].push(coin);
          }else{
            this.coins[coin.base + "2"].push(coin);
          }
          if (coin.symbol == this.currentCoin.symbol) {
            this.currentCoin = coin;
            this.form.entrustPrice = coin.price;
          }
        }
        require(["../../assets/js/swap.js"], function(e) {
          e.clickScTab();
        });
        this.startWebsock();
        this.changeBaseCion(this.basecion);
      });
    },
    getSymbolInfo() {
      //获取精度
      this.$http
        .post(this.host + this.api.swap.symbolInfo, {
          symbol: this.currentCoin.symbol
        })
        .then(response => {
          var resp = response.body;
          if (resp != null) {
            this.currentCoin.coinScale = resp.coinScale;
            this.currentCoin.baseCoinScale = resp.baseCoinScale;

            this.baseCoinScale = resp.baseCoinScale;
            this.coinScale = resp.coinScale;
            this.symbolFee = resp.fee;

            this.enableMarketBuy = resp.enableMarketBuy;
            this.enableMarketSell = resp.enableMarketSell;

            this.exchangeable = resp.exchangeable;

            this.contractCoinInfo = resp;
            if(this.contractCoinInfo.leverageType == 1) {
              this.leverageList = this.contractCoinInfo.leverage.split(",");
            }
            if(this.isLogin) {
              this.getMemberContractWallet(); // 获取用户合约资产信息
            }
          }
        });
    },
    getMemberContractWallet() {
      //获取精度
      this.$http
        .post(this.host + this.api.swap.contractWallet, {
          contractCoinId: this.contractCoinInfo.id
        }).then(response => {
          var resp = response.body;
          if (resp != null) {
            this.contractWalletInfo = resp.data;
            if(this.contractWalletInfo == null) {
              return;
            }
            if(this.contractWalletInfo.usdtPattern == "FIXED"){
              this.marginMode = "1";
            }else{
              this.marginMode = "0";
            }
            this.buyLeverage = this.contractWalletInfo.usdtBuyLeverage + "";
            this.sellLeverage = this.contractWalletInfo.usdtSellLeverage + "";
            // 默认杠杆如不在范围内
            var levArr = this.contractCoinInfo.leverage.split(",");
            if(Number(this.buyLeverage) < Number(levArr[0])) {
              this.buyLeverage = levArr[0];
            }
            if(Number(this.sellLeverage) < Number(levArr[0])) {
              this.sellLeverage = levArr[0];
            }
            if(Number(this.buyLeverage) > Number(levArr[levArr.length - 1])) {
              this.buyLeverage = levArr[levArr.length - 1];
            }
            if(Number(this.sellLeverage) > Number(levArr[levArr.length - 1])) {
              this.sellLeverage = levArr[levArr.length - 1];
            }

            // 构建多仓数据
            if(this.contractWalletInfo.usdtBuyPosition > 0 || this.contractWalletInfo.usdtFrozenBuyPosition > 0) {
              var buyPL = (this.currentCoin.price / this.contractWalletInfo.usdtBuyPrice -1) * (this.contractWalletInfo.usdtBuyPosition + this.contractWalletInfo.usdtFrozenBuyPosition) * this.contractWalletInfo.usdtShareNumber;

              var tItem = {
                direction: "Buy",
                openPrice: this.contractWalletInfo.usdtBuyPrice, // 开仓价格
                pl: buyPL, // 盈亏
                position: this.contractWalletInfo.usdtBuyPosition + this.contractWalletInfo.usdtFrozenBuyPosition, // 总仓位
                avaPosition: this.contractWalletInfo.usdtBuyPosition, // 可用仓位
                margin: this.contractWalletInfo.usdtBuyPrincipalAmount, // 保证金
                leverage: this.contractWalletInfo.usdtBuyLeverage, // 多仓杠杆
                plRatio: buyPL / this.contractWalletInfo.usdtBuyPrincipalAmount,
                mRatio: (buyPL + this.contractWalletInfo.usdtBuyPrincipalAmount) / this.contractWalletInfo.usdtBuyPrincipalAmount / this.contractWalletInfo.usdtBuyLeverage,
                cmRatio: this.contractCoinInfo.maintenanceMarginRate
              };
              this.currentPositionList.splice(1);
              this.$set(this.currentPositionList, 0, tItem);
            }else{
              this.currentPositionList.splice(0, 1);
            }

            if(this.contractWalletInfo.usdtSellPosition > 0 || this.contractWalletInfo.usdtFrozenSellPosition > 0) {
                          // 构建空仓数据
              var sellPL = (1 - this.currentCoin.price / this.contractWalletInfo.usdtSellPrice) * (this.contractWalletInfo.usdtSellPosition + this.contractWalletInfo.usdtFrozenSellPosition) * this.contractWalletInfo.usdtShareNumber;

              var tItem = {
                direction: "Sell",
                openPrice: this.contractWalletInfo.usdtSellPrice, // 开仓价格
                pl: sellPL, // 盈亏
                position: this.contractWalletInfo.usdtSellPosition + this.contractWalletInfo.usdtFrozenSellPosition, // 总仓位
                avaPosition: this.contractWalletInfo.usdtSellPosition, // 可用仓位
                margin: this.contractWalletInfo.usdtSellPrincipalAmount, // 保证金
                leverage: this.contractWalletInfo.usdtSellLeverage, // 多仓杠杆
                plRatio: this.contractWalletInfo.usdtTotalProfitAndLoss / this.contractWalletInfo.usdtSellPrincipalAmount,
                mRatio: (sellPL + this.contractWalletInfo.usdtSellPrincipalAmount) / this.contractWalletInfo.usdtSellPrincipalAmount / this.contractWalletInfo.usdtSellLeverage,
                cmRatio: this.contractCoinInfo.maintenanceMarginRate
              };

              if(this.currentPositionList.length == 1){
                this.$set(this.currentPositionList, 1, tItem);
              }else{
                this.$set(this.currentPositionList, 0, tItem);
              }
            }else{
              this.currentPositionList.splice(1, 1);
            }
            this.getCurrentEntrustOrders();
            this.getHistoryEntrustOrders();
          }
        });

    },
    getPlate(str="") {
      //买卖盘
      var params = {};
      params["symbol"] = this.currentCoin.symbol;
      this.$http
        .post(this.host + this.api.swap.platemini, params)
        .then(response => {
          this.plate.askRows = [];
          this.plate.bidRows = [];
          let resp = response.body;
          if (resp.ask && resp.ask.items) {
            for (var i = 0; i < resp.ask.items.length; i++) {
              if (i == 0) {
                resp.ask.items[i].totalAmount = resp.ask.items[i].amount;
              } else {
                resp.ask.items[i].totalAmount =
                  resp.ask.items[i - 1].totalAmount + resp.ask.items[i].amount;
              }
            }
            if (resp.ask.items.length >= this.plate.maxPostion) {
              for (var i = this.plate.maxPostion; i > 0; i--) {
                var ask = resp.ask.items[i - 1];
                ask.direction = "SELL";
                ask.position = i;
                this.plate.askRows.push(ask);
              }
              const rows = this.plate.askRows,
                len = rows.length,
                totle = rows[0].totalAmount;
              this.plate.askTotle = totle;
            } else {
              for (var i = this.plate.maxPostion; i > resp.ask.items.length; i--) {
                var ask = { price: 0, amount: 0 };
                ask.direction = "SELL";
                ask.position = i;
                ask.totalAmount = ask.amount;
                this.plate.askRows.push(ask);
              }
              for (var i = resp.ask.items.length; i > 0; i--) {
                var ask = resp.ask.items[i - 1];
                ask.direction = "SELL";
                ask.position = i;
                this.plate.askRows.push(ask);
              }
              var askItemIndex = (resp.ask.items.length - 1) > 0 ? (resp.ask.items.length - 1) : 0;
              const rows = this.plate.askRows,
                len = rows.length,
                totle =
                  rows[askItemIndex]
                    .totalAmount;
              this.plate.askTotle = totle;
            }
          }
          if (resp.bid && resp.bid.items) {
            for (var i = 0; i < resp.bid.items.length; i++) {
              if (i == 0)
                resp.bid.items[i].totalAmount = resp.bid.items[i].amount;
              else
                resp.bid.items[i].totalAmount =
                  resp.bid.items[i - 1].totalAmount + resp.bid.items[i].amount;
            }
            for (var i = 0; i < resp.bid.items.length; i++) {
              var bid = resp.bid.items[i];
              bid.direction = "BUY";
              bid.position = i + 1;
              this.plate.bidRows.push(bid);
              if (i == this.plate.maxPostion - 1) break;
            }
            if (resp.bid.items.length < this.plate.maxPostion) {
              for (
                var i = resp.bid.items.length;
                i < this.plate.maxPostion;
                i++
              ) {
                var bid = { price: 0, amount: 0 };
                bid.direction = "BUY";
                bid.position = i + 1;
                bid.totalAmount = 0;
                this.plate.bidRows.push(bid);
              }
              var bidItemIndex = (resp.bid.items.length - 1) > 0 ? (resp.bid.items.length - 1) : 0;
              const rows = this.plate.bidRows,
                len = rows.length,
                totle = rows[bidItemIndex].totalAmount;
              this.plate.bidTotle = totle;
            } else {
              const rows = this.plate.bidRows,
                len = rows.length,
                totle = rows[len - 1].totalAmount;
              this.plate.bidTotle = totle;
            }
          }
          if(str!=""){
            this.selectedPlate = str;
          }
        });
    },
    getPlateFull() {
      //深度图
      var params = {};
      params["symbol"] = this.currentCoin.symbol;
      this.$http
        .post(this.host + this.api.swap.platefull, params)
        .then(response => {
          var resp = response.body;
          this.fullTrade = resp;
          resp.skin = this.skin;
          this.$refs.depthGraph.draw(resp);
        });
    },
    getTrade() {
      var params = {};
      params["symbol"] = this.currentCoin.symbol;
      params["size"] = 20;
      this.$http
        .post(this.host + this.api.swap.trade, params)
        .then(response => {
          this.trade.rows = [];
          var resp = response.body;
          for (var i = 0; i < resp.length; i++) {
            this.trade.rows.push(resp[i]);
          }
        });
    },
	successCallback(that,stompClient){
		that.datafeed = new Datafeeds.WebsockFeed(
		  that.host + "/swap",
		  that.currentCoin,
		  stompClient,
		  that.baseCoinScale
		);
		that.getKline();
		//订阅实时成交信息
		stompClient.subscribe(
		  "/topic/swap/trade/" + that.currentCoin.symbol,
		  function(msg) {
		    var resp = JSON.parse(msg.body);
		    if (resp.length > 0) {
		      for (var i = 0; i < resp.length; i++) {
		        that.trade.rows.unshift(resp[i]);
		      }
		    }
		    if (that.trade.rows.length > 30) {
		      that.trade.rows = that.trade.rows.slice(0, 30);
		    }
		  }
		);
		if (that.isLogin) {
		  //订阅委托取消信息
		  /*
		  stompClient.subscribe(
		    "/topic/swap/order-canceled/" +
		      that.currentCoin.symbol +
		      "/" +
		      that.member.id,
		    function(msg) {
		      var resp = JSON.parse(msg.body);
		      that.refreshAccount();
		    }
		  );
		  //订阅委托交易完成
		  stompClient.subscribe(
		    "/topic/swap/order-completed/" +
		      that.currentCoin.symbol +
		      "/" +
		      that.member.id,
		    function(msg) {
		      var resp = JSON.parse(msg.body);
		      that.refreshAccount();
		    }
		  );
		  //订阅委托部分交易
		  stompClient.subscribe(
		    "/topic/swap/order-trade/" +
		      that.currentCoin.symbol +
		      "/" +
		      that.member.id,
		    function(msg) {
		      var resp = JSON.parse(msg.body);
		      that.refreshAccount();
		    }
		  );
		  */
		}
		
		//订阅盘口消息
		stompClient.subscribe(
		  "/topic/swap/trade-plate/" + that.currentCoin.symbol,
		  function(msg) {
		    var resp = JSON.parse(msg.body);
		    if (resp.direction == "SELL") {
		      var asks = resp.items;
		      that.plate.askRows = [];
		      let totle = 0;
		      for (var i = that.plate.maxPostion - 1; i >= 0; i--) {
		        var ask = {};
		        if (i < asks.length) {
		          ask = asks[i];
		        } else {
		          ask["price"] = 0;
		          ask["amount"] = 0;
		        }
		        ask.direction = "SELL";
		        ask.position = i + 1;
		        that.plate.askRows.push(ask);
		      }
		      for (var i = that.plate.askRows.length - 1; i >= 0; i--) {
		        if (
		          i == that.plate.askRows.length - 1 ||
		          that.plate.askRows[i].price == 0
		        ) {
		          that.plate.askRows[i].totalAmount =
		            that.plate.askRows[i].amount;
		        } else {
		          that.plate.askRows[i].totalAmount =
		            that.plate.askRows[i + 1].totalAmount +
		            that.plate.askRows[i].amount;
		        }
		        totle += that.plate.askRows[i].amount;
		      }
		      that.plate.askTotle = totle;
		    } else {
		      var bids = resp.items;
		      that.plate.bidRows = [];
		      let totle = 0;
		      for (var i = 0; i < that.plate.maxPostion; i++) {
		        var bid = {};
		        if (i < bids.length) {
		          bid = bids[i];
		        } else {
		          bid["price"] = 0;
		          bid["amount"] = 0;
		        }
		        bid.direction = "BUY";
		        bid.position = i + 1;
		        that.plate.bidRows.push(bid);
		      }
		      for (var i = 0; i < that.plate.bidRows.length; i++) {
		        if (i == 0 || that.plate.bidRows[i].amount == 0) {
		          that.plate.bidRows[i].totalAmount =
		            that.plate.bidRows[i].amount;
		        } else {
		          that.plate.bidRows[i].totalAmount =
		            that.plate.bidRows[i - 1].totalAmount +
		            that.plate.bidRows[i].amount;
		        }
		        totle += that.plate.bidRows[i].amount;
		      }
		      that.plate.bidTotle = totle;
		    }
		    if(that.currentImgTable == 's') {
		      that.getPlateFull();
		    }
		  }
		);
	},
    startWebsock() {
		
      if (this.stompClient) {
		  console.log("opening....");
        // this.stompClient.ws.close();
		return ;
      }
      var stompClient = null;
      var that = this;
      var socket = new SockJS(that.host + that.api.swap.ws);
      stompClient = Stomp.over(socket);
      this.stompClient = stompClient;
      stompClient.debug = false;
      // this.datafeed = new Datafeeds.WebsockFeed(that.host+'/market',this.currentCoin,stompClient);
      // this.getKline();
      stompClient.connect({}, function(frame) {
       that.successCallback(that,stompClient);
      },() => {
        that.reconnect(that)
      });
    },
	
	// 断开重连使用定时器定时连接服务器
	reconnect(that) {
	  console.info('in reconnect function')
	  let connected = false
	  const reconInv = setInterval(() => {
		console.info('in interval' + Math.random());
		if (that.stompClient) {
			console.log("reconnect opening.....")
			clearInterval(reconInv)
			connected = true;
		  // that.stompClient.ws.close();
		}
		var stompClient = null;
		var socket = new SockJS(that.host + that.api.swap.ws);
		stompClient = Stomp.over(socket)
		that.stompClient = stompClient;
		stompClient.debug = false;
		stompClient.connect({}, (frame) => {
		  console.info('reconnected success')
		  // 连接成功，清除定时器
		  clearInterval(reconInv)
		  connected = true
		  that.successCallback(that,stompClient)
		}, () => {
		  console.info('reconnect failed')
		  console.info('connected:' + connected)
		  if (connected) {
			console.info('connect .... what?')
		  }
			that.reconnect(that);
		});
	  }, 2000)
	},
	
    open_order() {
      this.showMarket = false;
    },
    close_order() {
      this.showMarket = true;
    },
    gohref(currentRow, oldCurrentRow) {
      this.$router.push({
          name: 'SwapPair',
          params: {
            pair: currentRow.href
          }
        });
    },
    buyPlate(currentRow) {
      this.form.entrustPrice = currentRow.price;
    },
    sellPlate(currentRow) {
      this.form.entrustPrice = currentRow.price;
    },
    cancel(eid) {
      var that = this;
      this.$Modal.confirm({
        title: "撤单提示",
        content: this.$t("swap.undotip"),
        onOk: () => {
          let params = {
            entrustId: eid
          };
          this.$http
            .post(this.host + this.api.swap.cancel, params)
            .then(response => {
              var resp = response.body;
              if (resp.code == 0) {
                this.$Notice.success({
                  title: this.$t("swap.tip"),
                  desc: this.$t("swap.cancelsuccess")
                });
                setTimeout(function () {
                  that.getMemberContractWallet();
                }, 1000);
              } else {
                this.$Notice.error({
                  title: this.$t("swap.tip"),
                  desc: resp.message
                });
              }
            });
        }
      });
    },
    // timeFormat: function(tick) {
    //   return moment(tick).format("HH:mm:ss");
    // },
    // dateFormat: function(tick) {
    //   return moment(tick).format("MM-DD HH:mm:ss");
    // },
    showMarginModeModal(){
      if(!this.isLogin) return;

      this.marginModeModal = true;
    },
    changeMarginMode(val) {
      if(!this.isLogin) {
        return;
      }
      var that = this;
      let params = {
        contractCoinId: this.contractCoinInfo.id,
        targetPattern: Number(val)
      };
      this.$http
        .post(this.host + this.api.swap.switchPattern, params)
        .then(response => {
          var resp = response.body;
          if (resp.code == 0) {
            this.$Message.success(this.$t("swap.marginModeSuccessTip"));
            this.getMemberContractWallet();
          } else {
            this.$Message.error(resp.message);
          }
        });
    },
    showAdjustLeverage(type) {
      if(!this.isLogin) return;

      this.leverageModal = true;
      this.leverageType = this.contractCoinInfo.leverageType;
      if(type == 1) {
        this.leverageAdjustVal = Number(this.contractWalletInfo.usdtBuyLeverage);
      }else{
        this.leverageAdjustVal = Number(this.contractWalletInfo.usdtSellLeverage);
      }
      // 默认杠杆如不在范围内
      var levArr = this.contractCoinInfo.leverage.split(",");
      if(this.leverageAdjustVal < levArr[0]) {
        this.leverageAdjustVal = Number(levArr[0]);
      }
      if(this.leverageAdjustVal > levArr[levArr.length - 1]) {
        this.leverageAdjustVal = Number(levArr[levArr.length - 1]);
      }

      this.changeLeverageType = type;
    },
    changeLeverage(lev) { // 离散模式：选择杠杆
      if(this.changeLeverageType == 1) {//多仓杠杆
        this.leverageAdjustVal = lev;
      }else{//空仓杠杆
        this.leverageAdjustVal = lev;
      }
    },
    changeEntrustOrderType(value) {
      if(value == "2") { // 计划委托
        this.form.trigglePrice = "";
        this.form.entrustPrice = "";
        this.form.volume = "";
      }else if(value == "0") { // 限价委托
        this.form.entrustPrice = this.currentCoin.price;
        this.form.volume = "";
      }
    },
    onLeverageChange(val){
      if(!this.isLogin) return;
      this.leverageAdjustVal = val;
    },
    adjustLeverage(){ // 连续模式，确认选择杠杆
      if(!this.isLogin) return;
      var levArr = this.contractCoinInfo.leverage.split(",");
      if(this.leverageAdjustVal < Number(levArr[0])) {
        this.leverageAdjustVal = Number(levArr[0]);
        this.$Message.error(this.$t("swap.levmintip"));
        return;
      }
      if(this.leverageAdjustVal > Number(levArr[levArr.length - 1])) {
        this.leverageAdjustVal = Number(levArr[levArr.length - 1]);
        this.$Message.error(this.$t("swap.levmaxtip"));
        return;
      }

      if(this.changeLeverageType == 1 && this.buyLeverage == this.leverageAdjustVal) {
        this.leverageModal = false;
        return;
      }
      if(this.changeLeverageType == 2 && this.sellLeverage == this.leverageAdjustVal){
        this.leverageModal = false;
        return;
      }
      // TODO 检查是否能够更改为目标杠杆

      this.$Spin.show();

      var that = this;
      let hasChanged = false;
      let params = {
        contractCoinId: this.contractCoinInfo.id,
        leverage: this.leverageAdjustVal,
        direction: this.changeLeverageType == 1 ? 0 : 1
      };

      this.$http
        .post(this.host + this.api.swap.modifyLeverage, params)
        .then(response => {
          var resp = response.body;
          if (resp.code == 0) {
            this.$Notice.success({
              title: that.$t("swap.tip"),
              desc: that.$t("swap.success")
            });
            hasChanged = true;
            this.getMemberContractWallet();
          } else {
            this.$Notice.error({
              title: that.$t("swap.tip"),
              desc: resp.message
            });
          }
          this.$Spin.hide();

          if(!hasChanged) {
            return;
          }
          if(this.changeLeverageType == 1){ //多仓杠杆
            this.buyLeverage = this.leverageAdjustVal;
          }else{ // 空仓杠杆
            this.sellLeverage = this.leverageAdjustVal;
          }

          this.leverageModal = false;
        });
    },
    onOpenAmountFocus(){
      this.sliderOpenPercent = 0;
    },
    onChangeOpenPercent(val) {
      this.sliderOpenPercent = val;
    },
    onCloseAmountFocus(){
      this.sliderClosePercent = 0;
    },
    onChangeClosePercent(val) {
      this.sliderClosePercent = val;
    },
    onOpen(direction){ // 开仓：买入开多
      if(!this.isLogin) return;
      var temVolume = 0;
      if(this.sliderOpenPercent > 0) {
        if(direction == 0) {
          temVolume = parseInt(this.avaOpenBuy * this.sliderOpenPercent / 100);
        }else{
          temVolume = parseInt(this.avaOpenSell * this.sliderOpenPercent / 100);
        }

        if(temVolume < 1) {
          temVolume = 1;
        }
      }else{
        temVolume = parseInt(this.form.volume);
      }
      if(this.form.triggerPrice == "" || this.form.triggerPrice == undefined) {
        this.form.triggerPrice = 0;
      }
      var oType = parseInt(this.entrustOrderType);
      if(oType == 2 && this.form.triggerPrice <= 0) {
        this.$Message.error(this.$t("swap.triggerpriceshouldbigger"));
        return;
      }
      let params= {
        type : oType, // 0:市价 1:限价 2计划委托
        direction : direction,// 买入
        contractCoinId : this.contractCoinInfo.id,
        triggerPrice : Number(this.form.triggerPrice),
        entrustPrice : this.form.entrustPrice == "" ? 0 : Number(this.form.entrustPrice),
        leverage : direction ==0 ? Number(this.buyLeverage) : Number(this.sellLeverage),
        volume : temVolume
      };
      if(params.volume <= 0) {
        this.$Message.error(this.$t("swap.pleaseinputopenvolume"));
        return;
      }
      var reg = /^\+?[1-9][0-9]*$/;
      if(!reg.test(params.volume)){
        this.$Message.error(this.$t("swap.pleaseinputcorrectopenvolume"));
        return;
      }
      if(direction == 0) { // 买入开多(计划委托时，不需考虑额度问题)
        if(params.volume > this.avaOpenBuy && params.type != 3) {
          this.$Message.error(this.$t("swap.noenoughbalance"));
          return;
        }
      }else{ // 卖出开空
        if(params.volume > this.avaOpenSell && params.type != 3) {
          this.$Message.error(this.$t("swap.noenoughbalance"));
          return;
        }
      }
      if(this.entrustOrderType == 1) { // 限价，必须输入价格
        if(this.form.entrustPrice == "" || this.form.entrustPrice == 0) {
          this.$Message.error(this.$t("swap.pleaseinputrightentrustprice"));
          return;
        }
      }


      this.$Spin.show();
      var that = this;
      this.$http
        .post(this.host + this.api.swap.open, params)
        .then(response => {
          var resp = response.body;
          if (resp.code == 0) {
            this.$Notice.success({
              title: that.$t("swap.tip"),
              desc: that.$t("swap.success")
            });
            setTimeout(function () {
              that.getMemberContractWallet();
            }, 1000);
          } else {
            this.$Notice.error({
              title: that.$t("swap.tip"),
              desc: resp.message
            });
          }
          this.$Spin.hide();
        });
    },
    onClose(direction){ // 平仓：卖出平多
      if(!this.isLogin) return;
      var temVolume = 0;
      if(this.sliderClosePercent > 0) {
        if(direction == 0) {
          temVolume = parseInt(this.contractWalletInfo.usdtSellPosition * this.sliderClosePercent / 100);
        }else{
          temVolume = parseInt(this.contractWalletInfo.usdtBuyPosition * this.sliderClosePercent / 100);
        }

        if(temVolume < 1) {
          temVolume = 1;
        }
      }else{
        temVolume = parseInt(this.form.volume);
      }
      if(this.form.triggerPrice == "" || this.form.triggerPrice == undefined) {
        this.form.triggerPrice = 0;
      }
      var oType = parseInt(this.entrustOrderType);
      if(oType == 2 && this.form.triggerPrice <= 0) {
        this.$Message.error(this.$t("swap.triggerpriceshouldbigger"));
        return;
      }
      let params= {
        type : this.entrustOrderType, // 0:市价 1:限价 2计划委托
        direction : direction,// 买入
        contractCoinId : this.contractCoinInfo.id,
        triggerPrice : Number(this.form.triggerPrice),
        entrustPrice : this.form.entrustPrice == "" ? 0 : Number(this.form.entrustPrice),
        volume : temVolume
      };
      if(params.volume <= 0) {
        this.$Message.error(this.$t("swap.pleaseinputclosevolume"));
        return;
      }
      var reg = /^\+?[1-9][0-9]*$/;
      if(!reg.test(params.volume)){
        this.$Message.error(this.$t("swap.pleaseinputcorrectclosevolume"));
        return;
      }
      if(direction == 0) { // 买入开多(计划委托时，不需考虑额度问题)
        if(params.volume > this.contractWalletInfo.usdtSellPosition && params.type != 3) {
          this.$Message.error(this.$t("swap.noenoughposition"));
          return;
        }
      }else{ // 卖出开空
        if(params.volume > this.contractWalletInfo.usdtBuyPosition && params.type != 3) {
          this.$Message.error(this.$t("swap.noenoughposition"));
          return;
        }
      }
      if(this.entrustOrderType == 1) { // 限价，必须输入价格
        if(this.form.entrustPrice == "" || this.form.entrustPrice == 0) {
          this.$Message.error(this.$t("swap.pleaseinputrightentrustprice"));
          return;
        }
      }

      this.$Spin.show();
      var that = this;
      this.$http
        .post(this.host + this.api.swap.close, params)
        .then(response => {
          var resp = response.body;
          if (resp.code == 0) {
            this.$Notice.success({
              title: that.$t("swap.tip"),
              desc: that.$t("swap.success")
            });
            setTimeout(function () {
              that.getMemberContractWallet();
            }, 1000);
          } else {
            this.$Notice.error({
              title: that.$t("swap.tip"),
              desc: resp.message
            });
          }
          this.$Spin.hide();
        });
    },
    getCurrentEntrustOrders(){
      if(!this.isLogin) return;
      var that = this;
      let params = {
        contractCoinId: this.contractCoinInfo.id,
        pageNo: 1,
        pageSize: 50
      };
      this.$http
        .post(this.host + this.api.swap.getEntrustList, params)
        .then(response => {
          var resp = response.body;
          if (resp.content.length > 0) {
            this.currentEntrustOrderList.splice(0);
            resp.content.forEach(function(item){
              that.currentEntrustOrderList.push(item);
            });
          }else{
            this.currentEntrustOrderList.splice(0);
          }
        });
    },
    getHistoryEntrustOrders(){
      if(!this.isLogin) return;
      var that = this;
      let params = {
        contractCoinId: this.contractCoinInfo.id,
        pageNo: 1,
        pageSize: 50
      };
      this.$http
        .post(this.host + this.api.swap.getHistoryEntrustList, params)
        .then(response => {
          var resp = response.body;
          if (resp.content.length > 0) {
            this.historyEntrustOrderList.splice(0);
            resp.content.forEach(function(item){
              that.historyEntrustOrderList.push(item);
            });
          }else{
            this.historyEntrustOrderList.splice(0);
          }
        });
    },
  }
};
</script>

<style scoped lang="scss">
@import url(../../assets/css/swap.css);
$night-bg: #0b1520;
$night-headerbg: #27313e;
$night-contentbg: #192330;
$night-color: #fff;

.swap {
  color: #fff;
  background-color: #0b1520;
  .main {
    width: 99%;
    margin-left: 0.3%;
    display: flex;
    margin-top: 5px;
    .left {
      
      border-radius: 0px;
      margin-right: 5px;
      overflow:hidden;
      .handlers {
        font-size: 0;
        padding: 10px 20px;
        background-color: #192330;
        border-top-left-radius: 0px;
        border-top-right-radius: 0px;
        .handler {
          display: inline-block;
          margin-right: 10px;
          width: 20px;
          height: 20px;
          background-size: 100% 100%;
          cursor: pointer;
          &.handler-all {
            background-image: url("../../assets/images/exchange/plate_all.png");
            &.active {
              background-image: url("../../assets/images/exchange/plate_all_active.png");
            }
          }
          &.handler-green {
            background-image: url("../../assets/images/exchange/plate_green.png");
            &.active {
              background-image: url("../../assets/images/exchange/plate_green_active.png");
            }
          }
          &.handler-red {
            background-image: url("../../assets/images/exchange/plate_red.png");
            &.active {
              background-image: url("../../assets/images/exchange/plate_red_active.png");
            }
          }
        }
      }
      .plate-nowprice {
        text-align: center;
        background-color: #27313e;
        line-height: 1;
        display: flex;
        align-items: center;
        height: 40px;
        justify-content: center;
        font-size: 14px;
        font-weight: 500;
        .price {
          font-size: 18px;
          margin-right: 10px;
        }
        .price-cny {
          font-size: 12px;
          margin-left: 10px;
          font-weight: 400;
          color: rgba(219, 222, 246, 0.3);
        }
      }
    }
    .center {
      flex: 0 0 76%;
      margin-right: 5px;
      .imgtable {
        height: 446px;
        position: relative;
        overflow: hidden;
        .handler {
          position: absolute;
          top: 10px;
          right: 40px;
          z-index: 1000;
          > span {
            background-color: #2c3b59;
            color: #c7cce6;
            padding: 4px 8px;
            cursor: pointer;
            font-size: 13px;
            opacity: 0.5;
            &.active {
              opacity: 1;
            }
          }
        }
      }
      .trade_wrap {
        .trade_menu {
          position: relative;
          background-color: #192330;
          border-top-left-radius: 0px;
          border-top-right-radius: 0px;
          border-bottom: 1px solid #27313e;
          font-size: 0;
          height: 40px;
          line-height: 40px;
          > span {
            font-size: 16px;
            padding: 11px 20px;
            cursor: pointer;
            &.active {
              color: #FFF;
              border-bottom: 2px solid #f0a70a;
            }
            &:first-child {
              border-top-left-radius: 0px;
            }
          }
          .fee-wrap {
            position: absolute;
            top: 0;
            right: 20px;
            font-size: 12px;
            > span {
              margin-right: 10px;
              color: #7c7f82;
            }
            > a {
              vertical-align: middle;
            }
          }
        }
        .trade_panel {
          position: relative;
          .mask {
            position: absolute;
            width: 100%;
            height: 100%;
            display: flex;
            background-color: rgba(0, 52, 77, 0.8);
            justify-content: center;
            align-items: center;
            z-index: 100;
            font-size: 24px;
            border-radius: 0px;
            // color: #bcd7e6;
          }
          .publish-mask {
            position: absolute;
            width: 100%;
            height: 100%;
            background-color: rgba(0, 37, 64, 0.93);
            justify-content: center;
            align-items: center;
            z-index: 101;
            font-size: 24px;
            border-radius: 0px;
            // color: #bcd7e6;
          }
        }
        .trade_panel .panel .hd {
          border-bottom: none;
          b {
            color: #fff;
          }
          a {
            color: #f0a70a;
          }
        }
      }
    }
    .right {
      flex: 0 0 23%;
      .coin-menu {
        overflow: hidden;
        height: 512px;
        background-color: #192330;
        border-radius: 0px;
      }
    }
  }
  .symbol {
    display: flex;
    justify-content: space-between;
    padding: 15px 30px;
    margin-bottom: 5px;
    align-items: center;
    background-color: #192330;
    line-height: 1;
    border-radius: 0px;
    .item {
      .price-cny {
        font-size: 12px;
        color: #546886;
      }
      .coin {
        font-size: 20px;
      }
      .text {
        width: 100%;
        display: block;
        font-size: 12px;
        color: #999;
        margin-bottom: 5px;
      }
      .num {
        font-size: 12px;
        color: #fff;
      }
      > img {
        display: block;
        width: 18px;
        height: 18px;
        cursor: pointer;
      }
    }
  }
  .order {
    min-height: 227px;
    margin-bottom: 10px;
    overflow:hidden;
    .order-handler {
      // background-color: #192330;
      background-color: #192330;
      font-size: 0;
      // line-height: 38px;
      > span {
        padding: 0 20px;
        font-size: 14px;
        display: inline-block;
        color: #fff;
        cursor: pointer;
        line-height: 30px;
        background-color: #192330;
        &.active {
          color: #f0a70a;
          background-color: #27313e;
          border-bottom: 2px solid #f0a70a;
        }
        &:first-child {
          border-top-left-radius: 0px;
        }
        &:last-child {
          border-top-right-radius: 0px;
        }
      }
    }
  }
}
.exchange.day {
  color: #333;
  background-color: #fff;
  .main {
    .left {
      background-color: #fff;
      box-shadow: 0 0 2px #ccc;
      .handlers {
        background-color: #fff;
      }
      .plate-nowprice {
        background-color: #fff;
        border-top: 1px solid #f0f0f0;
        border-bottom: 1px solid #f0f0f0;
      }
    }
    .imgtable {
      border-radius: 6px;
      box-shadow: 0 0 2px #ccc;
      .handler {
        > span {
          border: 1px solid #333;
        }
      }
    }
    .trade_wrap {
      box-shadow: 0 0 2px #ccc;
      .trade_menu {
        background-color: #fafafa;
        > span {
          background-color: #fafafa;
          border-right: 1px solid #f0f0f0;
          &.active {
            background-color: #fff;
            color: #f0a70a;
          }
          &:last-child {
            border-top-right-radius: 6px;
          }
        }
        .ivu-icon {
          color: #333 !important;
        }
      }
      .trade_panel {
        box-shadow: 0 0 2px #ccc;
        .mask {
          background-color: rgba(0, 52, 77, 0.8);
          color: #fff;
        }
      }
      .trade_panel .panel .hd {
        border-bottom: none;
        b {
          color: #333;
        }
        a {
          color: #f0a70a;
        }
      }
    }
    .right {
      .coin-menu {
        background-color: #fff;
        box-shadow: 0 2px 2px #ccc;
      }
      .trade-wrap {
        box-shadow: 0 0 2px #ccc;
        border-radius: 6px;
      }
      // .ivu-table-wrapper{
      //         box-shadow:0 0 2px #ccc;
      //       }
    }
  }
  .symbol {
    background-color: #fff;
    box-shadow: 0 0 2px #ccc;
    .item {
      .text {
        color: #999;
      }
      .num {
        color: #333;
      }
    }
  }
  .order {
    box-shadow: 0 2px 2px #ccc;
    min-height: 227px;
    .order-handler {
      margin-right: -2px;
      background-color: #fff;
      > span {
        color: #333;
        background-color: #fafafa;
        box-shadow: 0 0 2px #ccc;
        &.active {
          color: #f0a70a;
          background-color: #fff;
        }
      }
    }
  }
}
#kline_container{
    background: #192330;
}

.coin-info{
  color: #8f9ca5;
  display: -webkit-box;
  -webkit-box-orient: vertical;
  -webkit-line-clamp: 5;
  overflow: hidden;
  padding-top: 15px;
}

</style>