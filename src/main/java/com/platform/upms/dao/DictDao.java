/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.platform.upms.dao;


import com.platform.common.annotation.MyBatisDao;
import com.platform.common.base.dao.BaseDao;
import com.platform.upms.model.Dict;

import java.util.List;

/**
 * 字典DAO接口
 * @author ThinkGem
 * @version 2014-05-16
 */
@MyBatisDao
public interface DictDao extends BaseDao<Dict> {

	public List<String> findTypeList(Dict dict);
	void save(Dict dict);
	Dict get(String id);
	
}
