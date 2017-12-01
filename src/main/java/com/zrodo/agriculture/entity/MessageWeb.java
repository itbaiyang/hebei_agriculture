package com.zrodo.agriculture.entity;

import java.sql.Timestamp;

public class MessageWeb {
    private int messageId;
    private String title;
    private byte[] content;
    private Timestamp createDate;
    private int userId;
    private int deptId;
    private boolean pustatus;
    private boolean isShow;
    private boolean isSendWeiXin;
    private String contentFont;

    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getDeptId() {
        return deptId;
    }

    public void setDeptId(int deptId) {
        this.deptId = deptId;
    }

    public boolean isPustatus() {
        return pustatus;
    }

    public void setPustatus(boolean pustatus) {
        this.pustatus = pustatus;
    }

    public boolean isShow() {
        return isShow;
    }

    public void setShow(boolean show) {
        isShow = show;
    }

    public boolean isSendWeiXin() {
        return isSendWeiXin;
    }

    public void setSendWeiXin(boolean sendWeiXin) {
        isSendWeiXin = sendWeiXin;
    }

    public String getContentFont() {
        return contentFont;
    }

    public void setContentFont(String contentFont) {
        this.contentFont = contentFont;
    }
}
