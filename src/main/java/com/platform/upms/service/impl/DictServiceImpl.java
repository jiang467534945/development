package com.platform.upms.service.impl;

import com.platform.common.base.impl.BaseServiceImpl;
import com.platform.upms.dao.DictDao;
import com.platform.upms.model.Dict;
import com.platform.upms.service.DictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class DictServiceImpl extends BaseServiceImpl<Dict> implements DictService{
    @Autowired
    DictDao dictDao;
    /**
     * 查询字段类型列表
     * @return
     */
    public List<String> findTypeList(){
        return dictDao.findTypeList(new Dict());
    }

}
