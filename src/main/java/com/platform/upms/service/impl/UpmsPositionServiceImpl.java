package com.platform.upms.service.impl;

import com.platform.common.base.impl.BaseServiceImpl;
import com.platform.common.page.Page;
import com.platform.upms.dao.UpmsPositionDao;
import com.platform.upms.model.UpmsPosition;
import com.platform.upms.model.ZNode;
import com.platform.upms.service.UpmsPositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by yanzengbao on 2017/10/10.
 */
@Service
@Transactional
public class UpmsPositionServiceImpl extends BaseServiceImpl<UpmsPosition> implements UpmsPositionService {

    @Autowired
    private UpmsPositionDao upmsPositionDao;

    @Override
    public List<UpmsPosition> findAllList() {
        List<UpmsPosition> list = upmsPositionDao.findAllList();
        return list;
    }

    @Override
    public Integer save(UpmsPosition upmsPosition) throws Exception {
        return upmsPositionDao.save(upmsPosition);
    }

    @Override
    public Page<UpmsPosition> list(Page<UpmsPosition> page) {
        page.setResults(upmsPositionDao.list(page, null));
        return page;
    }

    @Override
    public void deleteByIds(String[] ids) {
        upmsPositionDao.deleteByIds(ids);
    }

    @Override
    public UpmsPosition selectById(UpmsPosition upmsPosition) {
        return upmsPositionDao.selectById(upmsPosition);
    }

    @Override
    public Integer updateById(UpmsPosition upmsPosition) throws Exception {
        return upmsPositionDao.updateById(upmsPosition);
    }

    @Override
    public Page<UpmsPosition> findByPage(Page<UpmsPosition> page, UpmsPosition upmsPosition) {
        page.setResults(upmsPositionDao.findByPage(page,upmsPosition));
        return page;
    }

    @Override
    public List<ZNode> menuList(UpmsPosition upmsPosition) {
        return upmsPositionDao.menuList(upmsPosition);
    }
}
