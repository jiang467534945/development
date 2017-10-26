package com.platform.upms.service;

import com.platform.common.base.service.BaseService;
import com.platform.common.page.Page;
import com.platform.upms.model.UpmsMenu;
import com.platform.upms.model.UpmsOrg;
import com.platform.upms.model.ZNode;

import java.util.List;


public interface UpmsMenuService extends BaseService<UpmsMenu> {

    //查询菜单
    List <UpmsMenu> list(UpmsMenu upmsMenu);
    UpmsMenu getMenu();


    List<ZNode> findZnodeList(UpmsOrg upmsOrg);

    Page<UpmsMenu> findByPageRedis(Page<UpmsMenu> page, UpmsMenu upmsMenu);
}
