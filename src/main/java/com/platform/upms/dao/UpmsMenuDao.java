package com.platform.upms.dao;

import com.platform.common.annotation.MyBatisDao;
import com.platform.common.base.dao.BaseDao;
import com.platform.common.page.Page;
import com.platform.upms.model.UpmsMenu;
import com.platform.upms.model.UpmsOrg;
import com.platform.upms.model.ZNode;

import java.util.List;
@MyBatisDao
public interface UpmsMenuDao extends BaseDao<UpmsMenu> {

    List<UpmsMenu> selectList(UpmsMenu upmsMenu);
    List<UpmsMenu> findAllList();
    UpmsMenu getMenu();
    int inset(UpmsMenu upmsMenu);

    /**
     * 分页 查询所有
     * @param page
     * @param upmsMenu
     * @return
     */
    List<UpmsMenu> list(Page<UpmsMenu> page, UpmsMenu upmsMenu);

    List<ZNode> findZnodeList(UpmsOrg upmsOrg);

}
