package com.platform.common.base.impl;

import com.platform.common.page.Page;
import com.platform.common.base.dao.BaseDao;
import com.platform.common.base.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class BaseServiceImpl<T> implements BaseService<T> {

    @Autowired
    protected BaseDao<T> dao;

    /**
     * 获取单条数据
     * @param id
     * @return
     */
    public T get(Integer id) {
        return dao.get(id);
    }

    /**
     * 获取单条数据
     * @param entity
     * @return
     */
    public T get(T entity) {
        return dao.get(entity);
    }

    /**
     * 查询列表数据
     * @param entity
     * @return
     */
    public List<T> findList(T entity) {
        return dao.findList(entity);
    }

    /**
     * 保存数据（插入或更新）
     * @param entity
     */
    @Transactional(readOnly = false)
    public void saveData(T entity) {
        dao.saveData(entity);

    }

    /**
     * 删除数据
     * @param entity
     */
    @Transactional(readOnly = false)
    public void deleteDataById(T entity) {
        dao.deleteData(entity);
    }

    /**
     * 分页查询
     * @param page
     * @param t
     * @return
     */
    @Override
    public Page<T> findByPage(Page<T> page, T t) {
        page.setResults(dao.findByPage(page, t));
        return page;
    }

    @Override
    public List<T> findAllList() {
        return dao.findAllList();
    }

    @Override
    public T get(String id) {
        return dao.get(id);
    }

    /**
     * 批量删除数据
     * @param ids
     */
    @Override
    public Integer deleteDataByIds(String[] ids) {
        return dao.deleteDataByIds(ids);
    }

    /**
     * 修改数据
     * @param t
     * @return
     */
    @Override
    public Integer update(T t) {
        return dao.update(t);
    }
}
