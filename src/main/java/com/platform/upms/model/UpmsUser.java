package com.platform.upms.model;

import java.io.Serializable;
import java.util.Date;

public class UpmsUser implements Serializable {

    private static final long serialVersionUID = -2022028670963986957L;

    private Integer id;
    private String userName;
    private String password;
    private String name;
    private String sex;
    private String idNumber;
    private Date createTime;
    private Date lastLogin;
    private Integer status;

    /**
     * 可用空间
     */
    private Double availableSpace;
    /**
     * 总空间
     */
    private Double totalSpace;

    public UpmsUser() {
        super();
    }

    public UpmsUser(Integer id, String userName, String password, String name, String sex, String idNumber, Date createTime, Date lastLogin, Integer status) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.name = name;
        this.sex = sex;
        this.idNumber = idNumber;
        this.createTime = createTime;
        this.lastLogin = lastLogin;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Double getAvailableSpace() {
        return availableSpace;
    }

    public void setAvailableSpace(Double availableSpace) {
        this.availableSpace = availableSpace;
    }

    public Double getTotalSpace() {
        return totalSpace;
    }

    public void setTotalSpace(Double totalSpace) {
        this.totalSpace = totalSpace;
    }

    @Override
    public String toString() {
        return "UpmsUser{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", idNumber='" + idNumber + '\'' +
                ", createTime=" + createTime +
                ", lastLogin=" + lastLogin +
                ", status=" + status +
                ", availableSpace=" + availableSpace +
                ", totalSpace=" + totalSpace +
                '}';
    }
}
