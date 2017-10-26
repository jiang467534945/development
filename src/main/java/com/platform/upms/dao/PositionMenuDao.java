package com.platform.upms.dao;

import com.platform.common.annotation.MyBatisDao;
import com.platform.common.base.dao.BaseDao;
import com.platform.upms.model.PositionMenu;
import org.apache.ibatis.annotations.Param;

@MyBatisDao
public interface PositionMenuDao extends BaseDao<PositionMenu> {

    Integer deleteByPositionId(Integer positionId);

    Integer saveArray(@Param(value = "positionId") Integer positionId, @Param(value = "menuIds") Integer[] menuIds);

}
