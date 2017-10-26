package com.platform.upms.controller;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.platform.common.page.Page;
import com.platform.common.redis.RedisUtil;
import com.platform.common.utils.StringUtils;
import com.platform.upms.model.UpmsMenu;
import com.platform.upms.model.json.Tip;
import com.platform.upms.service.UpmsMenuService;
import org.apache.ibatis.annotations.Param;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

import javax.servlet.http.HttpServletResponse;
import java.util.*;

import static java.awt.SystemColor.menu;

@Controller
@RequestMapping("/menu")
public class UpmsMenuController {

    @Autowired
    UpmsMenuService upmsMenuService;

    /**
     * 带分页的查询列表
     * @param modelMap
     * @param page
     * @return
     */
    @RequestMapping(value = "/list")
    @RequiresPermissions(value = "menu:list")
    public String list(ModelMap modelMap,Page<UpmsMenu> page)throws  Exception {
            Page<UpmsMenu> pageList = upmsMenuService.findByPageRedis(page,null);
            modelMap.put("menuList",pageList);
            return "/menu/list.jsp";
    }


    /**
     * 菜单的添加页面
     * @param menu
     * @param model
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.GET)

    public String add(UpmsMenu menu, Model model) {

        menu.setParent(upmsMenuService.getMenu());
        if (menu.getId()==null) {
            List<UpmsMenu> list = Lists.newArrayList();
            List<UpmsMenu> sourcelist = upmsMenuService.findAllList();
            UpmsMenu.sortList(list, sourcelist, menu.getpId(), false);
            if (list.size() > 0) {
                menu.setSort(list.get(list.size() - 1).getSort() + 30);
            }
        }
        model.addAttribute("menu", menu);
        return "/menu/add.jsp";
    }


    /**
     * 菜单的编辑页面
     * @param
     * @param model
     * @return
     */
    @RequestMapping(value = "/edit")
    public String edit(@Param(value = "id") String id, Model model) {
        UpmsMenu upmsMenu = upmsMenuService.get(id);
        model.addAttribute("upmsMenu", upmsMenu);
        return "/menu/edit.jsp";
    }

    @RequestMapping(value = "/update")
    @ResponseBody
    public Tip update(UpmsMenu upmsMenu){
        try{
            upmsMenuService.update(upmsMenu);
            return  new Tip();
        }catch (Exception e){
            e.printStackTrace();
            return  new Tip(1,"修改失败！！！");
        }


    }

    @ResponseBody
    @RequestMapping(value = "/treeData")
    public List<Map<String, Object>> treeData(@RequestParam(required=false) Integer extId, @RequestParam(required=false) String isShowHide, HttpServletResponse response) {
        List<Map<String, Object>> mapList = Lists.newArrayList();
        List<UpmsMenu> list = upmsMenuService.findAllList();
        for (int i=0; i<list.size(); i++){
            UpmsMenu e = list.get(i);
            if (extId == null || (extId!=null && !extId.equals(e.getId()))){

                Map<String, Object> map = Maps.newHashMap();
                map.put("id", e.getId());
                map.put("pId", e.getParentId());
                map.put("name", e.getMenuName());
                mapList.add(map);
            }
        }
        return mapList;
    }

    /**
     * 保存数据
     * @param upmsMenu
     * @param model
     * @return
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public Tip save(UpmsMenu upmsMenu, Model model) {
        try {
           upmsMenuService.saveData(upmsMenu);
           return new Tip("保存成功！");
        }catch (Exception e){
            e.printStackTrace();
            return new Tip(1,"数据异常，保存失败！");
        }
    }

    /**
     * 刪除
     * @param ids
     * @return
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Tip delete(@Param(value = "ids") String[] ids){
        try{
             upmsMenuService.deleteDataByIds(ids);
             return  new Tip();
        }catch (Exception e){
            e.printStackTrace();
            return  new Tip(1,"删除失败");
        }
    }
}
