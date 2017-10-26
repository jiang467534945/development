package com.platform.upms.service;

import com.platform.common.base.service.BaseService;
import com.platform.upms.model.FamilyType;

import java.util.List;

public interface FamilyTypeService extends BaseService<FamilyType> {

    /**
     * 查询 不带分页
     * @return
     */
    List<FamilyType> listNoPage();

    /**
     * 添加设备的类型
     * @param familyType
     * @return
     */
    Integer add(FamilyType familyType);

    /**
     * 删除设备的类型
     * @param ids
     * @return
     */
    Integer deletes(String[] ids);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    FamilyType selectById(String id);

    /**
     * 修改
     * @param familyType
     * @return
     */
    Integer update(FamilyType familyType);
}
