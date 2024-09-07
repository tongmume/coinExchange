<template>
  <div class="option">

    <div class="ctc_container">
      <h1>{{$t('option.desc')}}</h1>
      <div class="main">
          <div class="option-tab">
            <div :class="{ 'option-tab-item': true, 'option-tab-current': item.symbol == currentCoinSymbol }" class="option-tab-item" v-for="item in coinList" @click="changeCoin(item.symbol)">{{item.symbol}} {{$t('option.title')}}</div>
          </div>
          <div class="ctc-container">
            <div class="trade_wrap">
              <div class="trade_panel">
                <div class="trade_bd_ctc">
                  <div class="panel panel_buy">
                    <div class="bd bd_limited">
                      <div class="result-panel">
                        <div style="width: 100%;font-weight: bold;font-size: 24px;text-align: center;margin-bottom: 10px;">
                          <span :class="{ 'title-switch': true, 'switch-current': historyTab == 1 }" @click="historyClick(1)">{{$t('option.history')}}</span>
                          <span :class="{ 'title-switch': true, 'switch-current': historyTab == 2 }" @click="historyClick(2)">{{$t('option.kline')}}</span>
                        </div>
                        <div v-show="historyTab==2" style="height: 240px;position: relative;overflow: hidden;">
                          <div id="kline_container"></div>
                        </div>
                        <div v-show="historyTab==1">
                          <div class="result-item" v-for="item in historyList">
                            <div class="item-title"><span>{{$t('option.seriers')}}</span><span>{{item.optionNo}}</span><span>{{$t('option.period')}}</span></div>
                            <div class="item-body">
                              <Tooltip placement="bottom">
                                <span class="bg-green" v-if="item.result == 'WIN'"><Icon type="md-trending-up" /></span>
                                <span class="bg-red" v-if="item.result == 'LOSE'"><Icon type="md-trending-down" /></span>
                                <span class="bg-gray" v-if="item.result == 'TIED'"><Icon type="md-remove" /></span>
                                <div slot="content">
                                    <p class="tip-item"><span>{{$t('option.opentime')}}：</span>{{item.openTime | dateFormat(that)}}</p>
                                    <p class="tip-item"><span>{{$t('option.closetime')}}：</span>{{item.closeTime | dateFormat(that)}}</p>
                                    <p class="tip-item"><span>{{$t('option.openprice1')}}：</span>{{item.openPrice}} USDT</p>
                                    <p class="tip-item"><span>{{$t('option.closeprice')}}：</span>{{item.closePrice}} USDT</p>
                                    <p class="tip-item"><span>{{$t('option.buyupreward')}}：</span>{{item.totalBuy}} USDT</p>
                                    <p class="tip-item"><span>{{$t('option.buydownreward')}}：</span>{{item.totalSell}} USDT</p>
                                </div>
                              </Tooltip>
                            </div>
                          </div>
                        </div>
                      </div>
                      <div class="last-period" v-if="openingOption.id == 0">
                        <div style="height:40px;line-height:40px;">{{$t('option.noopening')}}</div>
                      </div>
                      <div class="last-period" v-if="openingOption.id != 0">
                        <div style="width: 10%;text-align: center;float:left;height:40px;line-height: 40px;font-size: 20px;font-weight: bold;text-shadow: 2px 2px 2px #000;">
                          {{$t('option.seriers')}} {{openingOption.optionNo}} {{$t('option.period')}}
                          <Tooltip placement="top-start">
                            <Icon type="ios-alarm" style="margin-top: -3px;"/>
                            <div slot="content">
                              {{(openingOption.createTime + currentCoin.openTimeGap * 1000) | dateFormat(that)}} ~ {{(openingOption.createTime + currentCoin.openTimeGap * 1000 + currentCoin.closeTimeGap * 1000) | dateFormat(that)}}
                            </div>
                          </Tooltip>
                        </div>
                        <div class="period-content">
                          <div class="content-item color_green"><span>{{$t('option.buyupreward')}}: <b>{{openingOption.totalBuy}}</b> USDT</span></div>
                          <div class="content-item color_red"><span>{{$t('option.buydownreward')}}: <b>{{openingOption.totalSell}}</b> USDT</span></div>
                          <div class="content-item color_gold" v-if="isLogin">
                            <span>{{$t('option.myamount')}}: <b>{{myOpeningOption.betAmount}}</b> USDT</span> &nbsp;&nbsp;
                            <span class="direction color_green" v-if="myOpeningOption.direction=='BUY'">{{$t('option.buyup')}}</span>
                            <span class="direction color_red" v-if="myOpeningOption.direction=='SELL'">{{$t('option.buydown')}}</span>
                          </div>
                          <div style="float:left;" v-if="!isLogin">{{$t('option.myamount')}}: 
                              <router-link to="/login" id="login">{{$t("common.login")}}</router-link>/<router-link to="/register" id="register">{{$t("common.register")}}</router-link>
                          </div>
                        </div>
                        <div style="float:right;height:40px;line-height:40px;padding-right:10px;">
                          <span :class="{ 'bg-green': currentPrice > openPrice, 'bg-red': currentPrice < openPrice }" style="letter-spacing:2px;display: inline-block;height:20px;line-height:20px;border-radius: 3px;padding: 0 5px;color:#FFF;">{{priceChange}}</span>
                        </div>
                        <div style="color:#a5a5a5;width:170px;float: right;height:40px;text-align: center;">
                          <div style="height:16px;line-height: 16px;margin-top: 4px;margin-left:5px;font-size: 6px;text-align: left;">{{$t('option.openprice')}}：{{openPrice | fixed2}} USDT</div>
                          <div style="height:16px;line-height: 16px;font-size: 6px;margin-left:5px;text-align: left;">{{$t('option.currentprice')}}：<span :class="{ 'color_green': currentPrice > openPrice, 'color_red': currentPrice < openPrice }">{{currentPrice | fixed2}} USDT</span>
                          </div>
                        </div>
                        <div  style="width:200px;float: right;height:40px;line-height: 36px;text-align: right;padding-right: 10px;">
                          <div><Progress :percent="currentPercent" :stroke-width="15" status="active"/></div>
                        </div>
                        <div style="color:#ff8100;width:80px;float: right;height:40px;line-height: 40px;text-align: right;font-size:12px;">{{$t('option.progress')}}：</div>
                      </div>
                      <div class="current-period">
                        <div class="content">
                          <div class="period-title">{{$t('option.seriers')}} {{startingOption.optionNo}} {{$t('option.period')}}</div>
                          <div class="period-time">
                            <div style="margin-top:10px;font-size:12px;">{{$t('option.currentoption')}}：{{(startingOption.createTime + currentCoin.openTimeGap * 1000) | dateFormat(that)}} ~ {{(startingOption.createTime + currentCoin.openTimeGap * 1000 + currentCoin.closeTimeGap * 1000) | dateFormat(that)}}</div>
                          </div>
                          <div class="period-reward color_green" style="border-top-left-radius: 10px;border-bottom-left-radius: 10px;">
                            <h2>{{startingOption.totalBuy | fixed2}} USDT</h2>
                            <p>{{$t('option.buyupreward')}}</p>
                          </div>
                          <div class="period-reward color_red">
                            <h2>{{startingOption.totalSell | fixed2}} USDT</h2>
                            <p>{{$t('option.buydownreward')}}</p>
                          </div>
                          <div class="period-reward color_gold">
                            <h2>{{myStartingOption.betAmount | fixed2}} USDT</h2>
                            <p>{{$t('option.myamount')}}</p>
                          </div>
                        </div>
                        <span></span>
                        <span></span>
                        <span></span>
                        <span></span>
                      </div>
                      <Form style="width: 100%;margin-top: 20px;">
                        <FormItem class="buy-input">
                          <div class="quantity-group">
                            <div v-for="item in currentCoinAmountList" :class="{ 'quantity-item': true, 'current': currentSelectCount == item }" @click="selectCount(item)"> {{item}} USDT</div>
                          </div>
                        </FormItem>
                        <div>{{$t('option.avaliablebalance')}}：{{assetUsdt | fixed2}} USDT</div>
                      </Form>
                      <div style="width: 100%;margin-top: 20px;">
                        <div style="width: 50%;float:left;text-align: center;">
                          <Button v-if="isLogin" class="bg-green" style="width: 80%;" size="large" @click="addClick(0)">{{$t('option.buyup')}}</Button>
                          <Button v-if="!isLogin" class="bg-gray" style="width: 80%;" size="large">{{$t('option.login')}}</Button>
                        </div>
                        <div style="width: 50%;float:right;text-align: center;">
                          <Button v-if="isLogin" class="bg-red" style="width: 80%;" size="large" @click="addClick(1)">{{$t('option.buydown')}}</Button>
                          <Button v-if="!isLogin" class="bg-gray" style="width: 80%;" size="large">{{$t('option.login')}}</Button>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
                <div style="margin-top: 10px;">
                  <div class="table">
                    <Table :no-data-text="$t('common.nodata')" :columns="columns" :data="orders" :loading="loading"></Table>
                    <div class="page">
                      <Page :total="total" :pageSize="pageSize" :current="pageNo" @on-change="loadMyOrders"></Page>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
      </div>
    </div>


    <Modal v-model="modal" width="450">
      <!-- <P style="color:red;font-weight: bold;">
        {{$t('uc.finance.withdraw.fundpwdtip')}}<br/>
        <Input type="password" v-model="fundpwd" :placeholder="$t('otc.chat.msg7')"></Input>
      </p> -->
      <p slot="header">
        {{$t("cexchange.confirm")}}
      </p>
      <p>{{$t("cexchange.confirmmsg")}}</p>
      <div slot="footer">
        <span style="margin-right:50px">{{$t("cexchange.cancel")}}</span>
        <span style="background:#f0ac19;color:#fff;width:80px;border-radius:30px;display:inline-block;text-align:center;height:30px;line-height: 30px;" @click="submit()">{{$t("cexchange.ok")}}</span>
      </div>
    </Modal>
  </div>
</template>

<script>
import Datafeeds from "@js/charting_library/datafeed/optiontrade.js";
var moment = require("moment-timezone");
var Stomp = require("stompjs");
var SockJS = require("sockjs-client");
import expandRow from "@components/exchange/expand.vue";
import $ from "@js/jquery.min.js";

export default {
  components: { expandRow },
  data() {
    const self = this;
    return {
	  that:this,
      modal: false,
      historyTab: 1,
      datafeed: null,
      datafeedK: null,
      stompClient: null,
      stompClientK: null,
      skin: "night", //皮肤样式day&night
      coinList: [],
      currentCoinSymbol: "BTC/USDT",
      currentCoin: {},
      currentCoinAmountList: [],
      currentPrice: 10000.00,
      openPrice: 12456.8,
      currentSelectCount: 0,
      baseCoinUnit: "USDT",
      tableMoney: [],
      assetUsdt: 0,
      currentTotalBuy: 0,
      currentTotalSell: 0,
      historyList: [],
      openingOption: {
        id: 0,
        optionNo: "-",
        totalBuy: 0,
        totalSell: 0
      },
      startingOption: {
        optionNo: "-",
        totalBuy: 0,
        totalSell: 0
      },
      myOpeningOption: {
        betAmount: 0
      },
      myStartingOption: {
        betAmount: 0
      },
      currentPercent: 0,
      orders: [],
      loading: false,
      total: 0,
      pageSize: 10,
      pageNo: 1,
      columns: [
        {
          title: self.$t("option.col_optionNo"),
          key: "optionNo",
          minWidth: 65,
          render: (h, params) => {
            return h("span", {}, params.row.symbol + " - " + self.$t('option.seriers') + params.row.optionNo + self.$t('option.period'));
          }
        },
        {
          title: self.$t("option.col_betAmount"),
          key: "betAmount",
          minWidth: 65,
          render: (h, params) => {
            return h("span", {}, params.row.betAmount);
          }
        },
        {
          title: self.$t("option.col_direction"),
          key: "direction",
          minWidth: 65,
          render: (h, params) => {

            let txt = params.row.direction == "BUY" ? self.$t("option.buyup") : self.$t("option.buydown");
            const txtColor = params.row.direction == "BUY" ? "#42b983" : "#FF0000";
            return h("div", {
                    style:{
                      color: txtColor
                    }
                  }, [
                    h("span", {}, txt)
                  ]);
          }
        },
        {
          title: self.$t("option.col_result"),
          key: "result",
          minWidth: 65,
          render: (h, params) => {
            let txt = params.row.result == "WIN" ? self.$t("option.win") : self.$t("option.lose");
            
            let txtColor = params.row.result == "WIN" ? "#42b983" : "#FF0000";
            if(params.row.result == "WAIT") {
              txt = self.$t("option.wait");
              txtColor = "#FFF";
            }
            return h("div", {
                    style:{
                      color: txtColor
                    }
                  }, [
                    h("span", {}, txt)
                  ]);
          }
        },
        {
          title: self.$t("option.col_rewardAmount"),
          key: "rewardAmount",
          minWidth: 65,
          render: (h, params) => {
            let txt = params.row.rewardAmount;
            if(params.row.result == "WAIT") {
              txt = "-";
            }else{
              if(params.row.rewardAmount > 0) {
                txt = "+" + txt;
              }
            }
            return h("span", {}, txt);
          }
        },
        {
          title: self.$t("option.col_fee"),
          key: "fee",
          minWidth: 65,
          render: (h, params) => {
            let txt = params.row.fee;
            if(params.row.result == "WAIT") {
              txt = "-";
            }
            return h("span", {}, txt);
          }
        },
        {
          title: self.$t("option.col_winfee"),
          key: "winFee",
          minWidth: 65,
          render: (h, params) => {
            let txt = params.row.winFee;
            if(params.row.result == "WAIT") {
              txt = "-";
            }
            return h("span", {}, txt);
          }
        },
        {
          title: self.$t("option.col_createTime"),
          key: "createTime",
          minWidth: 65,
          render: (h, params) => {
            return h("span", {}, self.dateFormatHM(params.row.createTime));
          }
        }
      ],
    };
  },
  created: function() {
    this.init();
  },
  filters:{
    dateFormat: function(tick,that) {
      return that.dateFormatHM();
    },
    fixedScale: function(value, scale) {
      return value.toFixed(scale);
    },
    fixed2: function(value){
      return value.toFixed(2);
    }
  },
  mounted () {

  },
  computed: {
    lang() {
      return this.$store.state.lang;
    },
    langPram(){
      return this.$store.state.lang;
    },
    isLogin: function() {
      return this.$store.getters.isLogin;
    },
    priceChange: function(){
      var chg = this.currentPrice - this.openPrice;
      var percent = chg/this.openPrice*100;
      if(chg > 0) {
        return "+" + percent.toFixed(3) + "%";
      }
      if(chg < 0) {
        return percent.toFixed(3) + "%";
      }
      return "0.00%";
    }
  },
  methods: {
    init() {
      this.$store.commit("navigate", "nav-option");
      if(this.isLogin){
        this.getAssets();
      }

      this.startWebsock();
      this.getCoinList();
      this.getCoinInfo();

      this.initPageData();
      this.setTime();

      this.loadMyOrders(1);

      this.startKlineWebsock();
    },
    historyClick(tab){
      this.historyTab = tab;
    },
    initPageData(){
      this.getHistory();
      this.getOpening();
      this.getStarting();
      this.loadMyOrders(1);
    },
    // dateFormat: function(tick) {
    //   return moment(tick).format("YYYY-MM-DD HH:mm:ss");
    // },
    setTime(){
      // 1s
      var that = this;
      setInterval(function(){
        if(that.openingOption == undefined || that.openingOption.openTime == undefined || that.openingOption.openTime == 0 || that.openingOption.openTime == null){
          return;
        }
        var currentTime = new Date().getTime();
        that.currentPercent = Number(((currentTime - that.openingOption.openTime) / that.currentCoin.closeTimeGap / 10).toFixed(2));
        if(that.currentPercent >= 100){
          that.initPageData();
        }
      }, 1000);
    },
    getCoinList() {
      var that = this;
      this.$http.post(this.host + "/option/coin/coin-list").then(response => {
        var resp = response.body;
        if (resp.code == 0) {
          this.coinList = resp.data;
        } else {
          this.$Notice.error({
              title: that.$t("exchange.tip"),
              desc: resp.message
            });
        }
      });
    },
    selectCount(count) {
      this.currentSelectCount = count;
    },
    changeCoin(symbol) {
      this.currentCoinSymbol = symbol;
      this.pageNo = 1;
      this.currentPrice = this.openPrice;
      this.startWebsock();
      this.getCoinInfo();

      this.initPageData();

      this.loadMyOrders(1);

      this.startKlineWebsock();
    },
    getAssets(){
      var that = this;
      if(this.isLogin) {
        this.$http.post(this.host + "/uc/asset/wallet").then(response => {
          var resp = response.body;
          if (resp.code == 0) {
            this.tableMoney = resp.data;
            for (let i = 0; i < this.tableMoney.length; i++) {
              if(this.tableMoney[i].coin.unit == "USDT") {
                this.assetUsdt = this.tableMoney[i].balance;
              }
            }
          } else {
            this.$Notice.error({
                title: that.$t("exchange.tip"),
                desc: resp.message
              });
          }
        });
      }
    },
    getHistory(){
      var that = this;
      let params = {};
      params.symbol = this.currentCoinSymbol;
      params.count = 40;

      this.$http.post(this.host + "/option/option/history", params).then(response => {
        var resp = response.body;
        if (resp.code == 0) {
          this.historyList = resp.data.content;
          this.historyList.reverse();
        } else {
          this.$Notice.error({
              title: that.$t("exchange.tip"),
              desc: resp.message
            });
        }
      });
    },
    getOpening() {
      var that = this;
      let params = {};
      params.symbol = this.currentCoinSymbol;

      this.$http.post(this.host + "/option/option/opening", params).then(response => {
        var resp = response.body;
        if (resp.code == 0) {
          if(resp.data.length > 0) {
            this.openingOption = resp.data[0];
            this.openPrice = this.openingOption.openPrice;
            this.getMyOpeningOptionOrder(this.openingOption.id);
          }else{
            this.openingOption = {
              id: 0
            };
          }
        } else {
          this.$Notice.error({
              title: that.$t("exchange.tip"),
              desc: resp.message
            });
        }
      });
    },
    getStarting() {
      var that = this;
      let params = {};
      params.symbol = this.currentCoinSymbol;

      this.$http.post(this.host + "/option/option/starting", params).then(response => {
        var resp = response.body;
        if (resp.code == 0) {
          if(resp.data.length > 0) {
            this.startingOption = resp.data[0];
            }
        } else {
          this.$Notice.error({
              title: that.$t("exchange.tip"),
              desc: resp.message
            });
        }
      });
    },
    getMyOpeningOptionOrder(optionId){
      var that = this;
      this.myOpeningOption = {betAmount: 0};
      if(this.isLogin) {
        let params = {};
        params.symbol = this.currentCoinSymbol;
        params.optionId = optionId;

        this.$http.post(this.host + "/option/order/current", params).then(response => {
          var resp = response.body;
          if (resp.code == 0) {
            if(resp.data.length > 0){
              this.myOpeningOption = resp.data[0];
            }
          } else {
            this.$Notice.error({
              title: that.$t("exchange.tip"),
              desc: resp.message
            });
          }
        });
      }
    },
    getMyStartingOptionOrder(optionId){
      var that = this;
      this.myStartingOption = {betAmount: 0};
      if(this.isLogin) {
        let params = {};
        params.symbol = this.currentCoinSymbol;
        params.optionId = optionId;

        this.$http.post(this.host + "/option/order/current", params).then(response => {
          var resp = response.body;
          if (resp.code == 0) {
            if(resp.data.length > 0){
              this.myStartingOption = resp.data[0];
            }
          } else {
            this.$Notice.error({
              title: that.$t("exchange.tip"),
              desc: resp.message
            });
          }
        });
      }
    },
    getCoinInfo(){
      var that = this;
      let params = {};
      params.symbol = this.currentCoinSymbol;
      this.$http.post(this.host + "/option/coin/coin-info", params).then(response => {
          var resp = response.body;
          if (resp.code == 0) {
            this.currentCoin = resp.data;
            this.currentCoinAmountList = this.currentCoin.amount.split(",");
          } else {
            this.$Notice.error({
              title: that.$t("exchange.tip"),
              desc: resp.message
            });
          }
        });
    },
    confirm(){
      
      this.modal = true;
    },
    cancel(){
      this.modal = false;
    },
    submit(){
      this.modal = false;
      var that = this;
      let params = {};

      this.$Spin.show();
    },
    startKlineWebsock(){
      if (this.stompClientK) {
        this.stompClientK.ws.close();
      }
      var stompClientK = null;
      var that = this;
      var socket = new SockJS(that.host + that.api.swap.ws);
      stompClientK = Stomp.over(socket);
      this.stompClientK = stompClientK;
      stompClientK.debug = false;

      stompClientK.connect({}, function(frame) {
        that.datafeedK = new Datafeeds.WebsockFeed(
          that.host + "/swap",
          that.currentCoin,
          stompClientK,
          that.baseCoinScale
        );
        that.getKline();
      });
    },
    startWebsock() {
      if (this.stompClient) {
        this.stompClient.ws.close();
      }
      var stompClient = null;
      var that = this;
      var socket = new SockJS(that.host + that.api.option.ws);
      stompClient = Stomp.over(socket);
      this.stompClient = stompClient;
      stompClient.debug = false;

      stompClient.connect({}, function(frame) {
        that.datafeed = new Datafeeds.WebsockFeed(
          that.host + "/option",
          that.currentCoin,
          stompClient,
          that.baseCoinScale
        );
        });
    },
    addClick(direction){
      var that = this;
      if(!this.isLogin) {
        this.$Message.error(this.$t("option.loginFirst"));
        return;
      }
      if(this.currentSelectCount == 0) {
        this.$Message.error(this.$t("option.selectAmount"));
        return;
      }
      if(this.currentSelectCount > this.assetUsdt) {
        this.$Message.error(this.$t("option.balancenotenough"));
        return;
      }
      var that = this;
      let params = {};
      params.symbol = this.currentCoinSymbol;
      params.direction = direction;
      params.optionId = this.startingOption.id;
      params.amount = this.currentSelectCount;
      this.$Spin.show();
      this.$http.post(this.host + "/option/order/add", params).then(response => {
        var resp = response.body;
        if (resp.code == 0) {
          this.getStarting();
          this.getAssets();
          this.loadMyOrders(1);
          this.$Message.success(this.$t("option.betsuccess"));
        } else {
          this.$Notice.error({
            title: that.$t("exchange.tip"),
            desc: resp.message
          });
        }
        this.$Spin.hide();
      });
    },
    loadMyOrders(page){
      if(!this.isLogin) {
        return;
      }
      var that = this;
      this.pageNo = page;
      let params = {};
      params.symbol = this.currentCoinSymbol;
      params.pageNo = this.pageNo - 1;
      params.pageSize = this.pageSize;

      this.orders = [];
      this.$http.post(this.host + "/option/order/history", params).then(response => {
        var resp = response.body;
        let rows = [];
        if (resp.code == 0) {
          if (resp.data.content.length > 0) {
            this.total = resp.data.totalElements;
            for (var i = 0; i < resp.data.content.length; i++) {
              var row = resp.data.content[i];
              rows.push(row);
            }
            this.orders = rows;
          }
        } else {
          this.$Notice.error({
            title: that.$t("exchange.tip"),
            desc: resp.message
          });
        }
        this.$Spin.hide();
      });
    },
	
    getKline() {
      var that = this;
      let config = {
        autosize: true,
        height: 200,
        fullscreen: true,
        symbol: that.currentCoinSymbol,
        interval: "1", // 默认K线周期
        timezone: this.getTimezone4K(),
        toolbar_bg: "#0b1520",
        container_id: "kline_container",
        datafeed: that.datafeedK,
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
          "volume_force_overlay",
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
        custom_css_url: "bundles/common_option.css",
        supported_resolutions: ["1", "5", "15", "30", "60", "1D", "1W", "1M"],
        charts_storage_url: "http://saveload.tradingview.com",
        charts_storage_api_version: "1.1",
        client_id: "tradingview.com",
        user_id: "public_user_id",
        overrides: {
          "paneProperties.background": "#0b1520",
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
          { text: "1min", resolution: "1", description: "realtime", title: that.$t("swap.realtime") },
          { text: "1min", resolution: "1", description: "1min" },
          { text: "5min", resolution: "5", description: "5min" },
          { text: "15min", resolution: "15", description: "15min" },
          { text: "30min", resolution: "30", description: "30min" },
          { text: "1hour", resolution: "60", description: "1hour", title: "1hour" },
          { text: "4hour", resolution: "240", description: "4hour",title: "4hour" },
          { text: "1day", resolution: "1D", description: "1day",title: "1day" },
          { text: "1week", resolution: "1W", description: "1week", title: "1week" },
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
              widget.chart().setChartType(2);
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
            .append("<span>M1</span>")
            .addClass("selected");

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
            .append("<span>M5</span>");

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
    }
  }
};
</script>

<style>
.ctc .item-title{
  font-size: 20px;
  text-align: center;
  font-weight: bold;
  color: rgb(188, 188, 188);
}
.ctc .red{
  color: #f2334f;
}
.ctc .green{
  color: #45b854;
}
.ctc .item-title .unit{
  font-size: 14px;
}
.option{
  color: rgb(188, 188, 188);
}
.option .item-desc{
  font-size: 12px;
  text-align: center;
  color: #7c7f82;
}
.option .notice-bottom{
  margin-top: 5px;height: 55px;background-color:#192330;padding-top: 12px;color: rgb(42, 147, 255);
}
.option .notice-btn-left{
  height: 30px;line-height: 30px;width: 42%;margin-left: 5%;float:left;border-radius:3px;border: 1px solid rgb(0, 116, 235);
}
.option .notice-btn-left:hover{
  cursor: pointer;
}
.option #sendCode {
  position: absolute;
  border: none;
  background: none;
  top: 6px;
  outline: none;
  right: 0;
  width: 30%;
  color: #f0ac19;
  cursor: pointer;
  height: 20px;
  line-height: 20px;
  border-left: 1px solid #dddee1;
}
.option .notice-btn-right{
  height: 30px;line-height: 30px;width: 42%;margin-right: 5%;float:right;border-radius:3px;border: 1px solid rgb(0, 116, 235);
}
.option .notice-btn-right:hover{
  cursor: pointer;
}
.option .ivu-tabs-bar{
    border-bottom: 1px solid #323c53;
    font-size: 18px;
}
.option .ivu-tabs-nav .ivu-tabs-tab:hover{
    color: #f0a70a;
}
.option .ivu-tabs-nav .ivu-tabs-tab:hover, .option .ivu-tabs-nav .ivu-tabs-tab-active{
    color: #f0a70a;
    font-size: 18px;
}
.option .ivu-tabs-ink-bar{
    background-color: #f0a70a;
}
.option .buy_total{
  border-top: 1px solid #323c53;
  padding-top: 30px;
  margin-bottom: 30px;
}
.option .trade_bd_ctc{
  width: 100%;
  min-width: 800px;
  height: 750px;
}
.option .trade_bd_ctc .panel {
    position: relative;
    z-index: 2;
    float: left;
    width: 100%;
    height: 750px;
    margin-top: 0;
    margin-right: 0;
    border: 0 solid transparent;
    padding-top: 15px;
}

.option .trade_panel{
  background: transparent!important;
}
.option .trade_panel .panel .hd {
    line-height: 20px;
    height: 20px;
    border-bottom: 1px solid #1F2943;
    margin-bottom: 5px;
}

.option .trade_panel .panel .hd span {
    padding-left: 0;
    font-size: 12px;
    margin: 0 3px;
    float:right;
}
.ctc-order-status{
  text-align:center;margin-bottom: 15px;background: #f0a70a;padding: 5px 0px;border-radius: 2px;color: #000000;
}
.option .trade_panel .panel .hd b {
    padding-left: 0;
    font-size: 12px;
    color: #7A98F7;
    float:right;
}

.option .trade_panel .panel .hd.hd_login a {
    float: right;
    text-decoration: none;
    font-size: 12px;
    margin-right: 10px;
}

.option .trade_panel .panel.panel_buy {
    padding-right: 15px;
    padding-left: 15px;
    background: #192330;
}

.option .trade_panel .panel.panel_sell {
    padding-right: 15px;
    padding-left: 15px;
    background: #192330;
    margin-left: 5px;
}
.option .trade_wrap .buy-input .ivu-input {
  color: rgb(220, 142, 0);
  font-weight: bold;
  font-size: 20px;
  height: 35px;
}
.option .trade_wrap .sell-input .ivu-input {
  color: #f2334f;
  font-weight: bold;
  font-size: 20px;
  height: 35px;
}
.option .ivu-tabs{
  color: #a5a5a5;
}
.option .trade_wrap .trade-input .ivu-input {
    border: 1px solid #27313e;
    color: #fff;
    height: 35px;
    border-radius: 0;
}

.option .trade_wrap .ivu-input-wrapper {
    outline: none;
}

.option .trade_wrap .ivu-input:focus,
.option .trade_wrap .ivu-input:hover {
    box-shadow: none;
    outline: none;
}
.option .trade_wrap .ivu-input-number-input:focus,
.option .trade_wrap .ivu-input-number-input:hover {
    box-shadow: none;
    border-color: #41546d;
    outline: none;
}

.option .trade_wrap .ivu-input:hover {
    box-shadow: none;
    outline: none;
}
.option .trade_wrap .ivu-input-number-input:hover {
    box-shadow: none;
    border-color: #41546d;
    outline: none;
}
.option .trade_wrap .ivu-form-item-content input{
    padding-left: 5px;
    text-align:center;
    padding-right: 55px;
    font-size: 16px;
}
.option .trade_wrap .ivu-form-item-content input::-webkit-input-placeholder {
    font-size: 14px;
    color: #515a6e;
    margin-bottom: 10px;
    text-align: left;
}
.option .trade_wrap .trade-input input::-webkit-input-placeholder {

}
.option .trade_wrap .ivu-form-item-content label.before {
    position: absolute;
    top: 4px;
    left: 10px;
    color: #7c7f82;
    z-index: 2;
    font-size: 14px;
}

.option .trade_wrap .ivu-form-item-content label.after {
    position: absolute;
    top: 4px;
    right: 10px;
    color: #7c7f82;
    font-size: 14px;
}
.trade_bd_ctc Button {
    width: 100%;
    border: 0;
    color: #fff;
}

.trade_bd_ctc Button.bg-red {
    background-color: #f15057;
}
.trade_bd_ctc Button.bg-red:hover {
    background-color: #ff7278;
}

.trade_bd_ctc Button.bg-green {
    background-color: #00b275;
}
.trade_bd_ctc Button.bg-green:hover {
    background-color: #01ce88;
}

.trade_bd_ctc Button.bg-gray {
    background-color: #35475b;
    cursor: not-allowed;
    color: #9fabb5;
}
.trade_bd_ctc Button.bg-gray:hover{
    color: #9fabb5!important;
}
.trade_bd_ctc Button:hover {
    /* background: #54679F; */
}
.option .trade_wrap .ivu-btn{
  color: #FFF!important;
}
.option .total{
  min-height: 90px;
}
.trade-input .ivu-form-item-content .ivu-radio-group .ivu-radio-wrapper{
  cursor: auto!important;
}
.option .trade_wrap .ivu-btn.ivu-btn-small{
  padding: 2px 5px!important;
}
.option .ivu-progress .ivu-progress-outer .ivu-progress-inner{
  background-color: #5d4920;
  border-radius: 0px;
}
.color_green{
  color: #00b275;
}
.color_red{
  color: #f15057;
}
.color_gold{
  color: #f0a70a;
}
.current-period{
  position: relative;overflow: hidden;
  background: #0b1520;
    background-size: 500% 500%;
    animation: gradientBG 5s ease infinite;
}
.current-period span:nth-child(1){
  display: block;
  position: absolute;
  height: 3px;
  width:200px;
  top:5px;
  left:0px;
  background: linear-gradient(to right, rgba(0,0,0,0), #ff8100);
  border-top-right-radius: 1px;
  border-bottom-right-radius: 1px;
  animation: span1 3s linear infinite;
  animation-delay: 1s;
}
@keyframes span1{
    0%{
        left:50%
    }
    100%{
        left:100%;
    }
}
.current-period span:nth-child(2){
    display: block;
    position: absolute;
    height: 70px;
    width: 3px;
    top:-70px;
    right:0px;
    background: linear-gradient(to bottom, rgba(0,0,0,0), #ff8100);
    border-bottom-left-radius: 1px;
    border-bottom-right-radius: 1px;
    animation: span2 3s linear infinite;
    animation-delay: 3.2s;
}
@keyframes span2{
    0%{
        top:-70px;
    }
    100%{
        top:100%;
    }
}
.current-period  span:nth-child(3){
    display: block;
    position: absolute;
    height:3px;
    width:200px;
    right:50%;
    top: 0px;
    background: linear-gradient(to left, rgba(0,0,0,0), #ff8100);
    border-top-left-radius: 1px;
    border-bottom-left-radius: 1px;
    animation: span3 3s linear infinite;
    animation-delay: 1s;
}
@keyframes span3{
    0%{
        right:40%;
    }
    100%{
        right: 100%;
    }
}

.current-period  span:nth-child(4){
    display: block;
    position: absolute;
    height:70px;
    width:3px;
    bottom:-70px;
    left:0px;
    background: linear-gradient(to bottom, rgba(0,0,0,0), #ff8100);
    border-top-right-radius: 1px;
    border-top-left-radius: 1px;
    animation: span4 3s linear infinite;
    animation-delay: 3.2s;
}
@keyframes span4{
    0%{
        top: -70px;
    }
    100%{
        top:100%;
    }
}


.current-period  span:nth-child(5){
  display: block;
  position: absolute;
  height: 3px;
  width:200px;
  top:0px;
  right:50%;
  background: linear-gradient(to right, rgba(0,0,0,0), #ff8100);
  border-top-right-radius: 1px;
  border-bottom-right-radius: 1px;
  animation: span5 3s linear infinite;
  animation-delay: 1s;
}
@keyframes span5{
    0%{
        left:40%;
    }
    100%{
        left:100%;
    }
}

.last-period{
    background: linear-gradient(-45deg, #a73e00, #3f0084, #a73e00, #3f0084, #a73e00);
    background-size: 400% 400%;
    animation: gradientBG 5s ease infinite;
}
@keyframes gradientBG {
  0% {
    background-position: 0% 0%;
  }
  50% {
    background-position: 50% 100%;
  }
  100% {
    background-position: 0% 0%;
  }
}
.option .ctc-container{
  width: 100%;
}
.option-tab{
  position: relative;
  width: 100%;margin-bottom: 10px;
  border-bottom: 1px solid #323c53;
}
.option-tab:after{
  height: 1px;
  background: #FFF;
  width: 100%;
  position: absolute;
  bottom: 1px;
}
.option-tab-item{
  float:left;margin-right: 10px;padding: 5px 10px;font-size: 17px;
}
.option-tab-item:hover{
  cursor: pointer;
}
.option-tab-current{
  border-bottom: 2px solid #f0a70a!important;
  color: #f0a70a!important;
}
.option .ivu-progress-text-inner{
  display: inline-block;
  width: 40px;
}
.option iframe{
  height:240px!important;
}
</style>

<style lang="scss" scoped>
  .option {
    height: 100%;
    background-size: cover;
    position: relative;
    overflow: hidden;
    padding-bottom: 50px;
    padding-top: 60px;
    color: #a5a5a5;
  }
  .option .bannerimg {
    display: block;
    width: 100%;
  }
  .option .ctc_container {
    padding: 0 10%;
    text-align: center;
    height: 100%;
    > h1 {
      margin-top: -150px;
      font-size: 32px;
      line-height: 1;
      padding: 50px 0 20px 0;
      letter-spacing: 3px;
    }
  }
  .option .main {
    margin-top: 55px;
    display: flex;
    justify-content: space-between;
    flex-direction: row;
    flex-wrap: wrap;
  }
  .ctc-container{
    min-height: 470px;
  }
  .bottom-panel{
      border-top: 1px solid rgb(237, 237, 237);margin-top: 15px;
      .bottom{
        display: flex;
        flex-direction: row;
        justify-content: space-between;
        span{
          font-size: 12px;
          color: #a7a7a7;
          margin-top:15px;
        }
        button, a{
          margin-top: 11px;
        }
        a.ivu-btn-primary{
          background:#0095ff;
        }
        a.ivu-btn-primary:hover{
          background: #2ba7ff;
        }
      }
  }
  .right{
    float: right;
  }
  .left{
    float: left;
  }
  .gray{
    color: #a7a7a7;
  }
  .option .quantity-group{
    .quantity-item{
      display: inline-block;
      padding: 0px 15px;
      border-radius: 3px;
      border: 1px solid #515a6e;
      margin-left: 15px;
      &:hover{
        border: 1px solid #f0ac19;
        cursor:pointer;
      }
    }
    .current{
      border: 1px solid #f0ac19;
      color: #f0ac19;
    }
  }
  .result-panel{
    border-radius: 5px;
    min-height:300px;
    background: #0b1520;
    width: 100%;
    text-align:left;
    padding: 10px 10px;
    margin-bottom: 10px;
    .title-switch{
      display: inline-block;
      margin: 0 8px;
      padding: 0px 3px 3px 3px;
      border-bottom: 2px solid #0b1520;
      &:hover{
        cursor: pointer;
        color: #f0a70a;
      }
    }
    .switch-current{
      color: #f0a70a!important;
      border-bottom: 2px solid #f0a70a!important;
    }
    .result-item{
      width:5%;
      display:inline-block;
      margin-bottom: 30px;
      float:left;
      .item-title{
        width: 100%;text-align: center;
        span{
          display: inline-block;width: 100%;line-height: 20px;font-size:13px;
        }
      }
      .item-body{
        width: 100%;text-align: center;color:#FFF;font-weight: bold;margin-top: 10px;
        span{
          display: inline-block;height:25px;line-height: 25px;width:25px;border-radius: 25px;font-size:16px;font-weight:bold;
          i{
            margin-top: -4px;
          }
        }
        .tip-item{
          span{
            display: inline-block;width: 60px;font-size: 10px;height:16px;line-height: 16px;text-align: right;
            font-size: 8px;font-weight:normal;
          }
        }
      }

      &:not(:last-child){
        border-right: 1px dashed #2c3038;
      }
      &:nth-child(20) {
        border-right: none;
      }
    }
  }
  .bg-green{
    background: #00b275;
  }
  .bg-red{
    background: #f2334f;
  }
  .bg-gray{
    background: #585858;
  }
  .last-period{
    width: 100%;
    border-radius: 5px;
    margin-bottom: 10px;
    min-height: 40px;
    .period-content{
      width:560px;float: left;margin-left: 30px;height:40px;line-height: 40px;font-size:12px;
      .content-item{
        float: left;width:33%;text-align:center;text-shadow: 2px 2px 5px #00000047;
        span:nth-child(1) {
          display: inline-block;height: 20px;line-height: 20px;background: #0000004d;padding: 0px 8px;border-radius: 3px;box-shadow: 2px 2px 5px 0px #00000047;
        }
        .direction{
          display: inline-block;height: 20px;line-height: 20px;background: #FFF;padding: 0px 8px;border-radius: 3px;box-shadow: 2px 2px 5px 0px #00000047;
        }
      }
    }
  }
  .current-period{
    min-height: 170px;width:100%;margin-bottom: 30px;padding: 10px 10px;border-radius: 5px;
    .period-title{
      display: inline-block;width: 100%;text-align: center;font-size: 24px;font-weight: bold;
    }
    .period-time{
      display: inline-block;width: 100%;text-align: center;font-size: 14px;margin-bottom: 20px;letter-spacing:1px;
    }
    .period-reward{
      width:33.3%;float:left;padding: 10px 0px;background:#111a25;
      &:not(:last-child){
        border-right: 2px dashed #0b1520;
      }
      &:first-child{
        border-top-left-radius: 10px;
        border-bottom-left-radius: 10px;
      }
      &:last-child{
        border-top-right-radius: 10px;
        border-bottom-right-radius: 10px;
      }
    }
    
  }
</style>
