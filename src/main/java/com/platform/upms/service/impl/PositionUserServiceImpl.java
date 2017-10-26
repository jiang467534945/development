package com.platform.upms.service.impl;

import com.platform.common.base.impl.BaseServiceImpl;
import com.platform.common.page.Page;
import com.platform.upms.dao.PositionUserDao;
import com.platform.upms.model.PositionUser;
import com.platform.upms.model.UpmsUser;
import com.platform.upms.model.ZNode;
import com.platform.upms.service.PositionUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PositionUserServiceImpl extends BaseServiceImpl<PositionUser> implements PositionUserService {

    @Autowired
    private PositionUserDao positionUserDao;

    @Override
    public Page<UpmsUser> listUserByPage(Page<UpmsUser> page, UpmsUser user) {
        page.setResults(positionUserDao.listUserByPage(page, user));
        return page;
    }

    @Override
    public Integer deleteByPositionId(Integer positionId) {
        return positionUserDao.deleteByPositionId(positionId);
    }

    @Override
    public Integer saveArray(Integer positionId, Integer[] userIds) {
        return positionUserDao.saveArray(positionId, userIds);
    }

    @Override
    public List<ZNode> positionList(Integer id) {
        return positionUserDao.positionList(id);
    }

    @Override
    public Integer deteleteByUserId(Integer userId) {
        return positionUserDao.deteleteByUserId(userId);
    }

    @Override
    public Integer saveArrayOpposite(Integer userId, Integer[] integers) {
        return positionUserDao.saveArrayOpposite(userId, integers);
    }

}
