package com.platform.gen.service;

import com.platform.gen.model.GenTable;

import java.util.List;

public interface GenTableService {
    List<GenTable> findAlllist();
    List<GenTable> findTableListFormDb(GenTable genTable);
    boolean   checkTableName(String genTable);
    GenTable getTableFormDb(GenTable genTable);
    void save(GenTable genTable);
    List<GenTable> findAll();

}
