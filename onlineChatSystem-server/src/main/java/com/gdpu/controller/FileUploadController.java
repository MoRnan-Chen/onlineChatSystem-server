package com.gdpu.controller;

import com.gdpu.result.Result;
import com.gdpu.utils.AliOssUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Slf4j
@RestController
public class FileUploadController
{
    //文件上传

    @Autowired
    private AliOssUtil aliOssUtil;
    @PostMapping("/upload")
    public Result<String> upload(MultipartFile file)
    {

        log.info("文件:{}",file);

        try
        {
            //原始文件名
            String originalFilename = file.getOriginalFilename();

            //截取原始文件名的后缀
            String extension = originalFilename.substring(originalFilename.lastIndexOf("."));

            //构造新文件名称
            String fileName = UUID.randomUUID().toString() + extension;
            String filePath = aliOssUtil.upload(fileName, file.getBytes());
            return Result.success(filePath);
        }
        catch (IOException e)
        {
            log.error("文件上传失败：{}",e);
        }

        return Result.error("文件上传失败") ;

    }
}
