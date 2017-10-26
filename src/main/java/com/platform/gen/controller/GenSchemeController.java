/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.platform.gen.controller;


import com.platform.common.utils.StringUtils;
import com.platform.gen.model.GenScheme;
import com.platform.gen.service.GenTableService;
import com.platform.gen.service.impl.GenSchemeServiceImpl;
import com.platform.gen.utils.GenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 生成方案Controller
 * @author ThinkGem
 * @version 2013-10-15
 */
@Controller
@RequestMapping(value = "/gen/genScheme")
public class GenSchemeController {

	@Autowired
	private GenSchemeServiceImpl genSchemeService;
	
	@Autowired
	private GenTableService genTableService;
	
	@ModelAttribute
	public GenScheme get(@RequestParam(required=false) String id) {
		if (StringUtils.isNotBlank(id)){
			return genSchemeService.get(id);
		}else{
			return new GenScheme();
		}
	}
	
	@RequestMapping(value = {"list", ""})
	public String list(GenScheme genScheme, HttpServletRequest request, HttpServletResponse response, Model model) {

       List<GenScheme> list = genSchemeService.find(genScheme);
        model.addAttribute("list", list);
		
		return "/gen/genSchemeList.jsp";
	}
	@RequestMapping(value = "/add")
	public String form(GenScheme genScheme, Model model) {
		if (StringUtils.isBlank(genScheme.getPackageName())){
			genScheme.setPackageName("com.platform");
		}
//		if (StringUtils.isBlank(genScheme.getFunctionAuthor())){
//			genScheme.setFunctionAuthor(UserUtils.getUser().getName());
//		}
		model.addAttribute("genScheme", genScheme);
		model.addAttribute("config", GenUtils.getConfig());
		model.addAttribute("tableList", genTableService.findAll());
		return "/gen/genSchemeAdd.jsp";
	}

	@RequestMapping(value = "/save",method = RequestMethod.POST)
	@ResponseBody
	public String save(GenScheme genScheme, Model model, RedirectAttributes redirectAttributes) {

		String result = genSchemeService.save(genScheme);
		return "1";
	}
	
	@RequestMapping(value = "/delete")
	public String delete(GenScheme genScheme, RedirectAttributes redirectAttributes) {
		genSchemeService.delete(genScheme);
		return "1";
	}

}
