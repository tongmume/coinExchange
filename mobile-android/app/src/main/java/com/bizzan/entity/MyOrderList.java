package com.bizzan.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class MyOrderList implements Parcelable, DataList {

    private boolean last;
    private String totalPages;
    private String totalElements;
    private boolean first;
    private String numberOfElements;
    private String size;
    private String number;

    private List<MyOrder> content;

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

    public List<MyOrder> getContent() {
        return content;
    }

    public void setContent(List<MyOrder> content) {
        this.content = content;
    }

    protected MyOrderList(Parcel in) {
        last = in.readByte() != 0;
        totalPages = in.readString();
        totalElements = in.readString();
        first = in.readByte() != 0;
        numberOfElements = in.readString();
        size = in.readString();
        number = in.readString();
        content = in.createTypedArrayList(MyOrder.CREATOR);
    }

    public static final Creator<MyOrderList> CREATOR = new Creator<MyOrderList>() {
        @Override
        public MyOrderList createFromParcel(Parcel in) {
            return new MyOrderList(in);
        }

        @Override
        public MyOrderList[] newArray(int size) {
            return new MyOrderList[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeByte((byte) (last ? 1 : 0));
        parcel.writeString(totalPages);
        parcel.writeString(totalElements);
        parcel.writeByte((byte) (first ? 1 : 0));
        parcel.writeString(numberOfElements);
        parcel.writeString(size);
        parcel.writeString(number);
        parcel.writeTypedList(content);
    }
}
