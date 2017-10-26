package com.platform.upms.service;

import com.platform.common.base.service.BaseService;
import com.platform.upms.model.FamilyEquipment;

public interface FamilyEquipmentService extends BaseService<FamilyEquipment> {

    /**
     * 保存数据
     * @param familyEquipment
     * @return
     */
    Integer insert(FamilyEquipment familyEquipment);

    /**
     * 根据设备的编号统计设备的个数
     * @param number
     * @return
     */
    Long countNumber(String number);

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
    FamilyEquipment selectById(String id);

    /**
     * 修改
     * @param familyType
     * @return
     */
    Integer update(FamilyEquipment familyType);
}
