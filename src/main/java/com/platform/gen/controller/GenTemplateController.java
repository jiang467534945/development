/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.platform.gen.controller;


import com.platform.common.page.Page;
import com.platform.common.utils.StringUtils;
import com.platform.gen.model.GenTemplate;
import com.platform.gen.service.GenTemplateService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 代码模板Controller
 * @author ThinkGem
 * @version 2013-10-15
 */
@Controller
@RequestMapping(value = "/gen/genTemplate")
public class GenTemplateController  {

	@Autowired
	private GenTemplateService genTemplateService;
	
	@ModelAttribute
	public GenTemplate get(@RequestParam(required=false) String id) {
		if (StringUtils.isNotBlank(id)){
			return genTemplateService.get(id);
		}else{
			return new GenTemplate();
		}
	}
	
	@RequestMapping(value = {"/list", ""})
	public String list(GenTemplate genTemplate, HttpServletRequest request, HttpServletResponse response, Model model) {
		List<GenTemplate> list = genTemplateService.find(genTemplate);
		model.addAttribute("list",list);
		return "/gen/genTemplateList.jsp";
	}

	@RequestMapping(value = "form")
	public String form(GenTemplate genTemplate, Model model) {
		return "/gen/genTemplateAdd.jsp";
	}


	@RequestMapping(value = "save")
	public String save(GenTemplate genTemplate, Model model, RedirectAttributes redirectAttributes) {

		genTemplateService.save(genTemplate);

		return "1";
	}
	
	@RequestMapping(value = "delete")
	public String delete(GenTemplate genTemplate, RedirectAttributes redirectAttributes) {
		genTemplateService.delete(genTemplate);
		return "1";
	}

}
