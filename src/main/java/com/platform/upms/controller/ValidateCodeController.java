package com.platform.upms.controller;

import com.platform.common.utils.ValidateCode;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/validateCode")
public class ValidateCodeController {

    /**
     * 响应验证码页面
     * @return
     */
    @RequestMapping(value="/code")
    public void validateCode(HttpServletRequest request, HttpServletResponse response) throws Exception{
        // 设置响应的类型格式为图片格式
        response.setContentType("image/jpeg");
        //禁止图像缓存。
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        HttpSession session = request.getSession();
        session.removeAttribute("code");
        ValidateCode vCode = new ValidateCode(84,40,4,100);
        session.setAttribute("code", vCode.getCode());
        vCode.write(response.getOutputStream());

    }

}
