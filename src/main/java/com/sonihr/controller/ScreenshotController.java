package com.sonihr.controller;/*
@author 黄大宁Rhinos
@date 2019/3/21 - 15:23
**/


import com.google.common.collect.Maps;
import com.sonihr.service.IFileService;
import com.sonihr.util.PropertiesUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
public class ScreenshotController {

    @Autowired
    private IFileService iFileService;


    @RequestMapping("upload.do")
    @ResponseBody
    public String upload(@RequestParam(value="upload_file",required = false)MultipartFile file, HttpServletRequest request){
        String path = request.getSession().getServletContext().getRealPath("upload");
        System.out.println(path);
        String targetFileName = iFileService.upload(file,path);
        String url = PropertiesUtil.getProperty("ftp.server.http.prefix")+targetFileName;
        Map fileMap = Maps.newHashMap();
        url = "![]" + "(" + url +  ")";
        return url;
    }
}
