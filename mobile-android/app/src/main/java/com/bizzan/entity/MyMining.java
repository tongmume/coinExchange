package com.bizzan.entity;

import android.content.res.Resources;
import android.os.Parcel;
import android.os.Parcelable;

import com.bizzan.R;

//我的矿机
public class MyMining implements Parcelable {
    /**
     * id : 1
     * type : 0
     * image : https://bizzan6.oss-accelerate.aliyuncs.com/2021/03/20/e9329c9f-c676-46c6-907f-65a220fd8592.png
     * title : 云矿机
     * memberId : 600800
     * activityId : 14546
     * period : 0
     * miningStatus : 1
     * miningDays : 100
     * miningedDays : 0
     * miningUnit : BZB
     * miningDaysprofit : 100
     * currentDaysprofit : 100
     * totalProfit : 0
     * miningInvite : 1
     * miningInvitelimit : 10
     * endTime : 2021-06-28 17:27:51
     * createTime : 2021-03-20 17:27:52
     */

    private int id;
    private int type;
    private String image;
    private String title;
    private int memberId;
    private int activityId;
    private int period;
    private int miningStatus;
    private int miningDays;
    private int miningedDays;
    private String miningUnit;
    private int miningDaysprofit;
    private int currentDaysprofit;
    private int totalProfit;
    private int miningInvite;
    private int miningInvitelimit;
    private String endTime;
    private String createTime;

    public String getStatusStr(Resources resources) {
        if (miningStatus == 0) {
            return resources.getString(R.string.miner_status0);
        } else if (miningStatus == 1) {
            return resources.getString(R.string.miner_status1);
        }
        return resources.getString(R.string.miner_status2);
    }

    public String getPeriodStr(Resources resources) {
        if (period == 0) {
            return resources.getString(R.string.lockday);
        } else if (period == 1) {
            return resources.getString(R.string.lockweek);
        } else if (period == 2) {
            return resources.getString(R.string.lockmonth);
        }
        return resources.getString(R.string.lockyear);
    }

    protected MyMining(Parcel in) {
        id = in.readInt();
        type = in.readInt();
        image = in.readString();
        title = in.readString();
        memberId = in.readInt();
        activityId = in.readInt();
        period = in.readInt();
        miningStatus = in.readInt();
        miningDays = in.readInt();
        miningedDays = in.readInt();
        miningUnit = in.readString();
        miningDaysprofit = in.readInt();
        currentDaysprofit = in.readInt();
        totalProfit = in.readInt();
        miningInvite = in.readInt();
        miningInvitelimit = in.readInt();
        endTime = in.readString();
        createTime = in.readString();
    }

    public static final Creator<MyMining> CREATOR = new Creator<MyMining>() {
        @Override
        public MyMining createFromParcel(Parcel in) {
            return new MyMining(in);
        }

        @Override
        public MyMining[] newArray(int size) {
            return new MyMining[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public int getActivityId() {
        return activityId;
    }

    public void setActivityId(int activityId) {
        this.activityId = activityId;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    public int getMiningStatus() {
        return miningStatus;
    }

    public void setMiningStatus(int miningStatus) {
        this.miningStatus = miningStatus;
    }

    public int getMiningDays() {
        return miningDays;
    }

    public void setMiningDays(int miningDays) {
        this.miningDays = miningDays;
    }

    public int getMiningedDays() {
        return miningedDays;
    }

    public void setMiningedDays(int miningedDays) {
        this.miningedDays = miningedDays;
    }

    public String getMiningUnit() {
        return miningUnit;
    }

    public void setMiningUnit(String miningUnit) {
        this.miningUnit = miningUnit;
    }

    public int getMiningDaysprofit() {
        return miningDaysprofit;
    }

    public void setMiningDaysprofit(int miningDaysprofit) {
        this.miningDaysprofit = miningDaysprofit;
    }

    public int getCurrentDaysprofit() {
        return currentDaysprofit;
    }

    public void setCurrentDaysprofit(int currentDaysprofit) {
        this.currentDaysprofit = currentDaysprofit;
    }

    public int getTotalProfit() {
        return totalProfit;
    }

    public void setTotalProfit(int totalProfit) {
        this.totalProfit = totalProfit;
    }

    public int getMiningInvite() {
        return miningInvite;
    }

    public void setMiningInvite(int miningInvite) {
        this.miningInvite = miningInvite;
    }

    public int getMiningInvitelimit() {
        return miningInvitelimit;
    }

    public void setMiningInvitelimit(int miningInvitelimit) {
        this.miningInvitelimit = miningInvitelimit;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
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
        parcel.writeInt(id);
        parcel.writeInt(type);
        parcel.writeString(image);
        parcel.writeString(title);
        parcel.writeInt(memberId);
        parcel.writeInt(activityId);
        parcel.writeInt(period);
        parcel.writeInt(miningStatus);
        parcel.writeInt(miningDays);
        parcel.writeInt(miningedDays);
        parcel.writeString(miningUnit);
        parcel.writeInt(miningDaysprofit);
        parcel.writeInt(currentDaysprofit);
        parcel.writeInt(totalProfit);
        parcel.writeInt(miningInvite);
        parcel.writeInt(miningInvitelimit);
        parcel.writeString(endTime);
        parcel.writeString(createTime);
    }
}
