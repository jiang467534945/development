package com.platform.gen.dao;

import com.platform.common.annotation.MyBatisDao;
import com.platform.gen.model.GenTable;
import com.platform.common.base.dao.BaseDao;

import java.util.List;
@MyBatisDao
public interface GenTableDao extends BaseDao<GenTable> {

    List<GenTable>findModel(GenTable genTable);
/*
    GenTable get(String id);
*/

}
