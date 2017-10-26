package com.platform.gen.dao;

import com.platform.common.annotation.MyBatisDao;
import com.platform.gen.model.GenTableColumn;
import com.platform.common.base.dao.BaseDao;

@MyBatisDao
public interface GenTableColumnDao extends BaseDao<GenTableColumn> {
    public void deleteByGenTableId(String genTableId);


}
