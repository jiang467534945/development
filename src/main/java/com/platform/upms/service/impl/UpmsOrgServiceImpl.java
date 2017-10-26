package com.platform.upms.service.impl;

import com.platform.common.base.impl.BaseServiceImpl;
import com.platform.upms.dao.UpmsOrgDao;
import com.platform.upms.model.UpmsOrg;
import com.platform.upms.model.ZNode;
import com.platform.upms.service.UpmsOrgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class UpmsOrgServiceImpl  extends BaseServiceImpl<UpmsOrg> implements UpmsOrgService {

    @Autowired
    private UpmsOrgDao upmsOrgDao;

    /**
     * 保存数据
     * @param upmsOrg
     * @return
     */
    public Integer insert(UpmsOrg upmsOrg)  {
        return upmsOrgDao.insert(upmsOrg);
    }

    /**
     * 根据Id删除
     * @param id
     * @return
     */
    public Integer deleteById(String id) {
        return upmsOrgDao.deleteById(id);
    }

    /**
     * 根据id修改
     * @param upmsOrg
     * @return
     */
    public Integer update(UpmsOrg upmsOrg) {
        return upmsOrgDao.update(upmsOrg);
    }

    /**
     * 查询列表
     * @param upmsOrg
     * @return
     */
    @Override
    public List<UpmsOrg> selectAllList(UpmsOrg upmsOrg) {
        return upmsOrgDao.selectAllList(upmsOrg);
    }

    @Override
    public UpmsOrg getOrg() {
        UpmsOrg upmsOrg = upmsOrgDao.getOrg();
        return upmsOrg;
    }

    @Override
    public List<ZNode> listOrg() {
        List<ZNode> list = upmsOrgDao.listOrg();
        return list;
    }


}
