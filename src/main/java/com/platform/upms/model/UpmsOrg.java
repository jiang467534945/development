package com.platform.upms.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.Date;
import java.util.List;

public class UpmsOrg {

    private Integer id;
    private String orgName;
    private Integer pId;
    private Date createTime;
    private  String description;
    private  String pName;
    private String menuId;
    private UpmsOrg parentOrg;

    private Integer sort;

    public UpmsOrg() {
    }

    public UpmsOrg(Integer id, String orgName, Integer pId, Date createTime, String description, String pName) {
        this.id = id;
        this.orgName = orgName;
        this.pId = pId;
        this.createTime = createTime;
        this.description = description;
        this.pName = pName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public Integer getpId() {
        return pId;
    }

    public void setpId(Integer pId) {
        this.pId = pId;
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

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public UpmsOrg getParentOrg() {
        return parentOrg;
    }

    public void setParentOrg(UpmsOrg parentOrg) {
        this.parentOrg = parentOrg;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    @Override
    public String toString() {
        return "UpmsOrg{" +
                "id=" + id +
                ", orgName='" + orgName + '\'' +
                ", pId=" + pId +
                ", createTime=" + createTime +
                ", description='" + description + '\'' +
                ", pName='" + pName + '\'' +
                ", parentOrg=" + parentOrg +
                ", sort=" + sort +
                '}';
    }

    @JsonIgnore
    public static void sortList(List<UpmsOrg> list, List<UpmsOrg> sourcelist, Integer parentId, boolean cascade){
        System.out.println("父ID:"+parentId);
        for (int i=0; i<sourcelist.size(); i++){
            UpmsOrg  upmsOrg = sourcelist.get(i);
            if (upmsOrg.getParentOrg()!=null && upmsOrg.getParentOrg().getId()!=null && upmsOrg.getParentOrg().getId().equals(parentId)){
                list.add(upmsOrg);
                if (cascade){
                    // 判断是否还有子节点, 有则继续获取子节点
                    for (int j=0; j<sourcelist.size(); j++){
                        UpmsOrg child = sourcelist.get(j);
                        if (child.getParentOrg().getId()!=null && child.getParentOrg().getId()!=null &&child.getParentOrg().getId().equals(upmsOrg.getId())){
                            sortList(list, sourcelist, upmsOrg.getId(), true);
                            break;
                        }
                    }
                }
            }
        }
    }
}
