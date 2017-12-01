package com.zrodo.agriculture.entity;

import java.util.Date;

public class DetectReport {

    private int detectId;
    private int sampleId;
    private int companyId;
    private int productTypeId;
    private int itemId;
    private int reagentId;
    private int objectId;
    private int sourceId;
    private int resultId;
    private int detectUserId;
    private Date detectDate;
    private String reviewFlag;
    private String imageUrl;

    public int getDetectId() {
        return detectId;
    }

    public void setDetectId(int detectId) {
        this.detectId = detectId;
    }

    public int getSampleId() {
        return sampleId;
    }

    public void setSampleId(int sampleId) {
        this.sampleId = sampleId;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public int getProductTypeId() {
        return productTypeId;
    }

    public void setProductTypeId(int productTypeId) {
        this.productTypeId = productTypeId;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public int getReagentId() {
        return reagentId;
    }

    public void setReagentId(int reagentId) {
        this.reagentId = reagentId;
    }

    public int getObjectId() {
        return objectId;
    }

    public void setObjectId(int objectId) {
        this.objectId = objectId;
    }

    public int getSourceId() {
        return sourceId;
    }

    public void setSourceId(int sourceId) {
        this.sourceId = sourceId;
    }

    public int getResultId() {
        return resultId;
    }

    public void setResultId(int resultId) {
        this.resultId = resultId;
    }

    public int getDetectUserId() {
        return detectUserId;
    }

    public void setDetectUserId(int detectUserId) {
        this.detectUserId = detectUserId;
    }

    public Date getDetectDate() {
        return detectDate;
    }

    public void setDetectDate(Date detectDate) {
        this.detectDate = detectDate;
    }

    public String getReviewFlag() {
        return reviewFlag;
    }

    public void setReviewFlag(String reviewFlag) {
        this.reviewFlag = reviewFlag;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
