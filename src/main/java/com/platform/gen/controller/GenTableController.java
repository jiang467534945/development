package com.platform.gen.controller;

import com.platform.common.utils.IdGen;
import com.platform.common.utils.StringUtils;
import com.platform.gen.model.GenTable;
import com.platform.gen.service.GenTableService;
import com.platform.gen.utils.GenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/gen")
public class GenTableController {
    @Autowired
    GenTableService genTableService;
    @RequestMapping(value = {"list"})
    private String list(ModelMap modelMap) {
        List<GenTable> list = genTableService.findAlllist();
        modelMap.put("genList",list);
        return  "/gen/list.jsp";
    }
    @RequestMapping(value = "/add")
    private String add(GenTable genTable, Model model){
        List<GenTable> list = genTableService.findTableListFormDb(new GenTable());
        model.addAttribute("tableList",list);
        // 验证表是否存在
        if (StringUtils.isBlank(String.valueOf(genTable.getId())) && !genTableService.checkTableName(genTable.getName())){
            genTable.setName("");
        }
        // 获取物理表字段
        else{
            genTable = genTableService.getTableFormDb(genTable);
        }
        model.addAttribute("genTable", genTable);
        model.addAttribute("config", GenUtils.getConfig());
        return "/gen/add.jsp";
    }
    @RequestMapping(value = "/twoadd")

    public String twoadd(GenTable genTable, Model model){
        System.out.println(genTable.getName());
        List<GenTable> list = genTableService.findTableListFormDb(new GenTable());
        model.addAttribute("tableList",list);
        genTable = genTableService.getTableFormDb(genTable);
        model.addAttribute("genTable", genTable);
        model.addAttribute("config", GenUtils.getConfig());
        return "/gen/twoadd.jsp";
    }
    @RequestMapping(value = "save",method = RequestMethod.POST)
    @ResponseBody
    public String save(GenTable genTable, Model model) {

        // 验证表是否已经存在
        if (StringUtils.isBlank(genTable.getId()) && !genTableService.checkTableName(genTable.getName())){
            genTable.setName("");
            return "0";
        }
        genTableService.save(genTable);
/*
        addMessage(redirectAttributes, "保存业务表'" + genTable.getName() + "'成功");
*/
        return "1";
    }

}
