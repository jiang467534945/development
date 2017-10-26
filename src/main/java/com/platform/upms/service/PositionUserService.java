package com.platform.upms.service;

import com.platform.common.base.service.BaseService;
import com.platform.common.page.Page;
import com.platform.upms.model.PositionUser;
import com.platform.upms.model.UpmsUser;
import com.platform.upms.model.ZNode;

import java.util.List;

public interface PositionUserService extends BaseService<PositionUser> {

    Page<UpmsUser> listUserByPage(Page<UpmsUser> page, UpmsUser user);

    Integer deleteByPositionId(Integer positionId);

    Integer saveArray(Integer positionId, Integer[] userIds);

    List<ZNode> positionList(Integer id);

    Integer deteleteByUserId(Integer userId);

    Integer saveArrayOpposite(Integer userId, Integer[] integers);
}
