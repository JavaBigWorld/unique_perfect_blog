package com.yxj.blog.upload.controller;

import com.yxj.blog.common.Error.ErrorCode;
import com.yxj.blog.common.aop.log.LogAnnotation;
import com.yxj.blog.common.vo.Result;
import com.yxj.blog.upload.utils.QiniuUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * @author 从前慢
 * @RestController 返回json数据
 * @PostMapping Post请求
 * @Api swagger类上接口说明
 * @ApiOperation swagger方法上接口说明
 * @LogAnnotation 记录日志
 */
@RestController
@Api(value = "文件上传接口", tags = "文件上传管理" )
public class UploadController {


    @Resource
    private QiniuUtils qiniuUtils;

    @PostMapping("/upload")
    @ApiOperation(value = "文件上传",
            notes = "<span style='color:green;'>描叙:</span>&nbsp;&nbsp;文件上传的接口")
    @LogAnnotation(module="文件",operator="文件上传")
    public Result upload(@RequestParam("image")MultipartFile file) {
        /**
         * 原始文件名称
         * 比如 aa.png,那么originalFilename就是aa.png
         */
        String originalFilename = file.getOriginalFilename();
        /**
         * 唯一的文件名称
         * StringUtils.substringAfterLast(originalFilename, ".") 比如aa.png,则就是png
         */
        String fileName = UUID.randomUUID().toString() + "." + StringUtils.substringAfterLast(originalFilename, ".");
        /**
         * 上传文件 七牛云服务器
         * 降低 我们自身应用服务器的带宽消耗
         */
        Boolean upload = qiniuUtils.upload(file, fileName);
        if (upload) {
            return Result.success(QiniuUtils.url + "/" +fileName);
        }
        return  Result.fail(ErrorCode.UPLOAD_ERROR.getCode(),ErrorCode.UPLOAD_ERROR.getMsg());

    }
}
