package com.bizzan.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/4/18 0018.
 */

public class EntrustHistory_constract implements Serializable {


    /**
     * content : [{"id":7926,"contractId":1,"memberId":10005,"contractOrderEntrustId":"CE159913195618774","patterns":"FIXED","entrustType":"CLOSE","direction":"SELL","type":"MARKET_PRICE","symbol":"BTC/USDT","coinSymbol":"BTC","baseSymbol":"USDT","triggerPrice":0,"entrustPrice":0,"tradedPrice":11254.17,"principalUnit":"USDT","principalAmount":0,"currentPrice":11254.17,"openFee":null,"closeFee":0.06,"shareNumber":100,"volume":6,"tradedVolume":6,"profitAndLoss":-5.91738,"status":"ENTRUST_SUCCESS","createTime":1599131956187,"triggeringTime":0,"isFromSpot":0,"isBlast":1},{"id":7925,"contractId":1,"memberId":10005,"contractOrderEntrustId":"CE159911713850682","patterns":"FIXED","entrustType":"CLOSE","direction":"SELL","type":"LIMIT_PRICE","symbol":"BTC/USDT","coinSymbol":"BTC","baseSymbol":"USDT","triggerPrice":0,"entrustPrice":11374.24,"tradedPrice":11379.5,"principalUnit":"USDT","principalAmount":5,"currentPrice":11286.07,"openFee":null,"closeFee":0.05,"shareNumber":100,"volume":5,"tradedVolume":5,"profitAndLoss":1.054415,"status":"ENTRUST_SUCCESS","createTime":1599117138506,"triggeringTime":0,"isFromSpot":0,"isBlast":0},{"id":7924,"contractId":1,"memberId":10005,"contractOrderEntrustId":"CE159911453352038","patterns":"FIXED","entrustType":"OPEN","direction":"BUY","type":"LIMIT_PRICE","symbol":"BTC/USDT","coinSymbol":"BTC","baseSymbol":"USDT","triggerPrice":11265,"entrustPrice":11266,"tradedPrice":11265.46,"principalUnit":"USDT","principalAmount":1,"currentPrice":11275.02,"openFee":0.01,"closeFee":null,"shareNumber":100,"volume":1,"tradedVolume":1,"profitAndLoss":0,"status":"ENTRUST_SUCCESS","createTime":1599114533520,"triggeringTime":0,"isFromSpot":1,"isBlast":0},{"id":7923,"contractId":1,"memberId":10005,"contractOrderEntrustId":"CE159911441759678","patterns":"FIXED","entrustType":"OPEN","direction":"BUY","type":"LIMIT_PRICE","symbol":"BTC/USDT","coinSymbol":"BTC","baseSymbol":"USDT","triggerPrice":11290,"entrustPrice":11289.19,"tradedPrice":11289.01,"principalUnit":"USDT","principalAmount":1,"currentPrice":11296.21,"openFee":0.01,"closeFee":null,"shareNumber":100,"volume":1,"tradedVolume":1,"profitAndLoss":0,"status":"ENTRUST_SUCCESS","createTime":1599114417596,"triggeringTime":0,"isFromSpot":1,"isBlast":0},{"id":7922,"contractId":1,"memberId":10005,"contractOrderEntrustId":"CE159911182168237","patterns":"FIXED","entrustType":"OPEN","direction":"BUY","type":"LIMIT_PRICE","symbol":"BTC/USDT","coinSymbol":"BTC","baseSymbol":"USDT","triggerPrice":0,"entrustPrice":11296,"tradedPrice":11275.96,"principalUnit":"USDT","principalAmount":1,"currentPrice":11275.94,"openFee":0.01,"closeFee":null,"shareNumber":100,"volume":1,"tradedVolume":1,"profitAndLoss":0,"status":"ENTRUST_SUCCESS","createTime":1599111821682,"triggeringTime":0,"isFromSpot":1,"isBlast":0},{"id":7921,"contractId":1,"memberId":10005,"contractOrderEntrustId":"CE159911175941326","patterns":"FIXED","entrustType":"OPEN","direction":"BUY","type":"LIMIT_PRICE","symbol":"BTC/USDT","coinSymbol":"BTC","baseSymbol":"USDT","triggerPrice":0,"entrustPrice":11298,"tradedPrice":11277.96,"principalUnit":"USDT","principalAmount":1,"currentPrice":11275.7,"openFee":0.01,"closeFee":null,"shareNumber":100,"volume":1,"tradedVolume":1,"profitAndLoss":0,"status":"ENTRUST_SUCCESS","createTime":1599111759413,"triggeringTime":0,"isFromSpot":1,"isBlast":0},{"id":7920,"contractId":1,"memberId":10005,"contractOrderEntrustId":"CE159910072752270","patterns":"FIXED","entrustType":"OPEN","direction":"BUY","type":"LIMIT_PRICE","symbol":"BTC/USDT","coinSymbol":"BTC","baseSymbol":"USDT","triggerPrice":11300,"entrustPrice":11414.57,"tradedPrice":11410.69,"principalUnit":"USDT","principalAmount":1,"currentPrice":11412.76,"openFee":0.01,"closeFee":null,"shareNumber":100,"volume":1,"tradedVolume":1,"profitAndLoss":0,"status":"ENTRUST_SUCCESS","createTime":1599100727522,"triggeringTime":0,"isFromSpot":1,"isBlast":0},{"id":7919,"contractId":1,"memberId":10005,"contractOrderEntrustId":"CE159910064881377","patterns":"FIXED","entrustType":"OPEN","direction":"BUY","type":"LIMIT_PRICE","symbol":"BTC/USDT","coinSymbol":"BTC","baseSymbol":"USDT","triggerPrice":11450,"entrustPrice":11419.86,"tradedPrice":11419.84,"principalUnit":"USDT","principalAmount":1,"currentPrice":11416.01,"openFee":0.01,"closeFee":null,"shareNumber":100,"volume":1,"tradedVolume":1,"profitAndLoss":0,"status":"ENTRUST_SUCCESS","createTime":1599100648813,"triggeringTime":0,"isFromSpot":1,"isBlast":0},{"id":7917,"contractId":1,"memberId":10005,"contractOrderEntrustId":"CE159909869468438","patterns":"FIXED","entrustType":"OPEN","direction":"BUY","type":"LIMIT_PRICE","symbol":"BTC/USDT","coinSymbol":"BTC","baseSymbol":"USDT","triggerPrice":0,"entrustPrice":11412.03,"tradedPrice":11410.33,"principalUnit":"USDT","principalAmount":1,"currentPrice":11409.62,"openFee":0.01,"closeFee":null,"shareNumber":100,"volume":1,"tradedVolume":1,"profitAndLoss":0,"status":"ENTRUST_SUCCESS","createTime":1599098694684,"triggeringTime":0,"isFromSpot":1,"isBlast":0},{"id":7916,"contractId":1,"memberId":10005,"contractOrderEntrustId":"CE159909867018781","patterns":"FIXED","entrustType":"OPEN","direction":"BUY","type":"LIMIT_PRICE","symbol":"BTC/USDT","coinSymbol":"BTC","baseSymbol":"USDT","triggerPrice":0,"entrustPrice":11412.03,"tradedPrice":11412.03,"principalUnit":"USDT","principalAmount":1,"currentPrice":11412.03,"openFee":0.01,"closeFee":null,"shareNumber":100,"volume":1,"tradedVolume":1,"profitAndLoss":0,"status":"ENTRUST_SUCCESS","createTime":1599098670187,"triggeringTime":0,"isFromSpot":1,"isBlast":0},{"id":7915,"contractId":1,"memberId":10005,"contractOrderEntrustId":"CE159909860497785","patterns":"FIXED","entrustType":"OPEN","direction":"BUY","type":"LIMIT_PRICE","symbol":"BTC/USDT","coinSymbol":"BTC","baseSymbol":"USDT","triggerPrice":0,"entrustPrice":11415.94,"tradedPrice":11415,"principalUnit":"USDT","principalAmount":1,"currentPrice":11415.01,"openFee":0.01,"closeFee":null,"shareNumber":100,"volume":1,"tradedVolume":1,"profitAndLoss":0,"status":"ENTRUST_SUCCESS","createTime":1599098604977,"triggeringTime":0,"isFromSpot":1,"isBlast":0},{"id":7914,"contractId":1,"memberId":10005,"contractOrderEntrustId":"CE159909836342584","patterns":"FIXED","entrustType":"OPEN","direction":"BUY","type":"LIMIT_PRICE","symbol":"BTC/USDT","coinSymbol":"BTC","baseSymbol":"USDT","triggerPrice":0,"entrustPrice":11424.24,"tradedPrice":11424.23,"principalUnit":"USDT","principalAmount":1,"currentPrice":11424.23,"openFee":0.01,"closeFee":null,"shareNumber":100,"volume":1,"tradedVolume":1,"profitAndLoss":0,"status":"ENTRUST_SUCCESS","createTime":1599098363425,"triggeringTime":0,"isFromSpot":1,"isBlast":0},{"id":7911,"contractId":1,"memberId":10005,"contractOrderEntrustId":"CE159907423009848","patterns":"FIXED","entrustType":"CLOSE","direction":"SELL","type":"MARKET_PRICE","symbol":"BTC/USDT","coinSymbol":"BTC","baseSymbol":"USDT","triggerPrice":0,"entrustPrice":11380,"tradedPrice":11380,"principalUnit":"USDT","principalAmount":1,"currentPrice":11380,"openFee":null,"closeFee":0.01,"shareNumber":100,"volume":1,"tradedVolume":1,"profitAndLoss":0.045187,"status":"ENTRUST_SUCCESS","createTime":1599074230098,"triggeringTime":0,"isFromSpot":0,"isBlast":0},{"id":7910,"contractId":1,"memberId":10005,"contractOrderEntrustId":"CE159907413480860","patterns":"FIXED","entrustType":"OPEN","direction":"BUY","type":"LIMIT_PRICE","symbol":"BTC/USDT","coinSymbol":"BTC","baseSymbol":"USDT","triggerPrice":0,"entrustPrice":11327.58,"tradedPrice":0,"principalUnit":"USDT","principalAmount":2,"currentPrice":11372.7,"openFee":0.02,"closeFee":null,"shareNumber":100,"volume":2,"tradedVolume":0,"profitAndLoss":0,"status":"ENTRUST_CANCEL","createTime":1599074134808,"triggeringTime":0,"isFromSpot":0,"isBlast":0},{"id":7909,"contractId":1,"memberId":10005,"contractOrderEntrustId":"CE159907410929885","patterns":"FIXED","entrustType":"OPEN","direction":"BUY","type":"LIMIT_PRICE","symbol":"BTC/USDT","coinSymbol":"BTC","baseSymbol":"USDT","triggerPrice":0,"entrustPrice":11377.58,"tradedPrice":11374.86,"principalUnit":"USDT","principalAmount":2,"currentPrice":11374.86,"openFee":0.02,"closeFee":null,"shareNumber":100,"volume":2,"tradedVolume":2,"profitAndLoss":0,"status":"ENTRUST_SUCCESS","createTime":1599074109298,"triggeringTime":0,"isFromSpot":0,"isBlast":0},{"id":7908,"contractId":1,"memberId":10005,"contractOrderEntrustId":"CE159904568804984","patterns":"FIXED","entrustType":"CLOSE","direction":"SELL","type":"MARKET_PRICE","symbol":"BTC/USDT","coinSymbol":"BTC","baseSymbol":"USDT","triggerPrice":0,"entrustPrice":0,"tradedPrice":11352.95,"principalUnit":"USDT","principalAmount":0,"currentPrice":11352.95,"openFee":null,"closeFee":0.1,"shareNumber":100,"volume":10,"tradedVolume":10,"profitAndLoss":-1.96216,"status":"ENTRUST_SUCCESS","createTime":1599045688049,"triggeringTime":0,"isFromSpot":0,"isBlast":1},{"id":7907,"contractId":1,"memberId":10005,"contractOrderEntrustId":"CE159904522334464","patterns":"FIXED","entrustType":"OPEN","direction":"BUY","type":"MARKET_PRICE","symbol":"BTC/USDT","coinSymbol":"BTC","baseSymbol":"USDT","triggerPrice":0,"entrustPrice":11391.03,"tradedPrice":11375.27,"principalUnit":"USDT","principalAmount":2,"currentPrice":11375.27,"openFee":0.1,"closeFee":null,"shareNumber":100,"volume":10,"tradedVolume":10,"profitAndLoss":0,"status":"ENTRUST_SUCCESS","createTime":1599045223344,"triggeringTime":0,"isFromSpot":0,"isBlast":0},{"id":7906,"contractId":1,"memberId":10005,"contractOrderEntrustId":"CE159904518766687","patterns":"FIXED","entrustType":"CLOSE","direction":"SELL","type":"MARKET_PRICE","symbol":"BTC/USDT","coinSymbol":"BTC","baseSymbol":"USDT","triggerPrice":0,"entrustPrice":0,"tradedPrice":11352.69,"principalUnit":"USDT","principalAmount":0,"currentPrice":11352.69,"openFee":null,"closeFee":0.1,"shareNumber":100,"volume":10,"tradedVolume":10,"profitAndLoss":-1.90692,"status":"ENTRUST_SUCCESS","createTime":1599045187666,"triggeringTime":0,"isFromSpot":0,"isBlast":1},{"id":7905,"contractId":1,"memberId":10005,"contractOrderEntrustId":"CE159904516976412","patterns":"FIXED","entrustType":"OPEN","direction":"BUY","type":"MARKET_PRICE","symbol":"BTC/USDT","coinSymbol":"BTC","baseSymbol":"USDT","triggerPrice":0,"entrustPrice":11370.32,"tradedPrice":11374.38,"principalUnit":"USDT","principalAmount":2,"currentPrice":11374.38,"openFee":0.1,"closeFee":null,"shareNumber":100,"volume":10,"tradedVolume":10,"profitAndLoss":0,"status":"ENTRUST_SUCCESS","createTime":1599045169764,"triggeringTime":0,"isFromSpot":0,"isBlast":0},{"id":7904,"contractId":1,"memberId":10005,"contractOrderEntrustId":"CE159904459473488","patterns":"FIXED","entrustType":"CLOSE","direction":"SELL","type":"MARKET_PRICE","symbol":"BTC/USDT","coinSymbol":"BTC","baseSymbol":"USDT","triggerPrice":0,"entrustPrice":0,"tradedPrice":11520,"principalUnit":"USDT","principalAmount":0,"currentPrice":11520,"openFee":null,"closeFee":0.02,"shareNumber":100,"volume":2,"tradedVolume":2,"profitAndLoss":-0.388048,"status":"ENTRUST_SUCCESS","createTime":1599044594734,"triggeringTime":0,"isFromSpot":0,"isBlast":1},{"id":7903,"contractId":1,"memberId":10005,"contractOrderEntrustId":"CE159904427550636","patterns":"FIXED","entrustType":"OPEN","direction":"BUY","type":"LIMIT_PRICE","symbol":"BTC/USDT","coinSymbol":"BTC","baseSymbol":"USDT","triggerPrice":0,"entrustPrice":11549.31,"tradedPrice":11546.41,"principalUnit":"USDT","principalAmount":0.2,"currentPrice":11552.54,"openFee":0.01,"closeFee":null,"shareNumber":100,"volume":1,"tradedVolume":1,"profitAndLoss":0,"status":"ENTRUST_SUCCESS","createTime":1599044275506,"triggeringTime":0,"isFromSpot":1,"isBlast":0},{"id":7902,"contractId":1,"memberId":10005,"contractOrderEntrustId":"CE159904422851573","patterns":"FIXED","entrustType":"OPEN","direction":"BUY","type":"LIMIT_PRICE","symbol":"BTC/USDT","coinSymbol":"BTC","baseSymbol":"USDT","triggerPrice":0,"entrustPrice":11554.01,"tradedPrice":11538.38,"principalUnit":"USDT","principalAmount":0.2,"currentPrice":11535.31,"openFee":0.01,"closeFee":null,"shareNumber":100,"volume":1,"tradedVolume":1,"profitAndLoss":0,"status":"ENTRUST_SUCCESS","createTime":1599044228515,"triggeringTime":0,"isFromSpot":1,"isBlast":0},{"id":7901,"contractId":1,"memberId":10005,"contractOrderEntrustId":"CE159904380392635","patterns":"FIXED","entrustType":"CLOSE","direction":"SELL","type":"MARKET_PRICE","symbol":"BTC/USDT","coinSymbol":"BTC","baseSymbol":"USDT","triggerPrice":0,"entrustPrice":0,"tradedPrice":11567.21,"principalUnit":"USDT","principalAmount":0,"currentPrice":11567.21,"openFee":null,"closeFee":0.05,"shareNumber":100,"volume":5,"tradedVolume":5,"profitAndLoss":-1.0004,"status":"ENTRUST_SUCCESS","createTime":1599043803926,"triggeringTime":0,"isFromSpot":0,"isBlast":1},{"id":7900,"contractId":1,"memberId":10005,"contractOrderEntrustId":"CE159904374873883","patterns":"FIXED","entrustType":"OPEN","direction":"BUY","type":"LIMIT_PRICE","symbol":"BTC/USDT","coinSymbol":"BTC","baseSymbol":"USDT","triggerPrice":0,"entrustPrice":11594.7,"tradedPrice":11590.4,"principalUnit":"USDT","principalAmount":1,"currentPrice":11596.47,"openFee":0.05,"closeFee":null,"shareNumber":100,"volume":5,"tradedVolume":5,"profitAndLoss":0,"status":"ENTRUST_SUCCESS","createTime":1599043748738,"triggeringTime":0,"isFromSpot":0,"isBlast":0},{"id":7899,"contractId":1,"memberId":10005,"contractOrderEntrustId":"CE159904345161887","patterns":"FIXED","entrustType":"CLOSE","direction":"SELL","type":"MARKET_PRICE","symbol":"BTC/USDT","coinSymbol":"BTC","baseSymbol":"USDT","triggerPrice":0,"entrustPrice":0,"tradedPrice":11580,"principalUnit":"USDT","principalAmount":0,"currentPrice":11580,"openFee":null,"closeFee":0.01,"shareNumber":100,"volume":1,"tradedVolume":1,"profitAndLoss":-0.491357,"status":"ENTRUST_SUCCESS","createTime":1599043451618,"triggeringTime":0,"isFromSpot":0,"isBlast":1},{"id":7898,"contractId":1,"memberId":10005,"contractOrderEntrustId":"CE159904297402342","patterns":"FIXED","entrustType":"OPEN","direction":"BUY","type":"LIMIT_PRICE","symbol":"BTC/USDT","coinSymbol":"BTC","baseSymbol":"USDT","triggerPrice":0,"entrustPrice":11638.33,"tradedPrice":11637.18,"principalUnit":"USDT","principalAmount":0.5,"currentPrice":11638.32,"openFee":0.01,"closeFee":null,"shareNumber":100,"volume":1,"tradedVolume":1,"profitAndLoss":0,"status":"ENTRUST_SUCCESS","createTime":1599042974023,"triggeringTime":0,"isFromSpot":0,"isBlast":0},{"id":7897,"contractId":1,"memberId":10005,"contractOrderEntrustId":"CE159904268003155","patterns":"FIXED","entrustType":"CLOSE","direction":"SELL","type":"MARKET_PRICE","symbol":"BTC/USDT","coinSymbol":"BTC","baseSymbol":"USDT","triggerPrice":0,"entrustPrice":0,"tradedPrice":11618.45,"principalUnit":"USDT","principalAmount":0,"currentPrice":11618.45,"openFee":null,"closeFee":0.06,"shareNumber":100,"volume":6,"tradedVolume":6,"profitAndLoss":-5.153766,"status":"ENTRUST_SUCCESS","createTime":1599042680031,"triggeringTime":0,"isFromSpot":0,"isBlast":1},{"id":7896,"contractId":1,"memberId":10005,"contractOrderEntrustId":"CE159904156774493","patterns":"FIXED","entrustType":"CLOSE","direction":"SELL","type":"SPOT_LIMIT","symbol":"BTC/USDT","coinSymbol":"BTC","baseSymbol":"USDT","triggerPrice":11710,"entrustPrice":11707.02,"tradedPrice":0,"principalUnit":"USDT","principalAmount":0,"currentPrice":11719.74,"openFee":null,"closeFee":0.01,"shareNumber":100,"volume":1,"tradedVolume":0,"profitAndLoss":0,"status":"ENTRUST_CANCEL","createTime":1599041567744,"triggeringTime":0,"isFromSpot":1,"isBlast":0},{"id":7895,"contractId":1,"memberId":10005,"contractOrderEntrustId":"CE159904125603825","patterns":"FIXED","entrustType":"CLOSE","direction":"BUY","type":"LIMIT_PRICE","symbol":"BTC/USDT","coinSymbol":"BTC","baseSymbol":"USDT","triggerPrice":0,"entrustPrice":11711.72,"tradedPrice":11618.45,"principalUnit":"USDT","principalAmount":4.9744897,"currentPrice":11719.74,"openFee":null,"closeFee":0.01,"shareNumber":100,"volume":1,"tradedVolume":1,"profitAndLoss":2.216838,"status":"ENTRUST_SUCCESS","createTime":1599041256038,"triggeringTime":0,"isFromSpot":0,"isBlast":0},{"id":7894,"contractId":1,"memberId":10005,"contractOrderEntrustId":"CE159904117065789","patterns":"FIXED","entrustType":"CLOSE","direction":"SELL","type":"SPOT_LIMIT","symbol":"BTC/USDT","coinSymbol":"BTC","baseSymbol":"USDT","triggerPrice":11710,"entrustPrice":11713.47,"tradedPrice":0,"principalUnit":"USDT","principalAmount":0,"currentPrice":11719.74,"openFee":null,"closeFee":0.01,"shareNumber":100,"volume":1,"tradedVolume":0,"profitAndLoss":0,"status":"ENTRUST_CANCEL","createTime":1599041170657,"triggeringTime":0,"isFromSpot":1,"isBlast":0},{"id":7893,"contractId":1,"memberId":10005,"contractOrderEntrustId":"CE159904114603211","patterns":"FIXED","entrustType":"CLOSE","direction":"SELL","type":"MARKET_PRICE","symbol":"BTC/USDT","coinSymbol":"BTC","baseSymbol":"USDT","triggerPrice":0,"entrustPrice":0,"tradedPrice":11719.74,"principalUnit":"USDT","principalAmount":0.49999999,"currentPrice":11719.74,"openFee":null,"closeFee":0.01,"shareNumber":100,"volume":1,"tradedVolume":1,"profitAndLoss":0.005354,"status":"ENTRUST_SUCCESS","createTime":1599041146032,"triggeringTime":0,"isFromSpot":0,"isBlast":0},{"id":7892,"contractId":1,"memberId":10005,"contractOrderEntrustId":"CE159904111653962","patterns":"FIXED","entrustType":"CLOSE","direction":"SELL","type":"LIMIT_PRICE","symbol":"BTC/USDT","coinSymbol":"BTC","baseSymbol":"USDT","triggerPrice":0,"entrustPrice":11717.08,"tradedPrice":11719.74,"principalUnit":"USDT","principalAmount":0.5,"currentPrice":11719.74,"openFee":null,"closeFee":0.01,"shareNumber":100,"volume":1,"tradedVolume":1,"profitAndLoss":0.005354,"status":"ENTRUST_SUCCESS","createTime":1599041116539,"triggeringTime":0,"isFromSpot":0,"isBlast":0},{"id":7891,"contractId":1,"memberId":10005,"contractOrderEntrustId":"CE159904013365311","patterns":"FIXED","entrustType":"CLOSE","direction":"BUY","type":"LIMIT_PRICE","symbol":"BTC/USDT","coinSymbol":"BTC","baseSymbol":"USDT","triggerPrice":0,"entrustPrice":11731.64,"tradedPrice":11719.74,"principalUnit":"USDT","principalAmount":5.00000025,"currentPrice":11719.74,"openFee":null,"closeFee":0.01,"shareNumber":100,"volume":1,"tradedVolume":1,"profitAndLoss":1.364361,"status":"ENTRUST_SUCCESS","createTime":1599040133653,"triggeringTime":0,"isFromSpot":0,"isBlast":0},{"id":7890,"contractId":1,"memberId":10005,"contractOrderEntrustId":"CE159904004326977","patterns":"FIXED","entrustType":"OPEN","direction":"SELL","type":"MARKET_PRICE","symbol":"BTC/USDT","coinSymbol":"BTC","baseSymbol":"USDT","triggerPrice":0,"entrustPrice":11731.63,"tradedPrice":11719.74,"principalUnit":"USDT","principalAmount":5,"currentPrice":11719.74,"openFee":0.01,"closeFee":null,"shareNumber":100,"volume":1,"tradedVolume":1,"profitAndLoss":0,"status":"ENTRUST_SUCCESS","createTime":1599040043269,"triggeringTime":0,"isFromSpot":0,"isBlast":0},{"id":7888,"contractId":1,"memberId":10005,"contractOrderEntrustId":"CE159903997347364","patterns":"FIXED","entrustType":"OPEN","direction":"SELL","type":"SPOT_LIMIT","symbol":"BTC/USDT","coinSymbol":"BTC","baseSymbol":"USDT","triggerPrice":11710,"entrustPrice":11731.53,"tradedPrice":0,"principalUnit":"USDT","principalAmount":5,"currentPrice":11719.74,"openFee":0.01,"closeFee":null,"shareNumber":100,"volume":1,"tradedVolume":0,"profitAndLoss":0,"status":"ENTRUST_CANCEL","createTime":1599039973473,"triggeringTime":0,"isFromSpot":1,"isBlast":0},{"id":7889,"contractId":1,"memberId":10005,"contractOrderEntrustId":"CE159903997347082","patterns":"FIXED","entrustType":"OPEN","direction":"SELL","type":"MARKET_PRICE","symbol":"BTC/USDT","coinSymbol":"BTC","baseSymbol":"USDT","triggerPrice":0,"entrustPrice":0,"tradedPrice":11719.74,"principalUnit":"USDT","principalAmount":5,"currentPrice":11719.74,"openFee":0.01,"closeFee":null,"shareNumber":100,"volume":1,"tradedVolume":1,"profitAndLoss":0,"status":"ENTRUST_SUCCESS","createTime":1599039973470,"triggeringTime":0,"isFromSpot":0,"isBlast":0},{"id":7887,"contractId":1,"memberId":10005,"contractOrderEntrustId":"CE159903995010437","patterns":"FIXED","entrustType":"OPEN","direction":"SELL","type":"LIMIT_PRICE","symbol":"BTC/USDT","coinSymbol":"BTC","baseSymbol":"USDT","triggerPrice":0,"entrustPrice":11731.53,"tradedPrice":0,"principalUnit":"USDT","principalAmount":5,"currentPrice":11719.74,"openFee":0.01,"closeFee":null,"shareNumber":100,"volume":1,"tradedVolume":0,"profitAndLoss":0,"status":"ENTRUST_CANCEL","createTime":1599039950104,"triggeringTime":0,"isFromSpot":0,"isBlast":0},{"id":7886,"contractId":1,"memberId":10005,"contractOrderEntrustId":"CE159903987716251","patterns":"FIXED","entrustType":"OPEN","direction":"SELL","type":"SPOT_LIMIT","symbol":"BTC/USDT","coinSymbol":"BTC","baseSymbol":"USDT","triggerPrice":11710,"entrustPrice":11731.53,"tradedPrice":0,"principalUnit":"USDT","principalAmount":5,"currentPrice":11719.74,"openFee":0.01,"closeFee":null,"shareNumber":100,"volume":1,"tradedVolume":0,"profitAndLoss":0,"status":"ENTRUST_CANCEL","createTime":1599039877162,"triggeringTime":0,"isFromSpot":1,"isBlast":0},{"id":7885,"contractId":1,"memberId":10005,"contractOrderEntrustId":"CE159903987716142","patterns":"FIXED","entrustType":"OPEN","direction":"SELL","type":"MARKET_PRICE","symbol":"BTC/USDT","coinSymbol":"BTC","baseSymbol":"USDT","triggerPrice":0,"entrustPrice":0,"tradedPrice":11719.74,"principalUnit":"USDT","principalAmount":5,"currentPrice":11719.74,"openFee":0.01,"closeFee":null,"shareNumber":100,"volume":1,"tradedVolume":1,"profitAndLoss":0,"status":"ENTRUST_SUCCESS","createTime":1599039877161,"triggeringTime":0,"isFromSpot":0,"isBlast":0},{"id":7884,"contractId":1,"memberId":10005,"contractOrderEntrustId":"CE159903986726056","patterns":"FIXED","entrustType":"OPEN","direction":"SELL","type":"LIMIT_PRICE","symbol":"BTC/USDT","coinSymbol":"BTC","baseSymbol":"USDT","triggerPrice":0,"entrustPrice":11730.52,"tradedPrice":0,"principalUnit":"USDT","principalAmount":5,"currentPrice":11719.74,"openFee":0.01,"closeFee":null,"shareNumber":100,"volume":1,"tradedVolume":0,"profitAndLoss":0,"status":"ENTRUST_CANCEL","createTime":1599039867260,"triggeringTime":0,"isFromSpot":0,"isBlast":0},{"id":7883,"contractId":1,"memberId":10005,"contractOrderEntrustId":"CE159903982791814","patterns":"FIXED","entrustType":"OPEN","direction":"BUY","type":"SPOT_LIMIT","symbol":"BTC/USDT","coinSymbol":"BTC","baseSymbol":"USDT","triggerPrice":11710,"entrustPrice":11715.04,"tradedPrice":0,"principalUnit":"USDT","principalAmount":0.5,"currentPrice":11719.74,"openFee":0.01,"closeFee":null,"shareNumber":100,"volume":1,"tradedVolume":0,"profitAndLoss":0,"status":"ENTRUST_CANCEL","createTime":1599039827918,"triggeringTime":0,"isFromSpot":1,"isBlast":0},{"id":7882,"contractId":1,"memberId":10005,"contractOrderEntrustId":"CE159903979299187","patterns":"FIXED","entrustType":"OPEN","direction":"BUY","type":"SPOT_LIMIT","symbol":"BTC/USDT","coinSymbol":"BTC","baseSymbol":"USDT","triggerPrice":null,"entrustPrice":11715.04,"tradedPrice":0,"principalUnit":"USDT","principalAmount":0.5,"currentPrice":11719.74,"openFee":0.01,"closeFee":null,"shareNumber":100,"volume":1,"tradedVolume":0,"profitAndLoss":0,"status":"ENTRUST_CANCEL","createTime":1599039792991,"triggeringTime":0,"isFromSpot":1,"isBlast":0},{"id":7881,"contractId":1,"memberId":10005,"contractOrderEntrustId":"CE159903979298716","patterns":"FIXED","entrustType":"OPEN","direction":"BUY","type":"MARKET_PRICE","symbol":"BTC/USDT","coinSymbol":"BTC","baseSymbol":"USDT","triggerPrice":0,"entrustPrice":0,"tradedPrice":11719.74,"principalUnit":"USDT","principalAmount":0.5,"currentPrice":11719.74,"openFee":0.01,"closeFee":null,"shareNumber":100,"volume":1,"tradedVolume":1,"profitAndLoss":0,"status":"ENTRUST_SUCCESS","createTime":1599039792987,"triggeringTime":0,"isFromSpot":0,"isBlast":0},{"id":7880,"contractId":1,"memberId":10005,"contractOrderEntrustId":"CE159903974330676","patterns":"FIXED","entrustType":"OPEN","direction":"BUY","type":"MARKET_PRICE","symbol":"BTC/USDT","coinSymbol":"BTC","baseSymbol":"USDT","triggerPrice":0,"entrustPrice":0,"tradedPrice":11719.74,"principalUnit":"USDT","principalAmount":0.5,"currentPrice":11719.74,"openFee":0.01,"closeFee":null,"shareNumber":100,"volume":1,"tradedVolume":1,"profitAndLoss":0,"status":"ENTRUST_SUCCESS","createTime":1599039743306,"triggeringTime":0,"isFromSpot":0,"isBlast":0},{"id":7879,"contractId":1,"memberId":10005,"contractOrderEntrustId":"CE159903974330562","patterns":"FIXED","entrustType":"OPEN","direction":"BUY","type":"SPOT_LIMIT","symbol":"BTC/USDT","coinSymbol":"BTC","baseSymbol":"USDT","triggerPrice":null,"entrustPrice":11715.04,"tradedPrice":0,"principalUnit":"USDT","principalAmount":0.5,"currentPrice":11719.74,"openFee":0.01,"closeFee":null,"shareNumber":100,"volume":1,"tradedVolume":0,"profitAndLoss":0,"status":"ENTRUST_CANCEL","createTime":1599039743305,"triggeringTime":0,"isFromSpot":1,"isBlast":0},{"id":7878,"contractId":1,"memberId":10005,"contractOrderEntrustId":"CE159903971181740","patterns":"FIXED","entrustType":"OPEN","direction":"BUY","type":"LIMIT_PRICE","symbol":"BTC/USDT","coinSymbol":"BTC","baseSymbol":"USDT","triggerPrice":0,"entrustPrice":11715.04,"tradedPrice":0,"principalUnit":"USDT","principalAmount":0.5,"currentPrice":11719.74,"openFee":0.01,"closeFee":null,"shareNumber":100,"volume":1,"tradedVolume":0,"profitAndLoss":0,"status":"ENTRUST_CANCEL","createTime":1599039711817,"triggeringTime":0,"isFromSpot":0,"isBlast":0},{"id":7877,"contractId":1,"memberId":10005,"contractOrderEntrustId":"CE159903946274199","patterns":"FIXED","entrustType":"OPEN","direction":"BUY","type":"SPOT_LIMIT","symbol":"BTC/USDT","coinSymbol":"BTC","baseSymbol":"USDT","triggerPrice":0,"entrustPrice":11718.75,"tradedPrice":0,"principalUnit":"USDT","principalAmount":0.5,"currentPrice":11719.74,"openFee":0.01,"closeFee":null,"shareNumber":100,"volume":1,"tradedVolume":0,"profitAndLoss":0,"status":"ENTRUST_CANCEL","createTime":1599039462741,"triggeringTime":0,"isFromSpot":1,"isBlast":0},{"id":7876,"contractId":1,"memberId":10005,"contractOrderEntrustId":"CE159903941281295","patterns":"FIXED","entrustType":"OPEN","direction":"SELL","type":"SPOT_LIMIT","symbol":"BTC/USDT","coinSymbol":"BTC","baseSymbol":"USDT","triggerPrice":null,"entrustPrice":11719.75,"tradedPrice":0,"principalUnit":"USDT","principalAmount":5,"currentPrice":11719.75,"openFee":0.01,"closeFee":null,"shareNumber":100,"volume":1,"tradedVolume":0,"profitAndLoss":0,"status":"ENTRUST_CANCEL","createTime":1599039412812,"triggeringTime":0,"isFromSpot":1,"isBlast":0},{"id":7875,"contractId":1,"memberId":10005,"contractOrderEntrustId":"CE159903938636663","patterns":"FIXED","entrustType":"OPEN","direction":"SELL","type":"LIMIT_PRICE","symbol":"BTC/USDT","coinSymbol":"BTC","baseSymbol":"USDT","triggerPrice":0,"entrustPrice":11719.75,"tradedPrice":11719.75,"principalUnit":"USDT","principalAmount":5,"currentPrice":11719.75,"openFee":0.01,"closeFee":null,"shareNumber":100,"volume":1,"tradedVolume":1,"profitAndLoss":0,"status":"ENTRUST_SUCCESS","createTime":1599039386366,"triggeringTime":0,"isFromSpot":0,"isBlast":0},{"id":7874,"contractId":1,"memberId":10005,"contractOrderEntrustId":"CE159903887423049","patterns":"FIXED","entrustType":"OPEN","direction":"BUY","type":"LIMIT_PRICE","symbol":"BTC/USDT","coinSymbol":"BTC","baseSymbol":"USDT","triggerPrice":11720,"entrustPrice":11731.36,"tradedPrice":11730.96,"principalUnit":"USDT","principalAmount":1,"currentPrice":11731.4,"openFee":0.02,"closeFee":null,"shareNumber":100,"volume":2,"tradedVolume":2,"profitAndLoss":0,"status":"ENTRUST_SUCCESS","createTime":1599038874230,"triggeringTime":0,"isFromSpot":1,"isBlast":0}]
     * last : false
     * totalPages : 144
     * totalElements : 7188
     * first : true
     * sort : [{"direction":"DESC","property":"createTime","ignoreCase":false,"nullHandling":"NATIVE","ascending":false,"descending":true}]
     * numberOfElements : 50
     * size : 50
     * number : 0
     */

    private boolean last;
    private int totalPages;
    private int totalElements;
    private boolean first;
    private int numberOfElements;
    private int size;
    private int number;
    private List<ContentBean> content;
    private List<SortBean> sort;

    public boolean isLast() {
        return last;
    }

    public void setLast(boolean last) {
        this.last = last;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(int totalElements) {
        this.totalElements = totalElements;
    }

    public boolean isFirst() {
        return first;
    }

    public void setFirst(boolean first) {
        this.first = first;
    }

    public int getNumberOfElements() {
        return numberOfElements;
    }

    public void setNumberOfElements(int numberOfElements) {
        this.numberOfElements = numberOfElements;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public List<ContentBean> getContent() {
        return content;
    }

    public void setContent(List<ContentBean> content) {
        this.content = content;
    }

    public List<SortBean> getSort() {
        return sort;
    }

    public void setSort(List<SortBean> sort) {
        this.sort = sort;
    }

    public static class ContentBean implements Serializable{
        /**
         * id : 7926
         * contractId : 1
         * memberId : 10005
         * contractOrderEntrustId : CE159913195618774
         * patterns : FIXED
         * entrustType : CLOSE
         * direction : SELL
         * type : MARKET_PRICE
         * symbol : BTC/USDT
         * coinSymbol : BTC
         * baseSymbol : USDT
         * triggerPrice : 0.0
         * entrustPrice : 0.0
         * tradedPrice : 11254.17
         * principalUnit : USDT
         * principalAmount : 0.0
         * currentPrice : 11254.17
         * openFee : null
         * closeFee : 0.06
         * shareNumber : 100.0
         * volume : 6.0
         * tradedVolume : 6.0
         * profitAndLoss : -5.91738
         * status : ENTRUST_SUCCESS
         * createTime : 1599131956187
         * triggeringTime : 0
         * isFromSpot : 0
         * isBlast : 1
         */

        private int id;
        private int contractId;
        private int memberId;
        private String contractOrderEntrustId;
        private String patterns;
        private String entrustType;
        private String direction;
        private String type;
        private String symbol;
        private String coinSymbol;
        private String baseSymbol;
        private double triggerPrice;
        private double entrustPrice;
        private double tradedPrice;
        private String principalUnit;
        private double principalAmount;
        private double currentPrice;
        private Object openFee;
        private double closeFee;
        private double shareNumber;
        private double volume;
        private double tradedVolume;
        private double profitAndLoss;
        private String status;
        private long createTime;
        private int triggeringTime;
        private int isFromSpot;
        private int isBlast;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getContractId() {
            return contractId;
        }

        public void setContractId(int contractId) {
            this.contractId = contractId;
        }

        public int getMemberId() {
            return memberId;
        }

        public void setMemberId(int memberId) {
            this.memberId = memberId;
        }

        public String getContractOrderEntrustId() {
            return contractOrderEntrustId;
        }

        public void setContractOrderEntrustId(String contractOrderEntrustId) {
            this.contractOrderEntrustId = contractOrderEntrustId;
        }

        public String getPatterns() {
            return patterns;
        }

        public void setPatterns(String patterns) {
            this.patterns = patterns;
        }

        public String getEntrustType() {
            return entrustType;
        }

        public void setEntrustType(String entrustType) {
            this.entrustType = entrustType;
        }

        public String getDirection() {
            return direction;
        }

        public void setDirection(String direction) {
            this.direction = direction;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getSymbol() {
            return symbol;
        }

        public void setSymbol(String symbol) {
            this.symbol = symbol;
        }

        public String getCoinSymbol() {
            return coinSymbol;
        }

        public void setCoinSymbol(String coinSymbol) {
            this.coinSymbol = coinSymbol;
        }

        public String getBaseSymbol() {
            return baseSymbol;
        }

        public void setBaseSymbol(String baseSymbol) {
            this.baseSymbol = baseSymbol;
        }

        public double getTriggerPrice() {
            return triggerPrice;
        }

        public void setTriggerPrice(double triggerPrice) {
            this.triggerPrice = triggerPrice;
        }

        public double getEntrustPrice() {
            return entrustPrice;
        }

        public void setEntrustPrice(double entrustPrice) {
            this.entrustPrice = entrustPrice;
        }

        public double getTradedPrice() {
            return tradedPrice;
        }

        public void setTradedPrice(double tradedPrice) {
            this.tradedPrice = tradedPrice;
        }

        public String getPrincipalUnit() {
            return principalUnit;
        }

        public void setPrincipalUnit(String principalUnit) {
            this.principalUnit = principalUnit;
        }

        public double getPrincipalAmount() {
            return principalAmount;
        }

        public void setPrincipalAmount(double principalAmount) {
            this.principalAmount = principalAmount;
        }

        public double getCurrentPrice() {
            return currentPrice;
        }

        public void setCurrentPrice(double currentPrice) {
            this.currentPrice = currentPrice;
        }

        public Object getOpenFee() {
            return openFee;
        }

        public void setOpenFee(Object openFee) {
            this.openFee = openFee;
        }

        public double getCloseFee() {
            return closeFee;
        }

        public void setCloseFee(double closeFee) {
            this.closeFee = closeFee;
        }

        public double getShareNumber() {
            return shareNumber;
        }

        public void setShareNumber(double shareNumber) {
            this.shareNumber = shareNumber;
        }

        public double getVolume() {
            return volume;
        }

        public void setVolume(double volume) {
            this.volume = volume;
        }

        public double getTradedVolume() {
            return tradedVolume;
        }

        public void setTradedVolume(double tradedVolume) {
            this.tradedVolume = tradedVolume;
        }

        public double getProfitAndLoss() {
            return profitAndLoss;
        }

        public void setProfitAndLoss(double profitAndLoss) {
            this.profitAndLoss = profitAndLoss;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public int getTriggeringTime() {
            return triggeringTime;
        }

        public void setTriggeringTime(int triggeringTime) {
            this.triggeringTime = triggeringTime;
        }

        public int getIsFromSpot() {
            return isFromSpot;
        }

        public void setIsFromSpot(int isFromSpot) {
            this.isFromSpot = isFromSpot;
        }

        public int getIsBlast() {
            return isBlast;
        }

        public void setIsBlast(int isBlast) {
            this.isBlast = isBlast;
        }
    }

    public static class SortBean {
        /**
         * direction : DESC
         * property : createTime
         * ignoreCase : false
         * nullHandling : NATIVE
         * ascending : false
         * descending : true
         */

        private String direction;
        private String property;
        private boolean ignoreCase;
        private String nullHandling;
        private boolean ascending;
        private boolean descending;

        public String getDirection() {
            return direction;
        }

        public void setDirection(String direction) {
            this.direction = direction;
        }

        public String getProperty() {
            return property;
        }

        public void setProperty(String property) {
            this.property = property;
        }

        public boolean isIgnoreCase() {
            return ignoreCase;
        }

        public void setIgnoreCase(boolean ignoreCase) {
            this.ignoreCase = ignoreCase;
        }

        public String getNullHandling() {
            return nullHandling;
        }

        public void setNullHandling(String nullHandling) {
            this.nullHandling = nullHandling;
        }

        public boolean isAscending() {
            return ascending;
        }

        public void setAscending(boolean ascending) {
            this.ascending = ascending;
        }

        public boolean isDescending() {
            return descending;
        }

        public void setDescending(boolean descending) {
            this.descending = descending;
        }
    }
}
