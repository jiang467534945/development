package com.platform.upms.controller;

import com.platform.upms.model.UpmsMenu;
import com.platform.upms.model.UpmsOrg;
import com.platform.upms.model.UpmsOrgMenu;
import com.platform.upms.model.ZNode;
import com.platform.upms.service.UpmsMenuService;
import com.platform.upms.service.UpmsOrgMenuService;
import com.platform.upms.service.UpmsOrgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 标签Controller
 * @author Easy
 * @version 2017-09-28
 */
@Controller
@RequestMapping(value="/tag")
public class TagController {
    /**
     * 树结构选择标签（treeselect.tag）
     */
    @Autowired
    UpmsMenuService upmsMenuService;

    @Autowired
    UpmsOrgService upmsOrgService;

    @Autowired
    UpmsOrgMenuService upmsOrgMenuService;

    @RequestMapping(value = "/treeselect")
    public String treeselect(HttpServletRequest request, ModelMap modelMap) {
        UpmsMenu upmsMenu = new UpmsMenu();
        upmsMenu.setpId(1);
        List<UpmsMenu> list = upmsMenuService.list(upmsMenu);
        //1.8 a为泛型中的对象,1.8
        list.forEach(a ->{
            upmsMenu.setpId(a.getId());
            a.setLevelList(upmsMenuService.list(upmsMenu));
        });
        modelMap.put("menuList",list);
        return "/menu/tagTreeselect.jsp";
    }

    @RequestMapping(value = "/checktreeselect")
    public String checktreeselect(HttpServletRequest request, Model model, int id) {
        model.addAttribute("id",id);
        return "/menu/tagCheckTreeselect.jsp";
    }

    @RequestMapping(value = "/treeData")
    @ResponseBody
    public List<ZNode> treeData(Integer id) {
        UpmsOrg upmsOrg = upmsOrgService.get(id);
        List<ZNode> list;
        if(upmsOrg.getpId() != 0){
            UpmsOrgMenu upmsOrgMenu = new UpmsOrgMenu();
            upmsOrgMenu.setChildId(upmsOrg.getId());
            upmsOrgMenu.setOrgId(upmsOrg.getpId());
            list = upmsOrgMenuService.findZnodeList(upmsOrgMenu);
            if(list.size() > 0) {
                list.remove(0);
            }
        } else {
            list = upmsMenuService.findZnodeList(upmsOrg);
        }

        return list;
    }

}
