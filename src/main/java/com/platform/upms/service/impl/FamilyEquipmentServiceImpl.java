package com.platform.upms.service.impl;

import com.platform.common.base.impl.BaseServiceImpl;
import com.platform.upms.dao.FamilyEquipmentDao;
import com.platform.upms.model.FamilyEquipment;
import com.platform.upms.service.FamilyEquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class FamilyEquipmentServiceImpl extends BaseServiceImpl<FamilyEquipment> implements FamilyEquipmentService {

    @Autowired
    private FamilyEquipmentDao familyEquipmentDao;
    @Override
    public Integer insert(FamilyEquipment familyEquipment) {
        return familyEquipmentDao.insert(familyEquipment);
    }

    @Override
    public Long countNumber(String number) {
        return familyEquipmentDao.countNumber(number);
    }

    @Override
    public Integer deletes(String[] ids) {
        return familyEquipmentDao.deletes(ids);
    }

    @Override
    public FamilyEquipment selectById(String id) {
        return familyEquipmentDao.selectById(id);
    }

    @Override
    public Integer update(FamilyEquipment familyType) {
        return familyEquipmentDao.update(familyType);
    }
}
