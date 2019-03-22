package com.sonihr.service.impl;/*
@author 黄大宁Rhinos
@date 2019/3/21 - 16:06
**/

import com.google.common.collect.Lists;
import com.sonihr.service.IFileService;
import com.sonihr.util.FTPUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service("iFileService")
public class FileServiceImpl implements IFileService {
    private Logger logger = LoggerFactory.getLogger(FileServiceImpl.class);

    public String upload(MultipartFile file, String path){
        String fileName = file.getOriginalFilename();
        //扩展名
        String fileExtensionName = fileName.substring(fileName.lastIndexOf(".")+1);
        String uploadFileName = UUID.randomUUID().toString()+"."+fileExtensionName;
        logger.info("开始上传文件，上传文件的文件名：{},上传的路径：{}，新文件名：{}",fileName,path,uploadFileName);

        File fileDir = new File(path);
        if(!fileDir.exists()){
            fileDir.setWritable(true);//赋予可写的权限
            fileDir.mkdirs();
        }
        File targetFile = new File(path,uploadFileName);
        try {
            file.transferTo(targetFile);//文件已经上传至web服务器
            // TODO: 2019/3/4 将targetFile上传到我们的ftp服务器
            FTPUtil.uploadFile(Lists.<File>newArrayList(targetFile));
            // TODO: 2019/3/4 上传完之后，删除upload下面的文件
            targetFile.delete();
        } catch (IOException e) {
            logger.error("上传文件异常",e);
            return null;
        }
        return targetFile.getName();
    }

    public static void main(String[] args) {
        String fileName = "abc.jpg";
        System.out.println(fileName.substring(fileName.indexOf(".")+1));
    }
}