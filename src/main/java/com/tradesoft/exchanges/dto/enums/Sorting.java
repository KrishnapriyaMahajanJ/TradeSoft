package com.tradesoft.exchanges.dto.enums;

public enum Sorting {

    ASCENDING(1),
    DESCENDING(-1);

    public final int orderBy;

    Sorting(int orderBy) {
        this.orderBy = orderBy;
    }
}
