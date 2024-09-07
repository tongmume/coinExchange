package com.bizzan.entity;

import android.content.res.Resources;
import android.os.Parcel;
import android.os.Parcelable;

import com.bizzan.R;

//我的锁仓
public class MyLocked implements Parcelable {
    /**
     * id : 1
     * image : https://bizzan6.oss-accelerate.aliyuncs.com/2021/03/20/93d68626-60a5-4a84-9b54-13f735496b32.png
     * title : 锁仓挖矿
     * memberId : 600800
     * activityId : 14547
     * period : 0
     * lockedStatus : 1
     * lockedDays : 100
     * releasedDays : 0
     * releaseUnit : BZB
     * totalLocked : 1500
     * releaseType : 0
     * releasePercent : 0
     * releaseCurrentpercent : 0
     * releaseTimes : 1.5
     * originReleaseamount : 15
     * currentReleaseamount : 15
     * totalRelease : 0
     * lockedInvite : 0
     * lockedInvitelimit : 0
     * endTime : 2021-06-28 17:28:25
     * createTime : 2021-03-20 17:28:26
     */

    private int id;
    private String image;
    private String title;
    private int memberId;
    private int activityId;
    private int period;
    private int lockedStatus;
    private int lockedDays;
    private int releasedDays;
    private String releaseUnit;
    private int totalLocked;
    private int releaseType;
    private int releasePercent;
    private int releaseCurrentpercent;
    private double releaseTimes;
    private int originReleaseamount;
    private int currentReleaseamount;
    private int totalRelease;
    private int lockedInvite;
    private int lockedInvitelimit;
    private String endTime;
    private String createTime;

    public String getStatusStr(Resources resources) {
        if (lockedStatus == 0) {
            return resources.getString(R.string.lockstatus0);
        } else if (lockedStatus == 1) {
            return resources.getString(R.string.lockstatus1);
        }
        return resources.getString(R.string.lockstatus2);
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


    protected MyLocked(Parcel in) {
        id = in.readInt();
        image = in.readString();
        title = in.readString();
        memberId = in.readInt();
        activityId = in.readInt();
        period = in.readInt();
        lockedStatus = in.readInt();
        lockedDays = in.readInt();
        releasedDays = in.readInt();
        releaseUnit = in.readString();
        totalLocked = in.readInt();
        releaseType = in.readInt();
        releasePercent = in.readInt();
        releaseCurrentpercent = in.readInt();
        releaseTimes = in.readDouble();
        originReleaseamount = in.readInt();
        currentReleaseamount = in.readInt();
        totalRelease = in.readInt();
        lockedInvite = in.readInt();
        lockedInvitelimit = in.readInt();
        endTime = in.readString();
        createTime = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(image);
        dest.writeString(title);
        dest.writeInt(memberId);
        dest.writeInt(activityId);
        dest.writeInt(period);
        dest.writeInt(lockedStatus);
        dest.writeInt(lockedDays);
        dest.writeInt(releasedDays);
        dest.writeString(releaseUnit);
        dest.writeInt(totalLocked);
        dest.writeInt(releaseType);
        dest.writeInt(releasePercent);
        dest.writeInt(releaseCurrentpercent);
        dest.writeDouble(releaseTimes);
        dest.writeInt(originReleaseamount);
        dest.writeInt(currentReleaseamount);
        dest.writeInt(totalRelease);
        dest.writeInt(lockedInvite);
        dest.writeInt(lockedInvitelimit);
        dest.writeString(endTime);
        dest.writeString(createTime);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<MyLocked> CREATOR = new Creator<MyLocked>() {
        @Override
        public MyLocked createFromParcel(Parcel in) {
            return new MyLocked(in);
        }

        @Override
        public MyLocked[] newArray(int size) {
            return new MyLocked[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getLockedStatus() {
        return lockedStatus;
    }

    public void setLockedStatus(int lockedStatus) {
        this.lockedStatus = lockedStatus;
    }

    public int getLockedDays() {
        return lockedDays;
    }

    public void setLockedDays(int lockedDays) {
        this.lockedDays = lockedDays;
    }

    public int getReleasedDays() {
        return releasedDays;
    }

    public void setReleasedDays(int releasedDays) {
        this.releasedDays = releasedDays;
    }

    public String getReleaseUnit() {
        return releaseUnit;
    }

    public void setReleaseUnit(String releaseUnit) {
        this.releaseUnit = releaseUnit;
    }

    public int getTotalLocked() {
        return totalLocked;
    }

    public void setTotalLocked(int totalLocked) {
        this.totalLocked = totalLocked;
    }

    public int getReleaseType() {
        return releaseType;
    }

    public void setReleaseType(int releaseType) {
        this.releaseType = releaseType;
    }

    public int getReleasePercent() {
        return releasePercent;
    }

    public void setReleasePercent(int releasePercent) {
        this.releasePercent = releasePercent;
    }

    public int getReleaseCurrentpercent() {
        return releaseCurrentpercent;
    }

    public void setReleaseCurrentpercent(int releaseCurrentpercent) {
        this.releaseCurrentpercent = releaseCurrentpercent;
    }

    public double getReleaseTimes() {
        return releaseTimes;
    }

    public void setReleaseTimes(double releaseTimes) {
        this.releaseTimes = releaseTimes;
    }

    public int getOriginReleaseamount() {
        return originReleaseamount;
    }

    public void setOriginReleaseamount(int originReleaseamount) {
        this.originReleaseamount = originReleaseamount;
    }

    public int getCurrentReleaseamount() {
        return currentReleaseamount;
    }

    public void setCurrentReleaseamount(int currentReleaseamount) {
        this.currentReleaseamount = currentReleaseamount;
    }

    public int getTotalRelease() {
        return totalRelease;
    }

    public void setTotalRelease(int totalRelease) {
        this.totalRelease = totalRelease;
    }

    public int getLockedInvite() {
        return lockedInvite;
    }

    public void setLockedInvite(int lockedInvite) {
        this.lockedInvite = lockedInvite;
    }

    public int getLockedInvitelimit() {
        return lockedInvitelimit;
    }

    public void setLockedInvitelimit(int lockedInvitelimit) {
        this.lockedInvitelimit = lockedInvitelimit;
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
}
