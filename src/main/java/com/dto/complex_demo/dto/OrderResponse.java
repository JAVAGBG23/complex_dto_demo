package com.dto.complex_demo.dto;

import java.util.Date;
import java.util.List;

public class OrderResponse {
    private String userId;

    private List<String> orderedProductIds;

    private Date orderedDate;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<String> getOrderedProductIds() {
        return orderedProductIds;
    }

    public void setOrderedProductIds(List<String> orderedProductIds) {
        this.orderedProductIds = orderedProductIds;
    }

    public Date getOrderedDate() {
        return orderedDate;
    }

    public void setOrderedDate(Date orderedDate) {
        this.orderedDate = orderedDate;
    }
}
