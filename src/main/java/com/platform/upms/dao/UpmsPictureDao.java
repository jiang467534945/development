package com.platform.upms.dao;

import com.platform.common.annotation.MyBatisDao;
import com.platform.common.base.dao.BaseDao;
import com.platform.upms.model.UpmsPicture;

@MyBatisDao
public interface UpmsPictureDao  extends BaseDao<UpmsPicture> {

    /**
     * 保存数据
     * @param upmsPicture
     * @return
     */
    Integer insert(UpmsPicture upmsPicture);

}
