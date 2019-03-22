package com.sonihr.service;/*
@author 黄大宁Rhinos
@date 2019/3/21 - 16:05
**/

import org.springframework.web.multipart.MultipartFile;

public interface IFileService {
    String upload(MultipartFile file, String path);
}
