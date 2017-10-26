package com.platform.upms.service;

import com.platform.common.base.service.BaseService;
import com.platform.upms.model.UpmsOrg;
import com.platform.upms.model.ZNode;

import java.util.List;

public interface UpmsOrgService extends BaseService<UpmsOrg> {

    /**
     * 查询
     * @param upmsOrg
     * @return
     */
    List<UpmsOrg> selectAllList(UpmsOrg upmsOrg);

    /**
     * 查询顶级组织 id：1
     * @return
     */
    UpmsOrg getOrg();



    List<ZNode> listOrg();
}
