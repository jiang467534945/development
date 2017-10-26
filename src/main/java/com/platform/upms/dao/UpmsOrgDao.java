package com.platform.upms.dao;

import com.platform.common.annotation.MyBatisDao;
import com.platform.common.base.dao.BaseDao;
import com.platform.upms.model.UpmsOrg;
import com.platform.upms.model.ZNode;

import java.util.List;

@MyBatisDao
public interface UpmsOrgDao extends BaseDao<UpmsOrg> {

    /**
     * 添加
     * @param upmsOrg
     * @return
     */
    Integer insert(UpmsOrg upmsOrg);

    /**
     * 根据id删除
     * @param id
     * @return
     */
    Integer deleteById(String id);



    List<UpmsOrg> selectAllList(UpmsOrg upmsOrg);

    /**
     * 查询顶级组织：id，1
     * @return
     */
    UpmsOrg getOrg();

    List<ZNode> listOrg();

}
