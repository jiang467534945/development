package com.platform.common.base.service;

import com.platform.common.page.Page;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface BaseService<T> {

    /**
     * 获取单条数据
     * @param id
     * @return
     */
    T get(Integer id);
    /**
     * 获取单条数据
     * @param id
     * @return
     */
    T get(String id);
    /**
     * 获取单条数据
     * @param entity
     * @return
     */
    T get(T entity);

    /**
     * 查询列表数据
     * @param entity
     * @return
     */
    List<T> findList(T entity);
    /**
     * 查询列表数据
     * @param
     * @return
     */
    List<T> findAllList();
    /**
     * 保存数据（插入或更新）
     * @param entity
     */
    @Transactional(readOnly = false)
    void saveData(T entity);

    /**
     * 删除数据
     * @param entity
     */
    @Transactional(readOnly = false)
    void deleteDataById(T entity);

    /**
     * 分页查询
     * @param page
     * @param t
     * @return
     */
    Page<T> findByPage(Page<T> page, T t);

    /**
     * 批量删除数据
     * @param ids
     */
    Integer deleteDataByIds(String[] ids);

    /**
     * 修改数据
     * @param t
     * @return
     */
    Integer update(T t);

}
