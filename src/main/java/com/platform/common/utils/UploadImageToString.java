package com.platform.common.utils;

import sun.misc.BASE64Decoder;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class UploadImageToString {

    public static String uploadImageToString(String myfile,HttpServletRequest request,Integer userId){
        if (myfile == null) //图像数据为空
            return "";
        BASE64Decoder decoder = new BASE64Decoder();
        String str = "";
        try
        {
            String url=File.separator + "static" + File.separator + "img"+ File.separator + "picture"+ File.separator+userId;
            String path = request.getSession().getServletContext()
                    .getRealPath(File.separator + "static" + File.separator + "img"+ File.separator + "picture"+ File.separator+userId);
            File file = new File(path);
            if (!file.exists()) {
                file.mkdirs();
            }
            String newFileName = UUIDFactory.getStringId()+".png";
            //Base64解码
            byte[] b = decoder.decodeBuffer(myfile.substring(myfile.indexOf("base64,") + "base64,".length()));
            for(int i=0;i<b.length;++i)
            {
                if(b[i]<0)
                {//调整异常数据
                    b[i]+=256;
                }
            }
            //生成jpg图片
            File tempFile = new File(path+File.separator + newFileName);
            OutputStream out = new FileOutputStream(tempFile);
            out.write(b);
            out.flush();
            out.close();
            str=url+File.separator + newFileName;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str;
    }
}

