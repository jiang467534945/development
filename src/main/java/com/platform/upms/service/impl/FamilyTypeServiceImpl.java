package com.platform.upms.service.impl;

import com.platform.common.base.impl.BaseServiceImpl;
import com.platform.upms.dao.FamilyTypeDao;
import com.platform.upms.model.FamilyType;
import com.platform.upms.service.FamilyTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class FamilyTypeServiceImpl  extends BaseServiceImpl<FamilyType> implements FamilyTypeService{

    @Autowired
    private FamilyTypeDao familyTypeDao;


    @Override
    public List<FamilyType> listNoPage() {
        return familyTypeDao.listNoPage();
    }

    @Override
    public Integer add(FamilyType familyType) {
        return familyTypeDao.insert(familyType);
    }

    @Override
    public Integer deletes(String[] ids) {
        return familyTypeDao.deletes(ids);
    }

    @Override
    public FamilyType selectById(String id) {
        return familyTypeDao.selectById(id);
    }

    @Override
    public Integer update(FamilyType familyType) {
        return familyTypeDao.update(familyType);
    }
}
