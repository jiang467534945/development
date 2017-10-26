package com.platform.upms.service.impl;

import com.platform.common.base.impl.BaseServiceImpl;
import com.platform.common.page.Page;
import com.platform.upms.dao.UpmsUserDao;
import com.platform.upms.model.UpmsUser;
import com.platform.upms.service.UpmsUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UpmsUserServiceImpl extends BaseServiceImpl<UpmsUser> implements UpmsUserService {

    @Autowired
    private UpmsUserDao upmsUserDao;

    @Override
    public UpmsUser selectByUserName(String userName) {
        return  upmsUserDao.selectByUserName(userName);
    }

    @Override
    public Integer save(UpmsUser user) {
        return upmsUserDao.save(user);
    }


    @Override
    public Page<UpmsUser> list(Page<UpmsUser> page) {
        page.setResults(upmsUserDao.list(page, null));
        return page;
    }

    @Override
    public void deleteByIds(Integer[] ids) {
        upmsUserDao.deleteByIds(ids);
    }

    @Override
    public UpmsUser findById(String id) {
        return upmsUserDao.findById(id);
    }

    @Override
    public void edit(UpmsUser user) {
        upmsUserDao.edit(user);
    }

    @Override
    public Integer update(UpmsUser upmsUser) {
        return upmsUserDao.update(upmsUser);
    }

    @Override
    public Page<UpmsUser> findByPage(Page<UpmsUser> page, UpmsUser upmsUser) {
        page.setResults(upmsUserDao.findByPage(page,upmsUser));
        return page;
    }

    @Override
    public List<String> findPermissionByUserId(Integer userId) {
        return upmsUserDao.findPermissionByUserId(userId);
    }
}
