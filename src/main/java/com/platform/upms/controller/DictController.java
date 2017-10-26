package com.platform.upms.controller;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.platform.common.config.Global;
import com.platform.common.page.Page;
import com.platform.common.utils.StringUtils;
import com.platform.upms.model.Dict;
import com.platform.upms.service.DictService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

public class DictController {

    @Autowired
    private DictService dictService;

    @ModelAttribute
    public Dict get(@RequestParam(required=false) String id) {
        if (StringUtils.isNotBlank(id)){
            return dictService.get(id);
        }else{
            return new Dict();
        }
    }

}
