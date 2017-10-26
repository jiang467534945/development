package com.platform.gen.service.impl;

import com.platform.common.page.Page;
import com.platform.common.utils.IdGen;
import com.platform.common.utils.StringUtils;
import com.platform.gen.dao.GenDataBasicTaleDao;
import com.platform.gen.dao.GenTableColumnDao;
import com.platform.gen.dao.GenTableDao;
import com.platform.gen.model.GenTable;
import com.platform.gen.model.GenTableColumn;
import com.platform.gen.service.GenTableService;
import com.platform.gen.utils.GenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class GenTableServiceImpl implements GenTableService {
    @Autowired
    GenTableDao genTableDao;
    @Autowired
    GenDataBasicTaleDao genDataBaseDictDao;
    @Autowired
    GenTableColumnDao genTableColumnDao;
    @Override
    public List<GenTable> findAlllist() {
        return genTableDao.findList(new GenTable());
    }

    public GenTable get(String id) {
        GenTable genTable = genTableDao.get(id);
        GenTable genTable1 = new GenTable();
        genTable1.setId(genTable.getId());
        GenTableColumn genTableColumn = new GenTableColumn();
        genTableColumn.setGenTable(genTable1);
        genTable.setColumnList(genTableColumnDao.findList(genTableColumn));
        return genTable;
    }



    public List<GenTable> findAll() {
        return genTableDao.findAllList(new GenTable());
    }

    /**
     * 获取物理数据表列表
     * @param genTable
     * @return
     */
    public List<GenTable> findTableListFormDb(GenTable genTable){
        return genDataBaseDictDao.findTableList(genTable);
    }

    /**
     * 验证表名是否可用，如果已存在，则返回false
     * @param genTable
     * @return
     */
    public boolean checkTableName(String tableName){
        if (StringUtils.isBlank(tableName)){
            return true;
        }
        GenTable genTable = new GenTable();
        genTable.setName(tableName);
        List<GenTable> list = genTableDao.findList(genTable);
        return list.size() == 0;
    }

    /**
     * 获取物理数据表列表
     * @param genTable
     * @return
     */
    public GenTable getTableFormDb(GenTable genTable){
        // 如果有表名，则获取物理表
        if (StringUtils.isNotBlank(genTable.getName())){

            List<GenTable> list = genDataBaseDictDao.findTableList(genTable);
            if (list.size() > 0){

                // 如果是新增，初始化表属性
                if (StringUtils.isBlank(genTable.getId())){
                    genTable = list.get(0);
                    // 设置字段说明
                    if (StringUtils.isBlank(genTable.getComments())){
                        genTable.setComments(genTable.getName());
                    }
                    genTable.setClassName(StringUtils.toCapitalizeCamelCase(genTable.getName()));
                }

                // 添加新列
                List<GenTableColumn> columnList = genDataBaseDictDao.findTableColumnList(genTable);
                for (GenTableColumn column : columnList){
                    boolean b = false;
                    for (GenTableColumn e : genTable.getColumnList()){
                        if (e.getName().equals(column.getName())){
                            b = true;
                        }
                    }
                    if (!b){
                        genTable.getColumnList().add(column);
                    }
                }

     /*           // 删除已删除的列
                for (GenTableColumn e : genTable.getColumnList()){
                    boolean b = false;
                    for (GenTableColumn column : columnList){
                        if (column.getName().equals(e.getName())){
                            b = true;
                        }
              }

                }*/

                // 获取主键
                genTable.setPkList(genDataBaseDictDao.findTablePK(genTable));

                // 初始化列属性字段
                GenUtils.initColumnField(genTable);

            }
        }
        return genTable;
    }

    @Transactional(readOnly = false)
    public void save(GenTable genTable) {
        if (StringUtils.isBlank(genTable.getId())){
            genTable.setId(IdGen.uuid());

            genTableDao.saveData(genTable);
        }else{
            genTableDao.update(genTable);
        }
        // 保存列
        for (GenTableColumn column : genTable.getColumnList()){
            column.setGenTable(genTable);
            if (StringUtils.isBlank(column.getId())){
                column.setId(IdGen.uuid());

                genTableColumnDao.saveData(column);
            }else{

                genTableColumnDao.update(column);
            }
        }
    }

    @Transactional(readOnly = false)
    public void delete(GenTable genTable) {
        genTableDao.deleteData(genTable);
        genTableColumnDao.deleteByGenTableId(genTable.getId());
    }
}
