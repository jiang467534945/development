package com.platform.upms.controller;

import com.platform.upms.model.json.Tip;
import com.platform.upms.service.PositionMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/positionMenu")
public class PositionMenuController {

    @Autowired
    private PositionMenuService upmsPositionMenuService;

    @RequestMapping(value = "/saveMenu")
    @ResponseBody
    public Tip saveMenu(Integer positionId, Integer[] menuIds) {
        Tip tip = new Tip();
        try {
            //删除原有权限
            upmsPositionMenuService.deleteByPositionId(positionId);
            //添加新权限
            upmsPositionMenuService.saveArray(positionId, menuIds);
        } catch (Exception e) {
            tip = new Tip(1);
        }

        return tip;
    }

}
