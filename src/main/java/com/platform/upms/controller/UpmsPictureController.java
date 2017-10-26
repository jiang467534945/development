package com.platform.upms.controller;

import com.platform.common.config.Global;
import com.platform.common.page.Page;
import com.platform.common.utils.UploadImageToString;
import com.platform.upms.model.UpmsPicture;
import com.platform.upms.model.UpmsUser;
import com.platform.upms.model.json.Tip;
import com.platform.upms.service.UpmsPictureService;
import com.platform.upms.service.UpmsUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;

@Controller
@RequestMapping("/picture")
public class UpmsPictureController {

    @Autowired
    private UpmsPictureService upmsPictureService;

    @Autowired
    private UpmsUserService upmsUserService;

    @RequestMapping("/list")
    public  String list(Model model, Page page, HttpSession session){
        UpmsUser upmsUser= (UpmsUser) session.getAttribute(Global.UPMSUSER);
        Page<UpmsPicture> p = upmsPictureService.findByPage(page,new UpmsPicture(upmsUser.getId()));
        model.addAttribute("page", p);
        return "/picture/list.jsp";
    }

    @RequestMapping("/add")
    public String add(){
        return  "/picture/add.jsp";
    }


    @RequestMapping("/insert")
    @ResponseBody
    public Tip insert(HttpServletRequest request, HttpServletResponse response, HttpSession session, String name, Integer size, String src) {
        Tip tip=new Tip();
        UpmsUser upmsUser = (UpmsUser) session.getAttribute(Global.UPMSUSER);
        Double kbSize=Double.valueOf(size)/1000;
        Integer sessionUserId=upmsUser.getId();
        String urlStr= UploadImageToString.uploadImageToString(src,request,sessionUserId); //图片的路径
        UpmsPicture upmsPicture=new UpmsPicture(sessionUserId,new Date(),urlStr,"1",Double.valueOf(kbSize));
        Tip tip1 = updateVolume(kbSize, session);
        if(tip1.getCode() !=null && tip1.getCode()==1){
            tip.setCode(2);
            tip.setMsg("可用空间不足，保存失败");
            tip.setSuccess(false);
            return tip;
        }
        Integer insert = upmsPictureService.insert(upmsPicture);
        if(insert>0){
            return  tip;
        }else{
            tip.setCode(1);
            tip.setMsg("上传失败");
            tip.setSuccess(false);
            return  tip;
        }
    }

    /**
     * 上传图片时修改该用户的可用空间大小
     */
    public Tip updateVolume(Double picSize,HttpSession session){
        UpmsUser upmsUser = (UpmsUser) session.getAttribute(Global.UPMSUSER);
        Integer sessionUserId = upmsUser.getId();
        UpmsUser byId = upmsUserService.findById(String.valueOf(sessionUserId));
        Double availableSpace = byId.getAvailableSpace();//获取到可用的空间
        Double availKB=availableSpace*1000;
        if(availKB>=picSize){
            Double  compute=availKB-picSize;
            upmsUser.setAvailableSpace(compute/1000);
            System.out.println(upmsUser);
            upmsUserService.update(upmsUser);
            return new Tip();
        }else{
            return  new Tip(1,"可用空间不足");
        }

    }

}
