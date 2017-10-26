package com.platform.upms.model;

import java.util.Date;

public class UpmsPicture {

    private Integer id;
    private Integer userId;
    private Date createTime;
    private String picUrl;
    private String type;

    private Double imgSize;

    private String  userName;

    public UpmsPicture() {
    }

    public UpmsPicture(Integer userId) {
        this.userId = userId;
    }

    public UpmsPicture(Integer id, Integer userId, Date createTime, String picUrl, String type) {
        this.id = id;
        this.userId = userId;
        this.createTime = createTime;
        this.picUrl = picUrl;
        this.type = type;
    }

    public UpmsPicture(Integer userId, Date createTime, String picUrl, String type, Double imgSize) {
        this.userId = userId;
        this.createTime = createTime;
        this.picUrl = picUrl;
        this.type = type;
        this.imgSize = imgSize;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Double getImgSize() {
        return imgSize;
    }

    public void setImgSize(Double imgSize) {
        this.imgSize = imgSize;
    }
}
