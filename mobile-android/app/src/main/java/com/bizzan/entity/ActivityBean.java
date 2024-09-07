package com.bizzan.entity;

import android.content.res.Resources;
import android.os.Parcel;
import android.os.Parcelable;

import com.bizzan.R;
import com.bumptech.glide.load.engine.Resource;

import java.util.List;

public class ActivityBean {
    /**
     * content : [{"id":1,"status":1,"step":1,"progress":1200,"startTime":"2020-10-05 00:00:00","endTime":"2021-10-17 00:00:00","type":5,"leveloneCount":0,"totalSupply":1.0E8,"tradedAmount":11,"freezeAmount":0,"price":100,"priceScale":2,"unit":"台","acceptUnit":"BZB","amountScale":2,"maxLimitAmout":200,"minLimitAmout":1,"holdLimit":0,"holdUnit":"","limitTimes":999,"miningPeriod":0,"miningDays":180,"miningDaysprofit":31,"miningUnit":"BTC","lockedUnit":"","lockedPeriod":0,"lockedDays":1,"releaseType":0,"releasePercent":0,"lockedFee":0,"releaseAmount":0,"releaseTimes":0,"miningInvite":1,"miningInvitelimit":1,"settings":"","title":"币严合伙人云矿机(4A）","titleEN":null,"detail":"本活动由币严（BIZZAN.PRO）独家举行","detailEN":null,"content":"<p><br><\/p><p><span style=\"font-size: medium; font-weight: bolder;\">关于【合伙人云矿机（3A<\/span><span style=\"font-size: medium; font-weight: bolder;\">）】<\/span><br><\/p><div><div>合伙人云矿机（3A）是币严本次推出的带邀请增加产能的云矿机，同时限量发售，每人限购一台。具体规格如下：<br><\/div><div><span style=\"color: rgb(0, 0, 0); font-family: 宋体; font-size: 16px; letter-spacing: normal;\">●&nbsp;<\/span>认购价格：5,000 BZB<\/div><div><span style=\"color: rgb(0, 0, 0); font-family: 宋体; font-size: 16px; letter-spacing: normal;\">●&nbsp;<\/span>挖矿时长：6个月（180天）<\/div><div><span style=\"color: rgb(0, 0, 0); font-family: 宋体; font-size: 16px; letter-spacing: normal;\">●&nbsp;<\/span>基础日产出：31 BZB/天<\/div><div><span style=\"color: rgb(0, 0, 0); font-family: 宋体; font-size: 16px; letter-spacing: normal;\">●&nbsp;<\/span>年化收益率：15% ~ 200%<\/div><div><span style=\"color: rgb(0, 0, 0); font-family: 宋体; font-size: 16px; letter-spacing: normal;\">●&nbsp;<\/span>发售数量：1000台<\/div><div><span style=\"color: rgb(0, 0, 0); font-family: 宋体; font-size: 16px; letter-spacing: normal;\">●&nbsp;<\/span>邀请增加产能：每邀请一人认购相同类型云矿机，日产能增加2%，上限100%<\/div><\/div><div><br><\/div><div><span style=\"font-weight: bolder;\"><font size=\"3\">关于合伙人云矿机<\/font><\/span><\/div><div><p>币严合伙人云矿机是由币严(BIZZAN.PRO)推出的币严平台币BZB矿机，该系列矿机以扩充平台级合伙人，提升币严综合实力为核心目标，并根据合伙人的贡献度给予相应的收益。币严合伙人云矿机分为6大类别，分别是：合伙人云矿机（矿霸）、合伙人云矿机（顶级）、合伙人云矿机（豪华）、合伙人云矿机（5A）、合伙人云矿机（4A）、合伙人云矿机（3A）。<br><\/p><p><br><\/p><\/div><div><p><span style=\"font-weight: bolder; font-size: medium;\">关于币严与BZB<\/span><br><\/p><div>币严(BIZZAN.PRO)数字资产交易平台以\u201c严选全球优质数字资产\u201d为核心理念，为用户过滤诸如\u201c资金盘\u201d、\u201c传销盘\u201d等伪区块链数字资产，仅为具有独立公链、拥有极大发展潜力、被大众认可的数字资产提供交易通道。平台币BZB是由币严发布的平台通证，是币严数字资产交易平台的唯一权益通证。持有BZB可享未来平台分红、空投等权益。<\/div><\/div><div><br><\/div><div><span style=\"font-weight: bolder;\"><font size=\"3\">关于认购与部署<\/font><\/span><\/div><div>认购合伙人云矿机后，平台将从您的账户扣除对应数量的BZB，并将由币严工作人员于次日部署，部署完成后，将于第三日开始产生收益。<br><\/div><div><br><\/div><div><span style=\"font-weight: bolder;\"><font size=\"3\">关于产出BZB发放<\/font><\/span><\/div><div>合伙人云矿机每日产出BZB，产出BZB每日发放到账。<\/div><div><br><\/div><div><span style=\"font-weight: bolder;\"><font size=\"3\">关于邀请增加产能<\/font><\/span><\/div><div>本次币严合伙人云矿机仅有合伙人云矿机（5A）、合伙人云矿机（4A）、合伙人云矿机（3A）三款矿机支持邀请增加产能，即邀请好友注册并认购矿机，邀请人即可获得2%的日产能提升。<\/div><div><br><\/div><div>注意：本活动最终解释权归币严(BIZZAN.PRO)官方所有<\/div><div><br><\/div><div><p style=\"padding-top: 0px; padding-bottom: 0px; text-align: justify; color: rgb(116, 119, 122); font-family: &quot;Helvetica Neue&quot;, Helvetica, &quot;PingFang SC&quot;, &quot;Hiragino Sans GB&quot;, &quot;Microsoft YaHei&quot;, &quot;\\\\5FAE软雅黑&quot;, Arial, sans-serif; letter-spacing: 1px;\"><span style=\"font-weight: bolder;\"><font size=\"3\">风险提示<\/font><font style=\"font-size: 14px;\">：<\/font><\/span><\/p><p style=\"padding-top: 0px; padding-bottom: 0px; text-align: justify; color: rgb(116, 119, 122); font-family: &quot;Helvetica Neue&quot;, Helvetica, &quot;PingFang SC&quot;, &quot;Hiragino Sans GB&quot;, &quot;Microsoft YaHei&quot;, &quot;\\\\5FAE软雅黑&quot;, Arial, sans-serif; font-size: 14px; letter-spacing: 1px;\">数字资产是创新的投资产品，价格波动较大，请您理性判断自己的投资能力，谨慎做出投资决策。<\/p><p style=\"padding-top: 0px; padding-bottom: 0px; text-align: justify; color: rgb(116, 119, 122); font-family: &quot;Helvetica Neue&quot;, Helvetica, &quot;PingFang SC&quot;, &quot;Hiragino Sans GB&quot;, &quot;Microsoft YaHei&quot;, &quot;\\\\5FAE软雅黑&quot;, Arial, sans-serif; font-size: 14px; letter-spacing: 1px;\"><br><\/p><p style=\"padding-top: 0px; padding-bottom: 0px; text-align: justify; color: rgb(116, 119, 122); font-family: &quot;Helvetica Neue&quot;, Helvetica, &quot;PingFang SC&quot;, &quot;Hiragino Sans GB&quot;, &quot;Microsoft YaHei&quot;, &quot;\\\\5FAE软雅黑&quot;, Arial, sans-serif; letter-spacing: 1px;\"><span style=\"font-weight: bolder;\"><font size=\"3\">BIZZAN GROUP<span style=\"font-size: 14px;\">：<\/span><\/font><\/span><\/p><p style=\"padding-top: 0px; padding-bottom: 0px; text-align: justify; color: rgb(116, 119, 122); font-family: &quot;Helvetica Neue&quot;, Helvetica, &quot;PingFang SC&quot;, &quot;Hiragino Sans GB&quot;, &quot;Microsoft YaHei&quot;, &quot;\\\\5FAE软雅黑&quot;, Arial, sans-serif; font-size: 14px; letter-spacing: 1px;\">微信：添加\u201cbizzan01\u201d为微信好友，进入微信社群<\/p><p style=\"padding-top: 0px; padding-bottom: 0px; text-align: justify; color: rgb(116, 119, 122); font-family: &quot;Helvetica Neue&quot;, Helvetica, &quot;PingFang SC&quot;, &quot;Hiragino Sans GB&quot;, &quot;Microsoft YaHei&quot;, &quot;\\\\5FAE软雅黑&quot;, Arial, sans-serif; font-size: 14px; letter-spacing: 1px;\">客户服务：service@bizzan.pro<\/p><p style=\"padding-top: 0px; padding-bottom: 0px; text-align: justify; color: rgb(116, 119, 122); font-family: &quot;Helvetica Neue&quot;, Helvetica, &quot;PingFang SC&quot;, &quot;Hiragino Sans GB&quot;, &quot;Microsoft YaHei&quot;, &quot;\\\\5FAE软雅黑&quot;, Arial, sans-serif; font-size: 14px; letter-spacing: 1px;\">技术支持：support@bizzan.pro<\/p><p style=\"padding-top: 0px; padding-bottom: 0px; text-align: justify; color: rgb(116, 119, 122); font-family: &quot;Helvetica Neue&quot;, Helvetica, &quot;PingFang SC&quot;, &quot;Hiragino Sans GB&quot;, &quot;Microsoft YaHei&quot;, &quot;\\\\5FAE软雅黑&quot;, Arial, sans-serif; font-size: 14px; letter-spacing: 1px;\">投诉建议：ceo@bizzan.pro<\/p><p style=\"padding-top: 0px; padding-bottom: 0px; text-align: justify; color: rgb(116, 119, 122); font-family: &quot;Helvetica Neue&quot;, Helvetica, &quot;PingFang SC&quot;, &quot;Hiragino Sans GB&quot;, &quot;Microsoft YaHei&quot;, &quot;\\\\5FAE软雅黑&quot;, Arial, sans-serif; font-size: 14px; letter-spacing: 1px;\"><br><\/p><p style=\"padding-top: 0px; padding-bottom: 0px; text-align: justify; color: rgb(116, 119, 122); font-family: &quot;Helvetica Neue&quot;, Helvetica, &quot;PingFang SC&quot;, &quot;Hiragino Sans GB&quot;, &quot;Microsoft YaHei&quot;, &quot;\\\\5FAE软雅黑&quot;, Arial, sans-serif; font-size: 14px; letter-spacing: 1px;\">感谢您对BIZZAN.PRO的支持，BIZZAN.PRO团队期待您的宝贵意见。<\/p><\/div>","contentEN":"","smallImageUrl":"https://bizzanex.oss-cn-hangzhou.aliyuncs.com/2021/02/25/bc11e28f-a5f2-43cc-9b84-c788ac42a92b.png","bannerImageUrl":"","noticeLink":"","activityLink":"","createTime":"2020-10-05 22:40:57"}]
     * last : true
     * totalPages : 1
     * totalElements : 1
     * sort : [{"direction":"DESC","property":"createTime","ignoreCase":false,"nullHandling":"NATIVE","ascending":false,"descending":true}]
     * first : true
     * numberOfElements : 1
     * size : 10
     * number : 0
     */

    private DataBean data;
    /**
     * data : {"content":[{"id":1,"status":1,"step":1,"progress":1200,"startTime":"2020-10-05 00:00:00","endTime":"2021-10-17 00:00:00","type":5,"leveloneCount":0,"totalSupply":1.0E8,"tradedAmount":11,"freezeAmount":0,"price":100,"priceScale":2,"unit":"台","acceptUnit":"BZB","amountScale":2,"maxLimitAmout":200,"minLimitAmout":1,"holdLimit":0,"holdUnit":"","limitTimes":999,"miningPeriod":0,"miningDays":180,"miningDaysprofit":31,"miningUnit":"BTC","lockedUnit":"","lockedPeriod":0,"lockedDays":1,"releaseType":0,"releasePercent":0,"lockedFee":0,"releaseAmount":0,"releaseTimes":0,"miningInvite":1,"miningInvitelimit":1,"settings":"","title":"币严合伙人云矿机(4A）","titleEN":null,"detail":"本活动由币严（BIZZAN.PRO）独家举行","detailEN":null,"content":"<p><br><\/p><p><span style=\"font-size: medium; font-weight: bolder;\">关于【合伙人云矿机（3A<\/span><span style=\"font-size: medium; font-weight: bolder;\">）】<\/span><br><\/p><div><div>合伙人云矿机（3A）是币严本次推出的带邀请增加产能的云矿机，同时限量发售，每人限购一台。具体规格如下：<br><\/div><div><span style=\"color: rgb(0, 0, 0); font-family: 宋体; font-size: 16px; letter-spacing: normal;\">●&nbsp;<\/span>认购价格：5,000 BZB<\/div><div><span style=\"color: rgb(0, 0, 0); font-family: 宋体; font-size: 16px; letter-spacing: normal;\">●&nbsp;<\/span>挖矿时长：6个月（180天）<\/div><div><span style=\"color: rgb(0, 0, 0); font-family: 宋体; font-size: 16px; letter-spacing: normal;\">●&nbsp;<\/span>基础日产出：31 BZB/天<\/div><div><span style=\"color: rgb(0, 0, 0); font-family: 宋体; font-size: 16px; letter-spacing: normal;\">●&nbsp;<\/span>年化收益率：15% ~ 200%<\/div><div><span style=\"color: rgb(0, 0, 0); font-family: 宋体; font-size: 16px; letter-spacing: normal;\">●&nbsp;<\/span>发售数量：1000台<\/div><div><span style=\"color: rgb(0, 0, 0); font-family: 宋体; font-size: 16px; letter-spacing: normal;\">●&nbsp;<\/span>邀请增加产能：每邀请一人认购相同类型云矿机，日产能增加2%，上限100%<\/div><\/div><div><br><\/div><div><span style=\"font-weight: bolder;\"><font size=\"3\">关于合伙人云矿机<\/font><\/span><\/div><div><p>币严合伙人云矿机是由币严(BIZZAN.PRO)推出的币严平台币BZB矿机，该系列矿机以扩充平台级合伙人，提升币严综合实力为核心目标，并根据合伙人的贡献度给予相应的收益。币严合伙人云矿机分为6大类别，分别是：合伙人云矿机（矿霸）、合伙人云矿机（顶级）、合伙人云矿机（豪华）、合伙人云矿机（5A）、合伙人云矿机（4A）、合伙人云矿机（3A）。<br><\/p><p><br><\/p><\/div><div><p><span style=\"font-weight: bolder; font-size: medium;\">关于币严与BZB<\/span><br><\/p><div>币严(BIZZAN.PRO)数字资产交易平台以\u201c严选全球优质数字资产\u201d为核心理念，为用户过滤诸如\u201c资金盘\u201d、\u201c传销盘\u201d等伪区块链数字资产，仅为具有独立公链、拥有极大发展潜力、被大众认可的数字资产提供交易通道。平台币BZB是由币严发布的平台通证，是币严数字资产交易平台的唯一权益通证。持有BZB可享未来平台分红、空投等权益。<\/div><\/div><div><br><\/div><div><span style=\"font-weight: bolder;\"><font size=\"3\">关于认购与部署<\/font><\/span><\/div><div>认购合伙人云矿机后，平台将从您的账户扣除对应数量的BZB，并将由币严工作人员于次日部署，部署完成后，将于第三日开始产生收益。<br><\/div><div><br><\/div><div><span style=\"font-weight: bolder;\"><font size=\"3\">关于产出BZB发放<\/font><\/span><\/div><div>合伙人云矿机每日产出BZB，产出BZB每日发放到账。<\/div><div><br><\/div><div><span style=\"font-weight: bolder;\"><font size=\"3\">关于邀请增加产能<\/font><\/span><\/div><div>本次币严合伙人云矿机仅有合伙人云矿机（5A）、合伙人云矿机（4A）、合伙人云矿机（3A）三款矿机支持邀请增加产能，即邀请好友注册并认购矿机，邀请人即可获得2%的日产能提升。<\/div><div><br><\/div><div>注意：本活动最终解释权归币严(BIZZAN.PRO)官方所有<\/div><div><br><\/div><div><p style=\"padding-top: 0px; padding-bottom: 0px; text-align: justify; color: rgb(116, 119, 122); font-family: &quot;Helvetica Neue&quot;, Helvetica, &quot;PingFang SC&quot;, &quot;Hiragino Sans GB&quot;, &quot;Microsoft YaHei&quot;, &quot;\\\\5FAE软雅黑&quot;, Arial, sans-serif; letter-spacing: 1px;\"><span style=\"font-weight: bolder;\"><font size=\"3\">风险提示<\/font><font style=\"font-size: 14px;\">：<\/font><\/span><\/p><p style=\"padding-top: 0px; padding-bottom: 0px; text-align: justify; color: rgb(116, 119, 122); font-family: &quot;Helvetica Neue&quot;, Helvetica, &quot;PingFang SC&quot;, &quot;Hiragino Sans GB&quot;, &quot;Microsoft YaHei&quot;, &quot;\\\\5FAE软雅黑&quot;, Arial, sans-serif; font-size: 14px; letter-spacing: 1px;\">数字资产是创新的投资产品，价格波动较大，请您理性判断自己的投资能力，谨慎做出投资决策。<\/p><p style=\"padding-top: 0px; padding-bottom: 0px; text-align: justify; color: rgb(116, 119, 122); font-family: &quot;Helvetica Neue&quot;, Helvetica, &quot;PingFang SC&quot;, &quot;Hiragino Sans GB&quot;, &quot;Microsoft YaHei&quot;, &quot;\\\\5FAE软雅黑&quot;, Arial, sans-serif; font-size: 14px; letter-spacing: 1px;\"><br><\/p><p style=\"padding-top: 0px; padding-bottom: 0px; text-align: justify; color: rgb(116, 119, 122); font-family: &quot;Helvetica Neue&quot;, Helvetica, &quot;PingFang SC&quot;, &quot;Hiragino Sans GB&quot;, &quot;Microsoft YaHei&quot;, &quot;\\\\5FAE软雅黑&quot;, Arial, sans-serif; letter-spacing: 1px;\"><span style=\"font-weight: bolder;\"><font size=\"3\">BIZZAN GROUP<span style=\"font-size: 14px;\">：<\/span><\/font><\/span><\/p><p style=\"padding-top: 0px; padding-bottom: 0px; text-align: justify; color: rgb(116, 119, 122); font-family: &quot;Helvetica Neue&quot;, Helvetica, &quot;PingFang SC&quot;, &quot;Hiragino Sans GB&quot;, &quot;Microsoft YaHei&quot;, &quot;\\\\5FAE软雅黑&quot;, Arial, sans-serif; font-size: 14px; letter-spacing: 1px;\">微信：添加\u201cbizzan01\u201d为微信好友，进入微信社群<\/p><p style=\"padding-top: 0px; padding-bottom: 0px; text-align: justify; color: rgb(116, 119, 122); font-family: &quot;Helvetica Neue&quot;, Helvetica, &quot;PingFang SC&quot;, &quot;Hiragino Sans GB&quot;, &quot;Microsoft YaHei&quot;, &quot;\\\\5FAE软雅黑&quot;, Arial, sans-serif; font-size: 14px; letter-spacing: 1px;\">客户服务：service@bizzan.pro<\/p><p style=\"padding-top: 0px; padding-bottom: 0px; text-align: justify; color: rgb(116, 119, 122); font-family: &quot;Helvetica Neue&quot;, Helvetica, &quot;PingFang SC&quot;, &quot;Hiragino Sans GB&quot;, &quot;Microsoft YaHei&quot;, &quot;\\\\5FAE软雅黑&quot;, Arial, sans-serif; font-size: 14px; letter-spacing: 1px;\">技术支持：support@bizzan.pro<\/p><p style=\"padding-top: 0px; padding-bottom: 0px; text-align: justify; color: rgb(116, 119, 122); font-family: &quot;Helvetica Neue&quot;, Helvetica, &quot;PingFang SC&quot;, &quot;Hiragino Sans GB&quot;, &quot;Microsoft YaHei&quot;, &quot;\\\\5FAE软雅黑&quot;, Arial, sans-serif; font-size: 14px; letter-spacing: 1px;\">投诉建议：ceo@bizzan.pro<\/p><p style=\"padding-top: 0px; padding-bottom: 0px; text-align: justify; color: rgb(116, 119, 122); font-family: &quot;Helvetica Neue&quot;, Helvetica, &quot;PingFang SC&quot;, &quot;Hiragino Sans GB&quot;, &quot;Microsoft YaHei&quot;, &quot;\\\\5FAE软雅黑&quot;, Arial, sans-serif; font-size: 14px; letter-spacing: 1px;\"><br><\/p><p style=\"padding-top: 0px; padding-bottom: 0px; text-align: justify; color: rgb(116, 119, 122); font-family: &quot;Helvetica Neue&quot;, Helvetica, &quot;PingFang SC&quot;, &quot;Hiragino Sans GB&quot;, &quot;Microsoft YaHei&quot;, &quot;\\\\5FAE软雅黑&quot;, Arial, sans-serif; font-size: 14px; letter-spacing: 1px;\">感谢您对BIZZAN.PRO的支持，BIZZAN.PRO团队期待您的宝贵意见。<\/p><\/div>","contentEN":"","smallImageUrl":"https://bizzanex.oss-cn-hangzhou.aliyuncs.com/2021/02/25/bc11e28f-a5f2-43cc-9b84-c788ac42a92b.png","bannerImageUrl":"","noticeLink":"","activityLink":"","createTime":"2020-10-05 22:40:57"}],"last":true,"totalPages":1,"totalElements":1,"sort":[{"direction":"DESC","property":"createTime","ignoreCase":false,"nullHandling":"NATIVE","ascending":false,"descending":true}],"first":true,"numberOfElements":1,"size":10,"number":0}
     * code : 0
     * message : null
     * totalPage : null
     * totalElement : null
     */

    private String code;
    private String message;
    private String totalPage;
    private String totalElement;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(String totalPage) {
        this.totalPage = totalPage;
    }

    public String getTotalElement() {
        return totalElement;
    }

    public void setTotalElement(String totalElement) {
        this.totalElement = totalElement;
    }

    public static class DataBean {
        private boolean last;
        private String totalPages;
        private String totalElements;
        private boolean first;
        private String numberOfElements;
        private String size;
        private String number;
        /**
         * id : 1
         * status : 1
         * step : 1
         * progress : 1200
         * startTime : 2020-10-05 00:00:00
         * endTime : 2021-10-17 00:00:00
         * type : 5
         * leveloneCount : 0
         * totalSupply : 1.0E8
         * tradedAmount : 11
         * freezeAmount : 0
         * price : 100
         * priceScale : 2
         * unit : 台
         * acceptUnit : BZB
         * amountScale : 2
         * maxLimitAmout : 200
         * minLimitAmout : 1
         * holdLimit : 0
         * holdUnit :
         * limitTimes : 999
         * miningPeriod : 0
         * miningDays : 180
         * miningDaysprofit : 31
         * miningUnit : BTC
         * lockedUnit :
         * lockedPeriod : 0
         * lockedDays : 1
         * releaseType : 0
         * releasePercent : 0
         * lockedFee : 0
         * releaseAmount : 0
         * releaseTimes : 0
         * miningInvite : 1
         * miningInvitelimit : 1
         * settings :
         * title : 币严合伙人云矿机(4A）
         * titleEN : null
         * detail : 本活动由币严（BIZZAN.PRO）独家举行
         * detailEN : null
         * content : <p><br></p><p><span style="font-size: medium; font-weight: bolder;">关于【合伙人云矿机（3A</span><span style="font-size: medium; font-weight: bolder;">）】</span><br></p><div><div>合伙人云矿机（3A）是币严本次推出的带邀请增加产能的云矿机，同时限量发售，每人限购一台。具体规格如下：<br></div><div><span style="color: rgb(0, 0, 0); font-family: 宋体; font-size: 16px; letter-spacing: normal;">●&nbsp;</span>认购价格：5,000 BZB</div><div><span style="color: rgb(0, 0, 0); font-family: 宋体; font-size: 16px; letter-spacing: normal;">●&nbsp;</span>挖矿时长：6个月（180天）</div><div><span style="color: rgb(0, 0, 0); font-family: 宋体; font-size: 16px; letter-spacing: normal;">●&nbsp;</span>基础日产出：31 BZB/天</div><div><span style="color: rgb(0, 0, 0); font-family: 宋体; font-size: 16px; letter-spacing: normal;">●&nbsp;</span>年化收益率：15% ~ 200%</div><div><span style="color: rgb(0, 0, 0); font-family: 宋体; font-size: 16px; letter-spacing: normal;">●&nbsp;</span>发售数量：1000台</div><div><span style="color: rgb(0, 0, 0); font-family: 宋体; font-size: 16px; letter-spacing: normal;">●&nbsp;</span>邀请增加产能：每邀请一人认购相同类型云矿机，日产能增加2%，上限100%</div></div><div><br></div><div><span style="font-weight: bolder;"><font size="3">关于合伙人云矿机</font></span></div><div><p>币严合伙人云矿机是由币严(BIZZAN.PRO)推出的币严平台币BZB矿机，该系列矿机以扩充平台级合伙人，提升币严综合实力为核心目标，并根据合伙人的贡献度给予相应的收益。币严合伙人云矿机分为6大类别，分别是：合伙人云矿机（矿霸）、合伙人云矿机（顶级）、合伙人云矿机（豪华）、合伙人云矿机（5A）、合伙人云矿机（4A）、合伙人云矿机（3A）。<br></p><p><br></p></div><div><p><span style="font-weight: bolder; font-size: medium;">关于币严与BZB</span><br></p><div>币严(BIZZAN.PRO)数字资产交易平台以“严选全球优质数字资产”为核心理念，为用户过滤诸如“资金盘”、“传销盘”等伪区块链数字资产，仅为具有独立公链、拥有极大发展潜力、被大众认可的数字资产提供交易通道。平台币BZB是由币严发布的平台通证，是币严数字资产交易平台的唯一权益通证。持有BZB可享未来平台分红、空投等权益。</div></div><div><br></div><div><span style="font-weight: bolder;"><font size="3">关于认购与部署</font></span></div><div>认购合伙人云矿机后，平台将从您的账户扣除对应数量的BZB，并将由币严工作人员于次日部署，部署完成后，将于第三日开始产生收益。<br></div><div><br></div><div><span style="font-weight: bolder;"><font size="3">关于产出BZB发放</font></span></div><div>合伙人云矿机每日产出BZB，产出BZB每日发放到账。</div><div><br></div><div><span style="font-weight: bolder;"><font size="3">关于邀请增加产能</font></span></div><div>本次币严合伙人云矿机仅有合伙人云矿机（5A）、合伙人云矿机（4A）、合伙人云矿机（3A）三款矿机支持邀请增加产能，即邀请好友注册并认购矿机，邀请人即可获得2%的日产能提升。</div><div><br></div><div>注意：本活动最终解释权归币严(BIZZAN.PRO)官方所有</div><div><br></div><div><p style="padding-top: 0px; padding-bottom: 0px; text-align: justify; color: rgb(116, 119, 122); font-family: &quot;Helvetica Neue&quot;, Helvetica, &quot;PingFang SC&quot;, &quot;Hiragino Sans GB&quot;, &quot;Microsoft YaHei&quot;, &quot;\\5FAE软雅黑&quot;, Arial, sans-serif; letter-spacing: 1px;"><span style="font-weight: bolder;"><font size="3">风险提示</font><font style="font-size: 14px;">：</font></span></p><p style="padding-top: 0px; padding-bottom: 0px; text-align: justify; color: rgb(116, 119, 122); font-family: &quot;Helvetica Neue&quot;, Helvetica, &quot;PingFang SC&quot;, &quot;Hiragino Sans GB&quot;, &quot;Microsoft YaHei&quot;, &quot;\\5FAE软雅黑&quot;, Arial, sans-serif; font-size: 14px; letter-spacing: 1px;">数字资产是创新的投资产品，价格波动较大，请您理性判断自己的投资能力，谨慎做出投资决策。</p><p style="padding-top: 0px; padding-bottom: 0px; text-align: justify; color: rgb(116, 119, 122); font-family: &quot;Helvetica Neue&quot;, Helvetica, &quot;PingFang SC&quot;, &quot;Hiragino Sans GB&quot;, &quot;Microsoft YaHei&quot;, &quot;\\5FAE软雅黑&quot;, Arial, sans-serif; font-size: 14px; letter-spacing: 1px;"><br></p><p style="padding-top: 0px; padding-bottom: 0px; text-align: justify; color: rgb(116, 119, 122); font-family: &quot;Helvetica Neue&quot;, Helvetica, &quot;PingFang SC&quot;, &quot;Hiragino Sans GB&quot;, &quot;Microsoft YaHei&quot;, &quot;\\5FAE软雅黑&quot;, Arial, sans-serif; letter-spacing: 1px;"><span style="font-weight: bolder;"><font size="3">BIZZAN GROUP<span style="font-size: 14px;">：</span></font></span></p><p style="padding-top: 0px; padding-bottom: 0px; text-align: justify; color: rgb(116, 119, 122); font-family: &quot;Helvetica Neue&quot;, Helvetica, &quot;PingFang SC&quot;, &quot;Hiragino Sans GB&quot;, &quot;Microsoft YaHei&quot;, &quot;\\5FAE软雅黑&quot;, Arial, sans-serif; font-size: 14px; letter-spacing: 1px;">微信：添加“bizzan01”为微信好友，进入微信社群</p><p style="padding-top: 0px; padding-bottom: 0px; text-align: justify; color: rgb(116, 119, 122); font-family: &quot;Helvetica Neue&quot;, Helvetica, &quot;PingFang SC&quot;, &quot;Hiragino Sans GB&quot;, &quot;Microsoft YaHei&quot;, &quot;\\5FAE软雅黑&quot;, Arial, sans-serif; font-size: 14px; letter-spacing: 1px;">客户服务：service@bizzan.pro</p><p style="padding-top: 0px; padding-bottom: 0px; text-align: justify; color: rgb(116, 119, 122); font-family: &quot;Helvetica Neue&quot;, Helvetica, &quot;PingFang SC&quot;, &quot;Hiragino Sans GB&quot;, &quot;Microsoft YaHei&quot;, &quot;\\5FAE软雅黑&quot;, Arial, sans-serif; font-size: 14px; letter-spacing: 1px;">技术支持：support@bizzan.pro</p><p style="padding-top: 0px; padding-bottom: 0px; text-align: justify; color: rgb(116, 119, 122); font-family: &quot;Helvetica Neue&quot;, Helvetica, &quot;PingFang SC&quot;, &quot;Hiragino Sans GB&quot;, &quot;Microsoft YaHei&quot;, &quot;\\5FAE软雅黑&quot;, Arial, sans-serif; font-size: 14px; letter-spacing: 1px;">投诉建议：ceo@bizzan.pro</p><p style="padding-top: 0px; padding-bottom: 0px; text-align: justify; color: rgb(116, 119, 122); font-family: &quot;Helvetica Neue&quot;, Helvetica, &quot;PingFang SC&quot;, &quot;Hiragino Sans GB&quot;, &quot;Microsoft YaHei&quot;, &quot;\\5FAE软雅黑&quot;, Arial, sans-serif; font-size: 14px; letter-spacing: 1px;"><br></p><p style="padding-top: 0px; padding-bottom: 0px; text-align: justify; color: rgb(116, 119, 122); font-family: &quot;Helvetica Neue&quot;, Helvetica, &quot;PingFang SC&quot;, &quot;Hiragino Sans GB&quot;, &quot;Microsoft YaHei&quot;, &quot;\\5FAE软雅黑&quot;, Arial, sans-serif; font-size: 14px; letter-spacing: 1px;">感谢您对BIZZAN.PRO的支持，BIZZAN.PRO团队期待您的宝贵意见。</p></div>
         * contentEN :
         * smallImageUrl : https://bizzanex.oss-cn-hangzhou.aliyuncs.com/2021/02/25/bc11e28f-a5f2-43cc-9b84-c788ac42a92b.png
         * bannerImageUrl :
         * noticeLink :
         * activityLink :
         * createTime : 2020-10-05 22:40:57
         */

        private List<ContentBean> content;


        public boolean isLast() {
            return last;
        }

        public void setLast(boolean last) {
            this.last = last;
        }

        public String getTotalPages() {
            return totalPages;
        }

        public void setTotalPages(String totalPages) {
            this.totalPages = totalPages;
        }

        public String getTotalElements() {
            return totalElements;
        }

        public void setTotalElements(String totalElements) {
            this.totalElements = totalElements;
        }

        public boolean isFirst() {
            return first;
        }

        public void setFirst(boolean first) {
            this.first = first;
        }

        public String getNumberOfElements() {
            return numberOfElements;
        }

        public void setNumberOfElements(String numberOfElements) {
            this.numberOfElements = numberOfElements;
        }

        public String getSize() {
            return size;
        }

        public void setSize(String size) {
            this.size = size;
        }

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }

        public List<ContentBean> getContent() {
            return content;
        }

        public void setContent(List<ContentBean> content) {
            this.content = content;
        }


        public static class ContentBean implements Parcelable {
            private String id;
            private String status;
            private String step;
            private String progress;
            private String startTime;
            private String endTime;
            private String type;
            private String leveloneCount;
            private String totalSupply;
            private String tradedAmount;
            private String freezeAmount;
            private String price;
            private String priceScale;
            private String unit;
            private String amount;
            private String acceptUnit;
            private String amountScale;
            private String maxLimitAmout;
            private String minLimitAmout;
            private String holdLimit;
            private String holdUnit;
            private String limitTimes;
            private String miningPeriod;
            private String miningDays;
            private String miningDaysprofit;
            private String miningUnit;
            private String lockedUnit;
            private String lockedPeriod;
            private String lockedDays;
            private String releaseType;
            private String releasePercent;
            private String lockedFee;
            private String releaseAmount;
            private String releaseTimes;
            private String miningInvite;
            private String miningInvitelimit;
            private String settings;
            private String title;
            private String titleEN;
            private String detail;
            private String detailEN;
            private String content;
            private String contentEN;
            private String smallImageUrl;
            private String bannerImageUrl;
            private String noticeLink;
            private String activityLink;
            private String createTime;

            public String getStepStr(Resources resource) {
                if ("0".equals(step)) {
                    return resource.getString(R.string.step_prepare);
                } else if ("1".equals(step)) {
                    return resource.getString(R.string.step_ongoing);
                } else if ("2".equals(step)) {
                    return resource.getString(R.string.step_distributing);
                } else {
                    return resource.getString(R.string.step_completed);
                }
            }


            protected ContentBean(Parcel in) {
                id = in.readString();
                status = in.readString();
                step = in.readString();
                progress = in.readString();
                startTime = in.readString();
                endTime = in.readString();
                type = in.readString();
                leveloneCount = in.readString();
                totalSupply = in.readString();
                tradedAmount = in.readString();
                freezeAmount = in.readString();
                price = in.readString();
                priceScale = in.readString();
                unit = in.readString();
                acceptUnit = in.readString();
                amountScale = in.readString();
                maxLimitAmout = in.readString();
                minLimitAmout = in.readString();
                holdLimit = in.readString();
                holdUnit = in.readString();
                limitTimes = in.readString();
                miningPeriod = in.readString();
                miningDays = in.readString();
                miningDaysprofit = in.readString();
                miningUnit = in.readString();
                lockedUnit = in.readString();
                lockedPeriod = in.readString();
                lockedDays = in.readString();
                releaseType = in.readString();
                releasePercent = in.readString();
                lockedFee = in.readString();
                releaseAmount = in.readString();
                releaseTimes = in.readString();
                miningInvite = in.readString();
                miningInvitelimit = in.readString();
                settings = in.readString();
                title = in.readString();
                titleEN = in.readString();
                detail = in.readString();
                detailEN = in.readString();
                content = in.readString();
                contentEN = in.readString();
                smallImageUrl = in.readString();
                bannerImageUrl = in.readString();
                noticeLink = in.readString();
                activityLink = in.readString();
                createTime = in.readString();
                amount = in.readString();
            }

            public static final Creator<ContentBean> CREATOR = new Creator<ContentBean>() {
                @Override
                public ContentBean createFromParcel(Parcel in) {
                    return new ContentBean(in);
                }

                @Override
                public ContentBean[] newArray(int size) {
                    return new ContentBean[size];
                }
            };

            public String getAmount() {
                return amount;
            }

            public void setAmount(String amount) {
                this.amount = amount;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getStep() {
                return step;
            }

            public void setStep(String step) {
                this.step = step;
            }

            public String getProgress() {
                // 持仓瓜分类型进度处理（未开始：0，进行中：50，派发中：75%，已结束：100
                if ("3".equals(type)) {
                    if ("1".equals(step)) {
                        progress = "50";
                    } else if ("2".equals(step)) {
                        progress = "75";
                    } else if ("3".equals(step)) {
                        progress = "100";
                    } else {
                        progress = "0";
                    }
                }
                return progress;
            }

            public void setProgress(String progress) {
                this.progress = progress;
            }

            public String getStartTime() {
                return startTime;
            }

            public void setStartTime(String startTime) {
                this.startTime = startTime;
            }

            public String getEndTime() {
                return endTime;
            }

            public void setEndTime(String endTime) {
                this.endTime = endTime;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getLeveloneCount() {
                return leveloneCount;
            }

            public void setLeveloneCount(String leveloneCount) {
                this.leveloneCount = leveloneCount;
            }

            public String getTotalSupply() {
                return totalSupply;
            }

            public void setTotalSupply(String totalSupply) {
                this.totalSupply = totalSupply;
            }

            public String getTradedAmount() {
                return tradedAmount;
            }

            public void setTradedAmount(String tradedAmount) {
                this.tradedAmount = tradedAmount;
            }

            public String getFreezeAmount() {
                return freezeAmount;
            }

            public void setFreezeAmount(String freezeAmount) {
                this.freezeAmount = freezeAmount;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public String getPriceScale() {
                return priceScale;
            }

            public void setPriceScale(String priceScale) {
                this.priceScale = priceScale;
            }

            public String getUnit() {
                return unit;
            }

            public void setUnit(String unit) {
                this.unit = unit;
            }

            public String getAcceptUnit() {
                return acceptUnit;
            }

            public void setAcceptUnit(String acceptUnit) {
                this.acceptUnit = acceptUnit;
            }

            public String getAmountScale() {
                return amountScale;
            }

            public void setAmountScale(String amountScale) {
                this.amountScale = amountScale;
            }

            public String getMaxLimitAmout() {
                return maxLimitAmout;
            }

            public void setMaxLimitAmout(String maxLimitAmout) {
                this.maxLimitAmout = maxLimitAmout;
            }

            public String getMinLimitAmout() {
                return minLimitAmout;
            }

            public void setMinLimitAmout(String minLimitAmout) {
                this.minLimitAmout = minLimitAmout;
            }

            public String getHoldLimit() {
                return holdLimit;
            }

            public void setHoldLimit(String holdLimit) {
                this.holdLimit = holdLimit;
            }

            public String getHoldUnit() {
                return holdUnit;
            }

            public void setHoldUnit(String holdUnit) {
                this.holdUnit = holdUnit;
            }

            public String getLimitTimes() {
                return limitTimes;
            }

            public void setLimitTimes(String limitTimes) {
                this.limitTimes = limitTimes;
            }

            public String getMiningPeriod() {
                return miningPeriod;
            }

            public void setMiningPeriod(String miningPeriod) {
                this.miningPeriod = miningPeriod;
            }

            public String getMiningDays() {
                return miningDays;
            }

            public void setMiningDays(String miningDays) {
                this.miningDays = miningDays;
            }

            public String getMiningDaysprofit() {
                return miningDaysprofit;
            }

            public void setMiningDaysprofit(String miningDaysprofit) {
                this.miningDaysprofit = miningDaysprofit;
            }

            public String getMiningUnit() {
                return miningUnit;
            }

            public void setMiningUnit(String miningUnit) {
                this.miningUnit = miningUnit;
            }

            public String getLockedUnit() {
                return lockedUnit;
            }

            public void setLockedUnit(String lockedUnit) {
                this.lockedUnit = lockedUnit;
            }

            public String getLockedPeriod() {
                return lockedPeriod;
            }

            public void setLockedPeriod(String lockedPeriod) {
                this.lockedPeriod = lockedPeriod;
            }

            public String getLockedDays() {
                return lockedDays;
            }

            public void setLockedDays(String lockedDays) {
                this.lockedDays = lockedDays;
            }

            public String getReleaseType() {
                return releaseType;
            }

            public void setReleaseType(String releaseType) {
                this.releaseType = releaseType;
            }

            public String getReleasePercent() {
                return releasePercent;
            }

            public void setReleasePercent(String releasePercent) {
                this.releasePercent = releasePercent;
            }

            public String getLockedFee() {
                return lockedFee;
            }

            public void setLockedFee(String lockedFee) {
                this.lockedFee = lockedFee;
            }

            public String getReleaseAmount() {
                return releaseAmount;
            }

            public void setReleaseAmount(String releaseAmount) {
                this.releaseAmount = releaseAmount;
            }

            public String getReleaseTimes() {
                return releaseTimes;
            }

            public void setReleaseTimes(String releaseTimes) {
                this.releaseTimes = releaseTimes;
            }

            public String getMiningInvite() {
                return miningInvite;
            }

            public void setMiningInvite(String miningInvite) {
                this.miningInvite = miningInvite;
            }

            public String getMiningInvitelimit() {
                return miningInvitelimit;
            }

            public void setMiningInvitelimit(String miningInvitelimit) {
                this.miningInvitelimit = miningInvitelimit;
            }

            public String getSettings() {
                return settings;
            }

            public void setSettings(String settings) {
                this.settings = settings;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getTitleEN() {
                return titleEN;
            }

            public void setTitleEN(String titleEN) {
                this.titleEN = titleEN;
            }

            public String getDetail() {
                return detail;
            }

            public void setDetail(String detail) {
                this.detail = detail;
            }

            public String getDetailEN() {
                return detailEN;
            }

            public void setDetailEN(String detailEN) {
                this.detailEN = detailEN;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getContentEN() {
                return contentEN;
            }

            public void setContentEN(String contentEN) {
                this.contentEN = contentEN;
            }

            public String getSmallImageUrl() {
                return smallImageUrl;
            }

            public void setSmallImageUrl(String smallImageUrl) {
                this.smallImageUrl = smallImageUrl;
            }

            public String getBannerImageUrl() {
                return bannerImageUrl;
            }

            public void setBannerImageUrl(String bannerImageUrl) {
                this.bannerImageUrl = bannerImageUrl;
            }

            public String getNoticeLink() {
                return noticeLink;
            }

            public void setNoticeLink(String noticeLink) {
                this.noticeLink = noticeLink;
            }

            public String getActivityLink() {
                return activityLink;
            }

            public void setActivityLink(String activityLink) {
                this.activityLink = activityLink;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel parcel, int i) {
                parcel.writeString(id);
                parcel.writeString(status);
                parcel.writeString(step);
                parcel.writeString(progress);
                parcel.writeString(startTime);
                parcel.writeString(endTime);
                parcel.writeString(type);
                parcel.writeString(leveloneCount);
                parcel.writeString(totalSupply);
                parcel.writeString(tradedAmount);
                parcel.writeString(freezeAmount);
                parcel.writeString(price);
                parcel.writeString(priceScale);
                parcel.writeString(unit);
                parcel.writeString(acceptUnit);
                parcel.writeString(amountScale);
                parcel.writeString(maxLimitAmout);
                parcel.writeString(minLimitAmout);
                parcel.writeString(holdLimit);
                parcel.writeString(holdUnit);
                parcel.writeString(limitTimes);
                parcel.writeString(miningPeriod);
                parcel.writeString(miningDays);
                parcel.writeString(miningDaysprofit);
                parcel.writeString(miningUnit);
                parcel.writeString(lockedUnit);
                parcel.writeString(lockedPeriod);
                parcel.writeString(lockedDays);
                parcel.writeString(releaseType);
                parcel.writeString(releasePercent);
                parcel.writeString(lockedFee);
                parcel.writeString(releaseAmount);
                parcel.writeString(releaseTimes);
                parcel.writeString(miningInvite);
                parcel.writeString(miningInvitelimit);
                parcel.writeString(settings);
                parcel.writeString(title);
                parcel.writeString(titleEN);
                parcel.writeString(detail);
                parcel.writeString(detailEN);
                parcel.writeString(content);
                parcel.writeString(contentEN);
                parcel.writeString(smallImageUrl);
                parcel.writeString(bannerImageUrl);
                parcel.writeString(noticeLink);
                parcel.writeString(activityLink);
                parcel.writeString(createTime);
                parcel.writeString(amount);
            }
        }

    }
}
