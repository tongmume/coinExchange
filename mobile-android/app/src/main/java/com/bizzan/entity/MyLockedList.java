package com.bizzan.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class MyLockedList implements Parcelable,DataList {

    private boolean last;
    private String totalPages;
    private String totalElements;
    private boolean first;
    private String numberOfElements;
    private String size;
    private String number;

    private List<MyLocked> content;

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

    public List<MyLocked> getContent() {
        return content;
    }

    public void setContent(List<MyLocked> content) {
        this.content = content;
    }

    protected MyLockedList(Parcel in) {
        last = in.readByte() != 0;
        totalPages = in.readString();
        totalElements = in.readString();
        first = in.readByte() != 0;
        numberOfElements = in.readString();
        size = in.readString();
        number = in.readString();
        content = in.createTypedArrayList(MyLocked.CREATOR);
    }

    public static final Creator<MyLockedList> CREATOR = new Creator<MyLockedList>() {
        @Override
        public MyLockedList createFromParcel(Parcel in) {
            return new MyLockedList(in);
        }

        @Override
        public MyLockedList[] newArray(int size) {
            return new MyLockedList[size];
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
