package com.platform.upms.controller;

import com.platform.upms.model.UpmsMenu;
import com.platform.upms.service.UpmsMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class IndexController {

    @Autowired
    UpmsMenuService upmsMenuService;

    @RequestMapping(value="/" ,method = RequestMethod.GET)
    public String index(ModelMap modelMap){
        UpmsMenu upmsMenu = new UpmsMenu();
        upmsMenu.setpId(1);
        List<UpmsMenu> list = upmsMenuService.list(upmsMenu);
        //1.8 a为泛型中的对象,1.8
        list.forEach(a ->{
            upmsMenu.setpId(a.getId());
            a.setLevelList(upmsMenuService.list(upmsMenu));
        });
        modelMap.put("menuList",list);
        return "/index.jsp";
    }
    @RequestMapping(value="/home" ,method = RequestMethod.GET)
    public String home(ModelMap modelMap) {
            return "/body/home.jsp";
    }
}
