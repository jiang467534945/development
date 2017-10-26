package com.platform.upms.controller;

import com.platform.common.page.Page;
import com.platform.upms.model.UpmsUser;
import com.platform.upms.model.ZNode;
import com.platform.upms.model.json.Tip;
import com.platform.upms.service.PositionUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(value = "/positionUser")
public class PositionUserController {

    @Autowired
    private PositionUserService positionUserService;

    @RequestMapping(value = "/userList")
    public String userList(Integer id, Page<UpmsUser> page, Model model) {
        Page<UpmsUser> p = positionUserService.listUserByPage(page,new UpmsUser());
        model.addAttribute("page", p);
        return "/position/user-list.jsp";
    }

    @RequestMapping(value = "/save")
    @ResponseBody
    public Tip save(Integer positionId, Integer[] userIds) {
        Tip tip = new Tip();
        //清空此职务下所有用户
        positionUserService.deleteByPositionId(positionId);
        //为此职务添加新用户
        positionUserService.saveArray(positionId, userIds);
        return tip;
    }

    @RequestMapping(value = "/positionList", method = RequestMethod.GET)
    public String positionList(Integer id, Model model) {
        model.addAttribute("id", id);
        return "/user/position-list.jsp";
    }

    @RequestMapping(value = "/positionList", method = RequestMethod.POST)
    @ResponseBody
    public List<ZNode> positionList(Integer id) {
        List<ZNode> list = positionUserService.positionList(id);
        return list;
    }

    @RequestMapping(value = "/savePosition")
    @ResponseBody
    public Tip savePosition(Integer userId, String[] positionIds) {
        Tip tip = new Tip();
        Integer[] integers = new Integer[positionIds.length];
        for(int i=0;i<positionIds.length;i++) {
            integers[i] = Integer.parseInt(positionIds[i].replaceAll("position", ""));
        }
        try {
            positionUserService.deteleteByUserId(userId);
            positionUserService.saveArrayOpposite(userId, integers);
        } catch (Exception e) {
            tip = new Tip(1);
        }
        return tip;
    }

}
