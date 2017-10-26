package com.platform.upms.controller;

import com.platform.common.page.Page;
import com.platform.common.utils.UUIDFactory;
import com.platform.upms.model.FamilyType;
import com.platform.upms.model.json.Tip;
import com.platform.upms.service.FamilyTypeService;
import com.platform.upms.service.impl.FamilyTypeServiceImpl;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/familyType")
public class FamilyTypeController {

    @Autowired
    private FamilyTypeService familyTypeService;

    /**
     * 列表页面带分页
     * @param modelMap
     * @param page
     * @return
     */
    @RequestMapping("/list")
    public String list(ModelMap modelMap, Page<FamilyType> page){
        Page<FamilyType> pageList = familyTypeService.findByPage(page,new FamilyType());
        modelMap.put("pageList",pageList);
        return "/familyType/list.jsp";
    }

    /**
     * 添加页面
     * @return
     */
    @RequestMapping("/add")
    public String add(){return  "/familyType/add.jsp";}

    /**
     * 保存设备的类型
     * @param familyType
     * @return
     */
    @RequestMapping("/insert")
    @ResponseBody
    public Tip insert(FamilyType familyType){
        familyType.setId(UUIDFactory.getStringId());
        Integer add = familyTypeService.add(familyType);
        if(add>0){
            return new Tip();
        }else{
            return  new Tip(1,"数据异常，保存失败");
        }
    }

    /**
     * 数据的删除
     * @param ids
     * @return
     */
    @RequestMapping("/deletes")
    @ResponseBody
    public Tip deletes(@Param(value = "ids") String[] ids){
        Integer deletes = familyTypeService.deletes(ids);
        if(deletes>0){
            return  new Tip();
        }else{
            return  new Tip(1,"删除失败！！！");
        }
    }

    /**
     * 编辑页面
     * @param id
     * @return
     */
    @RequestMapping("/edit")
    public String edit(@Param(value = "id")String id,ModelMap modelMap){
        FamilyType familyType = familyTypeService.selectById(id);
        modelMap.addAttribute("familyType",familyType);
        return "/familyType/edit.jsp";
    }

    /**
     * 修改设备类型
     * @param familyType
     * @return
     */
    @RequestMapping("/update")
    @ResponseBody
    public Tip update(FamilyType familyType){
        Integer update = familyTypeService.update(familyType);
        if(update>0){
            return  new Tip();
        }else {
            return  new Tip(1,"修改失败");
        }
    }

}
