package com.platform.upms.model;

import java.util.Date;

public class FamilyEquipment {

    private String id;
    private String number;
    private String name;
    private String typeId;
    private String gatewayId;
    private String intro;
    private Date CreateTime;
    private String gatewayNumber;
    private String typeName;

    public FamilyEquipment() {
    }

    public FamilyEquipment(String id, String number, String name, String typeId, String gatewayId, String intro, Date createTime, String gatewayNumber, String typeName) {
        this.id = id;
        this.number = number;
        this.name = name;
        this.typeId = typeId;
        this.gatewayId = gatewayId;
        this.intro = intro;
        CreateTime = createTime;
        this.gatewayNumber = gatewayNumber;
        this.typeName = typeName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public Date getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(Date createTime) {
        CreateTime = createTime;
    }

    public String getGatewayNumber() {
        return gatewayNumber;
    }

    public void setGatewayNumber(String gatewayNumber) {
        this.gatewayNumber = gatewayNumber;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getGatewayId() {
        return gatewayId;
    }

    public void setGatewayId(String gatewayId) {
        this.gatewayId = gatewayId;
    }
}
