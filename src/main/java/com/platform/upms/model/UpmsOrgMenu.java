package com.platform.upms.model;

public class UpmsOrgMenu {
    private Integer orgId;
    private Integer menuId;
    private UpmsMenu upmsMenu;

    private boolean checked;

    private Integer childId;

    public UpmsOrgMenu() {
        super();
    }

    public UpmsOrgMenu(Integer orgId, Integer menuId) {
        this.orgId = orgId;
        this.menuId = menuId;
    }

    public int getOrgId() {
        return orgId;
    }

    public void setOrgId(int orgId) {
        this.orgId = orgId;
    }

    public int getMenuId() {
        return menuId;
    }

    public void setMenuId(int menuId) {
        this.menuId = menuId;
    }

    public UpmsMenu getUpmsMenu() {
        return upmsMenu;
    }

    public void setUpmsMenu(UpmsMenu upmsMenu) {
        this.upmsMenu = upmsMenu;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public Integer getChildId() {
        return childId;
    }

    public void setChildId(Integer childId) {
        this.childId = childId;
    }
}
