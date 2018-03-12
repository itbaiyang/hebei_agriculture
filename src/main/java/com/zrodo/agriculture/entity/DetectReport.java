package com.zrodo.agriculture.entity;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;
import java.util.List;

public class DetectReport {
    @ApiModelProperty(name = "detectId", value = "自增id", hidden = true)
    private int detectId;
    @ApiModelProperty(name = "sampleId", value = "样品Id", required = true)
    private int sampleId;
    @ApiModelProperty(name = "companyId", value = "企业Id", required = true)
    private int companyId;
    @ApiModelProperty(name = "productId", value = "产品id", required = true)
    private int productId;
    @ApiModelProperty(name = "productTypeId", value = "产品类型id", required = true)
    private int productTypeId;
    @ApiModelProperty(name = "itemId", value = "检测项目", required = true)
    private int itemId;
    @ApiModelProperty(name = "reagentId", value = "检测方法", required = true)
    private int reagentId;
    @ApiModelProperty(name = "sourceId", value = "检测来源", required = true)
    private int sourceId;
    @ApiModelProperty(name = "resultId", value = "检测结果", required = true)
    private int resultId;
    @ApiModelProperty(name = "detectUserId", value = "检测人", required = true)
    private int detectUserId;
    @ApiModelProperty(name = "detectDate", value = "检测时间", required = true)
    private Date detectDate;
    @ApiModelProperty(name = "reviewFlag", value = "是否复核", required = true)
    private String reviewFlag;
    @ApiModelProperty(name = "imageUrl", value = "检测图片", required = false)
    private String imageUrl;
    @ApiModelProperty(name = "imageUrls", value = "检测图片", required = true)
    private List<String> imageUrls;

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

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
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

    public List<String> getImageUrls() {
        return imageUrls;
    }

    public void setImageUrls(List<String> imageUrls) {
        this.imageUrls = imageUrls;
    }
}
