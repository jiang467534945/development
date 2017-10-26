package com.platform.upms.service;

import com.platform.common.base.service.BaseService;
import com.platform.common.page.Page;
import com.platform.upms.model.UpmsPosition;
import com.platform.upms.model.ZNode;

import java.util.List;

/**
 * Created by yanzengbao on 2017/10/10.
 */
public interface UpmsPositionService extends BaseService<UpmsPosition> {

    /**
     * 查询所有角色列表
     * @return list
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
     * @return
     */
    Page<UpmsPosition> list(Page<UpmsPosition> page);

    /**
     * 单条删除/批量删除
     * @param ids
     */
    void deleteByIds(String[] ids);

    /**
     * 根据id查询
     * @param upmsPosition 实体对象 携带id
     * @return 实体对象
     */
    UpmsPosition selectById(UpmsPosition upmsPosition);

    /**
     * 根据id修改数据
     * @param upmsPosition
     * @return
     * @throws Exception
     */
    Integer updateById(UpmsPosition upmsPosition) throws Exception;

    Page<UpmsPosition> findByPage(Page<UpmsPosition> page, UpmsPosition upmsPosition);

    List<ZNode> menuList(UpmsPosition upmsPosition);
}
