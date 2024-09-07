package com.bizzan.entity;

import java.util.List;

public interface DataList {

    boolean isFirstPage();

    boolean isLastPage();

    List getContent();
}
