package com.bizzan.entity;

import android.content.res.Resources;
import android.os.Parcel;
import android.os.Parcelable;

import com.bizzan.R;

//我参与的活动
public class MyOrder implements Parcelable {
    /**
     * id : 4
     * activityId : 14544
     * activityName : 持仓瓜分
     * memberId : 600800
     * state : 1
     * type : 3
     * freezeAmount : 100
     * amount : 0
     * price : 2
     * turnover : 100
     * coinSymbol : BZB
     * baseSymbol : USDT
     * createTime : 2021-03-20 17:26:18
     */

    private int id;
    private int activityId;
    private String activityName;
    private int memberId;
    private int state;
    private int type;
    private int freezeAmount;
    private int amount;
    private int price;
    private int turnover;
    private String coinSymbol;
    private String baseSymbol;
    private String createTime;

    public String getTypeStr(Resources resources) {
        if (type == 1) {
            return resources.getString(R.string.type1);
        }
        if (type == 2) {
            return resources.getString(R.string.type2);
        }
        if (type == 3) {
            return resources.getString(R.string.type3);
        }
        if (type == 4) {
            return resources.getString(R.string.type4);
        }
        if (type == 5) {
            return resources.getString(R.string.type5);
        }
        return resources.getString(R.string.type6);
    }

    public String getSatusStr(Resources resources) {
        if (type == 5) {
            if (state == 1) {
                return resources.getString(R.string.status1);
            }
            if (state == 2) {
                return resources.getString(R.string.status2);
            }
            if (state == 3) {
                return resources.getString(R.string.status3);
            }
        } else {
            if (state == 1) {
                return resources.getString(R.string.status4);
            }
            if (state == 2) {
                return resources.getString(R.string.status5);
            }
            if (state == 3) {
                return resources.getString(R.string.status6);
            }
        }
        return resources.getString(R.string.status7);
    }

    protected MyOrder(Parcel in) {
        id = in.readInt();
        activityId = in.readInt();
        activityName = in.readString();
        memberId = in.readInt();
        state = in.readInt();
        type = in.readInt();
        freezeAmount = in.readInt();
        amount = in.readInt();
        price = in.readInt();
        turnover = in.readInt();
        coinSymbol = in.readString();
        baseSymbol = in.readString();
        createTime = in.readString();
    }

    public static final Creator<MyOrder> CREATOR = new Creator<MyOrder>() {
        @Override
        public MyOrder createFromParcel(Parcel in) {
            return new MyOrder(in);
        }

        @Override
        public MyOrder[] newArray(int size) {
            return new MyOrder[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getActivityId() {
        return activityId;
    }

    public void setActivityId(int activityId) {
        this.activityId = activityId;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getFreezeAmount() {
        return freezeAmount;
    }

    public void setFreezeAmount(int freezeAmount) {
        this.freezeAmount = freezeAmount;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getTurnover() {
        return turnover;
    }

    public void setTurnover(int turnover) {
        this.turnover = turnover;
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
        parcel.writeInt(activityId);
        parcel.writeString(activityName);
        parcel.writeInt(memberId);
        parcel.writeInt(state);
        parcel.writeInt(type);
        parcel.writeInt(freezeAmount);
        parcel.writeInt(amount);
        parcel.writeInt(price);
        parcel.writeInt(turnover);
        parcel.writeString(coinSymbol);
        parcel.writeString(baseSymbol);
        parcel.writeString(createTime);
    }
}
