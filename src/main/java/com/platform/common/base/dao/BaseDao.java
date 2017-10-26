package com.platform.common.base.dao;

import com.platform.common.annotation.MyBatisDao;
import com.platform.common.page.Page;
import org.apache.ibatis.annotations.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@MyBatisDao
public interface BaseDao<T> {

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
     * 保存数据（插入或更新）
     * @param entity
     */
    @Transactional(readOnly = false)
    Integer saveData(T entity);

    /**
     * 删除数据
     * @param id
     */
    @Transactional(readOnly = false)
    Integer deleteDataById(int id);
    /**
     * 删除数据
     * @param entity
     */
    @Transactional(readOnly = false)
    Integer deleteData(T entity);

    /**
     * 分页查询
     * @param page
     * @param t
     * @return
     */
    List<T> findByPage(Page<T> page, @Param(value = "param") T t);


    /**
     * 查询所有数据列表
     * @param entity
     * @return
     */
    List<T> findAllList(T entity);
    /**
     * 查询所有数据列表
     * @param
     * @return
     */
    List<T> findAllList();
    /**
     * 更新数据
     * @param entity
     * @return
     */
    Integer update(T entity);

    /**
     * 批量删除数据
     * @param ids
     */
    Integer deleteDataByIds(String[] ids);
}
