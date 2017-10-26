package com.platform.upms.dao;

import com.platform.common.annotation.MyBatisDao;
import com.platform.common.base.dao.BaseDao;
import com.platform.upms.model.FamilyEquipment;

@MyBatisDao
public interface FamilyEquipmentDao  extends BaseDao<FamilyEquipment> {

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
    Long  countNumber(String number);

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
    FamilyEquipment selectById(String id);


}
