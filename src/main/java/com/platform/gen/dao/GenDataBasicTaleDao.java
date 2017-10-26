package com.platform.gen.dao;

import com.platform.common.annotation.MyBatisDao;
import com.platform.gen.model.GenTable;
import com.platform.gen.model.GenTableColumn;

import java.util.List;

@MyBatisDao
public interface GenDataBasicTaleDao {
    List<GenTable>  findTableList(GenTable genTable);
    /**
     * 获取数据表字段
     * @param genTable
     * @return
     */
    List<GenTableColumn> findTableColumnList(GenTable genTable);
    /**
     * 获取数据表主键
     * @param genTable
     * @return
     */
    List<String> findTablePK(GenTable genTable);
}
