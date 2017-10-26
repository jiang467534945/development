package com.platform.upms.dao;

import com.platform.common.annotation.MyBatisDao;
import com.platform.common.base.dao.BaseDao;
import com.platform.upms.model.FamilyType;

import java.util.List;

@MyBatisDao
public interface FamilyTypeDao  extends BaseDao<FamilyType> {


    /**
     * 查询列表不带分页
     * @return
     */
    List<FamilyType> listNoPage();

    /**
     * 添加设备的类型
     * @param familyType
     * @return
     */
    Integer insert(FamilyType familyType);

    /**
     * 删除设备类型
     * @param ids
     * @return
     */
    Integer deletes(String[] ids);


    /**
     * 根据设备类型的ID查询
     * @param id
     * @return
     */
    FamilyType selectById(String id);



}
