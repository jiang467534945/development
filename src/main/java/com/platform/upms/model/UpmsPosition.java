package com.platform.upms.model;

import java.util.Date;

/**
 * Created by yanzengbao on 2017/10/10.
 */
public class UpmsPosition {

    private Integer id;

    private String positionName;

    private Integer orgId;

    private Date createTime;

    private String description;

    private String orgName;//组织名

    private UpmsOrg upmsOrg;//组织

    public UpmsPosition() {
    }

    public UpmsPosition(Integer id, String positionName, Integer orgId, Date createTime, String description) {
        this.id = id;
        this.positionName = positionName;
        this.orgId = orgId;
        this.createTime = createTime;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public UpmsOrg getUpmsOrg() {
        return upmsOrg;
    }

    public void setUpmsOrg(UpmsOrg upmsOrg) {
        this.upmsOrg = upmsOrg;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }
}

