package com.platform.upms.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.List;

public class UpmsMenu implements Serializable {

    private static final long serialVersionUID = 4935223973686213159L;

    private Integer id;

    private UpmsMenu parent;	// 父级菜单

    private String  menuName;

    private Integer pId;

    private String  url;

    private Integer sort;

    private String  icon;

    private String menuContext;

    private String permissionId;

    private List<UpmsMenu> levelList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getParentId() {
        return pId != null ? pId : 0;
    }

    public UpmsMenu getParent() {
        return parent;
    }

    public void setParent(UpmsMenu parent) {
        this.parent = parent;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public Integer getpId() {
        return pId;
    }

    public void setpId(Integer pId) {
        this.pId = pId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getMenuContext() {
        return menuContext;
    }

    public void setMenuContext(String menuContext) {
        this.menuContext = menuContext;
    }

    public String getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(String permissionId) {
        this.permissionId = permissionId;
    }

    public List<UpmsMenu> getLevelList() {
        return levelList;
    }

    public void setLevelList(List<UpmsMenu> levelList) {
        this.levelList = levelList;
    }

    @Override
    public String toString() {
        return "UpmsMenu{" +
                "id=" + id +
                ", parent=" + parent +
                ", menuName='" + menuName + '\'' +
                ", pId=" + pId +
                ", url='" + url + '\'' +
                ", sort=" + sort +
                ", icon='" + icon + '\'' +
                ", menuContext='" + menuContext + '\'' +
                ", permissionId='" + permissionId + '\'' +
                ", levelList=" + levelList +
                '}';
    }

    @JsonIgnore
    public static void sortList(List<UpmsMenu> list, List<UpmsMenu> sourcelist, Integer parentId, boolean cascade){
        for (int i=0; i<sourcelist.size(); i++){
            UpmsMenu e = sourcelist.get(i);
            if (e.getParent()!=null && e.getParent().getId()!=null && e.getParent().getId().equals(parentId)){
                list.add(e);
                if (cascade){
                    // 判断是否还有子节点, 有则继续获取子节点
                    for (int j=0; j<sourcelist.size(); j++){
                        UpmsMenu child = sourcelist.get(j);
                        if (child.getParent().getId()!=null && child.getParent().getId()!=null &&child.getParent().getId().equals(e.getId())){
                            sortList(list, sourcelist, e.getId(), true);
                            break;
                        }
                    }
                }
            }
        }
    }
}
