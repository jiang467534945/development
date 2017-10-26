package com.platform.upms.dao;

import com.platform.common.annotation.MyBatisDao;
import com.platform.common.page.Page;
import com.platform.upms.model.UpmsUser;
import com.platform.upms.model.ZNode;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@MyBatisDao
public interface PositionUserDao {

    List<UpmsUser> listUserByPage(Page<UpmsUser> page, @Param(value = "user") UpmsUser user);

    Integer deleteByPositionId(Integer positionId);

    Integer saveArray(@Param(value = "positionId")Integer positionId, @Param(value = "userIds")Integer[] userIds);

    List<ZNode> positionList(Integer id);

    Integer deteleteByUserId(Integer userId);

    Integer saveArrayOpposite(@Param(value = "userId")Integer userId, @Param(value = "integers")Integer[] integers);
}
