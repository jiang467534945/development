package com.platform.upms.service.impl;

import com.platform.common.base.impl.BaseServiceImpl;
import com.platform.upms.dao.UpmsOrgMenuDao;
import com.platform.upms.model.UpmsOrgMenu;
import com.platform.upms.model.ZNode;
import com.platform.upms.service.UpmsOrgMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UpmsOrgMenuServiceImpl extends BaseServiceImpl<UpmsOrgMenu> implements UpmsOrgMenuService {

    @Autowired
    private UpmsOrgMenuDao upmsOrgMenuDao;

    @Override
    public List<ZNode> findZnodeList(UpmsOrgMenu upmsOrgMenu) {
        return upmsOrgMenuDao.findZnodeList(upmsOrgMenu);
    }

    @Override
    public Integer deleteByOrgId(Integer orgId) {
        return upmsOrgMenuDao.deleteByOrgId(orgId);
    }

    @Override
    public Integer saveArray(Integer orgId,Integer[] menuId) {
        return upmsOrgMenuDao.saveArray(orgId, menuId);
    }

}
