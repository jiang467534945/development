package com.platform.upms.service.impl;

import com.platform.common.base.impl.BaseServiceImpl;
import com.platform.upms.dao.PositionMenuDao;
import com.platform.upms.model.PositionMenu;
import com.platform.upms.service.PositionMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PositionMenuServiceImpl extends BaseServiceImpl<PositionMenu> implements PositionMenuService {

    @Autowired
    private PositionMenuDao upmsPositionMenuDao;

    @Override
    public Integer deleteByPositionId(Integer positionId) {
        return upmsPositionMenuDao.deleteByPositionId(positionId);
    }

    @Override
    public Integer saveArray(Integer positionId, Integer[] menuIds) {
        return upmsPositionMenuDao.saveArray(positionId, menuIds);
    }
}
