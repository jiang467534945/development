package com.platform.upms.service;

import com.platform.common.base.service.BaseService;
import com.platform.upms.model.UpmsOrgMenu;
import com.platform.upms.model.ZNode;

import java.util.List;

public interface UpmsOrgMenuService extends BaseService<UpmsOrgMenu> {

    List<ZNode> findZnodeList(UpmsOrgMenu upmsOrgMenu);

    Integer deleteByOrgId(Integer orgId);

    Integer saveArray(Integer orgId,Integer[] menuId);

}
