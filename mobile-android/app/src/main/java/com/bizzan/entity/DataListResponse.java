package com.bizzan.entity;


import android.os.Parcel;
import android.os.Parcelable;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.List;

/**
 * 请求服务器返回实体
 */
public class DataListResponse<T extends DataList> {
    private String code;
    private String message;
    private String totalPage;
    private String totalElement;
    private T data;


    public boolean isFirstPage() {
        return data != null && data.isFirstPage();
    }

    public boolean isLastPage() {
        return data != null && data.isLastPage();
    }


    public void setAdapter(BaseQuickAdapter adapter) {
        setAdapter(adapter, true);
    }

    public void setAdapter(BaseQuickAdapter adapter, boolean enableLoadMore) {
        adapter.isUseEmpty(true);
        //预加载
        if (enableLoadMore) {
            adapter.setPreLoadNumber(4);
        }
        if (!isFirstPage()) {
            if (data != null && data.getContent() != null) {
                adapter.addData((List) data.getContent());
            }
        } else {
            adapter.setNewData((List) data.getContent());
        }
        if (isLastPage()) {
            adapter.loadMoreEnd(true);
        } else {
            adapter.loadMoreEnd(false);
            adapter.loadMoreComplete();
            adapter.setEnableLoadMore(enableLoadMore);
        }
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

    public static class DataBean<F> implements Parcelable {
        private boolean last;
        private String totalPages;
        private String totalElements;
        private boolean first;
        private String numberOfElements;
        private String size;
        private String number;

        private List<F> content;

        protected DataBean(Parcel in) {
            last = in.readByte() != 0;
            totalPages = in.readString();
            totalElements = in.readString();
            first = in.readByte() != 0;
            numberOfElements = in.readString();
            size = in.readString();
            number = in.readString();
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
        }

        @Override
        public int describeContents() {
            return 0;
        }

        public static final Creator<DataBean> CREATOR = new Creator<DataBean>() {
            @Override
            public DataBean createFromParcel(Parcel in) {
                return new DataBean(in);
            }

            @Override
            public DataBean[] newArray(int size) {
                return new DataBean[size];
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

        public List<F> getContent() {
            return content;
        }

        public void setContent(List<F> content) {
            this.content = content;
        }

    }

}
