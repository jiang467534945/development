package com.platform.upms.controller;

import com.platform.common.config.Global;
import com.platform.common.shiro.ShiroUser;
import com.platform.common.utils.MD5;
import com.platform.common.utils.StringUtils;
import com.platform.upms.model.UpmsUser;
import com.platform.upms.model.json.Tip;
import com.platform.upms.service.UpmsMenuService;
import com.platform.upms.service.UpmsUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


import javax.servlet.http.HttpSession;
import java.util.Date;

@Controller
@RequestMapping("/login")
public class loginController {

    @Autowired
    UpmsMenuService upmsMenuService;

    @Autowired
    UpmsUserService upmsUserService;

    /**
     * 跳转到登录页面
     * @return 登录界面
     */
    @RequestMapping(value="" ,method = RequestMethod.GET)
    public String login(ModelMap modelMap){
        return "/login.jsp";
    }



    /**
     * 登录验证
     * @param  code 用户信息{userName,password}
     * @return success = true 登录成功，
     */
    @ResponseBody
    @RequestMapping(value="/manager",method = RequestMethod.POST)
    public Tip login(HttpSession session,String code,String userName,String password) throws Exception {
        String msg = "登录成功";
        if (StringUtils.isBlank(userName)) {
            msg = "用户名不能为空！";
            return new Tip(3,msg);
        }
        if (StringUtils.isBlank(password)) {
            msg = "密码不能为空！";
            return new Tip(4,msg);
        }
//        if (StringUtils.isBlank(code)) {
//            msg = "验证码不能为空！";
//            return new Tip(5,msg);
//        }
//        String sessionCode = (String) session.getAttribute("code");
//        if (!StringUtils.equalsIgnoreCase(code,sessionCode)) {  //忽略验证码大小写
//            msg = "验证码有误！";
//            return  new Tip(4,msg);
//        }
        //获取当前会话主体
        Subject subject = SecurityUtils.getSubject();

        //获取token
        UsernamePasswordToken token = new UsernamePasswordToken(userName,MD5.md5(password));

        //是否记住密码
        //token.setRememberMe(true);

        try {
            //登录
            subject.login(token);

            //如果登录成功
            if (subject.isAuthenticated()) {
                ShiroUser shiroUser = (ShiroUser) subject.getPrincipal();
                UpmsUser dba = shiroUser.getUpmsUser();
                session.setAttribute(Global.UPMSUSER,dba);
                session.removeAttribute("code");
                UpmsUser upmsUser= (UpmsUser) session.getAttribute(Global.UPMSUSER);
                upmsUser.setLastLogin(new Date());
                Integer update = upmsUserService.update(upmsUser);


                // 检查分类并获取详细信息
//                checkType(session,dba);

                //检查权限并缓存
//                checkPower(session,dba.getId());

                //保存成功日志
                /*logService.loginLog(account,1,request.getRemoteAddr());*/

                return new Tip(msg);
            }
        } catch (IncorrectCredentialsException e) {
            msg = "登录密码错误！";
            return new Tip(11,msg);
        } catch (ExcessiveAttemptsException e) {
            msg = "登录失败次数过多！";
            return new Tip(12,msg);
        } catch (LockedAccountException e) {
            msg = "帐号已被锁定！";
            return new Tip(13,msg);
        } catch (DisabledAccountException e) {
            msg = "帐号已被禁用！";
            return new Tip(14,msg);
        } catch (ExpiredCredentialsException e) {
            msg = "帐号已过期！";
            return new Tip(15,msg);
        } catch (UnknownAccountException e) {
            msg = "帐号不存在！";
            return new Tip(16,msg);
        } catch (UnauthorizedException e) {
            msg = "您没有得到相应的授权！";
            return new Tip(17,msg);
        }

        return new Tip(1);
    }


    /**
     * 注销登录
     * @return sussess 成功 ，code = 1 失败
     */
    @RequestMapping(value="/logout")
    public String logout(HttpSession session){
        try {
            session.removeAttribute(Global.UPMSUSER);
            Subject subject = SecurityUtils.getSubject();
            subject.logout();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/login";
    }





}
