package com.platform.upms.controller;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.platform.common.page.Page;
import com.platform.upms.model.UpmsOrg;
import com.platform.upms.model.ZNode;
import com.platform.upms.model.json.Tip;
import com.platform.upms.service.UpmsMenuService;
import com.platform.upms.service.UpmsOrgMenuService;
import com.platform.upms.service.UpmsOrgService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
@RequestMapping("/org")
public class UpmsOrgController {
    private static Logger logger = LoggerFactory.getLogger(UpmsOrgController.class);

    @Autowired
    private UpmsOrgService upmsOrgService;
    @Autowired
    private UpmsMenuService upmsMenuService;
    @Autowired
    private UpmsOrgMenuService upmsOrgMenuService;
     /**
     * 组织列表
     * @param modelMap
     * @return
     */
    @RequestMapping(value = "/list")
    public String list(ModelMap modelMap,Page page) {
        Page<UpmsOrg> upmsOrgs=upmsOrgService.findByPage(page,new UpmsOrg());
        modelMap.addAttribute("page", upmsOrgs);
        return "/org/list.jsp";
    }

    /**
     * 组织列表的添加页面
     * @return
     */
    @RequestMapping(value = "/add",method = RequestMethod.GET)
    public String add(){
        return "/org/add.jsp";
    }

    /**
     * 组织列表的授权页面
     * @return
     *//*
    @RequestMapping(value = "/toAuth",method = RequestMethod.GET)
    public   String  toAuth(Model model){
        UpmsMenu menu= new UpmsMenu();
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
        return  "/org/toAuth.jsp";
    }*/
    /**
     * 添加组织
     * @param upmsOrg
     * @return
     */
    @RequestMapping(value = "/save")
    @ResponseBody
    public Tip insert(UpmsOrg upmsOrg){
        try {
            upmsOrg.setCreateTime(new Date());
            upmsOrgService.saveData(upmsOrg);
            return  new Tip("保存成功");

        } catch (Exception e){
            return  new Tip(1,"保存失败");
        }
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String edit(Integer id, Model model) {
        UpmsOrg upmsOrg = upmsOrgService.get(id);
        model.addAttribute("org", upmsOrg);
        return "/org/edit.jsp";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ResponseBody
    public Tip edit(UpmsOrg upmsOrg) {
        Tip tip = new Tip();
        upmsOrgService.update(upmsOrg);
        return tip;
    }

    @ResponseBody
    @RequestMapping(value = "treeData")
    public List<Map<String, Object>> treeData(@RequestParam(required=false) Integer extId, @RequestParam(required=false) String isShowHide, HttpServletResponse response) {
        List<Map<String, Object>> mapList = Lists.newArrayList();
        List<UpmsOrg> list = upmsOrgService.selectAllList(null);
        for (int i=0; i<list.size(); i++){
            UpmsOrg upmsOrg=list.get(i);
            if (extId == null || (extId!=null && !extId.equals(upmsOrg.getId()))){
                Map<String, Object> map = Maps.newHashMap();
                map.put("id", upmsOrg.getId());
                map.put("pId", upmsOrg.getpId());
                map.put("name", upmsOrg.getOrgName());
                mapList.add(map);
            }
        }
        return mapList;
    }

    /**
     * 加载上级组织页面
     * @return
     */
    @RequestMapping(value = "/preOrg")
    public String preOrg() {
        return "/org/pre-org.jsp";
    }

    /**
     * 查询所有组织
     * @return
     */
    @RequestMapping(value = "/getOrgList")
    @ResponseBody
    public List<ZNode> getOrgList() {
        List<ZNode> list = upmsOrgService.listOrg();
        return list;
    }

    @RequestMapping(value = "/delete")
    @ResponseBody
    public Tip delete(String[] ids) {
        Tip tip = new Tip();
        try {
            upmsOrgService.deleteDataByIds(ids);
        } catch(Exception e) {
            tip = new Tip(1);
        }
        return tip;
    }

}
