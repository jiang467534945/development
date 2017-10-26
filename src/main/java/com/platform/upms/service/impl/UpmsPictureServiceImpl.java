package com.platform.upms.service.impl;

import com.platform.common.base.impl.BaseServiceImpl;
import com.platform.upms.dao.UpmsPictureDao;
import com.platform.upms.model.UpmsPicture;
import com.platform.upms.service.UpmsPictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UpmsPictureServiceImpl extends BaseServiceImpl<UpmsPicture> implements UpmsPictureService   {
    @Autowired
    private UpmsPictureDao upmsPictureDao;


    @Override
    public Integer insert(UpmsPicture upmsPicture) {
        return upmsPictureDao.insert(upmsPicture);
    }
}
