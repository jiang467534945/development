package com.platform.upms.controller;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.platform.common.page.Page;
import com.platform.upms.model.*;
import com.platform.upms.model.json.Tip;
import com.platform.upms.service.UpmsOrgService;
import com.platform.upms.service.UpmsPositionService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/position")
public class UpmsPositionController {

    @Autowired
    private UpmsPositionService upmsPositionService;
    @Autowired
    private UpmsOrgService upmsOrgService;

    /**
     * 角色管理列表页
     * @param modelMap
     * @return list 页面
     */
    @RequestMapping(value = "/list")
    public String list(ModelMap modelMap,Page<UpmsPosition> page) {
        Page<UpmsPosition> pageList = upmsPositionService.findByPage(page,null);
        modelMap.addAttribute("pageList", pageList);
        return "/position/list.jsp";
    }

    /**
     * 角色管理添加页面
     * @return 页面
     */
    @RequestMapping(value = "/add")
    public String add() {
        return "/position/add.jsp";
    }

    /**
     * 角色保存
     * @param upmsPosition 实体对象
     * @return 提示信息
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public Tip save(UpmsPosition upmsPosition) {
        try {
            upmsPosition.setCreateTime(new Date());
            upmsPositionService.save(upmsPosition);
        }catch (Exception e){
            e.printStackTrace();
            return new Tip(1,"保存异常！");
        }
        return new Tip();
    }

    /**
     * 角色修改页面
     * @param modelMap
     * @param upmsPosition
     * @return
     */
    @RequestMapping(value = "/edit")
    public String edit(ModelMap modelMap,UpmsPosition upmsPosition){

        UpmsPosition position = upmsPositionService.selectById(upmsPosition);
        modelMap.addAttribute("data",position);
        return "/position/edit.jsp";
    }

    @RequestMapping(value = "/update")
    @ResponseBody
    public Tip update(UpmsPosition upmsPosition){
        try {
            System.out.println(upmsPosition.getId());
            int flag = upmsPositionService.updateById(upmsPosition);
            if(flag > 0){
                return new Tip("修改成功！");
            }else {
                return new Tip(1,"修改失败！");
            }
        }catch (Exception e){
            e.printStackTrace();
            return new Tip(1,"数据修改异常！");
        }
    }

    /**
     * 角色删除
     * @param ids 角色id
     * @return 提示信息
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Tip delete(@Param(value = "ids") String[] ids) {
        try {
            upmsPositionService.deleteByIds(ids);
        } catch(Exception e) {
            return new Tip(1,"数据异常！");
        }
        return new Tip("删除成功！");
    }

    /**
     * 职务授权页面
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "/menuList")
    public String menuList(Integer id, Model model) {
//        UpmsPosition upmsPosition = upmsPositionService.get(id);
//        if(upmsPosition != null) {
//
//        }
        model.addAttribute("id", id);
        return "/position/menu-list.jsp";
    }

    @RequestMapping(value = "/listMenu")
    @ResponseBody
    public List<ZNode> listMenu(Integer id) {
        List<ZNode> list;
        UpmsPosition upmsPosition = upmsPositionService.get(id);
        if(upmsPosition != null) {
            list = upmsPositionService.menuList(upmsPosition);
            if(list.size() > 0) {
                list.remove(0);
            }
        } else {
            list = null;
        }
        return list;
    }

}
