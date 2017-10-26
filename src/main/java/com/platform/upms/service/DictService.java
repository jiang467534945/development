package com.platform.upms.service;

import com.platform.common.base.service.BaseService;
import com.platform.upms.model.Dict;

import java.util.List;

public interface DictService extends BaseService<Dict> {
    List<String> findTypeList();

}

