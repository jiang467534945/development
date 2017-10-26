package com.platform.upms.service;

import com.platform.common.base.service.BaseService;
import com.platform.upms.model.PositionMenu;

public interface PositionMenuService extends BaseService<PositionMenu> {

    Integer deleteByPositionId(Integer positionId);

    Integer saveArray(Integer positionId, Integer[] menuIds);

}
