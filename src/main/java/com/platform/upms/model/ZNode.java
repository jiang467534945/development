package com.platform.upms.model;

import java.util.ArrayList;
import java.util.List;

public class ZNode {

    private String id;

    private String pId;

    private String name;

    private Boolean checked;

    private Boolean chkDisabled;

    private List<ZNode> children;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getpId() {
        return pId;
    }

    public void setpId(String pId) {
        this.pId = pId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }

    public Boolean getChkDisabled() {
        return chkDisabled;
    }

    public void setChkDisabled(Boolean chkDisabled) {
        this.chkDisabled = chkDisabled;
    }

    public List<ZNode> getChildren() {
        return children;
    }

    public void setChildren(List<ZNode> children) {
        this.children = children;
    }

    private List<ZNode> getPreOrg(List<ZNode> list) {
        List<ZNode> array = new ArrayList<>();
        list.forEach(l -> {
            if("0".equals(l.getpId())) {
                array.add(l);
            }
        });
        array.forEach(a -> a.setChildren(getOrgList(a.getId(),list)));
        return array;
    }

    private List<ZNode> getOrgList(String id, List<ZNode> list) {
        List<ZNode> array = new ArrayList<>();
        list.forEach(l -> {
            if(id.equals(l.getpId())) {
                array.add(l);
            }
        });
        if(array.size() == 0) {
            return null;
        }
        array.forEach(a -> a.setChildren(getOrgList(a.getId(),list)));
        return array;
    }

}
