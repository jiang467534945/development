package com.platform.upms.service;

import com.platform.common.base.service.BaseService;
import com.platform.common.page.Page;
import com.platform.upms.model.UpmsUser;

import java.util.List;

public interface UpmsUserService extends BaseService<UpmsUser> {

    /**
     * 根据用户名查询
     * 2017-9-28 榕榕 创建
     * @param userName
     * @return
     */
    UpmsUser selectByUserName(String userName);

    Integer save(UpmsUser user);

    Page<UpmsUser> list(Page<UpmsUser> page);

    void deleteByIds(Integer[] ids);

    UpmsUser findById(String id);

    void edit(UpmsUser user);

    Integer update(UpmsUser upmsUser);

    Page<UpmsUser> findByPage(Page<UpmsUser> page, UpmsUser upmsUser);

    List<String> findPermissionByUserId(Integer userId);
}
