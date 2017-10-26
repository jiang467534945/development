package com.platform.upms.controller;

import com.platform.upms.model.json.Tip;
import com.platform.upms.service.UpmsOrgMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value="/orgMenu")
public class UpmsOrgMenuController {
    @Autowired
    UpmsOrgMenuService upmsOrgMenuService;
    /**
     * 添加组织
     * @param
     * @return
     */
    @RequestMapping(value = "/authSave")
    @ResponseBody
    public Tip insert(Integer orgId,Integer[] menuId){
        Tip tip;
        try {
            //删除原有权限
            upmsOrgMenuService.deleteByOrgId(orgId);
            //添加新权限
            upmsOrgMenuService.saveArray(orgId, menuId);
            tip = new Tip("保存成功");
        } catch (Exception e){
            e.printStackTrace();
            tip = new Tip(1,"保存失败");
        }
        return tip;
    }
}
