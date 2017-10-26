package com.platform.upms.dao;

import com.platform.common.annotation.MyBatisDao;
import com.platform.common.base.dao.BaseDao;
import com.platform.common.page.Page;
import com.platform.upms.model.UpmsUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@MyBatisDao
public interface UpmsUserDao extends BaseDao<UpmsUser> {
    /**
     * 根据用户名查询
     * 2017-9-28  榕榕 创建
     * @param userName
     * @return
     */
    UpmsUser selectByUserName(String userName);

    Integer save(UpmsUser user);

    List<UpmsUser> list(Page<UpmsUser> page, @Param(value = "user")UpmsUser user);

    void deleteByIds(Integer[] ids);

    UpmsUser findById(String id);

    void edit(UpmsUser user);

    List<String> findPermissionByUserId(Integer userId);
}
