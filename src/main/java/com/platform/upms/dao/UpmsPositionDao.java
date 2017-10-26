package com.platform.upms.dao;

import com.platform.common.annotation.MyBatisDao;
import com.platform.common.base.dao.BaseDao;
import com.platform.common.page.Page;
import com.platform.upms.model.UpmsPosition;
import com.platform.upms.model.ZNode;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by yanzengbao on 2017/10/10.
 */
@MyBatisDao
public interface UpmsPositionDao extends BaseDao<UpmsPosition> {

    /**
     * 查询所有角色列表
     * @return
     */
    List<UpmsPosition> findAllList();

    /**
     * 保存
     * @param upmsPosition 实体对象
     * @return 受影响行数
     * @throws Exception 保存异常
     */
    Integer save(UpmsPosition upmsPosition) throws Exception;

    /**
     * 分页查询
     * @param page
     * @param
     * @return
     */
    List<UpmsPosition> list(Page<UpmsPosition> page, @Param(value = "upmsPosition")UpmsPosition upmsPosition);

    /**
     * 单条删除/批量删除
     * @param ids 角色id
     */
    void deleteByIds(String[] ids);

    /**
     * 根据id 查询
     * @param upmsPosition 实体对象
     * @return 实体
     */
    UpmsPosition selectById(UpmsPosition upmsPosition);

    /**
     * 根据id 修改数据
     * @param upmsPosition
     * @return
     * @throws Exception
     */
    Integer updateById(UpmsPosition upmsPosition) throws Exception;

    List<UpmsPosition> findByPage(Page<UpmsPosition> page, UpmsPosition upmsPosition);

    List<ZNode> menuList(UpmsPosition upmsPosition);
}
