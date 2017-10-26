package com.platform.upms.dao;

import com.platform.common.annotation.MyBatisDao;
import com.platform.common.base.dao.BaseDao;
import com.platform.upms.model.UpmsOrgMenu;
import com.platform.upms.model.ZNode;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@MyBatisDao
public interface UpmsOrgMenuDao extends BaseDao<UpmsOrgMenu> {

    List<ZNode> findZnodeList(UpmsOrgMenu upmsOrgMenu);

    Integer deleteByOrgId(Integer orgId);

    Integer saveArray(@Param(value = "orgId") Integer orgId, @Param(value = "menuId") Integer[] menuId);

}
