package com.platform.upms.service;

import com.platform.common.base.service.BaseService;
import com.platform.upms.model.UpmsPicture;

public interface UpmsPictureService extends BaseService<UpmsPicture> {

    /**
     * 保存数据
     * @param upmsPicture
     * @return
     */
    Integer insert(UpmsPicture upmsPicture);

}
