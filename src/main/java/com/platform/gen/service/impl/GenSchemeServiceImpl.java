/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.platform.gen.service.impl;


import com.platform.common.utils.IdGen;
import com.platform.common.utils.StringUtils;
import com.platform.gen.dao.GenSchemeDao;
import com.platform.gen.dao.GenTableColumnDao;
import com.platform.gen.dao.GenTableDao;
import com.platform.gen.model.*;
import com.platform.gen.service.GenSchemeService;
import com.platform.gen.service.GenTemplateService;
import com.platform.gen.utils.GenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * 生成方案Service
 * @author ThinkGem
 * @version 2013-10-15
 */
@Service
public class GenSchemeServiceImpl implements GenSchemeService {

	@Autowired
	private GenSchemeDao genSchemeDao;
//	@Autowired
//	private GenTemplateDao genTemplateDao;
	@Autowired
	private GenTableDao genTableDao;
	@Autowired
	private GenTableColumnDao genTableColumnDao;
	
	public GenScheme get(String id) {
		return genSchemeDao.get(id);
	}
	
	public List<GenScheme> find(GenScheme genScheme) {
		GenUtils.getTemplatePath();
		return genSchemeDao.findList(genScheme);

	}
	public String save(GenScheme genScheme) {
		if (StringUtils.isBlank(genScheme.getId())){
			genScheme.setId(IdGen.uuid());
			genSchemeDao.saveData(genScheme);
		}else{
			genSchemeDao.update(genScheme);
		}
		// 生成代码
		if ("1".equals(genScheme.getFlag())){
			return generateCode(genScheme);
		}
		return "";
	}
	
	public void delete(GenScheme genScheme) {
		genSchemeDao.deleteData(genScheme);
	}
	
	public String generateCode(GenScheme genScheme){

		StringBuilder result = new StringBuilder();
		
		// 查询主表及字段列
		GenTable genTable = genTableDao.get(genScheme.getGenTable().getId());
		GenTable genTable1 = new GenTable();
		genTable1.setId(genTable.getId());
		genTable.setColumnList(genTableColumnDao.findList(new GenTableColumn(genTable1)));
		
		// 获取所有代码模板
		GenConfig config = GenUtils.getConfig();
		
		// 获取模板列表
		List<GenTemplate> templateList = GenUtils.getTemplateList(config, genScheme.getCategory(), false);
		List<GenTemplate> childTableTemplateList = GenUtils.getTemplateList(config, genScheme.getCategory(), true);
		
		// 如果有子表模板，则需要获取子表列表
		if (childTableTemplateList.size() > 0){
			GenTable parentTable = new GenTable();
			parentTable.setParentTable(genTable.getName());
			genTable.setChildList(genTableDao.findList(parentTable));
		}
		
		// 生成子表模板代码
		for (GenTable childTable : genTable.getChildList()){
			childTable.setParent(genTable);
			GenTable genTable2 = new GenTable();
			genTable2.setId(childTable.getId());
			childTable.setColumnList(genTableColumnDao.findList(new GenTableColumn(genTable2)));
			genScheme.setGenTable(childTable);
			Map<String, Object> childTableModel = GenUtils.getDataModel(genScheme);
			for (GenTemplate tpl : childTableTemplateList){
				result.append(GenUtils.generateToFile(tpl, childTableModel, genScheme.getReplaceFile()));
			}
		}
		
		// 生成主表模板代码
		genScheme.setGenTable(genTable);
		Map<String, Object> model = GenUtils.getDataModel(genScheme);
		for (GenTemplate tpl : templateList){
			result.append(GenUtils.generateToFile(tpl, model, true));
		}
		return result.toString();
	}

}
