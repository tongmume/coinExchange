package com.bizzan.entity;

import java.io.Serializable;
import java.util.List;

public class CTCOrder implements Serializable {

    private int currentPage;
    private int totalPage;
    private int pageNumber;
    private int totalElement;

    private List<CTCOrderDetail> content;

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getTotalElement() {
        return totalElement;
    }

    public void setTotalElement(int totalElement) {
        this.totalElement = totalElement;
    }

    public List<CTCOrderDetail> getContent() {
        return content;
    }

    public void setContent(List<CTCOrderDetail> content) {
        this.content = content;
    }
}
