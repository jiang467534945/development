package com.platform.upms.controller;

import com.platform.common.page.Page;
import com.platform.common.utils.MD5;
import com.platform.upms.model.UpmsUser;
import com.platform.upms.model.json.Tip;
import com.platform.upms.service.UpmsUserService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

@Controller
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UpmsUserService upmsUserService;

    @RequestMapping(value = "/list")
    public String list(Model model, Page<UpmsUser> page) {
        Page<UpmsUser> p = upmsUserService.findByPage(page,null);
        model.addAttribute("page", p);
        return "/user/list.jsp";
    }

    @RequestMapping(value = "/add")
    public String add() {
        return "/user/add.jsp";
    }

    @RequestMapping(value = "/save")
    @ResponseBody
    public Tip save(UpmsUser user) {
        user.setCreateTime(new Date());
        user.setLastLogin(new Date());
        user.setAvailableSpace(user.getTotalSpace());
        user.setPassword(MD5.md5(user.getPassword()));
        Integer save = upmsUserService.save(user);
        if(save>0){
            return new Tip();
        }else {
            return  new Tip(1,"保存失败！！！");
        }
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String edit(@Param(value = "id") String id, Model model) {
        UpmsUser upmsUser = upmsUserService.findById(id);
        model.addAttribute("user", upmsUser);
        return "/user/edit.jsp";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ResponseBody
    public Tip edit(UpmsUser user) {
        Tip tip = new Tip();
        try {
            upmsUserService.edit(user);
        } catch (Exception e) {
            tip = new Tip(1);
        }
        return tip;
    }

    @RequestMapping(value = "/delete")
    @ResponseBody
    public Tip delete(Integer[] ids) {
        Tip tip = new Tip();
        try {
            upmsUserService.deleteByIds(ids);
        } catch(Exception e) {
            tip = new Tip(1);
        }
        return tip;
    }
}
