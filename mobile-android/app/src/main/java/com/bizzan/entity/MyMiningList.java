package com.bizzan.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class MyMiningList implements Parcelable, DataList {

    private boolean last;
    private String totalPages;
    private String totalElements;
    private boolean first;
    private String numberOfElements;
    private String size;
    private String number;
    private List<MyMining> content;

    protected MyMiningList(Parcel in) {
        last = in.readByte() != 0;
        totalPages = in.readString();
        totalElements = in.readString();
        first = in.readByte() != 0;
        numberOfElements = in.readString();
        size = in.readString();
        number = in.readString();
        content = in.createTypedArrayList(MyMining.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte((byte) (last ? 1 : 0));
        dest.writeString(totalPages);
        dest.writeString(totalElements);
        dest.writeByte((byte) (first ? 1 : 0));
        dest.writeString(numberOfElements);
        dest.writeString(size);
        dest.writeString(number);
        dest.writeTypedList(content);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<MyMiningList> CREATOR = new Creator<MyMiningList>() {
        @Override
        public MyMiningList createFromParcel(Parcel in) {
            return new MyMiningList(in);
        }

        @Override
        public MyMiningList[] newArray(int size) {
            return new MyMiningList[size];
        }
    };

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

    @Override
    public boolean isFirstPage() {
        return isFirst();
    }

    @Override
    public boolean isLastPage() {
        return isLast();
    }

    public List<MyMining> getContent() {
        return content;
    }

    public void setContent(List<MyMining> content) {
        this.content = content;
    }
}
