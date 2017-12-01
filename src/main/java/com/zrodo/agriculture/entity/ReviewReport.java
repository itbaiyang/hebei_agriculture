package com.zrodo.agriculture.entity;

import java.util.Date;

public class ReviewReport {

    private int id;

    private int detectId;

    private int reviewUserId;

    private int reviewReagentId;

    private int reviewResultId;

    private Date reviewDate;

    private String reviewReason;

    private int reasonId;

    private String videoUrl;

    private String reviewPicture;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDetectId() {
        return detectId;
    }

    public void setDetectId(int detectId) {
        this.detectId = detectId;
    }

    public int getReviewUserId() {
        return reviewUserId;
    }

    public void setReviewUserId(int reviewUserId) {
        this.reviewUserId = reviewUserId;
    }

    public int getReviewReagentId() {
        return reviewReagentId;
    }

    public void setReviewReagentId(int reviewReagentId) {
        this.reviewReagentId = reviewReagentId;
    }

    public int getReviewResultId() {
        return reviewResultId;
    }

    public void setReviewResultId(int reviewResultId) {
        this.reviewResultId = reviewResultId;
    }

    public Date getReviewDate() {
        return reviewDate;
    }

    public void setReviewDate(Date reviewDate) {
        this.reviewDate = reviewDate;
    }

    public String getReviewReason() {
        return reviewReason;
    }

    public void setReviewReason(String reviewReason) {
        this.reviewReason = reviewReason;
    }

    public int getReasonId() {
        return reasonId;
    }

    public void setReasonId(int reasonId) {
        this.reasonId = reasonId;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getReviewPicture() {
        return reviewPicture;
    }

    public void setReviewPicture(String reviewPicture) {
        this.reviewPicture = reviewPicture;
    }
}
